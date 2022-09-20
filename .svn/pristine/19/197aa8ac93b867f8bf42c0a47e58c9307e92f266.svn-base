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
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndms.infra.search.js"></script>
<div class="searchWrapper ui-droppable mCustomScrollbar" onselectstart="return false" data-mcs-theme="minimal-dark">

    <!-- <p class="searchTitle">
        <button class="tab" url="/nms/getInfraView.do">범례</button><button class="tab" active="active" url="/nms/getInfraSearchView.do">검색</button><button class="tab" url="/nms/getInfraAddView.do">신규등록</button>
    </p> -->
    <p class="searchTitle">통보문 검색</p>
    <table id="searchTable" class="searchTable">
        <tr>
            <th>구분</th>
            <td>
                <select id="key" name="" class="sendData">
                    <option value="">선택</option>
                    <option value="2">기상특보 통보문</option>
                    <option value="3">예비특보 통보문</option>
                    <option value="4">지진 통보문</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>일자</th>
            <td>
            <input id="emdCd" name="emdCd" class="sendData" value="" type="hidden">
                <input type="text" id="dat" name="dat" class="sendData date" style="width:100%; margin:1px; padding:0px;">
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
    <table id="resultHeader">
     <!--    <tr>
            <th width="70px">읍면동</th>
            <th width="115px">구분</th>
            <th>자료</th>
            <th width="70px">확인</th>
        </tr> -->
    </table>
    <div id="resultList" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark" style="max-height: 64%;">
	    <table></table>
    </div>

    <div class="detailWrapper"></div>

</div>