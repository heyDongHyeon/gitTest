package geomex.xeus.smartcity.service;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.ServletException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.xeus.log.service.IfEvtLogService;
import geomex.xeus.log.service.IfEvtLogVo;
import geomex.xeus.smartcity.ExtHead;
import geomex.xeus.smartcity.Head;
import geomex.xeus.smartcity.Utils;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  EventSocketReceiveService.java
 * 작성자 :  홍길동
 * 작성일 :  2018. 5. 3.
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

@Service("eventSocketReceiveService")
public class EventSocketReceiveService extends EgovAbstractServiceImpl {
    private Logger logger = LoggerFactory.getLogger("geomex.xeus.smartcity.event");

    @Resource(name = "propService")
    private EgovPropertyService propService;

    @Resource(name = "ifEvtLogService")
    private IfEvtLogService ifEvtLogService;

    @Resource(name = "eventWebSocketService")
    private EventWebSocketService eventWebSocketService;

    @Resource(name = "eventSocketSendService")
    private EventSocketSendService eventSocketSendService;

    // 이벤트 수신용 서버소켓 채널 정보
    private ServerSocketChannel ssc = null;
    private int port = 10060;

    // Socket에 연결된 ESE Client와 데이터를 수신한다
    private void startEventIO(SocketChannel socketChannel) {
        Runnable r = new Runnable() {

            private ExtHead header(SocketChannel sc) throws Exception {
                ByteBuffer buffer = ByteBuffer.allocate(ExtHead.LENGTH);
                buffer.clear();
                // System.out.println("====== Header ======= ");
                Utils.read(sc, buffer); // header를 읽는다. 60byte
                buffer.flip();
                return new ExtHead(buffer); // header 해석
            }

            private String body(SocketChannel sc, ExtHead h) throws Exception {
                ByteBuffer buffer = ByteBuffer.allocate(h.DATA_LEN);
                Utils.read(sc, buffer);
                buffer.flip();
                String body = Utils.readString(buffer, h.DATA_LEN);
                return body;
            }

            private void ack(SocketChannel sc, ExtHead h) throws Exception {
                ExtHead ack = (ExtHead) h.clone();
                ack.MSG_EXCH_PTRN = String.format("%1s", 3);
                ack.DATA_LEN = 0;
                ByteBuffer buffer = ByteBuffer.allocate(ExtHead.LENGTH);
                ack.write(buffer);
                buffer.flip();
                sc.write(buffer);
                buffer.clear();
            }

            @Override
            public void run() {

                String error = "";
                ExtHead h = new ExtHead();
                String body = "";
                try {
                    // Header 파싱
                    h = header(socketChannel);
                    // Body 읽기
                    body = body(socketChannel, h);
                    logger.info("Event Receive>>" + h.toString() + body);
                    //
                    // ACK전송
                    //h.MSG_EXCH_PTRN = "3";
                    //ack(socketChannel, h);
                    // 로그 기록
                    //logger.info("Event ACK>>" + h.toString());
                    // Body 메시지를 WebSocket으로 전송한다.
                    //
                    eventWebSocketService.broadcast(body);

                    //
                    forwarding(h, body);
                    logger.info("Event WebSocket>>" + h.toString());
                    //
                } catch (Exception e) {
                    error = ExceptionUtils.getMessage(e);
                    logger.error(ExceptionUtils.getStackTrace(e));
                } finally {
                    try {
                        socketChannel.close();
                    } catch (Exception e) {}
                    insertEventLog(h, body, error);
                }
            }
        };
        // client와 데이터 송수신 처리하는 thread 시작
        new Thread(r).start();
    }

    private void forwarding(ExtHead h, String json) {
        try {
            eventSocketSendService.sendEvent(h.SND_SYS_CD, h, json);
        } catch (Exception e) {
            egovLogger.error(ExceptionUtils.getStackTrace(e));
        }
    }

    //
    private void insertEventLog(ExtHead h, String json, String err) {
        try {
            ifEvtLogService.add(new IfEvtLogVo(h, json, err));
        } catch (Exception e) {
            egovLogger.error(ExceptionUtils.getStackTrace(e));
        }
    }

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
                    logger.info("EventReceiveServer Started...");

                    while (true) {
                        SocketChannel sc = ssc.accept();
                        startEventIO(sc);
                    }

                } catch (Exception e) {
                    logger.info("EventReceiveServer Closed...");
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

    @PostConstruct
    public void initIt() throws Exception {
        this.port = propService.getInt("socket.evt.port", 10060);

        try {
            startSocketServer();

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @PreDestroy
    public void cleanUp() throws Exception {
        egovLogger.info("Spring Container is destroy! EventSocketReceiveService clean up");
        serverClose();
    }
}
