package geomex.xeus.sysmgr.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  OrganizationVo.java
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
 * @since   :  2017. 7. 7.
 * @version :  1.0
 * @see
 */

public class OrganizationVo {

	private String orgMgrNo;

	@NotNull(message="기관구분을 선택해 주세요.")
	@NotEmpty(message="기관구분을 선택해 주세요.")
	private String orgGbnCd;

	@NotNull(message="기관명을 입력해 주세요.")
	@NotEmpty(message="기관명을 입력해 주세요.")
	@Size(min=5, max=50, message="기관명은 5자 이상, 50자 이하로 입력해주세요.")
	private String orgNm;
	@Size(min=1, max=50, message="연락처는 1자 이상, 50자 이하로 입력해주세요.")
	private String telNum;
	@Size(min=1, max=20, message="담당자명은 1자 이상, 20자 이하로 입력해주세요.")
	private String chrgNm;
	@Size(min=1, max=255, message="비고는 1자 이상, 255자 이하로 입력해주세요.")
	private String rmark;
	@Size(min=1, max=6, message="상위기관관리번호는 1자 이상, 6자 이하로 입력해주세요.")
	private String upMgrNo;

	/**
	 * @return the orgMgrNo
	 */
	public String getOrgMgrNo() {
		return orgMgrNo;
	}
	/**
	 * @param orgMgrNo the orgMgrNo to set
	 */
	public void setOrgMgrNo(String orgMgrNo) {
		this.orgMgrNo = orgMgrNo;
	}
	/**
	 * @return the orgGbnCd
	 */
	public String getOrgGbnCd() {
		return orgGbnCd;
	}
	/**
	 * @param orgGbnCd the orgGbnCd to set
	 */
	public void setOrgGbnCd(String orgGbnCd) {
		this.orgGbnCd = orgGbnCd;
	}
	/**
	 * @return the orgNm
	 */
	public String getOrgNm() {
		return orgNm;
	}
	/**
	 * @param orgNm the orgNm to set
	 */
	public void setOrgNm(String orgNm) {
		this.orgNm = orgNm;
	}
	/**
	 * @return the telNum
	 */
	public String getTelNum() {
		return telNum;
	}
	/**
	 * @param telNum the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	/**
	 * @return the chrgNm
	 */
	public String getChrgNm() {
		return chrgNm;
	}
	/**
	 * @param chrgNm the chrgNm to set
	 */
	public void setChrgNm(String chrgNm) {
		this.chrgNm = chrgNm;
	}
	/**
	 * @return the rmark
	 */
	public String getRmark() {
		return rmark;
	}
	/**
	 * @param rmark the rmark to set
	 */
	public void setRmark(String rmark) {
		this.rmark = rmark;
	}
	/**
	 * @return the upMgrNo
	 */
	public String getUpMgrNo() {
		return upMgrNo;
	}
	/**
	 * @param upMgrNo the upMgrNo to set
	 */
	public void setUpMgrNo(String upMgrNo) {
		this.upMgrNo = upMgrNo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrganizationVo [orgMgrNo="
			+ orgMgrNo + ", orgGbnCd=" + orgGbnCd + ", orgNm=" + orgNm + ", telNum=" + telNum + ", chrgNm=" + chrgNm
			+ ", rmark=" + rmark + ", upMgrNo=" + upMgrNo + "]";
	}

}
