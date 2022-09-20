/**
 * <pre>
 * 위젯 유틸리티객체 입니다.
 *
 * @author 이주영
 * </pre>
 */
var WIDGET = {

	/*
	 * 목적별 CCTV 위젯을 불러옵니다.
	 *
	 * @Deprecated
	 */
	getCctvWidget : function(cctvGov) {
		var _param = null;
		if(cctvGov != null && cctvGov != "") _param = { "cctvGov" : cctvGov };
		_common.callAjax("/widget/getCctvGbnList.json", _param, function(json) {
			var $parent = $("#widget-cctv").find(".widget-content");

			$parent.find("#left, #right, #total").html("");

			var left = Math.ceil(json.result.length / 2);
			var right = json.result.length - left;
			var totalCnt = 0;

			for(var i=0; i<left; i++){
				var cdeNm = json.result[i].cdeNm;
				var gbnCd = json.result[i].gbnCd;
				var cnt = json.result[i].cnt;
				totalCnt += cnt;

				var $div = $("<div></div>").append("<img src='../res/sym/cctv/" + gbnCd + ".png' style='vertical-align: middle;'>").append("<span class='cctvCnt' cfor='asset_cctv_" + cdeNm + "'>" + cdeNm + " (" + cnt + "개)" + "</span>");
				$parent.find("#left").append($div);
			}

			for(var i=left; i<left + right; i++){
				var cdeNm = json.result[i].cdeNm;
				var gbnCd = json.result[i].gbnCd;
				var cnt = json.result[i].cnt;
				totalCnt += cnt;

				var $div = $("<div></div>").append("<img src='../res/sym/cctv/" + gbnCd + ".png' style='vertical-align: middle;'>").append("<span class='cctvCnt' cfor='asset_cctv_" + cdeNm + "'>" + cdeNm + " (" + cnt + "개)" + "</span>");
				$parent.find("#right").append($div);
			}

			$("#left, #right").find("img").width(30).height(30);

			var $div = $("<div></div>").append("<img src='../res/sym/cctv_total.png' style='width: 80px;'>")
										.append("<div style='font-size: 20px; margin-top: 20px;'>총 " + totalCnt + "개</div>")
										.css({"text-align" : "center", "margin-left" : "10px"});
			$parent.find("#total").append($div);
			$parent.find("span").css("cursor", "pointer").click(function(){
				var $this = $(this);
				var selector = $(this).attr("cfor");
				$("#" + selector).click();
				if($("#" + selector).is(":checked")){
					$this.css("color", "white");
				}else{
					$this.css("color", "gray");
				}
			});
			$parent.find("span").each(function(){
				var $this = $(this);
				var selector = $(this).attr("cfor");
				if($("#" + selector).is(":checked")){
					$this.css("color", "white");
				}else{
					$this.css("color", "gray");
				}
			});
		});
	},

	/*
	 * 시군별 CCTV 위젯을 불러옵니다.
	 *
	 * @Deprecated
	 */
	getCctvGovWidget : function() {
		_common.callAjax("/widget/getCctvGovList.json", null, function(json) {
			var $parent = $("#widget-cctv").find(".widget-content");

			var $sggTable = $("<table></table").css({
				"width" : "100%",
				"text-align" : "center"

			});
			$sggTable.append("<tr></tr>").append("<tr></tr>");

			var length = json.result.length;
			for(var i=0; i<length; i++){
				var cctvGov = json.result[i].cctvGov;
				if(cctvGov == "경제자유구역청") cctvGov = "경제청";
				if(cctvGov == "국가교통정보센터") cctvGov = "국가 ITS";
				if(cctvGov == "인천교통정보센터") cctvGov = "인천 ITS";
				var $th = $("<th>" + cctvGov + "</th>").attr("val", json.result[i].cctvGov);
				$sggTable.find("tr").eq(0).append($th);
			}
			for(var i=0; i<length; i++){
				$sggTable.find("tr").eq(1).append("<td>" + json.result[i].cnt + "</td>");
			}

			$sggTable.find("th").css("cursor", "pointer").click(function(){
				var idx = $(this).index();
				if($(this).attr("active") == "active"){
					$sggTable.find("th").css("color", "white").removeAttr("active");
					$sggTable.find("td").css("color", "white");
					WIDGET.getCctvWidget(null);
				}else{
					$sggTable.find("th").css("color", "gray").removeAttr("active");
					$sggTable.find("td").css("color", "gray");
					$(this).css("color", "white").attr("active", "active");
					$sggTable.find("td").eq(idx).css("color", "white");
					WIDGET.getCctvWidget($(this).attr("val"));
				}
				xeusCCTV.cctv.reload();
			});

			$sggTable.find("td").css("cursor", "pointer").click(function(){
				var idx = $(this).index();
				$sggTable.find("th").eq(idx).click();
			});

			$parent.find("#sgg").append($sggTable);
			////////////////////////////////////////////////////////////

			var ty = 0;
			var vu = 0;
			var mil = 0;
			var gen53 = 0;
			var rtsp = 0;

			for(var i=0; i<length; i++){
				var cnt = json.result[i].cnt;
				var vmsTyp = json.result[i].vmsTyp;
				if(vmsTyp == "이노뎁T") ty += cnt;
				if(vmsTyp == "이노뎁V") vu += cnt;
				if(vmsTyp == "마일스톤") mil += cnt;
				if(vmsTyp == "제네텍5.3") gen53 += cnt;
				if(vmsTyp == "RTSP") rtsp += cnt;
			}
			var $vmsTable = $("<table></table").css({
				"width" : "100%",
				"text-align" : "center"

			});
			$vmsTable.append("<tr></tr>").append("<tr></tr>");

			$vmsTable.find("tr").eq(0).append("<th>이노뎁 Tybis</th>")
									  .append("<th>이노뎁 Vurix</th>")
									  .append("<th>마일스톤</th>")
									  .append("<th>제네텍5.3</th>")
									  .append("<th>RTSP</th>");

			$vmsTable.find("tr").eq(1).append("<td>" + ty + "</td>")
									  .append("<td>" + vu + "</td>")
									  .append("<td>" + mil + "</td>")
									  .append("<td>" + gen53 + "</td>")
									  .append("<td>" + rtsp + "</td>");

			$parent.find("#vms").append($vmsTable);
		});
	},

	/**
	 * tr 배경색을 변경합니다.
	 */
	changeOrder : function(){
		$("#evtTable").find("tbody").find("tr").css("background", "none");
		$("#evtTable").find("tbody").find("tr").eq(0).css("background", $("#evtTable").find("tbody").find("tr").eq(0).attr("back"));
		$("#evtTable").find("tbody").find("tr").eq(1).css("background", $("#evtTable").find("tbody").find("tr").eq(1).attr("back"));

		if($("#evtTable").find("tbody").find("tr").length == 0){
			$("#widget-point").find("#dis").hide();
		}else{
			$("#widget-point").find("#dis").show();
		}
		$("#widget-point").find(".pointBtn").attr("active", "");
	},

	/**
	 * 미해제 이벤트 리스트 위젯을 생성합니다.
	 */
	getPastEventListWidget : function(){
		var evt112 = true;
		var evt119 = true;
		var evtDsc = true;
		var evtLock = true;
		var evtCctvShare = true;
		var evtSmart = true;
		var evtPrev = true;

	/*	_common.callAjax("/auth/hasAuth.json", { "authData" : "NDMS119" }, function(json){
			if(json.result) evt119 = true;
		}, false);
		_common.callAjax("/auth/hasAuth.json", { "authData" : "NDPSWARN" }, function(json){
			if(json.result) evtDsc = true;
		}, false);
		_common.callAjax("/auth/hasAuth.json", { "authData" : "SMARTCCTV" }, function(json){
			if(json.result) evtSmart = true;
		}, false);
		_common.callAjax("/auth/hasAuth.json", { "authData" : "CCTVLOCK" }, function(json){
			if(json.result) evtLock = true;
		}, false);
		_common.callAjax("/auth/hasAuth.json", { "authData" : "CCTVPlay" }, function(json){
			if(json.result) evtCctvShare = true;
		}, false);*/
		//xeusJsonParser.getEventType()
		_common.callAjax("/eventList/getList.json", { "procSt" : "10", "dataLimit" : 'Y', "selectEvt" : 'Y' }, function(json) {

			if ( json.result === undefined ) {
				return false;
			}
			for(var i=0; i<json.result.length; i++){
				var $tbl = $(".searchWrapper").find("#evtTable");

				var Json = null;
				try {
					Json = JSON.parse(json.result[i]);
					xeusJsonParser.setJson(Json);
				} catch (e) {
					var date = new Date();
					console.log(date.formatYMDHMS(date.getYMDHMS()) + " Json Parse Error.");
					console.log(json.result[i]);
				}

				var isPass = false;
				var isTargetCheck = _common.utils.isNullAndEmpty(xeusJsonParser.getTargetGrp());
				var statEvetTypCd = xeusJsonParser.getStatEvetTypCd();
				if(statEvetTypCd == "NDMS119") isPass = evt119;
				if(statEvetTypCd == "NDPSWARN") isPass = evtDsc;
				if(statEvetTypCd == "CCTVLOCK") isPass = evtLock;
				if(statEvetTypCd == "CCTVSHER") isPass = evtCctvShare;
				if(statEvetTypCd == "SMARTCCTV") isPass = evtSmart;

				if(isPass){
					if(!isTargetCheck){
						if(!xeusJsonParser.getTargetGrp().contains(userGrpNo)){
							continue;
						}
					}
					var $tr = $("<tr></tr>").attr("k", xeusJsonParser.getUSvcOutbId()).data(Json);
					$tr.css({"cursor" : "pointer"}).click(function(){
						xeusJsonParser.setEventContent($(this).data());
					});
					if(xeusJsonParser.getProcSt() != "종료"){
						var $statEvetNm = $("<td class='tCenter'></td>").html("<div>" + xeusJsonParser.getEventType() + "</div>");
						$statEvetNm.find("div").css({
							"display": "inline-block",
							"text-overflow": "ellipsis",
							"overflow": "hidden",
							"white-space": "nowrap",
							"width": "120px",
							"padding-top": "6px"
						});
						var $procSt = $("<td class='tCenter'></td>").text(xeusJsonParser.getProcSt());

						var addr=xeusJsonParser.getAddr();
						if(xeusJsonParser.json['statEvetType']=='자연재난'){
							if(addr=='' || addr=="" || addr==null){
								addr=xeusJsonParser.json['statEvetCntn'].split('/')[1];
							}
						}

						var $outbPosNm = $("<td class='tCenter'></td>").html("<div>" + addr + "</div>");
						$outbPosNm.find("div").css({
							"display": "inline-block",
							"text-overflow": "ellipsis",
							"overflow": "hidden",
							"white-space": "nowrap",
							"width": "200px",
							"padding-top": "6px"
						});

						var $outbPos = $("<td class='tCenter'></td>").text(new Date().formatYMDHMS(xeusJsonParser.getYmd().substring(0, 14)));
						var $location = $("<td class='tCenter'></td>");
						var $btn = $("<img src='../res/img/places_normal.png'>").css({
							"cursor" : "pointer",
							"vertical-align" : "text-bottom"
						}).click(function(){
							var data = $(this).parent().parent().data();
							var targetMap = xeusLayout.mapService.getMap();
							var gmxMap = xeusLayout.mapService;

							if(data.outbPos[0].x==undefined || data.outbPos[0].x==null || data.outbPos[0].x=='' ||data.outbPos[0].y==undefined || data.outbPos[0].y==null || data.outbPos[0].y=='' ){
								if(data.statEvetTypCd == 'NDMS119'){

									eventVectorSource.clear();



//									targetMap.getView().setCenter([ 223038.97507357592, 441159.0711484331 ]);
//									targetMap.getView().setZoom(12);

									targetMap.getView().animate({
										center : [ 223038.97507357592, 441159.0711484331 ],
										zoom : 12,
										duration : 500
									});
								}else{
									alert('위치정보가 없습니다.');
									return;
								}
							}

							if ( statEvetTypCd === 'NDPSWARN' ) {
								xeusJsonParser.move(data, false, Json.statEvetCntn.replace(/ \/ /gi, '\r\n'));
							} else {
								xeusJsonParser.move(data);
							}
							//위치이동

							//xeusLayout.mapService.createNdmsPoint(Json.outbPos[0], Json.statEvetCntn);
						}).hover(function(){
							$(this).attr("src", "../res/img/places_over.png");
						}, function(){
							$(this).attr("src", "../res/img/places_normal.png");
						});

					/*	var $netBtn = $("<img src='../res/img/netwatch_btn.png'>").css({
							"width" : "16px",
							"height" : "16px",
							"cursor" : "pointer",
							"vertical-align" : "text-bottom"
						}).click(function(){
							var data = $(this).parent().parent().data();

							var _param = {};
							_param["srid"] = "4326";
							_param["lon"] = data.outbPos[0].x;
							_param["lat"] = data.outbPos[0].y;

							xeusCCTV.startNetMornitoring(JSON.stringify(_param));
						});
.append($netBtn)*/
						$location.append($btn);

						//$tr.append($statImg).append($statEvetNm).append($statEvetType).append($outbPosNm).append($outbPos).append($location);
						$tr.append($statEvetNm).append($procSt).append($outbPosNm).append($outbPos).append($location);
						$tbl.append($tr);
					}
				}
			}
		});
	},


	/**
	 * 이벤트 리스트 위젯을 생성합니다.
	 *  - 실시간
	 */
	getEventListWidget : function(){
		var $tbl = $(".searchWrapper").find("#evtTable");
		if(xeusJsonParser.isStart()){
			var Json = xeusJsonParser.getJson();

			var $outbPos = $("<td class='tCenter evtdat' k='"+xeusJsonParser.getYmd().substring(0, 14)+"'></td>").text(new Date().formatYMDHMS(xeusJsonParser.getYmd().substring(0, 14)));

			var $tr = $("<tr></tr>").attr("k", xeusJsonParser.getUSvcOutbId()).data(Json);
			$tr.css({"cursor" : "pointer"}).click(function(){
				xeusJsonParser.setEventContent($(this).data());
			});

			var $statEvetNm = $("<td class='tCenter'></td>").html("<div>" + xeusJsonParser.getEventType() + "</div>");
				$statEvetNm.find("div").css({
					"display": "inline-block",
					"text-overflow": "ellipsis",
					"overflow": "hidden",
					"white-space": "nowrap",
					"width": "80px",
					"padding-top": "6px"
				});

			var $procSt = $("<td class='tCenter'></td>").text(xeusJsonParser.getProcSt());
			var addr=xeusJsonParser.getAddr();
			if(xeusJsonParser.json['statEvetType']=='자연재난'){
				if(addr=='' || addr=="" || addr==null){
					addr=xeusJsonParser.json['statEvetCntn'].split('/')[1];
				}
			}
			var $outbPosNm = $("<td class='tCenter'></td>").html("<div>" + addr + "</div>");
			$outbPosNm.find("div").css({
				"display": "inline-block",
				"text-overflow": "ellipsis",
				"overflow": "hidden",
				"white-space": "nowrap",
				"width": "200px",
				"padding-top": "6px"
			});


			var $location = $("<td class='tCenter'></td>");
			var $btn = $("<img src='../res/img/places_normal.png'>").css({
				"cursor" : "pointer",
				"vertical-align" : "text-bottom"
			}).click(function(){
				if(Json.statEvetTypCd == "NDMS119"){
					if(Json.outbPos[0].x==undefined || Json.outbPos[0].x==null || Json.outbPos[0].x=='' ||Json.outbPos[0].y==undefined || Json.outbPos[0].y==null || Json.outbPos[0].y=='' ){
						alert('위치정보가 없습니다.');
						return;
					}
					xeusJsonParser.move(Json);
				}else{
					if((Json.tmx=="" || Json.tmx==null || Json.tmy=="" || Json.tmy==null) && (Json.outbPos[0].x==undefined || Json.outbPos[0].x==null || Json.outbPos[0].x=='' ||Json.outbPos[0].y==undefined || Json.outbPos[0].y==null || Json.outbPos[0].y=='')){
						alert('위치정보가 없습니다.');
						return;
					}
					xeusJsonParser.move(Json);
				}

			}).hover(function(){
				$(this).attr("src", "../res/img/places_over.png");
			}, function(){
				$(this).attr("src", "../res/img/places_normal.png");
			});

			/*var $netBtn = $("<img src='../res/img/netwatch_btn.png'>").css({
				"width" : "16px",
				"height" : "16px",
				"cursor" : "pointer",
				"vertical-align" : "text-bottom"
			}).click(function(){
				var _param = {};
				_param["srid"] = "4326";
				_param["lon"] = Json.outbPos[0].x;
				_param["lat"] = Json.outbPos[0].y;

				xeusCCTV.startNetMornitoring(JSON.stringify(_param));
			});append($netBtn)*/

			$location.append($btn);

			//$tr.append($statImg).append($statEvetNm).append($statEvetType).append($outbPosNm).append($outbPos).append($location);
			$tr.append($statEvetNm).append($procSt).append($outbPosNm).append($outbPos).append($location);
			var eventActiveChk = false;

			$("#evtTable").find('tbody tr').each(function(e, i){
				if ( xeusJsonParser.getUSvcOutbId() === $(this).attr('k') ) {
					$(this).remove();
					eventActiveChk = true;
				}
			});

				$tbl.prepend($tr);
				var duration = 200;
				for(var i=0; i<10; i++){
					$tr.animate({"color" : xeusJsonParser.getProcStColor()}, duration);
					$tr.animate({"color" : "black"}, duration);
				}


			$("#evtTable").find('tbody tr').each(function(e, i){
				if ( i > TIME_LIMIT ) {
					$(this).remove();
				}
			});


			$("#widget-point").find(".pointBtn").attr("active", "");
		}else if(xeusJsonParser.isChange()){
			var JSON = xeusJsonParser.getJson();
			$("#evtTable").find("tbody tr").each(function(){
				if($(this).data().uSvcOutbId == xeusJsonParser.getUSvcOutbId()){
					$(this).attr("k", xeusJsonParser.getUSvcOutbId()).data(JSON);
					$(this).find("td").eq(0).text(xeusJsonParser.getEventType());
					$(this).find("td").eq(1).text(xeusJsonParser.getProcSt());
					$(this).find("td").eq(2).text(xeusJsonParser.getAddr());
					$(this).find("td").eq(3).text(new Date().formatYMDHMS(xeusJsonParser.getYmd().substring(0, 14)));

					var duration = 200;
					for(var i=0; i<10; i++){
						$(this).animate({"color" : xeusJsonParser.getProcStColor()}, duration);
						$(this).animate({"color" : "black"}, duration);
					}

					var uSvcOutbId = $("#ctntTable").attr("uSvcOutbId");
					if(JSON.uSvcOutbId == uSvcOutbId){
						xeusJsonParser.setEventContent();
					}

					//TODO TTA 인증 후 제거
					//xeusJsonParser.move(JSON);
				}
			});
		}else{
			$("#evtTable").find("tbody tr").each(function(){
				if($(this).data().uSvcOutbId == xeusJsonParser.getUSvcOutbId()){
					var procSt = xeusJsonParser.getProcSt();
					$(this).find("td").eq(1).text(procSt);

					if(procSt == "종료"){
						$(this).remove();
					}else{
						var duration = 200;
						for(var i=0; i<10; i++){
							$(this).animate({"color" : xeusJsonParser.getProcStColor()}, duration);
							$(this).animate({"color" : "black"}, duration);
						}

						var uSvcOutbId = $("#ctntTable").attr("uSvcOutbId");
						if(xeusJsonParser.getJson().uSvcOutbId == uSvcOutbId){
							if(eventVectorSource) eventVectorSource.clear();
							Spatial.stopInterval();
							$("#ctntTable").find(".ctntTd").html("");
						}
					}
				}
			});
		}

		WIDGET.changeOrder();
	},

	/**
	 * 이벤트 발생위치로 이동합니다.
	 */
	moveSubEvent : function(xyArr){
		center = xyArr;
		if(center[0] > 0 && center[1] > 0){

			var targetMap = xeusLayout.mapService.getMap();

			var lnglat = Spatial.convertProjection(center, targetMap.getView().getProjection().getCode(), "EPSG:4326");
			var addr = Spatial.convertXYToAddr(lnglat[0], lnglat[1]);
			if(addr == "error") addr = "";

			var point = new ol.Feature(new ol.geom.Point(center));
			point.setStyle(new ol.style.Style({
				text : new ol.style.Text({
					text: addr,
					textAlign: "center",
					textBaseline: "hanging",
					offsetY: 30,
					font: "bold 15px arial",
					fill: new ol.style.Fill({
						color: "blue"
					}),
					stroke: new ol.style.Stroke({
						color: "#FFFFFF",
						width: 3
					})
				})
			}));

			eventVectorSource.clear();
			eventVectorSource.addFeature(point);
			Spatial.stopInterval();
			var color = '30';


			Spatial.animateInterval = setInterval(function(){
				setTimeout(function(){
					/*if(color == 'sb1_1'){
						color = 'sb1_2';
					}else{
						color = 'sb1_1';
					}*/
					point.setStyle(new ol.style.Style({

						image :
							 new ol.style.Icon(({
								//src : '../res/sym/evt/' + color + '.png'
								src: "../sym/getSymbol.do?mgrNo=" + smartCitySym[color]
							})),

							text : new ol.style.Text({
								text: addr,
								textAlign: "center",
								textBaseline: "hanging",
								offsetY: 30,
								font: "bold 15px arial",
								fill: new ol.style.Fill({
									color: "blue"
								}),
								stroke: new ol.style.Stroke({
									color: "#FFFFFF",
									width: 3
								})
							}),
							zindex: 10000
					}));
				}, 500);
			}, 500);

			var moveCenter = center;
			moveCenter = [center[0], center[1]];
			targetMap.getView().animate({
				center : moveCenter,
				zoom : 19,
				duration : 1000
			});
		}else{
			var date = new Date();
			try {
				//	console.log(date.formatYMDHMS(date.getYMDHMS()) + " Json > outbPos(" + JSON.outbPos.toString() + ") Error.");
			} catch (e) {
				//	console.log(date.formatYMDHMS(date.getYMDHMS()) + " Json > outbPos Error.");
				//	console.log(this.json);
			}
		}
	},

	showEventPopup : function(){

		_common.callAjax("/eventList/getList.json", { "procSt" : "10", "limit" : '5', "offset" : '0' }, function(json) {

			$('.show_event_form table').html('');
			$('.show_event_form').removeClass("hidden");
			$('.show_event_form').find("#overlay-west-bar").removeClass("hidden");

			if ( json.result === undefined ) {
				return false;
			}

			var str = '';

			var $target = $('.show_event_form table');
			for(var i=0; i<json.result.length; i++){
				var obj = JSON.parse(json.result[i]);
				var key = obj.uSvcOutbId;
				var nm = obj.statEvetNm;

				var addr=addr = obj.outbPosNm;
				if(obj.statEvetType=='자연재난'){
					if(obj.outbPosNm==""||obj.outbPosNm==null||obj.outbPosNm==undefined){
						addr = obj.statEvetCntn.split("/")[1];
					}
				}

				var lon = obj.outbPos[0].y;
				var lat = obj.outbPos[0].x;


				var $tr = $('<tr class="show_event_key" key="'+key+'">');
				var $posi = $('<td class="show_event_posi" lat="'+lat+'" lon="'+lon+'">'+nm+' ('+addr+')</td>').click(function(){
					var targetMap = xeusLayout.mapService.getMap();
					var posilon = $(this).attr('lat');
					var posilat = $(this).attr('lon');

					WIDGET.moveSubEvent(Spatial.convertProjection([posilon, posilat], "EPSG:4326", targetMap.getView().getProjection().getCode()));

				});


				var $play = $('<td width="30" lat="'+lat+'" lon="'+lon+'"><button class="show_event_play" ><img class="cctv-overlay-content-img" src="/xeus/res/img/pop_play.png" style="width:16px; height:16px;"></button></td>').click(function(){
					var targetMap = xeusLayout.mapService.getMap();

					var posilon = $(this).attr('lat');
					var posilat = $(this).attr('lon');

					if(posilon=='' || posilon==null || posilat=='' || posilat==null){
						alert('위치정보가 없습니다.');
						return;
					}
					var param = '{"srid":4326,"angle":0,"stateCd":"정상","isError":false,"lat":"'+posilat+'","lon":"'+posilon+'"}';
					console.log(param);
					console.log('=====================');
					param = (encodeURIComponent(param));
					var _param = JSON.parse(decodeURIComponent(param));
					xeusCCTV.startNetMornitoring(param);

				});//str += '<button class="show_event_remove" key="'+key+'"><img class="" src="/xeus/res/img/close.png" style="width:16px; height:16px;"></button></td>';
				$tr.append($posi).append($play);
				$target.append($tr);

			}

		});
	}
};
