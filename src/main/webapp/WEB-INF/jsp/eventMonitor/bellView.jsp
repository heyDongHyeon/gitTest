<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.eventmonitor.service.BellVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.TreeSet"%>
<%@ include file="../common.jsp" %>
<%
    CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

    /* C63 // 이벤트 종류 */
    HashMap<String, String> chkEventTyp = cde.convertCodeGrpToAllCde("C63");
    Set<String> chkEventTypKey = new TreeSet<String>(chkEventTyp.keySet());
    Iterator<String> chkEventTypItr = chkEventTypKey.iterator();

    /* C64 // 이벤트 종료사유 */
    HashMap<String, String> chkCloseTyp = cde.convertCodeGrpToAllCde("C64");
    Set<String> chkCloseTypKey = new TreeSet<String>(chkCloseTyp.keySet());
    Iterator<String> chkCloseTypItr = chkCloseTypKey.iterator();

    /* C65 // 이벤트 처리상태 */
    HashMap<String, String> chkStateTyp = cde.convertCodeGrpToAllCde("C65");
    Set<String> chkStateTypKey = new TreeSet<String>(chkStateTyp.keySet());
    Iterator<String> chkStateTypItr = chkStateTypKey.iterator();

    ArrayList<BellVo> bell = (ArrayList<BellVo>)request.getAttribute("bell");

%>
<script type="text/javascript" src="<%=context%>/res/geomex.xeus.event.bell.view.js"></script>
<style>
    .searchWrapper #searchResult tr th{
        max-width: 60px;
    }

    .searchWrapper #searchResult tr td{
        padding-left: 7px;
    }

    .searchWrapper #settingTable tr td:hover{
        cursor: pointer;
    }
</style>
<div class="searchWrapper">

    <p class="searchTitle">비상벨 모니터링</p>
    <table id="settingTable">
        <tr>
            <th>이벤트 시간</th>
            <th>CCTV명</th>
            <th>상태</th>
        </tr>
        <%
        if(bell.size() == 0){
        %>
        <tr>
            <td colspan="3" class="tCenter">
                데이터가 존재하지 않습니다.
            </td>
        </tr>
        <%
        } else {
            for( int i=0; i<bell.size(); i++){
        %>
        <tr evtno="<%= bell.get(i).getEvtMgrNo() %>">
            <td><%= DateUtil.formatDate( bell.get(i).getRecvDat() ) %></td>
            <td><%= StrUtil.chkNull( bell.get(i).getCctvNm() ) %></td>
            <td><%= chkStateTyp.get(bell.get(i).getStateCd()) %></td>
        </tr>

        <%
            }
        }
        %>
    </table>

    <p class="searchTitle">비상벨 상세정보</p>
    <div>
        <span>종료사유</span>
        <select id="selectCloseCd">
            <!-- <option value="">선택 // 코드표 생기면 넣기</option> -->
            <option value="">전체</option>
            <%
                while (chkCloseTypItr.hasNext()) {
                    String str = (String) chkCloseTypItr.next();
            %>
            <option value="<%=str%>"><%=chkCloseTyp.get(str)%></option>
            <%
                }
            %>
        </select>
    </div>
    <div class="btnDiv">
        <button id="btn_save" class="whiteBtn">상황종료</button>
    </div>
    <table id="searchResult">
        <colgroup>
            <col width="60" />
            <col width="" />
        </colgroup>
        <tr>
            <th>시간</th>
            <td>
                <input id="evtMgrNo" type="hidden"/>
                <input id="closeCd" type="hidden"/>
                <span id="recvDat"></span>
            </td>
        </tr>
        <tr>
            <th>CCTV명</th>
            <td>
                <span id="cctvNm"></span>
            </td>
        </tr>
        <tr>
            <th>위치</th>
            <td>
                <div>
                    <span id="evtLat"></span>
                    <span id="evtLon"></span>
                </div>
            </td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td>
                <span id="bellAddr"></span>
            </td>
        </tr>
        <tr>
            <th>조치내용</th>
            <td>
                <span id="actionNote"></span>
            </td>
        </tr>
    </table>

    <div class="btnDiv">
        <button id="btn_net" class="whiteBtn">투망모니터링</button>
    </div>

</div>