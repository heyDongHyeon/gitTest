package geomex.xeus.eventmonitor.service;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EliKmaDfsShrtVo.java
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

public class EliKmaDfsShrtVo {

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

	@Size(min=0, max=12, message="발표일시분은 최대 12자 까지 입력하실 수 있습니다.")
    private String fcYmdhm;
	@Size(min=0, max=251, message="위치는 최대 251자 까지 입력하실 수 있습니다.")
    private String loc;
    @Size(min=0, max=24, message="좌표X는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointX;
    @Size(min=0, max=24, message="좌표Y는 최대 24자 까지 입력하실 수 있습니다.")
    private String pointY;
    @Size(min=0, max=40, message="강수확률1은 최대 40자 까지 입력하실 수 있습니다.")
    private String rain1;
    @Size(min=0, max=40, message="강수확률2은 최대 40자 까지 입력하실 수 있습니다.")
    private String rain2;
    @Size(min=0, max=40, message="강수확률3은 최대 40자 까지 입력하실 수 있습니다.")
    private String rain3;
    @Size(min=0, max=40, message="강수확률4은 최대 40자 까지 입력하실 수 있습니다.")
    private String rain4;
    @Size(min=0, max=40, message="강수확률5은 최대 40자 까지 입력하실 수 있습니다.")
    private String rain5;
    @Size(min=0, max=40, message="날씨1은 최대 40자 까지 입력하실 수 있습니다.")
    private String wad1;
    @Size(min=0, max=40, message="날씨2은 최대 40자 까지 입력하실 수 있습니다.")
    private String wad2;
    @Size(min=0, max=40, message="날씨3은 최대 40자 까지 입력하실 수 있습니다.")
    private String wad3;
    @Size(min=0, max=40, message="날씨4은 최대 40자 까지 입력하실 수 있습니다.")
    private String wad4;
    @Size(min=0, max=40, message="날씨5은 최대 40자 까지 입력하실 수 있습니다.")
    private String wad5;
    @Size(min=0, max=40, message="온도1은 최대 40자 까지 입력하실 수 있습니다.")
    private String tem1;
    @Size(min=0, max=40, message="온도2은 최대 40자 까지 입력하실 수 있습니다.")
    private String tem2;
    @Size(min=0, max=40, message="온도3은 최대 40자 까지 입력하실 수 있습니다.")
    private String tem3;
    @Size(min=0, max=40, message="온도4은 최대 40자 까지 입력하실 수 있습니다.")
    private String tem4;
    @Size(min=0, max=40, message="온도5은 최대 40자 까지 입력하실 수 있습니다.")
    private String tem5;
    @Size(min=0, max=40, message="습도1은 최대 40자 까지 입력하실 수 있습니다.")
    private String hum1;
    @Size(min=0, max=40, message="습도2은 최대 40자 까지 입력하실 수 있습니다.")
    private String hum2;
    @Size(min=0, max=40, message="습도3은 최대 40자 까지 입력하실 수 있습니다.")
    private String hum3;
    @Size(min=0, max=40, message="습도4은 최대 40자 까지 입력하실 수 있습니다.")
    private String hum4;
    @Size(min=0, max=40, message="습도5은 최대 40자 까지 입력하실 수 있습니다.")
    private String hum5;

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
    public String getFcYmdhm() {
        return fcYmdhm;
    }
    public void setFcYmdhm(String fcYmdhm) {
        this.fcYmdhm = fcYmdhm;
    }
    public String getLoc() {
        return loc;
    }
    public void setLoc(String loc) {
        this.loc = loc;
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
    public String getRain1() {
        return rain1;
    }
    public void setRain1(String rain1) {
        this.rain1 = rain1;
    }
    public String getRain2() {
        return rain2;
    }
    public void setRain2(String rain2) {
        this.rain2 = rain2;
    }
    public String getRain3() {
        return rain3;
    }
    public void setRain3(String rain3) {
        this.rain3 = rain3;
    }
    public String getRain4() {
        return rain4;
    }
    public void setRain4(String rain4) {
        this.rain4 = rain4;
    }
    public String getRain5() {
        return rain5;
    }
    public void setRain5(String rain5) {
        this.rain5 = rain5;
    }
    public String getWad1() {
        return wad1;
    }
    public void setWad1(String wad1) {
        this.wad1 = wad1;
    }
    public String getWad2() {
        return wad2;
    }
    public void setWad2(String wad2) {
        this.wad2 = wad2;
    }
    public String getWad3() {
        return wad3;
    }
    public void setWad3(String wad3) {
        this.wad3 = wad3;
    }
    public String getWad4() {
        return wad4;
    }
    public void setWad4(String wad4) {
        this.wad4 = wad4;
    }
    public String getWad5() {
        return wad5;
    }
    public void setWad5(String wad5) {
        this.wad5 = wad5;
    }
    public String getTem1() {
        return tem1;
    }
    public void setTem1(String tem1) {
        this.tem1 = tem1;
    }
    public String getTem2() {
        return tem2;
    }
    public void setTem2(String tem2) {
        this.tem2 = tem2;
    }
    public String getTem3() {
        return tem3;
    }
    public void setTem3(String tem3) {
        this.tem3 = tem3;
    }
    public String getTem4() {
        return tem4;
    }
    public void setTem4(String tem4) {
        this.tem4 = tem4;
    }
    public String getTem5() {
        return tem5;
    }
    public void setTem5(String tem5) {
        this.tem5 = tem5;
    }
    public String getHum1() {
        return hum1;
    }
    public void setHum1(String hum1) {
        this.hum1 = hum1;
    }
    public String getHum2() {
        return hum2;
    }
    public void setHum2(String hum2) {
        this.hum2 = hum2;
    }
    public String getHum3() {
        return hum3;
    }
    public void setHum3(String hum3) {
        this.hum3 = hum3;
    }
    public String getHum4() {
        return hum4;
    }
    public void setHum4(String hum4) {
        this.hum4 = hum4;
    }
    public String getHum5() {
        return hum5;
    }
    public void setHum5(String hum5) {
        this.hum5 = hum5;
    }

}
