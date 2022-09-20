package geomex.xeus.smartcity.service;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.xeus.smartcity.ExtHead;
import geomex.xeus.smartcity.Utils;

/**
 * <pre>
 * ===========================================================
 * 파일명 :  EventSocketSendService.java
 * 작성자 :  홍길동
 * 작성일 :  2018. 4. 30.
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
@Service("eventSocketSendService")
public class EventSocketSendService extends EgovAbstractServiceImpl {

    private Logger logger = LoggerFactory.getLogger("geomex.xeus.smartcity.event");

    @Resource(name = "propService")
    private EgovPropertyService propService;

    //외부로 전달한 외부시스템 접속정보
    HashMap<String, Properties> tgtInfo = new HashMap<String, Properties>();

    //Input event를 전달한 외부system cd정보
    HashMap<String, String[]> srcInfo = new HashMap<String, String[]>();

    public void sendEvent(String srcSysCd, ExtHead h, String json) throws Exception {
        String[] tgt = srcInfo.get(srcSysCd);
        if (tgt == null || tgt.length == 0) {
            logger.info("Forwarding SEND>> src.sys.cd=" + srcSysCd + " tgt.sys.cd not found! ");
            return;
        }
        //
        for (String tgtSysCd : tgt) {
            Properties p = tgtInfo.get(tgtSysCd);
            if (p.isEmpty()) {
                String str = "Forwarding SEND>> src.sys.cd=" + srcSysCd
                    + " tgt.sys.cd=" + tgtSysCd + " is empty >> " + p;
                logger.info(str);
                continue;
            }
            startEventIO(srcSysCd, h, json, tgtSysCd);
            try {
                Thread.sleep(500);
            } catch (Exception e) {}
        }
    }

    private void startEventIO(String srcSysCd, ExtHead h, String json, String tgtSysCd) {

        Runnable r = new Runnable() {

            private ExtHead header(SocketChannel sc) throws Exception {
                ByteBuffer buffer = ByteBuffer.allocate(ExtHead.LENGTH);
                buffer.clear();
                // System.out.println("====== Header ======= ");
                Utils.read(sc, buffer); // header를 읽는다. 60byte
                buffer.flip();
                return new ExtHead(buffer); // header 해석
            }

            @Override
            public void run() {
                SocketChannel sc = null;

                StringBuilder sb = new StringBuilder();
                try {
                    byte[] jsonBytes = json.getBytes("UTF-8");
                    int size = jsonBytes.length;
                    h.DATA_LEN = size;
                    //
                    ByteBuffer buffer = ByteBuffer.allocate(ExtHead.LENGTH + size);
                    h.write(buffer);
                    buffer.put(jsonBytes);
                    buffer.flip();
                    //
                    Properties p = tgtInfo.get(tgtSysCd);
                    String host = p.getProperty("host");
                    int port = Integer.parseInt(p.getProperty("port"));
                    //
                    sb.append("Forwarding SEND>> src.sys.cd=").append(srcSysCd);
                    sb.append(" tgt.sys.cd=" + tgtSysCd).append(" : ").append(p);
                    sb.append("\r\n");
                    sb.append(h.toString()).append(json).append("\r\n");
                    sc = SocketChannel.open(new InetSocketAddress(host, port));
                    sc.write(buffer);
                    //
                    //ExtHead h = header(sc);
                    //if (StringUtils.equals(h.MSG_EXCH_PTRN, "3")) {
                    //    sb.append("Forwarding ACK>>" + h.toString());
                    //}
                } catch (Exception e) {
                    sb.append("\r\n").append(ExceptionUtils.getStackTrace(e));
                    //logger.error(ExceptionUtils.getMessage(e));
                } finally {
                    logger.info("\r\n" + sb.toString());
                    try {
                        sc.close();
                    } catch (Exception e) {}
                }
            }
        };

        new Thread(r).start();
    }

    @PostConstruct
    public void initIt() throws Exception {
        String tgt_sys_cd[] = propService.getStringArray("tgt.sys.cd");
        String src_sys_cd[] = propService.getStringArray("src.sys.cd");

        logger.info("tgt.sys.cd=> " + ArrayUtils.toString(tgt_sys_cd));
        logger.info("src.sys.cd=> " + ArrayUtils.toString(src_sys_cd));
        if (src_sys_cd == null || tgt_sys_cd == null) {
            return;
        }

        //HashMap<String, Properties> tgtInfo = new HashMap<String, Properties>();
        for (String cd : tgt_sys_cd) {
            Properties p = new Properties();
            p.setProperty("host", propService.getString(cd + ".host"));
            p.setProperty("port", propService.getString(cd + ".port"));
            p.setProperty("type", propService.getString(cd + ".type"));
            tgtInfo.put(cd, p);
        }
        //logger.info(tgtInfo.toString());

        //HashMap<String, String[]> srcInfo = new HashMap<String, String[]>();
        for (String cd : src_sys_cd) {
            String src[] = propService.getStringArray(cd + ".sendto");
            srcInfo.put(cd, src);
        }
        //logger.debug(">>>>>>>>>>>>>>>  "+srcInfo);
        StringBuilder sb = new StringBuilder();
        for (String key : srcInfo.keySet()) {
            sb.append("SRC:" + key).append("\r\n");
            String tgt[] = srcInfo.get(key);
            for (String t : tgt) {
                sb.append(">>>").append(t).append(">>").append(tgtInfo.get(t));
                sb.append("\r\n");
            }
        }

        logger.debug("Forward Source-Target Information..");
        logger.info("\r\n" + sb.toString());
        //System.exit(1);
    }
}
