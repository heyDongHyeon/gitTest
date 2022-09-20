package geomex.xeus.equipmgr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.xeus.user.service.UserVo;

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
@Mapper("brdcstTrmnlsMapper")
public interface BrdcstTrmnlsMapper {
	public int add(HashMap<String, String> param);
	public int edit(HashMap<String, String> param);
	public int del(HashMap<String, String> param);
	public ArrayList<BrdcstTrmnlsVo> getList(HashMap<String,String> param);
	public BrdcstTrmnlsVo getItem(HashMap<String, String> param);
}
