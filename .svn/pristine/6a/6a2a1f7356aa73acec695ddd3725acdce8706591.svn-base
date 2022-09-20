/**
 * 장비관리(NMS) 상태모니터링 메뉴의 시뮬레이터 이벤트입니다.
 */
(function(){

	/* 명칭 엔터키 이벤트 입니다. */
	//$('#wrap').find(".searchWrapper").find('.menu1').find(".dd").keyup(function(e){

	//$('#wrap').find(".searchWrapper").find('.menu1').find("#regBtn").click(function(e){
	$(document).on("click", "#regBtn", function(){

		var $thisBtn = $(this);

		var saveChk = true;

		var _statParam = _common.utils.collectSendData(".menu1");

		if(_statParam['mgrNo'] == ""){
			alert('장비번호를 선택하여 주십시오.');

			saveChk = false;
			return false;
		}

		if(_statParam['stateCd'] == ""){
			alert('상태코드를 선택하여 주십시오.');

			saveChk = false;
			return false;
		}

		if (saveChk){
			_statParam['recvDat'] = Date.prototype.getYMDHMS();

			_common.callAjax("/nms/addStatus.json", _statParam, function(json) {

				if(json.result){
					alert('저장되었습니다.');
					setTimeout("location.reload()", 1000);
					//$thisBtn.closest("tbody").find('#mgrNo').val('').prop("selected", true);
					//$thisBtn.closest("tbody").find('#stateCd').val('').prop("selected", true);
				}

			});
		}
	});

	$(document).on("click", ".editBtn", function(){

		var mgrNo = $(this).attr('mgrno');
		//var stateCd = $(this).closest("tr").find('#stateCd').val();
		var stateCd = $(this).parent().parent().find("#stateCd").val();

		var _statParam = {};
		_statParam['mgrNo'] = mgrNo;
		_statParam['stateCd'] = stateCd;
		_statParam['recvDat'] = Date.prototype.getYMDHMS();

		_common.callAjax("/nms/editStatus.json", _statParam, function(json) {

			if(json.result){
				alert('수정되었습니다.');
				setTimeout("location.reload()", 1000);
			}

		});
	});

	$(document).on("click", ".deleteBtn", function(){

		var mgrNo = $(this).attr('mgrno');

		confirm('삭제하시겠습니까?', function(){

			_common.callAjax("/nms/delStatus.json", {'mgrNo' : mgrNo}, function(json) {

				console.log(json);
				if(json.result){
					alert('삭제되었습니다.');
					setTimeout("location.reload()", 1000);
				}

			});
		});

	});

	$(document).on("click", ".initBtn", function(){

		var mgrNo = $(this).attr('mgrno');

		confirm('초기화하시겠습니까?', function(){

			_common.callAjax("/cctv/initCctv.json", null, function(json) {

				if(json.result){
					alert('초기화되었습니다.');
					setTimeout("location.reload()", 1000);
				}

			});
		});

	});

})();