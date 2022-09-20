package geomex.xeus.eventmonitor.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import geomex.xeus.sysmgr.service.ColumnVo;

/**
 * <pre>
 * 파일명 :  EliRtsaOccuridService.java
 * 설  명 :
 *   시가지도로돌발상황정보 서비스
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
@Service("eliRtsaOccuridService")
public class EliRtsaOccuridService extends EgovAbstractServiceImpl {

	@Resource(name = "eliRtsaOccuridMapper")
    private EliRtsaOccuridMapper mapper;

	/**
	 * 시가지도로돌발상황정보 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EliRtsaOccuridVo> getList(HashMap<String, String> map) throws Exception {

		return (ArrayList<EliRtsaOccuridVo>) mapper.getList(map);
	}

	/**
	 * 시가지도로돌발상황정보 단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public EliRtsaOccuridVo getItem(HashMap<String, String> map) throws Exception {

		return mapper.getItem(map);
	}

	/**
     * 시가지도로돌발상황정보 수를 조회합니다.
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
	 * 시가지도로돌발상황정보를 삭제합니다.
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
	 * 시가지도로돌발상황정보를 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(EliRtsaOccuridVo vo) throws Exception {

		int state = mapper.add(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 시가지도로돌발상황정보를 수정합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(EliRtsaOccuridVo vo) throws Exception {

		int state = mapper.edit(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
     * 시가지도로돌발상황정보 컬럼정보를 리턴합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ArrayList<ColumnVo> getColList(HashMap<String, String> map) throws Exception {

        return (ArrayList<ColumnVo>) mapper.getColList(map);
    }

}
