/**
 * 이력 조회 이벤트 입니다.<br>
 */
//(function(){

	$(document).ready(function(){

		/*페이징 처리*/
		$(".paging_wrap").paging({
			current	  : 10,
			max  	  : Number($("#max").val()),
			nowOffset : Number($("#offset").val()),
			bindEvent : callView
		});

		/**
		 * 180419 이은규
		 * DatePicker div가 사라지지 않아 다른페이지를 다녀올 시 문제가 생김.
		 * 강제로 div를 삭제
		 */
		$('#ui-datepicker-div').remove();

		/* DatePicker 생성 이벤트입니다. */
		$("#wrap").find(".datePicker").datepicker("destroy").datepicker({
			changeMonth: true,
		    changeYear: true,
		    dateFormat: "yymmdd",
		    showButtonPanel: true,
		    beforeShowDay: $.datepicker.noBefore
		});


	});



	/* 탭 클릭 이벤트 입니다. */
	$("#wrap").find("button.logTab").click(function(){
		if($(this).attr("active") != "active"){
			$("#wrap").find("button.logTab").removeAttr("active");
			$(this).attr("active", "active");

			var url = $(this).attr("url");
			if(url != null){
				var _param = {};
				_param['limit'] = 10;
				_param['offset'] = 0;
				_common.callAjax(url, _param, function(view) {
					$("#overlay-west-contents").html('');
					$("#overlay-west-contents").html(view);
				});
			}
	        //$(".searchWrapper").find("#searchBtn").click();
		}
    });

	function callView(offset,_param){
		if(offset == null) offset = 0;
		if(_param === undefined){
			_param = {};
			for(var key in schObj) {
				if (schObj[key] != ""){
					_param[key] = schObj[key];
				}
			}
		}
		_param['limit'] = 10;
		_param['offset'] = offset;

		var _url = '/log/getAccessView.do';
		$('.logTab').each(function() {
			var chkActive = $(this).attr("active");
			if (chkActive !== undefined && chkActive == "active"){
				_url = $(this).attr("url");
			}
		});


		/*console.log(_url);
		console.log(_param);*/
		_common.callAjax(_url, _param, function(view){
			$("#overlay-west-contents").html(view);
		});
	}

	/* 검색 입력항목 엔터키 입력 이벤트 입니다.*/
	/*$("#wrap").find(".sendData").keyup(function(e){
	    if(e.which == 13){
	    	$("#wrap").find("#searchBtn").click();
	    }
	});*/

	/* 검색 버튼 이벤트 입니다.*/
	$("#wrap").find("#searchBtn").click(function(){
		var _param = _common.utils.collectSendData("#search");

		if(_param['startDat'] != '') _param['startDat'] = _param['startDat'] + '000000';
		if(_param['endDat'] != '') _param['endDat'] = _param['endDat'] + '235959';

		callView(0, _param);

	});

	/* 엑셀 버튼 이벤트 입니다.*/
	$("#wrap").find("#excelBtn").click(function(){
		confirm("* 검색결과를 엑셀로 다운로드 하시겠습니까?", function(){
			var max = $('#max').val();
			if ( max == "0"){
				alert("* 검색결과가 존재하지 않습니다.");
			} else{
				var _param = {};
				for(var key in schObj) {
					if (schObj[key] != ""){
						_param[key] = schObj[key];
					}
				}
				_param['limit'] = max;
				_param['offset'] = 0;

				$('.logTab').each(function() {
					var chkActive = $(this).attr("active");
					if (chkActive !== undefined && chkActive == "active"){
					}
				});
				_param['excel'] = 'Access';

				//console.log(_param);

				_common.postForm.submit("/log/getLogAsExcel.do", _param);
			}
		});
	});

	/* SMS 상세보기 이벤트 입니다. */
	$("#content").find(".smsDetailBtn").click(function(){
		var v = $(this).attr("k");

		_common.callAjax("/sms/getSmsHistList.json", {"mgrSeq" : v}, function(json) {
			var prop = json.result[0];

			var _html = '';

			_html += '<div id="info" class="bPopup" style="display: none;"> ';
			_html += '    <div id="title-bar"> ';// style="margin-left: 10px;"
			_html += '        <button id="closeBtn" style="float: right;"><img src="../res/img/close_btn.png"/></button> ';
			_html += '        <p class="title">상세 정보</p> ';
			_html += '    </div> ';
			_html += '    <table class="list"> ';// cellspacing="0" width="100%" style="margin-top:0;"
			_html += '        <thead> ';
			_html += '        <colgroup> ';
			_html += '            <col width="70px" /> ';
			_html += '	          <col width="" /> ';
			_html += '        </colgroup> ';
			_html += '        </thead> ';
			_html += '        <tbody> ';

			for(var k in prop){
				var column = "";
				if(k == "mgrSeq") column = "고유번호";
				if(k == "msgGbn") column = "메세지구분";
				if(k == "rcvId") column = "수신자";
				if(k == "rcvPhone") column = "수신자핸드폰";
				if(k == "conts") column = "문자내용";
				if(k == "sndDat") column = "송신일시";
				if(k == "sndRslt") column = "송신결과";
				if(k == "sndRsltMsg") column = "송신결과메시지";
				if(k != "geometry" && k != "img_path" && k != "popup" && k != "target_field" && k != "sndRsltMsg"){
					_html += '        <tr> ';
					_html += '            <th><label>' + column + '</label></th> ';
					_html += '            <td><label>' + prop[k] + '</label></td> ';
					_html += '        </tr> ';
				}
			}

			_html += '        </tbody> ';
			_html += '    </table> ';
			_html += '</div> ';

			$('#wrap').append(_html);

			$('#info').bPopup({
				onOpen: function() {
					$('#info').find('#closeBtn').click(function(){
						$('#info').bPopup().close();
						$('.bPopup').remove();
					});
				},
				onClose: function() {
					$('.bPopup').remove();
				}
			});
		});
	});

	/* 위치 버튼 이벤트 입니다.
	$(".searchWrapper").find(".locBtn").click(function(){
		var v = $(this).attr("k");
		if(_common.utils.isNullAndEmpty(v)){
			alert("해당 사이트는 위치가 존재하지 않습니다.\n\n대표 CCTV 선택 후 위치 조회가 가능합니다.");
		}else{
			_common.callAjax("/nms/getGeometryLocation.json", {k : v}, function(json) {
				xeusLayout.mapService.moveToAnimation(0, [json.result[0].annoX, json.result[0].annoY]);
			});
		}
	}); */


	/* DatePicker 생성 이벤트입니다.
	$(".searchWrapper").find(".datePicker").datepicker("destroy").datepicker({
	    changeMonth: true,
	    changeYear: true,
	    dateFormat: "yymmdd",
	    showButtonPanel: true,
	    beforeShowDay: $.datepicker.noBefore
	});*/

	/* MonthPicker 생성 이벤트입니다.
	$(".searchWrapper").find(".monthPicker").MonthPicker({
		MonthFormat: "yymm",
		Button: false
	}); */

//})();