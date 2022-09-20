package geomex.xeus.log.service;

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
@Mapper("accessMapper")
public interface AccessMapper {

	/**
	 * 로그 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<AccessVo> getList(HashMap<String, String> map) throws Exception;

	/**
	 * 로그 단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public AccessVo getItem(HashMap<String, String> map) throws Exception;

	/**
	 * 로그 수를 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int getCount(HashMap<String, String> map) throws Exception;

	/**
	 * 로그를 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int add(AccessVo vo) throws Exception;

	/**
     * 오늘 접속자 수를 조회합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public int getTodayAcesCount(HashMap<String, String> map) throws Exception;

    /**
     * 당일 시간대별 접속자 수를 조회합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public HashMap<String, String> getTodayAcesByTime(HashMap<String, String> map) throws Exception;

}
