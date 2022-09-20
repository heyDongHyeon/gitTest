(function(){

	/**
	 * 속성 검색 이벤트 입니다.
	 */
	$("#" + parentView).find(".searchWrapper").find("#searchBtn").click(function(){
		var _param = _common.utils.collectSendData("#" + parentView + " .searchWrapper #searchTable");
		_param["reqUserId"] = userId;

		_common.callAjax("/cctvPreview/getList.json", _param, function(json){
			if(json.result.length == 0){
				var $tr = $("<tr><td colspan='3' class='tCenter'>결과가 존재하지 않습니다.</td></tr>");
				$("#" + parentView).find(".searchWrapper").find("#resultTable").find("tbody").html($tr);
			}else{
				$("#" + parentView).find(".searchWrapper").find("#resultTable").find("tbody").html("");
				for(var i=0; i<json.result.length; i++){
					var $tr = $("<tr class='tCenter' k='" + json.result[i].mgrSeq + "'></tr>");
						$tr.append("<td>" + json.result[i].cctvNm + "</td>");
						$tr.append("<td>" + new Date().formatDate(json.result[i].reqDat) + "</td>");
						$tr.append("<td><button class='locBtn'></button></td>");

					var prop = json.result[i];
					prop["point"] = [Number(json.result[i].tmx), Number(json.result[i].tmy)];
					$tr.data(prop);

					$("#" + parentView).find(".searchWrapper").find("#resultTable").find("tbody").append($tr);
				}

				/* 위치 버튼 이벤트입니다. */
				$("#" + parentView).find(".searchWrapper").find("#resultTable").find(".locBtn").click(function(){
					var prop = $(this).parent().parent().data();
					xeusLayout.mapService.getMap().getView().setCenter(prop.point);
				});
			}
		});
	});

	/**
	 * 공문전송 버튼 이벤트 입니다.
	 */
	$("#" + parentView).find(".searchWrapper").find("#docSendBtn").click(function(){
		$("#doc_pop_wrap").find(".sendData").val("");
		$("#doc_pop_wrap").bPopup();
	});

	$("#" + parentView).find(".searchWrapper").find("#reqResn").keyup(function(e){
		if(e.which == 13){
			$("#" + parentView).find(".searchWrapper").find("#searchBtn").click();
		}
	});

	/* DatePicker 생성 이벤트입니다. */
	$("#" + parentView).find(".searchWrapper").find(".datePicker").datepicker("destroy").datepicker({
	    changeMonth: true,
	    changeYear: true,
	    dateFormat: "yymmdd",
	    showButtonPanel: true,
	    beforeShowDay: $.datepicker.noBefore
	});

	/* MonthPicker 생성 이벤트입니다. */
	$("#" + parentView).find(".searchWrapper").find(".monthPicker").MonthPicker({
		MonthFormat: "yymm",
		Button: false
	});

	/* 저장 */
	$("#bpop_wrap").find("#saveBtn").click(function(){
		confirm("저장하시겠습니까?", function(){
			var reqDat = new Date().getYMD();
			if(Number($("#doc_pop_wrap").find("#docGbnCd").val()) == 20){
				reqDat = new Date().getYM();
			}
			_common.callAjax("/cctvPreview/getList.json", { "reqDat" : reqDat }, function(json){
				$("#doc_pop_wrap").find("#docJson").val(JSON.stringify(json));

				_common.formSubmit("/cctvPreviewDoc/add.json", $("#doc_pop_wrap").find("#sendForm"), function(json){
					if(json.result == true){
						$("#doc_pop_wrap").bPopup().close();
					}else{
						alert(json.result);
						return false;
					}
				});
			}, false);
		});
	});

	/* bPopup Close */
	$("#closeEditPop").click(function(){
		$("#doc_pop_wrap").bPopup().close();
	}).css("cursor", "pointer");

})();