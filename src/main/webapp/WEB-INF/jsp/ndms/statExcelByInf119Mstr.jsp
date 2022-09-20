<%@page import="org.omg.PortableInterceptor.USER_EXCEPTION"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="../common.jsp"%>
<%

	ArrayList<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) request.getAttribute("list");

	String fileName = "긴급구조 통계.xls";
	response.setContentType("application/vnd.ms-excel; charset=UTF-8");
	response.setHeader("Content-Disposition", "attachment; filename=" + new String(java.net.URLEncoder.encode(fileName,"UTF-8").getBytes(), "ISO8859_1"));

	ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
	for(int i=0; i<list.size(); i++){

		String division = list.get(i).get("division");
		String divisionCnt = list.get(i).get("division_cnt");
		String deathCnt = "";
		String injuryCnt = "";
		String etcCnt = "";
		String casualtiesSum = "";

		if(division == null || "".equals(division) || "10".equals(division)){
			division = "미입력";
		}else{
			String [] casualtiesList = list.get(i).get("casualties").split(",");
			String [] casualtiesCntList = list.get(i).get("casualties_cnt").split(",");



			int sum = 0;

			for(int j=0; j<casualtiesList.length; j++){
				if("사망".equals(casualtiesList[j])){
					deathCnt = casualtiesCntList[j];
				}else if("부상".equals(casualtiesList[j])){
					injuryCnt = casualtiesCntList[j];
				}else if("기타".equals(casualtiesList[j])){
					etcCnt = casualtiesCntList[j];
				}
				sum += Integer.parseInt(casualtiesCntList[j]);
			}

			casualtiesSum = Integer.toString(sum);
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("division", division);
		map.put("divisionCnt", divisionCnt);
		map.put("casualtiesSum", casualtiesSum);
		map.put("deathCnt", deathCnt);
		map.put("injuryCnt", injuryCnt);
		map.put("etcCnt", etcCnt);

		result.add(map);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
<style type="text/css">
table .title td{
	border:1px solid;
    font-size: 20px !important;
    color: #585858 !important;
    height: 38px !important;
    background-color: #90bad1 !important;
    font-weight: bold !important;
}
table th {
	border:1px solid;
    font-size: 12px;
    color: #585858;
    height: 38px;
    background-color: #9ec293;
}

table thead th {
    color: #CFCDCC;
    font-size: 14px;
    font-weight: bold;
    text-align: center;
    background-color: #9ec293;
}

table td {
    text-align: center;
    background-color: #F8F9FA;
    mso-number-format:"\@";
}
</style>
</head>
<body>

        <table>
            <thead>

	        </thead>
            <colgroup>
	            <col width="150" />
	            <col width="150" />
	            <col width="150" />
	            <col width="150" />
	            <col width="150" />
	            <col width="150" />
            </colgroup>
            <tr>
                <th rowspan="3">구분</th>
                <th colspan="5">피해현황</th>
            </tr>
            <tr>
            	<th rowspan="2">발생건수</th>
            	<th colspan="4">인명피해(명)</th>
            </tr>
             <tr>
            	<th>계</th>
            	<th>사망</th>
            	<th>부상</th>
            	<th>기타</th>
            </tr>

			<%
           	if (list.size() == 0){
            %>
            <tr>
                <td colspan="6" align="center" style="height: 100px;">데이터가 존재하지 않습니다.</td>
            </tr>
			<%
            }else {
            	for(int i=0; i<result.size(); i++){
// 					if(result.get(i).get("division") == null || "".equals(result.get(i).get("division"))  "10".equals(result.get(i).get("division"))){
// 						continue;
// 					}
// 					String [] casualtiesresult = result.get(i).get("casualties").split(",");
// 					String [] casualtiesCntresult = result.get(i).get("casualtiesCnt").split(",");
// 					for(int j=0; j<casualtiesresult.length; j++){
// 						String casualties = casualtiesresult[i];
// 						String casualtiesCnt = casualtiesCntresult[i];
// 					}
       		%>
            <tr>
            	<td><%=StrUtil.chkNull(result.get(i).get("division"))%></td>
            	<td><%=StrUtil.chkNull(result.get(i).get("divisionCnt"))%></td>
            	<td><%=StrUtil.chkNull(result.get(i).get("casualtiesSum"))%></td>
            	<td><%=StrUtil.chkNull(result.get(i).get("deathCnt"))%></td>
            	<td><%=StrUtil.chkNull(result.get(i).get("injuryCnt"))%></td>
            	<td><%=StrUtil.chkNull(result.get(i).get("etcCnt"))%></td>
            </tr>
            <%
            	}
            }
            %>
        </table>

</body>
</html>
