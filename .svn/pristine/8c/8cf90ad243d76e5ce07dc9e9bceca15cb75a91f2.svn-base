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
import geomex.xeus.smartcity.service.EventHistVo;
import geomex.xeus.smartcity.service.EventService;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.util.code.StrUtil;
import geomex.xeus.util.code.SystemParameter;
import geomex.xeus.util.code.ValidInspector;

@Controller
@RequestMapping("/eventList")
public class EventListController {

	@Resource(name = "eventService")
    private EventService service;

	@Resource(name = "columnController")
	private ColumnController col;


    @Resource(name = "crmsSysParamService")
    private CrmsSysParamService sysParamList;

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
		SystemParameter sysParam = new SystemParameter(sysParamList.getList(null));
		if (  map.get("dataLimit") != null &&  map.get("dataLimit").equals("Y") ) {
			map.put("dataLimit",	sysParam.getParamMap().get("event.time_limit") );
		}
		
		if (  map.get("selectEvt") != null &&  map.get("selectEvt").equals("Y") ) {
			HashMap<String, String> pkokey = sysParam.getParamMap();
			HashMap<String, Object> evtMap = StrUtil.strToMap(pkokey.get("event.ivcp_evt").toString());
			HashMap<String, Object> ndpsEvtMap = StrUtil.strToMap(pkokey.get("event.ndps_evt").toString());
			
			boolean allNo=true;
			for(String s : evtMap.keySet()) {
				if("Y".equals(evtMap.get(s).toString())) {
					allNo=false;
					break;
				}
			}
			if(allNo) {
				map.put("allNo", "allNo");
			}
			else {
				if(evtMap.get("추돌사고")!=null && evtMap.get("추돌사고").equals("Y")) map.put("fifteen", "추돌사고");
				if(evtMap.get("차량 주차")!=null && evtMap.get("차량 주차").equals("Y")) map.put("eighteen", "차량 주차");
				if(evtMap.get("연기")!=null && evtMap.get("연기").equals("Y")) map.put("eight", "연기");
				if(evtMap.get("버려짐")!=null && evtMap.get("버려짐").equals("Y")) map.put("six", "버려짐");
				if(evtMap.get("배회")!=null && evtMap.get("배회").equals("Y")) map.put("zero", "배회");
				if(evtMap.get("멈춤")!=null && evtMap.get("멈춤").equals("Y")) map.put("five", "멈춤");
				if(evtMap.get("불꽃")!=null && evtMap.get("불꽃").equals("Y")) map.put("nine", "불꽃");
				if(evtMap.get("금지된 방향 이동")!=null && evtMap.get("금지된 방향 이동").equals("Y")) map.put("two", "금지된 방향 이동");
				
				
				if(ndpsEvtMap.get("홍수 주의보")!=null && ndpsEvtMap.get("홍수 주의보").equals("Y")) map.put("rainWeak", "홍수 주의보");
				if(ndpsEvtMap.get("홍수 경보")!=null && ndpsEvtMap.get("홍수 경보").equals("Y")) map.put("rainStrong", "홍수 경보");
				if(ndpsEvtMap.get("대설 주의보")!=null && ndpsEvtMap.get("대설 주의보").equals("Y")) map.put("snowWeak", "대설 주의보");
				if(ndpsEvtMap.get("대설 경보")!=null && ndpsEvtMap.get("대설 경보").equals("Y")) map.put("snowStrong", "대설 경보");
			}
		}
		map.remove("procSt");
		ArrayList<EventHistVo> list = service.getList(map);
		ArrayList<String> result = new ArrayList<String>();

		for(int i=0; i<list.size(); i++){
			//System.out.println("============="+list.get(i));
			result.add(Utils.setJson(list.get(i)));
		}
		
		model.addAttribute("result", result);
		model.addAttribute("map", map);
		model.addAttribute("count", service.getCount(map));
		model.addAttribute("offset", map.get("offset"));
	}
    /**
     * 이벤트 리스트 정보 뷰를 리턴합니다.
     *
	 * @param model
	 * @param session
	 * @return view
	 * @throws Exception
	 */
    @RequestMapping(value = "/getEventListExcel.do")
    public String getEventListExcel(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	//System.out.println("map(Excel) = "+map);
		ArrayList<EventHistVo> list = service.getList(map);

		model.addAttribute("result", list);
    	return "/eventMonitor/excelEventListView";
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
		SystemParameter sysParam = new SystemParameter(sysParamList.getList(null));
		//System.out.println("==param==========="+map);
		if ( map.get("dateLimit").equals("Y") ) {
				map.put("dateLimit",	sysParam.getParamMap().get("event.time_limit") );
		}

		model.addAttribute("result", service.getItem(map));

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
	@RequestMapping(value = "/getEventChk.json", method = RequestMethod.POST)
	public void getEventChk(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", service.getEventChk(map));

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

}