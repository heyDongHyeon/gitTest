// 클래스 namespace만들기.
if (window.geomex == null)
	var geomex = {}

if (geomex.xeus == null) {
	geomex.xeus = {}
}

// WMS 레이어 정보
geomex.xeus.LayerInfo = function(zIndex, name, visible, style, cql) {
	this.zIndex = zIndex;
	this.name = name; // 레이어명
	this.visible = visible;
	this.style = style;
	this.cql = cql;

	this.toString = function() {
		return this.zIndex + " : " + this.name + " : " + this.visible;
	};
};

/**
 * 레이어 생성 및 관리하는 클래스
 *
 * @param options
 */
geomex.xeus.MapService = function(options) {
	var ctxPath = options.ctxPath;
	var target = options.target;
	var _self = this;

	// OpenLayers Map객체 생성..
	var mapObject = new ol.Map({
		controls : ol.control.defaults().extend(options.controls),
		logo : false,
		renderer : 'canvas', // 'canvas' or 'webgl'
		target : target, // 'xeus-map-content',
		layers : [],
		loadTilesWhileInteracting : true,
		loadTilesWhileAnimating : true,
		view : new ol.View({
			projection : options.projection,
			center : options.center,
			zoomFactor : 2,
			zoom : options.zoom, // 클수록 확대
			minZoom : options.minZoom,
			maxZoom : options.maxZoom
		})
	});


	// mapObject를 반환한다.
	this.getMap = function() {
		return mapObject;
	};

	// map에 Layer를 추가한다.
	this.addLayer = function(lyr) {
		//a = lyr;
		mapObject.addLayer(lyr);
	};

	// map에 Layer를 제거한다.
	this.removeLayer = function(lyr) {
		mapObject.removeLayer(lyr);
	};

	/**
	 * layer name의 레이어를 삭제한다.
	 */
	this.removeNameLayer = function(name) {
		var _layers = this.getAllLayers();
		for(var i=0; i<_layers.length; i++){
			if(_layers[i] != null){
				var _val = _layers[i].get("name");
				if(_val === name){
					console.log('삭제 : ' + _val);
					mapObject.removeLayer(_layers[i]);
				}
			}
		}
	};
	/**
	 * layer name의 레이어를 삭제한다.
	 */
	this.removeNameLayer = function(name) {
		var _layers = this.getAllLayers();
		for(var i=0; i<_layers.length; i++){
			if(_layers[i] != null){
				var _val = _layers[i].get("name");
				if(_val === name){
					console.log('삭제 : ' + _val);
					mapObject.removeLayer(_layers[i]);
				}
			}
		}
	};

	// TileWMSLayer에 레이어정보를 추가한다.
	// 테스트 필요
	this.addTileWMSLayer = function(_wmsLyrName, _info) {
		var _lyr = this.getLayerByName(_wmsLyrName);
		var _infos = _lyr.get('lyrInfos');
		_infos.push(_info);
		//
		var param = _getWMSParams(_infos);
		_checkWMSParamVisible(_lyr, param);
	};

	// WMS레이어내 레이어정보를 제거한다.
	// WMS는 WMS레이어 안에 각 레이어 정보가 있음
	// 테스트 필요
	this.removeTileWMSLayer = function(_wmsLyrName, _name) {
		var _lyr = this.getLayerByName(_wmsLyrName);
		var _infos = _lyr.get('lyrInfos');
		for ( var i in _infos) {
			if (_infos[i].name == _name) {
				_infos.splice(i, 1);
				break;
			}
		}
		var param = _getWMSParams(_infos);
		_checkWMSParamVisible(_lyr, param);
	};

	// 지도화면을 좌표로 이동한다.
	this.moveToAnimation = function(_delay, _pt) {
			mapObject.getView().animate({
			center : _pt,
			duration : _delay
		});
	};

	// 램던 생성기
	function _rand(min, max) {
		return min + Math.random() * (max - min);
	}

	// 랜덤칼라 생성기
	this.randomColor = function() {
		var h = _rand(1, 360);
		var s = _rand(0, 100);
		var l = _rand(0, 100);
		return 'hsl(' + h + ',' + s + '%,' + l + '%)';
	}

	function _checkWMSParamVisible(_lyr, _param) {
		if (_param == null) {
			_lyr.setVisible(false);
		} else {
			_lyr.setVisible(true);
			_lyr.getSource().updateParams(_param);
		}
	}
	// tilewms타입 레이어 정보로 WMS 요청 Param을 생성한다.
	// 내부 호출 함수
	// LayerInfo 설정정보로 레이어를 생성하는 로직보다 앞에 있어야 함
	function _getWMSParams(tilewms) {
		var wmsLayer = tilewms;
		var isFirst = true;
		var strFilter = "";
		var strLayer = "";
		var strStyle = "";

		var visibleCount = 0;

		wmsLayer.forEach(function(lyr) {
			if (lyr.visible) {
				visibleCount++;
				if (!isFirst) {
					strLayer += ",";
					strStyle += ",";
					strFilter += ";";
				}
				strLayer += lyr.name;
				strStyle += lyr.style;
				strFilter += lyr.cql;
				isFirst = false;
			}
		});
		// visibleCount가 없으면 null을 리턴한다.
		var params = null;
		if (visibleCount > 0) {
			params = {
				'LAYERS' : strLayer,
				'STYLES' : strStyle,
				'CQL' : strFilter,
				'FORMAT' : 'image/png',
				'TRANSPARENT' : 'TRUE'
			}
		}
		return params;
	}

	// Tile Layer를 생성한다.
	this.createTileWMSLayer = function(_opt) {
		var _name = '_TileWMSLayer';
		if (_opt != null && _opt.name != null) {
			_name = _opt.name;
		}

		var param = _getWMSParams(_opt.layerInfo);
		var lyr = new ol.layer.Tile({
			name : _name,
			lyrInfos : _opt.layerInfo, // tilemap 레이어 관리용.
			source : new ol.source.TileWMS({
				url : ctxPath + '/wms',
				params : param
			})
		});
		// wms param이 없으면 wmslayer를 disable한다.
		if (param == null) {
			lyr.setVisible(false);
		}
		return lyr;
	};

	// WFS 레이어를 생성한다. BBOX를 사용하여 자동으로 데이터 수신.
	// 화면 동작이 매끄럽지 않음
	this.createWFSLayer = function(_opt) {
		//console.log(_opt);
		var wfsurl = _opt.url;
		var lyrName = _opt.name;
		var _zIdx = _opt.zIndex;
		var type = _opt.type;
		var group = _opt.group;
		var fnGroup = _opt.fnGroup;
		var typeName = _opt.typeName;
		var fullName = "";
		if(typeName != null && typeName != "") fullName = typeName.replace("gmx:", "");
		var visible = _opt.visible;

		var style = new ol.style.Style({
			stroke : new ol.style.Stroke({
				color : this.randomColor(),
				width : 2
			})
		});

		var _minResolution = 0;
		var _maxResolution = Infinity;

		if (_opt != null && _opt.style != null) {
			style = _opt.style;
		}
		if (_opt != null && _opt.minResolution != null) {
			_minResolution = _opt.minResolution;
		}
		if (_opt != null && _opt.maxResolution != null) {
			_maxResolution = _opt.maxResolution;
		}

		var lyr = new ol.layer.Vector({
			name : lyrName,
			visible : visible,
			updateWhileAnimating : false,
			updateWhileInteracting : false,
			/* 이주영 추가 2017-06-07 */
			type : type,
			zIndex : _zIdx,
			fullName : fullName,
			minResolution : _minResolution,
			maxResolution : _maxResolution,
			group : group,
			fnGroup : fnGroup,

			source : new ol.source.Vector({
				strategy : ol.loadingstrategy.bbox,
				loader : function(extent, resolution, projection) {
					var _source = this;
					var _epsg = projection.getCode();
					var _format = new ol.format.GeoJSON();
					var _wfsParam = {
						service : 'WFS',
						version : '1.1.0',
						request : 'GetFeature',
						typename : typeName,
						outputFormat : 'json',
						srsname : _epsg,
						bbox : extent.join(',') + ',' + _epsg
					}
					$.ajax({
						url : wfsurl,
						//type : 'POST',
						data : _wfsParam,
						dataType : 'json',
						beforeSend : function() {
							_source.clear();
//							alert("Before Load: " + _source.getFeatures().length);
						},
						success : function(data) {
							var features = _format.readFeatures(data);
							_source.addFeatures(features);
//							alert("After Load: " + _source.getFeatures().length);
						},
						error : function(xhr, status, error) {
							var _xmlDoc = $.parseXML(xhr.responseText);
							var code = $(_xmlDoc).find("ExceptionText").text();
//							alert("WFS 요청중 오류가 발생하였습니다. > \r\n" + code);
						}
					});
				}
			}),
			style : style
		});
		return lyr;
	};

	// Vector Layer를 생성한다.
	this.createVectorLayer = function(_opt) {
		var _style = new ol.style.Style();
		var _source = new ol.source.Vector({
			strategy : ol.loadingstrategy.bbox
		});

		var _minResolution = 0;
		var _maxResolution = Infinity;
		var _zIdx = _opt.zIndex;
		var type = _opt.type;
		var group = _opt.group;
		var fnGroup = _opt.fnGroup;
		var fullName = _opt.typeName.replace("gmx:", "");

		if (_opt != null && _opt.style != null) {
			_style = _opt.style;
		}
		if (_opt != null && _opt.source != null) {
			_source = _opt.source;
		}
		if (_opt != null && _opt.minResolution != null) {
			_minResolution = _opt.minResolution;
		}
		if (_opt != null && _opt.maxResolution != null) {
			_maxResolution = _opt.maxResolution;
		}

		var lyr = new ol.layer.Vector({
			name : _opt.name,
			visible : _opt.visible,
			updateWhileAnimating : true,
			updateWhileInteracting : true,
			source : _source,
			style : _style,

			/* 이주영 추가 2017-06-07 */
			type : type,
			zIndex : _zIdx,
			fullName : fullName,
			minResolution : _minResolution,
			maxResolution : _maxResolution,
			group : group,
			fnGroup : fnGroup
		});
		return lyr;
	};

	// Heat Layer를 생성한다.
	this.createHeatLayer = function(_opt) {
		var _style = new ol.style.Style();
		var _source = new ol.source.Vector();

		var _minResolution = 0;
		var _maxResolution = Infinity;
		var _zIdx = _opt.zIndex;
		var type = _opt.type;
		var group = _opt.group;
		var fnGroup = _opt.fnGroup;

		if (_opt != null && _opt.style != null) {
			_style = _opt.style;
		}
		if (_opt != null && _opt.source != null) {
			_source = _opt.source;
		}
		if (_opt != null && _opt.minResolution != null) {
			_minResolution = _opt.minResolution;
		}
		if (_opt != null && _opt.maxResolution != null) {
			_maxResolution = _opt.maxResolution;
		}

		var heatMap = new ol.layer.Heatmap({
			opacity: 0.9,

			name : _opt.name,
			visible : _opt.visible,
			updateWhileAnimating : true,
			updateWhileInteracting : true,
			source : _source,
			style : _style,

			/* 이주영 추가 2017-06-07 */
			type : type,
			zIndex : _zIdx,
			minResolution : _minResolution,
			maxResolution : _maxResolution,
			group : group,
			fnGroup : fnGroup
		});

		return heatMap;
	};

	// 레이어 visible를 설정한다.
	// wms인 경우 param을 뒤져서 정리한다.
	/**
	 * 이주영 2017-06-08
	 * visible 옵션이 없을 경우 자동으로 토글 하도록 변경.
	 */
	this.setLayerVisible = function(lyrName, visible) {
		var _layers = mapObject.getLayers();
		for (var x = 0; x < _layers.getLength(); x++) {
			var _lyr = _layers.item(x);
			var _name = _lyr.get('name');
			var _fullName = _lyr.get('fullName');
			var _isVisible = _lyr.getVisible();
			if(visible == null){
				visible = true;
				if(_isVisible) visible = false;
			}
			if (lyrName == _name) {
				_lyr.setVisible(visible);
				Layers[_fullName].visible = visible;
				return;
			} else if (_lyr.get('lyrInfos')) { // WMS 레이어는 lyrInfos를 가지고 있음
				var _infos = _lyr.get('lyrInfos');
				var checkout = false;
				for (var y = 0; y < _infos.length; y++) {
					if (_infos[y].name == lyrName) {
						_infos[y].visible = visible;
						checkout = true;
						break;
					}
				}
				if (checkout == true) {
					var param = _getWMSParams(_infos);
					_checkWMSParamVisible(_lyr, param);
					return; // 반드시 return 하자.
				}
			}
		}
	};

	// 한번에 다수 레이어 visible를 설정한다.
	// 테스트 안됨.
	this.setAllLayerVisible = function(_option) {
		var _layers = mapObject.getLayers();

		var _wmsArrays = [];

		for (var x = 0; x < _layers.getLength(); x++) {
			var _lyr = _layers.item(x);
			var _name = _lyr.get('name');
			if (_option[_name] != null) {
				_lyr.setVisible(_option[_name]);
			} else if (_lyr.get('lyrInfos')) {
				_wmsArrays.push(_lyr);
				var _infos = _lyr.get('lyrInfos');
				for (var y = 0; y < _infos.length; y++) {
					if (_option[_infos[y].name] != null) {
						_infos[y].visible = _option[_infos[y].name];
					}
				}
			}
		}

		if (_wmsArrays != null && _wmsArrays.length > 0) {
			for (var x = 0; x < _wmsArrays.length; x++) {
				var param = _getWMSParams(_wmsArrays[x].get('lyrInfos'));
				if (param == null) {
					// visible이 없으면 lyr자체를 visible false한다.
					_wmsArrays[x].setVisible(false);
				} else {
					_wmsArrays[x].setVisible(true);
				}
				_wmsArrays[x].getSource().updateParams(param);
			}
		}
	};

	// 한번에 다수 레이어 visible를 설정한다.
	// 테스트 안됨.
	this.setAllLayerUnVisible = function() {
		var list = this.getMap().getLayers().getArray();
		for(var i=0; i<list.length; i++){
			var layerProp = list[i].getProperties();
			if("type" in layerProp){
				var type = layerProp.type;
				if(type === "POINT" || type === "MULTILINESTRING" || type === "MULTIPOLYGON"){
					list[i].setVisible(false);
				}
			}else{
				continue;
			}
		}
	};


	/**
	 * 지도에 추가된 모든 레이어를 반환합니다.
	 *
	 * <b>검색 결과용 등의 레이어는 제외합니다.</b>
	 *
	 * @author 이주영 2017-06-08
	 */
	this.getAllLayers = function() {
		var _result = new Array();
		var _layers = mapObject.getLayers();
		var _size = _layers.getLength();
		for (var x = 0; x < _size; x++) {
			var _lyr = _layers.item(x);
			var _name = _lyr.get('fullName');
			if (_name != null) {
				_result.push(_lyr);
			} else if (_lyr.get('lyrInfos')) {
				var _arrs = _lyr.get('lyrInfos');
				// var x = 0 하면 안됨, 상위의 x값이 변경됨.
				for (var y = 0; y < _arrs.length; y++) {
					var _info = _arrs[y];
					if (_info.name == lyrName) {
						_result.push(_lyr);
					}
				}
			} else if (_layers.item(x) instanceof ol.layer.Tile){
				_result.push(_lyr);
			}
		}
		return _result;
	};

	/**
	 * 지도에 추가된 레이어를 제거합니다.
	 *
	 * <b>검색 결과용 등의 레이어는 제외합니다.</b>
	 *
	 * @author 이주영 2017-09-14
	 */
	this.removeAllLayers = function() {
		var _layers = this.getAllLayers();
		for(var i=0; i<_layers.length; i++){
			mapObject.removeLayer(_layers[i]);
		}
	};

	/**
	 * 지도에 추가된 레이어 중 현재 기능과 관련없는 레이어를 제거합니다.
	 *
	 * <b>검색 결과용 등의 레이어는 제외합니다.</b>
	 *
	 * @author 이주영 2017-09-14
	 */
	this.removeFnOrderLayers = function() {
		var _layers = this.getAllLayers();
		for(var i=0; i<_layers.length; i++){
			if(_layers[i] != null){
				var _val = _layers[i].get("fnGroup");
				if(_val != LayerFnGroup.CMMN){
					mapObject.removeLayer(_layers[i]);
				}
			}
		}
	};

	// Layer명으로 Layer객체를 찾는다 WMS tile의 경우 TileWMS반환
	this.getLayerByName = function(lyrName) {
		var _layers = mapObject.getLayers();
		var _size = _layers.getLength();
		for (var x = 0; x < _size; x++) {
			var _lyr = _layers.item(x);
			var _name = _lyr.get('name');
			// console.log("getLayerByName >> " + _name);
			if (lyrName == _name) {
				return _lyr;
			} else if (_lyr.get('lyrInfos')) {
				var _arrs = _lyr.get('lyrInfos');
				// var x = 0 하면 안됨, 상위의 x값이 변경됨.
				for (var y = 0; y < _arrs.length; y++) {
					var _info = _arrs[y];
					if (_info.name == lyrName) {
						return _lyr;
					}
				}
			}
		}
		return null;
	};
	//
	// getFeature 구현..
	this.getFeature = function(_proj, _ns, _prefix, _featureTypes, _filter, _callback) {
		// By default, supports WFS version 1.1.0.
		var req = new ol.format.WFS().writeGetFeature({
			srsName : _proj,
			featureNS : _ns,
			featurePrefix : _prefix,
			featureTypes : _featureTypes,
			outputFormat : 'application/json',
			filter : _filter
		});

		var _format = new ol.format.GeoJSON();
		$.ajax({
			url : ctxPath + '/wfs',
			type : 'POST',
			contentType : 'text/xml',
			data : new XMLSerializer().serializeToString(req),
			dataType : 'json',
			success : function(json) {
				_callback(json);
			},
			error : function(xhr, status, error) {
				var _xmlDoc = $.parseXML(xhr.responseText);
				var code = $(_xmlDoc).find("ExceptionText").text();
				alert("WFS 요청중 오류가 발생하였습니다. > \r\n" + code);
			}
		});
	};

	// getFeature equals 구현
	this.getFeatureByEquals = function(_proj, _fType, _prop, _val, _callback) {
		var _filter = ol.format.filter.equalTo(_prop, _val);
		this.getFeature(_proj, '', '', [ _fType ], _filter, _callback);
	};


	/* OL 객체에 이전, 다음을 위한 프로퍼티 추가 */
	if(ol.Map.prototype.isMove === undefined) ol.Map.prototype.isMove = false;
	if(ol.Map.prototype.moveCount === undefined) ol.Map.prototype.moveCount = 0;
	if(ol.Map.prototype.moveList === undefined) ol.Map.prototype.moveList = new Array();
	if(ol.Map.prototype.getNext === undefined){
		ol.Map.prototype.getNext = function(){
			if (mapObject.moveList.length -1 > mapObject.moveCount){
				mapObject.moveCount++;
				mapObject.isMove = true;

				var mapCenter = mapObject.moveList[mapObject.moveCount];
				mapObject.getView().setCenter(mapCenter[0]);
				mapObject.getView().setZoom(mapCenter[1]);
			}
		}
	}
	if(ol.Map.prototype.getPrev === undefined){
		ol.Map.prototype.getPrev = function(){
			if (mapObject.moveCount > 0){
				mapObject.moveCount--;
				mapObject.isMove = true;

				var mapCenter = mapObject.moveList[mapObject.moveCount];
				mapObject.getView().setCenter(mapCenter[0]);
				mapObject.getView().setZoom(mapCenter[1]);
			}
		}
	}

	/* 이전, 다음, 이동이력 함수 등록 */
	this.moveEvent = function(){
		var mapMoveCnt = mapObject.moveList.length;
		var mapCenter = new Array( mapObject.getView().getCenter(), mapObject.getView().getZoom() );

		if(mapObject.moveList[mapMoveCnt - 1] != mapCenter && !mapObject.isMove){
			if(mapObject.moveList.length == 10) mapObject.moveList.splice(0, 1);
			mapObject.moveList.push(mapCenter);
			mapObject.moveCount = mapObject.moveList.length - 1;
		}

		mapObject.isMove = false;
	};
	this.getPrev = function(){
		mapObject.getPrev();
	};
	this.getNext = function(){
		mapObject.getNext();
	};


	/* 지도옵션을 반환한다. */
	this.getOptions = function(){
		return options;
	};

	/* 초기 지도영역으로 이동한다. */
	this.moveStartCenter = function(){
		var center = _self.getOptions().center;
		var zoom = _self.getOptions().zoom;
		mapObject.getView().setZoom(zoom);
		mapObject.getView().setCenter(center);
	};


	/* 거리 면적 객체 추가 */
	this.measure = new XeusMeasure(mapObject, true);

	this.overview = new XeusOverview(mapObject, new ol.View({
		projection : options.projection,
		center : options.center,
		zoomFactor : 2,
		zoom : 1, // 클수록 확대
		minZoom : options.minZoom,
		maxZoom : options.maxZoom
	}), "xeus-overview-" + parentView);

	/* 화면 정리 */
	this.clear = function(){
		_self.measure.clear().disable();
		if(_search.locVector) _search.locVector.getSource().clear();
		if(eventVectorSource) eventVectorSource.clear();
		Spatial.stopInterval();
		$("#" + parentView).find("#btn-map-move").click();
	}

	/* PNG 저장 */
	this.saveImage = function(){
		_self.getMap().once('postcompose', function(event) {
			a = event;
			console.log(event);
			var canvas = event.context.canvas;
			if (navigator.msSaveBlob) {
				navigator.msSaveBlob(canvas.msToBlob(), 'map.png');
			} else {
				canvas.toBlob(function(blob) {
					c = console.log(blob);
					saveAs(blob, 'map.png');
				});
			}
		});
		_self.getMap().renderSync();
	}

	/*mapObject.on("movestart", function(){
		if(_self.getLayerByName("다음지도") instanceof ol.layer.Tile){
			_self.getLayerByName("다음지도").getSource().tileCache.expireCache({});
			_self.getLayerByName("다음지도").getSource().tileCache.clear();
		}
	});*/

	mapObject.on("moveend", function(){
		_self.moveEvent();

		var resolution = _self.getMap().getView().getResolution();
        var units = _self.getMap().getView().getProjection().getUnits();
        var dpi = 25.4 / 0.28;
        var mpu = ol.proj.METERS_PER_UNIT[units];
        var inchesPerMeter = 39.37;
        var scale = Math.round(resolution * mpu * inchesPerMeter * dpi);

        $("#" + parentView).find("#scale-val").val(scale);

      //centerPosition - 화면 센터의 좌표를 가지고 DB의 시군구,읍면동,리 이름 찾음
        var t_epsg = _self.getMap().getView().getProjection().getCode();
		var lnglat = Spatial.convertProjection(_self.getMap().getView().getCenter(), t_epsg, "EPSG:4326");
		var centerPosition = lnglat[0] + ", " + lnglat[1];
		$("#" + parentView).find("#center-position").val(centerPosition);

		if(_self.getLayerByName("다음지도") instanceof ol.layer.Tile){
			_self.getLayerByName("다음지도").getSource().tileCache.expireCache({});
			_self.getLayerByName("다음지도").getSource().tileCache.clear();
		}

		var _param = {
			"posi1" : _self.getMap().getView().getCenter()[0],
			"posi2" : _self.getMap().getView().getCenter()[1]
		};

		_common.callAjax("/search/getCenterName.json", _param, function(json) {
			if (json.result == null) {
				//$("#center-position-nm").val("검색결과가 없습니다.");
				$("#" + parentView).find('#overlay-south-side-bar').find('#addr').text('');
			} else {
				//$("#center-position-nm").val(json.result);
				$("#" + parentView).find('#overlay-south-side-bar').find('#addr').text(json.result);
			}

		});



		//$('#overlay-south-side-bar').find('#addr').text(centerPosition);
	});

	var dialog = null;
	this.createPopup = function(prop){
		var _html = "";
		_html  = '<div class="ui-dialog ui-corner-all ui-widget ui-widget-content vectorPopup" style="width:100%;height:100%">';
		_html += 	'<div class="ui-dialog-titlebar ui-corner-all  ui-widget-header ui-helper-clearfix ">';
		_html += 		'<span id="ui-id-2" class="ui-dialog-title">';
		_html += 			'<div class="xeus-dialog-title-div">';
		_html += 				'<span class="xeus-dialog-title-txt">';
		_html += 					'<img id="' + prop[prop.target_field] + '" class="cctv-overlay-content-img ui-draggable ui-draggable-handle" src="' + prop.img_path + '">' + prop[prop.target_field] + '';
		_html += 				'</span>';
		_html += 			'</div>';
		_html += 		'</span>';
		_html += 		'<button type="button" id="popup_btn_close" class="ui-button ui-corner-all ui-widget ui-button-icon-only ui-dialog-titlebar-close">';
		_html += 			'<span class="ui-button-icon ui-icon ui-icon-closethick"></span>';
		_html += 		'</button>';
		_html += 	'</div>';
		_html += 	'<div id="popup_content" class="ui-dialog-content ui-widget-content" style="overflow:hidden;">';
		_html += 	'</div>';
		_html += '</div>';

		dialog = $(_html);
		$("body").append($(dialog));
		$(dialog).width(300).height(200);
		//$(dialog).draggable();
		$(dialog).css({
			position: "absolute",
			height: "auto",
			left: "45%",
			top: "30%"
		});
		$(dialog).find("#popup_content").css("border-bottom", "1px solid black");
		$(dialog).find("#popup_btn_close").click(function(){
			$(dialog).remove();
			$(".vectorPopup").remove();
		});
	}

	this.removePopup = function(){
		$(dialog).remove();
		$(".vectorPopup").remove();
	}

	/**
	 * 포인트 객체를 표출한다.
	 *
	 * @param xyArr
	 *  - [ {x : 경도, y: 위도} ]
	 * @returns rst
	 */
	this.createNdmsPoint = function(xyArr, txt){
		//source 생성
		var Features = [];
		console.log(xyArr);
		console.log(txt);
		xeusLayout.mapService.removeNameLayer('ndms');
//		for(var i=0; i<xyArr.length; i++){		//리스트로 넘어올 수도 있으니까..
//			var item = xyArr[i];
//			var lon = Number(item.lon); //위도
//			console.log("lon = "+lon);
//			var lat = Number(item.lat);  //경도
//			console.log("lat = "+lat);
//			var dsc = item.dsc; // 표출 텍스트.
//			console.log("dsc = "+dsc.cdstn);
//			//source에 feature 추가.
//			var Feature =	new ol.Feature({
//					geometry: new ol.geom.Point(ol.proj.transform([lon, lat], 'EPSG:4326',
//					'EPSG:5186')),
//					name: 'dsc'+i,
//					dsc : dsc,
//				})
//			Features.push(Feature);
//		}
		var item = xyArr;
		var lon = Number(item.x); //위도
		var lat = Number(item.y);  //경도

		if(lon != 0 && lat != 0){
			var xy = Spatial.convertAddrToXY(xyArr);
			lon = xy[0];
			lat = xy[1];
		}

		//source에 feature 추가.
		var Feature =	new ol.Feature({
			geometry: new ol.geom.Point(ol.proj.transform([lon, lat], 'EPSG:4326',
			'EPSG:5186')),
			name: 'dsc',
			dsc : txt.replace(/ \/ /gi, "\r\n")
		})
		Features.push(Feature);
		var source = new ol.source.Vector({
			  features: Features
			  , strategy: ol.loadingstrategy.bbox
			  , projection: 'EPSG:5186'
			});

		//레이어 추가.
		var layer = new ol.layer.Vector({
		      source: source,
		      name: 'ndms',
		      zIndex:999,
			  visible:true,
		      style: function(feature, resolution) {
		    	  console.log('////'+feature.get('dsc')+'///');
		    	  return new ol.style.Style({
		  	  		text : new ol.style.Text({
		  				 font: 'bold 12px "Malgun Gothic"',
		  				 text: feature.get('dsc'),
		  				 fill: new ol.style.Fill({color: 'blue'}),
		  				 stroke: new ol.style.Stroke({color: '#fff', width: 3}),
		  				 //rotation: style.label.rotate
		  	  		})
		  		});

		      }
		});
		xeusLayout.mapService.addLayer(layer);
	}

	/**
	 * 지도 클릭 이벤트 입니다.
	 * 포인트 레이어의 팝업을 위해 사용됩니다.
	 */
	mapObject.on("click", function(evt){
		var feature = mapObject.forEachFeatureAtPixel(evt.pixel, function(feature, layer){
			return feature;
		});
		/*if(dialog != null){
			$(dialog).remove();
			$(".vectorPopup").remove();
		}
		if(feature && feature.getGeometry() instanceof ol.geom.Point && !feature.get("cctvList")){
			a = feature;
			var prop = feature.getProperties();
			if(prop["target_field"] != null){
				_self.createPopup(prop);
			}
		}else{
			_self.removePopup();
		}*/
		var coordinate = Spatial.convertProjection(evt.coordinate, "EPSG:5186", "EPSG:4326");
		$("#" + parentView).find('#overlay-south-side-bar').find('#coorX').text(coordinate[1]);
		$("#" + parentView).find('#overlay-south-side-bar').find('#coorY').text(coordinate[0]);

//		var position = new daum.maps.LatLng(coordinate[1], coordinate[0]);	//클릭한 지점 좌표

//		var roadview_pop=$("#" + parentView).find('#roadview_pop');
//		if(roadview_pop.length==1){	//로드뷰div가 있을 시
//			if(roadview_pop.css("display")=="none"){	//none일 시 none을 없애고 표출시킨다.
//				roadview_pop.css("display","");
//			}
//			geomex.xeus.DaumRoadView.createRoadView(position);
//		}

	});

	/**
	 * 심볼 커서 변경 이벤트 입니다.
	 */

	mapObject.on("pointermove", function(event){
		var mouseCoordInMapPixels = [event.originalEvent.offsetX, event.originalEvent.offsetY];
		var hit = mapObject.forEachFeatureAtPixel(mouseCoordInMapPixels, function(feature, layer){
			if(feature.getGeometry() instanceof ol.geom.Point) return true;
		});

		/*
		 * 180430 이은규
		 * 상단 메뉴에 마우스 커서 위치의 좌표 정보를 표시한다.
		 */
		var coordinate = Spatial.convertProjection(event.coordinate, "EPSG:5186", "EPSG:4326");
		$("#" + parentView).find('#overlay-south-side-bar').find('#coorX').text(coordinate[1]);
		$("#" + parentView).find('#overlay-south-side-bar').find('#coorY').text(coordinate[0]);

		if(hit){
			$("body").css("cursor", "pointer");
		}else{
			$("body").css("cursor", "");
		}
	});


	this.addPulse = function(center, moveYn, repeat){
		var GMXMAP = this.getMap();
		if(moveYn){
			GMXMAP.getView().setCenter(center);
			GMXMAP.getView().setZoom(19);
		}

		if(!_common.utils.isNumber(repeat)) repeat = 3; repeat++;

		for(var i=0; i<repeat; i++){
			var timeout = setTimeout(function(){
				var feature = new ol.Feature(new ol.geom.Point(center));
				feature.setStyle(new ol.style.Style({
					image: new ol.style.Circle({
						radius: 30,
						snapToPixel: false,
						stroke: new ol.style.Stroke({
							color: "red",
							width: 2
						})
					})
				}));

				GMXMAP.animateFeature(feature, new ol.featureAnimation.Zoom({
					fade: ol.easing.easeOut,
					duration: 3000,
					easing: ol.easing["easeOut"]
				}));

				feature = null;

				clearTimeout(timeout);
				timeout = null;
			}, i * 1000);
		};
	};

};
