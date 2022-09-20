package geomex.xeus.equipmgr.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  AcsryVo.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2016-02-01      홍길동          최초 생성
 * 2016-09-13      이은규          테이블 변경으로 인한 수정
 *
 * </pre>
 *
 * @since   :  2017. 8. 30.
 * @version :  1.0
 * @see
 */

public class MobileVo {

	private String mgrNo;
	@NotNull(message="사용자ID는 필수사항 입니다.")
	@NotEmpty(message="사용자ID는 필수사항 입니다.")
	private String userId;
	private String userNm;
	private String purpCd;
	@Size(min=1, max=50, message="모델명은 최대 50자 까지 입력하실 수 있습니다.")
	private String modelNm;
	private String deviceId;
	private String authStatCd;
	private String statChgDat;
	private String acptUsrId;
	private String acptUserNm;
	private String lastLogDat;
	private String loginYn;

	/**
	 * @return the mgrNo
	 */
	public String getMgrNo() {
		return mgrNo;
	}
	/**
	 * @param mgrNo the mgrNo to set
	 */
	public void setMgrNo(String mgrNo) {
		this.mgrNo = mgrNo;
	}
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getPurpCd() {
        return purpCd;
    }
    public void setPurpCd(String purpCd) {
        this.purpCd = purpCd;
    }
    /**
	 * @return the modelNm
	 */
	public String getModelNm() {
		return modelNm;
	}
	/**
	 * @param modelNm the modelNm to set
	 */
	public void setModelNm(String modelNm) {
		this.modelNm = modelNm;
	}
	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}
	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * @return the authStatCd
	 */
	public String getAuthStatCd() {
		return authStatCd;
	}
	/**
	 * @param authStatCd the authStatCd to set
	 */
	public void setAuthStatCd(String authStatCd) {
		this.authStatCd = authStatCd;
	}
	/**
	 * @return the statChgDat
	 */
	public String getStatChgDat() {
		return statChgDat;
	}
	/**
	 * @param statChgDat the statChgDat to set
	 */
	public void setStatChgDat(String statChgDat) {
		this.statChgDat = statChgDat;
	}
	/**
	 * @return the acptUsrId
	 */
	public String getAcptUsrId() {
		return acptUsrId;
	}
	/**
	 * @param acptUsrId the acptUsrId to set
	 */
	public void setAcptUsrId(String acptUsrId) {
		this.acptUsrId = acptUsrId;
	}
	public String getAcptUserNm() {
		return acptUserNm;
	}
	public void setAcptUserNm(String acptUserNm) {
		this.acptUserNm = acptUserNm;
	}
	/**
	 * @return the lastLogDat
	 */
	public String getLastLogDat() {
		return lastLogDat;
	}
	/**
	 * @param lastLogDat the lastLogDat to set
	 */
	public void setLastLogDat(String lastLogDat) {
		this.lastLogDat = lastLogDat;
	}
	/**
	 * @return the loginYn
	 */
	public String getLoginYn() {
		return loginYn;
	}
	/**
	 * @param loginYn the loginYn to set
	 */
	public void setLoginYn(String loginYn) {
		this.loginYn = loginYn;
	}

}
