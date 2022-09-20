package geomex.xeus.eventmonitor.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.xeus.sysmgr.service.ColumnVo;

/**
 * <pre>
 * 파일명 :  EliLayerMapper.java
 * 설  명 :
 *   긴급재난상황 지도 레이어 Mapper
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-05-04      이은규          최초 생성
 *
 * </pre>
 *
 * @since   :  2018. 05. 04.
 * @version :  1.0
 * @see
 */
@Mapper("eliLayerMapper")
public interface EliLayerMapper {

	public List<EliLayerVo> getList(HashMap<String, List<String>> map);

}
