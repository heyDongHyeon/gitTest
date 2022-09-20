package geomex.xeus.ndpssend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import geomex.xeus.equipmgr.service.AwsVo;
import geomex.xeus.log.service.AccessVo;

/**
 * <pre>
 * 파일명 :  GeometryService.java
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
@Service("ndpsSendService")
public class NdpsSendService extends EgovAbstractServiceImpl {

	@Resource(name = "ndpsSendMapper")
    private NdpsSendMapper mapper;

	/**
	 * 사용자 그룹 정보를 가져온다.
	 * @param map
	 * @return 사용자 정보 리스트
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSMSGroupInfo(HashMap<String, String> map) {
		List<HashMap<String, String>> result=null;
		try {
			result = mapper.getSMSGroupInfo(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getSMSGroupInfo DAO Error : " + e.getMessage());
		}
		return result;
	}

	/**
	 * 검색한 사용자에 대한 사용자 정보를 가져온다. 사용자가 null이면 모든 사용자 정보를 가져온다.
	 * @param map
	 * @return 사용자 정보 리스트
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSMSInfo(HashMap<String, String> map) {
		List<HashMap<String, String>> result=null;
		try {
			result = mapper.getSMSInfo(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getSMSInfo DAO Error : " + e.getMessage());
		}
		return result;
	}
	/**
     * 해당 읍면동에 대한 음성장비 목록 가져오기. 읍면동이 없으면 모든 음성장비 목록 가져오기.
     * @param map
     * @return 음성장비 리스트
     * @throws Exception
     */
	public List<HashMap<String, String>> getVoiceInfo(HashMap<String, String> map) {
		List<HashMap<String, String>> result=null;
		try {
			result = mapper.getVoiceInfo(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getVoiceInfo DAO Error : " + e.getMessage());
		}
		return result;
	}
	/**
	 * SMS 메세지들를 보낸다.
	 * - 다수
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean sendAllSMS(List<HashMap<String, String>> list) throws Exception {
		Boolean result=false;
		try {
			mapper.sendAllSMS(list);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("sendAllSMS DAO Error : " + e.getMessage());
		}
		return result;
	}
	/**
     * 선택한 음성장비들에게 메세지 내용을 전달합니다.
     * @param list
     * @return true or false
     * @throws Exception
     */
	public boolean sendAllVoice(List<HashMap<String, String>> list) {
		Boolean result=false;
		try {
			mapper.sendAllVoice(list);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("sendAllVoice DAO Error : " + e.getMessage());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getSMSTreeGroupInfo(HashMap<String, String> param) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getSMSTreeGroupInfo(param); // 아이디 비밀번호 둘다 맞을 경우 1을 리턴 아닐경우
		} catch (Exception e) {
			System.out.println("에러가 났습니다. 밑에 에러를 확인해주세요.");
			e.printStackTrace();
			System.out.println(e.getMessage());
			result=null;
		} 
		return result;
	}
}
