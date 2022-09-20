package geomex.xeus.equipmgr.service;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <pre>
 * 파일명 :  EmrbellVo.java
 * 설  명 :
 *   비상벨 Vo
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-09-13      이은규          최초 생성
 *
 * </pre>
 *
 * @since   :  2018. 9. 13.
 * @version :  1.0
 * @see
 */

public class EmrbellVo {

    @NotNull(message="비상벨단말번호는 필수사항 입니다.")
    @NotEmpty(message="비상벨단말번호는 필수사항 입니다.")
    @Size(min=1, max=10, message="비상벨단말번호는 최대 10자 까지 입력하실 수 있습니다.")
    private String bellNo;

    @NotNull(message="CCTV관리번호는 필수사항 입니다.")
    @NotEmpty(message="CCTV관리번호는 필수사항 입니다.")
    @Size(min=1, max=10, message="CCTV관리번호는 최대 10자 까지 입력하실 수 있습니다.")
    private String cctvMgrNo;

    @Size(min=1, max=255, message="위치설명은 최대 255자 까지 입력하실 수 있습니다.")
    private String addrDesc;

    public String getBellNo() {
        return bellNo;
    }
    public void setBellNo(String bellNo) {
        this.bellNo = bellNo;
    }
    public String getCctvMgrNo() {
        return cctvMgrNo;
    }
    public void setCctvMgrNo(String cctvMgrNo) {
        this.cctvMgrNo = cctvMgrNo;
    }
    public String getAddrDesc() {
        return addrDesc;
    }
    public void setAddrDesc(String addrDesc) {
        this.addrDesc = addrDesc;
    }

}
