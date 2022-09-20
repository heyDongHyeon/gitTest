package geomex.xeus.equipmgr.web;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import geomex.xeus.equipmgr.service.AcsryService;
import geomex.xeus.equipmgr.service.AcsryVo;
import geomex.xeus.equipmgr.service.AwsService;
import geomex.xeus.equipmgr.service.AwsVo;
import geomex.xeus.equipmgr.service.BrdcstTrmnlsService;
import geomex.xeus.equipmgr.service.BrdcstTrmnlsVo;
import geomex.xeus.equipmgr.service.CableService;
import geomex.xeus.equipmgr.service.CableVo;
import geomex.xeus.equipmgr.service.CctvModelService;
import geomex.xeus.equipmgr.service.CctvModelVo;
import geomex.xeus.equipmgr.service.CctvService;
import geomex.xeus.equipmgr.service.CctvUnregService;
import geomex.xeus.equipmgr.service.CctvVo;
import geomex.xeus.equipmgr.service.DisbordService;
import geomex.xeus.equipmgr.service.DisbordVo;
import geomex.xeus.equipmgr.service.HistoryService;
import geomex.xeus.equipmgr.service.HistoryVo;
import geomex.xeus.equipmgr.service.InfraService;
import geomex.xeus.equipmgr.service.InfraVo;
import geomex.xeus.equipmgr.service.MobileService;
import geomex.xeus.equipmgr.service.MobileVo;
import geomex.xeus.equipmgr.service.SiteHistService;
import geomex.xeus.equipmgr.service.SiteHistVo;
import geomex.xeus.equipmgr.service.SiteService;
import geomex.xeus.equipmgr.service.SiteVo;
import geomex.xeus.equipmgr.service.StatusService;
import geomex.xeus.equipmgr.service.StatusVo;
import geomex.xeus.equipmgr.service.VoiceService;
import geomex.xeus.equipmgr.service.VoiceVo;
import geomex.xeus.map.service.GeometryService;
import geomex.xeus.map.service.SearchService;
import geomex.xeus.smartcity.service.EventWebSocketService;
import geomex.xeus.sysmgr.service.ImageService;
import geomex.xeus.sysmgr.service.ImageVo;
import geomex.xeus.sysmgr.service.OrganizationService;
import geomex.xeus.sysmgr.service.SymIconService;
import geomex.xeus.sysmgr.service.SymIconVo;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.user.service.UserService;
import geomex.xeus.util.code.CodeConvertor;
import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.ValidInspector;

/**
 * <pre>
 * 파일명 :  SiteController.java
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
 * @since   :  2017. 8. 28.
 * @version :  1.0
 * @see
 */
@Controller
@RequestMapping("/nms")
public class NmsController {
	@Resource(name = "CctvService")
	private CctvService cctv;

	@Resource(name = "brdcstTrmnlsService")
    private BrdcstTrmnlsService brdcstTrmnlsService;

	@Resource(name = "awsService")
	private AwsService aws;

	@Resource(name = "voiceService")
	private VoiceService voice;

	@Resource(name = "disbordService")
	private DisbordService disbord;

	@Resource(name = "CctvModelService")
    private CctvModelService cctvModel;

	@Resource(name = "CctvUnregService")
	private CctvUnregService unreg;

	@Resource(name = "cableService")
	private CableService cable;

	@Resource(name = "acsryService")
	private AcsryService acsry;

	@Resource(name = "infraService")
	private InfraService infra;

	@Resource(name = "siteService")
	private SiteService site;

	@Resource(name = "siteHistService")
	private SiteHistService siteHist;

	@Resource(name = "statusService")
	private StatusService status;

	@Resource(name = "imageService")
	private ImageService image;

	@Resource(name = "codeCtrl")
	private CodeCtrl code;

	@Resource(name = "geometryService")
	private GeometryService geom;

	@Resource(name = "historyService")
	private HistoryService history;

	@Resource(name = "searchService")
	private SearchService bjd;

	@Resource(name = "mobileService")
	private MobileService mobile;

	@Resource(name = "organizationService")
	private OrganizationService orgz;

	@Resource(name = "userService")
	private UserService user;

	@Resource(name = "eventWebSocketService")
	private EventWebSocketService socket;


	@Resource(name = "symIconService")
    private SymIconService symIcon;

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
	 * 장비관리(NMS) 모니터링 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getNmsMonitoringView.do")
	public String getNmsMonitoringView(Model model) throws Exception {

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("column", col.getList());

		model.addAttribute("result", status.getList(null));
		model.addAttribute("count", status.getCount(null));

		return "/nms/nmsMonitoringView";
	}

	/**
	 * 장비관리(NMS) 모니터링 리스트를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getStatusList.json", method = RequestMethod.POST)
	public void getStatusList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", status.getList(map));

	}

	/**
	 * 장비에 따라 실제 위치값을 리턴합니다.
	 *
	 * @param model
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "/getGeometryLocation.json", method = RequestMethod.POST)
    public void getGeometryLocation(Model model, @RequestParam(value="k", required=true) String val) throws Exception {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("col", "mgr_no");
		param.put("val", val);

		if("CTV".equals(val.substring(0, 3))) param.put("tbl", "asset_cctv");
		if("AWS".equals(val.substring(0, 3))) param.put("tbl", "asset_eqb_aws");
		if("VOI".equals(val.substring(0, 3))) param.put("tbl", "asset_eqb_voice");
		if("DIS".equals(val.substring(0, 3))) param.put("tbl", "asset_disbord");

		/*if("SIT".equals(val.substring(0, 3))) param.put("tbl", "asset_site");
		if("ACS".equals(val.substring(0, 3))) param.put("tbl", "asset_acsry");*/

		model.addAttribute("result", geom.getGeometry(param));
	}

	/**
	 * 요청점검을 추가합니다.
	 *
	 * @param model
	 * @param session
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = "/addHistory.json", method = RequestMethod.POST)
	public void addHistory(Model model, HttpSession session, @ModelAttribute @Valid HistoryVo vo, BindingResult br) throws Exception {

		vo.setRegUserId((String) session.getAttribute("userId"));

		String[] ignoreField = {""};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("historyVo", null);
			model.addAttribute("result", history.add(vo));
			socket.broadcast("CCTV-Reload");
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
	 * 점검 단건의 내용을 수정합니다.
	 *
	 * @param model
	 * @param session
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = "/editHistoryAttr.json", method = RequestMethod.POST)
	public void editHistoryAttr(Model model, HttpSession session, @ModelAttribute @Valid HistoryVo vo, BindingResult br) throws Exception {

		vo.setRegUserId((String) session.getAttribute("userId"));

		String[] ignoreField = {""};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("historyVo", null);
			model.addAttribute("result", history.edit(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
	 * 점검 단건을 삭제합니다.
	 *
	 * @param model
	 * @param session
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = "/delHistory.json", method = RequestMethod.POST)
	public void delHistory(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", history.del(map));
	}

	/**
	 * 점검요청을 수정합니다.<br>
	 * 등록된 mgr_no 의 모든 리스트를 삭제하고 다시 저장합니다.<br><br>
	 *
	 * <b>정기점검 등록 기능 등에 사용됩니다.</b><br><br>
	 * <b style="color: red;">점검 내용을 수정하려면 editHistoryAttr 메소드를 사용해야 합니다.</b>
	 *
	 * @param model
	 * @param session
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = {"/editHistory.json", "/addHistoryList.json"}, method = RequestMethod.POST)
	public void editHistory(HttpServletRequest req, Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		String[] full_url = req.getRequestURI().split("/");
    	String url = full_url[full_url.length - 1];

    	if("addHistoryList.json".equals(url)){
    		map.put("mode", "add");
    	}else if("editHistory.json".equals(url)){
    		map.put("mode", "edit");
    	}

		String val = map.get("val");
		String regDat = map.get("regDat");
		String chkNm = map.get("chkNm");
		String chkGbnCd = map.get("chkGbnCd");
		String chkStatCd = map.get("chkStatCd");
		String[] faMgrNo = null;
		if(!"".equals(val) && val != null){
			faMgrNo = val.split(",");
		}

		if(faMgrNo == null){
			model.addAttribute("result", history.del(map));
		}else if(chkNm.length() > 50){
			model.addAttribute("error", "점검명은 최대 50자 까지 입력하실 수 있습니다.");
		}else{
			ArrayList<HistoryVo> list = new ArrayList<HistoryVo>();
			for(int i=0; i<faMgrNo.length; i++){
				HistoryVo vo = new HistoryVo();
				vo.setRegUserId((String) session.getAttribute("userId"));
				vo.setFaMgrNo(faMgrNo[i]);
				vo.setChkGbnCd(chkGbnCd);
				vo.setChkStatCd(chkStatCd);
				vo.setRegDat(regDat);
				vo.setChkNm(chkNm);

				list.add(vo);
			}

			TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

			try {
				model.addAttribute("result", history.addTransaction(list, map));
				transactionManager.commit(txStatus);
			} catch (Exception e) {
				transactionManager.rollback(txStatus);
				model.addAttribute("error", "롤백처리 되었습니다.\n잠시 후 다시 시도하시거나, 관리자에게 문의해주세요.");
				e.printStackTrace();
			}
		}

	}

	/**
	 * 정기점검 리스트를 조회합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "getChkNmList.json", method = RequestMethod.POST)
	public void getChkNmList(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", history.getList(map));

	}

	/**
	 * 요청등록(NMS) 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCallRegView.do")
	public String getCallRegView(Model model) throws Exception {

		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/callRegView";
	}

	/**
	 * 정기점검등록(NMS) 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPeriodicRegView.do")
	public String getPeriodicRegView(Model model) throws Exception {

		model.addAttribute("hist", history.getChkNmList());
		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/periodicRegView";
	}

	/**
	 * 시설물을 검색합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "getFacilitySearch.json", method = RequestMethod.POST)
	public void getFacilitySearch(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		String objType = map.get("objType");
		String objName = map.get("objName");
//		System.out.println("map = "+map);
		if(objName != null && !"".equals(objName)){		//objName을 여기다 넣어라 만약에 있으면
			map.put("cctvNm", objName);
			map.put("facilityNm", objName);
		}

		if("ALL".equals(objType)){

			model.addAttribute("cctvList", cctv.getList(map));
			model.addAttribute("disbordList", disbord.getList(map));

			map.put("se", "B03105");
			model.addAttribute("awsList", brdcstTrmnlsService.getList(map));

			map.put("se", "B03001");
			model.addAttribute("voiceList", brdcstTrmnlsService.getList(map));

		}else if("CTV".equals(objType)){
			ArrayList<CctvVo> result=cctv.getList(map);
			model.addAttribute("cctvList", result);
//			System.out.println("result(cctv) = "+result);
//			model.addAttribute("cctvList", cctv.getList(map));

		}else if("INF".equals(objType)){

			//model.addAttribute("infraList", infra.getList(map));

		}else if("AWS".equals(objType)) {
//			ArrayList<AwsVo> result=aws.getList(map);
			map.put("se", "B03105");
			ArrayList<BrdcstTrmnlsVo> result =brdcstTrmnlsService.getList(map);
			model.addAttribute("awsList", result);
//			System.out.println("result(aws) = "+result);
		}else if("VOI".equals(objType)) {
//			ArrayList<VoiceVo> result=voice.getList(map);
			map.put("se", "B03001");
			ArrayList<BrdcstTrmnlsVo> result =brdcstTrmnlsService.getList(map);
			model.addAttribute("voiceList", result);
//			System.out.println("result(voice) = "+result);
		}else if("DIS".equals(objType)) {
			ArrayList<DisbordVo> result=disbord.getList(map);
			model.addAttribute("disbordList", result);
//			System.out.println("result(disbord) = "+result);
		}

	}

	/**
	 * 점검이력(NMS) 검색 창 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCallListParentView.do")
	public String getCallListParentView(Model model) throws Exception {

		model.addAttribute("column", col.getList());
		model.addAttribute("hist", history.getChkNmList());
		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/checkListParentView";
	}

	/**
	 * 점검이력(NMS) 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getHistorySearchView.do", method = RequestMethod.POST)
	public String getHistorySearchView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("column", col.getList());
		model.addAttribute("result", history.getList(map));
		model.addAttribute("count", history.getCount(map));
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/checkList/searchResultView";
	}

	/**
	 * 시설물을 검색합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getFacilitySearchExcel.do", method = RequestMethod.POST)
	public String getFacilitySearchExcel(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		String view = null;

		String objType = map.get("objType");
		String objName = map.get("objName");
//		System.out.println("map = "+map);
		if(objName != null && !"".equals(objName)){		//objName을 여기다 넣어라 만약에 있으면
			map.put("cctvNm", objName);
			map.put("facilityNm", objName);
		}

		if("CTV".equals(objType)){
			ArrayList<CctvVo> result=cctv.getList(map);
			model.addAttribute("result", result);

			view = "/cctv/excelView";
//			System.out.println("result(cctv) = "+result);
//			model.addAttribute("cctvList", cctv.getList(map));

		}else if("AWS".equals(objType)) {
//			ArrayList<AwsVo> result=aws.getList(map);
			map.put("se", "B03105");
			ArrayList<BrdcstTrmnlsVo> result = brdcstTrmnlsService.getList(map);
			model.addAttribute("result", result);

			view = "/ndps/awsExcelView";
//			System.out.println("result(aws) = "+result);
		}else if("VOI".equals(objType)) {
//			ArrayList<VoiceVo> result=voice.getList(map);
			map.put("se", "B03001");
			ArrayList<BrdcstTrmnlsVo> result =brdcstTrmnlsService.getList(map);
			model.addAttribute("result", result);

			view = "/ndps/voiceExcelView";
//			System.out.println("result(voice) = "+result);
		}else if("DIS".equals(objType)) {
			ArrayList<DisbordVo> result=disbord.getList(map);
			model.addAttribute("result", result);

			view = "/ndps/disbordExcelView";
//			System.out.println("result(disbord) = "+result);

		}

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("column", col.getList());

		return view;

	}


	/**
	 * 점검이력(NMS) 상세이력 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getHistoryDetailView.do", method = RequestMethod.POST)
	public String getHistoryDetailView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		String faMgrNo = map.get("faMgrNo");
		if(faMgrNo != null) faMgrNo = faMgrNo.substring(0, 3);

		HashMap<String, String> voParam = new HashMap<String, String>();
		voParam.put("mgrNo", map.get("faMgrNo"));
		if("CTV".equals(faMgrNo)) model.addAttribute("cctv", cctv.getItem(voParam));
		if("INF".equals(faMgrNo)) model.addAttribute("infra", infra.getItem(voParam));

		ArrayList<ImageVo> img = image.getList(map);
		if(img != null && img.size() > 0) model.addAttribute("img", img);

		model.addAttribute("column", col.getList());
		model.addAttribute("result", history.getItem(map));
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/checkList/detailView";
	}

	/**
	 * 점검이력(NMS) 취소요청관리 뷰를 리턴합니다.<br><br>
	 *
	 * 취소요청 처리코드(cnclRsltCd) 파라미터가 없을 경우,<br>
	 * 전체(cnclAll) 라는 임시 파라미터를 생성하여 사용합니다.<br>
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCancelView.do", method = RequestMethod.POST)
	public String getCancelView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		String cnclRsltCd = map.get("cnclRsltCd");
		if(cnclRsltCd == null || "".equals(cnclRsltCd)){
			map.put("cnclAll", "all");
		}

		model.addAttribute("result", history.getList(map));
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("map", map);

		map.remove("cnclAll");

		return "/nms/checkList/cancelView";
	}

	/**
	 * 모바일 장비관리 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMobileManageView.do", method = RequestMethod.POST)
	public String getMobileManageView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", mobile.getList(map));
		model.addAttribute("count", mobile.getCount(map));
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("column", col.getList());
		model.addAttribute("map", map);

		//map.remove("cnclAll");

		return "/sysMng/equipMobileMngView";
	}

	/**
	 * 사이트 관리 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSiteManageView.do", method = RequestMethod.POST)
	public String getSiteView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("column", col.getList());

		model.addAttribute("result", site.getList(map));
		model.addAttribute("count", site.getCount(map));
		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("map", map);

		return "/nms/siteParentView";
	}

	/**
	 * 사이트 신규추가 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSiteRegView.do", method = RequestMethod.POST)
	public String getSiteRegView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("map", map);

		return "/nms/siteRegView";
	}

	/**
	 * 사이트 관리 상세보기 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSiteDetailView.do", method = RequestMethod.POST)
	public String getSiteDetailView(Model model, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mgrNo", val);

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		SiteVo vo = site.getItem(map);

		if(vo.getRepMgrNo() != null && !"".equals(vo.getRepMgrNo())){
			HashMap<String, String> cctvParam = new HashMap<String, String>();
			cctvParam.put("mgrNo", vo.getRepMgrNo());
			CctvVo cctvVo = cctv.getItem(cctvParam);
			model.addAttribute("cctv", cctvVo);
		}

		model.addAttribute("result", vo);
		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("map", map);

		return "/nms/site/detailView";
	}

	/**
	 * 사이트 관리 상세보기 > 현장사진 탭 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getImageTabView.do", method = RequestMethod.POST)
	public String getImageTabView(Model model, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("refMgrNo", val);

		ArrayList<ImageVo> img = image.getList(map);
		if(img != null && img.size() > 0) model.addAttribute("img", img);

		map.put("mgrNo", val);
		model.addAttribute("result", site.getItem(map));

		return "/nms/site/imageTabView";
	}

	/**
	 * 사이트 관리 상세보기 > 카메라 탭 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCctvTabView.do", method = RequestMethod.POST)
	public String getCctvTabView(Model model, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("siteMgrNo", val);

		ArrayList<CctvVo> cctvList = cctv.getList(map);

		ArrayList<ArrayList<ImageVo>> img = null;
		if(cctvList.size() > 0){
			img = new ArrayList<ArrayList<ImageVo>>();

			for(int i=0; i<cctvList.size(); i++){
				map.put("refMgrNo", cctvList.get(i).getMgrNo());
				img.add(image.getList(map));
			}

		}
		if(img != null && img.size() > 0) model.addAttribute("img", img);

		map.put("mgrNo", val);
		model.addAttribute("result", site.getItem(map));
		model.addAttribute("cctv", cctvList);
		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/site/cctvTabView";
	}

	/**
	 * 사이트 관리 상세보기 > 부속시설 탭 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAcsryTabView.do", method = RequestMethod.POST)
	public String getAcsryTabView(Model model, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("siteMgrNo", val);

		model.addAttribute("result", acsry.getList(map));
		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("map", map);

		return "/nms/site/acsryTabView";
	}

	/**
	 * 사이트 관리 상세보기 > 관리이력 탭 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSiteHistTabView.do", method = RequestMethod.POST)
	public String getSiteHistTabView(Model model, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mgrNo", val);

		model.addAttribute("result", siteHist.getList(map));
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("map", map);

		return "/nms/site/siteHistTabView";
	}

	/**
	 * CCTV 검색 결과를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/getCctvList.json", "getCctvItem.json", "getCctvUnregItem.json"}, method = RequestMethod.POST)
	public void getCctvList(HttpServletRequest req, Model model, @RequestParam HashMap<String, String> map) throws Exception {

		String[] full_url = req.getRequestURI().split("/");
    	String url = full_url[full_url.length - 1];

    	if("getCctvList.json".equals(url)){
    		model.addAttribute("result", cctv.getList(map));
    	}else if("getCctvItem.json".equals(url)){
    		model.addAttribute("result", cctv.getItem(map));
    	}else if("getCctvUnregItem.json".equals(url)){
    		model.addAttribute("result", unreg.getItem(map));
    	}

	}

	/**
	 * 사이트 단건을 삭제합니다.
	 *
	 * @param model
	 * @param session
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = "/delSite.json", method = RequestMethod.POST)
	public void delSite(Model model, HttpSession session, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mgrNo", val);

		model.addAttribute("result", site.del(map));
	}

	/**
	 * 사이트에 속한 CCTV 단건을 삭제합니다.
	 *
	 * @param model
	 * @param session
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = "/delSiteInnerCctv.json", method = RequestMethod.POST)
	public void delSiteInnerCctv(Model model, HttpSession session, @RequestParam(value="k", required=true) String val) throws Exception {

		CctvVo vo = new CctvVo();
		vo.setMgrNo(val);
		vo.setSiteMgrNo("");

		model.addAttribute("result", cctv.edit(vo));
	}

	/**
	 * 사이트 단건의 내용을 수정합니다.
	 *
	 * @param model
	 * @param session
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = "/editSite.json", method = RequestMethod.POST)
	public void editSite(Model model, HttpSession session, @ModelAttribute @Valid SiteVo vo, BindingResult br) throws Exception {

		String[] ignoreField = {"mgrNo", "orgMgrNo", "siteNm"};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("siteVo", null);
			model.addAttribute("result", site.edit(vo));
		}else{
			model.addAttribute("error", msg);
		}
	}

	/**
	 * 사이트 내의 CCTV를 등록합니다.
	 *
	 * @param model
	 * @param session
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = "/editSiteCctv.json", method = RequestMethod.POST)
	public void editCctv(Model model, HttpSession session, @ModelAttribute CctvVo vo) throws Exception {

		model.addAttribute("cctvVo", null);
		model.addAttribute("result", cctv.edit(vo));
	}

	/**
	 * 부속시설을 추가합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "addAcsry.json", method = RequestMethod.POST)
	public void addAcsry(Model model, HttpSession session, @ModelAttribute @Valid AcsryVo vo, BindingResult br) throws Exception {

		String[] ignoreField = {"mgrNo"};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("acsryVo", null);
			model.addAttribute("result", acsry.add(vo));

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("orgMgrNo", vo.getOrgMgrNo());
			map.put("siteMgrNo", vo.getSiteMgrNo());
			map.put("instDat", vo.getInstDat());
			map.put("fclGbnCd", vo.getFclGbnCd());
			map.put("atchdFclNm", vo.getAtchdFclNm());
			map.put("makerNm", vo.getMakerNm());
			map.put("prdtSpec", vo.getPrdtSpec());
			map.put("rmark", vo.getRmark());

			map.put("sortCol", "mgr_no");
			map.put("sortTyp", "desc");
			map.put("limit", "1");
			map.put("offset", "0");

			ArrayList<AcsryVo> acry = acsry.getList(map);
			if(acry != null && acry.size() == 1) model.addAttribute("mgrNo", acry.get(0).getMgrNo());
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
	 * 부속시설을 삭제합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "delAcsry.json", method = RequestMethod.POST)
	public void delAcsry(Model model, HttpSession session, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mgrNo", val);

		model.addAttribute("result", acsry.del(map));

	}

	/**
	 * 사이트를 신규 추가합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "addSite.json", method = RequestMethod.POST)
	public void addSite(Model model, HttpSession session, @ModelAttribute @Valid SiteVo vo, BindingResult br) throws Exception {

		String[] ignoreField = {"mgrNo"};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("siteVo", null);
			model.addAttribute("result", site.add(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
	 * 관리이력을 추가합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "addSiteHist.json", method = RequestMethod.POST)
	public void addSiteHist(Model model, HttpSession session, @ModelAttribute @Valid SiteHistVo vo, BindingResult br) throws Exception {

		vo.setWriterId((String) session.getAttribute("userId"));

		String[] ignoreField = {"mgrNo"};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("siteHistVo", null);
			model.addAttribute("result", siteHist.add(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
	 * 관리이력을 삭제합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "delSiteHist.json", method = RequestMethod.POST)
	public void delSiteHist(Model model, HttpSession session, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mgrSeq", val);

		model.addAttribute("result", siteHist.del(map));

	}

	/**
	 * 기반시설 > 범례 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInfraView.do", method = RequestMethod.POST)
	public String getInfraView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		return "/nms/infraLegendView";

	}

	/**
	 * 기반시설 검색 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInfraSearchView.do")
	public String getInfraSearchView(Model model) throws Exception {

		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/infra/searchView";
	}

	/**
	 * 기반시설전용 심볼 목록을 리턴합니다.
	 *
	 * @param dirPath - 심볼경로
	 * @return
	 */
	private ArrayList<String> getInfraSymbolList(String dirPath) {
		ArrayList<String> symList = new ArrayList<String>();

		File dir = new File(dirPath);
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			for(int i=0; i<files.length; i++){
				symList.add(files[i].getName());
			}
		}

		return symList;
	}

	/**
	 * 기반시설의 주제도 목록을 리턴합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "getInfraTheme.json", method = RequestMethod.POST)
	public void getInfraTheme(Model model, HttpSession session) throws Exception {

		/*ArrayList<InfraVo> list = infra.getInfraTheme(null);
		ArrayList<String> symList = getInfraSymbolList(session.getServletContext().getRealPath("/resources/sym/nms/"));

		HashMap<String, String> theme = new HashMap<String, String>();
		for(int i=0; i<list.size(); i++){
			for(int l=0; l<symList.size(); l++){
				if(list.get(i).getSymCd().equals(symList.get(l).replace(".png", ""))){
					theme.put(list.get(i).getSymCd(), symList.get(l));
					break;
				}
			}
		}

		model.addAttribute("result", theme);*/

	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("symGrp", "nms");
        ArrayList<SymIconVo> symList = symIcon.getList(map);

        HashMap<String, String> theme = new HashMap<String, String>();
        for(int i=0; i<symList.size(); i++){
            theme.put(symList.get(i).getGbnCd(), symList.get(i).getFileNm());
        }

        model.addAttribute("result", theme);

	}

	/**
	 * 기반시설 신규등록 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInfraMngView.do")
	public String getInfraMngView(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		if(map.get("mgrNo") != null && !"".equals(map.get("mgrNo"))){
			model.addAttribute("vo", infra.getItem(map));

			map.put("refMgrNo", map.get("mgrNo"));

			ArrayList<ImageVo> img = image.getList(map);
			if(img != null && img.size() > 0) model.addAttribute("img", img);
		}

		ArrayList<String> symList = getInfraSymbolList(session.getServletContext().getRealPath("/resources/sym/nms/"));

		//model.addAttribute("site", site.getList(null));
		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("sym", symList);
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/infra/infraMngView";
	}

	/**
	 * 기반시설을 검색합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInfra.json")
	public void getInfra(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", infra.getList(map));

	}

	/**
	 * 기반시설 신규등록 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInfraAddView.do")
	public String getInfraAddView(Model model, HttpSession session) throws Exception {

		ArrayList<String> symList = getInfraSymbolList(session.getServletContext().getRealPath("/resources/sym/nms/"));

		//model.addAttribute("site", site.getList(null));
		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("sym", symList);
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/infra/addView";
	}

	/**
	 * 기반시설 상세정보 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getInfraDetailView.do", method = RequestMethod.POST)
	public String getInfraDetailView(Model model, HttpSession session, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mgrNo", val);
		map.put("refMgrNo", val);

		ArrayList<ImageVo> img = image.getList(map);
		if(img != null && img.size() > 0) model.addAttribute("img", img);

		ArrayList<String> symList = getInfraSymbolList(session.getServletContext().getRealPath("/resources/sym/nms/"));

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("result", infra.getItem(map));
		model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("site", site.getList(null));
		model.addAttribute("sym", symList);
		model.addAttribute("map", map);

		return "/nms/infra/detailView";
	}

	/**
	 * 기반시설을 추가합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "addInfra.json", method = RequestMethod.POST)
	public void addInfra(Model model, HttpSession session, @ModelAttribute @Valid InfraVo vo, BindingResult br) throws Exception {

		String[] ignoreField = {"mgrNo", "siteMgrNo"};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("infraVo", null);
			model.addAttribute("result", infra.add(vo));

			HashMap<String, String> map = new HashMap<String, String>();
			map.put("orgMgrNo", vo.getOrgMgrNo());
			//map.put("siteMgrNo", vo.getSiteMgrNo());
			map.put("instDat", vo.getInstDat());
			map.put("fclGbnCd", vo.getFclGbnCd());
			map.put("ipAddr", vo.getIpAddr());
			map.put("portNum", vo.getPortNum());
			map.put("useYn", vo.getUseYn());
			map.put("conId", vo.getConId());
			map.put("conPwd", vo.getConPwd());
			map.put("snmpStr", vo.getSnmpStr());
			map.put("statChkYn", vo.getStatChkYn());
			map.put("facilityId", vo.getFacilityId());
			map.put("facilityNm", vo.getFacilityNm());
			map.put("locDesc", vo.getLocDesc());
			map.put("rmark", vo.getRmark());

			map.put("sortCol", "mgr_no");
			map.put("sortTyp", "desc");
			map.put("limit", "1");
			map.put("offset", "0");

			ArrayList<InfraVo> list = infra.getList(map);
			model.addAttribute("mgrNo", list.get(0).getMgrNo());
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
	 * 기반시설을 삭제합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "delInfra.json", method = RequestMethod.POST)
	public void delInfra(Model model, HttpSession session, @RequestParam(value="k", required=true) String val) throws Exception {

		HashMap<String, String> param = new HashMap<String, String>();
		param.put("mgrNo", val);

		model.addAttribute("result", infra.del(param));

	}

	/**
	 * 기반시설을 수정합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "editInfra.json", method = RequestMethod.POST)
	public void editInfra(Model model, HttpSession session, @ModelAttribute @Valid InfraVo vo, BindingResult br) throws Exception {

		String[] ignoreField = {"mgrNo", "orgMgrNo", "siteNm", "siteMgrNo"};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("infraVo", null);
			model.addAttribute("mgrNo", vo.getMgrNo());
			model.addAttribute("result", infra.edit(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
	 * 케이블관리 > 케이블조회 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCableView.do")
	public String getCableView(Model model) throws Exception {

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/cableSearchView";
	}

	/**
	 * 케이블관리 > 케이블등록 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCableRegView.do")
	public String getCableRegView(Model model) throws Exception {

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/cable/regView";
	}

	/**
	 * 케이블관리 > 케이블삭제 뷰를 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCableDelView.do")
	public String getCableDelView(Model model) throws Exception {

		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

		return "/nms/cable/delView";
	}

	/**
	 * 케이블의 주제도 목록을 리턴합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "getCableTheme.json", method = RequestMethod.POST)
	public void getCableTheme(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		ArrayList<CableVo> list = cable.getCableTheme(map);

		HashMap<String, String> theme = new HashMap<String, String>();
		for(int i=0; i<list.size(); i++){
			theme.put(list.get(i).getCableNm(), list.get(i).getLineColor());
		}

		model.addAttribute("result", theme);

	}

	/**
	 * 케이블을 수정합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "editCable.json", method = RequestMethod.POST)
	public void editCable(Model model, HttpSession session, @ModelAttribute @Valid CableVo vo, BindingResult br) throws Exception {

		String[] ignoreField = {};
		String msg = ValidInspector.findError(br, ignoreField);

		if(vo.getGid() == null || "".equals(vo.getGid())) msg = "케이블을 선택해 주세요.";

		if("pass".equals(msg)){
			model.addAttribute("cableVo", null);
			model.addAttribute("result", cable.edit(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

	/**
	 * 케이블을 삭제합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "delCable.json", method = RequestMethod.POST)
	public void delCable(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", cable.del(map));

	}

	/**
	 * 케이블 여러건을 삭제합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = "delMultipleCable.json", method = RequestMethod.POST)
	public void delMultipleCable(Model model, HttpSession session, @ModelAttribute @Valid CableVo vo) throws Exception {

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			model.addAttribute("result", cable.del(vo.getCableList()));
			transactionManager.commit(txStatus);
		} catch (Exception e) {
			transactionManager.rollback(txStatus);
			model.addAttribute("error", "롤백처리 되었습니다.\n잠시 후 다시 시도하시거나, 관리자에게 문의해주세요.");
			e.printStackTrace();
		}

	}

	/**
	 * 케이블을 추가합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = {"addCable.json", "addMultipleCable.json"}, method = RequestMethod.POST)
	public void addCable(Model model, HttpServletRequest req, HttpSession session, @ModelAttribute @Valid CableVo vo, BindingResult br) throws Exception {

		String[] full_url = req.getRequestURI().split("/");
    	String url = full_url[full_url.length - 1];

    	if("addCable.json".equals(url)){
    		String[] ignoreField = {"mgrNo", "orgMgrNo", "siteNm"};
    		String msg = ValidInspector.findError(br, ignoreField);

    		if("pass".equals(msg)){
    			model.addAttribute("result", cable.add(vo));
    			model.addAttribute("cableVo", null);
    		}else{
    			model.addAttribute("error", msg);
    		}
    	}else if("addMultipleCable.json".equals(url)){
    		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

    		try {
    			model.addAttribute("result", cable.add(vo.getCableList()));
    			model.addAttribute("cableVo", null);
    			transactionManager.commit(txStatus);
    		} catch (Exception e) {
    			transactionManager.rollback(txStatus);
    			model.addAttribute("error", "롤백처리 되었습니다.\n잠시 후 다시 시도하시거나, 관리자에게 문의해주세요.");
    			e.printStackTrace();
    		}
    	}
	}

	/**
     * 상태 모니터링 테스트 페이지 뷰를 리턴합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/statTest.do")
    public String statTest(Model model) throws Exception {

        model.addAttribute("code", new CodeConvertor(code.getCdeList()));

        model.addAttribute("cctv", cctv.getList(null));
        model.addAttribute("infra", infra.getList(null));
        model.addAttribute("site", site.getList(null));
        model.addAttribute("acsry", acsry.getList(null));
        model.addAttribute("status", status.getList(null));

        return "/nms/statTest";
    }

    /**
     * 상태 점검 요청 데이터를 추가합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addStatus.json", method = RequestMethod.POST)
    public void addStatus(Model model, @ModelAttribute @Valid StatusVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){

        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("mgrNo", vo.getMgrNo());

        	status.del(map);
            model.addAttribute("result", status.add(vo));

            socket.broadcast("CCTV-Reload");

        }else{
            model.addAttribute("error", msg);
        }


    }

    /**
     * 상태 점검 요청 데이터를 수정합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editStatus.json", method = RequestMethod.POST)
    public void editStatus(Model model, @ModelAttribute @Valid StatusVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){

            model.addAttribute("result", status.edit(vo));

            socket.broadcast("CCTV-Reload");

        }else{
            model.addAttribute("error", msg);
        }

    }

    /**
     * 상태 점검 요청 데이터를 삭제합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delStatus.json", method = RequestMethod.POST)
    public void delStatus(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", status.del(map));

        socket.broadcast("CCTV-Reload");

    }


    /**
     * CCTV 모델관리 뷰를 리턴합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCctvModelMngView.do")
    public String getCctvModelMngView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("code", new CodeConvertor(code.getCdeList()));
        model.addAttribute("column", col.getList());
        model.addAttribute("result", cctvModel.getList(map));
        model.addAttribute("count", cctvModel.getCount(map));
        model.addAttribute("map", map);

        return "/sysMng/equipCctvModelMngView";
    }

    /**
     * CCTV 모델 항목을 가져옵니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCctvModel.json", method = RequestMethod.POST)
    public void getCctvModel(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", cctvModel.getItem(map));

    }

    /**
     * CCTV 모델 항목을 추가합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addCctvModel.json", method = RequestMethod.POST)
    public void addCctvModel(Model model, @ModelAttribute @Valid CctvModelVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){

            model.addAttribute("result", cctvModel.add(vo));

        }else{
            model.addAttribute("error", msg);
        }

    }

    /**
     * CCTV 모델 항목을 수정합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editCctvModel.json", method = RequestMethod.POST)
    public void editCctvModel(Model model, @ModelAttribute @Valid CctvModelVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {""};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){

            model.addAttribute("result", cctvModel.edit(vo));

        }else{
            model.addAttribute("error", msg);
        }

    }

    /**
     * CCTV 모델 항목을 삭제합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delCctvModel.json", method = RequestMethod.POST)
    public void delCctvModel(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", cctvModel.del(map));

    }

    /**
     * NMS 범례를 조회합니다.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getNmsLegendView.do")
    public String getNmsLegendView(Model model) {



        return "/nms/nmsLegendView";

    }

    /**
     * NMS 메뉴 뷰를 리턴합니다.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getNmsView.do")
    public String getNmsView(Model model) {

        return "/nms/nmsView";

    }

    /**
     * 구청주차 메뉴 뷰를 리턴합니다.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/getParkingView.do")
    public String getParkingView(Model model) {

        return "/nms/parkingView";

    }

    /**
     * 모바일 단건을 추가합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addMobile.json", method = RequestMethod.POST)
    public void addMobile(Model model, @ModelAttribute @Valid MobileVo vo, BindingResult br) throws Exception {

    	/*mgr_no character(10) NOT NULL, -- 관리번호
    	user_id character varying(30) NOT NULL, -- 사용자ID
    	purp_cd character(2), -- 사용목적코드
    	model_nm character varying(50), -- 모델명
    	device_id character varying(100), -- 기기식별정보
    	auth_stat_cd character(2), -- 인증상태코드
    	stat_chg_dat character(14), -- 상태변경일
    	acpt_usr_id character varying(30) NOT NULL, -- 인증처리자ID
    	last_log_dat character(14), -- 마지막로그인
    	login_yn character(1) NOT NULL, -- 로그인여부*/

    	vo.setAuthStatCd("11");
    	vo.setLoginYn("N");

		String[] ignoreField = {"mgrNo"};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("result", mobile.add(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

    /**
     * 모바일 단건을 추가합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editMobile.json", method = RequestMethod.POST)
    public void editMobile(Model model, HttpSession session, @ModelAttribute @Valid MobileVo vo, BindingResult br) throws Exception {



    	//인증상태정보 변경 시 acptUserId 변경
    	//승인자가 아닌 정보 변경자로 입력
    	String mgrNo = vo.getMgrNo();
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("mgrNo", mgrNo);
    	MobileVo beforeWork = mobile.getItem(map);

    	System.out.println(beforeWork.getAuthStatCd());
    	System.out.println(vo.getAuthStatCd());

    	if(!beforeWork.getAuthStatCd().equals(vo.getAuthStatCd())){
    		vo.setAcptUsrId((String) session.getAttribute("userId"));
    		vo.setStatChgDat(DateUtil.getStrSec());
    	}




    	/*mgr_no character(10) NOT NULL, -- 관리번호
    	user_id character varying(30) NOT NULL, -- 사용자ID
    	purp_cd character(2), -- 사용목적코드
    	model_nm character varying(50), -- 모델명
    	device_id character varying(100), -- 기기식별정보
    	auth_stat_cd character(2), -- 인증상태코드
    	stat_chg_dat character(14), -- 상태변경일
    	acpt_usr_id character varying(30) NOT NULL, -- 인증처리자ID
    	last_log_dat character(14), -- 마지막로그인
    	login_yn character(1) NOT NULL, -- 로그인여부*/

    	String[] ignoreField = {""};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("result", mobile.edit(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

    /**
     * 모바일 단건을 추가합니다.
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getMobileItem.json", method = RequestMethod.POST)
    public void getMobileItem(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", mobile.getItem(map));

	}

}
