$(document).ready(function (){
	//2017.11.21 by khkim, 메뉴선택 구분용
	$("#center-overlay-west").attr('xeus-event','');

	$("#center-overlay-west").css({
		"left" : "0px",
		"width" : "auto",
		"z-index" : "1"
	});
	$("#overlay-west-side-bar").css("top", 0).show();

	$("#btn-user-mng").click();
});

$(document).on("click", "#main-menu-group .menu-button", function(){
	$("#main-menu-group .menu-button").removeAttr("active");
	$(this).attr("active", "active");
});

$(document).on("click", ".overlay-side-bar .menu-button", function(){
	$(".overlay-side-bar .menu-button").removeAttr("active");
	$(this).attr("active", "active");
});

//사용자 관리
$(document).on('click', '#btn-user-mng', function() {
	//2017.11.21 by khkim 코드수정
	if($("#center-overlay-west").attr('xeus-event') != 'btn-user-mng'){
		$("#center-overlay-west").attr('xeus-event','btn-user-mng');

		$("#overlay-west-contents").hide(0, function(){
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			if(authStatCd != '') _param['authStatCd'] = authStatCd;
			if(ipChk == 'Y') _param['ipChk'] = ipChk;
			_common.callAjax("/userMng/getUserView.do", _param, function(view){
				$(".bpopup").remove();
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	}
});

$(document).on('click', '#btn-ip-mng', function() {
	//2017.11.21 by khkim 코드수정
	if($("#center-overlay-west").attr('xeus-event') != 'btn-ip-mng'){
		$("#center-overlay-west").attr('xeus-event','btn-ip-mng');

		$("#overlay-west-contents").hide(0, function(){
			_common.callAjax("/ip/getIpView.do", {"limit" : 10, "offset" : 0}, function(view){
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	};
});

$(document).on('click', '#btn-aces-mng', function() {
	//2017.11.21 by khkim 코드수정
	if($("#center-overlay-west").attr('xeus-event') != 'btn-aces-mng'){
		$("#center-overlay-west").attr('xeus-event','btn-aces-mng');

		$("#overlay-west-contents").hide(0, function(){
			_common.callAjax("/access/getAccessView.do", {"limit" : 10, "offset" : 0}, function(view){
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	}
});

$(document).on('click', '#btn-auth-mng', function() {
	//2017.11.21 by khkim 코드수정
	if($("#center-overlay-west").attr('xeus-event') != 'btn-auth-mng'){
		$("#center-overlay-west").attr('xeus-event','btn-auth-mng');

		$("#overlay-west-contents").hide(0, function(){
			_common.callAjax("/auth/getAuthView.do", null, function(view){
				$(".bpopup").remove();
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	}
});

$(document).on('click', '#btn-orgz-mng', function() {
	//2017.11.21 by khkim 코드수정
	if($("#center-overlay-west").attr('xeus-event') != 'btn-orgz-mng'){
		$("#center-overlay-west").attr('xeus-event','btn-orgz-mng');

		$("#overlay-west-contents").hide(0, function(){
			_common.callAjax("/orgz/getOrgzView.do", {"limit" : 10, "offset" : 0}, function(view){
				$(".bpopup").remove();
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
				$('')
			});
		});
	}
});

$(document).on('click', '#btn-code-mng', function() {
	//2017.11.21 by khkim 코드수정
	if($("#center-overlay-west").attr('xeus-event') != 'btn-code-mng'){
		$("#center-overlay-west").attr('xeus-event','btn-code-mng');

		$("#overlay-west-contents").hide(0, function(){
			_common.callAjax("/code/getCodeView.do", {"limit" : 10, "offset" : 0}, function(view){
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	}
});

$(document).on('click', '#btn-nots-mng', function() {
	if($("#center-overlay-west").attr('xeus-event') != 'btn-nots-mng'){
		$("#center-overlay-west").attr('xeus-event','btn-nots-mng');

		$("#overlay-west-contents").hide(0, function(){
			_common.callAjax("/notice/getNoticeView.do", {"limit" : 10, "offset" : 0}, function(view){
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	}
});

/*
 * 아이콘 관리 화면을 리턴합니다.
 */
$(document).on('click', '#btn-icon-mng', function() {
	if($("#center-overlay-west").attr('xeus-event') != 'btn-icon-mng'){
		$("#center-overlay-west").attr('xeus-event','btn-icon-mng');

		$("#overlay-west-contents").hide(0, function(){
			_common.callAjax("/sysMng/getCctvIconMngView.do", null, function(view){
				$(".bpopup").remove();
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	}
});

/*
 * 이력 조회 화면을 리턴합니다.
 */
$(document).on('click', '#btn-log-view', function() {
	if($("#center-overlay-west").attr('xeus-event') != 'btn-log-view'){
		$("#center-overlay-west").attr('xeus-event','btn-log-view');

		$("#overlay-west-contents").hide(0, function(){
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;

			//_common.callAjax("/msg/getMsgLogView.do", _param, function(view){
			_common.callAjax("/log/getAssetLogView.do", _param, function(view){
				$(".bpopup").remove();
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	}
});

/*
 * SMS 연동 테스트 화면을 리턴합니다.
 */
$(document).on('click', '#btn-sms-view', function() {
	if($("#center-overlay-west").attr('xeus-event') != 'btn-sms-view'){
		$("#center-overlay-west").attr('xeus-event','btn-sms-view');

		$("#overlay-west-contents").hide(0, function(){

			_common.callAjax("/sysMng/getSmsTestView.do", null, function(view){
				$(".bpopup").remove();
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	}
});

/*
 * 설정 화면을 리턴합니다.
 * 시스템 파라미터를 수정합니다.
 */
$(document).on('click', '#btn-env-set', function() {
	if($("#center-overlay-west").attr('xeus-event') != 'btn-env-set'){
		$("#center-overlay-west").attr('xeus-event','btn-env-set');

		$("#overlay-west-contents").hide(0, function(){
			_common.callAjax("/sysMng/getEnvSetView.do", null, function(view){
				$(".bpopup").remove();
				$("#overlay-west-contents").html(view).fadeIn(ANI_DELAY_FAST);
			});
		});
	}
});

//181016 이은규
//edit.jsp를 새로 제작, bPopup으로 열릴 예쩡이므로 대거 수정
/*$(document).on('click', '#btn-id-mng', function() {
	location.href = "/xeus/user/alter.do";
});*/

$(document).on('click', '#btn-map-mng', function() {
	location.href = "/xeus/map/view.do";
});

$(document).on('click', '#btn-tvus-reg', function() {
	location.href = "/xeus/tvius/getUsrTviusRqstMain.do";
});

$(document).on('click', '#btn-tvus-mng', function() {
	location.href = "/xeus/tvius/getMngTviusMngMain.do";
});

$(document).on('click', '#btn-boad-view', function() {
	location.href = "/xeus/board/view.do";
});

$(document).on('click', '#btn-cctv-view, #btn-evnt-view, #btn-nms-mng', function() {
	_common.postForm.submit("/map/view.do", { "btnId" : $(this).attr("id") })
});
