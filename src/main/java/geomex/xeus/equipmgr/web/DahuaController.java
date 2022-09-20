package geomex.xeus.equipmgr.web;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import geomex.xeus.equipmgr.service.CctvModelService;
import geomex.xeus.equipmgr.service.CctvService;
import geomex.xeus.equipmgr.service.CctvVo;

/**
 * <pre>
 * 파일명 :  DahuaController.java
 * 설  명 :
 *
 *   Dahua CCTV 제품군의 PTZ를 제어합니다.
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2018-03-12      이주영          최초 생성
 *
 * </pre>
 *
 * @since : 2018. 03. 12.
 * @version : 1.0
 * @see
 */

@Controller
@RequestMapping("/dahua")
public class DahuaController {

	@Resource(name = "CctvService")
	private CctvService cctvSvc;

	@Resource(name = "CctvModelService")
	private CctvModelService modelSvc;

	/**
     * Dahua CCTV PTZ를 제어합니다.
     *
     * @param model
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/setPTZ.json")
    public void getSearchView(Model model, @RequestParam HashMap<String, String> map) throws Exception {

    	CctvVo cctvVo = cctvSvc.getRTSP(map);

    	HttpHost target = null;
    	CloseableHttpClient httpclient = null;
    	String ip = cctvVo.getIpAddr();
    	String ptzURL = "http://" + ip + "/cgi-bin/ptz.cgi";

    	target = new HttpHost(ip, 80, "http");
    	CredentialsProvider credsProvider = new BasicCredentialsProvider();
    	credsProvider.setCredentials(
			new AuthScope(target.getHostName(), target.getPort()),
			new UsernamePasswordCredentials("admin", "admin")
		);
    	httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

    	HttpClientContext localContext = HttpClientContext.create();
    	HttpGet httpget = new HttpGet(this.getURL(ptzURL, map));
    	System.out.println("Executing request " + httpget.getRequestLine());
    	CloseableHttpResponse response = null;

    	try {
    		response = httpclient.execute(target, httpget, localContext);
    	} catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			response.close();
    		} catch (IOException e) {}
    	}

    	if (httpclient != null) {
    		try {
    			httpclient.close();
    		} catch (IOException e) {}
    	}

    }

    /**
     * PTZ 제어용 URL을 생성합니다.
     *
     * @param camIp
     * @param map
     * @return
     */
    private String getURL(String camIp, HashMap<String, String> map){
    	StringBuilder url = new StringBuilder(camIp);

    	String action = map.get("action");
    	String code = map.get("code");

        url.append("?action=" + action).append("&channel=1").append("&code=" + code);

        if("Up".equals(code) || "Down".equals(code) || "Left".equals(code) || "Right".equals(code)){
        	url.append("&arg1=0").append("&arg2=3").append("&arg3=0").append("&arg4=0");
        }else if("ZoomWide".equals(code) || "ZoomTele".equals(code)){
        	url.append("&arg1=0").append("&arg2=3").append("&arg3=0").append("&arg4=0");
        }

        return url.toString();
    }

}
