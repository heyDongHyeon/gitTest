package geomex.xeus.eventmonitor.service;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EliKmaAws10mVo.java
 * 설  명 :
 *   기상정보AWS Vo
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

public class EliKmaAws10mVo {

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

	@Size(min=0, max=80, message="관측일시분은 최대 12자 까지 입력하실 수 있습니다.")
	private String obsvtYmdhm;
    @Size(min=0, max=24, message="좌표X는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointX;
    @Size(min=0, max=24, message="좌표Y는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointY;
    @Size(min=0, max=251, message="위치는 최대 251자 까지 입력하실 수 있습니다.")
    private String loc;
    @Size(min=0, max=251, message="관측소주소는 최대 251자 까지 입력하실 수 있습니다.")
    private String obsvtAddr;
    @Size(min=0, max=40, message="기상상태코드는 최대 40자 까지 입력하실 수 있습니다.")
    private String awsLvlTp;
    @Size(min=0, max=40, message="기상상태는 최대 40자 까지 입력하실 수 있습니다.")
    private String awsLvl;
    @Size(min=0, max=40, message="풍향은 최대 40자 까지 입력하실 수 있습니다.")
    private String wind;
    @Size(min=0, max=15, message="풍속은 최대 15자 까지 입력하실 수 있습니다.")
    private String ws;
    @Size(min=0, max=15, message="기온은 최대 15자 까지 입력하실 수 있습니다.")
    private String ta;
    @Size(min=0, max=15, message="일누적강수량은 최대 15자 까지 입력하실 수 있습니다.")
    private String qtyDay;

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
    public String getObsvtYmdhm() {
        return obsvtYmdhm;
    }
    public void setObsvtYmdhm(String obsvtYmdhm) {
        this.obsvtYmdhm = obsvtYmdhm;
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
    public String getObsvtAddr() {
        return obsvtAddr;
    }
    public void setObsvtAddr(String obsvtAddr) {
        this.obsvtAddr = obsvtAddr;
    }
    public String getAwsLvlTp() {
        return awsLvlTp;
    }
    public void setAwsLvlTp(String awsLvlTp) {
        this.awsLvlTp = awsLvlTp;
    }
    public String getAwsLvl() {
        return awsLvl;
    }
    public void setAwsLvl(String awsLvl) {
        this.awsLvl = awsLvl;
    }
    public String getWind() {
        return wind;
    }
    public void setWind(String wind) {
        this.wind = wind;
    }
    public String getWs() {
        return ws;
    }
    public void setWs(String ws) {
        this.ws = ws;
    }
    public String getTa() {
        return ta;
    }
    public void setTa(String ta) {
        this.ta = ta;
    }
    public String getQtyDay() {
        return qtyDay;
    }
    public void setQtyDay(String qtyDay) {
        this.qtyDay = qtyDay;
    }

}
