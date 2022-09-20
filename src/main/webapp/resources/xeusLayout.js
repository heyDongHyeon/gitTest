/**
 * 화면 레이아웃 관리, 메뉴 이벤트, 지도 설정
 */

var xeusLayout = {
	NORTH : 50,
	WEST : 300,
	EAST : 600,
	WEST_TAB : 40, // 지도메뉴.. WEST 탭
	TITLE_BAR : 30,
	SOUTH : 33,

	ctxPath : null,
//1599 1482
	initLayout : function(_ctxPath) {

		xeusLayout.ctxPath = "/" + _ctxPath;

		// JQuery Dialog Title에 html 사용 Hack!!!!
		$.widget('ui.dialog', jQuery.extend({}, jQuery.ui.dialog.prototype, {
			_title : function(titleBar) {
				titleBar.html(this.options.title || '&#160;');
			}
		}));

		// 툴팁 설정... 제거 고려.....
		// $(document).tooltip();
		// $(document).click(function(e) {
		// 마우스 클릭시마다 tooltip 재 설정하여 div를 제거해야 함.
		// $(document).tooltip('destroy');
		// $(document).tooltip();
		// });
		// tooltip disable

		// resize 이벤트 등록
		//$(document).ready(function() {
		//	xeusLayout.reLayout();
		//});
		$(window).resize(function() {
			xeusLayout.reLayout();
		});

		window.getWidthOfText = function(txt, fontname, fontsize) {
			// Create dummy span
			this.e = document.createElement('span');
			// Set font-size
			this.e.style.fontSize = fontsize;
			// Set font-face / font-family
			this.e.style.fontFamily = fontname;
			// Set text
			this.e.innerHTML = txt;
			document.body.appendChild(this.e);
			// Get width NOW, since the dummy span is about to be removed
			// from the document
			var w = this.e.offsetWidth;
			// Cleanup
			document.body.removeChild(this.e);
			// All right, we're done
			return w;
		}

		xeusLayout.reLayout();
		xeusLayout.setMenuEvent();

		window.sysAlert = window.alert;
		window.sysConfirm = window.confirm;
		window.alert = xeusLayout.customAlert;
		window.confirm = xeusLayout.customConfirm;
	},

	reLayout : function() {
		/* 문서의 넓이와 높이를 구합니다. */
		var scrWidth = $(window).width();
		var scrHeight = $(window).height();

		// xeus-layout.css 설정을 무시하고 사이즈를 조절함
		$("#layout-north").height(xeusLayout.NORTH);
		$("#" + parentView).find(".map-target").height(scrHeight - xeusLayout.NORTH);

		// 2017.11.16 by khkim 점검이력은 가로 전체사이즈 적용
		if ($("#" + parentView).find("#center-overlay-west").attr('xeus-full-size') == 'true') {
			xeusLayout.WEST = $(document).width();
		}
		$("#" + parentView).find("#center-overlay-west").width(xeusLayout.WEST);
		$("#" + parentView).find("#center-overlay-west").height(scrHeight - xeusLayout.NORTH);
		$("#" + parentView).find("#center-lgd-overlay-west").height(scrHeight - xeusLayout.NORTH);

		// ///////////////////////////////////////////////////
		var tabLeft = xeusLayout.WEST;
		if ($("#" + parentView).find("#center-overlay-west").attr('xeus-show') == 'false') {
			tabLeft = 0;
			if($("#" + parentView).find("#overlay-west-side-bar").is(":visible")){
				tabLeft += $("#" + parentView).find("#overlay-west-side-bar").width();
			}
		}
		//2017.11.20 by khkim, adnimate ->css로 변경
		$("#" + parentView).find("#center-overlay-west-tab").css({
			//top : xeusLayout.NORTH,
			left : tabLeft
        });

		$("#" + parentView).find("#scale-wrap").css({
			left : ($("#" + parentView).find("#center-overlay-west-tab").offset().left + $("#" + parentView).find("#center-overlay-west-tab").width()) + 2 + 'px'
		});

		if ( $("#" + parentView).find("#search-parent").length > 0 ) {

			$("#" + parentView).find("#search-parent").css({
				left : ($("#" + parentView).find("#scale-wrap").offset().left + $("#" + parentView).find("#scale-wrap").outerWidth() - 1) + 1 + 'px'
			});
		}

		// ////////////////////////////////////////////////////
		$("#" + parentView).find("#center-overlay-east").width(xeusLayout.EAST);
		$("#" + parentView).find("#center-overlay-east").height(scrHeight - xeusLayout.NORTH);
		// EAST 보이기 여부에 따라 left 위치 조정
		var eastLeft = scrWidth - xeusLayout.EAST;
		if ($("#" + parentView).find("#center-overlay-east").attr('xeus-show') == 'false') {
			eastLeft = scrWidth;
		}


		$("#" + parentView).find("#center-overlay-east").offset({
			top : xeusLayout.NORTH + 4,
			left : eastLeft
		}).css("z-index", "15");
		// 내용물 height gridster땜시 필요
		if ($("#" + parentView).find('#overlay-east-contents').length > 0) {
			$("#" + parentView).find('#overlay-east-contents').height(scrHeight - xeusLayout.NORTH - xeusLayout.TITLE_BAR - 2);
		}
		// console.log(">>> after..... ");
		// console.log($('#overlay-east-contents').height());
		//
		// 순수지도영역(지도 가상영역) 사이즈를 조정한다.
		$("#" + parentView).find("#virtual-map-boundary").width(eastLeft - (tabLeft + xeusLayout.WEST_TAB));
		//$("#" + parentView).find("#virtual-map-boundary").height(scrHeight - xeusLayout.NORTH - 30);
		$("#" + parentView).find("#virtual-map-boundary").height(scrHeight - xeusLayout.NORTH - 30 - xeusLayout.SOUTH);
		$("#" + parentView).find("#virtual-map-boundary").offset({
			top : xeusLayout.NORTH + 30,
			left : tabLeft + xeusLayout.WEST_TAB
		});

		// boundary_map_resize event를 발생한다.
		$("#" + parentView).find("#virtual-map-boundary").trigger("boundary_map_resize");

		/*
		 * $("#scale-wrap").animate({ left :
		 * ($("#center-overlay-west-tab").offset().left +
		 * $("#center-overlay-west-tab").width()) + 'px' }, 0, function(){
		 * $("#search-parent").css({ left : ($("#scale-wrap").offset().left +
		 * $("#scale-wrap").outerWidth() - 1) + 'px' }); });
		 */

		//20181109 by 형준 맵 사이징.
		var westSubWid =  ($("#" + parentView).find("#center-overlay-west-tab").offset().left + $("#" + parentView).find("#center-overlay-west-tab").width()) + 2;
		$("#" + parentView).find('.map-target').css('margin-left',
				westSubWid+ 'px');
		//20181109 by 형준 맵 사이징.
		var mapWidth = $("#" + parentView).find('.map-target').width();
		if ( $("#" + parentView).find("#center-overlay-east").attr('xeus-show') === 'true') {
			$("#" + parentView).find('.map-target').css('margin-right',$("#" + parentView).find('#center-overlay-east').css('width'));
			//$("#" + parentView).find('.weather-parent').css('right',$("#" + parentView).find('#center-overlay-east').css('width'));
		} else {
			$("#" + parentView).find('.map-target').css('margin-right','0');
			//$("#" + parentView).find('.weather-parent').css('right','0');
		}

		//
		if (xeusLayout.mapService != undefined) xeusLayout.mapService.getMap().updateSize();

	},

	// 왼쪽 Overlay West Pane을 닫는다.
	// 옆에 붙은 지도메뉴 Tab을 왼쪽으로 이동한다.
	hideOverlayWestPane : function(delay, callback) {
		if ($("#" + parentView).find("#center-overlay-west").attr('xeus-show') != 'false') {
          // 2017.11.16 by khkim 장비점검이력을 닫을때 지도메뉴, 주소메뉴 보이기
            xeusLayout.showJusoSearchMenu();
            xeusLayout.showWestTabMenu();

			$("#" + parentView).find("#center-overlay-west").attr('xeus-show', 'false');
			$("#" + parentView).find("#center-overlay-west").animate({
				opacity: 0.0,
				left : '-' + (xeusLayout.WEST) + 'px',
				width : xeusLayout.WEST + 'px'
			}, delay, callback);
			// 지도 메뉴 Tab위치 이동, 왼쪽에 붙게
			$("#" + parentView).find("#center-overlay-west-tab").animate({
				left : $("#" + parentView).find("#overlay-west-side-bar").is(":visible") ? $("#" + parentView).find("#overlay-west-side-bar").width() + 'px' : '0px',
				width : xeusLayout.WEST_TAB + 'px'
			}, delay, function(){

			});
			// West Pane 열기 버튼모양 으로 변경
			$("#west-slide-btn>img").attr('src', xeusLayout.ctxPath + '/res/img/right_double_angle.png');

			$("#" + parentView).find("#scale-wrap").animate({
				left : $("#" + parentView).find("#overlay-west-side-bar").is(":visible")
						? ($("#" + parentView).find("#overlay-west-side-bar").width() + $("#" + parentView).find("#center-overlay-west-tab").width()) + 2 + 'px'
						: ($("#" + parentView).find("#center-overlay-west-tab").width()) + 'px'
			}, delay);
			$("#" + parentView).find("#search-parent").animate({
				left : $("#" + parentView).find("#overlay-west-side-bar").is(":visible")
						? ($("#" + parentView).find("#overlay-west-side-bar").width() + $("#" + parentView).find("#center-overlay-west-tab").width() + $("#" + parentView).find("#scale-wrap").outerWidth()) + 2 + 'px'
						: ($("#" + parentView).find("#center-overlay-west-tab").width() + $("#" + parentView).find("#scale-wrap").outerWidth()) + 'px'
			}, delay);
		} else {
			if (callback) {
				callback();
			}
		}
		xeusLayout.reLayout(); // 가상영역 영역 조절
	},

	// 왼쪽 Overlay West Pane을 보여준다.
	// 옆에 붙은 지도메뉴 Tab을 오른쪽으로 이동한다..
	showOverlayWestPane : function(delay, callback) {
		//TODO 2017.11.20 만약 주소바가 화면보다 크면 숨기기....
		$("#west-slide-btn").css("display", "block"); // 열기,닫기 버튼 보이기
		if ($("#" + parentView).find("#center-overlay-west").attr('xeus-show') != 'true') {
			$("#" + parentView).find("#center-overlay-west").attr('xeus-show', 'true');
			$("#" + parentView).find("#center-overlay-west").animate({
				opacity: 1.0,
				left : 0 + 'px',
				width : xeusLayout.WEST + 'px'
			}, delay, callback);
			// 지도 메뉴 Tab위치 이동, West Pane오른쪽에 붙게
			$("#" + parentView).find("#center-overlay-west-tab").animate({
				left : xeusLayout.WEST + 'px',
				width : xeusLayout.WEST_TAB + 'px'
			}, delay);
			// West Pane 닫기 버튼 모양으로 변경
			$("#west-slide-btn>img").attr('src', xeusLayout.ctxPath + '/res/img/left_double_angle.png');

			$("#" + parentView).find("#scale-wrap").animate({
				left : (xeusLayout.WEST + $("#" + parentView).find("#center-overlay-west-tab").width()) + 2 + 'px'
			}, delay);
			$("#" + parentView).find("#search-parent").animate({
				left : (xeusLayout.WEST + $("#" + parentView).find("#center-overlay-west-tab").width() + $("#" + parentView).find("#scale-wrap").outerWidth()) + 2 + 'px'
			}, delay);
		} else {
			if (callback) {
				callback();
			}
		}
		//console.log(">>>>>>>>>> showOverlayWestPane ");
		xeusLayout.reLayout();// 가상영역 영역 조절
	},
	// 왼쪽 Overlay West Pane을 보여준다.
	// 옆에 붙은 지도메뉴 Tab을 오른쪽으로 이동한다..
	showOverlayLgdWestPane : function(delay, callback) {
		//TODO 2017.11.20 만약 주소바가 화면보다 크면 숨기기....
		$("#west-lgd-slide-btn").css("display", "block"); // 열기,닫기 버튼 보이기
		if ($("#" + parentView).find("#center-lgd-overlay-west").attr('xeus-show') != 'true') {
			$("#" + parentView).find("#center-lgd-overlay-west").attr('xeus-show', 'true');
			$("#" + parentView).find("#center-lgd-overlay-west").animate({
				opacity: 1.0,
				left : '71px',
				width : (xeusLayout.WEST-71) + 'px'
			}, delay, callback);

		}
	},


	hideOverlayLgdWestPane : function(delay, callback) {
		if ($("#" + parentView).find("#center-lgd-overlay-west").attr('xeus-show') != 'false') {
			// 2017.11.16 by khkim 장비점검이력을 닫을때 지도메뉴, 주소메뉴 보이기

			$("#" + parentView).find("#center-lgd-overlay-west").attr('xeus-show', 'false');
			$("#" + parentView).find("#center-lgd-overlay-west").animate({
				opacity: 0.0,
				left : '-' + (xeusLayout.WEST) + 'px',
				width : xeusLayout.WEST + 'px'
			}, delay);

		}
	},

	// 오른쪽 Overlay East Pane을 닫는다.
	// 이미 닫혀 있으면 callback함수 호출
	hideOverlayEastPane : function(delay, callback) {
		if ($("#" + parentView).find("#center-overlay-east").attr('xeus-show') != 'false') {
			$("#" + parentView).find("#center-overlay-east").attr('xeus-show', 'false');
			$("#" + parentView).find("#center-overlay-east").animate({
				opacity: 0.0,
				left : $(window).width() + 'px',
				width : xeusLayout.EAST + 'px'
			}, delay, callback);
			// 만약 EAST Pane을 유지해야 한다면 열기 버튼 모양으로 변경
			$("#" + parentView).find("#east-slide-btn>img").attr('src', xeusLayout.ctxPath + '/res/img/left_double_angle.png');
		} else {
			if (callback) {
				callback();
			}
		}
		xeusLayout.reLayout();// 가상영역 영역 조절
	},

	// 오른쪽 Overlay East Panem을 보여준다.
	// 이미 보여지고 있으면 callback 함수만 호출
	showOverlayEastPane : function(delay, callback) {
		$("#" + parentView).find("#east-slide-btn").css("display", "block"); // 열기,닫기 버튼 보이기
		if ($("#" + parentView).find("#center-overlay-east").attr('xeus-show') != 'true') {
			$("#" + parentView).find("#center-overlay-east").attr('xeus-show', 'true');
			$("#" + parentView).find("#center-overlay-east").animate({
				opacity: 1.0,
				left : $(window).width() - xeusLayout.EAST + 'px',
				width : xeusLayout.EAST + 'px'
			}, delay, callback);

			// 닫기 버튼 모양으로 변경
			$("#" + parentView).find("#east-slide-btn>img").attr('src', xeusLayout.ctxPath + '/res/img/right_double_angle.png');
		} else {
			if (callback) {
				callback();
			}
		}
		// console.log(">>>aaaaaaaa >>>> showOverlayEastPane ");
		xeusLayout.reLayout();// 가상영역 영역 조절
	},
    // 지도메뉴메뉴를 숨긴다.
	hideWestTabMenu : function(){
		$("#" + parentView).find("#center-overlay-west-tab").hide();
		$("#" + parentView).find("#center-overlay-west-tab").attr('xeus-show', 'false');
	},
	showWestTabMenu : function(){
		$("#" + parentView).find("#center-overlay-west-tab").attr('xeus-show', 'true');
		$("#" + parentView).find("#center-overlay-west-tab").show();
	},
    // 스케일, 주소찾기메뉴를 숨긴다.
	hideJusoSearchMenu : function(){
		$("#" + parentView).find("#scale-wrap").hide();
		$("#" + parentView).find("#search-parent").hide();
		$("#" + parentView).find("#scale-wrap").attr('xeus-show', 'false');
		$("#" + parentView).find("#search-parent").attr('xeus-show', 'false');
	},
	showJusoSearchMenu : function(){
		$("#" + parentView).find("#scale-wrap").show();
		$("#" + parentView).find("#search-parent").show();
		$("#" + parentView).find("#scale-wrap").attr('xeus-show', 'true');
		$("#" + parentView).find("#search-parent").attr('xeus-show', 'true');
	},
	/* 2017-08-22 이주영 - 우측 패널 열림확인 함수 */
	isOverlayShow : function(){
		return ($("#" + parentView).find("#center-overlay-east").attr('xeus-show') == 'true');
	},

	// 안내 메시지를 보여준다.. 1초정도..
	showShortcutMessage : function(_msg, _width) {
		// if ($('#shortcut_message')) {
		// $('#shortcut_message').remove();
		// }

		var div = $('<div id="shortcut_message"></div>').appendTo('.map-target');//!! parentView 따다가 제대로 적용해야 함.
		var _position = {
			my : "center top-100",
			at : "center",
			of : window
		}

		var _dialog = $('#shortcut_message').dialog({
			resizable : false,
			position : _position,
			// dialogClass: 'hide-title-bar',
			istitle : false,
			autoOpen : false,
			width : _width,
			minHeight : 0,
			//height : 50,
			hide : {
				effect : "fade",
				duration : 100
			},
			create : function(event, ui) {
				// 타이틀바 제거
				$(this).parents('.ui-dialog:first').find('.ui-dialog-titlebar').remove();
				$('#shortcut_message').css({
					"background" : "#3B3B3B"
				});
			}
		});

		var _txt = '<span class="xeus-dialog-title-txt" style="padding:2px 0px 0px 10px;">';
		_txt += '<p>' + _msg + '</p>';
		_txt += '</span>';
		_dialog.html('');
		_dialog.html(_txt);

		_dialog.dialog('open');
		setTimeout(function() {
			try {
				_dialog.dialog("destroy");
				$('#shortcut_message').remove();
			} catch (err) {
				console.log(err);
			}
		}, 1000);
	},

	// 확인, 취소 Dialog를 보여준다.
	showYesNoDialog : function(_titleTxt, _msg, _funcOk) {
		var div = $('<div id="message_dialog_modal"></div>').appendTo('.map-target');//!! parentView 따다가 제대로 적용해야 함.
		var _position = {
			my : "center top-100",
			at : "center",
			of : window
		}

		var _title = "<div class='xeus-dialog-title-div'>";
		_title += "<img class='xeus-dialog-title-icon' ";
		_title += " src='" + xeusLayout.ctxPath + "/res/sym/cctv/icon_cctv.png'/>";
		_title += "<span class='xeus-dialog-title-txt'>" + _titleTxt + "</span>";
		_title += "</div>";

		var _dialog = $('#message_dialog_modal').dialog({
			modal : true,
			title : _title,
			resizable : false,
			position : _position,
			autoOpen : false,
			hide : {
				effect : "fade",
				duration : 100
			},
			buttons : {
				"확인" : function() {
					_funcOk();
					$(this).dialog("destroy");
				},
				"취소" : function() {
					$(this).dialog("destroy");
				}
			}
		});

		var _txt = '<span class="xeus-dialog-title-txt" style="padding: 10px;">';
		_txt += '<p>' + _msg + '</p>';
		_txt += '</span>';

		_dialog.html('');
		_dialog.html(_txt);
		// $('#message_dialog_modal').css('background','#ff00ff');
		_dialog.dialog('open');
	},

	/**
	 * 2017-11-13 이주영
	 *
	 * 커스텀 alert 입니다.
	 */
	customAlert : function(_msg){
		//var div = $('<div id="message_dialog_modal"></div>').appendTo('.map-target');
		var $target = $('#'+parentView).find('.map-target');
		if($('#'+parentView).find('.map-target').length == 0) $target = $('#'+parentView);
		var div = $('<div id="message_dialog_modal"></div>').appendTo($target);
		//!! parentView 따다가 제대로 적용해야 함.
		var _position = {
			my : "center top-100",
			at : "center",
			of : window
		}

		var _title = "<div class='xeus-dialog-title-div'>";
		/*
		 * _title += "<img class='xeus-dialog-title-icon' "; _title += " src='" +
		 * xeusLayout.ctxPath + "/res/sym/cctv/icon_cctv.png'/>";
		 */
		_title += "<span class='xeus-dialog-title-txt' style='height: 36px; padding-left: 10px;'>알림</span>";
		_title += "</div>";

		var _dialog = $('#message_dialog_modal').dialog({
			modal : true,
			title : _title,
			resizable : false,
			position : _position,
			autoOpen : false,
			hide : {
				effect : "fade",
				duration : 100
			},
			buttons : {
				"확인" : function() {
					$(this).dialog("destroy");
					$('.notice').remove();
				}
			},
			open : function(event, ui) {
				//x 버튼 제거
				//$('.ui-dialog-titlebar-close').hide();
			}
		});

		var _txt = '<span class="xeus-dialog-title-txt" style="padding: 10px;">';

		_txt += '<p>' + _common.utils.validNull(_msg.replace("\n", "<br>")) + '</p>';
		_txt += '</span>';

		_dialog.html('');
		_dialog.html(_txt);
		// $('#message_dialog_modal').css('background','#ff00ff');
		_dialog.dialog('open');
		/*$('.ui-dialog').addClass('notice');
		$('.ui-dialog').css('z-index', 99999);
		if($(".ui-dialog .ui-dialog-buttonpane button").length == 1){
			$(".ui-dialog .ui-dialog-buttonpane button").css("width", "100%");
		}*/
		/*
		 * 180528 이은규
		 * 모든 ui-dialog 클래스에 생기던 오류 수정
		 * 생성된 본인 객체에만 작업 진행
		 */
		_dialog.parent().addClass('notice');
		$(".notice").css('z-index', 99999);
		if($(".notice .ui-dialog-buttonpane button").length == 1){
			$(".notice .ui-dialog-buttonpane button").css("width", "100%");
		}
		$(".notice").find('.ui-dialog-titlebar-close').hide();
	},

	/**
	 * 2017-11-13 이주영
	 *
	 * 커스텀 alert 입니다.
	 */
	customConfirm : function(_msg, _callBack, _callBack2){
		// var def = $.Deferred();
		//var div = $('<div id="message_dialog_modal"></div>').appendTo('.map-target');
		//!! parentView 따다가 제대로 적용해야 함.
		$('#message_dialog_modal').remove();
		var $target = $('#'+parentView).find('.map-target');
		if($('#'+parentView).find('.map-target').length == 0) $target = $('#'+parentView);

		var div = $('<div id="message_dialog_modal"></div>').appendTo($target);
		var _position = {
				my : "center top-100",
				at : "center",
				of : window
		}

		var _title = "<div class='xeus-dialog-title-div'>";
		/*
		 * _title += "<img class='xeus-dialog-title-icon' "; _title += " src='" +
		 * xeusLayout.ctxPath + "/res/sym/cctv/icon_cctv.png'/>";
		 */
		_title += "<span class='xeus-dialog-title-txt' style='height: 36px; padding-left: 10px;'>알림</span>";
		_title += "</div>";

		var _dialog = $('#message_dialog_modal').dialog({
			modal : true,
			title : _title,
			resizable : false,
			position : _position,
			autoOpen : false,
			hide : {
				effect : "fade",
				duration : 100
			},
			buttons : {
				"확인" : function() {
					$(this).dialog("destroy");
					$('.notice').remove();
					if(_callBack != null && typeof _callBack == "function"){
						_callBack();
					}
				},
				"취소" : function() {
					$(this).dialog("destroy");
					$('.notice').remove();
					if(_callBack2 != null && typeof _callBack2 == "function"){
						_callBack2();
					}
				}
			},
			open : function(event, ui) {
				//x 버튼 제거
				//$('.ui-dialog-titlebar-close').hide();
			}
		});

		var _txt = '<span class="xeus-dialog-title-txt" style="padding: 10px;">';
		_txt += '<p>' + _common.utils.validNull(_msg).replace("\n", "<br>") + '</p>';
		_txt += '</span>';

		_dialog.html('');
		_dialog.html(_txt);
		// $('#message_dialog_modal').css('background','#ff00ff');
		_dialog.dialog('open');
		//$('.ui-dialog').addClass('notice');
		//$('.ui-dialog').css('z-index', 99999);
		//$('.notice').find('.ui-dialog-titlebar-close').hide();

		/*
		 * 180528 이은규
		 * 모든 ui-dialog 클래스에 생기던 오류 수정
		 * 생성된 본인 객체에만 작업 진행
		 */
		_dialog.parent().addClass('notice');
		$(".notice").css('z-index', 99999);
		$(".notice").find('.ui-dialog-titlebar-close').hide();

		// return def.promise();
	},
	//현재 지도화면을 png형태로 다운로드한다.
	downloadMapImage : function(data, fileName) {
		var imgData = atob(data.split(',')[1]),
		len = imgData.length,
		buf = new ArrayBuffer(len),
		view = new Uint8Array(buf),
		blob,
		i;

		for (i = 0; i < len; i++) {
			view[i] = imgData.charCodeAt(i) & 0xff; // masking
		}

		blob = new Blob([view], {
			type: 'application/octet-stream'
		});

		if (window.navigator.msSaveOrOpenBlob) {
			window.navigator.msSaveOrOpenBlob(blob, fileName);
		} else {
			//var url = URL.createObjectURL(blob);
			var a = document.createElement('a');
			a.style = "display: none";
			//a.href = url;
			a.href = data;
			a.download = fileName;
			document.body.appendChild(a);
			a.click();

			setTimeout(function() {
				document.body.removeChild(a);
				//URL.revokeObjectURL(url);
			}, 1000);
		}
	}
};

// ////////////////////////////////////////////////////
// 메뉴 이벤트 처리....
// ////////////////////////////////////////////////////
xeusLayout.setMenuEvent = function() {
	var menuBtnId = ''; //메인 메뉴 버튼 ID, 중복 클릭 체크용 2017.11.21 by khkim
	// 토글 버튼을 처리하기 위한 설정..
	xeusLayout.menuToggleButtons = new geomex.xeus.ToggleButtons();
	xeusLayout.mapToggleButtons = new geomex.xeus.ToggleButtons();

	// ////////////////////////////////////
	// 메뉴 이벤트..를 등록한다.
	// ////////////////////////////////////
	xeusLayout.menuToggleButtons.add('#btn-cctv-view', function() {
		if(menuBtnId != 'btn-cctv-view'){

			$(".tab[target=eventView]").show();
			if($("#eventView").html() != ""){
				return false;
			}else{
				_common.callAjax("/map/eventView.do", {}, function(view){

					parentView = "eventView";
					$("#layout-center").find(".viewWrap").hide();
					$("#layout-center").find("#" + parentView).show().html(view);
					$("#" + parentView).find("#overlay-west-side-bar").show();

					//좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다.
				//	$("#" + parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
				//		var position = $("#" + parentView).find("#center-overlay-west-tab").position();
				//		$('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
				///	});

					xeusSearch.searchMenuEvent();
					_common.callAjax("/search/getSearchView.do", null, function(view) {
						$("#" + parentView).find("#search-parent").html(view);

						$("#" + parentView).find("#scale-wrap").fadeIn(100);
						$("#" + parentView).find("#search-parent").fadeIn(100);
						$("#" + parentView).find("#jibunUI").show(0);
						xeusLayout.reLayout();
					});
					xeusLayout.initLayout('xeus');
					xeusLayout.initMap('xeus-map-content', '.tab[target=eventView]');
					//xeusLayout.initLayer("CCTV");
					$(".tab[target=" + parentView + "]").data("map").getMap().addControl(new ol.control.ZoomSlider());


				}, false);
			}

			return false;
	        //180510 영상반출 setTimeout해제용 함수 추가
			Public.TVIUS.Init.Clear();

			menuBtnId = 'btn-cctv-view';
			$('#main-menu-group').find('button').each(function(){
				$(this).removeAttr("active");
			});
			$('#'+menuBtnId).attr("active", "active");

			// 2017.11.21 by khkim
			$("#center-overlay-west").attr('xeus-event','');
			$("#center-overlay-east").attr('xeus-event','');
			$(".overlay-side-bar .menu-button").removeAttr("active");
			$("#center-overlay-west").attr('xeus-full-size', 'false');

			/**
			 * 171120 이은규
			 * 다른페이지에서 넘어올 시 열려있는 패널 내용 및 버튼들을 초기화한다.
			 */
			$("#overlay-west-contents").html('');	// 좌측 패널 내용을 없앤다.
			$("#center-overlay-east").html('');		// 우측 패널 내용을 없앤다.
			$('#west-slide-btn').hide();			// 좌측 패널용 버튼을 숨긴다.
			$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.

			xeusLayout.hideOverlayWestPane(ANI_DELAY);
			xeusLayout.hideOverlayEastPane(ANI_DELAY);
			//xeusLayout.initLayer("CCTV");
			////xeusLayout.initLayer("EVT");
		}
	});

	/* 이벤트 모니터링 */
	xeusLayout.menuToggleButtons.add('#btn-evnt-view', function() {
		// 2017.11.21 by khkim.
		if(menuBtnId != 'btn-evnt-view'){
			//180510 영상반출 setTimeout해제용 함수 추가
			Public.TVIUS.Init.Clear();

			menuBtnId = 'btn-evnt-view';
			$('#main-menu-group').find('button').each(function(){
				$(this).removeAttr("active");
			});
			$('#'+menuBtnId).attr("active", "active");

			$("#center-overlay-west").attr('xeus-event','');
			$("#center-overlay-east").attr('xeus-event','');
			$(".overlay-side-bar .menu-button").removeAttr("active");
			$("#center-overlay-west").attr('xeus-full-size', 'false');

			/**
			 * 171220 이은규
			 * CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
			 */
			if (xeusCCTV.getGridPanePlayerCount() > 0) {
				xeusCCTV.closeAllGridPanePlayer();	//CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
			}
			$("#overlay-west-contents").html('');	// 좌측 패널 내용을 없앤다.
			$("#center-overlay-east").html('');		// 우측 패널 내용을 없앤다.
			$('#west-slide-btn').hide();			// 좌측 패널용 버튼을 숨긴다.
			$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.

			xeusLayout.hideOverlayWestPane(ANI_DELAY);
			xeusLayout.hideOverlayEastPane(ANI_DELAY);
			//xeusLayout.initLayer("CCTV");  //cctv용 지도 보여주기.
			////xeusLayout.initLayer("EVT");

		    $("#btn-monitor-view").click();
		}
	});

	/* 장비관리 */
	xeusLayout.menuToggleButtons.add('#btn-nms-mng', function() {
		// 2017.11.21 by khkim.
		if(menuBtnId != 'btn-nms-mng'){

			$(".tab[target=nmsView]").show();
			if($("#nmsView").html() != ""){
				return false;
			}else{
				_common.callAjax("/map/nmsView.do", {}, function(view){

					parentView = "nmsView";
					$("#layout-center").find(".viewWrap").hide();
					$("#layout-center").find("#" + parentView).show().html(view);
					$("#" + parentView).find("#overlay-west-side-bar").show();

					//좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다.
					/*$("#" + parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
						var position = $("#" + parentView).find("#center-overlay-west-tab").position();
						$('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
					});*/
					xeusSearch.searchMenuEvent();
					_common.callAjax("/search/getSearchView.do", null, function(view) {
						$("#" + parentView).find("#search-parent").html(view);

						$("#" + parentView).find("#scale-wrap").fadeIn(100);
						$("#" + parentView).find("#search-parent").fadeIn(100);
						$("#" + parentView).find("#jibunUI").show(0);
						xeusLayout.reLayout();
					});
					xeusLayout.initLayout('xeus');
					xeusLayout.initMap('xeus-map-content-nms', '.tab[target=nmsView]');
					//xeusLayout.initLayer("NMS");
				}, false);
			}

			return false;
			//180510 영상반출 setTimeout해제용 함수 추가
			Public.TVIUS.Init.Clear();

			menuBtnId = 'btn-nms-mng';
			$('#main-menu-group').find('button').each(function(){
				$(this).removeAttr("active");
			});
			$('#'+menuBtnId).attr("active", "active");

			$("#center-overlay-west").attr('xeus-event','');
			$("#center-overlay-east").attr('xeus-event','');
			$("#center-overlay-west").attr('xeus-full-size', 'false');
			$(".overlay-side-bar .menu-button").removeAttr("active");

			/**
			 * 171220 이은규
			 */
			if (xeusCCTV.getGridPanePlayerCount() > 0) {
				xeusCCTV.closeAllGridPanePlayer();	//CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
			}
			$("#overlay-west-contents").html('');	// 좌측 패널 내용을 없앤다.
			$("#center-overlay-east").html('');		// 우측 패널 내용을 없앤다.
			$('#west-slide-btn').hide();			// 좌측 패널용 버튼을 숨긴다.
			$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.

			xeusLayout.hideOverlayWestPane(ANI_DELAY);
			xeusLayout.hideOverlayEastPane(ANI_DELAY);
			//xeusLayout.initLayer("NMS");
		}
	});

	/* 장비관리 */
	xeusLayout.menuToggleButtons.add('#btn-dashboard-view', function() {
//		var _URL = "http://10.159.1.191/xeus-dashboard/board/dash.do";

		var _URL = "http://" + location.host + "/xeus-dashboard/board/dash.do";
		window.open(_URL);
//		location.href = _URL;
	});

	/* 제우스 보드 */
	xeusLayout.menuToggleButtons.add('#btn-boad-view', function() {
		//_common.postForm.open("/board/view.do");
		//location.href = "/xeus/board/view.do";

		if(menuBtnId != 'btn-boad-view'){

			$(".tab[target=boardView]").show();
			if($("#boardView").html() != ""){
				return false;
			}else{
				_common.callAjax("/map/boardView.do", {}, function(view){

					parentView = "boardView";
					$("#layout-center").find(".viewWrap").hide();
					$("#layout-center").find("#" + parentView).show().html(view);
					$("#" + parentView).find("#overlay-west-side-bar").show();

					//좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다.
					$("#" + parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
						var position = $("#" + parentView).find("#center-overlay-west-tab").position();
						$('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
					});

					xeusSearch.searchMenuEvent();
					_common.callAjax("/search/getSearchView.do", null, function(view) {
						$("#" + parentView).find("#search-parent").html(view);

						$("#" + parentView).find("#scale-wrap").fadeIn(100);
						$("#" + parentView).find("#search-parent").fadeIn(100);
						$("#" + parentView).find("#jibunUI").show(0);
						xeusLayout.reLayout();
					});
				}, false);

				return false;
			}

			//180510 영상반출 setTimeout해제용 함수 추가
			Public.TVIUS.Init.Clear();

			menuBtnId = 'btn-boad-view';
			$('#main-menu-group').find('button').each(function(){
				$(this).removeAttr("active");
			});
			$('#'+menuBtnId).attr("active", "active");

			$("#" + parentView).find("#center-overlay-west").attr('xeus-event','');
			$("#" + parentView).find("#center-overlay-east").attr('xeus-event','');
			$("#" + parentView).find("#center-overlay-west").attr('xeus-full-size', 'false');
			$(".overlay-side-bar .menu-button").removeAttr("active");

			/**
			 * 171220 이은규
			 */
			if (xeusCCTV.getGridPanePlayerCount() > 0) {
				xeusCCTV.closeAllGridPanePlayer();	//CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
			}
			$("#" + parentView).find("#overlay-west-contents").html('');	// 좌측 패널 내용을 없앤다.
			$("#" + parentView).find("#center-overlay-east").html('');		// 우측 패널 내용을 없앤다.
			$('#west-slide-btn').hide();			// 좌측 패널용 버튼을 숨긴다.
			$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.

			xeusLayout.hideOverlayWestPane(ANI_DELAY);
			xeusLayout.hideOverlayEastPane(ANI_DELAY);
			//xeusLayout.initLayer("CCTV");
		}
	});

	/* 시스템 관리 */
	/*xeusLayout.menuToggleButtons.add('#btn-sys-mng', function() {
		menuBtnId = 'btn-sys-mng';
		location.href = "/xeus/sysMng/getSystemView.do"
	});*/
	xeusLayout.menuToggleButtons.add('#btn-sys-mng', function() {
		if(menuBtnId != 'btn-sys-mng'){

			$(".tab[target=systemView]").show();
			if($("#systemView").html() != ""){
				return false;
			}else{
				_common.callAjax("/map/systemView.do", {}, function(view){

					parentView = "systemView";
					$("#layout-center").find(".viewWrap").hide();
					$("#layout-center").find("#" + parentView).show().html(view);
					$("#" + parentView).find("#overlay-west-side-bar").show();

					//좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다.
					//$("#" + parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
					//	var position = $("#" + parentView).find("#center-overlay-west-tab").position();
					//	$('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
					//});

					xeusLayout.initLayout('xeus');

					/*xeusSearch.searchMenuEvent();
					_common.callAjax("/search/getSearchView.do", null, function(view) {
						$("#" + parentView).find("#search-parent").html(view);

						$("#" + parentView).find("#scale-wrap").fadeIn(100);
						$("#" + parentView).find("#search-parent").fadeIn(100);
						$("#" + parentView).find("#jibunUI").show(0);
						xeusLayout.reLayout();
					});*/
				}, false);

			}

			return false;

			menuBtnId = 'btn-sys-mng';
			$('#main-menu-group').find('button').each(function(){
				$(this).removeAttr("active");
			});
			$('#'+menuBtnId).attr("active", "active");

			$("#center-overlay-west").attr('xeus-event','');
			$("#center-overlay-east").attr('xeus-event','');
			$(".overlay-side-bar .menu-button").removeAttr("active");
			$("#center-overlay-west").attr('xeus-full-size', 'false');

			if (xeusCCTV.getGridPanePlayerCount() > 0) {
				xeusCCTV.closeAllGridPanePlayer();	//CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
			}
			$("#overlay-west-contents").html('');	// 좌측 패널 내용을 없앤다.
			$("#center-overlay-east").html('');		// 우측 패널 내용을 없앤다.
			$('#west-slide-btn').hide();			// 좌측 패널용 버튼을 숨긴다.
			$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.

			xeusLayout.hideOverlayWestPane(ANI_DELAY);
			xeusLayout.hideOverlayEastPane(ANI_DELAY);
			//xeusLayout.initLayer("CCTV");  //cctv용 지도 보여주기.

		    //$("#btn-user-mng").click();
		}
	});

	/* 재난 시설물 */
	xeusLayout.menuToggleButtons.add('#btn-ndps-view', function() {
		// 2017.11.21 by khkim.
		if(menuBtnId != 'btn-ndps-view'){

			$(".tab[target=ndpsView]").show();
			if($("#ndpsView").html() != ""){
				return false;
			}else{
				_common.callAjax("/map/ndpsView.do", {}, function(view){

					parentView = "ndpsView";
					$("#layout-center").find(".viewWrap").hide();
					$("#layout-center").find("#" + parentView).show().html(view);
					$("#" + parentView).find("#overlay-west-side-bar").show();

					//좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다.
					//$("#" + parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
				//		var position = $("#" + parentView).find("#center-overlay-west-tab").position();
					//	$('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
				//	});
					xeusSearch.searchMenuEvent();
					_common.callAjax("/search/getSearchView.do", null, function(view) {
						$("#" + parentView).find("#search-parent").html(view);

						$("#" + parentView).find("#scale-wrap").fadeIn(100);
						$("#" + parentView).find("#search-parent").fadeIn(100);
						$("#" + parentView).find("#jibunUI").show(0);
						xeusLayout.reLayout();
					});
					xeusLayout.initLayout('xeus');
					xeusLayout.initMap('xeus-map-content-ndps', '.tab[target=ndpsView]');
				}, false);
			}

			return false;

			menuBtnId = 'btn-ndps-view';
			$('#main-menu-group').find('button').each(function(){
				$(this).removeAttr("active");
			});
			$('#'+menuBtnId).attr("active", "active");

			$("#center-overlay-west").attr('xeus-event','');
			$("#center-overlay-east").attr('xeus-event','');
			$("#center-overlay-west").attr('xeus-full-size', 'false');
			$(".overlay-side-bar .menu-button").removeAttr("active");

			/**
			 * 171220 이은규
			 */
			if (xeusCCTV.getGridPanePlayerCount() > 0) {
				xeusCCTV.closeAllGridPanePlayer();	//CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
			}
			$("#overlay-west-contents").html('');	// 좌측 패널 내용을 없앤다.
			$("#center-overlay-east").html('');		// 우측 패널 내용을 없앤다.
			$('#west-slide-btn').hide();			// 좌측 패널용 버튼을 숨긴다.
			$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.

			xeusLayout.hideOverlayWestPane(ANI_DELAY);
		}
	});

	/* 통계 조회 */
	xeusLayout.menuToggleButtons.add('#btn-stat-mng', function() {
		if(menuBtnId != 'btn-stat-mng'){

			$(".tab[target=statView]").show();
			if($("#statView").html() != ""){
				return false;
			}else{
				_common.callAjax("/map/statView.do", {}, function(view){

					parentView = "statView";
					$("#layout-center").find(".viewWrap").hide();
					$("#layout-center").find("#" + parentView).show().html(view);
					$("#" + parentView).find("#overlay-west-side-bar").show();

					//좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다.
					//	$("#" + parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
					//		var position = $("#" + parentView).find("#center-overlay-west-tab").position();
					//		$('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
					//	});

					xeusLayout.initLayout('xeus');

					/*xeusSearch.searchMenuEvent();
					_common.callAjax("/search/getSearchView.do", null, function(view) {
						$("#" + parentView).find("#search-parent").html(view);

						$("#" + parentView).find("#scale-wrap").fadeIn(100);
						$("#" + parentView).find("#search-parent").fadeIn(100);
						$("#" + parentView).find("#jibunUI").show(0);
						xeusLayout.reLayout();
					});*/
				}, false);

			}

			//$("#btn-user-mng").click();
		}
	});

	/* NDMS */
	xeusLayout.menuToggleButtons.add('#btn-ndms-view', function() {
		// 2017.11.21 by khkim.
		if(menuBtnId != 'btn-ndms-view'){

			$(".tab[target=ndmsView]").show();
			if($("#ndmsView").html() != ""){
				return false;
			}else{
				_common.callAjax("/map/ndmsView.do", {}, function(view){

					parentView = "ndmsView";
					$("#layout-center").find(".viewWrap").hide();
					$("#layout-center").find("#" + parentView).show().html(view);
					$("#" + parentView).find("#overlay-west-side-bar").show();
					//좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다.
					//$("#" + parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
				//		var position = $("#" + parentView).find("#center-overlay-west-tab").position();
					//	$('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
				//	});
					xeusSearch.searchMenuEvent();
					_common.callAjax("/search/getSearchView.do", null, function(view) {
						$("#" + parentView).find("#search-parent").html(view);

						$("#" + parentView).find("#scale-wrap").fadeIn(100);
						$("#" + parentView).find("#search-parent").fadeIn(100);
						$("#" + parentView).find("#jibunUI").show(0);
						xeusLayout.reLayout();
					});
					xeusLayout.initLayout('xeus');
					xeusLayout.initMap('xeus-map-content-ndms', '.tab[target=ndmsView]');
				}, false);
			}
			return false;
			menuBtnId = 'btn-ndms-view';
			$('#main-menu-group').find('button').each(function(){
				$(this).removeAttr("active");
			});
			$('#'+menuBtnId).attr("active", "active");

			$("#center-overlay-west").attr('xeus-event','');
			$("#center-overlay-east").attr('xeus-event','');
			$("#center-overlay-west").attr('xeus-full-size', 'false');
			$(".overlay-side-bar .menu-button").removeAttr("active");

			/**
			 * 171220 이은규
			 */
			if (xeusCCTV.getGridPanePlayerCount() > 0) {
				xeusCCTV.closeAllGridPanePlayer();	//CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
			}
			$("#overlay-west-contents").html('');	// 좌측 패널 내용을 없앤다.
			$("#center-overlay-east").html('');		// 우측 패널 내용을 없앤다.
			$('#west-slide-btn').hide();			// 좌측 패널용 버튼을 숨긴다.
			$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.

			xeusLayout.hideOverlayWestPane(ANI_DELAY);
		}
	});
	/* 재난시설물 */
	xeusLayout.menuToggleButtons.add('#btn-aws-view', function() {
		// 2017.11.21 by khkim.
		if(menuBtnId != 'btn-aws-view'){

			$(".tab[target=awsView]").show();
			if($("#awsView").html() != ""){
				return false;
			}else{
				_common.callAjax("/map/awsView.do", {}, function(view){

					parentView = "awsView";
					$("#layout-center").find(".viewWrap").hide();
					$("#layout-center").find("#" + parentView).show().html(view);
					$("#" + parentView).find("#overlay-west-side-bar").show();
/*
					//좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다.
					$("#" + parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
						var position = $("#" + parentView).find("#center-overlay-west-tab").position();
						$('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
					});*/

					xeusSearch.searchMenuEvent();
					_common.callAjax("/search/getSearchView.do", null, function(view) {
						$("#" + parentView).find("#search-parent").html(view);

						$("#" + parentView).find("#scale-wrap").fadeIn(100);
						$("#" + parentView).find("#search-parent").fadeIn(100);
						$("#" + parentView).find("#jibunUI").show(0);
						xeusLayout.reLayout();
					});
					xeusLayout.initLayout('xeus');
					xeusLayout.initMap('xeus-map-content-aws', '.tab[target=awsView]');
				}, false);
			}

			return false;

			menuBtnId = 'btn-aws-view';
			$('#main-menu-group').find('button').each(function(){
				$(this).removeAttr("active");
			});
			$('#'+menuBtnId).attr("active", "active");

			$("#center-overlay-west").attr('xeus-event','');
			$("#center-overlay-east").attr('xeus-event','');
			$("#center-overlay-west").attr('xeus-full-size', 'false');
			$(".overlay-side-bar .menu-button").removeAttr("active");

			/**
			 * 171220 이은규
			 */
			if (xeusCCTV.getGridPanePlayerCount() > 0) {
				xeusCCTV.closeAllGridPanePlayer();	//CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
			}
			$("#overlay-west-contents").html('');	// 좌측 패널 내용을 없앤다.
			$("#center-overlay-east").html('');		// 우측 패널 내용을 없앤다.
			$('#west-slide-btn').hide();			// 좌측 패널용 버튼을 숨긴다.
			$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.

			xeusLayout.hideOverlayWestPane(ANI_DELAY);
		}
	});

	/* 상황전파 */
	xeusLayout.menuToggleButtons.add('#btn-send-view', function() {
		// 2017.11.21 by khkim.
		if(menuBtnId != 'btn-send-view'){

			$(".tab[target=sendView]").show();
			if($("#sendView").html() != ""){
				return false;
			}else{
				_common.callAjax("/map/sendView.do", {}, function(view){

					parentView = "sendView";
					$("#layout-center").find(".viewWrap").hide();
					$("#layout-center").find("#" + parentView).show().html(view);
					$("#" + parentView).find("#overlay-west-side-bar").show();

					/*//좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다.
					$("#" + parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
						var position = $("#" + parentView).find("#center-overlay-west-tab").position();
						$('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
					});*/

//					xeusSearch.searchMenuEvent();
					_common.callAjax("/search/getSearchView.do", null, function(view) {
						$("#" + parentView).find("#search-parent").html(view);

						$("#" + parentView).find("#scale-wrap").fadeIn(100);
						$("#" + parentView).find("#search-parent").fadeIn(100);
						$("#" + parentView).find("#jibunUI").show(0);
						xeusLayout.reLayout();
					});
					xeusLayout.initLayout('xeus');
					xeusLayout.initMap('xeus-map-content-send', '.tab[target=sendView]');
				}, false);
			}
			return false;
			menuBtnId = 'btn-send-view';
			$('#main-menu-group').find('button').each(function(){
				$(this).removeAttr("active");
			});
			$('#'+menuBtnId).attr("active", "active");

			$("#center-overlay-west").attr('xeus-event','');
			$("#center-overlay-east").attr('xeus-event','');
			$("#center-overlay-west").attr('xeus-full-size', 'false');
			$(".overlay-side-bar .menu-button").removeAttr("active");
			/**
			 * 171220 이은규
			 */
			if (xeusCCTV.getGridPanePlayerCount() > 0) {
				xeusCCTV.closeAllGridPanePlayer();	//CCTV모니터링에서 이동 시 열려있는 우측패널 CCTV들을 닫는다.
			}
			$("#overlay-west-contents").html('');	// 좌측 패널 내용을 없앤다.
			$("#center-overlay-east").html('');		// 우측 패널 내용을 없앤다.
			$('#west-slide-btn').hide();			// 좌측 패널용 버튼을 숨긴다.
			$('#east-slide-btn').hide();			// 우측 패널용 버튼을 숨긴다.
			xeusLayout.hideOverlayWestPane(ANI_DELAY);
		}
	});

	// //////////////////////////////////
	// 맵 제어용 Toggle버튼을 등록한다.
	// //////////////////////////////////
	xeusLayout.mapToggleButtons.add('#btn-map-move', function() {
		console.log("#btn-map-move clicked...");
	});
	xeusLayout.mapToggleButtons.add('#btn-map-dist', function() {
		console.log("#btn-map-dist clicked...");
	});
	xeusLayout.mapToggleButtons.add('#btn-map-area', function() {
		console.log("#btn-map-area clicked...");

		// getFeature();
	});

	// 맵 제어용 Default Toggle를 설정한다.
	xeusLayout.mapToggleButtons.toggle('#btn-map-move');
	// CCTV관리용 Default Toggle를 설정한다.
	xeusLayout.menuToggleButtons.toggle('#btn-cctv-srch');
};

// ////////////////////////////////////////////////////
// 지도 설정...
// ////////////////////////////////////////////////////
// xeusLayout.projection = ol.proj.get("EPSG:5186");

xeusLayout.initLayer = function(fnGroup) {
    //2017.11.21 by khkim, CCTV,NMS,이벤트 상황에 맞게 레이어 조절
	if(Public.Mode == fnGroup)return;	//동일 그룹이면 리턴
	Public.Mode = fnGroup;

	var fn = LayerFnGroup[fnGroup];
	for(var key in Layers){
		//Layers.LayerTheme를 제외 해야함 ... by khkim
		if(Layers[key].type != null){
			if(Layers[key].fnGroup === LayerFnGroup.CMMN){
				//console.log("common......" + Layers[key]["name"]);
				//공통 그룹이면 아무것도 안함.
			}else if(Layers[key].fnGroup === fn ||
		 		    XeusLayer.isFnGroupContains(fnGroup, Layers[key].fnGroup)){
				//console.log("equal......" + Layers[key]["name"]);

		        //요청한 그룹과 일치하는 경우 보이기 설정
				//state=active: 범례에 보여야 하므로 active로 설정
				//visible : pre_visible: 이전에 저장하고 있던 visible설정
				//         ex) nms에서 cable:off하고 cctv기능 수행후 다시 nms돌아올때
				//             이전에 설정한 cable:off를 유지하고자 함
				Layers[key]["state"]='active';
				var preVisible = Layers[key]["pre_visible"];//저장된 visible를 가져옴
				if(preVisible == null || preVisible == undefined)preVisible=true;
				Layers[key]["visible"]=preVisible; //보이기 off
				xeusLayout.mapService.setLayerVisible(Layers[key]["name"], preVisible);
			}else{
				//console.log("etc......" + Layers[key]["name"]);
				//그외 현재 보이기 상태를 pre_visible로 저장하고
				// visible=false, state=inactive로 함
				//범례에서는 state=active인 것만 보여주면 됨.
				Layers[key]["state"]='inactive';
				Layers[key]["pre_visible"]=Layers[key]["visible"]; //현재 visible상태 저장
				Layers[key]["visible"]=false; //보이기 off
				xeusLayout.mapService.setLayerVisible(Layers[key]["name"], false);
			}
		}
	};
//
//	Public.Mode = fnGroup;
//
//	xeusLayout.mapService.removeAllLayers();
//
//	//console.log(">>>>>> InitLayers......... ");
//	var fn = LayerFnGroup[fnGroup];
//	for(var key in Layers){
//		/* 영상레이어 생성 */
//		if(Layers[key].tile != null){
//			if(Layers[key].fnGroup == LayerFnGroup.CMMN){
//				xeusLayout.mapService.addLayer(
//					Layers[key].tile.createMapLayer({
//						visible : true,
//						proxy : false
//					})
//				);
//			}
//		}else{
//			/* 공통 레이어일 경우 */
//			if(Layers[key].fnGroup === LayerFnGroup.CMMN){
//				xeusLayout.mapService.addLayer(
//					xeusLayout.mapService.createWFSLayer(Layers[key])
//				);
//			/* 현재 기능 그룹과 일치하는 레이어일 경우 */
//			}else if(Layers[key].fnGroup === fn || XeusLayer.isFnGroupContains(fnGroup, Layers[key].fnGroup)){
//				/* CCTV일 경우(심볼표현을 위해 다른 요청) */
//
//				if(key == "asset_cctv"){
//					xeusLayout.mapService.addLayer(
//						xeusLayout.mapService.createVectorLayer(Layers.asset_cctv)
//					);
//					xeusCCTV.initCCTV(xeusLayout.ctxPath);
//					xeusCCTV.cctv.reload();
//				/* 일반 WFS 레이어일 경우 */
//				}else{
//					/* 주제도 일 경우 */
//					if(Layers[key]["LayerTheme"] != null){
//						LayerConst.ThemeLoad(key);
//						var thmLayer = xeusLayout.mapService.createVectorLayer(Layers[key]);
//						xeusLayout.mapService.addLayer(thmLayer);
//						Layers[key].loadFunction(thmLayer);
//					/* 단일 레이어일 경우 */
//					}else{
//						xeusLayout.mapService.addLayer(
//							xeusLayout.mapService.createWFSLayer(Layers[key])
//						);
//					}
//				}
//			}
//		}
//	}
}

xeusLayout.initMap = function(target, tab) {
	/*window.sysAlert = window.alert;
	window.sysConfirm = window.confirm;
	window.alert = xeusLayout.customAlert;
	window.confirm = xeusLayout.customConfirm;*/

	// 레이어 초기 설정하기..........
	var mapService = $(tab).data('map');
	if(mapService !== undefined){
		xeusLayout.mapService = mapService;
	} else {
		xeusLayout.mapService = new geomex.xeus.MapService({
			ctxPath : xeusLayout.ctxPath,
			controls : [
	         new ol.control.ScaleLine(),
	         new ol.control.MousePosition({
			    projection: 'EPSG:4326',
			    coordinateFormat: function(coordinate) {
			      return ol.coordinate.format(coordinate, '{x}, {y}', 4);
			    }
			  }),
		    ],
			projection : ol.proj.get("EPSG:5186"),
			target : target,//'xeus-map-content',
			//서초구 기준으로 초기화면 변경
			//center : [ 211594.147, 371755.67 ],
			center : [ 223038.97507357592, 441159.0711484331 ],
//			zoom : 12, // 클수록 확대
//			minZoom : 9,
//			maxZoom : 24
			zoom : 12, // 클수록 확대
			minZoom : 9,
			maxZoom : 19
		});

		$(tab).data('map', xeusLayout.mapService);
	}

	//2017.11.21 by khkim,
	//모든레이어를 생성한다.cctv,event,nms 활성화여부는 initLayer 참조
	for(var key in Layers){
		if(Layers[key].type == LayerConst.TMS){
			if(key == "daum_tile_map"){
				xeusLayout.mapService.addLayer(Layers[key].tile.createMapLayer());
			}else if(key == "daum_map"){
				xeusLayout.mapService.addLayer(Layers[key].tile.createSkyViewLayer());
			}else if(key == "daum_hybrid"){
				xeusLayout.mapService.addLayer(Layers[key].tile.createHybridLayer());
			}else if(key == "naver_map_view"){
				xeusLayout.mapService.addLayer(Layers[key].tile.createNaverViewLayer());
			}else if(key == "emap_map_view"){
				xeusLayout.mapService.addLayer(Layers[key].tile.createEmapViewLayer());
			}
			/*xeusLayout.mapService.addLayer(
				Layers[key].tile.createMapLayer({
					visible : true,
					proxy : false
				})
			);*/
		}else if(Layers[key].type != null && Layers[key].type != "HEAT"){
			//2017.11.21 by khkim, Layers.LayerTheme를 제외 해야함 ...
			//console.log(key + " / " + Layers[key]["LayerTheme"]);
			if(Layers[key]["LayerTheme"] != null){
				LayerConst.ThemeLoad(key);
				var thmLayer = xeusLayout.mapService.createVectorLayer(Layers[key]);
				xeusLayout.mapService.addLayer(thmLayer);
				Layers[key].loadFunction(thmLayer);
		    }else{
		    	if(key == "asset_cctv"){
					xeusLayout.mapService.addLayer(
						xeusLayout.mapService.createVectorLayer(Layers.asset_cctv)
					);
					Layers.asset_cctv.themeLoad();
					xeusCCTV.initCCTV(xeusLayout.ctxPath);
			   }else if(key == "v_asset_eqb_aws" || key == "v_asset_eqb_voice" || key == "asset_disbord" || key == "asset_heat"){
					var thmLayer = xeusLayout.mapService.createVectorLayer(Layers[key]);
					xeusLayout.mapService.addLayer(thmLayer);
					Layers[key].loadFunction(thmLayer);
			   }else {
				   xeusLayout.mapService.addLayer(
						xeusLayout.mapService.createWFSLayer(Layers[key])
				   );
		       }
		    }
		}
	};

	if(tab.contains("eventView")){
		eventVectorSource = new ol.source.Vector();
		eventVectorLayer = new ol.layer.Vector({
			source: eventVectorSource,
			zIndex: 9999
		});
		xeusLayout.mapService.addLayer(eventVectorLayer);
		xeusLayout.mapService.addLayer(xeusSymbol.createVector());
		xeusLayout.mapService.addLayer(xeusSymbol.createGuideLine());
	}

	// var daum = new geomex.xeus.tms.DaumMap();

	// var tileLayerInfo = [];
	// tileLayerInfo.push(new geomex.xeus.LayerInfo(50, 'kais_river_as', true,
	// '', ''));
	// tileLayerInfo.push(new geomex.xeus.LayerInfo(51, 'kais_park_as', true,
	// '', ''));

	/*
	 * xeusLayout.mapService.addLayer(daum.createMapLayer({ visible : true,
	 * proxy : false }));
	 */

	// xeusLayout.mapService.addLayer(daum.createSkyViewLayer({
	// visible : true,
	// proxy : false
	// }));

	// xeusLayout.mapService.addLayer(xeusLayout.mapService.createTileWMSLayer({
	// layerInfo : tileLayerInfo,
	// name : '_TileWMSLayer'
	// }));

	// xeusLayout.mapService.addLayer(daum.createHybridLayer({
	// visible : false,
	// proxy : false
	// }));

	// xeusLayout.mapService.addLayer(xeusLayout.mapService.createWFSLayer({
	// url : xeusLayout.ctxPath + '/wfs',
	// visible : true,
	// name : 'kais_buld_as',
	// typeName : 'gmx:kais_buld_as'
	// }));

	// 180904 이은규 이벤트 바인딩 시점 이동
	// 모든 대메뉴 이동시 initMap을 처리하므로 initMap 호출 시 같이 이벤트를 등록한다.
	// ////////////////////////////////////////////////
	// 슬라이드 관련 버튼 이벤트..
	// ///////////////////////////////////////////////
	//$('#west-slide-btn').on('click', function() {
	/*$('#west-slide-btn').on('click', function() {
		if ($("#center-overlay-west").attr('xeus-show') == 'true') {
			xeusLayout.hideOverlayWestPane(ANI_DELAY, function() {
				//console.log("show....'left pane.....");
			});
		} else {
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				//console.log("show....'left pane.....");
			});
		}
	});

	//$('#east-slide-btn').on('click', function() {
	$('#east-slide-btn').on('click', function() {

		if ($("#center-overlay-east").attr('xeus-show') == 'true') {
			xeusLayout.hideOverlayEastPane(ANI_DELAY, function() {
				//console.log("show....'right pane.....");
			});
		} else {
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
				//console.log("show....'right pane.....");
			});
		}
	});*/

	//$('#west_btn_close').on('click', function() {
	$("#" + parentView).find('#west_btn_close').on('click', function() {
		if($("#smsWrapper").css("display") == "block"){
			$("#smsWrapper").toggle("slide");
		}else if($("#eventWrapper").css("display") == "block"){
			$("#eventWrapper").toggle("slide");
		}else if($("#iconWrapper").css("display") == "block"){
			$("#iconWrapper").toggle("slide");
		}else{
			xeusLayout.hideOverlayWestPane();
		}
	});

	//$('#west_btn_close').on('click', function() {
	$("#" + parentView).find('#west_lgd_btn_close').on('click', function() {
		xeusLayout.hideOverlayLgdWestPane();
	});

	/**
	 * 180905 이은규
	 * geomex.xeus.mapEvent.js에서 빼왔음.
	 * 생성되는 탭마다 이벤트 바인딩하기 위해 initMap 호출 시 이벤트 바인딩
	 */
	$('#'+parentView).find("#center-overlay-west").attr('xeus-event','');
	$('#'+parentView).find("#center-overlay-east").attr('xeus-event','');

	/* 지도 기본기능 메뉴 이벤트 입니다. */
	//$(document).on("click", ".menu-button", function(){
	$('#'+parentView).find(".menu-button").click(function(){
		var id = $(this).attr("id");
		if(id == "btn-map-home") /* 홈 */	xeusLayout.mapService.moveStartCenter();
		if(id == "btn-map-cler") /* 정리 */ xeusLayout.mapService.clear();
		if(id == "btn-map-prev") /* 이전 */ xeusLayout.mapService.getMap().getPrev();
		if(id == "btn-map-next") /* 다음 */ xeusLayout.mapService.getMap().getNext();
		if(id == "btn-map-move") /* 이동 */ xeusLayout.mapService.measure.disable();
		if(id == "btn-map-dist") /* 거리 */ xeusLayout.mapService.measure.enable("length");
		if(id == "btn-map-area") /* 면적 */ xeusLayout.mapService.measure.enable("area");
		if(id == "btn-map-indx") /* 인덱스*/$('#'+parentView).find(".overview").toggle();

		if(id == "btn-map-favr") /* 관심 */ $('#'+parentView).find("#fav-wrap").toggle(0, function(){
			var top = $('#'+parentView).find("#btn-map-favr").offset().top - $('#'+parentView).find("#btn-map-favr").height() - 10;
			var left = $('#'+parentView).find("#btn-map-favr").offset().left + 50;
			$('#'+parentView).find("#fav-wrap").css({"top" : top, "left" : left});
		});
		if(id == "btn-map-image") /* 캡처 */ {
			var canvas = $('#'+parentView).find('.map-target').find('canvas')[0];
			var date = new Date().getYMDHMS_S();
			xeusLayout.downloadMapImage(canvas.toDataURL("image/png"), date+"-map.png");
		}
		if(id == "btn-road-mng")/*로드뷰*/ {
			var _html = '';
			if ( geomex.xeus.DaumRoadView.isAlive() ){
				geomex.xeus.DaumRoadView.destroyRoadView();
				return;
			};

			_html += '<div id="roadview_pop" style="display:none"> ';
			_html += '    <div id="roadview_title_bar"> ';
			_html += '       <span id="roadview_title">로드뷰</span> ';
			_html += '       <button id="roadview_close"> ';
			_html += '      	 <img src="/xeus/res/img/delete_normal.png"> ';
			_html += '       </button> ';
			_html += '    </div> ';
			_html += '    <div id="roadview"></div>> ';
			_html += '</div> ';

			$("#" + parentView).find('.map-target').append(_html);
//			geomex.xeus.DaumRoadView.createTMS();
			try{
				geomex.xeus.DaumRoadView.createMarker();
				alert("원하는 지점에 마커를 이동하면 로드뷰가 생성됩니다.");
			}catch(e){
				console.error(e);
				alert("로드뷰를 생성할 수 없습니다.\n\n네트워크 환경을 확인해 주세요.");
				geomex.xeus.DaumRoadView.destroyRoadView();
			}

		}
		if(id != "btn-total-stats-view") $("#widgetBtn").remove();

		if(Public.StopEvent != null && $(this).attr("for") != "btn-evnt-view") Public.StopEvent();

		var target = $(this).attr("for");
		if(socket != null && target != "btn-evnt-view"){
			/*socket.getWebSocket().close();
			socket = null;*/
		}

		$.widget('ui.dialog', jQuery.extend({}, jQuery.ui.dialog.prototype, {
			_title : function(titleBar) {
				titleBar.html(this.options.title || '&#160;');
			}
		}));
	});

	/* 관심영역 오버 및 종료 이벤트 입니다. */
	//$(document).on("hover", "#popup-closer", function(){
	$('#'+parentView).find("#popup-closer").hover(function(){
		var src = $(this).attr("src");
		$(this).attr("src", src.replace("normal", "over"));
	}, function(){
		var src = $(this).attr("src");
		$(this).attr("src", src.replace("over", "normal"));
	});
	//$(document).on("click", "#popup-closer", function(){
	$('#'+parentView).find("#popup-closer").click(function(){
		$('#'+parentView).find("#fav-wrap").hide();
	});
};

xeusLayout.initTree = function(json) {
	var setting={
		data : {
			simpleData : {
				enable: true
			}
		},
		check :{
			enable: true
		},
		callback: {
			onCheck: function(event,treeId,treeNode){
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				var nodes = treeObj.getCheckedNodes(true);
				nodes=filterNodes(nodes);
				callBackChangeCheckBox(nodes);

			}
		}
	};
	$.fn.zTree.init($("#tree"), setting, json.result);
};
function filterNodes(nodes){
	var result=[];
	for(var i=0; i<nodes.length; i++){
		if(nodes[i].phoneNumber!=undefined){
			result.push(nodes[i]);
		}
	}
	return result;
}
function callBackChangeCheckBox(nodes){
	$('#send_select_list').children().remove();
	var str;
	for(var i=0; i<nodes.length; i++){
		str = '<span id="send_select_'+nodes[i].id+'" nm="'+nodes[i].name+'" telno="'+nodes[i].phoneNumber+'" class="send_select_item"><span>'+nodes[i].name+'</span><span class="send_list_remove" key="'+nodes[i].id+'">x</span></span>';
		$('#send_select_list').append(str);
		bindRemoveEvent(nodes[i].id);
	}
	bindAllRemoveEvent();
};
function bindRemoveEvent(key){
	$('#send_select_'+key).find('.send_list_remove').on('click', function(){
		var kay =$(this).attr('key');

		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getCheckedNodes(true);
		for (var i=0, l=nodes.length; i < l; i++) {
			if(nodes[i].id===key)
			treeObj.checkNode(nodes[i], false, true);
		}

		$('#send_select_'+key).remove();

//		if ( $('#send_select_list .send_select_item').length === 0 ) {
//
//			$('#send_select_list').html(hintTag);
//		};
	});
};
function bindAllRemoveEvent(){
	//리스트 전체 삭제
	$('#send_list_all_remove').on('click', function(){
		$('#send_select_list').find('.send_list_remove').each(function(){
			$(this).click();
		});
	});
}
