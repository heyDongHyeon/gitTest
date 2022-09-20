package geomex.xeus.equipmgr.service;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class AwsVo {
	//aws
//	@NotNull(message="사이트명은 필수사항 입니다.")
//	@NotEmpty(message="사이트명은 필수사항 입니다.")
//	@Size(min=1, max=50, message="사이트명은 최대 50자 까지 입력하실 수 있습니다.")
	private String fclGbnNm;
	private String locNm;
	private String locAddr;
	private String locAddrDetail;
	private String rsInfo;
	private String stdDate;
	private String endDate;
	private String bizExp;
	private String warnDsc;
	private String commMtd;
	private String bizNm;
	private String docMgrNo;
	private String tmLat;
	private String tmLon;
	private String mgrNo;
	//mgr
	private String mgrSpot;
	private String mgrBelong;
	private String mgrLevel;
	private String mgrNm;
	private String mgrTel;
	private String mgrPhone;
	
	//insert할 때 parameter가 list로 들어옴.
	private List<Map<String, Object>> mgr;
	private String strMgr;
	public String getFclGbnNm() {
		return fclGbnNm;
	}
	public void setFclGbnNm(String fclGbnNm) {
		this.fclGbnNm = fclGbnNm;
	}
	public String getLocNm() {
		return locNm;
	}
	public void setLocNm(String locNm) {
		this.locNm = locNm;
	}
	public String getLocAddr() {
		return locAddr;
	}
	public void setLocAddr(String locAddr) {
		this.locAddr = locAddr;
	}
	public String getLocAddrDetail() {
		return locAddrDetail;
	}
	public void setLocAddrDetail(String locAddrDetail) {
		this.locAddrDetail = locAddrDetail;
	}
	public String getRsInfo() {
		return rsInfo;
	}
	public void setRsInfo(String rsInfo) {
		this.rsInfo = rsInfo;
	}
	public String getStdDate() {
		return stdDate;
	}
	public void setStdDate(String stdDate) {
		this.stdDate = stdDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getBizExp() {
		return bizExp;
	}
	public void setBizExp(String bizExp) {
		this.bizExp = bizExp;
	}
	public String getWarnDsc() {
		return warnDsc;
	}
	public void setWarnDsc(String warnDsc) {
		this.warnDsc = warnDsc;
	}
	public String getCommMtd() {
		return commMtd;
	}
	public void setCommMtd(String commMtd) {
		this.commMtd = commMtd;
	}
	public String getBizNm() {
		return bizNm;
	}
	public void setBizNm(String bizNm) {
		this.bizNm = bizNm;
	}
	public String getDocMgrNo() {
		return docMgrNo;
	}
	public void setDocMgrNo(String docMgrNo) {
		this.docMgrNo = docMgrNo;
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
	public String getMgrNo() {
		return mgrNo;
	}
	public void setMgrNo(String mgrNo) {
		this.mgrNo = mgrNo;
	}
	public String getMgrSpot() {
		return mgrSpot;
	}
	public void setMgrSpot(String mgrSpot) {
		this.mgrSpot = mgrSpot;
	}
	public String getMgrBelong() {
		return mgrBelong;
	}
	public void setMgrBelong(String mgrBelong) {
		this.mgrBelong = mgrBelong;
	}
	public String getMgrLevel() {
		return mgrLevel;
	}
	public void setMgrLevel(String mgrLevel) {
		this.mgrLevel = mgrLevel;
	}
	public String getMgrNm() {
		return mgrNm;
	}
	public void setMgrNm(String mgrNm) {
		this.mgrNm = mgrNm;
	}
	public String getMgrTel() {
		return mgrTel;
	}
	public void setMgrTel(String mgrTel) {
		this.mgrTel = mgrTel;
	}
	public String getMgrPhone() {
		return mgrPhone;
	}
	public void setMgrPhone(String mgrPhone) {
		this.mgrPhone = mgrPhone;
	}
	public List<Map<String, Object>> getMgr() {
		return mgr;
	}
	public void setMgr(List<Map<String, Object>> mgr) {
		this.mgr = mgr;
	}
	public String getStrMgr() {
		return strMgr;
	}
	public void setStrMgr(String strMgr) {
		this.strMgr = strMgr;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AwsVo [fclGbnNm=");
		builder.append(fclGbnNm);
		builder.append(", locNm=");
		builder.append(locNm);
		builder.append(", locAddr=");
		builder.append(locAddr);
		builder.append(", locAddrDetail=");
		builder.append(locAddrDetail);
		builder.append(", rsInfo=");
		builder.append(rsInfo);
		builder.append(", stdDate=");
		builder.append(stdDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", bizExp=");
		builder.append(bizExp);
		builder.append(", warnDsc=");
		builder.append(warnDsc);
		builder.append(", commMtd=");
		builder.append(commMtd);
		builder.append(", bizNm=");
		builder.append(bizNm);
		builder.append(", docMgrNo=");
		builder.append(docMgrNo);
		builder.append(", tmLat=");
		builder.append(tmLat);
		builder.append(", tmLon=");
		builder.append(tmLon);
		builder.append(", mgrNo=");
		builder.append(mgrNo);
		builder.append(", mgrSpot=");
		builder.append(mgrSpot);
		builder.append(", mgrBelong=");
		builder.append(mgrBelong);
		builder.append(", mgrLevel=");
		builder.append(mgrLevel);
		builder.append(", mgrNm=");
		builder.append(mgrNm);
		builder.append(", mgrTel=");
		builder.append(mgrTel);
		builder.append(", mgrPhone=");
		builder.append(mgrPhone);
		builder.append(", mgr=");
		builder.append(mgr);
		builder.append(", strMgr=");
		builder.append(strMgr);
		builder.append("]");
		return builder.toString();
	}
	
}
