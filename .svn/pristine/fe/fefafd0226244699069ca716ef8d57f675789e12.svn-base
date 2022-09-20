<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.map.service.EmdVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.equipmgr.service.MobileVo"%>
<%@ page import="geomex.xeus.equipmgr.service.SiteVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ include file="../common.jsp" %>
<%
CodeConvertor cde = (CodeConvertor) request.getAttribute("code");
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");
ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
ArrayList<EmdVo> emd = (ArrayList<EmdVo>) request.getAttribute("emd");
HashMap<String, String> map = (HashMap<String, String>) request.getAttribute("map");

ArrayList<SiteVo> list = (ArrayList<SiteVo>)request.getAttribute("result");
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.nms.site.js"></script>
<div class="overflow searchWrapper" onselectstart="return false">

    <p class="searchTitle">
	    <button class="tab" active="active" url="/nms/getSiteManageView.do" size="1100">검색</button><button class="tab" url="/nms/getSiteRegView.do" size="450">신규등록</button>
	</p>
	<table id="searchTable" class="searchTable">
	   <tr>
            <%-- <th>읍면동</th>
            <td>
                <select id="emdCd" name="emdCd" class="sendData">
                    <option value="">전체</option>
<% for(int i=0; i<emd.size(); i++){
	String selected = "";
    if(emd.get(i).getEmdCd().equals(map.get("emdCd"))) selected = "selected"; %>
                    <option value="<%= emd.get(i).getEmdCd() %>" <%= selected %>><%= emd.get(i).getEmdKorNm() %></option>
<% } %>
                </select>
            </td> --%>
            <th>관리기관</th>
            <td>
                <select id="orgMgrNo" name="orgMgrNo" class="sendData">
                    <option value="">전체</option>
<% for(int i=0; i<orgz.size(); i++){
	String selected = "";
    if(orgz.get(i).getOrgMgrNo().equals(map.get("orgMgrNo"))) selected = "selected"; %>
                    <option value="<%= orgz.get(i).getOrgMgrNo() %>" <%= selected %>><%= orgz.get(i).getOrgNm() %></option>
<% } %>
                </select>
            </td>
            <th>명칭</th>
            <td>
                <input type="text" id="siteNm" name="siteNm" class="wide sendData" value="<%= StrUtil.chkNull(map.get("siteNm")) %>">
            </td>
            <td class="tCenter">
                <button class="blackBtn" id="searchBtn">검색</button>
            </td>
	   </tr>
	</table>

    <p class="searchTitle">검색결과</p>
    <table id="resultTable">
        <thead>
            <tr>
               <th>사이트명</th>
               <th>폴구분</th>
               <th>설치일자</th>
               <th>통신사</th>
               <!-- <th>카메라수</th> -->
               <!-- <th>부속시설수</th> -->
               <th>관리</th>
            </tr>
        </thead>
        <tbody>
<%
if(list.size() == 0){
%>
            <tr class="tCenter">
                <td colspan="7"><p style="padding: 150px 0px;"><b>결과가 존재하지 않습니다.</b></p></td>
            </tr>
<%
}else{
    for(int i=0; i<list.size(); i++){
%>
            <tr class="tCenter">
                <td><%= StrUtil.chkNull(list.get(i).getSiteNm()) %></td>
                <td><%= cde.convertCodeToName("C09", list.get(i).getPollGbnCd()) %></td>
                <td><%= DateUtil.formatDate(list.get(i).getInstDat().trim()) %></td>
                <td><%= StrUtil.chkNull(list.get(i).getComMgrNm()) %></td>
                <%-- <td><%= list.get(i).getCctvCnt() %></td> --%>
                <%-- <td><%= list.get(i).getAcryCnt() %></td> --%>
                <td>
                    <!-- 171212 -->
                    <%-- <button class="blueBtn detailBtn" k="<%= list.get(i).getMgrNo() %>">상세</button>
                    <button class="blueBtn locBtn" k="<%= StrUtil.chkNull(list.get(i).getRepMgrNo()) %>">위치</button>
                    <button class="blueBtn delBtn" k="<%= list.get(i).getMgrNo() %>">삭제</button> --%>
                    <button class="detailBtn" k="<%= list.get(i).getMgrNo() %>"></button>
                    <%-- <button class="locBtn" k="<%= StrUtil.chkNull(list.get(i).getRepMgrNo()) %>"></button> --%>
                    <%-- <button class="delBtn" k="<%= list.get(i).getMgrNo() %>"></button> --%>
                </td>
            </tr>
<%
    }
}
%>
        </tbody>
    </table>

    <div id="detailWrapper"></div>

</div>