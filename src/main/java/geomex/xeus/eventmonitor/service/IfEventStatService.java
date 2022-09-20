package geomex.xeus.eventmonitor.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * 파일명 :  IfEventStatService.java
 * 설  명 :
 *   웹소켓 연동 서비스 발생 현황 서비스
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
@Service("ifEventStatService")
public class IfEventStatService extends EgovAbstractServiceImpl {

	@Resource(name = "ifEventStatMapper")
    private IfEventStatMapper mapper;

	/**
	 * 웹소켓 연동 서비스 발생 현황을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<IfEventStatVo> getList(HashMap<String, String> map) throws Exception {

		return (ArrayList<IfEventStatVo>) mapper.getList(map);
	}

	/**
	 * 웹소켓 연동 서비스 발생 현황 단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public IfEventStatVo getItem(HashMap<String, String> map) throws Exception {

		return mapper.getItem(map);
	}

	/**
     * 웹소켓 연동 서비스 발생 현황 수를 조회합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public int getCount(HashMap<String, String> map) throws Exception {

        int count = mapper.getCount(map);

        return count;

    }

	/**
	 * 웹소켓 연동 서비스 발생 현황을 삭제합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean del(HashMap<String, String> map) throws Exception {

		int state = mapper.del(map);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 웹소켓 연동 서비스 발생 현황을 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(IfEventStatVo vo) throws Exception {

		int state = mapper.add(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 웹소켓 연동 서비스 발생 현황을 수정합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(IfEventStatVo vo) throws Exception {

		int state = mapper.edit(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

}
