package geomex.xeus.equipmgr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * <pre>
 * 파일명 :  UserMapper.java
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
 * @since   :  2017. 5. 31.
 * @version :  1.0
 * @see
 */
@Mapper("awsMapper")
public interface AwsMapper {
	public int add(AwsVo param);
	public ArrayList<AwsVo> getList(HashMap<String,String> param);
	public int edit(AwsVo param);
	public int del(HashMap<String, Object> param);
	public AwsVo getItem(HashMap<String, String> param);
}
