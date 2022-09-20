package geomex.xeus.equipmgr.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * 파일명 :  IpService.java
 * 설  명 :
 *   상태 관리 서비스
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017-06-16      이주영          최초 생성
 * 2018-03-23      이은규          투망모니터링용 추가
 *
 * </pre>
 *
 * @since   :  2017. 6. 15.
 * @version :  1.0
 * @see
 */
@Service("CctvService")
public class CctvService extends EgovAbstractServiceImpl {

	@Resource(name = "CctvMapper")
    private CctvMapper mapper;

	/**
	 * CCTV 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
//	public ArrayList<CctvVo> getList(HashMap<String, String> map) throws Exception {
//		
//		return (ArrayList<CctvVo>) mapper.getList(map);
//	}
	public ArrayList<CctvVo> getList(HashMap<String, String> map) {
		ArrayList<CctvVo> result=null;
		try {
			result = (ArrayList<CctvVo>) mapper.getList(map); // 아이디 비밀번호 둘다 맞을 경우 1을 리턴 아닐경우
		} catch (Exception e) {
			result=null;
			System.out.println("getAws DAO Error : " + e.getMessage());
		} 
		return result;
	}
	/**
	 * CCTV 단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public CctvVo getItem(HashMap<String, String> map) throws Exception {

		return mapper.getItem(map);
	}
	/**
	 * CCTV의 관리자를 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public CctvVo getMgr(HashMap<String, String> map) throws Exception {

		return mapper.getMgr(map);
	}
	/**
     * srid, lat, lon으로 geometry를 생성하여 리턴합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public CctvVo makeGeometry(HashMap<String, String> map) throws Exception {

        return mapper.makeGeometry(map);
    }

	/**
     * 투망모니터링 아이템을 리턴합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public CctvVo getNetItem(HashMap<String, String> map) throws Exception {

        return mapper.getNetItem(map);
    }

    /**
     * 투망모니터링 대상 CCTV의 영역을 구합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public CctvVo getBoundingExtent(HashMap<String, List<String>> map) throws Exception {

        return mapper.getBoundingExtent(map);
    }

	/**
	 * CCTV RTST IP를 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public CctvVo getRTSP(HashMap<String, String> map) throws Exception {

		return mapper.getRTSP(map);
	}

	/**
	 * CCTV를 삭제합니다.
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
	 * CCTV를 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
//	public boolean add(CctvVo vo) throws Exception {
//		System.out.println("1");
//		int state = mapper.add(vo);
//		System.out.println("2"+state);
//		if(state == 1){
//			return true;
//		}else{
//			return false;
//		}
//	}
	public boolean add(CctvVo param) throws Exception {
		boolean result=false;
		try {
			mapper.add(param); 
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addCctv DAO Error : " + e.getMessage());
		} 
		return result;
	}

	/**
	 * CCTV를 수정합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(CctvVo vo) throws Exception {

		int state = mapper.edit(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * CCTV 수를 조회합니다.
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
     * CCTV 수를 조회합니다.
     *
     * @param map
     * @return
     * @throws Exception
     */
    public boolean init() throws Exception {

        int state = mapper.init();

        if(state >= 1){
            return true;
        }else{
            return false;
        }

    }

	

}
