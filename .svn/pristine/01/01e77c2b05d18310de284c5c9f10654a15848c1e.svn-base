<%@page import="geomex.xeus.map.service.EmdVo"%>
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
/*
HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String year = map.get("year");
if(year == null) year = ""; */

ArrayList<HashMap<String, String>> damnm = (ArrayList<HashMap<String, String>>) request.getAttribute("damnm");
ArrayList<EmdVo> emd = (ArrayList<EmdVo>) request.getAttribute("emd");
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
        <div id="title">NDMS 통계</div>
        <div id="search">
            <span>통계 종류  </span>
            <select id="stat_type" target="stat_sub_type" class="stat_type">
                <option value="">선택해주세요</option>
                <option value="aws">AWS 측정</option>
<!--                 <option value="powlvl">지역 별 수위</option> -->
                <option value="dmmst">댐 수위</option>
                <option value="dsr">긴급구조 발생</option>
                <!-- <option value="2017">2017</option>
                <option value="2016">2016</option> -->
            </select>
            <span>통계 기준  </span>
            <select id="stat_sub_type" class="stat_select_obj">
             	<option value="">선택해주세요</option>
                <option value="at_avg_ta" k="aws" typ="°C" class="non">평균 기온</option>
                <option value="wthr_hm" k="aws" typ="%" class="non">평균 습도</option>
                <option value="wv_ws" k="aws" typ="m/s" class="non">평균 풍속</option>
                <option value="obsr_value" k="powlvl" typ="mm" class="non">강수위</option>
                <option value="tototf" k="dmmst" typ="m3/sec" class="non">방류 량</option>
                <option value="dsr_num" k="dsr" typ="건" class="non">발생건수</option>
            </select>
             <span id="stat_emd_sp">지역  </span>
             <select id="stat_emd" class="stat_select_obj">
                <option value="">전체</option>
				<% for(int i=0; i<emd.size(); i++){ %>
					<option value="<%=  emd.get(i).getEmdKorNm() %>"><%= emd.get(i).getEmdKorNm() %></option>
				<% } %>
            </select>
             <span id="stat_damnm_sp" class="non">댐명  </span>
             <select id="stat_damnm" class="stat_select_obj non">
                <option value="">전체</option>
				<% for(int i=0; i<damnm.size(); i++){ %>
					<option value="<%=  damnm.get(i).get("damnm") %>"><%= damnm.get(i).get("damnm") %></option>
				<% } %>
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
            <button id="stat_ndms_search_btn" class="stat_btn">검 색</button>
            <button id="stat_excel_btn" class="stat_btn  stat_gray_btn">Excel 다운로드</button>
            <button id="stat_change_btn" class="stat_btn  stat_gray_btn">테이블 조회</button>
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
