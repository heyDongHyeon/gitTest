package geomex.xeus.eventmonitor.service;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EliNemEmreVo.java
 * 설  명 :
 *   소방서 Vo
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

public class EliNemFirsVo {

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

	@Size(min=0, max=40, message="소방서ID는 최대 40자 까지 입력하실 수 있습니다.")
    private String firsId;
	@Size(min=0, max=170, message="소방서명은 최대 170자 까지 입력하실 수 있습니다.")
    private String firsNm;
    @Size(min=0, max=40, message="지역코드는 최대 40자 까지 입력하실 수 있습니다.")
    private String areaCd;
    @Size(min=0, max=170, message="지역주소는 최대 170자 까지 입력하실 수 있습니다.")
    private String areaAddr;
    @Size(min=0, max=40, message="cdGis는 최대 40자 까지 입력하실 수 있습니다.")
    private String cdGis;
    @Size(min=0, max=40, message="전화번호는 최대 40자 까지 입력하실 수 있습니다.")
    private String tel;
    @Size(min=0, max=40, message="FAX번호는 최대 40자 까지 입력하실 수 있습니다.")
    private String fax;

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
    public String getFirsId() {
        return firsId;
    }
    public void setFirsId(String firsId) {
        this.firsId = firsId;
    }
    public String getFirsNm() {
        return firsNm;
    }
    public void setFirsNm(String firsNm) {
        this.firsNm = firsNm;
    }
    public String getAreaCd() {
        return areaCd;
    }
    public void setAreaCd(String areaCd) {
        this.areaCd = areaCd;
    }
    public String getAreaAddr() {
        return areaAddr;
    }
    public void setAreaAddr(String areaAddr) {
        this.areaAddr = areaAddr;
    }
    public String getCdGis() {
        return cdGis;
    }
    public void setCdGis(String cdGis) {
        this.cdGis = cdGis;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }

}
