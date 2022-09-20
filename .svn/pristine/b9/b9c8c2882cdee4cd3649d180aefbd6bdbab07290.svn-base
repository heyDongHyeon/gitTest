(function(){

	$(".searchWrapper").find("#settingTable").find("tr").find("td").click(function(){
		//$(this).prev().click();
		//alert($(this).attr('mgrseq'));

		var evtMgrNo = $(this).closest("tr").attr('evtno');

		if(evtMgrNo !== undefined){
			_common.callAjax("/monitor/getSoundItem.json", {'evtMgrNo': evtMgrNo}, function(json) {
				$.each(json.result, function(key, value){
					if (value != null){
						if ($('.searchWrapper').find('#searchResult').find('#'+key).length != 0){
							if(key == "evtMgrNo" || key == "closeCd"){
								$('.searchWrapper').find('#searchResult').find('#'+key).val(value);
							} else {
								if(key == "recvDat") value = Date.prototype.formatYMDHMS(value);
								$('.searchWrapper').find('#searchResult').find('#'+key).text(value);
							}
						}
					}
				});
			});
		}
	});

	$(".searchWrapper").find("#btn_save").click(function(){
		//alert('btn_save');
		var evtMgrNo = $(".searchWrapper").find('#searchResult').find("#evtMgrNo").val();
		var closeCd = $(".searchWrapper").find("#closeCd").val();
		var selectCloseCd = $(".searchWrapper").find("#selectCloseCd").val();
		//alert(closeCd);

		if( !_common.utils.isEmpty(evtMgrNo) && _common.utils.isEmpty(closeCd) ){
			var _param = {};
			_param['mgrNo'] = evtMgrNo;
			_param['closeCd'] = selectCloseCd;

			/*console.log(_param);

			return false;*/

			_common.callAjax("/monitor/editMasterEvt.json", _param, function(json) {
				if(json.result){
					alert('저장되었습니다.');
				}
			});
		} else if( !_common.utils.isEmpty(evtMgrNo) && !_common.utils.isEmpty(closeCd) ) {
			alert('이미 종료된 이벤트입니다.');
		} else {
			alert('선택된 이벤트가 없습니다.');
		}


	});

	$(".searchWrapper").find("#btn_net").click(function(){
		/*xeusLayout.hideOverlayEastPane(0);
		//오른쪽에  createVideoGridPane을 보여 줘야 한다...
		_common.callAjax("/cctv/getNetView.do", null, function(view) {
			$("#center-overlay-east").html(view);
			//180323 이은규
			//선택감시 버튼 클릭 시 항상 패널 너비가 X3으로 고정이지만
			//위 패널갯수 버튼은 이전 메뉴 접근시의 선택값으로 남아있음.
			//선택감시 버튼 클릭 시 3으로 초기화
			xeusCCTV.VIDEO_GRID_COLS = 3;
			xeusCCTV.createVideoGridPane();

			xeusCCTV.VIDEO_GRID_COLS = 3;
			xeusCCTV.createNetPane();

			xeusCCTV.resizeNetPane();
			xeusCCTV.setCloseEventInNetPane();

			var _param = {};
			_param['srid'] = '4326';
			_param['lon'] = '127.12849795';
			_param['lat'] = '35.94325250';
			//_param['gid'] = '6';

			xeusCCTV.startNetMornitoring(_param);

		});
		//사이즈는 CCTV갯수 만큼 자동 계산됨.
		xeusLayout.showOverlayEastPane(ANI_DELAY,function(){
		});*/

	});

})();