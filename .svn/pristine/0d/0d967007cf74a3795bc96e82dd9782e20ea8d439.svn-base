<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.NoticeVo"%>
<%@ page import="geomex.xeus.user.util.RSAKey"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<%
    RSAKey rsa = (RSAKey)session.getAttribute("RSA");
    ArrayList<NoticeVo> notice = (ArrayList<NoticeVo>) request.getAttribute("notice");

    String browser = "";
    String userAgent = request.getHeader("User-Agent");

    if(userAgent.indexOf("Trident") > 0 || userAgent.indexOf("MSIE") > 0){
    	browser = "IE";
    }else if(userAgent.indexOf("Opera") > 0){
    	browser = "Opera";
    }else if(userAgent.indexOf("Firefox") > 0){
    	browser = "Firefox";
    }else if(userAgent.indexOf("Safari") > 0){
    	if(userAgent.indexOf("Chrome") > 0){
    	 	browser = "Chrome";
    	}else{
    		browser = "Safari";
    	}
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<link rel="shortcut icon" href="${ctxPath}/res/img/geomex.ico">
<link rel="stylesheet" type="text/css" href="${ctxPath}/common/ui-1.12.1/themes/ui-darkness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="${ctxPath}/common/ol-v4.0.1/ol.css">
<link rel="stylesheet" type="text/css" href="${ctxPath}/common/jquery.gridster.css">
<link rel="stylesheet" type="text/css" href="${ctxPath}/res/css/xeus.login.css">
<% if(browser.equals("IE")){ %>
<link rel="stylesheet" type="text/css" href="${ctxPath}/res/css/xeus.layout.IE.css">
<% }else{ %>
<link rel="stylesheet" type="text/css" href="${ctxPath}/res/css/xeus.layout.css">
<% } %>
<title>XEUS-SignIn</title>
<script type="text/javascript" src="${ctxPath}/common/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="${ctxPath}/common/ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctxPath}/common/jquery.gridster.js"></script>
<script type="text/javascript" src="${ctxPath}/common/ol-v4.0.1/ol-debug.js"></script>
<script type="text/javascript" src="${ctxPath}/common/proj4js-2.4.3/proj4.js"></script>
<script type="text/javascript" src="${ctxPath}/res/geomex.xeus.proj4.js"></script>
<script type="text/javascript" src="${ctxPath}/common/jquery.spin.min.js"></script>
<script type="text/javascript" src="${ctxPath}/common/jquery.paging.js"></script>
<script type="text/javascript" src="${ctxPath}/common/jquery.timepicker.js"></script>
<script type="text/javascript" src="${ctxPath}/common/jquery.bpopup.js"></script>
<script type="text/javascript" src="${ctxPath}/common/jquery.form.js"></script>
<script type="text/javascript" src="${ctxPath}/common/jquery.download.js"></script>
<script type="text/javascript" src="${ctxPath}/common/tooltipsy.min.js"></script>
<script type="text/javascript" src="${ctxPath}/common/cipher/tea-block.js"></script>
<script type="text/javascript" src="${ctxPath}/common/cipher/base64.js"></script>
<script type="text/javascript" src="${ctxPath}/common/cipher/utf8.js"></script>
<script type="text/javascript" src="${ctxPath}/common/cipher/jsbn.js"></script>
<script type="text/javascript" src="${ctxPath}/common/cipher/rsa.js"></script>
<script type="text/javascript" src="${ctxPath}/common/cipher/helper.js"></script>
<script type="text/javascript" src="${ctxPath}/common/string.js"></script>
<script type="text/javascript" src="${ctxPath}/common/HashMap.js"></script>
<script type="text/javascript" src="${ctxPath}/common/string.js"></script>
<script type="text/javascript" src="${ctxPath}/common/Date.js"></script>
<script type="text/javascript" src="${ctxPath}/common/common.js"></script>
<script type="text/javascript" src="${ctxPath}/res/xeusUser.js"></script>
<script type="text/javascript" src="${ctxPath}/res/geomex.xeus.user.js"></script>
<script type="text/javascript" src="${ctxPath}/res/geomex.xeus.user.login.js"></script>
<script>
$(document).ready(function(){

    $("#wrap").center();

    $(window).resize(function(){
        $("#wrap").center();
    });

    $("#userId").focus();

    $("#userPwd, #login_btn").keyup(function(e){
        if(e.which == 13){
            user.valid.signIn();
        }
    });

    $("#login_btn").on("click", function(){
        user.valid.signIn();
    });

    window.sysAlert = window.alert;
    window.sysConfirm = window.confirm;
    window.alert = xeusCustom.customAlert;
    window.confirm = xeusCustom.customConfirm;

});

$(document).on("focus", "input", function(){
    var txt = $(this).attr("placeholder");
    $(this).attr("hint", txt).attr("placeholder", "");
});

$(document).on("focusout", "input", function(){
    var txt = $(this).attr("hint");
    $(this).attr("placeholder", txt);
});
</script>
</head>
<body>

    <div id="wrapParent">
        <div id="wrap">
	        <!-- <div id="wrapBack"></div> -->
            <div id="logo">
            	<img id="ci" src="../res/img/user/login/ci.png">
            </div>
            <div id="memberLogin">
                <table id="noticeWrap">
<%
for(int i=0; i<notice.size(); i++){
	if(i < 10){
%>
                    <tr>
                        <td class="notcTitle" k="<%= notice.get(i).getMgrSeq() %>"><%= notice.get(i).getNotcTitle() %></td>
                        <td class="date"><%= DateUtil.formatDate(notice.get(i).getLastMdfyDat().substring(0, 8)) %></td>
                    </tr>
<%
	}
}
%>
                </table>
                <table id="loginWrap">
                    <tr>
                        <td id="idBack">
                            <input type="text" id="userId" tabindex="1" placeholder="아이디" value="sejong">
                        </td>
                    </tr>
                    <tr>
                        <td id="pwBack">
                            <input type="password" id="userPwd" tabindex="2" placeholder="비밀번호" value=""><!-- admin123! -->
                        </td>
                    </tr>
                    <tr>
                        <td id="btnBack">
                            <button id="login_btn" title="로그인">로그인</button>
			                <input type="hidden" id="Modulus" name="Modulus" value="<%= RSAKey.toHex(rsa.getModulus()) %>">
			                <input type="hidden" id="Exponent" name="Exponent" value="<%= RSAKey.toHex(rsa.getPublicExponent()) %>">
                        </td>
                    </tr>
                    <tr>
                        <td>
				            <div id="mapWrap">
				                <span class="user_txt" style="margin-right: 10px;" onclick="location.href='${ctxPath}/user/find.do'">계정문의</span>
				                <span class="user_txt" onclick="location.href='${ctxPath}/user/reg.do'">등록신청</span>
				            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <!-- <img id="ci" src="../res/img/user/login/0_ci_eg_white.png"> -->
        </div>
    </div>

    <div class="bpopup" id="edit_pop_wrap">
	    <div id="bpop_wrap">
	        <h2 id="bpop_title">공지사항 상세정보</h2>
            <table>
                <tr class="top">
                    <th class="top">제목</th>
                    <td>
                        <input type="text" class="sendData" id="notcTitle" name="notcTitle" readonly="readonly" />
                    </td>
                </tr>
                <tr>
                    <th class="top">내용</th>
                    <td>
                        <textarea class="sendData" id="notcConts" name="notcConts" readonly="readonly"></textarea>
                    </td>
                </tr>
                <tr>
                    <th class="top">첨부파일</th>
                    <td id="downTr">
                        <input type="text" id="fileDown" readOnly />
                    </td>
                </tr>
            </table>
	        <table>
	            <tr align="center">
	                <td class="lastTd" colspan="2" style="border: 0 !important;">
	                    <button id="closeEditPop" class="bpopClose" tabindex="5">닫기</button>
	                </td>
	            </tr>
	        </table>
	    </div>
	</div>

	<div id="copyright">Copyright ⓒ GEOMEXSOFT Corp. All rights reserved.</div>

</body>
</html>