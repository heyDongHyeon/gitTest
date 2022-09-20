<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.sysmgr.service.IpVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");

ArrayList<IpVo> list = (ArrayList<IpVo>)request.getAttribute("result");
ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String searchStr = map.get("orgNm");
if(searchStr == null) searchStr = "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<link rel="stylesheet" type="text/css" href="<%= context %>/common/ui-1.12.1/themes/ui-darkness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.ip.css">
<script type="text/javascript" src="<%= context %>/common/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.gridster.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.spin.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.paging.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.timepicker.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.bpopup.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.form.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.download.js"></script>
<script type="text/javascript" src="<%= context %>/common/tooltipsy.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/tea-block.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/base64.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/utf8.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/jsbn.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/rsa.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/helper.js"></script>
<script type="text/javascript" src="<%= context %>/common/string.js"></script>
<script type="text/javascript" src="<%= context %>/common/HashMap.js"></script>
<script type="text/javascript" src="<%= context %>/common/string.js"></script>
<script type="text/javascript" src="<%= context %>/common/Date.js"></script>
<script type="text/javascript" src="<%= context %>/common/common.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ip.js"></script>
<title>IP 관리 | XEUS-Platform</title>
</head>
<body>

    <input type="hidden" id="offset" value="<%= offset %>" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

    <!-- <div id="header">
        <div id="back">뒤로가기</div>
    </div> -->

    <div id="wrap">
        <div id="title">허용IP관리</div>
        <div id="search">
            <input id="searchInput" class="keyup" for="#searchBtn" type="text" value="<%= searchStr %>" placeholder="사용기관"><button id="searchBtn">검색</button>
            <button id="addBtn">신규추가</button>
            <span id="count">총 <%= request.getAttribute("count") %>개의 IP 정보가 검색되었습니다.</span>
        </div>
        <div id="content">
           <table id="list">
                <thead>
                    <tr>
<%
for(int i=0; i<column.size(); i++){
	if(column.get(i).getTblId().equals("xeus.mt_allow_ip")){
%>
                        <th><%= column.get(i).getColNm() %></th>
<%
	}
}
%>
                        <th>관리</th>
                    </tr>
                </thead>
                <tbody>
<%
if(list.size() == 0){
%>
                    <tr>
                        <td colspan="8"><b>검색결과가 존재하지 않습니다.</b></td>
                    </tr>
<%
}else{
    for(int i=0; i<list.size(); i++){
	String useYn = "적용";
	if("N".equals(list.get(i).getUseYn())) useYn = "미적용";
%>
                    <tr>
                        <td><%= list.get(i).getMgrSeq() %></td>
                        <td><%= list.get(i).getStartIpNo() %></td>
                        <td><%= list.get(i).getEndIpNo() %></td>
                        <td><%= list.get(i).getWorkerId() %></td>
                        <td><%= list.get(i).getOrgNm() %></td>
                        <td><%= list.get(i).getLastMdfyDat() %></td>
                        <td><%= useYn %></td>
                        <td><button class="mngBtn" k="<%= list.get(i).getMgrSeq() %>"></button></td>
                    </tr>
<%
    }
}
%>
                </tbody>
            </table>
        </div>
        <div class="paging_wrap"></div>
    </div>

    <div class="bpopup" id="edit_pop_wrap">
    <div id="bpop_wrap">
        <h2 id="bpop_title">IP 관리</h2>
        <table>
            <tr class="hidden">
                <th class="top">관리번호</th>
                <td>
                    <input type="text" class="sendData" id="mgrSeq" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">시작IP</th>
                <td>
                    <input type="text" class="sendData" id="startIpNo" />
                </td>
            </tr>
            <tr>
                <th class="top">종료IP</th>
                <td>
                    <input type="text" class="sendData" id="endIpNo" />
                </td>
            </tr>
            <tr>
                <th class="top">사용기관명</th>
                <td>
                    <select class="sendData" id="orgMgrNo">
<% for(int i=0; i<orgz.size(); i++){ %>
                        <option value="<%= orgz.get(i).getOrgMgrNo() %>"><%= orgz.get(i).getOrgNm() %></option>
<% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th class="top">적용여부</th>
                <td>
                    <select class="sendData" id="useYn">
                        <option value="Y">적용</option>
                        <option value="N">미적용</option>
                    </select>
                </td>
            </tr>
        </table>
        <table>
            <tr align="center">
                <td class="lastTd" colspan="2" style="border: 0 !important;">
                    <button id="saveBtn" tabindex="4">저장</button>
                    <button id="delBtn" tabindex="4">삭제</button>
                    <button id="closeEditPop" class="bpopClose" tabindex="5">취소</button>
                </td>
            </tr>
        </table>
    </div>
</div>

</body>
</html>