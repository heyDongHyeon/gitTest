package geomex.xeus.dashboard.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * <pre>
 * 파일명 :  ApiController.java
 * 설  명 :
 *   클래스 설명을 쓰시오
 *
 * == 개정이력(Modification Information) ==
 * 수정일          수정자          수정내용
 * ----------      -----------     ---------------------------
 * 2016-02-01      홍길동          최초 생성
 *
 * </pre>
 *
 * @since   :  2017. 12. 13.
 * @version :  1.0
 * @see
 */
@Controller
@RequestMapping("/api")
public class ApiController {

	@Resource(name = "propService")
	private EgovPropertyService propService;


	/**
	 * 대기측정소 정보 API 입니다.
	 * @param response
	 * @param request
	 */
    @ResponseBody
    @RequestMapping(value = "/getTmLonLatByUmdName.xml", method = RequestMethod.POST)
    public void getTmLonLatByUmdName(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/xml; charset=UTF-8");
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> key = new ArrayList<String>();
        key.add("1Z9obcR8EGXo9ofkrWXb2PnzEjzj5RTd6TeYmoLdLDlgPB%2Fd1YsS5xKjP8qDwaDKNuvKmgJCYZTNXvhFwzJUFg%3D%3D");

        for(int i=0; i<key.size(); i++){
            try {

            	String url = "http://"+propService.getString("api.addr")+"/getTmLonLatByUmdName.xml?test=1";


                Enumeration<?> enu = request.getParameterNames();
                while(enu.hasMoreElements()) {
                    String name = (String)enu.nextElement();
                    if(name.equalsIgnoreCase("url") == false) {
                        url = url + "&" + name + "=" + URLEncoder.encode(request.getParameter(name), "UTF-8");
                    }
                }

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                con.setConnectTimeout(5000);
                con.setRequestMethod("GET");
                in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                sb.setLength(0);
                String line;
                while((line = in.readLine()) != null) {
                    sb.append(line);
                }
                response.getWriter().print(sb.toString());
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
            }
        }
    }

	/**
	 * 대기측정소 정보 API 입니다.
	 * @param response
	 * @param request
	 */
    @ResponseBody
    @RequestMapping(value = "/getAirStation.xml", method = RequestMethod.POST)
    public void getAirStation(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/xml; charset=UTF-8");
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> key = new ArrayList<String>();
        key.add("1Z9obcR8EGXo9ofkrWXb2PnzEjzj5RTd6TeYmoLdLDlgPB%2Fd1YsS5xKjP8qDwaDKNuvKmgJCYZTNXvhFwzJUFg%3D%3D");

        for(int i=0; i<key.size(); i++){
            try {

            	  String url = "http://"+propService.getString("api.addr")+"/getAirStation.xml?test=1";


                Enumeration<?> enu = request.getParameterNames();
                while(enu.hasMoreElements()) {
                    String name = (String)enu.nextElement();
                    if(name.equalsIgnoreCase("url") == false) {
                        url = url + "&" + name + "=" + request.getParameter(name);
                    }
                }

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                con.setConnectTimeout(5000);
                con.setRequestMethod("GET");
                in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                sb.setLength(0);
                String line;
                while((line = in.readLine()) != null) {
                    sb.append(line);
                }
                response.getWriter().print(sb.toString());
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
            }
        }
    }

    /**
     * 측정소별 대기오염 정보 API 입니다.
     *
     * @param response
     * @param request
     */
    @ResponseBody
    @RequestMapping(value = "/getAir.xml", method = RequestMethod.POST)
    public void getAir(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/xml; charset=UTF-8");
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> key = new ArrayList<String>();
        key.add("1Z9obcR8EGXo9ofkrWXb2PnzEjzj5RTd6TeYmoLdLDlgPB%2Fd1YsS5xKjP8qDwaDKNuvKmgJCYZTNXvhFwzJUFg%3D%3D");

        for(int i=0; i<key.size(); i++){
            try {
            	  String url = "http://"+propService.getString("api.addr")+"/getAir.xml?test=1";


                Enumeration<?> enu = request.getParameterNames();
                while(enu.hasMoreElements()) {
                    String name = (String)enu.nextElement();
                    if(name.equalsIgnoreCase("url") == false) {
                        url = url + "&" + name + "=" + URLEncoder.encode(request.getParameter(name), "UTF-8");
                    }
                }

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                con.setConnectTimeout(5000);
                con.setRequestMethod("GET");
                in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

                sb.setLength(0);
                String line;
                while((line = in.readLine()) != null) {
                    sb.append(line);
                }
                response.getWriter().print(sb.toString());
            } catch(Exception e) {
                //e.printStackTrace();
            } finally {
                if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
            }
        }
    }

    /**
     * 위치별 날씨정보 API 입니다.
     * @param response
     * @param request
     */
    @RequestMapping(value = "/getWeather.xml", method = RequestMethod.POST)
    public void getWeather(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/xml; charset=UTF-8");
        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();

        ArrayList<String> key = new ArrayList<String>();
        key.add("1Z9obcR8EGXo9ofkrWXb2PnzEjzj5RTd6TeYmoLdLDlgPB%2Fd1YsS5xKjP8qDwaDKNuvKmgJCYZTNXvhFwzJUFg%3D%3D");

        for(int i=0; i<key.size(); i++){
            try {
                String url = "http://"+propService.getString("api.addr")+"/getWeather.xml?test=1";

                Enumeration<?> enu = request.getParameterNames();
                while(enu.hasMoreElements()) {
                    String name = (String)enu.nextElement();
                    if(name.equalsIgnoreCase("url") == false) {
                        url = url + "&" + name + "=" + request.getParameter(name);
                    }
                }
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                con.setConnectTimeout(5000);
                con.setRequestMethod("GET");
                in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                sb.setLength(0);
                String line;
                while((line = in.readLine()) != null) {
                    sb.append(line);
                }

                response.getWriter().print(sb.toString());
            } catch(Exception e) {
//                e.printStackTrace();
            } finally {
                if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
            }
        }
    }

}
