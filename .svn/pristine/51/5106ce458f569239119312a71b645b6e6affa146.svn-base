package geomex.xeus.websocket.web;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import geomex.xeus.smartcity.Utils;
import geomex.xeus.smartcity.service.EventHistService;
import geomex.xeus.smartcity.service.EventHistVo;
import geomex.xeus.smartcity.service.EventService;
import geomex.xeus.smartcity.service.EventWebSocketService;
import geomex.xeus.system.annotation.NoSession;
import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.StrUtil;

@Controller
@RequestMapping("/ws")
public class WebSocketController {

	@Resource(name = "eventService")
	private EventService event;

	@Resource(name = "eventHistService")
	private EventHistService hist;

	@Resource(name = "eventWebSocketService")
	private EventWebSocketService socket;

	/**
	 * 이벤트를 관리(추가, 수정, 삭제)합니다.
	 *
	 * @throws Exception
	 */
	@RequestMapping(value = {"/addEvent.json"})
	public void mngEvent(HttpServletRequest req, Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

    	EventHistVo vo = Utils.parseVo(map.get("json"));
    	boolean isStart = true;
    	if(!"10".equals(vo.getEvtProcCd())) isStart = false;

    	try {
    		/* 이벤트 시작 */
    		if(isStart){
    			event.add(vo);
    		}else{
    			/* 이벤트 수정 */
    			if("10".equals(vo.getEvtProcCd()) || "40".equals(vo.getEvtProcCd())){
    				event.edit(vo);
    			/* 이벤트 삭제 */
    			}else{
    				vo.setEvtActnUsrid((String) session.getAttribute("userId"));
    				if("50".equals(vo.getEvtProcCd())) vo.setEvtClrDtm(DateUtil.getStrSec());
    				if("90".equals(vo.getEvtProcCd())) vo.setEvtActnDtm(DateUtil.getStrSec());
    				if("91".equals(vo.getEvtProcCd())) vo.setEvtActnDtm(DateUtil.getStrSec());

    				HashMap<String, String> uSvcOutbId = new HashMap<String, String>();
    				uSvcOutbId.put("uSvcOutbId", vo.getUsvcOutbId());

    				event.del(uSvcOutbId);

    				System.out.println("==============start==============");
    				System.out.println("key  : " + vo.getUsvcOutbId() );
    				System.out.println(vo.getEvtProcCd()+" : " + map.get("json"));
    				System.out.println("==============end==============");
    				if(hist.getItem(uSvcOutbId) == null){
    					hist.add(vo);
    				}else{
    					hist.edit(vo);
    				}
    			}
    		}
    		socket.broadcast(Utils.setJson(vo));
    		model.addAttribute("result", true);
    		model.addAttribute("uSvcOutbId", vo.getUsvcOutbId());
    		model.addAttribute("statEvetNm", vo.getEvtNm());
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("error", "이벤트 저장중 문제가 발생하였습니다.");
			model.addAttribute("result", false);
		}


	}

	/**
	 * NDMS 이벤트를 관리(추가, 수정, 삭제)합니다.
	 *
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@NoSession
	@RequestMapping(value = {"/addEventByNdms.json"})
	public void addEventByNdms(String bodyJson) throws Exception {

		EventHistVo vo = null;

		boolean isQueryString = false;
		boolean parseSuccess = false;
		boolean isBody = false;



		if(isBody && bodyJson != null && !"".equals(bodyJson)){
			try {
				vo = Utils.parseVo(bodyJson);
				isQueryString = false;
				parseSuccess = true;
			} catch (Exception e) {
				isQueryString = true;
			}
		}

		if(!parseSuccess){
			try {
				vo = Utils.parseVo(bodyJson);
				isQueryString = false;
				parseSuccess = true;
			} catch (Exception e) {
				isQueryString = true;
			}
		}

		if(!parseSuccess){


		}else{

			boolean isStart = true;
			if(!"10".equals(vo.getEvtProcCd())) isStart = false;

			try {

				HashMap<String, String> uSvcOutbId = new HashMap<String, String>();
				uSvcOutbId.put("uSvcOutbId", vo.getUsvcOutbId());

				ArrayList<EventHistVo> list = event.getList(uSvcOutbId);

				/* 이벤트 시작 */
				if(isStart){
					if(list.size() == 0){
						event.add(vo);
					}else{
						//소방 Key 겹칠경우 수정처리
						event.edit(vo);
					}
				}else{
					/* 수정이지만 신규이벤트일 경우 */
					if(list.size() == 0){
						if("40".equals(vo.getEvtProcCd())){
							vo.setEvtProcCd("10");
						}
						event.add(vo);
					}else{
						/* 이벤트 수정 */
						if("40".equals(vo.getEvtProcCd())){
							event.edit(vo);
							/* 이벤트 삭제 */
						}else{
							vo.setEvtActnUsrid("geomex");
							if("50".equals(vo.getEvtProcCd())) vo.setEvtClrDtm(DateUtil.getStrSec());
							if("90".equals(vo.getEvtProcCd())) vo.setEvtActnDtm(DateUtil.getStrSec());
							if("91".equals(vo.getEvtProcCd())) vo.setEvtActnDtm(DateUtil.getStrSec());

							event.del(uSvcOutbId);

							if(hist.getItem(uSvcOutbId) == null){
								hist.add(vo);
							}else{
								hist.edit(vo);
							}
						}
					}
				}

				String eventJson = Utils.setJson(vo);
				socket.broadcastByInterface(eventJson);


			} catch (Exception e) {
				System.out.println(vo.toString());
				e.printStackTrace();
			}

		}
	}

	/**
     * CCTV Lock 상태를 전파합니다.
     *
     * @throws Exception
     */
    @RequestMapping(value = {"/lockOn.json", "/lockOff.json"})
    public void setLock(HttpServletRequest req, Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

    	HashMap<String, String> param = new HashMap<String, String>();
    	param.put("statEvetTypCd", 		map.get("statEvetTypCd"));
    	param.put("statMsgTypCd", 		map.get("statMsgTypCd"));
    	param.put("outbPosNm", 			map.get("outbPosNm"));
    	param.put("statEvetNm", 		map.get("statEvetNm"));
    	param.put("statEvetClrDtm", 	map.get("statEvetClrDtm"));
    	param.put("statEvetCntn", 		map.get("statEvetCntn"));
    	param.put("statEvetType", 		map.get("statEvetType"));
    	param.put("outbPos", 			map.get("outbPos"));
    	param.put("x", 					map.get("x"));
    	param.put("y", 					map.get("y"));
    	param.put("statEvetOutbDtm", 	map.get("statEvetOutbDtm"));
    	param.put("statEvetActnCntn",	map.get("statEvetActnCntn"));
    	param.put("procSt", 			map.get("procSt"));
    	param.put("isTest", 			map.get("isTest"));
    	param.put("uSvcOutbId", 		map.get("uSvcOutbId"));
    	param.put("statEvetActnMn", 	map.get("statEvetActnMn"));
    	param.put("statEvetActnDtm", 	map.get("statEvetActnDtm"));
    	param.put("statEvetSvcTyp", 	map.get("statEvetSvcTyp"));
    	param.put("etcCntn", 			map.get("etcCntn"));

    	param.put("tmx", 				map.get("tmx"));
    	param.put("tmy", 				map.get("tmy"));

    	for(String key : param.keySet()){
    		param.replace(key, StrUtil.chkNull(param.get(key)));
    		if("uSvcOutbId".equals(key)){
    			if("".equals(param.get("uSvcOutbId"))){
    				param.replace("uSvcOutbId", RandomStringUtils.randomAlphanumeric(15));
    			}
    		}
    	}

    	EventHistVo vo = Utils.parseVo(Utils.setJson(param));

		String[] full_url = req.getRequestURI().split("/");
    	String url = full_url[full_url.length - 1];

    	if("lockOn.json".equals(url)){
    		event.add(vo);
    	}else if("lockOff.json".equals(url)){
    		event.del(param);
    		try {
    			hist.add(vo);
			} catch (Exception e) {

			}
    		//if(hist.getItem(param) == null) hist.add(vo);
    	}

    	socket.broadcast(Utils.setJson(param));
		model.addAttribute("result", true);
		model.addAttribute("uSvcOutbId", param.get("uSvcOutbId"));
		model.addAttribute("statEvetNm", param.get("statEvetNm"));
    }

}
