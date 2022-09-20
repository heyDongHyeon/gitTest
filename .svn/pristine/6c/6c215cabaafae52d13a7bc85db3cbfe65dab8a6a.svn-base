package geomex.xeus.smartcity.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import geomex.xeus.smartcity.Utils;
import geomex.xeus.smartcity.service.EventHistService;
import geomex.xeus.smartcity.service.EventHistVo;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.util.code.ValidInspector;

@Controller
@RequestMapping("/eventHist")
public class EventHistController {

	@Resource(name = "eventHistService")
    private EventHistService service;

	@Resource(name = "columnController")
	private ColumnController col;

	/**
     * 이벤트 리스트 정보 뷰를 리턴합니다.
     *
	 * @param model
	 * @param session
	 * @return view
	 * @throws Exception
	 */
    @RequestMapping(value = "/getEventHistView.do")
    public String getCodeView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
		model.addAttribute("count", service.getCount(map));
		model.addAttribute("map", map);
    	return "/eventMonitor/eventHistView";
	}

    /**
     * 이벤트 리스트 정보 뷰를 리턴합니다.
     *
	 * @param model
	 * @param session
	 * @return view
	 * @throws Exception
	 */
    @RequestMapping(value = "/getEventHistExcel.do")
    public String getEventHistExcel(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	System.out.println("map(Excel) = "+map);
		ArrayList<EventHistVo> list = service.getList(map);
		model.addAttribute("result", list);
    	return "/eventMonitor/excelEventHistView";
	}

    /**
     * 이벤트 리스트 갯수를 조회합니다.
     *
     * @return json
     * @throws Exception
     */
    @RequestMapping(value = "/setSession.json", method = RequestMethod.POST)
    public void setSession(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

    	session.setAttribute("eventUserId", map.get("eventUserId"));

    }

    /**
     * 이벤트 리스트 갯수를 조회합니다.
     *
     * @return json
     * @throws Exception
     */
    @RequestMapping(value = "/getCount.json", method = RequestMethod.POST)
    public void getCount(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("count", service.getCount(map));

    }

    /**
	 * 이벤트 리스트 정보 리스트를 가져옵니다.
	 *
	 * @param req
	 * @param res
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/getList.json", method = RequestMethod.POST)
	public void getList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		ArrayList<EventHistVo> list = service.getList(map);

		ArrayList<String> result = new ArrayList<String>();
		for(int i=0; i<list.size(); i++){
			result.add(Utils.setJson(list.get(i)));
		}
		model.addAttribute("result", result);
		model.addAttribute("count", service.getCount(map));
	}

	/**
	 * 특정 이벤트 리스트 데이터를 가져옵니다.
	 *
	 * @param req
	 * @param res
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getItem.json", method = RequestMethod.POST)
	public void getItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", service.getItem(map));

	}

	/**
	 * 이벤트 리스트를 삭제합니다.
	 *
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/del.json", method = RequestMethod.POST)
	public void del(Model model, HttpSession session, @RequestParam(required=true) HashMap<String, String> map) throws Exception {

		model.addAttribute("result", service.del(map));

	}

	/**
	 * 이벤트 리스트를 추가합니다.
	 *
	 * @param model
	 * @param param
	 * @param br
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public void add(Model model, HttpSession session, @ModelAttribute @Valid EventHistVo vo, BindingResult br) throws Exception {

		String msg = ValidInspector.findError(br);

		if("pass".equals(msg)){
			model.addAttribute("result", service.add(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
	 * 이벤트 리스트를 수정합니다.
	 *
	 * @param model
	 * @param param
	 * @param br
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit.json", method = RequestMethod.POST)
	public void edit(Model model, HttpSession session, @ModelAttribute @Valid EventHistVo vo, BindingResult br) throws Exception {

		String msg = ValidInspector.findError(br);

		if("pass".equals(msg)){
			model.addAttribute("result", service.edit(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
     * 이벤트 리스트 정보 리스트를 가져옵니다.
     *
     * @param req
     * @param res
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getStatList.json", method = RequestMethod.POST)
    public void getStatList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", service.getList(map));
        model.addAttribute("count", service.getCount(map));
        model.addAttribute("lineChart", service.getTodayEvtByTime(map));
        model.addAttribute("columnChart", service.getStatByType(map));

    }

}
