package geomex.xeus.equipmgr.web;

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

import geomex.xeus.equipmgr.service.NetwkService;
import geomex.xeus.equipmgr.service.NetwkVo;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.util.code.CodeConvertor;
import geomex.xeus.util.code.ValidInspector;

@Controller
@RequestMapping("/netwk")
public class NetwkController {

	@Resource(name = "netwkService")
	private NetwkService service;

	@Resource(name = "codeCtrl")
	private CodeCtrl code;

	@Resource
	private ColumnController col;

	@Resource(name = "txManager")
    PlatformTransactionManager transactionManager;

	@Resource
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(this.validator);
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
	 * CCTV, LORA, WIFI 케이블을 리턴합니다.
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getNmsCableList.json", method = RequestMethod.POST)
	public void getSiteHistTabView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", service.getList(map));

	}

	/**
	 * 케이블의 주제도 목록을 리턴합니다.
	 *
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = {"/getNmsCableTheme.json", "/getFnmsCableTheme.json"}, method = RequestMethod.POST)
	public void getNmsCableTheme(Model model, HttpServletRequest req, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		String[] full_url = req.getRequestURI().split("/");
    	String url = full_url[full_url.length - 1];

    	ArrayList<NetwkVo> list = service.getNmsCableTheme(map);

    	if("getFnmsCableTheme.json".equals(url)) list = service.getFnmsCableTheme(map);

		HashMap<String, String> theme = new HashMap<String, String>();
		for(int i=0; i<list.size(); i++){
			theme.put(list.get(i).getNetNm(), list.get(i).getLineColor());
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
	public void editCable(Model model, HttpSession session, @ModelAttribute @Valid NetwkVo vo, BindingResult br) throws Exception {

		String[] ignoreField = {};
		String msg = ValidInspector.findError(br, ignoreField);

		if(vo.getGid() == null || "".equals(vo.getGid())) msg = "케이블을 선택해 주세요.";

		if("pass".equals(msg)){
			model.addAttribute("cableVo", null);
			model.addAttribute("result", service.edit(vo));
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

		model.addAttribute("result", service.del(map));

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
	public void delMultipleCable(Model model, HttpSession session, @ModelAttribute @Valid NetwkVo vo) throws Exception {

		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

		try {
			model.addAttribute("result", service.del(vo.getCableList()));
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
	public void addCable(Model model, HttpServletRequest req, HttpSession session, @ModelAttribute @Valid NetwkVo vo, BindingResult br) throws Exception {

		String[] full_url = req.getRequestURI().split("/");
    	String url = full_url[full_url.length - 1];

    	if("addCable.json".equals(url)){
    		String[] ignoreField = {"mgrNo", "orgMgrNo", "siteNm"};
    		String msg = ValidInspector.findError(br, ignoreField);

    		if("pass".equals(msg)){
    			model.addAttribute("result", service.add(vo));
    			model.addAttribute("cableVo", null);
    		}else{
    			model.addAttribute("error", msg);
    		}
    	}else if("addMultipleCable.json".equals(url)){
    		TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

    		try {
    			model.addAttribute("result", service.add(vo.getCableList()));
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
	 * 케이블을 수정합니다.
	 *
	 * @param model
	 * @param vo
	 * @throws Exception
	 */
	@RequestMapping(value = "editThemeColor.json", method = RequestMethod.POST)
	public void editThemeColor(Model model, @ModelAttribute @Valid NetwkVo vo, BindingResult br) throws Exception {

		String[] ignoreField = {};
		String msg = ValidInspector.findError(br, ignoreField);

		if("pass".equals(msg)){
			model.addAttribute("result", service.editThemeColor(vo));
		}else{
			model.addAttribute("error", msg);
		}

	}

}
