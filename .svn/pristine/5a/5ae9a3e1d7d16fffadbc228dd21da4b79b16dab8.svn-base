<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.eventmonitor.service.EventMngListVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<EventMngListVo> list = (ArrayList<EventMngListVo>)request.getAttribute("result");

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String searchStr = map.get("evtNm");
if(searchStr == null) searchStr = "";
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.notice.css">
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.eventMngList.js"></script>
<script type="text/javascript">
var offset="<%= offset %>";
</script>

    <input type="hidden" id="offset" value="<%= offset %>" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

    <!-- <div id="header">
        <div id="back">뒤로가기</div>
    </div> -->

    <div id="wrap">
        <div id="title">이벤트 리스트 관리</div>
        <div id="search">
            <input id="searchInput" class="keyup" for="#searchBtn" type="text" value="<%= searchStr %>" placeholder="이벤트명"><button id="searchBtn">검색</button>
            <button id="addBtn">신규추가</button>
            <span id="count">총 <%= request.getAttribute("count") %>개의 이벤트 정보가 검색되었습니다.</span>
        </div>
        <div id="content">
           <table id="list">
                <thead>
                    <tr>
                        <th>고유번호</th>
                        <th>이벤트명</th>
                        <th>사용여부</th>
                        <th>이벤트코드</th>
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
                        <td><%= list.get(i).getMgrSeq() %></td>
                        <td><%= list.get(i).getEvtNm() %></td>
                        <td><%= list.get(i).getUseYn() %></td>
                        <td><%= list.get(i).getEvtCd() %></td>
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
        <h2 id="bpop_title">
                        이벤트 관리
            <img id="closeEditPop" class="bpopClose" style="width:16px;height:16px;float:right;background-color:#00000000" src="/xeus/res/img/delete_normal.png">
        </h2>
        <table>
            <tr class="hidden">
                <th class="top">고유번호</th>
                <td>
                    <input type="text" class="sendData" id="mgrSeq" name="mgrSeq" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">이벤트명</th>
                <td>
                    <input type="text" class="sendData" id="evtNm" name="evtNm" />
                </td>
            </tr>
            <tr class="top">
                <th class="top">사용여부</th>
                <td>
                	<select class="sendData" id="useYn" name="useYn">
                		<option value="Y">사용</option>
                		<option value="N">미사용</option>
                	</select>
                </td>
            </tr>
            <tr class="top">
                <th class="top">이벤트코드</th>
                <td>
                    <input type="text" class="sendData" id="evtCd" name="evtCd" />
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
