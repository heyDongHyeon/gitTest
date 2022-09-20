/**
 *
 */
(function(){

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

	$("#" + parentView).find(".searchWrapper").find("#evetPinBtn").click(function(){
		if($(this).attr("active") == "active"){
			evetPin = false;
			$(this).attr("active", "").text("이벤트 고정 시작");
			$("#" + parentView).find(".searchWrapper").find("#evetPinTxt").text("새로운 재난이 발생될 경우 지도가 자동으로 이동됩니다.");
		}else{
			evetPin = true;
			$(this).attr("active", "active").text("이벤트 고정 해제");
			$("#" + parentView).find(".searchWrapper").find("#evetPinTxt").text("새로운 재난이 발생될 경우 지도가 움직이지 않습니다.");

			if($("#" + parentView).find("#widget-point").find(".pointBtn").eq(0).attr("active") == "active") $("#" + parentView).find("#widget-point").find(".pointBtn").eq(0).attr("active", "");
			if($("#" + parentView).find("#widget-point").find(".pointBtn").eq(1).attr("active") == "active") $("#" + parentView).find("#widget-point").find(".pointBtn").eq(1).attr("active", "");
		}
	});

	$("#" + parentView).find(".searchWrapper").find("#smsBtn").click(function(){
		$("#" + parentView).find("#smsWrapper").toggle("slide");
	});

	$("#" + parentView).find("#smsWrapper").addClass("hidden");

	/* 팝업 */
	$("#" + parentView).find("#rcvId").click(function(){
		if ($('#edit_pop_wrap.bpopup').length > 1 ) $('#edit_pop_wrap.bpopup:eq(1)').remove();
		$("#edit_pop_wrap").bPopup({
			appendTo: $('#'+parentView)

		});
		$("#edit_pop_wrap").find("#bpop_wrap").show();
		$("#edit_pop_wrap").find("#event_bpop_wrap").hide();
	});

	//sms 권한 목록 클릭
	$("#edit_pop_wrap .grp_ul_list > li > label").click(function(){
		var key = $(this).prev().val();

		$('.user_ul_list').hide();
		console.log( $(this).hasClass('active'));
		if ( $(this).hasClass('active') ) {
			$('.user_ul_list').hide();
			$(this).removeClass('active');
		} else {
			$("#edit_pop_wrap .grp_ul_list li label").removeClass('active');
			$(this).addClass('active');
			$('#user_'+key).show();
		}

		$(this).focus();
	});

	//sms 그룹 체크
	$("#edit_pop_wrap .grp_ul_list > li > input[type=checkbox]").change(function(){
		var key = $(this).val();
		var grpChk = $(this).is(":checked");

		$('#user_'+key+' li input[type=checkbox]').each(function(){
			$(this).prop('checked', grpChk);
		});
	});

	//sms 사용자 선택 완료.
	$("#edit_pop_wrap .sms_user_select_btn").on('click', function(){

		var user = new Array();
		var numb = new Array();

		$('.user_ul_list li input[type=checkbox]').each(function(){
			if($(this).is(":checked")){
				user.push($(this).val());
				numb.push($(this).attr("numb"));
			}
		});
		$("#" + parentView).find("#rcvId").attr("numb", numb.toString()).val(user.toString());
		$(".bpopup").bPopup().close();
	});

	$("#" + parentView).find("#smsAddBtn").click(function(){
		var rcvId = $("#" + parentView).find("#rcvId").val();
		var conts = $("#" + parentView).find("#conts").val();

		if(_common.utils.isNullAndEmpty(rcvId)){
			alert('수신자를 선택하여 주십시오.');
			return false;
		}
		if(_common.utils.isNullAndEmpty(conts)){
			alert('내용을 입력하여 주십시오.');
			return false;
		}

		var param = {
			"rcvId" : rcvId,
			"rcvPhone" : $("#" + parentView).find("#rcvId").attr("numb"),
			"conts" : conts,
			"regDat" : new Date().getYMDHMS()
		}

		var pArray = new Array();
		if(param.rcvPhone == "" || param.rcvPhone == null){
			var array = param.rcvId.split(",");
			for(var l=0; l<array.length; l++){
				$("#edit_pop_wrap").find("input[type=checkbox]").each(function(){
					if($(this).val() == array[l]){
						pArray.push($(this).attr("numb"));
					}
				});
			}
			param.rcvPhone = pArray.toString();
		}
		confirm("신규추가 하시겠습니까?", function(){
			_common.callAjax("/sms/add.json", param, function(json){
				if(json.result) {
					alert('추가되었습니다.');
					setList();
					init();
				}
			});
		});
	});

	$("#" + parentView).find("#smsEditBtn").click(function(){
		var mgrSeq = $(this).attr("mgrSeq");

		//if(mgrSeq == null || mgrSeq == ""){
		if(_common.utils.isNullAndEmpty(mgrSeq)){
			alert('수정할 항목을 선택하여 주십시오.');
		}else {
			var param = {
				"rcvId" : $("#" + parentView).find("#rcvId").val(),
				"rcvPhone" : $("#" + parentView).find("#rcvId").attr("numb"),
				"conts" : $("#" + parentView).find("#conts").val(),
				"regDat" : new Date().getYMDHMS(),
				"mgrSeq" : mgrSeq
			}
			confirm("수정하시겠습니까?", function(){
				_common.callAjax("/sms/edit.json", param, function(json){
					if(json.result) {
						alert('수정되었습니다.');
						setList();
						init();
					}
				});
			});
		}
	});

	$("#" + parentView).find("#smsSendBtn").click(function(){

		var totalCnt = 0;
		var scsCnt = 0;

		var user = $("#" + parentView).find("#rcvId").val();
		var numb = $("#" + parentView).find("#rcvId").attr("numb");
		if(numb != "" && numb != null){
			confirm("전송하시겠습니까?", function(){
				var users = user.split(",");
				var numbers = numb.split(",");
				for(var i=0; i<numbers.length; i++){
					var _param = {};
					_param['phone'] = numbers[i];
					_param['conts'] = $("#" + parentView).find("#conts").val();
					_param['rcvId'] = users[i];

					totalCnt++;
					/**
					 * TODO : NDPS로 변환.
					 *
					 */
					//_common.callAjax("/sysMng/sendSms.json", _param, function(json){
					//	if(json.result == '1') scsCnt++;
					//}, false);
				}

				alert("총 " + totalCnt + "건 중, " + scsCnt + " 건이 발송되었습니다.");
				init();
			});
		}else{
			alert("연락처를 선택해 주세요.");
		}
	})

	/* bPopup Close */
	$(".bpopClose").click(function(){


		$(".bpopup").bPopup().close();
	});

	var result = false;


	$("#" + parentView).find(".searchWrapper").find("#addBtn").click(function(){
//		$("#eventWrapper").find("#statEvetTypCd").find("option").show();
//		$("#eventWrapper").find("#statEvetNm").find("option").show();
//		$("#eventWrapper").find("#isTest").find("option").show();
		$("#eventWrapper option").show();

		$("#" + parentView).find("#eventWrapper").toggle("slide");
	});

	$("#" + parentView).find(".searchWrapper").find("input[name=sendTarget]").click(function(){
		var val = $(this).val();
		if(val == "targetId"){
			$("#eventWrapper").find("#targetId").show();
			$("#eventWrapper").find("#targetId").find("option").eq(0).prop("selected", "selected");
			$("#eventWrapper").find("#targetGrp").hide();
		}else{
			$("#eventWrapper").find("#targetId").hide();
			$("#eventWrapper").find("#targetGrp").show();
			$("#eventWrapper").find("#targetGrp").find("option").eq(0).prop("selected", "selected");
		}
	});

	/* 수정 팝업 */
	$("#" + parentView).find("#editBtn").click(function(){

		$("#eventWrapper option").show();

		var data = $("#" + parentView).find("#ctntTable").data();
		var isNull = true;

		$("#eventWrapper").find(".sendData").val("");
		for(var k in data){
			isNull = false;
			console.log(data[k]);
			if ( k === 'statEvetCntn' ) data[k] = data[k].replace(/;;/gi, '\n');
			if ( k === 'statEvetActnCntn' ) data[k] = data[k].replace(/;;/gi, '\n');
			$("#eventWrapper").find("#" + k).val(data[k]);

			if(k == "statEvetTypCd"){
				$("#eventWrapper").find("#statEvetTypCd").find("option").hide();
				$("#eventWrapper").find("#statEvetTypCd").find("option[value=" + data[k].trim().replaceAll(" " ,"") + "]").show();
				$("#eventWrapper").find("#statEvetTypCd").find("option[value=" + data[k].trim().replaceAll(" " ,"") + "]").prop("selected", "selected");
			}

			if(k == "statEvetNm"){
				$("#eventWrapper").find("#statEvetNm").find("option").hide();
				$("#eventWrapper").find("#statEvetNm").find("option[value=" + data[k].trim().replaceAll(" " ,"") + "]").show();
				$("#eventWrapper").find("#statEvetNm").find("option[value=" + data[k].trim().replaceAll(" " ,"") + "]").prop("selected", "selected");
			}

			if(k == "isTest"){
				$("#eventWrapper").find("#isTest").find("option").hide();

				if(data[k] == "Y"){
					$("#eventWrapper").find("#isTest").find("option[value=Y]").show();
					$("#eventWrapper").find("#isTest").find("option[value=Y]").prop("selected", "selected");
				}else{
					$("#eventWrapper").find("#isTest").find("option[value=N]").show();
					$("#eventWrapper").find("#isTest").find("option[value=N]").prop("selected", "selected");
				}
			}
		}
		$("#eventWrapper").find("#outbPosX").val(data.outbPos[0].x);
		$("#eventWrapper").find("#outbPosY").val(data.outbPos[0].y);

		$("#" + parentView).find("#eventWrapper").toggle("slide");

		if(isNull){
//			alert("이벤트 상세정보 열람 후 관리할 수 있습니다.");
		}else{
//			$("#" + parentView).find("#eventWrapper").toggle("slide");
//
//			var val = $("#eventWrapper").find("#statEvetTypCd").val();
//			if(val == ""){
//				$("#eventWrapper").find("#statEvetNm").find("option").hide();
//				$("#eventWrapper").find("#statEvetNm").find("option").eq(0).show();
//			}else{
//				$("#eventWrapper").find("#statEvetNm").find("option").hide();
//				$("#eventWrapper").find("#statEvetNm").find("option[k=" + val + "]").show();
//			}
//
//			$("#eventWrapper").find("#saveBtn").attr("mode", "edit");
//			$("#eventWrapper").find("#uSvcOutbId").prop("readOnly", "readOnly");
//			$("#eventWrapper").find("#uSvcOutbId").parent().parent().show();
//			//$("#eventWrapper").find("#delBtn").show();
//
//			$("#eventWrapper").find("#statEvetTypCd, #statEvetNm").removeProp("readOnly");
//			$("#eventWrapper").find("#statEvetActnCntn").parent().parent().show();
//			$("#eventWrapper").find("#procSt").find("option").show();
//			$("#eventWrapper").find("#procSt").find("option").eq(0).prop("selected", "selected");
//			$("#eventWrapper").find("#procSt").find("option[value=10]").hide();
//
//			var type = data["statEvetNm"];
//
//			var $cctv = $("#" + parentView).find("#eventWrapper").find("#targetCctv").parent().parent(); $cctv.hide();
//			var $acciNum = $('#eventWrapper').find('#acciNum').parent().parent(); $acciNum.hide();
//			var $reqResn = $('#eventWrapper').find('#reqResn').parent().parent(); $reqResn.hide();
//
//			if(type == "PTZ" || type == "Preset"){
//				$cctv.show();
//			}
//			if(type == "CCTV 영상 공유"){
//				$cctv.show();
//			}
//			if(type == "선영상조회 요청"){
//				$cctv.show();
//				$acciNum.show();
//				$reqResn.show();
//			}
//
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

		var data = $("#" + parentView).find("#ctntTable").data();
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
				if(k == "statEvetCntn" && mode == "add") continue;
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
							setList({dateLimit : TIME_LIMIT});
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


	function setList(param){

		param["dataLimit"] = 'Y';

		_common.callAjax("/eventList/getList.json", param, function(json) {
			$(".searchWrapper").find("#evtWaitTable").find("tbody").html("");
			if ( json.result === undefined) {
				return false;
			}
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

						xeusJsonParser.move(data, false, Json.statEvetCntn.replace(/\//gi, '\r\n'));
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