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

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String year = map.get("year");
if(year == null) year = "";
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.log.css">
<%-- <script type="text/javascript" src="<%= context %>/res/geomex.xeus.system.mng.log.js"></script> --%>
<script type="text/javascript">

</script>
<div id="stat_wrap">
    <div id="wrap">
        <div id="title">CCTV 통계</div>
        <div id="search">
            <span>종류 : </span>
            <select id="stat_typ_select">
                <option value="A">CCTV</option>
                <!-- <option value="2017">2017</option>
                <option value="2016">2016</option> -->
            </select>
            <span>년도 : </span>
            <select id="year">
                <option value="2018">2018</option>
                <!-- <option value="2017">2017</option>
                <option value="2016">2016</option> -->
            </select>
        </div>
        <div id="content">
        	<div id="stat_chart_type"></div>
        	<div id="stat_table_type">

            </div>
        </div>

    </div>
</div>
