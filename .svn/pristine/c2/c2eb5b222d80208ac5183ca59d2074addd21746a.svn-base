<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.map.service.EmdVo"%>
<%@ page import="geomex.xeus.map.service.LiVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvVo"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<CctvVo> list = (ArrayList<CctvVo>) request.getAttribute("result");

CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

HashMap<String, String> gbn = cde.convertCodeGrpToAllCde("C14");
Set<String> gbnKey = gbn.keySet();
Iterator<String> gbnItr = gbnKey.iterator();

ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
ArrayList<EmdVo> emd = (ArrayList<EmdVo>) request.getAttribute("emd");
ArrayList<LiVo> li = (ArrayList<LiVo>) request.getAttribute("li");

HashMap<String, String> param = (HashMap<String, String>)request.getAttribute("param");

String chkPage = "";

if (param.containsKey("chkPage"))       chkPage = param.get("chkPage").trim();
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.cctv.search.js"></script>
<script type="text/javascript">
var chkPage = '<%=chkPage%>';
</script>
<div class="searchWrapper mCustomScrollbar" data-mcs-theme="minimal-dark">

    <p class="searchTitle">CCTV 속성 검색</p>
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
    <%--     <tr>
            <th>관리기관</th>
            <td>
                <select id="orgMgrNo" name="orgMgrNo" class="sendData">
                    <option value="">전체</option>
<% for(int i=0; i<orgz.size(); i++){ %>
                    <option value="<%= orgz.get(i).getOrgMgrNo() %>"><%= orgz.get(i).getOrgNm() %></option>
<% } %>
                </select>
            </td>
        </tr> --%>
       <%--  <tr>
            <th>설치목적</th>
            <td>
                <select id="gbnCd" name="gbnCd" class="sendData">
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
                <input type="text" id="cctvNm" name="cctvNm" class="sendData" style="margin: 1px !important; width: 99%;">
            </td>
        </tr>
        <!-- 171212 -->
        <!-- <tr>
            <td colspan="2" class="tCenter noneBack">
                <button class="blueBtn" id="searchBtn">검색</button>
            </td>
        </tr> -->
    </table>
    <div class="btnDiv">
        <button class="blackBtn" id="searchBtn" style="width: 80px; height: 28px;">검색</button>
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

    <!-- 171212 생성 -->
    <div style="width: 340px; display: table-cell;">
        <p class="searchTitle" style="width:49%; display: inline-block;">검색결과</p>

	    <div class="tRight" style="width: 49%; display: inline-block;">
            <%
            if ( !chkPage.equals("tvius") ){
            %>
	        <button id="excelBtn" class="whiteBtn" style="width: 88px; height: 28px;">내보내기</button>
            <% } %>
	    </div>

    </div>

    <table id="resultTable">
        <thead>
	        <!-- <tr>
	            <td colspan="3" class="tRight noneBack">
	               <button id="excelBtn" class="blueBtn">내보내기</button>
	            </td>
	        </tr> -->
	        <tr>
	            <th>구분</th>
	            <th>명칭</th>
                <%
                if(chkPage.equals("tvius")) {
                %>
                <th>위치</th>
                <%
                } else {
                %>
	            <th>관리</th>
                <%
                }
                %>
	        </tr>
        </thead>
        <tbody></tbody>
    </table>


</div>