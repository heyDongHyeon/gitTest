$(document).ready(function(){
	var value = $("#" + parentView).find("#searchInput").val();
	$("#" + parentView).find("#searchInput").val(value).focus();

	$("#" + parentView).find(".paging_wrap").paging({
		current	  : 10,
		max  	  : Number($("#" + parentView).find("#max").val()),
		nowOffset : Number($("#" + parentView).find("#offset").val()),
		bindEvent : callView
	});

	$.widget('ui.dialog', jQuery.extend({}, jQuery.ui.dialog.prototype, {
		_title : function(titleBar) {
			titleBar.html(this.options.title || '&#160;');
		}
	}));
});

function callView(offset){
	if(offset == null) offset = 0;
	var _param = {"limit" : 10, "offset" : offset};
	_param['gbn'] = gbn;

	var val = $("#" + parentView).find("#searchInput").val();
	if(val != "" && val != null) _param["vmsNm"] = val;

	_common.callAjax("/vms/getVmsView.do", _param, function(view){
		$("#" + parentView).find(".bpopup").remove();
		$("#" + parentView).find("#overlay-west-contents").html(view);
	});
}

/* 수정 팝업 */
$("#" + parentView).find(".mngBtn").click(function(){
	var mgrSeq = $(this).attr("k");

	_common.callAjax("/vms/getItem.json", {"mgrNo" : mgrSeq}, function(json){
		if(json.result != null){
			for(var key in json.result){
				$("#" + parentView).find("#edit_pop_wrap").find("#" + key).val(json.result[key]);
			}
			$("#" + parentView).find("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
			$("#" + parentView).find("#edit_pop_wrap").find("#saveBtn").attr("mode", "edit");
			$("#" + parentView).find("#edit_pop_wrap").find('#delBtn').show();
		}
	}, false);
});

/* 신규 팝업 */
$("#" + parentView).find("#addBtn").click(function(){
	$("#" + parentView).find(".sendData").val("");
	$("#" + parentView).find("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
	$("#" + parentView).find("#edit_pop_wrap").find("#saveBtn").attr("mode", "add");
	$("#" + parentView).find("#edit_pop_wrap").find('#delBtn').hide();
});

/* 수정 */
$("#" + parentView).find("#saveBtn").click(function(){
	var mode = $(this).attr("mode");

	confirm("저장하시겠습니까?", function(){
		_common.callAjax("/vms/" + mode + ".json", _common.utils.collectSendData(), function(json){
			if(json.result == true){
				$("#" + parentView).find("#edit_pop_wrap").bPopup().close();
				setTimeout(function(){
					callView();
				}, 300);
			}
		});
	});
});

/* 삭제 */
$("#" + parentView).find("#delBtn").click(function(){
	confirm("삭제하시겠습니까?", function(){
		_common.callAjax("/vms/del.json", _common.utils.collectSendData(), function(json){
			if(json.result == true){
				$("#" + parentView).find("#edit_pop_wrap").bPopup().close();
				setTimeout(function(){
					callView();
				}, 300);
			}
		});
	});
});

/* 검색버튼 */
$("#" + parentView).find("#searchBtn").click(function(){
	callView();
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
	$("#" + parentView).find(".bpopup").bPopup().close();
});