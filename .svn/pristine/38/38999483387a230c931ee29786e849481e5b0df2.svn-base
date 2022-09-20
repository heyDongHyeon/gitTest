package geomex.xeus.ndms.web;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.xeus.alarm.service.EventAlarmSocketService;
import geomex.xeus.ndms.service.NdmsApiService;
import geomex.xeus.ndms.service.NdmsCode;
import geomex.xeus.ndms.service.NdmsUpdateService;
import geomex.xeus.ndms.service.NdmsUtils;
import geomex.xeus.ndps.service.NdpsUpdateService;
import geomex.xeus.scheduler.SchedulerWorker;
import geomex.xeus.smartcity.Utils;
import geomex.xeus.smartcity.service.EventHistVo;
import geomex.xeus.smartcity.service.EventService;
import geomex.xeus.smartcity.service.EventWebSocketService;
import geomex.xeus.util.code.DateUtil;

@Controller
@RequestMapping("/ndms")
public class NdmsApiController {
	@Resource(name = "eventWebSocketService")
	private EventWebSocketService socket;
	@Resource(name="ndmsApiService")
	private NdmsApiService service;

	@Resource(name = "eventService")
	private EventService event;

	@Resource(name = "propService")
	private EgovPropertyService propService;

	@Resource(name = "eventAlarmSocketService")
	private EventAlarmSocketService alarmService;

	@Resource(name="schedulerWorker")
	private SchedulerWorker schedulerWorker;


	@Resource(name = "ndmsUpdateService")
	private NdmsUpdateService ndmsUpdateService;

	@Resource(name = "ndpsUpdateService")
	private NdpsUpdateService ndpsUpdateService;


	/**
     * CCTV Lock 상태를 전파합니다.
     *
     * @throws Exception
     */
    @RequestMapping(value = {"/updateTest.json"})
    public void updateTest(HttpServletRequest req, Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {
    	ndmsUpdateService.updateNdmsDataByNdmsApi(1);
//    	ndpsUpdateService.updateNdpsDataByMysqlDb();
//    	schedulerWorker.asyncNdpsEventTest();
    }


    /**
     * CCTV Lock 상태를 전파합니다.
     *
     * @throws Exception
     */
    @RequestMapping(value = {"/editNdmsDataByNdmsApi.json"})
    public void editNdmsDataByNdmsApi(HttpServletRequest req, Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {
    	model.addAttribute("result", ndmsUpdateService.editNdmsDataByNdmsApi(map));

//    	schedulerWorker.asyncNdpsEventTest();
    }


	/**
	 * 실시간 NDMS 재난 데이터를 수신받는다.
	 * - 오늘 일자를 검색하며 실시간으로 json만 넘겨준다.
	 * @param response
	 * @param request
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = {"/realTimeData.json"}, method = RequestMethod.POST)
	public void realTimeData(@RequestParam HashMap<String, String> map, Model model) throws Exception {
		//System.out.println("realTimeData.json(map) = "+map);
		List<HashMap<Object, Object>> result=null;

		int key = Integer.parseInt( map.get("key") );
		String emdCd=map.get("emdCd");
		String reqData = map.get("dat").toString().replaceAll("-", "");
		String url;
		List<HashMap<Object, Object>> list=null;
		if ( reqData == null || reqData.equals("") ) {
			reqData = DateUtil.getStrDay();
		}

		if( key ==4) {
			map.put("dat", reqData);
			list=service.getKmaAlertInfo(map);
		}
		else {
			url= NdmsCode.getUrl(key, reqData);
			list= NdmsUtils.getNdmsData(url, key);
//			list= service.getRealTest(map);
		}
		//list를 가지고 파씽
		if(key==7 || key==5 || key==9 || key==8 || key==6)	{
			//중복데이터 제거하고 최근 것만 가지고 오고 싶을 때
			result=NdmsUtils.passing(list, emdCd, key);	//리스트와 key와 지역명을 가지고 데이터를 passing
		} else	{
			//그냥 중복 포함 모든 데이터 가져오고 싶을 때
			result=NdmsUtils.duplePassing(list, emdCd, key);
		}
		model.addAttribute("result", result);

//		model.addAttribute("result", list);
//		System.out.println("result = "+list);
	}


	/**
	 * 해당 날짜의 NDMS 재난 데이터를 수신받는다.
	 * - DB에 데이터를 쌓는다.
	 * @param req
	 * @param model
	 * @param session
	 * @param map
	 * @throws Exception
	 */
	@RequestMapping(value = {"/syncData.json"}, method = RequestMethod.POST)
	public void syncData(@RequestParam HashMap<String, String> map, Model model) throws Exception {
		String[] keyArr = map.get("keys").split(",");
		String reqData = map.get("dat");

		for(int i=0; i<keyArr.length; i++){
			String url = NdmsCode.getUrl(Integer.parseInt(keyArr[i]), reqData);
			List<HashMap<Object, Object>>  list = NdmsUtils.getNdmsData(url, Integer.parseInt(keyArr[i]));

			service.add(list, Integer.parseInt(keyArr[i]));
		}

	}



	/**
     * CCTV Lock 상태를 전파합니다.
     *
     * @throws Exception
     */
    @RequestMapping(value = {"/test.json"})
    public void setLock(HttpServletRequest req, Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

    	HashMap<String, String> param = new HashMap<String, String>();
    	param.put("statEvetTypCd", 		"NDMSWARN");
    	param.put("statMsgTypCd", 		"");
    	param.put("outbPosNm", 			"세종 시청");
    	param.put("statEvetNm", 		"지진현황");
    	param.put("statEvetClrDtm", 	"");
    	param.put("statEvetCntn", 		"테스트 발생");
    	param.put("statEvetType", 		"사회재난");
    	param.put("outbPos", 			"[{x:127.2986687, y:36.5834718}]");
    	param.put("x", 					"127.2986687");
    	param.put("y", 					"36.5834718");
    	param.put("statEvetOutbDtm", 	"2018110617451111");
    	param.put("statEvetActnCntn",	"");
    	param.put("procSt", 			"10");
    	param.put("isTest", 			"N");
    	param.put("uSvcOutbId", 		"T2018110617451111");
    	param.put("statEvetActnMn", 	"");
    	param.put("statEvetActnDtm", 	"");
    	param.put("statEvetSvcTyp", 	"테스트 서비스");
    	param.put("etcCntn", 			"");
    	param.put("tmx", 				"36.5834718");
    	param.put("tmy", 				"127.2986687");
    	param.put("targetId", 				"geomex");
    	param.put("targetGrp", 				"G00001");
    	param.put("etcCntn", 				"{}");

    	/*for(String key : param.keySet()){
    		param.replace(key, StrUtil.chkNull(param.get(key)));
    		if("uSvcOutbId".equals(key)){
    			if("".equals(param.get("uSvcOutbId"))){
    				param.replace("uSvcOutbId", RandomStringUtils.randomAlphanumeric(15));
    			}
    		}
    	}
*/
    	EventHistVo vo = Utils.parseVo(Utils.setJson(param));

		String[] full_url = req.getRequestURI().split("/");
    	String url = full_url[full_url.length - 1];

    	if("test.json".equals(url)){
    		event.add(vo);
    	}else if("lockOff.json".equals(url)){
    		event.del(param);
    		try {
    			//hist.add(vo);
			} catch (Exception e) {

			}
    		//if(hist.getItem(param) == null) hist.add(vo);
    	}
    	socket.broadcast(Utils.setJson(param));
	//	model.addAttribute("result", true);
	//	model.addAttribute("uSvcOutbId", param.get("uSvcOutbId"));
	//	model.addAttribute("statEvetNm", param.get("statEvetNm"));
    }
    @RequestMapping(value = {"/addKmaAlertInfo.do"}, method = RequestMethod.POST)
	public void addKmaAlertInfo(@RequestParam HashMap<Object, Object> param, Model model) throws Exception {
//		System.out.println("map(KMAALERTINFO) = "+map);
		String apiKey=propService.getString("ndms.apikey");
		String docCode=(String)param.get("DocCode");
		String docNm="";

		double lat= Double.parseDouble((String)param.get("Latitude"));
		double lon=Double.parseDouble((String)param.get("Longitude"));

		String distance=String.valueOf(NdmsUtils.distance(36.56850356260252, 127.25738361299256, lat, lon));
		switch(docCode) {
			case "111" : docNm="지진조기경보"; break;
			case "212" : docNm="지진속보";break;
			case "102" : docNm="지진정보";break;
			case "103" : docNm="지진정보(재통보)";break;
			case "104" : docNm="국외지진정보";break;
			default : docNm="";
		}
		param.put("DocNm", docNm);
		param.put("distance", distance);
		System.out.println("map(new) = "+param);
		System.out.println("distance(new) = "+distance);


		if(apiKey.equals(param.get("apikey"))) {
			if((long)service.getKmaAlertEventIdCount(param).get("count")==0) {	//동일한 event_id가 없다.
				service.addKmaAlertInfo(param);
				socket.earthBroadcast(NdmsUtils.setEarthJson(param));
				alarmService.alarm(10);
			}
			else {			////동일한 event_id가 있다
				service.modKmaAlertInfo(param);
			}
		}
	}
    @RequestMapping(value = {"/preAddKmaAlertInfo.do"}, method = RequestMethod.GET)
   	public void preAddKmaAlertInfo(@RequestParam HashMap<Object, Object> param, Model model) throws Exception {
//   		System.out.println("map(KMAALERTINFO) = "+map);
   		String apiKey=propService.getString("ndms.apikey");
   		String docCode=(String)param.get("code");
   		String docNm="";

   		double lat= Double.parseDouble((String)param.get("lat"));
   		double lon=Double.parseDouble((String)param.get("lon"));

   		String distance=String.valueOf(NdmsUtils.distance(36.56850356260252, 127.25738361299256, lat, lon));

   		switch(docCode) {
   			case "111" : docNm="지진조기경보"; break;
   			case "212" : docNm="지진속보";break;
   			case "102" : docNm="지진정보";break;
   			case "103" : docNm="지진정보(재통보)";break;
   			case "104" : docNm="국외지진정보";break;
   			default : docNm="";
   		}
   		param.put("DocNm", docNm);
   		param.put("distance", distance);
   		System.out.println("distance(pre) = "+distance);
		System.out.println("map(pre) = "+param);
   		if(apiKey.equals(param.get("apikey"))) {
   			if((long)service.getKmaAlertEventIdCount(param).get("count")==0) {	//동일한 event_id가 없다.
				service.preAddKmaAlertInfo(param);
				socket.earthBroadcast(NdmsUtils.setPreEarthJson(param));
				alarmService.alarm(5);
			}
			else {			////동일한 event_id가 있다
				service.preModKmaAlertInfo(param);
			}
   		}
   	}
}
