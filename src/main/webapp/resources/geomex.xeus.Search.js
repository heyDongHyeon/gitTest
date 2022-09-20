if(window._search == null) var _search = {

	//지번주소검색
	getAddrSearchList : function(_param){
		_common.callAjax("/search/getAddrSearchList.json", _param, function(json){
			//console.log(json);
			var $wrap = $("#"+parentView+" #srchResult");
			var $div = $("<div id='overWrap' style='overFlow:auto;'></div>").css("overflow", "auto");
			var $head = $("<div id='searchHead'><h2>검색결과</h2><button id='searchToggle' class='blueBtn'>닫기</button></div>");
			var $table = $("<table></table>");
			if(json.result.length == 0){
				var $tr = $("<tr class = 'resultTableTr'></tr>");
				var $td = $("<td class = 'resultTableTdName'>검색결과가 존재하지 않습니다.</td>");
				$tr.append($td);
				$table.append($tr);
			}else{
				for(var i=0; i<json.result.length; i++){
					var jibun = json.result[i].emdName + " " +json.result[i].jibun;
					var doro = _common.utils.validNull(json.result[i].bldgNo);
					if(doro != ""){
						doro = "<br>" + json.result[i].emdName + " " + doro;
					}
					var pnu = json.result[i].pnu;

					var $tr  = $("<tr class = 'resultTableTr'></tr>");
					var $td1 = $("<td class = 'resultTableTdName'>" + jibun + doro + "</td>");
					var $td2 = $("<td class = 'resultTableTdButton'><button class='blueBtn moveLocation'>위치</button></td>").attr("key", "pnu")
																					   .attr("val", pnu)
																					   .attr("text", jibun)
					$tr.append($td1).append($td2);
					$table.append($tr);
				}
			}

			$head.append($table);
			$div.html($head);
			$wrap.html($div);
			xeusSearch.resize_search();

			$head.on('click', function(){
				$wrap.html('');
			});
		});
	},

	//새주소 검색
	getNewAddrSearchList : function(_param){
		_common.callAjax("/search/getNewAddrSearchList.json", _param, function(json){
			var $wrap = $("#"+parentView+" #srchResult");
			var $div = $("<div id='overWrap' style='overFlow:auto;'></div>").css("overflow", "auto");
			var $head = $("<div id='searchHead'><h2>검색결과</h2><button id='searchToggle' class='blueBtn'>닫기</button></div>");
			var $table = $("<table></table>");
			if(json.result.length == 0){
				var $tr = $("<tr class = 'resultTableTr'></tr>");
				var $td = $("<td class = 'resultTableTdName'>검색결과가 존재하지 않습니다.</td>");
				$tr.append($td);
				$table.append($tr);
			}else{
				for(var i=0; i<json.result.length; i++){
					var jibun = _common.utils.validNull(json.result[i].lnAddr);
					if(jibun != ""){
						jibun = "<br>" + jibun;
					}
					var doro = json.result[i].rdSeLbl;
					var gid = json.result[i].gid;

					var $tr  = $("<tr class = 'resultTableTr'></tr>");
					var $td1 = $("<td class = 'resultTableTdName'>" + doro + jibun + "</td>").width(227);
					var $td2 = $("<td class = 'resultTableTdButton'><button class='blueBtn moveLocation'>위치</button></td>").attr("key", "gid")
																					   .attr("val", gid)
																					   .attr("text", doro)
					$tr.append($td1).append($td2);
					$table.append($tr);
				}
			}
			$head.append($table);
			$div.html($head);
			$wrap.html($div);
			xeusSearch.resize_search();

			$head.on('click', function(){
				$wrap.html('');
			});

		});
	},

	//통합검색
	getApiSearchList : function(type, str){
		var key = "043cf003ea7e5ecff70b2e5cf9a9cd5e";
		var url;
		if(type == "local") url = "https://apis.daum.net/local/v1/search/keyword.json?apikey=" + key + "&output=json&query=" + str;
		if(type == "addr")  url = "https://apis.daum.net/local/geo/addr2coord?apikey=" + key + "&output=json&q=" + str;
		$.ajax({
			url      : url,
			dataType : "jsonp",
			success  : function(json){
				var $wrap = $("#"+parentView+" #srchResult");
				var $div = $("<div id='overWrap' style='overFlow:auto;'></div>").css("overflow", "auto");
				var $head = $("<div id='searchHead'><h2>검색결과</h2><button id='searchToggle' class='blueBtn'>닫기</button></div>");
				var $table = $("<table></table>");

				if(json.channel.item.length == 0){
					var $tr = $("<tr class = 'resultTableTr'></tr>");
					var $td = $("<td class = 'resultTableTdName'>검색결과가 존재하지 않습니다.</td>");
					$tr.append($td);
					$table.append($tr);
				}else{
					for(var i=0; i<json.channel.item.length; i++){
						var jibun = json.channel.item[i].address;
						var doro = json.channel.item[i].newAddress;
						var name = json.channel.item[i].title;
						var fullName = name + "<br>" + _common.utils.validNull(jibun);
						var lat = json.channel.item[i].latitude;
						var lng = json.channel.item[i].longitude;
						if(lat == null){
							lat = json.channel.item[i].lat;
						}
						if(lng == null){
							lng = json.channel.item[i].lng;
						}

						var $tr  = $("<tr class = 'resultTableTr'></tr>");
						var $td1 = $("<td class = 'resultTableTdName'>" + fullName + "</td>");
						var $td2 = $("<td class = 'resultTableTdButton'><button class='blueBtn'>위치</button></td>").attr("lng", lng)
																	  .attr("lat", lat)
																	  .attr("text", fullName.replace("<br>", "\n"));
						$td2.click(function(){
							var xy = [$(this).attr("lng"), $(this).attr("lat")];
							var text = $(this).attr("text");
							var b_epsg = "EPSG:4326";
							var t_epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
							var center = Spatial.convertProjection(xy, b_epsg, t_epsg);
							_search.moveLocation(center, text);
						});
						$tr.append($td1).append($td2);
						$table.append($tr);
					}
				}
				$head.append($table);
				$div.html($head);
				$wrap.html($div);
				xeusSearch.resize_search();

				$head.on('click', function(){
					$wrap.html('');
				});

			},
			error    : function(xhr, status, error){
				alert("서버에 요청중 문제가 발생했습니다.\n관리자에게 문의하여 주십시오.");
			}
		});
	},

	//좌표검색 TM->경위도
	getTmToLngLat : function(_param){
		var xy = [Number(_param.tmX), Number(_param.tmY)];
		var b_epsg = _param.epsg;
		var t_epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
		var center = Spatial.convertProjection(xy, b_epsg, t_epsg);

		var lnglat = Spatial.convertProjection(center, t_epsg, "EPSG:4326");
		//4326 = 경위도
		var $wrap = $("#"+parentView+" #srchResult");
		var $div = $("<div id='overWrap' style='overFlow:auto;'></div>").css("overflow", "auto");
		var $table = $("<table></table>");

		var lng = lnglat[0];
		var lat = lnglat[1];

		var $dgr1 = $("<tr><th colspan='2'>Degree(도)</th></tr>");
		var $dgr2 = $("<tr><td>경도</td><td>" + lng + "</td></tr>");
		var $dgr3 = $("<tr><td>위도</td><td>" + lat + "</td></tr>");
		$table.append($dgr1).append($dgr2).append($dgr3);

		var $dms1 = $("<tr><th colspan='2'>DMS(도분초)</th></tr>");
		var $dms2 = $("<tr><td>경도</td><td>" + Spatial.convertDegToDMS(lng) + "</td></tr>");
		var $dms3 = $("<tr><td>위도</td><td>" + Spatial.convertDegToDMS(lat) + "</td></tr>");
		$table.append($dms1).append($dms2).append($dms3);
		var $td2 = $("<tr><td colspan='2'><button class='blueBtn'>위치</button></td></tr>").css("text-align", "center");
		$td2.click(function(){
			_search.moveLocation(center);
		});
		$table.append($td2);
		$div.html($table);
		$wrap.html($div);

		xeusSearch.resize_search();

		$head.on('click', function(){
			$wrap.html('');
		});

	},

	//좌표검색 경위도->TM
	getLngLatToTm : function(_param){
		var xy = [Number(_param.lng), Number(_param.lat)];
		var b_epsg = "EPSG:4326";
		var t_epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
		var center = Spatial.convertProjection(xy, b_epsg, t_epsg);
		var convertCenter = Spatial.convertProjection(center, t_epsg, "EPSG:5186");

		var $wrap = $("#"+parentView+" #srchResult");
		var $div = $("<div id='overWrap' style='overFlow:auto;'></div>").css("overflow", "auto");
		var $head = $("<div id='searchHead'><h2>검색결과</h2><button id='searchToggle' class='blueBtn'>닫기</button></div>");
		var $table = $("<table></table>");

		var $td2 = $("<tr><td colspan='2'><button class='blueBtn locaBtn'>위치</button></td></tr>").css("text-align", "center");
		$td2.click(function(){
			_search.moveLocation(center);
		});
		$table.append($td2);
		var $dms1 = $("<tr><th colspan='2' class = 'resultTableTdNames'>*DMS(도분초)</th></tr>");
		var $dms2 = $("<tr><td class = 'resultTableTdNames'>경도  :  </td><td class = 'resultTableTdNames'>" + Spatial.convertDegToDMS(xy[0]) + "</td></tr>");
		var $dms3 = $("<tr><td class = 'resultTableTdNames'>위도  :  </td><td class = 'resultTableTdNames'>" + Spatial.convertDegToDMS(xy[1]) + "</td></tr>");
		$table.append($dms1).append($dms2).append($dms3);

		$("#tmUI").find("#epsg").find("option").each(function(){
			var epsg = $(this).val();
			var epsgTxt = $(this).text();
			var convertCenter = Spatial.convertProjection(center, t_epsg, epsg);
			var $tr1 = $("<tr><th colspan='2'>" + epsgTxt + "</th></tr>");
			var $tr2 = $("<tr><td>X</td><td>" + convertCenter[0] + "</td></tr>");
			var $tr3 = $("<tr><td>Y</td><td>" + convertCenter[1] + "</td></tr>");
			$table.append($tr1).append($tr2).append($tr3);
		});
		$head.append($table);
		$div.html($head);
		$wrap.html($div);

		xeusSearch.resize_search();

		$head.on('click', function(){
			$wrap.html('');
		});

	},

	//위치버튼이벤트(위치값 가져옴)
	getLocation : function(_param){
		_common.callAjax("/search/getLocation.json", _param, function(json){
			var array = json.result[0].center.split(",");
			var xy = [Number(array[0]), Number(array[1])];
			_search.moveLocation(xy, _param.text);
		});
	},

	//위치버튼이벤트(위치로 이동, 마커 & 글자 레이어)
	moveLocation : function(xy, text){
		if(this.locVector != null && this.locVector instanceof ol.layer.Vector){
			xeusLayout.mapService.getMap().removeLayer(this.locVector);
			this.locVector.getSource().clear();
			this.locVector = null;
		}

		text = _common.utils.validNull(text);
		xy = [Number(xy[0]), Number(xy[1])];

		this.locVector = new ol.layer.Vector({
			source : new ol.source.Vector({
				features: [new ol.Feature({
					geometry: new ol.geom.Point(xy),
					name: "SearchVector"
				})]
			}),
			style : new ol.style.Style({
				image: new ol.style.Circle({
					radius: 15,
					fill: new ol.style.Fill({
						color: 'rgba(0, 0, 255, 0.1)'
					}),
					stroke: new ol.style.Stroke({
						color: 'red',
						width: 5
					})
				})
				,text : new ol.style.Text({
					text: text,
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
			})
			/*style : new ol.style.Style({
				image: new ol.style.Icon({
					src: '/xeus/res/img/bottom_btn.png'
				}),
				text : new ol.style.Text({
					text: text,
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
			})*/
		});

		xeusLayout.mapService.getMap().addLayer(this.locVector);
		xeusLayout.mapService.getMap().getView().setCenter([(xy[0]), xy[1]]);
		xeusLayout.mapService.getMap().getView().setZoom(15);
	},

	//위치버튼이벤트(위치로 이동, 마커 & 글자 레이어)
	moveMapCenter : function(){
		if(this.locVector != null && this.locVector instanceof ol.layer.Vector){
			xeusLayout.mapService.getMap().removeLayer(this.locVector);
			this.locVector.getSource().clear();
			this.locVector = null;
		}

		xeusLayout.mapService.getMap().getView().animate({
			center : [ 223038.97507357592, 441159.0711484331 ],
			zoom : 12,
			duration : 500
		});

	}
};
