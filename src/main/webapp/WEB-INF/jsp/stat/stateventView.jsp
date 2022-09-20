<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.log.service.LogStatVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
//ArrayList<LogStatVo> listdsc = (ArrayList<LogStatVo>) request.getAttribute("dsc");

/* HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String year = map.get("year");
if(year == null) year = ""; */
ArrayList<Integer> year = (ArrayList<Integer>) request.getAttribute("year");
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.log.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.stat.css">

<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndms.chart.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.stat.js"></script>
<script type="text/javascript">

</script>
<div id="stat_wrap">
    <div id="wrap">
        <div id="title">이벤트 통계</div>
        <div id="search">
            <span>통계 종류  </span>
            <select id="stat_bet_select" target="stat_bec_select" class="stat_select_obj stat_event">
           		<option value="">선택해주세요</option>
                <option value="1">이벤트 별</option>
                <option value="2">재난 별</option>
            </select>
            <span>이벤트 대상  </span>
            <select id="stat_bec_select" target="stat_sec_select" class="stat_select_obj stat_event">
            	<option value="">선택해주세요</option>
                <option value="사회재난" k="2" class="non">사회재난</option>
                <option value="자연재난" k="2" class="non">자연재난</option>
                <option value="지능형 CCTV" k="1" class="non">지능형 CCTV 이벤트</option>
                <option value="기상 예경보" k="1" class="non">기상 예경보</option>
            </select>
            <span>이벤트 분류  </span>
            <select id="stat_sec_select" target="" class="stat_select_obj stat_event">
				<option value="">선택해주세요</option>
                <option value="배회" k="지능형 CCTV" class="non" typ="건">배회</option>
                <option value="금지된 방향 이동" k="지능형 CCTV" class="non" typ="건">금지된 방향 이동</option>
                <option value="멈춤" k="지능형 CCTV" class="non" typ="건">멈춤</option>
                <option value="버려짐" k="지능형 CCTV" class="non" typ="건">버려짐</option>
                <option value="연기" k="지능형 CCTV" class="non" typ="건">연기</option>
                <option value="불꽃" k="지능형 CCTV" class="non" typ="건">불꽃</option>
                <option value="추돌사고" k="지능형 CCTV" class="non" typ="건">추돌사고</option>
                <option value="대설 주의보" k="기상 예경보" class="non" typ="건">대설 주의보</option>
                <option value="대설 경보" k="기상 예경보" class="non" typ="건">대설 경보</option>
                <option value="홍수 주의보" k="기상 예경보" class="non" typ="건">홍수 주의보</option>
                <option value="홍수 경보" k="기상 예경보" class="non" typ="건">홍수 경보</option>

                <!-- <option value="소방 긴급구조" k="NDMS 이벤트"  class="non" typ="건">소방 긴급구조 발생</option>
                <option value="AWS 관측 정보" k="NDMS 이벤트" class="non" typ="건">AWS 임계치 초과</option>
                <option value="수위 정보" k="NDMS 이벤트" class="non" typ="건">강수 임계치 초과</option>
                <option value="댐 수위 정보" k="NDMS 이벤트" class="non" typ="건">댐 수위 임계치 초과</option> -->
            </select>

            <span>년도  </span>
            <select id="stat_year" class="stat_select_obj stat_event">
                <option value="">전체</option>
				<% for(int i=0; i<year.size(); i++){ %>
					<option value="<%=year.get(i)%>"><%=year.get(i)%>년</option>
				<% } %>
            </select>
            <span>월  </span>
            <select id="stat_month" class="stat_select_obj stat_event">
                <option value="">전체</option>
                <option value="01" k="mm" class="non">1월</option>
                <option value="02" k="mm" class="non">2월</option>
                <option value="03" k="mm" class="non">3월</option>
                <option value="04" k="mm" class="non">4월</option>
                <option value="05" k="mm" class="non">5월</option>
                <option value="06" k="mm" class="non">6월</option>
                <option value="07" k="mm" class="non">7월</option>
                <option value="08" k="mm" class="non">8월</option>
                <option value="09" k="mm" class="non">9월</option>
                <option value="10" k="mm" class="non">10월</option>
                <option value="11" k="mm" class="non">11월</option>
                <option value="12" k="mm" class="non">12월</option>
                <!-- <option value="2017">2017</option>
                <option value="2016">2016</option> -->
            </select>
            <button id="stat_event_search_btn" class="stat_btn">검 색</button>
            <button id="stat_excel_btn" class="stat_btn stat_gray_btn">Excel 다운로드</button>
            <button id="stat_change_btn" class="stat_btn stat_gray_btn">테이블 조회</button>
        </div>
        <div id="content">
        	<div id="stat_table_title"></div>
        	<div id="stat_chart_type"></div>
        	<div id="stat_table_type">
        		<table id="userList">
        			<colgroup>
        			</colgroup>
        			<thead></thead>
        			<tbody></tbody>
        		</table>
        	</div>
        </div>

    </div>
</div>
