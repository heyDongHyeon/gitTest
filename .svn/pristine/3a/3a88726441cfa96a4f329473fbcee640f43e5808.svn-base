/**
 * <pre>
 * 레이어 목록 객체 입니다.
 * OL Vector, Tile 객체 생성에 필요한 옵션을 지정하며,
 * 기본 프로퍼티 외에 범례 생성에 필요한 옵션이 추가되었습니다.
 *
 * 예) type		  : 공간객체 타입 (String)
 * 	   tile		  : tile 객체 (ol.layer.Tile Wrapper)
 * 	   shortcut   : 범례 숏컷 엘리먼트 (img or svg)
 *     selectable : Vector 선택 가능 여부 (boolean)
 *     group	  : 그룹명
 *     fnGroup	  : 기능 그룹(상단 대메뉴)
 * 2017.11.21      김경호     state : active/inactive 항목 추가
 *                            범례는 state가 active인것만 보여주면 됨.
 * </pre>
 *
 * @author 이주영
 */
if(window.LayerConst == null) var LayerConst = {
	URL : _common.context() + "/wfs",
	TMS : "TMS",
	HEAT : "HEAT",
	POINT : "POINT",
	MULTIPOLYGON : "MULTIPOLYGON",
	MULTILINESTRING : "MULTILINESTRING",
	/**
	 * <pre>
	 * 주제도를 서버에서 받아옵니다.
	 * 서버는 Key : Val 형식으로 리턴해야 합니다.
	 *
	 * 예) { "096_행망" : "#A50021" }
	 * </pre>ㅂ
	 * @param LayerName - 레이어 명칭(String)
	 */
	ThemeLoad : function(LayerName){
		_common.callAjax(Layers[LayerName]["themeUrl"], null, function(json){
			Layers.LayerTheme[LayerName] = json.result;
		});
	}
};

if(window.LayerFnGroup == null) var LayerFnGroup = {
	CMMN : "공통",
	CCTV : "CCTV",
	NMS  : "NMS",
	INF  : "기반시설",
	NDMS  : "NDMS",
	BOAD : "통합보드",
	EVT  : "연계서비스",
	DATA : "빅데이터"
};

	if(window.cctvSymIcon == null) {
		var cctvSymIcon = {};
		//정렬조건을 넣지 않으면 순서대로 정렬되서 나오기 때문에 바로 넣으면 됨.
		/*_common.callAjax("/symIcon/getSymIconList.json", {'symGrp' : 'ctv'}, function(json){
			var obj = {};
			$.each(json.result,function(key,value) {
				if(cctvSymIcon[value.gbnCd] === undefined || cctvSymIcon[value.gbnCd] == null){
					obj = {};
				} else {
					obj = cctvSymIcon[value.gbnCd];
				}
				obj[value.iconTyp] = value.fileNm;
				cctvSymIcon[value.gbnCd] = obj;
			});
		}, false);*/

		_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'asset_cctv'}, function(json) {
			if(json.result){
				var obj = {};
				for(var i=0; i<json.result.length; i++){
					obj[json.result[i].gbnCd] = json.result[i].symMgrNo;
				}
				cctvSymIcon = obj;
			}
		}, false);
	}
	/*
	if(window.nmsSymIcon == null) {
		var nmsSymIcon = {};
		//정렬조건을 넣지 않으면 순서대로 정렬되서 나오기 때문에 바로 넣으면 됨.
		_common.callAjax("/symIcon/getSymIconList.json", {'symGrp' : 'nms'}, function(json){
			var obj = {};
			$.each(json.result,function(key,value) {
				if(nmsSymIcon[value.gbnCd] === undefined || nmsSymIcon[value.gbnCd] == null){
					obj = {};
				} else {
					obj = nmsSymIcon[value.gbnCd];
				}
				obj[value.iconTyp] = value.fileNm;
				nmsSymIcon[value.gbnCd] = obj;
			});
		}, false);
		_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'asset_infra'}, function(json){
			if(json.result){
				var obj = {};
				for(var i=0; i<json.result.length; i++){
					obj[json.result[i].gbnCd] = json.result[i].symMgrNo;
				}
				nmsSymIcon = obj;
			}
		}, false);
	}*/
	/*
	if(window.evtStatSymIcon == null) {
		var evtStatSymIcon = {};
		_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'v_evt_stat'}, function(json){
			if(json.result){
				var obj = {};
				for(var i=0; i<json.result.length; i++){
					obj[json.result[i].gbnCd] = json.result[i].symMgrNo;
				}
				evtStatSymIcon = obj;
			}
		}, false);
	}
	*/

	if(window.smartCctvSym == null) {
		var smartCctvSym = {};
		_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'asset_smart_cctv'}, function(json) {
			if(json.result){
				smartCctvSym = json.result[0].symMgrNo;
			}
		}, false);
	}

	if(window.smartCitySym == null) {
		var smartCitySym = {};
		_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'smart_city'}, function(json) {
			if(json.result){
				var obj = {};
				for(var i=0; i<json.result.length; i++){
					obj[json.result[i].gbnCd] = json.result[i].symMgrNo;
				}
				smartCitySym = obj;
			}
		}, false);
	}

	if(window.disbordSymIcon == null) {
		var disbordSymIcon = {};
		_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'asset_disbord'}, function(json) {
			if(json.result){
				disbordSymIcon = json.result[0].symMgrNo;
			}
		}, false);
	}

	if(window.voiceSymIcon == null) {
		_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'asset_eqb_voice'}, function(json) {
			if(json.result){
				voiceSymIcon = json.result[0].symMgrNo;
			}
		}, false);
	}

	if(window.awsSymIcon == null) {
		_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'asset_eqb_aws'}, function(json) {
			if(json.result){
				awsSymIcon = json.result[0].symMgrNo;
			}
		}, false);
	}

	if(window.heatSymIcon == null) {
		heatSymIcon = 'SYM0000085';
	}
	/*
	kais_sig_as
	kais_emd_as
	kais_li_as
	kras_cbnd_as
	kais_buld_as
	kais_eqb_as
	kais_manage_ls*/
	if(window.kaisSigAs == null) {
		_common.callAjax("/lyrmgr/getLyrStyle.json", {'lyrNm': 'kais_sig_as'}, function(json) {
			kaisSigAs = {};
			if(json.result){
				kaisSigAs = json.result;
			} else {
				kaisSigAs = {
						line_color : '#000'
				}
			}
		}, false);
	}

	if(window.kaisEmdAs == null) {
		_common.callAjax("/lyrmgr/getLyrStyle.json", {'lyrNm': 'kais_emd_as'}, function(json) {
			kaisEmdAs = {};
			if(json.result){
				kaisEmdAs = json.result;
			} else {
				kaisEmdAs = {
						line_color : '#000'
						, area_color : '#000'
				}
			}
		}, false);
	}

	if(window.kaisLiAs == null) {
		_common.callAjax("/lyrmgr/getLyrStyle.json", {'lyrNm': 'kais_li_as'}, function(json) {
			kaisLiAs = {};
			if(json.result){
				kaisLiAs = json.result;
			} else {
				kaisLiAs = {
						line_color : '#000'
						, area_color : '#000'
				}
			}
		}, false);
	}

	if(window.krasCbndAs == null) {
		_common.callAjax("/lyrmgr/getLyrStyle.json", {'lyrNm': 'kras_cbnd_as'}, function(json) {
			krasCbndAs = {};
			if(json.result){
				krasCbndAs = json.result;
			} else {
				krasCbndAs = {
						line_color : '#000'
						, area_color : '#000'
				}
			}
		}, false);
	}

	if(window.kaisBuldAs == null) {
		_common.callAjax("/lyrmgr/getLyrStyle.json", {'lyrNm': 'kais_buld_as'}, function(json) {
			kaisBuldAs = {};
			if(json.result){
				kaisBuldAs = json.result;
			} else {
				kaisBuldAs = {
						line_color : '#000'
						, area_color : '#000'
				}
			}
		}, false);
	}

	if(window.kaisEqbAs == null) {
		_common.callAjax("/lyrmgr/getLyrStyle.json", {'lyrNm': 'kais_eqb_as'}, function(json) {
			kaisEqbAs = {};
			if(json.result){
				kaisEqbAs = json.result;
			} else {
				kaisEqbAs = {
						line_color : '#000'
						, area_color : '#000'
				}
			}
		}, false);
	}

	if(window.kaisManageLs == null) {
		_common.callAjax("/lyrmgr/getLyrStyle.json", {'lyrNm': 'kais_manage_ls'}, function(json) {
			if(json.result){
				kaisManageLs = json.result;
			} else {
				kaisManageLs = {
						line_color : '#000'
						, area_color : '#000'
				}
			}
		}, false);
	}

/*
if(window.pumpSymIcon == null) {
	_common.callAjax("/sym/getLyrSymList.json", {'lyrNm': 'asset_pump'}, function(json) {
		if(json.result){
			pumpSymIcon = json.result[0].symMgrNo;
		}
	}, false);
}*/

if(window.Layers == null) var Layers = {

	daum_tile_map : {
		state : 'active',
		group : "배경지도",
		fnGroup : LayerFnGroup.CMMN,
		type : LayerConst.TMS,  //2017.11.21 by khkim 추가
		tile : new geomex.xeus.tms.DaumMap(),
		shortcut : "<img src='" + _common.context() + "/res/sym/lyr/v0.png' class='sym-icon' style='height: 20px !important;'>"
	},

	daum_map : {
		state : 'active',
		group : "배경지도",
		fnGroup : LayerFnGroup.CMMN,
		type : LayerConst.TMS,  //2017.11.21 by khkim 추가
		tile : new geomex.xeus.tms.DaumMap(),
		shortcut : "<img src='" + _common.context() + "/res/sym/lyr/v1.png' class='sym-icon' style='height: 20px !important;'>"
	},

	daum_hybrid : {
		state : 'active',
		group : "배경지도",
		fnGroup : LayerFnGroup.CMMN,
		type : LayerConst.TMS,  //2017.11.21 by khkim 추가
		tile : new geomex.xeus.tms.DaumMap(),
		shortcut : "<img src='" + _common.context() + "/res/sym/lyr/v4.png' class='sym-icon' style='height: 20px !important;'>"
	},

	naver_map_view : {
		state : 'active',
		group : "배경지도",
		fnGroup : LayerFnGroup.CMMN,
		type : LayerConst.TMS,  //2017.11.21 by khkim 추가
		tile : new geomex.xeus.tms.NaverMap(),
		shortcut : "<img src='" + _common.context() + "/res/sym/lyr/v0.png' class='sym-icon' style='height: 20px !important;'>"
	},

	emap_map_view : {
		state : 'active',
		group : "배경지도",
		fnGroup : LayerFnGroup.CMMN,
		type : LayerConst.TMS,  //2017.11.21 by khkim 추가
		tile : new geomex.xeus.tms.EMap(),
		shortcut : "<img src='" + _common.context() + "/res/sym/lyr/v0.png' class='sym-icon' style='height: 20px !important;'>"
	},

	/* GIS Server */
	kais_sig_as : {
		state : 'active',
		name : "시군구",
		group : "행정동 경계",
		fnGroup : [
           LayerFnGroup.CCTV,
           LayerFnGroup.NMS,
           LayerFnGroup.INF
        ],
		url : LayerConst.URL,
		type : LayerConst.MULTIPOLYGON,
		typeName : "gmx:kais_sig_as",
		zIndex : 1,
		visible : false,
		selectable : true,
		minResolution : 0,
		maxResolution : Infinity,
		shortcut : "<svg width='30' height='20' style='vertical-align:bottom;'><rect width='30' height='20' style='fill:"+kaisSigAs.area_color+";stroke-width:4;stroke:"+kaisSigAs.line_color+";'/></svg>",
		style : function(feature, resolution){

			return new ol.style.Style({
				stroke: new ol.style.Stroke({
					color: kaisSigAs.line_color,
					width: 2
				}),
				fill: new ol.style.Fill({
					color: kaisSigAs.area_color
				}),
				text: new ol.style.Text({
					font: '15px Calibri,sans-serif',
					text: feature.get('sig_kor_nm'),
					fill: new ol.style.Fill({
						color: '#000'
					}),
					stroke: new ol.style.Stroke({
						color: '#fff',
						width: 3
					})
				})
			});
		},
		reload : function(){
			this.loadFunction($(".tab[target=" + parentView + "]").data("map").getLayerByName(this["name"]));
		}
	},

	kais_emd_as : {
		state : 'active',
		name : "읍면동",
		group : "행정동 경계",
		fnGroup : [
           LayerFnGroup.CCTV,
           LayerFnGroup.NMS,
           LayerFnGroup.INF
        ],
		url : LayerConst.URL,
		type : LayerConst.MULTIPOLYGON,
		typeName : "gmx:kais_emd_as",
		zIndex : 2,
		visible : false,
		selectable : true,
		minResolution : 0,
		maxResolution : 76.35146091935201,
		shortcut : "<svg width='30' height='20' style='vertical-align:bottom;'><rect width='30' height='20' style='fill:"+kaisEmdAs.area_color+";stroke-width:4;stroke:"+kaisEmdAs.line_color+";'/></svg>",
		style : function(feature, resolution){
			var txt = feature.get('emd_kor_nm');
			if(resolution > 38.175730459676004) txt = '';
			return new ol.style.Style({
				stroke: new ol.style.Stroke({
					color: kaisEmdAs.line_color,
					width: 2
				}),
				fill: new ol.style.Fill({
					color: kaisEmdAs.area_color
				}),
				text: new ol.style.Text({
					font: '15px Calibri,sans-serif',
					text: txt,
					fill: new ol.style.Fill({
						color: '#fff'
					}),
					stroke: new ol.style.Stroke({
						color: 'rgba(31, 73, 125, 1)',
						width: 3
					})
				})
			});
		}
	},
	kais_li_as : {
		state : 'active',
		name : "리",
		group : "행정동 경계",
		fnGroup : [
           LayerFnGroup.CCTV,
           LayerFnGroup.NMS,
           LayerFnGroup.INF
        ],
		url : LayerConst.URL,
		type : LayerConst.MULTIPOLYGON,
		typeName : "gmx:kais_li_as",
		zIndex : 3,
		visible : false,
		selectable : true,
		minResolution : 0,
		maxResolution : 76.35146091935201,

		shortcut : "<svg width='30' height='20' style='vertical-align:bottom;'><rect width='30' height='20' style='fill:"+kaisLiAs.area_color+";stroke-width:4;stroke:"+kaisLiAs.line_color+";'/></svg>",
		style : function(feature, resolution){
			var txt = feature.get('li_kor_nm');
			if(resolution > 38.175730459676004) txt = '';
			return new ol.style.Style({
				stroke: new ol.style.Stroke({
					color: kaisLiAs.line_color,
					width: 2
				}),
				fill: new ol.style.Fill({
					color: kaisLiAs.area_color
				}),
				text: new ol.style.Text({
					font: '15px Calibri,sans-serif',
					text: txt,
					fill: new ol.style.Fill({
						color: '#fff'
					}),
					stroke: new ol.style.Stroke({
						color: 'rgba(6, 166, 255, 1)',
						width: 3
					})
				})
			});
		}
	},
	kras_cbnd_as : {
		state : 'active',
		name : "연속지적",
		group : "지적 기반",
		fnGroup : [
           LayerFnGroup.CCTV,
           LayerFnGroup.NMS,
           LayerFnGroup.INF
        ],
		url : LayerConst.URL,
		type : LayerConst.MULTIPOLYGON,
		typeName : "gmx:kras_cbnd_as",
		zIndex : 4,
		visible : false,
		selectable : true,
		minResolution : 0,
		maxResolution : 0.6,
		shortcut : "<svg width='30' height='20' style='vertical-align:bottom;'><rect width='30' height='20' style='fill:"+krasCbndAs.area_color+";stroke-width:4;stroke:"+krasCbndAs.line_color+";'/></svg>",
		style : function(feature, resolution){
			Layers.LayerTheme["kras_cbnd_as"] = {
					lineColor : krasCbndAs.line_color
					, areaColor :krasCbndAs.area_color
			};

			return new ol.style.Style({
				stroke: new ol.style.Stroke({
					color: krasCbndAs.line_color,
					width: 2
				}),
				fill: new ol.style.Fill({
					color: krasCbndAs.area_color
				}),
				text: new ol.style.Text({
					font: '15px Calibri,sans-serif',
					text: feature.get('jibun'),
					fill: new ol.style.Fill({
						color: '#000'
					}),
					stroke: new ol.style.Stroke({
						color: '#fff',
						width: 3
					})
				})
			});
		}
	},
	kais_buld_as : {
		state : 'active',
		name : "건물",
		group : "지적 기반",
		fnGroup : [
           LayerFnGroup.CCTV,
           LayerFnGroup.NMS,
           LayerFnGroup.INF
        ],
		url : LayerConst.URL,
		type : LayerConst.MULTIPOLYGON,
		typeName : "gmx:kais_buld_as",
		zIndex : 5,
		visible : false,
		selectable : true,
		minResolution : 0,
		maxResolution : 1.2,
		shortcut : "<svg width='30' height='20' style='vertical-align:bottom;'><rect width='30' height='20' style='fill:"+kaisBuldAs.area_color+";stroke-width:4;stroke:"+kaisBuldAs.line_color+";'/></svg>",
		style : function(feature, resolution){
			return new ol.style.Style({
				stroke: new ol.style.Stroke({
					color: kaisBuldAs.line_color,
					width: 2
				}),
				fill: new ol.style.Fill({
					color: kaisBuldAs.area_color
				}),
				text: new ol.style.Text({
					font: '15px Calibri,sans-serif',
					text: feature.get('buld_nm'),
					fill: new ol.style.Fill({
						color: '#000'
					}),
					stroke: new ol.style.Stroke({
						color: '#fff',
						width: 3
					})
				})
			});
		}

	},
	kais_eqb_as : {
		state : 'active',
		name : "건물군",
		group : "지적 기반",
		fnGroup : [
           LayerFnGroup.CCTV,
           LayerFnGroup.NMS,
           LayerFnGroup.INF
        ],
		url : LayerConst.URL,
		type : LayerConst.MULTIPOLYGON,
		typeName : "gmx:kais_eqb_as",
		zIndex : 6,
		visible : false,
		selectable : true,
		minResolution : 0,
		maxResolution : 1.2,
		shortcut : "<svg width='30' height='20' style='vertical-align:bottom;'><rect width='30' height='20' style='fill:"+kaisEqbAs.area_color+";stroke-width:4;stroke:"+kaisEqbAs.line_color+";'/></svg>",
		style : function(feature, resolution){
			return new ol.style.Style({
				stroke: new ol.style.Stroke({
					color: kaisEqbAs.line_color,
					width: 2
				}),
				fill: new ol.style.Fill({
					color: kaisEqbAs.area_color
				}),
				text: new ol.style.Text({
					font: '15px Calibri,sans-serif',
					text: feature.get('buld_nm'),
					fill: new ol.style.Fill({
						color: '#000'
					}),
					stroke: new ol.style.Stroke({
						color: '#fff',
						width: 3
					})
				})
			});
		}
	},
	kais_manage_ls : {
		state : 'active',
		name : "도로구간",
		group : "지적 기반",
		fnGroup : [
           LayerFnGroup.CCTV,
           LayerFnGroup.NMS,
           LayerFnGroup.INF
        ],
		url : LayerConst.URL,
		type : LayerConst.MULTIPOLYGON,
		typeName : "gmx:kais_manage_ls",
		zIndex : 7,
		visible : false,
		selectable : true,
		minResolution : 0,
		maxResolution : 1.2,
		shortcut : "<svg height='5' width='30' style='vertical-align:middle;'><line x1='30' y1='0' style='stroke:"+kaisManageLs.line_color+";stroke-width:5;' /></svg>",
		style : function(feature, resolution){
			return new ol.style.Style({
				stroke: new ol.style.Stroke({
					color: kaisManageLs.line_color,
					width: 2
				}),
				text: new ol.style.Text({
					font: '15px Calibri,sans-serif',
					text: feature.get('rn'),
					fill: new ol.style.Fill({
						color: '#000'
					}),
					stroke: new ol.style.Stroke({
						color: '#fff',
						width: 3
					})
				})
			});
		}
	},
	asset_cctv : {
		state : 'active',
		name : "cctv",
		group : "CCTV",
		fnGroup : [
			LayerFnGroup.CCTV,
			LayerFnGroup.NMS,
			LayerFnGroup.DATA
			],
			url : LayerConst.URL,
			type : LayerConst.POINT,
			typeName : "gmx:asset_cctv",
			zIndex : 8,
			visible : true,
			selectable : true,
			minResolution : 0,
			maxResolution : Infinity,
			shortcut : "<img src='" + _common.context() + "/res/sym/cctv/99.png' class='sym-icon'>",
			//shortcut : "<svg width='30' height='30' style='vertical-align:middle;'><circle cx='15' cy='15' r='10' stroke='green' stroke-width='2' fill='rgb(0,0,0)' /></svg>",
			style : null,
			getThemeImg : function(symCd){
				var mgrNo = cctvSymIcon[symCd];
				return "../sym/getSymbol.do?mgrNo=" + mgrNo;
				/*var imgNm = cctvSymIcon[symCd]['N'];
			return "../res/sym/cctv/" + imgNm;*/
				//return "../res/sym/cctv/" + imgNm + ".png";
			},
			themeLoad : function(){
				Layers.LayerTheme["asset_cctv"] = {};
				var array = _common.getCodeByGroup("9999");
				for(var i=0; i<array.length; i++){
					if(array[i].cdeNm != "장애"){
						/*var cdeCde = array[i].cdeCde;
					if(cdeCde == '11') cdeCde = 'test13';
					else if(cdeCde == '18') cdeCde = 'test14';*/
						Layers.LayerTheme["asset_cctv"][array[i].cdeNm] = array[i].cdeCde;
					}
				}
			},
			loadFunction : function(_lyr, val){
				if(val != "null"){
					$(".tab[target=" + parentView + "]").data("map").getLayerByName("cctv").setVisible(true);
					xeusCCTV.cctv.setSymbolStyle(val).reload();
				}else{
					$(".tab[target=" + parentView + "]").data("map").getLayerByName("cctv").setVisible(false);
				}
			}
	},

	v_asset_eqb_voice : {

		state : 'active',
		name : "음성통보시스템",
		group : "시스템 장비",
		fnGroup : [
			LayerFnGroup.CCTV,
			LayerFnGroup.NMS,
			LayerFnGroup.DATA
        ],
		url : LayerConst.URL,
		type : LayerConst.POINT,
		typeName : "gmx:v_asset_eqb_voice",
		//LayerTheme : "asset_rainfall",
		zIndex : 8,
		visible : false,
		selectable : true,
		minResolution : 0,
		maxResolution : Infinity,
		//shortcut : "<img src='" + _common.context() + "/res/sym/waterPump/water.png' class='sym-icon'>",
		shortcut : "<img src='" + _common.context() + "/sym/getSymbol.do?mgrNo=" + voiceSymIcon + "' class='sym-icon'>",
		style : function(feature, resolution){
			return new ol.style.Style({
				image: new ol.style.Icon({
					crossOrigin: "anonymous",
					//src: "../res/sym/waterPump/water.png"
					src: "../sym/getSymbol.do?mgrNo=" + voiceSymIcon
				})
			});
		},
		loadFunction : function(_lyr, val){
			var _source = _lyr.getSource();
			var _geoJSON = new ol.format.GeoJSON();

			$.ajax({
				url : _common.context() + "/CustomWFS",
				type : "POST",
				data : {
					tbl : "v_asset_eqb_voice",
					box : Spatial.getExtent($(".tab[target=" + parentView + "]").data("map").getMap()).toString()
				},
				dataType : "json",
				beforeSend : function() {

					_source.clear();
				},
				success : function(json) {

					var _features = _geoJSON.readFeatures(json);
					for(var i=0; i<_features.length; i++){
						var feature = _features[i];

						feature.setProperties({
							//"img_path" : "../res/sym/nms/" + feature.get("sym_cd") + ".png",
							//"img_path" : "../res/sym/waterPump/water.png",
							"img_path" : "../sym/getSymbol.do?mgrNo=" + voiceSymIcon,
							"target_field" : "nm",
							"popup" : true
						});

					}
					_source.addFeatures(_features);
				},
				error : function(xhr, status, error) { }
			});
		},
		reload : function(){
			this.loadFunction($(".tab[target=" + parentView + "]").data("map").getLayerByName(this["name"]));
		}
	},
	v_asset_eqb_aws : {

		state : 'active',
		name : "기상관측기기",
		group : "시스템 장비",
		fnGroup : [
			LayerFnGroup.CCTV,
			LayerFnGroup.NMS,
			LayerFnGroup.DATA
			],
			url : LayerConst.URL,
			type : LayerConst.POINT,
			typeName : "gmx:v_asset_eqb_aws",
			//LayerTheme : "asset_rainfall",
			zIndex : 8,
			visible : false,
			selectable : true,
			minResolution : 0,
			maxResolution : Infinity,
			//shortcut : "<img src='" + _common.context() + "/res/sym/waterPump/water.png' class='sym-icon'>",
			shortcut : "<img src='" + _common.context() + "/sym/getSymbol.do?mgrNo=" + awsSymIcon + "' class='sym-icon'>",
			style : function(feature, resolution){
				return new ol.style.Style({
					image: new ol.style.Icon({
						crossOrigin: "anonymous",
						//src: "../res/sym/waterPump/water.png"
						src: "../sym/getSymbol.do?mgrNo=" + awsSymIcon
					})
				});
			},
			loadFunction : function(_lyr, val){
				var _source = _lyr.getSource();
				var _geoJSON = new ol.format.GeoJSON();

				$.ajax({
					url : _common.context() + "/CustomWFS",
					type : "POST",
					data : {
						tbl : "v_asset_eqb_aws",
						box : Spatial.getExtent($(".tab[target=" + parentView + "]").data("map").getMap()).toString()
					},
					dataType : "json",
					beforeSend : function() {
						_source.clear();
					},
					success : function(json) {

						var _features = _geoJSON.readFeatures(json);
						for(var i=0; i<_features.length; i++){
							var feature = _features[i];
							feature.setProperties({
								//"img_path" : "../res/sym/nms/" + feature.get("sym_cd") + ".png",
								//"img_path" : "../res/sym/waterPump/water.png",
								"img_path" : "../sym/getSymbol.do?mgrNo=" + awsSymIcon,
								"target_field" : "doc_mgr_no",
								"popup" : true
							});
						}
						_source.addFeatures(_features);
					},
					error : function(xhr, status, error) { }
				});
			},
			reload : function(){
				this.loadFunction($(".tab[target=" + parentView + "]").data("map").getLayerByName(this["name"]));
			}
	},

	asset_heat : {

		state : 'active',
		name : "무더위심터",
		group : "시스템 장비",
		fnGroup : [
			LayerFnGroup.CCTV,
			LayerFnGroup.NMS,
			LayerFnGroup.DATA
			],
			url : LayerConst.URL,
			type : LayerConst.POINT,
			typeName : "gmx:asset_heat",
			//LayerTheme : "asset_rainfall",
			zIndex : 8,
			visible : false,
			selectable : true,
			minResolution : 0,
			maxResolution : Infinity,
			//shortcut : "<img src='" + _common.context() + "/res/sym/waterPump/water.png' class='sym-icon'>",
			shortcut : "<img src='" + _common.context() + "/sym/getSymbol.do?mgrNo=" + heatSymIcon + "' class='sym-icon'>",
			style : function(feature, resolution){
				return new ol.style.Style({
					image: new ol.style.Icon({
						crossOrigin: "anonymous",
						//src: "../res/sym/waterPump/water.png"
						src: "../sym/getSymbol.do?mgrNo=" + heatSymIcon
					})
				});
			},
			loadFunction : function(_lyr, val){
				var _source = _lyr.getSource();
				var _geoJSON = new ol.format.GeoJSON();

				$.ajax({
					url : _common.context() + "/CustomWFS",
					type : "POST",
					data : {
						tbl : "asset_heat",
						box : Spatial.getExtent($(".tab[target=" + parentView + "]").data("map").getMap()).toString()
					},
					dataType : "json",
					beforeSend : function() {
						_source.clear();
					},
					success : function(json) {

						var _features = _geoJSON.readFeatures(json);
						for(var i=0; i<_features.length; i++){
							var feature = _features[i];
							feature.setProperties({
								//"img_path" : "../res/sym/nms/" + feature.get("sym_cd") + ".png",
								//"img_path" : "../res/sym/waterPump/water.png",
								"img_path" : "../sym/getSymbol.do?mgrNo=" + heatSymIcon,
								"target_field" : "name",
								"popup" : true
							});
						}
						_source.addFeatures(_features);
					},
					error : function(xhr, status, error) { }
				});
			},
			reload : function(){
				this.loadFunction($(".tab[target=" + parentView + "]").data("map").getLayerByName(this["name"]));
			}
	},

	asset_smart_cctv : {

		state : 'active',
		name : "지능형 CCTV",
		group : "CCTV",
		fnGroup : [
			LayerFnGroup.CCTV,
			],
			url : LayerConst.URL,
			type : LayerConst.POINT,
			typeName : "gmx:asset_smart_cctv",
			//LayerTheme : "asset_rainfall",
			zIndex : 8,

			visible : true,
			selectable : true,
			minResolution : 0,
			maxResolution : Infinity,
			//shortcut : "<img src='" + _common.context() + "/res/sym/waterPump/water.png' class='sym-icon'>",
			shortcut : "<img src='" + _common.context() + "/sym/getSymbol.do?mgrNo=" + smartCctvSym + "' class='sym-icon'>",
			style : function(feature, resolution){
				return new ol.style.Style({
					image: new ol.style.Icon({
						crossOrigin: "anonymous",
						//src: "../res/sym/waterPump/water.png"
						src: "../sym/getSymbol.do?mgrNo=" + smartCctvSym
					})
				});
			},
			loadFunction : function(_lyr, val){
				var _source = _lyr.getSource();
				var _geoJSON = new ol.format.GeoJSON();
				$.ajax({
					url : _common.context() + "/CustomWFS",
					type : "POST",
					data : {
						tbl : "asset_smart_cctv",
						box : Spatial.getExtent($(".tab[target=" + parentView + "]").data("map").getMap()).toString()
					},
					dataType : "json",
					beforeSend : function() {
						_source.clear();
					},
					success : function(json) {
						var _features = _geoJSON.readFeatures(json);

						for(var i=0; i<_features.length; i++){
							var feature = _features[i];

							feature.setProperties({
								//"img_path" : "../res/sym/nms/" + feature.get("sym_cd") + ".png",
								//"img_path" : "../res/sym/waterPump/water.png",
								"img_path" : "../sym/getSymbol.do?mgrNo=" + smartCctvSym,
								"target_field" : "cctv_nm",
								"popup" : true
							});
						}

						_source.addFeatures(_features);
					},
					error : function(xhr, status, error) {
						console.log('222');
					}
				});
			},
			reload : function(){
				this.loadFunction($(".tab[target=" + parentView + "]").data("map").getLayerByName(this["name"]));
			}
	},
	asset_disbord : {

		state : 'active',
		name : "재해문자전광판",
		group : "시스템 장비",
		fnGroup : [
			LayerFnGroup.CCTV,
			LayerFnGroup.NMS,
			LayerFnGroup.DATA
			],
			url : LayerConst.URL,
			type : LayerConst.POINT,
			typeName : "gmx:asset_disbord",
			//LayerTheme : "asset_rainfall",
			zIndex : 8,
			visible : false,
			selectable : true,
			minResolution : 0,
			maxResolution : Infinity,
			//shortcut : "<img src='" + _common.context() + "/res/sym/waterPump/water.png' class='sym-icon'>",
			shortcut : "<img src='" + _common.context() + "/sym/getSymbol.do?mgrNo=" + disbordSymIcon + "' class='sym-icon'>",
			style : function(feature, resolution){
				return new ol.style.Style({
					image: new ol.style.Icon({
						crossOrigin: "anonymous",
						//src: "../res/sym/waterPump/water.png"
						src: "../sym/getSymbol.do?mgrNo=" + disbordSymIcon
					})
				});
			},
			loadFunction : function(_lyr, val){
				var _source = _lyr.getSource();
				var _geoJSON = new ol.format.GeoJSON();

				$.ajax({
					url : _common.context() + "/CustomWFS",
					type : "POST",
					data : {
						tbl : "asset_disbord",
						box : Spatial.getExtent($(".tab[target=" + parentView + "]").data("map").getMap()).toString()
					},
					dataType : "json",
					beforeSend : function() {
						_source.clear();
					},
					success : function(json) {

						var _features = _geoJSON.readFeatures(json);
						for(var i=0; i<_features.length; i++){
							var feature = _features[i];
							feature.setProperties({
								//"img_path" : "../res/sym/nms/" + feature.get("sym_cd") + ".png",
								//"img_path" : "../res/sym/waterPump/water.png",
								"img_path" : "../sym/getSymbol.do?mgrNo=" + disbordSymIcon,
								"target_field" : "bord_nm",
								"popup" : true
							});
						}
						_source.addFeatures(_features);
					},
					error : function(xhr, status, error) { }
				});
			},
			reload : function(){
				this.loadFunction($(".tab[target=" + parentView + "]").data("map").getLayerByName(this["name"]));
			}
	},
	/*v_evt_stat : {
		state : 'active',
		name : "긴급재난상황",
		group : "EVT",
		fnGroup : LayerFnGroup.CCTV,
		url : LayerConst.URL,
		type : LayerConst.POINT,
		typeName : "gmx:v_evt_stat",
		LayerTheme : "v_evt_stat",
		themeUrl : "/event/getEvtTheme.json",
		zIndex : 8,
		visible : false,
		selectable : true,
		minResolution : 0,
		maxResolution : Infinity,
		shortcut : "<img src='" + _common.context() + "/res/sym/lyr/evt.png' class='sym-icon'>",
		style : function(feature, resolution){
			var symCd = feature.get("gbn_cd");
			if(symCd != null && symCd != ""){
				return new ol.style.Style({
					image: new ol.style.Icon({
						crossOrigin: "anonymous",
						src: "../res/sym/evt/" + symCd + ".png"
					})
				});
				var mgrNo = evtStatSymIcon[symCd];
				return new ol.style.Style({
					image: new ol.style.Icon({
						crossOrigin: "anonymous",
						src: "../sym/getSymbol.do?mgrNo=" + mgrNo
					})
				});
			}else{
				return new ol.style.Style({
					image: new ol.style.Circle({
						radius: 10,
						stroke: new ol.style.Stroke({
							color: 'rgba(0, 128, 0, 1.0)',
							width: 2
						}),
						fill : new ol.style.Fill({
							color: 'rgba(0, 0, 0, 1.0)'
						}),
						text: new ol.style.Text({
							font: '15px Calibri,sans-serif',
							text: feature.get('cable_nm'),
							fill: new ol.style.Fill({
								color: '#000'
							}),
							stroke: new ol.style.Stroke({
								color: '#fff',
								width: 3
							})
						})
					})
				});
			}
		},

		getThemeImg : function(symCd){
			//return "../res/sym/evt/" + symCd;// + ".png";
			if(symCd.indexOf(".png") > 0) symCd = symCd.substring(0, symCd.indexOf(".png"));
			var mgrNo = evtStatSymIcon[symCd];
			return "../sym/getSymbol.do?mgrNo=" + mgrNo;
		},
		themeLoad : function(){
			Layers.LayerTheme["v_evt_stat"] = {};
			var array = _common.getCodeByGroup("C68");
			//console.log(array);
			for(var i=0; i<array.length; i++){
				Layers.LayerTheme["v_evt_stat"][array[i].cdeNm] = array[i].cdeCde;
			}
		},
		loadFunction : function(_lyr, val){
			var _source = _lyr.getSource();
			var _geoJSON = new ol.format.GeoJSON();
			$.ajax({
				url : _common.context() + "/CustomWFS",
				type : "POST",
				data : {
					tbl : "v_evt_stat",
					box : Spatial.getExtent($(".tab[target=" + parentView + "]").data("map").getMap()).toString(),
					val : val,
					col: 'gbn_cd'
				},
				dataType : "json",
				beforeSend : function() {
					_source.clear();
				},
				success : function(json) {
						var _features = _geoJSON.readFeatures(json);
						for(var i=0; i<_features.length; i++){
							var feature = _features[i];
							var mgrNo = feature.get("sym_cd");
							feature.setProperties({
								//"img_path" : "../res/sym/nms/" + feature.get("sym_cd") + ".png",
								//"img_path" : "../res/sym/waterPump/water.png",
								"img_path" : "../sym/getSymbol.do?mgrNo=" + mgrNo,
								"target_field" : "evt_nm",
								"popup" : false
							});
						}
						_source.addFeatures(_features);
				},
				error : function(xhr, status, error) { }
			});
		},
		reload : function(){
			this.loadFunction($(".tab[target=" + parentView + "]").data("map").getLayerByName(this["name"]));
		}
	}*/

}
