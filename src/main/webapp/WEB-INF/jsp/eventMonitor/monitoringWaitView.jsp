<%@page import="geomex.xeus.user.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvVo"%>
<%@ page import="geomex.xeus.user.service.UserVo"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<CctvVo> cctv = (ArrayList<CctvVo>) request.getAttribute("cctv");
ArrayList<UserVo> user = (ArrayList<UserVo>) request.getAttribute("user");
ArrayList<AuthGrpVo> auth = (ArrayList<AuthGrpVo>) request.getAttribute("auth");


HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String first = map.get("first");

String evtNm = map.get("evtNm");
if(evtNm == null) evtNm = "";
String procSt = map.get("procSt");
if(procSt == null) procSt = "";
String statEvetOutbDtm = map.get("outbPosNm");
if(statEvetOutbDtm == null) statEvetOutbDtm = "";
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
#evtWaitTable { width: 98%; }
#evtWaitTable th { border-bottom: 1px solid #868686; }
#ctntWaitTable .thhead { border-bottom: 1px solid #868686; border-top: 1px solid #868686; padding: 5px 0px; }
#ctntWaitTable .thbody { border-bottom: 1px solid #868686; padding: 5px 0px; width: 65px; }
#ctntWaitTable .ctntTd { padding-left: 10px; }
#wsStat { position: absolute; right: 10px; top: 13px; width: 10px; }

#targetStatEvetNm, #targetStatEvetOutbDtm, #targetOutbPos { vertical-align: sub; }
.pointBtn, #evetPinBtn {
	background: #333333;
    color: #fff;
    border: 1px #333333 solid;
    font-size: 12px;
    font-weight: 800;
    cursor: pointer;
    padding: 5px;
    outline: none;
}
.pointBtn[active=active], #evetPinBtn[active=active] {
	border: 1px #cccccc solid;
    background: #ffffff;
    font-size: 12px;
    font-weight: 800;
    color: #666;
    cursor: pointer;
    padding: 5px;
    outline: none;
}

#smsWrapper, #eventWrapper {
	position: absolute;
    width: 99%;
    height: 100%;
    top: 0;
    background: white;
    overflow: auto;
}

#smsWrapper #rcvId {
	width: 99%;
	cursor: pointer;
    border: none;
}

#smsWrapper #conts {
    width: 98%;
    height: 300px;
    border: none;
}

.bpopup {
    display: none;
    border: 5px solid #222c38;
    background: #222c38;
}
.bpopup button {
    background: #4C535C;
    border: 0px;
    font-size: 13px;
    padding: 7px 25px;
    color: white;
    cursor: pointer;
}

#bpop_wrap {
    padding: 0 10px;
}
#bpop_wrap h2 {
    text-align: center;
    font-size: 14px;
    margin: 20px 0px;
    color: white;
}
#bpop_wrap table {
    /* border-spacing: 0; */
    border-spacing: 1px;
    width: 300px;
}
#bpop_wrap table tr .top {
    text-align: right;
    font-size: 13px;
    padding: 20px 15px 20px 15px;
    background-color: #35404C;
    color: white;
}
#bpop_wrap table tr th {
    width: 20%;
}
#bpop_wrap table tr td {
    border-bottom: 1px solid #D5D5D5;
    border-right: 1px solid #D5D5D5;
    /* background: #D5D5D5; */
    background: white;
    padding-left: 10px;
}
#bpop_wrap table tr td.lastTd {
    background: #222c38;
}
#bpop_wrap table tr .bottom {
    text-align: right;
    font-size: 13px;
    padding: 150px 15px 150px 73px;
    background-color: #35404C;
    color: white;
}
#bpop_wrap input[type=text] {
    height: 25px;
    width: 95%;
    /* background: #D5D5D5; */
    background: white;
    padding-left: 5px;
}

#bpop_wrap input, textarea {
    border: 0;
}
#bpop_wrap select {
    height: 25px;
    width: 95%;
    border: 0;
    /* background: #D5D5D5; */
    background: white;
}
#bpop_wrap table tr td button {
    margin: 10px 0px;
}
#bpop_wrap ul li {
	list-style: none;
	margin:10px;
}
#bpop_wrap input[type=checkbox] {
    height: 18px;
    width: 18px;
    vertical-align: middle;
    /* background: #D5D5D5; */
}


#bpop_wrap .grp_ul_list li {
	margin-top:10px;
	margin-bottom:10px;
}
#bpop_wrap .user_ul_list {
	margin-left: 10px;
	display: none;
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
var statEvetOutbDtm = "<%=statEvetOutbDtm%>";
var srchDateStart = "<%=srchDateStart%>";
var srchDateEnd = "<%=srchDateEnd%>";

</script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.eventWaitList.js"></script>
<div class="searchWrapper mCustomScrollbar" data-mcs-theme="minimal-dark" onselectstart="return false">
	<input type="hidden" id="offset" value="<%= offset %>" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

	<audio id="alram"><source src='../res/sound/alram.mp3' type='audio/mpeg'></audio>
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

    <p class="searchTitle">이벤트 처리대기 리스트</p>
    <button id="excelBtn" class="blackBtn" style="width: 90px;height: 28px;position: absolute;top: 130px;right: 14px;">Excel 내보내기</button>
     <!-- <div align="right">
    	<input type="checkbox" id="targetStatEvetNm" checked="checked"><label for="targetStatEvetNm">종류</label>
    	<input type="checkbox" id="targetStatEvetOutbDtm" checked="checked"><label for="targetStatEvetOutbDtm">발생일</label>
    	<input type="checkbox" id="targetOutbPos" checked="checked"><label for="targetOutbPos">위치(X, Y, 주소)</label>
    </div> -->
    <div id="listWrap" style="/* max-height: 530px; */ height: 33%; overflow: auto;" class="mCustomScrollbar" data-mcs-theme="minimal-dark">
		<table id="evtWaitTable" cellspacing="0">
			<colgroup>
				<col width="25%">
				<col width="10%">
				<col width="40%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<thead>
				<tr>
					<th >종류</th>
					<th>상태</th>
					<th style="">주소</th>
					<th style="">발생일</th>
					<th>확인</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	<div class="paging_wrap"></div>
	<div class="ctntWrap">
		<table id="ctntWaitTable" style="width: 100%;" cellspacing="0">
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col width="35%">
			</colgroup>
			<tr>
				<th colspan="4" class="thhead">상세정보</th>
			</tr>
			<tr>
				<th class="thbody">종류</th>
				<td id="statEvetNm" class="ctntTd"></td>
				<th class="thbody">발생일</th>
				<td id="statEvetOutbDtm" class="ctntTd"></td>
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
				<th class="thbody" style="border-bottom: none;">내용</th>
				<td colspan="3" class="ctntTd">
					<textarea id="statEvetCntn" readonly="readonly"></textarea>
				</td>
			</tr>
		</table>
	</div>

	<div class="btnDiv">
		<!-- <button id="smsBtn" class="blackBtn" style="width: 80px; height: 28px;">SMS</button> -->
		<button id="editBtn" class="blackBtn" style="width: 80px; height: 28px;">관리</button>
	</div>

    <!-- <div id="graphWrap" style="border: 1px solid black; width: 100%; height: 300px;">
        <div style="width: 250px; height: 250px; border: 1px solid black; float: left;">
            그래프1
        </div>
        <div style="width: 250px; height: 250px; border: 1px solid black; float: left;">
            그래프2
        </div>
    </div> -->

	<div id="eventWrapper" class="hidden">
		<p class="searchTitle">이벤트 관리</p>
        <table>
            <tr>
                <th class="top">이벤트 고유ID</th>
                <td>
                    <input type="text" class="wide sendData" id="uSvcOutbId" />
                </td>
            </tr>
            <tr>
                <th class="top">유형</th>
                <td>
                    <select id="statEvetTypCd" class="wide sendData">
						<option value=""></option>
						<option value="NDMSWARN">NDMS</option>
						<option value="SMARTCCTV">지하차도 CCTV</option>
					<!-- 	<option value="CCTVSHER">CCTV 영상 공유</option> -->
					</select>
                </td>
            </tr>
            <tr class="top">
                <th class="top">종류</th>
                <td>
                    <select id="statEvetNm" class="wide sendData">
						<option value=""></option>
						<option value="소방 긴급구조" k="NDMSWARN">소방 긴급구조</option>
						<option value="AWS 관측 정보" k="NDMSWARN">AWS 관측 정보</option>
						<option value="강우량 정보" k="NDMSWARN">강우량 정보</option>
						<option value="수위 정보" k="NDMSWARN">수위 정보</option>
						<option value="댐 수위 정보" k="NDMSWARN">댐 수위 정보</option>
						<option value="동네예보 강수 정보" k="NDMSWARN">동네예보 강수 정보</option>
						<option value="기상특보 통보문" k="NDMSWARN">기상특보 통보문</option>
						<option value="예비특보 통보문" k="NDMSWARN">예비특보 통보문</option>
						<option value="지진현황 통보문" k="NDMSWARN">지진현황 통보문</option>
						<!-- <option value="화재" k="NDMS119">화재</option>
						<option value="구조" k="NDMS119">구조</option>
						<option value="구급" k="NDMS119">구급</option>
						<option value="기타" k="NDMS119">기타</option> -->
						<!-- <option value="CCTV 영상 공유" k="CCTVSHER">CCTV 영상 공유</option> -->
						<option value="배회" k="SMARTCCTV">배회</option>
						<option value="금지된 방향 이동" k="SMARTCCTV">금지된 방향 이동</option>
						<option value="멈춤" k="SMARTCCTV">멈춤</option>
						<option value="버려짐" k="SMARTCCTV">버려짐</option>
						<option value="연기" k="SMARTCCTV">연기</option>
						<option value="불꽃" k="SMARTCCTV">불꽃</option>
						<option value="추돌사고" k="SMARTCCTV">추돌사고</option>
						<option value="차량주차" k="SMARTCCTV">차량주차</option>
					</select>
                </td>
            </tr>
            <tr>
                <th class="top">상태</th>
                <td>
                    <select id="procSt" class="wide sendData">
						<option value=""></option>
						<option value="10">발생</option>
						<option value="40">정보변경</option>
						<!-- <option value="90">취소</option> -->
						<option value="91">종료</option>
					</select>
                </td>
            </tr>
            <tr>
                <th class="top">모의여부</th>
                <td>
                    <select id="isTest" class="wide sendData">
						<option value="N">실제</option>
						<option value="Y">모의</option>
					</select>
                </td>
            </tr>
            <tr>
                <th class="top">위치</th>
                <td>
                    <input type="text" class="wide sendData" id="outbPosNm" placeholder="예) 양재역 > 강남역 사거리 부근" />
                </td>
            </tr>
            <tr>
                <th class="top">경위도</th>
                <td>
                    <input type="text" class="sendData" id="outbPosX" placeholder="예) 127.046571357" />
                    <input type="text" class="sendData" id="outbPosY" placeholder="예) 37.4775132415" />
                    <button class="blackBtn" id="mapClickBtn">지도에서 선택</button>
                </td>
            </tr>
            <tr>
	            <th colspan="2" class="pointer tCenter hidden selectCancel">선택을 취소하려면 여기를 눌러주세요.</th>
	        </tr>
            <tr class="hidden">
                <th class="top">발생일</th>
                <td>
                    <input type="text" class="wide sendData datePicker" id="statEvetOutbDtm" />
                </td>
            </tr>
            <tr>
                <th class="top">내용</th>
                <td>
                    <textarea class="wide sendData" id="statEvetCntn" placeholder="예) 양재역 사거리 4중추돌"></textarea>
                </td>
            </tr>
            <tr class="hidden">
                <th class="top">조치 내용</th>
                <td>
                	<textarea class="wide sendData" id="statEvetActnCntn" ></textarea>
                </td>
            </tr>
          <%--   <tr>
                <th class="top">수신 대상</th>
                <td>
                	<input type="radio" name="sendTarget" value="targetGrp" checked> 그룹
                	<input type="radio" name="sendTarget" value="targetId"> 계정
                    <select id="targetGrp" class="middle sendData" style="margin-left: 30px;">
						<option value="" selected>전체</option>
<% for(int i=0; i<auth.size(); i++){ %>
						<option value="<%= auth.get(i).getAuthGrpNo() %>"><%= auth.get(i).getAuthGrpNm() %></option>
<% } %>
					</select>
                    <select id="targetId" class="middle sendData" style="margin-left: 30px; display: none;">
						<option value="" selected>전체</option>
<% for(int i=0; i<user.size(); i++){ %>
						<option value="<%= user.get(i).getUserId() %>"><%= user.get(i).getUserNm() + "(" + user.get(i).getUserId() + ")" %></option>
<% } %>
					</select>
                </td>
            </tr> --%>
            <tr class="hidden">
                <th class="top">대상CCTV</th>
                <td>
                    <select id="targetCctv" class="wide">
<% for(int i=0; i<cctv.size(); i++){ %>
						<option value="<%= cctv.get(i).getGid() %>"><%= cctv.get(i).getCctvNm() %></option>
<% } %>
					</select>
                </td>
            </tr>
            <tr class="hidden">
                <th class="top">사건번호</th>
                <td>
                    <input type="text" class="wide" id="acciNum">
                </td>
            </tr>
            <tr class="hidden">
                <th class="top">신청사유</th>
                <td>
                    <input type="text" class="wide" id="reqResn">
                </td>
            </tr>
        </table>
        <table>
            <tr align="center">
                <td class="lastTd" colspan="2" style="border: 0 !important;">
                </td>
            </tr>
        </table>
		<div class="btnDiv">
            <button id="saveBtn" class="blackBtn" style="width: 80px; height: 28px;">저장</button>
            <!-- <button id="delBtn" class="blackBtn" style="width: 80px; height: 28px;">삭제</button> -->
		</div>
	</div>

</div>