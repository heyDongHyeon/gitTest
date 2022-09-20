package geomex.xeus.ndms.web;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
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
import org.springframework.web.multipart.MultipartFile;

import geomex.xeus.equipmgr.service.AwsVo;
import geomex.xeus.equipmgr.service.CctvVo;
import geomex.xeus.equipmgr.service.DisbordVo;
import geomex.xeus.equipmgr.service.VoiceService;
import geomex.xeus.equipmgr.service.VoiceVo;
import geomex.xeus.map.service.SearchService;
import geomex.xeus.ndms.service.NdmsService;
import geomex.xeus.sysmgr.service.AuthService;
import geomex.xeus.sysmgr.service.ImageService;
import geomex.xeus.sysmgr.service.ImageVo;
import geomex.xeus.sysmgr.service.NoticeService;
import geomex.xeus.sysmgr.service.OrganizationService;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.system.annotation.NoSession;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.util.code.CodeConvertor;
import geomex.xeus.util.code.ValidInspector;

/**
 * <pre>
 * 파일명 :  UserController.java
 * 설  명 :
 *   사용자 컨트롤러
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017-05-31      이주영          최초 생성
 *
 * </pre>
 *
 * @since : 2017. 5. 31.
 * @version : 1.0
 * @see
 */

@Controller
@RequestMapping("/ndms")
public class NdmsController {

    @Resource(name = "codeCtrl")
    private CodeCtrl code;

    @Resource(name = "ndmsService")
    private NdmsService service;

    @Resource(name = "authService")
    private AuthService auth;

	@Resource(name = "searchService")
	private SearchService bjd;

    @Resource(name = "crmsSysParamService")
    private CrmsSysParamService sysParamList;

    @Resource(name = "columnController")
    private ColumnController col;

    @Resource(name = "organizationService")
    private OrganizationService orgz;

    @Resource(name = "imageService")
	private ImageService image;

    @Resource
    private Validator validator;

    ArrayList<HashMap<String, String>> resultList=null;
    HashMap<String, String> resultHash=null;
    boolean resultBol;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(this.validator);
        binder.registerCustomEditor(MultipartFile.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                //System.out.println("initBinder MultipartFile.class: {}; set null; " + text);
                setValue(null);
            }
        });
    }

    @RequestMapping(value = "/getNdmsView.do")
    public String getNdmsView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	//System.out.println(map.get("event"));
    	model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

    	return "/ndms/"+map.get("event")+"View";
    }
    @RequestMapping(value = "/getNdmsMngChartView.do")
    public String getNdmsMngChartView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	String str=(String)map.get("k");

		model.addAttribute("str", str);

    	return "/ndms/ndmsMngChartView";
    }

    @RequestMapping(value = "/getNdmsMngInformView.do")
    public String getNdmsMngInformView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	String str=(String)map.get("k");
    	String key=(String)map.get("key");

    	model.addAttribute("str", str);
    	model.addAttribute("code", new CodeConvertor(code.getCdeList()));

    	//긴급구조
    	if("1".equals(key)){
    		return "/ndms/ndms119MngInformView";
    	}else{
    		return "/ndms/ndmsMngInformView";
    	}

    }
    //////////////////////////////////////////////////////11.12 여기서부터 내가 한것.
    @RequestMapping(value = "getFacilitySearch.json", method = RequestMethod.POST)
    public void getFacilitySearch(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {
    	String objType = map.get("objType");
    	String objName = map.get("objName");
    	//System.out.println("map = "+map);
    	//System.out.println("들어왔따.");

    	if("ALL".equals(objType)){

    	}else if("aws".equals(objType)){
    		//System.out.println("if문안에 들어왔따.");
    		resultList=service.getList(map);
    		//System.out.println("result(ndmsAws) = "+resultList);
    		model.addAttribute("awsList", resultList);
    	}else if("INF".equals(objType)){

    	}
    }


    @RequestMapping(value = {"/getListByInf119Mstr.json"}, method = RequestMethod.POST)
	public void getListByInf119Mstr(@RequestParam HashMap<String, String> map, Model model) throws Exception {
    	model.addAttribute("result", service.getListByInf119Mstr(map));
	}


    /**
     * CCTV 목록을 조회합니다.
     *
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getStatExcelByInf119Mstr.do", method = RequestMethod.POST)
    public String getStatExcelByInf119Mstr(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("param", map);
        model.addAttribute("list", service.getStatByInf119Mstr(map));

		return "/ndms/statExcelByInf119Mstr";
    }
}
