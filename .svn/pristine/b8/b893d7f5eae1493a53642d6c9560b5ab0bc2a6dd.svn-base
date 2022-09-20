<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvModelVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ImageVo"%>
<%@ page import="geomex.xeus.equipmgr.service.AwsVo"%>
<%@ include file="../common.jsp" %>

<script type="text/javascript" src="<%= context %>/res/geomex.xeus.aws.reg.js"></script>

<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">SMS 발송</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
</div>

<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="send_layout ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark">

	<div class="send_group">
		<p>수신자 <span id="send_list_all_remove">전체 삭제</span></p>
		<div id="send_select_div" class="mCustomScrollbar" data-mcs-theme="minimal-dark" >
			<div id="send_select_list" >
				<p class="send_hint_msg">* 지도 화면 왼쪽 검색 화면에서 검색하여 체크박스를 선택해주세요.</p>
			</div>
		</div>
	</div>

	<div class="send_group">
		<p>전송 메세지</p>
		<div>
			<textarea id="send_cntc_cn" name="send_cntc_cn" class="send_textarea sendData" placeholder="메세지를 입력해주세요."></textarea>
		</div>
	</div>

</div>
