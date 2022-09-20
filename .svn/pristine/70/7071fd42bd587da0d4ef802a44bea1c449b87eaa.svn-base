package geomex.xeus.eventmonitor.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import geomex.xeus.sysmgr.service.ColumnVo;

/**
 * <pre>
 * 파일명 :  EliKhcAccService.java
 * 설  명 :
 *   고속도로특별상황관리 서비스
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
@Service("eliKhcAccService")
public class EliKhcAccService extends EgovAbstractServiceImpl {

	@Resource(name = "eliKhcAccMapper")
    private EliKhcAccMapper mapper;

	/**
	 * 고속도로특별상황관리 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EliKhcAccVo> getList(HashMap<String, String> map) throws Exception {

		return (ArrayList<EliKhcAccVo>) mapper.getList(map);
	}

	/**
	 * 고속도로특별상황관리 단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public EliKhcAccVo getItem(HashMap<String, String> map) throws Exception {

		return mapper.getItem(map);
	}

	/**
     * 고속도로특별상황관리 수를 조회합니다.
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
	 * 고속도로특별상황관리를 삭제합니다.
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
	 * 고속도로특별상황관리를 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(EliKhcAccVo vo) throws Exception {

		int state = mapper.add(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 고속도로특별상황관리를 수정합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(EliKhcAccVo vo) throws Exception {

		int state = mapper.edit(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
     * 고속도로특별상황관리 컬럼정보를 리턴합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public ArrayList<ColumnVo> getColList(HashMap<String, String> map) throws Exception {

        return (ArrayList<ColumnVo>) mapper.getColList(map);
    }

}
