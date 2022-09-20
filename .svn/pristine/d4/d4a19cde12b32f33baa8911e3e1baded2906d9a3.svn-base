package geomex.xeus.eventmonitor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * 파일명 :  EliLayerService.java
 * 설  명 :
 *   긴급재난상황 지도 레이어 서비스
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
@Service("eliLayerService")
public class EliLayerService extends EgovAbstractServiceImpl {

	@Resource(name = "eliLayerMapper")
    private EliLayerMapper mapper;

	/**
	 * 긴급재난상황 지도 레이어용 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EliLayerVo> getList(HashMap<String, List<String>> map) throws Exception {

	    return (ArrayList<EliLayerVo>) mapper.getList(map);
	}

}
