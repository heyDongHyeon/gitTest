<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");

ArrayList<AuthVo> list = (ArrayList<AuthVo>)request.getAttribute("result");
ArrayList<AuthGrpVo> grpList = (ArrayList<AuthGrpVo>)request.getAttribute("grpList");
HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String searchStr = map.get("authGrpNm");
String gbn = map.get("gbn");
if(searchStr == null) searchStr = "";
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.auth.css">
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.auth.js"></script>
<script type="text/javascript">
var gbn = '<%=gbn%>';

_common.callAjax("/sysMng/getBasicTopMenuView.do", {'gbn': gbn}, function(view) {
	$("#" + parentView).find("#menuWrap").html('');
	$("#" + parentView).find("#menuWrap").html(view);
});
</script>
<title>권한 관리 | XEUS-Platform</title>

    <div id="wrap">
    	<div id="menuWrap">
        </div>
        <div id="search">
            <input id="searchInput" class="keyup" for="#searchBtn" type="text" value="<%= searchStr %>" placeholder="그룹명칭"><button id="searchBtn" class="stat_btn">검색</button>
            <button id="addBtn" class="stat_btn stat_gray_btn">신규추가</button>
            <span id="count">총 <%= request.getAttribute("grpCount") %>개의 그룹 정보가 검색되었습니다.</span>
        </div>
        <div id="content">
            <table id="list">
                <thead>
                    <tr>
                        <th>그룹목록</th>
                        <th>권한목록</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <select id="authGrpList" multiple="multiple">
<% for(int i=0; i<grpList.size(); i++){ %>
                                <option class="grp" k="<%= grpList.get(i).getAuthGrpNo() %>"><%= grpList.get(i).getAuthGrpNm() %></option>
<% } %>
                            </select>
                        </td>
                        <td>
                            <table id="authList">
                                <colgroup>
                                    <col width="30px">
                                    <col width="100%">
                                </colgroup>
                                <tr>
                                    <td><input type="checkbox" id="allAuth" disabled="disabled"></td>
                                    <td class="authNm">전체선택</td>
                                </tr>
<% for(int i=0; i<list.size(); i++){
%>

                                <tr>
                                    <td><input type="checkbox" class="auth" k="<%= list.get(i).getAuthMgrNo() %>" disabled="disabled"></td>
                                    <td class="authNm"><%= list.get(i).getAuthNm() %></td>
                                </tr>
<% } %>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="bpopup" id="edit_pop_wrap">
	    <div id="bpop_wrap">
	        <h2 id="bpop_title">그룹 관리</h2>
	        <table>
	            <tr class="hidden">
	                <th class="top">그룹ID</th>
	                <td>
	                    <input type="text" class="sendData" id="authGrpNo" />
	                </td>
	            </tr>
	            <tr class="top">
	                <th class="top">그룹명</th>
	                <td>
	                    <input type="text" class="sendData" id="authGrpNm" />
	                </td>
	            </tr>
	        </table>
	        <table>
	            <tr align="center">
	                <td class="lastTd" colspan="2" style="border: 0 !important;">
	                    <button id="saveBtn" style="width:32.5%;" tabindex="4">저장</button>
	                    <button id="delBtn" style="width:32.5%;" tabindex="4">삭제</button>
	                    <button id="closeEditPop" class="bpopClose" style="width:32.5%;" tabindex="5">취소</button>
	                </td>
	            </tr>
	        </table>
	    </div>
	</div>

