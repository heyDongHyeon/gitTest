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
    <b class="overlay-bar-title">음성 방송</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
</div>

<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="send_layout ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark">
	<div class="send_group">
		<p>대상 단말기 <span id="send_list_all_remove">전체 삭제</span></p>
		<div id="send_select_div" class="mCustomScrollbar" data-mcs-theme="minimal-dark" >
			<div id="send_select_list" >
				<p class="send_hint_msg">* 지도 화면 왼쪽 검색 화면에서 검색하여 체크박스를 선택해주세요.</p>
			</div>
		</div>
	</div>
	<div class="send_group">
		<p>방송 메세지</p>
		<div>
			<textarea id="send_cntc_cn" name="send_cntc_cn" class="send_textarea sendData" placeholder="메세지를 입력해주세요."></textarea>
		</div>
	</div>
	<div>
		<div class="send_group">
			<p>시작 알림음 -
			<input type="radio" id="send_beginntcn1" name="send_beginntcn" class="send_input_radio" value="선택안함" checked>
			<label for="send_beginntcn1">선택안함</label>
			<input type="radio" id="send_beginntcn2" name="send_beginntcn" class="send_input_radio send_bell_sound" value="1">
			<label for="send_beginntcn2">소리 1</label>
			<input type="radio" id="send_beginntcn3" name="send_beginntcn" class="send_input_radio send_bell_sound" value="2">
			<label for="send_beginntcn3">소리 2</label>
			<input type="radio" id="send_beginntcn4" name="send_beginntcn" class="send_input_radio send_bell_sound" value="3">
			<label for="send_beginntcn4">소리 3</label>
			</p>

			<audio id="send_bell_1" class="send_audio_obj"><source src='../res/sound/bell_1.wav' type='audio/mpeg'></audio>
			<audio id="send_bell_2" class="send_audio_obj"><source src='../res/sound/bell_2.wav' type='audio/mpeg'></audio>
			<audio id="send_bell_3" class="send_audio_obj"><source src='../res/sound/bell_3.wav' type='audio/mpeg'></audio>
		</div>
		<div class="send_group">
			<p>종료 알림음 -
			<input type="radio" id="send_endntcn1" name="send_endntcn" class="send_input_radio" value="선택안함" checked>
			<label for="send_endntcn1">선택안함</label>
			<input type="radio" id="send_endntcn2" name="send_endntcn" class="send_input_radio send_bell_sound" value="1">
			<label for="send_endntcn2">소리 1</label>
			<input type="radio" id="send_endntcn3" name="send_endntcn" class="send_input_radio send_bell_sound" value="2">
			<label for="send_endntcn3">소리 2</label>
			<input type="radio" id="send_endntcn4" name="send_endntcn" class="send_input_radio send_bell_sound" value="3">
			<label for="send_endntcn4">소리 3</label>
			</p>
		</div>

		<div class="send_group">
			<p>방송 음량 - <input type="text" id="volum_text" readonly class="send_slider_txt"></p>
			<div id="send_slider_voice_volum" class="send_slider_bar"></div>
			<input type="hidden" id="send_volum" name="send_volum" value="5">
		</div>

		<div class="send_group">
			<p>음성 성별 -
			<input type="radio" id="send_sexdstn1" name="send_sexdstn" class="send_input_radio" value="0" checked>
			<label for="send_sexdstn1">여성</label>
			<input type="radio" id="send_sexdstn2" name="send_sexdstn" class="send_input_radio" value="1">
			<label for="send_sexdstn2">남성</label>
			</p>
		</div>
		<div class="send_group">
			<p>음성 속도 - <input type="text" id="ve_text" readonly class="send_slider_txt"></p>
			<div id="send_slider_voice_ve" class="send_slider_bar"></div>
			<input type="hidden" id="send_ve" name="send_ve" value="1">
		</div>
		<!-- <div>
			<span>알림 설정</span>
		</div> -->
		<div class="send_group">
			<p>발송 설정 -
			<input type="radio" id="at1" name="send_at" value="0" checked>
			<label for="at1">즉시발송</label>
			<input type="radio" id="at2" name="send_at" value="1">
			<label for="at2">예약발송</label>
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
        <button class="active_btn global_btn" id="sendBtn">방 송</button>
        <button class="unactive_btn global_btn" id="initBtn">초 기 화</button>
    </div>
</div>
