package geomex.xeus.eventmonitor.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * 파일명 :  LogStatService.java
 * 설  명 :
 *   로그 통계 서비스
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-06-07      이은규          최초 생성
 *
 * </pre>
 *
 * @since   :  2018. 06. 07.
 * @version :  1.0
 * @see
 */
@Service("eliLogStatService")
public class EliLogStatService extends EgovAbstractServiceImpl {

	@Resource(name = "eliLogStatMapper")
    private EliLogStatMapper mapper;

	/**
	 * 112 로그 통계 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<EliLogStatVo> getEliList(HashMap<String, String> map) throws Exception {

		return (ArrayList<EliLogStatVo>)mapper.getEliList(map);
	}

}
