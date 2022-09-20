package geomex.xeus.alarm.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.xeus.alarm.web.EventAlarmController;
import geomex.xeus.tvius.service.CrmsSysParamService;
import geomex.xeus.util.code.StrUtil;
import geomex.xeus.util.code.SystemParameter;

@Service("eventAlarmSocketService")
public class EventAlarmSocketService {

	@Resource(name = "propService")
	private EgovPropertyService propService;

    @Resource(name = "crmsSysParamService")
    private CrmsSysParamService sysParamList;

    private int VOLUME =0;//소리 0:꺼짐 1~5 소리
    private int RED_LED =0;//빨강 0:꺼짐, 1:켜짐, 2:깜빡
    private int GREEN_LED =0;//초록 0:꺼짐, 1:켜짐, 2:깜빡
    private int YELLOW_LED =0;//노랑 0:꺼짐, 1:켜짐, 2:깜빡
    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

	/**
	 * 알람을 울린다.
	 *
	 * @param count sec 단위
	 * @throws Exception
	 */
	public synchronized void alarm(int time) throws Exception {
		SystemParameter sysParam = new SystemParameter(sysParamList.getList(null));
		
		VOLUME = 0;
		RED_LED = 0;
		GREEN_LED = 0;
		YELLOW_LED = 0;

		HashMap<String, Object> map = StrUtil.strToMap( sysParam.getParamMap().get("event.bell_led"));
		if ( sysParam.getParamMap().get("event.bell_evt").equals("Y") ) VOLUME = 4;
		if ( map.get("빨강").equals("Y") ) RED_LED = 2;
		if ( map.get("노랑").equals("Y") ) GREEN_LED = 2;
		if ( map.get("초록").equals("Y") ) YELLOW_LED = 2;
		
		//스태틱 처리.
		EventAlarmController.posAlarm=false;
		System.out.println(format.format(System.currentTimeMillis())+"      alarm Start");
		sendSocket(1);
		if(time ==0) {
			Thread.sleep((Integer.parseInt(sysParam.getParamMap().get("event.bell_time")))*1000);
			System.out.println(format.format(System.currentTimeMillis())+"      alarm end(Smart CCTV)");
		}
		else {
			Thread.sleep(time*1000);
			System.out.println(format.format(System.currentTimeMillis())+"      alarm end(earth)");

		}
		sendSocket(0);
		EventAlarmController.posAlarm=true;
	}
	public String getPrintStackTrace(Exception e) {
        
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
         
        return errors.toString();
	}
	/**
	 * 경광등에 소켓을 연결하고 패킷을 보낸다.
	 *
	 * @param eventCd  0:led 소리 중지, 1:led 소리 시작, 2:led 상태 호출(미구현)
	 */
    private void sendSocket(int eventCd){

    	String ip = propService.getString("alarm.socket.ip");
    	int port = Integer.parseInt(propService.getString("alarm.socket.port"));

    	Socket socket = null;
        OutputStream os = null;
        DataOutputStream dos = null;

        InputStream is = null;
        DataInputStream dis = null;

        try {
            socket = new Socket(ip, port);
            socket.setSoTimeout(5000);

            os = socket.getOutputStream();
            dos = new DataOutputStream(os);

            is = socket.getInputStream();
            dis = new DataInputStream(is);

            byte[] sendData = new byte[10];

            switch(eventCd){
	            case 0:
	                sendData[0] = (byte)0x57;
	                sendData[1] = (byte)0x00;
	                sendData[2] = (byte)0x00;
	                sendData[3] = (byte)0x00;
	                sendData[4] = (byte)0x00;
	                sendData[5] = (byte)0x00;
	                sendData[6] = (byte)0x00;
	                sendData[7] = (byte)0x00;
	                sendData[8] = (byte)0x00;
	                sendData[9] = (byte)0x00;
	            	break;
	            case 1:
	                sendData[0] = (byte)0x57;
	                sendData[1] = (byte)0x00;
	                sendData[2] = (byte)RED_LED;
	                sendData[3] = (byte)GREEN_LED;
	                sendData[4] = (byte)YELLOW_LED;
	                sendData[5] = (byte)0x00;
	                sendData[6] = (byte)0x00;
	                sendData[7] = (byte)VOLUME;
	                sendData[8] = (byte)0x00;
	                sendData[9] = (byte)0x00;
	            	break;
	            case 2:
	                sendData[0] = (byte)0x52;
	                sendData[1] = (byte)0x00;
	                sendData[2] = (byte)0x00;
	                sendData[3] = (byte)0x00;
	                sendData[4] = (byte)0x00;
	                sendData[5] = (byte)0x00;
	                sendData[6] = (byte)0x00;
	                sendData[7] = (byte)0x00;
	                sendData[8] = (byte)0x00;
	                sendData[9] = (byte)0x00;
	            	break;
	            default :
	                sendData[0] = (byte)0x57;
	                sendData[1] = (byte)0x00;
	                sendData[2] = (byte)0x00;
	                sendData[3] = (byte)0x00;
	                sendData[4] = (byte)0x00;
	                sendData[5] = (byte)0x00;
	                sendData[6] = (byte)0x00;
	                sendData[7] = (byte)0x00;
	                sendData[8] = (byte)0x00;
	                sendData[9] = (byte)0x00;
	            	break;
            }
//            System.out.println("");
//	   		for(int i=0; i<10; i++) {
//	   			System.out.print((byte)sendData[i]+" ");
//	   		}
//	   		System.out.println("");
//	   		for(int i=0; i<10; i++) {
//	   			System.out.print((byte)sendData[i]+" ");
//	   		}
       		dos.write(sendData);
        	//System.out.println("데이터 전송");
        	dos.flush();

        	//잠시 대기.
        	Thread.sleep(1000);
        	os.close();
        	is.close();
        	dos.close();
        	dis.close();
        	socket.close();

        } catch (Exception e) {
            System.out.println("led socket >>>>>>>>>>>>>>> error\n"+format.format(System.currentTimeMillis())+getPrintStackTrace(e));
        	// System.out.println(e);
        } finally {
        	try { if( os!=null ) os.close(); } catch (IOException e) {System.out.println("1");}
	    	try { if( is!=null ) is.close(); } catch (IOException e) {System.out.println("2");}
	    	try { if( dos!=null ) dos.close(); } catch (IOException e) {System.out.println("3");}
	    	try { if( dis!=null )  dis.close(); } catch (IOException e) {System.out.println("4");}
	    	try { if( socket!=null )  socket.close(); } catch (IOException e) {System.out.println("5");}
        }

        return;
    }



}
