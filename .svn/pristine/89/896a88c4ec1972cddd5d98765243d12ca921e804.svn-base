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
geomex.xeus.tms.EMap = function(options) {
	var ctxPath = "/xeus";
	if (options) {
		ctxPath = options.ctxPath;
	}
	var _Projection = ol.proj.get('EPSG:5179');
	var _EMapOrigin =[ -199900.0, -28023577.62 ];
	var _EMapResolutions = [ 1954.5973888747778, 977.2986944373889, 488.64934721869446,
  	     244.32467360934723, 122.16233680467361, 61.08116840233681,
	     30.540584201168404, 15.270292100584202, 7.635146050292101,
	     3.8175730251460505, 1.9087865125730252, 0.9543932562865126,
	     0.4771966281432563, 0.23859831407162815 ];

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

	function _createEmapLayer(_default) {
		var _sub = _default.tmsPath.split('#SUBDOMAIN#');

		var _emapLayer = new ol.layer.Tile({
			name : _default.name,
			type : _default.type,
			zIndex : _default.zIndex,
			visible : _default.visible,
			fullName : _default.fullName,
			group : _default.group,
			fnGroup : _default.fnGroup,
			source : new ol.source.XYZ({
				projection : _Projection,
				tileUrlFunction: function (coordinate) {

	                if (!coordinate) { return Options["failImg"]; }

	                var z = coordinate[0]+6;
	                var x = coordinate[1];
	                var y = coordinate[2];
	                return "http://10.159.1.191/emap/" + z + "/" + x + "/" + y + ".png";
	            },

				tileGrid : new ol.tilegrid.TileGrid({
					origin : _EMapOrigin,
					resolutions : _EMapResolutions
				})
			})
		});

		return _emapLayer;
	}

	this.createEmapViewLayer  = function(options) {
		var _default = {
				name : '바로E맵',
				fullName : 'emap_map_view',
				type : 'TMS',
				tmsPath : 'http://10.1.73.16:8080/emap/korean',
				proxy : false,
				visible : false,
				zIndex : 1,
				format : 'png',
				group : '배경지도',
				fnGroup : "CMMN"
		}
		_mergeOptions(_default, options);
		return _createEmapLayer(_default);
	};
}

