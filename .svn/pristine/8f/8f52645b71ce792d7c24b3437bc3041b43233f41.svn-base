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

/* CD94 // 긴급구조구분 */
HashMap<String, String> divisionMap = cde.convertCodeGrpToAllCde("C94");
Set<String> divisionSet = new TreeSet<String>(divisionMap.keySet());
Iterator<String> divisionKey = divisionSet.iterator();

ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
ArrayList<EmdVo> emd = (ArrayList<EmdVo>) request.getAttribute("emd");
ArrayList<LiVo> li = (ArrayList<LiVo>) request.getAttribute("li");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndms.infra.search.js"></script>
<div class="searchWrapper ui-droppable mCustomScrollbar" onselectstart="return false" data-mcs-theme="minimal-dark">

    <!-- <p class="searchTitle">
        <button class="tab" url="/nms/getInfraView.do">범례</button><button class="tab" active="active" url="/nms/getInfraSearchView.do">검색</button><button class="tab" url="/nms/getInfraAddView.do">신규등록</button>
    </p> -->

    <p class="searchTitle">긴급 구조 검색</p>
    <table id="searchTable" class="searchTable">
        <tr>
            <th>읍면동</th>
            <td>
            	<input id="key" name="key" class="sendData" type="hidden" value="1">

                <select id="emdCd" name="emdCd" class="sendData">
                    <option value="">전체</option>
<% for(int i=0; i<emd.size(); i++){ %>
                    <option value="<%= emd.get(i).getEmdCd() %>"><%= emd.get(i).getEmdKorNm() %></option>
<% } %>
                </select>
            </td>
        </tr>
       	<tr>
            <th>일자</th>
<!--             <td> -->
<!--                 <input type="text" id="dat" name="dat" class="sendData date" style="width:100%; margin:1px; padding:0px;"> -->
				<td class="thhead">
					<input type="text" id="startDate" class="datePickerStart sendData " readOnly>
					~
					<input type="text" id="endDate" class="datePickerEnd sendData" readOnly>
				</td>
<!--             </td> -->
        </tr>
        <tr>
            <th>구분</th>
            <td>
					<select id="division" name="구분" class="sendData wide">
						<option value="" selected>-선택-</option>
<%
						 while(divisionKey.hasNext()){
                			 String str = (String) divisionKey.next();
%>
						<option value="<%= str %>"><%= divisionMap.get(str) %></option>
<%
						 }
%>

					</select>
            </td>
        </tr>
        <!-- <tr>
            <th>종별</th>
            <td>
                <select id="" name="" class="sendData">
                    <option value="">전체</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>분류</th>
            <td>
                <select id="" name="" class="sendData">
                    <option value="">전체</option>
                </select>
            </td>
        </tr>
        <tr>
            <th>규모</th>
            <td>
                <select id="" name="" class="sendData">
                    <option value="">전체</option>
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
            <th>키워드 검색</th>
            <td>
                <input type="text" id="key" name="objName" class="sendData" style="width:100%; margin:1px; padding:0px;">
            </td>
        </tr>
         <tr id="hidden" style="display:none;">
            <td>
                <input type="text" id="key" name="objName" value="1">
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

	<div class="tRight" style="width: 49%; display: inline-block;float: right;">
    	<button id="excelDownBtn" class="whiteBtn" style="width: 88px; height: 28px;">엑셀 다운로드</button>
    </div>

    <div style="width: 460px; display: table-cell;">
        <p class="searchTitle" id="searchResult" style="width:49%; display: inline-block;">검색결과</p>
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