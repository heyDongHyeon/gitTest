package geomex.xeus.equipmgr.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * 파일명 :  EmrbellService.java
 * 설  명 :
 *   비상벨 서비스
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
@Service("emrbellService")
public class EmrbellService extends EgovAbstractServiceImpl {

	@Resource(name = "emrbellMapper")
    private EmrbellMapper mapper;

	/**
	 * 비상벨 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EmrbellVo> getList(HashMap<String, String> map) throws Exception {

		return (ArrayList<EmrbellVo>) mapper.getList(map);
	}

	/**
	 * 비상벨 단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public EmrbellVo getItem(HashMap<String, String> map) throws Exception {

	    EmrbellVo vo = mapper.getItem(map);

		return vo;
	}

	/**
	 * 비상벨을 삭제합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean del(HashMap<String, String> map) throws Exception {

		int state = mapper.del(map);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 비상벨을 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(EmrbellVo vo) throws Exception {

		int state = mapper.add(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 비상벨을 수정합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(EmrbellVo vo) throws Exception {

		int state = mapper.edit(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 비상벨 수를 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getCount(HashMap<String, String> map) throws Exception {

		int count = mapper.getCount(map);

		return count;

	}

}
