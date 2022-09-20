/**
 * <pre>
 * 지도관련 이벤트 입니다.
 * </pre>
 *
 * @author 이주영
 */
(function(){

	/* 우측패널 Active 이벤트입니다. */
	$(document).on("click", "#overlay-east-bar > .overlay-bar-button", function(){
		$("#overlay-east-bar > .overlay-bar-button").removeAttr("active");
		$(this).attr("active", "active");
	});
/*
	 기능그룹에 맞는 좌측 메뉴를 설정합니다.
	$(document).on("click", "#main-menu-group > .menu-button", function(){		//맨위에 있는 버튼을 누르면////////////////////////
		if (  parentView != $(this).attr('target')) {
			$("#" + parentView).hide();
		};
		$("#" + parentView).find("#overlay-west-side-bar").find(".menu-button").hide();
		$("#" + parentView).find("#overlay-west-side-bar").find(".menu-button[for='" + id + "']").show();
	});*/
	$(document).on("mouseover", ".weather-parent", function(e){
		if ( parentView != '' ) {
			$("#"+parentView+" .tipsy_form").show();

		}

	});
	$(document).on("mouseout", ".weather-parent", function(e){
		if ( parentView != '' ) {
			$("#"+parentView+" .tipsy_form").hide();

		}

	});

	/* 축척 입력 엔터키 이벤트 입니다. */
	$(document).on("keyup", "#scale-val", function(e){
		if(e.keyCode === 13){
			if(isNaN(Number($("#scale-val").val()))){
				alert("축척은 숫자만 입력할 수 있습니다.");
				return false;
			}

			if(Number($("#scale-val").val()) < 99){
				alert("축척은 100이상부터 입력 할 수 있습니다.");
				return false;
			}

			var dpi = 25.4 / 0.28;
			var mpu = ol.proj.METERS_PER_UNIT["m"];
			var inchesPerMeter = 39.37;
			var resolution = parseFloat($("#scale-val").val()) / (mpu * inchesPerMeter * dpi);

			xeusLayout.mapService.getMap().getView().setResolution(resolution);
		}
	});

	/* Tab 버튼 CSS 변경용 이벤트 입니다. */
	$(document).on("click", "button.tab", function(){
		$("button.tab").removeAttr("active");
		$(this).attr("active", "active");

		var size = $(this).attr("size");
		var url = $(this).attr("url");
		if(url != null){
			if(size != null){
				xeusLayout.WEST = Number(size);
				xeusLayout.showOverlayWestPane(0, function() {
					_common.callAjax(url, null, function(view) {
						$("#overlay-west-contents").html(view);
					});
				});
			}else{
				_common.callAjax(url, null, function(view) {
					$("#overlay-west-contents").html(view);
				});
			}
			if(Public.StopEvent != null) Public.StopEvent();
		}
	});

	/* 축척 목록 오프너 입니다. */
	$(document).on("click", "#scale-opener", function(){
		$("#scale-selector").show().focus().attr("size", $("#scale-selector").find("option").length);
	});

	/* 축척 목록 포커스 아웃 이벤트 입니다. */
	$(document).on("focusout", "#scale-selector", function(){
		var size = $("#scale-selector").attr("size");
		if(size != null && size != 0){
			$("#scale-selector").hide();
		}
	});
	/* 사이드바 Active 이벤트입니다. */
	$(document).on("click", ".overlay-side-bar .menu-button", function(e){
		if($(this).attr('active')) return false;
		$("#" + parentView).find(".overlay-side-bar .menu-button").removeAttr("active");
		$(this).attr("active", "active");
	});
	/* 축척 목록 선택 이벤트 입니다. */
	$(document).on("change", "#scale-selector", function(){
		var scale = $(this).val();

		var dpi = 25.4 / 0.28;
		var mpu = ol.proj.METERS_PER_UNIT["m"];
		var inchesPerMeter = 39.37;
		var resolution = parseFloat(scale / (mpu * inchesPerMeter * dpi));

		xeusLayout.mapService.getMap().getView().setResolution(resolution);

		var size = $("#scale-selector").attr("size");
		if(size != null && size != 0){
			$("#scale-selector").hide();
		}
	});

	/* 관심영역 오버 및 종료 이벤트 입니다. */
	$(document).on("hover", "#popup-closer", function(){
		var src = $(this).attr("src");
		$(this).attr("src", src.replace("normal", "over"));
	}, function(){
		var src = $(this).attr("src");
		$(this).attr("src", src.replace("over", "normal"));
	});
	$(document).on("click", "#popup-closer", function(){
		$("#fav-wrap").hide();
	});

	/* 검색결과 토글 이벤트 입니다. */
	$(document).on("click", "#searchToggle", function(){
		$("#srchResult").toggle();
	});

	/* 검색 뷰 요청 이벤트입니다. map.jsp로 이동 */

	/** CCTV모니터링 *******************************************************/
	/* CCTV 검색 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-cctv-sch", function(){
		xeusLayout.WEST = CCTV_BTN_CCTV_SCH_WEST_SIZE;
		/**
		 * 171221 이은규
		 * CCTV검색 버튼이 눌릴때는 오른쪽 패널이 무조건 닫혀야된다.
		 */
		if (xeusCCTV.getGridPanePlayerCount() > 0) {
			xeusCCTV.closeAllGridPanePlayer();	//CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
		}
		xeusLayout.hideOverlayEastPane(ANI_DELAY);
		$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.

		//2017.11.20 by khkim
		// CCTV검색은 east-pane이 보여도 닫지 않는다.
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-cctv-sch'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-cctv-sch');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/cctv/getSearchView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* CCTV 범례 설정 클릭 이벤트 입니다. */
	$(document).on("click", "#btn-lgd-mng", function(){
		//2017.11.20 by khkim
		// 범례는 east-pane이 보여도 닫지 않는다.

		//범례 클릭 시 로드뷰 제거
		if ( geomex.xeus.DaumRoadView.isAlive() ) {
			geomex.xeus.DaumRoadView.destroyRoadView();
		}
		if($("#" + parentView).find("#center-lgd-overlay-west").attr('xeus-event')=='btn-lgd-mng'){
			xeusLayout.showOverlayLgdWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-lgd-overlay-west").attr('xeus-event','btn-lgd-mng');
			xeusLayout.hideOverlayLgdWestPane(0);

			_common.callAjax("/nms/getNmsLegendView.do", null, function(view) {
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-lgd-west-contents").html(view);
				$("#" + parentView).find("#overlay-lgd-west-contents").show();
				//xeusLayout.WEST = CCTV_BTN_LGD_MNG_WEST_SIZE;
//				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
//				});

				xeusLayout.showOverlayLgdWestPane(ANI_DELAY, function() {
				});

			});

			/*_common.callAjax("/cctv/getLegendView.do", null, function(view) {
				//xeusLayout.initLayer("CCTV");
				$("#" + parentView).find("#overlay-lgd-west-contents").html(view);
				$("#" + parentView).find("#overlay-lgd-west-contents").show();
				xeusLayout.showOverlayLgdWestPane(ANI_DELAY, function() {
				});
			});*/
		}
	});

	/* 선택 감시 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-select-mng", function(){
		//2017.11.20 by kim, 기능 구현시 수정필요
		//west : cctv검색, 범례를 제외하고 닫는다.
		//east : 선택 감시 뷰가 오도록 한다.
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event') !='btn-cctv-sch'  &&
				$("#" + parentView).find("#center-overlay-west").attr('xeus-event') !='btn-lgd-mng'	){
			xeusLayout.hideOverlayWestPane(0);
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','');
		}

		if($("#" + parentView).find("#center-overlay-east").attr('xeus-event')=='btn-select-mng'){
			xeusLayout.showOverlayEastPane(ANI_DELAY, function(){});
		}else{
			$("#" + parentView).find("#center-overlay-east").attr('xeus-event','btn-select-mng');
			xeusLayout.hideOverlayEastPane(0);
			//오른쪽에  createVideoGridPane을 보여 줘야 한다...
			_common.callAjax("/cctv/getSelectView.do", null, function(view) {
				$("#" + parentView).find("#center-overlay-east").html(view);
				//180323 이은규
				//선택감시 버튼 클릭 시 항상 패널 너비가 X3으로 고정이지만
				//위 패널갯수 버튼은 이전 메뉴 접근시의 선택값으로 남아있음.
				//선택감시 버튼 클릭 시 3으로 초기화
				xeusCCTV.VIDEO_GRID_COLS = 3;
				xeusCCTV.createVideoGridPane();
			});
			//사이즈는 CCTV갯수 만큼 자동 계산됨.
			xeusLayout.showOverlayEastPane(ANI_DELAY,function(){
			});
		}
	});

    /* 선택 감시 뷰 요청 이벤트 입니다. */
	/*$(document).on("click", "#btn-select-mng", function(){
		//2017.11.20 by kim, 기능 구현시 수정필요
		//west : cctv검색, 범례를 제외하고 닫는다.
		//east : 선택 감시 뷰가 오도록 한다.
		if($("#center-overlay-west").attr('xeus-event') !='btn-cctv-sch'  &&
	       $("#center-overlay-west").attr('xeus-event') !='btn-lgd-mng'	){
			xeusLayout.hideOverlayWestPane(0);
			$("#center-overlay-west").attr('xeus-event','');
		}

		if($("#center-overlay-east").attr('xeus-event')=='btn-select-mng'){
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
			});
		}else{
			//TODO 코드 수정 필요...
			$("#center-overlay-east").attr('xeus-event','btn-select-mng');
			xeusLayout.hideOverlayEastPane(0);
	        //오른쪽에  createVideoGridPane을 보여 줘야 한다...
				if (xeusCCTV.VIDEO_GRID == null) {
				xeusCCTV.createVideoGridPane();
				//사이즈는 CCTV갯수 만큼 자동 계산됨.
				xeusLayout.showOverlayEastPane(ANI_DELAY,function(){
				});
			}
		}
	});*/

	/* 순찰 감시 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-ptr-view", function(){
		//TODO 2017.11.20 by khkim 기능 구현시 수정 필요...
		// west, east : 순찰감시 보이기.
		//WEST 보이기 설정
		xeusLayout.EAST = CCTV_BTN_PTR_VIEW_EAST_SIZE;
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ptr-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ptr-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/cctv/getPatrolView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = CCTV_BTN_PTR_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		};
		//
		//EAST 보이기 설정
		if($("#" + parentView).find("#center-overlay-east").attr('xeus-event')=='btn-ptr-view'){
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-east").attr('xeus-event','btn-ptr-view');
			//xeusLayout.hideOverlayEastPane(0);
			if(xeusCCTV.VIDEO_GRID != null) xeusCCTV.closeAllGridPanePlayer(0);
			xeusCCTV.createVideoGridPane(true);
			//TODO 순찰감시 목록을 가져오고... 보여주기... 수정필요.
			_common.callAjax("", null, function(view) {
			    //$("#" + parentView).find("#overlay-east-contents").html(view);

				xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 집중 감시 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-fcs-view", function(){
		//TODO 2017.11.20 by khkim, 기능 구현시 수정 필요
		// west, east : 순찰감시 보이기.
		//WEST 보이기
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-fcs-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-fcs-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/cctv/getFocusView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = CCTV_BTN_FCS_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
		//
		//EAST 보이기
		if($("#" + parentView).find("#center-overlay-east").attr('xeus-event')=='btn-fcs-view'){
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-east").attr('xeus-event','btn-fcs-view');
			//xeusLayout.hideOverlayEastPane(0);
			if(xeusCCTV.VIDEO_GRID != null) xeusCCTV.closeAllGridPanePlayer(0);
			xeusCCTV.createVideoGridPane(true);
			//TODO 집중감시 목록을 가져오고... 보여주기... 수정필요.
			_common.callAjax("", null, function(view) {
				//$("#overlay-east-contents").html(view);
				xeusLayout.EAST = CCTV_BTN_FCS_VIEW_EAST_SIZE;
				xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 문제 차량 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-prb-car-view", function(){
		//TODO 2017.11.20 by khkim, 기능 구현시 수정 필요
		// west, east : 순찰감시 보이기.
		//WEST 보이기
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-prb-car-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-prb-car-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/prbcar/getPrbCarView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = CCTV_BTN_PRB_CAR_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
		//
		//EAST 보이기
		if($("#" + parentView).find("#center-overlay-east").attr('xeus-event')=='btn-prb-car-view'){
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-east").attr('xeus-event','btn-prb-car-view');
			if(xeusCCTV.VIDEO_GRID != null) xeusCCTV.closeAllGridPanePlayer(0);
			xeusCCTV.createVideoGridPane(true);
			_common.callAjax("", null, function(view) {
				xeusLayout.EAST = CCTV_BTN_PRB_CAR_EAST_SIZE;
				xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 투망 모니터링 처리 함수는 어디에 구현할까?? 2017.11.20 by khkim */



	/* 등록관리 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-cctv-mng", function(){
		// west : 검색 보이기.
		// east : 등로관리 화면
		//WEST 보이기, cctv검색 화면
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-cctv-sch'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-cctv-sch');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/cctv/getSearchView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = CCTV_BTN_CCTV_SCH_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
		//오른쪽 등록관리 뷰
		if($("#" + parentView).find("#center-overlay-east").attr('xeus-event')=='btn-cctv-mng'){
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-east").attr('xeus-event','btn-cctv-mng');
			//xeusLayout.hideOverlayEastPane(0);
			if(xeusCCTV.VIDEO_GRID != null) xeusCCTV.closeAllGridPanePlayer(0);
			//if(xeusCCTV.getGridPanePlayerCount() > 0) xeusCCTV.closeAllGridPanePlayer(0);
			_common.callAjax("/cctv/getCctvMngView.do", null, function(view) {
				$("#" + parentView).find("#center-overlay-east").html(view);
				xeusLayout.EAST = CCTV_BTN_CCTV_MNG_EAST_SIZE;
				xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 프리셋 설정 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-prst-mng", function(){
		// west : 검색 보이기.
		// east : 등로관리 화면
		//WEST 보이기, cctv검색 화면
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-cctv-sch'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-cctv-sch');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/cctv/getSearchView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = CCTV_BTN_CCTV_SCH_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}

		//오른쪽 프리셋 뷰
		if($("#" + parentView).find("#center-overlay-east").attr('xeus-event')=='btn-prst-mng'){
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-east").attr('xeus-event','btn-prst-mng');
			xeusLayout.hideOverlayEastPane(0);
			if(xeusCCTV.getGridPanePlayerCount() > 0) xeusCCTV.closeAllGridPanePlayer(0);
			_common.callAjax("/cctv/getPresetView.do", null, function(view) {
				$("#" + parentView).find("#center-overlay-east").html(view);
				xeusLayout.EAST = CCTV_BTN_CCTV_MNG_EAST_SIZE;
				xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/** 이벤트 모니터링 *******************************************************/
	/* 모니터링 현황 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-monitor-view", function(){
		xeusLayout.WEST = EVNT_BTN_MONITOR_VIEW_WEST_SIZE;
		xeusLayout.hideOverlayEastPane(ANI_DELAY);
		$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event') === 'btn-monitor-view'){
			return false;
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-monitor-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/monitor/getMonitoringView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					if(socket == null){
						socket = new XeusWS();
						socket.create("ws://" + location.host + "/xeus/event");
					}
					WIDGET.getPastEventListWidget();
				});
			});
		}
	});

	/* 이벤트 처리대기 리스트 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-eventwait-list-view", function(){
		xeusLayout.WEST = EVNT_BTN_MONITOR_VIEW_WEST_SIZE;
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-eventwait-list-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-eventwait-list-view');
			xeusLayout.hideOverlayWestPane(0);
			xeusLayout.hideOverlayEastPane(ANI_DELAY);
			var _param = {};
			_param["limit"] = 10;
			_param["offset"] = 0;
			_param["statEvetOutbDtmStart"] = new Date().getYM()+'01';
			_param["statEvetOutbDtmEnd"] = new Date().getYMD();
			_param["first"] = "true";
			if(userAuth != null && userAuth != ""){
				_param['userAuth'] = userAuth;
			}
			_common.callAjax("/monitor/getMonitoringWaitView.do", _param, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {

				});
			});
		}
	});

	/* 이벤트 리스트 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-event-list-view", function(){
		xeusLayout.WEST = EVNT_BTN_MONITOR_VIEW_WEST_SIZE;
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-event-list-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-event-list-view');
			xeusLayout.hideOverlayWestPane(0);
			xeusLayout.hideOverlayEastPane(ANI_DELAY);
			var _param = {};
			_param["limit"] = 10;
			_param["offset"] = 0;
			_param["statEvetOutbDtmStart"] = new Date().getYM()+'01';
			_param["statEvetOutbDtmEnd"] = new Date().getYMD();
			_param["first"] = "true";
			if(userAuth != null && userAuth != ""){
				_param['userAuth'] = userAuth;
			}
			_common.callAjax("/eventHist/getEventHistView.do", _param, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {

				});
			});
		}
	});

	//////////////ndps
	/* aws view */
	$(document).on("click", "#btn-ndps-aws-view", function(){
		xeusLayout.WEST = NDPS_BTN_VIEW_WEST_SIZE;
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ndps-aws-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ndps-aws-view');
			xeusLayout.hideOverlayWestPane(0);
			var _param = {};
			_param["limit"] = 10;
			_param["offset"] = 0;
			_param["first"] = "true";

			if(userAuth != null && userAuth != ""){
				_param['userAuth'] = userAuth;
			}
			_param['searchNm'] = 'AWS';
			_param['se'] = 'B03105';
			_common.callAjax("/ndps/getNdpsView.do", _param, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {

				});
			});
		}
	});

	/* 강우계 view */
	$(document).on("click", "#btn-ndps-rain-view", function(){
		xeusLayout.WEST = NDPS_BTN_VIEW_WEST_SIZE;
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ndps-rain-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ndps-rain-view');
			xeusLayout.hideOverlayWestPane(0);
			var _param = {};
			_param["limit"] = 10;
			_param["offset"] = 0;
			_param["first"] = "true";

			if(userAuth != null && userAuth != ""){
				_param['userAuth'] = userAuth;
			}
			_param['searchNm'] = '강우계';
			_param['se'] = 'B03101';

			_common.callAjax("/ndps/getNdpsView.do", _param, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {

				});
			});
		}
	});

	/* 적설계 view */
	$(document).on("click", "#btn-ndps-snow-view", function(){
		xeusLayout.WEST = NDPS_BTN_VIEW_WEST_SIZE;
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ndps-snow-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ndps-snow-view');
			xeusLayout.hideOverlayWestPane(0);
			var _param = {};
			_param["limit"] = 10;
			_param["offset"] = 0;
			_param["first"] = "true";

			if(userAuth != null && userAuth != ""){
				_param['userAuth'] = userAuth;
			}
			_param['searchNm'] = '적설계';
			_param['se'] = 'B03103';

			_common.callAjax("/ndps/getNdpsView.do", _param, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {

				});
			});
		}
	});

	/* 수위계 view */
	$(document).on("click", "#btn-ndps-dim-view", function(){
		xeusLayout.WEST = NDPS_BTN_VIEW_WEST_SIZE;
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ndps-dim-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ndps-dim-view');
			xeusLayout.hideOverlayWestPane(0);
			var _param = {};
			_param["limit"] = 10;
			_param["offset"] = 0;
			_param["first"] = "true";

			if(userAuth != null && userAuth != ""){
				_param['userAuth'] = userAuth;
			}
			_param['searchNm'] = '수위계';
			_param['se'] = 'B03102';

			_common.callAjax("/ndps/getNdpsView.do", _param, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {

				});
			});
		}
	});



	/* 로그 통계 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-log-stat-view", function(){
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-log-stat-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-log-stat-view');
			xeusLayout.hideOverlayWestPane(0);
			xeusLayout.hideOverlayEastPane(ANI_DELAY);
			$("#" + parentView).find('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.
			var _param = {};
			_param["year"] = "2018";
			_common.callAjax("/log/get112LogStatView.do", _param, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					//$("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	/* SMS 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-sms-doc-view", function(){
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-event-list-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-event-list-view');
			xeusLayout.hideOverlayWestPane(0);
			var _param = {};
			_param["limit"] = 10;
			_param["offset"] = 0;
			if(userAuth != null && userAuth != ""){
				_param['userAuth'] = userAuth;
			}

			_common.callAjax("/monitor/getSmsView.do", _param, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = EVNT_BTN_MONITOR_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {

				});
			});
		}
	});

	/* 비상벨 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-bell-view", function(){
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-bell-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-bell-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/monitor/getBellView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = EVNT_BTN_BELL_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 안심귀가 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-safe-view", function(){
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-safe-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-safe-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/monitor/getSafeView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = EVNT_BTN_SAFE_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 문제차량 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-car-view", function(){
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-car-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-car-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/monitor/getCarView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = EVNT_BTN_CAR_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 이상음원 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-sound-view", function(){
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-sound-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-sound-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/monitor/getSoundView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = EVNT_BTN_SOUND_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 이력조회 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-evtLog-view", function(){
		xeusLayout.WEST = EVNT_BTN_EVTLOG_VIEW_WEST_SIZE;
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-evtLog-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-evtLog-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/monitor/getEventLogView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 긴급재난상황 메인 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-dist-view", function(){
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-dist-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-dist-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/event/getDisasterView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = EVNT_BTN_DISASTER_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 112 긴급출동지원 사건접수 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-112rec-view", function(){
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-112rec-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-112rec-view');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/event/get112RecView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = EVNT_BTN_112REC_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 112 긴급출동지원 지원 현황 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-112stat-view", function(){
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-112stat-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-112stat-view');
			xeusLayout.hideOverlayWestPane(0);
			var _param = {};
			_param['limit'] = '10';
			_param['offset'] = '0';
			_common.callAjax("/event/get112StatView.do", _param, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = EVNT_BTN_112STAT_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/** 장비관리 *******************************************************/
	/* NMS 상태 모니터링 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-nmg-monitor-view", function(){
		xeusLayout.WEST = NMS_BTN_NMG_MONITOR_VIEW_WEST_SIZE;
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-nmg-monitor-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-nmg-monitor-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'false');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/nms/getNmsMonitoringView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* NMS 범례 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-nms-lgd-mng", function(){
		xeusLayout.WEST = CCTV_BTN_LGD_MNG_WEST_SIZE;
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-nms-lgd-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-nms-lgd-mng');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/nms/getNmsLegendView.do", null, function(view) {
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* NMS 점검 등록 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-check-reg-view", function(){
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-check-reg-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-check-reg-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'false');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/nms/getCallRegView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = NMS_BTN_CHECK_REG_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* NMS 점검 이력 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-check-list-view", function(){
		//2017.11.20 by khkim
		//TODO 점검이력 레이아웃 깨짐.. 확인 바람.
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-check-list-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-check-list-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');

		  	xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/nms/getCallListParentView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				$("#" + parentView).find(".searchWrapper").css("margin-left", "80px");
				$("#" + parentView).find(".searchWrapper").find(".searchTitle").eq(0).css("line-height", "30px");
				$("#" + parentView).find(".searchWrapper").find("#searchBtn").click();

				xeusLayout.WEST = $(document).width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* NMS 사이트 관리 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-site-view", function(){
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-site-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-site-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'false');
			xeusLayout.hideOverlayWestPane(0);
			xeusLayout.hideOverlayEastPane(500);
			_common.callAjax("/nms/getSiteManageView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = NMS_BTN_SITE_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* NMS 기반시설 검색 관리 뷰 요청 이벤트 입니다. */
	$(document).on("click", "#btn-infra-view", function(){
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-infra-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-infra-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'false');
			xeusLayout.hideOverlayWestPane(0);
			xeusLayout.hideOverlayEastPane(500);
			_common.callAjax("/nms/getInfraSearchView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = NMS_BTN_INFRA_VIEW_WEST_SIZE;
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}

		//오른쪽 등록관리 뷰
		if($("#" + parentView).find("#center-overlay-east").attr('xeus-event')=='btn-infra-view'){
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-east").attr('xeus-event','btn-infra-view');
			//xeusLayout.hideOverlayEastPane(0);
			if(xeusCCTV.VIDEO_GRID != null) xeusCCTV.closeAllGridPanePlayer(0);
			//if(xeusCCTV.getGridPanePlayerCount() > 0) xeusCCTV.closeAllGridPanePlayer(0);
//			_common.callAjax("/cctv/getCctvMngView.do", null, function(view) {
//				$("#" + parentView).find("#center-overlay-east").html(view);
//				xeusLayout.EAST = CCTV_BTN_CCTV_MNG_EAST_SIZE;
//				xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
//				});
//			});
		}
	});


	/* 이벤트 통계 뷰 유청 이벤트입니다. */
	$(document).on('click', '#btn-evt-stats-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-evt-stats-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			//180615 긴급반출은 반출 신청을 제한하지 않는다.
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-evt-stats-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/board/getEventStatsView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 레이어 통계 뷰 유청 이벤트입니다. */
	$(document).on('click', '#btn-lyr-stats-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-lyr-stats-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			//180615 긴급반출은 반출 신청을 제한하지 않는다.
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-lyr-stats-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/board/getLayerStatsView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/** 대시보드 *******************************************************/
	/* 전체 통계 뷰 유청 이벤트입니다. */
	$(document).on('click', '#btn-total-stats-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-total-stats-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			//180615 긴급반출은 반출 신청을 제한하지 않는다.
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-total-stats-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/board/getTotalStatsView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/* 이벤트 통계 뷰 유청 이벤트입니다. */
	/*$(document).on('click', '#btn-event-stat-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-event-stat-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-event-stat-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/board/getEventStatsView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});*/

	/* 레이어 통계 뷰 유청 이벤트입니다. */
	$(document).on('click', '#btn-lyr-stats-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-lyr-stats-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			//180615 긴급반출은 반출 신청을 제한하지 않는다.
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-lyr-stats-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
			xeusLayout.hideOverlayWestPane(0);
			_common.callAjax("/board/getLayerStatsView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});
	/** 통계조회 *******************************************************/
	//cctv 통계
	$(document).on('click', '#btn-cctv-stat-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-cctv-stat-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-cctv-stat-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
			xeusLayout.hideOverlayWestPane(0);

			_common.callAjax("/log/getStatCctvView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	//event 발생 통계
	$(document).on('click', '#btn-event-stat-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-event-stat-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			//180615 긴급반출은 반출 신청을 제한하지 않는다.
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-event-stat-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
			xeusLayout.hideOverlayWestPane(0);

			_common.callAjax("/log/getStatEventView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	//ndms 발생 통계
	$(document).on('click', '#btn-ndms-stat-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ndms-stat-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			//180615 긴급반출은 반출 신청을 제한하지 않는다.
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ndms-stat-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
			xeusLayout.hideOverlayWestPane(0);

			_common.callAjax("/log/getStatNdmsView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	//ndps 발생 통계
	$(document).on('click', '#btn-ndps-stat-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ndps-stat-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			//180615 긴급반출은 반출 신청을 제한하지 않는다.
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ndps-stat-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
			xeusLayout.hideOverlayWestPane(0);

			_common.callAjax("/log/getStatNdpsView.do", null, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	/** 시스템관리 *******************************************************/
	//기본 관리
	$(document).on('click', '#btn-basic-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-basic-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			_param["discardChk"] = "Y";
			_param['gbn'] = 'user';
			_common.callAjax("/userMng/getUserView.do", _param, function(view) {
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				//xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.WEST = $("#layout-center").width();

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-basic-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//사용자 관리
	$(document).on('click', '#btn-user-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-user-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			_param["discardChk"] = "Y";
			_common.callAjax("/userMng/getUserView.do", _param, function(view) {
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				//xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.WEST = $("#layout-center").width();

				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-user-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//접근IP관리
	$(document).on('click', '#btn-ip-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ip-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			_common.callAjax("/ip/getIpView.do", _param, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ip-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//접근이력관리
	$(document).on('click', '#btn-aces-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-aces-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			_common.callAjax("/access/getAccessView.do", _param, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-aces-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//권한관리
	$(document).on('click', '#btn-auth-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-auth-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			_common.callAjax("/auth/getAuthView.do", null, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-auth-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//소속관리
	$(document).on('click', '#btn-orgz-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-orgz-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			_common.callAjax("/orgz/getOrgzView.do", _param, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-orgz-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//코드관리
	$(document).on('click', '#btn-code-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-code-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			_common.callAjax("/code/getCodeView.do", _param, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-code-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//공지사항관리
	$(document).on('click', '#btn-nots-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-nots-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			_common.callAjax("/notice/getNoticeView.do", _param, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-nots-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//아이콘관리
	$(document).on('click', '#btn-icon-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-icon-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			/*Public.TVIUS.Init.Clear();
			var _param = {};
			_param['symGrp'] = 'ctv';
			_param['subPath'] = 'cctv';
			_common.callAjax("/sysMng/getCctvIconMngView.do", _param, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-icon-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});*/

			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['sortCol'] = 'gbn_cd';
			_param['sortTyp'] = 'ASC';
			_common.callAjax("/sysMng/getIconMngView.do", _param, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-icon-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//장비관리
	$(document).on('click', '#btn-equip-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-equip-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			_param['sortCol'] = 'mgr_no';
			_param['sortTyp'] = 'asc';
			_param['gbn'] = 'vms';
			_common.callAjax("/vms/getVmsView.do", _param, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-equip-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//이벤트관리
	$(document).on('click', '#btn-evt-mng', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-icon-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			_common.callAjax("/eventMngList/getEventMngListView.do", null, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-evt-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//이력조회
	$(document).on('click', '#btn-log-view', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-log-mng'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			var _param = {};
			_param['limit'] = 10;
			_param['offset'] = 0;
			//_common.callAjax("/log/getAssetLogView.do", _param, function(view){
			_common.callAjax("/log/getAccessView.do", _param, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-log-mng');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//설정
	$(document).on('click', '#btn-env-set', function() {
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-env-set'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			Public.TVIUS.Init.Clear();
			_common.callAjax("/sysMng/getEnvSetView.do", null, function(view){
				$("#" + parentView).find(".bpopup").remove();
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.WEST = $("#" + parentView).find('.map-target').width();
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
					$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-env-set');
					$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'true');
				});
			});
		}
	});

	//NDMS/////////////////////////////////
	/**
	 * 긴급구조//기상정보//수위정보//통보문
	 */
	$(document).on("click", ".ndms-menu", function(){			///맨왼쪽에 있는 거 클릭하면...
		xeusLayout.WEST = SEND_BTN_SMS_VIEW_WEST_SIZE;
		var eventKey = $(this).attr('id').split('-')[2];
		//2017.11.20 by khkim
		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ndms-'+eventKey+'-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ndms-'+eventKey+'-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'false');
			xeusLayout.hideOverlayWestPane(0);

			_common.callAjax("/ndms/getNdmsView.do", {event:eventKey}, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);

				$("#" + parentView).find("#searchTable").find(".date").datepicker({
		            format: "yyyy/mm/dd",
		            language: "kr"
				});
				$("#" + parentView).find("#searchTable").find(".date").datepicker('setDate', 'today');	//디폴트를 오늘 날짜로 설정함.
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});
		}
	});

	//상황전파/////////////////////////////////
	/**
	 * sms//음성발송//이력 조회
	 */
	$(document).on("click", ".send-menu", function(){
		var eventKey = $(this).attr('id').split('-')[2];
		if ( eventKey === 'disboard' ) {
//			var popUrl = "http://125.60.28.107/login.do";	//팝업창에 출력될 페이지 URL
			var popUrl = "http://view.ndms.go.kr";	//팝업창에 출력될 페이지 URL (20220824에 수정)

			var popOption = "width=1000, height=700";    //팝업창 옵션(optoin)

			window.open(popUrl,"",popOption);


			return false;
		}
		xeusLayout.WEST = SEND_BTN_SMS_VIEW_WEST_SIZE;
		//2017.11.20 by khkim

		if($("#" + parentView).find("#center-overlay-west").attr('xeus-event')=='btn-ndms-'+eventKey+'-view'){
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','btn-ndms-'+eventKey+'-view');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'false');
			xeusLayout.hideOverlayWestPane(0);

			_common.callAjax("/send/getSendView.do", {event:eventKey}, function(view) {
				$("#" + parentView).find("#overlay-west-contents").html(view);
				xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				});
			});

			_common.callAjax("/send/getSMSTreeGroupInfo.json", {}, function(json) {
				xeusLayout.initTree(json);
			});

			if(eventKey == 'voice'){
				xeusLayout.mapService.setAllLayerUnVisible();
				xeusLayout.mapService.setLayerVisible('음성통보시스템',true)
			}

			/*_common.callAjax("/send/getSendMngView.do", {}, function(view) {
				xeusLayout.showOverlayEastPane(delay, function() {
					$("#" + parentView).find("#center-overlay-east").html(view);

//					$("#" + parentView).find(".btnDiv").removeClass("hidden");
//					$("#" + parentView).find('#vectorInfo').bPopup().close();
//					$("#" + parentView).find('.bPopup').remove();
				});
			});*/
		}

		//EAST 보이기
		/*if($("#" + parentView).find("#center-overlay-east").attr('xeus-event')=='btn-send-view'){
			//xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
			});
		}else{
			$("#" + parentView).find("#center-overlay-east").attr('xeus-event','btn-send-view');
			//xeusLayout.hideOverlayEastPane(0);
			if(xeusCCTV.VIDEO_GRID != null) xeusCCTV.closeAllGridPanePlayer(0);
			xeusCCTV.createVideoGridPane(true);
			//TODO SMS 발송 및 음성발송 입력 화면 출력.
			_common.callAjax("", null, function(view) {
				//$("#overlay-east-contents").html(view);
				xeusLayout.EAST = CCTV_BTN_FCS_VIEW_EAST_SIZE;
				xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
				});
			});
		}*/
	});


})();

//TODO 외부 호출 함수(투망모니터링 등) 구현..... 2017.11.20 by khkim