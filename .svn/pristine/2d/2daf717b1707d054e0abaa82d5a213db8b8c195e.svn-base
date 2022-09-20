package geomex.xeus.sysmgr.web;

import java.io.File;
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

import geomex.xeus.log.service.MsgLogService;
import geomex.xeus.log.service.MsgLogVo;
import geomex.xeus.sysmgr.service.SymDescService;
import geomex.xeus.sysmgr.service.SymIconService;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.tvius.service.CrmsSysParamVo;
import geomex.xeus.util.code.CodeConvertor;
import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.SystemParameter;
import geomex.xeus.util.code.ValidInspector;
import geomex.xeus.util.sms.CEncrypt;
import geomex.xeus.util.sms.ServiceSMSSoapProxy;


/**
 * <pre>
 * 파일명 :  UserManagementController.java
 * 설  명 :
 *
 *   시스템 관리 컨트롤러 입니다.
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2017-06-13      이주영          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 6. 12.
 * @version :  1.0
 * @see
 */
@Controller
@RequestMapping("/sysMng")
public class SystemManagementController {

	@Resource(name = "codeCtrl")
	private CodeCtrl code;

	@Resource(name = "crmsSysParamService")
    private CrmsSysParamService param;

	@Resource(name = "msgLogService")
	private MsgLogService msgLogService;

	@Resource(name = "symIconService")
    private SymIconService symIconService;

	@Resource(name = "symDescService")
    private SymDescService symDescService;

	@Resource
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(this.validator);
	}

	/**
     * 시스템 관리 뷰를 리턴합니다.
     *
	 * @param model
	 * @param session
	 * @return view
	 * @throws Exception
	 */
    @RequestMapping(value = "/getSystemView.do")
    public String getUserView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("param", map);

    	return "/sysMng/systemMngView";

	}

    /**
     * 시스템 환경설정 뷰를 리턴합니다.
     *
     * @param model
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/getEnvSetView.do")
    public String getEnvSetView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        /*SystemParameter sysParam = new SystemParameter(param.getList(null));
        model.addAttribute("param", sysParam.getParamMap());*/

        model.addAttribute("list", param.getList(null));

        return "/sysMng/systemMngEnvSetView";

    }

    /**
     * 환경설정 파라미터를 불러옵니다.
     *
     * @param model
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/getSysParam.json", method = RequestMethod.POST)
    public void getSysParam(Model model) throws Exception {

        /*sysParamList = param.getList(null);

        HashMap<String, String> map = null;
        map = new HashMap<String, String>();

        for(int i=0; i<sysParamList.size(); i++){
            map.put(sysParamList.get(i).getPropKey().trim(), sysParamList.get(i).getPropValue().trim());
        }

        convertParam = new ArrayList<HashMap<String, String>>();
        convertParam.add(map);

        model.addAttribute("result", convertParam);*/

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        SystemParameter sysParam = new SystemParameter(param.getList(null));

        list.add(sysParam.getParamMap());

        model.addAttribute("result", list);

    }

    /**
     * 환경설정 파라미터를 불러옵니다.
     *
     * @param model
     * @param vo
     * @throws Exception
     */
    @RequestMapping(value = "/editSysParam.json", method = RequestMethod.POST)
    public void editSysParam(Model model, @ModelAttribute @Valid CrmsSysParamVo vo, BindingResult br) throws Exception {

        String[] ignoreField = {"maskingRouteAf", "maskingRouteBf"};
        String msg = ValidInspector.findError(br, ignoreField);

        if("pass".equals(msg)){
            model.addAttribute("result", param.editSysParam(vo));
        }else{
            model.addAttribute("error", msg);
        }

    }

    /**
     * 아이콘 관리 뷰를 리턴합니다.
     *
     * @param model
     * @param map
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/getIconMngView.do")
    public String getIconMngView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("code", new CodeConvertor(code.getCdeList()));
        model.addAttribute("result", symDescService.getList(map));
        model.addAttribute("param", map);

        return "/sysMng/iconMngView";

    }

    /**
     * CCTV 아이콘 관리 뷰를 리턴합니다.
     *
     * @param model
     * @param map
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/getCctvIconMngView.do")
    public String getCctvIconMngView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("code", new CodeConvertor(code.getCdeList()));
        model.addAttribute("symicon", symIconService.getList(map));
        model.addAttribute("param", map);

        return "/sysMng/iconCctvMngView";

    }

    /**
     * 관공서 아이콘 관리 뷰를 리턴합니다.
     *
     * @param model
     * @param map
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/getGovIconMngView.do")
    public String getGovIconMngView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("code", new CodeConvertor(code.getCdeList()));
        model.addAttribute("symicon", symIconService.getList(map));
        model.addAttribute("param", map);

        return "/sysMng/iconGovMngView";

    }

    /**
     * 긴급재난상황 아이콘 관리 뷰를 리턴합니다.
     *
     * @param model
     * @param map
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/getDistIconMngView.do")
    public String getDistIconMngView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("code", new CodeConvertor(code.getCdeList()));
        model.addAttribute("symicon", symIconService.getList(map));
        model.addAttribute("param", map);

        return "/sysMng/iconDistMngView";

    }

    /**
     * 프로그램 내에 저장되어있는 심볼 리스트를 불러옵니다.
     *
     * @param model
     * @param session
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "/getIconList.json")
    public void getIconList(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception{
        ArrayList<String> iconList = new ArrayList<String>();

        String path = session.getServletContext().getRealPath("/resources/") + "sym\\" + map.get("subPath") + "\\";
        //System.out.println(path);
        File dir = new File(path);
        File[] fileList = dir.listFiles();
        try {
            for (int i = 0; i < fileList.length; i++) {
                File file = fileList[i];
                if (file.isFile()) {
                    if (file.getName().endsWith(".png")) {
                        iconList.add(file.getName());
                    }
                }
            }
            model.addAttribute("result", true);
            model.addAttribute("iconlist", iconList);
            //System.out.println(iconList.size());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("result", false);
        }
    }

    /**
     * 아이콘 이미지를 업로드합니다.
     *
     * @param model
     * @param param
     * @param br
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadIcon.json", method = RequestMethod.POST)
    public void uploadIcon(Model model, HttpSession session, @RequestParam(value="p", required=true) String sub,
                                                             @RequestParam(value="uploadImg", required=true) MultipartFile file) throws Exception {
        /*if(file.isEmpty()){
            model.addAttribute("error", "파일이 선택되지 않았습니다.");
        }else{
            boolean workChk = false;
            String [] splitFileNm = file.getOriginalFilename().split("\\.");
            String extension = splitFileNm[splitFileNm.length-1];
            String path = session.getServletContext().getRealPath("/resources/") + sub;
            String realFileNm = DateUtil.getStrMilSec() + "-" + file.getOriginalFilename();

            if("png".equals(extension)){

                HashMap<String, String> map = new HashMap<String, String>();
                map.put("symGrp", symGrp);
                map.put("gbnCd", gbnCd);
                map.put("iconTyp", iconTyp);

                if(symIconService.getCount(map) == 0){
                    SymIconVo vo = new SymIconVo();
                    vo.setSymGrp(symGrp);
                    vo.setGbnCd(gbnCd);
                    vo.setIconTyp(iconTyp);
                    vo.setFileNm(realFileNm);
                    workChk = symIconService.add(vo);
                    if(workChk){
                        File img = new File(path + realFileNm);
                        file.transferTo(img);
                        if(!img.exists()) {
                            workChk = false;
                            model.addAttribute("error", "파일 업로드에 실패하였습니다.");
                        }
                    } else {
                        model.addAttribute("error", "데이터베이스 작업에 실패하였습니다.");
                    }
                } else {
                    model.addAttribute("error", "이미 데이터베이스에 존재하는 건입니다.");
                }
            }else{
                model.addAttribute("error", "파일은 png 파일만 업로드 할 수 있습니다.");
            }
            model.addAttribute("result", workChk);
        }*/

        boolean workChk = false;
        if(file.isEmpty()){
            model.addAttribute("error", "파일이 선택되지 않았습니다.");
        }else{

            String [] splitFileNm = file.getOriginalFilename().split("\\.");
            String extension = splitFileNm[splitFileNm.length-1];
            String path = session.getServletContext().getRealPath("/resources/") + sub;
            String realFileNm = DateUtil.getStrMilSec() + "-" + file.getOriginalFilename();

            if("png".equals(extension)){

                File img = new File(path + realFileNm);
                file.transferTo(img);
                if(!img.exists()) {
                    workChk = false;
                    model.addAttribute("error", "파일 업로드에 실패하였습니다.");
                }else{
                    workChk = true;
                }
            }
        }
        model.addAttribute("result", workChk);

    }

    /**
     * SMS TEST용 임시 뷰를 리턴합니다.
     *
     * @param model
     * @param session
     * @return view
     * @throws Exception
     */
    /*@RequestMapping(value = "/getSmsTestView.do")
    public String getSmsTestView(Model model) throws Exception {

        return "/sysMng/result";

    }*/

    /**
     * SMS를 전송합니다.
     *
     * @param model
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/sendSms.json", method = RequestMethod.POST)
    public void sedSms(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        String sendSMS = "-1";
        ServiceSMSSoapProxy smsid = new ServiceSMSSoapProxy();

        String smsID = "nowcns";
        String hashValue = "now3217";
        String hash_temp = "";
        String senderPhone = "0332613217";
        String receivePhone =map.get("phone");//수신번호
        String smsContent = map.get("conts");//발송내용
        String sendRslt = "S0";
        /*String resultReturn = "";
        String reserveDate ="";
        String reserveTime = "";
        String userDefine = "";
        String callbackUrl = "";
        String searchValue = "";
        String mode = "";*/
        CEncrypt encrypt;


        try {
            hash_temp = (smsID + hashValue + receivePhone); //아이디, 패스워드,수신번호를 hash화시킴
            encrypt = new CEncrypt("MD5", hash_temp);
            sendSMS = smsid.sendSMS(smsID, encrypt.getEncryptData(), senderPhone, receivePhone, smsContent);
        } catch (Exception e) {
        	sendRslt = "F0";
        }

        MsgLogVo vo = new MsgLogVo();
        vo.setSendMsg(smsContent);
        vo.setSendTyp("S1");
        vo.setRecvId(map.get("rcvId"));
        vo.setRecvNum(receivePhone);
        vo.setSendDt(DateUtil.getStrSec());
        vo.setSendRslt(sendRslt);
        vo.setRsltDesc("");
        msgLogService.add(vo);

        model.addAttribute("result", sendSMS);

    }

    /**
     * 시스템 장비관리 탑 메뉴 뷰를 리턴합니다.
     *
     * @param model
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/getEquipTopMenuView.do")
    public String getEquipTopMenuView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

    	model.addAttribute("map", map);

        return "/sysMng/equipTopMenuView";
    }

    /**
     * 시스템 기본관리 탑 메뉴 뷰를 리턴합니다.
     *
     * @param model
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/getBasicTopMenuView.do")
    public String getBasicTopMenuView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

    	model.addAttribute("map", map);

        return "/sysMng/basicTopMenuView";
    }

}
