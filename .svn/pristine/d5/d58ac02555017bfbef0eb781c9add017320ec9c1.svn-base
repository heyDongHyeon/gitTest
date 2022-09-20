<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.smartcity.service.EventHistVo"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<EventHistVo> list = (ArrayList<EventHistVo>) request.getAttribute("result");
ArrayList<EventHistVo> type = (ArrayList<EventHistVo>) request.getAttribute("type");

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String first = map.get("first");

String evtNm = map.get("evtNm");
if(evtNm == null) evtNm = "";
String procSt = map.get("procSt");
if(procSt == null) procSt = "";
String statEvetActnDtm = map.get("statEvetActnDtm");
if(statEvetActnDtm == null) statEvetActnDtm = "";
String outbPosNm = map.get("outbPosNm");
if(outbPosNm == null) outbPosNm = "";
String usrAuth = map.get("userAuth");
if(usrAuth == null) usrAuth = "";
String srchDateEnd = map.get("statEvetOutbDtmEnd");
if(srchDateEnd == null) srchDateEnd = "";
String srchDateStart = map.get("statEvetOutbDtmStart");
if(srchDateStart == null) srchDateStart = "";

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
var isFirst = false;
<% if("true".equals(first)){ %>
isFirst = true;
<%}%>

//검색 키워드 저장
var outbPosNm = "<%=outbPosNm%>";
var procSt = "<%=procSt%>";
var evtNm = "<%=evtNm%>";
var statEvetActnDtm = "<%=statEvetActnDtm%>";
var srchDateStart = "<%=srchDateStart%>";
var srchDateEnd = "<%=srchDateEnd%>";
</script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.eventHist.js"></script>
<div class="searchWrapper" onselectstart="return false">

	<input type="hidden" id="offset" value="<%= offset %>" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

    <p class="searchTitle">이벤트 검색</p>
    <div class="ctntWrap">
		<table id="searchTable" style="width: 100%;" cellspacing="0">
			<!-- <tr>
				<th colspan="4" class="thhead">이벤트 검색</th>
			</tr> -->
			<tr>
				<th class="thbody">종류</th>
				<td class="ctntTd">
					<select id="evtNm" class="wide sendData">
						<option value=""  pk="" <% if("".equals(map.get("evtNm"))) out.print("selected"); %>>전체</option>
<%-- <% if(userAuth.toLowerCase().contains("ndmswarn") || userAuth.contains("all")) { %>
						<optgroup label="NDMS">
							<option value="소방 긴급구조" pk="NDMSWARN" <% if("소방 긴급구조".equals(map.get("evtNm"))) out.print("selected"); %>>소방 긴급구조</option>
							<option value="AWS 관측 정보" pk="NDMSWARN" <% if("AWS 관측 정보".equals(map.get("evtNm"))) out.print("selected"); %>>AWS 관측 정보</option>
							<option value="강우량 정보" pk="NDMSWARN" <% if("강우량 정보".equals(map.get("evtNm"))) out.print("selected"); %>>강우량정보</option>
							<option value="수위 정보" pk="NDMSWARN" <% if("수위 정보".equals(map.get("evtNm"))) out.print("selected"); %>>수위 정보</option>
							<option value="댐 수위 정보" pk="NDMSWARN" <% if("댐 수위 정보".equals(map.get("evtNm"))) out.print("selected"); %>>기상특보</option>
							<option value="동네예보 강수 정보" pk="NDMSWARN" <% if("동네예보 강수 정보".equals(map.get("evtNm"))) out.print("selected"); %>>동네예보 강수 정보</option>
							<option value="기상특보 통보문" pk="NDMSWARN" <% if("기상특보 통보문".equals(map.get("evtNm"))) out.print("selected"); %>>기상특보 통보문</option>
							<option value="예비특보 통보문" pk="NDMSWARN" <% if("예비특보 통보문".equals(map.get("evtNm"))) out.print("selected"); %>>예비특보 통보문</option>
							<option value="지진현황 통보문" pk="NDMSWARN" <% if("지진현황 통보문".equals(map.get("evtNm"))) out.print("selected"); %>>지진현황 통보문</option>
						</optgroup>
<% } %> --%>
						<optgroup label="기상 예경보">
							<option value="대설 주의" pk="NDPSWARN" <% if("대설 주의".equals(map.get("evtNm"))) out.print("selected"); %>>대설 주의</option>
							<option value="대설 경보" pk="NDPSWARN" <% if("대설 경보".equals(map.get("evtNm"))) out.print("selected"); %>>대설 경보</option>
							<option value="홍수 주의" pk="NDPSWARN" <% if("홍수 주의".equals(map.get("evtNm"))) out.print("selected"); %>>홍수 주의</option>
							<option value="홍수 경보" pk="NDPSWARN" <% if("홍수 경보".equals(map.get("evtNm"))) out.print("selected"); %>>홍수 경보</option>
						</optgroup>
						<optgroup label="지하차도 CCTV">
							<option value="배회" pk="SMARTCCTV" <% if("배회".equals(map.get("evtNm"))) out.print("selected"); %>>배회</option>
							<option value="금지된 방향 이동" pk="SMARTCCTV" <% if("금지된 방향 이동".equals(map.get("evtNm"))) out.print("selected"); %>>금지된 방향 이동</option>
							<option value="멈춤" pk="SMARTCCTV" <% if("멈춤".equals(map.get("evtNm"))) out.print("selected"); %>>멈춤</option>
							<option value="버려짐" pk="SMARTCCTV" <% if("버려짐".equals(map.get("evtNm"))) out.print("selected"); %>>버려짐</option>
							<option value="연기" pk="SMARTCCTV" <% if("연기".equals(map.get("evtNm"))) out.print("selected"); %>>연기</option>
							<option value="불꽃" pk="SMARTCCTV" <% if("불꽃".equals(map.get("evtNm"))) out.print("selected"); %>>불꽃</option>
							<option value="추돌사고" pk="SMARTCCTV" <% if("추돌사고".equals(map.get("evtNm"))) out.print("selected"); %>>추돌사고</option>
							<option value="차량주차" pk="SMARTCCTV" <% if("차량주차".equals(map.get("evtNm"))) out.print("selected"); %>>차량주차</option>
						</optgroup>
					</select>
				</td>
				<%-- <th class="thbody">상태</th>
				<td class="ctntTd">
					<select id="procSt" class="wide sendData">
						<option value="" <% if("".equals(map.get("procSt"))) out.print("selected"); %>>전체</option>
						<option value="10" <% if("10".equals(map.get("procSt"))) out.print("selected"); %>>발생</option>
						<option value="40" <% if("40".equals(map.get("procSt"))) out.print("selected"); %>>정보변경</option>
						<option value="50" <% if("50".equals(map.get("procSt"))) out.print("selected"); %>>해제</option>
						<option value="90" <% if("90".equals(map.get("procSt"))) out.print("selected"); %>>취소</option>
						<option value="91" <% if("91".equals(map.get("procSt"))) out.print("selected"); %>>종료</option>
					</select>
				</td> --%>
				<th class="thhead">발생일</th>
				<td class="thhead">
					<input type="text" id="statEvetOutbDtmStart" class="datePickerStart sendData" value="<%= StrUtil.chkNull(map.get("statEvetOutbDtmStart")) %>" readOnly>
					~
					<input type="text" id="statEvetOutbDtmEnd" class="datePickerEnd sendData" value="<%= StrUtil.chkNull(map.get("statEvetOutbDtmEnd")) %>" readOnly>
				</td>
			</tr>
			<tr class="hidden">
				<th class="thhead">주소</th>
				<td class="thhead" colspan="3">
					<input type="text" id="outbPosNm" class="wide keyup sendData" for="#searchBtn" value="<%= StrUtil.chkNull(map.get("outbPosNm")) %>">
				</td>
			</tr>
		</table>
		<div class="btnDiv">
			<button id="searchBtn" class="blackBtn" style="width: 80px; height: 28px;">검색</button>
		</div>
	</div>
	<%-- <div align="right" style="color: #666;">
    	<span id="count">총 <%= request.getAttribute("count") %>개의 이벤트 정보가 검색되었습니다.</span>
	</div> --%>
	<p class="searchTitle">이벤트 검색 결과</p>
	<button id="excelBtn" class="blackBtn" style="width: 90px;height: 28px;position: absolute;top: 137px;right: 10px;">Excel 내보내기</button>
    <div id="listWrap" class="mCustomScrollbar" data-mcs-theme="minimal-dark" style="overflow: auto; height: 245px;">
		<table id="evtHistTable" cellspacing="0">
			<colgroup>
				<col width="25%">
				<col width="10%">
				<col width="40%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th>종류</th>
					<th>상태</th>
					<th style="width: 230px;">주소</th>
					<th style="width: 150px;">이벤트발생일</th>
					<th>확인</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<div class="paging_wrap"></div>

	<p class="searchTitle">이벤트 상세정보</p>
	<table id="ctntHistTable" style="width: 100%;" cellspacing="0">
		<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col width="35%">
			</colgroup>
		<tr>
			<th class="thbody">종류</th>
			<td id="statEvetNm" class="ctntTd"></td>
			<th class="thbody">이벤트종료일</th>
			<td id="statEvetActnDtm" class="ctntTd"></td>
		</tr>
		<tr>
			<th class="thbody">위치(X)</th>
			<td id="outbPosX" class="ctntTd"></td>
			<th class="thbody">위치(Y)</th>
			<td id="outbPosY" class="ctntTd"></td>
		</tr>
		<tr>
			<th class="thbody">위치</th>
			<td colspan="3" id="outbPosNm" class="ctntTd"></td>
		</tr>
		<tr>
			<th class="thhead">내용</th>
			<td colspan="3" id="" class="ctntTd thhead">
				<textarea id="statEvetCntn" readonly="readonly"></textarea>
			</td>
		</tr>
		<tr>
			<th class="thhead">조치내용</th>
			<td colspan="3" id="" class="ctntTd thhead">
				<textarea id="statActnCntn" readonly="readonly"></textarea>
			</td>
		</tr>
	</table>

</div>