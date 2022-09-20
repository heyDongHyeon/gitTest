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

function callView(offset){
	if(offset == null) offset = 0;
	var _param = {"limit" : 10, "offset" : offset};

	var val = $("#" + parentView).find("#searchInput").val();
	if(val != "" && val != null) _param["usrId"] = val;

	_common.callAjax("/access/getAccessView.do", _param, function(view){
		$("#" + parentView).find(".bpopup").remove();
		$("#" + parentView).find("#overlay-west-contents").html(view);
	});
}

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
