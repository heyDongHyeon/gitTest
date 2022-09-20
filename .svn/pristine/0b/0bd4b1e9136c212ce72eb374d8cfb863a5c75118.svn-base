package geomex.xeus.ndms.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import geomex.xeus.map.service.SearchService;
import geomex.xeus.smartcity.Utils;
import geomex.xeus.smartcity.service.EventHistService;
import geomex.xeus.smartcity.service.EventHistVo;
import geomex.xeus.smartcity.service.EventService;
import geomex.xeus.smartcity.service.EventWebSocketService;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.StrUtil;
import geomex.xeus.util.code.SystemParameter;

@Service("ndmsApiService")
public class NdmsApiService {


    @Resource(name = "searchService")
    private SearchService searchService;

	@Resource(name = "ndmsApiMapper")
	private NdmsApiMapper mapper;

	@Resource(name = "eventWebSocketService")
	private EventWebSocketService socket;

	@Resource(name = "eventService")
	private EventService event;

	@Resource(name = "eventHistService")
	private EventHistService eventhist;

    @Resource(name = "crmsSysParamService")
    private CrmsSysParamService sysParamList;


	public boolean addEvent(List<HashMap<Object, Object>> list, int code)  {

		int state = 1;

		try {
			for ( int i = 0; i < list.size(); i++ ) {
				HashMap<Object, Object> map = list.get(i);
				String time =NdmsUtils.timeFormar (map.get(NdmsCode.getTime(code)).toString());
				HashMap<Object, Object> cord = NdmsCode.getEventCord(code,map);

				String cordLat =cord.get("x").toString();
				String cordLon =cord.get("y").toString();
				if ( cordLat.equals("0") ) {

					HashMap<String, String> posiMap = searchService.getAddrPosi(map.get(NdmsCode.getRegion(code)).toString());

					if ( posiMap != null ) {
						cordLat = posiMap.get("lat");
						cordLon = posiMap.get("lon");
					}

				}
				String eventKey = NdmsCode.getEventKey(code, map);
				/*System.out.println("=================================");
				System.out.println(map);
				System.out.println(map.get(NdmsCode.getRegion(code)).toString());
				System.out.println(eventKey);
				System.out.println(NdmsCode.getEventNm(code));
				System.out.println(NdmsCode.getEventCntn(code));
				System.out.println("=================================");*/
				HashMap<String, String> param = new HashMap<String, String>();
		    	param.put("statEvetTypCd", 		"NDMSWARN");
		    	param.put("statMsgTypCd", 		"");
		    	param.put("outbPosNm", 			map.get(NdmsCode.getRegion(code)).toString());
		    	param.put("statEvetNm", 		NdmsCode.getEventNm(code));
		    	param.put("statEvetClrDtm", 	"");

		    	param.put("statEvetCntn", 		NdmsCode.getEventCntn(code, map));
		    	if ( code == 1 ) {
		    		//긴급구조 일 시..
		    		param.put("statEvetType", 		"사회재난");
		    	} else {
		    		param.put("statEvetType", 		"자연재난");
		    	}
		    	param.put("outbPos", 			"[{x:"+cordLon+", y:"+cordLat+"}]");
		    	param.put("x", 					cordLon);
		    	param.put("y", 					cordLat);
		    	param.put("statEvetOutbDtm", 	time);
		    	param.put("statEvetActnCntn",	"");
		    	param.put("procSt", 			"10");
		    	param.put("isTest", 			"N");
		    	param.put("uSvcOutbId", 		eventKey);
		    	param.put("statEvetActnMn", 	"");
		    	param.put("statEvetActnDtm", 	"");
		    	param.put("statEvetSvcTyp", 	"NDMS 이벤트");
		    	param.put("etcCntn", 			"");
		    	param.put("tmx", 				cordLat);
		    	param.put("tmy", 				cordLon);
		    	param.put("targetId", 				"geomex");
		    	param.put("targetGrp", 				"G00001");
		    	param.put("etcCntn", 				"{}");

		    	EventHistVo vo = Utils.parseVo(Utils.setJson(param));

		    	//임계치 검사.
		    	if ( dataChk(list.get(i), code) ){
			    	if (sysParamList.chkSysParam(NdmsCode.getEventNm(code)) ) {

			    			event.add(vo);
			    			socket.broadcast(Utils.setJson(param));
			    	} else {
			    		param.put("procSt", 			"50");
			    		param.put("statEvetActnCntn",	"상황종료");
			    		param.put("statEvetActnMn", 	"시스템");
			    		//param.put("statEvetActnDtm", 	DateUtil.getStrSec());

			    		vo = Utils.parseVo(Utils.setJson(param));

			    		eventhist.add(vo);
			    	}
		    	}
			}


		} catch (Exception e) {
			state = 0;
			e.printStackTrace();
		}


		if(state == 1){
			return true;
		}else{
			return false;
		}

	}

	public boolean add(List<HashMap<Object, Object>> list, int code)  {

		int state = 1;

		try {
			switch (code){
			case 1 :
				//소방 긴급구조 정보
				mapper.addCmDsr(list);
				break;
			case 2 :
				//기상통보 통보문
				mapper.addKmaInform(list);
				break;
			case 3 :
				//예비특보 통보문
				mapper.addKmaPreInform(list);
				break;
//			case 4 :
//				//지진 통보문
//				mapper.addKmaEarthInfm(list);
//				break;
			case 5 :
				//지자체 10분 우량
				mapper.addNemPornqt(list);
				break;
			case 6 :
				//댐 수위 10분 단위
				mapper.addHrfDmmst(list);
				break;
			case 7 :
				//aws 1시간 자료
				mapper.addKmaAws(list);
				break;
			case 8 :
				//지자체 10분 수위
				mapper.addNemPowlvl(list);
				break;
			case 9 :
				//동네예보 강수확률
				mapper.addKmaDfsShrtPop(list);
				break;
			default :
				break;
			}
		} catch (Exception e) {
			state = 0;
			System.out.println("code = "+code+"->error");
			e.printStackTrace();
		}

		if(state == 1){
			return true;
		}else{
			return false;
		}

	}
	public boolean addTest(HashMap<Object, Object> map)  {
		boolean result=false;
		try {
			mapper.addTest(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addTest DAO Error : " + e.getMessage());
		} 
		return result;
	}
	public boolean addEventTest(HashMap<Object, Object> map)  {
		boolean result=false;
		try {
			mapper.addEventTest(map);
			result=true;
		} catch (Exception e) {
			result=false;
			System.out.println("addEventTest DAO Error : " + e.getMessage());
		} 
		return result;
	}
	private boolean dataChk(HashMap<Object, Object> map, int code)  {

		boolean state = false;

		try {
			switch (code){
			case 1 :
				//소방 긴급구조 정보
				 state = true;
				break;
			case 2 :
				//기상통보 통보문
				state = true;
				break;
			case 3 :
				//예비특보 통보문
				state = true;
				break;
			case 4 :
				//지진 통보문
				state = true;
				break;
			case 5 :
				//지자체 10분 우량
				if ( map.get("obsrvalue") != null ) {
					sysParamList.chkLimitSysParam("event.minrain_limit", Double.parseDouble( map.get("obsrvalue").toString()) );
					state = true;
				} else {
					state = false;
				}
				break;
			case 6 :
				//댐 수위 10분 단위
				state = true;
				break;
			case 7 :
				//aws 1시간 자료
				if ( map.get("wvws") != null ) {
					state = sysParamList.chkLimitSysParam("event.wind_limit", Double.parseDouble( map.get("wvws").toString()) / 10 );
					if ( state ) break;
				}

				if ( map.get("wthrhm") != null ) {
					state = sysParamList.chkLimitSysParam("event.hm_limit", Double.parseDouble( map.get("wthrhm").toString()) / 10 );
					if ( state ) break;
				}

				if ( map.get("atavgta") != null ) {
					state = sysParamList.chkLimitSysParam("event.avg_limit", Double.parseDouble( map.get("atavgta").toString()) / 10 );
					if ( state ) break;
				}

				if ( map.get("wthr1hr") != null ) {
					state = sysParamList.chkLimitSysParam("event.hourrain_limit", Double.parseDouble( map.get("wthr1hr").toString()) / 10 );
					if ( state ) break;
				}

				if ( map.get("wthrday") != null ) {
					state = sysParamList.chkLimitSysParam("event.dayrain_limit", Double.parseDouble( map.get("wthrday").toString()) / 10 );
					if ( state ) break;
				}

				break;
			case 8 :
				//지자체 10분 수위
				if ( map.get("obsrvalue") != null ) {
					sysParamList.chkLimitSysParam("event.dim_limit", Double.parseDouble( map.get("obsrvalue").toString()) );
					state = true;

				} else {
					state = false;

				}
				break;
			case 9 :
				//동네예보 강수확률
				if ( map.get("qtyrsrat1") != null ) {
					sysParamList.chkLimitSysParam("event.rain_avg", Double.parseDouble( map.get("qtyrsrat1").toString()) );
					state = true;

				} else {
					state = false;

				};
				break;
			default :
				break;
			}
		} catch (Exception e) {
			state = false;
			e.printStackTrace();
		}
		return state;
	}

	public boolean addKmaAlertInfo(HashMap<Object, Object> param) {
		boolean result=true;
		try {
			System.out.println("service(new)");
			mapper.addKmaAlertInfo(param);
			System.out.println("afterservice(new)");
		} catch (Exception e) {
			result=false;
			System.out.println("addKmaAlertInfo error");
			e.printStackTrace();
		}
		return result;
	}
	public boolean preAddKmaAlertInfo(HashMap<Object, Object> param) {
		boolean result=true;
		try {
			System.out.println("service(pre)");
			mapper.preAddKmaAlertInfo(param);
			System.out.println("afterservice(pre)");
		} catch (Exception e) {
			result=false;
			System.out.println("addKmaAlertInfo error");
			e.printStackTrace();
		}
		return result;
	}

	public List<HashMap<Object, Object>> getKmaAlertInfo(HashMap<String, String> map) {
		List<HashMap<Object, Object>> result=null;
		try {
			result=mapper.getKmaAlertInfo(map);
		} catch (Exception e) {
			System.out.println("addKmaAlertInfo error");
			e.printStackTrace();
		}
		return result;
	}
	
	public List<HashMap<Object, Object>> getRealTest(HashMap<String, String> map) {
		List<HashMap<Object, Object>> result=null;
		try {
			result=mapper.getRealTest(map);
		} catch (Exception e) {
			System.out.println("getRealTest error");
			e.printStackTrace();
		}
		return result;
	}
	
	public HashMap<Object, Object> getKmaAlertEventIdCount(HashMap<Object, Object> param) {
		HashMap<Object, Object> result=null;
		try {
			result=mapper.getKmaAlertEventIdCount(param);
		} catch (Exception e) {
			System.out.println("getKmaAlertEventIdCount error");
			e.printStackTrace();
		}
		return result;
	}

	public boolean modKmaAlertInfo(HashMap<Object, Object> param) {
		boolean result=true;
		try {
			System.out.println("service111222(newMode)");
			mapper.modKmaAlertInfo(param);
			System.out.println("afterservice111222(newMod)");
		} catch (Exception e) {
			result=false;
			System.out.println("modKmaAlertInfo error");
			e.printStackTrace();
		}
		return result;
	}
	public boolean preModKmaAlertInfo(HashMap<Object, Object> param) {
		boolean result=true;
		try {
			System.out.println("service111222(preMode)");
			mapper.preModKmaAlertInfo(param);
			System.out.println("afterservice111222(preMod)");
		} catch (Exception e) {
			result=false;
			System.out.println("preModKmaAlertInfo error");
			e.printStackTrace();
		}
		return result;
	}
}
