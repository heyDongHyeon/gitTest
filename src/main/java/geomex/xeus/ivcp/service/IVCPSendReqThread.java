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
import java.util.HashMap;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;

import geomex.xeus.ivcp.util.IVCPBody;
import geomex.xeus.ivcp.util.IVCPHead;
import geomex.xeus.util.code.BeanUtils;

public class IVCPSendReqThread extends Thread {

	// @Resource(name = "ivcpSendService")
	// private IVCPSendService service;
//	@Resource(name = "ivcpService")
//	private IVCPService ivcpService;
	// private IVCPSendService service = (IVCPSendService)
	// BeanUtils.getBean("ivcpSendService");
	private IVCPService ivcpService = (IVCPService) BeanUtils.getBean("ivcpService");
	SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	private IVCPacket vo = null;
	/**
	 * 0 : 배회 v 1 : 가상경로 통과 2 : 금지된 방향 이동 v 3 : 나타남 4 : 사라짐 5 : 멈춤 v 6 : 버려짐 v 7 :
	 * 급작스런 장면변화 8 : 연기 v 9 : 불꽃 v 10 : 쓰러짐 11 : 군집 12 : 싸움 13 : 위험한 횡단 14 : 추돌사고 v
	 * 15 : 해산 16 : 교통정체 17 : 색상 변화 v 18 : 차량 주차 19 : 제거됨 20 : 위험 수위 21 : 색상 검출 22 :
	 * 체류시간 1000 : 비디오 신호 아웃
	 */
	private final String[] EVENT_ALLOW = { "0", "2", "5", "6", "8", "9", "14", "17" };
	private String IP = "";
	private int PORT = 0;
	private int cnt = 0;
	private Socket socket = null;
	private Socket testSocket = null;
	private PrintWriter pw = null;
	private BufferedReader br = null;

	public void init(IVCPacket vo) {
		this.vo = vo;
		this.IP = vo.getIVCPConnIp();
		this.PORT = vo.getIVCPConnReqPort();
	}

	/**
	 * 소켓을 초기화 한다.
	 * @throws IOException 
	 *
	 */
	public void initSocket() throws IOException {
		try {
//			if (this.socket != null)
//				this.socket.close();
//			this.socket = new Socket(IP, PORT);
//			this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//			this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			if(this.vo.getConnect()){
				this.socket = new Socket();// 예외처리안했다.
				this.socket.setReuseAddress(true);
				socket.connect(new InetSocketAddress(IP, PORT));
				this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			}
		} catch (Exception e) {
			if(!this.socket.isClosed())this.socket.close();
			this.vo.setConnect(false);
//			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!REQ TRY. BUT FAIL");
		}
	}

	/**
	 * 패킷을 전송한다.
	 * @throws IOException 
	 */
	private void sendPacket(String packet) throws IOException {
		if (packet.indexOf("U_U 0 0 0") == -1 && packet.indexOf("geomex") == -1) {
			System.out.println("SEND PACKET " + IP + ":" + PORT +"\n"+ packet);
		}
		this.pw.print(packet);
		this.pw.flush();
	}

	/**
	 * IVCP 패킷을 전송한다.
	 * 
	 * @throws Exception
	 */
	private void sandPacket() throws Exception {
		long start = System.currentTimeMillis();
		long start2 = System.currentTimeMillis();
		boolean cam = false;
		boolean sendAlive=false;
		while (true) {
			Thread.sleep(1000);
			long end = System.currentTimeMillis();
			if (vo.isSocketConn(this.socket) && vo.getConnect()) { // 연결되어있으면
				String packet = ""; // packet "fh chrlghk
				/**
				 * 8~10초에 1번 서버에 얼라이브 패킷 전송.	
				 */
				if(cam == false) {
					long end2 = System.currentTimeMillis();
					if((end2-start2)/1000.0 >120) {
						System.out.println(format.format(System.currentTimeMillis())+"                       RESTART->"+IP+"\n\n");
						vo.setConnect(false);	//계속 receive에서 아무것도 안주면 req연결을 강제로 끊는다.
						start2 = System.currentTimeMillis();
					}
				}
				
				if (sendAlive && (end - start) / 1000.0 > 10) { 	//10초에 한번씩 생존신고
					sendPacket(IVCPHead.getKEEPALIVE());	
					start = System.currentTimeMillis();
				}
				if (vo.getIVCPRecivePacket().equals("")) {
					//아무데이터도 안오면 다시 while문을 돈다.
					continue;
				}
				if (vo.getIVCPRecivePacket().indexOf("N_OPTION") != -1) {
					// 로그인
					packet = IVCPHead.getLOGIN(IVCPBody.getLoginBody().length()) + IVCPBody.getLoginBody();
					sendPacket(packet);
					vo.init();

				} else if (vo.getIVCPRecivePacket().indexOf("N_LOGIN") != -1) {
					// 채널 오픈
					packet = IVCPHead.getSTARTUP(IVCPBody.getStartUpBody().length()) + IVCPBody.getStartUpBody();
					sendPacket(packet);
					vo.init();
					
				} else if (vo.getIVCPRecivePacket().indexOf("N_STARTUP") != -1) {
					// 카메라 목록 요청
					packet = IVCPHead.getCAMLIST(IVCPBody.getCamListBody().length()) + IVCPBody.getCamListBody();
					sendPacket(packet);
					vo.init();
				} else if (vo.getIVCPRecivePacket().indexOf("N_CAMLIST") != -1) {
				
					JSONObject json = (JSONObject) org.json.XML.toJSONObject(vo.getIVCPRecivePacket()).get("CameraList");

					JSONArray jsonList = new JSONArray();

					if (json.has("CamInfo")) {

						try {
							 jsonList = json.getJSONArray("CamInfo");

						} catch (org.json.JSONException je) {
							jsonList.put(json.getJSONObject("CamInfo"));
						}

						vo.setIVCPCamList(jsonList);
						
						for (int i = 0; i < vo.getIVCPCamList().length(); i++) {
							JSONObject camJson = (JSONObject) vo.getIVCPCamList().get(i);
							try {
								HashMap<String, String> ivcpMap = new HashMap<String, String>();
								ivcpMap.put("guid", camJson.optString("Guid"));
								IVCPVo ivcpVo = ivcpService.getItem(ivcpMap); // 왜 이게 null포인트가 뜰까..
								if (ivcpVo == null) {
									ivcpMap.put("ivcp_guid", camJson.optString("Guid"));
									ivcpMap.put("ivcp_uid", camJson.optString("UID"));
									ivcpMap.put("cctv_nm", camJson.optString("Name"));
									
									ivcpService.add(ivcpMap);
									System.out.println(format.format(System.currentTimeMillis()) + "  새로운 smartCCTV생성("+IP+ ")  guid = " + camJson.optString("Guid"));
								}
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println(IP+"->"+format.format(System.currentTimeMillis())+getPrintStackTrace(e));
								System.out.println(format.format(System.currentTimeMillis()) + "  새로운 smartCCTV생성 실패("+IP+")  guid = " + camJson.optString("Guid"));
							}
							
							// 이벤트 시작 ( ADD )
							String evtBody = IVCPBody.getEvetBody(camJson.optString("Guid"), camJson.optString("UID"),
									"0", "3");
							packet = IVCPHead.getEVET(evtBody.length()) + evtBody;
							sendPacket(packet);

						}
					}
//					System.out.println(format.format(System.currentTimeMillis())+"!!!!!!!!!!!!!!COMPLETE PROCESS "+cnt+" !!!!!!!!!!!!!..........>"+IP+"\n\n");
					cam=true;
					if(!sendAlive) {
						sendAlive = true;
						start = System.currentTimeMillis()+100000; //백초후에 시작.
					}

					vo.init();	//vo의 string부분을 " "초기화시켜준다. 
				} /*
					 * else if (vo.getIVCPRecivePacket().indexOf("L_EVENTINFO") != -1) { // 이벤트 처리.
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
							 */

			} else {	//이 소켓이 아닌 나머지 두소켓중에서 연결이 끊겼을 때.
				System.out.println(format.format(System.currentTimeMillis())+"                       DISCONNECT(REQ)->"+IP);
				vo.setNAI(IVCPHead.getNAI().toString());
				vo.closeSocket(this.socket, this.pw, this.br);
				break;
			}

		}
	}

	public String getPrintStackTrace(Exception e) {

		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));

		return errors.toString();
	}
	@Override
	public void run() {
		try {

			while (true) {
				if (vo.isSocketConn(this.socket) && vo.getConnect()) {	//해당 소켓이 연결되었을 때. 소켓이 널이아니고 소켓이 닫히지 않고 소켓이 연결되어있을 때.
					try {
//						Thread.sleep(1000);		//세 개중에서 제일 짧게..
						sendPacket(vo.getNAI());
						cnt++;
						System.out.println(format.format(System.currentTimeMillis())+"                       REQ START "+cnt+" ->"+IP+"\n");
//						System.out.println("NAI = "+vo.getNAI());
						System.out.println("\n");
						

						sandPacket();
						
//						vo.setReqConnect(false);
//						break;
					}catch(Exception e) {
						System.out.println(format.format(System.currentTimeMillis())+"                       REQ IN CATCH"+"->"+IP+"\n");
						this.vo.setConnect(false);
						this.vo.closeSocket(this.socket, this.pw, this.br);
						e.printStackTrace();
					}

				} else {
					initSocket();	//만약 연결되면 위에 if문 3가지를 충족시켜줌.. 연결 안되면 계속 initSocket을 탐..
				}
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
