package geomex.xeus.equipmgr.service;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * <pre>
 * 파일명 :  UserVo.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017-05-31      이주영          최초 생성
 * 2017-12-11	   이은규		   로그인 제한 횟수, 사용자 등록IP 추가
 * 2018-01-08      이은규          subDir 생성(업로드 경로 서브 폴더이름)
 *
 * </pre>
 *
 * @since   :  2017. 5. 31.
 * @version :  1.0
 * @see
 */
public class BrdcstTrmnlsVo {

	private String group_innb;
	private String orgnztInnb;
	private String groupNm;
	private String groupSe;
	private String upperGroupInnb;
	private String groupOrdr;
	private String innb;
	private String se;
	private String nm;
	private String lc;
	private String telno;
	private String instlde;
	private String instlSe;
	private String srcelctSe;
	private String outptSe;
	private String la;
	private String lo;

	public String getGroup_innb() {
		return group_innb;
	}
	public void setGroup_innb(String group_innb) {
		this.group_innb = group_innb;
	}
	public String getOrgnztInnb() {
		return orgnztInnb;
	}
	public void setOrgnztInnb(String orgnztInnb) {
		this.orgnztInnb = orgnztInnb;
	}
	public String getGroupNm() {
		return groupNm;
	}
	public void setGroupNm(String groupNm) {
		this.groupNm = groupNm;
	}
	public String getGroupSe() {
		return groupSe;
	}
	public void setGroupSe(String groupSe) {
		this.groupSe = groupSe;
	}
	public String getUpperGroupInnb() {
		return upperGroupInnb;
	}
	public void setUpperGroupInnb(String upperGroupInnb) {
		this.upperGroupInnb = upperGroupInnb;
	}
	public String getGroupOrdr() {
		return groupOrdr;
	}
	public void setGroupOrdr(String groupOrdr) {
		this.groupOrdr = groupOrdr;
	}
	public String getInnb() {
		return innb;
	}
	public void setInnb(String innb) {
		this.innb = innb;
	}
	public String getSe() {
		return se;
	}
	public void setSe(String se) {
		this.se = se;
	}
	public String getNm() {
		return nm;
	}
	public void setNm(String nm) {
		this.nm = nm;
	}
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getInstlde() {
		return instlde;
	}
	public void setInstlde(String instlde) {
		this.instlde = instlde;
	}
	public String getInstlSe() {
		return instlSe;
	}
	public void setInstlSe(String instlSe) {
		this.instlSe = instlSe;
	}
	public String getSrcelctSe() {
		return srcelctSe;
	}
	public void setSrcelctSe(String srcelctSe) {
		this.srcelctSe = srcelctSe;
	}
	public String getOutptSe() {
		return outptSe;
	}
	public void setOutptSe(String outptSe) {
		this.outptSe = outptSe;
	}
	public String getLa() {
		return la;
	}
	public void setLa(String la) {
		this.la = la;
	}
	public String getLo() {
		return lo;
	}
	public void setLo(String lo) {
		this.lo = lo;
	}
	@Override
	public String toString() {
		return "BrdcstTrmnlsVo [group_innb=" + group_innb + ", orgnztInnb=" + orgnztInnb + ", groupNm=" + groupNm
				+ ", groupSe=" + groupSe + ", upperGroupInnb=" + upperGroupInnb + ", groupOrdr=" + groupOrdr + ", innb="
				+ innb + ", se=" + se + ", nm=" + nm + ", lc=" + lc + ", telno=" + telno + ", instlde=" + instlde
				+ ", instlSe=" + instlSe + ", srcelctSe=" + srcelctSe + ", outptSe=" + outptSe + ", la=" + la + ", lo="
				+ lo + ", getGroup_innb()=" + getGroup_innb() + ", getOrgnztInnb()=" + getOrgnztInnb()
				+ ", getGroupNm()=" + getGroupNm() + ", getGroupSe()=" + getGroupSe() + ", getUpperGroupInnb()="
				+ getUpperGroupInnb() + ", getGroupOrdr()=" + getGroupOrdr() + ", getInnb()=" + getInnb() + ", getSe()="
				+ getSe() + ", getNm()=" + getNm() + ", getLc()=" + getLc() + ", getTelno()=" + getTelno()
				+ ", getInstlde()=" + getInstlde() + ", getInstlSe()=" + getInstlSe() + ", getSrcelctSe()="
				+ getSrcelctSe() + ", getOutptSe()=" + getOutptSe() + ", getLa()=" + getLa() + ", getLo()=" + getLo()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}


}
