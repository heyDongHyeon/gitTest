<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.log.service.IfEvtLogVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");

ArrayList<IfEvtLogVo> list = (ArrayList<IfEvtLogVo>) request.getAttribute("result");
ArrayList<AuthGrpVo> auth = (ArrayList<AuthGrpVo>) request.getAttribute("auth");
ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");

/* CodeConvertor cde = (CodeConvertor) request.getAttribute("code");
HashMap<String, String> cdeGrp = cde.convertCodeGrpToAllCde("C02"); */
/* Set<String> key = cdeGrp.keySet();
Iterator<String> itr = key.iterator(); */

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String msgTypCd = map.get("msgTypCd");
if(msgTypCd == null) msgTypCd = "";
String startDat = map.get("startDat");
if(startDat == null) startDat = "";
String endDat = map.get("endDat");
if(endDat == null) endDat = "";

//ref_id character varying(30), -- ref ID
/* sender_id character varying(30) NOT NULL, -- 전송자아이디
svc_typ character varying(30) NOT NULL, -- 서비스유형
evt_addr character varying(251) NOT NULL, -- 사건지점전체주소
dsc_nm character varying(20) NOT NULL, -- 이름, guard_nm character varying(20) NOT NULL, -- 보호자이름
evt_dtm character(14) NOT NULL, -- 발생일시 */


%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.log.css">
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.system.mng.log.js"></script>
<script type="text/javascript">

var schObj = new Object();

schObj.msgTypCd = '<%=msgTypCd%>';
schObj.startDat = '<%=startDat%>';
schObj.endDat = '<%=endDat%>';

</script>
<div>
    <input type="hidden" id="offset" value="<%= offset %>" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

    <!-- <div id="header">
        <div id="back">뒤로가기</div>
    </div> -->

    <div id="wrap">
     	<div id="menuWrap">
        </div>
        <!-- <p class="searchTitle">
            <button class="logTab" url="/log/getAssetLogView.do" excel="Asset">시설물 관리</button>
            <button class="logTab" url="/log/getMsgLogView.do" excel="Msg">SMS</button>
            <button class="logTab" url="/log/getIf112LogView.do" excel="112">112 긴급영상 지원</button>
            <button class="logTab" url="/log/getIf112JsonLogView.do" excel="112Json">112 긴급출동 메소드 호출 현황</button>
            <button class="logTab" url="/log/getIf119LogView.do" excel="119">119 긴급출동</button>
            <button class="logTab" url="/log/getIfDscLogView.do" excel="Evt">사회적약자</button>
            <button class="logTab" active="active" url="/log/getIfEvtLogView.do" excel="Evt">이벤트로그</button>
            <button class="logTab" url="/log/getAccessView.do" excel="Access">접근이력관리</button>
        </p> -->
        <div id="title" url="/log/getIfEvtLogView.do" excel = "Evt">이벤트 로그</div>
        <div id="search">
            <span>메시지타입코드 : </span>
            <select id="msgTypCd" class="sendData">
                <option value="">전체</option>
                <option value="112">112</option>
                <option value="119">119</option>
                <option value="DSC">사회적약자</option>
            </select>
            <span>기간 : </span><input id="startDat" class="keyup sendData datePicker" type="text" value="" readonly> ~ <input id="endDat" class="keyup sendData datePicker" type="text" value="" readonly>
            <button id="searchBtn" class="paleBtn">검색</button>
            <button id="excelBtn"class="paleBtn">내보내기</button>
            <span id="count">총 <%= request.getAttribute("count") %>개의 건이 검색되었습니다.</span>
        </div>
        <div id="content">
           <table id="userList">
                <thead>
                    <tr>
<%
for(int i=0; i<column.size(); i++){
    if(column.get(i).getTblId().equals("xeus.if_event_log")
        ){
        //&& !"snd_dtm_rsp".equals(column.get(i).getColId())
        //&& !"snd_orgn".equals(column.get(i).getColId())){
        String col = column.get(i).getColNm();
        if(col != null){
            /* if(col.equals("수신자아이디")) col = "수신자";
            else if(col.equals("소속기관")) col = "소속"; */
            if(col.equals("사건지점위치(위도)")) col = "사건지점위치";
%>
                        <th><%= col %></th>
<%
        }
    }
}
%>
                    </tr>
                </thead>
                <tbody>
<%
if(list.size() == 0){
%>
                    <tr>
                        <td colspan="9"><b>검색결과가 존재하지 않습니다.</b></td>
                    </tr>
<%
}else{
    for(int i=0; i<list.size(); i++){
%>
                    <tr>
                        <!-- MsgTypCd
                        SndSysCd
                        RcvSysCd
                        MsgExchPtrn
                        BodyType
                        SndDtm
                        DataLen
                        RcvOrgn
                        ErrMsg -->
                        <td><%= list.get(i).getMsgTypCd() %></td>
                        <td><%= list.get(i).getSndSysCd() %></td>
                        <td><%= list.get(i).getRcvSysCd() %></td>
                        <td><%= list.get(i).getMsgExchPtrn() %></td>
                        <td><%= list.get(i).getBodyType() %></td>
                        <td><%= DateUtil.formatDate(list.get(i).getSndDtm()) %></td>
                        <td><%= list.get(i).getDataLen() %></td>
                        <td><%= list.get(i).getRcvOrgn() %></td>
                        <td><%= list.get(i).getErrMsg() %></td>

                        <%-- <td><%= list.get(i).get %></td> --%>
                        <%-- <td><%= DateUtil.formatDate(list.get(i).getWorkDat()) %></td> --%>
                        <!--

                          send_num character varying(50) NOT NULL, -- 발생번호
                          svc_typ character varying(30) NOT NULL, -- 서비스유형
                          evt_lon character varying(24) NOT NULL, -- 사건지점위치(경도)
                          evt_lat character varying(24) NOT NULL, -- 사건지점위치(위도)
                          evt_addr character varying(251) NOT NULL, -- 사건지점전체주소
                          evt_bjd character varying(19) NOT NULL, -- 지역코드
                          ref_id character varying(30), -- ref ID
                          dsc_nm character varying(20) NOT NULL, -- 이름
                          dsc_phone character varying(15) NOT NULL, -- 핸드폰번호
                          dsc_birth character varying(15), -- 출생일
                          dsc_sex character varying(5), -- 성별
                          dsc_addr character varying(251), -- 주소(주거지)
                          guard_nm character varying(20) NOT NULL, -- 보호자이름
                          guard_phone character varying(15) NOT NULL, -- 보호자 핸드폰
                          evt_dtm character(14) NOT NULL, -- 발생일시
                          info character varying(500), -- 신상정보
                          note character varying(500), -- 특이사항
                          sender_id character varying(30) NOT NULL, -- 전송자아이디
                           -->

                    </tr>
<%
    }
}
%>
                </tbody>
            </table>
        </div>
        <div class="paging_wrap"></div>
    </div>
</div>
