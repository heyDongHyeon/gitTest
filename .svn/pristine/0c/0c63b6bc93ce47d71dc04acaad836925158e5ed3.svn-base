<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.map.service.EmdVo"%>
<%@ page import="geomex.xeus.map.service.LiVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="../../common.jsp" %>
<%
CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

HashMap<String, String> gbn = cde.convertCodeGrpToAllCde("C12");
Set<String> gbnKey = gbn.keySet();
Iterator<String> gbnItr = gbnKey.iterator();

ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
ArrayList<EmdVo> emd = (ArrayList<EmdVo>) request.getAttribute("emd");
ArrayList<LiVo> li = (ArrayList<LiVo>) request.getAttribute("li");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.nms.infra.search.js"></script>
<div class="searchWrapper ui-droppable mCustomScrollbar" onselectstart="return false" data-mcs-theme="minimal-dark">

    <!-- <p class="searchTitle">
        <button class="tab" url="/nms/getInfraView.do">범례</button><button class="tab" active="active" url="/nms/getInfraSearchView.do">검색</button><button class="tab" url="/nms/getInfraAddView.do">신규등록</button>
    </p> -->

    <p class="searchTitle">시설 검색</p>
    <table id="searchTable" class="searchTable">
    	<tr>
            <th>시설구분</th>
            <td>
                <select id="objType" name="objType" class="sendData">
                    <option value="ALL">전체</option>
                    <option value="CTV">재난재해용 CCTV</option>
                    <option value="AWS">기상관측기기</option>
                    <option value="VOI">음성통보시스템</option>
                    <option value="DIS">재해문자전광판</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>읍면동</th>
            <td>
                <select id="emdCd" name="emdCd" class="sendData">
                    <option value="">전체</option>
<% for(int i=0; i<emd.size(); i++){ %>
                    <option value="<%= emd.get(i).getEmdCd() %>"><%= emd.get(i).getEmdKorNm() %></option>
<% } %>
                </select>
            </td>
        </tr>

<%--         <tr>
            <th>시설구분</th>
            <td>
                <select id="fclGbnCd" name="fclGbnCd" class="sendData">
                    <option value="">전체</option>
<% while(gbnItr.hasNext()){
    String str = (String) gbnItr.next(); %>
                    <option value="<%= str %>"><%= gbn.get(str) %></option>
<% } %>
                </select>
            </td>
        </tr> --%>
        <tr>
            <th>명칭</th>
            <td>
                <input type="text" id="objName" name="objName" class="sendData" style="width:100%; margin:1px; padding:0px;" placeholder="명칭 or 주소">
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

    <div style="width: 460px; display: table-cell;">
        <p class="searchTitle" style="width:49%; display: inline-block;">검색결과</p>

	    <div class="tRight" style="width: 49%; display: inline-block;">
	        <button id="fclAddBtn" class="whiteBtn" style="width: 88px; height: 28px;">신규등록</button>
	        <button id="excelBtn" class="whiteBtn" style="width: 88px; height: 28px;">엑셀 다운로드</button>
	    </div>
    </div>
    <table>
        <tr>
            <th width="100px">시설구분</th>
            <th>명칭</th>
            <th width="70px">확인</th>
        </tr>
    </table>
    <div id="resultList" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark" style="max-height: 64%;">
	    <table></table>
    </div>

    <div class="detailWrapper"></div>

</div>