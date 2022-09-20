/**
 * 장비관리(NMS) 메뉴의 점검 등록 관련 이벤트 입니다.
 */
(function(){

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

	/* 시설물 신규추가 버튼입니다.. */
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
//		_html += '            	<td><select class="wide"><option value="CTV">CCTV</option><option value="AWS">기상관측기기</option><option value="VOI">방송장비</option><option value="DIS">재난재해전광판</option></select></td> ';
		_html += '            	<td><select class="wide"><option value="CTV">CCTV</option><option value="DIS">재난재해전광판</option></select></td> ';
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

					var url = "";
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
		xeusLayout.hideOverlayEastPane(500);

		var _param = _common.utils.collectSendData("#" + parentView + " .searchWrapper #searchTable");
		$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").html('');		//무슨 뜻....

		//NDPS에서 가져온 기상관측기기, 음성통보시스템 표출
//		if(_param["objType"] == "AWS" || _param["objType"] == "VOI"){
		if(false){
			_common.callLoadingAjax("/nms/getFacilitySearch.json", _param, function(json) {
				$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").html("");
				for(var key in json){
					var list = json[key];
					if(list.length > 0){
						for(var i=0; i<list.length; i++){
							var no = list[i].innb; //단말기 고유 번호
							var se = list[i].se; //단말기 구분
							var nm = list[i].nm; //단말기명
							var telno = list[i].telno; // 단말기 전화번호
							var checked = '';//

							if ( telno === undefined ) telno ='';

							$('#send_select_list .send_select_item').each(function(){

								var key = $(this).attr('id').replace('send_select_', '');

								if ( key == no ) {
									checked = 'checked';
								}

							});

							var type = "";
							if(key == "awsList"){
								type = "기상관측기기";
							}else if(key == "voiceList"){
								type = "음성통보시스템";
							}


							var $tr = $("<tr class='tCenter' k='" + no + "'></tr>");

							$tr.append("<td width='100px'>" + type + "</td>");
							$tr.append("<td>" + "명칭 : " + nm + "<br> 번호 : " + telno + "</td>");
							$tr.append("<td width='70px'></button><button class='locBtn'></button><button class='detailBtn'></td>");

							$tr.data(list[i]);

							$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").append($tr);

						}

					}
				}




				/* 위치 버튼 이벤트 입니다. */
				$("#" + parentView).find(".searchWrapper").find("#resultList").find(".locBtn").click(function(){
					var data = $(this).parent().parent().data();	//tr의 k

					if(data.lo == "" || data.lo == ""){
						alert("위치가 없습니다.");
						return;
					}

					var center = Spatial.convertProjection([data.lo,data.la], "EPSG:4326", "EPSG:5186");
					_search.moveLocation(center, "");

				});

				/* 상세 버튼 이벤트 입니다. */
				$("#" + parentView).find(".searchWrapper").find("#resultList").find(".detailBtn").click(function(){
					alert("정보수정은 운영 시스템에서 가능합니다.");
				});

			});
		}else{
			Public.NMS.Search.excelParam = _param
			_common.callAjax("/nms/getFacilitySearch.json", _param, function(json) {
				$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").html('');		//무슨 뜻....
				for(var key in json){		//요부분 잘 모르겠다.
					var list = json[key];	//요부분 잘 모르겠다...
					if(list.length > 0){
						for(var i=0; i<list.length; i++){

							var mgrNo = list[i].mgrNo;
							var name = list[i].facilityNm;		//list에 이런 것은 없다.
							var clscd = list[i].facilityClscd;		//list에 이런 것은 없다.
							var emdCd="";
							var type = "기반시설";
							var prop = {};
							if(key == "cctvList")
							{
								if ( list[i].addr === null ) {
									list[i].addr = '';
								} else {
									emdCd=list[i].addr.replace('세종특별자치시 ','').replace('세종특별자치시','')+"<br>";
								}
								type = "재난재해용 CCTV";
								name = list[i].cctvNm;
								clscd = "CCTV";
								prop = {
										gid : list[i].gid,
										mgrNo : list[i].mgrNo,
										gbnCd : list[i].gbnCd,
										angle : list[i].viewDir,
										cctvNm : list[i].cctvNm,
										channelNo : list[i].chnlNo,
										deviceId : list[i].deviceId,
										stateCd : list[i].stateCd,
										point : Spatial.convertProjection([list[i].lng, list[i].lat], "EPSG:4326", "EPSG:5186")
								};
								emdCd = '';



								var $tr = $("<tr class='tCenter' k='" + mgrNo + "'></tr>");
								/*$tr.append("<td width='140px'>" + emdCd + "</td>");*/
								$tr.append("<td width='100px'>" + type + "</td>");
								$tr.append("<td>" + emdCd + name + "</td>");
								/* 171212 */
								//$tr.append("<td><button class='blueBtn detailBtn'>상세</button><button class='blueBtn locBtn'>위치</button></td>");
								$tr.append("<td width='70px'></button><button class='locBtn'></button><button class='detailBtn'></td>");
								$tr.data(prop);

							}
							else if(key == "awsList" || key == "voiceList")
							{

								var no = list[i].innb; //단말기 고유 번호
								var se = list[i].se; //단말기 구분
								var nm = list[i].nm; //단말기명
								var telno = list[i].telno; // 단말기 전화번호
								var checked = '';//

								if ( telno === undefined ) telno ='';

								$('#send_select_list .send_select_item').each(function(){

									var key = $(this).attr('id').replace('send_select_', '');

									if ( key == no ) {
										checked = 'checked';
									}

								});

								var type = "";
								if(key == "awsList"){
									type = "기상관측기기";
								}else if(key == "voiceList"){
									type = "음성통보시스템";
								}


								var $tr = $("<tr class='tCenter' k='" + no + "'></tr>");

								$tr.append("<td width='100px'>" + type + "</td>");
								$tr.append("<td>" + "명칭 : " + nm + "<br> 번호 : " + telno + "</td>");
								$tr.append("<td width='70px'></button><button class='awsVoiceLocBtn'></button><button class='awsVoiceDetailBtn'></td>");

								$tr.data(list[i]);


							}
							else if(key == "voiceList")
							{
								if ( list[i].locAddr === null ) {
									list[i].locAddr = '';
								} else {
									emdCd=list[i].locAddr.substring(0, list[i].locAddr.indexOf('('))+"<br>";
									emdCd=emdCd.replace('세종특별자치시 ','').replace('세종특별자치시','').replace('세종시 ','').replace('세종시','');
								}
								type="음성통보시스템";
								clscd=list[i].fclGbnNm;
								name=list[i].docMgrNo;
								if(name!==null)
								{
									name=name.replace('세종특별자치시-','');
								}
								prop=list[i];



								var $tr = $("<tr class='tCenter' k='" + mgrNo + "'></tr>");
								/*$tr.append("<td width='140px'>" + emdCd + "</td>");*/
								$tr.append("<td width='100px'>" + type + "</td>");
								$tr.append("<td>" + emdCd + name + "</td>");
								/* 171212 */
								//$tr.append("<td><button class='blueBtn detailBtn'>상세</button><button class='blueBtn locBtn'>위치</button></td>");
								$tr.append("<td width='70px'></button><button class='locBtn'></button><button class='detailBtn'></td>");
								$tr.data(prop);
							}
							else if(key == "disbordList")
							{
								if ( list[i].locDesc === null ) {
									list[i].locDesc = '';
								} else {
									emdCd=list[i].locDesc.replace('세종특별자치시 ','').replace('세종특별자치시','')+"<br>";
								}
								type="재난재해전광판";
								name=list[i].bordNm;
								prop=list[i];



								var $tr = $("<tr class='tCenter' k='" + mgrNo + "'></tr>");
								/*$tr.append("<td width='140px'>" + emdCd + "</td>");*/
								$tr.append("<td width='100px'>" + type + "</td>");
								$tr.append("<td>" + emdCd + name + "</td>");
								/* 171212 */
								//$tr.append("<td><button class='blueBtn detailBtn'>상세</button><button class='blueBtn locBtn'>위치</button></td>");
								$tr.append("<td width='70px'></button><button class='locBtn'></button><button class='detailBtn'></td>");
								$tr.data(prop);
							}
							else
							{
								prop = list[i];


								var $tr = $("<tr class='tCenter' k='" + mgrNo + "'></tr>");
								/*$tr.append("<td width='140px'>" + emdCd + "</td>");*/
								$tr.append("<td width='100px'>" + type + "</td>");
								$tr.append("<td>" + emdCd + name + "</td>");
								/* 171212 */
								//$tr.append("<td><button class='blueBtn detailBtn'>상세</button><button class='blueBtn locBtn'>위치</button></td>");
								$tr.append("<td width='70px'></button><button class='locBtn'></button><button class='detailBtn'></td>");
								$tr.data(prop);

							}



							$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").append($tr);


						}
					}/*else{
						var $tr = $("<tr class='tCenter'></tr>");
						$tr.append("<td><p style='padding: 100px 0px;' class='tCenter'><b>검색결과가 존재하지 않습니다.</b></p></td>");
						$("#" + parentView).find(".searchWrapper").find("#resultList").find("table").append($tr);
					}*/
				}


				/* 위치 버튼 이벤트 입니다. */
				$("#" + parentView).find(".searchWrapper").find("#resultList").find(".locBtn").click(function(){
					var v = $(this).parent().parent().attr("k");	//tr의 k
					_common.callAjax("/nms/getGeometryLocation.json", {k : v}, function(json) {
//						console.log("x = "+json.result[0].annoX);
//						console.log("y = "+json.result[0].annoY);
//						xeusLayout.mapService.moveToAnimation(0, [json.result[0].annoX, json.result[0].annoY]);
						_search.moveLocation( [json.result[0].annoX, json.result[0].annoY], "");
					});
				});

				/* 상세 버튼 이벤트 입니다. */
				$("#" + parentView).find(".searchWrapper").find("#resultList").find(".detailBtn").click(function(){
					var v = $(this).parent().parent().attr("k");		//??????뭔지 모르곘음. mgrNo를 가져옴...
					var type = v.substring(0, 3);
					/*_common.callAjax("/nms/getInfraDetailView.do", {k : v}, function(view) {
						$("#" + parentView).find(".searchWrapper").css("overflow", "auto");
						$("#" + parentView).find(".detailWrapper").html(view);
					});*/

					var url = "";
					if(type == "CTV") url = "/cctv/getCctvMngView.do";
					if(type == "AWS") url = "/aws/getAwsMngView.do";		//aws 컨트롤러로 가겠지.현재 문제는 안간다...
					if(type == "VOI") url = "/voice/getVoiceMngView.do";
					if(type == "DIS") url = "/disbord/getDisbordMngView.do";

					_common.callAjax(url, {mgrNo : v}, function(view) {
						$("#" + parentView).find("#center-overlay-east").height(
							$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
						).html(view);
						$("#" + parentView).find(".btnDiv").removeClass("hidden");
						$("#" + parentView).find("#regTable").find(".date").datepicker({
				            format: "yyyy/mm/dd",
				            language: "kr"
						});
						xeusLayout.EAST=600;
						xeusLayout.WEST = NMS_BTN_INFRA_VIEW_WEST_SIZE;
						xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
						});
					});
				});


				/* 위치 버튼 이벤트 입니다. */
				$("#" + parentView).find(".searchWrapper").find("#resultList").find(".awsVoiceLocBtn").click(function(){
					var data = $(this).parent().parent().data();	//tr의 k

					if(data.lo == "" || data.lo == ""){
						alert("위치가 없습니다.");
						return;
					}

					var center = Spatial.convertProjection([data.lo,data.la], "EPSG:4326", "EPSG:5186");
					_search.moveLocation(center, "");

				});

				/* 상세 버튼 이벤트 입니다. */
				$("#" + parentView).find(".searchWrapper").find("#resultList").find(".awsVoiceDetailBtn").click(function(){
					alert("정보수정은 운영 시스템에서 가능합니다.");
				});
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


	/**
	 * 엑셀 다운로드 버튼 이벤트 입니다.
	 */
	$("#" + parentView).find(".searchWrapper").find("#excelBtn").click(function(){
		if($("#" + parentView).find(".searchWrapper").find("#resultList").find("table").children().length == 0){
//			alert("검색결과가 존재하지 않아 다운로드할 수 없습니다.");
		}else if(Public.NMS.Search.excelParam.objType == "ALL"){
			alert("먼저 시설구분을 선택하고 검색해주세요.");
		}else{
			confirm("검색결과를 엑셀로 다운로드 하시겠습니까?", function(){
				var param = JSON.parse(JSON.stringify(Public.NMS.Search.excelParam));
				_common.postForm.submit("/nms/getFacilitySearchExcel.do", param);
//				if(Public.NMS.Search.excelParam.objType == "CTV"){
//					var param = JSON.parse(JSON.stringify(Public.NMS.Search.excelParam));
//					param["cctvNm"] = param["objName"];
//					_common.postForm.submit("/cctv/getCctvListExcel.do", param);
//				}
//				else if(Public.NMS.Search.excelParam.objType == "AWS" || Public.NMS.Search.excelParam.objType == "VOI"){
//					var param = JSON.parse(JSON.stringify(Public.NMS.Search.excelParam));
//					_common.postForm.submit("/cctv/getCctvListExcel.do", Public.NMS.Search.excelParam);
//				}
			});
		}
	});

})();