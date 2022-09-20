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
@Service("statNdmsService")
public class StatNdmsService extends EgovAbstractServiceImpl {

	@Resource(name = "statNdmsMapper")
	private StatNdmsMapper mapper;

	public List<HashMap<String, Object>> getNdmsYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getNdmsYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getNdmsYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getNdmsMonth(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getNdmsMonth(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getNdmsMonth DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getAwsDay(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getAwsDay(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getAwsDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getAwsAllYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getAwsAllYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getAwsAllYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getPowlvlAllYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getPowlvlAllYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getPowlvlAllYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getPowlvlYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getPowlvlYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getPowlvlYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getPowlvlMonth(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getPowlvlMonth(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getPowlvlMonth DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getPowlvlDay(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getPowlvlDay(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getPowlvlDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getDmmstAllYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getDmmstAllYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getDmmstAllYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getDmmstYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getDmmstYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getDmmstYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getDmmstMonth(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getDmmstMonth(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getDmmstMonth DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getDmmstDay(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getDmmstDay(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getDmmstDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getDsrAllYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getDsrAllYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getDsrAllYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getDsrYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getDsrYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getDsrYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getDsrMonth(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getDsrMonth(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getDsrMonth DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getDsrDay(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getDsrDay(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getDsrDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public List<HashMap<String, Object>> getNdmsDay(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getNdmsDay(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getNdmsDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addNdmsChartYear(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addNdmsChartYear(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addNdmsChartYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addNdmsTableYear(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addNdmsTableYear(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addNdmsTableYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addNdmsChartMonth(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addNdmsChartMonth(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addNdmsChartMonth DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addNdmsTableMonth(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addNdmsTableMonth(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addNdmsTableMonth DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addNdmsChartDay(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addNdmsChartDay(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addNdmsChartDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addNdmsTableDay(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addNdmsTableDay(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addNdmsTableDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addDsrChartYear(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addDsrChartYear(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addDsrChartYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addDsrTableYear(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addDsrTableYear(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addDsrTableYear DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addDsrChartMonth(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addDsrChartMonth(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addDsrChartMonth DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addDsrTableMonth(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addDsrTableMonth(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addDsrTableMonth DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addDsrChartDay(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addDsrChartDay(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addDsrChartDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addDsrTableDay(HashMap<String, Object> map) {
		boolean result=false;
		try {
			mapper.addDsrTableDay(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addDsrTableDay DAO Error : " + e.getMessage());
		} 
		return result;
	}
}
