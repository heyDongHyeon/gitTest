<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="geomex.xeus.smartcity.service.EventHistVo"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%
	ArrayList<EventHistVo> list = (ArrayList<EventHistVo>) request.getAttribute("result");

	String fileName = "CCTV 검색 목록_" + DateUtil.getStrDay() + ".xls";
	response.setContentType("application/vnd.ms-excel; charset=UTF-8");
    response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859_1"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CCTV 검색 목록 | XEUS-Platform</title>
<style type="text/css">
table thead th {
	color: white;
	height: 38px;
	font-size: 14px;
	font-weight: bold;
/* 	text-align: center; */
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
				<th>종류</th>
				<th>발생일</th>
				<th>조치일자</th>
				<th>경도</th>
				<th>위도</th>
				<th>주소</th>
				<th>내용</th>
				<th>조치사항</th>
			</tr>
		</thead>

<%

for(int i=0; i<list.size(); i++){
%>
			<tr>
				<td><%= list.get(i).getEvtNm() %></td>
                <td><%= DateUtil.formatDate(list.get(i).getEvtOutbDtm()) %></td>
                <td><%= DateUtil.formatDate(list.get(i).getEvtActnDtm()) %></td>
				<td><%= list.get(i).getOutbPosx() %></td>  
				<td><%= list.get(i).getOutbPosy() %></td>
				<td><%= list.get(i).getOutbPosNm() %></td>
				<td><%= list.get(i).getEvtCntn() %></td>
				<td><%= list.get(i).getEvtActnCntn() %></td>
			</tr>
<%
}
%>

	</table>

</body>
</html>