/**
 * 장비관리(NMS) 메뉴의 점검 등록 관련 이벤트 입니다.
 */

var NDPS_OBJ_LIST = {}; //NDPS 데이터 전역 변수 - 목록 표출 및 차트에서 사용됨.

(function(){
//	xeusLayout.EAST = 0;
//	$("#" + parentView).find("#center-overlay-east").html('');
	xeusLayout.hideOverlayEastPane(ANI_DELAY);
	var _param = {};

	_param["offset"] = 0;
	_param["limit"] = 10;

	callList(0, _param);

	var hintTag = '<p class="send_hint_msg">* 지도 화면 왼쪽 검색 화면에서 검색하여 체크박스를 선택해주세요.</p>';
	/* 날짜UI */
	$("#" + parentView).find(".searchWrapper").find(".datePicker").datepicker("destroy").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yymmdd",
		showButtonPanel: true,
		beforeShowDay: $.datepicker.noBefore
	});
	/* 범례 탭 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("button.tab").eq(0).click(function(){
		$("#" + parentView).find("#btn-infra-view").click();
	});

	/* 명칭 엔터키 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#objName").keyup(function(e){
        if(e.which == 13){
            $("#" + parentView).find(".searchWrapper").find("#searchBtn").click();
        }
    });


	/* 검색 버튼 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#searchBtn").click(function(){

		var _param = _common.utils.collectSendData("#" + parentView + " .searchWrapper #searchTable");

		_param["offset"] = 0;
		_param["limit"] = 10;
		callList(0, _param);
	});

	/* DatePicker 생성 이벤트입니다. */
	$("#" + parentView).find(".searchWrapper").find(".datePicker").datepicker("destroy").datepicker({
		changeMonth: true,
        changeYear: true,
        dateFormat: "yymmdd",
        showButtonPanel: true,
        beforeShowDay: $.datepicker.noBefore
	});

	/**
	 * 전송 이력 상세 정보 조회
	 *
	 * @param key
	 * @returns
	 */
	function callList(offset, _param){
		if ( _param == null ) _param = {};
		_param["offset"] = offset;
		_param["limit"] = 10;

		_common.callAjax("/log/getHistList.json", _param, function(json) {

			$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").html("");
			$("#" + parentView).find('#offset').val(json.map.offset);
			$("#" + parentView).find('#max').val(json.count);
			
			var list = json.result;
			$("#" + parentView).find('#max').text(list.length);

			if(list.length > 0){

				for(var i=0; i<list.length; i++){
					var mgrSeq = list[i].mgrSeq; //관리번호
					var recvId = list[i].recvId; //대상
					var recvNum = list[i].recvNum; //대상번호
					var sendTyp = list[i].sendTyp; // 대상 구분
					var sendDt = new Date().formatYMDHMS(list[i].sendDt.substring(0, 14)); //일자

					var $tr = $("<tr class='tCenter' k='" + mgrSeq + "'></tr>");
					if ( sendTyp == 1 || sendTyp == '1') {
						sendTyp = '음성 방송';
					} else {
						sendTyp = 'SMS 발송';
					}
					//목록에 있을 시 체크 상태로 표출한다.
					$tr.append("<td width='80px'>" + sendTyp + "</td>");
					$tr.append("<td width='100px'>" + recvId + "</td>");
					$tr.append("<td width='100px'>" + sendDt + "</td>");
					var $location = $("<td width='40px' class='tCenter'></td>");
					var $btn = $("<button class='detailBtn' key='"+mgrSeq+"'>").css({
						"cursor" : "pointer"
					}).click(function(){
						$("#" + parentView).find('.detailWrapper').show();
						callView($(this).attr('key'));

					});

					$location.append($btn);
					$tr.append($location);
					$("#" + parentView).find(".searchWrapper").find("#resultList table").append($tr);
				}



			}
			$("#" + parentView).find(".paging_wrap").html('');
			$("#" + parentView).find(".paging_wrap").paging({
				current	  : 10,
				max  	  : Number($("#max").val()),
				nowOffset : Number($("#offset").val()),
				bindEvent : callList
			});

		});
	}

	/**
	 * 전송 이력 상세 정보 조회
	 *
	 * @param key
	 * @returns
	 */
	function callView(key){

		_common.callAjax('/log/getHistItem.json', {mgrSeq : key}, function(json) {
			console.log(json);
			//xeusLayout.EAST = 400;
			var str = '';
			var sendTyp = json.result.sendTyp;
			var sendDt = new Date().formatYMDHMS(json.result.sendDt.substring(0, 14)); //일자
			if ( sendTyp == 1 || sendTyp == '1') {
				sendTyp = '음성 방송';
			} else {
				sendTyp = 'SMS 발송';
			}

			str +='<tr>';
			str +='<th>대상</th><td>'+json.result.recvId+'</td>';
			str +='<th>대상번호</th><td>'+json.result.recvNum+'</td>';
			str +='</tr>';
			str +='<tr>';
			str +='<th>구분</th><td>'+sendTyp+'</td>';
			str +='<th>발신일자</th><td>'+sendDt+'</td>';
			str +='</tr>';
			str +='<tr>';
			str +='<th colspan="1">내용</th><td colspan="3"><textarea style="resize:none; width:100%; height:100px; border:none; outline: none;" readonly>'+json.result.sendMsg+'</textarea></td>';
			str +='</tr>';

			$("#" + parentView).find('.detailWrapper table').html(str);
			//xeusLayout.showOverlayEastPane(delay, function() {
				//$("#" + parentView).find("#center-overlay-east").html(view);

				//$("#" + parentView).find(".btnDiv").removeClass("hidden");

				//$("#" + parentView).find('#vectorInfo').bPopup().close();
				//$("#" + parentView).find('.bPopup').remove();

			//});


		});
	}

})();