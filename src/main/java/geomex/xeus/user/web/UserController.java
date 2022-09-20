package geomex.xeus.user.web;

import geomex.xeus.sysmgr.service.AuthService;
import geomex.xeus.sysmgr.service.NoticeService;
import geomex.xeus.sysmgr.service.OrganizationService;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.sysmgr.web.ColumnController;
import geomex.xeus.system.annotation.NoSession;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.user.service.UserService;
import geomex.xeus.user.service.UserVo;
import geomex.xeus.user.util.RSA;
import geomex.xeus.user.util.RSAKey;
import geomex.xeus.user.util.SHA;
import geomex.xeus.user.util.TEA;
import geomex.xeus.util.code.CodeConvertor;
import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.SystemParameter;
import geomex.xeus.util.code.ValidInspector;
import geomex.xeus.util.login.LoginManager;

import java.beans.PropertyEditorSupport;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.fasterxml.jackson.databind.ObjectMapper;

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
@RequestMapping("/user")
public class UserController {

    @Resource(name = "codeCtrl")
    private CodeCtrl code;

    @Resource(name = "userService")
    private UserService service;

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

    @Resource
    private Validator validator;

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

    @NoSession
    @RequestMapping(value = "/redirect.do")
    public String redirect() throws Exception {

        return "/user/redirect";
    }

    /**
     * 사용자 계정 갯수를 조회합니다.
     *
     * @return json
     * @throws Exception
     */
    @RequestMapping(value = "/getCount.json")
    public void getCount(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("count", service.getCount(map));

    }

    /**
     * 세션을 체크합니다.
     *
     * @param model
     * @param session
     */
    @NoSession
    @RequestMapping(value = "/sessionCheck.json")
    public void sessionCheck(Model model, HttpSession session) {

        boolean bool = false;
        if (session.getAttribute("userId") != null) {
            bool = true;
        }
        model.addAttribute("result", bool);
    }

    /**
     * 사용자 로그인 페이지 뷰로 이동합니다. <br>
     * 암호화 전송을 위하여 RSA 키를 발급합니다.
     *
     * @return view
     * @throws Exception
     */
    @NoSession
    @RequestMapping(value = "/login.do")
    public String loginView(Model model, HttpSession session) throws Exception {

        String view = "";
        String userId = (String) session.getAttribute("userId");
        /*
         * if(userId != null && !"".equals(userId)){ view = "redirect:/map/view.do"; }else{ session.setAttribute("RSA",
         * RSAKey.generate(1024));
         * 
         * model.addAttribute("notice", notice.getList(null)); model.addAttribute("noticeCount", notice.getCount(null));
         * 
         * view = "/user/login"; }
         */
        session.setAttribute("RSA", RSAKey.generate(1024));

        model.addAttribute("notice", notice.getList(null));
        model.addAttribute("noticeCount", notice.getCount(null));

        view = "/user/login";

        return view;
    }

    //Platform Tray에서 로그인 처리하기 위하여 Key를 json으로 받는 로직 추가
    //2018.10.24 by khkim
    @NoSession
    @RequestMapping(value = "/silentLogin.json")
    public ResponseEntity<String> silentloginView(Model model, HttpSession session) throws Exception {
        RSAKey rsaKey = RSAKey.generate(1024);
        session.setAttribute("RSA", rsaKey);  //session에 키 저장.
        // s
        String modulus = RSAKey.toHex(rsaKey.getModulus());
        String exponent = RSAKey.toHex(rsaKey.getPublicExponent());
        //
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=UTF-8");
        ObjectMapper obj = new ObjectMapper();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("modulus", modulus);
        map.put("exponent", exponent);
        String json = obj.writeValueAsString(map);
        return new ResponseEntity<String>(json, headers, HttpStatus.OK);
    }

    /**
     * 사용자 등록 페이지 뷰로 이동합니다.
     *
     * @return view
     * @throws Exception
     */
    @NoSession
    @RequestMapping(value = "/reg.do")
    public String regView(Model model) throws Exception {

        model.addAttribute("column", col.getList());
        model.addAttribute("orgz", orgz.getList(null));
        model.addAttribute("code", new CodeConvertor(code.getCdeList()));

        return "/user/reg";
    }

    /**
     * 사용자 정보 찾기 페이지 뷰로 이동합니다.
     *
     * @return view
     * @throws Exception
     */
    @NoSession
    @RequestMapping(value = "/find.do")
    public String findView(Model model) throws Exception {

        model.addAttribute("column", col.getList());
        model.addAttribute("orgz", orgz.getList(null));
        model.addAttribute("code", new CodeConvertor(code.getCdeList()));

        return "/user/find";
    }

    /**
     * 사용자 정보 수정 페이지 뷰로 이동합니다.
     *
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/alter.do")
    public String alterView(Model model, HttpSession session) throws Exception {

        String userId = (String) session.getAttribute("userId");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);

        model.addAttribute("userVo", service.getItem(map));
        model.addAttribute("column", col.getList());
        model.addAttribute("orgz", orgz.getList(null));
        model.addAttribute("code", new CodeConvertor(code.getCdeList()));

        return "/user/edit";

    }

    /**
     * 사용자 비밀번호 수정 페이지 뷰로 이동합니다.
     *
     * @return view
     * @throws Exception
     */
    @RequestMapping(value = "/alterPw.do")
    public String alterPw(Model model, HttpSession session) throws Exception {

        return "/user/editPassword";

    }

    /**
     * 사용자 로그인 인증을 수행합니다. <br>
     * 파라미터의 내용은 TEA 암호화 하며, <br>
     * TEA의 키는 RSA로 암호화 합니다.
     *
     * @param req
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @NoSession
    @RequestMapping(value = "/signIn.json", method = RequestMethod.POST)
    public void signIn(HttpServletRequest req, Model model, HttpSession session,
                       @RequestParam HashMap<String, String> map)
        throws Exception {

        if (session.getAttribute("RSA") == null) {
            //model.addAttribute("error", "RSAKey is Null");
            model.addAttribute("RSAError", true);
        } else {
            RSA rsa = null;
            TEA tea = null;

            try {
                rsa = new RSA((RSAKey) session.getAttribute("RSA"));
                tea = new TEA(rsa.decrypt(map.get("key")));
            } catch (Exception e) {
                //model.addAttribute("error", "Invalid key: Length was less than 16 bytes");
                model.addAttribute("RSAError", true);
                return;
            }

            map.put("userId", tea.decrypt(map.get("userId")));

            UserVo vo = null;
            UserVo pwdValid = service.getItem(map.get("userId"));
            if (pwdValid == null || "".equals(pwdValid.getUserId())) {
                model.addAttribute("result", null);
                return;
            }

            map.put("userPwd", SHA.simpleEnc512(pwdValid.getUserId() + tea.decrypt(map.get("userPwd"))));
            vo = service.getItem(map);

            if ((vo != null && !"".equals(vo.getUserId()) && "12".equals(vo.getAuthStatCd()))) {

                LoginManager loginManager = LoginManager.getInstance();

                if (!loginManager.isUsing(vo.getUserId())) {

                    loginManager.setSession(req.getSession(), vo.getUserId());

                }

                session.setAttribute("userId", vo.getUserId());
                session.setAttribute("userNm", vo.getUserNm());
                session.setAttribute("authList", auth.getList(null));
                session.setAttribute("authGrpId", vo.getAuthGrpNo());
        		session.setMaxInactiveInterval(60*60*6);	//6시간
//        		session.setMaxInactiveInterval(5);
                
                HashMap<String, String> authParam = new HashMap<String, String>();
                authParam.put("authGrpNo", vo.getAuthGrpNo());
                session.setAttribute("authGrp", auth.getAuthGrpList(authParam));
                session.setAttribute("userIp", req.getRemoteAddr());
                session.removeAttribute("RSA");

                model.addAttribute("result", vo);

                /*
                 * } else { model.addAttribute("error", "이미 접속 중인 사용자입니다."); }
                 */

            } else {
                model.addAttribute("error", "사용자 인증에 실패하였거나 시스템 접속권한이 존재하지 않습니다.");
            }

        }

    }

    /**
     * 사용자 비밀번호를 변경합니다.
     *
     * @param res
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editPassword.json", method = RequestMethod.POST)
    public void editPassword(Model model, HttpSession session, @RequestParam HashMap<String, String> map)
        throws Exception {

        map.put("userId", (String) session.getAttribute("userId"));
        map.put("userPwd", SHA.simpleEnc512(map.get("userId") + map.get("nowUserPwd")));

        UserVo vo = service.getItem(map);

        if (vo != null && !"".equals(vo.getUserId())) {
            map.put("newUserPwd", SHA.simpleEnc512(map.get("userId") + map.get("newUserPwd")));
            model.addAttribute("result", service.editPassword(map));
        } else {
            model.addAttribute("error", "현재 비밀번호를 다시한번 확인해 주세요.");
        }
    }

    /**
     * 세션을 무효화 합니다. <br>
     * redirect : 로그인페이지
     *
     * @param req
     * @param res
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/signOut.do")
    public String signOut(HttpServletRequest req, Model model, HttpSession session) throws Exception {

        LoginManager loginManager = LoginManager.getInstance();
        loginManager.LogOut(session.getId());

        session.invalidate();

        //return "redirect:/user/login.do";
        return "redirect:/map/view.do";
    }

    /**
     * 사용자 정보 리스트를 가져옵니다.
     *
     * @param req
     * @param res
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getList.json", method = RequestMethod.POST)
    public void getList(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        model.addAttribute("result", service.getList(map));
        model.addAttribute("count", service.getCount(map));

    }

    /**
     * 특정 사용자의 데이터를 가져옵니다. <br>
     * View, Json 리졸버 두가지를 사용합니다.
     *
     * @param req
     * @param res
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @NoSession
    @RequestMapping(value = "/getItem", method = RequestMethod.POST)
    public String getItem(HttpServletRequest req, Model model, @RequestParam HashMap<String, String> map)
        throws Exception {

        String url = req.getRequestURI();
        if (url.endsWith(".do")) {
            model.addAttribute("column", col.getList());
        }

        UserVo vo = service.getItem(map);

        model.addAttribute("result", vo);

        return "/user/view";
    }

    /**
     * 사용자 계정을 삭제합니다. <b>현재 사용되지 않습니다.</b>
     *
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @Deprecated
    @RequestMapping(value = "/del.json", method = RequestMethod.POST)
    public void del(Model model, HttpSession session, @RequestParam(required = true) HashMap<String, String> map)
        throws Exception {

        model.addAttribute("result", service.del(map));

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
    @NoSession
    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public void add(Model model, HttpSession session, @ModelAttribute @Valid UserVo param, BindingResult br)
        throws Exception {

        param.setUserPwd(SHA.simpleEnc512(param.getUserId() + param.getUserPwd()));
        //param.setAuthGrpNo("G00002");
        param.setAuthStatCd("11");
        /*
         * param.setAuthAtmtCnt(0); param.setAuthConnIp("127.0.0.1"); param.setDepartNm("1"); param.setPosNm("1");
         * param.setMobileNum("1");
         */
        param.setReqDat(DateUtil.getStrSec());

        String[] ignoreField = { "authGrpNo", "mobileNum", "posNm", "departNm" };
        String msg = ValidInspector.findError(br, ignoreField);

        if ("pass".equals(msg)) {

            SystemParameter sysParam = new SystemParameter(sysParamList.getList(null));

            HashMap<String, String> map = null;
            map = sysParam.getParamMap();

            String uploadPath = map.get("sys.upload_path");
            String subDir = param.getSubDir();
            MultipartFile file = param.getFile();
            if (file != null) {
                if (ValidInspector.isPathAttack(file.getOriginalFilename())) {
                    model.addAttribute("error", "올바른 파일 이름이 아닙니다.\n특수문자를 제거해 주세요.");
                    return;
                } else if (file.getOriginalFilename().length() > 30) {
                    model.addAttribute("error", "파일명은 30자 미만으로 업로드 할 수 있습니다.");
                    return;
                } else {
                    String fileNm = file.getOriginalFilename();
                    String tempNm = DateUtil.getStrMilSec() + "_" + fileNm;
                    String fullPath = uploadPath + subDir + tempNm;

                    File chkDir = new File(uploadPath + subDir);
                    if (!chkDir.isDirectory()) {
                        try {
                            chkDir.mkdirs();
                        } catch (Exception e) {}
                    }

                    param.setOathFileNm(fileNm);
                    param.setOathFilePath(fullPath);

                    File realFile = new File(fullPath);
                    file.transferTo(realFile);
                }
            }

            model.addAttribute("result", service.add(param));
        } else {
            model.addAttribute("error", msg);
        }

    }

    /**
     * 사용자 계정을 수정합니다.
     *
     * @param model
     * @param param
     * @param br
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit.json", method = RequestMethod.POST)
    public void edit(Model model, HttpSession session, @ModelAttribute @Valid UserVo param, BindingResult br)
        throws Exception {
        String[] ignoreField = { "authGrpNo" };
        String msg = ValidInspector.findError(br, ignoreField);

        if ("pass".equals(msg)) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("userId", param.getUserId());
            map.put("userPwd", SHA.simpleEnc512(param.getUserId() + param.getUserPwd()));
            UserVo valid = service.getItem(map);

            if (valid == null) {
                model.addAttribute("error", "비밀번호를 다시한번 확인해 주세요.");
            } else if (valid.getUserId() != null && !"".equals(valid.getUserId())) {

                SystemParameter sysParam = new SystemParameter(sysParamList.getList(null));

                HashMap<String, String> paramMap = null;
                paramMap = sysParam.getParamMap();

                String uploadPath = paramMap.get("sys.upload_path");
                String subDir = param.getSubDir();

                MultipartFile file = param.getFile();
                if (file != null) {
                    if (ValidInspector.isPathAttack(file.getOriginalFilename())) {
                        model.addAttribute("error", "올바른 파일 이름이 아닙니다.\n특수문자를 제거해 주세요.");
                        return;
                    } else if (file.getOriginalFilename().length() > 30) {
                        model.addAttribute("error", "파일명은 30자 미만으로 업로드 할 수 있습니다.");
                        return;
                    } else {
                        String fileNm = file.getOriginalFilename();
                        String tempNm = DateUtil.getStrMilSec() + "_" + fileNm;
                        String fullPath = uploadPath + subDir + tempNm;

                        File chkDir = new File(uploadPath + subDir);
                        if (!chkDir.isDirectory()) {
                            try {
                                chkDir.mkdirs();
                            } catch (Exception e) {}
                        }

                        param.setOathFileNm(fileNm);
                        param.setOathFilePath(fullPath);

                        File realFile = new File(fullPath);
                        file.transferTo(realFile);

                        File beforeFile = new File(valid.getOathFilePath());
                        if (beforeFile.exists()) beforeFile.delete();
                    }
                }

                model.addAttribute("result", service.edit(param));
            }
        } else {
            model.addAttribute("error", msg);
        }

    }

    /**
     * 사용자 그리드 정보를 변경합니다.
     *
     * @param model
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editBoardInfo.json", method = RequestMethod.POST)
    public void editBoardInfo(Model model, HttpSession session, @RequestParam HashMap<String, String> map)
        throws Exception {

        String userId = (String) session.getAttribute("userId");

        if (userId != null && !"".equals(userId)) {
            model.addAttribute("result", service.editBoardInfo(map));
        } else {
            model.addAttribute("error", "세션이 존재하지 않습니다.");
        }

    }

    /**
     * 사용자 계정 권한을 변경합니다. <br>
     * <br>
     * <b>ManagementController로 이동되어 현재 사용되지 않습니다.</b>
     *
     * @param model
     * @param param
     * @return
     * @throws Exception
     */
    @Deprecated
    @RequestMapping(value = "/editAuth.json", method = RequestMethod.POST)
    public void editAuth(Model model, @ModelAttribute UserVo param) throws Exception {

        if (!"".equals(param.getUserId()) && param.getUserId() != null &&
            !"".equals(param.getAcptDat()) && param.getAcptDat() != null) {

            model.addAttribute("result", service.edit(param));
        } else {
            model.addAttribute("error", "아이디, 사용자 구분, 계정상태는 필수 항목입니다.");
        }

    }

    /**
     * 사용자정보 확인 후, 해당 계정이 존재하면 임시 비밀번호를 발급합니다.
     *
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @NoSession
    @RequestMapping(value = "/findAndEidt.json", method = RequestMethod.POST)
    public void findAndEidt(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        UserVo vo = service.getItem(map);

        if (vo == null) {
            model.addAttribute("error",
                "입력하신 정보와 일치하는 계정이 존재하지 않습니다.\n계정정보가 정확할 경우만 임시 비밀번호발급이 가능합니다.\n계정정보 확인 후, 다시 시도하여 주십시오.");
        } else if (vo.getUserId() != null && !"".equals(vo.getUserId())) {
            Random rd = new Random();
            String temp_pw = "";

            String randStr = "1234567890abcdefghijkl1234567890mnopqrstuvwxyz";
            for (int i = 0; i < 8; i++) {
                temp_pw += "" + randStr.charAt(rd.nextInt(randStr.length()));
            }
            /*
             * for(int i=0; i<2; i++){ temp_pw += ""+rd.nextInt(10); }
             */

            try {
                HashMap<String, String> pwdMap = new HashMap<String, String>();
                pwdMap.put("userId", vo.getUserId());
                pwdMap.put("userPwd", vo.getUserPwd());
                pwdMap.put("newUserPwd", SHA.simpleEnc512(vo.getUserId() + temp_pw));

                service.editPassword(pwdMap);
                model.addAttribute("result", temp_pw);
            } catch (Exception e) {
                System.out.println(e);
                model.addAttribute("error", "임시 비밀번호 생성을 실패하였습니다.\n 관리자에게 문의하여 주십시오.");
            }
        }

    }

    /**
     * 입력한 정보와 일치하는 계쩡의 ID를 리턴합니다.
     *
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @NoSession
    @RequestMapping(value = "/findId.json", method = RequestMethod.POST)
    public void findId(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        UserVo vo = service.getItem(map);

        if (vo == null) {
            model.addAttribute("error", "입력하신 정보와 일치하는 계정이 존재하지 않습니다.\r\n계정정보 확인 후, 다시 시도하여 주십시오.");
        } else if (vo.getUserId() != null && !"".equals(vo.getUserId())) {
            model.addAttribute("result", vo.getUserId());
        } else {
            model.addAttribute("error", "아이디 찾기를 실패하였습니다.\r\n 관리자에게 문의하여 주십시오.");
        }

    }

    /**
     * 관리자가 해당 계정의 임시 비밀번호를 발급합니다.
     *
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editPasswordByAdmin.json", method = RequestMethod.POST)
    public void editPasswordByAdmin(Model model, HttpSession session, @RequestParam HashMap<String, String> map)
        throws Exception {

        UserVo vo = service.getItem(map);

        if (vo.getUserId() != null && !"".equals(vo.getUserId())) {
            Random rd = new Random();
            String temp_pw = "";
            for (int i = 0; i < 5; i++) {
                temp_pw += "" + rd.nextInt(10);
            }

            try {
                HashMap<String, String> pwdMap = new HashMap<String, String>();
                pwdMap.put("userId", vo.getUserId());
                pwdMap.put("userPwd", vo.getUserPwd());
                pwdMap.put("newUserPwd", SHA.simpleEnc512(vo.getUserId() + temp_pw));

                service.editPassword(pwdMap);
                model.addAttribute("result", temp_pw);
            } catch (Exception e) {
                System.out.println(e);
                model.addAttribute("error", "임시 비밀번호 생성을 실패하였습니다.");
            }
        } else {
            model.addAttribute("error", "임시 비밀번호 생성을 실패하였습니다.");
        }

    }

    /**
     * 요청한 파일을 리턴합니다.
     *
     * @param req
     * @param session
     * @param res
     * @param map
     * @throws Exception
     */
    @RequestMapping("/getFile.json")
    public void getFiles(HttpServletRequest req, HttpServletResponse res, HttpSession session, Model model,
                         @RequestParam HashMap<String, String> map)
        throws Exception {

        UserVo vo = service.getItem(map);

        if (vo == null) {
            model.addAttribute("error", "파일이 존재하지 않습니다.");
        } else {
            String realFileName = vo.getOathFileNm();
            String realFile = vo.getOathFilePath();

            BufferedOutputStream out = null;
            InputStream in = null;
            String exceptionStr = "존재하지않는  파일을 요청하였거나, 사용자("
                + req.getRemoteAddr() + ")가 파라미터 공격을 시도하였음 (파일주소 : " + realFile + ")";

            try {
                res.setContentType("application/octet-stream");
                res.setHeader("Content-Disposition",
                    "inline;filename=" + new String(realFileName.getBytes("UTF-8"), "ISO-8859-1"));
                File file = new File(realFile);
                if (file.exists()) {
                    in = new FileInputStream(file);
                    out = new BufferedOutputStream(res.getOutputStream());
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                } else {
                    System.out.println(exceptionStr);
                }
            } catch (Exception e) {
                System.out.println(exceptionStr);
            } finally {
                if (out != null) {
                    out.flush();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
        }

    }

    /**
     * 현재 접속중인 사용자 목록을 조회합니다.
     *
     * @param model
     * @param session
     */
    @NoSession
    @RequestMapping(value = "/getAccUserList.json")
    public void getAccUserList(Model model) {

        LoginManager loginManager = LoginManager.getInstance();

        model.addAttribute("list", loginManager.getUserList());

    }

    /**
     * 사용자 로그인 제한 횟수를 수정합니다.
     *
     * @param model
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editLockCnt.json", method = RequestMethod.POST)
    public void editLockCnt(Model model, @RequestParam HashMap<String, String> map) throws Exception {

        if (!"".equals(map.get("userId")) && map.get("userId") != null) {

            model.addAttribute("result", service.editAuthAtmtCnt(map));

        } else {
            model.addAttribute("error", "아이디는 필수 항목입니다.");
        }

    }

}
