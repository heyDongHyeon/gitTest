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
geomex.xeus.tms.NaverMap = function(options) {
	var ctxPath = "/xeus";
	if (options) {
		ctxPath = options.ctxPath;
	}

	var _Projection = ol.proj.get('EPSG:5186');
	var _NaverOrigin =[ -665080,	-207168 ];
	var _NaverResolutions = [ 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1, 0.5, 0.25, 0.125 ];

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

	function _createNaverLayer(_default) {
		var _sub = _default.tmsPath.split('#SUBDOMAIN#');

		var _naverLayer = new ol.layer.Tile({
			name : _default.name,
			type : _default.type,
			zIndex : _default.zIndex,
			visible : _default.visible,
			fullName : _default.fullName,
			group : _default.group,
			fnGroup : _default.fnGroup,
			source : new ol.source.XYZ({
				projection : _Projection,
				tileUrlFunction : function(coordinate) {
					if (coordinate == null) {
						return "";
					}
					var z = coordinate[0];
					var y = coordinate[2];
					var x = coordinate[1];

		          	var level = z + 1;
	    			var row = x;
	    			var col = y;

					var subdomain = ((level + col) % 4) + 1;
					var url = _default.tmsPath + level + "/" + row + "/" + col + "." + _default.format;
					if (_sub.length == 3) {
						url = _sub[0] + subdomain+_sub[1]+ level + "/" + row + "/" + col + _sub[2];
					}
					console.log(url);
					if (_default.proxy) {
						return ctxPath + "/tms?url=" + url + "&type=" + _default.format;
					} else {
						return url; // online으로 바로 받는다.
					}
				},
				tileGrid : new ol.tilegrid.TileGrid({
					origin : _NaverOrigin,
					resolutions : _NaverResolutions
				})
			})
		});

		return _naverLayer;
	}

	this.createNaverViewLayer = function(options) {
		var _default = {
				name : '네이버지도',
				fullName : 'naver_map_view',
				type : 'TMS',
				tmsPath : 'http://onetile#SUBDOMAIN#.map.naver.net/get/196/0/0/#SUBDOMAIN#/bl_vc_bg/ol_vc_an',
				proxy : false,
				visible : false,
				zIndex : 1,
				format : 'png',
				group : '배경지도',
				fnGroup : "CMMN"
		}
		_mergeOptions(_default, options);
		return _createNaverLayer(_default);
	};
}

