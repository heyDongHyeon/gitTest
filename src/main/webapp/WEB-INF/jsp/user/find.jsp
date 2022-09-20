<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.user.service.UserVo"%>
<%@ page import="geomex.xeus.user.util.RSAKey"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<%
    RSAKey rsa = (RSAKey)session.getAttribute("RSA");
    UserVo vo = (UserVo)request.getAttribute("userVo");
    String userId = null;
    if(vo != null) userId = vo.getUserId();

    ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");

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
<link rel="stylesheet" type="text/css" href="${ctxPath}/res/css/xeus.reg.css">
<% if(browser.equals("IE")){ %>
<link rel="stylesheet" type="text/css" href="${ctxPath}/res/css/xeus.layout.IE.css">
<% }else{ %>
<link rel="stylesheet" type="text/css" href="${ctxPath}/res/css/xeus.layout.css">
<% } %>
<title>XEUS-User Infomation Find</title>
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
<script type="text/javascript" src="${ctxPath}/res/geomex.xeus.user.js"></script>
<script type="text/javascript" src="${ctxPath}/res/geomex.xeus.user.find.js"></script>
<script type="text/javascript" src="${ctxPath}/res/xeusUser.js"></script>
<script>
<% if(userId != null){ %>
var isLogin = true;
<% }else{ %>
var isLogin = false;
<% } %>

window.sysAlert = window.alert;
window.sysConfirm = window.confirm;
window.alert = xeusCustom.customAlert;
window.confirm = xeusCustom.customConfirm;

</script>
<style>
.tab{
    margin: 0;
    padding: 25px 0;
    color: #a7a7aa;
    font-weight: bold;
    background: #56585e;
    float: left;
    width: 224px;
    text-align: center;
    height: 15px;
    cursor: pointer;
}
.tab:first-child {
    border-right: 2px solid #a7a7aa;
}
.active{
    color: white;
}
</style>
</head>
<body>

	<%-- <div id="header">
        <img src="${ctxPath}/img/user/logo.png" id="ci">
        <div id="back"></div>
    </div> --%>

	<div id="wrap">
		<div id="content">
            <!-- <div id="tab">
                <span>아이디 찾기</span>
                <span>비밀번호 찾기</span>
            </div>
            <style>
                #tab {
                    color: white;
                    font-size: 14px;
                    height: 54px;
                    background: #515358;
                    padding-left: 10px;
                }
            </style> -->
			<table cellspacing="0">
			    <!-- <tr>
                    <th>
                        <span>계정문의</span>
                        <button id="titleClose" type="button"></button>
                    </th>
                </tr> -->
                <tr>
                    <td>
                        <div class="tab findId active">아이디 찾기</div>
                        <div class="tab findPw">비밀번호 찾기</div>
                    </td>
                </tr>
				<tr class="pwOnly hidden">
                    <td>
                        <input type="text" id="userId" name="userId" class="wide sendData" size="30" placeholder="아이디 (6자 이상)" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" id="userNm" name="userNm" class="wide sendData" size="30" placeholder="이름" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" id="birthDay" name="birthDay" class="wide sendData" size="30" placeholder="생년월일 6자리 (주민등록번호 앞자리)" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <select class="wide sendData" id="orgMgrNo">
<% for(int i=0; i<orgz.size(); i++){ %>
                            <option value="<%= orgz.get(i).getOrgMgrNo() %>"><%= orgz.get(i).getOrgNm() %></option>
<% } %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" id="email" name="email" class="wide sendData" size="30" placeholder="이메일 주소" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="text" id="mobileNum" name="mobileNum" class="wide sendData" size="30" placeholder="휴대전화번호 ( - 없이 입력)" />
                    </td>
                </tr>
                <tr>
                    <td class="lastTd">
                        <button id="backBtn" type="button" style="width: 223px !important;">취소</button>
                        <!-- <button id="okBtn" type="button" onclick="user.valid.find();">확인</button> -->
                        <button id="okBtn" mode="id" type="button">확인</button>
                    </td>
                </tr>
			</table>
		</div>
	</div>

</body>
</html>