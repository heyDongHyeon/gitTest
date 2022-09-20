package geomex.xeus.eventmonitor.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.xeus.sysmgr.service.ColumnVo;

/**
 * <pre>
 * 파일명 :  EliKmaAws10mMapper.java
 * 설  명 :
 *   기상정보AWS Mapper
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
@Mapper("eliKmaAws10mMapper")
public interface EliKmaAws10mMapper {

	public List<EliKmaAws10mVo> getList(HashMap<String, String> map);

	public EliKmaAws10mVo getItem(HashMap<String, String> map);

	public int getCount(HashMap<String, String> map);

	public int del(HashMap<String, String> map);

	public int add(EliKmaAws10mVo vo);

	public int edit(EliKmaAws10mVo vo);

	public List<ColumnVo> getColList(HashMap<String, String> map);

}
