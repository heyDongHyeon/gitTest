<%@page import="geomex.xeus.map.service.EmdVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
//ArrayList<LogStatVo> listdsc = (ArrayList<LogStatVo>) request.getAttribute("dsc");
/*
HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
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
        <div id="title" style="position: relative;left: 20px;">재난시설물 통계</div>
        <div id="search">
            <span>통계 종류  </span>
            <select id="stat_type" target="stat_sub_type" class="stat_type">
                <option value="">선택해주세요</option>
                <option value="B03105">AWS</option>
                <option value="B03101">강우계</option>
                <option value="B03103">적설계</option>
                <option value="B03102">수위계</option>
                
                <!-- <option value="2017">2017</option>
                <option value="2016">2016</option> -->
            </select>
            <span>통계 기준  </span>
            <select id="stat_sub_type" target="stat_eqb" class="stat_select_obj">
             	<option value="">선택해주세요</option>
                <option value="B03105" k="B03105" typ="°C" class="non">온도</option>
                <option value="B03105" k="B03105" typ="%" class="non">습도</option>
                <option value="B03105" k="B03105" typ="m/s" class="non">풍속</option>
                <option value="B03103" k="B03103" typ="cm" class="non">적설량</option>
                <option value="B03102" k="B03102" typ="m" class="non">수위</option>
                <option value="B03101" k="B03101" typ="mm" class="non">강우량</option>
            </select>
             <span>지역  </span>
             <select id="stat_eqb" class="stat_select_obj">
                <option value="">전체</option>
            </select>
            <span>년도  </span>
            <select id="stat_year" class="stat_select_obj">
                <option value="">전체</option>
				<% for(int i=0; i<year.size(); i++){ %>
					<option value="<%=year.get(i)%>"><%=year.get(i)%>년</option>
				<% } %>
            </select>
            <span>월  </span>
            <select id="stat_month" class="stat_select_obj">
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
            <button id="stat_ndps_search_btn" class="stat_btn">검 색</button>
            <button id="stat_excel_btn" class="stat_btn  stat_gray_btn">Excel 다운로드</button>
            <button id="stat_change_btn" class="stat_btn  stat_gray_btn">테이블 조회</button>
            <button id="external_page_btn" class="stat_btn  stat_gray_btn" style="float:right">이동</button>
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
