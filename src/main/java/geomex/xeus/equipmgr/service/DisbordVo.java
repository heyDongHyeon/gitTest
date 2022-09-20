package geomex.xeus.equipmgr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 파일명 :  UserVo.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017-05-31      이주영          최초 생성
 * 2017-12-11	   이은규		   로그인 제한 횟수, 사용자 등록IP 추가
 * 2018-01-08      이은규          subDir 생성(업로드 경로 서브 폴더이름)
 *
 * </pre>
 *
 * @since   :  2017. 5. 31.
 * @version :  1.0
 * @see
 */
public class DisbordVo {
	//disbord
//	@NotNull(message="사이트명은 필수사항 입니다.")
//	@NotEmpty(message="사이트명은 필수사항 입니다.")
//	@Size(min=1, max=50, message="사이트명은 최대 50자 까지 입력하실 수 있습니다.")
	private String mgrNo;
	private String bordNm;
	private String gbnNm;
	private String makerNm;
	private String tecSpe;
	private String locDesc;
	private String rmark;
	private String tmLat;
	private String tmLon;
	private String strMgr;

	private String phoneNumber;
	private String expressionMethod;
	private String standard;
	private String installDate;
	private String installCost;
	private String note;


	private List<Map<String, Object>> mgr;
	public String getMgrNo() {
		return mgrNo;
	}
	public void setMgrNo(String mgrNo) {
		this.mgrNo = mgrNo;
	}
	public String getBordNm() {
		return bordNm;
	}
	public void setBordNm(String bordNm) {
		this.bordNm = bordNm;
	}
	public String getGbnNm() {
		return gbnNm;
	}
	public void setGbnNm(String gbnNm) {
		this.gbnNm = gbnNm;
	}
	public String getMakerNm() {
		return makerNm;
	}
	public void setMakerNm(String makerNm) {
		this.makerNm = makerNm;
	}
	public String getTecSpe() {
		return tecSpe;
	}
	public void setTecSpe(String tecSpe) {
		this.tecSpe = tecSpe;
	}
	public String getLocDesc() {
		return locDesc;
	}
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}
	public String getRmark() {
		return rmark;
	}
	public void setRmark(String rmark) {
		this.rmark = rmark;
	}
	public String getTmLat() {
		return tmLat;
	}
	public void setTmLat(String tmLat) {
		this.tmLat = tmLat;
	}
	public String getTmLon() {
		return tmLon;
	}
	public void setTmLon(String tmLon) {
		this.tmLon = tmLon;
	}
	public String getStrMgr() {
		return strMgr;
	}
	public void setStrMgr(String strMgr) {
		this.strMgr = strMgr;
	}
	public List<Map<String, Object>> getMgr() {
		return mgr;
	}
	public void setMgr(List<Map<String, Object>> mgr) {
		this.mgr = mgr;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getExpressionMethod() {
		return expressionMethod;
	}
	public void setExpressionMethod(String expressionMethod) {
		this.expressionMethod = expressionMethod;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public String getInstallDate() {
		return installDate;
	}
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	public String getInstallCost() {
		return installCost;
	}
	public void setInstallCost(String installCost) {
		this.installCost = installCost;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}


	@Override
	public String toString() {
		return "DisbordVo [mgrNo=" + mgrNo + ", bordNm=" + bordNm + ", gbnNm=" + gbnNm + ", makerNm=" + makerNm
				+ ", tecSpe=" + tecSpe + ", locDesc=" + locDesc + ", rmark=" + rmark + ", tmLat=" + tmLat + ", tmLon="
				+ tmLon + ", strMgr=" + strMgr + ", phoneNumber=" + phoneNumber + ", expressionMethod="
				+ expressionMethod + ", standard=" + standard + ", installDate=" + installDate + ", installCost="
				+ installCost + ", note=" + note + ", mgr=" + mgr + ", getMgrNo()=" + getMgrNo() + ", getBordNm()="
				+ getBordNm() + ", getGbnNm()=" + getGbnNm() + ", getMakerNm()=" + getMakerNm() + ", getTecSpe()="
				+ getTecSpe() + ", getLocDesc()=" + getLocDesc() + ", getRmark()=" + getRmark() + ", getTmLat()="
				+ getTmLat() + ", getTmLon()=" + getTmLon() + ", getStrMgr()=" + getStrMgr() + ", getMgr()=" + getMgr()
				+ ", getPhoneNumber()=" + getPhoneNumber() + ", getExpressionMethod()=" + getExpressionMethod()
				+ ", getStandard()=" + getStandard() + ", getInstallDate()=" + getInstallDate() + ", getInstallCost()="
				+ getInstallCost() + ", getNote()=" + getNote() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
