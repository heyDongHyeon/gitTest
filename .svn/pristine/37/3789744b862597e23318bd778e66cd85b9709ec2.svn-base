package geomex.xeus.map.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.xeus.alarm.service.EventAlarmSocketService;
import geomex.xeus.map.service.MapService;
import geomex.xeus.map.service.MapVo;
import geomex.xeus.util.code.ValidInspector;

/**
 * <pre>
 * 파일명 :  MapController.java
 * 설  명 :
 *   지도 컨트롤러
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017-06-05      이주영          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 6. 5.
 * @version :  1.0
 * @see
 */
@Controller
@RequestMapping("/map")
public class MapController {


	@Resource(name = "mapService")
	private MapService service;

	@Resource(name = "propService")
	private EgovPropertyService propService;

	@Resource
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(this.validator);
	}

	/**
	 * 메인 지도 뷰를 리턴합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view.do")
    public String view(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {
    	/*if(map.get("appYn") == null || map.get("appYn") == ""){
    		map.put("appYn", "N");
    	}

    	if(map.get("userId") == null || map.get("userId") == ""){
    		map.put("userId", (String) session.getAttribute("userId"));
    	}*/



    	model.addAttribute("favList", service.getFavList(map));
    	model.addAttribute("map", map);

        return "/map/mainView";
    }

	/**
	 * 대메뉴 뷰를 리턴합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/eventView.do", "/ndmsView.do","ndpsView.do", "sendView.do", "/awsView.do","/nmsView.do", "/statView.do", "/boardView.do", "/systemView.do"})
	public String eventView(Model model, HttpSession session, HttpServletRequest req, @RequestParam HashMap<String, String> map) throws Exception {

		String view = "";
		String[] full_url = req.getRequestURI().split("/");
    	String url = full_url[full_url.length - 1];
    	if("eventView.do".equals(url)){
    		view = "eventView";
    	}
    	if("ndmsView.do".equals(url)) view = "ndmsView";
    	if("ndpsView.do".equals(url)) view = "ndpsView";
    	if("sendView.do".equals(url)) view = "sendView";
    	if("nmsView.do".equals(url)) view = "nmsView";
    	if("statView.do".equals(url)) view = "statView";
    	if("boardView.do".equals(url)) view = "boardView";
    	if("systemView.do".equals(url)) view = "systemView";

		model.addAttribute("favList", service.getFavList(map));
		model.addAttribute("map", map);

		return "/map/" + view;
	}

	/**
	 * 관심영역 목록을 리턴합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
    @RequestMapping(value = "/getFavList.json", method = RequestMethod.POST)
    public void getFavList(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

    	if(map.get("userId") == null || map.get("userId") == ""){
    		map.put("userId", (String) session.getAttribute("userId"));
    	}

		model.addAttribute("result", service.getFavList(map));

    }

    /**
     * 관심영역을 추가합니다.
     *
     * @param model
     * @param session
     * @param vo
     * @param br
     * @throws Exception
     */
    @RequestMapping(value = "/addFav.json", method = RequestMethod.POST)
    public void addFav(Model model, HttpSession session, @ModelAttribute @Valid MapVo vo, BindingResult br) throws Exception {

    	String msg = ValidInspector.findError(br);

		if("pass".equals(msg)){
			if(vo.getUserId() == null || "".equals(vo.getUserId())){
				vo.setUserId((String) session.getAttribute("userId"));
			}
			model.addAttribute("result", service.addFav(vo));
		}else{
			model.addAttribute("error", msg);
		}
    }

    /**
     * 관심영역을 삭제합니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "/delFav.json", method = RequestMethod.POST)
    public void delFav(Model model, @RequestParam(value="k", required=true) String val) throws Exception {

    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("mgrSeq", val);

    	model.addAttribute("result", service.delFav(map));

    }

}
