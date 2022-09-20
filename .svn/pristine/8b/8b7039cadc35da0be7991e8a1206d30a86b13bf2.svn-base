<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvModelVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ImageVo"%>
<%@ page import="geomex.xeus.equipmgr.service.AwsVo"%>
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
AwsVo vo;
if(request.getAttribute("vo")==null)
{
	vo=null;
}
else
{
	vo = (AwsVo) request.getAttribute("vo");
}

ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");
ArrayList<CctvModelVo> model = (ArrayList<CctvModelVo>) request.getAttribute("model");
ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.aws.reg.js"></script>
<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">기상관측기기 관리</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
    <!--
    180411 이은규
    미등록리스트 없이 신규등록인 것처럼 보이기 위해 변경
     -->
	<!-- <button class="blueBtn" id="unregCctv">미등록 조회</button> -->
    <button class="blueBtn" id="unregAws">신규 등록</button>
</div>

<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark">

	<div class="dropBox" style="font-size: 14px;">원하시는 시설물을 이곳에 드롭 해주세요!</div>

<% if(vo == null){ %>
	<script>$(".dropBox").show();</script>
<% }else{ %>
	<table id="regTable" class="list" cellspacing="0">
		<tr>
			<th>문서관리번호</th>
			<td colspan="3">
				<input type="text" id="docMgrNo" name="conPwd" class="sendData wide" value="<%= StrUtil.chkNull(vo.getDocMgrNo()) %>">
			</td>
		</tr>
		<tr>
			<th>시설구분</th>
            <td colspan="3">
                <input type="text" id="fclGbnNm" name="cctvNm" class="sendData wide" value="<%= StrUtil.chkNull(vo.getFclGbnNm()) %>">
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
			<th>지구명</th>
			  <td colspan="3">
                <input type="text" id="locNm" name="cctvNm" class="sendData wide" value="<%= StrUtil.chkNull(vo.getLocNm()) %>">
            </td>
		</tr>
		<tr>
			<th>주소</th>
		 	  <td colspan="3">
               	<input type="text" id="locAddr" name="cctvNm" class="sendData wide" value="<%= StrUtil.chkNull(vo.getLocAddr()) %>">
           	  </td>
		</tr>
        <tr>
        	<th>상세주소</th>
			  <td colspan="3">
               	<input type="text" id="locAddrDetail" name="awsLocAddrDetail" class="sendData wide" value="<%= StrUtil.chkNull(vo.getLocAddrDetail()) %>">
           	  </td>
        </tr>
        <tr>
			<th>업체명</th>
			<td colspan="3">
				<input type="text" id="bizNm" name="conIdsa" class="sendData wide" value="<%= StrUtil.chkNull(vo.getBizNm()) %>">
			</td>
		</tr>
		<tr style="display:none;">
			<th>pk</th>
			<td>
				<input type="text" id="mgrNo" name="conPwd" class="sendData wide" value="<%= StrUtil.chkNull(vo.getMgrNo()) %>">
			</td>
		</tr>
		<tr>
			<th>착공일</th>
				<td>
					<input type="text" id="stdDate" name="deviceId" class="sendData wide date" value="<%= StrUtil.chkNull(vo.getStdDate()) %>">
				</td>
			<th>준공일</th>
				<td>
					<input type="text" id="endDate" name="chnlNo" class="sendData wide date" value="<%= vo.getEndDate() %>">
				</td>
		</tr>
		<tr>
			<th>사업비</th>
				<td>
					<input type="text" id="bizExp" name="deviceId" class="sendData wide" value="<%= StrUtil.chkNull(vo.getBizExp()) %>">
				</td>
			<th>하천명 및 유역연장</th>
              <td colspan="3">
               	<input type="text" id="rsInfo" name="awsLocAddrDetail" class="sendData wide" value="<%= StrUtil.chkNull(vo.getRsInfo()) %>">
           	  </td>
		</tr>
		<tr>
			<th>경보 발령기준</th>
			<td>
				<input type="text" id="warnDsc" name="ipAddr" class="sendData wide" value="<%= StrUtil.chkNull(vo.getWarnDsc()) %>">
			</td>
			<th>통신방식</th>
			<td>
				<input type="text" id="commMtd" name="portNum" class="sendData wide" value="<%= StrUtil.chkNull(vo.getCommMtd()) %>">
			</td>
		</tr>
		<tr>
			<th>위도</th>
			<td>
				<input type="text" id="lat" name="lng" class="sendData wide loc" value="<%= StrUtil.chkNull(vo.getTmLat()) %>">
			</td>
			<th>경도</th>
			<td>
				<input type="text" id="lng" name="lat" class="sendData wide loc" value="<%= StrUtil.chkNull(vo.getTmLon()) %>">
			</td>
		</tr>

		<tr>
			<th>소속</th>
			<th>직위/직급</th>
			<th>성명</th>
			<th>일반전화/휴대폰</th>
		</tr>
<%
	String str=vo.getStrMgr();
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
			<input type="text" id="mgrBelong<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[1] %>">
		</td>
		<td align="center" width="180px">
			<input type="text" id="mgrSpot<%=i+1%>" name="lat" class="sendData wide"  style="color: rgb(255, 255, 255); text-align:center; width:70px;" value="<%= mgr[0] %>" >
			<span class="span_t">/</span>
			<input type="text" id="mgrLevel<%=i+1%>" name="lat" class="sendData wide"  style="color: rgb(255, 255, 255); text-align:center; width:70px" value="<%= mgr[2] %>">
		</td>
		<td align="center">
			<input type="text" id="mgrNm<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[3] %>">
		</td>
		<td align="center">
			<input type="text" id="mgrTel<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[4] %>">
			<input type="text" id="mgrPhone<%=i+1%>" name="lat" class="sendData wide" style="color: rgb(255, 255, 255); text-align:center;" value="<%= mgr[5] %>">
		</td>
	</tr>

	<%-- 	<tr>
			<th>직위<%=mgr[6] %></th>
			<td>
				<input type="text" id="mgrSpot<%=mgr[6] %>" name="lng" class="sendData wide" value="<%=mgr[0] %>">
			</td>
			<th>소속<%=mgr[6] %></th>
			<td>
				<input type="text" id="mgrBelong<%=mgr[6] %>" name="lat" class="sendData wide" value="<%=mgr[1] %>">
			</td>
			<th>직급<%=mgr[6] %></th>
			<td>
				<input type="text" id="mgrLevel<%=mgr[6] %>" name="lat" class="sendData wide" value="<%=mgr[2] %>">
			</td>
		</tr>
		<tr>
			<th>성명<%=mgr[6] %></th>
			<td>
				<input type="text" id="mgrNm<%=mgr[6] %>" name="lng" class="sendData wide" value="<%=mgr[3] %>">
			</td>
			<th>직통번호<%=mgr[6] %></th>
			<td>
				<input type="text" id="mgrTel<%=mgr[6] %>" name="lat" class="sendData wide" value="<%=mgr[4] %>">
			</td>
			<th>휴대번호<%=mgr[6] %></th>
			<td>
				<input type="text" id="mgrPhone<%=mgr[6] %>" name="lat" class="sendData wide" value="<%=mgr[5] %>">
			</td>
		</tr> --%>
		<%}
	}%>
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

    <div class="btnDiv hidden" k="<%= vo.getMgrNo() %>" >
        <button class="whiteBtn" id="mapClickBtn">지도에서 위치 선택</button>
        <button class="whiteBtn" id="saveBtn">저장</button>
        <button class="whiteBtn" id="delBtn">삭제</button>
    </div>
 <% }%>
</div>
