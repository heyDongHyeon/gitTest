/**
 * 페이징 이벤트를 처리합니다.
 * 페이지를 나눌 기준 수와 총 갯수로 아이콘을 셀렉터 내부에 생성하며, 현재 오프셋을 이용하여 다음 필요 갯수를 계산합니다.
 * 그리고 바인딩 이벤트를 전달받아 클릭시 해당 메소드를 실행합니다.
 *
 * 필수) current - 기준 페이징 수 (number)
 * 필수) max - 총 카운트수 (number)
 * 필수) nowOffset - 현재 오프셋 (number)
 * 필수) bindEvent - 바인딩 이벤트 (function)
 *
 * @author 이주영
 */
$.fn.paging = function(_OBJECT){

	if(!_common.utils.validObject(_OBJECT.current, "number")){
		console.warn(">> 필수 파라미터 이상) current - 기준 페이징 수 (number)");
		return false;
	}else if(!_common.utils.validObject(_OBJECT.max, "number")){
		console.warn(">> 필수 파라미터 이상) max - 총 카운트수 (number)");
		return false;
	}else if(!_common.utils.validObject(_OBJECT.nowOffset, "number")){
		console.warn(">> 필수 파라미터 이상) nowOffset - 현재 오프셋 (number)");
		return false;
	}else if(!_common.utils.validObject(_OBJECT.bindEvent, "function")){
		console.warn(">> 필수 파라미터 이상) bindEvent - 바인딩 이벤트 (function)");
		return false;
	};

	var pageLength = Math.ceil(_OBJECT.max / _OBJECT.current);
	var nowPage = (Math.ceil(_OBJECT.nowOffset / _OBJECT.current)) + 1;

	if(typeof pageLength != "number"){
		console.warn(">> 페이지 수 계산 불가 - Math.ceil(_OBJECT.max / _OBJECT.current)");
		return false;
	}

	this.children().remove();
	this.append("<img class='prev' src='../res/img/form/page_left.png' alt='이전으로' title='이전으로' />");
	$(".prev").on("click", function(){
		if(!$(".paging_wrap").find("button.active").prev().is("img")){
			$(".paging_wrap").find("button.active").prev().click();
		}
	});

/***********************************************************************/
		var left = 0;
		for(var i=(nowPage - 4); i<nowPage; i++){
			if(i > -1){
				left++;
				this.append("<button class='pageBtn active' offset='" + (i*_OBJECT.current) + "'>" + (i+1) + "</button>");
			}
		}

		var right = 7 - left;
		for(var i=nowPage; i<(nowPage + right); i++){
			if(i < pageLength){
				this.append("<button class='pageBtn' offset='" + (i*_OBJECT.current) + "'>" + (i+1) + "</button>");
			}
		}

		if(this.find(".pageBtn").length < 7){
			var length = (7 - this.find(".pageBtn").length);
			for(var i=0; i<length; i++){
				var number = Number($(".pageBtn").eq(0).text()) - 1;
				var offset = Number($(".pageBtn").eq(0).attr("offset")) - _OBJECT.current;
				if(number > 0){
					$(".prev").after("<button class='pageBtn' offset='" + offset + "'>" + number + "</button>");
				}
			}
		}

		this.find(".pageBtn").each(function(i, e){
			$(e).on("click", function(){
				_OBJECT.bindEvent(Number($(e).attr("offset")));
			});
			if($(e).attr("offset") == _OBJECT.nowOffset){
				$(e).addClass("active");
			}else{
				$(e).removeClass("active");
			}
		});
/***********************************************************************/

	this.append("<img class='next' src='../res/img/form/page_right.png' alt='다음으로' title='다음으로' />");
	$(".next").on("click", function(){
		if(!$(".paging_wrap").find("button.active").next().is("img")){
			$(".paging_wrap").find("button.active").next().click();
		}
	});
	this.show();

	return this;
};