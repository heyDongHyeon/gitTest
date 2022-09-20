package geomex.xeus.eventmonitor.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.xeus.sysmgr.service.ColumnVo;

/**
 * <pre>
 * 파일명 :  StatusMapper.java
 * 설  명 :
 *   고속도로특별상황관리 Mapper
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
@Mapper("eliKhcAccMapper")
public interface EliKhcAccMapper {

	public List<EliKhcAccVo> getList(HashMap<String, String> map);

	public EliKhcAccVo getItem(HashMap<String, String> map);

	public int getCount(HashMap<String, String> map);

	public int del(HashMap<String, String> map);

	public int add(EliKhcAccVo vo);

	public int edit(EliKhcAccVo vo);

	public List<ColumnVo> getColList(HashMap<String, String> map);

}
