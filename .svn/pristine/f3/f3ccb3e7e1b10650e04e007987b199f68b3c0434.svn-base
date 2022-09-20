<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.equipmgr.service.StatusVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<StatusVo> list = (ArrayList<StatusVo>) request.getAttribute("result");

CodeConvertor cde = (CodeConvertor) request.getAttribute("code");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.nms.monitor.js"></script>
<!-- <script>
(function(){
	XeusLayer.createLegend("#legendWrap");
})();
</script> -->
<style>
#listWrap {
	max-height: 250px;
}
</style>
<div class="searchWrapper mCustomScrollbar" data-mcs-theme="minimal-dark">

    <p class="searchTitle">시설물 상태 모니터링</p>
    <div class="tCenter blankBottom">
        <span>갱신주기</span>
        <select class="middle" id="scdVal">
            <option value="5000">5 seconds</option>
            <option value="10000">10 seconds</option>
            <option value="15000">15 seconds</option>
            <option value="20000">20 seconds</option>
            <option value="30000">30 seconds</option>
        </select>
        <button class="blackBtn" id="intervalBtn">갱신 시작</button>
        <button class="blackBtn hidden" id="intervalStopBtn">갱신 종료</button>
    </div>

    <div id="listWrap" class="mCustomScrollbar" data-mcs-theme="minimal-dark">
	    <table id="listTable" class="blankBottom">
	        <thead>
		        <tr>
		            <th width="70px">장비구분</th>
		            <th>장비이름</th>
		            <th width="40px">상태</th>
		            <th width="120px">수신일</th>
		            <th width="60px">관리</th>
		        </tr>
	        </thead>
	        <tbody>
	        </tbody>
	    </table>
    </div>

	<p class="searchTitle">상세정보</p>
    <table id="detailTable"></table>

    <!-- <div id="legendWrap"></div> -->

</div>