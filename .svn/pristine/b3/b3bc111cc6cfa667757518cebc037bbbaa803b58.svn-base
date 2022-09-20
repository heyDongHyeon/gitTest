/**
 * <pre>
 * NMS관련 이벤트 입니다.
 * </pre>
 *
 * @auther 이주영
 */
Public.NMS = {
	Search : {
		excelParam : null,
	},
	Monitoring : {
		interval : null,
		getList : function(){
			_common.callAjax("/nms/getStatusList.json", {}, function(json) {

				$("#" + parentView).find("#listTable").find("tbody").html("");

				var result = json.result;
				var length = result.length;

				for(var i=0; i<length; i++){
					var mgrNo = result[i].mgrNo;
					var type = mgrNo.substring(0, 3);
					var name = "";

					if("CTV" == type){
						type = "CCTV";
						name = result[i].cctvNm;
					}
					if("INF" == type){
						type = "스위치";
						name = result[i].facilityNm;
					}
					if("RNF" == type){
						type = "강우량계";
						name = result[i].raingaugeName;
					}
					if("PUM" == type){
						type = "펌프장";
						name = result[i].pumpjangName;
					}
					if("TES" == type){
						type = "블랙박스";
						name = JSON.parse(result[i].stateJson)["address"];
					}

					var str = "";
					str += "<tr class='tCenter' k='" + result[i].mgrNo + "' ftype='" + type + "'>";
					str += 	"<td>" + type + "</td>";
					str += 	"<td>" + _common.utils.validNull(name) + "</td>";
					str += 	"<td>" + _common.getCodeByName("C13", result[i].stateCd) + "</td>";
					str += 	"<td>" + new Date().formatDate(result[i].recvDat).substring(5) + "</td>";
					str += 	"<td>";
					str += 		"<button class='locBtn'></button>";
					str += 		"<button class='detailBtn'></button>";
					str += 	"</td>";
					str += "</tr>";

					var $tr = $(str);
					$tr.data(result[i]);

					$("#" + parentView).find("#listTable").find("tbody").append($tr);
				}

				/* 위치 버튼 이벤트 입니다. */
				$("#" + parentView).find(".searchWrapper").find(".locBtn").click(function(){
					var v = $(this).parent().parent().attr("k");
					_common.callAjax("/nms/getGeometryLocation.json", {k : v}, function(json) {
						xeusLayout.mapService.moveToAnimation(0, [json.result[0].annoX, json.result[0].annoY]);
					});
				});

				/* 상세 버튼 이벤트 입니다. */
				$("#" + parentView).find(".searchWrapper").find(".detailBtn").click(function(){
					var fType = $(this).parent().parent().attr("fType");
					var jsonStr = $(this).parent().parent().data("stateJson");

					xeusJsonFacilityParser.setJson(jsonStr);
					if(fType == "스위치") 	xeusJsonFacilityParser.getInfra();
					if(fType == "펌프장") 	xeusJsonFacilityParser.getPump();
					if(fType == "강우량계") xeusJsonFacilityParser.getRainFall();
					if(fType == "블랙박스") xeusJsonFacilityParser.getBlackBox();
				}).css("margin-right", "15px");
			});
		},
		Start : function(time) {
			if (this.interval != null) clearInterval(this.interval);
			this.interval = setInterval(function() {
				Public.NMS.Monitoring.getList();
			}, time);

			Public.StopEvent = function() {
				clearInterval(this.NMS.Monitoring.interval);
				this.NMS.Monitoring.interval = null;
				this.StopEvent = null;
			}
		}
	},

	Infra : {
		Start : function(evt) {
			var coordinates = evt.coordinate;
			var epsg = $(".tab[target=" + parentView + "]").data("map").getMap().getView().getProjection().getCode();
			var mainCenter = ol.proj.transform(coordinates, epsg, 'EPSG:4326');
			$("#regTable #lng").val(mainCenter[0]);
			$("#regTable #lat").val(mainCenter[1]);

			$("body").css("cursor", "default");
			$(".selectCancel").hide(500);
			$(".tab[target=" + parentView + "]").data("map").getMap().un('click', Public.NMS.Infra.Start);
		}
	},

	Cable : {
		vector : null,
		interaction : null,
		snapInteraction : null,
		selectInteraction : null,
		modifyInteraction : null,

		/**
		 * @Deprecated
		 */
		Search : function(){
			if(Public.StopEvent != null) Public.StopEvent();

			var vector = $(".tab[target=" + parentView + "]").data("map").getLayerByName("물리망");
			var features = vector.getSource().getFeatures();
			var length = features.length;

			var attr = {
				linkGbnCd : $("#" + parentView).find("#linkGbnCd").val(),
				netGbnCd : $("#" + parentView).find("#netGbnCd").val(),
				netNm : $("#" + parentView).find("#netNm").val(),
				cableTyp : $("#" + parentView).find("#cableTyp").val(),
				cableDesc : $("#" + parentView).find("#cableDesc").val(),
				stMgrNo : $("#" + parentView).find("#stMgrNo").val(),
				edMgrNo : $("#" + parentView).find("#edMgrNo").val()
			};

			for(var i=0; i<length; i++){
				var prop = features[i].getProperties();
			}
		},

		EditStart : function() {
			if (Public.StopEvent != null) Public.StopEvent();

			this.interaction = new ol.interaction.Select();

			this.modifyInteraction = new ol.interaction.Modify({
				features : Public.NMS.Cable.interaction.getFeatures()
			});

			$(".tab[target=" + parentView + "]").data("map").getMap().addInteraction(this.interaction);
			$(".tab[target=" + parentView + "]").data("map").getMap().addInteraction(this.modifyInteraction);

			var inter = this.interaction;
			this.interaction.on('select', function(e) {
				for (var i = 0; i < e.deselected.length; i++) {
					e.deselected[i].setStyle(null);
				}
				if (inter.getFeatures().getArray().length != 0) {
					if (e.selected[0] != null) {
						if (e.selected[0].getId().split(".")[0] != "asset_netwk") {
							inter.getFeatures().clear();
							e.preventDefault();
							return false;
						}
					}

					var data = e.selected[0].getProperties();
					e.selected[0].setStyle(new ol.style.Style({
						stroke : new ol.style.Stroke({
							color : 'blue',
							width : 3
						})
					}));

					$("#" + parentView).find("#gid").val(e.selected[0].getId().split(".")[1]);
					$("#" + parentView).find("#linkGbnCd").val(data["link_gbn_cd"]);
					$("#" + parentView).find("#netGbnCd").val(data["net_gbn_cd"]);
					$("#" + parentView).find("#lineColor").val(data["line_color"]);
					$("#" + parentView).find("#netNm").val(data["net_nm"]);
					$("#" + parentView).find("#cableTyp").val(data["cable_typ"]);
					$("#" + parentView).find("#cableDesc").val(data["cable_desc"]);
					$("#" + parentView).find("#cableTyp").val(data["cable_typ"]);
					$("#" + parentView).find("#cableDesc").val(data["cable_desc"]);
					$("#" + parentView).find("#stMgrNo").val(data["st_mgr_no"]);
					$("#" + parentView).find("#edMgrNo").val(data["ed_mgr_no"]);
				}
			});

			this.modifyInteraction.on('modifyend',function(e){
				var feature = e.features.getArray()[0];
				var prop = feature.getProperties();

				var format = new ol.format.WKT();
				var wkt = format.writeGeometry(feature.getGeometry());
				$("#" + parentView).find("#wkt").val(wkt);
			});

			Public.StopEvent = function() {
				if (this.NMS.Cable.interaction != null) {
					var array = this.NMS.Cable.interaction.getFeatures().getArray();
					for (var i = 0; i < array.length; i++) {
						array[i].setStyle(null);
					}
					$(".tab[target=" + parentView + "]").data("map").getMap().removeInteraction(this.NMS.Cable.interaction);
					this.NMS.Cable.interaction.getFeatures().clear();
					this.NMS.Cable.interaction = null;
					this.StopEvent = null;
				}

				if (this.NMS.Cable.modifyInteraction != null) {
					this.NMS.Cable.modifyInteraction.setActive(false);
					$(".tab[target=" + parentView + "]").data("map").getMap().removeInteraction(this.NMS.Cable.modifyInteraction);
					this.NMS.Cable.modifyInteraction = null;
				}
			}
		},

		DrawStart : function() {
			if (Public.StopEvent != null) {
				Public.NMS.Cable.interaction.setActive(true);
				Public.NMS.Cable.snapInteraction.setActive(true);
				Public.NMS.Cable.modifyInteraction.setActive(false);
				Public.NMS.Cable.selectInteraction.setActive(false);
				return false;
			}

			this.vector = $(".tab[target=" + parentView + "]").data("map").getLayerByName("물리망");

			this.selectInteraction = new ol.interaction.Select({
				condition: ol.events.condition.singleClick,
				toggleCondition: ol.events.condition.shiftKeyOnly,
				layers: function (layer) {
					return layer.get('fullName') == 'asset_fnms';
				}
			});
			this.modifyInteraction = new ol.interaction.Modify({
				features : Public.NMS.Cable.selectInteraction.getFeatures()
			});
			this.snapInteraction = new ol.interaction.Snap({
				source : Public.NMS.Cable.vector.getSource()
			});
			this.interaction = new ol.interaction.Draw({
				source : Public.NMS.Cable.vector.getSource(),
				type : "LineString"
			});

			this.selectInteraction.on('select', function(evt){
				var selected = evt.selected;
				var deselected = evt.deselected;
				for (var i = 0; i < deselected.length; i++) {
					deselected[i].setStyle(null);
				}

				if (selected.length) {
					selected.forEach(function(feature){
						feature.setStyle(new ol.style.Style({
							stroke: new ol.style.Stroke({
								color : 'blue',
								width : 3
							})
						}));
					});
				} else {
					deselected.forEach(function(feature){
						feature.setStyle(null);
					});
				}
			});

			this.modifyInteraction.on('modifyend',function(e){
				var feature = e.features.getArray()[0];
				var prop = feature.getProperties();

				var format = new ol.format.WKT();
				var wkt = format.writeGeometry(feature.getGeometry());
				$("#" + parentView).find("option[ol_uid=" + prop["ol_uid"] + "]").attr("wkt", wkt);
			});

			this.interaction.on("drawend", function(e) {
				if (_common.utils.isNullAndEmpty($("#" + parentView).find("#netNm").val())) {
					alert("케이블이름을 입력해 주세요.");
					$("#" + parentView).find("#cableNm").focus();
					setTimeout(function() {
						Public.NMS.Cable.vector.getSource().removeFeature(e.feature);
					}, 100);
				} else {
					var format = new ol.format.WKT();
					var wkt = format.writeGeometry(e.feature.getGeometry());
					var prop = {
						"link_gbn_cd" : $("#" + parentView).find("#linkGbnCd").val(),
						"line_color" : $("#" + parentView).find("#lineColor").val(),
						"net_gbn_cd" : $("#" + parentView).find("#netGbnCd").val(),
						"net_nm" : $("#" + parentView).find("#netNm").val(),
						"cable_typ" : $("#" + parentView).find("#cableTyp").val(),
						"cable_desc" : $("#" + parentView).find("#cableDesc").val(),
						"st_mgr_no" : $("#" + parentView).find("#stMgrNo").val(),
						"ed_mgr_no" : $("#" + parentView).find("#edMgrNo").val(),
						"ol_uid" : e.feature.ol_uid,
						"wkt" : wkt
					};
					var getStyle = function(color, width) {
						if (width == null)
							width = 1;
						return new ol.style.Style({
							stroke : new ol.style.Stroke({
								color : color,
								width : width
							})
						})
					};

					e.feature.setProperties(prop);
					e.feature.setStyle(getStyle("blue"));
					var $option = $("<option>" + prop["net_nm"] + "</option>").attr(prop);
					$option.click(function() {
						var uid = $(this).attr("ol_uid");
						var features = Public.NMS.Cable.vector.getSource().getFeatures();
						if (features.length > 0) {
							for (var i = 0; i < features.length; i++) {
								var feature = features[i];
								feature.setStyle(getStyle("blue"));

								if (feature.get("ol_uid") == uid) {
									feature.setStyle(getStyle("red", 2));
									var attr = feature.getProperties();
									$("#" + parentView).find("#linkGbnCd").val(attr["link_gbn_cd"]);
									$("#" + parentView).find("#lineColor").val(attr["line_color"]);
									$("#" + parentView).find("#netGbnCd").val(attr["net_gbn_cd"]);
									$("#" + parentView).find("#netNm").val(attr["net_nm"]);
									$("#" + parentView).find("#cableTyp").val(attr["cable_typ"]);
									$("#" + parentView).find("#cableDesc").val(attr["cable_desc"]);
									$("#" + parentView).find("#stMgrNo").val(attr["st_mgr_no"]);
									$("#" + parentView).find("#edMgrNo").val(attr["ed_mgr_no"]);
								}
							}
						}
					}).dblclick(function() {
						var $this = $(this);
						confirm("케이블을 삭제하시겠습니까?", function() {
							var uid = $this.attr("ol_uid");
							var features = Public.NMS.Cable.vector.getSource().getFeatures();
							if (features.length > 0) {
								for (var i = 0; i < features.length; i++) {
									var feature = features[i];
									if (feature.get("ol_uid") == uid) {
										Public.NMS.Cable.vector.getSource().removeFeature(feature);
									}
								}
							}
							$this.remove();
							$("#" + parentView).find(".sendData").val("");
						});
					});
					$("#" + parentView).find("td#cableListWrapper").find("#cableList").find("optgroup").append($option);
					$("#" + parentView).find(".sendData").val("");
				}
			});

			$("#" + parentView).find("#drawCncl").show("slow");
			$(".tab[target=" + parentView + "]").data("map").getMap().addInteraction(this.interaction);
			$(".tab[target=" + parentView + "]").data("map").getMap().addInteraction(this.snapInteraction);
			$(".tab[target=" + parentView + "]").data("map").getMap().addInteraction(this.modifyInteraction);
			$(".tab[target=" + parentView + "]").data("map").getMap().addInteraction(this.selectInteraction);

			Public.StopEvent = function() {
				$("#" + parentView).find("#drawCncl").hide("slow");
				$("#" + parentView).find("td#cableListWrapper").find("#cableList").find("optgroup").html("");
				if (this.NMS.Cable.selectInteraction != null) {
					this.NMS.Cable.selectInteraction.setActive(false);
					$(".tab[target=" + parentView + "]").data("map").getMap().removeInteraction(this.NMS.Cable.selectInteraction);
					this.NMS.Cable.selectInteraction = null;
				}
				if (this.NMS.Cable.modifyInteraction != null) {
					this.NMS.Cable.modifyInteraction.setActive(false);
					$(".tab[target=" + parentView + "]").data("map").getMap().removeInteraction(this.NMS.Cable.modifyInteraction);
					this.NMS.Cable.modifyInteraction = null;
				}
				if (this.NMS.Cable.snapInteraction != null) {
					this.NMS.Cable.snapInteraction.setActive(false);
					$(".tab[target=" + parentView + "]").data("map").getMap().removeInteraction(this.NMS.Cable.snapInteraction);
					this.NMS.Cable.snapInteraction = null;
				}
				if (this.NMS.Cable.interaction != null) {
					this.NMS.Cable.interaction.setActive(false);
					$(".tab[target=" + parentView + "]").data("map").getMap().removeInteraction(this.NMS.Cable.interaction);
					this.NMS.Cable.interaction = null;
				}
				this.StopEvent = null;
			}
		},

		DelStart : function() {
			if (Public.StopEvent != null) {
				Public.NMS.Cable.interaction.setActive(true);
				return false;
			}

			this.interaction = new ol.interaction.Select();
			$(".tab[target=" + parentView + "]").data("map").getMap().addInteraction(this.interaction);
			var inter = this.interaction;
			this.interaction.on('select', function(e) {
				for (var i = 0; i < e.deselected.length; i++) {
					e.deselected[i].setStyle(null);
				}
				if (inter.getFeatures().getArray().length != 0) {
					if (e.selected[0] != null) {
						if (e.selected[0].getId().split(".")[0] != "asset_netwk") {
							inter.getFeatures().clear();
							e.preventDefault();
							return false;
						}
					}

					var data = e.selected[0].getProperties();
					data["gid"] = e.selected[0].getId().split(".")[1];

					e.selected[0].setProperties(data);
					e.selected[0].setStyle(new ol.style.Style({
						stroke : new ol.style.Stroke({
							color : "blue",
							width : 3
						})
					}));

					var $option = $("<option>" + data["net_nm"] + "</option>").attr(data);
					if ($("#" + parentView).find("td#cableListWrapper").find("#cableList").find("optgroup").find("option[gid=" + data["gid"] + "]").length == 0) {
						$option.dblclick(function(){
							var $this = $(this);
							confirm("선택된 케이블을 목록에서 제거하시겠습니까?", function(){
								$this.remove();
								var array = Public.NMS.Cable.interaction.getFeatures().getArray();
								for (var i = 0; i < array.length; i++) {
									array[i].setStyle(null);
								}
								Public.NMS.Cable.interaction.getFeatures().clear();
							});
						});
						$("#" + parentView).find("td#cableListWrapper").find("#cableList").find("optgroup").append($option);
					}
				}
			});

			Public.StopEvent = function() {
				if (this.NMS.Cable.interaction != null) {
					$("#" + parentView).find("td#cableListWrapper").find("#cableList").find("optgroup").html("");
					var array = this.NMS.Cable.interaction.getFeatures().getArray();
					for (var i = 0; i < array.length; i++) {
						array[i].setStyle(null);
					}
					$(".tab[target=" + parentView + "]").data("map").getMap().removeInteraction(this.NMS.Cable.interaction);
					this.NMS.Cable.interaction.getFeatures().clear();
					this.NMS.Cable.interaction = null;
					this.StopEvent = null;
				}
			}
		}
	},

	Parking : {
		interval : null,
		Start : function() {
			var isFirst = true;
			var $img1 = $("#" + parentView).find(".searchWrapper").find("#img1");
			var $img2 = $("#" + parentView).find(".searchWrapper").find("#img2");
			var url = $img1.attr("src");

			var $allCnt = $("#" + parentView).find(".searchWrapper").find("#stateWrap").find("#allCnt");
			var $nowCnt = $("#" + parentView).find(".searchWrapper").find("#stateWrap").find("#nowCnt");
			var $marginCnt = $("#" + parentView).find(".searchWrapper").find("#stateWrap").find("#marginCnt");

			$allCnt.text("전체 주차 공간 : 100 대");

			if(this.interval != null) clearInterval(this.interval);
			this.interval = setInterval(function() {
				var randVal = Math.random() * (90- 100) + 100;
				var nowVal = Math.floor(randVal);
				var marginVal = 100 - nowVal;
				var noCacheUrl = url + "&callTime=" + new Date().getYMDHMS();

				if(isFirst){
					$img1.toggle("clip", "slow", function(){
						$(this).attr("src", noCacheUrl);
						$img2.toggle("clip", "slow");
					});

					$nowCnt.text("현재 주차 수 : " + nowVal + " 대");
					$marginCnt.text("여유 공간 : " + marginVal + " 대");
					isFirst = false;
				}else{
					$img2.toggle("clip", "slow", function(){
						$(this).attr("src", noCacheUrl);
						$img1.toggle("clip", "slow");
					});

					$nowCnt.text("현재 주차 수 : " + nowVal + " 대");
					$marginCnt.text("여유 공간 : " + marginVal + " 대");
					isFirst = true;
				}
			}, 3000);

			Public.StopEvent = function() {
				clearInterval(this.NMS.Parking.interval);
				this.NMS.Parking.interval = null;
				this.StopEvent = null;
			}
		}
	},

	WaterPump : {
		tooltip : null,
		overlay : null,
		interaction : null,
		CreateTooltip : function(){
			var $ele = $('<div id="ol-tooltip" class="ol-tooltip"></div>');
			$("#xeus-map-content-nms").append($ele);
			this.tooltip = $("#" + parentView).find("#ol-tooltip")[0];
			this.overlay = new ol.Overlay({
				element: Public.NMS.WaterPump.tooltip,
				offset: [0, -20],
				positioning: 'bottom-center'
			});

			$(".tab[target=" + parentView + "]").data("map").getMap().addOverlay(this.overlay);

			return this;
		},
		Start : function(evt){
			var map = $(".tab[target=" + parentView + "]").data("map").getMap();

			var pixel = evt.pixel;
			var feature = map.forEachFeatureAtPixel(pixel, function(feature, layer) {
				var isGmxLayer = "fullName" in layer.getProperties();
				if(feature.getGeometry() instanceof ol.geom.LineString){
					if(isGmxLayer){
						if(layer.getProperties()["fullName"] == "asset_pump_sec"){
							return feature;
						}
					}
				}
			});
			Public.NMS.WaterPump.tooltip.style.display = feature ? '' : 'none';
			if (feature) {
				Public.NMS.WaterPump.overlay.setPosition(evt.coordinate);
				var str = feature.get('pumpjang_name') + " : " + feature.get('outlevel') + "m";
				Public.NMS.WaterPump.tooltip.innerHTML = str;
			}

			/*Public.StopEvent = function(){
				map.un("pointermove", this.NMS.WaterPump.Start);
				map.removeOverlay(this.NMS.WaterPump.overlay);
				this.NMS.WaterPump.overlay = null;
				this.NMS.WaterPump.tooltip = null;
				this.StopEvent = null;
			}*/
		},
		Stop : function(){
			var map = $(".tab[target=" + parentView + "]").data("map").getMap();
			map.un("pointermove", Public.NMS.WaterPump.Start);
			map.removeOverlay(Public.NMS.WaterPump.overlay);
			Public.NMS.WaterPump.overlay = null;
			Public.NMS.WaterPump.tooltip = null;
		}
	},

	RainFall : {
		Start : function(){

			this.vector = $(".tab[target=" + parentView + "]").data("map").getLayerByName("강우량");
			this.overlays = new Array();

			var features = this.vector.getSource().getFeatures();
			var length = features.length;
			for(var i=0; i<length; i++){
				var feature = features[i];
				var prop = feature.getProperties();
				var center = feature.getGeometry().getCoordinates();
				var $markerElem = $("<div class='arrow_box'></div>").text(prop["raingauge_name"]).attr("id", prop["mgr_no"]);
				var $markerCntn = $("<div class='cntnWrap'></div>");
				var $tr1 = $("<tr><td>금일우량</td><td>" + prop["rain_day"] + "</td></tr>");
				var $tr2 = $("<tr><td>시간우량</td><td>" + prop["rain_hour"] + "</td></tr>");
				var $tr3 = $("<tr><td>시간최대</td><td>" + prop["rain_hourmax"] + "</td></tr>");
				var $tr4 = $("<tr><td>누계우량</td><td>" + prop["rain_year"] + "</td></tr>");
				var $tbl = $("<table></table>").append($tr1).append($tr2).append($tr3).append($tr4);

				$markerCntn.append($tbl);
				$markerElem.append($markerCntn);

				$("#" + parentView).append($markerElem);

				var overlay = new ol.Overlay({
					element: document.getElementById(prop["mgr_no"]),
					positioning: 'bottom-center',
					offset: [0, -20],
					stopEvent: false
				});
				overlay.setPosition(center);
				this.overlays.push(overlay);
				$(".tab[target=" + parentView + "]").data("map").getMap().addOverlay(overlay);
			}

			/*Public.StopEvent = function() {
				$("#" + parentView).find(".rainfallOverlay").remove();
				var length = Public.NMS.RainFall.overlays.length;
				for(var i=0; i<length; i++){
					$(".tab[target=" + parentView + "]").data("map").getMap().removeOverlay(Public.NMS.RainFall.overlays[i]);
				}
			}*/
		},
		Stop : function(){
			$("#" + parentView).find(".rainfallOverlay").remove();
			var length = Public.NMS.RainFall.overlays.length;
			for(var i=0; i<length; i++){
				$(".tab[target=" + parentView + "]").data("map").getMap().removeOverlay(Public.NMS.RainFall.overlays[i]);
			}
		}
	},

	Ring : {
		vector : null,

		Start : function(features){

			if (Public.StopEvent != null) Public.StopEvent();

			this.vector = new ol.layer.Vector({
				source : new ol.source.Vector({
					features : features,
					wrapX : false
				})
			});

			$(".tab[target=" + parentView + "]").data("map").getMap().addLayer(this.vector);
			$(".tab[target=" + parentView + "]").data("map").getMap().getView().fit(this.vector.getSource().getExtent());

			Public.StopEvent = function() {
				if (this.NMS.Ring.vector != null) {
					$(".tab[target=" + parentView + "]").data("map").getMap().removeLayer(this.NMS.Ring.vector);
					this.NMS.Ring.vector = null;
				}
				this.StopEvent = null;
			}
		},
		getList : function(type){
			_common.callAjax("/nms/getInfra.json", { "facilityClscd" : type, "sortCol" : "facility_clscd", "sortTyp" : "asc" }, function(json){
				var list = json.result;
				var length = list.length;

				var $tbl = $("#" + parentView).find("#resultList").find("table");
				var str = "";
				var preIdx = 0;
				for(var i=0; i<list.length; i++){
					var clscd = list[i].facilityClscd;
					var nowIdx = Number(clscd.substring(4, 6));
					if(preIdx < nowIdx){
						preIdx = nowIdx;
						str += "<tr>";
						str += 		"<th class='grpTitle'>" + clscd + "</th>";
						str += 		"<td><button class='locBtn isGrp' clscd='" + clscd + "'></button></td>";
						str += "</tr>";
					}
					str += "<tr clscd='" + clscd + "' class='clscdTr'>";
					str += 		"<td class='tCenter'>" + list[i].facilityNm + "</td>";
					str += 		"<td><button class='locBtn' k='" + list[i].mgrNo + "'></button></td>";
					str += "</tr>";
				}

				var $tbody = $(str);

				$tbl.html($tbody);
				$tbl.find(".grpTitle").next().width(40);
				$tbl.find(".clscdTr").hide();

				$tbl.find(".grpTitle").click(function(){
					$tbl.find(".clscdTr").hide();

					var $target = $tbl.find("tr[clscd=" + $(this).text() + "]");
					if($target.is("visible")){
						$target.hide();
					}else{
						$target.show();
					}
				});

				/* 위치 버튼 이벤트 입니다. */
				$("#" + parentView).find(".searchWrapper").find("#resultList").find(".locBtn").click(function(){
					if($(this).hasClass("isGrp")){
						var clscd = $(this).attr("clscd");
						var array = new Array();
						$tbl.find("tr[clscd=" + clscd + "]").each(function(i, e){
							array.push($(this).find(".locBtn").attr("k"));
						});

						// N05021-101R-11-8P > N09008-101R-13-8P
						_common.callAjax("/netwk/getNmsCableList.json", {stMgrNo : array.join("|").toString(), netGbnCd : "11"}, function(json) {
							var result = json.result;
							var length = result.length;

							if(length == 0){
								alert("링 정보가 존재하지 않습니다.");
								return false;
							}else{
								var features = new Array();
								for(var i=0; i<length; i++){
									var format = new ol.format.WKT();

									var feature = format.readFeature(result[i].wkt, {
										dataProjection: 'EPSG:5186',
										featureProjection: 'EPSG:5186'
									});

									feature.setStyle(new ol.style.Style({
										stroke : new ol.style.Stroke({
											color : result[i].lineColor,
											width : 3
										})
									}));

									features.push(feature);

									Public.NMS.Ring.Start(features);
								}
							}
						});
					}else{
						var v = $(this).attr("k");
						_common.callAjax("/nms/getGeometryLocation.json", {k : v}, function(json) {
							xeusLayout.mapService.moveToAnimation(0, [json.result[0].annoX, json.result[0].annoY]);
						});
					}
				});
			});
		}
	}
}