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

import geomex.xeus.equipmgr.service.BrdcstTrmnlsService;
import geomex.xeus.equipmgr.service.BrdcstTrmnlsVo;
import geomex.xeus.equipmgr.service.VoiceService;
import geomex.xeus.equipmgr.service.VoiceVo;
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
@RequestMapping("/brdcstTrmnls")
public class BrdcstTrmnlsController {

    @Resource(name = "codeCtrl")
    private CodeCtrl code;

    @Resource(name = "voiceService")
    private VoiceService service;

    @Resource(name = "brdcstTrmnlsService")
    private BrdcstTrmnlsService brdcstTrmnlsService;

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
    public void getList(Model model, @RequestParam HashMap<String, String>param) throws Exception {

    	ArrayList<BrdcstTrmnlsVo> result = brdcstTrmnlsService.getList(param);

    	for(int i=0; i<result.size(); i++){
    		BrdcstTrmnlsVo vo = result.get(i);
    		if("654".equals(vo.getInnb())){
    			result.remove(i);
    		}
    	}

    	HashMap<String, String> itemParam = new HashMap<String, String>();
    	//세종 재난 안전상황실 6층
    	itemParam.put("innb","654");
    	itemParam.put("se", param.get("se"));

    	BrdcstTrmnlsVo defaultBrdcstTrmnlsVo = brdcstTrmnlsService.getItem(itemParam);
    	result.add(0,defaultBrdcstTrmnlsVo);

    	model.addAttribute("result", result);
    }

}
