<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvModelVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ImageVo"%>
<%@ page import="geomex.xeus.equipmgr.service.DisbordVo"%>
<%@ include file="../common.jsp" %>
<%
// CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

// ArrayList<ImageVo> img = (ArrayList<ImageVo>) request.getAttribute("img");

// HashMap<String, String> gbn = cde.convertCodeGrpToAllCde("C14");
// Set<String> gbnKey = gbn.keySet();
// Iterator<String> gbnItr = gbnKey.iterator();

// HashMap<String, String> vms = cde.convertCodeGrpToAllCde("C80");
// Set<String> vmsKey = vms.keySet();
// Iterator<String> vmsItr = vmsKey.iterator();

ArrayList<ImageVo> img = (ArrayList<ImageVo>) request.getAttribute("img");
DisbordVo vo,mgrObj;
if(request.getAttribute("vo")==null){
	vo=null;
}
else{
	vo = (DisbordVo) request.getAttribute("vo");
}
mgrObj = (DisbordVo) request.getAttribute("mgr");
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");
ArrayList<CctvModelVo> model = (ArrayList<CctvModelVo>) request.getAttribute("model");
ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.disbord.reg.js"></script>


<script>
$(document).ready(function(){
	var $installCost = $("#overlay-east-contents").find("#regTable").find("#installCost")
	var selectedValue = $installCost.attr("k");

	$installCost.val(selectedValue).prop("selected", true);

});
</script>
<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">전광판 관리</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
    <!--
    180411 이은규
    미등록리스트 없이 신규등록인 것처럼 보이기 위해 변경
     -->
	<!-- <button class="blueBtn" id="unregCctv">미등록 조회</button> -->
    <button class="blueBtn" id="unregVoice">신규 등록</button>
</div>

<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark">

	<div class="dropBox" style="font-size: 14px;">원하시는 시설물을 이곳에 드롭 해주세요!</div>

<% if(vo == null){ %>
	<script>$(".dropBox").show();</script>
<% }else{ %>
	<table id="regTable" class="list" cellspacing="0">
		<tr>
			<th>전광판명</th>
			<td colspan="3">
				<input type="text" id="bordNm" name="전광판명" class="sendData wide" value="<%= StrUtil.chkNull(vo.getBordNm()) %>">
			</td>
		</tr>
		<tr>
			<th>시설구분</th>
            <td colspan="3">
                <input type="text" id="gbnNm" name="시설구분" class="sendData wide" value="<%= StrUtil.chkNull(vo.getGbnNm()) %>">
            </td>
		</tr>
		<tr>
			<th>제조사</th>
			  <td colspan="3">
                <input type="text" id="makerNm" name="cctvNm" class="sendData wide" value="<%= StrUtil.chkNull(vo.getMakerNm()) %>">
            </td>
		</tr>
		<tr>
			<th>기술규격</th>
		 	  <td colspan="3">
               	<input type="text" id="tecSpe" name="cctvNm" class="sendData wide" value="<%= StrUtil.chkNull(vo.getTecSpe()) %>">
           	  </td>
		</tr>






		<tr>
			<th>전화번호</th>
		 	  <td colspan="3">
               	<input type="text" id="phoneNumber" name="phoneNumber" class="sendData wide" value="<%= StrUtil.chkNull(vo.getPhoneNumber()) %>">
           	  </td>
		</tr>
		<tr>
			<th>표출방식</th>
		 	  <td colspan="3">
				<input type="text" id="expressionMethod" name="expressionMethod" class="sendData wide" value="<%= StrUtil.chkNull(vo.getExpressionMethod()) %>">
           	  </td>
		</tr>
		<tr>
			<th>규격</th>
		 	  <td colspan="3">
               	<input type="text" id="standard" name="standard" class="sendData wide" value="<%= StrUtil.chkNull(vo.getStandard()) %>">
           	  </td>
		</tr>
		<tr>
			<th>설치일자</th>
		 	  <td colspan="3">
               	<input type="date" style="width: initial;" id="installDate" name="installDate" class="sendData wide" value="<%= StrUtil.chkNull(vo.getInstallDate()) %>">
           	  </td>
		</tr>
		<tr>
			<th>설치비</th>
		 	  <td colspan="3">
		 	  	<select id="installCost" name="installCost" class="sendData wide" k="<%= StrUtil.chkNull(vo.getInstallCost()) %>">
		 	  		<option value="">선 택</option>
		 	  		<option value="시스템설치비">시스템설치비</option>
		 	  		<option value="기반설치비">기반설치비</option>
		 	  		<option value="계">계</option>
		 	  	</select>
<%--                	<input type="text" id="installCost" name="installCost" class="sendData wide" value="<%= StrUtil.chkNull(vo.getInstallCost()) %>"> --%>
           	  </td>
		</tr>
		<tr>
			<th>비고</th>
		 	  <td colspan="3">
               	<input type="text" id="note" name="note" class="sendData wide" value="<%= StrUtil.chkNull(vo.getNote()) %>">
           	  </td>
		</tr>










        <tr>
        	<th>주소</th>
			  <td colspan="3">
               	<input type="text" id="locDesc" name="awsLocAddrDetail" class="sendData wide" value="<%= StrUtil.chkNull(vo.getLocDesc()) %>">
           	  </td>
        </tr>
        <tr>
			<th>위도</th>
			<td>
				<input type="text" id="lat" name="위도" class="sendData wide loc" style="color: rgb(255, 255, 255);" value="<%= StrUtil.chkNull(vo.getTmLat()) %>">
			</td>
			<th>경도</th>
			<td>
				<input type="text" id="lng" name="경도" class="sendData wide loc" style="color: rgb(255, 255, 255);" value="<%= StrUtil.chkNull(vo.getTmLon()) %>">
			</td>
		</tr>
        <tr>
			<th>메모</th>
			<td colspan="3">
				<input type="text" id="rmark" name="conIdsa" class="sendData wide" value="<%= StrUtil.chkNull(vo.getRmark()) %>">
			</td>
		</tr>
			<tr style="display:none;">
			<th>pk</th>
			<td>
				<input type="text" id="mgrNo" name="conPwd" class="sendData wide" value="<%= StrUtil.chkNull(vo.getMgrNo()) %>">
			</td>
		</tr>
		<tr>
			<th>소속</th>
			<th>직위/직급</th>
			<th>성명</th>
			<th>일반전화/휴대폰</th>
		</tr>
<%
    	String str=mgrObj.getStrMgr();
		if("".equals(str)){
			str=" / / / / / , / / / / / , / / / / / ";
		}
    	String arr[]=str.split(",");
    	String mgr[]=null;
    	for(int i=0; i<arr.length; i++)
    	{
    		mgr=arr[i].split("/");
    		if(mgr!=null)
    		{
%>
	<tr>
		<td align="center">
			<input type="text" id="mgrBelong<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[1] %>" readOnly>
		</td>
		<td align="center" width="180px">
			<input type="text" id="mgrSpot<%=i+1%>" name="lat" class="sendData wide"  style="color: rgb(255, 255, 255); text-align:center; width:70px;" value="<%= mgr[0] %>"  readOnly>
			<span class="span_t">/</span>
			<input type="text" id="mgrLevel<%=i+1%>" name="lat" class="sendData wide"  style="color: rgb(255, 255, 255); text-align:center; width:70px" value="<%= mgr[2] %>" readOnly>
		</td>
		<td align="center">
			<input type="text" id="mgrNm<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[3] %>" readOnly>
		</td>
		<td align="center">
			<input type="text" id="mgrTel<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[4] %>" readOnly>
			<input type="text" id="mgrPhone<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[5] %>" readOnly>
		</td>
	</tr>

			<%}

	}%>

		<tr>
            <th colspan="4" class="pointer tCenter hidden selectCancel">선택을 취소하려면 여기를 눌러주세요.</th>
        </tr>
<!-- 		<tr> -->
<!-- 			<th colspan="4">사진</th> -->
<!-- 		</tr> -->
<!-- 		<tr class="tCenter"> -->
<!-- 			<td colspan="4" id="imgWrapper"> -->
<!-- 				<form class="hidden" id="hiddenForm" method="POST" enctype="multipart/form-data"> -->
<%-- 					<input type="text" name="k" id="k" class="hidden" value="<%= vo.getMgrNo() %>"> --%>
<!-- 					<input type="text" name="i" id="i" class="hidden" value="0"> -->
<!-- 					<input type="text" name="p" id="p" class="hidden" value="\upload\nms\"> -->
<!--                     <input type="text" name="p" id="p" class="hidden" value="\nms\"> -->
<!-- 					<input type="file" name="uploadImg" id="uploadImg" class="hidden" accept="image/gif, image/jpeg, image/png"> -->
<!-- 				</form> -->
<%-- <% if(img == null){ %> --%>
<!-- 				<p style="padding: 50px 0px;"><b>이미지가 존재하지 않습니다.</b></p> -->
<%-- <% }else{ %> --%>
<%-- 	<% for(int i=0; i<img.size(); i++){ %> --%>
<!-- 				<span class="imgBox"> -->
<%-- 					<img class="imgs" alt="<%= img.get(i).getFileNm() %>" src="../image/getImage.do?mgrSeq=<%= img.get(i).getMgrSeq() %>" k="<%= img.get(i).getMgrSeq() %>" onerror="this.src='../res/img/no_img.png'"> --%>
<%-- 					<img class="close" src="../res/img/close.png" k="<%= img.get(i).getMgrSeq() %>"> --%>
<!-- 				</span> -->
<%-- 	<% } %> --%>
<%-- <% } %> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<%-- 			<td colspan="4" class="tCenter" k="<%= vo.getMgrNo() %>"> --%>
<!-- 				<button class="whiteBtn" id="uploadBtn">사진 추가</button> -->
<!--                 <button class="whiteBtn imgBtn" id="uploadBtn"><img class="close" height="20px" width="20px" src="/xeus/res/img/add.png"></button> -->
<!-- 			</td> -->
<!-- 		</tr> -->
   </table>

    <div class="btnDiv hidden" k="<%= vo.getMgrNo() %>" >
        <button class="whiteBtn" id="mapClickBtn">지도에서 위치 선택</button>
        <button class="whiteBtn" id="saveBtn">저장</button>
        <button class="whiteBtn" id="delBtn">삭제</button>
    </div>
 <% }%>
</div>