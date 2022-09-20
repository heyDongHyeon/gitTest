package geomex.xeus.map.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * <pre>
 * 파일명 :  searchServiceImpl.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017. 5. 29.      전우람          최초 생성
 *
 * </pre>
 *
 * @since  : 2017. 5. 29.
 * @version : 1.0
 * @see
 */
@Service("searchService")
public class SearchService extends EgovAbstractServiceImpl{
    @Resource(name="searchMapper")
    private SearchMapper dao;


    //주소를 보내면 위치 값을 가져온다.
    public HashMap<String, String> getAddrPosi( String addr) throws Exception {

    	HashMap<String, String> map = new HashMap<String, String>();
    	HashMap<String, String> reqMap = new HashMap<String, String>();

       	HashMap<String, String> sigMap = this.getSigPosi(map);

    	String lat = sigMap.get("lat");
    	String lon = sigMap.get("lon");

       	map.put("addrArr", addr);
       	HashMap<String, String> emdMap = this.getEmdPosi(map);

    	if ( emdMap != null ) {

    		map.put("emdCd", emdMap.get("emdCd"));
    		lat = emdMap.get("lat");
    		lon = emdMap.get("lon");

    		HashMap<String, String> liMap =this.getLiPosi(map);

    		if ( liMap != null ) {
    			lat = liMap.get("lat");
    			lon = liMap.get("lon");
    		}
    	}

    	reqMap.put("lat", lat);
    	reqMap.put("lon", lon);

    	return reqMap;
    }

    public HashMap<String, String> getSigPosi(HashMap<String, String> map) throws Exception {

    	HashMap<String, String> reqMap = dao.getSigPosi(map);

        return reqMap;
    }

    public HashMap<String, String> getEmdPosi(HashMap<String, String> map) throws Exception {

    	HashMap<String, String> reqMap = dao.getEmdPosi(map);

    	return reqMap;
    }

    public HashMap<String, String> getLiPosi(HashMap<String, String> map) throws Exception {

    	HashMap<String, String> reqMap = dao.getLiPosi(map);

        return reqMap;
    }

    public ArrayList<EmdVo> getEmdList() throws Exception {

    	ArrayList<EmdVo> list = (ArrayList<EmdVo>) dao.getEmdList();

    	return list;
    }
    
    public ArrayList<HashMap<String, String>> getDamnmList() throws Exception {

    	ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) dao.getDamnmList();

    	return list;
    }

    public ArrayList<LiVo> getLiList() throws Exception {

    	ArrayList<LiVo> list = (ArrayList<LiVo>) dao.getLiList();

    	return list;
    }

    public ArrayList<DoroVo> getRnList() throws Exception {

        ArrayList<DoroVo> list = (ArrayList<DoroVo>) dao.getRnList();

        return list;
    }

    public ArrayList<JibunSearchVo> getAddrSearchList(HashMap<String, String> map) throws Exception {

        ArrayList<JibunSearchVo> list = (ArrayList<JibunSearchVo>) dao.getAddrSearchList(map);

        return list;
    }
    public ArrayList<DoroSearchVo> getNewAddrSearchList(HashMap<String, String> map) throws Exception {

        ArrayList<DoroSearchVo> list = (ArrayList<DoroSearchVo>) dao.getNewAddrSearchList(map);

        return list;
    }

    public ArrayList<LocationVo> getLocation(HashMap<String, String> map) throws Exception {

        ArrayList<LocationVo> list = (ArrayList<LocationVo>) dao.getLocation(map);

        return list;
    }

    public String getCenterName(HashMap<String, String> map) throws Exception {

        String nm = (String) dao.getCenterName(map);

        return nm;
    }
}
