package geomex.xeus.ndpssend.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import geomex.xeus.log.service.AccessVo;

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
@Mapper("ndpsSendMapper")
public interface NdpsSendMapper {


	public List<HashMap<String, String>> getSMSInfo(HashMap<String, String> map);
	public List<HashMap<String, String>> getSMSGroupInfo(HashMap<String, String> map);
	public List<HashMap<String, String>> getVoiceInfo(HashMap<String, String> map);
	public int sendAllSMS(List<HashMap<String, String>> map) ;
	public int sendAllVoice(List<HashMap<String, String>> map);
	public ArrayList<HashMap<String, String>> getSMSTreeGroupInfo(HashMap<String, String> param);
}
