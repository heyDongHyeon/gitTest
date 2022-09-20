package geomex.xeus.proxy;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import geomex.xeus.proxy.service.TmsProxyService;
import geomex.xeus.system.annotation.NoSession;

/**
 * <pre>
 * @파일명 :  ProxyController.java
 * @작성자 :  김경호
 * @작성일 :  2017. 1. 19.
 * @설명   :
 *   클래스 설명을 쓰시오
 *
 * @수정이력
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 *
 * </pre>
 *
 * @version : 1.0
 * @see
 */

@Controller("tmsProxyController")
@RequestMapping("/tms")
public class TmsProxyController {

    private Logger logger = LoggerFactory.getLogger(TmsProxyController.class);

    @Autowired
    //private ServletContext servletContext;@Autowired
    private WebApplicationContext wac;
    
    @Autowired
	private TmsProxyService proxy;
    

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody ResponseEntity<byte[]> getImage(@RequestParam("url") String url,
                                                         @RequestParam("type") String type,
                                                         HttpServletRequest req) {
        HttpHeaders headers = new HttpHeaders();

        ByteArrayOutputStream baos = null;
        byte[] media = null;
        try {
            if (type.equalsIgnoreCase("png")) {
                headers.setContentType(MediaType.IMAGE_PNG);
            } else if (type.equalsIgnoreCase("jpg") ||
                type.equalsIgnoreCase("jpeg")) {
                headers.setContentType(MediaType.IMAGE_JPEG);
            }
            URL imgURL = null;
            if (url.contains("http://")) {
                imgURL = new URL(url);
            } else {
                imgURL = new URL("http://" + req.getServerName() + ":" + req.getServerPort() + url);
            }
            logger.debug(url);
            System.out.println(imgURL);
            BufferedImage img = ImageIO.read(imgURL);//in = servletContext.getResourceAsStream(url);
            baos = new ByteArrayOutputStream();
            ImageIO.write(img, type, baos);
            baos.flush();
            media = baos.toByteArray();
            baos.close();
        } catch (Exception e) {
            logger.debug(ExceptionUtils.getMessage(e));
        } finally {
            IOUtils.closeQuietly(baos);
        }

        if (media == null) {
            InputStream in = null;
            headers.setContentType(MediaType.IMAGE_PNG);
            try {
                in = wac.getServletContext().getResourceAsStream("/resources/img/tms_noimage.png");
                media = IOUtils.toByteArray(in);
            } catch (Exception e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            } finally {
                IOUtils.closeQuietly(in);
            }
        }

        headers.setCacheControl("no-cache");
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(media, headers, HttpStatus.OK);
        return responseEntity;
    }
    
    
    @NoSession
	@RequestMapping(value = "/dmz")
    public void dmz(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if(proxy.isDmzProxyActive()){
    		proxy.sendData(request, response, true);
    	}else{
    		proxy.sendData(request, response, false);
    		/*JSONObject result = new JSONObject();
    		result.put("result", false);
    		result.put("msg", "DMZ proxy module is not activated.");

    		response.setContentType("application/json");
        	response.getWriter().write(result.toString());*/
    	}

    }
}
