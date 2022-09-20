package geomex.xeus.eventmonitor.service;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EliHrfRfhrVo.java
 * 설  명 :
 *   고속도로특별상황관리 Vo
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

public class EliHrfRfhrVo {

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

    @Size(min=0, max=80, message="관측소코드는 최대 17자 까지 입력하실 수 있습니다.")
    private String obsvtId;
    @Size(min=0, max=80, message="관측소명은 최대 170자 까지 입력하실 수 있습니다.")
    private String obsvtNm;
    @Size(min=0, max=80, message="우량시단위등급은 최대 15자 까지 입력하실 수 있습니다.")
    private String rfhrStatus;
    @Size(min=0, max=80, message="관측일시분은 최대 12자 까지 입력하실 수 있습니다.")
    private String obsvtYmdhm;
    @Size(min=0, max=80, message="시강우는 최대 15자 까지 입력하실 수 있습니다.")
    private String hrrf;
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
    public String getObsvtId() {
        return obsvtId;
    }
    public void setObsvtId(String obsvtId) {
        this.obsvtId = obsvtId;
    }
    public String getObsvtNm() {
        return obsvtNm;
    }
    public void setObsvtNm(String obsvtNm) {
        this.obsvtNm = obsvtNm;
    }
    public String getRfhrStatus() {
        return rfhrStatus;
    }
    public void setRfhrStatus(String rfhrStatus) {
        this.rfhrStatus = rfhrStatus;
    }
    public String getObsvtYmdhm() {
        return obsvtYmdhm;
    }
    public void setObsvtYmdhm(String obsvtYmdhm) {
        this.obsvtYmdhm = obsvtYmdhm;
    }
    public String getHrrf() {
        return hrrf;
    }
    public void setHrrf(String hrrf) {
        this.hrrf = hrrf;
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

