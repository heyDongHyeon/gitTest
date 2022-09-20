package geomex.xeus.ndps.service;

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
@Mapper("ndpsMapper")
public interface NdpsMapper{

	ArrayList<HashMap<String, String>> getData(HashMap<String, String> map);
	ArrayList<HashMap<String, String>> getAwsData(HashMap<String, String> map);
	ArrayList<HashMap<String, String>> getRealTimeData(HashMap<String, String> map);
	ArrayList<HashMap<String, String>> getList(HashMap<String, String> map);
	ArrayList<HashMap<String, String>> getAwsList(HashMap<String, String> map);
	ArrayList<HashMap<String, String>> getEqbList(HashMap<String, String> map);
	ArrayList<HashMap<String, String>> getAllList();

	List<HashMap<String, Object>> getStatYear(HashMap<String, Object> map);
	List<HashMap<String, Object>> getStatMonth(HashMap<String, Object> map);
	List<HashMap<String, Object>> getStatDay(HashMap<String, Object> map);

	ArrayList<HashMap<String, String>> getRainEqbList(HashMap<String, String> map);
	ArrayList<HashMap<String, String>> getTodayRainList(HashMap<String, String> map);
	ArrayList<HashMap<String, String>> getRecentAwsList(HashMap<String, String> map);

}
