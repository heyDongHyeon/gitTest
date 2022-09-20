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
	if(val != "" && val != null) _param["userIdOrNm"] = val;*/
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
	_param["discardChk"] = "Y";
	_param["gbn"] = gbn;
	if(_param["userIdOrNm"] == null){
		if(userIdOrNm != "" && userIdOrNm != null) _param["userIdOrNm"] = userIdOrNm;
	}

	_common.callAjax("/userMng/getUserView.do", _param, function(view){
		$("#" + parentView).find(".bpopup").remove();
		$("#" + parentView).find("#overlay-west-contents").html(view);
	});
}

/* 계정 수정(수정 팝업창 생성)*/
$("#" + parentView).find(".mngBtn").click(function(){
	var userId = $(this).attr("k");

	_common.callAjax("/user/getItem.json", {"userId" : userId}, function(json){
		if(json.result != null){
			for(var key in json.result){
				$("#" + parentView).find("#user_edit_pop_wrap").find("#" + key).val(json.result[key]);
			}

			$("#" + parentView).find("#authStatCd").attr("authStatCd", json.result["authStatCd"]);
			$("#" + parentView).find("#user_edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
			$("#" + parentView).find("#user_edit_pop_wrap").find("#saveBtn").attr("mode", "edit");
			$("#" + parentView).find("#user_edit_pop_wrap").find("#userId").attr('readonly', 'readonly');
			$("#" + parentView).find("#user_edit_pop_wrap").find("#userId").css('width', '95%');
			$("#" + parentView).find("#user_edit_pop_wrap").find(".add").hide();
			$("#" + parentView).find("#user_edit_pop_wrap").find(".edit").show();
		}
	}, false);
});

/* 신규 팝업 */
$("#" + parentView).find("#addBtn").click(function(){
	$("#" + parentView).find("#user_edit_pop_wrap").find('.sendData').each(function(){
		if($(this).is("input")) $(this).val('') ;
		else if($(this).is("select")) $(this).find('option:eq(0)').attr("selected", "selected");
	});
	idChkStat = false;
	$("#" + parentView).find("#user_edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
	$("#" + parentView).find("#user_edit_pop_wrap").find("#saveBtn").attr("mode", "add");
	//신규 추가이므로 readonly 속성을 제거
	$("#" + parentView).find("#user_edit_pop_wrap").find("#userId").removeAttr('readonly');
	$("#" + parentView).find("#user_edit_pop_wrap").find("#userId").css('width', "60%");
	$("#" + parentView).find("#user_edit_pop_wrap").find("#userPwdChk").val('');
	$("#" + parentView).find("#user_edit_pop_wrap").find(".add").show();
	$("#" + parentView).find("#user_edit_pop_wrap").find(".edit").hide();
});

/* 아이디 중복 확인 */
$("#" + parentView).find("#idChk").click(function(){

	var id = $("#" + parentView).find("#user_edit_pop_wrap").find("#userId").val();

	if(id == "" || id == null){
		alert("아이디를 입력하여 주십시오!");
		$("#" + parentView).find("#user_edit_pop_wrap").find("#userId").focus();
		return false;
	}

	if(id.length < 6){
		alert("아이디는 6자리 이상 입력하여 주십시오!");
		$("#" + parentView).find("#userId").focus();
		return false;
	}

	_common.callAjax("/user/getItem.json", {"userId" : id}, function(json){
		if(json.result == null){
			alert("사용하셔도 좋습니다.\n나머지 항목을 입력해 주세요.");
			$("#" + parentView).find("#user_edit_pop_wrap").find("#userNm").focus();
			idChkStat = true;
		}else{
			if(json.result.userId != ""){
				alert("입력하신 계정이 이미 존재합니다.\n다른 계정을 입력하여 주십시오.");
				$("#" + parentView).find("#user_edit_pop_wrap").find("#userId").focus();
				idChkStat = false;
			}
		}
	});
});

/* 계정 등록 및 수정 */
$("#" + parentView).find("#saveBtn").click(function(){
	var chkNotice = $("#" + parentView).find('.notice').length;
	if(chkNotice == 0){
		confirm("저장하시겠습니까?", function(){
			var mode = $("#" + parentView).find("#saveBtn").attr("mode")
			if(mode == "add"){
				if(idChkStat){
					//TODO 신규추가 로직 진행
					var _param = _common.utils.collectSendData();
					//비밀번호는 따로 받아와야 함.
					var $userPwd = $("#" + parentView).find("#user_edit_pop_wrap").find("#userPwd");
					var $userPwdChk = $("#" + parentView).find("#user_edit_pop_wrap").find("#userPwdChk");
					//비밀번호와 확인란의 값이 같은지 확인해야 함.
					if($userPwd.val() != $userPwdChk.val()){
						alert("패스워드가 같지 않습니다.");
						$userPwd.focus();
						return false;
					}
					if($userPwd.val() == ''){
						alert('비밀번호를 입력하여 주십시오.');
						return false;
					}
					_param['userPwd'] = $userPwd.val();
					//계정상태는 승인으로 넣기(12)
					_param['authStatCd'] = '12';
					var date = new Date().getYMDHMS();
					_param["acptDat"] = date;
					_param['authAtmtCnt'] = '0';

					_common.callAjax("/userMng/add.json", _param, function(json){
						if(json.result == true){
							//alert('등록되었습니다.');
							$("#" + parentView).find("#user_edit_pop_wrap").bPopup().close();
							setTimeout(function(){
								var _param={};
								if(userIdOrNm != "" && userIdOrNm != null) _param["userIdOrNm"] = userIdOrNm;
								callView(offset, _param);
								//callView();
							}, 300);
						}
					}, false);
				} else{
					alert("계정 중복확인은 필수사항 입니다.");
					$("#" + parentView).find("#user_edit_pop_wrap").find("#userId").focus();
					return false;
				}
			} else if(mode == "edit"){
				var _param = _common.utils.collectSendData();
				var def = $("#" + parentView).find("#authStatCd").attr("authStatCd");
				var cde = _param["authStatCd"];
				if(cde == ""){
					alert('권한그룹을 선택하여 주십시오.');
					return false;
				}
				if(def != cde){
					var date = new Date().getYMDHMS();
					_param["acptDat"] = "";
					_param["lockDat"] = "";
					_param["exprDat"] = "";
					if(cde == "12"){
						_param["acptDat"] = date;
					}else if(cde == "14"){
						_param["lockDat"] = date;
					}else if(cde == "15"){
						_param["exprDat"] = date;
					}
				}
				_common.callAjax("/userMng/edit.json", _param, function(json){
					if(json.result){
						/**
						 * 180612 이은규
						 * 폐기상태로 바뀌는 경우 SMS 임시저장 리스트에서 해당 계정이 삭제되어야 한다.
						 */
						if(cde == "15"){
							_common.callAjax("/sms/delDiscardId.json", _param, function(json){
								if(!json.result){
									console.log('sms list is not updated');
								}
							}, false);
						}
						$("#" + parentView).find("#user_edit_pop_wrap").bPopup().close();
						setTimeout(function(){
							var _param={};
							if(userIdOrNm != "" && userIdOrNm != null) _param["userIdOrNm"] = userIdOrNm;
							callView(offset, _param);
							//callView();
						}, 300);
					}
				}, false);
			}
		});
	}
});

/* 파일 다운 */
$("#" + parentView).find(".downBtn").click(function(){
	var k = $(this).attr("k");
	var u = $(this).attr("u");

	if(k != null && k != "" && u != null && u != ""){
		_common.postForm.submit("/user/getFile.json", { "oathFileNm" : k , "userId" : u });
	}
});

/* 검색버튼 */
$("#" + parentView).find("#searchBtn").click(function(){
	var _param = {};
	var userIdOrNm = $("#" + parentView).find("#searchInput").val();
	if(userIdOrNm != null) _param["userIdOrNm"] = userIdOrNm;
	var authStatCd = $("#" + parentView).find("#searchAuthStatCd").val();
	if(authStatCd != null && authStatCd != "") _param["authStatCd"] = authStatCd;
	var authGrpNo = $("#" + parentView).find("#searchAuthGrpNo").val();
	if(authGrpNo != null && authGrpNo != "") _param["authGrpNo"] = authGrpNo;

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