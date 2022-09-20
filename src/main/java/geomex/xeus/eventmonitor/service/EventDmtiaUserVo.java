package geomex.xeus.eventmonitor.service;

public class EventDmtiaUserVo {

	private String dmtiaMgrNo;
	private String dmtiaNm;
	private String dmtiaGender;
	private String dmtiaPhone;
	private String protectorPhone;

	private String locMgrNo;
	private String locDat;
	private String lat;
	private String lon;
	private String gid;

	public String getDmtiaMgrNo() {
		return dmtiaMgrNo;
	}
	public void setDmtiaMgrNo(String dmtiaMgrNo) {
		this.dmtiaMgrNo = dmtiaMgrNo;
	}
	public String getDmtiaNm() {
		return dmtiaNm;
	}
	public void setDmtiaNm(String dmtiaNm) {
		this.dmtiaNm = dmtiaNm;
	}
	public String getDmtiaGender() {
		return dmtiaGender;
	}
	public void setDmtiaGender(String dmtiaGender) {
		this.dmtiaGender = dmtiaGender;
	}
	public String getDmtiaPhone() {
		return dmtiaPhone;
	}
	public void setDmtiaPhone(String dmtiaPhone) {
		this.dmtiaPhone = dmtiaPhone;
	}
	public String getProtectorPhone() {
		return protectorPhone;
	}
	public void setProtectorPhone(String protectorPhone) {
		this.protectorPhone = protectorPhone;
	}
	public String getLocMgrNo() {
		return locMgrNo;
	}
	public void setLocMgrNo(String locMgrNo) {
		this.locMgrNo = locMgrNo;
	}
	public String getLocDat() {
		return locDat;
	}
	public void setLocDat(String locDat) {
		this.locDat = locDat;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	@Override
	public String toString() {
		return "EventDmtiaUserVo [dmtiaMgrNo=" + dmtiaMgrNo + ", dmtiaNm=" + dmtiaNm + ", dmtiaGender=" + dmtiaGender
				+ ", dmtiaPhone=" + dmtiaPhone + ", protectorPhone=" + protectorPhone + ", locMgrNo=" + locMgrNo
				+ ", locDat=" + locDat + ", lat=" + lat + ", lon=" + lon + ", gid=" + gid + "]";
	}

}
