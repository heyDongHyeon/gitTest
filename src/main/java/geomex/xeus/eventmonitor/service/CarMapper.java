package geomex.xeus.eventmonitor.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * <pre>
 * 파일명 :  FavCctvMapper.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2016-02-01      홍길동          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 12. 4.
 * @version :  1.0
 * @see
 */
@Mapper("carMapper")
public interface CarMapper {

	public int getCount(HashMap<String, String> map);


	public List<CarVo> getList(HashMap<String, String> map);


	public CarVo getItem(HashMap<String, String> map);


	public int del(HashMap<String, String> map);


	public int add(CarVo vo);


	public int edit(CarVo vo);

}
