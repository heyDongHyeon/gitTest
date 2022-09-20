package geomex.xeus.ivcp.util;

import geomex.xeus.smartcity.Utils;

/**
 * IVCP 프로토콜 바디
 * - PrintWriter 객체로 전송
 * @author GEOMEX-NB-SS-ABYSER
 *
 */
public class IVCPBody {

	private static final String ID ="intellivix";
	private static final String PW ="pass0001!";
	//private static final String ID ="test";
	//private static final String PW ="12345!";
	private static final String ENTER_CODE = "\n";

	/**
	 * 로그인 바디
	 *
	 * @return
	 */
	public static String getLoginBody(){

		return "<?xml version='1.0' ?><Login ID='"+ID+"' PW='"+PW+"' DevName='IntelliVIX-G100 v4.0'/>"+ENTER_CODE;
	}

	/**
	 * 채널 오픈 바디
	 *
	 * @return
	 */
	public static String getStartUpBody(){

		return "<?xml version=\"1.0\" ?> <StartUp StreamFlag =\"0\" MetaFlag = \"0\" Option = \" \" />"+ENTER_CODE;
	}

	/**
	 * 카메라 리스트
	 *
	 * @return
	 */
	public static String getCamListBody(){

		return "<?xml version=\"1.0\" ?> <CameraList/>";
	}

	/**
	 * 이벤트 수신 요청
	 *
	 * @param guid 카메라 GUID
	 * @param uid 전송 받을 카메라 UID
	 * @param reslid 요청에 대한 응답 ID
	 * @param flag 메타 플레그
	 * @return
	 */
	public static String getEvetBody(String guid, String uid, String reslid, String flag){

		return "<?xml version=\"1.0\" ?> <LiveMeta GUID=\""+guid+"\" UID=\""+uid+"\" ResID=\""+reslid+"\" Flag=\""+flag+"\" Option=\"0\" />\n";
	}

}
