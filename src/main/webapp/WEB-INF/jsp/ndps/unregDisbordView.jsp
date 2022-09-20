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
// CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

// HashMap<String, String> gbn = cde.convertCodeGrpToAllCde("C14");
// Set<String> gbnKey = gbn.keySet();
// Iterator<String> gbnItr = gbnKey.iterator();

// HashMap<String, String> vms = cde.convertCodeGrpToAllCde("C80");
// Set<String> vmsKey = vms.keySet();
// Iterator<String> vmsItr = vmsKey.iterator();

// ArrayList<CctvModelVo> model = (ArrayList<CctvModelVo>) request.getAttribute("model");
// ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.disbord.unreg.js"></script>
<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">전광판 신규등록</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
</div>

<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="ui-droppable">

	<div class="dropBox" style="font-size: 14px;">원하시는 시설물을 이곳에 드롭 해주세요!</div>

	<table id="regTable" class="list" cellspacing="0">
		<tr>
			<th>전광판명</th>
			<td colspan="3">
				<input type="text" id="bordNm" name="전광판이름" class="sendData wide" placeholder="전광판이름 입력" style="color: rgb(255, 255, 255);" value="">
			</td>
		</tr>
		<tr>
			<th>시설구분</th>
            <td colspan="3">
                <input type="text" id="gbnNm" name="시설구분 " class="sendData wide" placeholder="시설구분 입력" style="color: rgb(255, 255, 255);" value="재난재해전광판">
            </td>
		</tr>
		<tr>
			<th>제조사</th>
			  <td colspan="3">
                <input type="text" id="makerNm" name="cctvNm" class="sendData wide" placeholder="회사이름 입력" style="color: rgb(255, 255, 255);" value="">
            </td>
		</tr>
		<tr>
			<th>기술규격</th>
		 	  <td colspan="3">
               	<input type="text" id="tecSpe" name="cctvNm" class="sendData wide" placeholder="사용한 기술 입력" style="color: rgb(255, 255, 255);" value="">
           	  </td>
		</tr>







		<tr>
			<th>전화번호</th>
		 	  <td colspan="3">
               	<input type="text" id="phoneNumber" name="phoneNumber" class="sendData wide" placeholder="전화번호 입력" value="">
           	  </td>
		</tr>
		<tr>
			<th>표출방식</th>
		 	  <td colspan="3">
				<input type="text" id="expressionMethod" name="expressionMethod" class="sendData wide" placeholder="전화번호 입력" value="">
           	  </td>
		</tr>
		<tr>
			<th>규격</th>
		 	  <td colspan="3">
               	<input type="text" id="standard" name="standard" class="sendData wide" placeholder="규격 입력" value="">
           	  </td>
		</tr>
		<tr>
			<th>설치일자</th>
		 	  <td colspan="3">
               	<input type="date" style="width: initial;" id="installDate" name="installDate" class="sendData wide" placeholder="설치일자 입력" value="">
           	  </td>
		</tr>
		<tr>
			<th>설치비</th>
		 	  <td colspan="3">
		 	  	<select id="installCost" name="installCost" class="sendData wide">
		 	  		<option value="">선 택</option>
		 	  		<option value="시스템설치비">시스템설치비</option>
		 	  		<option value="기반설치비">기반설치비</option>
		 	  		<option value="계">계</option>
		 	  	</select>
<!--                	<input type="text" id="installCost" name="installCost" class="sendData wide" placeholder="설치비 입력" value=""> -->
           	  </td>
		</tr>
		<tr>
			<th>비고</th>
		 	  <td colspan="3">
               	<input type="text" id="note" name="note" class="sendData wide" placeholder="비고 입력" value="">
           	  </td>
		</tr>









        <tr>
        	<th>주소</th>
			  <td colspan="3">
               	<input type="text" id="locDesc" name="awsLocAddrDetail" class="sendData wide" placeholder="주소 입력" style="color: rgb(255, 255, 255);" value="">
           	  </td>
        </tr>
        <tr>
			<th>위도</th>
			<td>
				<input type="text" id="lat" name="위도" class="sendData wide loc" placeholder="지도에서 위치선택 클릭" style="color: rgb(255, 255, 255);" value="">
			</td>
			<th>경도</th>
			<td>
				<input type="text" id="lng" name="경도" class="sendData wide loc" placeholder="지도에서 위치선택 클릭" style="color: rgb(255, 255, 255);" value="">
			</td>
		</tr>
        <tr>
			<th>메모</th>
			<td colspan="3">
				<input type="text" id="rmark" name="conIdsa" class="sendData wide" placeholder="비고 입력" style="color: rgb(255, 255, 255);" value="">
			</td>
		</tr>
		<tr>
			<th>소속</th>
			<th>직위/직급</th>
			<th>성명</th>
			<th>일반전화/휴대폰</th>
		</tr>
<%
	for(int i=1; i<=3; i++)
	{
%>
		<tr>
			<td align="center">
				<input type="text" id="mgrBelong<%=i%>" name="lat" class="sendData wide" placeholder="과" style="color: rgb(255, 255, 255); text-align:center;" value="">
			</td>
			<td align="center" width="180px">
				<input type="text" id="mgrSpot<%=i%>" name="lat" class="sendData wide" placeholder="직위" style="color: rgb(255, 255, 255); text-align:center; width:70px;" value="" >
				<span class="span_t">/</span>
				<input type="text" id="mgrLevel<%=i%>" name="lat" class="sendData wide" placeholder="직급 " style="color: rgb(255, 255, 255); text-align:center; width:70px" value="">
			</td>
			<td align="center">
				<input type="text" id="mgrNm<%=i%>" name="lat" class="sendData wide" placeholder="홍길동" style="color: rgb(255, 255, 255); text-align:center;" value="">
			</td>
			<td align="center">
				<input type="text" id="mgrTel<%=i%>" name="lat" class="sendData wide" placeholder="033-000-0000" style="color: rgb(255, 255, 255); text-align:center;" value="">
				<input type="text" id="mgrPhone<%=i%>" name="lat" class="sendData wide" placeholder="010-000-0000" style="color: rgb(255, 255, 255); text-align:center;" value="">
			</td>
		</tr>
<%
	}
%>
	<tr>
    	<th colspan="4" class="pointer tCenter hidden selectCancel">선택을 취소하려면 여기를 눌러주세요.</th>
    </tr>

   </table>
    <div class="btnDiv">
        <button class="whiteBtn" id="mapClickBtn">지도에서 위치 선택</button>
        <button class="whiteBtn" id="saveBtn">저장</button>
    </div>

</div>