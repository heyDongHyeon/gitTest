/**
 * 이력 조회 이벤트 입니다.<br>
 */
//(function(){



	$(document).ready(function(){

		/**
		 * 180419 이은규
		 * DatePicker div가 사라지지 않아 다른페이지를 다녀올 시 문제가 생김.
		 * 강제로 div를 삭제
		 */
		$('#ui-datepicker-div').remove();

		/* DatePicker 생성 이벤트입니다. */
		$(".searchWrapper").find(".datePicker").datepicker("destroy").datepicker({
			changeMonth: true,
		    changeYear: true,
		    dateFormat: "yymmdd",
		    showButtonPanel: true,
		    beforeShowDay: $.datepicker.noBefore
		});

		createColParam();

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

		//console.log(_param);

		//var _url = '/event/get'+_param['selectDist']+'List.json';
		var _url = '/event/get'+$('#selectDist').val()+'List.json';
		if (_url != ''){
			_common.callAjax(_url, _param, function(json){

				/*var a = Number('0.0000000');
				var b = 0;

				console.log(a == b);*/

				//console.log(json.count);
				//console.log(json.result.length);

				if(json.result != null && json.result != undefined){

					var str = '';

					for(var i=0; i<json.result.length; i++){

						var lkInfoId = json.result[i].lkInfoId;
						var rcvYmdHms = json.result[i].rcvYmdHms;
						var seqNo = json.result[i].seqNo;
						var pointX = json.result[i].pointX;
						var pointY = json.result[i].pointY;

						str += '<tr>';
						str += '<td class="tCenter">'+seqNo+'</td>';
						str += '<td class="tCenter">'+lkInfoId+'</td>';
						str += '<td class="tCenter">'+rcvYmdHms+'</td>';
						str += '<td class="tCenter">';
						if(pointX != null && pointX != undefined){
							if(Number(pointX)>0 && Number(pointX)<180 && Number(pointY)>0 && Number(pointY)<90){
								str += '<button class="locBtn" lon="'+pointX+'" lat="'+pointY+'"></button>';
							}
						}
						str += '</td>';
						//str += '<td class="tCenter"><button class="transparent detailBtn" seqno="'+seqNo+'"><img src="/xeus/res/img/icon_info_normal.png" style="width: 24px; height: 24px;"/></button></td>';
						str += '<td class="tCenter"><button class="infoBtn" lkinfoid="'+lkInfoId+'" rcvymdhms="'+rcvYmdHms+'" seqno="'+seqNo+'"></button></td>';
						str += '</tr>';
					}

					$('#searchResult').find('#target').empty();
					$('#searchResult').find('#target').attr('tablenm', _param['selectDist']);
					$('#searchResult').find('#target').append(str);

					/* 위치 버튼 이벤트입니다. */
					$(".searchWrapper").find("#searchResult").find('#target').find(".locBtn").click(function(){
						//alert($(this).attr('lat') + ' // ' + $(this).attr('lon'));

						var lat = Number($(this).attr('lat'));
						var lon = Number($(this).attr('lon'));

						if(lat != 0 && lon != 0){

							//cctvParam["point"] = Spatial.convertProjection([Number(cctvParam.lng), Number(cctvParam.lat)], "EPSG:4326", "EPSG:5186");
							//127.502231326895000;36.393819380410900

							var _pt = Spatial.convertProjection([lon, lat], "EPSG:4326", "EPSG:5186");

							var _boundary = $('#virtual-map-boundary');
							var _ndx = (xeusLayout.mapService.getMap().getSize()[0] / 2)
									- (_boundary.offset().left + _boundary.width() / 2);
							var _wdx = xeusLayout.mapService.getMap().getCoordinateFromPixel([ _ndx, 0 ]);
							var _wsx = xeusLayout.mapService.getMap().getCoordinateFromPixel([ 0, 0 ]);
							var _newPt = [ _pt[0] + _wdx[0] - _wsx[0], _pt[1] ];
							xeusLayout.mapService.getMap().getView().animate({
								center : _newPt,
								duration : 1000
							});

						}

						/*var v = $(this).parent().parent().attr("k");
						var prop = $(this).parent().parent().data();
						_common.callAjax("/nms/getGeometryLocation.json", {k : v}, function(json) {
							xeusLayout.mapService.moveToAnimation(0, [json.result[0].annoX, json.result[0].annoY]);
							xeusCCTV.viewVideo(JSON.stringify(prop));
						});*/
					});

					/* 상세보기 버튼 이벤트입니다. */
					$(".searchWrapper").find("#searchResult").find('#target').find(".infoBtn").click(function(){
						//alert($(this).attr('seqno'));

						var lkInfoId = $(this).attr('lkinfoid');
						var rcvYmdHms = $(this).attr('rcvymdhms');
						var seqNo = $(this).attr('seqno');
						var tableNm = $(this).closest('tbody').attr('tablenm');

						//console.log(seqNo + ' // ' + tableNm);

						createDetailPopup(lkInfoId, rcvYmdHms, seqNo, tableNm);
						/*$("#btn-cctv-mng").click();
						var v = $(this).parent().parent().attr("k");
						_common.callAjax("/cctv/getCctvMngView.do", {mgrNo : v}, function(view) {
	        				$("#center-overlay-east").height(
	        					$(window).height() - $("#layout-north").outerHeight() - $("#overlay-east-bar").outerHeight()
	        				).html(view);
	        			});*/
					});

					$("#offset").val(offset);
					$("#max").val(json.count);
					setPaging();

				}

			});
		}

	}

	function setPaging(){
		$(".paging_wrap").empty();
		/*페이징 처리*/
		$(".paging_wrap").paging({
			current	  : 10,
			max  	  : Number($("#max").val()),
			nowOffset : Number($("#offset").val()),
			bindEvent : callView
		});
	}

	function createDetailPopup(lkInfoId, rcvYmdHms, seqNo, tableNm) {
		var _param = {};
	    _param['lkInfoId'] = lkInfoId;
	    _param['rcvYmdHms'] = rcvYmdHms;
	    _param['seqNo'] = seqNo;

	    var _url = "/event/get"+tableNm+"Item.json";
		_common.callAjax(_url, _param, function(json){
			//console.log(json);

			if (json != null){

				//var column = transformRst(json.column);
				var _html = '';

				_html += '<div id="cctvInfo" class="bPopup" style="display: none;"> ';
				_html += '    <div id="title-bar"> ';// style="margin-left: 10px;"
				_html += '        <button id="closeBtn" style="float: right;"><img src="/xeus/res/img/close_btn.png"/></button> ';
				_html += '        <p class="title">상세 정보</p> ';
				_html += '    </div> ';
				_html += '    <table class="list"> ';// cellspacing="0" width="100%" style="margin-top:0;"

				for(key in json.result){
				_html += '        <tr> ';

					//if(column[key] != null)
					var column = getColNm(key);
					if(column != null)
				//_html += '            <th><label>' + column[key] + '</label></th> ';
				_html += '            <th><label>' + column + '</label></th> ';
					else
				_html += '            <th><label>' + key + '</label></th> ';
				_html += '            <td><label>' + json.result[key] + '</label></td> ';
				_html += '        </tr> ';
				}

				_html += '    </table> ';
				_html += '</div> ';

				$('#layout-body').append(_html);

				var bpop = $('#cctvInfo').bPopup({
					appendTo: $('#'+parentView),
					onOpen: function() {
						$('#cctvInfo').find('#closeBtn').click(function(){
							$('#cctvInfo').bPopup().close();
							$('.bPopup').remove();

						});

						var trCnt = $('#cctvInfo tr').length;
						$('#cctvInfo').height(30 + 35 + (trCnt*41));
					},
					onClose: function() {
						$('.bPopup').remove();
					}

				});

				/*var test = '<tr><th><label>test</label></th><td><label>dddddddddddddddddddddddddd</label></td></tr>';
				for (var i=0; i<10; i++){
					$('#cctvInfo').find('.list').append(test);
				}

				$('#cctvInfo').height(900);
				bpop.reposition(300);*/
			}

		});
	}

	function transformRst(column){

		var _rst = {};

		for(var i=0; i<column.length; i++){
			var colId = snakeToCamel(column[i]['colId']);
			var colNm = column[i]['colNm'];
			_rst[colId] = colNm;
		}

		return _rst;

	}

	function snakeToCamel(s){
	    return s.replace(/(\_\w)/g, function(m){return m[1].toUpperCase();});
	}

	/*String.prototype.toUnderscore = function(){
		return this.replace(/([A-Z])/g, function($1){return "_"+$1.toLowerCase();});
	};*/

	/* 검색 버튼 이벤트 입니다.*/
	$(".searchWrapper").find("#schBtn").click(function(){
		var _param = _common.utils.collectSendData("#search");

		if(_param['startDat'] != '') _param['startDat'] = _param['startDat'] + '000000';
		if(_param['endDat'] != '') _param['endDat'] = _param['endDat'] + '235959';

		if(_param['selectDist'] == ''){
			alert('재난상황을 선택하여 주십시오.');
			return false;
		}

		//console.log(_param);

		callView(0, _param);

	});

	function getColNm(key){

		return _colParam[key];
	}

	/* 엑셀 버튼 이벤트 입니다.*/
	/*$("#wrap").find("#excelBtn").click(function(){
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

				$('.tab').each(function() {
					var chkActive = $(this).attr("active");
					if (chkActive !== undefined && chkActive == "active"){
						_param['excel'] = $(this).attr("excel");
					}
				});

				console.log(_param);

				_common.postForm.submit("/log/getLogAsExcel.do", _param);
			}
		});
	});*/

	/* 상세보기 이벤트 입니다.
	$(".searchWrapper").find("#resultTable").find(".detailBtn").click(function(){
		var v = $(this).attr("k");

		_common.callAjax("/nms/getSiteDetailView.do", {"k" : v}, function(view) {
			$(".searchWrapper").find("#detailWrapper").html(view);
		});
	}); */

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

	$(".searchWrapper").find("#test").click(function(){

		var _param = {};
		_param['phone'] = '01089630524';
		_param['conts'] = '문자발송 테스트입니다.';

		_common.callAjax("/sysMng/sendSms.json", _param, function(json){
			if(json.result == '1') alert('문자 발송 성공');
			else alert('문자발송 실패 : ' + json.result);
		});




		/*_common.callAjax("/event/getEliFoaForestMapList.json", null, function(json) {
			console.log(json);

			var jsonArray = new Array();
			for(var i=0; i<json.result.length; i++){

				lkInfoId:"FOA_FOREST_MAP_SND"
				loc:"경기도 성남시 수정구 시흥동 282-26"
				pointX:"127.098317000000000"
				pointY:"37.411315000000000"
				rcvYmdHms:"20180425143235"
				rcvYmdhm:"201804232100"
				seqNo:"18"

				var obj = new Object();

				var _pt = Spatial.convertProjection([json.result[i].pointX, json.result[i].pointY], "EPSG:4326", "EPSG:5186");
				obj.pointX = _pt[0];
				obj.pointY = _pt[1];
				obj.lkInfoId = json.result[i].lkInfoId;
				obj.seqNo = json.result[i].seqNo;
				obj.symCd = '11';

				jsonArray.push(obj);

			}

			var jsonData = JSON.stringify(jsonArray);
			var map = xeusLayout.mapService.getMap();
			var size = map.getSize();
			var extent = map.getView().calculateExtent(size);
			var epsg = map.getView().getProjection().getCode();
			// false로 반환되는 값은 "", null, undefined, 0, NaN 이 있고
			var SYMSIZE = 40;
			if (!epsg) {
				return;
			}
			var codes = [];
			epsg = epsg.split(':')[1];
			$.ajax({
				url :  "/xeus/event/eventmap",
				type : "POST",
				data : {
					'epsg' : epsg,
					'map_width' : Math.floor(size[0]),
					'map_height' : Math.floor(size[1]),
					'sym_width' : SYMSIZE,
					'sym_height' : SYMSIZE,
					'bbox' : extent.join(','),
					'codes' : codes.join(','),
					'list' : jsonData
				},
				dataType : "json",
				success : function(json) {
					console.log(json);
					var source = cctvLayer.getSource();
					var features = new ol.format.GeoJSON().readFeatures(json);
					source.clear();
					source.addFeatures(features);
				},
				error : function(xhr, status, error) {
					alert("EVENT data request error occurred.. > \r\n" + error);
				}
			});
		});*/

	});

	function createColParam(){

		//공통
		_colParam['lkInfoId'] = '연계정보아이디';
		_colParam['rcvYmdHms'] = '수신일자시각';
		_colParam['seqNo'] = '연번';
		_colParam['pointX'] = '좌표x';
		_colParam['pointY'] = '좌표y';
		_colParam['loc'] = '위치';

		//산불
		_colParam['tpStatus'] = '산불발생진행상태';
		_colParam['rcvYmdhm'] = '접수일시분';

		//우량
		_colParam['obsvtId'] = '관측소코드';
		_colParam['obsvtNm'] = '관측소명';
		_colParam['rfhrStatus'] = '우량시단위등급';
		_colParam['obsvtYmdhm'] = '관측일시분';
		_colParam['hrrf'] = '시강우';

		//고속도로
		_colParam['endYn'] = '종료여부';
		_colParam['ymdhm'] = '일시분';
		_colParam['updn'] = '상하행구분';
		_colParam['stat'] = '내용';
		_colParam['gId'] = '도로아이디';

		//기상정보
		_colParam['obsvtYmdhm'] = '관측일시분';
		_colParam['obsvtAddr'] = '관측소주소';
		_colParam['awsLvlTp'] = '기상상태코드';
		_colParam['awsLvl'] = '기상상태';
		_colParam['wind'] = '풍향';
		_colParam['ws'] = '풍속';
		_colParam['ta'] = '기온';
		_colParam['qtyDay'] = '일누적강수량';

		//동네
		_colParam['fcYmdhm'] = '발표일시분';
		_colParam['rain1'] = '강수확률1';
		_colParam['rain2'] = '강수확률2';
		_colParam['rain3'] = '강수확률3';
		_colParam['rain4'] = '강수확률4';
		_colParam['rain5'] = '강수확률5';
		_colParam['wad1'] = '날씨1';
		_colParam['wad2'] = '날씨2';
		_colParam['wad3'] = '날씨3';
		_colParam['wad4'] = '날씨4';
		_colParam['wad5'] = '날씨5';
		_colParam['tem1'] = '온도1';
		_colParam['tem2'] = '온도2';
		_colParam['tem3'] = '온도3';
		_colParam['tem4'] = '온도4';
		_colParam['tem5'] = '온도5';
		_colParam['hum1'] = '습도1';
		_colParam['hum2'] = '습도2';
		_colParam['hum3'] = '습도3';
		_colParam['hum4'] = '습도4';
		_colParam['hum5'] = '습도5';

		//지진
		_colParam['ocrYmdhms'] = '발생시분초';
		_colParam['earthInfmScle'] = '지진규모(리히터)';
		_colParam['earthInfmNoOrd'] = '지진등급';
		_colParam['earthInfmNoRef'] = '지진참고번호';
		_colParam['earthInfmCdStn'] = '지진지점번호';

		//기상특보
		_colParam['kmaYmdhms'] = 'kma발표일시분초';
		_colParam['kmaSeqNo'] = 'kma발표연번';
		_colParam['kmaTitle'] = 'kma발표제목';
		_colParam['kmaStatCtnt'] = 'kma발표내용';
		_colParam['kmaSectArea'] = 'kma지역';
		_colParam['kmaPwVl'] = 'kma예비특보';
		_colParam['kmaCdStn'] = 'kma지점번호';

		//응급복구장비
		_colParam['aseqNo'] = '장비연번';
		_colParam['equpCd'] = '장비코드';
		_colParam['equpName'] = '장비명';
		_colParam['amt'] = '수량';
		_colParam['areaCd'] = '지역코드';
		_colParam['areaName'] = '지역명';
		_colParam['areaCdH'] = '지역코드(행정동)';
		_colParam['company'] = '소유사';
		_colParam['ownerName'] = '소유자';

		//소방서
		_colParam['firsId'] = '소방서id';
		_colParam['firsNm'] = '소방서명';
		_colParam['areaCd'] = '지역코드';
		_colParam['areaAddr'] = '지역주소';
		_colParam['cdGis'] = 'cdgis';
		_colParam['tel'] = '전화번호';
		_colParam['fax'] = 'fax번호';

		//시가지
		_colParam['stdLinkId'] = '표준링크id';
		_colParam['roadName'] = '노선명';
		_colParam['measureTime'] = '발생일시';
		_colParam['typeOther'] = '상황내용';
		_colParam['rgsDate'] = '등록일시';
	}
