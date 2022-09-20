(function(){

	$(".searchWrapper").find("#settingTable").find("tr").find("td").click(function(){
		//$(this).prev().click();
		//alert($(this).attr('mgrseq'));

		var evtMgrNo = $(this).closest("tr").attr('evtno');

		if(evtMgrNo !== undefined){
			_common.callAjax("/monitor/getBellItem.json", {'evtMgrNo': evtMgrNo}, function(json) {
				$.each(json.result, function(key, value){
					if (value != null){
						if ($('.searchWrapper').find('#searchResult').find('#'+key).length != 0){
							if(key == "evtMgrNo" || key == "closeCd"){
								$('.searchWrapper').find('#searchResult').find('#'+key).val(value);
								if(key == "closeCd"){
									$('.searchWrapper').find('#selectCloseCd').val(value).prop('selected', true);
									if(value != null) $(".searchWrapper").find("#btn_save").hide();
								}
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

			console.log(_param);

			return false;

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
		alert('btn_net');
	});

})();