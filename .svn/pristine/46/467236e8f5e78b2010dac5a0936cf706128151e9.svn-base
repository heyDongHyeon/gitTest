package geomex.xeus.log.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * <pre>
 * 파일명 :  IfDscLogMapper.java
 * 설  명 :
 *   사회적약자 로그 Mapper
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
@Mapper("ifDscLogMapper")
public interface IfDscLogMapper {

	public List<IfDscLogVo> getList(HashMap<String, String> map);

	public IfDscLogVo getItem(HashMap<String, String> map);

	public int getCount(HashMap<String, String> map);

	public int del(HashMap<String, String> map);

	public int add(IfDscLogVo vo);

	public int edit(IfDscLogVo vo);

}
