package geomex.xeus.ivcp.service;

/**
 * <pre>
 * 파일명 :  AccessVo.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2016-02-01      홍길동          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 6. 22.
 * @version :  1.0
 * @see
 */

public class IVCPVo {

	private String mgrNo = "";//관리 번호
	private String ivcpUid = "";//cctv_key
	private String cctvNm = "";//cctv 명
	private String cctvArea = "";//cctv 구역 ( 대전/조치원 )
	private String cctvLen = "";//
	private String cctvCate = "";//주추 / 사오리
	private String locLat = "";//경도
	private String locLon = "";//위도
	private static boolean isConnect=true;

	public static boolean getIsConnect() {
		return isConnect;
	}
	public static void setIsConnect(boolean isConnects) {
		isConnect = isConnects;
	}
	public String getLocLat() {
		return locLat;
	}
	public void setLocLat(String locLat) {
		this.locLat = locLat;
	}
	public String getLocLon() {
		return locLon;
	}
	public void setLocLon(String locLon) {
		this.locLon = locLon;
	}
	public String getMgrNo() {
		return mgrNo;
	}
	public void setMgrNo(String mgrNo) {
		this.mgrNo = mgrNo;
	}
	public String getIvcpUid() {
		return ivcpUid;
	}
	public void setIvcpUid(String ivcpUid) {
		this.ivcpUid = ivcpUid;
	}
	public String getCctvNm() {
		return cctvNm;
	}
	public void setCctvNm(String cctvNm) {
		this.cctvNm = cctvNm;
	}
	public String getCctvArea() {
		return cctvArea;
	}
	public void setCctvArea(String cctvArea) {
		this.cctvArea = cctvArea;
	}
	public String getCctvLen() {
		return cctvLen;
	}
	public void setCctvLen(String cctvLen) {
		this.cctvLen = cctvLen;
	}
	public String getCctvCate() {
		return cctvCate;
	}
	public void setCctvCate(String cctvCate) {
		this.cctvCate = cctvCate;
	}


}
