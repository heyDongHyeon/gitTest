/**
 *
 */
(function(){

	var value = $("#searchInput").val();
	$("#searchInput").val(value).focus();

    $("#" + parentView +' #dt').val(new Date().getYMD(true));


	/* DatePicker 생성 이벤트입니다. */
	$(".searchWrapper").find(".datePicker").datepicker("destroy").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		showButtonPanel: true,
		beforeShowDay: $.datepicker.noBefore
	});

	$("#" + parentView).find(".searchWrapper").find("#searchBtn").click(function(){
		var _param = _common.utils.collectSendData("#searchTable");


		setNdpsList(_param);
	});

	//var _param = _common.utils.collectSendData("#searchTable");

	if(isFirst){

		//setList(_param);
	}

	/* 엔터키 이벤트 */
	$(".keyup").keyup(function(e){
		if(e.which == 13){
			var selector = $(this).attr("for");
			$(selector).click();
		};
	});

	/* 지도에서 위치설정 이벤트 입니다. */
	$("#" + parentView).find(".searchWrapper").find("#mapClickBtn").click(function(){
	    $("body").css("cursor", "crosshair");
	    $("#" + parentView).find(".selectCancel").show(500);
	    xeusLayout.mapService.getMap().on('click', Public.EVT.Add.Start);
	    Public.StopEvent = function(){
	        $("body").css("cursor", "default").off("click");
	        $("#" + parentView).find(".selectCancel").hide(500);
	        xeusLayout.mapService.getMap().un('click', Public.EVT.Add.Start);
	    }
	});
	$("#" + parentView).find(".searchWrapper").find(".selectCancel").click(function(){
	    Public.StopEvent();
	});

	function setNdpsList(param){

//		param['emdCd'] = param['nm'];
		param['se'] = GBN;
		param['dt'] = $("#" + parentView +' #dt').val();
		_common.callLoadingAjax("/ndps/getList.json", param, function(json) {
			$("#" + parentView).find("#ndpsTable").find("tbody").html("");
			if(json.result[0]==undefined){
				alert('데이터가 없습니다.');
			}
			for(var i=0; i< json.result.length; i++){
				var list = json.result[i];


						var innb = list.innb; //단말기 고유 번호
						var nm = list.nm; //단말기명
						var addr = list.addr; //단말기명
						var lon = list.lon; //단말기명
						var lat = list.lat; //단말기명
						var dt = list.dt; //날짜
                         if ( addr === undefined ) addr = '';
						var $tr = $("<tr class='tCenter' k='" + innb + "' dt='"+dt+"' nm='"+nm+"' lat='"+lat+"' lon='"+lon+"'></tr>");
						//목록에 있을 시 체크 상태로 표출한다.
						$tr.append("<td width=''>" + nm + "</td>");
						$tr.append("<td width=''>" + addr + "</td>");
						$tr.append("<td width=''><button class='locBtn'></button><button class='detailBtn'></button></td>");
						$("#" + parentView).find("#ndpsTable").find("tbody").append($tr);

			}

			/* 위치 버튼 이벤트 입니다. */
			$("#" + parentView).find(".searchWrapper").find("#ndpsTable").find(".locBtn").click(function(){
				var lon = $(this).parent().parent().attr("lon");	//tr의 k
				var lat = $(this).parent().parent().attr("lat");	//tr의 k
				var tm = Spatial.convertProjection([lon, lat], "EPSG:4326", "EPSG:5186");

				if (lat != null && lat != 0 && lat != undefined && lat != "undefined" && lon != null && lon != 0 && lon != undefined && lon != "undefined" ) {
//					xeusLayout.mapService.moveToAnimation(0, tm);
					_search.moveLocation(tm, "");
				}else{
					alert('위치정보가 없습니다.');
				}
			});

			/* 상세 버튼 이벤트 입니다. */
			$("#" + parentView).find(".searchWrapper").find("#ndpsTable").find(".detailBtn").click(function(){
				var v = $(this).parent().parent().attr("k");
				var dt = $(this).parent().parent().attr("dt");
				var nm = $(this).parent().parent().attr("nm");

				var url = "/ndps/getData.do";
				param['innb'] = v;
				_common.callAjax(url, {innb : v, dt : param['dt']}, function(view) {

					$("#" + parentView).find("#center-overlay-east").height(
						$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
					).html(view);

					_common.callAjax("/ndps/getData.json", param, function(json) {
						var ext = '';
						if ( GBN === 'B0305' ) {

						} else if ( GBN === 'B03101' ) {
							ext = 'mm';

						} else if ( GBN === 'B03103' ) {
							ext = 'cm';

						} else if ( GBN === 'B03102' ) {
							ext = 'M';

						}

						if(GBN==='B03105'){
							NdpsData.getChartAwsData(json.result, nm, ext, ext);

						}else{
							NdpsData.getChartData(json.result, nm, ext, ext);
						}

					});
					xeusLayout.WEST=NDPS_BTN_VIEW_WEST_SIZE;
					xeusLayout.EAST=600;
					xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
					});

				},false);
			});
		},false);

	}
})();