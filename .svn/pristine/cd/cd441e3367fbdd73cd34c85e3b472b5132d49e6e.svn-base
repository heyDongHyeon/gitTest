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
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.send.voice.js"></script>
<div class="searchWrapper ui-droppable mCustomScrollbar" onselectstart="return false" data-mcs-theme="minimal-dark">

    <!-- <p class="searchTitle">
        <button class="tab" url="/nms/getInfraView.do">범례</button><button class="tab" active="active" url="/nms/getInfraSearchView.do">검색</button><button class="tab" url="/nms/getInfraAddView.do">신규등록</button>
    </p> -->

    <p class="searchTitle">방송장비 검색</p>
    <table id="searchTable" class="searchTable">
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
<!--         <tr> -->
<!--             <th>구분</th> -->
<!--             <td> -->
<!--                 <select id="" name="" class="sendData"> -->
<!--                     <option value="">전체</option> -->
<!--                 </select> -->
<!--             </td> -->
<!--         </tr> -->
        <tr>
            <th>단말기명</th>
            <td>
                <input type="text" id="objName" name="objName" class="sendData" style="width:100%; margin:1px; padding:0px;">
            </td>
        </tr>
    </table>

    <div class="btnDiv">
        <button class="blackBtn" style="margin: 3px 0px 3px 0px;" id="searchBtn">검색</button>
    </div>

    <p class="searchTitle">영역 검색</p>
    <table>
        <tr>
            <th colspan="3">검색방법</th>
        </tr>
        <tr>
            <td class="tRight">
                <input type="radio" name="spatial" class="drawType" value="Circle" id="circle"><label for="circle">반경</label>
            </td>
            <td class="tCenter" style="border-left: none !important;">
                <input type="radio" name="spatial" class="drawType" value="Box" id="box"><label for="box">사각형</label>
            </td>
            <td class="tLeft" style="border-left: none !important; height: 30px;">
                <input type="radio" name="spatial" class="drawType" value="Polygon" id="polygon"><label for="polygon">다각형</label>
            </td>
            <!-- <td class="tRight noneBack">
                <button class="blueBtn" id="spatialBtn">영역 선택</button>
            </td> -->
        </tr>
        <tr>
            <th colspan="3" id="drawCncl" class="hidden pointer">그리기를 종료하시려면 여기를 눌러주세요.</th>
        </tr>
    </table>

    <div style="width: 300px; display: table-cell;">
        <p class="searchTitle" style="width:49%; display: inline-block;">검색결과</p>
    </div>
    <table>
     	<tr>
     		<th width="20px"><input id="send_all_chk" type="checkbox" style="vertical-align: middle"></th>
            <th width="100px">단말기명</th>
            <th width="100px">번호</th>
        </tr>
    </table>
    <div id="resultList" class=" ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark" style="max-height: 64%;">
	    <table id="send_table"></table>
    </div>

    <div class="detailWrapper"></div>

</div>