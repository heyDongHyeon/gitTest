package geomex.xeus.ndmssend.service;

/**
 * <pre>
 * 파일명 :  GeometryVo.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2016-02-01      홍길동          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 9. 5.
 * @version :  1.0
 * @see
 */

public class NdmsSendVo {

	private String cntcSn; //메세지 연계 일련번호 d : geomex
	private String cntcId; // 시스템 아이디 d : geomex
	private String cntcNm; //연계 시스템 이름 d : 세종시 스마트 재난전파 시스템
	private String transMiter; // 송신 전화번호 d : 고정으로 설정해야함. 방송시 x
	private String rcverNm; //수신자이름 - 음성인 경우 방송장비명
	private String recptnNo; //수신 전화번호
	private String cntcCn; //메시지내용
	private String tranlNo; //방송 단말기 고유번호
	private String brdcstMth;//방송방법
	private String voiceSex;//음성 성별 0 : 여성, 1: 남성
	private String voiceVolum; //음성 볼륨 5: 보통
	private String voiceVe;//음성 속도 1 : 보통
	private String beginNtcn;//방송시작 알림소리
	private String endNtcn;//방송종료 알림소리
	private String msgTyp;//메시지 유형 4 : 단문, 6:장문, 10:방송
	private String resveAt;//메시지 예약구분
	private String transState; // 메시지 연계 상태
	private String sendDt; // 송신일시
	private String reqDt; //송신요청일시
	private String sendComDt; //송신요청완료일시
	private String ComDt;//송신처리 완료 일시

	public String getCntcSn() {
		return cntcSn;
	}
	public void setCntcSn(String cntcSn) {
		this.cntcSn = cntcSn;
	}
	public String getCntcId() {
		return cntcId;
	}
	public void setCntcId(String cntcId) {
		this.cntcId = cntcId;
	}
	public String getCntcNm() {
		return cntcNm;
	}
	public void setCntcNm(String cntcNm) {
		this.cntcNm = cntcNm;
	}
	public String getTransMiter() {
		return transMiter;
	}
	public void setTransMiter(String transMiter) {
		this.transMiter = transMiter;
	}
	public String getRcverNm() {
		return rcverNm;
	}
	public void setRcverNm(String rcverNm) {
		this.rcverNm = rcverNm;
	}
	public String getRecptnNo() {
		return recptnNo;
	}
	public void setRecptnNo(String recptnNo) {
		this.recptnNo = recptnNo;
	}
	public String getCntcCn() {
		return cntcCn;
	}
	public void setCntcCn(String cntcCn) {
		this.cntcCn = cntcCn;
	}
	public String getTranlNo() {
		return tranlNo;
	}
	public void setTranlNo(String tranlNo) {
		this.tranlNo = tranlNo;
	}
	public String getBrdcstMth() {
		return brdcstMth;
	}
	public void setBrdcstMth(String brdcstMth) {
		this.brdcstMth = brdcstMth;
	}
	public String getVoiceSex() {
		return voiceSex;
	}
	public void setVoiceSex(String voiceSex) {
		this.voiceSex = voiceSex;
	}
	public String getVoiceVolum() {
		return voiceVolum;
	}
	public void setVoiceVolum(String voiceVolum) {
		this.voiceVolum = voiceVolum;
	}
	public String getVoiceVe() {
		return voiceVe;
	}
	public void setVoiceVe(String voiceVe) {
		this.voiceVe = voiceVe;
	}
	public String getBeginNtcn() {
		return beginNtcn;
	}
	public void setBeginNtcn(String beginNtcn) {
		this.beginNtcn = beginNtcn;
	}
	public String getEndNtcn() {
		return endNtcn;
	}
	public void setEndNtcn(String endNtcn) {
		this.endNtcn = endNtcn;
	}
	public String getMsgTyp() {
		return msgTyp;
	}
	public void setMsgTyp(String msgTyp) {
		this.msgTyp = msgTyp;
	}
	public String getResveAt() {
		return resveAt;
	}
	public void setResveAt(String resveAt) {
		this.resveAt = resveAt;
	}
	public String getTransState() {
		return transState;
	}
	public void setTransState(String transState) {
		this.transState = transState;
	}
	public String getSendDt() {
		return sendDt;
	}
	public void setSendDt(String sendDt) {
		this.sendDt = sendDt;
	}
	public String getReqDt() {
		return reqDt;
	}
	public void setReqDt(String reqDt) {
		this.reqDt = reqDt;
	}
	public String getSendComDt() {
		return sendComDt;
	}
	public void setSendComDt(String sendComDt) {
		this.sendComDt = sendComDt;
	}
	public String getComDt() {
		return ComDt;
	}
	public void setComDt(String comDt) {
		ComDt = comDt;
	}

}
