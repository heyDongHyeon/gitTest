package geomex.xeus.util.code;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import com.vividsolutions.jts.io.ParseException;

/**
 * <PRE>
 * 파일명   : DateUtil.java
 * 파일설명 : 날짜 유틸리티 클래스
 * 수정이력 :
 *       2013. 6. 19.  김경호  : 최초작성
 *       2014. 9. 22.  이규하  : formatDate 함수 수정 및 추가
 *       2015. 11.19.  이주영  : parseTime 메소드 추가
 *
 * </PRE>
 *
 */
public class DateUtil {

    /**
     * 년월일시분초 형태의 문자열을 2007-11-10 23:05:03 형식으로 변환한다.
     *
     * @param ymdhms 년월일시분초 문자열
     * @return String 2007-11-10 23:05:03 형식의 문잘열
     */
    public static String formatDate(String ymdhms) {
    	if(ymdhms == null || "".equals(ymdhms) || "".equals(ymdhms.trim())){
    		return "";
    	}else{
    		int dateLen = ymdhms.length();
    		StringBuilder rtn = new StringBuilder(19);

    		if ( dateLen > 0 ) rtn.append(ymdhms.substring(0, 4));
    		if ( dateLen > 4 ) rtn.append("-").append(ymdhms.substring(4, 6));
    		if ( dateLen > 6 ) rtn.append("-").append(ymdhms.substring(6, 8));
    		if ( dateLen > 8 ) rtn.append(" ").append(ymdhms.substring(8, 10));
    		if ( dateLen > 10 ) rtn.append(":").append(ymdhms.substring(10, 12));
    		if ( dateLen > 12 ){
    			rtn.append(":").append(ymdhms.substring(12, 14));
    		} else {
        		rtn.append(":").append("00");

        	}

    		return rtn.toString();
    	}
    }

    /**
     * 년월일시분초 형태의 문자열을 2007-11-10 23:05:03 형식으로 변환한다.
     *
     * @param ymdhms 년월일시분초 문자열
     * @param split	년월일시분초 중 앞에서부터 사용할 길이
     * @return String 2007-11-10 23:05:03 형식의 문잘열
     */
    public static String formatDate(String ymdhms, int splLen) {
    	int dateLen = ymdhms.length();
    	StringBuilder rtn = new StringBuilder(19);

    	if ( dateLen > 0 && splLen > 0) rtn.append(ymdhms.substring(0, 4));
    	if ( dateLen > 4 && splLen > 4 ) rtn.append("-").append(ymdhms.substring(4, 6));
    	if ( dateLen > 6 && splLen > 6 ) rtn.append("-").append(ymdhms.substring(6, 8));
    	if ( dateLen > 8 && splLen > 8 ) rtn.append(" ").append(ymdhms.substring(8, 10));
    	if ( dateLen > 10 && splLen > 10 ) rtn.append(":").append(ymdhms.substring(10, 12));
    	if ( dateLen > 12 && splLen > 12 ) rtn.append(":").append(ymdhms.substring(12, 14));


        return rtn.toString();
    }

    /**
     * 현재날짜의 요일 문자열을 반환한다
     *
     * @return String 요일
     *
     */
    public static String getYoil() {
        Calendar cal = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
        // 1     2     3     4     5     6     7
        final String[] week = { "일", "월", "화", "수", "목", "금", "토" };
        return week[cal.get(Calendar.DAY_OF_WEEK) - 1] + "요일";

    }

    /**
     * 년월일시분초 문자열을 long 형 Timestamp로 바꾸어 변환한다.
     *
     * @param ymdhms 년월일시분초 문자열
     * @return long TimeStamp 값
     */
    public static long toTimeMillisSec(String ymdhms) {
        GregorianCalendar cal = new GregorianCalendar(Integer.parseInt(ymdhms
            .substring(0, 4)),
            Integer.parseInt(ymdhms.substring(4, 6)) - 1,
            Integer.parseInt(ymdhms.substring(6, 8)),
            Integer.parseInt(ymdhms.substring(8, 10)),
            Integer.parseInt(ymdhms.substring(10, 12)),
            Integer.parseInt(ymdhms.substring(12, 14)));
        return cal.getTimeInMillis();
    }

    /**
     * 현재 년도 문자열을 얻는다.
     *
     * @return String 현재 년월일 문자열
     */
    public static String getStrYear() {
    	GregorianCalendar cal = new GregorianCalendar();
    	return new StringBuilder(8)
    			.append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
    			.toString();
    }

    /**
     * 현재 년월일 문자열을 얻는다.
     *
     * @return String 현재 년월일 문자열
     */
    public static String getStrDay() {
        GregorianCalendar cal = new GregorianCalendar();
        return new StringBuilder(8)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .toString();
    }

    /**
     * 현재 년월일 문자열을 얻는다.
     *
     * @return String 현재 년월일 문자열
     */
    public static String getStrDay(String typ) {
    	GregorianCalendar cal = new GregorianCalendar();
    	return new StringBuilder(8)
    			.append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000")+typ)
    			.append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00")+typ)
    			.append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
    			.toString();
    }

    /**
     * 어제 년월일 문자열을 얻는다.
     *
     * @return String 현재 년월일 문자열
     */
    public static String getStrYesterDay(int day) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.DATE, day);
        return new StringBuilder(8)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .toString();
    }

    /**
     * 전 년월일 문자열을 얻는다.
     *
     * @return String 전 년월일 문자열
     */
    public static String getStrYesterYMD() {
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(Calendar.MONTH, -1);
        return new StringBuilder(8)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .toString();
    }

    /**
     * long형 timestamp를 년월일 형태의 문자열로 변환한다.
     *
     * @param stamps timestamp
     * @return String 년월일 문자열
     */
    public static String getStrDay(long stamps) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(stamps);
        return new StringBuilder(8)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .toString();
    }

    /**
     * 현재 년월일시 문자열을 반환한다.
     *
     * @return String 년월일시 문자열
     */
    public static String getStrHour() {
        GregorianCalendar cal = new GregorianCalendar();
        return new StringBuilder(10)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .toString();
    }

    /**
     * long형 timestamp를 년월일시 형태의 문자열로 변환한다.
     *
     * @param stamps timestamp
     * @return String 년월일시 문자열
     */
    public static String getStrHour(long stamps) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(stamps);
        return new StringBuilder(10)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .toString();
    }

    /**
     * 현재 년월일시분 문자열을 반환한다.
     *
     * @return String 년월일시분 문자열
     */
    public static String getStrMin() {
        GregorianCalendar cal = new GregorianCalendar();
        return new StringBuilder(12)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"))
            .toString();
    }

    /**
     * long형 timestamp를 년월일시분 형태의 문자열로 변환한다.
     *
     * @param stamps timestamp
     * @return 년월일시분 문자열
     */
    public static String getStrMin(long stamps) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(stamps);
        return new StringBuilder(12)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"))
            .toString();
    }

    /**
     * 현재 년월일시분초 문자열을 반환한다.
     *
     * @return String 년월일시분초 문자열
     */
    public static String getStrSec() {
        GregorianCalendar cal = new GregorianCalendar();
        return new StringBuilder(14)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.SECOND), "00"))
            .toString();
    }

    /**
     * long형 timestamp를 년월일시분초 형태의 문자열로 반환한다.
     *
     * @param stamps timestamp
     * @return String 년월일시분초 문자열
     */
    public static String getStrSec(long stamps) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(stamps);
        return new StringBuilder(14)
            .append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
            .append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"))
            .append(StrUtil.numFormat(cal.get(Calendar.SECOND), "00"))
            .toString();
    }

    /**
     * 현재 년월일시분초 밀리초 문자열을 반환한다.
     *
     * @return String 년월일시분초 문자열
     */
    public static String getStrMilSec() {
    	GregorianCalendar cal = new GregorianCalendar();
    	return new StringBuilder(14)
    	.append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"))
    	.append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"))
    	.append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"))
    	.append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"))
    	.append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"))
    	.append(StrUtil.numFormat(cal.get(Calendar.SECOND), "00"))
    	.append(StrUtil.numFormat(cal.get(Calendar.MILLISECOND), "000"))
    	.toString();
    }

    /**
     * <pre>
     * Timestamp 값을 년월일 시분초로 변경합니다.
     * <pre>
     *
     * @param times
     * @return
     */
    public static String parseTime(String times) {
    	Calendar cal = new GregorianCalendar();
    	StringBuilder sb = null;
    	String str = null;
    	if(times != null && !"".equals(times) && times.length() > 9 && times.length() < 14){
    		if(times.length() == 10){
    			times = times + "000";
    		}
            cal.setTimeInMillis(Long.parseLong(times));
            sb = new StringBuilder(14);
            sb.append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"));
            sb.append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"));
            sb.append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"));
            sb.append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"));
            sb.append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"));
            sb.append(StrUtil.numFormat(cal.get(Calendar.SECOND), "00"));
            str = sb.toString();
    	}else{
    		str = "";
    	}
		return str;
    }

    /**
     * <pre>
     * Timestamp 값을 년월일 시분초로 변경합니다.
     * <pre>
     *
     * @param times
     * @return
     */
    public static String parseTime(long times) {
    	Calendar cal = new GregorianCalendar();
    	StringBuilder sb = null;
    	String str = null;
		cal.setTimeInMillis(times);
		sb = new StringBuilder(14);
		sb.append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"));
		sb.append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"));
		sb.append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"));
		sb.append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"));
		sb.append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"));
		sb.append(StrUtil.numFormat(cal.get(Calendar.SECOND), "00"));
		str = sb.toString();
    	return str;
    }

    /**
     * <pre>
     * Timestamp 값을 년월일 시분초로 변경합니다.
     * <pre>
     *
     * @param times
     * @param bool
     * @return
     */
    public static String parseTime(String times, boolean bool) {
    	Calendar cal = new GregorianCalendar();
    	StringBuilder sb = null;
    	String str = null;
    	if(times != null && !"".equals(times) && times.length() > 9 && times.length() < 14){
    		if(times.length() == 10){
    			times = times + "000";
    		}
    		cal.setTimeInMillis(Long.parseLong(times));
    		sb = new StringBuilder(14);
    		sb.append(StrUtil.numFormat(cal.get(Calendar.YEAR), "0000"));
    		sb.append(StrUtil.numFormat(cal.get(Calendar.MONTH) + 1, "00"));
    		sb.append(StrUtil.numFormat(cal.get(Calendar.DAY_OF_MONTH), "00"));
    		sb.append(StrUtil.numFormat(cal.get(Calendar.HOUR_OF_DAY), "00"));
    		sb.append(StrUtil.numFormat(cal.get(Calendar.MINUTE), "00"));
    		sb.append(StrUtil.numFormat(cal.get(Calendar.SECOND), "00"));
    		if(bool){
    			str = DateUtil.formatDate(sb.toString());
    		}
    	}else{
    		str = "";
    	}
    	return str;
    }

    /**
     * <pre>
     * 두 날의 차를 구합니다.
     * <pre>
     *
     * @param times
     * @param bool
     * @return long
     */
    public static long getDifferrentDate(String startDate, String endDate) {
    	if(startDate == null || "".equals(startDate)){
    		startDate = "" + System.currentTimeMillis();
    	}
    	if(endDate == null || "".equals(endDate)){
    		endDate = "" + System.currentTimeMillis();
    	}

    	long date = (Long.parseLong(endDate) - Long.parseLong(startDate)) / 60000;

    	return date;
    }

    /**
     * 연도 차이를 구합니다.
     *
     * @param y 연도
     * @return
     */
    public static ArrayList<Integer> getYearList(String y) {

    	int orgY = Integer.parseInt(y);
    	GregorianCalendar cal = new GregorianCalendar();
		ArrayList<Integer> list = new ArrayList<Integer>();
		int newY = (cal.get(Calendar.YEAR));

		for ( int i = 0; i <= (newY-orgY); i++ ) {
			list.add(orgY+i);
		}

		return list;
    }

    /**
     * 연도 차이를 구합니다.
     *
     * @param y 연도
     * @return
     */
    public static ArrayList<HashMap<String, Object>> getYearMapList(String y) {

    	int orgY = Integer.parseInt(y);
    	GregorianCalendar cal = new GregorianCalendar();
    	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    	int newY = (cal.get(Calendar.YEAR));

    	for ( int i = 0; i <= (newY-orgY); i++ ) {
    		HashMap<String, Object> map = new HashMap<String, Object>();
    		map.put("year", orgY+i);

    		list.add(map);
    	}

    	return list;
    }
    /**
     * 달 차이를 구합니다.
     *
     * @param m 달
     * @return
     */
    public static ArrayList<HashMap<String, Object>> getMonthMapList() {
    	ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
    	for(int i=1; i<=12; i++) {
			HashMap<String, Object> temp=new HashMap<String,Object>();
			temp.put("month", i);
			list.add(temp);
		}
    	return list;
    }
    
    /* 달 차이를 구합니다.
    *
    * @param m 달
    * @return
    */
	public static ArrayList<HashMap<String, Object>> getDayMapList(String y, String m) throws ParseException {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		int length = DateUtil.LastDay(Integer.parseInt(y), Integer.parseInt(m));
//		int length = DateUtil.LastDay(2019, 01);
		for (int i = 1; i <= length; i++) {
			HashMap<String, Object> temp = new HashMap<String, Object>();
			temp.put("day", i);
			list.add(temp);
		}
		return list;
	}

    /**
     * <pre>
     * 해당 월의 마지막 일자를 구한다.
     * <pre>
     *
     * @param time 시간 ( 1시 )
     * @return
     */
    public static int LastDay(int yy, int mm) throws ParseException {

    	Calendar calendar = Calendar.getInstance();

    	calendar.set(yy, (mm-1), 1);

    	int DayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    	return DayOfMonth;
    }
}
