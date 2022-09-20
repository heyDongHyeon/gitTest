package geomex.xeus.log.service;

import javax.validation.constraints.Size;

import org.springmodules.validation.bean.conf.loader.annotation.handler.NotEmpty;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotNull;

import geomex.xeus.smartcity.BodyDSC;
import geomex.xeus.smartcity.Head;
import geomex.xeus.smartcity.Response;
import geomex.xeus.smartcity.Utils;

/**
 * <pre>
 * 파일명 :  IfDscLogVo.java
 * 설  명 :
 *   사회적약자 로그 Vo
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-04-17      이은규          최초 생성
 *
 * </pre>
 *
 * @since : 2018. 04. 17.
 * @version : 1.0
 * @see
 */

public class IfDscLogVo {

    @Size(min = 0, max = 2, message = "전문구분코드는 최대 2자 까지 입력하실 수 있습니다.")
    private String msgTypCd;
    @Size(min = 0, max = 2, message = "진행구분코드는 최대 2자 까지 입력하실 수 있습니다.")
    private String staTypCd;
    @Size(min = 0, max = 14, message = "진행구분일시는 최대 14자 까지 입력하실 수 있습니다.")
    private String msgStaDtm;
    @Size(min = 0, max = 3, message = "송신시스템코드는 최대 3자 까지 입력하실 수 있습니다.")
    private String sndSysCd;
    @Size(min = 0, max = 3, message = "수신시스템코드는 최대 3자 까지 입력하실 수 있습니다.")
    private String rcvSysCd;
    @Size(min = 0, max = 14, message = "발송일시는 최대 14자 까지 입력하실 수 있습니다.")
    private String sndDtm;
    @Size(min = 0, max = 10, message = "DATA 길이는 최대 10자 까지 입력하실 수 있습니다.")
    private String dataLen;

    @NotNull(message = "발생번호는 필수사항입니다.")
    @NotEmpty(message = "발생번호는 필수사항입니다.")
    @Size(min = 0, max = 50, message = "발생번호는 최대 50자 까지 입력하실 수 있습니다.")
    private String sendNum;

    @NotNull(message = "서비스유형은 필수사항입니다.")
    @NotEmpty(message = "서비스유형은 필수사항입니다.")
    @Size(min = 0, max = 30, message = "서비스유형은 최대 30자 까지 입력하실 수 있습니다.")
    private String svcTyp;

    @NotNull(message = "사건지점위치(경도)는 필수사항입니다.")
    @NotEmpty(message = "사건지점위치(경도)는 필수사항입니다.")
    @Size(min = 0, max = 24, message = "사건지점위치(경도)는 최대 24자 까지 입력하실 수 있습니다.")
    private String evtLon;

    @NotNull(message = "사건지점위치(위도)는 필수사항입니다.")
    @NotEmpty(message = "사건지점위치(위도)는 필수사항입니다.")
    @Size(min = 0, max = 24, message = "사건지점위치(위도)는 최대 24자 까지 입력하실 수 있습니다.")
    private String evtLat;

    @NotNull(message = "사건지점전체주소는 필수사항입니다.")
    @NotEmpty(message = "사건지점전체주소는 필수사항입니다.")
    @Size(min = 0, max = 251, message = "사건지점전체주소는 최대 251자 까지 입력하실 수 있습니다.")
    private String evtAddr;

    @NotNull(message = "지역코드는 필수사항입니다.")
    @NotEmpty(message = "지역코드는 필수사항입니다.")
    @Size(min = 0, max = 10, message = "지역코드는 최대 10자 까지 입력하실 수 있습니다.")
    private String evtBjd;

    @Size(min = 0, max = 30, message = "ref ID는 최대 30자 까지 입력하실 수 있습니다.")
    private String refId;

    @NotNull(message = "이름은 필수사항입니다.")
    @NotEmpty(message = "이름은 필수사항입니다.")
    @Size(min = 0, max = 20, message = "이름은 최대 20자 까지 입력하실 수 있습니다.")
    private String dscNm;

    @NotNull(message = "핸드폰번호는 필수사항입니다.")
    @NotEmpty(message = "핸드폰번호는 필수사항입니다.")
    @Size(min = 0, max = 15, message = "핸드폰번호는 최대 15자 까지 입력하실 수 있습니다.")
    private String dscPhone;
    @Size(min = 0, max = 15, message = "출생일은 최대 15자 까지 입력하실 수 있습니다.")
    private String dscBirth;
    @Size(min = 0, max = 5, message = "성별은 최대 5자 까지 입력하실 수 있습니다.")
    private String dscSex;
    @Size(min = 0, max = 251, message = "주소(주거지)는 최대 251자 까지 입력하실 수 있습니다.")
    private String dscAddr;

    @NotNull(message = "보호자이름은 필수사항입니다.")
    @NotEmpty(message = "보호자이름은 필수사항입니다.")
    @Size(min = 0, max = 20, message = "보호자이름은 최대 20자 까지 입력하실 수 있습니다.")
    private String guardNm;

    @NotNull(message = "보호자 핸드폰은 필수사항입니다.")
    @NotEmpty(message = "보호자 핸드폰은 필수사항입니다.")
    @Size(min = 0, max = 15, message = "보호자 핸드폰은 최대 15자 까지 입력하실 수 있습니다.")
    private String guardPhone;

    @NotNull(message = "발생일시는 필수사항입니다.")
    @NotEmpty(message = "발생일시는 필수사항입니다.")
    @Size(min = 0, max = 14, message = "발생일시는 최대 14자 까지 입력하실 수 있습니다.")
    private String evtDtm;
    private String images;
    @Size(min = 0, max = 500, message = "신상정보는 최대 500자 까지 입력하실 수 있습니다.")
    private String info;
    @Size(min = 0, max = 500, message = "특이사항은 최대 500자 까지 입력하실 수 있습니다.")
    private String note;

    @NotNull(message = "전송자아이디는 필수사항입니다.")
    @NotEmpty(message = "전송자아이디는 필수사항입니다.")
    @Size(min = 0, max = 30, message = "전송자아이디는 최대 30자 까지 입력하실 수 있습니다.")
    private String senderId;

    @Size(min = 0, max = 14, message = "수신일시는 최대 30자 까지 입력하실 수 있습니다.")
    private String rcvDtmRqst;
    @Size(min = 0, max = 2000, message = "수신원문은 최대 2000자 까지 입력하실 수 있습니다.")
    private String rcvOrgn;
    @Size(min = 0, max = 1, message = "정상유무는 최대 1자 까지 입력하실 수 있습니다.")
    private String nrmlYn;
    @Size(min = 0, max = 100, message = "에러메세지는 최대 100자 까지 입력하실 수 있습니다.")
    private String errMsg;
    @Size(min = 0, max = 14, message = "송신일시 최대 14자 까지 입력하실 수 있습니다.")
    private String sndDtmRsp;
    @Size(min = 0, max = 2000, message = "송신원문은 최대 2000자 까지 입력하실 수 있습니다.")
    private String sndOrgn;

    public IfDscLogVo() {}

    public IfDscLogVo(Head h, BodyDSC b, Response r) {
        this.msgTypCd = String.format("%2s", h.MSG_TYP_CD);
        this.staTypCd = String.format("%2s", h.STA_TYP_CD);
        this.msgStaDtm = String.format("%14s", h.MSG_STA_DTM);
        this.sndSysCd = String.format("%3s", h.SND_SYS_CD);
        this.rcvSysCd = String.format("%3s", h.RCV_SYS_CD);
        this.sndDtm = String.format("%14s", h.SND_DTM);
        this.dataLen = String.format("%010d", h.DATA_LEN);
        //
        this.sendNum = b.SEND_NUM;
        this.svcTyp = b.SVC_TYP;
        this.evtLon = b.EVT_LON;
        this.evtLat = b.EVT_LAT;
        this.evtAddr = b.EVT_ADDR;
        this.evtBjd = b.EVT_BJD;
        this.refId = b.REF_ID;
        this.dscNm = b.DSC_NM;
        this.dscPhone = b.DSC_PHONE;
        this.dscBirth = b.DSC_BIRTH;
        this.dscSex = b.DSC_SEX;
        this.dscAddr = b.DSC_ADDR;
        this.guardNm = b.GUARD_NM;
        this.guardPhone = b.GUARD_PHONE;
        this.evtDtm = b.EVT_DTM;
        this.images = b.IMAGE;
        this.info = b.INFO;
        this.note = b.NOTE;
        this.senderId = b.SENDER_ID;
        //
        String dtm = Utils.getDate();
        this.rcvDtmRqst = dtm;
        this.rcvOrgn = h.toString() + b.toString();
        this.nrmlYn = r.STATUS;
        this.errMsg = r.MSG;
        this.sndDtmRsp = dtm;
        this.sndOrgn = r.toString();
    }

    public String getMsgTypCd() {
        return msgTypCd;
    }

    public void setMsgTypCd(String msgTypCd) {
        this.msgTypCd = msgTypCd;
    }

    public String getStaTypCd() {
        return staTypCd;
    }

    public void setStaTypCd(String staTypCd) {
        this.staTypCd = staTypCd;
    }

    public String getMsgStaDtm() {
        return msgStaDtm;
    }

    public void setMsgStaDtm(String msgStaDtm) {
        this.msgStaDtm = msgStaDtm;
    }

    public String getSndSysCd() {
        return sndSysCd;
    }

    public void setSndSysCd(String sndSysCd) {
        this.sndSysCd = sndSysCd;
    }

    public String getRcvSysCd() {
        return rcvSysCd;
    }

    public void setRcvSysCd(String rcvSysCd) {
        this.rcvSysCd = rcvSysCd;
    }

    public String getSndDtm() {
        return sndDtm;
    }

    public void setSndDtm(String sndDtm) {
        this.sndDtm = sndDtm;
    }

    public String getDataLen() {
        return dataLen;
    }

    public void setDataLen(String dataLen) {
        this.dataLen = dataLen;
    }

    public String getSendNum() {
        return sendNum;
    }

    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }

    public String getSvcTyp() {
        return svcTyp;
    }

    public void setSvcTyp(String svcTyp) {
        this.svcTyp = svcTyp;
    }

    public String getEvtLon() {
        return evtLon;
    }

    public void setEvtLon(String evtLon) {
        this.evtLon = evtLon;
    }

    public String getEvtLat() {
        return evtLat;
    }

    public void setEvtLat(String evtLat) {
        this.evtLat = evtLat;
    }

    public String getEvtAddr() {
        return evtAddr;
    }

    public void setEvtAddr(String evtAddr) {
        this.evtAddr = evtAddr;
    }

    public String getEvtBjd() {
        return evtBjd;
    }

    public void setEvtBjd(String evtBjd) {
        this.evtBjd = evtBjd;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getDscNm() {
        return dscNm;
    }

    public void setDscNm(String dscNm) {
        this.dscNm = dscNm;
    }

    public String getDscPhone() {
        return dscPhone;
    }

    public void setDscPhone(String dscPhone) {
        this.dscPhone = dscPhone;
    }

    public String getDscBirth() {
        return dscBirth;
    }

    public void setDscBirth(String dscBirth) {
        this.dscBirth = dscBirth;
    }

    public String getDscSex() {
        return dscSex;
    }

    public void setDscSex(String dscSex) {
        this.dscSex = dscSex;
    }

    public String getDscAddr() {
        return dscAddr;
    }

    public void setDscAddr(String dscAddr) {
        this.dscAddr = dscAddr;
    }

    public String getGuardNm() {
        return guardNm;
    }

    public void setGuardNm(String guardNm) {
        this.guardNm = guardNm;
    }

    public String getGuardPhone() {
        return guardPhone;
    }

    public void setGuardPhone(String guardPhone) {
        this.guardPhone = guardPhone;
    }

    public String getEvtDtm() {
        return evtDtm;
    }

    public void setEvtDtm(String evtDtm) {
        this.evtDtm = evtDtm;
    }

    
    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRcvDtmRqst() {
        return rcvDtmRqst;
    }

    public void setRcvDtmRqst(String rcvDtmRqst) {
        this.rcvDtmRqst = rcvDtmRqst;
    }

    public String getRcvOrgn() {
        return rcvOrgn;
    }

    public void setRcvOrgn(String rcvOrgn) {
        this.rcvOrgn = rcvOrgn;
    }

    public String getNrmlYn() {
        return nrmlYn;
    }

    public void setNrmlYn(String nrmlYn) {
        this.nrmlYn = nrmlYn;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getSndDtmRsp() {
        return sndDtmRsp;
    }

    public void setSndDtmRsp(String sndDtmRsp) {
        this.sndDtmRsp = sndDtmRsp;
    }

    public String getSndOrgn() {
        return sndOrgn;
    }

    public void setSndOrgn(String sndOrgn) {
        this.sndOrgn = sndOrgn;
    }

}
