(function(){

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

	/* 등록관리-미등록관리 뷰 요청 이벤트 입니다. */
	$("#" + parentView).find("#backBtn").click(function(){
		//xeusLayout.hideOverlayWestPane(0);
		var delay = ANI_DELAY;
		if(xeusLayout.isOverlayShow()) delay = 0;
		_common.callAjax("/cctv/getCctvMngView.do", null, function(view) {
			xeusLayout.showOverlayEastPane(delay, function() {
				$("#" + parentView).find("#center-overlay-east").html(view);
			});
		});
	}).width(70);

	/* 우측 패덜 닫기 이벤트입니다. */
	$("#" + parentView).find("#closeBtn").click(function(){
		xeusLayout.hideOverlayEastPane(500);
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

	/* 지도에서 위치설정 이벤트 입니다. */
	$("#" + parentView).find("#overlay-east-contents").find("#mapClickBtn").click(function(){
	    $("body").css("cursor", "crosshair");
	    $("#" + parentView).find(".selectCancel").show(500);		//알았다.
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

	/* 미등록 CCTV 속성 조회 이벤트 입니다. */
	$("#" + parentView).find("#unregList").change(function(){
		_common.callAjax("/nms/getCctvUnregItem.json", { "mgrNo" : $(this).find("option:selected").val() }, function(json){
            if(json.result != null){
            	for(var key in json.result){
            		if(key != "lng" && key != "lat"){
            			$("#" + parentView).find("#regTable").find("#" + key).val(json.result[key]);
            		}
            	}
            }
        });
	});

	/* 이미지 새탭으로 보기 이벤트 입니다. */
	/*$("#imgWrapper").find(".imgs").click(function(){
		var param = {"mgrSeq" : $(this).attr("k")};
		_common.postForm.open("/image/getImage.do", param);
	});*/

	/* 이미지 삭제 이벤트 입니다. */
	/*$("#imgWrapper").find(".close").click(function(){
		var $span = $(this).parent();
		var param = {"mgrSeq" : $(this).attr("k")};
	    confirm("이미지를 삭제하시겠습니까?", function(){
	    	_common.callAjax("/image/del.json", param, function(json){
	            if(json.result){
	                $span.remove();
	            }
	        });
	    });
	});*/

	/* 사진 추가 버튼 이벤트 입니다. */
	/*$("#overlay-east-contents").find("#uploadBtn").click(function(){
		$("#hiddenForm").find("#uploadImg").click();
	});*/

	/* 상위 "사진 추가" 버튼을 통해 실제 이미지 선택시 업로드 이벤트 입니다. */
	/*$("#hiddenForm").find("#uploadImg").on("change", function(){
		var nm = $(this).val();
		var k = $(this).parent().find("#k").val();
		if(nm != ""){
			confirm("선택하신 파일을 업로드 하시겠습니까?", function(){
				$("#hiddenForm").find("#i").val($(".imgBox").length + 1);
	            _common.formSubmit("/image/add.json", $("#hiddenForm"), function(json){
	                if(json.result){
	                    _common.callAjax("/cctv/getCctvMngView.do", {mgrNo : k}, function(view) {
	        				$("#center-overlay-east").height(
	        					$(window).height() - $("#layout-north").outerHeight() - $("#overlay-east-bar").outerHeight()
	        				).html(view);
	        			});
	                }
	            }, false);
			}, function(){
				$("#hiddenForm").find("#uploadImg").val("");
			});
		}
	});*/

	/* 저장 버튼입니다. */
	$("#" + parentView).find("#overlay-east-contents").find("#saveBtn").click(function(){
		var param = _common.utils.collectSendData("#overlay-east-contents #regTable");
		if(_common.utils.isNullAndEmpty(param["ipAddr"])) delete param["ipAddr"];
		if(_common.utils.isNullAndEmpty(param["portNum"])) delete param["portNum"];

		var lng = param["lng"];
	    var lat = param["lat"];
	    if(!_common.utils.isNullAndEmpty(lng) && !_common.utils.isNullAndEmpty(lat)){
	        var epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
	        var mainCenter = ol.proj.transform([lng, lat], 'EPSG:4326', epsg);
	        param["lng"] = mainCenter[0];
	        param["lat"] = mainCenter[1];
	    }else{
	    	alert("위치를 지정해 주세요.");
	    	return false;
	    }
//		var first=
//		{
//			mgrSpot : param["mgrSpot1"],
//			mgrBelong : param["mgrBelong1"],
//			mgrLevel : param["mgrLevel1"],
//			mgrNm : param["mgrNm1"],
//			mgrTel : param["mgrTel1"].replaceAll("-",""),
//			mgrPhone : param["mgrPhone1"].replaceAll("-",""),
//			mgrSubNo : 1
//		};
//		var second=
//		{
//			mgrSpot :  param["mgrSpot2"],
//			mgrBelong : param["mgrBelong2"],
//			mgrLevel : param["mgrLevel2"],
//			mgrNm : param["mgrNm2"],
//			mgrTel : param["mgrTel2"].replaceAll("-",""),
//			mgrPhone : param["mgrPhone2"].replaceAll("-",""),
//			mgrSubNo : 2
//		};
//		var third=
//		{
//			mgrSpot : param["mgrSpot3"],
//			mgrBelong : param["mgrBelong3"],
//			mgrLevel : param["mgrLevel3"],
//			mgrNm : param["mgrNm3"],
//			mgrTel : param["mgrTel3"].replaceAll("-",""),
//			mgrPhone : param["mgrPhone3"].replaceAll("-",""),
//			mgrSubNo : 3
//		};
//		var mgr=[];
//		mgr.push(first);
//		mgr.push(second);
//		mgr.push(third);
//		param["mgr"]=mgr;
	    
		confirm("신규추가 하시겠습니까?", function(){
			_common.callAjax("/cctv/addCctv.json", param, function(json) {
				if(json.result){
					alert("저장되었습니다.");
					xeusCCTV.cctv.reload();
					_common.callAjax("/cctv/getUnregCctvView.do", null, function(view) {
						xeusLayout.showOverlayEastPane(delay, function() {
							$("#" + parentView).find("#center-overlay-east").html(view);
						});
					});
					$("#" + parentView).find(".searchWrapper").find("#emdCd").val("");
					$("#" + parentView).find(".searchWrapper").find("#objType").val("CTV");
					$("#" + parentView).find(".searchWrapper").find("#objName").val("");
					$("#" + parentView).find(".searchWrapper").find("#searchBtn").click();
				}
			});
		});
	});

	/* DatePicker 생성 이벤트입니다. */
	$("#" + parentView).find("#overlay-east-contents").find(".datePicker").datepicker("destroy").datepicker({
		changeMonth: true,
        changeYear: true,
        dateFormat: "yymmdd",
        showButtonPanel: true,
        beforeShowDay: $.datepicker.noBefore
	});

})();