<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvModelVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ImageVo"%>
<%@ page import="geomex.xeus.equipmgr.service.VoiceVo"%>
<%@ include file="../common.jsp" %>

<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndms.chart.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndms.data.js"></script>

<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">NDMS 상세 정보</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
</div>
<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark">
	<div id="ndms_chart"></div>
</div>
