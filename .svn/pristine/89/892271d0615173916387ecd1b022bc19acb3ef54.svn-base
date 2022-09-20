package geomex.xeus.ndms.service;

import java.util.HashMap;

import geomex.xeus.smartcity.Utils;
import geomex.xeus.util.code.DateUtil;

public class NdmsCode {


	//소방 긴급구조 정보
	//	private final static String CM_DSR = "CM_DSR";
	private final static String INF_119_MSTR = "INF_119_MSTR";

	private final static String KMA_INFORM = "KMA_INFORM";

	//예비특보 통보문
	private final static String KMA_PRE_INFORM = "KMA_PRE_INFORM";

	//지진통보문
	private final static String KMA_EARTH_INFM = "KMA_EARTH_INFM";

	//지자체 10분 우량
	private final static String NEM_PORNQT = "NEM_PORNQT";

	//댐 수위 10분 단위
	private final static String HRF_DMMST = "HRF_DMMST";

	//AWS 1시간 자료
	private final static String KMA_AWS = "KMA_AWS";

	//지자체 10분 수위
	private final static String NEM_POWLVL = "NEM_POWLVL";

	//동네예보 강수확률
	private final static String KMA_DFS_SHRT_POP = "KMA_DFS_SHRT_POP";

	//API 접속 계정
	private static String USER_ID = "SEJONG";

	//API KEY
	private static String API_KEY = "";

	//요청 아이디
	private static String INFO_ID = "";

	//요청 이벤트 명
	private static String EVENT_NM = "";

	//수신타입 ( jsno / xml )
	private final static String SQL_TYP = "json";

	//areaCd ( 지역코드-세종시 : 36  )
	private final static String AREA_CD = "36";

	/**
	 * NDMS URL 을 생성한다.
	 *
	 * @param code - 정의는 getCode 메소드 확인.
	 * @return
	 */
	public static String getUrl(int code){
		setCode(code);
		String fromDateTime = DateUtil.getStrDay();
		//System.out.println(fromDateTime);
		String url = "http://dico.ndms.go.kr/idsiDIJ/dij/getOpenUseInfo.do"
				+ "?userId="+USER_ID
				+ "&serviceKey="+API_KEY
				+ "&sqlType="+SQL_TYP
				+ "&infoId="+INFO_ID
				+ "&fromDateTime="+fromDateTime
				+ "&areaCd="+AREA_CD;
		return url;
	}

	/**
	 * NDMS URL 을 생성한다.
	 *
	 * @param code - 정의는 getCode 메소드 확인.
	 * @param fromDateTime - 받아올 날짜를 선택한다.
	 * @return
	 */
	public static String getUrl(int code, String fromDateTime){

		setCode(code);

		String url = "http://dico.ndms.go.kr/idsiDIJ/dij/getOpenUseInfo.do"
				+ "?userId="+USER_ID
				+ "&serviceKey="+API_KEY
				+ "&sqlType="+SQL_TYP
				+ "&infoId="+INFO_ID
				+ "&fromDateTime="+fromDateTime
				+ "&areaCd="+AREA_CD;

		return url;
	}


	/**
	 * 코드 값으로 요청 파라미터를 만든다.
	 *  - INFO_ID, API_KEY
	 *
	 * @param code
	 */
	private static void setCode(int code) {
		switch (code){
			case 1 :
				//소방 긴급구조 정보
				INFO_ID = INF_119_MSTR;
				API_KEY = "FA80355F750CFBF729CA15CA026B0286";
				USER_ID = "20200509";
				break;
			case 2 :
				//기상특보 통보문
				INFO_ID = KMA_INFORM;
				API_KEY = "5A4BE8D51950B0BF5C3FDA46A39B2A02";
				USER_ID = "20180116";
				break;
			case 3 :
				//예비특보 통보문
				INFO_ID = KMA_PRE_INFORM;
				API_KEY = "4AF3102478088EA8D99A4EDF81D1B849";
				USER_ID = "20180117";
				break;
			case 4 :
				//지진 통보문
				INFO_ID = KMA_EARTH_INFM;
				API_KEY = "96C4D1856373648A2C61449A2502D8ED";
				USER_ID = "20180118";
				break;
			case 5 :
				//지자체 10분 우량
				INFO_ID = NEM_PORNQT;
				API_KEY = "3DBDF5D00BA598FBBD1CCE5A07DCF939";
				USER_ID = "20180119";
				break;
			case 6 :
				//댐 수위 10분 단위
				INFO_ID = HRF_DMMST;
				API_KEY = "15E22A5FF71CD14CD218834F4E6E3805";
				USER_ID = "20180120";
				break;
			case 7 :
				//aws 1시간 자료
				INFO_ID = KMA_AWS;
				API_KEY = "F71FF686B4E5C391413E7627E81B330C";
				USER_ID = "20180121";
				break;
			case 8 :
				//지자체 10분 수위
				INFO_ID = NEM_POWLVL;
				API_KEY = "02E4B833544D6D5F25CD8C3E054E842C";
				USER_ID = "20180122";
				break;
			case 9 :
				//동네예보 강수확률
				INFO_ID = KMA_DFS_SHRT_POP;
				API_KEY = "1E62506135B88FB19889B0AE3976E656";
				USER_ID = "20180123";
				break;
			default :
				INFO_ID = null;
				API_KEY = null;
				break;
		}
	}


	/**
	 * 코드 값으로 요청 파라미터를 만든다.
	 *  - INFO_ID, API_KEY
	 *
	 * @param code
	 */
	private static void setCodeByNdmsType(String ndmsType) {
		switch (ndmsType){
			case "긴급구조" :
				//소방 긴급구조 정보
				INFO_ID = INF_119_MSTR;
				API_KEY = "FA80355F750CFBF729CA15CA026B0286";
				USER_ID = "20200509";
				break;
			case "기상특보 통보문" :
				//기상특보 통보문
				INFO_ID = KMA_INFORM;
				API_KEY = "5A4BE8D51950B0BF5C3FDA46A39B2A02";
				USER_ID = "20180116";
				break;
			case "예비특보 통보문" :
				//예비특보 통보문
				INFO_ID = KMA_PRE_INFORM;
				API_KEY = "4AF3102478088EA8D99A4EDF81D1B849";
				USER_ID = "20180117";
				break;
			case "지진 통보문" :
				//지진 통보문
				INFO_ID = KMA_EARTH_INFM;
				API_KEY = "96C4D1856373648A2C61449A2502D8ED";
				USER_ID = "20180118";
				break;
			case "지자체 10분 우량" :
				//지자체 10분 우량
				INFO_ID = NEM_PORNQT;
				API_KEY = "3DBDF5D00BA598FBBD1CCE5A07DCF939";
				USER_ID = "20180119";
				break;
			case "댐 수위 10분 단위" :
				//댐 수위 10분 단위
				INFO_ID = HRF_DMMST;
				API_KEY = "15E22A5FF71CD14CD218834F4E6E3805";
				USER_ID = "20180120";
				break;
			case "aws 1시간 자료" :
				//aws 1시간 자료
				INFO_ID = KMA_AWS;
				API_KEY = "F71FF686B4E5C391413E7627E81B330C";
				USER_ID = "20180121";
				break;
			case "지자체 10분 수위" :
				//지자체 10분 수위
				INFO_ID = NEM_POWLVL;
				API_KEY = "02E4B833544D6D5F25CD8C3E054E842C";
				USER_ID = "20180122";
				break;
			case "동네예보 강수확률" :
				//동네예보 강수확률
				INFO_ID = KMA_DFS_SHRT_POP;
				API_KEY = "1E62506135B88FB19889B0AE3976E656";
				USER_ID = "20180123";
				break;
			default :
				INFO_ID = null;
				API_KEY = null;
				break;
		}
	}


	/**
	 * 코드 값으로 이벤트 명을 만든다.
	 *
	 * @param code
	 */
	public static String getEventKey(int code, HashMap<Object, Object> map) {
		String evtKey = "T";
		switch (code){
		case 1 :
			//소방 긴급구조 정보
			evtKey += NdmsUtils.timeFormar(map.get("statenddtime").toString())+"P1E"+map.get("dsrseq");
			break;
		case 2 :
			//예비특보 통보문
			evtKey += NdmsUtils.timeFormar(map.get("dtregtdate").toString())+"P2E"+map.get("notmseq");
			break;
		case 3 :
			//기상특보 통보문
			evtKey += NdmsUtils.timeFormar(map.get("dttmfc").toString())+"P3E"+map.get("notmseq");
			break;
		case 4 :
			//지진 통보문
			evtKey += NdmsUtils.timeFormar(map.get("dttmfc").toString())+"P4E"+map.get("dttmfc").toString();
			break;
		case 5 :
			//지자체 10분 우량areaCd
			evtKey += NdmsUtils.timeFormar(map.get("dtregt").toString())+"P5E"+map.get(NdmsCode.getRegion(code)).toString().replaceAll(" ", "");
			break;
		case 6 :
			//댐 수위 10분 단위
			evtKey += NdmsUtils.timeFormar(map.get("dtregt").toString())+"P6E"+map.get(NdmsCode.getRegion(code)).toString().replaceAll(" ", "");
			break;
		case 7 :
			//aws 1시간 자료
			evtKey += NdmsUtils.timeFormar(map.get("dtregt").toString())+"P7E"+map.get(NdmsCode.getRegion(code)).toString().replaceAll(" ", "");
			break;
		case 8 :
			//지자체 10분 수위
			evtKey += NdmsUtils.timeFormar(map.get("dtregt").toString())+"P8E"+map.get(NdmsCode.getRegion(code)).toString().replaceAll(" ", "");
			break;
		case 9 :
			//동네예보 강수확률
			evtKey += NdmsUtils.timeFormar(map.get("dtregt").toString())+"P9E"+map.get(NdmsCode.getRegion(code)).toString().replaceAll(" ", "");
			break;
		default :
			break;
		}

		return evtKey;
	}

	/**
	 * 코드 값으로 이벤트 명을 만든다.
	 *
	 * @param code
	 */
	public static String getEventNm(int code) {
		String evtNm = "";
		switch (code){
		case 1 :
			//소방 긴급구조 정보
			evtNm = "소방 긴급구조";
			break;

		case 2 :
			//기상특보 통보문
			evtNm = "기상특보 통보문";
			break;
		case 3 :
			//예비특보 통보문
			evtNm = "예비특보 통보문";
			break;
		case 4 :
			//지진 통보문
			evtNm = "지진현황 통보문";
			break;
		case 5 :
			//지자체 10분 우량
			evtNm = "강우량 정보";
			break;
		case 6 :
			//댐 수위 10분 단위
			evtNm = "댐 수위 정보";
			break;
		case 7 :
			//aws 1시간 자료
			evtNm = "AWS 관측 정보";
			break;
		case 8 :
			//지자체 10분 수위
			evtNm = "수위 정보";
			break;
		case 9 :
			//동네예보 강수확률
			evtNm = "동네예보 강수 정보";
			break;
		default :
			break;
		}

		return evtNm;
	}

	/**
	 * 코드 값으로 이벤트 내용을 만든다.
	 * - 추후 각 항목 별 수집 값으로 표출할 계획임.
	 * @param code
	 * @param map
	 *
	 * @return map { x : 경도, y : 위도 }
	 */
	public static HashMap<Object, Object> getEventCord(int code, HashMap<Object, Object> map) {
		HashMap<Object, Object> cordMap = new HashMap<Object, Object>();
		String lat = ""; //경도
		String lon = ""; //위도

		switch (code){
		case 1 :
			//소방 긴급구조 정보
			lat = map.get("gisx").toString();
			lon = map.get("gisy").toString();
			break;
		case 2 :
			//기상특보 통보문
			break;
		case 3 :
			//예비특보 통보문
			break;
		case 4 :
			//지진 통보문
			break;
		case 5 :
			//지자체 10분 우량
			break;
		case 6 :
			//댐 수위 10분 단위
			break;
		case 7 :
			//aws 1시간 자료
			lat = map.get("cordlat").toString();
			lon = map.get("cordlon").toString();

			break;
		case 8 :
			//지자체 10분 수위
			break;
		case 9 :
			//동네예보 강수확률
			break;
		default :
			break;
		}

		if ( lat.equals("") || lon.equals("")) {
			lat = "0";
			lon = "0";
		}
		cordMap.put("x", lat);
		cordMap.put("y", lon);

		return cordMap;
	}

	/**
	 * 코드 값으로 이벤트 좌표 값을 만든다.
	 * - 추후 각 항목 별 수집 값으로 표출할 계획임.
	 * @param code
	 */
	public static String getEventCntn(int code, HashMap<Object,Object> map) {
		StringBuilder evtCntn = new StringBuilder();
		switch (code){
		case 1 :
			//소방 긴급구조 정보
			//evtCntn.append("긴급구조번호 : "+map.get("dsrseq"));
			//evtCntn.append("/동일긴급구조번호 : "+map.get("samedsrseq"));
			//evtCntn.append("/긴급구조우편번호일련번호 : "+map.get("dsrzipseq"));
			//evtCntn.append("/지역명 : "+map.get("userdefineareanm"));
			//evtCntn.append("/긴급구조번지 : "+map.get("dsrhouseno"));
			//evtCntn.append("/긴급구조상세주소 : "+map.get("dsretcaddr"));
			//evtCntn.append("/대상물ID : "+map.get("objid"));
			evtCntn.append("긴급구조종별코드 : "+map.get("dsrkndcd"));
			evtCntn.append(" / 긴급구조분류코드 : "+map.get("dsrclscd"));
			evtCntn.append(" / 긴급구조규모코드 : "+map.get("dsrsizecd"));
			evtCntn.append(" / 긴급구조진행상황코드 : "+map.get("proccd"));
			//evtCntn.append("/관제여부 : "+map.get("ctlyn"));
			//evtCntn.append("/관제자ID : "+map.get("ctluserid"));
			//evtCntn.append("/단말ID : "+map.get("ttyid"));
			//evtCntn.append("/상황종료일시 : "+map.get("statenddtime"));
			//evtCntn.append("/종결자ID : "+map.get("enduserid"));
			//evtCntn.append("/관할서 : "+map.get("juriswardid"));
			//evtCntn.append("/수동입력여부 : "+map.get("handinsyn"));
			//evtCntn.append("/무선현재채널 : "+map.get("radioch"));
			//evtCntn.append("/기지국ID : "+map.get("stationid"));
			//evtCntn.append("/지점정확도구분 : "+map.get("pointaccrcls"));
			//evtCntn.append("/GIS X : "+map.get("gisx"));
			//evtCntn.append("/GIS Y : "+map.get("gisy"));
			//evtCntn.append("/SAMEDSR RAD : "+map.get("samedsrrad"));
			//evtCntn.append("/최초접수번호 : "+map.get("dfstregseq"));
			//evtCntn.append("/중앙구조대출동요청여부 : "+map.get("ctrlrscdspreqyn"));
			//evtCntn.append("/사후등록여부 : "+map.get("aftregyn"));
			//evtCntn.append("/출동지연사유 : "+map.get("dspdelayrsncd"));
			//evtCntn.append("/항공구조번호 : "+map.get("airdsrseq"));
			//evtCntn.append("/행정동 우편번호 : "+map.get("dsradminzipseq"));
			//evtCntn.append("/행정동 시도코드 : "+map.get("dsradminsidocd"));
			//evtCntn.append("/행정동 구군코드 : "+map.get("dsradminguguncd"));
			//evtCntn.append("/행정동 동코드 : "+map.get("dsradmindongcd"));
			//evtCntn.append("/행정동 리코드 : "+map.get("dsradminricd"));
			//evtCntn.append("/주소 FLAG : "+map.get("addrflag"));
			//evtCntn.append("/AVL GIS X좌표 : "+map.get("avlgisx"));
			//evtCntn.append("/AVL GIS Y좌표 : "+map.get("avlgisy"));
			//evtCntn.append("/의식없음 : "+map.get("awareyn"));
			//evtCntn.append("/호흡없음 : "+map.get("breathyn"));
			//evtCntn.append("/CPR지도 : "+map.get("cpryn"));
			//evtCntn.append("/파악거절 : "+map.get("refuseyn"));
			//evtCntn.append("/도로명 코드 : "+map.get("rncd"));
			//evtCntn.append("/읍면동 일련번호 : "+map.get("emdsn"));
			//evtCntn.append("/지하 여부 : "+map.get("fg"));
			//evtCntn.append("/건물 본번 : "+map.get("buldmnnm"));
			//evtCntn.append("/건물 부번 : "+map.get("buldslno"));
			//evtCntn.append("/저장주소의 유형 : "+map.get("addrtype"));
			//evtCntn.append("/도로명 기타주소 : "+map.get("dsrrnetcaddr"));
			//evtCntn.append("/권역ID : "+map.get("zoneid"));
			//evtCntn.append("/비긴급여부 : "+map.get("noemeryn"));

			break;
		case 2 :
			//기상특보 통보문
			//evtCntn.append("지역명 : "+map.get("userdefineareanm"));
			evtCntn.append("발표 시각 : "+map.get("dttmfc"));
			//evtCntn.append(" / 해당 구역 : "+map.get("sectareatxt"));
			evtCntn.append(" / 제목 : "+map.get("etcttl"));
			evtCntn.append(" / 발표 내용 : "+map.get("statctnt"));
			evtCntn.append(" / 특보 발표 시각 텍스트 : "+map.get("dttmeftxt"));
			evtCntn.append(" / 참고 사항 : "+map.get("etcref"));
			//evtCntn.append("/발표 일련번호 : "+map.get("notmseq"));
			evtCntn.append(" / 예비 특보 발효 현황 : "+map.get("statpwvl"));
			evtCntn.append(" / 특보 발효 현황 내용 : "+map.get("stattmef"));

			break;
		case 3 :
			//예비특보 통보문
			evtCntn.append("입수 일시 : "+map.get("dtregtdate"));
			evtCntn.append(" / 특보 발효 현황 내용 : "+map.get("stattm2"));
			evtCntn.append(" / 예보 관 : "+map.get("nmmanfc"));
			//evtCntn.append("/발표 순번 : "+map.get("notmseq"));

			break;
		case 4 :
			//지진 통보문
			evtCntn.append("발표 시각 : "+map.get("dttmfc"));
			evtCntn.append(" / 위치 : "+map.get("locloc"));
			evtCntn.append(" / 규모 : "+map.get("sectscle"));
			evtCntn.append(" / 참고 사항 : "+map.get("statother"));
			break;
		case 5 :
			//지자체 10분 우량
			//evtCntn.append("관측 일시 : "+map.get("obsrdttm"));
			//evtCntn.append("/전체 지역명 : "+map.get("fullareanm"));
			evtCntn.append("관측소명 : "+map.get("obstnm"));
			evtCntn.append(" / 관측값 : "+map.get("obsrvalue"));
			evtCntn.append(" / 위험상태 : "+map.get("dngrstatcancel"));
			//evtCntn.append("/관측주기 : "+map.get("datatp"));
			//evtCntn.append("/GIS코드 : "+map.get("cdgis"));
			//evtCntn.append("/행정안전부 입수일시 : "+map.get("dtregt"));
			//evtCntn.append("/지역코드 : "+map.get("areacd"));
			//evtCntn.append("/일련번호 : "+map.get("seqno"));
			break;
		case 6 :
			//댐 수위 10분 단위
			//evtCntn.append("년월일시분 : "+map.get("ymdhm"));
			//evtCntn.append("/댐 관측소 코드 : "+map.get("dmobscd"));
			evtCntn.append("댐명 : "+map.get("damnm"));
			evtCntn.append(" / 댐 형식 : "+map.get("damtyp"));
			evtCntn.append(" / 좌안 : "+map.get("lftbnk"));
			evtCntn.append(" / 우안 : "+map.get("rgtbnk"));
			evtCntn.append(" / 저 수위 : "+map.get("swl"));
			evtCntn.append(" / 방수로 수위 : "+map.get("owl"));
			evtCntn.append(" / 저수량 : "+map.get("sfw"));
			evtCntn.append(" / 공용량 : "+map.get("ecpc"));
			evtCntn.append(" / 유입량 : "+map.get("inf"));
			evtCntn.append(" / 총 방류량 : "+map.get("tototf"));
			evtCntn.append(" / GATE방류량 : "+map.get("gtotf"));
			evtCntn.append(" / 발전 방류량 : "+map.get("egotf"));
			evtCntn.append(" / 여수로 방류량 : "+map.get("spwotf"));
			evtCntn.append(" / 개문 : "+map.get("og"));
			evtCntn.append(" / 개도 : "+map.get("oh"));
			evtCntn.append(" / 원시자료 저수위 : "+map.get("tmswl"));
			//evtCntn.append("/등록일시 : "+map.get("dtregt"));

			break;
		case 7 :
			//aws 1시간 자료
			//evtCntn.append("지역명 : "+map.get("userdefineareanm"));
			//evtCntn.append("/AWS 관측소 코드 : "+map.get("cdstnnumber"));
			//evtCntn.append("/관측 시간 : "+map.get("dtobz"));
			evtCntn.append("10분 평균 풍향 : "+map.get("windwd"));
			evtCntn.append(" / 1분 평균 풍속 : "+map.get("wvwsnumber"));
			evtCntn.append(" / 1분 평균 기온 : "+map.get("atavgta"));
			evtCntn.append(" / 1분 평균 습도 : "+map.get("wthrhm"));
			evtCntn.append(" / 1분 평균 현지 기압 : "+map.get("appa"));
			evtCntn.append(" / 1분 평균 해면 기압 : "+map.get("apps"));
			evtCntn.append(" / 강수 감지 : "+map.get("wthryn"));
			evtCntn.append(" / 시간 누적 강수량 : "+map.get("wthr1hr"));
			evtCntn.append(" / 일 누적 강수량 : "+map.get("wthrday"));
			//evtCntn.append("/위도 : "+map.get("cordlat"));
			//evtCntn.append("/경도 : "+map.get("cordlon"));
			//evtCntn.append("/고도 : "+map.get("cordhtvarchar"));
			//evtCntn.append("/생성 일시 : "+map.get("dtregtdate"));
			break;
		case 8 :
			//지자체 10분 수위
			//evtCntn.append("관측일시 : "+map.get("obsrdttm"));
			//evtCntn.append("/전체 지역명 : "+map.get("fullareanm"));
			evtCntn.append("관측소명 : "+map.get("obstnm"));
			evtCntn.append(" / 관측값 : "+map.get("obsrvalue"));
			//evtCntn.append("/위험상태 : "+map.get("dngrstatcancel"));
			//evtCntn.append("/관측주기 : "+map.get("datatp"));
			//evtCntn.append("/EXPORT TIME : "+map.get("wlvlexptdttm"));
			//evtCntn.append("/지역코드 : "+map.get("areacd"));
			//evtCntn.append("/일련번호 : "+map.get("seqno"));
			//evtCntn.append("/행정안전부 입수일시 : "+map.get("dtregt"));

			break;
		case 9 :
			//동네예보 강수확률
			evtCntn.append("강수확률1 : "+map.get("qtyrsrat1"));
			evtCntn.append(" / 강수확률2 : "+map.get("qtyrsrat2"));
			evtCntn.append(" / 강수확률3 : "+map.get("qtyrsrat3"));
			evtCntn.append(" / 강수확률4 : "+map.get("qtyrsrat4"));
			evtCntn.append(" / 강수확률5 : "+map.get("qtyrsrat5"));
			evtCntn.append(" / 강수확률6 : "+map.get("qtyrsrat6"));
			evtCntn.append(" / 강수확률7 : "+map.get("qtyrsrat7"));
			evtCntn.append(" / 강수확률8 : "+map.get("qtyrsrat8"));
			evtCntn.append(" / 강수확률9 : "+map.get("qtyrsrat9"));
			evtCntn.append(" / 강수확률10 : "+map.get("qtyrsrat10"));
			evtCntn.append(" / 강수확률11 : "+map.get("qtyrsrat11"));
			evtCntn.append(" / 강수확률12 : "+map.get("qtyrsrat12"));
			evtCntn.append(" / 강수확률13 : "+map.get("qtyrsrat13"));
			evtCntn.append(" / 강수확률14 : "+map.get("qtyrsrat14"));
			evtCntn.append(" / 강수확률15 : "+map.get("qtyrsrat15"));
			evtCntn.append(" / 강수확률16 : "+map.get("qtyrsrat16"));
			evtCntn.append(" / 강수확률17 : "+map.get("qtyrsrat17"));
			evtCntn.append(" / 강수확률18 : "+map.get("qtyrsrat18"));
			evtCntn.append(" / 강수확률19 : "+map.get("qtyrsrat19"));
			evtCntn.append(" / 강수확률20 : "+map.get("qtyrsrat20"));
			evtCntn.append(" / 강수확률21 : "+map.get("qtyrsrat21"));
			evtCntn.append(" / 강수확률22 : "+map.get("qtyrsrat22"));
			//evtCntn.append("/생성일시 : "+map.get("dtregt"));
			//evtCntn.append("/행정구역 코드 : "+map.get("dtregt"));

			break;
		default :
			break;
		}

		return evtCntn.toString();
	}


	/**
	 * 각 시간 별 컬럼을 가져온다.
	 *
	 * @param key
	 * @return
	 */
	public static String getTime(int key) {
		String str;
		switch(key) {
			case 1 : 	//119
				str="reptdt";
				break;
			case 7 :	//지역별 aws
				str="dtregt";
				break;
			case 9 :	//동네예보 강수확률
				str="dtregt";
				break;
			case 5 :	//지역별 강우량
				str="dtregt";
				break;
			case 8 :	//강수 우위
				str="dtregt";
				break;
			case 6 :	//댐 수위
				str="ymdhm";
				break;
			case 3 :	//예비특보
				str="dttmfc";
				break;
			case 4 :	//지진
				str="event_time";
				break;
			case 2 :	//기상특보
				str="dttmfc";
				break;
			default :
				str=null;
				break;
		}
		return str;
	}

	/**
	 * 각이벤트별 지역 컬럼을 지정한다.
	 *
	 * @param key
	 * @return
	 */
	public static String getRegion(int key) {
		String str;
		switch(key) {
		case 1 :	//119
			str="dstrareanm";
			break;
		case 7 :	//지역별 aws
			str="userdefineareanm";
			break;
		case 9 :	//동네예보 강수확률
			str="userdefineareanm";
			break;
		case 5 :	//지역별 강우량
			str="fullareanm";
			break;
		case 8 :	//강수 수위
			str="fullareanm";
			break;
		case 6 :	//댐 수위
			str="damnm";
			break;
		case 4 :	//지진
			str="location";
			break;
		case 3 :	//예비특보
			str="nmmanfc";
			break;
		case 2 :	//기상특보
			str="userdefineareanm";
			break;
		default :
			str=null;
			break;
		}
		return str;
	}

	public static String getAddressAttributeNm(int code) {
		String str;
		switch(code) {
		case 1 :	//119
			str="dstrAreaNm";
			break;
//		case 7 :	//지역별 aws
//			str="userdefineareanm";
//			break;
//		case 9 :	//동네예보 강수확률
//			str="userdefineareanm";
//			break;
//		case 5 :	//지역별 강우량
//			str="fullareanm";
//			break;
//		case 8 :	//강수 수위
//			str="fullareanm";
//			break;
//		case 6 :	//댐 수위
//			str="damnm";
//			break;
//		case 4 :	//지진
//			str="location";
//			break;
//		case 3 :	//예비특보
//			str="nmmanfc";
//			break;
//		case 2 :	//기상특보
//			str="userdefineareanm";
//			break;
		default :
			str=null;
			break;
		}
		return str;
	}

}
