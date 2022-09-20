package geomex.xeus.send.web;

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

import geomex.xeus.equipmgr.service.VoiceService;
import geomex.xeus.equipmgr.service.VoiceVo;
import geomex.xeus.log.service.MsgLogService;
import geomex.xeus.map.service.SearchService;
import geomex.xeus.ndpssend.service.NdpsSendService;
import geomex.xeus.sysmgr.service.AuthService;
import geomex.xeus.sysmgr.service.ImageService;
import geomex.xeus.sysmgr.service.ImageVo;
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
@RequestMapping("/send")
public class SendController {

    @Resource(name = "codeCtrl")
    private CodeCtrl code;

    @Resource(name = "voiceService")
    private VoiceService service;

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
    @Resource(name = "msgLogService")
    private MsgLogService msgLog;

    @Resource(name = "ndpsSendService")
    private NdpsSendService ndps;

    @Resource
    private Validator validator;
    boolean resultBol;
    ArrayList<VoiceVo> resultList;
    VoiceVo resultObj;


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

    @RequestMapping(value = "/getSendView.do")
    public String getNdms119View(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
		model.addAttribute("count", msgLog.getCount(map));
		model.addAttribute("grp", ndps.getSMSGroupInfo(map));
	
    	return "/send/"+map.get("event")+"View";
    }
    @RequestMapping(value = "/getSendMngView.do")
    public String getSendMngView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
//    	model.addAttribute("orgz", orgz.getList(null));
//		model.addAttribute("emd", bjd.getEmdList());
//		model.addAttribute("li", bjd.getLiList());
//		model.addAttribute("code", new CodeConvertor(code.getCdeList()));
    	return "/send/smsMngView";
    }
    
    @RequestMapping(value = "/getSMSTreeGroupInfo.json", method = RequestMethod.POST)
    public void getSMSTreeGroupInfo(Model model, @RequestParam HashMap<String, String> param) throws Exception {

    	ArrayList<HashMap<String, String>> resultList=ndps.getSMSTreeGroupInfo(param);
    	if(resultList==null)
    	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else
    	{
    		model.addAttribute("result",resultList);
    	}

    }
    

    @RequestMapping(value = "/getList.json", method = RequestMethod.POST)
    public void getList(Model model, @RequestParam HashMap<String, String> param) throws Exception {

    	System.out.println("param = "+param);
    	resultList=service.getList(param);
    	if(resultList==null)
    	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else
    	{
    		model.addAttribute("result",resultList);
    		System.out.println("result(getList.json) = "+resultList);
    	}

    }
    @RequestMapping(value = "/getItem.json", method = RequestMethod.POST)
    public void getItem(Model model, @RequestParam HashMap<String, String> param) throws Exception {

    	System.out.println("params = "+param);
    	resultObj=service.getItem(param);
    	if(resultObj==null)
    	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else
    	{
    		model.addAttribute("result",resultObj);
    		System.out.println("result = "+resultObj);
    	}

    }
    @NoSession
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public void add(Model model, HttpSession session, @ModelAttribute @Valid VoiceVo param, BindingResult br)
    		throws Exception {

    	String[] ignoreField = {""};
    	String msg = ValidInspector.findError(br, ignoreField);
    	System.out.println("param = "+param);


    	if("pass".equals(msg)){
    		resultBol=service.add(param);
    		if(resultBol==false)
    		{
    			model.addAttribute("error","에러");
    			System.out.println("error = 에러");
    		}
    		else
    		{
    			model.addAttribute("result",resultBol);
    			System.out.println("result = "+resultBol);
    		}

    	}else{
    		model.addAttribute("error", msg);
    	}
    }
    @NoSession
    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    public void edit(Model model, HttpSession session, @ModelAttribute @Valid VoiceVo param, BindingResult br) throws Exception {

    	String[] ignoreField = {""};
    	String msg = ValidInspector.findError(br, ignoreField);
    	System.out.println("param = "+param);


    	if("pass".equals(msg)){
    		resultBol=service.edit(param);
    		if(resultBol==false)
    		{
    			model.addAttribute("error","에러");
    			System.out.println("error = 에러");
    		}
    		else
    		{
    			model.addAttribute("result",resultBol);
    			System.out.println("result = "+resultBol);
    		}

    	}else{
    		model.addAttribute("error", msg);
    	}
    }

    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    public void del(Model model, @RequestParam HashMap<String, String> param) throws Exception {

    	System.out.println("param = "+param);
    	resultBol=service.del(param);
    	if(resultBol==false)
    	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else
    	{
    		model.addAttribute("result",resultBol);
    		System.out.println("result = "+resultBol);
    	}

    }

    @RequestMapping(value = "/getVoiceMngView.do")
    public String getCctvMngView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
//    	model.addAttribute("code", new CodeConvertor(code.getCdeList()));		//이거는 나도 모르겠음..
//		model.addAttribute("column", col.getList());
//
//		model.addAttribute("orgz", orgz.getList(null));
//		model.addAttribute("model", cctvModel.getList(null));
		if(map.get("mgrNo") != null && !"".equals(map.get("mgrNo"))){		//해당 mgrNo에 대한 데이터
			resultObj=service.getItem(map);
			model.addAttribute("vo", resultObj);
			System.out.println("voiceMngView result = "+resultObj);
			System.out.println("result(getItem) = "+resultObj);
			ArrayList<ImageVo> img = image.getList(map);
			System.out.println("img = "+img);
			if(img != null && img.size() > 0) model.addAttribute("img", img);		//이미지
		}
		else
		{
			model.addAttribute("vo", null);
		}
    	return "/ndms/voiceMngView";
    }
    /**
     * 미등록 뷰를 리턴합니다.
     *
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUnregVoiceView.do")
    public String getUnregCctvView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

//    	model.addAttribute("code", new CodeConvertor(code.getCdeList()));
//		model.addAttribute("column", col.getList());
//
//		model.addAttribute("orgz", orgz.getList(null));
//		model.addAttribute("model", cctvModel.getList(null));

//		model.addAttribute("cctv", unreg.getList(map));

    	return "/ndms/unregVoiceView";

    }

}
