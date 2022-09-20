package geomex.xeus.alarm.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import geomex.xeus.alarm.service.EventAlarmSocketService;
import geomex.xeus.system.annotation.NoSession;

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
@RequestMapping("/alarm")
public class EventAlarmController {

	@Resource(name = "eventAlarmSocketService")
	private EventAlarmSocketService service;

	public static boolean posAlarm=true;
    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
//	private static boolean posAlaram;
	/**
	 * 경광등을 울린다.
	 *
	 * @param model
	 * @param map
	 * @return
	 * @throws Exception
	 */
    @NoSession
	@RequestMapping(value = "/start.json",  method = RequestMethod.POST)
    public void startAlarm(Model model, @RequestParam HashMap<String, String> map) throws Exception {
    	//System.out.println(map.get("event"));
		if(posAlarm) {
			service.alarm(0);
		}
		else {
			System.out.println(format.format(System.currentTimeMillis())+" alarming . can't another alarm\n");
		}
    	model.addAttribute("map", map);
    }
}
