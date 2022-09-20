$(document).ready(function(){
	var value = $("#" + parentView).find("#searchInput").val();
	$("#" + parentView).find("#searchInput").val(value).focus();
	$("#" + parentView).find("#authGrpList").css("height", $("#" + parentView).find("#authList").height());

	$.widget('ui.dialog', jQuery.extend({}, jQuery.ui.dialog.prototype, {
		_title : function(titleBar) {
			titleBar.html(this.options.title || '&#160;');
		}
	}));
});

/* 등록된 권한 조회 */
$("#" + parentView).find(".grp").click(function(){
	var key = $(this).attr("k");

	$("#" + parentView).find("input.auth").removeAttr("grp").prop("checked", false);
	_common.callAjax("/auth/getAuthGrpList.json", {"authGrpNo" : key}, function(json){
		if(json.result != null){
			if(json.result.length == 0){
				$("#" + parentView).find("input.auth").attr("grp", key);
				$("#" + parentView).find("input.auth, #allAuth").prop("disabled", false);
				$("#" + parentView).find("#allAuth").prop("checked", false);
			}else{
				var array = json.result[0].authMgrNo.split(",");

				$("#" + parentView).find("input.auth").attr("grp", key);
				for(var i=0; i<array.length; i++){
					$("#" + parentView).find("input.auth[k=" + array[i] + "]").prop("checked", true);
				}
				$("#" + parentView).find("input.auth, #allAuth").prop("disabled", false);
				if($("#" + parentView).find("input.auth").is(":checked")){
					$("#" + parentView).find("#allAuth").prop("checked", true);
				}
			}
		}
	}, false);
});

/* 권한 수정 */
$("#" + parentView).find(".grp").dblclick(function(){
	var key = $(this).attr("k");

	_common.callAjax("/auth/getGrpList.json", {"authGrpNo" : key}, function(json){
		if(json.result != null){
			$("#" + parentView).find('#edit_pop_wrap').find('#authGrpNm').val('');
			for(var key in json.result[0]){
				$("#" + parentView).find("#edit_pop_wrap").find("#" + key).val(json.result[0][key]);
			}
			$("#" + parentView).find("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
			$("#" + parentView).find("#edit_pop_wrap").find("#saveBtn").attr("mode", "edit");
			$("#" + parentView).find("#saveBtn").css('width', '32.5%')
			$("#" + parentView).find("#closeEditPop").css('width', '32.5%')
			$("#" + parentView).find("#delBtn").css('width', '32.5%')
			$("#" + parentView).find("#delBtn").show();
		}
	}, false);
});

/* 그룹 삭제 */
$("#" + parentView).find("#delBtn").click(function(){
	confirm("그룹을 삭제하시겠습니까?", function(){
		_common.callAjax("/auth/delGrp.json", _common.utils.collectSendData(), function(json){
			if(json.result == true){
				$("#" + parentView).find("#edit_pop_wrap").bPopup().close();
				setTimeout(function(){
					_common.callAjax("/auth/getAuthView.do", {"gbn" : gbn}, function(view){
						$("#" + parentView).find(".bpopup").remove();
						$("#" + parentView).find("#overlay-west-contents").html(view);
					});
				}, 300);
			}
		}, false);
	});
});

/* 전체선택 클릭 이벤트 */
$("#" + parentView).find("#allAuth").click(function(){
	if($("#" + parentView).find("input.auth").is(":checked")){
		$("#" + parentView).find("input.auth:checked").each(function(){
			$(this).click();
		});
	}else{
		$("#" + parentView).find("input.auth").not(":checked").each(function(){
			$(this).click();
		});
	}
});

/* 명칭 클릭 이벤트 */
$("#" + parentView).find(".authNm").click(function(){
	$(this).prev().find("input").click();
});

/* 체크박스 이벤트 */
$("#" + parentView).find(".auth").change(function(){
	var grp = $(this).attr("grp");
	if(_common.utils.isNullAndEmpty(grp)){
		alert("그룹을 선택해 주세요.");
		return false;
	}
	var key = $(this).attr("k");
	var dat = new Date().getYMDHMS();
	var mode = "addGrpAuth";
	if(!$(this).is(":checked")) mode = "delGrpAuth";

	var param = {
		"authGrpNo" : grp,
		"authMgrNo"	: key
	}
	_common.callAjax("/auth/" + mode + ".json", param, function(json){}, true);

	if($("#" + parentView).find("input.auth").is(":checked")){
		$("#" + parentView).find("#allAuth").prop("checked", true);
	}else{
		$("#" + parentView).find("#allAuth").prop("checked", false);
	}
});

/* 뒤로가기 */
$("#" + parentView).find("#back").click(function(){
	location.href = "../map/view.do";
});

/* 신규 팝업 */
$("#" + parentView).find("#addBtn").click(function(){
	$("#" + parentView).find('#edit_pop_wrap').find('#authGrpNm').val('');
	$("#" + parentView).find("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
	$("#" + parentView).find("#edit_pop_wrap").find("#saveBtn").attr("mode", "add");
	$("#" + parentView).find("#saveBtn").css('width', '48.5%')
	$("#" + parentView).find("#closeEditPop").css('width', '48.5%')
	$("#" + parentView).find("#delBtn").hide();
	$("#" + parentView).find("#authGrpNm").focus();
});

/* 수정 및 저장 */
$("#" + parentView).find("#saveBtn").click(function(){
	var mode = $(this).attr("mode");
	if(mode == "add") mode = "addGrp";
	if(mode == "edit") mode = "editGrp";

	confirm("저장하시겠습니까?", function(){
		_common.callAjax("/auth/" + mode + ".json", _common.utils.collectSendData(), function(json){
			if(json.result == true){
				$("#" + parentView).find("#edit_pop_wrap").bPopup().close();
				setTimeout(function(){
					_common.callAjax("/auth/getAuthView.do", {"gbn" : gbn}, function(view){
						$("#" + parentView).find(".bpopup").remove();
						$("#" + parentView).find("#overlay-west-contents").html(view);
					});
				}, 300);
			}
		});
	});
});

/* 검색버튼 */
$("#" + parentView).find("#searchBtn").click(function(){
	var val = $("#" + parentView).find("#searchInput").val();
	var _param ={};
	 _param["authGrpNm"] = val;
	 _param["gbn"] = gbn;
	_common.callAjax("/auth/getAuthView.do", _param, function(view){
		$("#" + parentView).find(".bpopup").remove();
		$("#" + parentView).find("#overlay-west-contents").html(view);
	});
	//_common.postForm.submit("/auth/getAuthView.do", { "authGrpNm" : val });
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