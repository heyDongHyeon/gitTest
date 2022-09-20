package geomex.xeus.equipmgr.web;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;

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

import geomex.xeus.equipmgr.service.DisbordService;
import geomex.xeus.equipmgr.service.DisbordVo;
import geomex.xeus.sysmgr.service.AuthService;
import geomex.xeus.sysmgr.service.ImageService;
import geomex.xeus.sysmgr.service.ImageVo;
import geomex.xeus.sysmgr.service.NoticeService;
import geomex.xeus.sysmgr.service.OrganizationService;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.system.annotation.NoSession;
import geomex.xeus.tvius.service.CrmsSysParamService;
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
@RequestMapping("/disbord")
public class DisbordController {

    @Resource(name = "codeCtrl")
    private CodeCtrl code;

    @Resource(name = "disbordService")
    private DisbordService service;

    @Resource(name = "authService")
    private AuthService auth;

    @Resource(name = "noticeService")
    private NoticeService notice;

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
    boolean resultBol;
    ArrayList<DisbordVo> resultList;
    DisbordVo resultObj;


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
    /**
     * 사용자 계정을 추가합니다.
     *
     * @param model
     * @param param
     * @param br
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/getList.json", method = RequestMethod.POST)
    public void getList(Model model, @RequestParam HashMap<String, String> param) throws Exception {

//    	System.out.println("param = "+param);
    	resultList=service.getList(param);
    	if(resultList==null)
    	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else
    	{
    		model.addAttribute("result",resultList);
//    		System.out.println("result(getList.json) = "+resultList);
    	}

    }
    @RequestMapping(value = "/getItem.json", method = RequestMethod.POST)
    public void getItem(Model model, @RequestParam HashMap<String, String> param) throws Exception {

//    	System.out.println("params = "+param);
    	resultObj=service.getItem(param);
    	if(resultObj==null)
    	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else
    	{
    		model.addAttribute("result",resultObj);
//    		System.out.println("result = "+resultObj);
    	}

    }
    @NoSession
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public void add(Model model, HttpSession session, @ModelAttribute @Valid DisbordVo param, BindingResult br)
    		throws Exception {
    	String[] ignoreField = {""};
    	String msg = ValidInspector.findError(br, ignoreField);

    	if("pass".equals(msg)){
    		resultBol=service.add(param);
    		if(resultBol==false){
    			model.addAttribute("error","에러");
    			System.out.println("error = 에러");
    		}
    		else{
    			model.addAttribute("result",resultBol);
//    			System.out.println("result = "+resultBol);
    		}

    	}else{
    		model.addAttribute("error", msg);
    	}
    }
    @NoSession
    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    public void edit(Model model, HttpSession session, @ModelAttribute @Valid DisbordVo param, BindingResult br) throws Exception {

    	String[] ignoreField = {""};
    	String msg = ValidInspector.findError(br, ignoreField);
//    	System.out.println("param = "+param);


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
//    			System.out.println("result = "+resultBol);
    		}

    	}else{
    		model.addAttribute("error", msg);
    	}
    }

    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    public void del(Model model, @RequestParam HashMap<String, String> param) throws Exception {

//    	System.out.println("param = "+param);
    	resultBol=service.del(param);
    	if(resultBol==false)
    	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else
    	{
    		model.addAttribute("result",resultBol);
//    		System.out.println("result = "+resultBol);
    	}

    }
    /**
     * 사용자 계정을 추가합니다.
     *
     * @param model
     * @param param
     * @param br
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDisbordMngView.do")
    public String getCctvMngView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
		if(map.get("mgrNo") != null && !"".equals(map.get("mgrNo"))){		//해당 mgrNo에 대한 데이터
			model.addAttribute("vo", service.getItem(map));
			model.addAttribute("mgr", service.getMgr(map));
			ArrayList<ImageVo> img = image.getList(map);
			if(img != null && img.size() > 0) model.addAttribute("img", img);		//이미지
		}
		else{
			model.addAttribute("vo", null);
		}
    	return "/ndps/disbordMngView";
    }
    /**
     * 미등록 뷰를 리턴합니다.
     *
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUnregDisbordView.do")
    public String getUnregCctvView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

//    	model.addAttribute("code", new CodeConvertor(code.getCdeList()));
//		model.addAttribute("column", col.getList());
//
//		model.addAttribute("orgz", orgz.getList(null));
//		model.addAttribute("model", cctvModel.getList(null));

//		model.addAttribute("cctv", unreg.getList(map));

    	return "/ndps/unregDisbordView";

    }

}
