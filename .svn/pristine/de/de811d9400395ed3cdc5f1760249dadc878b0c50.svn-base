package geomex.xeus.eventmonitor.service;


import javax.validation.constraints.Size;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

/**
 * <pre>
 * 파일명 :  IfEventStatVo.java
 * 설  명 :
 *   웹소켓 연동 서비스 발생 현황 Vo
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-04-27      이은규          최초 생성
 *
 * </pre>
 *
 * @since   :  2018. 04. 27.
 * @version :  1.0
 * @see
 */

public class IfEventStatVo {

    @NotNull(message="발생번호는 필수사항입니다.")
    @NotEmpty(message="발생번호는 필수사항입니다.")
    @Size(min=0, max=50, message="발생번호는 최대 50자 까지 입력하실 수 있습니다.")
    private String sendNum;

    @Size(min=0, max=3, message="서비스명은 최대 3자 까지 입력하실 수 있습니다.")
    private String svcNm;

    @NotNull(message="이벤트유형은 필수사항입니다.")
    @NotEmpty(message="이벤트유형은 필수사항입니다.")
    @Size(min=0, max=30, message="이벤트유형은 최대 30자 까지 입력하실 수 있습니다.")
    private String evtTyp;

    /*@NotNull(message="위치(경도)는 필수사항입니다.")
    @NotEmpty(message="위치(경도)는 필수사항입니다.")*/
    @Size(min=0, max=24, message="위치(경도)는 최대 24자 까지 입력하실 수 있습니다.")
    private String lon;

    /*@NotNull(message="위치(위도)는 필수사항입니다.")
    @NotEmpty(message="위치(위도)는 필수사항입니다.")*/
    @Size(min=0, max=24, message="위치(위도)는 최대 24자 까지 입력하실 수 있습니다.")
    private String lat;

    /*@NotNull(message="주소는 필수사항입니다.")
    @NotEmpty(message="주소는 필수사항입니다.")*/
    @Size(min=0, max=251, message="주소는 최대 251자 까지 입력하실 수 있습니다.")
    private String addr;

    @NotNull(message="발생일시는 필수사항입니다.")
    @NotEmpty(message="발생일시는 필수사항입니다.")
    @Size(min=0, max=14, message="발생일시는 최대 14자 까지 입력하실 수 있습니다.")
    private String evtDtm;

    public String getSendNum() {
        return sendNum;
    }

    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }

    public String getSvcNm() {
        return svcNm;
    }

    public void setSvcNm(String svcNm) {
        this.svcNm = svcNm;
    }

    public String getEvtTyp() {
        return evtTyp;
    }

    public void setEvtTyp(String evtTyp) {
        this.evtTyp = evtTyp;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEvtDtm() {
        return evtDtm;
    }

    public void setEvtDtm(String evtDtm) {
        this.evtDtm = evtDtm;
    }

}
