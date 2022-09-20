(function(){

	/* 등록관리-미등록관리 뷰 요청 이벤트 입니다. */
	$("#" + parentView).find("#unregVoice").click(function(){
		//xeusLayout.hideOverlayWestPane(0);
		var delay = ANI_DELAY;
		if(xeusLayout.isOverlayShow()) delay = 0;
		_common.callAjax("/disbord/getUnregDisbordView.do", null, function(view) {
			xeusLayout.showOverlayEastPane(delay, function() {
				$("#" + parentView).find("#center-overlay-east").html(view);	//이거 뭔지 모르겠음 .전에 awsMngView에 안나옴.
				$("#" + parentView).find(".btnDiv").removeClass("hidden");
			});
		});
	}).width(90);

	/* 우측 패덜 닫기 이벤트입니다. */
	$("#" + parentView).find("#closeBtn").click(function(){
		xeusLayout.hideOverlayEastPane(500);
	});
	/*위도 경도 클릭시 alert 이벤트입니다. */
	$("#" + parentView).find("#regTable").find(".loc").on("focus", function(){
		alert("지도에서 위치 선택을 클릭해주세요.");
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
		/*
		 * 180410 이은규
		 * 지번과 도로명은 어차피 수정이 안되므로 배경 및 글씨 색 적용 X
		 */
		if($(this).attr('id') != "jibun" && $(this).attr('id') != "doro" ){
			$(this).css("color", "black");
			$(this).parent().css("background", "white");
		}
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
		var nm = $(this).val();		//이미지
		var k = $(this).parent().find("#k").val();	//mgrNo
		if(nm != ""){
			confirm("선택하신 파일을 업로드 하시겠습니까?", function(){
				$("#" + parentView).find("#hiddenForm").find("#i").val($("#" + parentView).find(".imgBox").length + 1);
	            _common.formSubmit("/image/add.json", $("#" + parentView).find("#hiddenForm"), function(json){
	                if(json.result){
	                    _common.callAjax("/disbord/getDisbordMngView.do", {mgrNo : k}, function(view) {
	        				$("#" + parentView).find("#center-overlay-east").height(
	        					$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
	        				).html(view);

	        				$("#" + parentView).find("#overlay-east-contents").find(".btnDiv").removeClass("hidden");	//저장 삭제 이런거 없애는 거
	        			});
	                }
	            }, false);
			}, function(){		//업로드 안할 때
				$("#" + parentView).find("#hiddenForm").find("#uploadImg").val("");
			});
		}
	});

	/* 수정 저장 버튼입니다. */
	$("#" + parentView).find("#overlay-east-contents").find("#saveBtn").click(function(){	//어짜피 다른 jsp에서 똑같은 이름이기 때문에 상관 없을 듯.
		var param = _common.utils.collectSendData("#" + parentView + " #overlay-east-contents #regTable");


		for(var key in param){
		    if(key == "lat" || key == "lng" || key == "bordNm" ||  key == "gbnNm"){
		         if(param[key] == ""){
		        	 	var $selector = $("#overlay-east-contents").find("#regTable").find("#" + key);
		                alert($selector.attr("name") + "를 입력하세요.");
		                return
		         }
		    }
		}
//		console.log("param = "+param);
//		console.log("param[Fcl] = "+param["fclGbnNm"]);
//		param["mgrNo"] = $(this).parent().attr("k");	//undefine됨
//		console.log("mgrNoss = "+param["mgrNo"]);
//		console.log("mgrBelong1111234 = "+param["mgrBelong1"]);
//		console.log("mgrNm1 = "+param["mgrNm1"]);
//		var mgr1=param["mgrSpotLevel1"].split("/");
//		console.log("mgr1[0] = "+mgr1[0]);
//		console.log("mgr1[1] = "+mgr1[1]);
//		var mgr2=param["mgrSpotLevel2"].split("/");
//		console.log("mgr2[0] = "+mgr2[0]);
//		console.log("mgr2[1] = "+mgr2[1]);
//		var mgr3=param["mgrSpotLevel3"].split("/");
//		console.log("mgr3[0] = "+mgr3[0]);
//		console.log("mgr3[1] = "+mgr3[1]);
		var lng=param["lng"];		//요 데이터는 삭제하는 게 좋지만 나중에합시다..
		var lat=param["lat"];
		param["tmLat"]=lat;
		param["tmLon"]=lng;

		confirm("수정하시겠습니까?", function(){
			_common.callAjax("/disbord/edit.json", param, function(json) {
				if(json){
					alert("저장되었습니다.");
					Layers['asset_disbord'].reload();
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
//		var k = $(this).parent().find("#k").val();	//mgrNo

		confirm("삭제하시겠습니까?", function(){
			_common.callAjax("/disbord/del.json", {mgrNo : v}, function(json) {
				if(json){
					alert("삭제되었습니다.");
					//이 뒤로 저절로 searchBtn이 눌러진다......
				    	_common.callAjax("/image/del.json", {mgrNo : v}, function(json){
				            if(json.result){
				            	$("#" + parentView).find(".searchWrapper").find("#emdCd").val("");
								$("#" + parentView).find(".searchWrapper").find("#objType").val("DIS");
								$("#" + parentView).find(".searchWrapper").find("#objName").val("");
								$("#" + parentView).find(".searchWrapper").find("#searchBtn").click();
				            }
				        });
				    xeusLayout.hideOverlayEastPane(500);
//					_common.callAjax("/disbord/getDisbordMngView.do", null, function(view) {
//						$("#" + parentView).find("#center-overlay-east").html(view);
//					});
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


})();