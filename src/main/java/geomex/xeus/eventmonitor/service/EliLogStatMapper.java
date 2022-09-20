package geomex.xeus.eventmonitor.service;

import java.util.ArrayList;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * <pre>
 * 파일명 :  LogStatMapper.java
 * 설  명 :
 *   로그 통계 Mapper
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
@Mapper("eliLogStatMapper")
public interface EliLogStatMapper {

	public ArrayList<EliLogStatVo> getEliList(HashMap<String, String> map);

}
