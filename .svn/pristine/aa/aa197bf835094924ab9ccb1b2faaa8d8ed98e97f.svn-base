<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.log.service.IfDscLogVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="../../common.jsp" %>
<%
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");
ArrayList<IfDscLogVo> list = (ArrayList<IfDscLogVo>) request.getAttribute("list");

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");

String startDat = "";
String endDat = "";

if (map.containsKey("startDat")){
    if (map.get("startDat") != null) {
        startDat = DateUtil.formatDate(map.get("startDat").trim(), 8);
    }
}

if (map.containsKey("endDat")){
    if (map.get("endDat") != null) {
        endDat = DateUtil.formatDate(map.get("endDat").trim(), 8);
    }
} else {

    SimpleDateFormat dayTime = new SimpleDateFormat("YYYY-MM-dd");
    endDat = dayTime.format(new Date());
}

String fileName = "사회적약자서비스지원현황.xls";
response.setContentType("application/vnd.ms-excel; charset=UTF-8");
response.setHeader("Content-Disposition", "attachment; filename=" + new String(java.net.URLEncoder.encode(fileName,"UTF-8").getBytes(), "ISO8859_1"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
<style type="text/css">
table th {
    font-size: 12px;
    color: #585858;
    height: 38px;
    background-color: #D9DADC;
}

table thead th {
    color: #CFCDCC;
    font-size: 14px;
    font-weight: bold;
    text-align: center;
    background-color: #3F4551;
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
                    <tr align = "center">
                       <td colspan="18" align="center">112사회적약자서비스지원현황</td>
                    </tr>
                    <tr align="center">
                        <td colspan="15"></td>
                        <td align="center">조회기간</td>
                        <td align="center" colspan="2">
                        <%
                        if (!startDat.equals("")){
                        %>
                             <%= startDat %>
                        <%
                        }
                        %>
                             <%= " ~ " + endDat %>
                        </td>
                    </tr>
                    <tr>
<%
for(int i=0; i<column.size(); i++){
    if(column.get(i).getTblId().equals("xeus.if_dsc_log")
        && !"msg_typ_cd".equals(column.get(i).getColId())
        && !"sta_typ_cd".equals(column.get(i).getColId())
        && !"msg_sta_dtm".equals(column.get(i).getColId())
        && !"snd_sys_cd".equals(column.get(i).getColId())
        && !"rcv_sys_cd".equals(column.get(i).getColId())
        && !"snd_dtm".equals(column.get(i).getColId())
        && !"data_len".equals(column.get(i).getColId())
        && !"evt_lon".equals(column.get(i).getColId())
        && !"rcv_dtm_rqst".equals(column.get(i).getColId())
        && !"rcv_orgn".equals(column.get(i).getColId())
        && !"err_msg".equals(column.get(i).getColId())
        && !"snd_dtm_rsp".equals(column.get(i).getColId())
        && !"snd_orgn".equals(column.get(i).getColId())){
        String col = column.get(i).getColNm();
        if(col != null){
            /* if(col.equals("수신자아이디")) col = "수신자";
            else if(col.equals("소속기관")) col = "소속"; */
            if(col.equals("사건지점위치(위도)")) col = "사건지점위치";
%>
                        <th><%= col %></th>
<%
        }
    }
}
%>
                    </tr>
                </thead>
                <tbody>
<%
if(list.size() == 0){
%>
                    <tr>
                        <td colspan="18"><b>검색결과가 존재하지 않습니다.</b></td>
                    </tr>
<%
}else{
    for(int i=0; i<list.size(); i++){
%>
                    <tr>
                        <td><%= list.get(i).getSendNum() %></td>
                        <td><%= list.get(i).getSvcTyp() %></td>
                        <td>
                            <%= list.get(i).getEvtLat() %><br>
                            <%= list.get(i).getEvtLon() %>
                        </td>
                        <td><%= list.get(i).getEvtAddr() %></td>
                        <td><%= list.get(i).getEvtBjd() %></td>
                        <td><%= list.get(i).getRefId() %></td>
                        <td><%= list.get(i).getDscNm() %></td>
                        <td><%= list.get(i).getDscPhone() %></td>
                        <td><%= list.get(i).getDscBirth() %></td>
                        <td><%= list.get(i).getDscSex() %></td>
                        <td><%= list.get(i).getDscAddr() %></td>
                        <td><%= list.get(i).getGuardNm() %></td>
                        <td><%= list.get(i).getGuardPhone() %></td>
                        <td><%= DateUtil.formatDate(list.get(i).getEvtDtm()) %></td>
<%
        String images = list.get(i).getImages();
        if(images != null) images = images.replaceAll(";", "<br/>");
%>
                        <td><%= images %></td>
                        <td><%= list.get(i).getInfo() %></td>
                        <td><%= list.get(i).getNote() %></td>
                        <td><%= list.get(i).getSenderId() %></td>
                        <td><%= list.get(i).getNrmlYn() %></td>

                    </tr>
<%
    }
}
%>
                </tbody>
            </table>

</body>
</html>