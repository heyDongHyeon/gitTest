<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.map.service.EmdVo"%>
<%@ page import="geomex.xeus.map.service.LiVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="../common.jsp" %>
<%
CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

HashMap<String, String> gbn = cde.convertCodeGrpToAllCde("C12");
Set<String> gbnKey = gbn.keySet();
Iterator<String> gbnItr = gbnKey.iterator();

ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
ArrayList<EmdVo> emd = (ArrayList<EmdVo>) request.getAttribute("emd");
ArrayList<LiVo> li = (ArrayList<LiVo>) request.getAttribute("li");

ArrayList<HashMap<String, String>> grp = (ArrayList<HashMap<String, String>>) request.getAttribute("grp");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.send.sms.js"></script>


<div class="searchWrapper ui-droppable" onselectstart="return false" data-mcs-theme="minimal-dark">

    <!-- <p class="searchTitle">
        <button class="tab" url="/nms/getInfraView.do">범례</button><button class="tab" active="active" url="/nms/getInfraSearchView.do">검색</button><button class="tab" url="/nms/getInfraAddView.do">신규등록</button>
    </p> -->

    <p class="searchTitle">대상 검색</p>
    <table id="searchTable" class="searchTable">
    	  <tr>
            <th>사용자<br>그룹</th>
            <td>
               <ul id="tree" class="ztree"></ul>
            </td>
          </tr>
<!--           <tr> -->
<!--             <th>사용자 그룹</th> -->
<!--             <td> -->
<!--                 <select id="grp" name="grp" class="sendData"> -->
<!--                     <option value="">전체</option> -->
<%-- <% for(int i=0; i<grp.size(); i++){ %> --%>
<%--                     <option value="<%=grp.get(i).get("grp") %>"><%=grp.get(i).get("grp") %></option> --%>
<%-- <% } %> --%>
<!--                 </select> -->
<!--             </td> -->
<!--         </tr> -->
       
        <tr>
            <th>사용자</th>
            <td>
                <input type="text" id="objName" name="objName" class="sendData" style="width:100%; margin:1px; padding:0px;">
            </td>
        </tr>
        <tr>
            <th>연락처</th>
            <td>
                <input type="text" id="objNum" name="objNum" class="sendData" style="width:100%; margin:1px; padding:0px;">
            </td>
        </tr>
        <!-- <tr>
            <td colspan="2" class="tCenter noneBack">
                <button class="blackBtn" style="margin: 3px 0px 3px 0px;" id="searchBtn">검색</button>
            </td>
        </tr> -->
    </table>

    <div class="btnDiv">
        <button class="blackBtn" style="margin: 3px 0px 3px 0px;" id="searchBtn">검색</button>
    </div>

    <div style="width: 300px; display: table-cell;">
        <p class="searchTitle" style="width:49%; display: inline-block;">검색결과</p>
    </div>
    <table>
     	<tr>
     		<th width="20px"><input id="send_all_chk" type="checkbox" style="vertical-align: middle"></th>
            <th width="80px">사용자그룹</th>
            <th width="80px">성명</th>
            <th width="100px">휴대번호</th>

        </tr>
    </table>
    <div id="resultList" class=" ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark" style="max-height: 64%;">
	    <table id="send_table"></table>
    </div>

    <div class="detailWrapper"></div>

</div>