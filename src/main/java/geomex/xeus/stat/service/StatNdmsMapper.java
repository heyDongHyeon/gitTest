package geomex.xeus.stat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.xeus.equipmgr.service.DisbordVo;

/**
 * <pre>
 * 파일명 :  GeometryMapper.java
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
@Mapper("statNdmsMapper")
public interface StatNdmsMapper {
	List<HashMap<String, Object>> getNdmsYear(HashMap<String, Object> map);
	List<HashMap<String, Object>> getNdmsMonth(HashMap<String, Object> map);
	List<HashMap<String, Object>> getAwsDay(HashMap<String, Object> map);
	List<HashMap<String, Object>> getAwsAllYear(HashMap<String, Object> map);
	List<HashMap<String, Object>> getPowlvlAllYear(HashMap<String, Object> map);
	List<HashMap<String, Object>> getPowlvlYear(HashMap<String, Object> map);
	List<HashMap<String, Object>> getPowlvlMonth(HashMap<String, Object> map);
	List<HashMap<String, Object>> getPowlvlDay(HashMap<String, Object> map);
	List<HashMap<String, Object>> getDmmstAllYear(HashMap<String, Object> map);
	List<HashMap<String, Object>> getDmmstYear(HashMap<String, Object> map);
	List<HashMap<String, Object>> getDmmstMonth(HashMap<String, Object> map);
	List<HashMap<String, Object>> getDmmstDay(HashMap<String, Object> map);
	List<HashMap<String, Object>> getDsrAllYear(HashMap<String, Object> map);
	List<HashMap<String, Object>> getDsrYear(HashMap<String, Object> map);
	List<HashMap<String, Object>> getDsrMonth(HashMap<String, Object> map);
	List<HashMap<String, Object>> getDsrDay(HashMap<String, Object> map);
	List<HashMap<String, Object>> getNdmsDay(HashMap<String, Object> map);
	void addNdmsChartYear(HashMap<String, Object> map);
	void addNdmsTableYear(HashMap<String, Object> map);
	void addNdmsChartMonth(HashMap<String, Object> map);
	void addNdmsTableMonth(HashMap<String, Object> map);
	void addNdmsChartDay(HashMap<String, Object> map);
	void addNdmsTableDay(HashMap<String, Object> map);
	
	void addDsrChartYear(HashMap<String, Object> map);
	void addDsrTableYear(HashMap<String, Object> map);
	void addDsrChartMonth(HashMap<String, Object> map);
	void addDsrTableMonth(HashMap<String, Object> map);
	void addDsrChartDay(HashMap<String, Object> map);
	void addDsrTableDay(HashMap<String, Object> map);
	
}
