package geomex.xeus.ndms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
@Mapper("ndmsApiMapper")
public interface NdmsApiMapper {

	public int addCmDsr(List<HashMap<Object, Object>> list) throws Exception;
	public int addKmaInform(List<HashMap<Object, Object>> list) throws Exception;
	public int addKmaPreInform(List<HashMap<Object, Object>> list) throws Exception;
//	public int addKmaEarthInfm(List<HashMap<Object, Object>> list) throws Exception;
	public int addNemPornqt(List<HashMap<Object, Object>> list) throws Exception;
	public int addHrfDmmst(List<HashMap<Object, Object>> list) throws Exception;
	public int addKmaAws(List<HashMap<Object, Object>> list) throws Exception;
	public int addNemPowlvl(List<HashMap<Object, Object>> list) throws Exception;
	public int addKmaDfsShrtPop(List<HashMap<Object, Object>> list) throws Exception;
	public void addTest(HashMap<Object, Object> map);
	public void addEventTest(HashMap<Object, Object> map);
	
	public void addKmaAlertInfo(HashMap<Object, Object> map);
	public void preAddKmaAlertInfo(HashMap<Object, Object> map);
	public List<HashMap<Object, Object>> getKmaAlertInfo(HashMap<String, String> map);
	public HashMap<Object, Object> getKmaAlertEventIdCount(HashMap<Object, Object> param);
	public List<HashMap<Object, Object>> getRealTest(HashMap<String, String> map);
	public void modKmaAlertInfo(HashMap<Object, Object> param);
	public void preModKmaAlertInfo(HashMap<Object, Object> param);

}
