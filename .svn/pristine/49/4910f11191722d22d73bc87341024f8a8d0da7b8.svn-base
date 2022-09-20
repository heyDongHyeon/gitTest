<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.NoticeVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");

ArrayList<NoticeVo> list = (ArrayList<NoticeVo>)request.getAttribute("result");

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String searchStr = map.get("notcTitle");
String gbn = map.get("gbn");
if(searchStr == null) searchStr = "";
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.notice.css">
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.notice.js"></script>
<script type="text/javascript">
var notcTitle = "<%=searchStr%>";
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
            <input id="searchInput" class="keyup" for="#searchBtn" type="text" value="<%= searchStr %>" placeholder="제목"><button id="searchBtn" class="stat_btn">검색</button>
            <button id="addBtn" class="stat_btn stat_gray_btn">신규추가</button>
            <span id="count">총 <%= request.getAttribute("count") %>개의 공지사항 정보가 검색되었습니다.</span>
        </div>
        <div id="content">
           <table id="userList">
                <thead>
                    <tr>
<%
for(int i=0; i<column.size(); i++){
	if(column.get(i).getTblId().equals("xeus.mt_notc_list")
		&& !"atch_file_path".equals(column.get(i).getColId())
		&& !"notc_conts".equals(column.get(i).getColId())){
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
                        <td colspan="6"><b>검색결과가 존재하지 않습니다.</b></td>
                    </tr>
<%
}else{
    for(int i=0; i<list.size(); i++){
	String key = list.get(i).getMgrSeq();
	String file = StrUtil.chkNull(list.get(i).getAtchFileNm());
%>
                    <tr>
                        <td><%= key %></td>
                        <td><%= list.get(i).getNotcTitle() %></td>
                        <td><%= list.get(i).getWorkerId() %></td>
                        <%-- <td><%= list.get(i).getNotcConts() %></td> --%>
                        <td><%= DateUtil.formatDate(list.get(i).getLastMdfyDat()) %></td>
                        <td class="downBtn" k="<%= file %>" u="<%= key %>" ><%= file %></td>
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
                        공지사항 관리
            <img id="closeEditPop" class="bpopClose" style="width:16px;height:16px;float:right;background-color:#00000000" src="/xeus/res/img/delete_normal.png">
        </h2>
        <form id="sendForm" method="POST" enctype="multipart/form-data">
	        <table>
	            <tr class="hidden">
	                <th class="top">관리번호</th>
	                <td>
	                    <input type="text" class="sendData" id="mgrSeq" name="mgrSeq" />
	                </td>
	            </tr>
	            <tr class="top">
	                <th class="top">제목</th>
	                <td>
	                    <input type="text" class="sendData" id="notcTitle" name="notcTitle" />
	                </td>
	            </tr>
	            <tr>
	                <th class="top">내용</th>
	                <td>
	                    <textarea class="sendData" id="notcConts" name="notcConts"></textarea>
	                </td>
	            </tr>
	            <tr>
	                <th class="top">첨부파일</th>
	                <!-- <td id="formTr" class="hidden">
	                    <input type="file" id="file" name="file" class="wide sendData" size="30" placeholder="첨부파일" />
	                </td>
	                <td id="downTr">
	                    <input type="text" id="fileDown" class="small" readOnly />
	                    <button id="editBtn" type="button">첨부파일 수정</button>
	                </td> -->
                    <td>
                        <div id="formTr" class="hidden">
                            <input type="file" id="file" name="file" class="wide sendData" size="30" placeholder="첨부파일" />
                        </div>
                        <div id="downTr">
                            <input type="text" id="fileDown" class="small" readOnly />
                            <button id="editBtn" type="button">첨부파일 수정</button>
                        </div>
                    </td>
	            </tr>
	        </table>
        </form>
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

