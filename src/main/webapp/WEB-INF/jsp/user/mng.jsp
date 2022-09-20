<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.user.service.UserVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");

ArrayList<UserVo> list = (ArrayList<UserVo>) request.getAttribute("result");
ArrayList<AuthGrpVo> auth = (ArrayList<AuthGrpVo>) request.getAttribute("auth");
ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");

CodeConvertor cde = (CodeConvertor) request.getAttribute("code");
HashMap<String, String> cdeGrp = cde.convertCodeGrpToAllCde("C02");
Set<String> key = cdeGrp.keySet();
Iterator<String> itr = key.iterator();

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String gbn = map.get("gbn");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String searchStr = map.get("userIdOrNm");
if(searchStr == null) searchStr = "";
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.userMng.css">
<script type="text/javascript" src="<%= context %>/common/jquery.spin.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.paging.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.timepicker.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.bpopup.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.form.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.download.js"></script>
<script type="text/javascript" src="<%= context %>/common/tooltipsy.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/tea-block.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/base64.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/utf8.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/jsbn.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/rsa.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/helper.js"></script>
<script type="text/javascript" src="<%= context %>/common/string.js"></script>
<script type="text/javascript" src="<%= context %>/common/HashMap.js"></script>
<script type="text/javascript" src="<%= context %>/common/string.js"></script>
<script type="text/javascript" src="<%= context %>/common/Date.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.user.mng.js"></script>
<script type="text/javascript">
var userIdOrNm = "<%=searchStr%>";
var offset="<%= offset %>";
var gbn="<%= gbn %>";
var idChkStat = false;

_common.callAjax("/sysMng/getBasicTopMenuView.do", {'gbn': gbn}, function(view) {
	$("#" + parentView).find("#menuWrap").html('');
	$("#" + parentView).find("#menuWrap").html(view);
});
</script>

	<input type="hidden" id="offset" value="<%= offset %>" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

    <!-- <div id="header">
        <div id="back">뒤로가기</div>
    </div> -->

	<div id="wrap">
		<div id="menuWrap">
        </div>

        <div id="search">
            <input id="searchInput" class="keyup" for="#searchBtn" type="text" value="<%= searchStr %>" placeholder="계정 또는 이름">
            <span>사용자상태 </span>
            <select id="searchAuthStatCd">
                <option value="">전체</option>
<%
while(itr.hasNext()){    String str = (String)itr.next();
    if(!str.equals("15")){

%>
                <option value="<%= str %>"><%= cdeGrp.get(str) %></option>
<%
    }
}
%>
            </select>
            <span>사용자권한 </span>
            <select id="searchAuthGrpNo">

                <option value="">전체</option>
<%
for(int i=0; i<auth.size(); i++){ %>
                    <option value="<%= auth.get(i).getAuthGrpNo() %>"><%= auth.get(i).getAuthGrpNm() %></option>
<% } %>
            </select>
            <button id="searchBtn" class="stat_btn">검색</button>
            <button id="addBtn" class="stat_btn stat_gray_btn" >신규추가</button>
            <span id="count">총 <%= request.getAttribute("count") %>개의 계정이 검색되었습니다.</span>
        </div>
        <div id="content">
           <table id="userList">
	            <thead>
		            <tr>
<%
for(int i=0; i<column.size(); i++){
    if(column.get(i).getTblId().equals("xeus.mt_usr_desc")
    	&& !"user_pwd".equals(column.get(i).getColId())
    	&& !"oath_file_path".equals(column.get(i).getColId())
    	&& !"mobile_num".equals(column.get(i).getColId())
    	&& !"depart_nm".equals(column.get(i).getColId())
    	&& !"auth_atmt_cnt".equals(column.get(i).getColId())
    	&& !"expr_dat".equals(column.get(i).getColId())
    	&& !"pos_nm".equals(column.get(i).getColId())
    	&& !"board_info".equals(column.get(i).getColId())){
    	String col = column.get(i).getColNm();
    	if(col != null){
    		col = col.replace("관리번호", "").replace("코드", "");
            if(col.equals("사무실전화")) col = "연락처";
            else if(col.equals("소속기관")) col = "소속";
%>
                        <th><%= col %></th>
<%
    	}
    }
}
%>
                        <th>관리</th>
		            </tr>
	            </thead>
	            <tbody>
<%
if(list.size() == 0){
%>
					<tr>
                        <td colspan="16"><b>검색결과가 존재하지 않습니다.</b></td>
                    </tr>
<%
}else{
	for(int i=0; i<list.size(); i++){
		String usrId = list.get(i).getUserId();
		String file = StrUtil.chkNull(list.get(i).getOathFileNm());
%>
		            <tr>
		                <td><%= usrId %></td>
		                <td><%= list.get(i).getUserNm() %></td>
		                <td><%= list.get(i).getBirthDay() %></td>
		                <td><%= StrUtil.chkNull(list.get(i).getAuthGrpNm()) %></td>
		                <td>
                            <%= StrUtil.chkNull(list.get(i).getOrgNm()) %><br>
                            <%= StrUtil.chkNull(list.get(i).getDepartNm()) %><br>
                            <%= StrUtil.chkNull(list.get(i).getPosNm()) %>
                        </td>
		                <td>
                            <%= StrUtil.strTelAdd(list.get(i).getTelNum()) %><br>
                            <%= StrUtil.strTelAdd(list.get(i).getMobileNum()) %>
                        </td>
		                <td><%= list.get(i).getEmail() %></td>
		                <td><%= cde.convertCodeToName("C02", list.get(i).getAuthStatCd()) %></td>
		                <td><%= DateUtil.formatDate(list.get(i).getReqDat()) %></td>
		                <td><%= DateUtil.formatDate(list.get(i).getAcptDat()) %></td>
		                <td><%= DateUtil.formatDate(list.get(i).getLockDat()) %></td>
                        <!--
                        180615 이은규
                        폐기목록은 어차피 목록에 표시되지 않으므로 표시 컬럼에서 제외한다.
                        -->
		                <%-- <td><%= DateUtil.formatDate(list.get(i).getExprDat()) %></td> --%>
		                <td><%= StrUtil.chkNull(list.get(i).getAcptUserId()) %></td>
		                <td class="downBtn" k="<%= file %>" u="<%= usrId %>" ><%= file %></td>
                        <td><%= StrUtil.chkNull(list.get(i).getAuthConnIp()) %></td>
		                <td><button class="mngBtn" k="<%= list.get(i).getUserId() %>"></button></td>
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

	<div class="bpopup" id="user_edit_pop_wrap">
    <div id="bpop_wrap">
        <h2 id="bpop_title">사용자 관리</h2>
        <table>
            <tr class="top">
                <th class="top">계정</th>
                <td>
                    <input type="text" class="sendData" id="userId" readonly="readonly" placeholder="아이디 (6자 이상)"/>
                    <button id="idChk" class="add" type="button">중복확인</button>
                </td>
            </tr>
            <tr>
                <th class="top">이름</th>
                <td>
                    <input type="text" class="sendData" id="userNm" placeholder="이름"/>
                </td>
            </tr>
            <tr class="add">
                <th class="top">비밀번호</th>
                <td>
                    <input type="password" id="userPwd" placeholder="비밀번호 (영문, 숫자, 특수문자 모두 포함)"/>
                </td>
            </tr>
            <tr class="add">
                <th class="top">비밀번호확인</th>
                <td>
                    <input type="password" id="userPwdChk" placeholder="비밀번호 확인"/>
                </td>
            </tr>
            <tr>
                <th class="top">생년월일</th>
                <td>
                    <input type="text" class="sendData" id="birthDay" placeholder="생년월일 6자리 (주민등록번호 앞자리)"/>
                </td>
            </tr>
            <tr>
                <th class="top">권한그룹</th>
                <td>
                    <select class="sendData" id="authGrpNo">
<%
for(int i=0; i<auth.size(); i++){ %>
                        <option value="<%= auth.get(i).getAuthGrpNo() %>"><%= auth.get(i).getAuthGrpNm() %></option>
<% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th class="top">소속기관</th>
                <td>
                    <select class="sendData" id="orgMgrNo">
<% for(int i=0; i<orgz.size(); i++){ %>
                        <option value="<%= orgz.get(i).getOrgMgrNo() %>"><%= orgz.get(i).getOrgNm() %></option>
<% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <th class="top">부서명</th>
                <td>
                    <input type="text" class="sendData" id="departNm" placeholder="부서명"/>
                </td>
            </tr>
            <tr>
                <th class="top">직급(직책)</th>
                <td>
                    <input type="text" class="sendData" id="posNm" placeholder="직급 (직책)"/>
                </td>
            </tr>
            <tr>
                <th class="top">사무실 전화번호</th>
                <td>
                    <input type="text" class="sendData" id="telNum" placeholder="사무실 전화번호 ( - 없이 입력)"/>
                </td>
            </tr>
            <tr>
                <th class="top">휴대폰 번호</th>
                <td>
                    <input type="text" class="sendData" id="mobileNum" placeholder="휴대폰 번호 ( - 없이 입력)"/>
                </td>
            </tr>
            <tr>
                <th class="top">이메일 주소</th>
                <td>
                    <input type="text" class="sendData" id="email" placeholder="이메일 주소"/>
                </td>
            </tr>
            <tr>
                <th class="top">인증IP</th>
                <td>
                    <input type="text" class="sendData" id="authConnIp" placeholder="인증IP ( 0.0.0.0 형태로 입력)"/>
                </td>
            </tr>
            <tr class="edit">
                <th class="top">계정상태</th>
                <td>
                    <select class="sendData" id="authStatCd">
<%
//180601 이은규
//위에서 itr이 사용되었으므로 itr 다시 초기화
itr = key.iterator();
while(itr.hasNext()){
    String str = (String)itr.next();
    if(!str.equals("11")){
%>
                        <option value="<%= str %>"><%= cdeGrp.get(str) %></option>
<%
    }
}
%>
                    </select>
                </td>
            </tr>
            <tr class="hidden">
                <th class="top">승인일시</th>
                <td>
                    <input type="text" class="sendData" id="acptDat" />
                </td>
            </tr>
            <tr class="hidden">
                <th class="top">잠김일시</th>
                <td>
                    <input type="text" class="sendData" id="lockDat" />
                </td>
            </tr>
            <tr class="hidden">
                <th class="top">폐기일시</th>
                <td>
                    <input type="text" class="sendData" id="exprDat" />
                </td>
            </tr>
            <tr class="hidden">
                <th class="top">인증시도횟수</th>
                <td>
                    <input type="text" class="sendData" id="authAtmtCnt" />
                </td>
            </tr>
        </table>
        <table>
            <tr align="center">
                <td class="lastTd" colspan="2" style="border: 0 !important;">
                    <button id="saveBtn" tabindex="4">저장</button>
                    <button id="closeEditPop" class="bpopClose" tabindex="5">취소</button>
                </td>
            </tr>
        </table>
    </div>
</div>
