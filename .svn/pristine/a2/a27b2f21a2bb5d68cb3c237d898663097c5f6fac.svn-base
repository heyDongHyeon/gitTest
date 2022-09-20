<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page import="geomex.xeus.equipmgr.service.CctvVo"%> --%>
<%@ include file="../common.jsp"%>
<%

%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.board.api.js"></script>
<script>
(function(){
	var options = {
		width: 12,
		float: true,
		animate: true,
		handle: ".title",
		cellHeight: 120,
		resizable: {
			handles: 'e, se, s, sw, w'
		}
	};
	$("#" + parentView).find('#grid').gridstack(options);

	var items = [
		{"x":1,"y":2,"width":1,"height":1,"type":"text","title":"오늘 로그인 수","content":"342","id":"SignCnt","target":"signCnt"},
	//	{"x":3,"y":0,"width":2,"height":2,"type":"text","title":"오늘 분석된 데이터","content":"56","id":"TodayAnalysCnt","target":"todayAnalysCnt"},
		{"x":2,"y":2,"width":1,"height":1,"type":"text","title":"총 사용자","content":"567","id":"UsrCnt","target":"usrCnt"},
		{"x":0,"y":3,"width":1,"height":1,"type":"text","title":"습도","content":"70%","id":"Hum","target":"hum"},
		{"x":0,"y":2,"width":1,"height":1,"type":"text","title":"풍량","content":"1m/s","id":"Wind","target":"wind"},
		{"x":0,"y":5,"width":2,"height":1,"type":"text","title":"오늘 발생된 이벤트","content":"26","id":"TodayEventCnt","target":"todayEventCnt"},
		{"x":0,"y":0,"width":3,"height":2,"type":"chart","title":"온도","content":"","id":"GaugeChart","target":"temp"},
		{"x":1,"y":3,"width":1,"height":1,"type":"text","title":"자외선","content":"주의","id":"Uv","target":"uv"},
		{"x":5,"y":0,"width":3,"height":3,"type":"chart","title":"차트","content":"","id":"AgeBarChart","target":"ageBarChart"},
		{"x":8,"y":0,"width":4,"height":3,"type":"chart","title":"차트","content":"","id":"TimeLineChart","target":"timeBarChart"},
		{"x":0,"y":4,"width":1,"height":1,"type":"text","title":"미세먼지","content":"보통","id":"Dust","target":"dust"},
		/* {"x":1,"y":4,"width":1,"height":1,"type":"text","title":"초 미세먼지","content":"보통","id":"UltraDust","target":"ultraDust"}, */
		{"x":1,"y":4,"width":1,"height":1,"type":"text","title":"강수확률","content":"0%","id":"Rain","target":"rain"},
		{"x":8,"y":3,"width":4,"height":3,"type":"chart","title":"차트","content":"","id":"EventLineChart","target":"eventChart"},
//		{"x":3,"y":2,"width":2,"height":1,"type":"text","title":"오늘 반출된 영상","content":"31","id":"TodayTviusCnt","target":"todayTviusCnt"},
//		{"x":5,"y":3,"width":3,"height":3,"type":"map","title":"CCTV 분포도","content":"","id":"CctvHitMap","target":"cctvHitMap"},
		{"x":2,"y":3,"width":3,"height":3,"type":"map","title":"실시간 이벤트","content":"","id":"EventMap","target":"eventMap"}
	];

	/* var items;
	_common.callAjax("/board/getTotalStatsView.json", {"userId": userId}, function(json){
		console.log(json.result);
		items = json.result;
	}, false); */

	$("#" + parentView).find('.grid-stack').each(function () {
		var grid = $(this).data('gridstack');

		_.each(items, function (node) {
			var $ele = $('<div><div class="grid-stack-item-content" /><div/>');
			$ele.find(".grid-stack-item-content").attr("target", node.target).data(node)
				.append('<div class="title">' + node.title + '</div>')
				.append('<div class="content">' + node.content + '</div>');

			var id = node.id;
			grid.addWidget($ele, Number(node.x), Number(node.y), Number(node.width), Number(node.height), false, 1, 4, 1, 4, id);
			if(id) $ele.find(".grid-stack-item-content").attr("id", id);
			GridStackUI.Utils.setVertialSort();
		});

		GridStackUI.Utils.setWidgetColor();
	});

	GridStackUI.Utils.removeAllChart();
	GridStackUI.Utils.createAllChart();

	GridStackUI.Utils.removeAllMap();
	GridStackUI.Utils.createAllMap();

	$("#" + parentView).find('.grid-stack').on('gsresizestop', function(event, elem) {
		setTimeout(function(){
			GridStackUI.Utils.setVertialSort();
			GridStackUI.Utils.setAllChartAlign();
			GridStackUI.Utils.setAllMapAlign();

			_common.callAjax("/user/editBoardInfo.json", {"userId": userId, "boardInfo": JSON.stringify(GridStackUI.Utils.saveGrid())}, function(json){
				console.log(json.result);
			});
		}, 280);
	});

	$("#" + parentView).find('.grid-stack').on('change', function(event, elem) {
		setTimeout(function(){
			_common.callAjax("/user/editBoardInfo.json", {"userId": userId, "boardInfo": JSON.stringify(GridStackUI.Utils.saveGrid())}, function(json){
				console.log(json.result);
			});
		}, 280);
	});

	var $widgetBtn = $("<button id='widgetBtn' class='blueBtn'>위젯 설정</button>").css({
	    "position": "absolute",
    	"top": "1px",
    	"right": "40px",
    	"padding": "7px"
	}).click(function(){
		$("#" + parentView).find("#widgetWrapper, #hideWrapper").show("blind");
	});
	$("#" + parentView).find("#overlay-west-bar").append($widgetBtn);

	$("#" + parentView).find("#widgetWrapperClose").click(function(){
		$("#" + parentView).find("#widgetWrapper, #hideWrapper").hide("blind");
	});

	$("#" + parentView).find(".widgetOnOff").click(function(){
		var $this = $(this);
		var target = $(this).attr("target");

		var isOn = false;
		var $widget = null;

		if(target){
			if($("#" + parentView).find(".grid-stack-item-content[target=" + target + "]").length){
				isOn = true;
				$widget = $("#" + parentView).find(".grid-stack-item-content[target=" + target + "]").parent();
			}

			if(isOn){
				$("#" + parentView).find('.grid-stack').data("gridstack").removeWidget($widget);
				$(this).css("background", "#cccccc");
			}else{
				_.each(items, function (node) {
					if(target == node.target){
						var $ele = $('<div><div class="grid-stack-item-content" /><div/>');
						$ele.find(".grid-stack-item-content").attr("target", node.target).data(node)
							.append('<div class="title">' + node.title + '</div>')
							.append('<div class="content">' + node.content + '</div>');

						var id = node.id;
						$("#" + parentView).find('.grid-stack').data("gridstack").addWidget($ele, Number(node.x), Number(node.y), Number(node.width), Number(node.height), false, 1, 4, 1, 4, id);
						if(id) $ele.find(".grid-stack-item-content").attr("id", id);

						if(node.type == "chart") GridStackUI.Utils.createChart(id);
						if(node.type == "map") GridStackUI.Utils.createMap(id);
						GridStackUI.Utils.setVertialSort();

					}
				});
				$this.css("background", "white");
			}
		}
	}).each(function(){
		var target = $(this).attr("target");
		var isContains = false;
		for(var i=0; i<items.length; i++){
			if(items[i].target == target){
				isContains = true;
			}
		}
		if(!isContains) $(this).css("background", "#cccccc");
	});

	/* _common.callAjax("/board/getTotalStatsView.json", {"userId": "aa"}, function(json){
		console.log(json.result);
	}); */

	GridStackUI.Utils.startInterval();

})();
</script>

<style>
#boardView #overlay-west-contents {
	background: #3E3F48;
}
#grid {
	margin-top: 20px;
}

.grid-stack-item-content {
	color: #2c3e50;
	text-align: center;
	background-color: white;
}

.grid-stack-item-content > .title, .highcharts-title {
	position: absolute;
    text-align: center;
    cursor: default;
    width: 100%;
    font-weight: bold;
    font-size: 22px;
    padding: 5px;
    z-index: 1;
}

.grid-stack-item-content > .content {
	position: absolute;
    text-align: center;
    cursor: default;
    width: 100%;
    font-size: 50px;
    font-weight: bold;
}

#widgetWrapper {
    position: absolute;
    top: 0;
    right: 0;
    background: #3E3F48;
    height: 100%;
    border-left: 1px solid #30303A;
}

#widgetWrapper > #titleWrap {
    border-bottom: 1px solid #30303A;
    text-align: right;
}

#widgetWrapper #widgetWrapperClose {
	width: 40px;
    padding: 7px;
    background: none;
}

#widgetWrapper > #widgetMng {
    margin: 10px;
    max-width: 365px;
	height: 94%;
}

.widgetOnOff {
	padding: 20px;
	margin: 2px;
	display: inline-block;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
	width: 177px;
}

#hideWrapper {
	position: absolute;
    width: 100%;
    height: 100%;
    background: rgba(62, 63, 72, 0.8);
    top: 0;
    left: 0;
}

</style>
<div class="searchWrapper mCustomScrollbar" data-mcs-theme="minimal-dark">
	<div class="grid-stack grid-stack-animate" id="grid" data-gs-animate="yes"></div>
</div>

<div id="hideWrapper" class="hidden"></div>
<div id="widgetWrapper" class="hidden">
	<div id="titleWrap">
		<button class="blueBtn" id="widgetWrapperClose">
			<img src="/xeus/res/img/close_btn.png">
		</button>
	</div>
	<div id="widgetMng" class="mCustomScrollbar" data-mcs-theme="minimal-dark">
		<button class="widgetOnOff whiteBtn" target="temp">온도</button>
		<button class="widgetOnOff whiteBtn" target="hum">습도</button>
		<button class="widgetOnOff whiteBtn" target="wind">풍량</button>
		<button class="widgetOnOff whiteBtn" target="rain">강수확률</button>
		<button class="widgetOnOff whiteBtn" target="uv">자외선</button>
		<button class="widgetOnOff whiteBtn" target="dust">미세먼지</button>
		<button class="widgetOnOff whiteBtn" target="ultraDust">초미세먼지</button>
		<button class="widgetOnOff whiteBtn" target="usrCnt">총 사용자</button>
		<button class="widgetOnOff whiteBtn" target="signCnt">오늘 로그인 수</button>

	<!-- 	<button class="widgetOnOff whiteBtn" target="todayAnalysCnt">오늘 분석된 데이터</button>
		<button class="widgetOnOff whiteBtn" target="">총 분석된 데이터</button>
		<button class="widgetOnOff whiteBtn" target="todayTviusCnt">오늘 반출된 영상</button>
		<button class="widgetOnOff whiteBtn" target="cctvHitMap">CCTV 분포도</button>-->
		<button class="widgetOnOff whiteBtn" target="eventMap">이벤트 지도</button>
		<button class="widgetOnOff whiteBtn" target="eventChart">이벤트 발생 차트</button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
		<button class="widgetOnOff whiteBtn" target=""></button>
	</div>
</div>