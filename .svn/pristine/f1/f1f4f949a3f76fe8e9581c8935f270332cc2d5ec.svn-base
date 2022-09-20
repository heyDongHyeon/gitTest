/**
 *
 */
(function(){

	if($(".bpopup").length > 1){
		$(".bpopup").not(":eq(0)").remove();
	}

	var value = $("#searchInput").val();
	$("#searchInput").val(value).focus();

	/* DatePicker 생성 이벤트입니다. */
	$(".searchWrapper").find(".datePickerStart").datepicker("destroy").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yymmdd",
		defaultDate: new Date().getFullYear().toString()+(new Date().getMonth()+1).toString()+'01',
		showButtonPanel: true,
		beforeShowDay: $.datepicker.noBefore
	});
	$(".searchWrapper").find(".datePickerEnd").datepicker("destroy").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yymmdd",
		defaultDate: new Date(),
		showButtonPanel: true,
		beforeShowDay: $.datepicker.noBefore
	});

	$('.datePickerStart').val(srchDateStart);
	$('.datePickerEnd').val(srchDateEnd);

	$("#searchBtn").click(function(){
		var _param = _common.utils.collectSendData("#searchTable");
		_param["limit"] = 10;
		_param["offset"] = 0;
		srchExcEventParam=_param;

		if(userAuth != null && userAuth != "")
			_param['userAuth'] = userAuth;

		_common.callAjax("/monitor/getMonitoringWaitView.do", _param, function(view) {
			$("#overlay-west-contents").html(view);
			setWaitList(_param);
			$(".paging_wrap").paging({
				current	  : 10,
				max  	  : Number($("#"+parentView).find("#max").val()),
				nowOffset : Number($("#"+parentView).find("#offset").val()),
				bindEvent : callView
			});
		});
	});

	$("#excelBtn").click(function(){
		delete srchExcEventParam.limit;
		delete srchExcEventParam.offset;

		if($(".searchWrapper").find("#evtWaitTable").find("tbody").children().length == 0){
			alert("검색결과가 존재하지 않아 다운로드할 수 없습니다.");
		}else{
				confirm("검색결과를 엑셀로 다운로드 하시겠습니까?", function(){
				_common.postForm.submit("/eventList/getEventListExcel.do", srchExcEventParam, '_blank');
			});
		}
	});

	var _param = _common.utils.collectSendData("#searchTable");
	if(isFirst){
		_param["limit"] = 10;
		_param["offset"] = 0;
		srchExcEventParam=_param;
		setWaitList(_param);
		$(".paging_wrap").paging({
			current	  : 10,
			max  	  : Number($("#"+parentView).find("#max").val()),
			nowOffset : Number($("#"+parentView).find("#offset").val()),
			bindEvent : callView
		});
	}

	if($("#" + parentView).find("#evtTable").find("tbody").find("tr").length == 0){
		$("#" + parentView).find("#widget-point").find("#dis").hide();
	}else{
		$("#" + parentView).find("#widget-point").find("#dis").show();
	}

	$("#" + parentView).find("#widget-point").find(".pointBtn").click(function(){
		if($(this).attr("active") == "active"){
			$("#" + parentView).find("#widget-point").find(".pointBtn").attr("active", "");
			//$("#widget-point").find("th").eq(1).text("");
		}else{
			$("#" + parentView).find("#widget-point").find(".pointBtn").attr("active", "");
			$(this).attr("active", "active");
			/* if($(this).attr("id") == "dis"){
				$("#widget-point").find("th").eq(1).text("지도의 임의지점을 지정하여 CCTV를 재생할 수 있습니다.");
			}else{
				$("#widget-point").find("th").eq(1).text("지도의 임의지점을 지정하여 교통정보센터 CCTV를 재생할 수 있습니다.");
			} */
		}
	});

	var result = false;
	_common.callAjax("/auth/hasAuth.json", { "authData" : "112edit" }, function(json){
		if(json.result) result = true;
	}, false);
	_common.callAjax("/auth/hasAuth.json", { "authData" : "119edit" }, function(json){
		if(json.result) result = true;
	}, false);


	/* 수정 팝업 */
	$("#" + parentView).find("#editBtn").click(function(){
		var data = $("#" + parentView).find("#ctntWaitTable").data();
		var isNull = true;

		$("#eventWrapper").find(".sendData").val("");
		for(var k in data){
			isNull = false;
			if ( k === 'statEvetCntn' ) data[k] = data[k].replace(/;;/gi, '\n');
			if ( k === 'statEvetActnCntn' ) data[k] = data[k].replace(/;;/gi, '\n');
			$("#eventWrapper").find("#" + k).val(data[k]);

			if(k == "statEvetTypCd"){
				$("#eventWrapper").find("#statEvetTypCd").find("option").hide();
				$("#eventWrapper").find("#statEvetTypCd").find("option[value=" + data[k] + "]").show();
			}

			if(k == "statEvetNm"){
				$("#eventWrapper").find("#statEvetNm").find("option").hide();
				$("#eventWrapper").find("#statEvetNm").find("option").each(function(){
					if($(this).val() == data[k]) $(this).show();
				});
			}
		}
		$("#eventWrapper").find("#outbPosX").val(data.outbPos[0].x);
		$("#eventWrapper").find("#outbPosY").val(data.outbPos[0].y);

		if(isNull){
			alert("이벤트 상세정보 열람 후 관리할 수 있습니다.");
		}else{
			$("#" + parentView).find("#eventWrapper").toggle("slide");

			var val = $("#eventWrapper").find("#statEvetTypCd").val();
			if(val == ""){
				$("#eventWrapper").find("#statEvetNm").find("option").hide();
				$("#eventWrapper").find("#statEvetNm").find("option").eq(0).show();
			}else{
				$("#eventWrapper").find("#statEvetNm").find("option").hide();
				$("#eventWrapper").find("#statEvetNm").find("option[k=" + val + "]").show();
			}

			$("#eventWrapper").find("#saveBtn").attr("mode", "edit");
			$("#eventWrapper").find("#uSvcOutbId").prop("readOnly", "readOnly");
			$("#eventWrapper").find("#uSvcOutbId").parent().parent().show();
			//$("#eventWrapper").find("#delBtn").show();

			$("#eventWrapper").find("#statEvetTypCd, #statEvetNm").removeProp("readOnly");
			$("#eventWrapper").find("#statEvetActnCntn").parent().parent().show();
			$("#eventWrapper").find("#procSt").find("option").show();
			$("#eventWrapper").find("#procSt").find("option").eq(0).prop("selected", "selected");
			$("#eventWrapper").find("#procSt").find("option[value=10]").hide();

			var type = data["statEvetNm"];

			var $cctv = $("#" + parentView).find("#eventWrapper").find("#targetCctv").parent().parent(); $cctv.hide();
			var $acciNum = $('#eventWrapper').find('#acciNum').parent().parent(); $acciNum.hide();
			var $reqResn = $('#eventWrapper').find('#reqResn').parent().parent(); $reqResn.hide();

			if(type == "PTZ" || type == "Preset"){
				$cctv.show();
			}
			if(type == "CCTV 영상 공유"){
				$cctv.show();
			}
			if(type == "선영상조회 요청"){
				$cctv.show();
				$acciNum.show();
				$reqResn.show();
			}

		}
	});

	/* 신규 팝업 */
	$("#" + parentView).find("#addBtn").click(function(){
		$("#eventWrapper").find(".sendData").val("");
		$("#eventWrapper").find("#saveBtn").attr("mode", "add");
		$("#eventWrapper").find("#uSvcOutbId").prop("readOnly", "readOnly");
		$("#eventWrapper").find("#uSvcOutbId").parent().parent().hide();
		//$("#eventWrapper").find("#delBtn").hide();
		$("#eventWrapper").find("#statEvetTypCd, #statEvetNm").prop("readOnly", "readOnly");
		$("#eventWrapper").find("#statEvetActnCntn").parent().parent().hide();
		$("#eventWrapper").find("#procSt").find("option").hide();
		$("#eventWrapper").find("#procSt").find("option[value=10]").show();
		$("#eventWrapper").find("#statEvetTypCd, #statEvetNm").find("option").show();
	});

	/* 수정 */
	$("#" + parentView).find("#saveBtn").click(function(){
		var mode = $(this).attr("mode");

		var data = $("#" + parentView).find("#ctntWaitTable").data();
		var subData = _common.utils.collectSendData("#eventWrapper");
		if(mode == "add"){
			data = xeusJsonParser.getTemplate();
			subData.statEvetOutbDtm = new Date().getYMDHMS();
		}else if(mode == "edit"){
			if(isNaN(Number(subData["outbPosX"]))){
				alert("경도 값이 올바르지 않습니다.");
				return false;
			}
			if(isNaN(Number(subData["outbPosY"]))){
				alert("위도 값이 올바르지 않습니다.");
				return false;
			}

			var tm = Spatial.convertProjection([Number(subData["outbPosX"]), Number(subData["outbPosY"])], "EPSG:4326", "EPSG:5186");
			data["tmx"] = String(tm[0]);
			data["tmy"] = String(tm[1]);

		}

		var isValid = true;
		for(var k in subData){
			if(subData[k] == ""){
				if(k == "uSvcOutbId" && mode == "add") continue;
				if(k == "statEvetActnCntn" && mode == "add") continue;
				if(k == "targetGrp") continue;
				if(k == "targetId") continue;

				sysAlert("이벤트의 모든 내용은 공백을 허용하지 않습니다.");
				$("#eventWrapper").find("#" + k).focus();
				isValid = false;
				if(subData[k] == null) subData[k] = "";
				if(subData[k] == "null") subData[k] = "";
				break;
			}
			data[k] = subData[k];
		}

		if(isValid){
			data.outbPos[0].x = subData["outbPosX"];
			data.outbPos[0].y = subData["outbPosY"];
			subData.outbPosX = subData["outbPosX"];
			subData.outbPosY = subData["outbPosY"];

			param = subData;
			data.statEvetCntn = param.statEvetCntn.replace(/\n/gi, ";;");
			param.statEvetCntn= param.statEvetCntn.replace(/\n/gi, ";;");
			data.statEvetActnCntn = param.statEvetActnCntn.replace(/\n/gi, ";;");
			param.statEvetActnCntn= param.statEvetActnCntn.replace(/\n/gi, ";;");
			param["json"] = JSON.stringify(data);
			data["targetGrp"] = '';
			data["targetId"] = '';
			data["etcCntn"] = getEtcCntn();
			confirm("저장하시겠습니까?", function(){
				_common.callAjax("/ws/addEvent.json", { "json" : JSON.stringify(data) }, function(json){
					if(json.result == true){
						$("#" + parentView).find("#eventWrapper").toggle("slide", function(){
							var _param = {};
							_param["limit"] = 10;
							_param["offset"] = 0;
							_param["dateLimitWait"] = 'Y';
							setWaitList(_param);
						});
					}
				});
			});
		}
	});

	/* 엔터키 이벤트 */
	$(".keyup").keyup(function(e){
		if(e.which == 13){
			var selector = $(this).attr("for");
			$(selector).click();
		};
	});

	/* 셀렉트박스 내용 변경 */
	$("#" + parentView).find("#eventWrapper").find("#statEvetTypCd").change(function(){
		var val = $(this).val();
		if(val == ""){
			$("#" + parentView).find("#eventWrapper").find("#statEvetNm").val("");
			$("#" + parentView).find("#eventWrapper").find("#statEvetNm").find("option").hide();
			$("#" + parentView).find("#eventWrapper").find("#statEvetNm").find("option").show();
		}else{
			$("#" + parentView).find("#eventWrapper").find("#statEvetNm").val("");
			$("#" + parentView).find("#eventWrapper").find("#statEvetNm").find("option").hide();
			$("#" + parentView).find("#eventWrapper").find("#statEvetNm").find("option[k=" + val + "]").show();
		}
	});

	/* 셀렉트박스 내용 변경 */
	$("#" + parentView).find("#eventWrapper").find("#statEvetNm").change(function(){
		var $cctv = $("#" + parentView).find("#eventWrapper").find("#targetCctv").parent().parent(); $cctv.hide();
		var $acciNum = $('#eventWrapper').find('#acciNum').parent().parent(); $acciNum.hide();
		var $reqResn = $('#eventWrapper').find('#reqResn').parent().parent(); $reqResn.hide();

		var val = $(this).val();
		if(val == "PTZ" || val == "Preset"){
			$cctv.show();
		}
		if(val == "CCTV 영상 공유"){
			$cctv.show();
		}
		if(val == "선영상조회 요청"){
			$cctv.show();
			$acciNum.show();
			$reqResn.show();
		}
	});

	/* 지도에서 위치설정 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#mapClickBtn").click(function(){
	    $("body").css("cursor", "crosshair");
	    $("#" + parentView).find(".selectCancel").show(500);
	    xeusLayout.mapService.getMap().on('click', Public.EVT.Add.Start);
	    Public.StopEvent = function(){
	        $("body").css("cursor", "default").off("click");
	        $("#" + parentView).find(".selectCancel").hide(500);
	        xeusLayout.mapService.getMap().un('click', Public.EVT.Add.Start);
	    }
	});
	$("#" + parentView).find(".searchWrapper").find(".selectCancel").click(function(){
	    Public.StopEvent();
	});

	function getEtcCntn(){
		var etcCntn = {};
		var type = $("#" + parentView).find("#eventWrapper").find("#statEvetNm").val();
		var gid = $("#" + parentView).find("#eventWrapper").find("#targetCctv").val();

		if(type == "PTZ" || type == "Preset"){
			if(gid == "" || gid == null){
				alert("CCTV를 선택해주세요.");
				return false;
			}else{
				etcCntn = { "gid" : Number(gid) };
			}
		}

		if(type == "CCTV 영상 공유" || type == "선영상조회 요청"){
			if(gid == "" || gid == null){
				alert("CCTV를 선택해주세요.");
				return false;
			}else{
				gid = Number(gid);
			}
			var cctv = $(".tab[target=" + parentView + "]").data("map").getLayerByName("cctv");
			var features = cctv.getSource().getFeatures();
			for(var i=0; i<features.length; i++){
				var prop = features[i].getProperties().cctvList[0];
				if(Number(prop["gid"]) == gid){
					etcCntn = JSON.parse(JSON.stringify(prop));

					if(type == "선영상조회 요청"){
						etcCntn["userId"] = userId;
						etcCntn["cctvNm"] = etcCntn["cctvNm"];
						etcCntn["gid"] = gid;
						etcCntn["acciNum"] = $('#eventWrapper').find('#acciNum').val();
						etcCntn["reqResn"] = $('#eventWrapper').find('#reqResn').val();
						etcCntn["cctvMgrNo"] = etcCntn["mgrNo"];
						etcCntn["cctv"] = etcCntn;

						if(_common.utils.isNullAndEmpty(etcCntn["acciNum"])){
							alert("사건번호를 입력해주세요.");
							return false;
						}
						if(_common.utils.isNullAndEmpty(etcCntn["reqResn"])){
							alert("신청사유를 입력해주세요.");
							return false;
						}
					}

					break;
				}
			}

		}

		return JSON.stringify(etcCntn);
	}

	function callView(offset){
		if(offset == null) offset = 0;

		/**
		 * 180525 이은규
		 * 검색기능 수정
		 * 페이지 버튼 클릭 시 검색된 파라미터로 처리
		 */
		//var _param = _common.utils.collectSendData("#searchTable");
		var _param = {};
		if(outbPosNm != null && outbPosNm != "")
			_param['outbPosNm'] = outbPosNm;
		if(procSt != null && procSt != "")
			_param['procSt'] = procSt;
		if(evtNm != null && evtNm != "")
			_param['evtNm'] = evtNm;
		if(srchDateStart != null && srchDateStart != "")
			_param['statEvetOutbDtmStart'] = srchDateStart;
		if(srchDateEnd != null && srchDateEnd != "")
			_param['statEvetOutbDtmEnd'] = srchDateEnd;

		_param["limit"] = 10;
		_param["offset"] = offset;
		_param["dateLimitWait"] = 'Y';

		if(userAuth != null && userAuth != "")
			_param['userAuth'] = userAuth;
		_common.callAjax("/monitor/getMonitoringWaitView.do", _param, function(view) {
			$("#overlay-west-contents").html(view);
			setWaitList(_param);
			$(".paging_wrap").paging({
				current	  : 10,
				max  	  : Number($("#" + parentView).find("#max").val()),
				nowOffset : Number($("#" + parentView).find("#offset").val()),
				bindEvent : callView
			});
		}, false);
	}

	function setWaitList(param){

		_param["dateLimitWait"] = 'Y';
		_common.callAjax("/eventList/getList.json", param, function(json) {

			$(".searchWrapper").find("#evtWaitTable").find("tbody").html("");

			if ( json.result === undefined || json.result.length === 0) {
				var $noSearch = $(".searchWrapper").find("#listWrap");
				var $div="<div style =\"position: absolute;left: 36%; top: 50%; font-size: 20px;\">검색결과가 없습니다.</div>";
				$noSearch.append($div);
				return false;
			}
			$("#" + parentView).find('#max').val(json.count);
			$("#" + parentView).find('#offset').val(json.offset);

			for(var i=0; i<json.result.length; i++){
				var $tbl = $(".searchWrapper").find("#evtWaitTable");

				var Json = JSON.parse(json.result[i]);

				xeusJsonParser.setJson(Json);
				var $tr = $("<tr></tr>").attr("k", xeusJsonParser.getUSvcOutbId()).data(Json);
				$tr.css({"cursor" : "pointer"}).click(function(){
					xeusJsonParser.setEventContent($(this).data(), 'wait');
				});

				var $statEvetNm = $("<td class='tCenter'></td>").html("<div>" + xeusJsonParser.getEventType() + "</div>");

				$statEvetNm.find("div").css({
					"display": "inline-block",
					"text-overflow": "ellipsis",
					"overflow": "hidden",
					"white-space": "nowrap",
					"width": "120px",
					"padding-top": "6px"
				});

				var $procSt = $("<td class='tCenter'></td>").text(xeusJsonParser.getProcSt());

				var $outbPosNm = $("<td class='tCenter'></td>").html("<div>" + xeusJsonParser.getAddr() + "</div>");

				$outbPosNm.find("div").css({

					"display": "inline-block",
					"text-overflow": "ellipsis",
					"overflow": "hidden",
					"white-space": "nowrap",
					"width": "200px",
					"padding-top": "6px"

				});

				var $outbPos = $("<td class='tCenter'></td>").text(new Date().formatYMDHMS(xeusJsonParser.getYmd().substring(0, 14)));

				var $location = $("<td class='tCenter'></td>");

				var $btn = $("<img src='../res/img/places_normal.png'>").css({

					"cursor" : "pointer",
					"vertical-align" : "text-bottom"

				}).click(function(){

					var data = $(this).parent().parent().data();

					if ( Json === 'NDPSWARN' ) {
						xeusJsonParser.move(data, false, Json.statEvetCntn.replace(/ \/ /gi, '\r\n'));
					} else {
						xeusJsonParser.move(data);
					}

				}).hover(function(){

					$(this).attr("src", "../res/img/places_over.png");

				}, function(){

					$(this).attr("src", "../res/img/places_normal.png");

				});

				$location.append($btn);

				//$tr.append($statImg).append($statEvetNm).append($statEvetType).append($outbPosNm).append($outbPos).append($location);
				$tr.append($statEvetNm).append($procSt).append($outbPosNm).append($outbPos).append($location);
				$tbl.append($tr);
			}
		});
	}
})();