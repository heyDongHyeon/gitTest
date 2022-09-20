package geomex.xeus.log.service;

import java.io.Serializable;

import geomex.xeus.smartcity.ExtHead;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  IfEvtLogVo.java
 * 작성자 :  홍길동
 * 작성일 :  2018. 5. 3.
 * 버전   :  1.0
 * 설명   :  
 * 클래스 설명을 쓰시오
 *
 * 수정이력
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 
 * ===========================================================
 * </pre>
 */
public class IfEvtLogVo implements Serializable {
    private static final long serialVersionUID = -5299630744842059375L;

    private String msgTypCd; //메시지타입코드
    private String sndSysCd; //송신시스템코드
    private String rcvSysCd; //수신시스템 코드

    private String msgExchPtrn; //메시지교환패턴
    private String bodyType; //메시지바디타입

    private String sndDtm; //발송일시
    private String dataLen; //DATA 길이

    private String rcvOrgn; //수신원문
    private String errMsg;  //에러메시지

    //private String forwdId; //포워딩 대상 시스템 ID

    public IfEvtLogVo() {}

    public IfEvtLogVo(ExtHead h, String json, String errMsg) {
        this.msgTypCd = String.format("%3s", h.MSG_TYP_CD);
        this.sndSysCd = String.format("%3s", h.SND_SYS_CD);
        this.rcvSysCd = String.format("%3s", h.RCV_SYS_CD);
        this.msgExchPtrn = String.format("%1s", h.MSG_EXCH_PTRN);

        this.bodyType = String.format("%2s", h.BODY_TYPE);
        this.sndDtm = String.format("%14s", h.SND_DTM);
        this.dataLen = String.format("%010d", h.DATA_LEN);
        //
        this.rcvOrgn = h.toString() + json;
        this.errMsg = errMsg;
    }

    public String getMsgTypCd() {
        return msgTypCd;
    }

    public void setMsgTypCd(String msgTypCd) {
        this.msgTypCd = msgTypCd;
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

    public String getMsgExchPtrn() {
        return msgExchPtrn;
    }

    public void setMsgExchPtrn(String msgExchPtrn) {
        this.msgExchPtrn = msgExchPtrn;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
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

    public String getRcvOrgn() {
        return rcvOrgn;
    }

    public void setRcvOrgn(String rcvOrgn) {
        this.rcvOrgn = rcvOrgn;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
