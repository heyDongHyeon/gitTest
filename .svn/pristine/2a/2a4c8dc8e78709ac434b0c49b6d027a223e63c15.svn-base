package geomex.xeus.eventmonitor.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import geomex.xeus.equipmgr.service.CctvService;
import geomex.xeus.eventmonitor.service.BellService;
import geomex.xeus.eventmonitor.service.CarService;
import geomex.xeus.eventmonitor.service.IfEventStatService;
import geomex.xeus.eventmonitor.service.IfEventStatVo;
import geomex.xeus.eventmonitor.service.MasterEventService;
import geomex.xeus.eventmonitor.service.MasterEventVo;
import geomex.xeus.eventmonitor.service.SoundService;
import geomex.xeus.eventmonitor.service.UserTraceService;
import geomex.xeus.smartcity.service.EventService;
import geomex.xeus.sysmgr.service.AuthService;
import geomex.xeus.sysmgr.service.ImageService;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.user.service.UserService;
import geomex.xeus.util.code.CodeConvertor;
import geomex.xeus.util.code.SystemParameter;
import geomex.xeus.util.code.ValidInspector;

/**
 * <pre>
 * 파일명 :  MonitorController.java
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
 * @since   :  2017. 8. 25.
 * @version :  1.0
 * @see
 */
@Controller
@RequestMapping("/monitor")
public class MonitorController {

	@Resource(name = "masterEventService")
	private MasterEventService master;

	@Resource(name = "eventService")
    private EventService service;

	@Resource(name = "carService")
	private CarService car;

	@Resource(name = "bellService")
	private BellService bell;

	@Resource(name = "soundService")
	private SoundService sound;

	@Resource(name = "userTraceService")
    private UserTraceService user;

	@Resource(name = "CctvService")
	private CctvService cctv;

	@Resource(name = "imageService")
	private ImageService image;

	@Resource(name = "ifEventStatService")
    private IfEventStatService eventStat;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "authService")
	private AuthService authService;

	@Resource(name = "codeCtrl")
	private CodeCtrl code;

	@Resource
	private ColumnController col;

	@Resource(name = "txManager")
    PlatformTransactionManager transactionManager;

    @Resource(name = "crmsSysParamService")
    private CrmsSysParamService sysParamList;

	@Resource
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(this.validator);
	}

	/* 모니터링 현황 */
    @RequestMapping(value = "/getMonitoringView.do")
    public String getMonitoringView(Model model) throws Exception {

    	model.addAttribute("cctv", cctv.getList(null));
    	model.addAttribute("user", userService.getList(null));
    	model.addAttribute("auth", authService.getAuthGrpList(null));

    	SystemParameter sysParam = new SystemParameter(sysParamList.getList(null));

    	model.addAttribute("timelimit", sysParam.getParamMap().get("event.time_limit") );
    	return "/eventMonitor/monitoringView";

    }
    /* 모니터링 현황 */

    @RequestMapping(value = "/getMonitoringWaitView.do")
    public String getMonitoringWaitView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	SystemParameter sysParam = new SystemParameter(sysParamList.getList(null));

    	model.addAttribute("cctv", cctv.getList(null));
    	model.addAttribute("user", userService.getList(null));
    	model.addAttribute("auth", authService.getAuthGrpList(null));
    	//System.out.println("==param==========="+map);
    	if (  map.get("dateLimit") != null &&  map.get("dateLimit").equals("Y") ) {
    		map.put("dateLimit",	sysParam.getParamMap().get("event.time_limit") );
    	}

    	if ( map.get("dateLimitWait") != null && map.get("dateLimitWait").equals("Y") ) {
    		map.put("dateLimitWait",	sysParam.getParamMap().get("event.time_limit") );
    	}
    	model.addAttribute("count", service.getCount(map));
    	model.addAttribute("map", map);
    	return "/eventMonitor/monitoringWaitView";

    }

    /**
     * 이벤트 조회 입니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/getAllEvnetList.json", "/getMasterList.json", "getSoundEventList.json", "getBellEventList.json", "getCarEventList.json"})
    public void getEventList(Model model, HttpServletRequest req, @RequestParam HashMap<String, String> map) throws Exception {

    	String[] full_url = req.getRequestURI().split("/");
    	String url = full_url[full_url.length - 1];

    	if("getAllEvnetList.json".equals(url)){
    		model.addAttribute("car", car.getList(map));
    		model.addAttribute("bell", bell.getList(map));
    		model.addAttribute("sound", sound.getList(map));

    	}else if("getMasterList.json".equals(url)){
    		model.addAttribute("master", master.getList(map));

    	}else if("getSoundEventList.json".equals(url)){
    		model.addAttribute("sound", sound.getList(map));

    	}else if("getBellEventList.json".equals(url)){
    		model.addAttribute("bell", bell.getList(map));

    	}else if("getCarEventList.json".equals(url)){
    		model.addAttribute("car", car.getList(map));
    	}

    }

    /**
     * 이벤트의 내용을 수정합니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "/editMasterEvt.json")
    public void editMasterEvt(Model model, @ModelAttribute @Valid MasterEventVo vo, BindingResult br) throws Exception {

        model.addAttribute("result", master.edit(vo));

    }


    /* SMS */
    @RequestMapping(value = "/getSmsView.do")
    public String getSmsView(Model model) throws Exception {

    	model.addAttribute("user", userService.getList(null));
    	model.addAttribute("auth", authService.getAuthGrpList(null));

    	return "/eventMonitor/smsView";
    }

    /* 비상벨 */
    @RequestMapping(value = "/getBellView.do")
    public String getBellView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("bell", bell.getList(map));
        model.addAttribute("code", new CodeConvertor(code.getCdeList()));

    	return "/eventMonitor/bellView";

    }

    /**
     * 비상벨 단건 상세보기 내역을 가져옵니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "/getBellItem.json")
    public void getBellItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", bell.getItem(map));

    }

    /* 안심귀가 */
    @RequestMapping(value = "/getSafeView.do")
    public String getSafeView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("user", user.getList(map));
        model.addAttribute("code", new CodeConvertor(code.getCdeList()));

    	return "/eventMonitor/safeView";

    }

    /* 문제차량 */
    @RequestMapping(value = "/getCarView.do")
    public String getCarView(Model model) {

    	return "/eventMonitor/carView";

    }

    /* 이상음원 */
    @RequestMapping(value = "/getSoundView.do")
    public String getSoundView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("sound", sound.getList(map));
        model.addAttribute("code", new CodeConvertor(code.getCdeList()));

    	return "/eventMonitor/soundView";

    }

    /**
     * 비상벨 단건 상세보기 내역을 가져옵니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "/getSoundItem.json")
    public void getSoundItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", sound.getItem(map));

    }

    /* 이력조회 */
    @RequestMapping(value = "/getEventLogView.do")
    public String getEventLogView(Model model) {

    	return "/eventMonitor/eventLogView";

    }

    /*
    *
    * 웹소켓 연동 서비스 현황
    *
    */

   /*@RequestMapping(value = "/getIfEventStatView.do", method = RequestMethod.POST)
   public String getIfEventStatView(Model model, @ModelAttribute @Valid HashMap<String, String> map) throws Exception {

       model.addAttribute("result", eventStat.getList(map));
       model.addAttribute("count", eventStat.getCount(map));
       model.addAttribute("column", col.getList());
       model.addAttribute("map", map);

       return "/mornitor/eventStatView";

   }*/

   /**
    * 웹소켓 연동 서비스 현황 리스트를 조회합니다.
    *
    * @param model
    * @param map
    * @throws Exception
    */
   @RequestMapping(value = "/getIfEventStatList.json", method = RequestMethod.POST)
   public void getIfEventStatList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

       model.addAttribute("count", eventStat.getCount(map));
       model.addAttribute("result", eventStat.getList(map));

   }

   /**
    * 웹소켓 연동 서비스 현황 단건을 조회합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/getIfEventStatItem.json", method = RequestMethod.POST)
   public void getIfEventStatItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

       model.addAttribute("result", eventStat.getItem(map));

   }

   /**
    * 웹소켓 연동 서비스 현황 단건을 삭제합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/delIfEventStat.json", method = RequestMethod.POST)
   public void delIfEventStat(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

       model.addAttribute("result", eventStat.del(map));
   }

   /**
    * 웹소켓 연동 서비스 현황를 추가합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/addIfEventStat.json", method = RequestMethod.POST)
   public void addIfEventStat(Model model, HttpSession session, @ModelAttribute @Valid IfEventStatVo vo, BindingResult br) throws Exception {

       String[] ignoreField = {""};
       String msg = ValidInspector.findError(br, ignoreField);

       if("pass".equals(msg)){
           model.addAttribute("eventStatVo", null);
           model.addAttribute("result", eventStat.add(vo));
       }else{
           model.addAttribute("error", msg);
       }

   }

   /**
    * 웹소켓 연동 서비스 현황 내용을 수정합니다.
    *
    * @param model
    * @param session
    * @param vo
    * @throws Exception
    */
   @RequestMapping(value = "/editIfEventStat.json", method = RequestMethod.POST)
   public void editIfEventStat(Model model, HttpSession session, @ModelAttribute @Valid IfEventStatVo vo, BindingResult br) throws Exception {

       String[] ignoreField = {""};
       String msg = ValidInspector.findError(br, ignoreField);

       if("pass".equals(msg)){
           model.addAttribute("eventStatVo", null);
           model.addAttribute("result", eventStat.edit(vo));
       }else{
           model.addAttribute("error", msg);
       }

   }

}
