<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$('#edit_pw').find('#saveBtn').click(function(){
	var nowUserPwd = $("#nowUserPwd").val();
	var newUserPwd = $("#newUserPwd").val();
	var newUserPwdRe = $("#newUserPwdRe").val();

	if(nowUserPwd.length < 8){
		alert("패스워드는 8자리 이상 입력하여 주십시오!");
		$("#nowUserPwd").focus();
		return false;
	}

	if(!newUserPwd.match(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/)){
		alert("비밀번호는 문자, 숫자, 특수문자의 조합으로 8~16자리로 입력해주세요.");
		$("#newUserPwd").focus();
		return false;
	}

	if(newUserPwd != newUserPwdRe){
		alert("패스워드가 같지 않습니다.");
		$("#newUserPwd").focus();
		return false;
	}

	_common.callAjax("/user/editPassword.json", {'nowUserPwd': nowUserPwd,'newUserPwd': newUserPwd}, function(json){
		if(json.result){
			alert("비밀번호가 변경되었습니다.");
			$('#edit_pw').find('#closePwPop').click();
		}
	});
});
</script>
<div class="bpopup" id="edit_pw">
    <div id="bpop_wrap">
        <h2 id="bpop_title">비밀번호 변경</h2>
        <table cellspacing="1">
            <tr>
                <th class="top">현재 비밀번호</th>
                <td>
                    <input type="password" id="nowUserPwd" name="nowUserPwd" class="wide" size="30"/>
                </td>
            </tr>
            <tr>
                <th class="top">새 비밀번호</th>
                <td>
                    <input type="password" id="newUserPwd" name="newUserPwd" class="wide" size="30"/>
                </td>
            </tr>
            <tr>
                <th class="top">새 비밀번호 확인</th>
                <td>
                    <input type="password" id="newUserPwdRe" name="newUserPwdRe" class="wide" size="30"/>
                </td>
            </tr>
        </table>
        <table>
            <tr align="center">
                <td class="lastTd" style="border: 0 !important;">
                    <button id="saveBtn" type="button">수 정</button>&nbsp;&nbsp;
                    <button id="closePwPop" type="button">취  소</button>
                </td>
            </tr>
        </table>
    </div>
</div>