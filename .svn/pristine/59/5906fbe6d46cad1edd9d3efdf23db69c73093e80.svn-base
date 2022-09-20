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
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.send.hist.js"></script>
<div class="searchWrapper ui-droppable mCustomScrollbar" onselectstart="return false" data-mcs-theme="minimal-dark">

    <!-- <p class="searchTitle">
        <button class="tab" url="/nms/getInfraView.do">범례</button><button class="tab" active="active" url="/nms/getInfraSearchView.do">검색</button><button class="tab" url="/nms/getInfraAddView.do">신규등록</button>
    </p> -->

	<input type="hidden" id="offset" value="0" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

    <p class="searchTitle">이력 검색</p>
    <table id="searchTable" class="searchTable">
        <tr>
            <th>구분</th>
            <td>
                <select id="sendTyp" name="" class="sendData">
                    <option value="">전체</option>
                    <option value="0">SMS 발송</option>
                    <option value="1">음성 방송</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>일자</th>
            <td>
                <input type="text" id="sendDt" name="sendDt" class="sendData datePicker" style="width:100%; margin:1px; padding:0px;">
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

    <table id="">
    	<thead>
	     	<tr>
	            <th width="80px">구분</th>
	            <th width="100px">대상</th>
	            <th width="100px">전송일</th>
	            <th width="40px"></th>
	        </tr>
    	</thead>
    	<tbody>

    	</tbody>
    </table>

    <div id="resultList" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark" style="max-height: 33%;">
	    <table></table>
    </div>

	<div class="paging_wrap"></div>
    <div class="detailWrapper" style="display: none;">
    	 <p class="searchTitle">이력 상세정보</p>
    	<table>
    	</table>
    </div>

</div>