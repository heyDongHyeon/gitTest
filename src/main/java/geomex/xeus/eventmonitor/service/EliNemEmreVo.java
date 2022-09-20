package geomex.xeus.eventmonitor.service;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EliNemEmreVo.java
 * 설  명 :
 *   지진현황 Vo
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-04-13      이은규          최초 생성
 *
 * </pre>
 *
 * @since   :  2018. 04. 13.
 * @version :  1.0
 * @see
 */

public class EliNemEmreVo {

	@NotNull(message="연계정보아이디는 필수사항입니다.")
    @NotEmpty(message="연계정보아이디는 필수사항입니다.")
	@Size(min=0, max=80, message="연계정보아이디는 최대 80자 까지 입력하실 수 있습니다.")
    private String lkInfoId;

	@NotNull(message="수신일자시각은 필수사항입니다.")
    @NotEmpty(message="수신일자시각은 필수사항입니다.")
	@Size(min=0, max=14, message="수신일자시각은 최대 14자 까지 입력하실 수 있습니다.")
    private String rcvYmdHms;

	@NotNull(message="연번은 필수사항입니다.")
    @NotEmpty(message="연번은 필수사항입니다.")
	@Size(min=0, max=8, message="연번은 최대 8자 까지 입력하실 수 있습니다.")
    private String seqNo;

	@Size(min=0, max=40, message="장비연번은 최대 40자 까지 입력하실 수 있습니다.")
	private String aseqNo;
	@Size(min=0, max=40, message="장비코드는 최대 40자 까지 입력하실 수 있습니다.")
    private String equpCd;
	@Size(min=0, max=170, message="장비명은 최대 170자 까지 입력하실 수 있습니다.")
    private String equpName;
	@Size(min=0, max=5, message="수량은 최대 5자 까지 입력하실 수 있습니다.")
    private String amt;
    @Size(min=0, max=40, message="지역코드는 최대 40자 까지 입력하실 수 있습니다.")
    private String areaCd;
    @Size(min=0, max=170, message="지역명은 최대 170자 까지 입력하실 수 있습니다.")
    private String areaName;
    @Size(min=0, max=40, message="지역코드(행정동)는 최대 40자 까지 입력하실 수 있습니다.")
    private String areaCdH;
    @Size(min=0, max=170, message="소유사는 최대 170자 까지 입력하실 수 있습니다.")
    private String company;
    @Size(min=0, max=170, message="소유자는 최대 170자 까지 입력하실 수 있습니다.")
    private String ownerName;
	@Size(min=0, max=24, message="좌표X는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointX;
    @Size(min=0, max=24, message="좌표Y는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointY;

    public String getLkInfoId() {
        return lkInfoId;
    }
    public void setLkInfoId(String lkInfoId) {
        this.lkInfoId = lkInfoId;
    }
    public String getRcvYmdHms() {
        return rcvYmdHms;
    }
    public void setRcvYmdHms(String rcvYmdHms) {
        this.rcvYmdHms = rcvYmdHms;
    }
    public String getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
    public String getAseqNo() {
        return aseqNo;
    }
    public void setAseqNo(String aseqNo) {
        this.aseqNo = aseqNo;
    }
    public String getEqupCd() {
        return equpCd;
    }
    public void setEqupCd(String equpCd) {
        this.equpCd = equpCd;
    }
    public String getEqupName() {
        return equpName;
    }
    public void setEqupName(String equpName) {
        this.equpName = equpName;
    }
    public String getAmt() {
        return amt;
    }
    public void setAmt(String amt) {
        this.amt = amt;
    }
    public String getAreaCd() {
        return areaCd;
    }
    public void setAreaCd(String areaCd) {
        this.areaCd = areaCd;
    }
    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public String getAreaCdH() {
        return areaCdH;
    }
    public void setAreaCdH(String areaCdH) {
        this.areaCdH = areaCdH;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getPointX() {
        return pointX;
    }
    public void setPointX(String pointX) {
        this.pointX = pointX;
    }
    public String getPointY() {
        return pointY;
    }
    public void setPointY(String pointY) {
        this.pointY = pointY;
    }

}
