var xeusLayout = {
	ctxPath : "/xeus",
	mapService : null,
	StopEvent : null
};

window.getWidthOfText = function(txt, fontname, fontsize) {
	// Create dummy span
	this.e = document.createElement('span');
	// Set font-size
	this.e.style.fontSize = fontsize;
	// Set font-face / font-family
	this.e.style.fontFamily = fontname;
	// Set text
	this.e.innerHTML = txt;
	document.body.appendChild(this.e);
	// Get width NOW, since the dummy span is about to be removed
	// from the document
	var w = this.e.offsetWidth;
	// Cleanup
	document.body.removeChild(this.e);
	// All right, we're done
	return w;
}

$(document).ready(function() {
	resize(); // 사이즈
	// XEUS.map.createMap("map"); //맵
	setTime(); // 시계
	_common.setCode();

	_common.callAjax("/board/getYearCount.json", null, function(json){
		if(json.result.length > 0){
			var cnt = new Array();
			for(var i=0; i<json.result.length; i++){
				cnt.push(Number(json.result[i].cnt));
			}

			var chart = $('#chart1').highcharts();
			if(chart) chart.destroy();
			BoardChart.createBarChart("chart1", "영상반출 현황", [{data : cnt}], null);
		}
	});

	// JQuery Dialog Title에 html 사용 Hack!!!!
	$.widget('ui.dialog', jQuery.extend({}, jQuery.ui.dialog.prototype, {
		_title : function(titleBar) {
			titleBar.html(this.options.title || '&#160;');
		}
	}));

	xeusLayout.mapService = new geomex.xeus.MapService({
		ctxPath : xeusLayout.ctxPath,
		controls : [ new ol.control.ScaleLine(),new ol.control.MousePosition({
			projection: 'EPSG:4326',
			coordinateFormat: function(coordinate) {
				return ol.coordinate.format(coordinate, '{x}, {y}', 4);
			}
		})],
		projection : ol.proj.get("EPSG:5186"),
		target : 'xeus-map-content',//!! 나중에 정확한 id명이 나오면 변겅해야 함.
		center : [ 223038.97507357592, 441159.0711484331 ],
//		zoom : 12, // 클수록 확대
//		minZoom : 9,
//		maxZoom : 24
		zoom : 12, // 클수록 확대
		minZoom : 9,
		maxZoom : 19
	});

	xeusLayout.mapService.getMap().on('moveend', function(){
		BoardAPI.getWeather(xeusLayout.mapService.getMap());
		//BoardAPI.getAir(xeusLayout.mapService.getMap());
	});

	xeusLayout.mapService.Layers = {
		CCTV : null,
		NMS : null
	};

	for(var key in Layers){
		if(Layers[key].type == LayerConst.TMS){
			xeusLayout.mapService.addLayer(
				Layers[key].tile.createMapLayer({
					visible : true,
					proxy : false
				})
			);
		}
	};

	$("#returnBtn").click(function(){
		location.href = "/xeus";
	});

	$(".menuBtn").click(function(){
		$(".menuBtn").removeAttr("active");
		$(this).attr("active", "active");

		for(var key in xeusLayout.mapService.Layers){
			var layer = xeusLayout.mapService.Layers[key];
			xeusLayout.mapService.removeLayer(layer);
		}

		if(xeusLayout.StopEvent) xeusLayout.StopEvent();
	});

	$("#btnCCTV").on("click", function() {
		xeusLayout.mapService.Layers.CCTV = xeusLayout.mapService.createVectorLayer(Layers["asset_cctv"]);
		xeusLayout.mapService.Layers.CCTV.setVisible(true);
		xeusLayout.mapService.addLayer(xeusLayout.mapService.Layers.CCTV);
		xeusCCTV.initCCTV(xeusLayout.ctxPath);
		xeusCCTV.cctv.reload();

		cctvMission.doWork();
	}).click();

	$("#btnNMS").on("click",function() {
		LayerConst.ThemeLoad("asset_infra");
		xeusLayout.mapService.Layers.INF = xeusLayout.mapService.createVectorLayer(Layers["asset_infra"]);
		xeusLayout.mapService.Layers.INF.setVisible(true);
		xeusLayout.mapService.addLayer(xeusLayout.mapService.Layers.INF);
		Layers["asset_infra"].loadFunction(xeusLayout.mapService.Layers.INF);

		LayerConst.ThemeLoad("asset_cable");
		xeusLayout.mapService.Layers.NMS = xeusLayout.mapService.createVectorLayer(Layers["asset_cable"]);
		xeusLayout.mapService.Layers.NMS.setVisible(true);
		xeusLayout.mapService.addLayer(xeusLayout.mapService.Layers.NMS);
		Layers["asset_cable"].loadFunction(xeusLayout.mapService.Layers.NMS);

		nmsMission.doWork();
	});

	$("#btnHeat").on("click",function() {
		xeusLayout.mapService.Layers.HEAT = xeusLayout.mapService.createHeatLayer(Layers["asset_cctv_heat"]);
		xeusLayout.mapService.Layers.HEAT.setVisible(true);
		xeusLayout.mapService.addLayer(xeusLayout.mapService.Layers.HEAT);
		Layers["asset_cctv_heat"].loadFunction(xeusLayout.mapService.Layers.HEAT);

		xeusLayout.mapService.Layers.RQST = xeusLayout.mapService.createHeatLayer(Layers["v_asset_cctv_heat"]);
		xeusLayout.mapService.Layers.RQST.setVisible(false);
		xeusLayout.mapService.addLayer(xeusLayout.mapService.Layers.RQST);
		Layers["v_asset_cctv_heat"].loadFunction(xeusLayout.mapService.Layers.RQST);

		heatMission.doWork();
	});

	$(".table_data").on("DOMNodeInserted", function(){
		var $this = $(this);
		var $tr = $(this).find("tr");
		var level = $tr.find("td").eq(2).text();
		if(level == "경고" || level == "위험"){
			$("#ticker").append('<li><span>' + new Date().getYMDHMS(true) + '</span><a href="#">' + $tr.find("td").eq(3).text() + '</a></li>');
		}
	});

	$(document).on("click", ".cctv-overlay-content-img-div a", function(){
		$(".ui-dialog-title").html($(".ui-dialog-title").text());
		$(".ui-dialog-content").height(242);
	});
});

$(function() {
	$("#allWrap").click(function() {
		if ($("#chooseChart1").css("display") == "block") {
			$("#chooseChart1").css("display", "none");
		}
		if ($("#chooseChart2").css("display") == "block") {
			$("#chooseChart2").css("display", "none");
		}
		if ($("#chooseChart3").css("display") == "block") {
			$("#chooseChart3").css("display", "none");
		}
	});
});

$(window).resize(function() {
	resize();
});

function resize() {
	var width = $(document).width();
	var height = $(document).height();

	$("#left").width(width - 615);
	$("#left").height(height - 60);
	$("#left_left").height(height - 90);

	$('#'+parentView).find(".map-target").height(height - 90);//!! 테스트 해봐야 함.
	$('#'+parentView).find(".map-target").width(width - 695);//!! 테스트 해봐야 함.
	$("#left_top").width(width - 695);

	$("#layerWrap").css("top", height - 115 - 120 - 50);
	$("#top_left").width(width - 1070 - 20);
	$("#right").height(height - 60);
	$("#rightEtc").height(height - 440);
}

function setTime() {
	BoardAPI.getDate();
	setTimeout('setTime()', 1000);
}
