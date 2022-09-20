package geomex.xeus.ivcp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import geomex.xeus.ivcp.util.IVCPHead;

public class IVCPSendStreamThread extends Thread {

	private Logger logger = LoggerFactory.getLogger("geomex.xeus.ivcp");
	SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
	
	private IVCPacket vo = null;

	private String IP = "";
	private int PORT = 0;
	private int cnt = 0;
	private Socket socket = null;
	private Socket testSocket = null;
	private PrintWriter pw = null;
	private BufferedReader br = null;
	private int Chk = 0;
	
	public void init(IVCPacket vo) {
		this.vo = vo;
		this.IP = vo.getIVCPConnIp();
		this.PORT = vo.getIVCPConnStreamPort();
	}

	/**
	 * 소켓을 초기화 한다.
	 * @throws IOException 
	 *
	 */
	public void initSocket() throws IOException {
		try {
			if(this.vo.getConnect()){
				this.socket = new Socket();
				this.socket.setReuseAddress(true);
				socket.connect(new InetSocketAddress(IP, PORT));
				this.pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			}
		} catch (Exception e) {
			if(!this.socket.isClosed())this.socket.close();
			this.vo.setConnect(false);
//			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!STREAM TRY. BUT FAIL");
		}
	}

	/**
	 * 패킷을 전송한다.
	 */
	private void sendPacket(String packet) {
//		System.out.println("SEND PACKET " + IP + ":" + PORT + " : " + packet);
		this.pw.print(packet);
		this.pw.flush();
	}

	private void recivePacket() throws IOException, InterruptedException {
		while (true) {
			Thread.sleep(3000);
			if (!vo.isSocketConn(this.socket) || !vo.getConnect()) {
				System.out.println(format.format(System.currentTimeMillis())+"                       DISCONNECT(STREAM)->"+IP);
				vo.setNAI(IVCPHead.getNAI().toString());
				vo.closeSocket(this.socket, this.pw, this.br);
				break;
			}
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (vo.isSocketConn(this.socket) && vo.getConnect()) {
					try {
//						Thread.sleep(1000);
						sendPacket(vo.getNAI());
						cnt++;
						System.out.println(format.format(System.currentTimeMillis())+"                       STREAM START "+cnt+" ->"+IP+"\n");
//						System.out.println("NAI = "+vo.getNAI());
						System.out.println("\n");

						recivePacket();
//				    		  break;
					} catch (Exception e) {
						System.out.println(format.format(System.currentTimeMillis())+"                       STREAM IN CATCH"+"->"+IP+"\n");						vo.setConnect(false);
						vo.closeSocket(this.socket, this.pw, this.br);
						e.printStackTrace();
					}
				} else {
					initSocket();
				}
				Thread.sleep(3000);
			}

			// recivePacket();
		} catch (Exception e) {
		} finally {

		}
	}
}
