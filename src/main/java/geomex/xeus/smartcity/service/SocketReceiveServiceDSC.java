package geomex.xeus.smartcity.service;

import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.xeus.eventmonitor.service.EventMngListService;
import geomex.xeus.eventmonitor.service.EventMngListVo;
import geomex.xeus.log.service.IfDscLogService;
import geomex.xeus.log.service.IfDscLogVo;
import geomex.xeus.smartcity.BodyDSC;
import geomex.xeus.smartcity.Code;
import geomex.xeus.smartcity.ExtHead;
import geomex.xeus.smartcity.Head;
import geomex.xeus.smartcity.Response;
import geomex.xeus.smartcity.Utils;
import geomex.xeus.sysmgr.service.AuthService;
import geomex.xeus.user.service.UserService;
import geomex.xeus.user.service.UserVo;
import geomex.xeus.util.code.StrUtil;
import net.sf.json.util.JSONBuilder;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  SocketReceiveServiceDSC.java
 * 작성자 :  홍길동
 * 작성일 :  2018. 4. 17.
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

@Service("socketReceiveServiceDSC")
public class SocketReceiveServiceDSC extends EgovAbstractServiceImpl {
    private Logger logger = LoggerFactory.getLogger("geomex.xeus.smartcity.event");

    @Resource(name = "propService")
    private EgovPropertyService propService;

    @Resource(name = "eventWebSocketService")
    private EventWebSocketService eventWebSocketService;

    @Resource(name = "eventSocketSendService")
    private EventSocketSendService eventSocketSendService;

    @Resource(name = "ifDscLogService")
    private IfDscLogService ifDscLogService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "authService")
    private AuthService authService;

    @Resource(name = "eventHistService")
    private EventHistService eventHistService;

    @Resource(name = "eventMngListService")
    private EventMngListService  eventMngListService;


    @Resource(name = "txManager")
    PlatformTransactionManager transactionManager;

    // 이벤트 수신용 서버소켓 채널 정보
    private ServerSocketChannel ssc = null;
    private int port = 10080;

    /**
     * 이벤트 수신용 ServerSocket를 시작한다
     *
     * @throws Exception
     */
    public void startSocketServer() throws Exception {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    ssc = ServerSocketChannel.open();
                    ssc.socket().bind(new InetSocketAddress(port));
                    logger.info("SocketReceiveServiceDSC Started...>" + port);
                    while (true) {
                        // creating socket and waiting for client connection
                        SocketChannel sc = ssc.accept();
                        //
                        startEventIO(sc);
                    }
                } catch (Exception e) {
                    logger.info("SocketReceiveServiceDSC Closed...");
                } finally {
                    serverClose();
                }
            }
        };

        new Thread(r).start();
    }

    /**
     * 이벤트 수신용 ServerSocketChannel을 닫는다.
     */
    public void serverClose() {
        try {
            ssc.close();
        } catch (Exception e) {}
    }

    // Socket에 연결된 연계시스템의 데이터를 수신한다
    private void startEventIO(SocketChannel socketChannel) {
        Runnable r = new Runnable() {

            private Head header(SocketChannel sc) throws Exception {
                ByteBuffer buffer = ByteBuffer.allocate(Head.LENGTH);
                buffer.clear();
                // System.out.println("====== Header ======= ");
                Utils.read(sc, buffer); // header를 읽는다. 60byte
                buffer.flip();
                return new Head(buffer); // header 해석
            }

            private BodyDSC body(SocketChannel sc, Head h) throws Exception {
                ByteBuffer buffer = ByteBuffer.allocate(h.DATA_LEN);
                Utils.read(sc, buffer);
                buffer.flip();
                BodyDSC body = new BodyDSC(buffer);
                return body;
            }

            private void response(SocketChannel sc, Response res) throws Exception {
                ByteBuffer buffer = ByteBuffer.allocate(Response.LENGTH);
                res.write(buffer);
                buffer.flip();
                sc.write(buffer);
                buffer.clear();
            }

            @Override
            public void run() {

                try {
                    // Header 파싱
                    //Head h = header(socketChannel);
                    // Body 읽기
                    //BodyDSC body = body(socketChannel, h);
                    //logger.info("Receive>>" + h.toString() + body.toString());
                    //
                    ///////////////////////////////////////
                    //일단; 까지 읽고 원본 log 기록후... 처리..
                    byte[] packet = Utils.readAll(Channels.newInputStream(socketChannel));
                    logger.info("Receive>>" + new String(packet));
                    ByteBuffer buffer = ByteBuffer.allocate(packet.length);
                    buffer.put(packet);
                    buffer.flip();
                    Head h = new Head(buffer);
                    BodyDSC body = new BodyDSC(buffer);
                    //////////////////////////////////////
                    Response res = event(h, body);
                    //
                    if (res == null) {
                        res = new Response("1", Code.ERROR_EVENT);
                    }
                    //Response를 119긴급출동 지원 서비스 시스템에 전달한다.
                    logger.info("Response>>" + res.toString());
                    response(socketChannel, res);
                    //DSC 이벤트 로그를 DB에 기록한다.
                    insertEventLog(h, body, res);
                } catch (Exception e) {
                    logger.error(ExceptionUtils.getMessage(e));
                } finally {
                    try {
                        socketChannel.close();
                    } catch (Exception e) {}
                }
            }
        };
        // client와 데이터 송수신 처리하는 thread 시작
        new Thread(r).start();
    }

    /**
     * 사용자 유무 및 사회적약자 이벤트 처리 권한이 있는지 체크
     *
     * @param id
     * @return
     */
    private boolean validUser(String id) {
        //boolean notUser = true; //사용자가 아니다.
        //boolean noauth = true;   //DSC권한 이 없다.
        //TODO DB에서 사용자 인지여부마 및 권한을 체크한다.
        try {
            //사용자체크
            UserVo user = userService.getItem(id);
            if (user == null) return false;
            //TODO 권한체크.
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("userId", id);
            map.put("authData", "DSC"); //TODO 권한테이블 변경해야 한다.

            if (!authService.hasAuth(map)) {
                return false;
            }
        } catch (Exception e) {
            egovLogger.error(ExceptionUtils.getStackTrace(e));
        }

        return true;
    }

    //(사전정보수신)IF-DSC-001
    private Response event(Head h, BodyDSC body) {
        Response res = new Response(Code.ERROR, Code.ERROR_USER);

        HashMap<String, String> map = new HashMap<String,String>();
        map.put("evtCd","DSC");
        EventMngListVo evtMngList = null;
        try{
            evtMngList = eventMngListService.getItem(map);
        } catch(Exception e){}
        if (!StringUtils.equalsIgnoreCase(evtMngList.getUseYn(),"Y")){
            res = new Response(Code.ERROR, "미등록 서비스 요청입니다.");
            return res;
        }

        if (!StringUtils.equals(h.SND_SYS_CD, "WP1")) {
            res = new Response(Code.ERROR, Code.ERROR_INVALID_CD + " {SND_SYS_CD:" + h.SND_SYS_CD + "}");
            return res;
        }
        if (!StringUtils.equals(h.RCV_SYS_CD, Code.SYS_UCP)) {
            res = new Response(Code.ERROR, Code.ERROR_INVALID_CD + " {RCV_SYS_CD:" + h.RCV_SYS_CD + "}");
            return res;
        }

        if (validUser(body.SENDER_ID)) { //유효한 사용자 이다.
            String json = parseJson(h, body);
            eventWebSocketService.broadcast(json);
            res = new Response(Code.NORMAL, "");

            TransactionStatus txStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

            try {
                eventHistService.addTransaction(Utils.parseVo(json));
                transactionManager.commit(txStatus);
            } catch (Exception e) {
                transactionManager.rollback(txStatus);
                egovLogger.error(ExceptionUtils.getStackTrace(e));
            }
            forwarding(h, json);
        }
        return res;
    }

    private void forwarding(Head h, String json) {
        try {
            ExtHead extHead = new ExtHead("DSC", h.SND_SYS_CD, h.RCV_SYS_CD, h.SND_DTM);
            eventSocketSendService.sendEvent(h.SND_SYS_CD, extHead, json);
        } catch (Exception e) {
            egovLogger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    //
    private void insertEventLog(Head h, BodyDSC body, Response res) {
        //System.out.println(">>>>> Insert..Event.... Log....");
        //System.out.println(h.MSG_STA_DTM);
        //System.out.println(body.SENDER_ID);
        try {
            ifDscLogService.add(new IfDscLogVo(h, body, res));
        } catch (Exception e) {
            egovLogger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    private String json(Head head, BodyDSC body) {
        StringWriter writer = new StringWriter(8192);
        final JSONBuilder jsonWriter = new JSONBuilder(writer);
        jsonWriter.object();
        jsonWriter.key("EVENT_TYP_CD").value(Code.SYS_DSC);
        //
        jsonWriter.key("MSG_TYP_CD").value(head.MSG_TYP_CD);
        jsonWriter.key("STA_TYP_CD").value(head.STA_TYP_CD);
        jsonWriter.key("MSG_STA_DTM").value(head.MSG_STA_DTM);
        jsonWriter.key("SND_SYS_CD").value(head.SND_SYS_CD);
        jsonWriter.key("RCV_SYS_CD").value(head.RCV_SYS_CD);
        jsonWriter.key("SND_DTM").value(head.SND_DTM);
        //jsonWriter.key("DATA_LEN").value("DATA_LEN");
        //head end
        jsonWriter.key("SEND_NUM").value(body.SEND_NUM);
        jsonWriter.key("SVC_TYP").value(body.SVC_TYP);
        jsonWriter.key("EVT_LON").value(body.EVT_LON);
        jsonWriter.key("EVT_LAT").value(body.EVT_LAT);
        jsonWriter.key("EVT_ADDR").value(body.EVT_ADDR);
        jsonWriter.key("EVT_BJD").value(body.EVT_BJD);
        jsonWriter.key("REF_ID").value(body.REF_ID);
        jsonWriter.key("DSC_NM").value(body.DSC_NM);
        jsonWriter.key("DSC_PHONE").value(body.DSC_PHONE);
        jsonWriter.key("DSC_BIRTH").value(body.DSC_BIRTH);
        jsonWriter.key("DSC_SEX").value(body.DSC_SEX);
        jsonWriter.key("DSC_ADDR").value(body.DSC_ADDR);
        jsonWriter.key("GUARD_NM").value(body.GUARD_NM);
        jsonWriter.key("GUARD_PHONE").value(body.GUARD_PHONE);
        jsonWriter.key("EVT_DTM").value(body.EVT_DTM);
        jsonWriter.key("IMAGE").value(body.IMAGE);
        jsonWriter.key("INFO").value(body.INFO);
        jsonWriter.key("NOTE").value(body.NOTE);
        jsonWriter.key("SENDER_ID").value(body.SENDER_ID);
        //
        jsonWriter.endObject();
        writer.flush();
        return writer.toString();
    }

    private String parseDSC(BodyDSC body) {
        String result = "";

        result += "대상자 : "
            + body.DSC_NM + " (" + body.DSC_SEX + " / " + body.DSC_BIRTH + " / " + body.DSC_PHONE + " / "
            + body.DSC_ADDR + ")<br>";
        result += "보호자 : " + body.GUARD_NM + " (" + body.GUARD_PHONE + ")";

        return result;
    }

    private String parseJson(Head head, BodyDSC body) {
        String JSON = "{"
            + StrUtil.createKV("statEvetTypCd", Code.SYS_DSC)
            + StrUtil.createKV("statMsgTypCd", head.MSG_TYP_CD)

            + StrUtil.createKV("outbPosNm", body.EVT_ADDR)
            + StrUtil.createKV("statEvetNm", "사회적약자")
            + StrUtil.createKV("statEvetClrDtm", null)
            + StrUtil.createKV("statEvetCntn", this.parseDSC(body))
            + "\"outbPos\":[{" + StrUtil.createKV("x", body.EVT_LON) + StrUtil.createKV("y", body.EVT_LAT, true) + "}],"
            + StrUtil.createKV("statEvetOutbDtm", body.EVT_DTM)
            + StrUtil.createKV("statEvetActnCntn", null)
            + StrUtil.createKV("procSt", head.STA_TYP_CD)
            + StrUtil.createKV("uSvcOutbId", body.SEND_NUM)
            + "\"statEvetItem\":[{" + StrUtil.createKV("value", "1") + StrUtil.createKV("key", "accidentType", true)
            + "}],"
            + StrUtil.createKV("statEvetActnMn", null)
            + StrUtil.createKV("statEvetActnDtm", null)
            + StrUtil.createKV("statEvetId", null)
            + StrUtil.createKV("statEvetSvcTyp", null)

            + "\"etcCntn\":" + this.json(head, body)
            + "}";

        return JSON;
    }

    @PostConstruct
    public void initIt() throws Exception {
        //System.out.println("System file.encoding : " + System.getProperty("file.encoding"));
        this.port = propService.getInt("socket.dsc.port", 10080);

        try {
            // workspacePath = new File(arg0.getRealPath("/WEB-INF/config/workspace/"));
            startSocketServer();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @PreDestroy
    public void cleanUp() throws Exception {
        System.out.println("Spring Container is destroy! Customer clean up");
        serverClose();
    }
}
