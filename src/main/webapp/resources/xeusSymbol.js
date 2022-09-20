/**
 * <pre>
 * Point 심볼 변경을 위한 객체입니다.
 * 기존 Feature 객체를 변경하지 않고,
 * 새로운 Feature 를 생성하여 같은 자리에 추가합니다.
 * </pre>
 *
 * @author 이주영
 * @version 1.0r
 */
if(window.xeusSymbol == null) var xeusSymbol = {

	/* 심볼 표시용 벡터 객체 입니다. */
	vector : null,
	vectorSource : null,

	/* 가이드라인용 벡터 객체 입니다. */
	guide : null,
	guideSource : null,

	/**
	 * 심볼용 벡터를 생성하여 지도에 추가합니다.
	 *
	 * @param 선택) _Map - ol.Map
	 * @returns {___anonymous_xeusSymbol}
	 */
	createVector : function(_Map){
		this.vectorSource = new ol.source.Vector();
		this.vector = new ol.layer.Vector({
			source: xeusSymbol.vectorSource,
			zIndex: 100
		});

		if(_Map instanceof ol.Map){
			_Map.addLayer(vector);
		}else{
			return this.vector;
		}
	},

	/**
	 * 가이드라인 벡터를 생성하여 지도에 추가합니다.
	 *
	 * @param 선택) _Map - ol.Map
	 * @returns {___anonymous_xeusSymbol}
	 */
	createGuideLine : function(_Map){
		this.guideSource = new ol.source.Vector();
		this.guide = new ol.layer.Vector({
			source: xeusSymbol.guideSource,
			zIndex: 99,
			style: new ol.style.Style({
				stroke : new ol.style.Stroke({
					width : 3,
					color : "#ff1a8c",
					lineDash : [ .2, 6 ]
				}),
			})
		});

		if(_Map instanceof ol.Map){
			_Map.addLayer(guide);
		}else{
			return this.guide;
		}
	},

	/**
	 * Gid 값으로 등록된 Feature 객체를 찾아 리턴합니다.
	 *
	 * @param _Gid - Number
	 * @returns
	 */
	findFeature : function(_Gid){
		var feature = null;
		var features = this.vectorSource.getFeatures();
		for(var i=0; i<features.length; i++){
			if(features[i].get("gid") == Number(_Gid)){
				feature = features[i];
			}
		}

		return feature;
	},

	/**
	 * Gid 값으로 등록된 Feature 객체를 찾아 삭제합니다.
	 *
	 * TODO 필요시 추후 모든 타입(isPlay, isPreset, isLock 등을 모두 제거)
	 *
	 * @param 필수) _Gid - Number
	 * @param 필수) _Type - String
	 * @param 선택) _Callback - function
	 * @returns
	 */
	removeFeature : function(_Gid, _Type, _Callback){
		var success = false;
		var feature = null;
		var features = this.vectorSource.getFeatures();
		for(var i=0; i<features.length; i++){
			if(features[i].get("gid") == Number(_Gid) && features[i].get(_Type)){
				try {
					this.vectorSource.removeFeature(features[i]);
					success = true;
				} catch(e) {
					console.error(e);
				}
			}
		}

		if(success && typeof _Callback == "function") _Callback();

		return success;
	},


	/**
	 * 재생 심볼을 추가합니다.
	 *
	 * @param _Object - Array or ol.Feature
	 * @param _Gid - String
	 * @returns {___anonymous_xeusSymbol}
	 */
	addPlay : function(_Object, _Gid){

		var center = null;

		if(_Object instanceof Array){
			center = _Object;
		}else if(_Object instanceof ol.Feature){
			center = _Object.getGeometry().getCoordinates();
		}

		try {
			var point = new ol.Feature(new ol.geom.Point(center));
			point.setProperties({
				"isIgnore" : true,
				"isPlay" : true,
				"gid" : _Gid
			});
			point.setStyle(new ol.style.Style({
				image: new ol.style.Icon({
					src: '../res/sym/cctv/play.png'
				})
			}));

			this.vectorSource.addFeature(point);
		} catch(e) {
			console.log("Feature 생성에 실패하었습니다. (중심점 확인 필요)");
			console.error(e);
		}

		return this;
	},

	/**
	 * TODO
	 * 회전각도 심볼을 추가합니다.
	 *
	 * @param _Object - Array or ol.Feature
	 * @param _Gid - String
	 * @returns {___anonymous_xeusSymbol}
	 */
	addAngle : function(_Object, _Gid, _Angle){

		this.removeFeature(_Gid, "isAngle");

		var center = null;

		if(_Object instanceof Array){
			center = _Object;
		}else if(_Object instanceof ol.Feature){
			center = _Object.getGeometry().getCoordinates();
		}

		try {
			var point = new ol.Feature(new ol.geom.Point(center));
			point.setProperties({
				"isIgnore" : true,
				"isAngle" : true,
				"gid" : _Gid
			});
			point.setStyle(new ol.style.Style({
				image: new ol.style.Icon({
					src: '../res/img/area.png',
					rotation: Spatial.convertRadians(Number(_Angle)),
					anchorXUnits: 'fraction',
					anchorYUnits: 'pixels',
					anchor: [0.5, 45]
				}),
				zIndex: -1
			}));

			this.vectorSource.addFeature(point);
		} catch(e) {
			console.log("Feature 생성에 실패하었습니다. (중심점 확인 필요)");
			console.error(e);
		}

		return this;
	},

	/**
	 * 장애 심볼을 추가합니다.
	 *
	 * @param _Object - Array or ol.Feature
	 * @param _Gid - String
	 * @returns {___anonymous_xeusSymbol}
	 */
	addError : function(_Object, _Gid){

		var center = null;

		if(_Object instanceof Array){
			center = _Object;
		}else if(_Object instanceof ol.Feature){
			center = _Object.getGeometry().getCoordinates();
		}

		try {
			var point = new ol.Feature(new ol.geom.Point(center));
			point.setProperties({
				"isIgnore" : true,
				"isError" : true,
				"gid" : _Gid
			});
			/*point.setStyle(new ol.style.Style({
				image: new ol.style.RegularShape({
					fill: new ol.style.Fill({
						color: "red"
					}),
					stroke: new ol.style.Stroke({
						color: "red",
						width: 2
					}),
					points: 4,
					radius: 15,
					radius2: 0,
					angle: Math.PI / 4
				})
			}));*/

			point.setStyle(new ol.style.Style({
				image: new ol.style.Icon({
					src: '../res/sym/cctv/error.png'
				})
			}));

			this.vectorSource.addFeature(point);
		} catch(e) {
			console.log("Feature 생성에 실패하었습니다. (중심점 확인 필요)");
			console.error(e);
		}

		return this;
	},

	/**
	 * 카메라가 바라보는 재생 방향 및 길이를 추가합니다.
	 *
	 * @param _StartObject - Array or ol.Feature
	 * @param _EndObject - Array or ol.Feature
	 * @param _Gid - String
	 * @param _PresetNo - String
	 * @returns {___anonymous_xeusSymbol}
	 */
	addPreset : function(_StartObject, _EndObject, _Gid, _PresetNo){

		var startCenter = null;
		var endCenter = null;

		if(_StartObject instanceof Array){
			startCenter = _StartObject;
		}else if(_StartObject instanceof ol.Feature){
			startCenter = _StartObject.getGeometry().getCoordinates();
		}

		if(_EndObject instanceof Array){
			endCenter = _EndObject;
		}else if(_EndObject instanceof ol.Feature){
			endCenter = _EndObject.getGeometry().getCoordinates();
		}

		try {
			var line = new ol.Feature(new ol.geom.LineString([startCenter, endCenter]))
			line.setProperties({
				"isIgnore" : true,
				"isPreset" : true,
				"gid" : _Gid
			});
			line.setStyle(new ol.style.Style({
				stroke: new ol.style.Stroke({
					color: "#ffcc33",
					width: 2
				})
			}));

			var point = new ol.Feature(new ol.geom.Point(endCenter));
			point.setProperties({
				"isIgnore" : true,
				"isPreset" : true,
				"gid" : _Gid
			});
			point.setStyle(new ol.style.Style({
				image: new ol.style.Circle({
					radius: 1,
					fill: new ol.style.Fill({
						color: 'rgba(0, 0, 255, 0.1)'
					}),
					stroke: new ol.style.Stroke({
						//color: 'rgba(240, 90 , 30, 1)',
						color: 'red',
						width: 3
					})
				}),
				text: new ol.style.Text({
					font: '20px Calibri,sans-serif',
					text: "" + _PresetNo,
					fill: new ol.style.Fill({
						color: 'red'
					}),
					stroke: new ol.style.Stroke({
						color: '#fff',
						width: 10
					})
				})
			}));

			this.vectorSource.addFeature(line);
			this.vectorSource.addFeature(point);


			/*var dx = endCenter[0] - startCenter[0];
			var dy = endCenter[1] - startCenter[1];
			var rotation = Math.atan2(dy, dx);

			var point = new ol.Feature(new ol.geom.Point(endCenter));
			point.setProperties({ "isIgnore" : true });
			point.setStyle(new ol.style.Style({
				image: new ol.style.Icon({
					src: "../res/img/arrow.png",
					anchor: [0.75, 0.5],
					rotateWithView: true,
					rotation: -rotation
				})
			}));

			this.vectorSource.addFeature(point);*/
		} catch(e) {
			console.error("Feature 생성에 실패하었습니다. (중심점 또는 스타일 확인 필요)");
			console.error(e);
		}

		return this;
	},

	/**
	 * 카메라 제어 심볼을 추가합니다.
	 *
	 * @param _Object - Array or ol.Feature
	 * @param _Gid - String
	 * @returns {___anonymous_xeusSymbol}
	 */
	addLock : function(_Object, _Gid){

		var center = null;

		if(_Object instanceof Array){
			center = _Object;
		}else if(_Object instanceof ol.Feature){
			center = _Object.getGeometry().getCoordinates();
		}

		try {
			var point = new ol.Feature(new ol.geom.Point(center));
			point.setProperties({
				"isIgnore" : true,
				"isLock" : true,
				"gid" : _Gid
			});
			point.setStyle(new ol.style.Style({
				image: new ol.style.Icon({
					src: '../res/sym/cctv/lock_red.png'
				})
			}));

			this.vectorSource.addFeature(point);
		} catch(e) {
			console.log("Feature 생성에 실패하었습니다. (중심점 확인 필요)");
			console.error(e);
		}

		return this;
	},

	/**
	 * 재생창과 심볼의 가이드 라인을 추가합니다.
	 *
	 * @param 필수) _Map - String
	 * @param 필수) _PlayerId - String
	 * @returns {___anonymous_xeusSymbol}
	 */
	addGuideline : function(_Map, _PlayerId){

		try {
			var source = this.guideSource;
			var features = this.guideSource.getFeatures();

			var $player = $("#" + _PlayerId);
			var center = $player.dialog("option", "basePoint")
			var x = center[0];
			var y = center[1];

			var offset = $player.offset();
			var w = $player.outerWidth() / 2;
			var h = $player.outerHeight() / 2;

			var mapW =Number($("#" + parentView).find("#xeus-map-content").css('margin-left').replace('px',''));
//			console.log(mapW);
			var playerCenter = _Map.getCoordinateFromPixel([ (offset.left + w)-mapW, (offset.top - h)+200]);

			var isExist = false;
			for (var i=0; i<features.length; i++) {
				var feature = features[i];
				if (feature.get("playerId") == _PlayerId) {
					feature.getGeometry().setCoordinates([ [ x, y ], playerCenter ]);
					isExist = true;
					break;
				}
			}

			if (isExist == false) {
				source.addFeature(new ol.Feature({
					geometry : new ol.geom.LineString([ [ x, y ], playerCenter ]),
					playerId : _PlayerId
				}));
			}
		} catch(e) {
			console.log("Feature 생성에 실패하었습니다. (중심점 확인 필요)");
			console.error(e);
		}

		return this;
	},

	/**
	 * 가이드 라인을 삭제합니다.
	 *
	 * @param 필수) _PlayerId - String
	 * @param 선택) _Callback - function
	 * @returns
	 */
	removeGuideFeature : function(_PlayerId, _Callback){
		var success = false;
		var feature = null;
		var features = this.guideSource.getFeatures();
		for(var i=0; i<features.length; i++){
			if(features[i].get("playerId") == _PlayerId){
				try {
					this.guideSource.removeFeature(features[i]);
					success = true;
				} catch(e) {
					console.error(e);
				}
			}
		}

		if(typeof _Callback == "function") _Callback();

		return success;
	},
}