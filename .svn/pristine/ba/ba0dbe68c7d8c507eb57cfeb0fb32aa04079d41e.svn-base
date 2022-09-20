/**
 * 장비관리(NMS) 메뉴의 점검 등록 관련 이벤트 입니다.
 */

var NDPS_OBJ_LIST = {}; //NDPS 데이터 전역 변수 - 목록 표출 및 차트에서 사용됨.

(function(){

	var hintTag = '<p class="send_hint_msg">* 지도 화면 왼쪽 검색 화면에서 검색하여 체크박스를 선택해주세요.</p>';

	//전송 폼을 생성한다.
	//켜짐과 동시에.
	_common.callLoadingAjax('/ndpsSend/getSmsSendView.do', {}, function(view) {

		xeusLayout.EAST = 400;

		xeusLayout.showOverlayEastPane(delay, function() {

			$("#" + parentView).find("#center-overlay-east").html(view);

			$("#" + parentView).find(".btnDiv").removeClass("hidden");

			$("#" + parentView).find('#vectorInfo').bPopup().close();
			$("#" + parentView).find('.bPopup').remove();

			$("#send_toggle_date .date_util").datepicker({
	            format: "yyyy-mm-dd",
	            language: "kr"
			});

			$('input[name=send_at]').on('click', function(){
				var typ = $(this).val();

				if ( typ === '0' ) {
					$('#send_toggle_date').hide();
				} else {
					$('#send_toggle_date').show();
					$('.date_util').val(new Date().getYMD(true));
					$('#send_at_hh').val(new Date().getHour());
					$('#send_at_mm').val(new Date().getMin());
				}
			});

			$('#sendBtn').on('click', function(){
				var recInfo = '';//이름,번호;
				$('#send_select_list .send_select_item').each(function(){
					var nm = $(this).attr('nm');
					var telno =  $(this).attr('telno');
					if(telno==null || telno==undefined || telno == ''){
						telno='undefined';
					}
					recInfo += nm+','+telno+';';
				});
				var cntcCn = $('#send_cntc_cn').val();//메세지내용
				var mssageTy = $('input[name=send_ty]:checked').val();//메세지 타입
				var resveAt =  $('input[name=send_at]:checked').val();//예약 설정
				var tranDate;
				if(resveAt==0){
					transDate=null;
				}else{
					tranDate= $('#send_at_ymd').val().replace(/-/gi, '')
					+$('#send_at_hh').val()+$('#send_at_mm').val();//예약 날짜
					if((tranDate-getTimeStamp())<0){
						alert('이미 지나간 시간입니다. 현재시간 이후로 설정해주세요.');
						return;
					}
				}
				var param = {
						recInfo : recInfo,
						cntcCn : cntcCn,
						mssageTy : mssageTy,
						resveAt : resveAt,
						tranDate : tranDate
				};
				
				confirm("SMS발송을 하시겠습니까?", function(){
					_common.callAjax('/ndpsSend/sendAllSMS.json', param, function(json) {
						if(json.result){
							alert('전송했습니다.');
						}else{
							alert('전송 실패했습니다.');
						}
					},false);
				});
				
			});
			
			$('#initBtn').on('click', function(){
				console.log('init');
				$('#send_list_all_remove').trigger("click");
				$('#send_cntc_cn').val('');
				$('.send_group #at1').prop('checked',true);
				$('#send_toggle_date').hide();
			});
			
			
		});


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
		if(_param['objName']==''&&_param['objNum']==''){
			alert('사용자 또는 연락처를 입력해주세요.');
			return;
		}
		_common.callLoadingAjax("/ndpsSend/getSMSInfo.json", _param, function(json) {

			$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").html("");
			for(var key in json){
				var list = json[key];
				if(list.length > 0){
					for(var i=0; i<list.length; i++){

						var innb = list[i].innb; //주소록 관리번호
						var nm = list[i].nm; //성명
						var telno = list[i].telno; //사내 전화번호
						var moblphonno = list[i].moblphonno; // 모바일 번호
						var faxno = list[i].faxno;//팩스 번호
						var grp = list[i].grp;//팩스 번호
						var checked = '';//팩스 번호

						if ( telno === undefined ) telno ='';

						$('#send_select_list .send_select_item').each(function(){
							var key = $(this).attr('id').replace('send_select_', '');

							if ( key === innb ) checked = 'checked';
						});

						var $tr = $("<tr class='tCenter' k='" + innb + "'></tr>");

						//목록에 있을 시 체크 상태로 표출한다.
						$tr.append("<td width='20px'><input class='send_chk_box' type='checkbox' id='send_id_"+innb+"' nm='"+nm+"' telno='"+moblphonno+"' "+checked+"></td>");
						$tr.append("<td width='80px'>" + grp + "</td>");
						$tr.append("<td width='80px'>" + nm + "</td>");
						$tr.append("<td width='100px'>" + moblphonno + "</td>");
						$("#" + parentView).find(".searchWrapper").find("#resultList").find("#send_table").append($tr);
					}

				}
			}

			//리스트 전체 삭제
			$('#send_list_all_remove').on('click', function(){
				$('#send_select_list').find('.send_list_remove').each(function(){
					$(this).click();
				});
			});

			//체크 박스 선택 시
			$('.send_chk_box').on('click', function(){
				var chk = $(this).prop('checked');
				var key = $(this).attr('id').replace('send_id_', '');
				var nm = $(this).attr('nm');
				var telno = $(this).attr('telno');
				if ( !chk ) {
					$('#send_select_list').find('#send_select_'+key).remove();

					if ( $('#send_select_list .send_select_item').length === 0 ) {

						$('#send_select_list').html(hintTag);
					};
				} else {
					$('#send_select_list p').remove();
					var str = '<span id="send_select_'+key+'" nm="'+nm+'" telno="'+telno+'" class="send_select_item"><span>'+nm+'</span><span class="send_list_remove" key="'+key+'">x</span></span>';
					if ( $('#send_select_list').find('#send_select_'+key).length === 0 ) {
						$('#send_select_list').append(str);
					}

					$('#send_select_'+key).find('.send_list_remove').on('click', function(){
						var kay =$(this).attr('key');
						$('#send_id_'+key).prop('checked', false);
						$('#send_select_'+key).remove();

						if ( $('#send_select_list .send_select_item').length === 0 ) {

							$('#send_select_list').html(hintTag);
						};
					});
				}
			});

			//체크 박스 전체선택 시
			$('#send_all_chk').on('click', function(){
				var chk = $(this).prop('checked');
				$('#send_select_list p').remove();
				$('.send_chk_box').each(function(){
					var key = $(this).attr('id').replace('send_id_', '');
					var nm = $(this).attr('nm');
					var telno = $(this).attr('telno');

					if ( !chk ) {		

						$('.send_chk_box').prop('checked', false);
						$('#send_select_list').find('#send_select_'+key).remove();

					} else {

						$('.send_chk_box').prop('checked', true);
						var str = '<span id="send_select_'+key+'" nm="'+nm+'" telno="'+telno+'" class="send_select_item"><span>'+nm+'</span><span class="send_list_remove" key="'+key+'">x</span></span>';
						if ( $('#send_select_list').find('#send_select_'+key).length === 0  ) {
							$('#send_select_list').append(str);
						}


					}
				});

				$('.send_list_remove').on('click', function(){
					var key =$(this).attr('key');
					$('#send_id_'+key).prop('checked', false);
					$('#send_select_'+key).remove();

					if ( $('#send_select_list .send_select_item').length === 0 ) {

						$('#send_select_list').html(hintTag);
					};
				});
			});

/*			 위치 버튼 이벤트 입니다.
			$("#" + parentView).find(".searchWrapper").find("#resultList").find(".locBtn").click(function(){
				var juso = $(this).parent().parent().attr("k");	//tr의 k
//				console.log("juso = "+juso);
				var data = $(this).parent().parent().attr("p");	//tr의 k
//				console.log("data = "+data);
				var xyArr=[];
				xyArr.dsc=data;
				var xy = Spatial.convertAddrToXY(juso);
				xyArr.lon=xy[0];
				xyArr.lat=xy[1];
				var epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();	//왜갑자기4326(위도를) 5186으로 바꿩ㅈ
				var mainCenter = ol.proj.transform([xy[0], xy[1]], 'EPSG:4326', epsg);	//바꾼다..

				xeusLayout.mapService.moveToAnimation(0, [mainCenter[0], mainCenter[1]]);	//지도 위치로 가지는 것.
				xeusLayout.mapService.createNdmsPoint(xyArr);	//지도 위치로 가지는 것.
			});*/
		});
	});

	/* DatePicker 생성 이벤트입니다. */
	$("#" + parentView).find(".searchWrapper").find(".datePicker").datepicker("destroy").datepicker({
		changeMonth: true,
        changeYear: true,
        dateFormat: "yymmdd",
        showButtonPanel: true,
        beforeShowDay: $.datepicker.noBefore
	});
	/* 지도에서 위치설정 이벤트 입니다.
	$("#" + parentView).find(".searchWrapper").find("#mapClickBtn").click(function(){
	    $("body").css("cursor", "crosshair");
	    $("#" + parentView).find(".selectCancel").show(500);
	    xeusLayout.mapService.getMap().on('click', Public.NMS.Infra.Start);
	    Public.StopEvent = function(){
	        $("body").css("cursor", "default").off("click");
	        $("#" + parentView).find(".selectCancel").hide(500);
	        xeusLayout.mapService.getMap().un('click', Public.NMS.Infra.Start);
	    }
	});
	$("#" + parentView).find(".searchWrapper").find(".selectCancel").click(function(){
	    Public.StopEvent();
	});
*/


	/* 시설물 신규추가 버튼입니다..
	$("#" + parentView).find(".searchWrapper").find("#fclAddBtn").click(function(e){

		$("#" + parentView).append(_html);

		$("#" + parentView).find('#vectorInfo').bPopup({
			appendTo: $('#'+parentView),
			onOpen: function() {
				$("#" + parentView).find('#vectorInfo').find('#closeBtn').click(function(){
					$("#" + parentView).find('#vectorInfo').bPopup().close();
					$("#" + parentView).find('.bPopup').remove();
				});

				$("#" + parentView).find('#vectorInfo').find('#saveBtn').click(function(){
					var type = $("#" + parentView).find('#vectorInfo').find("select").val();

					var url = "";s
					if(type == "CTV") url = "/cctv/getUnregCctvView.do";
					if(type == "AWS") url = "/aws/getUnregAwsView.do";
					if(type == "VOI") url = "/voice/getUnregVoiceView.do";
					if(type == "DIS") url = "/disbord/getUnregDisbordView.do";

					_common.callAjax(url, {}, function(view) {
						xeusLayout.showOverlayEastPane(delay, function() {
							$("#" + parentView).find("#center-overlay-east").html(view);

							$("#" + parentView).find(".btnDiv").removeClass("hidden");
							$("#" + parentView).find("#regTable").find(".date").datepicker({
					            format: "yyyy/mm/dd",
					            language: "kr"
							});

							$("#" + parentView).find('#vectorInfo').bPopup().close();
							$("#" + parentView).find('.bPopup').remove();
						});
					});
				});
			},
			onClose: function() {
				$("#" + parentView).find('.bPopup').remove();
			}

		});
	});*/
})();
function getTimeStamp() {
  var d = new Date();
  var s =
    leadingZeros(d.getFullYear(), 4) +
    leadingZeros(d.getMonth() + 1, 2) +
    leadingZeros(d.getDate(), 2) +

    leadingZeros(d.getHours(), 2) +
    leadingZeros(d.getMinutes(), 2);
   

  return s;
}

function leadingZeros(n, digits) {
  var zero = '';
  n = n.toString();

  if (n.length < digits) {
    for (i = 0; i < digits - n.length; i++)
      zero += '0';
  }
  return zero + n;
}
