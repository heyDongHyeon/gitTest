// 클래스 namespace만들기.
if (window.geomex == null)
	var geomex = {}

if (geomex.xeus == null) {
	geomex.xeus = {}
}

geomex.xeus.ToggleButtons = function(_opt) {
	// toggle button으로 사용한 버튼 id를 저장

	var BUTTONS = [];

	/*171212 css 수정*/
	var _color = 'rgb(170, 170, 170)';
	var _toggleColor = 'white';
	var _tabcolor = '#3e3f48';
	var _tabtoggleColor = '#4582ac';

	if (_opt != null && _opt.color != null) {
		_color = _opt.color;
	}

	if (_opt != null && _opt.toggle != null) {
		_toggleColor = _opt.toggle;
	}

	var _self = this;

	//$('#' + _player_grid_id + '_btn_close').unbind('click');

	this.clear = function(){
		$.each(BUTTONS, function(i, val) {
			$(val).unbind('click');

		});
		BUTTONS = [];
	}

	this.add = function(btnId, eventFunc) {
		if(btnId.indexOf('btn-map')>-1){
			return;
		}
		BUTTONS.push(btnId);
		$(btnId).off('click').on('click', function() {

			var target = $(this).attr('target');
			_self.toggle(btnId);

			if ( geomex.xeus.DaumRoadView.isAlive() ) {
				xeusLayout.showYesNoDialog("다음 로드뷰 종료",
						"화면 전환 시 다음 로드뷰 기능은 종료됩니다.<br>다음 로드뷰 기능을 종료하시겠습니까?",
						function() {
							geomex.xeus.DaumRoadView.destroyRoadView();

							if (eventFunc) {
								eventFunc();
							}
							_self.defultEvent(btnId, target);
						}
				);
			} else {
				if (eventFunc) {
					eventFunc();
				}
				_self.defultEvent(btnId, $(this).attr('target'));
			}
		});
	};

	/*171212 css 수정*/
	this.toggle = function(btnId) {
		if ( btnId.indexOf('map') == -1) {
			$.each(BUTTONS, function(i, val) {
				$(val).css('color', _color);
				$(val).css('border-bottom', '');
				if (val == btnId) {
					$(btnId).css('color', _toggleColor);
					//$(btnId).css('border-bottom', '2px #ffffff solid');
				}
			});
		} else {
			$.each(BUTTONS, function(i, val) {
				$(val).css('background', _tabcolor);
				if (val == btnId) {
					$(btnId).css('background', _tabtoggleColor);
				}
			});
		}
	};

	this.count = function() {
		return BUTTONS.length;
	};

	this.defultEvent = function(btnId, parent){
		//var id = $(this).attr("id");
		var $targetObj = $(btnId);
		$("#layout-center").css("background", "none");

		$("#tabs .tab").each(function(){
	    	$(this).css("background", "#30303a").removeAttr("active");

	    	var src = $(this).find(".tabRight").attr("src");
	    	$(this).find(".tabRight").attr("src", src.replace("over", "normal")).css("z-index", 0);
		});

		var src = $targetObj.attr("src");

		if ( $targetObj.hasClass('active') ) {
			$('#tabs .tab[target='+$targetObj.attr('target')+']').click();
			//$targetObj.removeClass('active');
		//	$targetObj.attr("src", src.replace("over", "normal")).css("z-index", 0);
		//	$targetObj.attr("src", src.replace("normal", "over")).css("z-index", 0);
		} else {
			if(btnId.indexOf('btn-dashboard-view')>-1){

			}else{
				$targetObj.addClass('active');
			}

			$("#" + parent).find("#overlay-west-side-bar").show();

		//if (  $("#tabs .tab[target=" + $(this).attr("target") + "]").css('display') === 'none') {
			$("#" + parent).find("#overlay-west-side-bar").find(".menu-button").eq(0).click();
			$targetObj.attr("src", src.replace("normal", "over")).css("z-index", 1);
		//}
		}

		var $target = $("#tabs .tab[target=" + $targetObj.attr("target") + "]");
		src = $target.find(".tabRight").attr("src");
		$target.css("background", "#4582ac").attr("active", "active");
		$target.find(".tabRight").attr("src", src.replace("normal", "over")).css("z-index", 1);
	}
};