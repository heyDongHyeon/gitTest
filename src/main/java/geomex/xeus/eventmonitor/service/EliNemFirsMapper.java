package geomex.xeus.eventmonitor.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.xeus.sysmgr.service.ColumnVo;

/**
 * <pre>
 * 파일명 :  EliNemFirsMapper.java
 * 설  명 :
 *   소방서 Mapper
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
@Mapper("eliNemFirsMapper")
public interface EliNemFirsMapper {

	public List<EliNemFirsVo> getList(HashMap<String, String> map);

	public EliNemFirsVo getItem(HashMap<String, String> map);

	public int getCount(HashMap<String, String> map);

	public int del(HashMap<String, String> map);

	public int add(EliNemFirsVo vo);

	public int edit(EliNemFirsVo vo);

	public List<ColumnVo> getColList(HashMap<String, String> map);

}
