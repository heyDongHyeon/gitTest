package geomex.xeus.map.web;

import geomex.xeus.map.service.GeometryService;
import geomex.xeus.system.annotation.NoSession;
import geomex.xeus.util.code.StrUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.minidev.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <pre>
 * 파일명 :  GeometryController.java
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
 * @since   :  2017. 11. 2.
 * @version :  1.0
 * @see
 */
@Controller
//@RequestMapping("/geom")
public class GeometryController {

	@Resource(name = "geometryService")
	private GeometryService service;

	/**
	 * Custom WFS 를 리턴합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@NoSession
	@RequestMapping(value = "CustomWFS"/*, method = RequestMethod.POST*/)
	public void getWfs(HttpServletResponse res, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");

		if(map.get("val") != null && !"".equals(map.get("val"))){
			String[] vals = map.get("val").split(",");
			for(int i=0; i<vals.length; i++){
				System.out.println(vals[i] );
				vals[i] = "'" + vals[i] + "'";
			}
			map.put("val", Arrays.toString(vals).replace("[", "").replace("]", ""));
			System.out.println(map);
		}

		if(map.get("reqGbn") != null && !"".equals(map.get("reqGbn"))){
            String[] vals = map.get("reqGbn").split(",");
            for(int i=0; i<vals.length; i++){
                vals[i] = "'" + vals[i] + "'";
            }
            map.put("reqGbn", Arrays.toString(vals).replace("[", "").replace("]", ""));
        }

		ArrayList<HashMap<String, String>> list = service.getWfs(map);

		StringBuilder sb = new StringBuilder();
        sb.append("{ \"type\": \"FeatureCollection\",");
        sb.append("\"features\": [");
        int cnt = 0;
        for(int i=0; i<list.size(); i++){
        	HashMap<String, String> hash = list.get(i);
        	Set <String> key = hash.keySet();
        	Iterator<String> itr = key.iterator();

        	if (cnt > 0) sb.append(",");
        	sb.append("{ \"type\": \"Feature\",");
        	sb.append("\"id\": \"" + map.get("tbl") + "." + String.valueOf(hash.get("_gid")) + "\",");
        	sb.append("\"geometry\":").append(hash.get("geojson")).append(",");
        	sb.append("\"properties\": {");

        	String propStr = "\"typename\":" + "\"" + map.get("tbl") + "\",";
        	while(itr.hasNext()){
        		String k = (String) itr.next();
        		if(!"geojson".equals(k) && !"_geometry".equals(k)){
        			propStr += "\"" + k + "\":" + "\"" + String.valueOf(hash.get(k)) + "\",";
        		}
        	}
        	sb.append(propStr.substring(0, propStr.length() - 1));

        	cnt++;
        	sb.append("}"); //properties end
        	sb.append("}");
        }
        sb.append("]");
        sb.append("}");
        res.getWriter().print(sb.toString());

	}

	/**
	 * 지점과의 가장 가까운 Preset CCTV 정보를 리턴합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "getPresetCCTV.json"/*, method = RequestMethod.POST*/)
	public void getPresetCCTV(HttpSession session, Model model, @RequestParam HashMap<String, String> map) throws Exception {

		ArrayList<HashMap<String, String>> list = service.getPresetCCTV(map);

		if(list.size() == 1){
			model.addAttribute("result", list.get(0));
		}else{
			model.addAttribute("result", false);
		}

	}

}
