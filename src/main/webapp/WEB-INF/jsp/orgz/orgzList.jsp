<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
CodeConvertor cde = (CodeConvertor) request.getAttribute("code");
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");
HashMap<String, String> orgGbn = cde.convertCodeGrpToAllCde("C01");
Set<String> key = orgGbn.keySet();
Iterator<String> itr = key.iterator();

ArrayList<OrganizationVo> list = (ArrayList<OrganizationVo>)request.getAttribute("result");
HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String searchStr = map.get("orgNm");
String gbn = map.get("gbn");
if(searchStr == null) searchStr = "";
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.orgz.css">
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.orgz.js"></script>
<script type="text/javascript">
var orgNm = "<%=searchStr%>";
var offset="<%= offset %>";
var gbn = '<%=gbn%>';

_common.callAjax("/sysMng/getBasicTopMenuView.do", {'gbn': gbn}, function(view) {
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
            <input id="searchInput" class="keyup" for="#searchBtn" type="text" value="<%= searchStr %>" placeholder="기관명"><button id="searchBtn" class="stat_btn">검색</button>
            <button id="addBtn" class="stat_btn stat_gray_btn">신규추가</button>
            <span id="count">총 <%= request.getAttribute("count") %>개의 기관 정보가 검색되었습니다.</span>
        </div>
        <div id="content">
           <table id="userList">
                <thead>
                    <tr>
<%
for(int i=0; i<column.size(); i++){
	if(column.get(i).getTblId().equals("xeus.mt_orgz_desc")){
		String col = column.get(i).getColNm();
        col = col.replace("관리번호", "").replace("코드", "");
%>
                        <th><%= col %></th>
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
%>
                    <tr>
                        <td><%= list.get(i).getOrgMgrNo() %></td>
                        <td><%= cde.convertCodeToName("C01", list.get(i).getOrgGbnCd()) %></td>
                        <td><%= list.get(i).getOrgNm() %></td>
                        <td><%= list.get(i).getTelNum() %></td>
                        <td><%= list.get(i).getChrgNm() %></td>
                        <td><%= list.get(i).getRmark() %></td>
                        <td><%= list.get(i).getUpMgrNo() %></td>
                        <td><button class="mngBtn" k="<%= list.get(i).getOrgMgrNo() %>"></button></td>
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
        <h2 id="bpop_title">소속 관리</h2>
        <table>
            <tr class="hidden">
                <th class="top">기관관리번호</th>
                <td>
                    <input type="text" class="sendData" id="orgMgrNo" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">기관명</th>
                <td>
                    <input type="text" class="sendData" id="orgNm" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">기관구분</th>
                <td>
                    <select class="sendData" id="orgGbnCd">
<%
while(itr.hasNext()){
    String str = (String)itr.next();
%>
                        <option value="<%= str %>"><%= orgGbn.get(str) %></option>
<%
}
%>
                    </select>
                </td>
            </tr>
            <tr>
                <th class="top">상위기관</th>
                <td>
                    <input type="text" class="sendData" id="upMgrNo" />
                </td>
            </tr>
            <tr>
                <th class="top">연락처</th>
                <td>
                    <input type="text" class="sendData" id="telNum" />
                </td>
            </tr>
            <tr>
                <th class="top">담당자명</th>
                <td>
                    <input type="text" class="sendData" id="chrgNm" />
                </td>
            </tr>
            <tr>
                <th class="top">비고</th>
                <td>
                    <input type="text" class="sendData" id="rmark" />
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
