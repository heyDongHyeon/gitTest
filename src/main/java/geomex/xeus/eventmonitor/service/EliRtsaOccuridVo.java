package geomex.xeus.eventmonitor.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EliRtsaOccuridVo.java
 * 설  명 :
 *   시가지도로돌발상황정보 Vo
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

public class EliRtsaOccuridVo {

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

    @Size(min=0, max=40, message="표준링크ID는 최대 40자 까지 입력하실 수 있습니다.")
    private String stdLinkId;
    @Size(min=0, max=170, message="노선명은 최대 170자 까지 입력하실 수 있습니다.")
    private String roadName;
    @Size(min=0, max=14, message="발생일시는 최대 14자 까지 입력하실 수 있습니다.")
    private String measureTime;
    @Size(min=0, max=251, message="상황내용은 최대 251자 까지 입력하실 수 있습니다.")
    private String typeOther;
    private String rgsDate;

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
    public String getStdLinkId() {
        return stdLinkId;
    }
    public void setStdLinkId(String stdLinkId) {
        this.stdLinkId = stdLinkId;
    }
    public String getRoadName() {
        return roadName;
    }
    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }
    public String getMeasureTime() {
        return measureTime;
    }
    public void setMeasureTime(String measureTime) {
        this.measureTime = measureTime;
    }
    public String getTypeOther() {
        return typeOther;
    }
    public void setTypeOther(String typeOther) {
        this.typeOther = typeOther;
    }
    public String getRgsDate() {
        return rgsDate;
    }
    public void setRgsDate(String rgsDate) {
        this.rgsDate = rgsDate;
    }

}
