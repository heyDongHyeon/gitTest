package geomex.xeus.eventmonitor.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import geomex.xeus.sysmgr.service.ColumnVo;

/**
 * <pre>
 * 파일명 :  EliKmaDfsShrtService.java
 * 설  명 :
 *   동네예보 서비스
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-04-13      이은규          최초 생성
 *
 * </pre>
 *
 * @since   :  2018. 04. 13.
 * @version :  1.0
 * @see
 */
@Service("eliKmaDfsShrtService")
public class EliKmaDfsShrtService extends EgovAbstractServiceImpl {

	@Resource(name = "eliKmaDfsShrtMapper")
    private EliKmaDfsShrtMapper mapper;

	/**
	 * 동네예보 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EliKmaDfsShrtVo> getList(HashMap<String, String> map) throws Exception {

		return (ArrayList<EliKmaDfsShrtVo>) mapper.getList(map);
	}

	/**
	 * 동네예보 단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public EliKmaDfsShrtVo getItem(HashMap<String, String> map) throws Exception {

		return mapper.getItem(map);
	}

	/**
     * 동네예보 수를 조회합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public int getCount(HashMap<String, String> map) throws Exception {

        int count = mapper.getCount(map);

        return count;

    }

	/**
	 * 동네예보를 삭제합니다.
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
	 * 동네예보를 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(EliKmaDfsShrtVo vo) throws Exception {

		int state = mapper.add(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 동네예보를 수정합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(EliKmaDfsShrtVo vo) throws Exception {

		int state = mapper.edit(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
     * 동네예보 컬럼정보를 리턴합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ArrayList<ColumnVo> getColList(HashMap<String, String> map) throws Exception {

        return (ArrayList<ColumnVo>) mapper.getColList(map);
    }

}
