/**
 * 장비관리(NMS) 메뉴의 점검 등록 관련 이벤트 입니다.
 */

var NDMS_OBJ_LIST = {}; //NDMS 데이터 전역 변수 - 목록 표출 및 차트에서 사용됨.
var NDMS_PARAM_LIST = {}; //NDMS 데이터 전역 변수 - 목록 표출 및 차트에서 사용됨.

(function(){
/*
	_common.callAjax( "/ndms/getNdmsMngInformView.do" ,{k : null }, function(view) {		//json을 까라로 넣었다. 안넣으면 에러가 뜨기 때문에..
		$("#" + parentView).find("#center-overlay-east").height(
			$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
		).html(view);

		$("#" + parentView).find(".btnDiv").removeClass("hidden");
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
		});

		var arrData = [];
		//var time = NDMS_OBJ_LIST[d][0].dttmfc;
		//var qtyrsrat1 = NDMS_OBJ_LIST[d][0].qtyrsrat1;
		//var objArr = NDMS_OBJ_LIST[emdCd];

		NdmsData.dataView('4', '');
	});

*/
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

	/* 시설물 신규추가 버튼입니다. */
	$("#" + parentView).find(".searchWrapper").find("#fclAddBtn").click(function(e){
		var _html = '';
		_html += '<div id="vectorInfo" class="bPopup" style="display: none;"> ';
		_html += '    <div id="title-bar"> ';// style="margin-left: 10px;"
		_html += '        <button id="closeBtn" style="float: right;"><img src="'+xeusCCTV.ctxPath + '/res/img/close_btn.png"/></button> ';
		_html += '        <p class="title">신규추가 대상 선택</p> ';
		_html += '    </div> ';
		_html += '    <table class="list"> ';// cellspacing="0" width="100%" style="margin-top:0;"
		_html += '        <thead> ';
		_html += '        <colgroup> ';
		_html += '            <col width="110px" /> ';
		_html += '	          <col width="" /> ';
		_html += '        </colgroup> ';
		_html += '        </thead> ';
		_html += '        <tbody> ';

		_html += '        	<tr> ';
		_html += '            	<th><label>대상 시설물</label></th> ';
		_html += '            	<td><select class="wide"><option value="CTV">CCTV</option><option value="AWS">기상관측기기</option><option value="VOI">방송장비</option><option value="DIS">전광판</option></select></td> ';
		_html += '        	</tr> ';
		_html += '        	<tr> ';
		_html += '            	<th colspan="2"><button id="saveBtn">확인</button></th> ';
		_html += '        	</tr> ';

		_html += '        </tbody> ';
		_html += '    </table> ';
		_html += '</div> ';

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
	});
	/* 검색 버튼 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#searchBtn").click(function(){
		var _param = _common.utils.collectSendData("#" + parentView + " .searchWrapper #searchTable");

		var url = "/ndms/realTimeData.json";

		if(_param.key === "1"){
			url = "/ndms/getListByInf119Mstr.json";
			_param["sortCol"] = "rept_dt";
			_param["sortTyp"] = "desc";
		}

		if(_param.key === "")
		{
			alert("구분을 선택해주세요.");
		}
		else if(_param.dat === "")
		{
			alert("날짜를 선택해주세요.");
		}
		else
		{
			_common.callLoadingAjax(url, _param, function(json) {

				$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").html("");
				$("#" + parentView).find(".searchWrapper").find("#searchResult").text("검색결과" + "(" + json.result.length + "개)" );
				for(var key in json){
					var list = json[key];
					$("#" + parentView).find(".searchWrapper").find("#resultHeader").empty();

					var $head = $("<tr>");
					if(_param.key === "7")	//지역별 AWS
					{
						$head.append("<th width='200px'>지역</th>");
						$head.append("<th width='150px'>최근 관측시간</th>");
						$head.append("<th width='70px'>확인</th>");
					}
					else if(_param.key === "5" || _param.key === "8")	//지역별 강우량   강우 수위
					{
						$head.append("<th width='200px'>지역</th>");
						$head.append("<th width='150px'>최근 관측 일시</th>");
						$head.append("<th width='70px'>확인</th>");
					}
					else if(_param.key === "9")	//동네예보 강수확률
					{
						$head.append("<th width='200px'>지역</th>");
						$head.append("<th width='150px'>최근 발표 시각</th>");
						$head.append("<th width='70px'>확인</th>");
					}
					else if(_param.key === "2")	//기상특보
					{
						$head.append("<th width='200px'>내용</th>");
						$head.append("<th width='150px'>발표 시각</th>");
						$head.append("<th width='70px'>확인</th>");
					}
					else if(_param.key === "6")	//댐수위
					{
						$head.append("<th width='200px'>지역</th>");
						$head.append("<th width='150px'>년 월 일 시 분</th>");
						$head.append("<th width='70px'>확인</th>");
					}
					else if(_param.key === "3")	//예비특보
					{
						$head.append("<th width='200px'>예보관</th>");
						$head.append("<th width='150px'>발표 시각</th>");
						$head.append("<th width='70px'>확인</th>");
					}
					else if(_param.key === "4")	//지진
					{
						$head.append("<th width='200px'>지역</th>");
						$head.append("<th width='150px'>발표 시각</th>");
						$head.append("<th width='70px'>확인</th>");
					}
					else if(_param.key === "1")	//119
					{
						$head.append("<th width='200px'>지역</th>");
						$head.append("<th width='150px'>일자</th>");
						$head.append("<th width='70px'>확인</th>");
					}
					$head.append("</tr>");
					$("#" + parentView).find(".searchWrapper").find("#resultHeader").append($head);

					if(list.length > 0)
					{
						NDMS_OBJ_LIST = {};
						NDMS_PARAM_LIST = JSON.parse(JSON.stringify(_param));

						for(var i=0; i<list.length; i++){
							var emdCd=list[i].emdCd;
							var date=list[i].time;

							if(_param.key=='1'){
								emdCd = list[i].dstr_area_nm;
								date = list[i].rept_dt;
							}


							if(_param.key === "6"){
								if(emdCd != "대청" && emdCd != "용담"){
									continue;
								}
							}
							//선택한 지역명이 아니면 date하고 emdCd는 undefined이다.
							//선택한 지역명만 표출시키기 위해서 continue
							if(date==undefined || emdCd==undefined){
								continue;
							}
							var obj=[];
							var str="";
//							var strList="";
							obj=list[i].obj;

							if ( date.indexOf(':') < 1 ) {
								date = new Date().formatYMDHMS(date);
							}

							if ( list[i].objList != undefined ) {
								list[i].objList.lng = list[i].objList._annox;
								list[i].objList.lat = list[i].objList._annoy;

								NDMS_OBJ_LIST[emdCd]=list[i].objList;
							} else {
								list[i].lng = list[i]._annox;
								list[i].lat = list[i]._annoy;

								NDMS_OBJ_LIST[emdCd]=list[i];
							}
//							for(var ind in objList)	//objList로 포문을 돌려고 했으나 안되서 그냥 이렇게 쓴다..
//							{
//								for(var keey in objList[ind])		//객체를 stirng으로 만들기..
//								{
//									strList+=(keey+" : "+objList[ind][keey]+",");
//								}
//								strList+="\n";
//							}

							for(var ke in obj)		//객체를 stirng으로 만들기..
							{
								str+=(ke+" : "+obj[ke]+"\n");
							}
							var prop = {};


							if(_param.key=='1'){
//								prop._annox = list[i]._annox;
//								prop._annoy = list[i]._annoy;
								prop = list[i];
							}

							if ( key == '3' ) {
								emdCd = emdCd + obj.notmseq;
							}
							//댐수위이면
							if(_param.key=='6'){
								var $tr = $("<tr class='tCenter'  k='" + emdCd + "' m='" + list[i].obj.lftbnk + "' p='" + str + "'></tr>");
							}
							else{
								var $tr = $("<tr class='tCenter' k='" + emdCd + "' p='" + str + "'></tr>");
							}




							$tr.append("<td width='200px'>" + emdCd + "</td>");
							$tr.append("<td width='150px'>" + date + "</td>");
							$tr.append("<td width='70px'></button><button class='locBtn'></button><button class='detailBtn' key='"+_param.key+"'></td>");
							$tr.data(prop);
							$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").append($tr);
						}
						/* 위치 버튼 이벤트 입니다. */
						$("#" + parentView).find(".searchWrapper").find("#resultList").find(".locBtn").click(function(){

							if(_param.key=='6'){
								var juso = $(this).parent().parent().attr("m");	//tr의 k
							}else{
								var juso = $(this).parent().parent().attr("k");	//tr의 k
							}

							var lon,lat;
							if(_param.key === "4"){	//지진통보문
								lon=NDMS_OBJ_LIST[juso].longitude;
								lat=NDMS_OBJ_LIST[juso].latitude;
							}
							else if(_param.key === "7"){	//aws
								lon=NDMS_OBJ_LIST[juso][0].cordlon;
								lat=NDMS_OBJ_LIST[juso][0].cordlat;
							}
							else if(_param.key === "3"){	//kma_inform
								lon=127.2891;
								lat=36.4800;
							}else if(_param.key === "1"){
								var prop = $(this).parent().parent().data()

								lon = prop._annox;
								lat = prop._annoy;
							}
							else {	//나머지는 주소로
								var xy = Spatial.convertAddrToXY(juso);

								//해당 주소의 데이터가 없으면, 특수문자 숫자 제거해서 다시한번 주소 데이터 검색
								if(xy=='error'){
									var SpecialRegExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
									var NumberRegExp =/[0-9]/g;
									juso=juso.replace(SpecialRegExp,'').replace(NumberRegExp,'');
									xy=Spatial.convertAddrToXY(juso);
								}
								lon=xy[0];
								lat=xy[1];
							}
							//위도 경도가 숫자일 때만
							if(!isNaN(lon) && !isNaN(lat)){
								var mainCenter=Spatial.convertProjection([lon,lat], "EPSG:4326", "EPSG:5186");
//								xeusLayout.mapService.moveToAnimation(0, [mainCenter[0], mainCenter[1]]);
								_search.moveLocation(mainCenter, "")
							}else{
								if(_param.key == "1"){
									_search.moveMapCenter();
								}else{
									alert('위치정보가 없습니다.');
								}
							}

						});

						/* 상세 버튼 이벤트 입니다. */
						$("#" + parentView).find(".searchWrapper").find("#resultList").find(".detailBtn").click(function(){

							var emdCd = $(this).parent().parent().attr("k");	//tr의 k
							var key = $(this).attr("key");	//tr의 k
							var page = 'Chart';

							//소방 및 통보문.
							if ( Number(key) < 5 ) page = 'Inform'

							_common.callAjax( "/ndms/getNdmsMng"+page+"View.do" ,{k : emdCd, key : key }, function(view) {

								$("#" + parentView).find("#center-overlay-east").height(
									$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
								).html(view);

								xeusLayout.WEST=SEND_BTN_SMS_VIEW_WEST_SIZE;
								xeusLayout.EAST=600;

								$("#" + parentView).find(".btnDiv").removeClass("hidden");
									xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
								});

								//var time = NDMS_OBJ_LIST[d][0].dttmfc;
								//var qtyrsrat1 = NDMS_OBJ_LIST[d][0].qtyrsrat1;

							},false);

							var objArr = NDMS_OBJ_LIST[emdCd];
							if(_param.key === "1"){
								objArr = $(this).parent().parent().data();
							}
							NdmsData.dataView(_param.key, objArr);
						});
					}
				}
			});
		}
	});

	/* 지도에서 위치설정 이벤트 입니다. */
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


	/* DatePicker 생성 이벤트입니다. */
	$("#" + parentView).find(".searchWrapper").find(".datePicker").datepicker("destroy").datepicker({
		changeMonth: true,
        changeYear: true,
        dateFormat: "yymmdd",
        showButtonPanel: true,
        beforeShowDay: $.datepicker.noBefore
	});


	/* DatePicker 생성 이벤트입니다. */
	$("#" + parentView).find("#searchTable").find(".datePickerStart").datepicker("destroy").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showButtonPanel: true,
		beforeShowDay: $.datepicker.noBefore
	});
	$("#" + parentView).find("#searchTable").find(".datePickerEnd").datepicker("destroy").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showButtonPanel: true,
		beforeShowDay: $.datepicker.noBefore
	});

	/* 지도에서 위치설정 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#excelDownBtn").click(function(){
		if($("#" + parentView).find("#resultList").find("table").children().length != 0){
			var param = NDMS_PARAM_LIST;

			_common.postForm.submit("/ndms/getStatExcelByInf119Mstr.do", param);
		}
	});

	$(document).ready(function(){
		$("#searchTable").find(".datePickerStart").datepicker('setDate', 'today');
		$("#searchTable").find(".datePickerEnd").datepicker('setDate', 'today');
	});

})();