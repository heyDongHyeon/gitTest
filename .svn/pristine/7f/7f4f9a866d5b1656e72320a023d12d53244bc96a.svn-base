var $tab = $('<button class="tab blueBtn"><span class="title"></span><span class="ui-icon ui-icon-close" role="presentation">Remove Tab</span></button>');
(function(){
/*
	$("#tabs").find(".tab").on("click", function(){
		var targetView = $(this).attr("target");
		parentView = targetView;
		$("#layout-center").find(".viewWrap").hide();
		$("#layout-center").find("#" + targetView).show();
	});*/

	$("#tabs").find(".tab").find(".ui-icon-close").on("click", function(){
		var targetView = $(this).attr("target");
		$("#" + targetView).html("");
		$(this).parent().hide();

		$("#tabs").find(".tab").each(function(){
			if($(this).is(":visible")){
				$(this).click();
				//break;
			}
		});
	});
})();