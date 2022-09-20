/**
 * <pre>
 * 레이어 관리 객체 입니다.
 *
 * 레이어 생성 메소드 호출 전,
 * <b style="color: red;">xeusLayout.initLayer("그룹종류");</b> 를 호출해야 합니다.
 * </pre>
 *
 * @dependency xeusLayerList.js (Layers)
 * @author 이주영
 */
if(window.XeusLayer == null) var XeusLayer = {

	/**
	 * 범례 엘리먼트 셀렉터 입니다.
	 */
	legendSelector : null,

	/**
	 * 범례를 설정합니다.
	 *
	 * 선택)DOM - 생성 대상 셀렉터(String)
	 */
	createLegend : function(DOM){
		var _Selector = DOM;
		if(_Selector == null || _Selector == "") _Selector = "#overlay-west-contents";
		_Selector = "#" + parentView + " " + _Selector;
		if ( $(_Selector).length > 1 ) {
			$(_Selector).eq(1).remove();
		}

		this.legendSelector = $(_Selector);
		this.legendSelector.html("");

		var $parentDiv = $("<div id='layer-over-wrap' onSelectStart='return false'></div>");

		var _Group = this.getLayerGroup();
		for(var i=0; i<_Group.length; i++){
			var $div = $("<div class='groups' val='" + _Group[i] + "' style='margin-bottom: 10px;'></div>");
			var $p = $("<p class='group-title'>" + _Group[i] + "</p>").prepend("<span mode='open'><span style='font-size: 12px;'>▶</span> &nbsp;</span>");
			$div.append($p);

			$parentDiv.append($div);
		}

		var eliCdeList = _common.getCodeByGroup("C68");
		var govCdeList = _common.getCodeByGroup("C70");
		var vEliStat = this.convertList(eliCdeList);
		var vGovOffice = this.convertList(govCdeList);

		/*var _Layers = xeusLayout.mapService.getAllLayers();*/
		var _Layers = $(".tab[target=" + parentView + "]").data("map").getAllLayers();
		for(var i=0; i<_Layers.length; i++){
			if(_Layers[i].get("type") != null){
				var fullName = _Layers[i].get("fullName");
				var isActive = Layers[fullName].state == "active" ? true : false;
				if(isActive){
					var key = parentView + "-" + _Layers[i].get("name");
					var group = _Layers[i].get("group");
					var shortcut = Layers[fullName].shortcut;

					var isCheck = "";
					if(_Layers[i].getVisible()) isCheck = "checked";
					var LayerNm = _Layers[i].get("name").toUpperCase();
					if ( LayerNm === 'CCTV' ) LayerNm = '재난재해감시용 CCTV';
					var $div = $("<div class='legends'></div>").attr("key", key);
					var $layer = $("<p></p>").append("<input type='checkbox' id='" + key + "' class='layer' " + isCheck + ">")
											 .append(shortcut)
											 .append("<label for='" + key + "'>&nbsp;" + LayerNm+ "</label>");

					$div.append($layer);
					$parentDiv.find(".groups[val='" + group + "']").append($div);
					//$parentDiv.prepend($div);

					if(Layers.LayerTheme[fullName] != null){
						var $ul = $("<ul></ul>");
						for(var thmKey in Layers.LayerTheme[fullName]){
							var color = Layers.LayerTheme[fullName][thmKey];
							var isImg = Layers[fullName].getThemeImg != null;
							var $shortcut = $(Layers[fullName].shortcut);
							if(isImg){
								$shortcut = $("<img src='" + Layers[fullName].getThemeImg(color) + "'>").css({
									"width" : "30px",
									"height" : "30px",
									"cursor" : "pointer",
									"vertical-align" : "middle",
									"margin" : "0px 10px"
								}).click(function(){
									$(this).next().click();
								});
							}else{
								color = Layers.LayerTheme[fullName][thmKey];
								$shortcut.children().css("stroke", color);
							}
							var $li = $("<li></li>");

							var thmNm = thmKey;
							if(fullName == "v_evt_stat") thmNm = vEliStat[thmKey];
							else if(fullName == "v_gov_office") thmNm = vGovOffice[thmKey];
							$li.append("<input type='checkbox' class='" + fullName + "' id='" + fullName + "_" + thmKey + "' value='" + thmKey + "' " + isCheck + ">")
								.append($shortcut)
								.append("<label for='" + fullName + "_" + thmKey + "'>" + thmNm + "</label>");

							//if(isImg) $li.css("padding", "10px 0px 10px 0px");
							$ul.append($li);
						}
						$div.append($ul);
						if(Layers.LayerTheme[fullName] != null){
							$layer.find("label").eq(0).removeAttr("for").css({
								"cursor" : "pointer"
							}).on("click", function(){
								$(this).parent().next().toggle();
							});
						}
					}

					if(isCheck && fullName == "asset_pump_sec") $(".tab[target=" + parentView + "]").data("map").getMap().on("pointermove", Public.NMS.WaterPump.CreateTooltip().Start);
					if(isCheck && fullName == "asset_rainfall") Public.NMS.RainFall.Start();
				}
			}
		}
		$(_Selector).append($parentDiv).find("ul").toggle();

		$parentDiv.find("div.groups").each(function(){
			if($(this).children(".legends").length == 0){
				$(this).remove();
			}
		});

		$parentDiv.find(".group-title").click(function(){
			$(this).parent().find(".legends").toggle();
			var $span = $(this).find("span");
			if($span.attr("mode") == "open"){
				$span.attr("mode", "close").text("▼ ")
			}else if($span.attr("mode") == "close"){
				$span.attr("mode", "open").text("▶ ")
			}
		});

		$parentDiv.find(".legends svg, .sym-icon").click(function(){
			$(this).next().click();
		});

		$(document).on("change", "input.layer", function(){

			var key = $(this).attr("id");
			key = key.split("-")[1];
			/*
			 * 180115 이은규
			 * cctv레이어의 범주가 변경되면 열려있는 cctv-overlay-content를 닫는다.
			 */
			if (key == 'cctv')	$('#cctv-overlay-closer')[0].click();

			if($(this).is(":checked")){
				/*xeusLayout.mapService.setLayerVisible(key, true);*/
				$(".tab[target=" + parentView + "]").data("map").setLayerVisible(key, true);
				$(this).parent().next().find("input[type=checkbox]").prop("checked", true);

				if(key == "펌프장하천구간") $(".tab[target=" + parentView + "]").data("map").getMap().on("pointermove", Public.NMS.WaterPump.CreateTooltip().Start);
				if(key == "강우량") Public.NMS.RainFall.Start();
			}else{
				/*xeusLayout.mapService.setLayerVisible(key, false);*/
				$(".tab[target=" + parentView + "]").data("map").setLayerVisible(key, false);
				$(this).parent().next().find("input[type=checkbox]").prop("checked", false);

				if(key == "펌프장하천구간") Public.NMS.WaterPump.Stop();
				if(key == "강우량") Public.NMS.RainFall.Stop();
			}

			if(key == "cctv" && $(this).is(":checked")){
				var array = new Array();
				$("#" + parentView).find(".groups ul li input[type=checkbox].asset_cctv").each(function(){
					if($(this).is(":checked")){
						array.push($(this).val());
					}
				});

				Layers["asset_cctv"].loadFunction(
					/*xeusLayout.mapService.getLayerByName(Layers["asset_cctv"]["name"]),*/
					$(".tab[target=" + parentView + "]").data("map").getLayerByName(Layers["asset_cctv"]["name"]),
					array.toString()
				);
			}
		});

		$("#" + parentView).find(".groups ul li input[type=checkbox]").on("change", function(){
			/*
			 * 180115 이은규
			 * cctv레이어의 범주가 변경되면 열려있는 cctv-overlay-content를 닫는다.
			 */
			var lengendsKey = $(this).parent().parent().parent().attr('key');
			if (lengendsKey == 'cctv')	$('#cctv-overlay-closer')[0].click();

			var key = $(this).attr("class");

			var array = new Array();
			$("#" + parentView).find(".groups ul li input[type=checkbox]." + key).each(function(){
				if($(this).is(":checked")){
					array.push($(this).val());
				}
			});


			if(array.length == 0){
				array = "null";
				$(this).parent().parent().prev().find("input[type=checkbox]").prop("checked", false);
			}else{
				$(this).parent().parent().prev().find("input[type=checkbox]").prop("checked", true);
			}

			//console.log(".tab[target=" + parentView + "]");
			Layers[key].loadFunction(
				//xeusLayout.mapService.getLayerByName(Layers[key]["name"]),
				$(".tab[target=" + parentView + "]").data("map").getLayerByName(Layers[key]["name"]),
				array.toString()
			)
		});

		return this;
	},

	/**
	 * 컬럼, 값을 받아 필터 스트링을 생성합니다.
	 *
	 * @returns {String}
	 * @deprecated
	 */
	makeFilter : function(col, val){
		var filter;
		filter  = '<ogc:Filter>';
		filter += 		"<ogc:PropertyIsEqualTo>";
		filter += 			"<ogc:PropertyName>" + col + "</ogc:PropertyName>";
		filter += 			"<ogc:Literal>" + val + "</ogc:Literal>";
		filter += 		"</ogc:PropertyIsEqualTo>";
		filter += "</ogc:Filter>";

		return filter;
	},

	/**
	 * 전체 레이어 리스트에서 레이어 그룹을 추출합니다.
	 *
	 * @returns {Array}
	 */
	getLayerGroup : function(){
		var array = new Array();
		/*var _Layers = xeusLayout.mapService.getAllLayers();*/
		var _Layers = $(".tab[target=" + parentView + "]").data("map").getAllLayers();
		for(var i=0; i<_Layers.length; i++){
			if(_Layers[i].get("type") != null){
				var group = _Layers[i].get("group");

				if(!_common.utils.isNullAndEmpty(group)){
					var isContains = false;
					for(var l=0; l<array.length; l++){
						if(array[l] == group) isContains = true;
						if(isContains) break;
					}

					if(!isContains) array.push(_Layers[i].get("group"));
				}
			}
		}

		return array;
	},

	/**
	 * 주어진 기능 그룹명이 해당 레이어의 기능 그룹에 존재하는지 추출합니다.
	 *
	 * @param fn
	 * @param fnGroup
	 * @returns {Boolean}
	 */
	isFnGroupContains : function(fn, fnGroup){
		var bool = false;

		if(fnGroup instanceof Array){
			for(var i=0; i<fnGroup.length; i++){
				if(fn === fnGroup[i]){
					bool = true;
					break;
				}
			}
		}else if(fn === fnGroup){
			bool = true;
		}

		return bool;
	}
	,

	/**
	 * 범례 명을 표시하기 위해 코드 리스트를 가져옵니다.
	 *
	 * @param list
	 * @returns rst
	 */
	convertList : function(_list){
		var _rst = {};
		for(var i=0; i<_list.length; i++){
			_rst[_list[i].cdeCde] = _list[i].cdeNm;
		}
		return _rst;
	},

}