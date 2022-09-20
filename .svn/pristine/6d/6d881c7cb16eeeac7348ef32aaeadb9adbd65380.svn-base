package geomex.xeus.eventmonitor.service;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EliKmaInformVo.java
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

public class EliKmaInformVo {

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

    @Size(min=0, max=14, message="KMA발표일시분초는 최대 14자 까지 입력하실 수 있습니다.")
    private String kmaYmdhms;
    @Size(min=0, max=15, message="KMA발표연번은 최대 15자 까지 입력하실 수 있습니다.")
    private String kmaSeqNo;
    @Size(min=0, max=170, message="KMA발표제목은 최대 170자 까지 입력하실 수 있습니다.")
    private String kmaTitle;
    @Size(min=0, max=2000, message="KMA발표내용은 최대 2000자 까지 입력하실 수 있습니다.")
    private String kmaStatCtnt;
    @Size(min=0, max=2000, message="KMA지역은 최대 2000자 까지 입력하실 수 있습니다.")
    private String kmaSectArea;
    @Size(min=0, max=2000, message="KMA예비특보는 최대 2000자 까지 입력하실 수 있습니다.")
    private String kmaPwVl;
    @Size(min=0, max=40, message="KMA지점번호는 최대 40자 까지 입력하실 수 있습니다.")
    private String kmaCdStn;

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
    public String getKmaYmdhms() {
        return kmaYmdhms;
    }
    public void setKmaYmdhms(String kmaYmdhms) {
        this.kmaYmdhms = kmaYmdhms;
    }
    public String getKmaSeqNo() {
        return kmaSeqNo;
    }
    public void setKmaSeqNo(String kmaSeqNo) {
        this.kmaSeqNo = kmaSeqNo;
    }
    public String getKmaTitle() {
        return kmaTitle;
    }
    public void setKmaTitle(String kmaTitle) {
        this.kmaTitle = kmaTitle;
    }
    public String getKmaStatCtnt() {
        return kmaStatCtnt;
    }
    public void setKmaStatCtnt(String kmaStatCtnt) {
        this.kmaStatCtnt = kmaStatCtnt;
    }
    public String getKmaSectArea() {
        return kmaSectArea;
    }
    public void setKmaSectArea(String kmaSectArea) {
        this.kmaSectArea = kmaSectArea;
    }
    public String getKmaPwVl() {
        return kmaPwVl;
    }
    public void setKmaPwVl(String kmaPwVl) {
        this.kmaPwVl = kmaPwVl;
    }
    public String getKmaCdStn() {
        return kmaCdStn;
    }
    public void setKmaCdStn(String kmaCdStn) {
        this.kmaCdStn = kmaCdStn;
    }

}
