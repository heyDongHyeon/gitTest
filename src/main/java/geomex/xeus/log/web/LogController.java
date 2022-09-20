package geomex.xeus.log.web;

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

import geomex.xeus.equipmgr.service.StatusHistService;
import geomex.xeus.log.service.AccessService;
import geomex.xeus.log.service.AssetLogService;
import geomex.xeus.log.service.IfDscLogService;
import geomex.xeus.log.service.IfDscLogVo;
import geomex.xeus.log.service.IfEvtLogService;
import geomex.xeus.log.service.IfEvtLogVo;
import geomex.xeus.log.service.LogStatService;
import geomex.xeus.log.service.MonCctvLogService;
import geomex.xeus.log.service.MonPrevLogService;
import geomex.xeus.log.service.MonStillCutLogService;
import geomex.xeus.log.service.MsgLogService;
import geomex.xeus.log.service.MsgLogVo;
import geomex.xeus.map.service.GeometryService;
import geomex.xeus.map.service.SearchService;
import geomex.xeus.ndps.service.NdpsService;
import geomex.xeus.smartcity.service.EventHistService;
import geomex.xeus.sysmgr.service.AuthLogService;
import geomex.xeus.sysmgr.service.OrganizationService;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.system.annotation.NoSession;
import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.ValidInspector;

/**
 * <pre>
 * 파일명 :  LogController.java
 * 설  명 :
 *   로그 관련 컨트롤러
 *   로그 관리 목록
 *      스마트 플랫폼 5대 연계 서비스 이력
 *      SMS 전송 이력
 *      시설물 관리 이력
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-04-18      이은규          최초 생성
 *
 * </pre>
 *
 * @since   :  2018. 04. 18.
 * @version :  1.0
 * @see
 */
@Controller
@RequestMapping("/log")
public class LogController {

	@Resource(name = "codeCtrl")
	private CodeCtrl code;


	@Resource(name = "ifDscLogService")
    private IfDscLogService ifDscLog;

	@Resource(name = "ifEvtLogService")
    private IfEvtLogService ifEvtLog;

	@Resource(name = "eventHistService")
    private EventHistService evtHistlog;

	@Resource(name = "msgLogService")
    private MsgLogService msgLog;

	@Resource(name = "assetLogService")
    private AssetLogService assetLog;

	@Resource(name = "accessService")
    private AccessService access;

	@Resource(name = "logStatService")
    private LogStatService logStat;

	@Resource(name = "geometryService")
	private GeometryService geom;

	@Resource(name = "searchService")
	private SearchService bjd;

	@Resource(name = "organizationService")
	private OrganizationService orgz;

	@Resource(name = "authLogService")
	private AuthLogService authLog;

	@Resource(name = "monCctvLogService")
	private  MonCctvLogService monCctv;

	@Resource(name = "monPrevLogService")
	private  MonPrevLogService monPrev;

	@Resource(name = "statusHistService")
	private  StatusHistService statusHist;

	@Resource(name = "monStillCutLogService")
	private  MonStillCutLogService stillcut;

	@Resource(name = "ndpsService")
	private  NdpsService ndpsService;

	@Resource
	private ColumnController col;

	@Resource(name = "txManager")
    PlatformTransactionManager transactionManager;

	@Resource
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(this.validator);
		/*binder.registerCustomEditor(MultipartFile.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				//System.out.println("initBinder MultipartFile.class: {}; set null; " + text);
				setValue(null);
			}
		});*/
	}

	 /**
	    * ndms 통계 화면
	    *
	    * @param model
	    * @param map
	    * @throws Exception
	    */
	@RequestMapping(value = "/getStatNdmsView.do", method = RequestMethod.POST)
	public String getStatNdmsView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
		model.addAttribute("damnm", bjd.getDamnmList());
	    model.addAttribute("emd", bjd.getEmdList());
	    model.addAttribute("year", DateUtil.getYearList("2018"));
	    return "/stat/statndmsView";
	}

	/**
	 * event 통계 화면
	 *
	 * @param model
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "/getStatEventView.do", method = RequestMethod.POST)
	public String getStatEventView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("year", DateUtil.getYearList("2018"));
		return "/stat/stateventView";
	}

	/**
	 * ndps 통계 화면
	 *
	 * @param model
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "/getStatNdpsView.do", method = RequestMethod.POST)
	public String getStatNdpsView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
		model.addAttribute("eqb", ndpsService.getEqbList(map));
		model.addAttribute("year", DateUtil.getYearList("2018"));

		return "/stat/statndpsView";
	}

	/**
     * SMS 전송 로그 리스트를 가져옵니다.
     *
     * @param req
     * @param res
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getHistList.json", method = RequestMethod.POST)
    public void getList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", msgLog.getList(map));
        model.addAttribute("map", map);
        model.addAttribute("count", msgLog.getCount(map));

    }

    /**
     * SMS 전송 로그 단건을 조회합니다.
     *
     * @param req
     * @param res
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @NoSession
    @RequestMapping(value = "/getHistItem.json", method = RequestMethod.POST)
    public void getItem(Model model, HttpServletRequest req, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", msgLog.getItem(map));

    }

    /*
     * 접근이력관리 관리 로그
     */
    /**
     * 접근이력관리 뷰를 리턴합니다.
     *
     * @param model
     * @param session
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/getAccessView.do")
    public String getAccessView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", access.getList(map));
        model.addAttribute("count", access.getCount(map));
        model.addAttribute("column", col.getList());
        model.addAttribute("map", map);

        return "/log/accessLogView";
    }

    /**
     * 로그를 엑셀 형식으로 내보냅니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "getLogAsExcel.do", method = RequestMethod.POST)
    public String getExcel(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        String view = null;

        if (map.get("excel").equals("Asset")){
            model.addAttribute("map", map);
            model.addAttribute("list", assetLog.getList(map));
            view = "/log/xls/xlsAsset";

        } else if (map.get("excel").equals("Dsc")){
            model.addAttribute("map", map);
            model.addAttribute("list", ifDscLog.getList(map));
            view = "/log/xls/xlsDsc";

        } else if (map.get("excel").equals("Evt")){
            model.addAttribute("map", map);
            model.addAttribute("list", ifEvtLog.getList(map));
            view = "/log/xls/xlsEvt";

        } else if (map.get("excel").equals("Msg")){
            model.addAttribute("map", map);
            model.addAttribute("list", msgLog.getList(map));
            view = "/log/xls/xlsMsg";

        } else if (map.get("excel").equals("Access")){
            model.addAttribute("map", map);
            model.addAttribute("list", access.getList(map));
            view = "/log/xls/xlsAccess";

        }

        model.addAttribute("column", col.getList());

        return view;

    }

}
