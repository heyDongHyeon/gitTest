// 클래스 namespace만들기.
if (window.geomex == null)
	var geomex = {}

if (geomex.xeus == null) {
	geomex.xeus = {}
}

if (geomex.xeus.tms == null) {
	geomex.xeus.tms = {}
}

/**
 * createMapLayer, createSkyViewLayer,createHybridLayer,createRoadViewLayer
 * proxy 적용여부 확인 후 수정
 *
 * @param options
 */
geomex.xeus.tms.DaumMap = function(options) {
	var ctxPath = "/xeus";
	if (options) {
		ctxPath = options.ctxPath;
	}

	var _daumProjection = ol.proj.get('EPSG:5181');
	var _daumOrigin = [ -30000, -60000 ];
	var _daumResolutions = [ 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25, 0.125 ];

	// _default option 값을 변경한다.
	function _mergeOptions(_default, options) {
		if (options == null) {
			return;
		}
		if (options.name != null) {
			_default.name = options.name;
		}
		if (options.tmsPath != null) {
			_default.tmsPath = options.tmsPath;
		}
		if (!options.proxy != null) {
			_default.proxy = options.proxy;
		}
		if (options.visible != null) {
			_default.visible = options.visible;
		}
		if (options.zIndex != null) {
			_default.zIndex = options.zIndex;
		}
		if (options.type != null) {
			_default.type = options.type;
		}
		if (options.fullName != null) {
			_default.fullName = options.fullName;
		}
		if (options.group != null) {
			_default.group = options.group;
		}
		if (options.fnGroup != null) {
			_default.fnGroup = options.fnGroup;
		}
	}

	function _createLayer(_default) {
		var _sub = _default.tmsPath.split('#SUBDOMAIN#');

		var _daumLayer = new ol.layer.Tile({
			name : _default.name,
			type : _default.type,
			zIndex : _default.zIndex,
			visible : _default.visible,
			fullName : _default.fullName,
			group : _default.group,
			fnGroup : _default.fnGroup,
			source : new ol.source.XYZ({
				//cacheSize : 1,
				projection : _daumProjection,
				tileUrlFunction : function(coordinate) {
					if (coordinate == null) {
						return "";
					}
					var level = 14 - coordinate[0]; // z
					var row = coordinate[2]; // y
					var col = coordinate[1]; // x

					var subdomain = ((level + col) % 4) + 1;

					var url = _default.tmsPath + level + "/" + row + "/" + col + "." + _default.format;
					if (_sub.length == 2) {
						url = _sub[0] + subdomain + _sub[1] + level + "/" + row + "/" + col + "." + _default.format;
					}
//					if (_default.proxy) {
//						return ctxPath + "/tms?url=" + url + "&type=" + _default.format;
//					} else {
//						return url; // online으로 바로 받는다.
//					}
					
//					return "http://127.0.0.1:48080/xeusapi/proxy.jsp?url=" + encodeURI(url) + "&type=" + encodeURI(_default.format);
					return "../tms/dmz?url=" + url;
				},
				tileGrid : new ol.tilegrid.TileGrid({
					origin : _daumOrigin,
					resolutions : _daumResolutions
				})
			})
		});
		return _daumLayer;
	}

	// DaumMap Layer를 생성한다.
	// options = {
	// online : 온라인 직접 접속 여부(true/false)
	// tmsPath : offline일 경우 tms경로(/tms/map)
	// }
	this.createMapLayer = function(options) {

		var _default = {
			name : '다음지도',
			fullName : 'daum_tile_map',
			type : 'TMS',
			//tmsPath : 'http://map#SUBDOMAIN#.daumcdn.net/map_2d/166joy/L',
			//180731 이은규 : 지도가 표출되지 않아 신맵 도메인으로 변경하였음.
			tmsPath : 'http://map#SUBDOMAIN#.daumcdn.net/map_2d_hd/2205pfk/L',
			proxy : false,
			visible : true,
			zIndex : 0,
			format : 'png',
			group : '배경지도',
			fnGroup : "CMMN"
		}
		_mergeOptions(_default, options);
		return _createLayer(_default);
	};

	// DaumSkyView Layer를 생성한다.
	// options = {
	// online : 온라인 직접 접속 여부(true/false)
	// tmsPath : offline일 경우 tms경로(/tms/sat)
	// }
	this.createSkyViewLayer = function(options) {
		var _default = {
			name : '다음항공영상',
			fullName : 'daum_map',
			type : 'TMS',
			tmsPath : 'http://map#SUBDOMAIN#.daumcdn.net/map_skyview/L',
			proxy : false,
			visible : false,
			zIndex : 0,
			format : 'jpg',
			group : '배경지도',
			fnGroup : "CMMN"
		}
		_mergeOptions(_default, options);
		return _createLayer(_default);
	};

	// DaumHybrid Layer를 생성한다.
	// options = {
	// online : 온라인 직접 접속 여부(true/false)
	// tmsPath : offline일 경우 tms경로(/tms/hybrid)
	// }
	this.createHybridLayer = function(options) {
		var _default = {
			name : '다음하이브리드',
			fullName : 'daum_hybrid',
			type : 'TMS',
			tmsPath : 'http://map#SUBDOMAIN#.daumcdn.net/map_hybrid_hd/2205pfk/L',
			proxy : false,
			visible : false,
			zIndex : 0,
			format : 'png',
			group : '배경지도',
			fnGroup : "CMMN"
		}
		_mergeOptions(_default, options);
		return _createLayer(_default);
	};

	// DaumRoadView Layer를 생성한다.
	// options = {
	// online : 온라인 직접 접속 여부(true/false)
	// tmsPath : offline일 경우 tms경로(/tms/hybrid)
	// }
	this.createRoadViewLayer = function(options) {
		var _default = {
			name : '다음로드뷰',
			fullName : 'daum_road_view',
			type : 'TMS',
			tmsPath : 'http://map#SUBDOMAIN#.daumcdn.net/map_roadviewline/4.00/L',
			proxy : false,
			visible : true,
			zIndex : 0,
			format : 'png',
			group : '배경지도',
			fnGroup : "CMMN"
		}
		_mergeOptions(_default, options);
		return _createLayer(_default);
	};
}
