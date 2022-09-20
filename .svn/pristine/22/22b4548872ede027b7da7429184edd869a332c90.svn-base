<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.map.service.DoroVo"%>
<%@ page import="geomex.xeus.map.service.EmdVo"%>
<%@ page import="geomex.xeus.map.service.LiVo"%>
<%@ page import="java.util.ArrayList"%>
<%

String liChk = (String) request.getAttribute("liChk");
ArrayList<EmdVo> emdList = (ArrayList<EmdVo>) request.getAttribute("emdList");
ArrayList<LiVo> liList = (ArrayList<LiVo>) request.getAttribute("liList");
ArrayList<DoroVo> rnList = (ArrayList<DoroVo>) request.getAttribute("rnList");
%>
<div id="searchBox" align="center">
    <div id="searchWrap">
        <select id="searchMode" class="searchMode">
            <option value="1">지번</option>
            <option value="2">새주소</option>
            <option value="3">좌표</option>
            <option value="4">웹검색</option>
        </select>

        <table id="jibunUI">
            <tr>
                <td>읍면동</td>
                <td>
                    <select id="emdCd" class="emdSearchCd sendData">
                    <option value="-99999">선택하세요.</option>
                    <%
                    for(int i=0; i<emdList.size(); i++){
                        //String emdCd = emdList.get(i).getEmdCd().substring(5);
                        String emdCd = emdList.get(i).getEmdCd();
                    %>
                    <option value="<%= emdCd %>"><%= emdList.get(i).getEmdKorNm() %></option>
                    <%
                    }
                    %>
                    </select>
                </td>
                <% if(liChk.equals("Y")){ %>
                <td>리</td>
                <td>
                    <select id="liCd" class="liCd sendData" >
                        <option value="-99999">선택하세요.</option>
                        <%
                        for(int i=0; i<liList.size(); i++){
                            String liCd = liList.get(i).getLiCd().substring(8);
                            String emdCd = liList.get(i).getLiCd().substring(5,8);
                        %>
                        <option value="<%= liCd %>" emd = "<%=emdCd%>" bjdCd="<%=liList.get(i).getLiCd()%>"><%= liList.get(i).getLiKorNm() %></option>
                        <%
                        }
                        %>
                    </select>
                </td>
                <% } %>
                <td>
                    <input type="checkbox" id="san" name="san" class="sendData">
                    <label for="san">산</label>
                </td>
                <td>
                    <input type="text" id="bon" class="sendData keyup" placeholder="본번" for="#jibunSearch">
                    <input type="text" id="bu" class="sendData keyup" placeholder="부번" for="#jibunSearch">
                </td>
                <td colspan="2" class="lastTd"><button id="jibunSearch" class="jibunSearch searchBtn blueBtn">검 색</button></td>
            </tr>
        </table>

        <table id="doroUI" class="hidden">
            <tr>
                <td>도로명</td>
                <td>
                    <select id="rnCd" class="rnCd sendData">
                    <%
                    for(int i=0; i<rnList.size(); i++){
                        String rnCd = rnList.get(i).getRnCd();
                    %>
                        <option value="<%= rnCd %>"><%= rnList.get(i).getRn() %></option>
                    <%
                    }
                    %>
                    </select>
                </td>
                <td>건물번호</td>
                <td>
                   <input type="text" id="bon" class="sendData keyup" placeholder="본번" for="#doroSearch"> -
                   <input type="text" id="bu" class="sendData keyup" placeholder="부번" for="#doroSearch">
                </td>
                <td colspan="2" class="lastTd"><button id="doroSearch" class="doroSearch searchBtn blueBtn">검 색</button></td>
            </tr>
        </table>

        <table id="lnglatUI" class="hidden">
            <tr>
                <td>경도</td>
                <td>
                    <input type="text" id="lng" class="sendData keynext" placeholder="도">
                </td>
                <td>위도</td>
                <td>
                    <input type="text" id="lat" class="sendData keyup" placeholder="도" for="#lnglatSearch">
                </td>
                <td colspan="2" class="lastTd"><button id="lnglatSearch" class="lnglatSearch searchBtn blueBtn">검 색</button></td>
            </tr>
        </table>

        <table id="addrUI" class="hidden">
            <tr>
                <td>주소 또는 건물명</td>
                <td>
                    <input type="text" id="addr" class="keyup" value="" style="width: 300px;" for="#addrSearch">
                </td>
                <td class="lastTd"><button id="addrSearch" class="addrSearch searchBtn blueBtn">검 색</button></td>
            </tr>
        </table>
    </div>
</div>

<div id="srchResult" align="center">

</div>