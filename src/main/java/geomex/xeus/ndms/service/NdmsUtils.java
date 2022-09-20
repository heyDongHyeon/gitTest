package geomex.xeus.ndms.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import geomex.xeus.util.code.DateUtil;
import geomex.xeus.util.code.StrUtil;

public class NdmsUtils {

	/**
	 * NDMS API URL 요청을 한다.
	 *
	 * @param url api 요청 주소
	 * @return
	 */


	private static String apiKey = "2a491b7e474a4f7032c06cbbc8420043";
	private static String addrSrchUrl = "http://dapi.kakao.com/v2/local/search/address.json?query=";
	private static String addrSrchUrlOfProxy = "http://dapi.kakao.com/v2/local/search/address.json&query=";

	@SuppressWarnings("unchecked")
	public static List<HashMap<Object, Object>> getNdmsData(String url, int code) {
		BufferedReader in = null;
		StringBuilder sb = new StringBuilder();
		JSONObject json = null;
		List<HashMap<Object, Object>> list = new ArrayList<HashMap<Object, Object>>();

		try {

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setConnectTimeout(5000);
			con.setRequestMethod("GET");

			in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			sb.setLength(0);
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
//			sb=NdmsString.getNdmsNew119();
			if (new JSONParser().parse(sb.toString()).getClass() == JSONArray.class) {
				json = (JSONObject) ((JSONArray) new JSONParser().parse(sb.toString())).get(0);
			} else {
				json = (JSONObject) new JSONParser().parse(sb.toString());
			}
			HashMap<Object, Object> mapDocument = (HashMap<Object, Object>) json.get("document");
			HashMap<Object, Object> mapRows = (HashMap<Object, Object>) mapDocument.get("rows");

			if (mapRows != null && mapRows.get("row") != null) {

				list = (List<HashMap<Object, Object>>) mapRows.get("row");

				// 어떠한 값도 생기지 않았다.
				if (list == null) {
					return null;
				}
				list = StrUtil.keyChangeLower(list, code);
			}
			// System.out.println(list.get(0));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}

		return list;
	}

	public static List<HashMap<Object, Object>> passing(List<HashMap<Object, Object>> list, String emdCd, int key) {
		// TODO Auto-generated method stub
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 문자열 비교가 되는구나..
		Date date;
		long max = 0, n = 0;
		int i, j;

		List<HashMap<Object, Object>> newList = new ArrayList<HashMap<Object, Object>>();
		List<HashMap<Object, Object>> sameRegionList = null;
		HashMap<Object, Object> newMap;
		HashMap<Object, Object> obj = new HashMap<Object, Object>(); // newwMap에 넣어줄 obj
		HashMap<Object, Object> map = null;
		HashMap<Object, Object> temp = null;
		int size = list.size();
//		System.out.println(size);
		String region = NdmsCode.getRegion(key);
		String time = NdmsCode.getTime(key);
		try {
			for (i = 0; i < size; i++) {
				map = list.get(i);
				newMap = new HashMap<Object, Object>();
				obj=new HashMap<Object, Object>();
				if (((String) map.get(region)).contains(emdCd)) // 이거를 변수로 바꿔줘야함...
				{
					for (Object str : map.keySet()) {
						if (region.equals((String) str)) {
							newMap.put("emdCd", map.get(str));
						} else if (time.equals((String) str)) {
							if (map.get(str).toString().indexOf(':') < 1) {
								newMap.put("time", DateUtil.formatDate(map.get(str).toString()));
//								newMap.put("time", map.get(str).toString()+" "+map.get("dtobz").toString().substring(8,10)+":00:00");

							} else {
								newMap.put("time", map.get(str));

							}
						} else {
							obj.put(str, map.get(str));
						}
					}
					newMap.put("obj", obj); // 나머지 데이터를 obj에 넣고 newMap에 넣어줌..
					sameRegionList = sameRegion(list, (String) newMap.get("emdCd"), region);
					newMap.put("objList", sameRegionList);

					date = f.parse((String) newMap.get("time")); // 새로들어올려는 데이터
					n = date.getTime(); // 오는 데이터의 시간대를 파악하고
					for (j = 0; j < newList.size(); j++) {
						temp = newList.get(j); // 새로운데이터를 돌려서
						if (newMap.get("emdCd").equals(temp.get("emdCd"))) // 같은 전라북도가 오면(어짜피 한개)왜냐하면 리무브해줌.
						{
							date = f.parse((String) temp.get("time")); // 어짜피 한개를 시간을 파악함..
							max = date.getTime(); // newList에서 시간이 가장 최근 데이터...
							if (max < n) // 만약 어짜피한개보다 최신이면 들어감..
							{
								newList.remove(j); // 지우고 들어감
								newList.add(newMap); // 다음 레코드로 들어감.
								break;
							} else {
								break; // 다음 레코드록 ㅏ야함. 어짜피 한개보다 작기 때문에 들어갈수가 없음.
							}
						}
					}
					if (j == newList.size()) // 만약에 어짜ㅣ 한개가 없으면
					{
						newList.add(newMap); // 그냥 들어감..
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newList;
	}

	public static List<HashMap<Object, Object>> duplePassing(List<HashMap<Object, Object>> list, String emdCd, int key) {
		int i;
		HashMap<Object, Object> map = null;
		int size = list.size();

		String region = NdmsCode.getRegion(key);
		String time = NdmsCode.getTime(key);
		for (i = 0; i < size; i++) {
			map = list.get(i);
			if (((String) map.get(region)).contains(emdCd)) {
				map.put("emdCd", map.get(region));
				map.put("time", map.get(time));
			}
		}
		return list;
	}

	private static List<HashMap<Object, Object>> sameRegion(List<HashMap<Object, Object>> list, String emdCd,
			String region) {
		int i;
		List<HashMap<Object, Object>> sameRegionList = new ArrayList<HashMap<Object, Object>>();
		HashMap<Object, Object> map = null;
		int size = list.size();
		for (i = 0; i < size; i++) {
			map = list.get(i);
			if (map.get(region).equals(emdCd)) // 여기서 type 때문에 error가 날 수 있따.ㄴ
			{
				sameRegionList.add(map);
			}
		}
		return sameRegionList;
	}

	public static String timeFormar(String date) {
		date = date.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		if (date.length() > 14) {
			date = date.substring(0, 14);
		} else if (date.length() < 14) {

			while (date.length() == 14) {
				date = date + "0";
			}
		}
		return date;
	}

	/**
     * HashMap 데이터를 JSON String 으로 리턴합니다.(지진통보 데이터)
     *
     * @param param
     * @return
     */
    public static String setEarthJson(HashMap<Object, Object> param){
    	String eventTime=StrUtil.chkNull((String)param.get("EventTime"));
    	String reportTime=StrUtil.chkNull((String)param.get("EventTime"));
    	if(!eventTime.equals("") || eventTime.length()==12) {
    		eventTime=eventTime.substring(0,4)+"-"+eventTime.substring(4,6)+"-"+eventTime.substring(6,8)+" "+eventTime.substring(8,10)+":"+eventTime.substring(10,12);
    	}
    	if(!reportTime.equals("") || reportTime.length()==12) {
    		reportTime=reportTime.substring(0,4)+"-"+reportTime.substring(4,6)+"-"+reportTime.substring(6,8)+" "+reportTime.substring(8,10)+":"+reportTime.substring(10,12);
    	}

    	String JSON = "{"
    		// 상황구분
		+	"\"status\" : \"" + StrUtil.chkNull((String)param.get("status")) + "\","

			// 발표시각
		+	"\"ReportTime\" : \"" + reportTime + "\","

			// 발생시각
		+	"\"EventTime\" : \"" + eventTime + "\","

			// 세종시와의 거리
		+	"\"distance\" : \"" + StrUtil.chkNull((String)param.get("distance")) + "\","

			// 위도
		+	"\"Latitude\" : \"" + StrUtil.chkNull((String)param.get("Latitude")) + "\","

			// 경도
		+	"\"Longitude\" : \"" + StrUtil.chkNull((String)param.get("Longitude")) + "\","

			// 규모
		+	"\"Magnitude\" : \"" + StrUtil.chkNull((String)param.get("Magnitude")) + "\","

			// 발생위치
		+	"\"Location\" : \"" + StrUtil.chkNull((String)param.get("Location")) + "\","

			// 참고사항
		+	"\"instruction\" : \"" + StrUtil.chkNull((String)param.get("instruction")) + "\","

			// 발생깊이
		+	"\"Depth\" : \"" + StrUtil.chkNull((String)param.get("Depth")) + "\""

//			// 진도구분
//		+	"\"IntensityType\" : \"" + StrUtil.chkNull((String)param.get("IntensityType")) + "\","
//
//			// 진도등급
//		+	"\"IntensityDesc\" : \"" + StrUtil.chkNull((String)param.get("IntensityDesc")) + "\""


		+"}";

        return JSON;
    }

    /**
     * HashMap 데이터를 JSON String 으로 리턴합니다.(지진통보 데이터)
     *
     * @param param
     * @return
     */
    public static String setPreEarthJson(HashMap<Object, Object> param){
    	String eventTime=StrUtil.chkNull((String)param.get("orgtime"));
    	String reportTime=StrUtil.chkNull((String)param.get("rtptime"));
    	if(!eventTime.equals("") || eventTime.length()==12) {
    		eventTime=eventTime.substring(0,4)+"-"+eventTime.substring(4,6)+"-"+eventTime.substring(6,8)+" "+eventTime.substring(8,10)+":"+eventTime.substring(10,12);
    	}
    	if(!reportTime.equals("") || reportTime.length()==12) {
    		reportTime=reportTime.substring(0,4)+"-"+reportTime.substring(4,6)+"-"+reportTime.substring(6,8)+" "+reportTime.substring(8,10)+":"+reportTime.substring(10,12);
    	}

    	String JSON = "{"
    		// 상황구분
		+	"\"status\" : \"" + StrUtil.chkNull((String)param.get("status")) + "\","

			// 발표시각
		+	"\"ReportTime\" : \"" + reportTime + "\","

			// 발생시각
		+	"\"EventTime\" : \"" + eventTime + "\","

			// 세종시와의 거리
		+	"\"distance\" : \"" + StrUtil.chkNull((String)param.get("distance")) + "\","

			// 위도
		+	"\"Latitude\" : \"" + StrUtil.chkNull((String)param.get("lat")) + "\","

			// 경도
		+	"\"Longitude\" : \"" + StrUtil.chkNull((String)param.get("lon")) + "\","

			// 규모
		+	"\"Magnitude\" : \"" + StrUtil.chkNull((String)param.get("mag")) + "\","

			// 발생위치
		+	"\"Location\" : \"" + StrUtil.chkNull((String)param.get("area")) + "\","

			// 발생깊이
		+	"\"Depth\" : \"" + StrUtil.chkNull((String)param.get("depth")) + "\""

//			// 진도구분
//		+	"\"IntensityType\" : \"" + StrUtil.chkNull((String)param.get("Itype")) + "\","
//
//			// 진도등급
//		+	"\"IntensityDesc\" : \"" + StrUtil.chkNull((String)param.get("Idesc")) + "\""


		+"}";

        return JSON;
    }
    /**
     * 두지점의 좌표를 가지고 위치를 구합니다.
     *
     * @param param
     * @return
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);

        dist = dist * 60 * 1.1515;

        dist = dist * 1.609344;
        dist= Math.round(dist*100)/100.0;
        return (dist);
    }
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }


    /**
     * 주소를 받아서 KAKAO API 호출해서 해당 주소의 경위도 좌표를 반환한다
     *
     * @param addr(주소)
     * @return 경위도 좌표(ex 127,37)
     */
    public static String getAddrFromKaKao(String addr, String dmzUrl) {
		String lat_lan = "";
		try {
			URL url;
			//proxy가 없을 때
			if("".equals(dmzUrl)){
				url = new URL(dmzUrl + addrSrchUrl + URLEncoder.encode(addr, "UTF-8"));
			}else{
				url = new URL(dmzUrl + addrSrchUrlOfProxy + URLEncoder.encode(addr, "UTF-8"));
			}

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000); // 서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", "KakaoAK " + apiKey);
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);

			int responseCode = con.getResponseCode();
			//System.out.println("responseCode : " + responseCode);

			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "utf-8"));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();

			JSONParser jsonParse = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParse.parse(res.toString());
			JSONArray docArray = (JSONArray) jsonObj.get("documents");
			JSONObject addrObj = (JSONObject) docArray.get(0);
			JSONObject address = (JSONObject) addrObj.get("address");
			lat_lan = address.get("x") + "," + address.get("y");
			//System.out.println("sd>> " + res.toString());

		} catch (Exception e) {
//			e.printStackTrace();
		}
		return lat_lan;

	}


}
