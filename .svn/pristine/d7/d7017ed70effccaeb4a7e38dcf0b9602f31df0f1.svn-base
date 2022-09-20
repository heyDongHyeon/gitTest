<%@page import="geomex.xeus.eventmonitor.service.EventDmtiaUserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<EventDmtiaUserVo> list = (ArrayList<EventDmtiaUserVo>) request.getAttribute("result");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.dmtia.js"></script>
<div class="searchWrapper mCustomScrollbar" data-mcs-theme="minimal-dark">

    <p class="searchTitle" style="width:49%; display: inline-block;">치매어르신 목록</p>
    <div class="tRight" style="width: 49%; display: inline-block;">
		<button id="resetBtn" class="whiteBtn" style="width: 105px; height: 28px;">어르신 위치보기</button>
	</div>
    <table id="searchTable" class="searchTable">
    	<thead>
	        <tr>
	            <th>성함</th>
	            <th>성별</th>
	            <th>연락처</th>
	            <th>보호자 연락처</th>
	            <th>경로확인</th>
	        </tr>
    	</thead>
    	<tbody>
<%
for(int i=0; i<list.size(); i++){
	String gender = "";
	if("M".equals(list.get(i).getDmtiaGender())) gender = "남";
	if("W".equals(list.get(i).getDmtiaGender())) gender = "여";
%>
	        <tr class="tCenter">
	            <td><%= list.get(i).getDmtiaNm() %></td>
	            <td><%= gender %></td>
	            <td><%= list.get(i).getDmtiaPhone() %></td>
	            <td><%= list.get(i).getProtectorPhone() %></td>
	            <td><button class="locBtn" k="<%= list.get(i).getDmtiaNm() %>"></button></td>
	        </tr>
<%
}
%>
    	</tbody>
    </table>

</div>