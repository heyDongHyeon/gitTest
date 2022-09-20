(function(){

	function validPatrolData(){
		var moveSec = Number($("#" + parentView).find("#moveSec").val());
		var moveBuffer = Number($("#" + parentView).find("#moveBuffer").val());
		var showCnt = Number($("#" + parentView).find("#showCnt").val());
		var drawNm = $("#" + parentView).find("#drawNm").val();

		if(Public.CCTV.Patrol.vector == null){
			alert("경로가 존재하지 않아 순찰감시를 시작 할 수 없습니다.");
			return false;
		}

		if(isNaN(moveSec)){
			alert("이동간격은 숫자만 입력하실 수 있습니다.");
			return false;
		}
		if(moveSec < 10 || moveSec > 101){
			alert("이동간격은 10~100 초 사이로 설정하실 수 있습니다.");
			return false;
		}
		if(isNaN(moveBuffer)){
			alert("검색반경은 숫자만 입력하실 수 있습니다.");
			return false;
		}
		if(moveBuffer < 1 || moveBuffer > 10001){
			alert("검색반경은 1~10000 미터 사이로 설정하실 수 있습니다.");
			return false;
		}
		if(isNaN(showCnt)){
			alert("표시CCTV는 숫자만 입력하실 수 있습니다.");
			return false;
		}
		if(!Number.isInteger(showCnt)){
			alert("표시CCTV는 정수만 입력하실 수 있습니다.");
			return false;
		}
		if(showCnt < 1 || showCnt > 21){
			alert("표시CCTV는 1~20 개 사이로 설정하실 수 있습니다.");
			return false;
		}
		if(drawNm == null || drawNm == ""){
			alert("경로명을 입력해 주세요.");
			return false;
		}

		return true;
	}

	function viewPathGeometry(v){
		_common.callAjax("/cctv/getPatrolItem.json", {gid : v}, function(json){
			$("#" + parentView).find("#moveSec").val(json.result.intvlTime);
			$("#" + parentView).find("#moveBuffer").val(json.result.srchRadius);
			$("#" + parentView).find("#showCnt").val(json.result.cctvLimit);
			$("#" + parentView).find("#drawNm").val(json.result.titleNm);

			xeusCCTV.VIDEO_GRID_COLS = 3;
			xeusCCTV.createVideoGridPane();
			//$("#" + parentView).find("#overlay-east-bar").find(".overlay-bar-button").eq(Number(json.result.colNum) - 1).click();

			//var wkt = json.result.wkt.replace("MULTI", "").replace("(", "").replace(")", "");
			var wkt = json.result.wkt;
			var format = new ol.format.WKT();
			var feature = format.readFeature(wkt);
			var prop = {
				"draw_nm" : $("#" + parentView).find("#drawNm").val(),
				"ol_uid" : feature.ol_uid,
				"geomArray" : feature.getGeometry().getCoordinates(),
				"wkt" : wkt
			};

			feature.setProperties(prop);

			if (Public.CCTV.Patrol.vector != null) {
				Public.CCTV.Patrol.vector.getSource().clear();
				xeusLayout.mapService.getMap().removeLayer(Public.CCTV.Patrol.vector);
				Public.CCTV.Patrol.vector = null;
			}

			Public.CCTV.Patrol.vector = new ol.layer.Vector({
				source : new ol.source.Vector({
					wrapX : false,
					features: [feature]
				}),
				style : function(f) {
					var geometry = f.getGeometry();
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

			xeusLayout.mapService.getMap().addLayer(Public.CCTV.Patrol.vector);

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
		});
	}

	/* 경로 그리기 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#drawBtn").click(function(){
		if(Public.CCTV.Patrol.interval != null){
			confirm("순찰감시를 종료하고 그리기를 시작하시겠습니까?", function(){
				Public.CCTV.Patrol.AutoMoveStop();
				Public.CCTV.Patrol.DrawStart();
				$("#" + parentView).find("#drawCncl").show("slow");
			});
		}else{
			var drawNm = $("#" + parentView).find(".searchWrapper").find("#drawNm").val();
			if(_common.utils.isNullAndEmpty(drawNm)){
				alert("경로명을 입력해 주세요.");
				return false;
			}
			Public.CCTV.Patrol.DrawStart();
			$("#" + parentView).find("#drawCncl").show("slow");
		}
	});

	/* 경로취소 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#drawCncl").click(function(){
		Public.CCTV.Patrol.interaction.setActive(false);
		$("#" + parentView).find("#drawCncl").hide("slow");
	});

	/* 감시 시작 이벤트입니다. */
	$("#" + parentView).find(".searchWrapper").find("#patrolStart").click(function(){
		if(validPatrolData()){
			var moveSec = Number($("#" + parentView).find("#moveSec").val());
			var moveBuffer = Number($("#" + parentView).find("#moveBuffer").val());
			var showCnt = Number($("#" + parentView).find("#showCnt").val());

			Public.CCTV.Patrol.AutoMoveStart(moveSec, moveBuffer, showCnt);
		}
	});

	/* 감시 취소 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#patrolStop").click(function(){
		Public.CCTV.Patrol.AutoMoveStop();
	});

	/* 저장 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#saveBtn").click(function(){
		if(validPatrolData()){
			confirm("저장하시겠습니까?", function(){
				var param = {
					intvlTime : $("#" + parentView).find("#moveSec").val(),
					srchRadius : $("#" + parentView).find("#moveBuffer").val(),
					cctvLimit : $("#" + parentView).find("#showCnt").val(),
					titleNm : $("#" + parentView).find("#drawNm").val(),
					wkt : Public.CCTV.Patrol.vector.getSource().getFeatures()[0].getProperties().wkt
				}
				$("#" + parentView).find("#overlay-east-bar").find(".overlay-bar-button").each(function(i){
					if(Number($(this).css("border-bottom").substring(0, 1)) > 0){
						param["colNum"] = i + 1;
					}
				});
				_common.callAjax("/cctv/addPatrol.json", param, function(json){
					if(json.result){
						alert("경로가 저장되었습니다.");

						var $tr = $("<tr></tr>");
						var $td1 = $("<td></td>").text(json.vo.titleNm);
						var $td2 = $("<td></td>").addClass("tCenter").attr("k", json.vo.gid);
						var $btn1 = $("<button class='blackBtn viewBtn' style='margin-right:5px'>보기</button> ").click(function(){
							viewPathGeometry(json.vo.gid);
						});
						var $btn2 = $("<button class='blackBtn removeBtn'>삭제</button>").click(function(){
							var msg = "";
							if(Public.StopEvent != null) msg = "<br>참고) 진행중인 순찰감시가 종료됩니다.";

							var v = $(this).parent().attr("k");
							var $tr = $(this).parent().parent();
							confirm("삭제하시겠습니까?" + msg, function(){
								_common.callAjax("/cctv/delPatrol.json", {k : v}, function(json){
									if(json.result){
										alert("경로가 삭제되었습니다.");
										$("#" + parentView).find("#drawNm, #moveSec, #moveBuffer, #showCnt").val("");
										$tr.remove();
										if($('#listTable > tbody > tr').length==1){
											var $originTr=$('<tr><td class="tCenter" colspan="2">등록된 정보가 없습니다.</td></tr>');
											$("#" + parentView).find(".searchWrapper").find("#listTable").append($originTr);
										}
										if(Public.StopEvent != null)Public.StopEvent();
									}
								});
							});
						});
						$td2.append($btn1).append($btn2);
						$tr.append($td1).append($td2);
						if($('#listTable > tbody > tr:nth-child(2)').text().contains("등록된 정보")){
							$('#listTable > tbody > tr:nth-child(2)').remove();
						}
						$("#" + parentView).find(".searchWrapper").find("#listTable").append($tr);
					}
				});
			});
		}
	});

	/* 보기 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find(".viewBtn").click(function(){
		if(Public.StopEvent != null) Public.StopEvent();

		var v = $(this).parent().attr("k");
		viewPathGeometry(v);
	});

	/* 삭제 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find(".removeBtn").click(function(){
		var msg = "";
		if(Public.StopEvent != null) msg = "<br>참고) 진행중인 순찰감시가 종료됩니다.";

		var v = $(this).parent().attr("k");
		var $tr = $(this).parent().parent();
		confirm("삭제하시겠습니까?" + msg, function(){
			_common.callAjax("/cctv/delPatrol.json", {k : v}, function(json){
				if(json.result){
					alert("경로가 삭제되었습니다.");
					$("#" + parentView).find("#drawNm, #moveSec, #moveBuffer, #showCnt").val("");
					$tr.remove();
					if($('#listTable > tbody > tr').length==1){
						var $originTr=$('<tr><td class="tCenter" colspan="2">등록된 정보가 없습니다.</td></tr>');
						$("#" + parentView).find(".searchWrapper").find("#listTable").append($originTr);
					}
					if(Public.StopEvent != null)Public.StopEvent();
					
				}
			});
		});
	});

})();