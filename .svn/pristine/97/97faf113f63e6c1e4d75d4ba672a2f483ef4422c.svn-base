package geomex.xeus.tvius.service;

import java.util.List;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import geomex.xeus.util.code.StrUtil;
import geomex.xeus.util.code.SystemParameter;

/**
 * <pre>
 * 파일명 :  CrmsSysParamService.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017. 10. 26.      이은규          최초 생성
 *
 * </pre>
 *
 * @since  : 2017. 10. 26.
 * @version : 1.0
 * @see
 */
@Service("crmsSysParamService")
public class CrmsSysParamService {

	@Resource(name="crmsSysParamMapper")
    private CrmsSysParamMapper mapper;

	/**
	 * 영상반출 환경설정 목록을 조회합니다.
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public ArrayList<CrmsSysParamVo> getList(HashMap<String, String> map) throws Exception {

		ArrayList<CrmsSysParamVo> list = (ArrayList<CrmsSysParamVo>) mapper.getList(map);

		return list;
	}

	/**
	 * 영상반출 환경설정 코드 단건을 조회합니다. (여러가지 조건을 사용합니다.)
	 * getList로 대체 가능할 것으로 보아 보류
	 *
	 * @param map
	 * @return
	 * @throws Exception
	 */
	/*public CrmsSysParamVo getItem(HashMap<String, String> map) throws Exception {

		CrmsSysParamVo vo = mapper.getItem(map);

		return vo;
	}*/

	/**
	 * 영상반출 환경설정 코드를 삭제합니다.
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
	 * 영상반출 환경설정 코드를 추가합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean add(CrmsSysParamVo vo) throws Exception {

		int state = mapper.add(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
	 * 영상반출 환경설정 코드를 수정합니다.
	 *
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean edit(CrmsSysParamVo vo) throws Exception {

		int state = mapper.edit(vo);

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	/**
     * 영상반출 환경설정 코드를 수정합니다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean editSysParam(CrmsSysParamVo vo) throws Exception {

        int state = mapper.editSysParam(vo);

        if(state >= 1){
            return true;
        }else{
            return false;
        }

    }

	/**
	 * 영상반출 환경설정 코드 수를 조회합니다.
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
     * 이벤트를 전송할지 안할지 체크.
     *
     * @param statEvetTypCd
     * @param evtSvcNm
     * @return
     * @throws Exception
     */
    public boolean chkSysParam(String statEvetTypCd, String evtSvcNm) throws Exception{
    	boolean chk = false;
    	SystemParameter sysParam = new SystemParameter(getList(null));
    	HashMap<String, String>  map = sysParam.getParamMap();
    	String pkoKey = "";
    	if ( statEvetTypCd.equals("NDPSWARN") ) {
    		pkoKey = "event.ndps_evt";
    	} else if ( statEvetTypCd.equals("SMARTCCTV") ) {
    		pkoKey = "event.ivcp_evt";
    	} else if ( statEvetTypCd.equals("CCTVSHER") ) {
    		pkoKey = "event.cctv_evt";
    	}
    	HashMap<String, Object> EvtMap = StrUtil.strToMap(map.get(pkoKey).toString());//CCTV
    	if (EvtMap.get(evtSvcNm)!=null && EvtMap.get(evtSvcNm).equals("Y") ){
    		chk = true;
    	}

    	return chk;
    }

	/**
	 * param을 체크한다.
	 *
	 * @param evtSvcNm
	 * @return
	 * @throws Exception
	 */
	public boolean chkSysParam(String evtSvcNm) throws Exception{
    	boolean chk = false;
    	SystemParameter sysParam = new SystemParameter(getList(null));
    	HashMap<String, String>  map = sysParam.getParamMap();
    	String pkoKey = "event.ndps_evt";

    	HashMap<String, Object> EvtMap = StrUtil.strToMap(map.get(pkoKey).toString());

    	if ( EvtMap.get(evtSvcNm).equals("Y") ) {

    		chk = true;
    	}

    	return chk;
    }

	/**
	 * 임계치를 체크한다.
	 *
	 * @param evtSvcNm
	 * @return
	 * @throws Exception
	 */
	public boolean chkLimitSysParam(String evtSvcNm, Double data) throws Exception{
		boolean chk = false;
		SystemParameter sysParam = new SystemParameter(getList(null));
		HashMap<String, String>  map = sysParam.getParamMap();
		if ( Integer.parseInt(map.get(evtSvcNm).toString()) <= data)  {
			chk = true;
		}

		return chk;
	}

	/**
	 * 임계치를 체크한다.
	 *
	 * @param evtSvcNm
	 * @return
	 * @throws Exception
	 */
	public boolean chkLimitSysParam(String evtSvcNm, int data) throws Exception{
		boolean chk = false;
		SystemParameter sysParam = new SystemParameter(getList(null));
		HashMap<String, String>  map = sysParam.getParamMap();

		if ( Integer.parseInt(map.get(evtSvcNm).toString()) <= data)  {
			chk = true;
		}

		return chk;
	}

	/**
	 * 임계치를 체크한다.
	 *
	 * @param evtSvcNm
	 * @return 0 : 주의보 , 1 : 경보
	 * @throws Exception
	 */
	public String codeLimitSysParam(String evtSvcNm, int data) throws Exception{
		String msg = "";
		SystemParameter sysParam = new SystemParameter(getList(null));
		HashMap<String, String>  map = sysParam.getParamMap();

		if ( Integer.parseInt(map.get(evtSvcNm).toString().split(",")[0]) <= data)  {
			msg = "0";
		}


		if ( Integer.parseInt(map.get(evtSvcNm).toString().split(",")[1]) <= data)  {
			msg = "1";
		}

		System.out.println(evtSvcNm+"///"+Integer.parseInt(map.get(evtSvcNm).toString().split(",")[0])+"<<<"+data);
		return msg;
	}

}
