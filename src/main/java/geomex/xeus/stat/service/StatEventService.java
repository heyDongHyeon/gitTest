package geomex.xeus.stat.service;

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
@Service("statEventService")
public class StatEventService extends EgovAbstractServiceImpl {

	@Resource(name = "statEventMapper")
	private StatEventMapper mapper;

	public List<HashMap<String, Object>> getEventYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getEventYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getEventYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getEventMonth(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getEventMonth(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getEventMonth DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getEventDay(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getEventDay(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getEventDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getEventAllYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getEventAllYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getEventAllYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
}
