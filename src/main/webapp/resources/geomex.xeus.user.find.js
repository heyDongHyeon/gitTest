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

	$("#titleClose").on("click", function(){
		confirm("입력란을 모두 비우시겠습니까?", function(){
			$("input.sendData").val("");
		});
	});

	$("#backBtn, #back").on("click", function(){
		if(isLogin){
			confirm("지도 화면으로 이동하시겠습니까?", function(){
				location.href = _common.context() + "/layout/layout.jsp";
			});
		}else{
			confirm("로그인 페이지로 이동하시겠습니까?", function(){
				location.href = _common.context() + "/user/login.do";
			});
		}
	});

	$('#okBtn').click(function(){
		var mode = $(this).attr("mode");
		user.valid.find(mode);
	});

	$('.tab').click(function(){
		if(!$(this).hasClass("active")){
			$('.tab').removeClass("active");
			$(this).addClass("active");
			if($(this).hasClass("findId")){
				$('.pwOnly').hide();
				$('#okBtn').attr("mode", "id");
			}else{
				$('.pwOnly').show();
				$('#okBtn').attr("mode", "pw");
			}
		}
	});

});
