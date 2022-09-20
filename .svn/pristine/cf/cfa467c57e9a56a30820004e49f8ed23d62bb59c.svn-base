/**
 * <pre>
 * CCTV관련 이벤트 입니다.
 * </pre>
 *
 * @auther 이주영
 */
Public.CCTV = {
	Search : {
		excelParam : null,

		vector : null,
		interaction : null,

		Start : function() {
			if (Public.StopEvent != null && Public.CCTV.Search.interaction != null) {
				Public.CCTV.Search.interaction.setActive(true);
				return false;
			}

			this.vector = new ol.layer.Vector({
				source : new ol.source.Vector({
					wrapX : false
				})
			});

			var drawMode = $("#" + parentView).find(".drawType:checked").val();
			var geometryFunction = null;
			if(drawMode == "Box"){
				drawMode = "Circle";
				geometryFunction = ol.interaction.Draw.createBox();
			}

			this.interaction = new ol.interaction.Draw({
				source : Public.CCTV.Search.vector.getSource(),
				type : drawMode,
				geometryFunction : geometryFunction
			});
			this.interaction.on("drawstart", function(e){
				var features = Public.CCTV.Search.vector.getSource().getFeatures();
				for(var i=0; i<features.length; i++){
					Public.CCTV.Search.vector.getSource().removeFeature(features[i]);
				}
			});
			this.interaction.on("drawend", function(e) {
				var param = {};
				var geometry = e.feature.getGeometry();
				if(geometry.getRadius != null){
					var radius = geometry.getRadius();
					var center = geometry.getCenter();
					param["radius"] = radius;
					param["center"] = "POINT(" + center.join(" ").toString() + ")";
				}else{
					var format = new ol.format.WKT();
					var wkt = format.writeGeometry(e.feature.getGeometry());
					param["wkt"] = wkt;
				}
				_common.callAjax("/cctv/getCctvList.json", param, function(json){
					Public.CCTV.Search.excelParam = param;
					if(json.result.length == 0){
						var $tr = $("<tr><td colspan='3' class='tCenter'>결과가 존재하지 않습니다.</td></tr>");
						$("#" + parentView).find(".searchWrapper").find("#resultTable").find("tbody").html($tr);
					}else{
						$("#" + parentView).find(".searchWrapper").find("#resultTable").find("tbody").html("");
						for(var i=0; i<json.result.length; i++){
							var $tr = $("<tr class='tCenter' k='" + json.result[i].mgrNo + "'></tr>");
							$tr.append("<td>" + _common.getCodeByName("C14", json.result[i].gbnCd) + "</td>");
							$tr.append("<td>" + json.result[i].cctvNm + "</td>");
							if (chkPage == "tvius"){
								/* 171212 */
								//$tr.append("<td><button class='blueBtn locBtn'>위치</button></td>");
								$tr.append("<td><button class='locBtn'></button></td>");
							} else {
								/* 171212 */
								//$tr.append("<td><button class='blueBtn locBtn'>위치</button><button class='blueBtn detailBtn'>관리</button></td>");
								$tr.append("<td><button class='locBtn'></button><button class='detailBtn'></button></td>");
							}

							var prop = {
									gid : json.result[i].gid,
									mgrNo : json.result[i].mgrNo,
									gbnCd : json.result[i].gbnCd,
									angle : json.result[i].viewDir,
									cctvNm : json.result[i].cctvNm,
									channelNo : json.result[i].chnlNo,
									deviceId : json.result[i].deviceId,
									stateCd : json.result[i].stateCd,
									point : Spatial.convertProjection([json.result[i].lng, json.result[i].lat], "EPSG:4326", "EPSG:5186")
								};
							$tr.data(prop);

							$("#" + parentView).find(".searchWrapper").find("#resultTable").find("tbody").append($tr);
						}

						/* 위치 버튼 이벤트입니다. */
						$("#" + parentView).find(".searchWrapper").find("#resultTable").find(".locBtn").click(function(){
							var v = $(this).parent().parent().attr("k");
							var prop = $(this).parent().parent().data();
							_common.callAjax("/nms/getGeometryLocation.json", {k : v}, function(json) {
								xeusLayout.mapService.moveToAnimation(0, [json.result[0].annoX, json.result[0].annoY]);
								//xeusCCTV.viewVideo(JSON.stringify(prop));
								//암호화 처리
								if(prop.stateCd != "12") xeusCCTV.viewVideo(encodeURIComponent(JSON.stringify(prop)));
								else alert("<br>해당 CCTV는 현재 재생이 불가능 합니다.");
							});
						});

						/* 관리 버튼 이벤트입니다. */
						$("#" + parentView).find(".searchWrapper").find("#resultTable").find(".detailBtn").click(function(){
							xeusCCTV.westReLayout();
							$("#" + parentView).find("#btn-cctv-mng").click();
							var v = $(this).parent().parent().attr("k");
							_common.callAjax("/cctv/getCctvMngView.do", {mgrNo : v}, function(view) {
		        				$("#" + parentView).find("#center-overlay-east").height(
		        					$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
		        				).html(view);
		        				xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
		    					});
		        			});
						});
					}
				});
			});

			$("#" + parentView).find("#drawCncl").show("slow");
			xeusLayout.mapService.getMap().addLayer(this.vector);
			xeusLayout.mapService.getMap().addInteraction(this.interaction);

			Public.StopEvent = function() {
				$("#" + parentView).find("#drawCncl").hide("slow");
				if (this.CCTV.Search.interaction != null) {
					xeusLayout.mapService.getMap().removeInteraction(this.CCTV.Search.interaction);
					this.CCTV.Search.interaction = null;
				}
				if (this.CCTV.Search.vector != null) {
					xeusLayout.mapService.getMap().removeLayer(this.CCTV.Search.vector);
					this.CCTV.Search.vector = null;
				}
				this.StopEvent = null;
			}
		}
	},

	Add : {
		Start : function(evt) {
			var coordinates = evt.coordinate;
			var epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
			var mainCenter = ol.proj.transform(coordinates, epsg, 'EPSG:4326');
			$("#" + parentView).find("#regTable #lng").val(mainCenter[0]);
			$("#" + parentView).find("#regTable #lat").val(mainCenter[1]);

			$("body").css("cursor", "default");
			$("#" + parentView).find(".selectCancel").hide(500);
			xeusLayout.mapService.getMap().un('click', Public.CCTV.Add.Start);
		}
	},

	Preset : {
		Start : function(evt){
			var coordinates = evt.coordinate;

			_common.callAjax("/proxy/xeusGateWay.json", { "path" : "getPreset", "cctvMgrNo" : Public.CCTV.Preset["mgrNo"], "presetNo" : Public.CCTV.Preset["presetNo"] }, function(json){
				_common.callAjax("/proxy/xeusGateWay.json", { "path" : "getPTZPosition", "cctvMgrNo" : Public.CCTV.Preset["mgrNo"], "gbnCd" : Public.CCTV.Preset["cctvProp"]["gbnCd"] }, function(ptz){
					var mode = "insert";
					if(json.result) mode = "update";

					var prop = Public.CCTV.Preset["cctvProp"];
					var param = {
						"path" : mode + "Preset",
						"cctvMgrNo" : Public.CCTV.Preset["mgrNo"],
						"presetNo" : Public.CCTV.Preset["presetNo"],
						"gbnCd" : Public.CCTV.Preset["cctvProp"]["gbnCd"],
						"gid" : Public.CCTV.Preset["cctvProp"]["gid"],
						"pan" : ptz.result.pan,
						"tilt" : ptz.result.tilt,
						"zoom" : ptz.result.zoom,
						"spd" : 1,
						"dirX" : coordinates[0],
						"dirY" : coordinates[1],
						"dirDeg" : Spatial.getAngle(Public.CCTV.Preset["cctvProp"]["point"], coordinates),
					};

					confirm(param["presetNo"] + "번의 프리셋을 설정하시겠습니까?", function(){
						_common.callAjax("/proxy/xeusGateWay.json", param, function(json){

							_common.callAjax("/proxy/xeusGateWay.json", { "path" : "getPresets", "cctvMgrNo" : prop["mgrNo"], "gbnCd" : prop["gbnCd"] }, function(json){
						    	xeusSymbol.removeFeature(prop["gid"], "isPreset");
								var length = json.result.length;
								for(var i=11; i<20; i++){

									for(var l=0; l<length; l++){
										if(i == Number(json.result[l].presetNo)){
											var endPoint = [ Number(json.result[l].dirX), Number(json.result[l].dirY) ];
											var presetNo = json.result[l].presetNo;
											xeusSymbol.addPreset(prop["point"], endPoint, prop["gid"], presetNo);
											break;
										}
									}
								}
							}, false);
						}, false);
					})
				}, false);
			}, false);

			xeusSymbol.removeFeature(Public.CCTV.Preset["cctvProp"]["gid"], "isPreset");
			delete Public.CCTV.Preset["cctvProp"];
			delete Public.CCTV.Preset["mgrNo"];
			delete Public.CCTV.Preset["presetNo"];
			$("body").css("cursor", "default");
			$("#" + parentView).find(".selectPresetCancel").hide(500);
			$(".tab[target=" + parentView + "]").data("map").getMap().un('click', Public.CCTV.Preset.Start);
		}
	},

	Focus : {
		pointVector : null,
		lineVector : null,
		interaction : null,

		Start : function(){
			var cctvList = xeusCCTV.getGridPanePlayerData();
			if(cctvList.length == 0){
				alert("집중감시 할 CCTV를 지도에서 선택해주세요.")
				return false;
			}

			if(Public.StopEvent != null){
				Public.StopEvent();
			}

			this.pointVector = new ol.layer.Vector({
				source : new ol.source.Vector()
			});

			this.lineVector = new ol.layer.Vector({
				source: new ol.source.Vector()
			});

			this.interaction = new ol.interaction.Draw({
				source : this.pointVector.getSource(),
				type : "Point"
			});

			this.interaction.on("drawstart", function(e){
				Public.CCTV.Focus.pointVector.getSource().clear();
			});

			this.interaction.on("drawend", function(e){
				Public.CCTV.Focus.lineVector.getSource().clear();
				cctvList = xeusCCTV.getGridPanePlayerData();

				var endXY = e.feature.getGeometry().getCoordinates();
				e.feature.setStyle(new ol.style.Style({
					image: new ol.style.Circle({
						radius: 5,
						stroke: new ol.style.Stroke({
							color: 'rgba(128, 128, 0, 1.0)',
							width: 2
						}),
						fill : new ol.style.Fill({
							color: 'rgba(255, 255, 255, 0.5)'
						})
					}),
					text: new ol.style.Text({
						textBaseline : "alphabetic",
						offsetY : -25,
						font: '15px Calibri,sans-serif',
						text: endXY.join("\n").toString(),
						fill: new ol.style.Fill({
							color: 'red'
						}),
						stroke: new ol.style.Stroke({
							color: '#fff',
							width: 3
						})
					})
				}));

				for(var i=0; i<cctvList.length; i++){
					var mgrNo = cctvList[i].mgrNo;
					var gbnCd = cctvList[i].gbnCd;

					_common.callAjax("/getPresetCCTV.json", { "cctvMgrNo" : mgrNo, "tmx" : cctvList[i].point[0], "tmy" : cctvList[i].point[1] }, function(json){
						var param = {
							 "path" : "gotoPreset",
							 "cctvMgrNo" : json.result.cctv_mgr_no,
							 "presetNo" : json.result.preset_no
						};
						_common.callAjax("/proxy/xeusGateWay.json", param, function(){});
					}, false);


					var line = new ol.Feature(new ol.geom.LineString([cctvList[i].point, endXY]))
					var length = line.getGeometry().getLength();
					var metter = null;
					if(length > 100){
						metter = (Math.round(length / 1000 * 100) / 100) + ' ' + 'km';
					}else{
						metter = (Math.round(length * 100) / 100) + ' ' + 'm';
					}
					line.setStyle(new ol.style.Style({
						stroke: new ol.style.Stroke({
							color: 'yellow',
							width: 1
						}),
						text: new ol.style.Text({
							textAlign : "end",
							font: '15px Calibri,sans-serif',
							text: metter,
							fill: new ol.style.Fill({
								color: 'red'
							}),
							stroke: new ol.style.Stroke({
								color: '#fff',
								width: 3
							})
						})
					}))
					Public.CCTV.Focus.lineVector.getSource().addFeature(line);
				}
				if(Public.CCTV.Focus.interaction != null){
					xeusLayout.mapService.getMap().removeInteraction(Public.CCTV.Focus.interaction);
					Public.CCTV.Focus.interaction = null;
				}
			});

			xeusLayout.mapService.getMap().addLayer(this.lineVector);
			xeusLayout.mapService.getMap().addLayer(this.pointVector);
			xeusLayout.mapService.getMap().addInteraction(this.interaction);

			Public.StopEvent = function() {
				if(this.CCTV.Focus.interaction != null){
					xeusLayout.mapService.getMap().removeInteraction(this.CCTV.Focus.interaction);
					this.CCTV.Focus.interaction = null;
				}
				if(this.CCTV.Focus.lineVector != null){
					xeusLayout.mapService.getMap().removeLayer(this.CCTV.Focus.lineVector);
					this.CCTV.Focus.lineVector = null;
				}
				if(this.CCTV.Focus.pointVector != null){
					xeusLayout.mapService.getMap().removeLayer(this.CCTV.Focus.pointVector);
					this.CCTV.Focus.pointVector = null;
				}
				this.StopEvent = null;
			}
		}
	},

	Patrol : {
		vector : null,
		bufferVector : null,
		interval : null,
		interaction : null,

		RoopCnt : 0,
		AutoMoveStart : function(moveSec, moveBuffer, showCnt){
			var roopCnt = this.RoopCnt;
			var geomArray = null;
			if(this.vector.getSource().getFeatures().length > 0) geomArray = this.vector.getSource().getFeatures()[0].getProperties().geomArray;
			if(this.interval != null) clearInterval(this.interval);
			this.bufferVector = new ol.layer.Vector({
				source : new ol.source.Vector()
			});

			xeusLayout.mapService.getMap().addLayer(this.bufferVector);

			var searchFunction = function(){
				var circle = new ol.Feature(new ol.geom.Circle(geomArray[roopCnt], moveBuffer));
				Public.CCTV.Patrol.bufferVector.getSource().clear();
				Public.CCTV.Patrol.bufferVector.getSource().addFeature(circle);

				var param = {};
				var geometry = circle.getGeometry();
				if(geometry.getRadius != null){
					var radius = geometry.getRadius();
					var center = geometry.getCenter();
					param["radius"] = radius;
					param["center"] = "POINT(" + center.join(" ").toString() + ")";

					_common.callAjax("/cctv/getCctvList.json", param, function(json){
						xeusCCTV.closeAllGridPanePlayerAndKeepPane();
						if(json.result.length == 0){

						}else{
							var cctvArray = new Array();
							for(var i=0; i<json.result.length; i++){
								if(i < showCnt){
									var prop = {
										gid : json.result[i].gid,
										mgrNo : json.result[i].mgrNo,
										gbnCd : json.result[i].gbnCd,
										angle : json.result[i].viewDir,
										cctvNm : json.result[i].cctvNm,
										channelNo : json.result[i].chnlNo,
										deviceId : json.result[i].deviceId,
										point : Spatial.convertProjection([json.result[i].lng, json.result[i].lat], "EPSG:4326", "EPSG:5186")
									};
									cctvArray.push(prop);
								}else{
									break;
								}
							}
							xeusCCTV.viewVideo(JSON.stringify(cctvArray));
						}
					});
				}

				xeusLayout.mapService.getMap().getView().setCenter(geomArray[roopCnt]);
				if(geomArray.length - 1 == roopCnt){
					roopCnt = 0;
				}else{
					roopCnt++;
				}
			};

			searchFunction(roopCnt);
			this.interval = setInterval(function(){
				searchFunction(roopCnt);
			}, moveSec * 1000);
			$("#" + parentView).find("#patrolStart").hide();
			$("#" + parentView).find("#patrolStop").show();
		},

		AutoMoveStop : function(){
			xeusCCTV.closeAllGridPanePlayerAndKeepPane();
			clearInterval(this.interval);
			this.interval = null;

			if(this.bufferVector != null){
				this.bufferVector.getSource().clear();
				xeusLayout.mapService.getMap().removeLayer(this.bufferVector);
				this.bufferVector = null;
			}

			$("#" + parentView).find("#patrolStart").show();
			$("#" + parentView).find("#patrolStop").hide();
		},

		DrawStart : function() {
			if (_common.utils.isNullAndEmpty($("#" + parentView).find("#drawNm").val())) {
				alert("경로명을 입력해 주세요.");
				return false;
			}

			if (this.interaction != null) {
				xeusLayout.mapService.getMap().removeInteraction(this.interaction);
				this.interaction = null;
			}

			if (this.vector != null) {
				this.vector.getSource().clear();
				xeusLayout.mapService.getMap().removeLayer(this.vector);
				this.vector = null;
			}

			this.vector = new ol.layer.Vector({
				source : new ol.source.Vector({
					wrapX : false
				}),
				style : function(feature) {
					var geometry = feature.getGeometry();
					var styles = [new ol.style.Style({
							stroke : new ol.style.Stroke({
								color : "blue",
								width : 2
							})
						})
	              	];

					var last = null;
					var points = new Array();
					geometry.forEachSegment(function(start, end){
						points.push(start.slice());
						last = end.slice();
					});
					points.push(last);

					for(var i=0; i<points.length; i++){
						styles.push(new ol.style.Style({
							geometry: new ol.geom.Point(points[i]),
							image: new ol.style.Circle({
								radius: 5,
								stroke: new ol.style.Stroke({
									color: 'rgba(0, 128, 0, 1.0)',
									width: 2
								}),
								fill : new ol.style.Fill({
									color: 'rgba(255, 255, 255, 1.0)'
								})
							}),
							text: new ol.style.Text({
								font: '15px Calibri,sans-serif',
								textBaseline: "bottom",
								offsetY: -10,
								text: (i + 1) + "번 경로",
								fill: new ol.style.Fill({
									color: '#000'
								}),
								stroke: new ol.style.Stroke({
									color: '#fff',
									width: 3
								})
							})
						}));
					}

			        return styles;
			      }
			});

			var coordsLength = 0;
			var geomArray = null;
			var format = new ol.format.WKT();

			this.interaction = new ol.interaction.Draw({
				source : Public.CCTV.Patrol.vector.getSource(),
				type : "LineString",
				geometryFunction : function(coords, geom){
					if (!geom) geom = new ol.geom.LineString(null);
					geom.setCoordinates(coords);
					if(coords.length !== coordsLength){
						coordsLength = coords.length;
						geomArray = coords;
					}
					$("#" + parentView).find("#drawList").attr("wkt", format.writeGeometry(geom));
					return geom;
				}
			});
			this.interaction.on("drawstart", function(e) {
				Public.CCTV.Patrol.vector.getSource().clear();
			});
			this.interaction.on("drawend", function(e) {
				if (_common.utils.isNullAndEmpty($("#" + parentView).find("#drawNm").val())) {
					alert("경로명을 입력해 주세요.");
					setTimeout(function() {
						Public.CCTV.Patrol.vector.getSource().removeFeature(e.feature);
					}, 100);
				} else {
					var format = new ol.format.WKT();
					var wkt = format.writeGeometry(e.feature.getGeometry());
					var prop = {
						"draw_nm" : $("#" + parentView).find("#drawNm").val(),
						"ol_uid" : e.feature.ol_uid,
						"geomArray" : geomArray,
						"wkt" : wkt
					};

					e.feature.setProperties(prop);

					$("#" + parentView).find("#drawList").html("");
					for(var i=0; i<geomArray.length; i++){
						var xy = geomArray[i].join(", ").toString();
						var $p = $("<p>" + xy + "</p>").css({
							"margin-left" : "15px",
							"font-weight" : "bold"
						});
						$("#" + parentView).find("#drawList").append($p);
					}
					setTimeout(function(){
						Public.CCTV.Patrol.interaction.setActive(false);
					}, 100);
					$("#" + parentView).find("#drawCncl").hide("slow");
				}
			});

			xeusLayout.mapService.getMap().addLayer(this.vector);
			xeusLayout.mapService.getMap().addInteraction(this.interaction);

			Public.StopEvent = function() {
				$("#" + parentView).find("#drawCncl").hide("slow");
				$("#" + parentView).find("#drawList").removeAttr("wkt").html("");

				this.CCTV.Patrol.AutoMoveStop();

				if (this.CCTV.Patrol.interaction != null) {
					xeusLayout.mapService.getMap().removeInteraction(this.CCTV.Patrol.interaction);
					this.CCTV.Patrol.interaction = null;
				}

				if (this.CCTV.Patrol.vector != null) {
					this.CCTV.Patrol.vector.getSource().clear();
					xeusLayout.mapService.getMap().removeLayer(this.CCTV.Patrol.vector);
					this.CCTV.Patrol.vector = null;
				}

				this.StopEvent = null;
			}
		}
	},

	Dmtia : {

		vector : null,
		Start : function() {
			if (Public.StopEvent != null) {
				return false;
			}

			var _Source = new ol.source.Vector({
				wrapX : false
			});
			this.vector = new ol.layer.Vector({
				source : _Source
			});

			_common.callAjax("/eventDmtia/getLocationList.json", { }, function(json){
				var length = json.result.length;
				for(var i=0; i<length; i++){
					var lonlat = [Number(json.result[i].lon), Number(json.result[i].lat)];
					var center = Spatial.convertProjection(lonlat, "EPSG:4326", "EPSG:5186");
					var sym = "gf";
					if(json.result[i].dmtiaGender == "W") sym = "gm";

					var point = new ol.Feature(new ol.geom.Point(center));
					point.setStyle(new ol.style.Style({
						image: new ol.style.Icon({
							src: '../res/img/' + sym + '.png'
						})
					}));

					_Source.addFeature(point);
				}
			});

			xeusLayout.mapService.getMap().addLayer(this.vector);

			Public.StopEvent = function() {
				if (this.CCTV.Dmtia.vector != null) {
					xeusLayout.mapService.getMap().removeLayer(this.CCTV.Dmtia.vector);
					this.CCTV.Dmtia.vector = null;
				}
				this.StopEvent = null;
			}
		},

		DetailReload : function(dmtiaNm){
			var _source = this.vector.getSource();
			var _geoJSON = new ol.format.GeoJSON();

			$.ajax({
				url : _common.context() + "/CustomWFS",
				type : "POST",
				data : {
					tbl : "mon_dmtia_pt",
					//col : "sym_cd",
					col : "dmtia_nm",
					val : dmtiaNm
					//box : Spatial.getExtent(xeusLayout.mapService.getMap()).toString()
				},
				dataType : "json",
				beforeSend : function() {
					_source.clear();
				},
				success : function(json) {

					var _features = _geoJSON.readFeatures(json);
					for(var i=0; i<_features.length; i++){
						var feature = _features[i];
						feature.setProperties({
							"isIgnore" : true
						});
						feature.setStyle(new ol.style.Style({
							image: new ol.style.Circle({
								radius: 1,
								fill: new ol.style.Fill({
									color: 'rgba(0, 0, 255, 0.1)'
								}),
								stroke: new ol.style.Stroke({
									//color: 'rgba(240, 90 , 30, 1)',
									color: 'blue',
									width: 3
								})
							})
						}));
					}
					_source.addFeatures(_features);
					xeusLayout.mapService.getMap().getView().fit(_source.getExtent());
				},
				error : function(xhr, status, error) { }
			});
		},

		Reset : function(){
			if(Public.StopEvent) Public.StopEvent();
			this.Start();
		}
	}
}