function resize(){
	$("#wrap").center();
}

$(document).ready(function (){

	resize();

	$(window).resize(function(){
		resize();
	});

	$("input, select").focus(function(){
		$(this).parent().parent().css("background-color", "#efefef");
	}).focusout(function(){
		$(this).parent().parent().css("background-color", "#ffffff");
	});

	$("#userId").focus();

	$("#idChk").on("click", function(){
		user.reg.idChk();
	});

	$("#titleClose").on("click", function(){
		confirm("입력란을 모두 비우시겠습니까?", function(){
			$("input.sendData").val("");
		});
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

});
