<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.AuthGrpVo"%>
<%@ page import="geomex.xeus.sysmgr.service.AuthVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.TreeSet"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ page session="true" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />

<%
    String context = (String)pageContext.getAttribute("ctxPath");
    String userId = (String) session.getAttribute("userId");
    String userNm = (String) session.getAttribute("userNm");
    String authGrpId = (String) session.getAttribute("authGrpId");

    ArrayList<AuthVo> authList = (ArrayList<AuthVo>) session.getAttribute("authList");
    ArrayList<AuthGrpVo> authGrpList = (ArrayList<AuthGrpVo>) session.getAttribute("authGrp");

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

    String userAuth = "";
    if(authGrpList != null){
        if(!authGrpList.get(0).getAuthMgrNo().contains("EVT001") && !authGrpList.get(0).getAuthMgrNo().contains("EVT004") && !authGrpList.get(0).getAuthMgrNo().contains("EVT007"))
            userAuth = "none";
        else if(authGrpList.get(0).getAuthMgrNo().contains("EVT001") && !authGrpList.get(0).getAuthMgrNo().contains("EVT004") && !authGrpList.get(0).getAuthMgrNo().contains("EVT007"))
            userAuth = "112";
        else if (!authGrpList.get(0).getAuthMgrNo().contains("EVT001") && authGrpList.get(0).getAuthMgrNo().contains("EVT004") && !authGrpList.get(0).getAuthMgrNo().contains("EVT007"))
            userAuth = "119";
        else if (!authGrpList.get(0).getAuthMgrNo().contains("EVT001") && !authGrpList.get(0).getAuthMgrNo().contains("EVT004") && authGrpList.get(0).getAuthMgrNo().contains("EVT007"))
            userAuth = "DSC";
        else if (!authGrpList.get(0).getAuthMgrNo().contains("EVT001") && authGrpList.get(0).getAuthMgrNo().contains("EVT004") && authGrpList.get(0).getAuthMgrNo().contains("EVT007"))
            userAuth = "119dsc";
        else if (authGrpList.get(0).getAuthMgrNo().contains("EVT001") && !authGrpList.get(0).getAuthMgrNo().contains("EVT004") && authGrpList.get(0).getAuthMgrNo().contains("EVT007"))
            userAuth = "112dsc";
        else if (authGrpList.get(0).getAuthMgrNo().contains("EVT001") && authGrpList.get(0).getAuthMgrNo().contains("EVT004") && !authGrpList.get(0).getAuthMgrNo().contains("EVT007"))
            userAuth = "112119";
        else
            userAuth = "all";
    }
%>
<script type="text/javascript">
	var userId = "<%=userId%>";
	var userAuth = "<%=userAuth%>";
	var userGrpNo = "<%=authGrpId%>";
</script>
