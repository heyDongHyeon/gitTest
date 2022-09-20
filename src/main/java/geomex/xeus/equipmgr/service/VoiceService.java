package geomex.xeus.equipmgr.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import geomex.xeus.user.service.UserVo;

/**
 * <pre>
 * 파일명 :  UserService.java
 * 설  명 :
 *   사용자 Service
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017-05-31      이주영          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 5. 31.
 * @version :  1.0
 * @see
 */
@Service("voiceService")
public class VoiceService extends EgovAbstractServiceImpl {

	@Resource(name = "voiceMapper")
    private VoiceMapper mapper;


	public ArrayList<VoiceVo> getList(HashMap<String, String> param) {
		ArrayList<VoiceVo> result=null;
		try {
			result = mapper.getList(param); // 아이디 비밀번호 둘다 맞을 경우 1을 리턴 아닐경우
		} catch (Exception e) {
			result=null;
			System.out.println("getAws DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public VoiceVo getItem(HashMap<String, String> param) {
		VoiceVo result=null;
		try {
			result = mapper.getItem(param); // 아이디 비밀번호 둘다 맞을 경우 1을 리턴 아닐경우
		} catch (Exception e) {
			result=null;
			System.out.println("getItem DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public Boolean add(VoiceVo param) throws Exception {
		Boolean result=false;
		try {
			mapper.add(param); 
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addAws DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public Boolean edit(VoiceVo param) {
		Boolean result=false;
		try {
			mapper.edit(param); 
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("modAws DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public Boolean del(HashMap<String, String> param) {
		Boolean result=false;
		try {
			mapper.del(param); 
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("delAws DAO Error : " + e.getMessage());
		} 
		return result;
	}
}
