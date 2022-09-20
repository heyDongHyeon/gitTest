package geomex.xeus.scheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.rte.fdl.property.EgovPropertyService;
import geomex.xeus.map.service.EmdVo;
import geomex.xeus.map.service.SearchService;
import geomex.xeus.ndms.service.NdmsApiService;
import geomex.xeus.ndms.service.NdmsCode;
import geomex.xeus.ndms.service.NdmsUtils;
import geomex.xeus.ndps.service.NdpsService;
import geomex.xeus.smartcity.service.EventWebSocketService;
import geomex.xeus.stat.service.StatNdmsService;
import geomex.xeus.util.code.DateUtil;




@Service
public class SchedulerWorker   {

	@Resource(name="ndmsApiService")
	private NdmsApiService service;

	@Resource(name="ndpsService")
	private NdpsService ndpsService;

	@Resource(name = "eventWebSocketService")
	private EventWebSocketService socket;

	@Resource(name = "propService")
	private EgovPropertyService propService;

    @Resource(name = "searchService")
	private SearchService bjd;

    @Resource(name = "statNdmsService")
   	private StatNdmsService ndms;

    SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");


	/**
     * NDMS 실시간 이벤트 수집.
     *
     * @param str
     * @return
     */
    public void asyncNdmsEvent() {
        // LOG : 시작 입력

		//System.out.println("==============>>>>>>>>>>>> NDMS EVENT ASYNC START");

//    	int[] keyArr = {1,2,3,5,6,7,8,9};
    	int[] keyArr = {1};

    	if ( propService.getString("ndms.event.use").equals("Y") ) {

	    	for(int i=0; i<keyArr.length; i++){
	    		String url = NdmsCode.getUrl(keyArr[i]);
//	    		System.out.println("url  "+i+"  = "+url);
	    		List<HashMap<Object, Object>> list = NdmsUtils.getNdmsData(url, keyArr[i]);
	    		for ( int j = 0; j < list.size(); j++ ) {
	    			HashMap<Object, Object> map = list.get(j);
	    			String time =NdmsUtils.timeFormar (map.get(NdmsCode.getTime(keyArr[i])).toString());
	    			if ( time.length() >= 8) {
	    				list.get(j).put("datyy", time.substring(0, 4));
	    				list.get(j).put("datmm", time.substring(4, 6));
	    				list.get(j).put("datdd", time.substring(6, 8));

	    			} else {
	    				list.remove(j);
	    			}
	    		}
//	    		System.out.println("list  "+i+"  = "+list);
	    		this.service.add(list, keyArr[i]);

	    		//service.addEvent(list, keyArr[i]);

	    	}
    	}
		//System.out.println("==============>>>>>>>>>>>> NDMS EVENT ASYNC END");
    }
    public void asyncNdmsEventTest() {
    	System.out.println("start!");
        // LOG : 시작 입력

		//System.out.println("==============>>>>>>>>>>>> NDMS EVENT ASYNC START");

//    	int[] keyArr = {1,2,3,4,5,6,7,8,9};
//
//    	if ( propService.getString("ndms.event.use").equals("Y") ) {
//
//	    	for(int i=0; i<keyArr.length; i++){
//	    		String url = NdmsCode.getUrl(keyArr[i]);
//	    		System.out.println("url  "+i+"  = "+url);
//	    		List<HashMap<Object, Object>> list = NdmsUtils.getNdmsData(url, keyArr[i]);
//	    		for ( int j = 0; j < list.size(); j++ ) {
//	    			HashMap<Object, Object> map = list.get(j);
//	    			String time =NdmsUtils.timeFormar (map.get(NdmsCode.getTime(keyArr[i])).toString());
//	    			if ( time.length() >= 8) {
//	    				list.get(j).put("datyy", time.substring(0, 4));
//	    				list.get(j).put("datmm", time.substring(4, 6));
//	    				list.get(j).put("datdd", time.substring(6, 8));
//
//	    			} else {
//	    				list.remove(j);
//	    			}
//	    		}
//	    		System.out.println("list  "+i+"  = "+list);
//	    		this.service.add(list, keyArr[i]);
//
//	    		//service.addEvent(list, keyArr[i]);
//
//	    	}
//    	}
		//System.out.println("==============>>>>>>>>>>>> NDMS EVENT ASYNC END");
    	List<HashMap<Object, Object>> list = new ArrayList<HashMap<Object, Object>>();
    	HashMap<Object,Object> a=new HashMap<Object,Object>();
    	HashMap<Object,Object> b=new HashMap<Object,Object>();
    	a.put("dttmfc", "201908281111");
    	a.put("locloc", "위치위치에이");
    	a.put("sectscle", "5.0");
    	a.put("statother", "에이에이");
    	a.put("datyy", 2019);
    	a.put("datmm", 8);
    	a.put("datdd", 30);
    	a.put("depth", "10");
    	a.put("cordlat", "1");
    	a.put("cordlon", "2");
    	a.put("intensitydesc", "aaaaaaaaaaaa!!!!!!!!!!!");
    	a.put("intensitypageuri", "bbbbbbbbbbbbbbbbbbbb!!!");
    	a.put("intensitylocationuri", "ccccccccccccccccccccc!!!");
    	a.put("cdstn", 123);
    	a.put("noord", 456);
    	a.put("noref", 789);

    	b.put("dttmfc", "201908281111");
    	b.put("locloc", "위치위치비");
    	b.put("sectscle", "5.0");
    	b.put("statother", "비비");
    	b.put("datyy", 2019);
    	b.put("datmm", 8);
    	b.put("datdd", 30);
    	b.put("depth", "2545");
    	b.put("cordlat", "3");
    	b.put("cordlon", "4");
    	b.put("intensitydesc", "dddddddddddddd@@");
    	b.put("intensitypageuri", "eeeeeeeeeeee@@");
    	b.put("intensitylocationuri", "ffffffffffffffffffffff@@");
       	b.put("cdstn", 222);
    	b.put("noord", 333);
    	b.put("noref", 444);

    	list.add(a);
    	list.add(b);
    	System.out.println("list = "+list);
    	this.service.add(list, 4);
    	System.out.println("end!");

    }

    /**
     * NDPS 실시간 이벤트 수집.
     *
     * @param str
     * @return
     * @throws InterruptedException
     */
	public void asyncNdpsEvent() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dat", DateUtil.getStrDay("-"));
		List<HashMap<String, String>> list = ndpsService.getRealTimeData(map);
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> ndpsMap = list.get(i);
			String data=String.valueOf((Object)ndpsMap.get("data"));
			int i_data = (int) Math.round(Double.parseDouble(data));
			if(i_data!=0) {
				ndpsService.addEvent(ndpsMap);
			}
		}
	}

    public void ndmsAddTest() {
        // LOG : 시작 입력

		//System.out.println("==============>>>>>>>>>>>> NDMS EVENT ASYNC START");
    	String time=format.format(System.currentTimeMillis());
    	System.out.println("ndms이벤트추가 = "+time);
    	String year=time.substring(0, 4);
		String month=time.substring(5, 7);
		String day=time.substring(8, 10);
//    	int[] keyArr = {1,2,3,4,5,6,7,8,9};
		Random random=new Random();
		String [] area = {"세종특별자치시 연기면 세종리", "세종특별자치시 전의면 읍내리", "세종특별자치시 연서면 봉암리", "세종특별자치시 금남면 성덕리", "세종특별자치시 고운동",
				"세종특별자치시 연서면 쌍전리", "세종특별자치시 금남면 용포리"
		};
		String [] table = {"ndms_kma_aws","ndms_nem_powlvl"};
		String [] ctgory = {"at_avg_ta", "wthr_hm", "wv_ws"};
		String [] yearList = {"2018", "2019"};
    	if ( propService.getString("ndms.event.use").equals("Y") ) {

//	    	for(int i=0; i<keyArr.length; i++){
//	    		String url = NdmsCode.getUrl(keyArr[i]);
//	    		System.out.println("url = "+url);
//	    		List<HashMap<Object, Object>> list = NdmsUtils.getNdmsData(url, keyArr[i]);
//	    		List<HashMap<Object, Object>> list = new ArrayList<HashMap<Object, Object>>();
//	    		for ( int j = 0; j < list.size(); j++ ) {
//	    			HashMap<Object, Object> map = list.get(j);
//	    			String time =NdmsUtils.timeFormar (map.get(NdmsCode.getTime(keyArr[i])).toString());
//	    			if ( time.length() >= 8) {
//	    				list.get(j).put("datyy", time.substring(0, 4));
//	    				list.get(j).put("datmm", time.substring(4, 6));
//	    				list.get(j).put("datdd", time.substring(6, 8));
//
//	    			} else {
//	    				list.remove(j);
//	    			}
//
//	    		}
	    		HashMap<Object, Object> list = new HashMap<Object, Object>();
				list.put("datYy", year);
				list.put("datMm", month);
				list.put("datDd", day);

				int num=random.nextInt(4);
//				int num=2;
				if(num==0) {
					list.put("table","xeus.ndms_kma_aws");
					list.put("ctgory",ctgory[random.nextInt(3)]);
					list.put("emdType","userdefineareanm");
				}
				else if(num==1) {
					list.put("table","xeus.ndms_nem_powlvl");
					list.put("ctgory","obsr_value");
					list.put("emdType","full_area_nm");
				}
				else if(num==2) {
					list.put("table","xeus.ndms_hrf_dmmst");
					list.put("ctgory","tototf");
					list.put("emdType","damnm");
				}
				else if(num==3) {
					list.put("table","xeus.ndms_cm_dsr");
					list.put("ctgory","dsr_zip_seq");
					list.put("emdType","userdefineareanm");
				}
				list.put("realValue",(Math.random()*70+30));
				list.put("emdValue",area[random.nextInt(7)]);
				list.put("dataTp","Y");

//				System.out.println("map(please) = "+list);
	    		boolean please=this.service.addTest(list);
//	    		System.out.println("please = "+please);

	    		//service.addEvent(list, keyArr[i]);

	    	}
		//System.out.println("==============>>>>>>>>>>>> NDMS EVENT ASYNC END");
    }

	public void eventAddTest() {
		// LOG : 시작 입력
		try {
			// System.out.println("==============>>>>>>>>>>>> NDMS EVENT ASYNC START");
			String time = format.format(System.currentTimeMillis());
			System.out.println("사회재난이벤트추가 = " + time);
			String year = time.substring(0, 4);
			String month = time.substring(5, 7);
			String day = time.substring(8, 10);
//	    	int[] keyArr = {1,2,3,4,5,6,7,8,9};
			Random random = new Random();
//			String[] evtNm = { "배회", "버려짐", "멈춤", "불꽃", "금지된 방향 이동", "연기", "차량 주차" };
			String[] evtNm = { "홍수 주의보", "홍수 경보", "대설 주의보", "대설 경보"};
			String[] table = { "ndms_kma_aws", "ndms_nem_powlvl" };
			String[] ctgory = { "at_avg_ta", "wthr_hm", "wv_ws" };
			String[] yearList = { "2018", "2019" };
			String monthList[] = new String[12];
			String zero = "0";
			for (int i = 0; i < monthList.length; i++) {
				if (i < 9) {
					monthList[i] = zero.concat(String.valueOf(i + 1));
				} else {
					monthList[i] = String.valueOf(i + 1);
				}
			}
			String dayList[] = new String[30];
			for (int i = 0; i < dayList.length; i++) {
				if (i < 9) {
					dayList[i] = zero.concat(String.valueOf(i + 1));
				} else {
					dayList[i] = String.valueOf(i + 1);
				}
			}
			if (propService.getString("ndms.event.use").equals("Y")) {

//		    	for(int i=0; i<keyArr.length; i++){
//		    		String url = NdmsCode.getUrl(keyArr[i]);
//		    		System.out.println("url = "+url);
//		    		List<HashMap<Object, Object>> list = NdmsUtils.getNdmsData(url, keyArr[i]);
//		    		List<HashMap<Object, Object>> list = new ArrayList<HashMap<Object, Object>>();
//		    		for ( int j = 0; j < list.size(); j++ ) {
//		    			HashMap<Object, Object> map = list.get(j);
//		    			String time =NdmsUtils.timeFormar (map.get(NdmsCode.getTime(keyArr[i])).toString());
//		    			if ( time.length() >= 8) {
//		    				list.get(j).put("datyy", time.substring(0, 4));
//		    				list.get(j).put("datmm", time.substring(4, 6));
//		    				list.get(j).put("datdd", time.substring(6, 8));
	//
//		    			} else {
//		    				list.remove(j);
//		    			}
	//
//		    		}
				HashMap<Object, Object> list = new HashMap<Object, Object>();
				year = yearList[random.nextInt(2)];
				month = monthList[random.nextInt(11)];
				day = dayList[random.nextInt(30)];
				String mapTime = year.concat(month);
				mapTime = mapTime.concat(day);
				list.put("evtActnDtm", mapTime);
				list.put("evtNm", evtNm[random.nextInt(4)]);
//				list.put("evtSvcNm", "지능형 CCTV");
				list.put("evtSvcNm", "기상 예경보");
				list.put("evtTyp", "자연재난");
//				list.put("evtTyp", "사회재난");
//				System.out.println("map event wow = " + list);
//					System.out.println("map(please) = "+list);
				boolean wow = this.service.addEventTest(list);
//				System.out.println("wow = " + wow);

				// service.addEvent(list, keyArr[i]);

			}
			// System.out.println("==============>>>>>>>>>>>> NDMS EVENT ASYNC END");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * 기상예경보 테스트
     *
     * @param str
     * @return
     * @throws InterruptedException
     */
    public void asyncNdpsEventTest() throws InterruptedException {
    	HashMap<String, String> map = new HashMap<String, String>();
    	map.put("dt", DateUtil.getStrSec() );
    	map.put("lat", "36.5834718");
    	map.put("lon", "127.2986687");
    	map.put("innb", "123");
    	map.put("data", "80");
    	map.put("addr", "세종시청");
    	map.put("nm", "강우계1");
    	map.put("se", "B03101");
    	map.put("dat", DateUtil.getStrDay("-"));
    	this.ndpsService.addEvent(map);

    	Thread.sleep(5000);

    	map.put("dt", DateUtil.getStrSec() );
    	map.put("lat", "36.5834718");
    	map.put("lon", "127.2986687");
    	map.put("innb", "123");
    	map.put("data", "60");
    	map.put("addr", "세종시청");
    	map.put("nm", "강우계1");
    	map.put("se", "B03101");
    	map.put("dat", DateUtil.getStrDay("-"));
    	this.ndpsService.addEvent(map);

    	Thread.sleep(5000);

    	map.put("dt", DateUtil.getStrSec() );
    	map.put("lat", "36.5834718");
    	map.put("lon", "127.2986687");
    	map.put("innb", "123");
    	map.put("data", "30");
    	map.put("addr", "세종시청");
    	map.put("nm", "강우계1");
    	map.put("se", "B03101");
    	map.put("dat", DateUtil.getStrDay("-"));
    	this.ndpsService.addEvent(map);

    	Thread.sleep(5000);

    	map.put("dt", DateUtil.getStrSec() );
    	map.put("lat", "36.5834718");
    	map.put("lon", "127.2986687");
    	map.put("innb", "123");
    	map.put("data", "20");
    	map.put("addr", "세종시청");
    	map.put("nm", "강우계1");
    	map.put("se", "B03101");
    	map.put("dat", DateUtil.getStrDay("-"));
    	this.ndpsService.addEvent(map);

    	Thread.sleep(5000);

    	map.put("dt", DateUtil.getStrSec() );
    	map.put("lat", "36.5834718");
    	map.put("lon", "127.2986687");
    	map.put("innb", "123");
    	map.put("data", "70");
    	map.put("addr", "세종시청");
    	map.put("nm", "강우계1");
    	map.put("se", "B03101");
    	map.put("dat", DateUtil.getStrDay("-"));
    	this.ndpsService.addEvent(map);

    	Thread.sleep(5000);

    	map.put("dt", DateUtil.getStrSec() );
    	map.put("lat", "36.5834718");
    	map.put("lon", "127.2986687");
    	map.put("innb", "123");
    	map.put("data", "90");
    	map.put("addr", "세종시청");
    	map.put("nm", "강우계1");
    	map.put("se", "B03101");
    	map.put("dat", DateUtil.getStrDay("-"));
    	this.ndpsService.addEvent(map);
    }
    /**
     * NDMS(AWS) 통계 테이블 오늘 날짜 insert
     *
     * @param str
     * @return
     * @throws Exception
     */
	public void chartNdmsAwsEvent() throws Exception {
		// LOG : 시작 입력
		String[] ctgoryList = { "at_avg_ta", "wthr_hm", "wv_ws" };

		String time = format.format(System.currentTimeMillis());
//		System.out.println("NOW TIME(aws) = " + time);
		String year = time.substring(0, 4);
		String month = time.substring(5, 7);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", "aws");
//		map.put("ctgory", "at_avg_ta");
		map.put("table", "xeus.ndms_kma_aws");
		map.put("emdType", "userdefineareanm");
		map.put("emdList", emdList());
		map.put("year", year);
		map.put("month", month);
		map.put("sajong", "세종");

		for (int i = 0; i < ctgoryList.length; i++) {
			System.out.println("NOW(aws "+ctgoryList[i]+") = " + time);
			map.put("ctgory", ctgoryList[i]);

			map.put("datList", DateUtil.getYearMapList("2018"));
//					System.out.println("map aws year = "+map);
			boolean resultYearChart = ndms.addNdmsChartYear(map);
			boolean resultYearTable = ndms.addNdmsTableYear(map);
//					System.out.println("result aws year(chart)"+ctgoryList[i]+" = "+resultYearChart);
//					System.out.println("result aws year(table)"+ctgoryList[i]+" = "+resultYearTable);

			map.put("datList", DateUtil.getMonthMapList());
//					System.out.println("map aws month = "+map);
			boolean resultMonthChart = ndms.addNdmsChartMonth(map);
			boolean resultMonthTable = ndms.addNdmsTableMonth(map);
//					System.out.println("result aws month(chart)"+ctgoryList[i]+" = "+resultMonthChart);
//					System.out.println("result aws month(table)"+ctgoryList[i]+" = "+resultMonthTable);

//			map.put("datList", DateUtil.getDayMapList(Integer.toString(a), Integer.toString(b)));
			map.put("datList", DateUtil.getDayMapList(year, month));
//					System.out.println("map aws day = "+map);
			boolean resultDayChart = ndms.addNdmsChartDay(map);
			boolean resultDayTable = ndms.addNdmsTableDay(map);
//					System.out.println("result aws day(chart)"+ctgoryList[i]+" = "+resultDayChart);
//					System.out.println("result aws day(table)"+ctgoryList[i]+" = "+resultDayTable);
			String afterTime = format.format(System.currentTimeMillis());
			System.out.println("AFTER(aws "+ctgoryList[i]+") = " + time);
		}
	}
    /**
     * NDMS(AWS) 통계 테이블 전체 날짜 insert
     *
     * @param str
     * @return
     * @throws Exception
     */
	public void chartNdmsAllAwsEvent() throws Exception {
		// LOG : 시작 입력
		String[] ctgoryList = { "at_avg_ta", "wthr_hm", "wv_ws" };

		String time = format.format(System.currentTimeMillis());
//		System.out.println("NOW TIME(aws) = " + time);
		String year = time.substring(0, 4);
		String month = time.substring(5, 7);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("type", "aws");
//		map.put("ctgory", "at_avg_ta");
		map.put("table", "xeus.ndms_kma_aws");
		map.put("emdType", "userdefineareanm");
		map.put("emdList", emdList());
		map.put("year", year);
		map.put("month", month);
		map.put("sajong", "세종");

		for (int i = 0; i < ctgoryList.length; i++) {
			System.out.println("NOW(awsALL "+ctgoryList[i]+") = " + time);
			map.put("ctgory", ctgoryList[i]);
			map.put("datList", DateUtil.getYearMapList("2018"));
			boolean resultYearChart = ndms.addNdmsChartYear(map);
			boolean resultYearTable = ndms.addNdmsTableYear(map);
			for(int j=2018; j<=2019; j++) {
				map.put("year", Integer.toString(j));
				map.put("datList", DateUtil.getMonthMapList());
				boolean resultMonthChart = ndms.addNdmsChartMonth(map);
				boolean resultMonthTable = ndms.addNdmsTableMonth(map);
				for(int k=1; k<=12; k++) {
					map.put("month", Integer.toString(k));
					map.put("datList", DateUtil.getDayMapList(Integer.toString(j), Integer.toString(k)));
					boolean resultDayChart = ndms.addNdmsChartDay(map);
					boolean resultDayTable = ndms.addNdmsTableDay(map);
				}
			}
			String afterTime = format.format(System.currentTimeMillis());
			System.out.println("AFTER(awsALL "+ctgoryList[i]+") = " + afterTime);
		}
	}
    /**
     * NDMS(powlvl) 통계 테이블 오늘 날짜 insert
     *
     * @param str
     * @return
     * @throws Exception
     */
	public void chartNdmsPowlvlEvent() throws Exception {
		// LOG : 시작 입력
		String time=format.format(System.currentTimeMillis());
		System.out.println("NOW(powlvl) = "+time);
		String year=time.substring(0, 4);
		String month=time.substring(5, 7);

	    HashMap<String,Object> map=new HashMap<String,Object>();
	    map.put("type", "powlvl");
		map.put("ctgory", "obsr_value");
		map.put("table", "xeus.ndms_nem_powlvl");
		map.put("emdType", "full_area_nm");
		map.put("emdList", emdList());
		map.put("year", year);
		map.put("month", month);
		map.put("sajong", "세종");

//		map.put("year", 2019);
//		map.put("year", Integer.parseInt(year));
//		map.put("month", 01);
//		map.put("month", Integer.parseInt(month));

		map.put("datList", DateUtil.getYearMapList("2018"));
//		System.out.println("map powlvl year = "+map);
		boolean resultYearChart=ndms.addNdmsChartYear(map);
		boolean resultYearTable=ndms.addNdmsTableYear(map);
//		System.out.println("result powlvl year(chart) = "+resultYearChart);
//		System.out.println("result powlvl year(table) = "+resultYearTable);

		map.put("datList", DateUtil.getMonthMapList());
//		System.out.println("map powlvl month = "+map);
		boolean resultMonthChart=ndms.addNdmsChartMonth(map);
		boolean resultMonthTable=ndms.addNdmsTableMonth(map);
//		System.out.println("result powlvl month(chart) = "+resultMonthChart);
//		System.out.println("result powlvl month(table) = "+resultMonthTable);

		map.put("datList", DateUtil.getDayMapList(year, month));
//		System.out.println("map powlvl day = "+map);
		boolean resultDayChart=ndms.addNdmsChartDay(map);
		boolean resultDayTable=ndms.addNdmsTableDay(map);
//		System.out.println("result powlvl day(chart) = "+resultDayChart);
//		System.out.println("result powlvl day(table) = "+resultDayTable);
		String afterTime = format.format(System.currentTimeMillis());
		System.out.println("AFTER(powlvl) = " + time);
	}
	/**
     * NDMS(powlvl) 통계 테이블 전체 날짜 insert
     *
     * @param str
     * @return
     * @throws Exception
     */
	public void chartNdmsAllPowlvlEvent() throws Exception {
		// LOG : 시작 입력
		String time=format.format(System.currentTimeMillis());
		System.out.println("NOWALL(powlvl) = "+time);
//		String year=time.substring(0, 4);
//		String month=time.substring(5, 7);

	    HashMap<String,Object> map=new HashMap<String,Object>();
	    map.put("type", "powlvl");
		map.put("ctgory", "obsr_value");
		map.put("table", "xeus.ndms_nem_powlvl");
		map.put("emdType", "full_area_nm");
		map.put("emdList", emdList());
		map.put("sajong", "세종");
//		map.put("year", year);
//		map.put("month", month);
//		map.put("year", 2019);
//		map.put("year", Integer.parseInt(year));
//		map.put("month", 01);
//		map.put("month", Integer.parseInt(month));
		map.put("datList", DateUtil.getYearMapList("2018"));
		boolean resultYearChart = ndms.addNdmsChartYear(map);
		boolean resultYearTable = ndms.addNdmsTableYear(map);
		for(int j=2018; j<=2019; j++) {
			map.put("year", Integer.toString(j));
			map.put("datList", DateUtil.getMonthMapList());
			boolean resultMonthChart = ndms.addNdmsChartMonth(map);
			boolean resultMonthTable = ndms.addNdmsTableMonth(map);
			for(int k=1; k<=12; k++) {
				map.put("month", Integer.toString(k));
				map.put("datList", DateUtil.getDayMapList(Integer.toString(j), Integer.toString(k)));
				boolean resultDayChart = ndms.addNdmsChartDay(map);
				boolean resultDayTable = ndms.addNdmsTableDay(map);
			}
		}
		String afterTime = format.format(System.currentTimeMillis());
		System.out.println("AFTERAll(powlvl) = " + afterTime);
	}
	/**
     * NDMS(Dmmst) 통계 테이블 오늘 날짜 insert
     *
     * @param str
     * @return
     * @throws Exception
     */
	public void chartNdmsDmmstEvent() throws Exception {
		// LOG : 시작 입력
		String time=format.format(System.currentTimeMillis());
		System.out.println("NOW(Dmmst) = "+time);
		String year=time.substring(0, 4);
		String month=time.substring(5, 7);

	    HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("type", "dmmst");
		map.put("ctgory", "tototf");
		map.put("table", "xeus.ndms_hrf_dmmst");
		map.put("emdType", "damnm");;
		map.put("emdList", damnmList());
		map.put("sajong", "");

		map.put("year", year );
		map.put("month", month);
		map.put("datList", DateUtil.getYearMapList("2018"));
//		System.out.println("map dmmst year = "+map);
		boolean resultYearChart=ndms.addNdmsChartYear(map);
		boolean resultYearTable=ndms.addNdmsTableYear(map);
//		System.out.println("result dmmst year(chart) = "+resultYearChart);
//		System.out.println("result dmmst year(table) = "+resultYearTable);

		map.put("datList", DateUtil.getMonthMapList());
//		System.out.println("map dmmst month = "+map);
		boolean resultMonthChart=ndms.addNdmsChartMonth(map);
		boolean resultMonthTable=ndms.addNdmsTableMonth(map);
//		System.out.println("result dmmst month(chart) = "+resultMonthChart);
//		System.out.println("result dmmst month(table) = "+resultMonthTable);

		map.put("datList", DateUtil.getDayMapList(year, month));
//		map.put("datList", DateUtil.getDayMapList(year, month));
//		System.out.println("map dmmst day = "+map);
		boolean resultDayChart=ndms.addNdmsChartDay(map);
		boolean resultDayTable=ndms.addNdmsTableDay(map);
//		System.out.println("result dmmst day(chart) = "+resultDayChart);
//		System.out.println("result dmmst day(table) = "+resultDayTable);
		String afterTime = format.format(System.currentTimeMillis());
		System.out.println("AFTER(Dmmst) = " + time);
	}
	/**
     * NDMS(Dmmst) 통계 테이블 전체 날짜 insert
     *
     * @param str
     * @return
     * @throws Exception
     */
	public void chartNdmsAllDmmstEvent() throws Exception {
		// LOG : 시작 입력
		String time=format.format(System.currentTimeMillis());
		System.out.println("NOW(Dmmst) = "+time);
//		String year=time.substring(0, 4);
//		String month=time.substring(5, 7);

	    HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("type", "dmmst");
		map.put("ctgory", "tototf");
		map.put("table", "xeus.ndms_hrf_dmmst");
		map.put("emdType", "damnm");;
		map.put("emdList", damnmList());
		map.put("datList", DateUtil.getYearMapList("2018"));
		map.put("sajong", "");

		boolean resultYearChart = ndms.addNdmsChartYear(map);
		boolean resultYearTable = ndms.addNdmsTableYear(map);
		for(int j=2018; j<=2019; j++) {
			map.put("year", Integer.toString(j));
			map.put("datList", DateUtil.getMonthMapList());
			boolean resultMonthChart = ndms.addNdmsChartMonth(map);
			boolean resultMonthTable = ndms.addNdmsTableMonth(map);
			for(int k=1; k<=12; k++) {
				map.put("month", Integer.toString(k));
				map.put("datList", DateUtil.getDayMapList(Integer.toString(j), Integer.toString(k)));
				boolean resultDayChart = ndms.addNdmsChartDay(map);
				boolean resultDayTable = ndms.addNdmsTableDay(map);
			}
		}
		String afterTime = format.format(System.currentTimeMillis());
		System.out.println("AFTERAll(Dmmst) = " + afterTime);
	}
	/**
     * NDMS(Dsr) 통계 테이블 오늘 날짜 insert
     *
     * @param str
     * @return
     * @throws Exception
     */
	public void chartNdmsDsrEvent() throws Exception {
		// LOG : 시작 입력
//		String time=format.format(System.currentTimeMillis());
		String time="2020-08-15 23:59:30";
		System.out.println("NOW(DSR) = "+time);
		String year=time.substring(0, 4);
		String month=time.substring(5, 7);

	    HashMap<String,Object> map=new HashMap<String,Object>();

		map.put("emdList", emdList());

		map.put("year", year);

		map.put("month", month);
		map.put("datList", DateUtil.getYearMapList("2018"));

//		System.out.println("map dmmst month = "+map);
		boolean resultYearChart=ndms.addDsrChartYear(map);
		boolean resultYearTable=ndms.addDsrTableYear(map);
//		System.out.println("result dmmst year(chart) = "+resultYearChart);
//		System.out.println("result dmmst year(table) = "+resultYearTable);

		map.put("datList", DateUtil.getMonthMapList());
//		System.out.println("map dmmst month = "+map);
		boolean resultMonthChart=ndms.addDsrChartMonth(map);
		boolean resultMonthTable=ndms.addDsrTableMonth(map);
//		System.out.println("result dmmst month(chart) = "+resultMonthChart);
//		System.out.println("result dmmst month(table) = "+resultMonthTable);

		map.put("datList", DateUtil.getDayMapList(year, month));
//		System.out.println("map dmmst day = "+map);
		boolean resultDayChart=ndms.addDsrChartDay(map);
		boolean resultDayTable=ndms.addDsrTableDay(map);
//		System.out.println("result dmmst day(chart) = "+resultDayChart);
//		System.out.println("result dmmst day(table) = "+resultDayTable);
		String afterTime = format.format(System.currentTimeMillis());
		System.out.println("AFTER(DSR) = " + time);
	}
	/**
     * NDMS(Dsr) 통계 테이블 전체 날짜 insert
     *
     * @param str
     * @return
     * @throws Exception
     */
	public void chartNdmsAllDsrEvent() throws Exception {
		// LOG : 시작 입력
		String time=format.format(System.currentTimeMillis());
		System.out.println("NOWAll(DSR) = "+time);
//		String year=time.substring(0, 4);
//		String month=time.substring(5, 7);

	    HashMap<String,Object> map=new HashMap<String,Object>();

		map.put("emdList", emdList());

//		map.put("year", year);

//		map.put("month", month);
		map.put("datList", DateUtil.getYearMapList("2018"));

		boolean resultYearChart=ndms.addDsrChartYear(map);
		boolean resultYearTable=ndms.addDsrTableYear(map);
		for(int j=2018; j<=2019; j++) {
			map.put("year", Integer.toString(j));
			map.put("datList", DateUtil.getMonthMapList());
			boolean resultMonthChart=ndms.addDsrChartMonth(map);
			boolean resultMonthTable=ndms.addDsrTableMonth(map);
			for(int k=1; k<=12; k++) {
				map.put("month", Integer.toString(k));
				map.put("datList", DateUtil.getDayMapList(Integer.toString(j), Integer.toString(k)));
				boolean resultDayChart=ndms.addDsrChartDay(map);
				boolean resultDayTable=ndms.addDsrTableDay(map);
			}
		}
		String afterTime = format.format(System.currentTimeMillis());
		System.out.println("AFTERAll(DSR) = " + afterTime);
	}
	public ArrayList<HashMap<String, Object>> damnmList() throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, String>> damnm = bjd.getDamnmList();
		for (int i = 0; i < damnm.size(); i++) {
			HashMap<String, Object> maps = new HashMap<String, Object>();
			maps.put("nm", damnm.get(i).get("damnm"));
			list.add(maps);
		}
		return list;
	}
	public ArrayList<HashMap<String, Object>> emdList() throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		ArrayList<EmdVo> emd = bjd.getEmdList();
		for (int i = 0; i < emd.size(); i++) {
			HashMap<String, Object> maps = new HashMap<String, Object>();
			maps.put("nm", emd.get(i).getEmdKorNm());
			list.add(maps);
		}
		return list;
	}
}


