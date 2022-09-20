<%@page import="geomex.xeus.user.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvVo"%>
<%@ page import="geomex.xeus.user.service.UserVo"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<CctvVo> cctv = (ArrayList<CctvVo>) request.getAttribute("cctv");
ArrayList<UserVo> user = (ArrayList<UserVo>) request.getAttribute("user");
ArrayList<AuthGrpVo> auth = (ArrayList<AuthGrpVo>) request.getAttribute("auth");

String timeLimit = (String) request.getAttribute("timelimit");

System.out.println(timeLimit);
%>
<style>
#evtTable { width: 98%; }
#evtTable th { border-bottom: 1px solid #868686; }
#ctntTable .thhead { border-bottom: 1px solid #868686; border-top: 1px solid #868686; padding: 5px 0px; }
#ctntTable .thbody { border-bottom: 1px solid #868686; padding: 5px 0px; width: 65px; }
#ctntTable .ctntTd { padding-left: 10px; }
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
	var TIME_LIMIT = <%=timeLimit%>;
</script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.eventList.js"></script>
<div class="searchWrapper mCustomScrollbar" data-mcs-theme="minimal-dark" onselectstart="return false">

	<audio id="alram"><source src='../res/sound/alram.mp3' type='audio/mpeg'></audio>

    <p class="searchTitle">이벤트 리스트</p>
    <!-- <div align="right">
    	<input type="checkbox" id="targetStatEvetNm" checked="checked"><label for="targetStatEvetNm">종류</label>
    	<input type="checkbox" id="targetStatEvetOutbDtm" checked="checked"><label for="targetStatEvetOutbDtm">발생일</label>
    	<input type="checkbox" id="targetOutbPos" checked="checked"><label for="targetOutbPos">위치(X, Y, 주소)</label>
    </div> -->
    <div id="listWrap" style="/* max-height: 530px; */ height: 50%; overflow: auto;" class="mCustomScrollbar" data-mcs-theme="minimal-dark">
		<table id="evtTable" cellspacing="0">
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
	<div class="ctntWrap">
		<table id="ctntTable" style="width: 100%;" cellspacing="0">
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
			<tr>
				<td colspan="2" class="thhead" align="center">
					<button id="evetPinBtn" active="">이벤트 고정 시작</button>
				</td>
				<th colspan="2" class="thhead" align="center" id="evetPinTxt">새로운 재난이 발생될 경우 지도가 자동으로 이동됩니다.</th>
			</tr>
		</table>
	</div>
	<div class="btnDiv">
		<!-- <button id="smsBtn" class="blackBtn" style="width: 80px; height: 28px;">SMS</button> -->
		<button id="addBtn" class="blackBtn" style="width: 80px; height: 28px;">신규추가</button>
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

	<div id="smsWrapper" class="hidden">
		<p class="searchTitle">SMS 리스트</p>
		<div style="height: 330px; overflow: auto;">
			<table id="smsList">
				<thead>
					<tr>
						<th>수신자</th>
						<th>내용</th>
						<th>저장일</th>
						<th>삭제</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>

		<p class="searchTitle">SMS 전송</p>
		<table id="smsSend">
			<tr>
				<th width="80px">수신자</th>
				<td>
					<input type="text" id="rcvId" readonly="readonly">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea id="conts"></textarea>
				</td>
			</tr>
		</table>
		<div class="btnDiv">
			<button id="smsEditBtn" class="blackBtn" style="width: 80px; height: 28px;">내용수정</button>
			<button id="smsAddBtn" class="blackBtn" style="width: 80px; height: 28px;">신규추가</button>
			<button id="smsSendBtn" class="blackBtn" style="width: 80px; height: 28px;">SMS 전송</button>
		</div>
	</div>

	<div id="eventWrapper" class="hidden">
		<p class="searchTitle">이벤트 관리</p>
        <table>
            <tr>
                <th class="top">이벤트 고유ID</th>
                <td>
                    <input type="text" class="wide sendData" id="uSvcOutbId" readonly/>
                </td>
            </tr>
            <tr>
                <th class="top">유형</th>
                <td>
                    <select id="statEvetTypCd" class="wide sendData">
						<option value=""></option>
						<option value="NDPSWARN">기상예경보</option>
						<option value="SMARTCCTV">지하차도 CCTV</option>
						<option value="NDMS119">NDMS119</option>

					<!-- 	<option value="CCTVSHER">CCTV 영상 공유</option> -->
					</select>
                </td>
            </tr>
            <tr class="top">
                <th class="top">종류</th>
                <td>
                    <select id="statEvetNm" class="wide sendData">
						<option value=""></option>
						<option value="대설주의" k="NDPSWARN">대설 주의</option>
						<option value="대설경보" k="NDPSWARN">대설 경보</option>
						<option value="홍수주의" k="NDPSWARN">홍수 주의</option>
						<option value="홍수경보" k="NDPSWARN">홍수 경보</option>
						<!-- <option value="강우량 정보" k="NDPSWARN">강우량 정보</option>
						<option value="수위 정보" k="NDPSWARN">수위 정보</option> -->
						<!-- <option value="댐 수위 정보" k="NDMSWARN">댐 수위 정보</option>
						<option value="동네예보 강수 정보" k="NDMSWARN">동네예보 강수 정보</option>
						<option value="기상특보 통보문" k="NDMSWARN">기상특보 통보문</option>
						<option value="예비특보 통보문" k="NDMSWARN">예비특보 통보문</option>
						<option value="지진현황 통보문" k="NDMSWARN">지진현황 통보문</option> -->
						<!-- <option value="화재" k="NDMS119">화재</option>
						<option value="구조" k="NDMS119">구조</option>
						<option value="구급" k="NDMS119">구급</option>
						<option value="기타" k="NDMS119">기타</option> -->
						<!-- <option value="CCTV 영상 공유" k="CCTVSHER">CCTV 영상 공유</option> -->
						<option value="배회" k="SMARTCCTV">배회</option>
						<option value="금지된방향이동" k="SMARTCCTV">금지된 방향 이동</option>
						<option value="멈춤" k="SMARTCCTV">멈춤</option>
						<option value="버려짐" k="SMARTCCTV">버려짐</option>
						<option value="연기" k="SMARTCCTV">연기</option>
						<option value="불꽃" k="SMARTCCTV">불꽃</option>
						<option value="추돌사고" k="SMARTCCTV">추돌사고</option>
						<option value="차량주차" k="SMARTCCTV">차량주차</option>
						<option value="긴급구조" k="NDMS119">긴급구조</option>
					</select>
                </td>
            </tr>
            <tr>
                <th class="top">상태</th>
                <td>
                    <select id="procSt" class="wide sendData">
<!-- 						<option value=""></option> -->
						<option value="10">발생</option>
<!-- 						<option value="40">정보변경</option> -->
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
            <tr>
				<th class="thhead">조치내용</th>
				<td colspan="3" id="" class="ctntTd thhead">
					<textarea class="wide sendData" id="statEvetActnCntn"></textarea>
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

	<div class="bpopup" id="edit_pop_wrap">
	    <div id="bpop_wrap">
	        <h2 id="bpop_title">수신자 목록</h2>
	        <div style="height: 500px; overflow: auto; background:white;">
			<ul class="grp_ul_list">


 <%
for(int i=0; i<auth.size(); i++){
    	String authNm = auth.get(i).getAuthGrpNm();
%>
	        	<li id="grp_<%=auth.get(i).getAuthGrpNo()%>" class="grp_li_list">
	        		<input type="checkbox" value="<%= auth.get(i).getAuthGrpNo() %>" numb=""><label><%= authNm %></label>
	        		<ul class="user_ul_list" id="user_<%=auth.get(i).getAuthGrpNo()%>">
	        			 <%
							for(int j=0; j<user.size(); j++){

								if ( auth.get(i).getAuthGrpNo().equals(user.get(j).getAuthGrpNo())){
									String userInfo = user.get(j).getUserNm() + "(" + StrUtil.strTelAdd(user.get(j).getMobileNum()) + ")";
							%>
			        				<li>
			        					<input id="user_chk_<%=i+"_"+j %>" type="checkbox" value="<%= user.get(j).getUserId() %>" numb="<%= user.get(j).getMobileNum() %>">
			        					<label for="user_chk_<%=i+"_"+j %>"><%= userInfo %></label>
			        				</li>
	        			<%} } %>
					</ul>
	        	<li>
<%
}
%>
<%-- <%
for(int i=0; i<auth.size(); i++){
    	String userInfo = auth.get(i).getUserNm() + "(" + StrUtil.strTelAdd(user.get(i).getMobileNum()) + ")";
%>
	                    <input type="checkbox" value="<%= user.get(i).getUserId() %>" numb="<%= user.get(i).getMobileNum() %>"><label><%= userInfo %></label><br><br>
<%
}
%> --%>
			</ul>

			</div>
	        <table>
	            <tr align="center">
	                <td class="lastTd" colspan="2" style="border: 0 !important;">
	                    <button id="" class="sms_user_select_btn" tabindex="5">확인</button>
	                </td>
	            </tr>
	        </table>
	    </div>
	</div>

</div>