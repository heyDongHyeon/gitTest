<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.equipmgr.service.PatrolVo"%>
<%@ include file="../common.jsp" %>
<%
	ArrayList<PatrolVo> list = (ArrayList<PatrolVo>) request.getAttribute("result");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.cctv.patrol.js"></script>
<div class="searchWrapper">
	<p class="searchTitle">순찰경로 목록</p>
	<table id="listTable">
		<tr>
			<th>경로명</th>
			<th class="small tCenter">관리</th>
		</tr>
<%
if(list == null || list.size() == 0){
%>
		<tr>
			<td class="tCenter" colspan="2">등록된 정보가 없습니다.</td>
		</tr>
<%
}else{
	for(int i=0; i<list.size(); i++){
%>
		<tr>
			<td><%= list.get(i).getTitleNm() %></td>
			<td class="tCenter" k="<%= list.get(i).getGid() %>">
				<button class="blackBtn viewBtn">보기</button><!-- whiteBtn -->
				<button class="blackBtn removeBtn">삭제</button><!-- whiteBtn -->
			</td>
		</tr>
<%
	}
}
%>
    </table>

	<p class="searchTitle">순찰경로 상세정보</p>
	<table>
		<tr>
			<th class="small">경로명</th>
			<td>
				<input type="text" id="drawNm" class="wide">
			</td>
			<td class="small tCenter" style="padding:0px 0px 0px 1px;">
				<button class="blackBtn" id="saveBtn" style="width:100%; height:100%; border-width:2px;">경로저장</button><!-- whiteBtn -->
			</td>
		</tr>
		<tr>
			<td colspan="3" id="drawList"></td>
		</tr>
		<tr>
			<th colspan="3" id="drawCncl" class="hidden pointer">그리기를 종료하시려면 여기를 눌러주세요.</th>
		</tr>
	</table>
	<div class="btnDiv">
	   <button class="blackBtn" id="drawBtn">경로 그리기</button>
	</div>

	<p class="searchTitle">순찰감시 설정</p>
	<table>
		<tr>
			<th>이동간격</th>
			<td><input type="text" class="wide tRight" id="moveSec"></td>
			<td class="tCenter bold" style="width: 30px;">초</td>
		</tr>
		<tr>
			<th>검색반경</th>
			<td><input type="text" class="wide tRight" id="moveBuffer"></td>
			<td class="tCenter bold" style="width: 30px;">M</td>
		</tr>
		<tr>
			<th>표시CCTV</th>
			<td><input type="text" class="wide tRight" id="showCnt"></td>
			<td class="tCenter bold" style="width: 30px;">대</td>
		</tr>
		<!-- 171212 -->
		<!-- <tr>
			<td colspan="3" class="tCenter noneBack">
				<button class="blackBtn">순찰감시 시작</button>
			</td>
		</tr> -->
	</table>
	<div class="btnDiv">
	   <button class="blackBtn" id="patrolStart">순찰감시 시작</button>
	   <button class="blackBtn hidden" id="patrolStop">순찰감시 종료</button>
	</div>
</div>