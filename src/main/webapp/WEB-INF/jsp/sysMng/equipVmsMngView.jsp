<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.equipmgr.service.VmsVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");

ArrayList<VmsVo> list = (ArrayList<VmsVo>)request.getAttribute("result");

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String searchStr = map.get("vmsNm");
String gbn = map.get("gbn");
if(searchStr == null) searchStr = "";
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.notice.css">
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.vms.js"></script>
<script type="text/javascript">
var offset="<%= offset %>";
var gbn="<%= gbn %>";

_common.callAjax("/sysMng/getEquipTopMenuView.do", {'gbn': gbn}, function(view) {
	$("#" + parentView).find("#menuWrap").html('');
	$("#" + parentView).find("#menuWrap").html(view);
});
</script>

    <input type="hidden" id="offset" value="<%= offset %>" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

    <!-- <div id="header">
        <div id="back">뒤로가기</div>
    </div> -->

    <div id="wrap">
    	<div id="menuWrap">
        </div>
        <div id="search">
            <input id="searchInput" class="keyup" for="#searchBtn" type="text" value="<%= searchStr %>" placeholder="VMS명"><button id="searchBtn" class="stat_btn">검색</button>
            <button id="addBtn" class="stat_btn stat_gray_btn">신규추가</button>
            <span id="count">총 <%= request.getAttribute("count") %>개의 VMS 정보가 검색되었습니다.</span>
        </div>
        <div id="content">
           <table id="userList">
                <thead>
                    <tr>
<%
for(int i=0; i<column.size(); i++){
	if(column.get(i).getTblId().equals("xeus.asset_cctv_vms")){
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
                        <td colspan="9"><b>검색결과가 존재하지 않습니다.</b></td>
                    </tr>
<%
}else{
    for(int i=0; i<list.size(); i++){
%>
                    <tr>
                        <td><%= list.get(i).getMgrNo() %></td>
                        <td><%= list.get(i).getVmsTyp() %></td>
                        <td><%= list.get(i).getVmsNm() %></td>
                        <td><%= list.get(i).getIpAddr() %></td>
                        <td><%= list.get(i).getPortNum() %></td>
                        <td><%= list.get(i).getConId() %></td>
                        <td><%= list.get(i).getConPwd() %></td>
                        <td><%= list.get(i).getRmark() %></td>
                        <td><button class="mngBtn" k="<%= list.get(i).getMgrNo() %>"></button></td>
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
        <h2 id="bpop_title">
                        VMS 관리
            <img id="closeEditPop" class="bpopClose" style="width:16px;height:16px;float:right;background-color:#00000000" src="/xeus/res/img/delete_normal.png">
        </h2>
        <table>
            <tr class="hidden">
                <th class="top">관리번호</th>
                <td>
                    <input type="text" class="sendData" id="mgrNo" name="mgrNo" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">VMS타입명</th>
                <td>
                    <input type="text" class="sendData" id="vmsTyp" name="vmsTyp" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">VMS명</th>
                <td>
                    <input type="text" class="sendData" id="vmsNm" name="vmsNm" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">IP주소</th>
                <td>
                    <input type="text" class="sendData" id="ipAddr" name="ipAddr" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">Port번호</th>
                <td>
                    <input type="text" class="sendData" id="portNum" name="portNum" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">접속ID</th>
                <td>
                    <input type="text" class="sendData" id="conId" name="conId" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">접속암호</th>
                <td>
                    <input type="text" class="sendData" id="conPwd" name="conPwd" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">비고</th>
                <td>
                    <input type="text" class="sendData" id="rmark" name="rmark" />
                </td>
            </tr>
        </table>
        <table>
            <tr align="center">
                <td class="lastTd" colspan="2" style="border: 0 !important;">
                    <button id="saveBtn" tabindex="4">저장</button>
                    <button id="delBtn" tabindex="4">삭제</button>
                    <!-- <button id="closeEditPop" class="bpopClose" tabindex="5">취소</button> -->
                </td>
            </tr>
        </table>
    </div>
</div>

