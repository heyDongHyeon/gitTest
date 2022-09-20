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
@Service("disbordService")
public class DisbordService extends EgovAbstractServiceImpl {

	@Resource(name = "disbordMapper")
    private DisbordMapper mapper;


	public ArrayList<DisbordVo> getList(HashMap<String, String> param) {
		ArrayList<DisbordVo> result=null;
		try {
			result = mapper.getList(param); // 아이디 비밀번호 둘다 맞을 경우 1을 리턴 아닐경우
		} catch (Exception e) {
			result=null;
			System.out.println("getList DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public DisbordVo getItem(HashMap<String, String> param) {
		DisbordVo result=null;
		try {
			result = mapper.getItem(param); // 아이디 비밀번호 둘다 맞을 경우 1을 리턴 아닐경우
		} catch (Exception e) {
			result=null;
			System.out.println("getItem DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public DisbordVo getMgr(HashMap<String, String> param) {
		DisbordVo result=null;
		try {
			result = mapper.getMgr(param); // 아이디 비밀번호 둘다 맞을 경우 1을 리턴 아닐경우
		} catch (Exception e) {
			result=null;
			System.out.println("getMgr DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public Boolean add(DisbordVo param){
		Boolean result=false;
		try {
			mapper.add(param); 
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("add DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public Boolean edit(DisbordVo param) {
		Boolean result=false;
		try {
			mapper.edit(param); 
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("edit DAO Error : " + e.getMessage());
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
			System.out.println("del DAO Error : " + e.getMessage());
		} 
		return result;
	}
}
