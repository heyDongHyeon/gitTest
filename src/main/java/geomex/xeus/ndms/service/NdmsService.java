package geomex.xeus.ndms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import geomex.xeus.equipmgr.service.DisbordVo;

/**
 * <pre>
 * 파일명 :  GeometryService.java
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
 * @since   :  2017. 9. 5.
 * @version :  1.0
 * @see
 */
@Service("ndmsService")
public class NdmsService extends EgovAbstractServiceImpl {

	@Resource(name = "ndmsMapper")
	private NdmsMapper mapper;

	public ArrayList<HashMap<String, String>> getList(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getListAws(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getListAws DAO Error : " + e.getMessage());
		}
		return result;
	}


	public ArrayList<HashMap<String, String>> getListByInf119Mstr(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getListByInf119Mstr(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getListByInf119Mstr DAO Error : " + e.getMessage());
		}
		return result;
	}


	public ArrayList<HashMap<String, String>> getStatByInf119Mstr(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getStatByInf119Mstr(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getStatByInf119Mstr DAO Error : " + e.getMessage());
		}
		return result;
	}





}
