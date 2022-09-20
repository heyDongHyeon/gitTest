(function(){

	/**
	 * 속성 검색 이벤트 입니다.
	 */
	$(".searchWrapper").find("#searchBtn").click(function(){
		var _param = _common.utils.collectSendData("#" + parentView + " .searchWrapper #searchTable");

		_common.callAjax("/cctv/getCctvList.json", _param, function(json){
			Public.CCTV.Search.excelParam = _param;
			if(json.result.length == 0){
				var $tr = $("<tr><td colspan='3' class='tCenter'>결과가 존재하지 않습니다.</td></tr>");
				$(".searchWrapper").find("#resultTable").find("tbody").html($tr);
			}else{
				$(".searchWrapper").find("#resultTable").find("tbody").html("");
				for(var i=0; i<json.result.length; i++){
					var $tr = $("<tr class='tCenter' k='" + json.result[i].mgrNo + "'></tr>");
						$tr.append("<td>" + _common.getCodeByName("C14", json.result[i].gbnCd) + "</td>");
						$tr.append("<td>" + json.result[i].cctvNm + "</td>");
						if (chkPage == "tvius"){
							/* 171212 */
							//$tr.append("<td><button class='blueBtn locBtn'>위치</button></td>");
							$tr.append("<td><button class='locBtn'></button></td>");
						} else {
							/* 171212 */
							//$tr.append("<td><button class='blueBtn locBtn'>위치</button><button class='blueBtn detailBtn'>관리</button></td>");
							$tr.append("<td><button class='locBtn'></td>");
						}

						/*console.log(json.result[i].lng);
						console.log(json.result[i].lat);*/

					var prop = {
						gid : json.result[i].gid,
						mgrNo : json.result[i].mgrNo,
						gbnCd : json.result[i].gbnCd,
						angle : json.result[i].viewDir,
						cctvNm : json.result[i].cctvNm,
						channelNo : json.result[i].chnlNo,
						deviceId : json.result[i].deviceId,
						stateCd : json.result[i].stateCd,
						point : Spatial.convertProjection([json.result[i].lng, json.result[i].lat], "EPSG:4326", "EPSG:5186")
					};
					$tr.data(prop);

					$(".searchWrapper").find("#resultTable").find("tbody").append($tr);
				}

				/* 위치 버튼 이벤트입니다. */
				$(".searchWrapper").find("#resultTable").find(".locBtn").click(function(){
					var v = $(this).parent().parent().attr("k");
					var prop = $(this).parent().parent().data();
					_common.callAjax("/nms/getGeometryLocation.json", {k : v}, function(json) {
						xeusLayout.mapService.moveToAnimation(0, [json.result[0].annoX, json.result[0].annoY]);
						//암호화 처리
						if(prop.stateCd != "12") xeusCCTV.viewVideo(encodeURIComponent(JSON.stringify(prop)));
						else alert("<br>해당 CCTV는 현재 재생이 불가능 합니다.");
					});
				});

				/* 관리 버튼 이벤트입니다. */
				$(".searchWrapper").find("#resultTable").find(".detailBtn").click(function(){
					$("#btn-cctv-mng").click();
					var v = $(this).parent().parent().attr("k");
					_common.callAjax("/cctv/getCctvMngView.do", {mgrNo : v}, function(view) {
        				$("#center-overlay-east").height(
        					$(window).height() - $("#layout-north").outerHeight() - $("#overlay-east-bar").outerHeight()
        				).html(view);
        				$(".btnDiv").removeClass("hidden");
        			});
				});
			}
		});
	});

	/**
	 * 영역검색 버튼 이벤트 입니다.
	 */
	$("#" + parentView).find(".searchWrapper").find("#spatialBtn").click(function(){
		if($(".drawType:checked").val() == null){
			alert("검색방법을 선택해 주세요.");
			return false;
		}
		Public.CCTV.Search.Start();
	});

	/**
	 * 영역검색 방식 변경 이벤트 입니다.
	 */
	$("#" + parentView).find(".searchWrapper").find(".drawType").click(function(){
		if(Public.StopEvent != null) Public.StopEvent();
		Public.CCTV.Search.Start();
	});

	/**
	 * 영역검색 취소버튼 이벤트 입니다.
	 */
	$("#" + parentView).find(".searchWrapper").find("#drawCncl").click(function(){
		Public.StopEvent();
		$(".drawType").prop("checked", false);
	});

	/**
	 * 엑셀 다운로드 버튼 이벤트 입니다.
	 */
	$("#" + parentView).find(".searchWrapper").find("#excelBtn").click(function(){
		if($(".searchWrapper").find("#resultTable").find("tbody").children().length == 0){
			alert("검색결과가 존재하지 않아 다운로드할 수 없습니다.");
		}else{
			confirm("검색결과를 엑셀로 다운로드 하시겠습니까?", function(){
				_common.postForm.submit("/cctv/getCctvListExcel.do", Public.CCTV.Search.excelParam);
			});
		}
	});

	$("#" + parentView).find(".searchWrapper").find("#cctvNm").keyup(function(e){
		if(e.which == 13){
			$(".searchWrapper").find("#searchBtn").click();
		}
	});

})();