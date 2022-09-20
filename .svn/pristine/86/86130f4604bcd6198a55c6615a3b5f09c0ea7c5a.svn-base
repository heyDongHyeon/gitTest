/**
 * <pre>
 * 영상반출관련 이벤트 입니다.
 * </pre>
 *
 * @auther 이은규(xeusGlobalCCTV 복사)
 */
Public.TVIUS = {
	Init : {
		Clear : function() {
			//페이지 이동 시 setTimeout으로 작동중이던 interval을 모두 종료
			if (intervalListChk !== null)	clearTimeout(intervalListChk);
			if (intervalPrevDown !== null)	clearTimeout(intervalPrevDown);
			if (intervalStat !== null)		clearTimeout(intervalStat);
			if (intervalPrev !== null)		clearTimeout(intervalPrev);
			if (intervalRqst !== null)		clearTimeout(intervalRqst);
			if (intervalService !== null)	clearTimeout(intervalService);
		}
	},
	Map : {
		Clear : function() {
			//페이지 이동 시 히트맵 레이어, 차량운행겁색 레이어를 삭제
			if (crmsHeatLayer != null)		xeusLayout.mapService.removeLayer(crmsHeatLayer);
			if (crmsCarSchLayer != null)	crmsCarSchSource.clear();
			if (crmsCarSchLayer != null)	xeusLayout.mapService.removeLayer(crmsCarSchLayer);
		}
	},
	Car : {
		Clear : function() {
			//페이지 이동 시 히트맵 레이어, 차량운행겁색 레이어를 삭제
			if ($('#car-sch-image-panel').length > 0) $('#car-sch-image-panel').remove();
		}
	},
	Search : {

		vector : null,
		interaction : null,

		Start : function() {
			if (Public.StopEvent != null && Public.TVIUS.Search.interaction != null) {
				Public.TVIUS.Search.interaction.setActive(true);
				return false;
			}

			this.vector = new ol.layer.Vector({
				source : new ol.source.Vector({
					wrapX : false
				})
			});

			var target = '';
			var reqGbn = '';
			if($('.aviWrapper').is(':visible')){
				target = '.aviWrapper';
				reqGbn = '반출';//열람도 똑같은 로직이므로 반출로 통일
			}else if($('.carWrapper').is(':visible')){
				target = '.carWrapper';
				reqGbn = '차량번호';
			}

			var drawMode = $(".drawType:checked").val();
			var geometryFunction = null;
			if(drawMode == "Box"){
				drawMode = "Circle";
				geometryFunction = ol.interaction.Draw.createBox();
			}

			this.interaction = new ol.interaction.Draw({
				source : Public.TVIUS.Search.vector.getSource(),
				type : drawMode,
				geometryFunction : geometryFunction
			});
			this.interaction.on("drawstart", function(e){
				var features = Public.TVIUS.Search.vector.getSource().getFeatures();
				for(var i=0; i<features.length; i++){
					Public.TVIUS.Search.vector.getSource().removeFeature(features[i]);
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

				if(target != ''){
					//장애 CCTV는 검색되지 않게 하는 파라미터
					//state_cd 12를 제외한 나머지가 검색된다.
					//현재 장애 CCTV여도 과거 영상은 존재할 수도 있으므로 주석처리 해놓음.
					//param['stateCd'] = '12';
					_common.callAjax("/cctv/getCctvList.json", param, function(json){
						if(json.result.length == 0){
							//var $tr = $("<tr><td colspan='3' class='tCenter'>결과가 존재하지 않습니다.</td></tr>");
							//$(".searchWrapper").find("#resultTable").find("tbody").html($tr);
							alert('검색결과가 존재하지 않습니다.');
						}else{
							for(var i=0; i<json.result.length; i++){

								//console.log(json.result[i]);

								var mgrNo = json.result[i].mgrNo;
								var cctvNm = json.result[i].cctvNm;
								var point = Spatial.convertProjection([json.result[i].lng, json.result[i].lat], "EPSG:4326", "EPSG:5186");
								var type = mgrNo.substring(0, 3);

								if(target == ".aviWrapper"){
									if ( SELECTED_CCTV_COUNT < 10){
										SELECTED_CCTV_COUNT++;
										addCctv(mgrNo, cctvNm, point, reqGbn);

										/**
										 * CCTV 추가 시 모든 CCTV row의 시간 유효성을 검사한다.
										 * tr 고유 식별자를 정하지 않고 모든 CCTV row를 다 돌린다.
										 */
										$('#tbl_cctv_list .cctv_row').each(function() {
											cctvTimeChk($(this).find('[name=cctv_sdate]'));
										});
									} else {
										alert("선택 할 수 있는 CCTV는 최대 10개입니다.");
										break;
									}
								} else if(target == ".carWrapper"){
									if ( SELECTED_CCTV_COUNT_CAR < 10){
										SELECTED_CCTV_COUNT_CAR++;
										addCctv(mgrNo, cctvNm, point, reqGbn);

										/**
										 * CCTV 추가 시 모든 CCTV row의 시간 유효성을 검사한다.
										 * tr 고유 식별자를 정하지 않고 모든 CCTV row를 다 돌린다.
										 */
										$('#tbl_cctv_list_car .cctv_row').each(function() {
											cctvTimeChk($(this).find('[name=cctv_sdate]'));
										});
									} else {
										alert("선택 할 수 있는 CCTV는 최대 10개입니다.");
										break;
									}
								}
								resizeDone();
							}
						}
					});
				}

			});

			$(target).find("#drawCncl").show("slow");
			xeusLayout.mapService.getMap().addLayer(this.vector);
			xeusLayout.mapService.getMap().addInteraction(this.interaction);

			Public.StopEvent = function() {
				$(target).find("#drawCncl").hide("slow");
				if (this.TVIUS.Search.interaction != null) {
					xeusLayout.mapService.getMap().removeInteraction(this.TVIUS.Search.interaction);
					this.TVIUS.Search.interaction = null;
				}
				if (this.TVIUS.Search.vector != null) {
					xeusLayout.mapService.getMap().removeLayer(this.TVIUS.Search.vector);
					this.TVIUS.Search.vector = null;
				}
				this.StopEvent = null;
			}
		}
	}
}