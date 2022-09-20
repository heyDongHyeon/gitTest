package geomex.xeus.ndms.service;

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
@Mapper("ndmsMapper")
public interface NdmsMapper{

	ArrayList<HashMap<String, String>> getListAws(HashMap<String, String> map);

	ArrayList<HashMap<String, String>> getListByInf119Mstr(HashMap<String, String> map);

	ArrayList<HashMap<String, String>> getStatByInf119Mstr(HashMap<String, String> map);

	void insertByInf119Mstr(HashMap<String, String> map);

	void editByInf119Mstr(HashMap<String, String> map);

}
