package geomex.xeus.ivcp.service;


import java.net.Socket;


public class IVCPCheckPortThread extends Thread {



	private IVCPacket vo = null;
	private String IP = "";
	private int reqPORT = 0;
	private int eventPORT = 0;
	private int streamPORT = 0;
	private Socket reqSocket = null;
	private Socket eventSocket = null;
	private Socket streamSocket = null;

	public void init(IVCPacket vo) {
		this.vo = vo;
		this.IP = vo.getIVCPConnIp();
		this.reqPORT = vo.getIVCPConnReqPort();
		this.eventPORT = vo.getIVCPConnEventPort();
		this.streamPORT = vo.getIVCPConnStreamPort();
	}

	public void checkReq() {
		try {
			reqSocket = new Socket(IP, reqPORT);
			vo.setReqConnect(true);
		} catch (Exception e) {
			vo.setReqConnect(false);
//			System.out.println("REQ DISCONNECT(check)");
		} finally {
			try {
				reqSocket.close();
			} catch (Exception e) {
			}
		}
	}

	public void checkEvent() {
		try {
			eventSocket=new Socket(IP, eventPORT);
			vo.setEventConnect(true);
		} catch (Exception e) {
			vo.setEventConnect(false);
//			System.out.println("EVENT DISCONNECT(check)");
		}finally {
			try {
				eventSocket.close();
			} catch (Exception e) {}
		}
	}
	public void checkStream() {
		try {
			streamSocket = new Socket(IP, streamPORT);
			vo.setStreamConnect(true);
		} catch (Exception e) {
			vo.setStreamConnect(false);
//			System.out.println("STREAM DISCONNECT(check)");
		} finally {
			try {
				streamSocket.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void run() {
		try {

			while (true) {
				Thread.sleep(3000);
				checkReq();
				checkEvent();
				checkStream();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
