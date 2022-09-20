package geomex.xeus.ivcp.service;

import java.util.ArrayList;
import java.util.HashMap;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * <pre>
 * 파일명 :  AccessMapper.java
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
 * @since   :  2017. 6. 22.
 * @version :  1.0
 * @see
 */
@Mapper("ivcpMapper")
public interface IVCPMapper {

	public ArrayList<IVCPVo> getList(HashMap<String, String> map) throws Exception;

	public IVCPVo getItem(HashMap<String, String> map) throws Exception;

	public int getCount(HashMap<String, String> map) throws Exception;

	public int add(HashMap<String, String> map) throws Exception;

}
