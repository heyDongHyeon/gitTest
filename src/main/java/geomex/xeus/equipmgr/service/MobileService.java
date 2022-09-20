package geomex.xeus.equipmgr.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * 파일명 :  IpService.java
 * 설  명 :
 *   Mobile 관리 서비스
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017-06-16      이주영          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 6. 15.
 * @version :  1.0
 * @see
 */
@Service("mobileService")
public class MobileService extends EgovAbstractServiceImpl {

	@Resource(name = "mobileMapper")
    private MobileMapper mapper;

	/**
	 * Mobile 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<MobileVo> getList(HashMap<String, String> map) throws Exception {

		return (ArrayList<MobileVo>) mapper.getList(map);
	}

	/**
	 * Mobile 단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public MobileVo getItem(HashMap<String, String> map) throws Exception {

		return mapper.getItem(map);
	}

	/**
	 * Mobile을 삭제합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean del(HashMap<String, String> map) throws Exception {

		int state = mapper.del(map);

		if(state > 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * Mobile을 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(MobileVo vo) throws Exception {

		int state = mapper.add(vo);

		if(state >= 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * Mobile을 수정합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(MobileVo vo) throws Exception {

		int state = mapper.edit(vo);

		if(state >= 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * Mobile 수를 조회합니다.
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
