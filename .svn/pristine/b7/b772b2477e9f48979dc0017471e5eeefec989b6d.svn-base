<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="geomex.xeus.equipmgr.service.BrdcstTrmnlsVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%
	ArrayList<BrdcstTrmnlsVo> list = (ArrayList<BrdcstTrmnlsVo>) request.getAttribute("result");
	CodeConvertor cde = (CodeConvertor) request.getAttribute("code");
	ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");

	String fileName = "음성통보시스템_" + DateUtil.getStrDay() + ".xls";
	response.setContentType("application/vnd.ms-excel; charset=UTF-8");
    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859_1"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>음성통보시스템 검색 목록 | XEUS-Platform</title>
<style type="text/css">
table thead th {
	color: white;
	height: 38px;
	font-size: 14px;
	font-weight: bold;
	text-align: center;
	background-color: #3F4551;
}

table td {
	text-align: center;
    background-color: #F8F9FA;
}
</style>
</head>
<body>

	<table>
		<thead>
			<tr>

				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
				<th>전화번호</th>
				<th>경도</th>
				<th>위도</th>

			</tr>
		</thead>

<%
for(int i=0; i<list.size(); i++){
%>
			<tr>
				<%-- <td><%= list.get(i).getGid() %></td>
				<td><%= list.get(i).getMgrNo() %></td>
				<td><%= list.get(i).getOrgMgrNo() %></td>
				<td><%= list.get(i).getMdMgrNo() %></td>
				<td><%= list.get(i).getSiteMgrNo() %></td> --%>

<%--                 <td><%= list.get(i).getVmsMgrNo() %></td> --%>
<%--                 <td><%= list.get(i).getCctvNm() %></td> --%>
<%-- 				<td><%= DateUtil.formatDate(list.get(i).getInstDat()) %></td> --%>
<%-- 				<td><%= list.get(i).getDeviceId() %></td> --%>
<%-- 				<td><%= list.get(i).getChnlNo() %></td> --%>
<%-- 				<td><%= cde.convertCodeToName("C14", list.get(i).getGbnCd()) %></td> --%>
<%-- 				<td><%= StrUtil.chkNull(list.get(i).getIpAddr()) %></td> --%>
<%-- 				<td><%= StrUtil.chkNull(list.get(i).getPortNum()) %></td> --%>
<%-- 				<td><%= "Y".equals(list.get(i).getUseYn()) ? "사용" : "미사용" %></td> --%>
<%-- 				<td><%= "Y".equals(list.get(i).getLightYn()) ? "가능" : "불가능" %></td> --%>
<%-- 				<td><%= "Y".equals(list.get(i).getInfrdYn()) ? "가능" : "불가능" %></td> --%>
<%--                 <td><%= "Y".equals(list.get(i).getPanYn()) ? "가능" : "불가능" %></td> --%>
<%-- 				<td><%= "Y".equals(list.get(i).getTiltYn()) ? "가능" : "불가능" %></td> --%>
<%-- 				<td><%= "Y".equals(list.get(i).getZoomYn()) ? "가능" : "불가능" %></td> --%>
<%-- 				<td><%= "Y".equals(list.get(i).getTalkYn()) ? "가능" : "불가능" %></td> --%>
<%--                 <td><%= "Y".equals(list.get(i).getTourYn()) ? "가능" : "불가능" %></td> --%>
<%-- 				<td><%= StrUtil.chkNull(list.get(i).getConId()) %></td> --%>
				<td><%= StrUtil.chkNull(list.get(i).getInnb()) %></td>
                <td><%= StrUtil.chkNull(list.get(i).getNm()) %></td>
				<td><%= StrUtil.chkNull(list.get(i).getLc()) %></td>
				<td><%= StrUtil.chkNull(list.get(i).getTelno()) %></td>
				<td><%= StrUtil.chkNull(list.get(i).getLo()) %></td>
				<td><%= StrUtil.chkNull(list.get(i).getLa()) %></td>
			</tr>
<%
}
%>

	</table>

</body>
</html>