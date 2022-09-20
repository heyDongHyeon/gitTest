package geomex.xeus.eventmonitor.service;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EliKhcAccVo.java
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

public class EliKhcAccVo {

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

    @Size(min=0, max=1, message="종료여부는 최대 1자 까지 입력하실 수 있습니다.")
    private String endYn;
    @Size(min=0, max=251, message="위치는 최대 251자 까지 입력하실 수 있습니다.")
    private String loc;
    @Size(min=0, max=12, message="일시분은 최대 12자 까지 입력하실 수 있습니다.")
    private String ymdhm;
    @Size(min=0, max=40, message="일시분은 최대 40자 까지 입력하실 수 있습니다.")
    private String updn;
    @Size(min=0, max=251, message="내용은 최대 251자 까지 입력하실 수 있습니다.")
    private String stat;
    @Size(min=0, max=24, message="좌표X는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointX;
    @Size(min=0, max=24, message="좌표Y는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointY;
    @Size(min=0, max=20, message="도로아이디는 최대 20자 까지 입력하실 수 있습니다.")
    private String gId;

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
    public String getEndYn() {
        return endYn;
    }
    public void setEndYn(String endYn) {
        this.endYn = endYn;
    }
    public String getLoc() {
        return loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public String getYmdhm() {
        return ymdhm;
    }
    public void setYmdhm(String ymdhm) {
        this.ymdhm = ymdhm;
    }
    public String getUpdn() {
        return updn;
    }
    public void setUpdn(String updn) {
        this.updn = updn;
    }
    public String getStat() {
        return stat;
    }
    public void setStat(String stat) {
        this.stat = stat;
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
    public String getgId() {
        return gId;
    }
    public void setgId(String gId) {
        this.gId = gId;
    }

}
