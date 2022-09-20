package geomex.xeus.stat.web;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import geomex.xeus.map.service.EmdVo;
import geomex.xeus.map.service.SearchService;
import geomex.xeus.ndps.service.NdpsService;
import geomex.xeus.stat.service.StatEventService;
import geomex.xeus.stat.service.StatNdmsService;
import geomex.xeus.util.code.DateUtil;



@Controller
@RequestMapping("/stat")
public class StatController {

    @Resource(name = "statNdmsService")
   	private StatNdmsService ndms;

    @Resource(name = "statEventService")
   	private StatEventService event;

    //이기종 DB 이기에 해당 서비스를 빈으로 사용...^^
    @Resource(name = "ndpsService")
    private NdpsService ndps;
    
    @Resource(name = "searchService")
	private SearchService bjd;



    @Resource
    private Validator validator;

    List<HashMap<String, String>> resultList=null;
    HashMap<String, String> resultObj=null;
    boolean resultBol=false;
    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(this.validator);
        binder.registerCustomEditor(MultipartFile.class, new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) {
                //System.out.println("initBinder MultipartFile.class: {}; set null; " + text);
                setValue(null);
            }
        });
    }
    /**
     * 모든 연도 별 NDMS통계를 보냅니다.
     * @param type, ctgory(aws만), emd
     * @return jsp
     * @throws Exception
     */
	@RequestMapping(value = "/getNdmsStatYear.json")
    public void getNdmsStatYear(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		List<HashMap<String,Object>> resultList=null;


		//emd가 null 이면 ""로 바꾼다.
		if(map.get("emd")==null){
			map.put("emd", "");
		}
		//yearList를 map에다 넣어준다.
		//year통계 데이터를 가져온다.
		String type=(String)map.get("type");

		map.put("datList",  DateUtil.getYearMapList("2018"));
		if("".equals((String)map.get("emd"))) {
			map.put("allEmd", "전체");
		}

		if("dsr".equals(type)) {
			map.put("col", "건수");
		}
		else {
			map.put("col", "평균,최대,최소");
		}
		resultList=ndms.getNdmsYear(map);
	
//		System.out.println("result(ndms year) = "+resultList);
		model.addAttribute("result", resultList);
		model.addAttribute("datList", DateUtil.getYearMapList("2018"));
    }
	/**
     * 검색한 년도에 대한 월별 NDMS통계를 보냅니다.
     * @param type, ctgory(aws만), time, emd
     * @return jsp
     * @throws Exception
     */
	@RequestMapping(value = "/getNdmsStatMonth.json")
    public void getNdmsStatMonth(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		List<HashMap<String,Object>> resultList=null;
		//emd가 null 이면 ""로 바꾼다.
		if(map.get("emd")==null){
			map.put("emd", "");
		}
		//시간에서 년도를 뺀다.
		String year=map.get("time").toString().substring(0,4);
		map.put("year", year);
		//1~12 month list를 만든다.
		map.put("datList", DateUtil.getMonthMapList());
		//month통계를 가져온다.
		String type=(String)map.get("type");
		if("".equals((String)map.get("emd"))) {
			map.put("allEmd", "전체");
		}
		
		if("dsr".equals(type)) {
			map.put("col", "건수");
		}
		else {
			map.put("col", "평균,최대,최소");
		}
//		System.out.println("map(ndms month) ㅎㅎ = "+map);
		resultList=ndms.getNdmsMonth(map);
		model.addAttribute("datList", DateUtil.getMonthMapList());
		model.addAttribute("result", resultList);
//		System.out.println("result(ndms month) = "+resultList);
    }
	  /**
     * 검색한 년도에 대한 일별 NDMS통계를 보냅니다.
     * @param map->type, ctgory(항목..aws만),time,emd
     * @return jsp
     * @throws Exception
     */
	@RequestMapping(value = "/getNdmsStatDay.json")
    public void getNdmsStatDay(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		List<HashMap<String,Object>> resultList=null;

		//emd가 null 이면 ""로 바꾼다.
		if(map.get("emd")==null){
			map.put("emd", "");
		}
		//시간에서 년도를 뺀다.
		String year=map.get("time").toString().substring(0,4);
		map.put("year", year);
		//시간에서 월을 뺀다.
		String month=map.get("time").toString().substring(4,6);
		map.put("month", month);
		// day list를 만든다.
		map.put("datList", DateUtil.getDayMapList(year, month));
		if("".equals((String)map.get("emd"))) {
			map.put("allEmd", "전체");
		}
//		System.out.println("map(ndms day aws) ㅎㅎ = "+map);
		String type=(String)map.get("type");

		if("dsr".equals(type)) {
			map.put("col", "건수");
		}
		else {
			map.put("col", "평균,최대,최소");
		}
		resultList=ndms.getNdmsDay(map);

		model.addAttribute("datList", DateUtil.getDayMapList(year, month));
		model.addAttribute("result", resultList);
//		System.out.println("result(map days aws) ㅎㅎ = "+resultList);
    }

	/**
	 * 모든 연도 별 NDMS통계를 보냅니다.
	 * @param type, ctgory(aws만), emd
	 * @return jsp
	 * @throws Exception
	 */
	@RequestMapping(value = "/getNdpsStatYear.json")
	public void getNdpsStatYear(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		List<HashMap<String,Object>> resultList=null;


		//emd가 null 이면 ""로 바꾼다.
		if(map.get("emd")==null){
			map.put("emd", "");
		}
		//yearList를 map에다 넣어준다.
		//year통계 데이터를 가져온다.

		map.put("datList",  DateUtil.getYearMapList("2018"));
//		System.out.println("map(ndpsstatyaer)1 = "+map);
//		System.out.println("result = "+ndps.getStatYear(map));
		model.addAttribute("result", ndps.getStatYear(map));
		model.addAttribute("datList", DateUtil.getYearMapList("2018"));
	}
	/**
	 * 검색한 년도에 대한 월별 NDMS통계를 보냅니다.
	 * @param type, ctgory(aws만), time, emd
	 * @return jsp
	 * @throws Exception
	 */
	@RequestMapping(value = "/getNdpsStatMonth.json")
	public void getNdpsStatMonth(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		List<HashMap<String,Object>> resultList=null;
		List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		//emd가 null 이면 ""로 바꾼다.
		if(map.get("emd")==null){
			map.put("emd", "");
		}
		//시간에서 년도를 뺀다.
		String year=map.get("time").toString().substring(0,4);
		map.put("year", year);
		//1~12 month list를 만든다.
		for(int i=1; i<=12; i++) {
			HashMap<String, Object> temp=new HashMap<String,Object>();
			if(i<10) {
				temp.put("month", "0"+Integer.toString(i));
			}
			else {
				temp.put("month", Integer.toString(i));
			}
			list.add(temp);
		}
		map.put("datList", list);
		//month통계를 가져온다.
//		System.out.println(list);
//		System.out.println("map(ndpsstat month) = "+map);
		model.addAttribute("datList", list);
//		System.out.println(map);
		model.addAttribute("result", ndps.getStatMonth(map));
	}
	/**
	 * 검색한 년도에 대한 일별 NDMS통계를 보냅니다.
	 * @param map->type, ctgory(항목..aws만),time,emd
	 * @return jsp
	 * @throws Exception
	 */
	@RequestMapping(value = "/getNdpsStatDay.json")
	public void getNdpsStatDay(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,Object>> resultList=null;

		//emd가 null 이면 ""로 바꾼다.
		if(map.get("emd")==null){
			map.put("emd", "");
		}
		//시간에서 년도를 뺀다.
		String year=map.get("time").toString().substring(0,4);
		map.put("year", year);
		//시간에서 월을 뺀다.
		String month=map.get("time").toString().substring(4,6);
		map.put("month", month);
		// day list를 만든다.
		int length=DateUtil.LastDay(Integer.parseInt(year), Integer.parseInt(month));
		for(int i=1; i<=length; i++) {
			HashMap<String, Object> temp=new HashMap<String,Object>();
			if(i<10) {
				temp.put("day", "0"+Integer.toString(i));
			}
			else {
				temp.put("day", Integer.toString(i));
			}
			list.add(temp);
		}
//		System.out.println(list);
		map.put("datList", list);
//		System.out.println("map(ndpsstat day) = "+map);
		model.addAttribute("result", ndps.getStatDay(map));

		model.addAttribute("datList", list);
		//model.addAttribute("result", resultList);
	}
	 /**
     * 모든 연도 별 이벤트통계를 보냅니다.
     * @param map->bet("1"->evt_svc_nm "2"->evt_typ), bec, sec, time
     * @return jsp
     * @throws Exception
     */
	@RequestMapping(value = "/getEventStatYear.json")
    public void getEventStatYear(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		
		List<HashMap<String,Object>> resultList=null;
		List<HashMap<String,Object>> yearList=null;
		List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();

		String type=(String)map.get("type");

		//evt_svc_nm일 때
		if(map.get("bet").toString().equals("1")){
			map.put("evt", "evt_svc_nm");
			if(map.get("bec")==null || map.get("bec").toString().equals("")){
				map.put("bec", "");	//bec가 null 이면 ""로 바꾼다.
				map.put("sec", "");
				map.put("daso", "evt_svc_nm");	//대분류로 group by를 한다.
			}
			else{
				map.put("daso", "evt_nm");	//소분류로 group by를 한다.
			}
		}
		//evt_typ일 때
		else {
			if(map.get("bec")==null || map.get("bec").toString().equals("")) {
				map.put("bec", "");
				
			}
			map.put("sec", "");
			map.put("evt", "evt_typ");
			map.put("daso", "evt_typ");
		}
		//모든 연도 가져오기
		map.put("datList", DateUtil.getYearMapList("2018"));
		model.addAttribute("datList", DateUtil.getYearMapList("2018"));
//		System.out.println("map(year event) = "+map);
		resultList=event.getEventYear(map);
		model.addAttribute("result", resultList);
//		System.out.println("result(year event) = "+resultList);
    }
	 /**
     * 모든  월 별 이벤트통계를 보냅니다.
     * @param map->bet("1"->evt_svc_nm "2"->evt_typ), bec, sec, time
     * @return jsp
     * @throws Exception
     */
	@RequestMapping(value = "/getEventStatMonth.json")
    public void getEventStatMonth(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		List<HashMap<String,Object>> resultList=null;
		List<HashMap<String,Object>> yearList=null;
		List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		String year=map.get("time").toString().substring(0,4);
		map.put("year", year);
		//시간에서 월을 뺀다.
		String type=(String)map.get("type");
		//evt_svc_nm일 때
		if(map.get("bet").toString().equals("1")){
			map.put("evt", "evt_svc_nm");
			//대분류를 선택을 안했을 때
			if(map.get("bec")==null || map.get("bec").toString().equals("")){
				map.put("bec", "");
				map.put("sec", "");
				map.put("daso", "evt_svc_nm");	//대분류로 group by를 한다.
			}
			//대분류를 선택했을 떄
			else{
				map.put("daso", "evt_nm");	//소분류로 group by를 한다.
			}
		}
		//evt_typ일 때
		else {
			if(map.get("bec")==null || map.get("bec").toString().equals("")){
				map.put("bec", "");
			}
			map.put("sec", "");
			map.put("daso", "evt_typ");
			map.put("evt", "evt_typ");
		}
		//시간에서 년도를 뺀다.
		//1~12 month list를 만든다.
		for(int i=1; i<=12; i++) {
			HashMap<String, Object> temp=new HashMap<String,Object>();
			if ( i < 10 ) {
				temp.put("month", "0"+i);
			} else {
				temp.put("month", i);
			}
			list.add(temp);
		}
		map.put("datList", list);
		model.addAttribute("datList", list);
//		System.out.println("map(event month) = "+map);
		resultList=event.getEventMonth(map);
		model.addAttribute("result", resultList);
//		System.out.println("result(event month) = "+resultList);
    }
	 /**
     * 모든  일 별 이벤트통계를 보냅니다.
     * @param map->bet("1"->evt_svc_nm "2"->evt_typ), bec, sec, time
     * @return jsp
     * @throws Exception
     */
	@RequestMapping(value = "/getEventStatDay.json")
    public void getEventStatDay(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		List<HashMap<String,Object>> resultList=null;
		List<HashMap<String,Object>> yearList=null;
		List<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();

		String type=(String)map.get("type");
		//evt_svc_nm일 때
		if(map.get("bet").toString().equals("1")){
			map.put("evt", "evt_svc_nm");
			if(map.get("bec")==null || map.get("bec").toString().equals("")){
				map.put("bec", "");	//bec가 null 이면 ""로 바꾼다.
				map.put("sec", "");
				map.put("daso", "evt_svc_nm");	//대분류로 group by를 한다.
			}
			else{
				map.put("daso", "evt_nm");	//소분류로 group by를 한다.
			}
		}
		//evt_typ일 때
		else {
			if(map.get("bec")==null || map.get("bec").toString().equals("")){
				map.put("bec", "");
			}
			map.put("sec", "");
			map.put("daso", "evt_typ");
			map.put("evt", "evt_typ");
		}
		//시간에서 년도를 뺀다.
		String year=map.get("time").toString().substring(0,4);
		map.put("year", year);
		//시간에서 월을 뺀다.
		String month=map.get("time").toString().substring(4,6);
		map.put("month", month);
		// day list를 만든다.
		int length=DateUtil.LastDay(Integer.parseInt(year), Integer.parseInt(month));
		for(int i=1; i<=length; i++) {
			HashMap<String, Object> temp=new HashMap<String,Object>();
			if ( i < 10 ) {
				temp.put("day", "0"+i);
			} else {
				temp.put("day", i);


			}
//			System.out.println(i);
			list.add(temp);
		}
		map.put("datList", list);
		model.addAttribute("datList", list);
//		System.out.println("map(event day) = "+map);
		resultList=event.getEventDay(map);
		model.addAttribute("result", resultList);
//		System.out.println("result(event day) = "+resultList);
    }



	/**
     * 로그를 엑셀 형식으로 내보냅니다.
     *
     * @param model
     * @param map
     * @throws Exception
     */
    @RequestMapping(value = "/getExcel.do", method = RequestMethod.POST)
    public String getExcel(Model model, @RequestParam HashMap<String, Object> map) throws Exception {

        String view = "/stat/xls/xlsDoc";
//        System.out.println(map.get("body"));
        model.addAttribute("body", map.get("body"));
        model.addAttribute("fileNm", map.get("exceltitle")+DateUtil.getStrSec());

        return view;

    }
}
