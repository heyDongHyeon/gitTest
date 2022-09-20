package geomex.xeus.ndps.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import geomex.xeus.equipmgr.service.DisbordVo;
import geomex.xeus.ndms.service.NdmsCode;
import geomex.xeus.ndms.service.NdmsUtils;
import geomex.xeus.smartcity.Utils;
import geomex.xeus.smartcity.service.EventHistService;
import geomex.xeus.smartcity.service.EventHistVo;
import geomex.xeus.smartcity.service.EventService;
import geomex.xeus.smartcity.service.EventWebSocketService;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.SystemParameter;

/**
 * <pre>
 * 파일명 :  GeometryService.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2016-02-01      홍길동          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 9. 5.
 * @version :  1.0
 * @see
 */
@Service("ndpsService")
public class NdpsService extends EgovAbstractServiceImpl {

	@Resource(name = "ndpsMapper")
	private NdpsMapper mapper;

	@Resource(name = "eventWebSocketService")
	private EventWebSocketService socket;

	@Resource(name = "eventService")
	private EventService event;

	@Resource(name = "eventHistService")
	private EventHistService eventhist;

    @Resource(name = "crmsSysParamService")
    private CrmsSysParamService sysParamList;

	public ArrayList<HashMap<String, String>> getData(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getData(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getData DAO Error : " + e.getMessage());
		}
		return result;
	}
	public ArrayList<HashMap<String, String>> getAwsData(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getAwsData(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getAwsData DAO Error : " + e.getMessage());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getRealTimeData(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getRealTimeData(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getData DAO Error : " + e.getMessage());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getList(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getList(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getList DAO Error : " + e.getMessage());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getAwsList(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getAwsList(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getAwsList DAO Error : " + e.getMessage());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getEqbList(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getEqbList(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getEqbList DAO Error : " + e.getMessage());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getAllList() {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getAllList();
		} catch (Exception e) {
			result=null;
			System.out.println("getEqbList DAO Error : " + e.getMessage());
		}
		return result;
	}


	public List<HashMap<String, Object>> getStatYear(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getStatYear(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getStatYear DAO Error : " + e.getMessage());
		}
		return result;
	}
	public List<HashMap<String, Object>> getStatMonth(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getStatMonth(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getStatMonth DAO Error : " + e.getMessage());
		}
		return result;
	}
	public List<HashMap<String, Object>> getStatDay(HashMap<String, Object> map) {
		List<HashMap<String, Object>> result=null;
		try {
			result = mapper.getStatDay(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getStatDay DAO Error : " + e.getMessage());
		}
		return result;
	}


	public boolean addEvent(HashMap<String, String> map)  {

		int state = 1;

		try {

			String cordLat ="null".equals(String.valueOf((Object)map.get("lat")))? "":String.valueOf((Object)map.get("lat"));
			String cordLon ="null".equals(String.valueOf((Object)map.get("lon")))? "":String.valueOf((Object)map.get("lon"));
			String innb ="null".equals(String.valueOf((Object)map.get("innb")))? "":String.valueOf((Object)map.get("innb"));
			String dt =NdmsUtils.timeFormar(map.get("dt").toString());
			String data=String.valueOf((Object)map.get("data"));
			String addr ="null".equals(String.valueOf((Object)map.get("addr")))? "":String.valueOf((Object)map.get("addr"));
			String se =String.valueOf((Object)map.get("se"));
			String nm =String.valueOf((Object)map.get("nm"));
			int i_data = 0;
			//강수량은 데이터/10  단위 : mm
			//적설량은 데이터/10  단위 : cm    ==  데이터 (그대로) 단위 : mm
			if ( data != null && !data.equals("") ) {
				if("B03101".equals(se)) {
					i_data = (int) Math.round(Double.parseDouble(data));
					data=Integer.toString(i_data);
				}
				else {
					i_data = (int) Math.round(Double.parseDouble(data));
				}

			}

			String limitCode = sysParamList.codeLimitSysParam(getEventSysKey(se), i_data);
//			System.out.println("\nlimitCode = "+limitCode);
//			System.out.println("\n");
//			System.out.println("innb = "+innb);
//			System.out.println("limitCode = "+limitCode);
//			System.out.println("dt = "+dt);
			String eventNm = getEventNm(se, data, limitCode);//이벤트 명 구하기.
			HashMap<String, String> param = new HashMap<String, String>();
			String eventCntn = getEventCntn(se, data, nm, addr, limitCode);//이벤트 내용

	    	param.put("statEvetTypCd", 		"NDPSWARN");
	    	param.put("statMsgTypCd", 		"");
	    	param.put("outbPosNm", 			addr);
	    	param.put("statEvetNm", 		eventNm);
	    	param.put("statEvetClrDtm", 	"");

	    	param.put("statEvetCntn", 		eventCntn);
	    	param.put("statEvetType", 		"자연재난");
	    	param.put("outbPos", 			"[{x:"+cordLon+", y:"+cordLat+"}]");
	    	param.put("x", 					cordLon);
	    	param.put("y", 					cordLat);
	    	param.put("statEvetOutbDtm", 	dt);
	    	param.put("statEvetActnCntn",	"");
	    	param.put("procSt", 			"10");
	    	param.put("isTest", 			"N");
	    	param.put("uSvcOutbId", 		"TNDPS"+innb+dt+limitCode);
	    	param.put("statEvetActnMn", 	"");
	    	param.put("statEvetActnDtm", 	"");
	    	param.put("statEvetSvcTyp", 	"기상 예경보");
	    	param.put("etcCntn", 			"");
	    	param.put("tmx", 				cordLat);
	    	param.put("tmy", 				cordLon);
	    	param.put("targetId", 				"geomex");
	    	param.put("targetGrp", 				"G00001");
	    	param.put("etcCntn", 				"{}");

	    	HashMap<String, String> paramEvent = new HashMap<String, String>();
	    	paramEvent.put("key", "TNDPS"+innb+dt.substring(0, 8));
//	    	paramEvent.put("evetNm", eventNm);
	    	paramEvent.put("procSt", 	"15");
//	    	System.out.println("paramEvent = "+paramEvent);
	    	EventHistVo voChk = event.getEventChk(paramEvent);	// 같은 날짜의 이벤트가 현재 있는지..
	    	EventHistVo histVoChk = eventhist.getEventChk(paramEvent);	// 같은 날짜의 이벤트가 현재 있는지..
	    	EventHistVo vo = Utils.parseVo(Utils.setJson(param));
//	    	System.out.println("param(ㅎㅎ) = "+param);
	    	if ( !limitCode.equals("") ) {		//호우 주의보거나 경보일 때


	    		if (sysParamList.chkSysParam(eventNm) ) {	//설정 체크
	    			if ( voChk == null ) {	//현재 이벤트 list에 해당 이벤트가 있는지..
	    				event.add(vo);		//이벤트리스트에 추가.
	    				System.out.println("list table add");
	    				socket.broadcast(Utils.setJson(param));

	    			} else {
		    			param.put("uSvcOutbId", 	voChk.getUsvcOutbId().toString());
		    			param.put("procSt", 		"10");
		    			param.remove("statEvetOutbDtm");
		    			param.put("statEvetOutbDtm", 	voChk.getEvtOutbDtm().toString());
		    			vo = Utils.parseVo(Utils.setJson(param));
		    			event.edit(vo);
		    			System.out.println("list table edit");
		    			socket.broadcast(Utils.setJson(param));
	    			}

	    		} else {
	    			//여기서부터 내가 한 것

	    			if( histVoChk == null) {
	    				System.out.println("hist table add(15)");
	    				param.put("procSt", 			"15");
//				    	param.put("statEvetActnCntn",	"상황종료");
//				    	param.put("statEvetActnMn", 	"시스템");
				    	vo = Utils.parseVo(Utils.setJson(param));
	    				eventhist.add(vo);		//이벤트리스트에 추가.
	    			}else {
	    				System.out.println("hist table edit(15)");
		    			param.put("uSvcOutbId", 	histVoChk.getUsvcOutbId().toString());
		    			param.put("procSt", 		"15");
		    			param.remove("statEvetOutbDtm");
		    			vo = Utils.parseVo(Utils.setJson(param));
		    			eventhist.edit(vo);
	    			}

//	    			param.put("procSt", 			"91");
//			    	param.put("statEvetActnCntn",	"상황종료");
//			    	param.put("statEvetActnMn", 	"시스템");
//			    	param.put("statEvetActnDtm", 	DateUtil.getStrSec());
//			    	vo = Utils.parseVo(Utils.setJson(param));
//			    	event.del(param);
//			    	eventhist.add(vo);
//
//			    	socket.broadcast(Utils.setJson(param));


			    	//vo = Utils.parseVo(Utils.setJson(param));

			    	//eventhist.add(vo);

	    		}

	    	} else {
	    		if ( voChk != null ) {

    				param.put("uSvcOutbId", 	voChk.getUsvcOutbId().toString());
    				param.put("procSt", 			"91");
    		    	param.put("statEvetActnCntn",	"상황종료");
    		    	param.put("statEvetActnMn", 	"시스템");
    		    	param.put("statEvetActnDtm", 	DateUtil.getStrSec());
    		    	param.put("statEvetOutbDtm", voChk.getEvtOutbDtm());
    		    	param.put("statEvetNm", voChk.getEvtNm());
    		    	param.put("statEvetCntn", voChk.getEvtCntn());
    		    	vo = Utils.parseVo(Utils.setJson(param));
    		    	event.del(param);
    		    	eventhist.add(vo);
    		    	System.out.println("list -> hist");
    		    	socket.broadcast(Utils.setJson(param));
    			}
	    		else if(histVoChk != null ) {
	    			param.put("uSvcOutbId", 	histVoChk.getUsvcOutbId().toString());
    				param.put("procSt", 			"91");
    		    	param.put("statEvetActnCntn",	"상황종료");
    		    	param.put("statEvetActnMn", 	"시스템");
    		    	param.put("statEvetActnDtm", 	DateUtil.getStrSec());
    		    	param.put("statEvetOutbDtm", histVoChk.getEvtOutbDtm());
    		      	param.put("statEvetNm", histVoChk.getEvtNm());
    		    	param.put("statEvetCntn", histVoChk.getEvtCntn());
    		    	vo = Utils.parseVo(Utils.setJson(param));
    		    	eventhist.del(param);
    		    	eventhist.add(vo);
    		    	System.out.println("hist -> hist");
    		    	socket.broadcast(Utils.setJson(param));
	    		}

		    	//vo = Utils.parseVo(Utils.setJson(param));

		    	//eventhist.add(vo);
	    	}
	    	//임계치 검사.
		    /*if (sysParamList.chkSysParam() ) {

		    		event.add(vo);
		    		socket.broadcast(Utils.setJson(param));
		    } else {
		    	param.put("procSt", 			"50");
		    	param.put("statEvetActnCntn",	"상황종료");
		    	param.put("statEvetActnMn", 	"시스템");
		    	//param.put("statEvetActnDtm", 	DateUtil.getStrSec());

		    	vo = Utils.parseVo(Utils.setJson(param));

		    	eventhist.add(vo);
		    }*/


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

	private String getEventNm(String code, String data, String limitCode) throws Exception {
		String eventNm = "";

		switch (code) {
			case "B03101":
				eventNm = "홍수 주의보";
				if ( limitCode.equals("1")) eventNm = "홍수 경보";
				break;
			case "B03103":
				eventNm = "대설 주의보";
				if ( limitCode.equals("1")) eventNm = "대설 경보";
				break;
			case "B03105":
				eventNm = "AWS";
				break;
		}


		return eventNm;

	}

	private String getEventSysKey(String code) throws Exception {
		String eventNm = "";

		switch (code) {
		case "B03101":
			eventNm = "event.fullrain_limit";
			break;
		case "B03103":
			eventNm = "event.snow_limit";
			break;
		case "B03105":
			eventNm = "AWS";
			break;
		}


		return eventNm;

	}

	private String getEventCntn(String code, String data, String nm, String addr, String limitCode) throws Exception {
		SystemParameter sysParam = new SystemParameter(sysParamList.getList(null));
		String eventCntn= "";
		String sysData = "";
		String typeNm = "";

		switch (code) {
			case "B03101":
				sysData = sysParam.getParamMap().get("event.fullrain_limit").split(",")[0];
				typeNm = "주의보";

				if ( limitCode.equals("1") ) {
					typeNm = "경보";
					sysData = sysParam.getParamMap().get("event.fullrain_limit").split(",")[1];

				}

				eventCntn = "홍수 "+typeNm+"./단말기명 - "+nm+" /지역 - "+addr+" /내용 - /강우량이 기준 값("+sysData+"mm)을 초과하였습니다.";
				break;
			case "B03103":

				sysData = sysParam.getParamMap().get("event.snow_limit").split("0")[0];
				typeNm = "주의보";

				if ( limitCode.equals("1") ) {
					typeNm = "경보";
					sysData = sysParam.getParamMap().get("event.snow_limit").split(",")[1];
				}

				eventCntn = "대설 "+typeNm+"./단말기명 - "+nm+" /지역 - "+addr+" /내용 - /적설량이 기준 값("+sysData+"mm)을 초과하였습니다.";
				break;
			case "B03105":
				eventCntn = "AWS";
				break;
		}

		return eventCntn;

	}


	public ArrayList<HashMap<String, String>> getRainEqbList(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getRainEqbList(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getRainEqbList DAO Error : " + e.getMessage());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getTodayRainList(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getTodayRainList(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getTodayRainList DAO Error : " + e.getMessage());
		}
		return result;
	}

	public ArrayList<HashMap<String, String>> getRecentAwsList(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> result=null;
		try {
			result = mapper.getRecentAwsList(map);
		} catch (Exception e) {
			result=null;
			System.out.println("getRecentAwsList DAO Error : " + e.getMessage());
		}
		return result;
	}
}
