<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvUnregVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvModelVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ImageVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvVo"%>
<%@ include file="../common.jsp" %>
<%
CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

HashMap<String, String> gbn = cde.convertCodeGrpToAllCde("C14");
Set<String> gbnKey = gbn.keySet();
Iterator<String> gbnItr = gbnKey.iterator();

HashMap<String, String> vms = cde.convertCodeGrpToAllCde("C80");
Set<String> vmsKey = vms.keySet();
Iterator<String> vmsItr = vmsKey.iterator();

ArrayList<CctvModelVo> model = (ArrayList<CctvModelVo>) request.getAttribute("model");
ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.cctv.unreg.js"></script>
<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">CCTV 신규등록</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
</div>

<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="ui-droppable">

	<div class="dropBox" style="font-size: 14px;">원하시는 시설물을 이곳에 드롭 해주세요!</div>

	<table id="regTable" class="list" cellspacing="0">
		<tr>
			<th>명칭</th>
            <td colspan="3">
                <input type="text" id="cctvNm" name="cctvNm" class="sendData wide" value="">
            </td>
			<%-- <th>사이트</th>
			<td>
            	<select id="siteMgrNo" name="siteMgrNo" class="sendData wide">
					<option value=""></option>
<% for(int i=0; i<orgz.size(); i++){ %>
					<option value="<%= orgz.get(i).getOrgMgrNo() %>"><%= orgz.get(i).getOrgNm() %></option>
<% } %>
				</select>
			</td> --%>
		</tr>
		<tr>
			<th>설치목적</th>
			<td>
				<select id="gbnCd" name="gbnCd" class="sendData wide">
					<option value=""></option>
<% while(gbnItr.hasNext()){
	String str = (String) gbnItr.next(); %>
					<option value="<%= str %>"><%= gbn.get(str) %></option>
<% } %>
				</select>
			</td>
			<th>모델</th>
			<td>
				<select id="mdMgrNo" name="mdMgrNo" class="sendData wide">
					<option value=""></option>
<% for(int i=0; i<model.size(); i++){ %>
					<option value="<%= model.get(i).getMgrNo() %>"><%= model.get(i).getMakerNm() + " - " + model.get(i).getModelNm() %></option>
<% } %>
				</select>
                <%-- <input type="text" id="mdMgrNo" name="mdMgrNo" class="sendData wide" value="<%= StrUtil.chkNull(vo.getMdMgrNo().trim()) %>" maxlength="10"> --%>
			</td>
		</tr>
		<%-- 180506 이은규
             VMS 항목 선택 추가
        <tr>
			<th>명칭</th>
			<td colspan="3">
				<input type="text" id="cctvNm" name="cctvNm" class="sendData wide" value="<%= StrUtil.chkNull(vo.getCctvNm()) %>">
			</td>
		</tr> --%>
        <tr>
        	<th>관리기관</th>
			<td>
            	<select id="orgMgrNo" name="orgMgrNo" class="sendData wide">
					<option value=""></option>
<% for(int i=0; i<orgz.size(); i++){ %>
					<option value="<%= orgz.get(i).getOrgMgrNo() %>"><%= orgz.get(i).getOrgNm() %></option>
<% } %>
				</select>
			</td>
            <th>VMS 종류</th>
            <td>
                <select id="vmsMgrNo" name="vmsMgrNo" class="sendData wide">
                    <option value=""></option>
                    <option value="VMS0000001">RTSP</option>
<% while(vmsItr.hasNext()){
	String vmsStr = (String) vmsItr.next();%>
					<option value="VMS00000<%= vmsStr %>"><%= vms.get(vmsStr) %></option>
<% } %>
					
                </select>
            </td>
        </tr>
		<tr>
			<th>접속 디바이스<br>번호</th>
			<td>
				<input type="text" id="deviceId" name="deviceId" class="sendData wide" value="">
			</td>
			<th>접속 채널번호</th>
			<td>
				<input type="text" id="chnlNo" name="chnlNo" class="sendData wide" value="">
			</td>
		</tr>
		<tr>
			<th>옵션</th>
			<td colspan="3">
				<input type="checkbox" id="useYn" name="useYn" class="sendData">
				<label for="useYn">사용</label>
				<input type="checkbox" id="panYn" name="panYn" class="sendData">
				<label for="turnYn">회전</label>
				<input type="checkbox" id="lightYn" name="lightYn" class="sendData">
				<label for="lightYn">조명</label>
				<input type="checkbox" id="infrdYn" name="infrdYn" class="sendData">
				<label for="infrdYn">적외선</label>
				<input type="checkbox" id="tiltYn" name="tiltYn" class="sendData">
				<label for="tiltYn">틸트</label>
				<input type="checkbox" id="zoomYn" name="zoomYn" class="sendData">
				<label for="zoomYn">줌</label>
				<input type="checkbox" id="talkYn" name="talkYn" class="sendData">
				<label for="talkYn">음성지원</label>
                <input type="checkbox" id="tourYn" name="tourYn" class="sendData">
                <label for="talkYn">투어링</label>
			</td>
		</tr>
		<tr>
			<th>IP</th>
			<td>
				<input type="text" id="ipAddr" name="ipAddr" class="sendData wide" value="">
			</td>
			<th>Port</th>
			<td>
				<input type="text" id="portNum" name="portNum" class="sendData wide" value="">
			</td>
		</tr>
		<tr>
			<th>계정</th>
			<td>
				<input type="text" id="conId" name="conId" class="sendData wide" value="">
			</td>
			<th>암호</th>
			<td>
				<input type="text" id="conPwd" name="conPwd" class="sendData wide" value="">
			</td>
		</tr>
		<tr>
			<th>경도</th>
			<td>
				<input type="text" id="lng" name="lng" class="sendData wide" value="">
			</td>
			<th>위도</th>
			<td>
				<input type="text" id="lat" name="lat" class="sendData wide" value="">
			</td>
		</tr>
		<tr>
            <th colspan="4" class="pointer tCenter hidden selectCancel">선택을 취소하려면 여기를 눌러주세요.</th>
        </tr>
        <!--
        /*
         * 180410 이은규
         * 지번과 도로명은 어차피 수정이 안되므로 readonly 추가
         */
         -->
		<%-- <tr>
			<th>도로명주소</th>
			<td colspan="3">
				<input type="text" id="doro" name="" class="wide" readonly="readonly">
			</td>
		</tr>
		<tr>
			<th>지번주소</th>
			<td colspan="3">
				<input type="text" id="jibun" name="" class="wide" value="<%= StrUtil.chkNull(vo.getAddr()) %>" readonly="readonly">
			</td>
		</tr> --%>
		<tr>
			<th>위치설명</th>
			<td colspan="3">
				<input type="text" id="locDesc" name="locDesc" class="sendData wide" value="">
			</td>
		</tr>
		<tr>
			<th>설치일자</th>
			<td>
				<input type="text" id="instDat" name="instDat" class="sendData wide" value="" placeholder="년월일시분초">
			</td>
			<th>사업년도</th>
			<td>
				<input type="text" id="constYear" name="constYear" class="sendData wide" value="">
			</td>
		</tr>
		<tr>
			<th>사업명</th>
			<td colspan="3">
				<input type="text" id="constNm" name="constNm" class="sendData wide" value="">
			</td>
		</tr>
		<tr>
			<th>비고</th>
			<td colspan="3">
				<input type="text" id="rmark" name="rmark" class="sendData wide" value="">
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<th>소속</th> -->
<!-- 			<th>직위/직급</th> -->
<!-- 			<th>성명</th> -->
<!-- 			<th>일반전화/휴대폰</th> -->
<!-- 		</tr> -->
<%-- <% --%>
<!-- 	for(int i=1; i<=3; i++) -->
<!-- 	{ -->
<!-- %> -->
<!-- 		<tr> -->
<!-- 			<td align="center"> -->
<%-- 				<input type="text" id="mgrBelong<%=i%>" name="lat" class="sendData wide" placeholder="과" style="color: rgb(255, 255, 255); text-align:center;" value=""> --%>
<!-- 			</td> -->
<!-- 			<td align="center" width="180px"> -->
<%-- 				<input type="text" id="mgrSpot<%=i%>" name="lat" class="sendData wide" placeholder="직위" style="color: rgb(255, 255, 255); text-align:center; width:70px;" value="" > --%>
<!-- 				<span class="span_t">/</span> -->
<%-- 				<input type="text" id="mgrLevel<%=i%>" name="lat" class="sendData wide" placeholder="직급 " style="color: rgb(255, 255, 255); text-align:center; width:70px" value=""> --%>
<!-- 			</td> -->
<!-- 			<td align="center"> -->
<%-- 				<input type="text" id="mgrNm<%=i%>" name="lat" class="sendData wide" placeholder="홍길동" style="color: rgb(255, 255, 255); text-align:center;" value=""> --%>
<!-- 			</td> -->
<!-- 			<td align="center"> -->
<%-- 				<input type="text" id="mgrTel<%=i%>" name="lat" class="sendData wide" placeholder="033-000-0000" style="color: rgb(255, 255, 255); text-align:center;" value=""> --%>
<%-- 				<input type="text" id="mgrPhone<%=i%>" name="lat" class="sendData wide" placeholder="010-000-0000" style="color: rgb(255, 255, 255); text-align:center;" value=""> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
<%
// 	}
%>
    </table>
    <div class="btnDiv">
        <button class="whiteBtn" id="mapClickBtn">지도에서 위치 선택</button>
        <button class="whiteBtn" id="saveBtn">저장</button>
    </div>

</div>