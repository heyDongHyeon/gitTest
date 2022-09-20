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
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.aws.unreg.js"></script>
<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">기상관측기기 신규등록</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
</div>

<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="ui-droppable">

	<div class="dropBox" style="font-size: 14px;">원하시는 시설물을 이곳에 드롭 해주세요!</div>

	<table id="regTable" class="list" cellspacing="0">
		<tr>
			<th>문서관리번호</th>
			<td colspan="3">
				<input type="text" id="docMgrNo" name="conPwd" class="sendData wide" placeholder="세종특별자치시-기상관측기기-01" style="color: rgb(255, 255, 255);" value="">
			</td>
		<tr>
			<th>시설구분</th>
            <td colspan="3">
                <input type="text" id="fclGbnNm" name="cctvNm" class="sendData wide" placeholder="AWS, 강우량계, 적설계" style="color: rgb(255, 255, 255);" value="">
            </td>
		</tr>
		<tr>
			<th>지구명</th>
			  <td colspan="3">
                <input type="text" id="locNm" name="cctvNm" class="sendData wide" placeholder="보람동" style="color: rgb(255, 255, 255);" value="">
            </td>
		</tr>
		<tr>
			<th>주소</th>
		 	  <td colspan="3">
               	<input type="text" id="locAddr" name="cctvNm" class="sendData wide" placeholder="주소 입력" style="color: rgb(255, 255, 255);" value="">
           	  </td>
		</tr>
        <tr>
        	<th>상세주소</th>
			  <td colspan="3">
               	<input type="text" id="locAddrDetail" name="awsLocAddrDetail" class="sendData wide" placeholder="상세내용" style="color: rgb(255, 255, 255);" value="">
           	  </td>
        </tr>
        		<tr>
			<th>업체명</th>
			<td colspan="3">
				<input type="text" id="bizNm" name="conIdsa" class="sendData wide" placeholder="(주)삼성전자" style="color: rgb(255, 255, 255);" value="">
			</td>
		</tr>
		</tr>
		<tr>
			<th>착공일</th>
				<td>
					<input type="text" id="stdDate" name="deviceId" class="sendData wide date" placeholder="2018-00-00" style="color: rgb(255, 255, 255);" value="">
				</td>
			<th>준공일</th>
				<td>
					<input type="text" id="endDate" name="chnlNo" class="sendData wide date" placeholder="2018-00-00" style="color: rgb(255, 255, 255);" value="">
				</td>
		</tr>
		<tr>
			<th>사업비</th>
				<td>
					<input type="text" id="bizExp" name="deviceId" class="sendData wide" placeholder="1,111,1111" style="color: rgb(255, 255, 255);" value="">
				</td>
			<th>하천명 및 유역연장</th>
              <td colspan="3">
               	<input type="text" id="rsInfo" name="awsLocAddrDetail" class="sendData wide" placeholder="" style="color: rgb(255, 255, 255);" value="">
           	  </td>
		</tr>
		<tr>
			<th>경보 발령기준</th>
			<td>
				<input type="text" id="warnDsc" name="ipAddr" class="sendData wide" placeholder="기상특보 발령" style="color: rgb(255, 255, 255);" value="">
			</td>
			<th>통신방식</th>
			<td>
				<input type="text" id="commMtd" name="portNum" class="sendData wide" placeholder="TCP/IP" style="color: rgb(255, 255, 255);" value="">
			</td>
		</tr>
		<tr>
			<th>위도</th>
			<td>
				<input type="text" id="lat" name="lng" class="sendData wide loc" placeholder="" style="color: rgb(255, 255, 255);" value="">
			</td>
			<th>경도</th>
			<td>
				<input type="text" id="lng" name="lat" class="sendData wide loc" placeholder="" style="color: rgb(255, 255, 255);" value="">
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