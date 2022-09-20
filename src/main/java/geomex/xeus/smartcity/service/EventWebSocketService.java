package geomex.xeus.smartcity.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.xeus.alarm.service.EventAlarmSocketService;
import geomex.xeus.smartcity.Utils;
import geomex.xeus.sysmgr.service.AuthGrpVo;
import geomex.xeus.sysmgr.service.AuthService;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.util.code.StrUtil;
import geomex.xeus.util.code.SystemParameter;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  EventWebSocketService.java
 * 작성자 :  홍길동
 * 작성일 :  2018. 4. 18.
 * 버전   :  1.0
 * 설명   :
 * 클래스 설명을 쓰시오
 *
 * 수정이력
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 *
 * ===========================================================
 * </pre>
 */

@Service("eventWebSocketService")
public class EventWebSocketService extends EgovAbstractServiceImpl {

    @Resource(name = "propService")
    private EgovPropertyService propService;


    @Resource(name = "crmsSysParamService")
    private CrmsSysParamService sysParamList;

	@Resource(name = "eventAlarmSocketService")
	private EventAlarmSocketService alarmService;


	@Resource(name = "authService")
	private AuthService auth;

    private List<WebSocketSession> clients = Collections.synchronizedList(new ArrayList<WebSocketSession>()); //event receivers...

    public synchronized void addClient(WebSocketSession session) {
        clients.add(session);
        try {
            notifyAll();
        } catch (Exception ex) {}
    }

    public synchronized void removeClient(WebSocketSession session) {
        clients.remove(session);
        try {
            notifyAll();
        } catch (Exception ex) {}
    }


    public void broadcast(String payload) {
        egovLogger.info("Event Broadcast>>" + payload);
        List<WebSocketSession> tgt = new ArrayList<WebSocketSession>(clients);
        int size = tgt.size();
//        System.out.println("payload = "+payload);
        for (int x = 0; x < size; x++) {
            WebSocketSession s = tgt.get(x);
            if (s != null && s.isOpen()) {

            	Map<String, Object> attr = s.getAttributes();

            	String userId = (String) attr.get("userId");

            	@SuppressWarnings("unchecked")
			//	ArrayList<AuthGrpVo> authGrpList = (ArrayList<AuthGrpVo>) attr.get("authGrp");

            //	String[] authList = authGrpList.get(0).getAuthData().split(",");

            	EventHistVo json = Utils.parseVo(payload);
                try {
                	String statEvetTypCd = json.getEvtTypCd();

                	/* 1 순위. 이벤트 타입코드 체크 */
                	if(statEvetTypCd != null && !"".equals(statEvetTypCd)){
                		if("CCTVSHER".equals(statEvetTypCd)) statEvetTypCd = "CCTVPlay";

                		boolean isContain = false;

//                		isContain = sysParamList.chkSysParam(json.getEvtTypCd(), json.getEvtNm());
                		isContain = true;

                		/* 2 순위. 특정 계정만 수신 */
                		if(isContain && json.getTargetId() != null && !"".equals(json.getTargetId()) && !"null".equals(json.getTargetId())){
                			if(json.getTargetId().equals(userId)){
                				s.sendMessage(new TextMessage(payload));
                				//alarmService.alarm();
                				break;
                			}
                		}

                		/* 3 순위. 수신권한 체크 */
                	//	for(int i=0; i<authList.length; i++){
                	//		if(statEvetTypCd.equals(authList[i])){
                	//			break;
                	//		}
                	//	}


                		if(isContain){
                			if(json.getTargetGrp() != null && !"".equals(json.getTargetGrp()) && !"null".equals(json.getTargetGrp())){

                				//String authGrpNo = authGrpList.get(0).getAuthGrpNo();

                				//if(json.getTargetGrp().contains(authGrpNo)){
                					s.sendMessage(new TextMessage(payload));
                				//}
                			}else{
                				s.sendMessage(new TextMessage(payload));
                			}

                			//alarmService.alarm();
                		}
                	}
                } catch (Exception e) {
                	e.printStackTrace();
                    try {
                        s.close();
                    } catch (Exception ea) {}
                }
            } else {
                clients.remove(s); //문제가 있는것 제거
            }
        } // for
    }


    public void broadcastByInterface(String payload) {
        egovLogger.info("Event Broadcast>>" + payload);
        List<WebSocketSession> tgt = new ArrayList<WebSocketSession>(clients);
        int size = tgt.size();

        boolean isUserTarget = false;

        for (int x = 0; x < size; x++) {
            WebSocketSession s = tgt.get(x);
            if (s != null && s.isOpen()) {

            	Map<String, Object> attr = s.getAttributes();

            	String userId = (String) attr.get("userId");
            	String authGrpId = (String) attr.get("authGrpId");

            	EventHistVo json = Utils.parseVo(payload);

                try {
                	String statEvetTypCd = json.getEvtTypCd();

                	/* 1 순위. 이벤트 타입코드 체크 */
                	if(statEvetTypCd != null && !"".equals(statEvetTypCd)){
                		if("CCTVSHER".equals(statEvetTypCd)) statEvetTypCd = "CCTVPLAY";
                		if("CCTVPREVREQ".equals(statEvetTypCd)) statEvetTypCd = "CCTVPREVRES";

                		/* 2 순위. 특정 계정만 수신 */
                		if(json.getTargetId() != null && !"".equals(json.getTargetId()) && !"null".equals(json.getTargetId())){
                			if(json.getTargetId().equals(userId)){
                				s.sendMessage(new TextMessage(payload));
                				isUserTarget = true;
                			}

                		}else{

                			/* 3 순위. 수신권한 체크 */
                			boolean isContain = false;
                			HashMap<String, String> map = new HashMap<String, String>();
                			map.put("userId", userId);
                			map.put("authData", statEvetTypCd);
                			if(auth.hasAuth(map)) isContain = true;

                			/* 4 순위. 그룹 또는 권한소유자에게 전파 */
                			if(isContain){
                				if(!isUserTarget){
                					if(json.getTargetGrp() != null && !"".equals(json.getTargetGrp()) && !"null".equals(json.getTargetGrp())){
                						if(json.getTargetGrp().contains(authGrpId)){
                							s.sendMessage(new TextMessage(payload));
                						}
                					}else{
                						s.sendMessage(new TextMessage(payload));
                					}
                				}
                			}

                		}
                	}
                } catch (Exception e) {
                	e.printStackTrace();
                    try {
                        s.close();
                    } catch (Exception ea) {}
                }
            } else {
                clients.remove(s);
            }
        }
    }


    public void earthBroadcast(String payload) {
        egovLogger.info("Event Broadcast>>" + payload);
        List<WebSocketSession> tgt = new ArrayList<WebSocketSession>(clients);
        int size = tgt.size();
        for (int x = 0; x < size; x++) {
            WebSocketSession s = tgt.get(x);
            if (s != null && s.isOpen()) {

            	Map<String, Object> attr = s.getAttributes();

            	String userId = (String) attr.get("userId");
            	System.out.println("userId = "+userId);
            	System.out.println("payLoad = "+payload);
                try {
    				s.sendMessage(new TextMessage(payload));
                } catch (Exception e) {
                	e.printStackTrace();
                    try {
                        s.close();
                    } catch (Exception ea) {}
                }
            } else {
                clients.remove(s); //문제가 있는것 제거
            }
        } // for
    }
}
