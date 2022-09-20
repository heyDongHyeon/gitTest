package geomex.xeus.eventmonitor.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.xeus.sysmgr.service.ColumnVo;

/**
 * <pre>
 * 파일명 :  EliRtsaOccuridMapper.java
 * 설  명 :
 *   시가지도로돌발상황정보 Mapper
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
@Mapper("eliRtsaOccuridMapper")
public interface EliRtsaOccuridMapper {

	public List<EliRtsaOccuridVo> getList(HashMap<String, String> map);

	public EliRtsaOccuridVo getItem(HashMap<String, String> map);

	public int getCount(HashMap<String, String> map);

	public int del(HashMap<String, String> map);

	public int add(EliRtsaOccuridVo vo);

	public int edit(EliRtsaOccuridVo vo);

	public List<ColumnVo> getColList(HashMap<String, String> map);

}
