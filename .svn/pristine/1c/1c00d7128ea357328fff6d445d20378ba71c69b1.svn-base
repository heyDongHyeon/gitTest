package geomex.xeus.eventmonitor.service;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EliKmaEarthInfmVo.java
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

public class EliKmaEarthInfmVo {

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

	@Size(min=0, max=14, message="발생시분초는 최대 14자 까지 입력하실 수 있습니다.")
    private String ocrYmdhms;
    @Size(min=0, max=24, message="좌표X는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointX;
    @Size(min=0, max=24, message="좌표Y는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointY;
    @Size(min=0, max=251, message="위치는 최대 251자 까지 입력하실 수 있습니다.")
    private String loc;
    @Size(min=0, max=15, message="지진규모(리히터)는 최대 15자 까지 입력하실 수 있습니다.")
    private String earthInfmScle;
    @Size(min=0, max=40, message="지진등급은 최대 40자 까지 입력하실 수 있습니다.")
    private String earthInfmNoOrd;
    @Size(min=0, max=40, message="지진참고번호는 최대 40자 까지 입력하실 수 있습니다.")
    private String earthInfmNoRef;
    @Size(min=0, max=40, message="지진지점번호는 최대 40자 까지 입력하실 수 있습니다.")
    private String earthInfmCdStn;

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
    public String getOcrYmdhms() {
        return ocrYmdhms;
    }
    public void setOcrYmdhms(String ocrYmdhms) {
        this.ocrYmdhms = ocrYmdhms;
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
    public String getLoc() {
        return loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public String getEarthInfmScle() {
        return earthInfmScle;
    }
    public void setEarthInfmScle(String earthInfmScle) {
        this.earthInfmScle = earthInfmScle;
    }
    public String getEarthInfmNoOrd() {
        return earthInfmNoOrd;
    }
    public void setEarthInfmNoOrd(String earthInfmNoOrd) {
        this.earthInfmNoOrd = earthInfmNoOrd;
    }
    public String getEarthInfmNoRef() {
        return earthInfmNoRef;
    }
    public void setEarthInfmNoRef(String earthInfmNoRef) {
        this.earthInfmNoRef = earthInfmNoRef;
    }
    public String getEarthInfmCdStn() {
        return earthInfmCdStn;
    }
    public void setEarthInfmCdStn(String earthInfmCdStn) {
        this.earthInfmCdStn = earthInfmCdStn;
    }

}
