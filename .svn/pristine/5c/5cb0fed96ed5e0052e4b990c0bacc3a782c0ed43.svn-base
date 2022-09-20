$(document).ready(function(){
	var value = $("#searchInput").val();
	$("#searchInput").val(value).focus();

	$(".paging_wrap").paging({
		current	  : 10,
		max  	  : Number($("#max").val()),
		nowOffset : Number($("#offset").val()),
		bindEvent : callView
	});
});

function callView(offset){
	if(offset == null) offset = 0;
	var _param = {"limit" : 10, "offset" : offset};

	var val = $("#searchInput").val();
	if(val != "" && val != null) _param["orgNm"] = val;

	_common.callAjax("/ip/getIpView.do", _param, function(view){
		$(".bpopup").remove();
		$("#overlay-west-contents").html(view);
	});
}

/* 수정 팝업 */
$(".mngBtn").click(function(){
	var mgrSeq = $(this).attr("k");

	_common.callAjax("/ip/getItem.json", {"mgrSeq" : mgrSeq}, function(json){
		if(json.result != null){
			for(var key in json.result){
				$("#edit_pop_wrap").find("#" + key).val(json.result[key]);
			}
			$("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
			$("#edit_pop_wrap").find("#saveBtn").attr("mode", "edit");
		}
	}, false);
});

/* 신규 팝업 */
$("#addBtn").click(function(){
	$(".sendData").val("");
	$("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
	$("#edit_pop_wrap").find("#saveBtn").attr("mode", "add");
});

/* 수정 */
$("#saveBtn").click(function(){
	var mode = $(this).attr("mode");

	confirm("저장하시겠습니까?", function(){
		_common.callAjax("/ip/" + mode + ".json", _common.utils.collectSendData(), function(json){
			if(json.result == true){
				$("#edit_pop_wrap").bPopup().close();
				setTimeout(function(){
					callView();
				}, 300);
			}
		});
	});
});

/* 삭제 */
$("#delBtn").click(function(){
	confirm("삭제하시겠습니까?", function(){
		_common.callAjax("/ip/del.json", _common.utils.collectSendData(), function(json){
			if(json.result == true){
				$("#edit_pop_wrap").bPopup().close();
				setTimeout(function(){
					callView();
				}, 300);
			}
		});
	});
});

/* 검색버튼 */
$("#searchBtn").click(function(){
	callView();
});

/* 엔터키 이벤트 */
$(".keyup").keyup(function(e){
	if(e.which == 13){
		var selector = $(this).attr("for");
		$(selector).click();
	};
});

/* bPopup Close */
$(".bpopClose").click(function(){
	$(".bpopup").bPopup().close();
});