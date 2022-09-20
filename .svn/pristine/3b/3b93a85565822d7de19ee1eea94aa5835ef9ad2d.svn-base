<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.sysmgr.service.AuthGrpVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page import="geomex.xeus.user.service.UserVo"%>
<%@ include file="../common.jsp"%>
<%
    UserVo vo = (UserVo) request.getAttribute("userVo");

    ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
    CodeConvertor cde = (CodeConvertor) request.getAttribute("code");
    HashMap<String, String> cdeGrp = cde.convertCodeGrpToAllCde("C02");
    Set<String> key = cdeGrp.keySet();
    Iterator<String> itr = key.iterator();
%>
<%-- <script type="text/javascript" src="${ctxPath}/res/geomex.xeus.user.edit.js"></script>
<script type="text/javascript" src="${ctxPath}/res/xeusUser.js"></script> --%>
<script type="text/javascript">
/* $("#pwdChange").on("click", function(){
	$(".bpopup").find("#nowUserPwd, #newUserPwd, #newUserPwdRe").val("");
	$(".bpopup").bPopup({appendTo: $('#'+parentView)});
	$(".bpopup").find("#nowUserPwd").focus();
});

*/
/* 파일 다운 */
$('#editInfoPop').find(".doc_down").click(function(){
	var k = $(this).attr("k");
	var u = $(this).attr("u");

	if(k != null && k != "" && u != null && u != ""){
		_common.postForm.submit("/user/getFile.json", { "oathFileNm" : k , "userId" : u });
	}
});

/* 파일 수정 */
$('#editInfoPop').find('#editFileBtn').click(function(){
	$('#editInfoPop').find("#file").click();
});

$('#editInfoPop').find("#file").on("change", function(){
    var nm = $(this).val();
    if(nm != ""){
    	var arr = nm.split("\\");
    	var _html = '';
    	_html += '<p id="uploadFileNm">'+arr[arr.length-1]+'</p>';
    	$(_html).insertBefore("#editFileBtn");
    	if($('.doc_down').length > 0) $('.doc_down').remove();
    }
    /* if(nm != ""){
        confirm("선택하신 파일을 업로드 하시겠습니까?", function(){
            _common.formSubmit("/tvius/addDocFile.json", $("#hiddenForm"), function(json){
                if(json.realNm !== undefined && json.uploadNm !== undefined){
                    $('#docFileNm').val(json.uploadNm);
                    $('#docFilePath').val(json.realNm);
                }
            });
        }, function(){
            $(this).val("");
        });
    } */
});

$('#editInfoPop').find('#editBtn').click(function(){
	if($("#userNm").val() == ""){
		alert("사용자명을 입력하여 주십시오!");
		$("#userNm").focus();
		return false;
	}

	if($("#orgMgrNo").val() == ""){
		alert("소속기관을 선택하여 주십시오!");
		$("#orgMgrNo").focus();
		return false;
	}

	var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;

	if(filter.test($('#email').val()) != true){
		alert("이메일 형식이 아닙니다!");
		$('#email').focus();
		return false;
	}

	if($("#telNum").val() == ""){
		alert("휴대전화번호를 입력하여 주십시오!");
		$("#telNum").focus();
		return false;
	}

	if($("#birthDay").val() == ""){
		alert("생년월일을 입력하여 주십시오!");
		$("#birthDay").focus();
		return false;
	}

	//console.log(_common.utils.collectSendData('#user_edit_pop_wrap'));

	//_common.callAjax("/user/edit.json", _common.utils.collectSendData('#user_edit_pop_wrap'), function(json){
	_common.formSubmit("/user/edit.json", $("#sendForm"), function(json){
		if(json.result == null){
			alert(json.error);
		}else{
			alert("사용자 정보가 변경되었습니다.");
			$('#editInfoPop').find('#closeEditPop').click();
		}
	});


});
</script>
<style>

</style>
<div class="bpopup" id="user_edit_pop_wrap" style="display: none; width: 500px !important;">
    <div id="bpop_wrap">
        <h2 id="bpop_title">계정정보 수정</h2>
        <form id="sendForm" method="POST" enctype="multipart/form-data">
            <table style="width: 480px !important;">
                <tr class="top">
                    <th class="top">계정</th>
                    <td>
                        <input type="text" id="userId" name="userId" class="sendData" readonly="readonly" value="<%=vo.getUserId()%>"/>
                    </td>
                </tr>
                <tr>
                    <th class="top">이름</th>
                    <td>
                        <input type="text" class="sendData" id="userNm" name="userNm" placeholder="이름" value="<%=vo.getUserNm()%>"/>
                    </td>
                </tr>
                <tr>
                    <th class="top">비밀번호 확인</th>
                    <td>
                        <input type="password" id="userPwd" name="userPwd" class="sendData" size="30"/>
                    </td>
                </tr>
                <tr>
                    <th class="top">생년월일</th>
                    <td>
                        <input type="text" class="sendData" id="birthDay" name="birthDay" placeholder="생년월일 6자리 (주민등록번호 앞자리)" value="<%=vo.getBirthDay()%>"/>
                    </td>
                </tr>
                <tr>
                    <th class="top">소속기관</th>
                    <td>
                        <select class="sendData" id="orgMgrNo" name="orgMgrNo">
<% for(int i=0; i<orgz.size(); i++){ %>
                            <option value="<%= orgz.get(i).getOrgMgrNo() %>" <%if(orgz.get(i).getOrgMgrNo().equals(vo.getOrgMgrNo())){%>selected<%} %> ><%= orgz.get(i).getOrgNm() %></option>
<% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th class="top">부서명</th>
                    <td>
                        <input type="text" class="sendData" id="departNm" name="departNm" placeholder="부서명" value="<%=vo.getDepartNm()%>"/>
                    </td>
                </tr>
                <tr>
                    <th class="top">직급(직책)</th>
                    <td>
                        <input type="text" class="sendData" id="posNm" name="posNm" placeholder="직급 (직책)" value="<%=vo.getPosNm()%>"/>
                    </td>
                </tr>
                <tr>
                    <th class="top">사무실 전화번호</th>
                    <td>
                        <input type="text" class="sendData" id="telNum" name="telNum" placeholder="사무실 전화번호 ( - 없이 입력)" value="<%=vo.getTelNum()%>"/>
                    </td>
                </tr>
                <tr>
                    <th class="top">휴대폰 번호</th>
                    <td>
                        <input type="text" class="sendData" id="mobileNum" name="mobileNum" placeholder="휴대폰 번호 ( - 없이 입력)" value="<%=vo.getMobileNum()%>"/>
                    </td>
                </tr>
                <tr>
                    <th class="top">이메일 주소</th>
                    <td>
                        <input type="text" class="sendData" id="email" name="email" placeholder="이메일 주소" value="<%=vo.getEmail()%>"/>
                    </td>
                </tr>
                <tr id="downTr">
                    <th class="top">서약서</th>
                    <td id="oathArea">
                        <%-- <input type="text" id="fileDown" class="medium" value="<%= vo.getOathFileNm() %>" k="<%= vo.getOathFileNm() %>" u="<%= vo.getUserId() %>" readOnly /> --%>
<% if(!StrUtil.chkNull(vo.getOathFileNm()).equals("")){ %>
                        <a class="doc_down" style="cursor: pointer; font-weight: bold;" k= "<%=vo.getOathFileNm()%>" u="<%=vo.getUserId()%>" target="_blank"><%=vo.getOathFileNm()%></a>
<% } %>
                        <input type="text" id="subDir" name="subDir" class="hidden sendData" value="user\">
                        <input type="file" id="file" name="file" class="hidden sendData" size="30"/>
                        <button id="editFileBtn" type="button">첨부파일 수정</button>
                    </td>
                </tr>

                <!-- hidden -->

                <%-- <tr class="hidden">
                    <th class="top">권한그룹</th>
                    <td>
                        <input type="text" class="sendData" id="authGrpNo" value="<%=vo.getAuthGrpNo()%>"/>
                    </td>
                </tr>
                <tr class="hidden">
                    <th class="top">신청일시</th>
                    <td>
                        <input type="text" class="sendData" id="reqDat" value="<%=vo.getReqDat()%>"/>
                    </td>
                </tr>
                <tr class="hidden">
                    <th class="top">계정상태</th>
                    <td>
                        <input type="text" class="sendData" id="authStatCd" value="<%=vo.getAuthStatCd()%>"/>
                    </td>
                </tr>
                <tr class="hidden">
                    <th class="top">승인일시</th>
                    <td>
                        <input type="text" class="sendData" id="acptDat" value="<%=vo.getAcptDat()%>"/>
                    </td>
                </tr>
                <tr class="hidden">
                    <th class="top">잠김일시</th>
                    <td>
                        <input type="text" class="sendData" id="lockDat" value="<%=vo.getLockDat()%>"/>
                    </td>
                </tr>
                <tr class="hidden">
                    <th class="top">폐기일시</th>
                    <td>
                        <input type="text" class="sendData" id="exprDat" value="<%=vo.getExprDat()%>"/>
                    </td>
                </tr>
                <tr class="hidden">
                    <th class="top">승인자ID</th>
                    <td>
                        <input type="text" class="sendData" id="acptUserId" value="<%=vo.getAcptUserId()%>"/>
                    </td>
                </tr>
                <tr class="hidden">
                    <th class="top">인증시도횟수</th>
                    <td>
                        <input type="text" class="sendData" id="authAtmtCnt" value="<%=vo.getAuthAtmtCnt()%>"/>
                    </td>
                </tr>
                <tr class="hidden">
                    <th class="top">인증시도횟수</th>
                    <td>
                        <input type="text" class="sendData" id="authConnIp" value="<%=vo.getAuthConnIp()%>"/>
                    </td>
                </tr>
                 --%>

            </table>
        </form>
        <table style="width: 480px !important;">
            <tr align="center">
                <td class="lastTd" colspan="2" style="border: 0 !important;">
                    <button id="editBtn" tabindex="4">수정</button>
                    <button id="closeEditPop" class="bpopClose" tabindex="5">취소</button>
                </td>
            </tr>
        </table>
    </div>
</div>