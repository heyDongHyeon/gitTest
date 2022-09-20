package geomex.xeus.ivcp.service;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

public class IVCPacket {

	private String IVCPRecivePacket = "";//수신 패킷
	private JSONArray IVCPCamList;//카메라 리스트
	private String IVCPConnIp;//소켓 IP
	private String NAI;//처음 소켓 연결할 때 필요한 NAI정보
	private int IVCPConnReqPort;//응답 포트
	private int IVCPConnEventPort;//요청 포트
	private int IVCPConnStreamPort;//스트림 포트
	private boolean reqConnect; //send연결
	private boolean eventConnect; //receive연결
	private boolean streamConnect; //stream연결
	
	private boolean connect; //stream연결
	
	
	public boolean getConnect() {
		return this.connect;
	}

	public void setConnect(boolean connect){
		this.connect = connect;
	}
	public boolean getReqConnect() {
		return reqConnect;
	}

	public void setReqConnect(boolean reqConnect){
		this.reqConnect = reqConnect;
	}
	public boolean getEventConnect() {
		return eventConnect;
	}

	public void setEventConnect(boolean eventConnect){
		this.eventConnect = eventConnect;
	}
	public boolean getStreamConnect() {
		return streamConnect;
	}

	public void setStreamConnect(boolean streamConnect){
		this.streamConnect = streamConnect;
	}
	public String getNAI() {
		return NAI;
	}

	public void setNAI(String NAI){
		this.NAI = NAI;
	}
	public String getIVCPConnIp() {
		return IVCPConnIp;
	}

	public void setIVCPConnIp(String iVCPConnIp) {
		IVCPConnIp = iVCPConnIp;
	}

	public int getIVCPConnReqPort() {
		return IVCPConnReqPort;
	}

	public void setIVCPConnReqPort(int iVCPConnReqPort) {
		IVCPConnReqPort = iVCPConnReqPort;
	}

	public int getIVCPConnEventPort() {
		return IVCPConnEventPort;
	}

	public void setIVCPConnEventPort(int iVCPConnEventPort) {
		IVCPConnEventPort = iVCPConnEventPort;
	}

	public int getIVCPConnStreamPort() {
		return IVCPConnStreamPort;
	}

	public void setIVCPConnStreamPort(int iVCPConnStreamPort) {
		IVCPConnStreamPort = iVCPConnStreamPort;
	}

	public String getIVCPRecivePacket() {
		return IVCPRecivePacket;
	}

	public void setIVCPRecivePacket(String iVCPRecivePacket) {
		
		IVCPRecivePacket = iVCPRecivePacket;
	}

	public JSONArray getIVCPCamList() {
		return IVCPCamList;
	}

	public void setIVCPCamList(JSONArray iVCPCamList) {
		IVCPCamList = iVCPCamList;
		
	}

	public void init(){

		IVCPRecivePacket ="";
		IVCPCamList =null;
	}



	 /**
	  *  코드를 반환한다.
		    0 : 배회 v
		    1 : 가상경로 통과
		    2 : 금지된 방향 이동 v
		    3 : 나타남
		    4 : 사라짐
		    5 : 멈춤 v
		    6 : 버려짐 v
		    7 : 급작스런 장면변화
		    8 : 연기 v
		    9 : 불꽃 v
		    10 : 쓰러짐
		    11 : 군집
		    12 : 싸움
		    13 : 위험한 횡단
		    14 : 추돌사고 v
		    15 : 해산
		    16 : 교통정체
		    17 : 색상 변화
		    18 : 차량 주차 v
		    19 : 제거됨
		    20 : 위험 수위
		    21 : 색상 검출
		    22 : 체류시간
	    	1000 : 비디오 신호 아웃

	   * @return String
	   */
	public String getEventCode(String codeStr){
		String evtNm = "";
		int code = Integer.parseInt(codeStr);

		switch(code){
			case 0 :
				evtNm = "배회";
			break;
			case 2 :
				evtNm = "금지된 방향 이동";

				break;
			case 5 :
				evtNm = "멈춤";
				break;
			case 6 :
				evtNm = "버려짐";
				break;
			case 8 :
				evtNm = "연기";
				break;
			case 9 :
				evtNm = "불꽃";
				break;
			case 14 :
				evtNm = "추돌사고";
				break;
			case 17 :
				evtNm = "차량 주차";
				break;
		}

		return evtNm;
	}

	/**
	 * 식별 객체 타입을 반환한다.
	 *
	 * @param codeStr
	 * @return
	 */
	public String getObjCode(String codeStr){
		String evtNm = "";
		int code = Integer.parseInt(codeStr);

		switch(code){
		case 0 :
			evtNm = "사람";
			break;
		case 1 :
			evtNm = "차량";

			break;
		case 2 :
			evtNm = "미확인";
			break;
		default :
			evtNm = "미확인";
			break;
		}

		return evtNm;
	}

	/**
	 * 식별 객체 타입을 반환한다.
	 *
	 * @param codeStr
	 * @return
	 */
	public String getContent(String codeStr, JSONObject json){
		String evtNm = "";
		int code = Integer.parseInt(codeStr);

		switch(code) {
		case 0 :
			evtNm = "배회 중 대상(대상 : "+this.getObjCode(json.opt("ObjType").toString())+")이 감지 되었습니다.";
		break;
		case 2 :
			evtNm = "금지된 방향으로 이동 중 인 대상(대상 : "+this.getObjCode(json.opt("ObjType").toString())+")이 감지 되었습니다.";
			break;
		case 5 :
			evtNm = "멈춤 상태인 대상(대상 : "+this.getObjCode(json.opt("ObjType").toString())+")이 감지 되었습니다.";
			break;
		case 6 :
			evtNm = "버려짐( 대상 : "+this.getObjCode(json.opt("ObjType").toString())+")이 감지 되었습니다." ;
			break;
		case 8 :
			evtNm = "연기( 대상 : "+this.getObjCode(json.opt("ObjType").toString())+")가 감지 되었습니다." ;
			break;
		case 9 :
			evtNm = "불꽃( 대상 : "+this.getObjCode(json.opt("ObjType").toString())+")이 감지 되었습니다." ;
			break;
		case 14 :
			evtNm = "추돌 사고( 대상 : "+this.getObjCode(json.opt("ObjType").toString())+")가 발생하였습니다." ;
			break;
		case 17 :
			evtNm = "정지 대상( 대상 : "+this.getObjCode(json.opt("ObjType").toString())+")이 감지 되었습니다.";

			break;
	}

		return evtNm;
	}
	public boolean closeSocket(Socket socket, PrintWriter pw, BufferedReader br){
		try {
			if (br != null )	br.close();
			if (pw != null )	pw.close();
			if (socket != null)	socket.close();
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	public boolean isSocketConn(Socket socket){
		if(socket != null && !socket.isClosed()) {return true;}
		else {return false;}
	}
	public boolean isAllSocketConn(){
		if(getReqConnect() && getEventConnect() && getStreamConnect()) {
			return true;
		}
		return false;
	}


	//test 용.
	/*	Ipublic static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		String IP = "115.93.67.45";
	VCPSendReqThread req = new IVCPSendReqThread(IP, 4204);
		IVCPSendEventThread event = new IVCPSendEventThread(IP, 4205);
		IVCPSendStreamThread stream = new IVCPSendStreamThread(IP, 4206);

		req.start();
		event.start();
		stream.start();
	}*/

}
