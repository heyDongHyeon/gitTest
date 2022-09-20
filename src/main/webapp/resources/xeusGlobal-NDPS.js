/**
 * <pre>
 * CCTV관련 이벤트 입니다.
 * </pre>
 *
 * @auther 이주영
 */
Public.NDPS = {
	Search : {
		excelParam : null,

		vector : null,
		interaction : null,

		Start : function() {
			if (Public.StopEvent != null && Public.NDPS.Search.interaction != null) {
				Public.NDPS.Search.interaction.setActive(true);
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
				source : Public.NDPS.Search.vector.getSource(),
				type : drawMode,
				geometryFunction : geometryFunction
			});
			this.interaction.on("drawstart", function(e){
				var features = Public.NDPS.Search.vector.getSource().getFeatures();
				for(var i=0; i<features.length; i++){
					Public.NDPS.Search.vector.getSource().removeFeature(features[i]);
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
				$("#" + parentView).find(".searchWrapper").find("#objName").val('')
				$("#" + parentView).find(".searchWrapper").find("#emdCd").val('')

				//방송장비
				param["se"] = "B03001";

				_common.callAjax("/brdcstTrmnls/getList.json", param, function(json){
					$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").html("");
					for(var key in json){
						var list = json[key];
						if(list.length > 0){
							for(var i=0; i<list.length; i++){
								var no = list[i].innb; //단말기 고유 번호
								var se = list[i].se; //단말기 구분
								var nm = list[i].nm; //단말기명
								var telno = list[i].telno; // 단말기 전화번호
								var checked = '';//

								if ( telno === undefined ) telno ='';

								$('#send_select_list .send_select_item').each(function(){

									var key = $(this).attr('id').replace('send_select_', '');

									if ( key == no ) {
										checked = 'checked';
									}

								});

								var $tr = $("<tr class='tCenter' k='" + no + "'></tr>");
								//목록에 있을 시 체크 상태로 표출한다.
								$tr.append("<td width='20px'><input class='send_chk_box' type='checkbox' id='send_id_"+no+"' nm='"+nm+"' telno='"+telno+"' "+checked+"></td>");
								$tr.append("<td width='100px'>" + nm + "</td>");
								$tr.append("<td width='100px'>" + telno + "</td>");
								$("#" + parentView).find(".searchWrapper").find("#resultList").find("#send_table").append($tr);

							}

						}
					}

					//리스트 전체 삭제
					$('#send_list_all_remove').on('click', function(){
						$('#send_select_list').find('.send_list_remove').each(function(){
							$(this).click();

						});
					});

					//체크 박스 선택 시
					$('.send_chk_box').on('click', function(){
						var chk = $(this).prop('checked');
						var key = $(this).attr('id').replace('send_id_', '');
						var nm = $(this).attr('nm');
						var telno = $(this).attr('telno');
						if ( !chk ) {
							$('#send_select_list').find('#send_select_'+key).remove();

							if ( $('#send_select_list .send_select_item').length === 0 ) {

								$('#send_select_list').html(hintTag);
							};

						} else {
							$('#send_select_list p').remove();
							var str = '<span id="send_select_'+key+'" nm="'+nm+'" telno="'+telno+'"  class="send_select_item"><span>'+nm+'</span><span class="send_list_remove" key="'+key+'">x</span></span>';
							if ( $('#send_select_list').find('#send_select_'+key).length === 0 ) {
								$('#send_select_list').append(str);
							}

							$('#send_select_'+key).find('.send_list_remove').on('click', function(){
								var kay =$(this).attr('key');
								$('#send_id_'+key).prop('checked', false);
								$('#send_select_'+key).remove();


								if ( $('#send_select_list .send_select_item').length === 0 ) {

									$('#send_select_list').html(hintTag);
								};
							});
						}
					});

					//체크 박스 전체선택 시
					$('#send_all_chk').on('click', function(){
						var chk = $(this).prop('checked');
						$('#send_select_list p').remove();
						$('.send_chk_box').each(function(){
							var key = $(this).attr('id').replace('send_id_', '');
							var nm = $(this).attr('nm');
							var telno = $(this).attr('telno');

							if ( !chk ) {

								$('.send_chk_box').prop('checked', false);
								$('#send_select_list').find('#send_select_'+key).remove();

							} else {

								$('.send_chk_box').prop('checked', true);
								var str = '<span id="send_select_'+key+'" nm="'+nm+'" telno="'+telno+'"  class="send_select_item"><span>'+nm+'</span><span class="send_list_remove" key="'+key+'">x</span></span>';
								if ( $('#send_select_list').find('#send_select_'+key).length === 0  ) {
									$('#send_select_list').append(str);
								}


							}
						});

						if ( $('#send_select_list .send_select_item').length === 0 ) {

							$('#send_select_list').html(hintTag);
						};

						$('.send_list_remove').on('click', function(){
							var key =$(this).attr('key');
							$('#send_id_'+key).prop('checked', false);
							$('#send_select_'+key).remove();

							if ( $('#send_select_list .send_select_item').length === 0 ) {

								$('#send_select_list').html(hintTag);
							};
						});
					});

		/*			 위치 버튼 이벤트 입니다.
					$("#" + parentView).find(".searchWrapper").find("#resultList").find(".locBtn").click(function(){
						var juso = $(this).parent().parent().attr("k");	//tr의 k
//						console.log("juso = "+juso);
						var data = $(this).parent().parent().attr("p");	//tr의 k
//						console.log("data = "+data);
						var xyArr=[];
						xyArr.dsc=data;
						var xy = Spatial.convertAddrToXY(juso);
						xyArr.lon=xy[0];
						xyArr.lat=xy[1];
						var epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();	//왜갑자기4326(위도를) 5186으로 바꿩ㅈ
						var mainCenter = ol.proj.transform([xy[0], xy[1]], 'EPSG:4326', epsg);	//바꾼다..

						xeusLayout.mapService.moveToAnimation(0, [mainCenter[0], mainCenter[1]]);	//지도 위치로 가지는 것.
						xeusLayout.mapService.createNdmsPoint(xyArr);	//지도 위치로 가지는 것.
					});*/

				});
			});

			$("#" + parentView).find("#drawCncl").show("slow");
			xeusLayout.mapService.getMap().addLayer(this.vector);
			xeusLayout.mapService.getMap().addInteraction(this.interaction);

			Public.NDPS.StopEvent = function() {
				$("#" + parentView).find("#drawCncl").hide("slow");
				if (this.Search.interaction != null) {
					xeusLayout.mapService.getMap().removeInteraction(this.Search.interaction);
					this.Search.interaction = null;
				}
				if (this.Search.vector != null) {
					xeusLayout.mapService.getMap().removeLayer(this.Search.vector);
					this.Search.vector = null;
				}
				this.StopEvent = null;
			}
		}
	}

}