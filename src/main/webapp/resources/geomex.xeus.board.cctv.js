//2017.12.20 by khkim
//Board용으로 cctv기능 을 재정의
// 심볼 클릭시 전체 play기능 제거..

xeusCCTV.initCCTV = function(_ctxPath) {
	xeusCCTV.ctxPath = _ctxPath;

	xeusCCTV.cctv = new geomex.xeus.CCTV({
		ctxPath : xeusCCTV.ctxPath,
		mapService : xeusLayout.mapService,
		layer : 'cctv',
		virtualBoundary : 'virtual-map-boundary'
	});

	xeusCCTV.cctv._showMapOverlayDetailList = function(_event) {
		// / _showMapOverlayDetailList 함수 재정의...
		var map = xeusLayout.mapService.getMap();
		var cctvLayer = xeusCCTV.cctv.getCCTVLayer();
		var ctxPath = xeusCCTV.ctxPath;
		var _self = xeusCCTV.cctv;
		var playList = _self.getPlayList();
		var mapOverlay = _self.getMapOverlay();
		// /////////////////////////////////

		map.getTargetElement().style.cursor = '';
		_self.closeMapOverlay(); // 이전에 보이는 overlay닫는다.

		var _lyrName = cctvLayer.get('name');
		var _feature = map.forEachFeatureAtPixel(_event.pixel, function(feature) {
			return feature;
		}, {
			layerFilter : function(_layer) {
				return _layer.get('name') == _lyrName;
			}
		});
		if (!_feature) { // _feature 가 없다.
			return;
		}

		var _cctvSize = _feature.get('cctvSize');
		var _point = _feature.getGeometry().getCoordinates();
		var _cctvList = _feature.get('cctvList');
		var _txtSize = 200;

		var _html = '<div>';
		var _url = ctxPath + "/res/sym/cctv/";
		// 그룹CCTV일경우 동일위치 타이틀을 보여준다.
		// if (_cctvSize > 1) {
		// var _symCode = _feature.get("symCode");
		// for (var x = 0; x < _cctvSize; x++) {
		// _cctvList[x]['point'] = _point;
		// }
		//
		// var _json = encodeURIComponent(JSON.stringify(_cctvList));
		// // jwr 각 CCTV마다 있는 geojson을 가져옴??
		// _html += '<div class="cctv-overlay-content-item"
		// style="background-color:#2B5E93;">';
		//
		// // jwr 심볼을 눌렀을때 전체 보기 - viewVideo
		// _html += '<div class="cctv-overlay-content-img-div">'
		// _html += '<a href="#" onClick="xeusCCTV.viewVideo(\'' + _json +
		// '\');" >';
		// _html += '<img class="cctv-overlay-content-img" src="' + _url +
		// _symCode + '.png"/> ';
		// _html += '</a>';
		// _html += '</div>'
		// // cctv전체 상세정보 보기 메뉴 추가
		// _html += '<div class="cctv-overlay-content-txt-div">';
		// //_html += '<a href="#" onClick="xeusCCTV.viewInformation(\'' + _json
		// + '\');" ';
		// //_html += ' style="text-decoration:none;" >';
		// _html += '<span class="cctv-overlay-content-txt">동일위치CCTV (' +
		// _cctvSize + ')</span>';
		// //_html += '</a>';
		// _html += '</div>';
		//
		// // empty div....
		// _html += '<div class="cctv-overlay-content-img-div">';
		// _html += '<span class="cctv-overlay-content-txt"
		// style="width:34px;"></span>';
		// _html += '</div>';
		// _html += '</div>';
		// }

		// 목록 symbol 아이콘을 drag할때 전달할 정보 목록
		var draggableIds = [];

		var _fontFamily = $("body").css("font-family");
		// overlay가 보여져야 css함수를 쓸수 있음. 아니면 undefined 반환
		// CCTV 목록 보여주기, 목록이 너무 많으면 scroll처리 함.
		_html += '<div>';
		if (_cctvSize > 8) {
			_html += '<div style="height: 260px; overflow-y: auto;">';
		}
		// 동일위치 cctv만큼 목록 생성.
		// jwr 전체 CCTV관련을 빼고 나머지 CCTV심볼, 타이틀 출력
		for (var x = 0; x < _cctvSize; x++) {
			var gid = _cctvList[x]['gid'];
			var mgrNo = _cctvList[x]['mgrNo'];
			var cctvNm = _cctvList[x]['cctvNm'];
			var deviceId = _cctvList[x]['deviceId'];
			var channelNo = _cctvList[x]['channelNo'];
			var gbnCd = _cctvList[x]['gbnCd'];
			var gbnTxt = _cctvList[x]['gbnTxt'];
			var angle = _cctvList[x]['angle'];

			_cctvList[x]['point'] = _point;
			var dragId = 'cctv_drag_' + gid;

			// cctvList[x]['cy'] = point.y;
			// 상세목록 메뉴 선택시 매개변수로 전달할 정보를 encoding 한다.
			var _json = encodeURIComponent(JSON.stringify(_cctvList[x]));
			var _txtLen = window.getWidthOfText(cctvNm, _fontFamily, '13px');

			// Popup Dialog 가로 사이즈 추정
			if ((_txtLen) + 100 > _txtSize) {
				_txtSize = (_txtLen) + 100;
			}
			_html += '<div class="cctv-overlay-content-item">';
			// cctv 영상보기 메뉴 추가
			_html += '<div class="cctv-overlay-content-img-div" >';
			 _html += '<a href="#" onClick="xeusCCTV.viewVideo(\'' + _json + '\');" >';
			_html += '<img id=\"' + dragId + '\" ';

			// 만약 현재 play중이면 play중 symbol로 변경해야 함.
			var _symImage = _url + gbnCd;
			if (playList[gid]) {
				_symImage += 'P';
			}
			_html += 'class="cctv-overlay-content-img" src="' + _symImage + '.png"/> ';
			 _html += '</a>';
			_html += '</div>'
			// cctv상세정보 보기 메뉴 추가
			_html += '<div class="cctv-overlay-content-txt-div">';
			// _html += '<a href="#" onClick="xeusCCTV.viewInformation(\'' +
			// _json + '\');" ';
			// _html += ' style="text-decoration:none;" >';
			_html += '<span class="cctv-overlay-content-txt">' + cctvNm + '</span>';
			// _html += '</a>';
			_html += '</div>';
			// 투망분석 메뉴 추가
			_html += '<div class="cctv-overlay-content-img-div">';
			// _html += '<a href="#" onClick="xeusCCTV.viewNetWatch(\'' + _json
			// + '\');" >';
			// _html += '<img class="cctv-overlay-content-img" src="' + ctxPath
			// + '/res/img/netwatch_btn.png"/>';
			// _html += '</a>';
			_html += '</div>';

			// 하나밖에 없으면 x(closer) 보이게 빈 div추가
			if (_cctvSize == 1) {
				_html += '<div style="width: 26px;border: 0;"></div>';
			}

			_html += '</div>'; // cctv-overlay-content-item

			draggableIds[x] = {
				gid : gid,
				mgrNo : mgrNo,
				cctvNm : cctvNm,
				deviceId : deviceId,
				channelNo : channelNo,
				gbnCd : gbnCd,
				gbnTxt : gbnTxt,
				angle : angle,
				point : _point,
				dragId : dragId
			};
			// console.log(dragId + " " +txt );
		}
		_html += '</div>'; // for문 cctv목록

		_html += '</div>'; // overlay 전체
		$('#cctv-overlay-content').html(_html);
		// text사이즈에 따라서 Dialog 크기를 변경한다.
		$('#cctv-overlay').css('min-width', _txtSize + 'px');
		$('#cctv-overlay-closer').css('display', 'inline');
		mapOverlay.setPosition(_point);
		// dialog type cf. symbol_list
		mapOverlay.set('type', 'cctv_detail_list');

		// //////////////////////////////////////////
		// draggable 설정
		// //////////////////////////////////////////
		// videoDialog, VideoGridPane에 드래그 하여 play 할수 있도록 설정
		for (x = 0; x < draggableIds.length; x++) {
			var cctvDragID = "#" + draggableIds[x].dragId;
			$(cctvDragID).draggable({
				appendTo : "body",
				helper : "clone",
				start : function(event, ui) {
					$(ui.helper).addClass("ui-draggable-helper");
					$(this).draggable("option", "revert", true);

					/* 2017-09-19 이주영 수정 */
					$(".dropBox").show("puff", 200);
					for (var i = 0; i < 2; i++) {
						$(".dropBox").animate({
							"color" : "white"
						}, 150);
						$(".dropBox").animate({
							"color" : "gray"
						}, 150);
					}
				}
			}).data({
				"gid" : draggableIds[x].gid,
				"mgrNo" : draggableIds[x].mgrNo,
				"cctvNm" : draggableIds[x].cctvNm,
				"deviceId" : draggableIds[x].deviceId,
				"channelNo" : draggableIds[x].channelNo,
				"gbnCd" : draggableIds[x].gbnCd,
				"gbnTxt" : draggableIds[x].gbnTxt,
				"angle" : draggableIds[x].angle,
				"point" : draggableIds[x].point
			});
		}
	}

}