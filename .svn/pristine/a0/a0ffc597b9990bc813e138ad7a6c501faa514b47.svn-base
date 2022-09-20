$(document).ready(function(){
	var value = $("#" + parentView).find("#searchInput").val();
	$("#" + parentView).find("#searchInput").val(value).focus();

	$("#" + parentView).find(".paging_wrap").paging({
		current	  : 10,
		max  	  : Number($("#" + parentView).find("#max").val()),
		nowOffset : Number($("#" + parentView).find("#offset").val()),
		bindEvent : callView
	});
});

function callView(offset, _param){
	/*if(offset == null) offset = 0;
	var _param = {"limit" : 10, "offset" : offset};

	var val = $("#searchInput").val();
	if(val != "" && val != null) _param["cdeNm"] = val;*/
	/**
	 * 180523 이은규
	 * 검색기능 수정
	 * 페이지 로드 시 들어온 값으로 페이징 처리한다.
	 */
	if(offset == null) offset = 0;
	if(_param == null){
		var _param = {};
	}
	_param["limit"] = 10;
	_param["offset"] = offset;
	_param['gbn'] = gbn;
	if(_param["cdeNm"] == null){
		if(cdeNm != "" && cdeNm != null) _param["cdeNm"] = cdeNm;
	}

	_common.callAjax("/code/getCodeView.do", _param, function(view){
		$("#" + parentView).find(".bpopup").remove();
		$("#" + parentView).find("#overlay-west-contents").html(view);
	});
}

/* 수정 팝업 */
$("#" + parentView).find(".mngBtn").click(function(){
	var grp = $(this).attr("grp");
	var key = $(this).attr("k");

	_common.callAjax("/code/getItem.json", {"grpCde" : grp, "cdeCde" : key}, function(json){
		if(json.result != null){
			for(var key in json.result){
				$("#" + parentView).find("#edit_pop_wrap").find("#" + key).val(json.result[key]);
			}
			$("#" + parentView).find("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
			$("#" + parentView).find("#edit_pop_wrap").find("#saveBtn").attr("mode", "edit");
			$("#" + parentView).find("#edit_pop_wrap").find("#delBtn").show();
		}
	}, false);
});

/* 신규 팝업 */
$("#" + parentView).find("#addBtn").click(function(){
	$("#" + parentView).find("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
	$("#" + parentView).find("#edit_pop_wrap").find("#saveBtn").attr("mode", "add");
	$("#" + parentView).find("#edit_pop_wrap").find("#delBtn").hide();
	$("#" + parentView).find(".sendData").val("");
});

/* 수정 */
$("#" + parentView).find("#saveBtn").click(function(){
	var chkNotice = $("#" + parentView).find('.notice').length;
	if(chkNotice == 0){
		var mode = $(this).attr("mode");
		confirm("저장하시겠습니까?", function(){
			_common.callAjax("/code/" + mode + ".json", _common.utils.collectSendData(), function(json){
				if(json.result == true){
					$("#" + parentView).find("#edit_pop_wrap").bPopup().close();
					setTimeout(function(){
						var _param={};
						if(cdeNm != "" && cdeNm != null) _param["cdeNm"] = cdeNm;
						callView(offset, _param);
						//callView();
					}, 300);
				}
			});
		});
	}
});

/* 삭제 */
$("#" + parentView).find("#delBtn").click(function(){
	var chkNotice = $("#" + parentView).find('.notice').length;
	if(chkNotice == 0){
		confirm("삭제하시겠습니까?", function(){
			_common.callAjax("/code/del.json", _common.utils.collectSendData(), function(json){
				if(json.result == true){
					$("#" + parentView).find("#edit_pop_wrap").bPopup().close();
					setTimeout(function(){
						var _param={};
						if(cdeNm != "" && cdeNm != null) _param["cdeNm"] = cdeNm;
						callView(offset, _param);
						//callView();
					}, 300);
				}
			});
		});
	}
});

/* 검색버튼 */
$("#" + parentView).find("#searchBtn").click(function(){
	var _param = {};
	var val = $("#" + parentView).find("#searchInput").val();
	if(val != null) _param["cdeNm"] = val;
	callView(0, _param);
	//callView();
});

/* 엔터키 이벤트 */
$("#" + parentView).find(".keyup").keyup(function(e){
	if(e.which == 13){
		var selector = $(this).attr("for");
		$(selector).click();
	};
});

/* bPopup Close */
$("#" + parentView).find(".bpopClose").click(function(){
	var chkNotice = $("#" + parentView).find('.notice').length;
	if(chkNotice == 0){
		$("#" + parentView).find(".bpopup").bPopup().close();
	}
});