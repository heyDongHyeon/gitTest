/**
 *
 */
$(document).ready(function(){

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
		showButtonPanel: true,
		beforeShowDay: $.datepicker.noBefore
	});
	$(".searchWrapper").find(".datePickerEnd").datepicker("destroy").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yymmdd",
		showButtonPanel: true,
		beforeShowDay: $.datepicker.noBefore
	});


	$('.datePickerStart').val(srchDateStart);
	$('.datePickerEnd').val(srchDateEnd);

	$("#searchBtn").click(function(){
		var _param = _common.utils.collectSendData("#searchTable");
		_param["limit"] = 10;
		_param["offset"] = 0;
		srchExcEventParam = _param;
		if(userAuth != null && userAuth != "")
			_param['userAuth'] = userAuth;

		_common.callAjax("/eventHist/getEventHistView.do", _param, function(view) {
			$("#overlay-west-contents").html(view);
			$("#" + parentView).find(".paging_wrap").paging({
				current	  : 10,
				max  	  : Number($("#max").val()),
				nowOffset : Number($("#offset").val()),
				bindEvent : callView
			});
			setList(_param);
		});
	});

	$("#excelBtn").click(function(){

		delete srchExcEventParam.limit;
		delete srchExcEventParam.offset;

		if($(".searchWrapper").find("#evtHistTable").find("tbody").children().length == 0){
			alert("검색결과가 존재하지 않아 다운로드할 수 없습니다.");
		}else{
				confirm("검색결과를 엑셀로 다운로드 하시겠습니까?", function(){
				_common.postForm.submit("/eventHist/getEventHistExcel.do", srchExcEventParam, '_blank');
			});
		}
	});

	if(isFirst){
		var _param = _common.utils.collectSendData("#searchTable");
		_param["limit"] = 10;
		_param["offset"] = 0;
		srchExcEventParam = _param;
		setList(_param);
		$("#" + parentView).find(".paging_wrap").paging({
			current	  : 10,
			max  	  : Number($("#max").val()),
			nowOffset : Number($("#offset").val()),
			bindEvent : callView
		});
	}

});

function callView(offset, _param){
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

	if(userAuth != null && userAuth != "")
		_param['userAuth'] = userAuth;

	_common.callAjax("/eventHist/getEventHistView.do", _param, function(view) {
		$("#overlay-west-contents").html(view);
		setList(_param);
		$("#" + parentView).find(".paging_wrap").paging({
			current	  : 10,
			max  	  : Number($("#max").val()),
			nowOffset : Number($("#offset").val()),
			bindEvent : callView
		});
	});
}

function setList(param){
	_common.callAjax("/eventHist/getList.json", param, function(json) {
		$(".searchWrapper").find("#evtHistTable").find("tbody").html("");
		if ( json.result === undefined || json.result.length === 0) {
			var $noSearch = $(".searchWrapper").find("#listWrap");
			var $div="<div style =\"position: absolute;left: 36%; top: 50%; font-size: 20px;\">검색결과가 없습니다.</div>";
			$noSearch.append($div);
			return false;
		}

		for(var i=0; i<json.result.length; i++){
			var $tbl = $(".searchWrapper").find("#evtHistTable");
			var Json = JSON.parse(json.result[i]);

			xeusJsonParser.setJson(Json);
			var $tr = $("<tr></tr>").attr("k", xeusJsonParser.getUSvcOutbId()).data(Json);
			$tr.css({"cursor" : "pointer"}).click(function(){
				xeusJsonParser.setEventContent($(this).data(), 'hist');
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
				if ( Json === 'NDMSWARN' ) {

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