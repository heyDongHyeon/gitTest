function resize(){

	$("#wrap").center();
}

$(document).ready(function (){

	resize();

	$(window).resize(function(){
		resize();
	});

	$("input, select").not("#fileDown").focus(function(){
		$(this).parent().parent().css("background-color", "#efefef");
	}).focusout(function(){
		$(this).parent().parent().css("background-color", "#ffffff");
	});

	$("#pwdChange").on("click", function(){
		$(".bpopup").find("#nowUserPwd, #newUserPwd, #newUserPwdRe").val("");
		$(".bpopup").bPopup({appendTo: $('#'+parentView)});
		$(".bpopup").find("#nowUserPwd").focus();
	});

	$("#closePop").on("click", function(){
		$(".bpopup").bPopup().close();
	});

	$("#titleClose").on("click", function(){
		confirm("입력란을 모두 비우시겠습니까?", function(){
			$("input.sendData").not("#userId").val("");
		});
	});

	$("#saveBtn").on("click", function(){
		user.valid.editPassword();
	});

	$("#userPwd").focus();

	$("#idChk").on("click", function(){
		user.reg.idChk();
	});

	$("#backBtn, #back").on("click", function(){
		if(isLogin){
			confirm("지도 화면으로 이동하시겠습니까?", function(){
				location.href = _common.context() + "/map/view.do";
			});
		}else{
			confirm("로그인 페이지로 이동하시겠습니까?", function(){
				location.href = _common.context() + "/user/login.do";
			});
		}
	});

	if($("#fileDown").val() == ""){
		$("#formTr").removeClass("hidden");
	}

});

/* 파일 다운 */
$(document).on("click", "#fileDown", function(){
	var k = $(this).attr("k");
	var u = $(this).attr("u");

	if(k != null && k != "" && u != null && u != ""){
		_common.postForm.submit("/user/getFile.json", { "oathFileNm" : k , "userId" : u });
	}
});

/* 파일 수정 */
$(document).on("click", "#editBtn", function(){
	$("#downTr").addClass("hidden");
	$("#formTr").removeClass("hidden");
});