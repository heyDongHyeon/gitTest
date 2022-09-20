// 클래스 namespace만들기.
if (window.geomex == null)
	var geomex = {}

if (geomex.xeus == null) {
	geomex.xeus = {}
}

geomex.xeus.CctvStyle = function(_opt) {
	this.id = _opt.id; // style id
	this.sym = _opt.sym; // 기본 심볼 스타일
	this.play = _opt.play; // video play시 스타일
	this.visible = _opt.visible; // 보이기 여부
};

geomex.xeus.CCTV = function(options) {
	var ctxPath = options.ctxPath;
	var mapService = options.mapService;
	var cctvLayer = mapService.getLayerByName(options.layer);
	var virtualMapBoundary = options.virtualBoundary;

	var map = mapService.getMap();

	var SYMBOLS = [ '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '98', '99',
			'X2', 'X3', 'X4', 'X5', 'X6', 'X7', 'X8', 'X9', 'XX' ];

	var SYMSIZE = 40;

	var STYLES = {}; // cctv 스타일(기본, playing);

	// play되는 cctv 목록(_gid)기준
	// _geometry의 _gid를 사용하면 변경 가능성이 있으므로 고유키로 관리 되어야 함..
	// _player_dlg_ : videoDialog에 플레이 되는 cctv정보
	// _player_grid_ : videoGridPane에 플레이 되는 cctv정보
	var PLAY_LIST = {};

	// 투망모니터링 리스트
	var NET_LIST = {};

	// ntsc 320*240, 640*480
	this.SIZE = {
		width : 320,//480,
		height : 240,//360, // 창: 280
		minWidth : 200,
		minHeight : 150,
		mode1 : 1,
		mode2 : 1.5,
		mode3 : 2
	};

	var _self = this;

	this.SYM_ICON = {};
	/*_common.callAjax("/symIcon/getSymIconList.json", {'symGrp' : 'ctv'}, function(json){
		var obj = {};
		$.each(json.result,function(key,value) {
			if(_self.SYM_ICON[value.gbnCd] === undefined || _self.SYM_ICON[value.gbnCd] == null){
				obj = {};
			} else {
				obj = _self.SYM_ICON[value.gbnCd];
			}
			obj[value.iconTyp] = value.fileNm;
			_self.SYM_ICON[value.gbnCd] = obj;
		});
		//console.log(_self.SYM_ICON);
	}, false);*/

	_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'asset_cctv'}, function(json) {
		if(json.result){
			var obj = {};
			for(var i=0; i<json.result.length; i++){
				obj[json.result[i].gbnCd] = json.result[i].symMgrNo;
			}
			_self.SYM_ICON = obj;
		}
	}, false);

	// 마우스 move(click)시 겹칩된 cctv 목록을 보여주기 위한 cctv-overlay 생성
	var mapOverlay = _createMapOverlay();

	// openlayers interaction을 등록한다.
	// mouse move시 overlay보이기, click시 상세 cctv목록 보여주기
	map.addInteraction(new ol.interaction.Pointer({
		handleMoveEvent : function(evt) {
			var _type = mapOverlay.get('type');// ,'cctv_list');
			if (_type != 'cctv_detail_list') { // DetailList Dialog skip
				_showMapOverlay(evt);
			}
			/*var _type = mapOverlay.get('type');// ,'cctv_list');

			var mouseCoordInMapPixels = [evt.originalEvent.offsetX, evt.originalEvent.offsetY];
			var hit = map.forEachFeatureAtPixel(mouseCoordInMapPixels, function(feature, layer){
				if(feature.getGeometry() instanceof ol.geom.Point){
					if (_type != 'cctv_detail_list') { // DetailList Dialog skip
						_showMapOverlay(evt);
					}
				}
			});*/
		},
		handleDownEvent : function(evt) {
			_self.closeMapOverlay(evt);

			var mouseCoordInMapPixels = [evt.originalEvent.offsetX, evt.originalEvent.offsetY];
			var hit = map.forEachFeatureAtPixel(mouseCoordInMapPixels, function(feature, layer){
				if(feature.getGeometry() instanceof ol.geom.Point){
					var prop = feature.getProperties();
					try{
						if("cctvList" in prop){
							_self._showMapOverlayDetailList(evt);
						}else{
							var tblId = feature.id_.split(".")[0];
							var targetField;

							if(tblId == "v_asset_eqb_voice"){
								targetField = "nm";
							}
							else if(tblId == "v_asset_eqb_aws"){
								targetField = "nm";
							}
							else if(tblId == "asset_smart_cctv"){
								targetField = "cctv_nm";
							}
							else if(tblId == "asset_disbord"){
								targetField = "bord_nm";
							}
							else if(tblId == "asset_heat"){
								targetField = "name";
							}

							if(targetField != undefined){
								_self._showMapOverlayDetailVector(evt, targetField);
							}

						}
					}catch(e){

					}
				}
			});
		},
		handleDragEvent : function(evt) {
			//console.log("handleDragEvent");
		},
		handleUpEvent : function(evt) {
			//console.log("handleUpEvent");
		}
	}));

	// CCTV 스타일을 생성한다.
	var _anchor = [ 0.5, 0.5 ];
	var _size = [ SYMSIZE, SYMSIZE ];
	for (x = 0; x < SYMBOLS.length; x++) {
		var symId = SYMBOLS[x] + '';

		/*var normalImgNm = _self.SYM_ICON[symId]['N'];
		var playImgNm = _self.SYM_ICON[symId]['P'];*/
		STYLES[symId] = new geomex.xeus.CctvStyle({
			id : symId,
			sym : new ol.style.Style({
				image : new ol.style.Icon(({
					anchor : _anchor,
					size : _size,
					//src : ctxPath + '/res/sym/cctv/' + normalImgNm
					src : ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[symId]
				}))
			}),
			play : new ol.style.Style({
				image : new ol.style.Icon(({
					anchor : _anchor,
					size : _size,
					//src : ctxPath + '/res/sym/cctv/' + playImgNm
					src : ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[symId]
				}))
			}),
			visible : true
		});
	}

	/**
	 * mapservice반환
	 */
	this.getMapService = function(){
		return mapService;
	}

	/**
	 * cctvlayer반환
	 */
	this.getCCTVLayer = function(){
		return cctvLayer;
	}

	/**
	 * 외부 override 시 호출되는 경우 있음
	 */
	this.getMapOverlay = function(){
		return mapOverlay;
	}

	/**
	 * CCTV playlist 반환한다.
	 */
	this.getPlayList = function(){
		return PLAY_LIST;
	}

	/**
	 * 투망모니터링 리스트를 반환한다.
	 */
	this.getNetList = function(){
		return NET_LIST;
	}

	/**
	 * 심볼 스타일 배열을 리턴합니다.
	 */
	this.getStyle = function(){
		return STYLES;
	}

	/**
	 * 주어진 값을 통해 스타일을 설정합니다.
	 */
	this.setSymbolStyle = function(value){
		var thm = Layers.LayerTheme["asset_cctv"];
		for(var key in thm){
			STYLES[thm[key]].visible = false;
		}

		var array = value.split(",");
		for(var i=0; i<array.length; i++){
			var key = Layers.LayerTheme["asset_cctv"][array[i]];
			STYLES[key].visible = true;
		}

		return this;
	}

	/*
	 * STYLES 내용을 변경한다.
	 */
	this.resetStyles = function(){
		for (x = 0; x < SYMBOLS.length; x++) {
			var symId = SYMBOLS[x] + '';

			/*var normalImgNm = _self.SYM_ICON[symId]['N'];
			var playImgNm = _self.SYM_ICON[symId]['P'];*/
			STYLES[symId] = new geomex.xeus.CctvStyle({
				id : symId,
				sym : new ol.style.Style({
					image : new ol.style.Icon(({
						anchor : _anchor,
						size : _size,
						//src : ctxPath + '/res/sym/cctv/' + normalImgNm
						src : ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[symId]
					}))
				}),
				play : new ol.style.Style({
					image : new ol.style.Icon(({
						anchor : _anchor,
						size : _size,
						//src : ctxPath + '/res/sym/cctv/' + playImgNm
						src : ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[symId]
					}))
				}),
				visible : true
			});
		}
	}

	/*
	 * STYLES 확인용 임시 함수
	 */
	this.getStyles = function(){
		return STYLES;
	}

	// cctv 레이어에 styleFunction을 설정한다.
	// play중인 gid가 있는 경우 symbol을 갱신해야 한다.
	// 180404 투망모니터링 리스트도 체크하여 심볼 아이콘을 지정한다.
	/**
	 * @Deprecated
	 */
	cctvLayer.setStyle(function(_feature) {
		var _code = _feature.get('symCode');

		return (_code) ? STYLES[_code].sym : STYLES['99'].sym;

		// 플레이 목록이 없으면 기본 심볼로....
		if (Object.keys(PLAY_LIST).length == 0 && Object.keys(NET_LIST).length == 0) {
			return (_code) ? STYLES[_code].sym : STYLES['99'].sym;
		}

		// Video Play 목록이 있다.
		var _cctvList = _feature.get('cctvList');
		var _size = _cctvList.length;
		for (var x = 0; x < _size; x++) {
			var _gid = _cctvList[x]['gid'];
			if (PLAY_LIST[_gid] != undefined || NET_LIST[_gid] != undefined) {
				return (_code) ? STYLES[_code].play : STYLES['99'].play;
			}
		}

		return (_code) ? STYLES[_code].sym : STYLES['99'].sym;
	});

	// visible된 style의 id배열을 얻어온다.
	// reload에서 visible된 cctv정보만 가져오기 위해 사용됨.
	function _getVisibleStyleIds() {
		var _len = Object.keys(STYLES).length;
		if (_len == 0) {
			return null;
		}
		var _codes = [];
		for ( var key in STYLES) {
			if (STYLES[key].visible == true) {
				_codes.push('\'' + key + '\'');
			}
		}
		return _codes;
	}

	// mouse over, click 시 보여줄 mapOverlay를 생성한다.
	function _createMapOverlay() {
		var _html = '<div id="cctv-overlay" class="cctv-overlay">';
		_html += '<a href="#" id="cctv-overlay-closer" class="cctv-overlay-closer"></a>';
		_html += '<div id="cctv-overlay-content"></div>';
		_html += '</div>';
		$(_html).appendTo(map.getTargetElement());
		//
		var overlay = new ol.Overlay(({
			element : $('#'+parentView).find('#cctv-overlay')[0],
			offset : [ 0, -20 ],
			autoPan : true,
			autoPanAnimation : {
				duration : 250
			}
		}));

		map.addOverlay(overlay);
		// closer는 상세 목록에만 보여짐.
		$('#'+parentView).find('#cctv-overlay-closer')[0].onclick = function() {
			_self.closeMapOverlay();
		}
		return overlay;
	}

	// CCTV mapOverlay 창을 닫는다.
	this.closeMapOverlay = function() {
		if (mapOverlay.getPosition() === undefined) {
			return;
		}
		mapOverlay.setPosition(undefined);
		mapOverlay.set('gid', undefined); // 동일 overlay 체크를 위한 gid값 추가
		mapOverlay.set('type', undefined);
		$('#'+parentView).find('#cctv-overlay-closer').blur();
		// mouseStatus = MODE.move;
		map.getTargetElement().style.cursor = '';
	};

	// 겹침표시 symbol에 마우스 overlay되면 심볼목록을 보여준다.
	function _showMapOverlay(_event) {
		var _lyrName = cctvLayer.get('name');
		// mouse가 위치한 곳에 feature가 있는지 체크
		var _feature = map.forEachFeatureAtPixel(_event.pixel, function(feature) {
			if(feature.get("isIgnore")){
				return null;
			}else{
				return feature;
			}
		}, {
			layerFilter : function(_layer) {
				return _layer.get('name') == _lyrName;
			}
		});
		if (!_feature) { // _feature 가 없다.
			map.getTargetElement().style.cursor = '';
			_self.closeMapOverlay(); // 보이는 overlay닫는다.
			return;
		}

		var _gid = _feature.get('cctvList')[0]['gid']
		if (mapOverlay.getPosition() && mapOverlay.get('gid') == _gid) {
			return; // 이미 같은 _gid의 overlay가 보이면 리턴
		}
		// feature 선택 마우스 모양 설정
		if(_feature.getGeometry() instanceof ol.geom.Point){
			map.getTargetElement().style.cursor = 'pointer';
		}

		// 동일위치 CCTV 처리된 목록을 보여준다.
		var _cctvSize = _feature.get('cctvSize');
		if (_cctvSize > 1) {
			var _point = _feature.getGeometry().getCoordinates();
			var _cctvList = _feature.get('cctvList');
			var _minSize = ((_cctvSize > 5) ? 5 : _cctvSize) * 36;

			$('#'+parentView).find('#cctv-overlay').css('min-width', _minSize + 'px');

			// jwr 마우스 오버했을때 보이는 아이콘들
			var _html = '<div>';
			//var _url = ctxPath + "/res/sym/cctv/";
			for (x = 0; x < _cctvSize; x++) {
				var gid = _cctvList[x]['gid'];
				var gbnCd = _cctvList[x]['gbnCd'];
				_html += '<img class="cctv-overlay-content-img" src="';

				// 만약 현재 play중이면 play중 symbol로 변경해야 함.
				/*var imgNm = _self.SYM_ICON[gbnCd]['N'];

				if (PLAY_LIST[gid]) {
					imgNm = _self.SYM_ICON[gbnCd]['P'];
				}*/
				//var _symImage = _url + imgNm;
				//_html += _symImage + '" alt="" /> ';

				//TODO 플레이 중인 아이콘 표시되게 처리해야 함.
				var _symImage = ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[gbnCd];
				/*src : ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[symId]*/
				_html += _symImage + '" alt="" /> ';
			}
			_html += '</div>';

			$('#'+parentView).find('#cctv-overlay-content').html(_html);


			//마우스 오버 시 전부 다 동영상으로 표시해야하므로 주석처리
//			$('#'+parentView).find('#cctv-overlay-content').find(".cctv-overlay-content-img").each(function(){
//				var targetPosi = $(this).position();
//				var $stat = $('<img class="stat" src="/xeus/res/sym/cctv/play.png" style="position:absolute; z-index:2;">');
//
//				$($stat).insertAfter($(this));
//				$stat.css('left', (targetPosi['left'])+'px');
//				$stat.css('top', (targetPosi['top'])+'px');
//			});





			$('#'+parentView).find('#cctv-overlay-closer').css('display', 'none');
			mapOverlay.setPosition(_point);
			mapOverlay.set('gid', _gid); // 동일 overlay 체크를 위한 gid값 추가
			// overlay 타입을 설정. cf : cctv_detail_list
			mapOverlay.set('type', 'symbol_list');
		}
	}

	// _txt size 가 _size보가 크면 말줄임표(...)처리하여 반환한다.
	function ellipseTxt(_txt, _size) {
		var _fontFamily = $("body").css("font-family");
		var _txtLen = window.getWidthOfText(_txt, _fontFamily, '13px');

		// Popup Dialog 가로 사이즈 추정
		var _tmpTxt = _txt;
		if (_txtLen > _size) {
			for (_x = 1; _x <= _txt.length; _x++) {
				var _str = _txt.substring(0, _x);
				if (window.getWidthOfText(_str, _fontFamily, '13px') > (_size - 40)) {
					_tmpTxt = _str + '...';
					break;
				}
			}
		}
		return _tmpTxt;
	}

	// 동일위치 cctv를 선택했을때 cctv세부 목록을 보여준다.
	// jwr 지도에서 심볼을 클릭했을때
	this._showMapOverlayDetailList = function(_event) {
		map.getTargetElement().style.cursor = '';
		_self.closeMapOverlay(); // 이전에 보이는 overlay닫는다.

		var _lyrName = cctvLayer.get('name');
		var _feature = map.forEachFeatureAtPixel(_event.pixel, function(feature) {
			if(feature.get("isIgnore")){
				return false;
			}else{
				return feature;
			}
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
		//var _url = ctxPath + "/res/sym/cctv/";
		// 그룹CCTV일경우 동일위치 타이틀을 보여준다.
		if (_cctvSize > 1) { // jwr CCTV크기가 1개보다 클때 전체영상관련 메뉴 추가
			var _symCode = _feature.get("symCode");
			for (var x = 0; x < _cctvSize; x++) {
				var gbnCd = _cctvList[x]['gbnCd'];
				_cctvList[x]['point'] = _point;
			}

			var _json = encodeURIComponent(JSON.stringify(_cctvList));
			// jwr 각 CCTV마다 있는 geojson을  가져옴??
			_html += '<div class="cctv-overlay-content-item" style="background-color:#2B5E93;">';

			// jwr 심볼을 눌렀을때 전체 보기 - viewVideo
			_html += '<div class="cctv-overlay-content-img-div">'
			_html += '<a href="#" onClick="xeusCCTV.viewVideo(\'' + _json + '\');" >';

			//var imgNm = _self.SYM_ICON[_symCode]['N'];

			var _symImage = ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[gbnCd];

			_html += '<img class="cctv-overlay-content-img" src="' + _symImage + '"/> ';
			_html += '</a>';
			_html += '</div>'
			// cctv전체 상세정보 보기 메뉴 추가
			_html += '<div class="cctv-overlay-content-txt-div">';
			//_html += '<a href="#" onClick="xeusCCTV.viewInformation(\'' + _json + '\');" ';
			//_html += ' style="text-decoration:none;" >';
			//_html += '<span class="cctv-overlay-content-txt">동일위치CCTV (' + _cctvSize + ')</span>';
			//_html += '</a>';
			_html += '</div>';

			// empty div....
			_html += '<div class="cctv-overlay-content-img-div">';
			_html += '<span class="cctv-overlay-content-txt" style="width:34px;"></span>';
			_html += '</div>';
			_html += '</div>';
		}

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
			var stateCd = _cctvList[x]['stateCd'];

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

			//TODO 플레이중일 때의 아이콘은 추후에 적용해야 함.
			var _symImage;

			//심볼 클릭 시 현재 재생(PLAY) CCTV는 재생 심볼로 표시
			if (PLAY_LIST[gid] || NET_LIST[gid]) {
				_symImage='/xeus/res/sym/cctv/play.png';
			}else{
				_symImage= ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[gbnCd];
			}


			/*var imgNm = _self.SYM_ICON[gbnCd]['N'];
			if (PLAY_LIST[gid] || NET_LIST[gid]) {
				imgNm = _self.SYM_ICON[gbnCd]['P'];
			}
			var _symImage = _url +imgNm;*/

			//180405 투망모니터링 추가
			var _netUrl = ctxPath + "/res/img/pop_play";
			var _infoUrl = ctxPath + "/res/img/icon_info_normal";

			_html += 'class="cctv-overlay-content-img" src="' + _symImage + '"/> ';
			_html += '</a>';
			_html += '</div>'
			// cctv상세정보 보기 메뉴 추가
			_html += '<div class="cctv-overlay-content-txt-div">';
			//_html += '<a href="#" onClick="xeusCCTV.viewInformation(\'' + _json + '\');" ';
			//_html += ' style="text-decoration:none;" >';
			_html += '<a href="#" onClick="xeusCCTV.viewCctvInfo(\'' + _json + '\');" ';
			_html += ' style="text-decoration:none; cursor:pointer"  >';
			_html += '<span class="cctv-overlay-content-txt">' + cctvNm + '</span>';
			_html += '</a>';
			//_html += '</a>';
			_html += '</div>';

			/*
			 * 180411 이은규
			 * 속성정보 버튼 추가
			 */
	/*		_html += '<div class="cctv-overlay-content-img-div">';
			_html += '<a href="#" onClick="xeusCCTV.viewCctvInfo(\'' + _json + '\');" ';
			_html += ' style="text-decoration:none;" >';
			_html += '<img class="cctv-overlay-content-img" src="' + _infoUrl + '.png" style="width: 24px; height: 24px;"/> ';
			_html += '</a>';
			_html += '</div>';*/

			// 투망분석 메뉴 추가
			if(parentView=='eventView'){
				_html += '<div class="cctv-overlay-content-img-div">';
				//_html += '<a href="#" onClick="xeusCCTV.viewNetWatch(\'' + _json + '\');" >';
				//_html += '<img class="cctv-overlay-content-img" src="' + ctxPath + '/res/img/netwatch_btn.png"/>';
				//_html += '</a>';
				_html += '<a href="#" onClick="xeusCCTV.startNetMornitoring(\'' + _json + '\');" ';
				_html += ' style="text-decoration:none;" >';
				_html += '<img class="cctv-overlay-content-img" src="'+_netUrl+'.png" style="width:16px; height:16px;  margin-right: 7px;"/> ';
				_html += '</a>';
				_html += '</div>';
			}

			// 하나밖에 없으면 x(closer) 보이게 빈 div추가
			if (_cctvSize == 1) {
				_html += '<div class="cctv-overlay-content-img-div">';
				//_html += '<a href="#" onClick="xeusCCTV.viewNetWatch(\'' + _json + '\');" >';
				//_html += '<img class="cctv-overlay-content-img" src="' + ctxPath + '/res/img/netwatch_btn.png"/>';
				//_html += '</a>';
				_html += '<a href="#" onClick="xeusCCTV.viewNetWatch(\'' + _json + '\');" ';
				_html += ' style="text-decoration:none;" >';
				_html += '<img class="" src="'+ctxPath+'/res/img/close.png" style="width:16px; height:16px; margin-right:5px;"/> ';
				_html += '</a>';
				_html += '</div>';
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
				dragId : dragId,
				stateCd : stateCd
			};

			// console.log(dragId + " " +txt );
		}
		_html += '</div>'; // for문 cctv목록

		_html += '</div>'; // overlay 전체
		$('#'+parentView).find('#cctv-overlay-content').html(_html);
		// text사이즈에 따라서 Dialog 크기를 변경한다.
		$('#'+parentView).find('#cctv-overlay').css('min-width', _txtSize + 'px');
		$('#'+parentView).find('#cctv-overlay-closer').css('display', 'inline');
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

					var $target = $(".dropBox");
					if($('.aviWrapper').is(":visible")) $target = $(".aviWrapper").find(".dropBox");
					else if($('.carWrapper').is(":visible")) $target = $(".carWrapper").find(".dropBox");
					/* 2017-09-19 이주영 수정 */
					$target.show("puff", 200);
					for (var i = 0; i < 3; i++) {
						$target.animate({
							"color" : "white"
						}, 150);
						$target.animate({
							"color" : "gray"
						}, 150);
					}
					$target.animate({
						"color" : "white"
					}, 150);
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
				"point" : draggableIds[x].point,
				"stateCd" : draggableIds[x].stateCd
			});
		}
	}

	/* 2018-02-05 이주영 - CCTV가 아닌, 일반 Vector 포인트의 팝업 이벤트 입니다. */
	this._showMapOverlayDetailVector = function(_event, targetField) {

		map.getTargetElement().style.cursor = '';
		_self.closeMapOverlay(); // 이전에 보이는 overlay닫는다.

		var _feature = map.forEachFeatureAtPixel(_event.pixel, function(feature){
			return feature;
		});

		/*var _feature = map.forEachFeatureAtPixel(_event.pixel, function(feature) {
			if(feature.get("isIgnore")){
				return false;
			}else{
				return feature;
			}
		}, {
			layerFilter : function(_layer) {
				return _layer.get('name') == "기반시설";
			}
		});*/

		if (!_feature) return;

		var _point = _feature.getGeometry().getCoordinates();
//		if(xy != null){
//			if(Array.isArray(xy)){
//				_point = xy;
//			}
//		}
		var _prop = {
			"point" : _point
		};
		var _featureProp = _feature.getProperties();
		for(var key in _featureProp){
			if(key == "_gid"){
				_prop["gid"] = _featureProp[key];
			}else{
				_prop[humps.camelize(key)] = _featureProp[key];
			}
		}
		var _txtSize = 200;

		var _html = '<div>';
		var _imgPath = _feature.get("img_path");

		var _fontFamily = $("body").css("font-family");
//		var _name = _prop[humps.camelize(_prop['targetField'])];
		var _name = _prop[humps.camelize(targetField)];
		var _content = _name;
//		if(_cont) _content = _cont;
		var _txtLen = window.getWidthOfText(_name, _fontFamily, '13px');
		_html += '<div>';

		//if ((_txtLen) + 100 > _txtSize) {
	//		_txtSize = (_txtLen) + 100;
	//	}
		_html += '<div class="cctv-overlay-content-item">';
		_html += '<div class="cctv-overlay-content-img-div" >';
		if(!_common.utils.isNullAndEmpty(_imgPath)){
			_html += '<img id=\"' + "vector_drag_" + _prop["gid"] + '\" class="cctv-overlay-content-img" src="' + _imgPath + '"/> ';
		}
		_html += '</div>'
		_html += '<div class="cctv-overlay-content-txt-div">';
		_html += '<span class="cctv-overlay-content-txt">';
		if(!_common.utils.isNullAndEmpty(_imgPath)){
			_html += '<div title="'+ _name +'"style="width: 150px; text-overflow: ellipsis; white-space: nowrap;">'+ _content + '</div>';
		}else{
			_html += '<div title="'+ _name +'"style="width: 140px; padding-left: 10px; text-overflow: ellipsis; white-space: nowrap;">'+ _content + '</div>';
		}
		_html += '</span>';
//		_html += '<span class="cctv-overlay-content-txt">' + _name + '</span>';
		_html += '</div>';
//		_html += '<div class="cctv-overlay-content-img-div">';
//		_html += '<a href="#"';
//		_html += ' style="text-decoration:none;" >';
//		_html += '<img class="cctv-overlay-content-img" src="/xeus/res/img/icon_info_normal.png" style="width: 24px; height: 24px;"/> ';
//		_html += '</a>';
//		_html += '</div>';

		// 하나밖에 없으면 x(closer) 보이게 빈 div추가
		_html += '<div style="width: 26px;border: 0;"></div>';
		_html += '</div>'; // cctv-overlay-content-item
		_html += '</div>'; // for문 cctv목록
		_html += '</div>'; // overlay 전체
		$('#'+parentView).find('#cctv-overlay-content').html(_html);

//		$('#'+parentView).find('#cctv-overlay-content').find(".cctv-overlay-content-txt").click(function(){
//			xeusCCTV.viewVectorInfo(_featureProp);
//		});
		// text사이즈에 따라서 Dialog 크기를 변경한다.
		//$('#'+parentView).find('#cctv-overlay').css('min-width', _txtSize + 'px');
		$('#'+parentView).find('#cctv-overlay-closer').css('display', 'inline');
		mapOverlay.setPosition(_point);
		// dialog type cf. symbol_list
		mapOverlay.set('type', 'cctv_detail_list');

		// //////////////////////////////////////////
		// draggable 설정
		// //////////////////////////////////////////
		// videoDialog, VideoGridPane에 드래그 하여 play 할수 있도록 설정
		$("#vector_drag_" + _prop["gid"]).draggable({
			appendTo : "body",
			helper : "clone",
			start : function(event, ui) {
				$(ui.helper).addClass("ui-draggable-helper");
				$(this).draggable("option", "revert", true);

				/* 2017-09-19 이주영 수정 */
				$(".dropBox").show("puff", 200);
				for (var i = 0; i < 3; i++) {
					$(".dropBox").animate({
						"color" : "white"
					}, 150);
					$(".dropBox").animate({
						"color" : "gray"
					}, 150);
				}
				$(".dropBox").animate({
					"color" : "white"
				}, 150);
			}
		}).data(_prop);

	}

	// 화면에 보여지는 Video Player Dialog 갯수를 반환한다.
	// _player_dlg_로 구분됨
	this.getVideoDialogCount = function() {
		var _keys = Object.keys(PLAY_LIST);
		if (_keys.length == 0) {
			return 0;
		}
		var _count = 0
		for (x = 0; x < _keys.length; x++) {
			var _player_dlg_id = PLAY_LIST[_keys[x]].id;
			// console.log(_player_dlg_id);
			// ^_player_dlg로 시작하는 문자열
			if (_player_dlg_id != null && _player_dlg_id.match("^_player_dlg_")) {
				_count++;
			}
		}
		return _count;
	};

	// play_list목록을 얻는다.
	// _name : _player_grid_, _player_dlg_로 구분됨
	this.getPlayListByType = function(_name) {
		var _list = [];
		var _keys = Object.keys(PLAY_LIST);
		if (_keys.length > 0) {
			for (x = 0; x < _keys.length; x++) {
				var _gid = _keys[x];
				var _player_id = PLAY_LIST[_gid].id;
				if (_player_id != null && _player_id.match("^" + _name)) {
					_list[_gid] = PLAY_LIST[_gid];
				}
			}
		}
		return _list;
	};

	// CCTV play list에서 gid에 해당하는 목록을 제거한다.
	this.removePlayList = function(_gid) {
		//jsMpeg player destroy
//		console.log(PLAY_LIST);
		if(PLAY_LIST[_gid].jsmpeg != undefined ){
			try{
				PLAY_LIST[_gid].jsmpeg.destroy();
				xeusSymbol.removeFeature(_gid, "isPlay");
				xeusSymbol.removeFeature(_gid, "isAngle");
			}catch(e){
				// IE 에서는 jsmpeg > destroy 메소드를 찾지 못하여, t-c 블록씌움. ffmpeg 프로세스는 소멸 확인.
			}
		}
		var _symid = PLAY_LIST[_gid].sym;
		PLAY_LIST[_gid] = undefined;
		delete PLAY_LIST[_gid];
		_setOverlayStyle(_gid, _symid);
//		console.log("aaf remove..............." + _gid);
//		console.log(PLAY_LIST);
	};

	// 투망모니터링 리스트에서 gid에 해당하는 목록을 제거한다.
	this.removeNetList = function(_gid) {
		//jsMpeg player destroy
		console.log(NET_LIST);
		if(NET_LIST[_gid].jsmpeg != undefined ){
			try{
				NET_LIST[_gid].jsmpeg.destroy();
			}catch(e){
				// IE 에서는 jsmpeg > destroy 메소드를 찾지 못하여, t-c 블록씌움. ffmpeg 프로세스는 소멸 확인.
			}
		}
		var _symid = NET_LIST[_gid].sym;
		NET_LIST[_gid] = undefined;
		delete NET_LIST[_gid];
		_setOverlayStyle(_gid, _symid);
		console.log("net remove..............." + _gid);
		console.log(NET_LIST);
	};

	// PLAY_LIST에 play중인 cctv정보를 등록한다.
	// _cctv : cctv 개체
	// _playerId : _player_grid_ 또는 _player_dlg 로 시작하는 player id
	this.addPlayList = function(_playerId, _cctv, _jsmpeg) {
		PLAY_LIST[_cctv.gid] = {
			id : _playerId,  //dialog id
			sym : _cctv.gbnCd, //심볼 구분
			cctv : _cctv,      //cctv정보
			jsmpeg: _jsmpeg //jsMpeg player객체
		};
//		console.log("add player... list ");
		_setOverlayStyle(_cctv.gid, _cctv.gbnCd + 'P');
//		console.log(PLAY_LIST);
	}

	this.addNetList = function(_playerId, _cctv, _jsmpeg) {
		NET_LIST[_cctv.gid] = {
			id : _playerId,  //dialog id
			sym : _cctv.gbnCd, //심볼 구분
			cctv : _cctv,      //cctv정보
			jsmpeg: _jsmpeg //jsMpeg player객체
		};
		console.log("add net... list ");
		_setOverlayStyle(_cctv.gid, _cctv.gbnCd + 'P');
		console.log(NET_LIST);
	};

	// PLAY_LIST에 gid가 있는지 확인한다.
	this.existPlayList = function(_gid) {
		if (PLAY_LIST[_gid] != undefined) {
			return true;
		}
		return false;
	}

	// PLAY_LIST에 gid가 있는지 확인한다.
	this.existNetList = function(_gid) {
		if (NET_LIST[_gid] != undefined) {
			return true;
		}
		return false;
	}

	// mapOverlay 목록에 있는 symbol 모양을 설정한다.
	function _setOverlayStyle(_gid, _symid) {
		$('#cctv_drag_' + _gid).attr('src', ctxPath + '/res/sym/cctv/' + _symid + '.png');
	}

	//mepgPlayer를 생성한다.
	this.getMpegPlayer = function(_url, _canvas, _mediaId, _size, _rtspUrl){
		/*var player = new JSMpeg.Player(_url,{
			autoplay: false,
			loop : false,
			canvas : _canvas,
			disableGl : true,
			cctvMgrNo : _mediaId,
			size : _size,
			userId : userId
		});*/

		//!!
		var rtspUrl = "";
		if(_rtspUrl != undefined && _rtspUrl != null && _rtspUrl != ""){
			rtspUrl = encodeURIComponent(_rtspUrl);
		}

//		var _option = {
//            playerId : _canvas+'_div',
//            url : _url,
//            cctvMgrNo : _mediaId,
//            userId : userId,
//            evtType : '',
//            timestamp :'',// '20200219142030',
//            speed : '',
//            rtspUrl : rtspUrl,
////            size : '640x480',
//            codec : 'mjpeg',
////            codec : 'h264',
//            debug : false
//        };

		var _option = {
				url : _url,
		        playerId : _canvas+'_div',
		        cctvMgrNo: _mediaId,
//		        cctvNm : cctvNm,
	        	userId: "geomex",
				evtType : "",
	            timestamp : "",
	            speed : "",
				size : "",
	            rtspUrl : rtspUrl,
	            codec : 'h264',
	            debug : true
	        };
		console.log(_option);
		var player = new XeusGate.Player(_option);







		return player;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////
	// _player_grid_ 로 시작하는 player를 닫는다.
	this.closeVideoDialog = function(_dlgid) {
//		console.log(">>> closeVideoDialog... " + _dlgid);
		$('#' + _dlgid).droppable('destroy');
		var _gid = $('#' + _dlgid).dialog('option', 'gid');
		this.removePlayList(_gid);
		$('#' + _dlgid).remove(); // _player_grid_xxx dialog를 제거한다.
		xeusSymbol.removeGuideFeature(_dlgid);
	}

	// CCTV 비디오 Dialog를 화면에 생성한다.
	// _cctv : cctv정보
	// _cnt : videodialog 순서번호, 2017.04.27 의미없음
	this.createVideoDialog = function(_cctv, _cnt, _isPreview) {
		var cnt = _self.getVideoDialogCount();
		if(cnt >= VIDEO_POPUP_PLAYER_LIMIT){
			var msg = "더이상 팝업 플레이어를 생성할 수 없습니다<br>"
				msg+= "허용 팝업 플레이어 수 : " + VIDEO_POPUP_PLAYER_LIMIT;
    		xeusLayout.showShortcutMessage(msg, 400);
    		return;
		}

		var gid = _cctv['gid'];
		var gbnCd = _cctv['gbnCd'];
		var cctvNm = _cctv['cctvNm'];
		var point = _cctv['point'];

		if (this.existPlayList(gid)) {
			// 이미 play 중인 cctv이면 return; createVideoDialog 호출시 체크함.
			return;
		}

		// dialog 타이틀 html을 만든다.
		var _title = this.getVideoDialogTitle(point[0], point[1], gbnCd, cctvNm, 0);
		// 마우스 클릭 위치...
		var _pos = mapService.getMap().getPixelFromCoordinate(point);
		var _width = parseInt($('#'+parentView).find('#' + virtualMapBoundary).outerWidth());
		var _height = parseInt($('#'+parentView).find('#' + virtualMapBoundary).outerHeight());

		var _cols = parseInt(_width /(this.SIZE['width'] + 4 + 2)); //가로 갯수
        var _rows = parseInt(_height/(this.SIZE['height'] + 4)); //세로갯수

		// dialog 위치 가로 offset
	 	var _left = ((_cnt % _cols) * (parseInt(this.SIZE['width']) + 4)) + 2;

	 	var _rowIdx = Math.floor(_cnt /_cols); //행 index,,,
	 	if(_rowIdx > (_rows-1))_rowIdx = 0; //세로로 더 추가 할 수 없을때 top에 위치..
		var _top = (_rowIdx * (parseInt(this.SIZE['height']) + 40)) + 34;

		// ///////////////////////////////////
		var _player_dlg_id = '_player_dlg_' + new Date().getTime();
		//var div = $('<div id="' + _player_dlg_id + '"></div>').appendTo('#' + virtualMapBoundary);
		$('#'+parentView).find('#' + virtualMapBoundary).append('<div id="' + _player_dlg_id + '" class="dialogDrag"></div>');



		var _position = {
			my : "left top",
			at : "left+" + _left + " top+" + _top,
			//of : '#' + virtualMapBoundary
			of : $('#'+parentView).find('#' + virtualMapBoundary)
		}

		var _playerWidth = this.SIZE['width'];
		var _playerHeight = this.SIZE['height'];

		_self.createPlayerDialog(_player_dlg_id, _title, _cctv, _position, _playerWidth, _playerHeight, this.SIZE['mode1'], _isPreview);

		// dialog drag영역을 제한함. 2017.02.28 검토. 필요.
		// $('#' + _player_dlg_id).parent().draggable("option", "containment",
		// '#' + virtualMapBoundary);
		// //////////////////////////////////////////
		// droppable 설정
		// /////////////////////////////////////////
		// cctv세부목록에서 심볼을 videodialog 에 drop 처리.
		$('#' + _player_dlg_id).droppable({
			drop : function(event, ui) {
				var dragid = ui.draggable.attr("id");

				var _drp_gid = ui.draggable.data("gid");
				var _drp_mgrNo = ui.draggable.data("mgrNo");
				var _drp_cctvNm = ui.draggable.data("cctvNm");
				var _drp_deviceId = ui.draggable.data("deviceId");
				var _drp_channelNo = ui.draggable.data("channelNo");
				var _drp_gbnCd = ui.draggable.data("gbnCd");
				var _drp_gbnTxt = ui.draggable.data("gbnTxt");
				var _drp_angle = ui.draggable.data("angle");
				var _drp_point = ui.draggable.data("point");

				// cctv_drag_로 시작하는 drop이 아니면 리턴 var dragId = 'cctv_drag_' +
				if (!dragid || !dragid.match("^cctv_drag_")) {
					return;
				}
				// 현재 dialog gid
				var _oldGid = $('#' + event.target.id).dialog('option', 'gid');
				// 같은 cctv이거나 이미 플레이 중이면 리턴
				if ((_oldGid == _drp_gid) || _self.existPlayList(_drp_gid)) {
					return;
				}

				// drag 취소(revert) : false 즉 drop 완료 설정
				ui.draggable.draggable("option", "revert", false);

				// videoDialog 타이틀
				var _title = _self.getVideoDialogTitle(_drp_point[0], _drp_point[1], _drp_gbnCd, _drp_cctvNm, 0);

				var _dlg = $('#' + event.target.id).dialog();
				_dlg.dialog().dialog('option', 'title', _title);
				_dlg.dialog().dialog('option', 'gid', _drp_gid);
				_dlg.dialog().dialog('option', 'mgrNo', _drp_mgrNo);
				_dlg.dialog().dialog('option', 'cctvNm', _drp_cctvNm);
				_dlg.dialog().dialog('option', 'deviceId', _drp_deviceId);
				_dlg.dialog().dialog('option', 'channelNo', _drp_channelNo);
				_dlg.dialog().dialog('option', 'gbnCd', _drp_gbnCd);
				_dlg.dialog().dialog('option', 'gbnTxt', _drp_gbnTxt);
				_dlg.dialog().dialog('option', 'angle', _drp_angle);
				_dlg.dialog().dialog('option', 'basePoint', _drp_point);

				_self.removePlayList(_oldGid); //이전것 제거...
				//
				_dlg.dialog().html('');
				var _canvasId = "video-canvas-" +_drp_gid;
				_dlg.dialog().html("<div id='" + _canvasId +"_div'></div>");
				var _size =  _self.SIZE['width'] + "x" + _self.SIZE['height'];

				var _jsmpeg = _self.getMpegPlayer(VIDEO_WEBSOCKET_URL, _canvasId, _drp_mgrNo, _size);
				_self.addPlayList(event.target.id, {
					gid : _drp_gid,
					mgrNo : _drp_mgrNo,
					cctvNm : _drp_cctvNm,
					deviceId : _drp_deviceId,
					channelNo : _drp_channelNo,
					gbnCd : _drp_gbnCd,
					gbnTxt : _drp_gbnTxt,
					angle : _drp_angle,
					point : _drp_point
				},_jsmpeg);

				/*
				 * 	180403 이은규
				 *	cctv 재생 중 팝업으로 드래그했을 때 재생 아이콘으로 변경 안되는 것 수정.
				 */
				xeusCCTV.cctv.reload();
			}
		});
	};
	/**
	 * 180615 이은규
	 * 다이얼로그 생성 로직을 분리
	 */
	// dialog를 생성한다.
	this.createPlayerDialog = function(_player_dlg_id, _title, _cctv, _position, _playerWidth, _playerHeight, mode, _isPreview){
		var gid = _cctv['gid'];
		var mgrNo = _cctv['mgrNo'];
		var cctvNm = _cctv['cctvNm'];
		var deviceId = _cctv['deviceId'];
		var channelNo = _cctv['channelNo'];
		var gbnCd = _cctv['gbnCd'];
		var gbnTxt = _cctv['gbnTxt'];
		var angle = _cctv['angle'];
		var point = _cctv['point'];

		var _player = $('#' + _player_dlg_id).dialog({
			appendTo : '#'+parentView,
			gid : gid,
			mgrNo : mgrNo,
			cctvNm : cctvNm,
			deviceId : deviceId,
			channelNo : channelNo,
			gbnCd : gbnCd,
			gbnTxt : gbnTxt,
			angle : angle,
			basePoint : point, // 안내서 시작 위치.
			title : _title,
			width : (_playerWidth * mode),
			height : (_playerHeight * mode) + 38,
			// 2017.12.05 by khkim, JSMpeg.Player는 미디어 소스와 동일 사이즈로 플레이 됨
			resizable : true,
			position : _position,
			autoOpen : false,
			hide : {
				effect : "fade",
				duration : 100
			},
			resizeStop : function(event,ui){ // 리사이징이 끝날때 호출되는 콜백함수.
					_self.removePlayList(gid);
					_self.createPlayerDialog(_player_dlg_id, _title, _cctv, _position, Math.round(_player.width()), Math.round(_player.height()), _self.SIZE['mode1'],_isPreview);
			},
			drag : function(event, ui) {
				xeusSymbol.addGuideline(xeusLayout.mapService.getMap(), _player_dlg_id);
			},
			open : function(event, ui) {
				xeusSymbol.addGuideline(xeusLayout.mapService.getMap(), _player_dlg_id);

				$('#' + event.target.id).css('overflow', 'hidden');
				// Dialog 타이틀 버튼을 2개(grid보기, 닫기)로 생성한다.
				//180503 이은규 - 스틸컷 버튼을 생성한다.
				//180615 이은규 - 화면 확대 재생 버튼을 추가한다.
				var _str = '';

				/*_str += '<button type="button" id="' + _player_dlg_id + '_btn_share" style="right:155px !important" title="영상 공유"';
				_str += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin tooltip">';
				_str += '<span class="ui-button-icon ui-icon ui-icon-link"></span>';
				_str += '</button>';*/

//				_str += '<span id="' + _player_dlg_id + '_btn_mode1" class="active-player play-mode-control ui-button ui-corner-all ui-widget tooltip" style="right:128px !important" title="재생 크기 원본">1</span>';
//				_str += '<span id="' + _player_dlg_id + '_btn_mode2" class="play-mode-control ui-button ui-corner-all ui-widget tooltip" style="right:103px !important" title="재생 크기 X1.5배">1.5</span>';
//				_str += '<span id="' + _player_dlg_id + '_btn_mode3" class="play-mode-control ui-button ui-corner-all ui-widget tooltip" style="right:78px !important" title="재생 크기 X2배">2</span>';

//				rtsp이므로 ptz 제어 사용 x

//				_str += '<button type="button" id="' + _player_dlg_id + '_btn_ptz" style="right:78px !important" title="PTZ 제어"';
//				_str += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin ptz_' + mgrNo + ' tooltip">';
//				_str += '<span class="ui-button-icon ui-icon ui-icon-arrow-4"></span>';
//				_str += '</button>';
/*
				_str += '<button type="button" id="' + _player_dlg_id + '_btn_cprtsp" style="right:7.00em !important" title="RTSP URL 저장"';
				_str += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin tooltip">';
				_str += '<span class="ui-button-icon ui-icon ui-icon-clipboard"></span>';
				_str += '</button>';
*/

//				rtsp이므로 프리셋 조절 사용 x

//				_str += '<button type="button" id="' + _player_dlg_id + '_btn_preset" style="right:103px !important" title="프리셋 조절"';
//				_str += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin tooltip">';
//				_str += '<span class="ui-button-icon ui-icon ui-icon-person"></span>';
//				_str += '</button>';

//				_str += '<button type="button" id="' + _player_dlg_id + '_btn_stillcut" style="right:54px !important" title="스틸컷 저장"';
//				_str += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin tooltip">';
//				_str += '<span class="ui-button-icon ui-icon ui-icon-image"></span>';
//				_str += '</button>';

				_str += '<button type="button" id="' + _player_dlg_id + '_btn_undock" style="right:29px !important"  title="작은 위젯으로 재생"';
				_str += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin tooltip">';
				_str += '<span class="ui-button-icon ui-icon ui-icon-newwin"></span>';
				_str += '</button>';

				_str += '<button type="button" id="' + _player_dlg_id + '_btn_close" title="영상재생 종료"';
				_str += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-close close_' + mgrNo + ' tooltip">';
				_str += '<span class="ui-button-icon ui-icon ui-icon-closethick"></span>';
				_str += '</button>';

				$('#' + event.target.id).parent().children().children('button').replaceWith(_str);
				// 혹시 모를 click 이벤트 unbind
				$('#' + event.target.id + '_btn_mode1').off('click');
				$('#' + event.target.id + '_btn_mode2').off('click');
				$('#' + event.target.id + '_btn_mode3').off('click');
				$('#' + event.target.id + '_btn_close').off('click');
				$('#' + event.target.id + '_btn_undock').off('click');
				$('#' + event.target.id + '_btn_stillcut').off('click');
				$('#' + event.target.id + '_btn_preset').off('click');
				$('#' + event.target.id + '_btn_cprtsp').off('click');
				$('#' + event.target.id + '_btn_ptz').off('click');
				$('#' + event.target.id + '_btn_share').off('click');

				// 영상공유
				$('#' + event.target.id + '_btn_share').click(function(e) {
					xeusCCTV.shareCCTV(_cctv);
				});

				// mode1 event
				$('#' + event.target.id + '_btn_mode1').click(function(e) {
					if(!$(this).hasClass('active-player')){
						//alert($(this).attr("id"));
						$thisBtn = $(this);
						$('.play-mode-control').each(function(){
							$(this).removeClass('active-player');
						});
						$(this).addClass('active-player');

						//droppable을 굳이 없앨 필요 없음.
						//$('#' + _dlgid).droppable('destroy');

						_self.removePlayList(gid);
						_self.createPlayerDialog(_player_dlg_id, _title, _cctv, _position, _playerWidth, _playerHeight, _self.SIZE['mode1']);
						//createPlayerDialog에서 setPlayerFunction을 호출하므로 두번 호출할 필요가 없음.
						//_self.setPlayerFunction(_player, _player_dlg_id, _cctv, gid, mgrNo, _playerWidth, _playerHeight, _self.SIZE['mode1']);
					}
				});

				// mode2 event
				$('#' + event.target.id + '_btn_mode2').click(function(e) {
					if(!$(this).hasClass('active-player')){
						//alert($(this).attr("id"));
						$thisBtn = $(this);
						$('.play-mode-control').each(function(){
							$(this).removeClass('active-player');
						});
						$(this).addClass('active-player');

						//droppable을 굳이 없앨 필요 없음.
						//$('#' + _dlgid).droppable('destroy');

						_self.removePlayList(gid);
						_self.createPlayerDialog(_player_dlg_id, _title, _cctv, _position, _playerWidth, _playerHeight, _self.SIZE['mode2']);
						//_self.setPlayerFunction(_player, _player_dlg_id, _cctv, gid, mgrNo, _playerWidth, _playerHeight, _self.SIZE['mode2']);
					}
				});

				// mode3 event
				$('#' + event.target.id + '_btn_mode3').click(function(e) {
					if(!$(this).hasClass('active-player')){
						//alert($(this).attr("id"));
						$thisBtn = $(this);
						$('.play-mode-control').each(function(){
							$(this).removeClass('active-player');
						});
						$(this).addClass('active-player');

						//droppable을 굳이 없앨 필요 없음.
						//$('#' + _dlgid).droppable('destroy');

						_self.removePlayList(gid);
						_self.createPlayerDialog(_player_dlg_id, _title, _cctv, _position, _playerWidth, _playerHeight, _self.SIZE['mode3']);
						//_self.setPlayerFunction(_player, _player_dlg_id, _cctv, gid, mgrNo, _playerWidth, _playerHeight, _self.SIZE['mode3']);
					}
				});

				var preview = null;
				var timeout = null;

				if(_isPreview){
					preview = setTimeout(function(){
						$('#' + event.target.id + '_btn_close').click();
					}, 1000 * 180);
				}

				// close event
				$('#' + event.target.id + '_btn_close').click(function(e) {
					// TODO 닫기전, Lock 해제 후 서버에 Lock 전송 필요.
					var lock = $(this).attr("lock");
					if(_common.utils.isNullAndEmpty(lock)){
						var lonlat = Spatial.convertProjection(point, "EPSG:5186", "EPSG:4326");
						var _Json = xeusJsonParser.getTemplate();

						_Json.statEvetTypCd = "CCTVLOCK";
						_Json.statEvetNm = $("#" + _player_dlg_id).attr("statEvetNm");

						_Json.statEvetCntn = "사용자(" + userId + ")가 " + cctvNm + "(고유번호 : " + gid + ") CCTV 제어를 종료하였습니다.";
						_Json["x"] = String(lonlat[0]);
						_Json["y"] = String(lonlat[1]);
						_Json["tmx"] = String(point[0]);
						_Json["tmy"] = String(point[1]);
						_Json.statEvetOutbDtm = new Date().getYMDHMS();
						_Json.statEvetClrDtm = new Date().getYMDHMS();
						_Json.statEvetActnMn = userId;
						_Json.statEvetActnCntn = "사용자(" + userId + ")가 " + cctvNm + "(고유번호 : " + gid + ") CCTV 제어를 종료하였습니다.";
						_Json.procSt = "91";
						_Json.isTest = "N";
						_Json.uSvcOutbId = $("#" + _player_dlg_id).attr("uSvcOutbId");
						_Json.statEvetSvcTyp = "CCTVLOCK";
						_Json.outbPosNm = cctvNm + "(고유번호 : " + gid + ")";
						_Json.outbPos[0].x = String(lonlat[0]);
						_Json.outbPos[0].y = String(lonlat[1]);
						_Json.etcCntn = JSON.stringify({"gid" : gid});
						//04_22 주석처리함
//						_common.callAjax("/ws/addEvent.json", { "json" : JSON.stringify(_Json) }, function(json){
//							if(json.result){
//								xeusSymbol.removeFeature(gid, "isPreset");
//								xeusSymbol.removeFeature(gid, "isAngle");
//							}
//						});
						xeusSymbol.removeFeature(gid, "isPreset");
						xeusSymbol.removeFeature(gid, "isAngle");
					}
					_self.closeVideoDialog(event.target.id);
					_self.reload();
					clearTimeout(timeout);
					clearTimeout(preview);
				});

				// videoDialog 영상을 gridpane으로 이동한다.
				// jwr CCTV영상 Dialog에서 최소화 버튼 이벤트
				$('#' + event.target.id + '_btn_undock').click(function(e) {

					if(Object.keys(xeusCCTV.cctv.getNetList()).length > 0){
						alert('투망모니터링 중엔 기능이 제한됩니다.');
						return false;
					}
					clearTimeout(timeout);
					_self.closeVideoDialog(event.target.id);
					xeusCCTV.showVideoInGridPane(_cctv);
					_self.reload();
				});

				$('#' + event.target.id + '_btn_stillcut').click(function(e) {
					//_common.postForm.submit("/proxy/getShapshot.json", { "path" : "getShapshot", "cctvMgrNo" : mgrNo });

					var browserType = XeusGate.BrowserType();
					if(browserType == "Chrome"){
						var wrapDiv = $('#'+event.target.id);
						var imageOrVideo;

						if(wrapDiv.find('video').length > 0 ){
							imageOrVideo = wrapDiv.find('video').get(0);
							if(wrapDiv.find('video').next().css('visibility') != 'hidden'){
								console.log('video is not loading...');
								return false;
							}
						}

						if(wrapDiv.find('img').length > 0 ){
							imageOrVideo = wrapDiv.find('img').get(0);
							if(wrapDiv.find('img').next().css('visibility') != 'hidden'){
								console.log('img is not loading...');
								return false;
							}
						}




						//임시 canvas 태그 생성
						var canvas = document.createElement("canvas");
						canvas.width = imageOrVideo.videoWidth;
						canvas.height = imageOrVideo.videoHeight;
						//canvas에 img 저장
						canvas.getContext('2d').drawImage(imageOrVideo, 0, 0, imageOrVideo.videoWidth, imageOrVideo.videoHeight);

						//임시 img태그 생성
						var img = document.createElement("img");
						//canvas 데이터 저장
						img.src = canvas.toDataURL();

						//img태그 blob데이터 추출
						var imgData = atob(img.src.split(",")[1]);
						var len = imgData.length
						var buf = new ArrayBuffer(len) // 비트를 담을 버퍼를 만든다.
						var view = new Uint8Array(buf) // 버퍼를 8bit Unsigned Int로 담는다.
						var blob, i
						for (i = 0; i < len; i++) {
							view[i] = imgData.charCodeAt(i) & 0xff // 비트 마스킹을 통해 msb를 보호한다.
						}
						// Blob 객체를 image/png 타입으로 생성한다. (application/octet-stream도 가능)
						blob = new Blob([view], { type: "image/png" });

						var date = new Date().getYMDHMS_S();
						if (navigator.msSaveBlob) {
							navigator.msSaveBlob(blob, date + '_stillcut.png');
						} else {
							saveAs(blob, date + '_stillcut.png');
						}
					} else if(browserType == "Trident"){
						var playerImg = $('#'+event.target.id).find('img');

						var blobUrl = playerImg.attr("src");

						var request = new XMLHttpRequest();
						request.open('GET', blobUrl, true);
						request.responseType = 'blob';
						request.onload = function() {
						    var reader = new FileReader();
						    reader.readAsDataURL(request.response);
						    reader.onload =  function(e){
						        //console.log('DataURL:', e.target.result);
								var imgData = atob(e.target.result.split(",")[1]);
								var len = imgData.length
								var buf = new ArrayBuffer(len) // 비트를 담을 버퍼를 만든다.
								var view = new Uint8Array(buf) // 버퍼를 8bit Unsigned Int로 담는다.
								var blob, i
								for (i = 0; i < len; i++) {
									view[i] = imgData.charCodeAt(i) & 0xff // 비트 마스킹을 통해 msb를 보호한다.
								}
								// Blob 객체를 image/png 타입으로 생성한다. (application/octet-stream도 가능)
								blob = new Blob([view], { type: "image/png" });

								var date = new Date().getYMDHMS_S();
								if (navigator.msSaveBlob) {
									navigator.msSaveBlob(blob, date + '_stillcut.png');
								} else {
									saveAs(blob, date + '_stillcut.png');
								}
						    };
						};
						request.send();
					}
				});

				function presetStartOption(){
					var lonlat = Spatial.convertProjection(point, "EPSG:5186", "EPSG:4326");
					var _Json = xeusJsonParser.getTemplate();

					_Json.statEvetTypCd = "CCTVLOCK";
					_Json.statEvetNm = "Preset";
					_Json.statEvetCntn = "사용자(" + userId + ")가 " + cctvNm + "(고유번호 : " + gid + ") CCTV Preset 제어를 시작하였습니다.";
					_Json["x"] = String(lonlat[0]);
					_Json["y"] = String(lonlat[1]);
					_Json["tmx"] = String(point[0]);
					_Json["tmy"] = String(point[1]);
					_Json.statEvetOutbDtm = new Date().getYMDHMS();
					_Json.procSt = "10";
					_Json.isTest = "N";
					_Json.uSvcOutbId = "";
					_Json.statEvetSvcTyp = "CCTVLOCK";
					_Json.outbPosNm = cctvNm + "(고유번호 : " + gid + ")";
					_Json.outbPos[0].x = String(lonlat[0]);
					_Json.outbPos[0].y = String(lonlat[1]);
					_Json.etcCntn = JSON.stringify({"gid" : gid});

					return JSON.stringify(_Json);
				}
				function presetEndOption(){
					var lonlat = Spatial.convertProjection(point, "EPSG:5186", "EPSG:4326");
					var _Json = xeusJsonParser.getTemplate();

					_Json.statEvetTypCd = "CCTVLOCK";
					_Json.statEvetNm = $("#" + _player_dlg_id).attr("statEvetNm");
					_Json.statEvetCntn = "사용자(" + userId + ")가 " + cctvNm + "(고유번호 : " + gid + ") CCTV Preset 제어를 종료하였습니다.";
					_Json["x"] = String(lonlat[0]);
					_Json["y"] = String(lonlat[1]);
					_Json["tmx"] = String(point[0]);
					_Json["tmy"] = String(point[1]);
					_Json.statEvetOutbDtm = new Date().getYMDHMS();
					_Json.statEvetClrDtm = new Date().getYMDHMS();
					_Json.statEvetActnMn = userId;
					_Json.statEvetActnCntn = "사용자(" + userId + ")가 " + cctvNm + "(고유번호 : " + gid + ") CCTV Preset 제어를 종료하였습니다.";
					_Json.procSt = "91";
					_Json.isTest = "N";
					_Json.uSvcOutbId = $("#" + _player_dlg_id).attr("uSvcOutbId");
					_Json.statEvetSvcTyp = "CCTVLOCK";
					_Json.outbPosNm = cctvNm + "(고유번호 : " + gid + ")";
					_Json.outbPos[0].x = String(lonlat[0]);
					_Json.outbPos[0].y = String(lonlat[1]);
					_Json.etcCntn = JSON.stringify({"gid" : gid});

					return JSON.stringify(_Json);
				}
				$('#' + event.target.id + '_btn_preset').click(function(e) {
					$("#" + parentView).find("#" + _player_dlg_id).find("#presetWrap").toggle("blind", function(){

						if($(this).is(":visible")){
							$(this).attr("lock", "preset");

							timeout = setTimeout(function(){
								if($("#" + parentView).find("#" + _player_dlg_id).find("#presetWrap").is(":visible")){
									$('#' + event.target.id + '_btn_preset').click();
								}
							}, 60000);
							$("#" + parentView).find("#" + _player_dlg_id).find("#presetWrap").find(".gotoPreset").click(function(){
								clearTimeout(timeout);
								timeout = setTimeout(function(){
									if($("#" + parentView).find("#" + _player_dlg_id).find("#presetWrap").is(":visible")){
										$('#' + event.target.id + '_btn_preset').click();
									}
								}, 60000);
							});

							_common.callAjax("/ws/addEvent.json", { "json" : presetStartOption() }, function(json){
								if(json.result){
									$("#" + _player_dlg_id).attr({
										"uSvcOutbId" : json.uSvcOutbId,
										"statEvetNm" : json.statEvetNm
									});

									$("#" + parentView).find("#" + _player_dlg_id).find("#presetWrap").find(".whiteBtn").each(function(){
										var endPoint = [ $(this).data().dirX, $(this).data().dirY ];
										var presetNo = $(this).data().presetNo;
										xeusSymbol.addPreset(point, endPoint, gid, presetNo);
									});

									_common.callAjax("/proxy/xeusGateWay.json", { "path" : "getPTZPosition", "cctvMgrNo" : mgrNo }, function(json){
										var dirDeg = xeusCCTV.calculAngle(json.result);

										xeusSymbol.addAngle(_cctv["point"], gid, dirDeg);
									});

								}
							});
						}else{
							clearTimeout(timeout);
							$(this).removeAttr("lock");
							//xeusSymbol.removeFeature(gid, "isLock");
							_common.callAjax("/ws/addEvent.json", { "json" : presetEndOption() }, function(json){
								if(json.result){
									xeusSymbol.removeFeature(gid, "isPreset");
									xeusSymbol.removeFeature(gid, "isAngle");
								}
							});
						}
					});
				});

				function ptzStartOption(){
					var lonlat = Spatial.convertProjection(point, "EPSG:5186", "EPSG:4326");
					var _Json = xeusJsonParser.getTemplate();

					_Json.statEvetTypCd = "CCTVLOCK";
					_Json.statEvetNm = "PTZ";
					_Json.statEvetCntn = "사용자(" + userId + ")가 " + cctvNm + "(고유번호 : " + gid + ") CCTV PTZ 제어를 시작하였습니다.";
					_Json["x"] = String(lonlat[0]);
					_Json["y"] = String(lonlat[1]);
					_Json["tmx"] = String(point[0]);
					_Json["tmy"] = String(point[1]);
					_Json.statEvetOutbDtm = new Date().getYMDHMS();
					_Json.procSt = "10";
					_Json.isTest = "N";
					_Json.uSvcOutbId = "";
					_Json.statEvetSvcTyp = "CCTVLOCK";
					_Json.outbPosNm = cctvNm + "(고유번호 : " + gid + ")";
					_Json.outbPos[0].x = String(lonlat[0]);
					_Json.outbPos[0].y = String(lonlat[1]);
					_Json.etcCntn = JSON.stringify({"gid" : gid});

					return JSON.stringify(_Json);
				}
				function ptzEndOption(){
					var lonlat = Spatial.convertProjection(point, "EPSG:5186", "EPSG:4326");
					var _Json = xeusJsonParser.getTemplate();

					_Json.statEvetTypCd = "CCTVLOCK";
					_Json.statEvetNm = $("#" + _player_dlg_id).attr("statEvetNm");
					_Json.statEvetCntn = "사용자(" + userId + ")가 " + cctvNm + "(고유번호 : " + gid + ") CCTV PTZ 제어를 종료하였습니다.";
					_Json["x"] = String(lonlat[0]);
					_Json["y"] = String(lonlat[1]);
					_Json["tmx"] = String(point[0]);
					_Json["tmy"] = String(point[1]);
					_Json.statEvetOutbDtm = new Date().getYMDHMS();
					_Json.statEvetClrDtm = new Date().getYMDHMS();
					_Json.statEvetActnMn = userId;
					_Json.statEvetActnCntn = "사용자(" + userId + ")가 " + cctvNm + "(고유번호 : " + gid + ") CCTV PTZ 제어를 종료하였습니다.";
					_Json.procSt = "91";
					_Json.isTest = "N";
					_Json.uSvcOutbId = $("#" + _player_dlg_id).attr("uSvcOutbId");
					_Json.statEvetSvcTyp = "CCTVLOCK";
					_Json.outbPosNm = cctvNm + "(고유번호 : " + gid + ")";
					_Json.outbPos[0].x = String(lonlat[0]);
					_Json.outbPos[0].y = String(lonlat[1]);
					_Json.etcCntn = JSON.stringify({"gid" : gid});

					return JSON.stringify(_Json);
				}
				$('#' + event.target.id + '_btn_ptz').click(function(e) {
					$("#" + parentView).find("#" + _player_dlg_id).find("#ptzWrap").toggle("blind", function(){

						if($(this).is(":visible")){
							$(this).attr("lock", "ptz");

							timeout = setTimeout(function(){
								if($("#" + parentView).find("#" + _player_dlg_id).find("#ptzWrap").is(":visible")){
									$('#' + event.target.id + '_btn_ptz').click();
								}
							}, 60000);
							$("#" + parentView).find("#" + _player_dlg_id).find("#ptzWrap").find(".ptzBtn").click(function(){
								clearTimeout(timeout);
								timeout = setTimeout(function(){
									if($("#" + parentView).find("#" + _player_dlg_id).find("#ptzWrap").is(":visible")){
										$('#' + event.target.id + '_btn_ptz').click();
									}
								}, 60000);
							});
							//xeusSymbol.addLock(point, gid);
							_common.callAjax("/ws/addEvent.json", { "json" : ptzStartOption() }, function(json){
								if(json.result){
									$("#" + _player_dlg_id).attr({
										"uSvcOutbId" : json.uSvcOutbId,
										"statEvetNm" : json.statEvetNm
									});
									_common.callAjax("/proxy/xeusGateWay.json", { "path" : "getPTZPosition", "cctvMgrNo" : mgrNo }, function(json){
										var dirDeg = xeusCCTV.calculAngle(json.result);

										xeusSymbol.addAngle(_cctv["point"], gid, dirDeg);
									});
								}
							});
						}else{
							clearTimeout(timeout);
							$(this).removeAttr("lock");
							//xeusSymbol.removeFeature(gid, "isLock");
							_common.callAjax("/ws/addEvent.json", { "json" : ptzEndOption() }, function(json){
								if(json.result){
									xeusSymbol.removeFeature(gid, "isPreset");
									xeusSymbol.removeFeature(gid, "isAngle");
								}
							});
						}
					});
				});

				$('#' + event.target.id + '_btn_cprtsp').click(function(e) {
					_common.callAjax("/proxy/xeusGateWay.json", { "path" : "getRTSP", "cctvMgrNo" : mgrNo }, function(json){
						if(json){
							var url = json.result.rtsp;
							confirm("해당 CCTV의 RTSP 주소입니다.<br><br>" + url + "<br><br>* 클립보드에 저장하시려면 확인을 눌러주세요.", function(){
								var ta = document.createElement("textarea");
								document.body.appendChild(ta);
								ta.value = url;
								ta.select();
								document.execCommand('copy');
								document.body.removeChild(ta);
							});
						}
					}, false);
				});

//				$(".tooltip").tooltipsy({
//					delay: 0,
//					offset: [5, 5],
//					css: {
//						'font-size' : '12px',
//						'font-weight' : 'bold',
//						'padding': '10px',
//						'color': '#303030',
//						'background-color': '#ffffff',
//						'border': '2px solid #4893BA',
//						'-moz-box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
//						'-webkit-box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
//						'box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
//						'text-shadow': 'none'
//					},
//					from: _player_dlg_id
//				}).click(function (e) {
//					$('.tooltipsy').parent().hide();
//
//					var from = _player_dlg_id;
//					if(from != null){
//						setTimeout(function(){
//							if(!$("div[aria-describedby=" + from + "]").is(":visible")){
//								$("div[from=" + from + "]").remove();
//							}
//						}, 500);
//					}
//				});
			}
		});

		_self.setPlayerFunction(_player, _player_dlg_id, _cctv, gid, mgrNo, _playerWidth, _playerHeight, mode, gbnCd);
	}

	this.setPlayerFunction = function(_player, _player_dlg_id, _cctv, gid, mgrNo, _playerWidth, _playerHeight, mode, gbnCd){
		_player.html('');
		var _canvasId = "video-canvas-" + gid;
		_player.append("<canvas id='" + _canvasId + "' width="+ (_playerWidth * mode) + " height="+ (_playerHeight * mode) +" style='display:none;'></canvas>");
		_player.append("<div id='" + _canvasId + "_div'></div>");

		var $ptzTop = $("<span class='ptzBtn' id='ptzTop'></span>").data({"type" : "Up", "mgrNo" : mgrNo});

		var $ptzBottom = $("<span class='ptzBtn' id='ptzBottom'></span>").data({"type" : "Down", "mgrNo" : mgrNo});

		var $ptzLeft = $("<span class='ptzBtn' id='ptzLeft'></span>").data({"type" : "Left", "mgrNo" : mgrNo});

		var $ptzRight = $("<span class='ptzBtn' id='ptzRight'></span>").data({"type" : "Right", "mgrNo" : mgrNo});

		var $zoomIn = $("<span class='ptzBtn' id='ptzZoomIn'></span>").data({"type" : "ZoomIn", "mgrNo" : mgrNo});

		var $zoomOut = $("<span class='ptzBtn' id='ptzZoomOut'></span>").data({"type" : "ZoomOut", "mgrNo" : mgrNo});

		var $ptzWrap = $("<div class='ptzWrap' id='ptzWrap'></div>").css({
			"position": "absolute",
			"width": "100%",
			"height": "100%",
			"top": "0px",
			"left": "0%",
			"display": "none"
		}).append($ptzTop).append($ptzBottom).append($ptzLeft).append($ptzRight).append($zoomIn).append($zoomOut);


		var $presetWrap = $("<div class='presetWrap mCustomScrollbar' id='presetWrap' data-mcs-theme='minimal-dark'></div>").css({
			"position": "absolute",
			"width": "45px",
			"height": "100%",
			"top": "0px",
			"right": "-17px",
			"overflow": "auto",
			"display": "none"
		});

		//방범용
		if(10 == Number(gbnCd)){
			_common.callAjax("/proxy/xeusGateWay.json", { "path" : "getPresets", "cctvMgrNo" : mgrNo, "gbnCd" : gbnCd }, function(json){
				var length = json.result.length;
				for(var i=11; i<20; i++){
					var $btn = $("<button class='gotoPreset whiteBtn'>" + i + "</button>").width(28).height(28);

					var isExist = false;
					for(var l=0; l<length; l++){
						if(i == Number(json.result[l].presetNo)){
							$btn.data(json.result[l]);
							isExist = true;
							break;
						}
					}

					if(!isExist) $btn.css("cursor", "not-allowed").removeClass("whiteBtn").addClass("blackBtn");

					$presetWrap.append($btn);
				}

				$presetWrap.find(".gotoPreset").click(function(){
					var prop = $(this).data();
					if($(this).hasClass("whiteBtn")){
						_common.callAjax("/proxy/xeusGateWay.json", { "path" : "gotoPreset", "cctvMgrNo" : prop["cctvMgrNo"], "presetNo" : prop["presetNo"] }, function(json){
							xeusSymbol.addAngle(_cctv["point"], gid, prop["dirDeg"]);
						}, false);
					}
				});
			}, false);
		}

		_player.append($ptzWrap).append($presetWrap);
		$presetWrap.mCustomScrollbar({
			scrollInertia: 500
		});

		var isDown = false;
		var $target = null;
		_player.find(".ptzBtn").mousedown(function(){
			isDown = true;
			$target = $(this);
			//console.log("PTZ : " + $(this).data().type + " Start");
			var mgrNo = $(this).data().mgrNo;
			var code = $(this).data().type;

			_common.callAjax("/proxy/xeusGateWay.json", {"path" : "setPTZ", "cctvMgrNo" : mgrNo, "action" : "start", "code" : code}, function(){

			});
		}).mouseup(function(){
			isDown = false;
			$target = null;
			var mgrNo = $(this).data().mgrNo;
			var code = $(this).data().type;

			_common.callAjax("/proxy/xeusGateWay.json", {"path" : "setPTZ", "cctvMgrNo" : mgrNo, "action" : "stop", "code" : code}, function(){
				_common.callAjax("/proxy/xeusGateWay.json", { "path" : "getPTZPosition", "cctvMgrNo" : mgrNo }, function(json){
					var dirDeg = xeusCCTV.calculAngle(json.result);

					xeusSymbol.addAngle(_cctv["point"], gid, dirDeg);
				});
			});
		});

		/*_player.find(".ptzBtn").hover(function(){
			$(this).stop().fadeIn("fast");

			var id = $(this).attr("id");
			if(id == "ptzZoomIn" || id == "ptzZoomOut"){
				$("#ptzZoomIn").stop().fadeIn("fast");
				$("#ptzZoomOut").stop().fadeIn("fast");
			}
		}, function(){
			if(isDown) $target.mouseup();
			$(this).stop().fadeOut("fast");

		});

		_player.find("canvas").hover(function(){
			$(".ptzBtn").stop().fadeIn("fast");
		}, function(){
			$(".ptzBtn").stop().fadeOut("fast");
		});*/
		_player.dialog('open');
		var _size =  (_playerWidth * mode) + "x" + (_playerHeight * mode);
		//var _jsmpeg = this.getMpegPlayer(VIDEO_WEBSOCKET_URL, $('#'+_canvasId)[0], mgrNo, _size);
		var _jsmpeg = this.getMpegPlayer(VIDEO_WEBSOCKET_URL, _canvasId, mgrNo, _size,_cctv.deviceId);
		this.addPlayList(_player_dlg_id, _cctv, _jsmpeg);


		_player.find("video").dblclick(function(){
			var $PLAYER = $(this).parent()
			if(!document.fullscreenElement){
				$PLAYER.attr("w", $PLAYER.width()).attr("h", $PLAYER.height()).width("100%").height("100%");
				$PLAYER.parent()[0].requestFullscreen();
			}else{
				if(document.exitFullscreen){
					document.exitFullscreen();
					$PLAYER.width($PLAYER.attr("w")).height($PLAYER.attr("h"));
				}
			}
//			xeusCCTV.createFullHDPlayer(mgrNo, _isPreview, _cctv);
		});

		_player.find("img").dblclick(function(){
			var $PLAYER = $(this).parent()
			if(!document.fullscreenElement){
				$PLAYER.attr("w", $PLAYER.width()).attr("h", $PLAYER.height()).width("100%").height("100%");
				$PLAYER.parent()[0].requestFullscreen();
			}else{
				if(document.exitFullscreen){
					document.exitFullscreen();
					$PLAYER.width($PLAYER.attr("w")).height($PLAYER.attr("h"));
				}
			}
//			xeusCCTV.createFullHDPlayer(mgrNo, _isPreview, _cctv);
		});

		//화면 크기에 따른 ptz버튼들의 css 정리
		//ptzTop	//40X30
		//ptzBottom
		//ptzLeft
		//ptzRight
		//ptzZoomIn//95		//50X50
		//ptzZoomOut//119
		$('#'+_player_dlg_id).find('#ptzTop').css('left', (($('#'+_player_dlg_id).width()-10)/2)-20);
		$('#'+_player_dlg_id).find('#ptzBottom').css('left', (($('#'+_player_dlg_id).width()-10)/2)-20);
		$('#'+_player_dlg_id).find('#ptzLeft').css('top', (($('#'+_player_dlg_id).height()-30)/2)-15);
		$('#'+_player_dlg_id).find('#ptzRight').css('top', (($('#'+_player_dlg_id).height()-20)/2)-15);
		$('#'+_player_dlg_id).find('#ptzZoomIn').css('right', ($('#'+_player_dlg_id).width()/2)-25);
		$('#'+_player_dlg_id).find('#ptzZoomIn').css('top', ($('#'+_player_dlg_id).height()/2)-25);
		$('#'+_player_dlg_id).find('#ptzZoomOut').css('right', ($('#'+_player_dlg_id).width()/2)-25);
		$('#'+_player_dlg_id).find('#ptzZoomOut').css('top', ($('#'+_player_dlg_id).height()/2));
	}

	// 영상 player Title html을 생성한다.
	this.getVideoDialogTitle = function(_px, _py, _cd, _txt, _len) {
		// 제목이 길면 줄임표를 표시한다.
		var _reducedTxt = ((_len == 0) ? _txt : ellipseTxt(_txt, _len));
		var title = "<div class='xeus-dialog-title-div'>";
//		title += '<a href="#" onClick="xeusCCTV.moveTo([' + _px + ',' + _py + ']);">';
//		title += '<a href="#">';

		// jwr 영상 실행중일때 타이틀에 있는 심볼을 누르면 해당 위치로 이동함(moveTo);
		title += "<img class='xeus-dialog-title-icon' ";

		//var imgNm = _self.SYM_ICON[_cd]['N'];

		var _symImage = ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[_cd];

		//title += " src='" + ctxPath + "/res/sym/cctv/" + imgNm + "'/>";
		title += " src='" + _symImage + "'/>";
//		title += '</a>';
		title += "<span class='xeus-dialog-title-txt' title='"+_txt+"'>" + _reducedTxt + "</span>";
		title += "</div>";
		return title;
	}

	// CCTV 데이터를 서버에서 받아와 Vector레이어에 채운다.
	this.reload = function() {
		var size = map.getSize();
		var extent = map.getView().calculateExtent(size);
		var epsg = map.getView().getProjection().getCode();
		// false로 반환되는 값은 "", null, undefined, 0, NaN 이 있고
		if (!epsg) {
			return;
		}
		var codes = _getVisibleStyleIds();
		if (codes == null) {
			codes = [];
		}

		epsg = epsg.split(':')[1];
		// jwr json으로 CCTV목록 가져옴 (geometry에 coord에 좌표값, property에 cctv리스트
		// 있음(하나일땐 리스트가 1개))
		$.ajax({
			url : ctxPath + "/cctv/cctvmap",
			type : "POST",
			data : {
				'epsg' : epsg,
				'map_width' : Math.floor(size[0]),
				'map_height' : Math.floor(size[1]),
				'sym_width' : SYMSIZE,
				'sym_height' : SYMSIZE,
				'bbox' : extent.join(','),
				'codes' : codes.join(',')
			},
			dataType : "json",
			success : function(json) {
				var source = cctvLayer.getSource();
				var features = new ol.format.GeoJSON().readFeatures(json);
				source.clear(); // jwr CCTV레이어의 모든 피쳐를 지움
				source.addFeatures(features); // jwr json에서 geometry가 있는 모든것을

				for(var i=0; i<features.length; i++){
					var feature = features[i];
					//_self.getCCTVLayer().setStyle(feature);
					//cctvLayer.setStyle(feature);
					//console.log('setStyle');
					for(var l=0; l<feature.get("cctvList").length; l++){
						var isError = feature.get("cctvList")[l]["isError"];
						var gid = feature.get("cctvList")[l]["gid"];
						if(isError) xeusSymbol.addError(feature, gid);
					}
				}

				// 202824.35189968106, 542465.4019472917
				// 피쳐로 등로
				// 화면에 CCYV영상 Dialog가 있으면 심볼 Point값을 재설정해야 한다.
				// 심볼이 합쳐지거나 분리 되기 때문에..
				// var keys = Object.keys(_cctvSelf.dialogs);
				// if (keys.length > 0) {
				// _cctvSelf.checkCCTVSymbolPointAfterReload(features);
				// }

				// TODO Feature 추가.
				/*for(var i=0; i<features.length; i++){
					var end = new Array();
					end.push(features[i].getGeometry().getCoordinates()[0] + 100);
					end.push(features[i].getGeometry().getCoordinates()[1] + 100);
					xeusSymbol.addPreset(features[i], end, source);
				}*/
			},
			error : function(xhr, status, error) {
				//alert("CCTV data request error occurred.. > \r\n" + error);
			}
		});
	};

	// ////////////////////////////////////////////////////
	// 이벤트 등록
	// ////////////////////////////////////////////////////
	// moveend 이벤트 발생시 자동으로 처리됨
	map.on('moveend', function(e) {
		// jwr 처음 페이지 열때 호출
		_self.reload(); // cctv데이터를 다시 로드
		BoardAPI.getWeather(xeusLayout.mapService.getMap());
		BoardAPI.getAir(xeusLayout.mapService.getMap());
		$('.dialogDrag').each(function(){
			xeusSymbol.addGuideline(xeusLayout.mapService.getMap(), $(this).attr('id'));
		})
	});

};

//////////////////////////////////////////////////////////////////////////////////////////
var xeusCCTV = {
	cctv : null,
	VIDEO_GRID : null,
	ctxPath : null,
	VIDEO_GRID_COLS : 3,
	TITLE_LEN : 140,

	// 투망모니터링 시 그려지는 임시 선 레이어
	vectorLineLayer : null,
	// 투망모니터링 시 그려지는 임시 반경 레이어
	vectorCircleLayer : null,
	// 투망모니터링 시 그려지는 방향 표시 포인트 레이어
	vectorPointLayer : null,
	// cctv 선택시 이전 다이어로그를 교체할 것인지, gridpane으로 보여질것인지 정책
	// replace : 현재 보여지는 video dialog를 닫고 새 player를 보여줌
	// gridpane : 현재 보여지는 video dailog 유지, 선택한 것을 gridpane에서 보여짐.
	gridToggleButtons : new geomex.xeus.ToggleButtons(), // 가로 열 수 선택 토글버튼

	initCCTV : function(_ctxPath) {
		xeusCCTV.ctxPath = _ctxPath;
		xeusCCTV.cctv = new geomex.xeus.CCTV({
			ctxPath : xeusCCTV.ctxPath,
			mapService : xeusLayout.mapService,
			layer : 'cctv',
			virtualBoundary : 'virtual-map-boundary'
		});

		$('.tab[target=' + parentView + ']').data('cctv', xeusCCTV.cctv);
	},

	// 사용안함 : 2017.04.27
	// jwr 영상 실행중일때 타이틀에 있는 심볼을 누르면 해당 위치로 이동함(moveTo);
	moveTo : function(_pt) {
		// west pane, east-pane이 있는 경우 지도 이동을 조정한다.
		var _boundary = $('#virtual-map-boundary');
		var _ndx = (xeusLayout.mapService.getMap().getSize()[0] / 2)
				- (_boundary.offset().left + _boundary.width() / 2);
		var _wdx = xeusLayout.mapService.getMap().getCoordinateFromPixel([ _ndx, 0 ]);
		var _wsx = xeusLayout.mapService.getMap().getCoordinateFromPixel([ 0, 0 ]);
		var _newPt = [ _pt[0] + _wdx[0] - _wsx[0], _pt[1] ];
		xeusLayout.mapService.getMap().getView().animate({
			center : _newPt,
			duration : 1000
		});
	},

	setPreviewContent : function(_cctv){
		var _html = '';
		_html += '<div id="previewInfo" class="bPopup" style="display: none;"> ';
		_html += '    <div id="title-bar"> ';// style="margin-left: 10px;"
		_html += '        <button id="closeBtn" style="float: right;"><img src="' + xeusCCTV.ctxPath + '/res/img/close_btn.png"/></button> ';
		_html += '        <p class="title">선영상 조회 사유</p> ';
		_html += '    </div> ';
		_html += '    <table class="list"> ';// cellspacing="0" width="100%" style="margin-top:0;"
		_html += '      <thead> ';
		_html += '        <colgroup> ';
		_html += '            <col width="70px" /> ';
		_html += '	          <col width="" /> ';
		_html += '	          <col width="" /> ';
		_html += '        </colgroup> ';
		_html += '      </thead> ';
		_html += '      <tbody> ';
		_html += '        <tr> ';
		_html += '            <th><label>사건번호</label></th> ';
		_html += '            <td><input type="text" class="sendData wide" id="acciNum"></td> ';
		_html += '        </tr> ';
		_html += '        <tr> ';
		_html += '            <th><label>신청사유</label></th> ';
		_html += '            <td><input type="text" class="sendData wide" id="reqResn"></td> ';
		_html += '        </tr> ';
		_html += '        <tr> ';
		_html += '            <th colspan="2">요청 후 승인 될 경우 영상이 자동 재생되며,<br>브라우저를 종료 할 경우 다시 요청해야 합니다.</th> ';
		_html += '        </tr> ';
		_html += '        <tr> ';
		_html += '            <th colspan="2"><button id="saveBtn">재생 요청</button></th> ';
		_html += '        </tr> ';
		_html += '      </tbody> ';
		_html += '    </table> ';
		_html += '</div> ';

		$('#layout-body').append(_html);

		$('#previewInfo').bPopup({
			onOpen: function() {
				$('#previewInfo').find('#closeBtn').click(function(){
					$('#previewInfo').bPopup().close();
					$('.bPopup').remove();
				});

				$('#previewInfo').find('#saveBtn').click(function(){
					var gid = _cctv["gid"];
					var cctvNm = _cctv["cctvNm"];
					var point = _cctv["point"];

					var lonlat = Spatial.convertProjection(point, "EPSG:5186", "EPSG:4326");
					var _Json = xeusJsonParser.getTemplate();

					var param = {
							userId : userId,
							cctvNm : cctvNm,
							gid : gid,
							reqResn : $('#previewInfo').find('#reqResn').val(),
							acciNum : $('#previewInfo').find('#acciNum').val(),
							cctvMgrNo : _cctv["mgrNo"],
							cctv : _cctv
					}

					_Json.statEvetTypCd = "CCTVPREV";
					_Json.statEvetNm = "선영상조회 요청";
					_Json.statEvetCntn  = "사용자(" + userId + ")가 " + cctvNm + "(고유번호 : " + gid + ") CCTV 선영상 조회를 요청하였습니다.";
					_Json.statEvetCntn += "<br>사건번호 : " + param["reqResn"];
					_Json.statEvetCntn += "<br>신청사유 : " + param["acciNum"];
					_Json["x"] = String(lonlat[0]);
					_Json["y"] = String(lonlat[1]);
					_Json["tmx"] = String(point[0]);
					_Json["tmy"] = String(point[1]);
					_Json.statEvetOutbDtm = new Date().getYMDHMS();
					_Json.statEvetClrDtm = "";
					_Json.statEvetActnMn = "";
					_Json.statEvetActnCntn = "";
					_Json.procSt = "10";
					_Json.isTest = "N";
					_Json.uSvcOutbId = "";
					_Json.statEvetSvcTyp = "CCTVPREV";
					_Json.outbPosNm = cctvNm + "(고유번호 : " + gid + ")";
					_Json.outbPos[0].x = String(lonlat[0]);
					_Json.outbPos[0].y = String(lonlat[1]);
					_Json.etcCntn = JSON.stringify(param);

					_common.callAjax("/auth/getAuthGrpList.json", { "authMgrNo" : "EVT011" }, function(json){
						var list = new Array();

						for(var i=0; i<json.result.length; i++){
							list.push(json.result[i].authGrpNo);
						}

						_Json["targetGrp"] = list.toString();

						_common.callAjax("/ws/addEvent.json", { "json" : JSON.stringify(_Json) }, function(_json){
							if(_json.result){
								$('#previewInfo').bPopup().close();
								$('.bPopup').remove();
							}
						});
					}, false);

					/*_common.callAjax("/cctvPreview/add.json", param, function(json){
						if(json.result){
							$('#previewInfo').bPopup().close();
							$('.bPopup').remove();

							var _point = _cctv['point'];
							var cnt = xeusCCTV.cctv.getVideoDialogCount();
							var isPreview = true;

							xeusCCTV.cctv.createVideoDialog(_cctv, cnt, isPreview);
							xeusCCTV.cctv.reload();
							xeusSymbol.addPlay(_cctv["point"], _cctv["gid"]);
						}
					});*/
				});
			},
			onClose: function() {
				$('.bPopup').remove();
			}
		});

	},

	// cctv세부 목록(overlay)에서 cctv 영상 보기 선택
	// _json cctv정보 json
	viewVideo : function(_json) {
		if(parentView === "eventView"){
			_common.callAjax("/auth/hasEvtAuth.json", {authData : "CCTVPlay"}, function(json){
				if(json.result){
					xeusCCTV.cctv.closeMapOverlay();
					var _cctv = JSON.parse(decodeURIComponent(_json));
					if(_cctv['stateCd'] == '장애'){
						alert("<br>해당 CCTV는 현재 재생이 불가능 합니다.");
					}else{
						// 동일위치 CCTV 전체 보기 선택시,, gridpane에 보여준다.
						// jwr _cctv.constructor가 array면 전체 선택, object면 단일 선택
						if (_cctv.constructor == Array) {
							for (var _x = 0; _x < _cctv.length; _x++) {
								(function(_x) { // 이렇게 해야지 0.5초에 하나씩 수행한다.
									setTimeout(function() {
										xeusCCTV.showVideoInGridPane(_cctv[_x]);
										xeusSymbol.addPlay(_cctv[_x]["point"], _cctv[_x]["gid"]);
									}, 500 * _x);
								})(_x);
							}
							xeusCCTV.cctv.reload();
						} else { // obj.constructor == Object
							_common.callAjax("/auth/hasAuth.json", { "authData" : "CCTVPREV" }, function(json){
								if(json.result){
									xeusCCTV.setPreviewContent(_cctv);
								}else{
									var _point = _cctv['point'];
									var cnt = xeusCCTV.cctv.getVideoDialogCount();
									xeusCCTV.cctv.createVideoDialog(_cctv, cnt);
									xeusCCTV.cctv.reload();
									xeusSymbol.addPlay(_cctv["point"], _cctv["gid"]);
								}
							});
							// 단일 CCTV선택시 videoDialog에 보여준다. 갯수 제한 있음

							/*var _point = _cctv['point'];
							var cnt = xeusCCTV.cctv.getVideoDialogCount();
							xeusCCTV.cctv.createVideoDialog(_cctv, cnt);
							xeusCCTV.cctv.reload();
							xeusSymbol.addPlay(_cctv["point"], _cctv["gid"]);*/
						}
					}
				}else{
					alert("<br>CCTV 재생 권한이 존재하지 않습니다.");
				}
			}, false);
		}
	},

	// CCTV 정보 보기
	viewInformation : function(_json) {
		this.cctv.closeMapOverlay();
		console.log(">>showCCTVInfo..............");
		// jwr 아마도 Json으로 DB내용 긁어와야할듯?
	},

	// 투망모니터링
	viewNetWatch : function(_json) {
		this.cctv.closeMapOverlay();
		console.log("showNetWatchVideo........");
	}

};

// CCTV영상을 grid pane에 보여준다.
/* 2017-12-06 이주영 - col, row 사용하도록 변경 (arguments Object use) */
xeusCCTV.showVideoInGridPane = function(_cctv) {
	// grid pane을 최소 생성한다.
	if (xeusCCTV.VIDEO_GRID == null) {
		xeusCCTV.createVideoGridPane(true);
	}
	// grid pane의 gridster정보를 json으로 얻어온다.
	var _json = xeusCCTV.VIDEO_GRID.serialize();
	// console.log(_json);
	// console.log("제이슨.....................");
	// 비어있는 grid pane의 위치를 찾는다.
	var _empty = xeusCCTV.getEmptyGridSlot(_json);

	// 비어 있는 gridpane이 없다면 짧은 안내 메세지 출력
	if (_empty == undefined) {
		xeusLayout.showShortcutMessage('더이상 영상화면을 추가할 수 없습니다.', 340);
		return;
	}

	var gid = _cctv['gid'];
	var mgrNo = _cctv['mgrNo'];
	var cctvNm = _cctv['cctvNm'];
	var deviceId = _cctv['deviceId'];
	var channelNo = _cctv['channelNo'];
	var gbnCd = _cctv['gbnCd'];
	var gbnTxt = _cctv['gbnTxt'];
	var angle = _cctv['angle'];
	var point = _cctv['point'];

	// jwr 오른쪽 그리드패널 열기
	xeusCCTV.westReLayout();
	xeusLayout.showOverlayEastPane(500); // 함수 내부코드에서 이미 보이는지 체크한다.

	// 이미 play중인 cctv이면 return
	// cctv symbol에 play 표시가 있으므로 안내 메시지 생략
	if (this.cctv.existPlayList(gid)) {
		return;
	}

	var _player_grid_id = '_player_grid_' + new Date().getTime();
	var _html = xeusCCTV.makeWidgetHtml(_cctv, _player_grid_id);
	if(arguments[1] != null && arguments[2] != null){
		xeusCCTV.VIDEO_GRID.add_widget(_html, 1, 1, arguments[1], arguments[2]);
	}else{
		xeusCCTV.VIDEO_GRID.add_widget(_html, 1, 1, _empty['c'], _empty['r']);
	}
   //
	//gridPane Widget에 player등록.
	var _canvasId = "grid-canvas-"  + gid;
	var _size =  xeusCCTV.cctv.SIZE['minWidth'] + "x" + xeusCCTV.cctv.SIZE['minHeight'];
//	var _jsmpeg = xeusCCTV.cctv.getMpegPlayer(VIDEO_WEBSOCKET_URL, $('#'+_canvasId)[0], mgrNo, _size);

	var _jsmpeg = xeusCCTV.cctv.getMpegPlayer(VIDEO_WEBSOCKET_URL, _canvasId, mgrNo, _size, _cctv.deviceId);

	xeusCCTV.cctv.addPlayList(_player_grid_id, _cctv, _jsmpeg);
	///////////////////////////////////////////////////////////
	$('#' + _player_grid_id).data('gid', gid);
	$('#' + _player_grid_id).data('mgrNo', mgrNo);
	$('#' + _player_grid_id).data('gbnCd', gbnCd);
	// html을 처리후 event를 등록해야 함
	// pane player를 닫기 이벤트..
	$('#' + _player_grid_id + '_btn_close').off('click');
	$('#' + _player_grid_id + '_btn_undock').off('click');
	$('#' + _player_grid_id + '_btn_stillcut').off('click');
	$('#' + _player_grid_id + '_btn_cprtsp').off('click');

	$('#' + _player_grid_id + '_btn_close').one('click',function(e) {
		//one 한번만 시행, 더블되면 droppable('destroy')에서 오류남)
		xeusCCTV.closeGridPanePlayer(gid, _player_grid_id);
		xeusCCTV.cctv.reload();
	});

	// 팝업Player가 없어야 함.... 있으면 메세지...
	$('#' + _player_grid_id + '_btn_undock').on('click',function(e) {
		xeusCCTV.unDockGridPanePlayer({
			player_id : _player_grid_id,
			gid : gid,
			mgrNo : mgrNo,
			cctvNm : cctvNm,
			deviceId : deviceId,
			channelNo : channelNo,
			gbnCd : gbnCd,
			gbnTxt : gbnTxt,
			angle : angle,
			point : point
		});
	});

	$('#' + _player_grid_id + '_btn_stillcut').on('click',function(e) {
		_common.postForm.submit("/proxy/getShapshot.json", { "path" : "getShapshot", "cctvMgrNo" : mgrNo });
	});

	$('#' + _player_grid_id + '_btn_cprtsp').click(function(e) {
		_common.callAjax("/proxy/xeusGateWay.json", { "path" : "getRTSP", "cctvMgrNo" : mgrNo }, function(json){
			if(json){
				var url = json.rtsp;
				confirm("해당 CCTV의 RTSP 주소입니다.<br><br>" + url + "<br><br>* 클립보드에 저장하시려면 확인을 눌러주세요.", function(){
					var ta = document.createElement("textarea");
					document.body.appendChild(ta);
					ta.value = url;
					ta.select();
					document.execCommand('copy');
					document.body.removeChild(ta);
				});
			}
		}, false);
	});

//	$(".tooltip").tooltipsy({
//		delay: 0,
//		offset: [5, 5],
//		css: {
//			'font-size' : '12px',
//			'font-weight' : 'bold',
//			'padding': '10px',
//			'color': '#303030',
//			'background-color': '#ffffff',
//			'border': '2px solid #4893BA',
//			'-moz-box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
//			'-webkit-box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
//			'box-shadow': '0 0 10px rgba(0, 0, 0, .5)',
//			'text-shadow': 'none'
//		},
//		from: _player_grid_id
//	}).click(function (e) {
//		$('.tooltipsy').parent().hide();
//
//		var from = _player_grid_id;
//		if(from != null){
//			setTimeout(function(){
//				if(!$("div[aria-describedby=" + from + "]").is(":visible")){
//					$("div[from=" + from + "]").remove();
//				}
//			}, 500);
//		}
//	});

	// //////////////////////////////////////////
	// droppable 설정
	// /////////////////////////////////////////
	// cctv세부 목록에서 symbol을 gridpane player에 drop 처리
	// 만약 gridpane에 drop 하면 영상 추가..createVideoGridPane() 참고
	$('#' + _player_grid_id).droppable({
		greedy : true,
		drop : function(event, ui) {
			var dragid = ui.draggable.attr("id");

			var _drp_gid = ui.draggable.data("gid");
			var _drp_mgrNo = ui.draggable.data("mgrNo");
			var _drp_cctvNm = ui.draggable.data("cctvNm");
			var _drp_deviceId = ui.draggable.data("deviceId");
			var _drp_channelNo = ui.draggable.data("channelNo");
			var _drp_gbnCd = ui.draggable.data("gbnCd");
			var _drp_gbnTxt = ui.draggable.data("gbnTxt");
			var _drp_angle = ui.draggable.data("angle");
			var _drp_point = ui.draggable.data("point");

			// cctv_drag_로 시작하는 drop이 아니면 리턴
			if (!dragid || !dragid.match("^cctv_drag_")) {
				return;
			}

			var _oldGid = $('#' + event.target.id).data('gid');
			// 이미 playerlist에 있으면 return;
			if ((_oldGid == _drp_gid) || xeusCCTV.cctv.existPlayList(_drp_gid)) {
				return;
			}
		    // 이전 player 정보를 제거한다...
			xeusCCTV.cctv.removePlayList(_oldGid);
			//
			// 정상 drop 설정
			ui.draggable.draggable("option", "revert", false);
			// 타이틀 text 생성
			var _len = xeusCCTV.TITLE_LEN;
			var _title = xeusCCTV.cctv.getVideoDialogTitle(_drp_point[0], _drp_point[1], _drp_gbnCd, _drp_cctvNm, _len);
			var _canvasId = "grid-canvas-" +_drp_gid;
			var _newHtml="";
			_newHtml += "<div id='" + _canvasId + "_div'></div>";

			$('#' + _player_grid_id + '_title_warp').html(_title);
			$('#' + _player_grid_id + '_video_warp').html(_newHtml);
			$('#' + _player_grid_id).data('gid', _drp_gid);
			$('#' + _player_grid_id).data('gbnCd', _drp_gbnCd);

			var _size =  xeusCCTV.cctv.SIZE['minWidth'] + "x" + xeusCCTV.cctv.SIZE['minHeight'];
			var _jsmpeg = xeusCCTV.cctv.getMpegPlayer(VIDEO_WEBSOCKET_URL, _canvasId, _drp_mgrNo, _size);
    		xeusCCTV.cctv.addPlayList(event.target.id, {
				gid : _drp_gid,
				mgrNo : _drp_mgrNo,
				cctvNm : _drp_cctvNm,
				deviceId : _drp_deviceId,
				channelNo : _drp_channelNo,
				gbnCd : _drp_gbnCd,
				gbnTxt : _drp_gbnTxt,
				angle : _drp_angle,
				point : _drp_point
			}, _jsmpeg);
			//////////////////////////////////////////

			$('#' + _player_grid_id + '_btn_close').off('click');
			$('#' + _player_grid_id + '_btn_undock').off('click');
			// 닫기 버튼 event
			$('#' + _player_grid_id + '_btn_close').one('click',function(e) {
				xeusCCTV.closeGridPanePlayer(_drp_gid, _player_grid_id);
				xeusCCTV.cctv.reload();
			});

			$('#' + _player_grid_id + '_btn_undock').on('click',function(e) {
				//dialog 실행 실패시 click event유지해야 함.
				xeusCCTV.unDockGridPanePlayer({
					player_id : _player_grid_id,
					gid : _drp_gid,
					mgrNo : _drp_mgrNo,
					cctvNm : _drp_cctvNm,
					deviceId : _drp_deviceId,
					channelNo : _drp_channelNo,
					gbnCd : _drp_gbnCd,
					gbnTxt : _drp_gbnTxt,
					angle : _drp_angle,
					point : _drp_point
				});
			});

		}
	}); // player에 cctv가 drop되었을때.. 화면정보 교체...처리 끝...

};

// grid pane widget html...
xeusCCTV.makeWidgetHtml = function(_cctv, _player_grid_id) {
	var gid = _cctv['gid'];
	var mgrNo = _cctv['mgrNo'];
	var cctvNm = _cctv['cctvNm'];
	var deviceId = _cctv['deviceId'];
	var channelNo = _cctv['channelNo'];
	var gbnCd = _cctv['gbnCd'];
	var gbnTxt = _cctv['gbnTxt'];
	var angle = _cctv['angle'];
	var point = _cctv['point'];
	// player 제목 생성
	var title = xeusCCTV.cctv.getVideoDialogTitle(point[0], point[1], gbnCd, cctvNm, xeusCCTV.TITLE_LEN);

	var _html = '';
	_html += '<li data-row="1" data-col="1" data-sizex="1" data-sizey="1"  id="' + _player_grid_id + '">';
	_html += '<div class="ui-dialog ui-corner-all ui-widget ui-widget-content" style="width:100%;height:100%">';
	_html += '<div class="ui-dialog-titlebar ui-corner-all  ui-widget-header ui-helper-clearfix ">';
	_html += '<div id="' + _player_grid_id + '_title_warp">';
	_html += title;
	_html += '</div>';

	/*_html += '<button type="button" id="' + _player_grid_id + '_btn_cprtsp" style="right:5.3em !important" title="RTSP URL 저장"';
	_html += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin tooltip">';
	_html += '<span class="ui-button-icon ui-icon ui-icon-clipboard"></span>';
	_html += '</button>';*/

	/*_html += '<button type="button" id="' + _player_grid_id + '_btn_preset" style="right:3.6em !important"';
	_html += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin">';
	_html += '<span class="ui-button-icon ui-icon ui-icon-person"></span>';
	_html += '</button>';*/

//	_html += '<button type="button" id="' + _player_grid_id + '_btn_stillcut" style="right:3.6em !important" title="스틸컷 저장"';
//	_html += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin tooltip">';
//	_html += '<span class="ui-button-icon ui-icon ui-icon-image"></span>';
//	_html += '</button>';

	_html += '<button type="button" id="' + _player_grid_id + '_btn_undock" title="큰 위젯으로 재생"';
	_html += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-newwin tooltip">';
	_html += '<span class="ui-button-icon ui-icon ui-icon-newwin"></span>';
	_html += '</button>';

	_html += '<button type="button" id="' + _player_grid_id + '_btn_close" title="영상재생 종료"';
	_html += 'class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-close tooltip">';
	_html += '<span class="ui-button-icon ui-icon ui-icon-closethick"></span>';
	_html += '</button>';

	_html += '</div>';
	_html += '<div id="' + _player_grid_id + '_player" class="ui-dialog-content ui-widget-content"  ';
	_html += 'style="overflow:hidden;">';
	_html += '<div id="' + _player_grid_id + '_video_warp" style="position: relative; width:200px; height:150px;">';

//	_html += '<canvas id="grid-canvas-' + gid + '" ';
//	_html += ' width="' + xeusCCTV.cctv.SIZE['minWidth'] + '" ';
//	_html += ' height="' + xeusCCTV.cctv.SIZE['minHeight'] + '" ';
//	_html += '></canvas>';
	_html += '<div id="grid-canvas-' + gid + '_div" ';
	_html += ' width="100%" ';
	_html += ' height="100%" ';
	_html += '></div>';

	//_html += _playerHtml;
	_html += '</div>';
	_html += '</div>';
	_html += '</div>';
	_html += '</li>';
	return _html;
};

// grid pane내 player수를 얻는다.
xeusCCTV.getGridPanePlayerCount = function() {
	var _playList = xeusCCTV.cctv.getPlayListByType('_player_grid_');
	var _keys = Object.keys(_playList);
	return _keys.length;
};

/* 2018-01-10 이주영 - grid pane내 player JSON을 얻는다. */
xeusCCTV.getGridPanePlayerData = function() {
	var playList = xeusCCTV.cctv.getPlayListByType('_player_grid_');
	var keys = Object.keys(playList);

	var result = new Array();
	for(var i=0; i<keys.length; i++){
		result.push(playList[keys[i]].cctv);
	}

	return result;
};

// grid pane에 있는 모든 player를 닫는다.
/* 2017-08-22 이주영 - 패널 클로즈 딜레이 옵션 추가(arguments object use) */
xeusCCTV.closeAllGridPanePlayer = function() {
	var _delay = 1000;
	if (!isNaN(arguments[0])) _delay = Number(arguments[0]);
	var _playList = xeusCCTV.cctv.getPlayListByType('_player_grid_');
	var _keys = Object.keys(_playList);
	for (x = 0; x < _keys.length; x++) {
		var _gid = _keys[x];
		var _player_id = _playList[_gid].id;
		$('#' + _player_id).droppable('destroy');
		xeusCCTV.cctv.removePlayList(_gid);
		xeusCCTV.VIDEO_GRID.remove_widget($('#' + _player_id));
	}
	xeusCCTV.VIDEO_GRID.remove_all_widgets();
	xeusLayout.hideOverlayEastPane(_delay);
	xeusCCTV.VIDEO_GRID = null; // grid pane 자체를 제거
	xeusCCTV.cctv.reload();
};

/* 2017-08-22 이주영 - grid pane에 있는 모든 player를 닫는다. (패널은 유지) */
xeusCCTV.closeAllGridPanePlayerAndKeepPane = function() {
	if(xeusCCTV.VIDEO_GRID != null){
		var _delay = 1000;
		if (!isNaN(arguments[0])) _delay = Number(arguments[0]);
		var _playList = xeusCCTV.cctv.getPlayListByType('_player_grid_');
		var _keys = Object.keys(_playList);
		for (x = 0; x < _keys.length; x++) {
			var _gid = _keys[x];
			var _player_id = _playList[_gid].id;
			$('#' + _player_id).droppable('destroy');
			xeusCCTV.cctv.removePlayList(_gid);
			xeusCCTV.VIDEO_GRID.remove_widget($('#' + _player_id));
		}
		xeusCCTV.VIDEO_GRID.remove_all_widgets();
		xeusCCTV.VIDEO_GRID = null; // grid pane 자체를 제거
		xeusCCTV.cctv.reload();
	}
};

// VideoGridPane에서 player를 제거한다.
xeusCCTV.closeGridPanePlayer = function(_gid, _player_grid_id) {
	$('#' + _player_grid_id).droppable('destroy');
	xeusCCTV.cctv.removePlayList(_gid);
	xeusCCTV.VIDEO_GRID.remove_widget($('#' + _player_grid_id));
};

// GridPanePlayer를 unDock하고 videoDialog에 play한다.
xeusCCTV.unDockGridPanePlayer = function(_cctv) {
	var cnt = xeusCCTV.cctv.getVideoDialogCount();
	if(cnt >= VIDEO_POPUP_PLAYER_LIMIT){
		var msg = "더이상 팝업 플레이어를 생성할 수 없습니다<br>"
			msg+= "허용 팝업 플레이어 수 : " + VIDEO_POPUP_PLAYER_LIMIT;
		xeusLayout.showShortcutMessage(msg, 400);
		return;
	}
	//
	var _player_grid_id = _cctv['player_id'];
	var _gid = _cctv['gid'];
	$('#' + _player_grid_id + '_btn_undock').off('click'); //더블 click방지용

	// grid pane에서 player를 우선 닫는다.
	xeusCCTV.closeGridPanePlayer(_gid, _player_grid_id);
	xeusCCTV.cctv.createVideoDialog(_cctv, cnt);
	xeusCCTV.cctv.reload();
};

// 우측 vidoeGridPane을 생성한다.
/**
 * 2017-12-06 이주영
 * - HTML 생성 옵션 추가(arguments object use)
 * > true 일 경우만 생성
 */
xeusCCTV.createVideoGridPane = function() {
	var _width = xeusCCTV.cctv.SIZE['minWidth'] + 2;
	var _height = xeusCCTV.cctv.SIZE['minHeight'] + 36;

	// 우측 grid Pane의 칼럼수...
	var _cols = xeusCCTV.VIDEO_GRID_COLS;
	xeusLayout.EAST = _width * _cols + 10;
	// gridster 이미 생성되어 있으면 return
	if (xeusCCTV.VIDEO_GRID != null) {
		xeusCCTV.VIDEO_GRID = null;
		if ($("#video_player_grid").data("gridster") != null) {
			$("#video_player_grid").data("gridster").destroy();
		}
	}

	if (arguments[0]) {
		var _html = '';
		_html += '<div id="overlay-east-bar" class="overlay-bar">';

		_html += '<b class="overlay-bar-title">그리드 모니터링</b>';
		_html += '<button type="button" id="gridpane_btn_close" ';
		_html += 'class="icon-button ui-button ui-corner-all ui-widget ui-button-icon-only">';
		_html += '<span class="ui-button-icon ui-icon ui-icon-closethick"></span>';
		_html += '</button>';

		_html += '<button class="overlay-bar-button" id="gridpane_btn_x2">x2</button>';
		_html += '<button class="overlay-bar-button" id="gridpane_btn_x3">x3</button>';
		_html += '<button class="overlay-bar-button" id="gridpane_btn_x4">x4</button>';

		_html += '</div>';
		_html += '<div id="overlay-east-contents">';
		_html += ' <div class="gridster">';
		_html += '  <ul id="video_player_grid">';
		_html += '  </ul>';
		_html += ' </div>';
		_html += '</div>';
		$('#'+parentView).find('#center-overlay-east').html(_html);
	}

	var _contentsHeight = $('#'+parentView).find("#center-overlay-east").height() - 2;
	$('#'+parentView).find('#overlay-east-contents').css('background', '#3B3B3B');
	$('#'+parentView).find('#overlay-east-contents').css('padding', '0'); // grid pan hack by khkim

	// 등록된 토글 버튼을 clear한다.
	xeusCCTV.gridToggleButtons.clear();
	// 상단 토글 버튼 이벤트 등록
	xeusCCTV.gridToggleButtons.add('#gridpane_btn_x2', function() {
		xeusCCTV.relocateWidget(2, _width);
		//xeusLayout.showShortcutMessage('가로 2열 보기 선택', 200);
	});
	xeusCCTV.gridToggleButtons.add('#gridpane_btn_x3', function() {
		xeusCCTV.relocateWidget(3, _width);
		//xeusLayout.showShortcutMessage('가로 3열 보기 선택', 200);
	});
	xeusCCTV.gridToggleButtons.add('#gridpane_btn_x4', function() {
		xeusCCTV.relocateWidget(4, _width);
		//xeusLayout.showShortcutMessage('가로 4열 보기 선택', 200);
	});

	// 버튼 toggle상태 변경하기
	switch (xeusCCTV.VIDEO_GRID_COLS) {
	case 2:
		xeusCCTV.gridToggleButtons.toggle('#gridpane_btn_x2');
		break;
	case 3:
		xeusCCTV.gridToggleButtons.toggle('#gridpane_btn_x3');
		break;
	case 4:
		xeusCCTV.gridToggleButtons.toggle('#gridpane_btn_x4');
		break;
	}
	//
	$('#'+parentView).find('#gridpane_btn_close').click(function(_dialog) {
		if (xeusCCTV.getGridPanePlayerCount() > 0) {
			xeusLayout.showYesNoDialog("영상그리드 화면 닫기",
					"CCTV영상 그리드 화면을 닫으시겠습니까?</br> 모든 CCTV영상 화면을 닫습니다.",
					function() {
				xeusCCTV.westReLayout();
				xeusCCTV.closeAllGridPanePlayer();
			});
		} else {
			xeusCCTV.westReLayout();
			xeusLayout.hideOverlayEastPane(1000);
			xeusCCTV.VIDEO_GRID = null;
			xeusCCTV.cctv.reload();
		}
	});
	// grid ster생성.
	xeusCCTV.VIDEO_GRID = $("#video_player_grid").gridster({
		widget_base_dimensions : [ _width, _height ],
		widget_margins : [ 2, 3 ],
		min_cols : _cols,
		max_cols : _cols,
		autogrow_cols : false,
		autogrow_rows : true,
		shift_widgets_up : false,
		shift_larger_widgets_down : false,
		collision : {
			wait_for_mouseup : true
		},
		resize : {
			enabled : false,
			max_size : [ 2, 2 ],
			stop : function(e, ui, $widget) {
				console.log("gridster resize stop!");
			}
		},
		serialize_params : function($w, wgd) {
			return {
				id : $w.attr('id'),
				mgrNo : $w.data().mgrNo,
				col : wgd.col,
				row : wgd.row,
				size_x : wgd.size_x,
				size_y : wgd.size_y
			};
		}
	}).data("gridster");

	/**
	 * 그리드의 데이터를 리턴합니다.
	 *
	 * 이주영 2017-11-29
	 */
	xeusCCTV.VIDEO_GRID.getData = function() {
		var result = "";
		if (xeusCCTV.VIDEO_GRID instanceof Gridster) {
			if(xeusCCTV.VIDEO_GRID.serialize().length > 0){
				result = xeusCCTV.VIDEO_GRID.serialize();
			}
		}
		return result;
	};

	// cctv세부 목록에서 symbol을 드롭 처리..
	// Pane에 drop하면 cctv player를 추가한다.
	$('#overlay-east-contents').droppable({
		greedy : true,
		drop : function(event, ui) {
			var _gid = ui.draggable.data("gid");
			// 이미 playerlist에 있으면 return;
			if (xeusCCTV.cctv.existPlayList(_gid)) {
				return;
			}
			// 정상 drop 처리
			ui.draggable.draggable("option", "revert", false);
			// 정상 drop되었으므로 player를 생성한다.
			xeusCCTV.showVideoInGridPane({
				gid : _gid,
				mgrNo : ui.draggable.data("mgrNo"),
				cctvNm : ui.draggable.data("cctvNm"),
				deviceId : ui.draggable.data("deviceId"),
				channelNo : ui.draggable.data("channelNo"),
				gbnCd : ui.draggable.data("gbnCd"),
				gbnTxt : ui.draggable.data("gbnTxt"),
				angle : ui.draggable.data("angle"),
				point : ui.draggable.data("point")
			});
			xeusCCTV.cctv.reload();
		}
	});
};

// gridster widget에서 c,r에 위치한 wedget를 얻는다.
xeusCCTV.findWidjetAt = function(widgets, _c, _r) {
	var _len = widgets.length;
	for (_x = 0; _x < widgets.length; _x++) {
		var _cc = $(widgets[_x]).attr('data-col');
		var _rr = $(widgets[_x]).attr('data-row');
		if (_cc == _c && _rr == _r) {
			return $(widgets[_x]);
		}
	}
	return null;
};

// _id에 해당하는 widget위치를 지정한다.
xeusCCTV.moveWidgetTo = function(widgets, _id, _c, _r) {
	var _len = widgets.length;
	for (_x = 0; _x < widgets.length; _x++) {
		var _wid = $(widgets[_x]).attr('id');
		if (_id == _wid) {
			$(widgets[_x]).attr('data-col', _c);
			$(widgets[_x]).attr('data-row', _r);
			xeusCCTV.VIDEO_GRID.new_move_widget_to($(widgets[_x]), _c, _r);
			break;
		}
	}
}
// 가로 cololum수에 맞게 widget를 재 배열 한다.
xeusCCTV.relocateWidget = function(_newCols, _width) {
	var _oldCols = xeusCCTV.VIDEO_GRID_COLS;
	if (_oldCols == _newCols)
		return;
	// 사이즈 설정
	xeusCCTV.VIDEO_GRID_COLS = _newCols;
	xeusCCTV.VIDEO_GRID.options.min_cols = xeusCCTV.VIDEO_GRID_COLS;
	xeusCCTV.VIDEO_GRID.options.max_cols = xeusCCTV.VIDEO_GRID_COLS;

	xeusLayout.EAST = _width * xeusCCTV.VIDEO_GRID_COLS + 10;
	$('#overlay-east-contents').width(xeusLayout.EAST);

	xeusLayout.reLayout();

	var widgets = xeusCCTV.VIDEO_GRID.get_widgets_from_DOM();
	var _json = xeusCCTV.VIDEO_GRID.serialize();
	if(_json.length > 0){
		var _sorted = Gridster.sort_by_row_and_col_asc(_json);
		var _rows = _sorted.length / _newCols + 1;

		var _cnt = 0;
		for (_r = 1; _r <= _rows; _r++) {
			for (_c = 1; _c <= _newCols; _c++) {
				xeusCCTV.moveWidgetTo(widgets.$widgets, _sorted[_cnt]['id'], _c, _r);
				_cnt++;
				if (_cnt >= _sorted.length)
					break;
			}
			if (_cnt >= _sorted.length)
				break;
		}
		//
		xeusCCTV.VIDEO_GRID.clean_up_changed();
		xeusCCTV.VIDEO_GRID.generate_grid_and_stylesheet();
		xeusCCTV.VIDEO_GRID.init(); // 필수
	}
};

// video grid pane에서 empty slot을 찾는다.
// 신규 생성시 empty slot에 우선 생성한다.
xeusCCTV.getEmptyGridSlot = function(_json) {
	var _ROWS = Math.floor($(window).height() / xeusCCTV.cctv.SIZE.minHeight); // 버림
	var _slot = new Array(_ROWS);
	for (var x = 0; x < _slot.length; x++) {
		_slot[x] = new Array(xeusCCTV.VIDEO_GRID_COLS);
	}
	var _len = _json.length;
	for (var x = 0; x < _len; x++) {
		var _col = _json[x].col - 1;
		var _row = _json[x].row - 1;
		var _sx = _json[x].size_x;
		var _sy = _json[x].size_y;
		// _slot[_json[x].row][_json[x].col] = 'Y';
		for (var _r = _row; _r < (_row + _sy); _r++) {
			for (var _c = _col; _c < (_col + _sx); _c++) {
				// console.log("check : " + _r + " / " + _c);
				_slot[_r][_c] = 'Y';
			}
		}
	}
	for (var _r = 0; _r < _slot.length; _r++) {
		for (var _c = 0; _c < _slot[0].length; _c++) {
			if (_slot[_r][_c] != 'Y') {
				return '[r:' + _r + ',c:' + _c + ']';
			}
		}
	}
	return undefined;
};







///////////////////////////////////////////////





//투망모니터링 CCTV영상을 grid pane에 보여준다.
//CCTV 개별 이벤트
xeusCCTV.showNetMornitorInGridPane = function(_cctv) {

	// 패널 생성은 생략

	//cctvVo에서 필요 파라미터 뺴기
	var netSchGbn = _cctv['netSchGbn'];
	var gid = _cctv['gid'];
	var mgrNo = _cctv['mgrNo'];
	var cctvNm = _cctv['cctvNm'];
	var gbnCd = _cctv['gbnCd'];

	//img태그 src 속성용 링크 생성
	/*var _url = xeusCCTV.ctxPath + "/res/sym/cctv/";
	var imgNm = xeusCCTV.cctv.SYM_ICON[gbnCd]['N'];
	var src = _url + imgNm;*/

	//var src = xeusCCTV.ctxPath + '/sym/getSymbol.do?mgrNo=' + _self.SYM_ICON[gbnCd];

	//title div에 이미지 및 cctv명 추가
	var _html = '';
	//_html += '<img class="xeus-dialog-title-icon" src="'+src+'">';
	_html += '<span class="cctv_title" title="'+cctvNm+'">'+cctvNm+'</span>';
	$('#netGrid').find("#"+netSchGbn).find('.xeus-dialog-title-div').html(_html);

	//영상이 그려질 캔버스에 영상 추가
	var _canvasId = "grid_"  + netSchGbn;
	var _size =  "200x150";

	var _jsmpeg = xeusCCTV.cctv.getMpegPlayer(VIDEO_WEBSOCKET_URL,_canvasId, mgrNo, _size, _cctv.deviceId);

	//투망모니터링 재생 리스트를 별도로 분리
	xeusCCTV.cctv.addNetList(netSchGbn, _cctv, _jsmpeg);
	///////////////////////////////////////////////////////////
	//영상 grid에 재 투망용 데이터 추가
	$('#netGrid').find('#' + netSchGbn).data('gid', gid);
	$('#netGrid').find('#' + netSchGbn).data('mgrNo', mgrNo);
	$('#netGrid').find('#' + netSchGbn).data('gbnCd', gbnCd);


	$("#" + _canvasId + "_div").dblclick(function(){
		if(!document.fullscreenElement){
			$(this).attr("w", $(this).width()).attr("h", $(this).height()).width("100%").height("100%");
			$(this).parent()[0].requestFullscreen();
		}else{
			if(document.exitFullscreen){
				document.exitFullscreen();
				$(this).width($(this).attr("w")).height($(this).attr("h"));
			}
		}
	});

	// html을 처리후 event를 등록해야 함
	// 이미지 클릭 시 해당 CCTV를 기준으로 재 투망
	$('#netGrid').find("#"+netSchGbn).find(".xeus-dialog-title-icon").click(function(){

//		var gid  = $('#netGrid').find("#"+netSchGbn).data('gid');
//		var dist = $('#net-dist-limit').val();
//
//		//영상이 재생되지 않는 CCTV 클릭 시 예외 처리
//		if(gid != undefined){
//			var _param = {};
//			_param['gid'] = gid;
//			_param['dist'] = dist;
//
//			//투망모니터링 패널에 재생중인 영상 멈춤
//			xeusCCTV.stopNetMornitoring();
//
//			//jsmpeg 프로세스가 어느정도 사라질때 쯤 재 투망
//			setTimeout(function(){
//				//html에서 onclick 넣기 위해서 인코딩하기로 했음.
//				_param = encodeURIComponent(JSON.stringify(_param));
//				xeusCCTV.startNetMornitoring(_param);
//			}, 2000);
//
//		}]
//		console.log('11');

	});


	// //////////////////////////////////////////
	// droppable 설정
	// 일단은 drag&drop 옵션은 뺐음.
	// /////////////////////////////////////////
	/*$('#' + _player_grid_id).droppable({
		greedy : true,
		drop : function(event, ui) {
			//아무것도 하지 않음.
		}
	});*/

};

//모니터링 시작 함수
// 전달받은 파라미터(좌표 or cctv gid)를 중심으로 투망모니터링 시작
xeusCCTV.startNetMornitoring = function(_json) {

	if(xeusCCTV.getGridPanePlayerCount() > 0 ){
		alert('그리드모니터링 중엔 기능이 제한됩니다.');
		return;
	}

	xeusCCTV.westReLayout();
	_common.callAjax("/auth/hasEvtAuth.json", {authData : "CCTVPlay"}, function(json){
		if(json.result){
			var _param = JSON.parse(decodeURIComponent(_json));

			if(_param['stateCd'] == "장애"){
				alert('장애 CCTV는 투망모니터링 기능이 지원되지 않습니다.');
				return;
			}

			//$("#center-overlay-west").attr('xeus-event','');
			xeusCCTV.cctv.closeMapOverlay();

			//if(_param['dist'])
			//_param['dist'] = 500;
			xeusCCTV.createNetPane(_param['dist']);

			//if(xeusCCTV.cctv.PLAY_LIST)
			//투망모니터링 외에 재생되고있는 영상들을 모두 닫아야 될거 같음.
			//체크하는 로직을 넣어야 함.
			//xeusCCTV.cctv.closeVideoDialog();
			//xeusCCTV.closeAllGridPanePlayer();

			//투망모니터링 대상 CCTV 목록 리턴(vo 리스트 및 중심좌표가 넘어옴)
			var netCenter;
			if("point" in _param){
				netCenter = _param["point"];
			}else if("srid" in _param){
				netCenter = Spatial.convertProjection([Number(_param.lon), Number(_param.lat)], "EPSG:4326", "EPSG:5186");
			}
			_common.callAjax("/cctv/getNetCctvList.json", _param, function(json){

				//리턴값이 있을때만 실행
				if (json.result != null){

					if(xeusCCTV.vectorLineLayer != null){
						xeusCCTV.vectorLineLayer.getSource().clear();
					}

					//중심점과 선택된 cctv 중 가장 먼 cctv와의 거리를 구한다.
					//구한 dist로 가상 선 생성 및 화면이동 영역을 지정한다.
					var dist = 1000;

					var arrow_features = [];

					//투망모니터링은 기존 모니터링에서 순차 재생되게 설정되었던 setTimeout을 제거
					for(var i=0; i<json.result.length; i++){
						(function(i){
							var cctvParam = json.result[i];
							if(cctvParam.mgrNo != null){
								cctvParam["point"] = Spatial.convertProjection([Number(cctvParam.lng), Number(cctvParam.lat)], "EPSG:4326", "EPSG:5186");
								//반복문을 통해 CCTV 하나씩 영상을 패널에 추가
								xeusCCTV.showNetMornitorInGridPane(cctvParam);
								//지도상에 방향 표시를 위한 피쳐 생성
								arrow_features.push(xeusCCTV.makeArrowFeature(cctvParam));
								//가장 거리가 먼 cctv와의 거리를 저장
								if(dist < cctvParam['dist']) dist = cctvParam['dist'];

								/*_common.callAjax("/getPresetCCTV.json", { "cctvMgrNo" : cctvParam.mgrNo, "tmx" : netCenter[0], "tmy" : netCenter[1] }, function(json){
									var param = {
										 "path" : "gotoPreset",
										 "cctvMgrNo" : json.result.cctv_mgr_no,
										 "presetNo" : json.result.preset_no
									};
									_common.callAjax("/proxy/xeusGateWay.json", param, function(){});
								}, false);*/
							}
						})(i);
					}

					xeusCCTV.vectorPointLayer = xeusCCTV.createPointLayer(arrow_features);
					xeusCCTV.vectorPointLayer.setStyle(function(_feature) {
						var _code = _feature.getProperties().name;
						var features = xeusCCTV.vectorPointLayer.getSource().getFeatures();
						var style = null;
						for(var i=0; i<features.length; i++){
							if(features[i].getProperties().name == _code){
								style = new ol.style.Style({
									image: new ol.style.Icon({
										src: '/xeus/res/img/cctv/cctv_'+_code+'.png'
									})
								});
							}
						}
						if(style == null){
							style = new ol.style.Style({
								image: new ol.style.Icon({
									src: '/xeus/res/img/cctv/cctv_center.png'//null일수가 없지만 혹시 모르므로 center 아이콘 넣음.
								})
							});
						}
						return (style) ? style : null;
					});

					xeusLayout.mapService.addLayer(xeusCCTV.vectorPointLayer);

					//가상의 선을 그릴 때 여유있게 그리기 위해 dist를 늘림.
					dist += 10;
					//투망 모니터링 시의 중심좌표를 얻는다.
					var centerX = Number(json.center.centerX);
					var centerY = Number(json.center.centerY);

					xeusCCTV.vectorLineLayer = xeusCCTV.createLineLayer(centerX, centerY, dist);
					//레이어 등록
					xeusLayout.mapService.addLayer(xeusCCTV.vectorLineLayer);

					var radius = Number(json.center.dist);

					if(radius > 0){
						xeusCCTV.vectorCircleLayer = xeusCCTV.createCircleLayer(centerX, centerY, radius);
						xeusLayout.mapService.addLayer(xeusCCTV.vectorCircleLayer);
					}

					/////////////////////////////////////////////////////////////////

					//화면에 보일 영역은 그려진 가상의 선보다 넓게 보여야 보기 좋음.
					//일정 값을 더 더함.
					dist += 20;

					//중심 좌표로부터 계산된 dist만큼 떨어진 영역을 구한다.
					var extent = [centerX - dist, centerY - dist, centerX + dist, centerY + dist];
					//구한 영역으로 화면을 이동한다.
					xeusLayout.mapService.getMap().getView().fit( extent, xeusLayout.mapService.getMap().getSize() );

					//CCTV 데이터를 reload한다.
					//reload해야 지도화면에 재생 CCTV의 아이콘이 변경 됨.
					xeusCCTV.cctv.reload();
				}
			});

		}else{
			alert("<br>CCTV 재생 권한이 존재하지 않습니다.");
		}
	}, false);

};

//현재 실행중인 투망모니터링 초기화
//영상재생 정보 및 재생화면, jsmpeg 프로세스 초기화
xeusCCTV.stopNetMornitoring = function() {
	//투망모니터링 화면의 영상 div로 반복문을 수행
	$('#netGrid').find('.area').each(function(){
		//data에 포함되어있는 gid를 얻음.
		var gid = $(this).data('gid');

		//타이틀 div의 하위 태그(타이틀, img태그) 삭제
		//투망모니터링 시작 시 어차피 생성됨.
		$(this).find('.xeus-dialog-title-div').empty();

		//재생이 되지 않는 패널엔 gid가 undefined이므로 체크 후 삭제하여야 함.
		//체크 안하면 오류 발생
		if (gid != undefined) xeusCCTV.cctv.removeNetList(gid);

		//재생중인 div의 하위 목록도 삭제
		$(this).find('.grid_parent').empty();

		//canvas 태그 다시 생성
		_html = "";
		_html += '<canvas id="grid_'+$(this).attr('id')+'" width="200" height="150">';
		_html += '</canvas>';
		$(this).find('.grid_parent').html(_html);
		//기존에 들어있던 데이터 삭제
		$(this).removeData();
	});

	//지도상에 그려져 있는 가상 레이어 삭제
	xeusLayout.mapService.removeLayer(xeusCCTV.vectorPointLayer);
	xeusLayout.mapService.removeLayer(xeusCCTV.vectorLineLayer);
	//생성 안됐을 수도 있으니 체크
	if(xeusCCTV.vectorCircleLayer)
		xeusLayout.mapService.removeLayer(xeusCCTV.vectorCircleLayer);

	//cctv reload(지도화면의 cctv 영상 재생 아이콘 삭제)
	xeusCCTV.cctv.reload();

};

//어디다 둬야할 지 몰라서 투망모니터링 패널 생성 시 종료아이콘 이벤트를 여기다가 만듬.
//사용 안함. createNetPane 만들면서 거기다가 넣었음.
xeusCCTV.setCloseEventInNetPane = function (){
	$('#gridpane_btn_close').click(function(_dialog) {
		//현재 투망모니터링 영상 재생중이면 체크 후 모니터링 멈춤 및 패널 숨김
		if (xeusCCTV.getNetPlayerCount() > 0) {
			xeusLayout.showYesNoDialog("투망모니터링 종료",
					"투망모니터링 화면을 닫으시겠습니까?</br> 모든 CCTV영상 화면을 닫습니다.",
					function() {
				xeusCCTV.stopNetMornitoring();
				xeusLayout.hideOverlayEastPane(1000);
			});
		} else {
			xeusLayout.hideOverlayEastPane(1000);
		}
	});
};

//사용
xeusCCTV.createNetPane = function() {

	var _param = {};
	//console.log(arguments[0]);
	if(arguments[0]){
		_param['dist'] = arguments[0];
	}

	_common.callAjax("/cctv/getNetView.do", _param, function(view) {

		xeusLayout.hideOverlayEastPane(0);

		//$("#center-overlay-west").attr('xeus-event','btn-cctv-net');
		$('#'+parentView).find("#center-overlay-east").html(view);
		xeusCCTV.resizeNetPane();
		xeusLayout.showOverlayEastPane(ANI_DELAY,function(){});

		$('#'+parentView).find('#gridpane_btn_close').click(function(_dialog) {
			//현재 투망모니터링 영상 재생중이면 체크 후 모니터링 멈춤 및 패널 숨김
			xeusCCTV.westReLayout();
			if (xeusCCTV.getNetPlayerCount() > 0) {
				xeusLayout.showYesNoDialog("투망모니터링 종료",
						"투망모니터링 화면을 닫으시겠습니까?</br> 모든 CCTV영상 화면을 닫습니다.",
						function() {
					xeusCCTV.stopNetMornitoring();
					xeusLayout.hideOverlayEastPane(1000);
				});
			} else {
				xeusLayout.mapService.removeLayer(xeusCCTV.vectorPointLayer);
				xeusLayout.mapService.removeLayer(xeusCCTV.vectorLineLayer);
				//생성 안됐을 수도 있으니 체크
				if(xeusCCTV.vectorCircleLayer){
					xeusLayout.mapService.removeLayer(xeusCCTV.vectorCircleLayer);
				}
				xeusLayout.hideOverlayEastPane(1000);
			}
		});
	}, false);
};

//WEST 레이아웃을 메뉴에 따라서 재조정한다.
xeusCCTV.westReLayout = function() {
	$("#eventView").find(".overlay-side-bar .menu-button").each(function(index,item){
	    if($(this).attr('active')=='active'){
	        switch($(this).attr('id')){
	            case 'btn-monitor-view' :
	                xeusLayout.WEST=EVNT_BTN_MONITOR_VIEW_WEST_SIZE;
	                break;
	            case 'btn-cctv-sch' :
	                xeusLayout.WEST=CCTV_BTN_CCTV_SCH_WEST_SIZE;
	                break;
	            case 'btn-eventwait-list-view' :
	                xeusLayout.WEST=EVNT_BTN_MONITOR_VIEW_WEST_SIZE;
	                break;
	            case 'btn-ptr-view' :
	                xeusLayout.WEST=CCTV_BTN_PTR_VIEW_EAST_SIZE;
	                break;
	            case 'btn-event-list-view' :
	                xeusLayout.WEST=EVNT_BTN_MONITOR_VIEW_WEST_SIZE;
	                break;
	        }
	    }
	});
};




//현재 투망모니터링 영상 재생 갯수를 리턴한다.
xeusCCTV.getNetPlayerCount = function() {
	var netList = xeusCCTV.cctv.getNetList();

	return Object.keys(netList).length;
};

//기존 우측패널 영상 모니터링의 사이즈 변경은 사용 불가, 새로 만듬
xeusCCTV.resizeNetPane = function() {

	var _width = xeusCCTV.cctv.SIZE['minWidth'] + 2;
	xeusLayout.EAST = _width * 3 + 10;
	$('#'+parentView).find('#overlay-east-contents').width(xeusLayout.EAST);
	$('#'+parentView).find("#center-overlay-east").width(xeusLayout.EAST);
	$('#'+parentView).find("#center-overlay-east").css({left: $(window).width() - xeusLayout.EAST});

	//$("#center-overlay-east").css({width: xeusLayout.EAST, left: $(window).width() - xeusLayout.EAST});

	xeusLayout.reLayout();
};


xeusCCTV.makeLineStringFeature = function(start_x, start_y, end_x, end_y){

	//ol로 포인트 객체 만들어서 linestring 만든다고 까불면 안됨. (ex. var point = new ol.geom.Point([[1,2],[3,4]]); )
	//그냥 밑의 선언처럼 배열같이 만들어야 정상 작동함.
	var points = [
				    [ start_x, start_y ],
				    [ end_x, end_y ]
			     ];

	return new ol.Feature({
		geometry: new ol.geom.LineString(points)
	});
}

xeusCCTV.createLineLayer = function(centerX, centerY, dist){
	////////////////////////////////////
	//지도화면에 가상의 선을 그린다.
	////////////////////////////////////
	//수직, 수평, 2개의 대각선, 총 4개의 feature를 만든다.
	var vertical_feature = xeusCCTV.makeLineStringFeature( centerX, centerY - dist, centerX, centerY + dist );
	var horizontal_feature = xeusCCTV.makeLineStringFeature( centerX - dist, centerY, centerX + dist, centerY );
	var first_diagonal_feature = xeusCCTV.makeLineStringFeature( centerX - dist, centerY - dist, centerX + dist, centerY + dist );
	var second_diagonal_feature = xeusCCTV.makeLineStringFeature( centerX - dist, centerY + dist, centerX + dist, centerY - dist );

	//벡터 레이어용 스타일 생성
	var style = new ol.style.Style({
		/*fill: new ol.style.Fill({
			color: 'rgba(255, 54, 14, 0.5)'
		}),*/
		stroke: new ol.style.Stroke({
			color: 'rgba(255, 54, 14, 0.8)',
			width: 3,
			lineDash: [.1, 5]
		})
	});

	//source 생성
	var lineSource = new ol.source.Vector({
		wrapX: false
	});

	//source.addFeatures(vertical_feature, horizontal_feature, first_diagonal_feature, second_diagonal_feature);
	//addFeatures가 안먹혀서 그냥 개별로 넣음.
	lineSource.addFeature(vertical_feature);
	lineSource.addFeature(horizontal_feature);
	lineSource.addFeature(first_diagonal_feature);
	lineSource.addFeature(second_diagonal_feature);

	//벡터 레이어 생성
	//만들어 놓은 벡터 소스로 벡터 레이어를 생성한다.
	//스타일 적용
	return new ol.layer.Vector({
		name: "net_line",
		zIndex : 999999,
		type: "MULTILINESTRING",
	    source: lineSource,
	    style: style
	});
}

xeusCCTV.makeCircleFeature = function(x, y, radius){

	//중심점 생성
	var center = [ x, y ];

	return new ol.Feature({
		geometry: new ol.geom.Circle(center, radius)
	});
}

xeusCCTV.createCircleLayer = function(centerX, centerY, radius){
	//반경 표시 원 생성
	var circle_feature = xeusCCTV.makeCircleFeature(centerX, centerY, radius);

	//source 생성
	var circleSource = new ol.source.Vector({
		wrapX: false
	});

	circleSource.addFeature(circle_feature);

	//벡터 레이어용 스타일 생성
	var style = new ol.style.Style({
		/*fill: new ol.style.Fill({
			color: 'rgba(255, 54, 14, 0.5)'
		}),*/
		stroke: new ol.style.Stroke({
			color: 'rgba(255, 54, 14, 0.8)',
			width: 3,
			lineDash: [.1, 5]
		})
	});

	//벡터 레이어 생성
	//만들어 놓은 벡터 소스로 벡터 레이어를 생성한다.
	//스타일 적용
	return new ol.layer.Vector({
		name: "net_circle",
		zIndex : 999999,
		//type: "MULTILINESTRING",
	    source: circleSource,
	    style: style
	});
}

xeusCCTV.makeArrowFeature = function(cctvParam){
	return new ol.Feature({
		geometry: new ol.geom.Point(cctvParam['point']),
		name: cctvParam['netSchGbn']
	});
}

xeusCCTV.createPointLayer = function(features){
	var pointSource = new ol.source.Vector({
		wrapX: false
	});

	for(var i=0; i<features.length; i++){
		pointSource.addFeature(features[i]);
	}

	//벡터 레이어 생성
	//만들어 놓은 벡터 소스로 벡터 레이어를 생성한다.
	//스타일 적용
	return new ol.layer.Vector({
		name: "net_point",
		zIndex : 999999,
		type: "POINT",
	    source: pointSource
	});
}

/////////////////////////////////////////////////
/*
 * 180411 CCTV 속성정보 조회 기능 추가
 */
//암호화된 cctv정보를 복호화하여 bPopup으로 속성정보 표출
xeusCCTV.viewCctvInfo = function(_json) {

	var _param = JSON.parse(decodeURIComponent(_json));

	_common.callAjax("/cctv/getCctv.json", _param, function(json){

		if (json.result != null){

			//console.log(json.result);

			/*
			cctvNm:"중앙공원입구2"
			channelNo:"0"
			deviceId:"402513"
			gbnCd:"19"
			gbnTxt:"시설물관리"
			infrdYn:"N"
			lat:"35.9429802572225867"
			lightYn:"N"
			lng:"127.126948746148173"
			tiltYn:"N"
			turnYn:"N"
			useYn:"Y"
			viewDir:"0"
			zoomYn:"N"
			 */
			var _html = '';

			_html += '<div id="cctvInfo" class="bPopup" style="display: none;"> ';
			_html += '    <div id="title-bar"> ';// style="margin-left: 10px;"
			_html += '        <button id="closeBtn" style="float: right;"><img src="'+xeusCCTV.ctxPath + '/res/img/close_btn.png"/></button> ';
			_html += '        <p class="title">CCTV 정보</p> ';
			_html += '    </div> ';
			_html += '    <table class="list"> ';// cellspacing="0" width="100%" style="margin-top:0;"
			_html += '        <thead> ';
			_html += '        <colgroup> ';
			_html += '            <col width="70px" /> ';
			_html += '	          <col width="" /> ';
			_html += '        </colgroup> ';
			_html += '        </thead> ';
			_html += '        <tbody> ';
			_html += '        <tr> ';
			_html += '            <th><label>CCTV 명</label></th> ';
			_html += '            <td><label>' + json.result.cctvNm + '</label></td> ';
			_html += '        </tr> ';
			_html += '        <tr> ';
			_html += '            <th><label>설치 목적</label></th> ';
			_html += '            <td><label>' + json.result.gbnTxt + '</label></td> ';
			_html += '        </tr> ';
			_html += '        <tr> ';
			_html += '            <th><label>위치</label></th> ';
			_html += '            <td><label>' + json.result.lat + '\r\n' + json.result.lng + '</label></td> ';
			_html += '        </tr> ';
			/*_html += '        <tr> ';
			_html += '            <th><label>옵션</label></th> ';
			_html += '            <td style="padding: 0px;"> ';

			_html += '                <table class="option"> ';
			_html += '                    <tr> ';
			_html += '                        <td> ';
			_html += '                            <input type="checkbox" id="useYn"'; if(json.result.useYn == "Y") _html += ' checked="checked" '; _html += ' disabled> ';
			_html += '				              <label for="useYn">사용</label> ';
			_html += '                        </td> ';
			_html += '                        <td> ';
			_html += '                            <input type="checkbox" id="turnYn"'; if(json.result.turnYn == "Y") _html += ' checked="checked" '; _html += ' disabled> ';
			_html += '				              <label for="useYn">회전</label> ';
			_html += '                        </td> ';
			_html += '                        <td> ';
			_html += '                            <input type="checkbox" id="lightYn"'; if(json.result.lightYn == "Y") _html += ' checked="checked" '; _html += ' disabled> ';
			_html += '				              <label for="useYn">조명</label>';
			_html += '                        </td> ';
			_html += '                    </tr> ';
			_html += '                    <tr> ';
			_html += '                        <td> ';
			_html += '                            <input type="checkbox" id="infrdYn"'; if(json.result.infrdYn == "Y") _html += ' checked="checked" '; _html += ' disabled> ';
			_html += '				              <label for="useYn">적외선</label> ';
			_html += '                        </td> ';
			_html += '                        <td> ';
			_html += '                            <input type="checkbox" id="tiltYn"'; if(json.result.tiltYn == "Y") _html += ' checked="checked" '; _html += ' disabled> ';
			_html += '				              <label for="useYn">틸트</label> ';
			_html += '                        </td> ';
			_html += '                        <td> ';
			_html += '                            <input type="checkbox" id="zoomYn"'; if(json.result.zoomYn == "Y") _html += ' checked="checked" '; _html += ' disabled> ';
			_html += '				              <label for="useYn">줌</label> ';
			_html += '                        </td> ';
			_html += '                    </tr> ';
			_html += '                    <tr> ';
			_html += '                        <td> ';
			_html += '                            <input type="checkbox" id="talkYn"'; if(json.result.talkYn == "Y") _html += ' checked="checked" '; _html += ' disabled> ';
			_html += '				              <label for="useYn">음성지원</label> ';
			_html += '                        </td> ';
			_html += '                        <td> ';
			_html += '                            <input type="checkbox" id="tourYn"'; if(json.result.tourYn == "Y") _html += ' checked="checked" '; _html += ' disabled> ';
			_html += '				              <label for="useYn">투어링</label> ';
			_html += '                        </td> ';
			_html += '                        <td></td> ';
			_html += '                    </tr> ';
			_html += '                </table> ';
			_html += '			</td> ';
			_html += '        </tr> ';*/
			_html += '        </tbody> ';
			_html += '    </table> ';
			_html += '</div> ';

			$('#layout-body').append(_html);

			$('#cctvInfo').bPopup({
				appendTo: $('#'+parentView),
				onOpen: function() {
					$('#cctvInfo').find('#closeBtn').click(function(){
						$('#cctvInfo').bPopup().close();
						$('.bPopup').remove();
					});
				},
				onClose: function() {
					$('.bPopup').remove();
				}

			});

			//console.log(json.result.cctvNm);
		}

	});
};

/*
 * 180506 벡터 속성정보 조회 기능 추가
 */
xeusCCTV.viewVectorInfo = function(prop) {

	var columnNm = {};

	columnNm['typename'] = '타입명';
	columnNm['sym_cd'] = '구분코드';
	columnNm['juso'] = '주소';
	columnNm['buld_nm'] = '명칭';
	columnNm['_annoy'] = '좌표Y';
	columnNm['_annox'] = '좌표X';
	columnNm['_gid'] = '일련번호';

	var isError = "isError" in prop;

	var _html = '';

	_html += '<div id="vectorInfo" class="bPopup" style="display: none;"> ';
	_html += '    <div id="title-bar"> ';// style="margin-left: 10px;"
	_html += '        <button id="closeBtn" style="float: right;"><img src="'+xeusCCTV.ctxPath + '/res/img/close_btn.png"/></button> ';
	_html += '        <p class="title">상세 정보</p> ';
	_html += '    </div> ';
	_html += '    <table class="list"> ';// cellspacing="0" width="100%" style="margin-top:0;"
	_html += '        <thead> ';
	_html += '        <colgroup> ';
	_html += '            <col width="70px" /> ';
	_html += '	          <col width="" /> ';
	_html += '        </colgroup> ';
	_html += '        </thead> ';
	_html += '        <tbody> ';

	var colNm = '';
	for(var k in prop){
		if(k != "geometry" && k != "img_path" && k != "popup" && k != "target_field"){
			colNm = k;
			if( columnNm[k] != null ) colNm = columnNm[k];
			_html += '        <tr> ';
			//_html += '            <th><label>' + k + '</label></th> ';
			_html += '            <th><label>' + colNm + '</label></th> ';
			_html += '            <td><label>' + prop[k] + '</label></td> ';
			_html += '        </tr> ';
		}
	}

	// TODO 상태 체크하여 리스트 추가. 하지만 너무 긴 정보일경우 다른부분에 해야함. 컨펌 필요.
	if(isError){
		_common.callAjax("/nms/getStatusList.json", { "mgrNo" : "INF0000004" }, function(json){
			console.log(json);
		});
	}

	_html += '        </tbody> ';
	_html += '    </table> ';
	_html += '</div> ';

	$('#layout-body').append(_html);

	$('#vectorInfo').bPopup({
		appendTo: $('#'+parentView),
		onOpen: function() {
			$('#vectorInfo').find('#closeBtn').click(function(){
				$('#vectorInfo').bPopup().close();
				$('.bPopup').remove();
			});
		},
		onClose: function() {
			$('.bPopup').remove();
		}

	});

};

xeusCCTV.calculAngle = function(prop){
	var pan = Number(parseFloat(prop.pan).toFixed(1));

	var list = [-1, -0.9, -0.8, -0.7, -0.6, -0.5, -0.4, -0.3, -0.2, -0.1, 0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1];
	var idx = list.indexOf(pan) + 1;
	var num = 360 / list.length;
	var dirDeg = idx * num;

	return dirDeg;
}

/*
 * 181010 영상 공유
 */
xeusCCTV.shareCCTV = function(_cctv) {

	var gid = _cctv['gid'];
	var mgrNo = _cctv['mgrNo'];
	var cctvNm = _cctv['cctvNm'];
	var deviceId = _cctv['deviceId'];
	var channelNo = _cctv['channelNo'];
	var gbnCd = _cctv['gbnCd'];
	var gbnTxt = _cctv['gbnTxt'];
	var angle = _cctv['angle'];
	var point = _cctv['point'];

	var _html = '';
	_html += '<div id="previewInfo" class="bPopup" style="display: none;"> ';
	_html += '    <div id="title-bar"> ';// style="margin-left: 10px;"
	_html += '        <button id="closeBtn" style="float: right;"><img src="' + xeusCCTV.ctxPath + '/res/img/close_btn.png"/></button> ';
	_html += '        <p class="title">영상 공유</p> ';
	_html += '    </div> ';
	_html += '    <table class="list"> ';// cellspacing="0" width="100%" style="margin-top:0;"
	_html += '      <thead> ';
	_html += '        <colgroup> ';
	_html += '            <col width="100px" /> ';
	_html += '	          <col width="" /> ';
	_html += '	          <col width="" /> ';
	_html += '        </colgroup> ';
	_html += '      </thead> ';
	_html += '      <tbody> ';
	_html += '        <tr class="hidden"> ';
	_html += '            <th><label>대상 CCTV</label></th> ';
	_html += '            <td><input type="text" class="sendData wide" id="mgrNo" value="' + mgrNo + '" readOnly></td> ';
	_html += '        </tr> ';
	_html += '        <tr> ';
	_html += '            <th><label>대상 CCTV</label></th> ';
	_html += '            <td><input type="text" class="sendData wide" id="cctvNm" value="' + cctvNm + '" readOnly></td> ';
	_html += '        </tr> ';
	_html += '        <tr> ';
	_html += '            <th><label>공유 대상그룹</label></th> ';
	_html += '            <td> ';
	_html += '            	  <select class="sendData wide" id="authGrpNo"> ';
	_html += "					  <option value=''>전체</option>";
	_common.callAjax("/auth/getGrpList.json", {}, function(json){
		for(var i=0; i<json.count; i++){
			_html += "			  <option value='" + json.result[i].authGrpNo + "'>" + json.result[i].authGrpNm + "</option>";
		}
	}, false);
	_html += '            	  </select> ';
	_html += '            </td> ';
	_html += '        </tr> ';
	_html += '        <tr> ';
	_html += '            <th><label>공유 사유</label></th> ';
	_html += '            <td><input type="text" class="sendData wide" id="shareReason"></td> ';
	_html += '        </tr> ';
	_html += '        <tr> ';
	_html += '            <th colspan="2"><button id="saveBtn">공유 시작</button></th> ';
	_html += '        </tr> ';
	_html += '      </tbody> ';
	_html += '    </table> ';
	_html += '</div> ';

	$('#layout-body').append(_html);

	$('#previewInfo').bPopup({
		appendTo: $('#'+parentView),
		onOpen: function() {
			$('#previewInfo').find('#closeBtn').click(function(){
				$('#previewInfo').bPopup().close();
				$('.bPopup').remove();
			});

			$('#previewInfo').find('#saveBtn').click(function(){
				var lonlat = Spatial.convertProjection(point, "EPSG:5186", "EPSG:4326");

				var _Json = xeusJsonParser.getTemplate();

				_Json.statEvetTypCd = "CCTVSHER";
				_Json.statEvetNm = "CCTV 영상 공유";
				_Json.statEvetCntn = $("#shareReason").val();
				_Json["x"] = String(lonlat[0]);
				_Json["y"] = String(lonlat[1]);
				_Json["tmx"] = String(point[0]);
				_Json["tmy"] = String(point[1]);
				_Json.statEvetOutbDtm = new Date().getYMDHMS();
				_Json.statEvetClrDtm = "";
				_Json.statEvetActnMn = "";
				_Json.statEvetActnCntn = "";
				_Json.procSt = "10";
				_Json.isTest = "N";
				_Json.uSvcOutbId = "";
				_Json.statEvetSvcTyp = "CCTVSHER";
				_Json.outbPosNm = cctvNm + "(고유번호 : " + gid + ")";
				_Json.outbPos[0].x = String(lonlat[0]);
				_Json.outbPos[0].y = String(lonlat[1]);
				_Json.etcCntn = JSON.stringify(_cctv);

				_Json.targetGrp = $("#authGrpNo").val();

				confirm("저장하시겠습니까?", function(){
					_common.callAjax("/ws/addEvent.json", { "json" : JSON.stringify(_Json) }, function(json){
						if(json.result == true){
							$('#previewInfo').bPopup().close();
						}
					});
				});
			});
		},
		onClose: function() {
			$('.bPopup').remove();
		}
	});

};
