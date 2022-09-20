(function(){

	/* 체크박스의 세로정렬을 변경합니다. */
	$(".searchWrapper").find("#settingTable").find("input[type=checkbox]").css("vertical-align", "middle");

	/* label 클릭 이벤트 입니다. */
	$(".searchWrapper").find("#settingTable").find("label").click(function(){
		$(this).prev().click();
	});

	/* 시작 이벤트 입니다. */
	$(".searchWrapper").find("#startBtn").click(function(){
		$(".searchWrapper").find("#stopBtn").show();
		Public.EVT.Monitoring.Start();
		$(this).hide();
	});

	/* 종료 이벤트 입니다. */
	$(".searchWrapper").find("#stopBtn").click(function(){
		$(".searchWrapper").find("#startBtn").show();
		Public.StopEvent();
		$(this).hide();
	});

	/* 접수중 다른이벤트 무시 입니다. */
	$(".searchWrapper").find("#passEvt").change(function(){
		if($(this).is(":checked")){
			$(".searchWrapper").find("input.view").each(function(){
				$(this).prop("disabled", "disabled");
			});
		}else{
			$(".searchWrapper").find("input.view").each(function(){
				$(this).prop("disabled", "");
			});
		}
	});

})();