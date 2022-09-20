/**
 * <pre>
 * 관심영역 이벤트 입니다.
 * </pre>
 *
 * @author 이주영
 */
(function(){
	/* 관심영역 추가 */
	$(document).on("click", "#fav-add", function(){
		var xy = xeusLayout.mapService.getMap().getView().getCenter();
		var _param = {
				"title" : $("#fav-name").val(),
				"x" : xy[0],
				"y" : xy[1],
				"zoom" : xeusLayout.mapService.getMap().getView().getZoom()
		}
		confirm("추가하시겠습니까?", function(){
			_common.callAjax("/map/addFav.json", _param, function(rst){
				if(rst.result){
					_common.callAjax("/map/getFavList.json", null, function(json){
						if(json.result.length == 0){
							var $div = $('<div align="center" style="margin-top: 60px;">관심영역이 존재하지 않습니다.</div>');

							//$("#fav-content").html($div);
							$('.fav-target').each(function(){
								$(this).find("#fav-content").html($div);
							});
						}else{
							/*$("#fav-content").html("");
							for(var i=0; i<json.result.length; i++){
								var $button = $("<button class='fav-del'>삭제</button>").attr("k", json.result[i].mgrSeq);
								var $div = $("<div class='fav-list'></div>");
								$div.append("<span>" + json.result[i].title + "</span>");
								$div.append($button);
								$div.attr("k", json.result[i].mgrSeq);
								$div.attr("x", json.result[i].x);
								$div.attr("y", json.result[i].y);
								$div.attr("zoom", json.result[i].zoom);

								$("#fav-content").append($div);
							}*/

							$('.fav-target').each(function(){
								$(this).find("#fav-content").html("");
								for(var i=0; i<json.result.length; i++){
									var $button = $("<button class='fav-del'>삭제</button>").attr("k", json.result[i].mgrSeq);
									var $div = $("<div class='fav-list'></div>");
									$div.append("<span>" + json.result[i].title + "</span>");
									$div.append($button);
									$div.attr("k", json.result[i].mgrSeq);
									$div.attr("x", json.result[i].x);
									$div.attr("y", json.result[i].y);
									$div.attr("zoom", json.result[i].zoom);

									$(this).find("#fav-content").append($div);
									$(this).find("#fav-name").val("");
								}
							});

							alert("저장되었습니다.");
						}
					}, false);
				}
			}, false);
		});
	});

	/* 관심영역 삭제 */
	$(document).on("click", ".fav-del", function(){
		var param = { "k" : $(this).attr("k") };
		confirm("삭제하시겠습니까?", function(){
			_common.callAjax("/map/delFav.json", param, function(rst){
				if(rst.result){
					_common.callAjax("/map/getFavList.json", null, function(json){
						if(json.result.length == 0){
							var $div = $('<div align="center" style="margin-top: 60px;">관심영역이 존재하지 않습니다.</div>');

							//$("#fav-content").html($div);
							//$("#fav-content").html($div);
							$('.fav-target').each(function(){
								$(this).find("#fav-content").html($div);
							});
						}else{
							/*$("#fav-content").html("");
							for(var i=0; i<json.result.length; i++){
								var $button = $("<button class='fav-del'>삭제</button>").attr("k", json.result[i].mgrSeq);
								var $div = $("<div class='fav-list'></div>");
								$div.append("<span>" + json.result[i].title + "</span>");
								$div.append($button);
								$div.attr("k", json.result[i].mgrSeq);
								$div.attr("x", json.result[i].x);
								$div.attr("y", json.result[i].y);
								$div.attr("zoom", json.result[i].zoom);

								$("#fav-content").append($div);
							}*/

							$('.fav-target').each(function(){
								$(this).find("#fav-content").html("");
								for(var i=0; i<json.result.length; i++){
									var $button = $("<button class='fav-del'>삭제</button>").attr("k", json.result[i].mgrSeq);
									var $div = $("<div class='fav-list'></div>");
									$div.append("<span>" + json.result[i].title + "</span>");
									$div.append($button);
									$div.attr("k", json.result[i].mgrSeq);
									$div.attr("x", json.result[i].x);
									$div.attr("y", json.result[i].y);
									$div.attr("zoom", json.result[i].zoom);

									$(this).find("#fav-content").append($div);
									$(this).find("#fav-name").val("");
								}
							});

							alert("삭제되었습니다.");
						}
					}, false);
				}
			}, false);
		});
	});

	/* 관심영역 이동 */
	$(document).on("click", ".fav-list", function(){
		var x = Number($(this).attr("x"));
		var y = Number($(this).attr("y"));
		var zoom = Number($(this).attr("zoom"));

		xeusLayout.mapService.getMap().getView().setCenter([x, y]);
		xeusLayout.mapService.getMap().getView().setZoom(zoom);
	});
})();