package geomex.xeus.ivcp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geomex.xeus.ivcp.util.IVCPHead;
import geomex.xeus.util.code.BeanUtils;

public class IVCPSendEventThread extends Thread {

	private IVCPSendService service = (IVCPSendService) BeanUtils.getBean("ivcpSendService");
	private Logger logger = LoggerFactory.getLogger("geomex.xeus.ivcp");
	private final String[] EVENT_ALLOW = { "0", "2", "5", "6", "8", "9", "14", "17" };
	SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

	private Socket socket = null;
	private PrintWriter pw = null;
	private BufferedReader br = null;
	private IVCPacket vo = null;
	private String IP = "";
	private int PORT = 0;
	private int cnt = 0;
	private boolean eventStart=false;


	public void init(IVCPacket vo) {
		this.vo = vo;
		this.IP = vo.getIVCPConnIp();
		this.PORT = vo.getIVCPConnEventPort();
	}

	/**
	 * 소켓을 초기화 한다.
	 * @throws IOException 
	 *
	 */
	public void initSocket() throws IOException {

		try {
			this.socket = new Socket();// 예외처리안했다.
			this.socket.setReuseAddress(true);
			socket.connect(new InetSocketAddress(IP, PORT));
			this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.socket.setSoTimeout(1000*60*60*24);	//하루동안 데이터가 안들어오면 에러..
			this.vo.setConnect(true);
		} catch (Exception e) {
			if(!this.socket.isClosed())this.socket.close();
			this.vo.setConnect(false);
//			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!EVENT TRY. BUT FAIL");
//			e.printStackTrace();
		}
	}

	/**
	 * 패킷을 전송한다.
	 */
	private void sendPacket(String packet) {
//		System.out.println("SEND PACKET " + IP + ":" + PORT + " : " + packet);
		this.pw.print(packet);
		this.pw.flush();
	}

	/**
	 * IVCP 응답 패킷을 수신한다.
	 * 
	 * @throws Exception
	 */
	private void recivePacket() throws Exception {
//		String keepAlive="U_U 0 0 0\n";
//		long time=500;
		while (true) {
			Thread.sleep(300);
			String line = "";
			if (vo.isSocketConn(this.socket) && vo.getConnect()) {
				int n=0;
				while (true) {
					int data = br.read();
//					System.out.println("data = "+data);
					n=data;
					if(n == -1) break;	//아무것도 안왔다는 것.
					if(data == 0) break;
					char ch = (char) data;
					line += (ch);
				}
				if(n==-1) {
					System.out.println((format.format(System.currentTimeMillis()))+"  "+IP+"->-1이옴");
					vo.setConnect(false);
					continue;
				}
//				System.out.println(line);
				if (line != null && line.indexOf("L_EVENTINFO") == -1) {	//EVENTINFO가 포함되지 않으면
					// EVENTINFO가 아니면
//					System.out.println("IP = "+IP+"\n"+"receive!!!!\n"+line);
					vo.setIVCPRecivePacket(line);
				} else if (line != null && line.indexOf("L_EVENTINFO") != -1) {		//EVENTINFO가 포함되면
					if(!eventStart) {
						 System.out.println(format.format(System.currentTimeMillis())+"!!!!!!!!!!!!!!!!!!!!!!START "+cnt+" !!!!!!!!!!!!!!!!  ..........>"+IP+"\n\n");
						 eventStart=true;
					}
					if(line.indexOf("U_U 0 0 0") != -1) {
						line=line.replaceAll("U_U 0 0 0\n", "");
					}
//					time=1000;
//					System.out.println(line);
					JSONObject json = (JSONObject) org.json.XML.toJSONObject(line);
					json = (JSONObject) json.get("Event");
					if (geomex.xeus.util.code.Utils.contains(EVENT_ALLOW, json.get("EvtType").toString()) ) {	//
						service.eventProcess(json, vo);
					}
				}
				
			} else {	//연결이 끊어졌을 떄
				System.out.println(format.format(System.currentTimeMillis())+"                       DISCONNECT(EVENT)->"+IP);
				eventStart=false;
				vo.setNAI(IVCPHead.getNAI().toString());
				vo.closeSocket(this.socket, this.pw, this.br);
				break;
			}
		}
	}
	/*		*//**
				 * IVCP 패킷을 전송한다.
				 * 
				 * @throws Exception
				 */

	/*
	 * private void aaa() throws Exception {
	 * 
	 * if (vo.getIVCPRecivePacket().indexOf("L_EVENTINFO") != -1) { // 이벤트 처리.
	 * System.out.println("==============" + IP + PORT +
	 * " Event recv============="); System.out.println(vo.getIVCPRecivePacket());
	 * System.out.println("==============Event recv End============="); JSONObject
	 * json = (JSONObject) org.json.XML.toJSONObject(vo.getIVCPRecivePacket()); json
	 * = (JSONObject) json.get("Event");
	 * 
	 * 
	 * //이벤트 종료 ( UPDATE ) String evtBody =IVCPBody.getEvetBody(
	 * json.optString("GUID") , json.optString("UID") , "0" , "2");
	 * 
	 * packet = IVCPHead.getEVET(evtBody.length())+evtBody; sendPacket(packet);
	 * 
	 * System.out.println(json.get("EvtType").toString()); if
	 * (geomex.xeus.util.code.Utils.contains(EVENT_ALLOW,
	 * json.get("EvtType").toString())) { // System.out.println(json); // F-KEY
	 * System.out.println("카메라 GUID : " + json.get("GUID")); // F-KEY
	 * System.out.println("카메라 번호 : " + json.get("UID")); // YYYY_MMDD_H24mmss_msse
	 * System.out.println("시작시간 : " + json.get("Start"));
	 * System.out.println("종료시간 : " + json.get("End")); // 초단위 ex) 1.2 //
	 * System.out.println("체류시간 : " + json.get("DwellTime")); // 0 : 시작, 1: 종료
	 * System.out.println("이벤트 상태 : " + json.get("EvtStatus")); // PK
	 * System.out.println("이벤트 ID  : " + json.get("EvtID"));
	 *//**
		 * 0 : 배회 v 1 : 가상경로 통과 2 : 금지된 방향 이동 v 3 : 나타남 4 : 사라짐 5 : 멈춤 v 6 : 버려짐 v 7 :
		 * 급작스런 장면변화 8 : 연기 v 9 : 불꽃 v 10 : 쓰러짐 11 : 군집 12 : 싸움 13 : 위험한 횡단 14 : 추돌사고 v
		 * 15 : 해산 16 : 교통정체 17 : 색상 변화 v 18 : 차량 주차 19 : 제거됨 20 : 위험 수위 21 : 색상 검출 22 :
		 * 체류시간 1000 : 비디오 신호 아웃
		 *//*
			 * System.out.println("이벤트 종류 : " + json.get("EvtType")); //0 : 사람, 1 : 차량, 2:
			 * 미확인 System.out.println("객체 종류 : " + json.get("ObjType"));
			 * 
			 * // service.eventProcess(json, vo); }
			 * 
			 * vo.init(); }
			 * 
			 * }
			 */
	public String getPrintStackTrace(Exception e) {
	         
	        StringWriter errors = new StringWriter();
	        e.printStackTrace(new PrintWriter(errors));
	         
	        return errors.toString();
	}
	@Override
	public void run() {

		try {

			while (true) {
				if (vo.isSocketConn(this.socket) && vo.getConnect()) {
					try {
//						Thread.sleep(1000);
						sendPacket(vo.getNAI());
						cnt++;
						System.out.println(format.format(System.currentTimeMillis())+"                       EVENT START(wow) "+cnt+" ->"+IP+"\n");
//						System.out.println("NAI = "+vo.getNAI());
						System.out.println("\n");

						recivePacket();
//				    		  break;
					} catch (Exception e) {
						System.out.println(format.format(System.currentTimeMillis())+"                       EVENT IN CATCH"+"->"+IP+"\n");
						eventStart=false;
						vo.setConnect(false);// 왜냐하면은 연결이 끊겼으니까
						vo.closeSocket(this.socket, this.pw, this.br);
						e.printStackTrace();
						System.out.println(IP+"->"+format.format(System.currentTimeMillis())+getPrintStackTrace(e));
					}
				} else {
					initSocket();
				}

				Thread.sleep(3000);

			}

		} catch (Exception e) {

		} finally {

		}
	}

}