package geomex.xeus.ndmssend.web;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

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
import geomex.xeus.ndmssend.service.NdmsSendService;
import geomex.xeus.sysmgr.service.OrganizationService;
import geomex.xeus.sysmgr.web.CodeCtrl;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.util.code.CodeConvertor;
import geomex.xeus.util.code.SystemParameter;



@Controller
@RequestMapping("/ndmsSend")
public class NdmsSendController {

    @Resource(name = "organizationService")
    private OrganizationService orgz;

    @Resource(name = "codeCtrl")
    private CodeCtrl code;

	@Resource(name = "searchService")
	private SearchService bjd;

    @Resource(name = "ndmsSendService")
    private NdmsSendService service;
    
    @Resource(name = "crmsSysParamService")
    private CrmsSysParamService sysParamList;

    @Resource
    private Validator validator;

    List<HashMap<String, String>> resultList=null;
    HashMap<String, String> resultObj=null;
    boolean resultBol=false;


    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(this.validator);
        binder.registerCustomEditor(MultipartFile.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) {
                //System.out.println("initBinder MultipartFile.class: {}; set null; " + text);
                setValue(null);
            }
        });
    }
	@RequestMapping(value = "/getSmsSendView.do")
    public String getSmsSendView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	model.addAttribute("orgz", orgz.getList(null));
		model.addAttribute("emd", bjd.getEmdList());
		model.addAttribute("li", bjd.getLiList());
		model.addAttribute("code", new CodeConvertor(code.getCdeList()));

    	return "/send/smsSendView";
    }

    @RequestMapping(value = "/getVoiceSendView.do")
    public String getVoiceSendView(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	model.addAttribute("orgz", orgz.getList(null));
    	model.addAttribute("emd", bjd.getEmdList());
    	model.addAttribute("li", bjd.getLiList());
    	model.addAttribute("code", new CodeConvertor(code.getCdeList()));

    	return "/send/voiceSendView";
    }

   /**
    * 검색한 사용자에 대한 사용자 정보를 가져온다. 사용자가 null이면 모든 사용자 정보를 가져온다.
    * @param map->name
    * @return 사용자 정보 리스트
    * @throws Exception
    */
    @RequestMapping(value = "/getSMSInfo.json", method = RequestMethod.POST)
    public void getList(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	System.out.println("map = "+map);
		resultList=service.getSMSInfo(map);
		if(map.get("name")==null || map.get("name").equals(""))
    	{
    		map.put("name","");
    	}
    	if(resultList==null)
    	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else
    	{
    		model.addAttribute("result",resultList);
    		System.out.println("result = "+resultList);
    	}
    }
    /**
     * 해당 읍면동에 대한 음성장비 목록 가져오기. 읍면동이 없으면 모든 음성장비 목록 가져오기.
     * @param map->emdCd
     * @return 음성장비 리스트
     * @throws Exception
     */
    @RequestMapping(value = "/getVoiceInfo.json", method = RequestMethod.POST)
    public void getVoiceInfo(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	System.out.println("map = "+map);
    	if(map.get("emdCd")==null || map.get("emdCd").equals(""))
    	{
    		map.put("emdCd","");
    	}
    	resultList=service.getVoiceInfo(map);

    	if(resultList==null)	{
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else{
    		model.addAttribute("result",resultList);
    		System.out.println("result = "+resultList);
    	}
    }
    /**
     * 선택한 사용자들에게 문자를 보냅니다.
     * @param map->recInfo, cntcCn mssageTy, resveAt, tranDate
     * @return true or false
     * @throws Exception
     */
    @RequestMapping(value = "/sendAllSMS.json", method = RequestMethod.POST)
    public void sendAllSMS(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	System.out.println("map = "+map);
    	List<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();
    	HashMap<String, String> temp=null;
    	SystemParameter sysParam = new SystemParameter(sysParamList.getList(null));
    	String cntcCn=map.get("cntcCn");	
    	String userInfo=map.get("recInfo");	
     	String mssageTy=map.get("mssageTy");
     	String resveAt=map.get("resveAt");
    	
    	String tranDate;
    	if(map.get("tranDate")!=null && !map.get("tranDate").equals("")){
    		tranDate=map.get("tranDate");
    	}
    	else{
    		tranDate=null;
    	}
    	//즉시 발송이면 예약날짜를 없앤다.
    	if(map.get("resveAt").equals("0")){
    		tranDate=null;
    	}
    	
    	String firstArr[],secondArr[];
    	firstArr=userInfo.split(";");
    	//recInfo를 파씽해서 새로운 HashMap에다 넣기
    	for(int i=0; i<firstArr.length; i++){
    		temp=new HashMap<String, String>();
    		secondArr=firstArr[i].split(",");
    		
    		temp.put("cntcCn", (String)cntcCn);	//내용
    		temp.put("mssageTy", mssageTy);	//메세지 타입
    		temp.put("resveAt", resveAt );	//예약인지
    		temp.put("tranDate", tranDate);//예약날짜
    		temp.put("rcverNm", (String)secondArr[0]);	//단말기이름
    		temp.put("recptnNo", (String)secondArr[1]);	//단말기번호
    		temp.put("transNo", sysParam.getParamMap().get("sms.send_num"));	//장비번호
    		
    		//새로운 HashMap을 새로운 list에 넣기.
    		list.add(temp);
    	}
    	resultBol=service.sendAllSMS(list);

    	if(resultBol==false){
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else{
    		model.addAttribute("result",resultBol);
    		System.out.println("result = "+resultBol);
    	}
    }
    /**
     * 선택한 음성장비들에게 메세지 내용을 전달합니다.
     * @param map->recInfo, cntcCn, mssageTy, voiceSexdstn, voiceVolum, voiceVe, beginNtcn, endNtcn, resveAt, tranDate
     * @return true or false
     * @throws Exception
     */
    @RequestMapping(value = "/sendAllVoice.json", method = RequestMethod.POST)
    public void sendAllVoice(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	System.out.println("map = "+map);
    	List<HashMap<String,String>> list=new ArrayList<HashMap<String, String>>();
    	HashMap<String, String> temp=null;
    	
    	String cntcCn=map.get("cntcCn");
    	String voiceInfo=map.get("recInfo");
    	
    	String voiceSexdstn=map.get("voiceSexdstn");
    	String voiceVolum=map.get("voiceVolum");
    	String voiceVe=map.get("voiceVe");
    	//beginNtcn, endNtcn 디폴트값->선택안함
    	String beginNtcn,endNtcn;
    	if(map.get("beginNtcn")==null || map.get("beginNtcn").equals("")){
    		beginNtcn="선택안함";
    	}
    	else{
    		beginNtcn=map.get("beginNtcn");
    	}
    	if(map.get("endNtcn")==null || map.get("endNtcn").equals("")){
    		endNtcn="선택안함";
    	}
    	else{
    		endNtcn=map.get("endNtcn");
    	}
    	String mssageTy=map.get("mssageTy");
    	String resveAt=map.get("resveAt");
    	
    	String tranDate;
    	if(map.get("tranDate")!=null && !map.get("tranDate").equals("")){
    		tranDate=map.get("tranDate");
    	}
    	else{
    		tranDate=null;
    	}
    	//즉시 발송이면 예약날짜를 없앤다.
    	if(map.get("resveAt").equals("0")){
    		tranDate=null;
    	}
    	String firstArr[],secondArr[];
    	firstArr=voiceInfo.split(";");
    	//합쳐진 문자열 파씽해서 새로운 hashmap에 넣기.
    	for(int i=0; i<firstArr.length; i++){
    		temp=new HashMap<String, String>();
    		secondArr=firstArr[i].split(",");

    		temp.put("rcverNm", (String)secondArr[1]);	//단말기명
    		temp.put("recptnNo", (String)secondArr[2]);	//단말기번호
    		temp.put("tranlNo", (String)secondArr[0]);	//코드
    		temp.put("cntcCn", (String)cntcCn);		//내용
    		//여기서부터 코드
    		temp.put("voiceSexdstn", voiceSexdstn );
    		temp.put("voiceVolum",voiceVolum );
    		temp.put("voiceVe", voiceVe);
    		temp.put("beginNtcn", beginNtcn );
    		temp.put("endNtcn", endNtcn );
    		temp.put("mssageTy",mssageTy);
    		temp.put("resveAt", resveAt );
    		temp.put("tranDate", tranDate);
    		//새로운  hashmap을 새로운 hashmap list에 넣어줌.
    		list.add(temp);
    	}
    	resultBol=service.sendAllVoice(list);

    	if(resultBol==false){
    		model.addAttribute("error","에러");
    		System.out.println("error = 에러");
    	}
    	else{
    		model.addAttribute("result",resultBol);
    		System.out.println("result = "+resultBol);
    	}
    }
}
