package geomex.xeus.ivcp.util;

import geomex.xeus.smartcity.Utils;

/**
 * IVCP 프로토콜 헤더
 * - PrintWriter 객체로 전송
 * @author GEOMEX-NB-SS-ABYSER
 *
 */
public class IVCPHead {

	private static final String SERVICE_ID ="geomex@geomex.co.kr";
	private static final String ENTER_CODE = "\n";

	private static String NAI = SERVICE_ID;
	private static String LOGIN = "N_LOGIN 0 0 69"+ENTER_CODE;
	private static String LOGINOUT = "N_LOGOUT"+ENTER_CODE;
	private static String CAMLIST  = "N_CAMLIST 0 0 35"+ENTER_CODE;
	private static String STARTUP = "N_STARTUP 0 0 32"+ENTER_CODE;
	private static String SHUTDOWN = "N_SHUTDOWN"+ENTER_CODE;
	private static String KEEPALIVE = "U_U 0 0 0"+ENTER_CODE;
	private static String EVET = "C_LIVEMETA 0 0 ???"+ENTER_CODE;

	//NAI 패킷 3개의 소켓 연결 시 모두 등록이 필요하다.
	public static String getKEEPALIVE() {
		return KEEPALIVE;
	}
	//NAI 패킷 3개의 소켓 연결 시 모두 등록이 필요하다.
	public static String getNAI() {
		NAI = SERVICE_ID +" 0 "+Utils.getDate()+ENTER_CODE;
		return NAI;
	}
	//로그인 패킷 NAI 패킷 이후 필요하다.
	public static String getLOGIN(int len) {
		return "N_LOGIN 0 0 "+len+ENTER_CODE;
	}
	//채널 오픈 패킷 데이터 수신을 위해 필요하다.
	public static String getSTARTUP(int len) {
		return  "N_STARTUP 0 0 "+len+ENTER_CODE;
	}
	//채널 오픈 패킷 데이터 수신을 위해 필요하다.
	public static String getCAMLIST(int len) {
		return "N_CAMLIST 0 0 "+len+ENTER_CODE;
	}
	//이벤트 수신 패킷 이벤트 수신을 위해 필요하다.
	public static String getEVET(int len) {
		return  "C_LIVEMETA 0 0 "+len+ENTER_CODE;
	}
	//채널을 닫을때 필요하다 ( 추후 구현 예정 )
	public static String getSHUTDOWN() {
		return SHUTDOWN;
	}
	//로그아웃시 필요하다 ( 추후 구현 예정 )
	public static String getLOGINOUT() {
		return LOGINOUT;
	}
}
