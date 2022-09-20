(function(){

	/* 등록관리-미등록관리 뷰 요청 이벤트 입니다. */
	$("#" + parentView).find("#unregCctv").click(function(){
		//xeusLayout.hideOverlayWestPane(0);
		var delay = ANI_DELAY;
		if(xeusLayout.isOverlayShow()) delay = 0;
		_common.callAjax("/cctv/getUnregCctvView.do", null, function(view) {
			xeusLayout.showOverlayEastPane(delay, function() {
				$("#" + parentView).find("#center-overlay-east").html(view);
				$("#" + parentView).find(".btnDiv").removeClass("hidden");
			});
		});
	}).width(90);

	/* 우측 패덜 닫기 이벤트입니다. */
	$("#" + parentView).find("#closeBtn").click(function(){
		if(parentView=='eventView'){
			xeusCCTV.westReLayout(); 
		}
		else if(parentView=='nmsView'){
			xeusLayout.EAST=600;
			xeusLayout.WEST = NMS_BTN_INFRA_VIEW_WEST_SIZE;
		}
		xeusLayout.hideOverlayEastPane(500);
	});

	/* 지도에서 위치설정 이벤트 입니다. */
	$("#" + parentView).find("#overlay-east-contents").find("#mapClickBtn").click(function(){
	    $("body").css("cursor", "crosshair");
	    $("#" + parentView).find(".selectCancel").show(500);
	    xeusLayout.mapService.getMap().on('click', Public.CCTV.Add.Start);
	    Public.StopEvent = function(){
	        $("body").css("cursor", "default").off("click");
	        $("#" + parentView).find(".selectCancel").hide(500);
	        xeusLayout.mapService.getMap().un('click', Public.CCTV.Add.Start);
	    }
	});

	$("#" + parentView).find("#overlay-east-contents").find(".selectCancel").click(function(){
	    Public.StopEvent();
	});

	/* th 클릭 이벤트 입니다. */
	$("#" + parentView).find("#overlay-east-contents").find("#regTable").find("th").click(function(){
		$(this).next().children().eq(0).focus();
	});

	/* 체크박스 ON/OFF 값 변경 이벤트 입니다. */
	$("#" + parentView).find("#regTable").find("input[type=checkbox]").each(function(){
		if($(this).is(":checked")){
			$(this).val("Y");
		}else{
			$(this).val("N");
		}
	});
	$("#" + parentView).find("#regTable").find("input[type=checkbox]").change(function(){
		$("#" + parentView).find("#regTable").find("input[type=checkbox]").each(function(){
			if($(this).is(":checked")){
				$(this).val("Y");
			}else{
				$(this).val("N");
			}
		});
	});
	/* input focus & blur 이벤트 입니다. */
	$("#" + parentView).find("#regTable").find("input[type=text]").on("focus", function(){
		$(this).css("color", "black");
		$(this).parent().css("background", "white");
	});
	$("#" + parentView).find("#regTable").find("input[type=text]").on("blur", function(){
		$(this).css("color", "white");
		$(this).parent().css("background", "#333");
	});
	/* 드롭박스 이벤트 입니다. */
	$("#" + parentView).find("#overlay-east-contents").find(".dropBox").droppable({
		accept: ".cctv-overlay-content-img",
		hoverClass: "dropBoxHover",
        deactivate: function(){
        	$("#" + parentView).find(".searchWrapper").find(".dropBox").hide("clip", 200);
        	//180528 이은규
        	//셀렉터 추가
        	$("#" + parentView).find("#overlay-east-contents").find(".dropBox").hide("clip", 200);
        },
		drop: function(event, ui){
			var data = $(ui.draggable).data();
			var type = data.mgrNo.substring(0, 3);

			ui.draggable.draggable("option", "revert", false);

			var url = "";
			if(type == "CTV") url = "/cctv/getCctvMngView.do";
			if(type == "INF") url = "/nms/getInfraMngView.do";

			_common.callAjax(url, {mgrNo : data.mgrNo}, function(view) {
				$("#" + parentView).find("#center-overlay-east").height(
					$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
				).html(view);
				$("#" + parentView).find(".btnDiv").removeClass("hidden");
			});
		}
	});

	/* 이미지 새탭으로 보기 이벤트 입니다. */
	$("#" + parentView).find("#imgWrapper").find(".imgs").click(function(){
		var param = {"mgrSeq" : $(this).attr("k")};
		_common.postForm.open("/image/getImage.do", param);
	});

	/* 이미지 삭제 이벤트 입니다. */
	$("#" + parentView).find("#imgWrapper").find(".close").click(function(){
		var $span = $(this).parent();
		var param = {"mgrSeq" : $(this).attr("k")};
	    confirm("이미지를 삭제하시겠습니까?", function(){
	    	_common.callAjax("/image/del.json", param, function(json){
	            if(json.result){
	                $span.remove();
	            }
	        });
	    });
	});

	/* 사진 추가 버튼 이벤트 입니다. */
	$("#" + parentView).find("#overlay-east-contents").find("#uploadBtn").click(function(){
		$("#" + parentView).find("#hiddenForm").find("#uploadImg").click();
	});

	/* 상위 "사진 추가" 버튼을 통해 실제 이미지 선택시 업로드 이벤트 입니다. */
	$("#" + parentView).find("#hiddenForm").find("#uploadImg").on("change", function(){
		var nm = $(this).val();
		var k = $(this).parent().find("#k").val();
		if(nm != ""){
			confirm("선택하신 파일을 업로드 하시겠습니까?", function(){
				$("#" + parentView).find("#hiddenForm").find("#i").val($("#" + parentView).find(".imgBox").length + 1);
	            _common.formSubmit("/image/add.json", $("#" + parentView).find("#hiddenForm"), function(json){
	                if(json.result){
	                    _common.callAjax("/cctv/getCctvMngView.do", {mgrNo : k}, function(view) {
	        				$("#" + parentView).find("#center-overlay-east").height(
	        					$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
	        				).html(view);

	        				$("#" + parentView).find("#overlay-east-contents").find(".btnDiv").removeClass("hidden");
	        			});
	                }
	            }, false);
			}, function(){
				$("#" + parentView).find("#hiddenForm").find("#uploadImg").val("");
			});
		}
	});

	/* 수정 저장 버튼입니다. */
	$("#" + parentView).find("#overlay-east-contents").find("#saveBtn").click(function(){
		var param = _common.utils.collectSendData("#" + parentView + " #overlay-east-contents #regTable");
		param["mgrNo"] = $(this).parent().attr("k");
		if(_common.utils.isNullAndEmpty(param["ipAddr"])) delete param["ipAddr"];
		if(_common.utils.isNullAndEmpty(param["portNum"])) delete param["portNum"];

		var lng = param["lng"];
	    var lat = param["lat"];
	    if(!_common.utils.isNullAndEmpty(lng) && !_common.utils.isNullAndEmpty(lat)){
	        var epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
	        var mainCenter = ol.proj.transform([lng, lat], 'EPSG:4326', epsg);
	        param["lng"] = mainCenter[0];
	        param["lat"] = mainCenter[1];
	    }
		confirm("수정하시겠습니까?", function(){
			_common.callAjax("/cctv/editCctv.json", param, function(json) {
				if(json){
					alert("저장되었습니다.");
					$("#" + parentView).find('#cctv-overlay-closer')[0].click();
					xeusCCTV.cctv.reload();
				}
			});
		});
	});

	/*
	 * 180410 이은규
	 * 삭제버튼 이벤트 추가
	 */
	$("#" + parentView).find("#overlay-east-contents").find("#delBtn").click(function(){
		var v = $(this).parent().attr("k");
		confirm("삭제하시겠습니까?", function(){
			_common.callAjax("/cctv/delCctv.json", {k : v}, function(json) {
				if(json){
					alert("삭제되었습니다.");

				    	_common.callAjax("/image/del.json", {mgrNo : v}, function(json){
				            if(json.result){
				            	console.log("이미지도 삭제되었습니다.");
				            	$("#" + parentView).find(".searchWrapper").find("#emdCd").val("");
								$("#" + parentView).find(".searchWrapper").find("#objType").val("CTV");
								$("#" + parentView).find(".searchWrapper").find("#objName").val("");
								$("#" + parentView).find(".searchWrapper").find("#searchBtn").click();
				            }
				        });
			    	xeusLayout.hideOverlayEastPane(500);
				}
			}, false);
		});
	});

	/* DatePicker 생성 이벤트입니다. */
	$("#overlay-east-contents").find(".datePicker").datepicker("destroy").datepicker({
		changeMonth: true,
        changeYear: true,
        dateFormat: "yymmdd",
        showButtonPanel: true,
        beforeShowDay: $.datepicker.noBefore
	});

	$("#" + parentView).find(".presetBtn").click(function(){
		var idx = $(this).attr("idx");
		var mgrNo = $(this).attr("mgrNo");
		var prop = {};

		_common.callAjax("/cctv/getCctv.json", { mgrNo : mgrNo }, function(json) {
			prop = json.result;
			xeusCCTV.viewVideo(encodeURIComponent(JSON.stringify(json.result)));
			setTimeout(function(){
				if(!$("#" + parentView).find("button.ptz_" + mgrNo).parent().next().find("#ptzWrap").is(":visible")){
					$("#" + parentView).find("button.ptz_" + mgrNo).click();
				}

				$("#" + parentView).find(".selectPresetCancel").show(500);
				$(".tab[target=" + parentView + "]").data("map").getMap().on('click', Public.CCTV.Preset.Start);
				Public.CCTV.Preset["cctvProp"] = prop;
				Public.CCTV.Preset["mgrNo"] = mgrNo;
				Public.CCTV.Preset["presetNo"] = idx;
			    Public.StopEvent = function(){
			    	xeusSymbol.removeFeature(Public.CCTV.Preset["cctvProp"]["gid"], "isPreset");
			    	delete Public.CCTV.Preset["cctvProp"];
			    	delete Public.CCTV.Preset["mgrNo"];
			    	delete Public.CCTV.Preset["presetNo"];
			        $("body").css("cursor", "default").off("click");
			        $("#" + parentView).find(".selectPresetCancel").hide(500);
			        $(".tab[target=" + parentView + "]").data("map").un('click', Public.CCTV.Preset.Start);
			    }

			    _common.callAjax("/proxy/xeusGateWay.json", { "path" : "getPresets", "cctvMgrNo" : mgrNo, "gbnCd" : prop["gbnCd"] }, function(json){
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
			}, 2000);

		});
	});

	$("#" + parentView).find(".searchWrapper").find(".selectPresetCancel").click(function(){
	    Public.StopEvent();
	});

})();