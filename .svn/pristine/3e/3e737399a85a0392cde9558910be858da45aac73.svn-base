package geomex.xeus.lyrmgr.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import geomex.xeus.lyrmgr.service.LyrMgrService;



@Controller
@RequestMapping("/lyrmgr")
public class LyrMgrController {

    @Resource(name = "lyrMgrService")
    private LyrMgrService service;

	@RequestMapping(value = "/getLyrStyle.json", method = RequestMethod.POST)
    public void getLyrStyle(Model model, @RequestParam HashMap<String, String> map) throws Exception {
//		System.out.println(map);
		model.addAttribute("result", service.getLyrStyle(map));
    }


	@RequestMapping(value = "/editLyrStyle.json", method = RequestMethod.POST)
	public void editLyrStyle(Model model, HttpSession session, @RequestParam HashMap<String, String> map) throws Exception {

		model.addAttribute("result", service.editLyrStyle(map));


	}
}
