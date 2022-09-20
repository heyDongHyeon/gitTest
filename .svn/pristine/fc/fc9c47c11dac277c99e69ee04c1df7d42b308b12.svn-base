package geomex.xeus.ivcp.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import geomex.xeus.ivcp.util.IVCPBody;
import geomex.xeus.ivcp.util.IVCPHead;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 IVCPacket vo = new IVCPacket();
         IVCPSendReqThread reqThread = new IVCPSendReqThread();
         IVCPSendEventThread eventThread = new IVCPSendEventThread();
         IVCPSendStreamThread streamThread = new IVCPSendStreamThread();
         IVCPCheckPortThread checkPortThread = new IVCPCheckPortThread();
         vo.setIVCPConnIp("115.93.67.45");
         vo.setIVCPConnReqPort(4204);
         vo.setIVCPConnEventPort(4205);
         vo.setIVCPConnStreamPort(4206);
         vo.setConnect(true);
         vo.setNAI(IVCPHead.getNAI().toString());

         reqThread.init(vo);
         reqThread.start();
         eventThread.init(vo);
         eventThread.start();
         streamThread.init(vo);
         streamThread.start();
         
//         checkPortThread.init(vo);
//         checkPortThread.start();
	}
}
