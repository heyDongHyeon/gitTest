<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.smartcity.service.EventHistVo"%>
<%@ include file="../common.jsp" %>
<%
//ArrayList<EventHistVo> list = (ArrayList<EventHistVo>) request.getAttribute("result");
//ArrayList<EventHistVo> type = (ArrayList<EventHistVo>) request.getAttribute("type");

 HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");

 String offset = map.get("offset");
//String sortCol = map.get("sortCol");
//String sortTyp = map.get("sortTyp");
String first = map.get("first");

%>
<style>
#evtHistTable { width: 100%; }
#evtHistTable th { border-bottom: 1px solid #868686; }
#ctntHistTable .thhead { border-bottom: 1px solid #868686; border-top: 1px solid #868686; padding: 5px 0px; }
#ctntHistTable .thbody { border-bottom: 1px solid #868686; padding: 5px 0px; width: 65px; }
#ctntHistTable .ctntTd { padding-left: 10px; }
#wsStat { position: absolute; right: 10px; top: 13px; width: 10px; }
.pointBtn {
	background: #333333;
    color: #ffff;
    border: 1px #333333 solid;
    font-size: 12px;
    font-weight: 800;
    cursor: pointer;
    padding: 5px;
    outline: none;
}
.pointBtn[active=active] {
	border: 1px #cccccc solid;
    background: #ffffff;
    font-size: 12px;
    font-weight: 800;
    color: #666;
    cursor: pointer;
    padding: 5px;
    outline: none;
}

</style>
<script>
	var isFirst = <%=first%>;
	var GBN = '<%=map.get("se")%>';
</script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndms.chart.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndps.data.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndps.js"></script>
<div class="searchWrapper" onselectstart="return false">

	<input type="hidden" id="offset" value="0" />
    <input type="hidden" id="max" value="" />

    <p class="searchTitle"><%=map.get("searchNm") %> 검색</p>
    <div class="ctntWrap">
		<table id="searchTable" style="width: 100%;" cellspacing="0">
			<!-- <tr>
				<th colspan="4" class="thhead">이벤트 검색</th>
			</tr> -->
			<tr>
				<th class="thhead">관측일</th>
				<td class="thhead">
					<input type="text" id="dt" class="wide datePicker sendData" value="" readOnly>
				</td>
			</tr>
			<tr>
				<th class="thhead">단말기명</th>
				<td class="thhead">
					<input type="text" id="nm" class="wide sendData" value="">
				</td>
			</tr>
		</table>
		<div class="btnDiv">
			<button id="searchBtn" class="blackBtn" style="width: 80px; height: 28px;" key="">검색</button>
		</div>
	</div>
	<%-- <div align="right" style="color: #666;">
    	<span id="count">총 <%= request.getAttribute("count") %>개의 이벤트 정보가 검색되었습니다.</span>
	</div> --%>
	<p class="searchTitle">검색 결과</p>
    <div id="listWrap" class="mCustomScrollbar" data-mcs-theme="minimal-dark" style="overflow: auto; height: 400px;">
		<table id="ndpsTable" cellspacing="0">
			<colgroup>
				<col width="25%">
				<col width="40%">
				<col width="15%">
			</colgroup>
			<thead>
				<tr>
					<th>단말기명</th>
					<th style="width: 150px;">단말기위치</th>
					<th></th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</div>