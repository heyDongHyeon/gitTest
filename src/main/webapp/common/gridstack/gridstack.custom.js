/**
 * 그리드 Util 객체입니다.
 */
(function(){
	if(GridStackUI && _){

		GridStackUI.Utils.Interval = null;
		GridStackUI.Utils.Charts = new Array();
		GridStackUI.Utils.Maps = new Array();
		GridStackUI.Utils.Socket = null;

		GridStackUI.Utils.startInterval = function(){
			GridStackUI.Utils.reloadData();
			GridStackUI.Utils.Interval = setInterval(function(){
				GridStackUI.Utils.reloadData();
			}, 10000);
		};

		GridStackUI.Utils.stopInterval = function(){
			clearInterval(GridStackUI.Utils.Interval);
			GridStackUI.Utils.Interval = null;
		};

		GridStackUI.Utils.reloadData = function(){
			GridStackUI.Utils.setWeatherData();
		};

		/**
		 * 그리드 포지션을 리턴합니다.
		 */
		GridStackUI.Utils.saveGrid = function(selector){
			var $slt = $('.grid-stack > .grid-stack-item:visible');
			if(selector) $slt = $(selector + " > .grid-stack-item:visible");
			return _.map($slt, function (el) {
				var node = $(el).data('_gridstack_node');
				var prop = $(el).find(".grid-stack-item-content").data();
				return {
					x: node.x,
					y: node.y,
					width: node.width,
					height: node.height,
					type: prop.type,
					title: prop.title,
					content: prop.content,
					id: prop.id
				};
			});
		}

		/**
		 * 모든 그리드를 제거 합니다.
		 */
		GridStackUI.Utils.clearGrid = function(selector){
			var grid = $(selector).data('gridstack');
			grid.removeAll();
		}

		/**
		 * 그리드를 로드합니다.
		 */
		GridStackUI.Utils.loadGrid = function(selector, data){
			var grid = $(selector).data('gridstack');
			grid.removeAll();

			var items = GridStackUI.Utils.sort(data);
            _.each(items, function (node) {
            	var $ele = $('<div><div class="grid-stack-item-content" /></div>');
            	$ele.find(".grid-stack-item-content").data(node)
            		.append('<div class="title">' + node.title + '</div>')
            		.append('<div class="content">' + node.content + '</div>');

            	var id = node.id;
            	grid.addWidget($ele, node.x, node.y, node.width, node.height, false, 1, 4, 1, 4, id);
            	if(id) $ele.find(".grid-stack-item-content").attr("id", id);
            });

            GridStackUI.Utils.setVertialSort();
            GridStackUI.Utils.setWidgetColor();
		}

		/**
		 * 위젯 내부의 컨텐츠를 수직 중앙 정렬합니다.
		 */
		GridStackUI.Utils.setVertialSort = function(selector){
			if(!selector) selector = ".grid-stack-item-content";
			$(selector).find(".content").each(function(){
				var pHeight = $(this).parent().height();
				var tHeight = $(this).height();
				var top = ((pHeight / 2) - (tHeight / 2)) + 10;
				$(this).css("top", top);
			});
		}

		/**
		 * 위젯의 배경색을 변경합니다.
		 */
		GridStackUI.Utils.setWidgetColor = function(selector){
			if(!selector) selector = ".grid-stack-item-content";
			var length = $(selector).length;
			$(selector).each(function(){
				$(this).css("background", GmxColor.random());
			});
		}

		/**
		 * 모든 차트를 생성합니다.
		 */
		GridStackUI.Utils.createAllChart = function(){
			GridStackUI.Utils.createBarChart("AgeBarChart");
			GridStackUI.Utils.createLineChart("TimeLineChart");
			GridStackUI.Utils.createEventLineChart("EventLineChart");
			GridStackUI.Utils.createGaugeChart("GaugeChart");
		}

		/**
		 * 특정 차트를 생성합니다.
		 */
		GridStackUI.Utils.createChart = function(selector){
			if(selector == "AgeBarChart") GridStackUI.Utils.createBarChart("AgeBarChart");
			if(selector == "TimeLineChart") GridStackUI.Utils.createLineChart("TimeLineChart");
			if(selector == "EventLineChart") GridStackUI.Utils.createEventLineChart("EventLineChart");
			if(selector == "GaugeChart") GridStackUI.Utils.createGaugeChart("GaugeChart");
		}

		/**
		 * 모든 차트를 재정렬합니다.
		 */
		GridStackUI.Utils.setAllChartAlign = function(){
			for(var i=0; i<GridStackUI.Utils.Charts.length; i++){
				GridStackUI.Utils.Charts[i].reflow();
			}
		}

		/**
		 * 모든 생성된 차트와 배열을 삭제합니다.
		 */
		GridStackUI.Utils.removeAllChart = function(){
			for(var i=0; i<GridStackUI.Utils.Charts.length; i++){
				GridStackUI.Utils.Charts[i].destroy();
			}
			GridStackUI.Utils.Charts = new Array();
		}

		/**
		 * 모든 지도를 생성합니다.
		 */
		GridStackUI.Utils.createAllMap = function(){
			GridStackUI.Utils.createEventMap("EventMap");
			GridStackUI.Utils.createCctvHitMap("CctvHitMap");
		}

		/**
		 * 특정 지도를 생성합니다.
		 */
		GridStackUI.Utils.createMap = function(selector){
			if(selector == "EventMap") GridStackUI.Utils.createEventMap("EventMap");
			if(selector == "CctvHitMap") GridStackUI.Utils.createCctvHitMap("CctvHitMap");
		}

		/**
		 * 모든 지도를 재정렬합니다.
		 */
		GridStackUI.Utils.setAllMapAlign = function(){
			for(var i=0; i<GridStackUI.Utils.Maps.length; i++){
				GridStackUI.Utils.Maps[i].updateSize();
			}
		}

		/**
		 * 모든 생성된 지도와 배열을 삭제합니다.
		 */
		GridStackUI.Utils.removeAllMap = function(){
			for(var i=0; i<GridStackUI.Utils.Maps.length; i++){
				GridStackUI.Utils.Maps[i] = null;
			}
			GridStackUI.Utils.Maps = new Array();
		}

		/**
		 * 위젯에 바 차트를 생성합니다.
		 */
		GridStackUI.Utils.createBarChart = function(selector){
			if(!selector) selector = "AgeBarChart";

			var title = "연령대별 현황";
			if(arguments[1] !== undefined && arguments[1] !== "") title = arguments[1];

			var categories = [ '20세 이하', '20세~30세', '30세~40세', '40세~50세', '50세 이상' ];
			/*var series = [ {
								name : '20세 이하',
								colorByPoint: true,
								data:[ Number(354) ]
							},{
								name : '20세~30세',
								colorByPoint: true,
								data:[ Number(42) ]
							},{
								name : '30세~40세',
								colorByPoint: true,
								data:[ Number(54) ]
							},{
								name : '40~50세',
								colorByPoint: true,
								data:[ Number(134) ]
							},{
								name : '50세 이상',
								colorByPoint: true,
								data: [ Number(13) ]
							} ];*/

			var series = [{name:'사람 수', 'data':[['20세 이하', 1],['20~30', 2],['30~40', 3],['40~50', 4], ['50 이상', 5]]}];

			if(arguments[2] !== undefined && Array.isArray(arguments[2])) {
				categories = arguments[2][0];
				series = arguments[2][1];
			}
			GridStackUI.Utils.Charts.push(
				Highcharts.chart(selector, {
					chart : {
						type : 'column'
					},
					title : {
						text : title,
					},
					credits: {
			            enabled: false
			        },
			        legend: {
			        	//enabled: false
			        },
					xAxis : {
						categories : categories,
					},
					yAxis : {
						title: {
				    		enabled: false
				    	}
					},
				    tooltip: {
				        pointFormat: '<b>{point.y}명</b>'
				    },
					series : series,
				})
			);

			$("#AgeBarChart").find(".highcharts-title").addClass("title");
		}

		/**
		 * 위젯에 라인 차트를 생성합니다.
		 */
		GridStackUI.Utils.createLineChart = function(selector){
			if(!selector) selector = "TimeLineChart";

			var title = '시간대별 앱 실행 현황';
			if(arguments[1] !== undefined && arguments[1] !== "") title = arguments[1];

			var data = [22, 23, 28, 24, 13, 87, 16, 65, 14, 23, 20, 24, 18, 35, 67, 84, 94, 88, 132, 201, 21, 23, 14, 23];
			if(arguments[2] !== undefined && Array.isArray(arguments[2])) data = arguments[2];

			GridStackUI.Utils.Charts.push(
				Highcharts.chart(selector, {
				    chart: {
				        type: 'line'
				    },
				    title: {
				        text: title
				    },
				    subtitle: {
				        text: ''
				    },
				    credits: {
			            enabled: false
			        },
				    tooltip: {
				        pointFormat: '<b>{point.y}명</b>'
				    },
				    xAxis: {
				        categories: [
				        	'00시', '01시', '02시', '03시', '04시', '05시', '06시', '07시', '08시', '09시', '10시', '11시',
				          '12시', '13시', '14시', '15시', '16시', '17시', '18시', '19시', '20시', '21시', '22시', '23시',
				        ]
				    },
				    yAxis: {
				    	title: {
				    		enabled: false
				    	}
				    },
				    plotOptions: {
				        line: {
				            dataLabels: {
				                enabled: false,
				                //color: '#dd32ed',
				            },
				            enableMouseTracking: true,
				        },
				    },
				    series: [{
				        name: ' ',
				        data: data,
				        showInLegend: false
				    }]
				})
			);

			$("#TimeLineChart").find(".highcharts-title").addClass("title");
		}

		/**
		 * 위젯에 파인 차트를 생성합니다.
		 */
		GridStackUI.Utils.createEventLineChart = function(selector){
			if(!selector) selector = "EventLineChart";
			GridStackUI.Utils.Charts.push(
				Highcharts.chart(selector, {
				    chart: {
				        type: 'bar'
				    },
				    credits: {
			            enabled: false
			        },
			        legend: {
			        	//enabled: false
			        },
				    title: {
				        text: '이벤트 종류별 발생 현황'
				    },
				    xAxis: {
				        categories: ['화재', '구조', '구급', '기타', '치매노인'],
				        title: {
				            text: null
				        }
				    },
				    yAxis: {
				        min: 0,
				        title: {
				            text: ''
				        },
				        labels: {
				            overflow: 'justify'
				        }
				    },
				    tooltip: {
				        valueSuffix: ' 건'
				    },
				    plotOptions: {
				        bar: {
				            dataLabels: {
				                enabled: true
				            }
				        }
				    },
				    legend: {
				        layout: 'vertical',
				        align: 'right',
				        verticalAlign: 'top',
				        x: -40,
				        y: 80,
				        floating: true,
				        borderWidth: 1,
				        backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
				        shadow: true
				    },
				    credits: {
				        enabled: false
				    },
				    series: [{
				        name: '오늘',
				        data: [107, 31, 635, 203, 2]
				    }, {
				        name: '어제',
				        data: [133, 156, 947, 408, 6]
				    }]
				})
			);

			$("#EventLineChart").find(".highcharts-title").addClass("title");
		}

		/**
		 * 위젯에 게이지 차트를 생성합니다.
		 */
		GridStackUI.Utils.createGaugeChart = function(selector){
			if(!selector) selector = "GaugeChart";
			GridStackUI.Utils.Charts.push(
				Highcharts.chart(selector, Highcharts.merge({
					chart: {
						type: 'solidgauge'
					},
					credits: {
			            enabled: false
			        },
			        title: {
				        text: '온도'
				    },

					pane: {
						center: ['50%', '85%'],
						size: '140%',
						startAngle: -90,
						endAngle: 90,
						background: {
							backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
							innerRadius: '60%',
							outerRadius: '100%',
							shape: 'arc'
						}
					},

					tooltip: {
						enabled: false
					},

				    // the value axis
					yAxis: {
						stops: [
						    [0.1, '#55BF3B'], // green
				            [0.5, '#DDDF0D'], // yellow
				            [0.9, '#DF5353'] // red
				        ],
				        lineWidth: 0,
				        minorTickInterval: null,
				        tickAmount: 2,
				        title: {
				            y: -70
				        },
				        labels: {
				            y: 16
				        }
				    },

				    plotOptions: {
				        solidgauge: {
				            dataLabels: {
				                y: 5,
				                borderWidth: 0,
				                useHTML: true
				            }
				        }
				    }
				}, {
					yAxis: {
						min: 0,
						max: 45,
						title: {
							text: ''
						}
					},

					credits: {
						enabled: false
					},

					series: [{
						name: '온도',
						data: [35],
						dataLabels: {
							format: '<div style="text-align:center"><span style="font-size:25px;color:' +
								((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}º</span>' +
								'<span style="font-size:12px;color:silver"></span></div>'
						}
					}]
				}))
			);

			$("#GaugeChart").find(".highcharts-title").addClass("title");
		}

		/**
		 * 위젯에 히트맵 지도를 생성합니다.
		 */
		GridStackUI.Utils.createCctvHitMap = function(selector){
			if(!selector) selector = "CctvHitMap";

			var hitLayer = xeusLayout.mapService.createHeatLayer(Layers["v_asset_cctv_heat"]);

			GridStackUI.Utils.Maps.push(
				new ol.Map({
					/*controls : ol.control.defaults().extend([
						new ol.control.ScaleLine()
            	    ]),*/
					logo : false,
					renderer : 'canvas', // 'canvas' or 'webgl'
					target : selector, // 'xeus-map-content',
					layers : [
						Layers["daum_tile_map"].tile.createMapLayer({
							visible : true,
							proxy : false
						}),
						hitLayer
					],
					loadTilesWhileInteracting : true,
					loadTilesWhileAnimating : true,
					view : new ol.View({
						projection : ol.proj.get("EPSG:5186"),
						center : [ 202876.8435290633, 542661.0525658976 ],
						zoomFactor : 2,
						zoom : 15,
						minZoom : 9,
						maxZoom : 24
					})
				})
			);

			$("#CctvHitMap").find(".ol-zoom").hide();
			hitLayer.setVisible(true);
			Layers["v_asset_cctv_heat"].loadFunction(hitLayer);
		}

		/**
		 * 위젯에 이벤트 지도를 생성합니다.
		 */
		GridStackUI.Utils.createEventMap = function(selector){
			if(!selector) selector = "EventMap";

			var eventMap = new ol.Map({
				logo : false,
				renderer : 'canvas', // 'canvas' or 'webgl'
				target : selector, // 'xeus-map-content',
				layers : [
					Layers["daum_tile_map"].tile.createSkyViewLayer({
						visible : true,
						proxy : false
					}),
					Layers["daum_tile_map"].tile.createHybridLayer({
						visible : true,
						proxy : false
					}),
				//	eventVectorLayer
				],
				loadTilesWhileInteracting : true,
				loadTilesWhileAnimating : true,
				view : new ol.View({
					projection : ol.proj.get("EPSG:5186"),
					center : [ 202876.8435290633, 542661.0525658976 ],
					zoomFactor : 2,
					zoom : 15,
					minZoom : 9,
					maxZoom : 24
				})
			});

			GridStackUI.Utils.Maps.push(eventMap);

			$("#EventMap").find(".ol-zoom").hide();

			if(GridStackUI.Utils.Socket == null){
				GridStackUI.Utils.Socket = new XeusWS(eventMap);
				GridStackUI.Utils.Socket.create("ws://" + location.host + "/xeus/event");
			}
		}

		/**
		 * 날씨정보를 갱신합니다.
		 */
		GridStackUI.Utils.setWeatherData = function(selector){
			var data = BoardAPI.getWeatherData(GridStackUI.Utils.Maps[0], 37.48338931738832, 127.03252691020276);
			for(var key in data){
				$(".grid-stack-item-content[target=" + key + "]").find(".content").text(data[key]);
			}
		}

	}
})();