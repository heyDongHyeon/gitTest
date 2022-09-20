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
	<div>
		<div class="send_group">
			<p>전송 설정 -
			<input type="radio" id="at1" name="send_at" value="0" checked>
			<label for="at1">즉시 전송</label>
			<input type="radio" id="at2" name="send_at" value="1">
			<label for="at2">예약 전송</label>
			</p>
			<div id="send_toggle_date">
				<input type="text" id="send_at_ymd" name="" class="date_util" value="">
				<select id="send_at_hh" class="select_custom">
					<%
						for ( int i = 0; i < 24; i++ ) {
							String hour = "0"+i;
							if ( hour.length() == 3 ) hour = hour.substring(1);
					%>
						<option value="<%=hour%>"><%=hour%> 시</option>
					<%
						}
					%>
				</select>
				<select id="send_at_mm" class="select_custom">
					<%
						for ( int j = 0; j < 60;j++ ) {
							String min = "0"+j;
							if ( min.length() == 3 ) min = min.substring(1);
					%>
						<option value="<%=min%>"><%=min%> 분</option>
					<%
						}
					%>
				</select>
			</div>
			<!-- 예약발송 시 보이기 -->

		</div>
	</div>

    <div class="btnDiv hidden" >
        <button class="active_btn global_btn" id="sendBtn">전 송</button>
        <button class="unactive_btn global_btn" id="initBtn">초 기 화</button>
    </div>
</div>
