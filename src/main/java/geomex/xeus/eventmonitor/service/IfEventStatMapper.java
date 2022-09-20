package geomex.xeus.eventmonitor.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * <pre>
 * 파일명 :  IfEventStatMapper.java
 * 설  명 :
 *   웹소켓 연동 서비스 발생 현황 Mapper
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-04-27      이은규          최초 생성
 *
 * </pre>
 *
 * @since   :  2018. 04. 27.
 * @version :  1.0
 * @see
 */
@Mapper("ifEventStatMapper")
public interface IfEventStatMapper {

	public List<IfEventStatVo> getList(HashMap<String, String> map);

	public IfEventStatVo getItem(HashMap<String, String> map);

	public int getCount(HashMap<String, String> map);

	public int del(HashMap<String, String> map);

	public int add(IfEventStatVo vo);

	public int edit(IfEventStatVo vo);

}
