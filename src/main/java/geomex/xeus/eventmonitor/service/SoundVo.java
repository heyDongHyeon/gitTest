package geomex.xeus.eventmonitor.service;

public class SoundVo {

	private String evtMgrNo;
	private String cctvMgrNo;
	private String evtLat;
	private String evtLon;
	private String mgrSeq;

	// Master Event
    private String eventTyp;
    private String recvDat;
    private String actionNote;
    private String closeCd;

    // CCTV Info
    private String cctvNm;

    // Location
    private String SoundAddr;

	public String getEvtMgrNo() {
		return evtMgrNo;
	}
	public void setEvtMgrNo(String evtMgrNo) {
		this.evtMgrNo = evtMgrNo;
	}
	public String getCctvMgrNo() {
		return cctvMgrNo;
	}
	public void setCctvMgrNo(String cctvMgrNo) {
		this.cctvMgrNo = cctvMgrNo;
	}
	public String getEvtLat() {
		return evtLat;
	}
	public void setEvtLat(String evtLat) {
		this.evtLat = evtLat;
	}
	public String getEvtLon() {
		return evtLon;
	}
	public void setEvtLon(String evtLon) {
		this.evtLon = evtLon;
	}
	public String getMgrSeq() {
		return mgrSeq;
	}
	public void setMgrSeq(String mgrSeq) {
		this.mgrSeq = mgrSeq;
	}
    public String getEventTyp() {
        return eventTyp;
    }
    public void setEventTyp(String eventTyp) {
        this.eventTyp = eventTyp;
    }
    public String getRecvDat() {
        return recvDat;
    }
    public void setRecvDat(String recvDat) {
        this.recvDat = recvDat;
    }
    public String getActionNote() {
        return actionNote;
    }
    public void setActionNote(String actionNote) {
        this.actionNote = actionNote;
    }
    public String getCloseCd() {
        return closeCd;
    }
    public void setCloseCd(String closeCd) {
        this.closeCd = closeCd;
    }
    public String getCctvNm() {
        return cctvNm;
    }
    public void setCctvNm(String cctvNm) {
        this.cctvNm = cctvNm;
    }
    public String getSoundAddr() {
        return SoundAddr;
    }
    public void setSoundAddr(String soundAddr) {
        SoundAddr = soundAddr;
    }

}
