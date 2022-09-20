package geomex.xeus.ndps.web;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import geomex.xeus.map.service.SearchService;
import geomex.xeus.ndps.service.NdpsService;
import geomex.xeus.sysmgr.service.AuthService;
import geomex.xeus.sysmgr.service.ImageService;
import geomex.xeus.sysmgr.service.OrganizationService;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.system.annotation.NoSession;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.util.code.CodeConvertor;

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
@RequestMapping("/ndps")
public class NdpsController {

    @Resource(name = "codeCtrl")
    private CodeCtrl code;

    @Resource(name = "ndpsService")
    private NdpsService service;

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

    @RequestMapping(value = "/getNdpsView.do")
    public String getNdpsView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	//System.out.println(map.get("event"));
    	model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("map", map);

    	return "/ndps/getNdpsView";
    }

    @RequestMapping(value = "/getData.do")
    public String getDataView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	//System.out.println(map.get("event"));
    	model.addAttribute("map", map);

    	return "/ndps/getData";
    }

    @RequestMapping(value = "/getData.json", method = RequestMethod.POST)
    public void getData(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	if("B03105".equals(map.get("se"))) {
    		model.addAttribute("result", service.getAwsData(map));
    	}else {
    		model.addAttribute("result", service.getData(map));
    	}


    }
    @RequestMapping(value = "/getEqbList.json", method = RequestMethod.POST)
    public void getEqbList(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	model.addAttribute("result", service.getEqbList(map));

    }
    /**
     * 해당 읍면동에 대한 음성장비 목록 가져오기. 읍면동이 없으면 모든 음성장비 목록 가져오기.
     * @param map->emdCd
     * @return 음성장비 리스트
     * @throws Exception
     */
    @RequestMapping(value = "/getList.json", method = RequestMethod.POST)
    public void getList(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	if(map.get("nm")==null || map.get("nm").equals(""))
    	{
    		map.put("nm","");
    	}

    	if("B03105".equals(map.get("se"))) {
        	resultList=service.getAwsList(map);
    	}else {
        	resultList=service.getList(map);

    	}

    	if(resultList==null)	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else{
    		model.addAttribute("result",resultList);
    		System.out.println("result = "+resultList);
    	}
    }

    @NoSession
    @RequestMapping(value = "/getTodayRainList.json", method = RequestMethod.POST)
    public void getTodayRainList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

    	ArrayList<HashMap<String, String>> todayRainList=null;
    	ArrayList<HashMap<String, String>> rainEqbList=null;

    	todayRainList = service.getTodayRainList(map);
    	rainEqbList = service.getRainEqbList(map);


		model.addAttribute("todayRainList",todayRainList);
		model.addAttribute("rainEqbList",rainEqbList);

    }

    @NoSession
    @RequestMapping(value = "/getRecentAwsList.json", method = RequestMethod.POST)
    public void getRecentAwsList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

    	ArrayList<HashMap<String, String>> result = null;

    	result=service.getRecentAwsList(map);

    	model.addAttribute("result",result);


    }
}
