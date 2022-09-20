<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvModelVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ImageVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvVo"%>
<%@ include file="../common.jsp" %>
<%
CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

ArrayList<ImageVo> img = (ArrayList<ImageVo>) request.getAttribute("img");

HashMap<String, String> gbn = cde.convertCodeGrpToAllCde("C14");
Set<String> gbnKey = gbn.keySet();
Iterator<String> gbnItr = gbnKey.iterator();

HashMap<String, String> vms = cde.convertCodeGrpToAllCde("C80");
Set<String> vmsKey = vms.keySet();
Iterator<String> vmsItr = vmsKey.iterator();

CctvVo vo = (CctvVo) request.getAttribute("vo");
CctvVo mgrObj = (CctvVo) request.getAttribute("mgr");
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");
ArrayList<CctvModelVo> model = (ArrayList<CctvModelVo>) request.getAttribute("model");
ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.cctv.reg.js"></script>
<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">CCTV 관리</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
    <!--
    180411 이은규
    미등록리스트 없이 신규등록인 것처럼 보이기 위해 변경
     -->
	<!-- <button class="blueBtn" id="unregCctv">미등록 조회</button> -->
    <button class="blueBtn" id="unregCctv">신규 등록</button>
</div>

<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark">

	<div class="dropBox" style="font-size: 14px;">원하시는 시설물을 이곳에 드롭 해주세요!</div>

<% if(vo == null){ %>
	<script>$(".dropBox").show();</script>
<% }else{ %>
	<table id="regTable" class="list" cellspacing="0">
		<tr>
			<th>명칭</th>
            <td colspan="3">
                <input type="text" id="cctvNm" name="cctvNm" class="sendData wide" value="<%= StrUtil.chkNull(vo.getCctvNm()) %>">
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
					<option value="<%= str %>" <%= vo.getGbnCd().equals(str) ? "selected" : "" %>><%= gbn.get(str) %></option>
<% } %>
				</select>
			</td>
			<th>모델</th>
			<td>
				<select id="mdMgrNo" name="mdMgrNo" class="sendData wide">
					<option value=""></option>
<% for(int i=0; i<model.size(); i++){
	String selected = vo.getMdMgrNo().equals(model.get(i).getMgrNo()) ? "selected" : ""; %>
					<option value="<%= model.get(i).getMgrNo() %>" <%= selected %>><%= model.get(i).getMakerNm() + " - " + model.get(i).getModelNm() %></option>
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
					<option value="<%= orgz.get(i).getOrgMgrNo() %>" <%= vo.getOrgMgrNo().equals(orgz.get(i).getOrgMgrNo()) ? "selected" : "" %>><%= orgz.get(i).getOrgNm() %></option>
<% } %>
				</select>
			</td>
            <th>VMS 종류</th>
            <td>
                <select id="vmsMgrNo" name="vmsMgrNo" class="sendData wide">
                    <option value=""></option>
                    <option value="VMS0000001" <% if(vo.getVmsMgrNo() != null) { %> <%= vo.getVmsMgrNo().equals("VMS0000001") ? "selected" : "" %><% } %>>RTSP</option>
<% while(vmsItr.hasNext()){
	String vmsStr = (String) vmsItr.next();%>
					<option value="VMS00000<%= vmsStr %>" <% if(vo.getVmsMgrNo() != null) { %> <%= vo.getVmsMgrNo().equals("VMS00000" + vmsStr) ? "selected" : "" %><% } %>><%= vms.get(vmsStr) %></option>
<% } %>
					
                </select>
            </td>
        </tr>
		<tr>
			<th>접속 디바이스<br>번호</th>
			<td>
				<input type="text" id="deviceId" name="deviceId" class="sendData wide" value="<%= StrUtil.chkNull(vo.getDeviceId()) %>">
			</td>
			<th>접속 채널번호</th>
			<td>
				<input type="text" id="chnlNo" name="chnlNo" class="sendData wide" value="<%= vo.getChnlNo() %>">
			</td>
		</tr>
		<%-- <tr>
			<th>옵션</th>
			<td colspan="3">
				<input type="checkbox" id="useYn" name="useYn" class="sendData" <%= "Y".equals(vo.getUseYn()) ? "checked" : "" %>>
				<label for="useYn">사용</label>
				<input type="checkbox" id="panYn" name="panYn" class="sendData" <%= "Y".equals(vo.getPanYn()) ? "checked" : "" %>>
				<label for="turnYn">회전</label>
				<input type="checkbox" id="lightYn" name="lightYn" class="sendData" <%= "Y".equals(vo.getLightYn()) ? "checked" : "" %>>
				<label for="lightYn">조명</label>
				<input type="checkbox" id="infrdYn" name="infrdYn" class="sendData" <%= "Y".equals(vo.getInfrdYn()) ? "checked" : "" %>>
				<label for="infrdYn">적외선</label>
				<input type="checkbox" id="tiltYn" name="tiltYn" class="sendData" <%= "Y".equals(vo.getTiltYn()) ? "checked" : "" %>>
				<label for="tiltYn">틸트</label>
				<input type="checkbox" id="zoomYn" name="zoomYn" class="sendData" <%= "Y".equals(vo.getZoomYn()) ? "checked" : "" %>>
				<label for="zoomYn">줌</label>
				<input type="checkbox" id="talkYn" name="talkYn" class="sendData" <%= "Y".equals(vo.getTalkYn()) ? "checked" : "" %>>
				<label for="talkYn">음성지원</label>
                <input type="checkbox" id="tourYn" name="tourYn" class="sendData" <%= "Y".equals(vo.getTourYn()) ? "checked" : "" %>>
                <label for="talkYn">투어링</label>
			</td>
		</tr> --%>
		<tr>
			<th>IP</th>
			<td>
				<input type="text" id="ipAddr" name="ipAddr" class="sendData wide" value="<%= StrUtil.chkNull(vo.getIpAddr()).trim() %>">
			</td>
			<th>Port</th>
			<td>
				<input type="text" id="portNum" name="portNum" class="sendData wide" value="<%= StrUtil.chkNull(vo.getPortNum()) %>">
			</td>
		</tr>
		<tr>
			<th>계정</th>
			<td>
				<input type="text" id="conId" name="conId" class="sendData wide" value="<%= StrUtil.chkNull(vo.getConId()) %>">
			</td>
			<th>암호</th>
			<td>
				<input type="password" id="conPwd" name="conPwd" class="sendData wide" value="<%= StrUtil.chkNull(vo.getConPwd()) %>">
			</td>
		</tr>
		<tr>
			<th>경도</th>
			<td>
				<input type="text" id="lng" name="lng" class="sendData wide" value="<%= StrUtil.chkNull(vo.getLng()) %>">
			</td>
			<th>위도</th>
			<td>
				<input type="text" id="lat" name="lat" class="sendData wide" value="<%= StrUtil.chkNull(vo.getLat()) %>">
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
				<input type="text" id="locDesc" name="locDesc" class="sendData wide" value="<%= StrUtil.chkNull(vo.getLocDesc()) %>">
			</td>
		</tr>
		<tr>
			<th>설치일자</th>
			<td>
				<input type="text" id="instDat" name="instDat" class="sendData wide" value="<%= StrUtil.chkNull(vo.getInstDat()).trim() %>" placeholder="년월일시분초">
			</td>
			<th>사업년도</th>
			<td>
				<input type="text" id="constYear" name="constYear" class="sendData wide" value="<%= StrUtil.chkNull(vo.getConstYear()) %>">
			</td>
		</tr>
		<tr>
			<th>사업명</th>
			<td colspan="3">
				<input type="text" id="constNm" name="constNm" class="sendData wide" value="<%= StrUtil.chkNull(vo.getConstNm()) %>">
			</td>
		</tr>
		<tr>
			<th>비고</th>
			<td colspan="3">
				<input type="text" id="rmark" name="rmark" class="sendData wide" value="<%= StrUtil.chkNull(vo.getRmark()) %>">
			</td>
		</tr>
	
<!-- 		<tr> -->
<!-- 			<th>소속</th> -->
<!-- 			<th>직위/직급</th> -->
<!-- 			<th>성명</th> -->
<!-- 			<th>일반전화/휴대폰</th> -->
<!-- 		</tr> -->
<% 
//     	String str=mgrObj.getStrMgr();
//         if("".equals(str)){
//         	str=" / / / / / , / / / / / , / / / / / ";
//         }
//     	String arr[]=str.split(",");
//     	String mgr[]=null;
//     	for(int i=0; i<arr.length; i++)
//     	{
//     		mgr=arr[i].split("/");
//     		if(mgr!=null)
//     		{
%>
<!-- 	<tr> -->
<!-- 		<td align="center"> -->
<%-- 			<input type="text" id="mgrBelong<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[1] %>"> --%>
<!-- 		</td> -->
<!-- 		<td align="center" width="180px"> -->
<%-- 			<input type="text" id="mgrSpot<%=i+1%>" name="lat" class="sendData wide"  style="color: rgb(255, 255, 255); text-align:center; width:70px;" value="<%= mgr[0] %>" > --%>
<!-- 			<span class="span_t">/</span> -->
<%-- 			<input type="text" id="mgrLevel<%=i+1%>" name="lat" class="sendData wide"  style="color: rgb(255, 255, 255); text-align:center; width:70px" value="<%= mgr[2] %>"> --%>
<!-- 		</td> -->
<!-- 		<td align="center"> -->
<%-- 			<input type="text" id="mgrNm<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[3] %>"> --%>
<!-- 		</td> -->
<!-- 		<td align="center"> -->
<%-- 			<input type="text" id="mgrTel<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[4] %>"> --%>
<%-- 			<input type="text" id="mgrPhone<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[5] %>"> --%>
<!-- 		</td> -->
<!-- 	</tr> -->

<%-- 			<%} --%>
		
<%-- 	}%> --%>
<%-- <% if("10".equals(vo.getGbnCd())){ %>
		<tr>
			<th>프리셋</th>
			<td colspan="3" class="tCenter">
<%	for(int i=11; i<21; i++){ %>
				<button class="whiteBtn left presetBtn" idx="<%= i %>" mgrNo="<%= vo.getMgrNo() %>"><%= i %></button>
<% } %>
			</td>
		</tr>
<% } %>
		<tr>
            <th colspan="4" class="pointer tCenter hidden selectPresetCancel">프리셋 설정을 취소하려면 여기를 눌러주세요.</th>
        </tr> --%>
		<tr>
			<th colspan="4">사진</th>
		</tr>
		<tr class="tCenter">
			<td colspan="4" id="imgWrapper">
				<form class="hidden" id="hiddenForm" method="POST" enctype="multipart/form-data">
					<input type="text" name="k" id="k" class="hidden" value="<%= vo.getMgrNo() %>">
					<input type="text" name="i" id="i" class="hidden" value="0">
					<!-- <input type="text" name="p" id="p" class="hidden" value="\upload\nms\"> -->
                    <input type="text" name="p" id="p" class="hidden" value="\nms\">
					<input type="file" name="uploadImg" id="uploadImg" class="hidden" accept="image/gif, image/jpeg, image/png">
				</form>
<% if(img == null){ %>
				<p style="padding: 50px 0px;"><b>이미지가 존재하지 않습니다.</b></p>
<% }else{ %>
	<% for(int i=0; i<img.size(); i++){ %>
				<span class="imgBox">
					<img class="imgs" alt="<%= img.get(i).getFileNm() %>" src="../image/getImage.do?mgrSeq=<%= img.get(i).getMgrSeq() %>" k="<%= img.get(i).getMgrSeq() %>" onerror="this.src='../res/img/no_img.png'">
					<img class="close" src="../res/img/close.png" k="<%= img.get(i).getMgrSeq() %>">
				</span>
	<% } %>
<% } %>
			</td>
		</tr>
		<tr>
			<td colspan="4" class="tCenter" k="<%= vo.getMgrNo() %>">
				<!-- <button class="whiteBtn" id="uploadBtn">사진 추가</button> -->
                <button class="whiteBtn imgBtn" id="uploadBtn"><img class="close" height="20px" width="20px" src="/xeus/res/img/add.png"></button>
			</td>
		</tr>
    </table>
<% } %>
    <div class="btnDiv hidden" <% if(vo != null){ %> k="<%= vo.getMgrNo() %>" <% } %>>
        <button class="whiteBtn" id="mapClickBtn">지도에서 위치 선택</button>
        <button class="whiteBtn" id="saveBtn">저장</button>
        <button class="whiteBtn" id="delBtn">삭제</button>
    </div>
</div>
