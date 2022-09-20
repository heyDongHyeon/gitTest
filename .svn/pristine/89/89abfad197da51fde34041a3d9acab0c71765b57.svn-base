$(document).ready(function(){
	var value = $("#" + parentView).find("#searchInput").val();
	$("#" + parentView).find("#searchInput").val(value).focus();

	$("#" + parentView).find(".downBtn").each(function(){
		if($(this).attr("k") == ""){
			$(this).removeAttr("class", "");
		}
	});

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

function callView(offset, _param){
	/*if(offset == null) offset = 0;
	var _param = {"limit" : 10, "offset" : offset};

	var val = $("#searchInput").val();
	if(val != "" && val != null) _param["notcTitle"] = val;*/
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
	_param["gbn"] = gbn;
	if(_param["notcTitle"] == null){
		if(notcTitle != "" && notcTitle != null) _param["notcTitle"] = notcTitle;
	}

	console.log(_param);

	_common.callAjax("/notice/getNoticeView.do", _param, function(view){
		$("#" + parentView).find(".bpopup").remove();
		$("#" + parentView).find("#overlay-west-contents").html(view);
	});
}

/* 수정 팝업 */
$("#" + parentView).find(".mngBtn").click(function(){
	var mgrSeq = $(this).attr("k");

	_common.callAjax("/notice/getItem.json", {"mgrSeq" : mgrSeq}, function(json){
		if(json.result != null){
			$("#" + parentView).find("#formTr, #downTr").removeClass("hidden");
			$("#" + parentView).find("#formTr").addClass("hidden");
			for(var key in json.result){
				$("#" + parentView).find("#edit_pop_wrap").find("#" + key).val(json.result[key]);
				if(key == "atchFileNm") $("#" + parentView).find("#fileDown").val(json.result[key]);
			}
			$("#" + parentView).find("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
			$("#" + parentView).find("#edit_pop_wrap").find("#saveBtn").attr("mode", "edit");
			$("#" + parentView).find("#edit_pop_wrap").find("#delBtn").show();

			/*if($("#fileDown").val() == ""){
				$("#formTr").removeClass("hidden");
			}*/
		}
	}, false);
});

/* 신규 팝업 */
$("#" + parentView).find("#addBtn").click(function(){
	$("#" + parentView).find(".sendData").val("");
	$("#" + parentView).find("#formTr, #downTr").removeClass("hidden");
	$("#" + parentView).find("#downTr").addClass("hidden");
	$("#" + parentView).find("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
	$("#" + parentView).find("#edit_pop_wrap").find("#saveBtn").attr("mode", "add");
	$("#" + parentView).find("#edit_pop_wrap").find("#delBtn").hide();
});

/* 저장 */
$("#" + parentView).find("#saveBtn").click(function(){
	var chkNotice = $("#" + parentView).find('.notice').length;
	if(chkNotice == 0){
		var mode = $(this).attr("mode");
		confirm("저장하시겠습니까?", function(){
			console.log("/notice/" + mode + ".json");
			_common.formSubmit("/notice/" + mode + ".json", $("#" + parentView).find("#sendForm"), function(json){
				if(json.result == true){
					$("#" + parentView).find("#edit_pop_wrap").bPopup().close();
					setTimeout(function(){
						var _param={};
						if(notcTitle != "" && notcTitle != null) _param["notcTitle"] = notcTitle;
						callView(offset, _param);
						//callView();
					}, 300);
				}else{
					alert(json.result);
					return false;
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
			_common.callAjax("/notice/del.json", _common.utils.collectSendData(), function(json){
				if(json.result == true){
					$("#" + parentView).find("#edit_pop_wrap").bPopup().close();
					setTimeout(function(){
						var _param={};
						if(notcTitle != "" && notcTitle != null) _param["notcTitle"] = notcTitle;
						callView(offset, _param);
						//callView();
					}, 300);
				}
			});
		});
	}
});

/* 파일 다운 */
$("#" + parentView).find(".downBtn").click(function(){
	var k = $(this).attr("k");
	var u = $(this).attr("u");

	if(k != null && k != "" && u != null && u != ""){
		_common.postForm.submit("/notice/getFile.json", { "atchFileNm" : k , "mgrSeq" : u });
	}
});

/* 파일 수정 */
$("#" + parentView).find("#editBtn").click(function(){
	$("#" + parentView).find("#downTr").addClass("hidden");
	$("#" + parentView).find("#formTr").removeClass("hidden");
});

/* 검색버튼 */
$("#" + parentView).find("#searchBtn").click(function(){
	var _param = {};
	var val = $("#" + parentView).find("#searchInput").val();
	if(val != null) _param["notcTitle"] = val;
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
$("#" + parentView).find("#closeEditPop").click(function(){
	var chkNotice = $("#" + parentView).find('.notice').length;
	if(chkNotice == 0){
		$("#" + parentView).find(".bpopup").bPopup().close();
	}
});