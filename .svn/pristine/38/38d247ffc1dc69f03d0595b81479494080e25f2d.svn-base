if (window.ChartUtils == null) var ChartUtils = {};

/**
 * <pre>
 * 차트
 * 유틸리티객체입니다.
 *
 * @author 이주영
 * </pre>
 */

ChartUtils = {

	setTheme : function() {
		Highcharts.theme = {
			colors : [ '#2b908f', '#90ee7e', '#f45b5b', '#7798BF', '#aaeeee',
					'#ff0066', '#eeaaee', '#55BF3B', '#DF5353', '#7798BF',
					'#aaeeee' ],
			chart : {
				backgroundColor : {
					// linearGradient: { x1: 0, y1: 0, x2: 1, y2: 1 },
					stops : [ [ 0, '#3b3b3b' ],
					// [1, '#3b3b3b']
					]
				},
				style : {
					fontFamily : 'KoPubDotum'
				},
				plotBorderColor : '#606063'
			},
			title : {
				style : {
					color : '#E0E0E3',
					// textTransform: 'uppercase',
					fontSize : '15px'
				}
			},
			subtitle : {
			/*
			 * style: { color: '#E0E0E3', textTransform: 'uppercase' }
			 */
			},
			credits : {
				enabled : false
			},
			legend : {
				enabled : true
			},
			xAxis : {
				gridLineColor : '#707073',
				labels : {
					style : {
						color : '#E0E0E3'
					}
				},
				lineColor : '#707073',
				minorGridLineColor : '#505053',
				tickColor : '#707073',
				title : {
					style : {
						color : '#A0A0A3'
					}
				}
			},
			yAxis : {
				gridLineColor : '#707073',
				labels : {
					style : {
						color : '#E0E0E3'
					}
				},
				lineColor : '#707073',
				minorGridLineColor : '#505053',
				tickColor : '#707073',
				tickWidth : 1,
				title : {
					enabled : false
				/*
				 * style: { color: '#A0A0A3' }
				 */
				}
			},
			tooltip : {
				backgroundColor : 'rgba(0, 0, 0, 0.85)',
				style : {
					color : '#F0F0F0'
				}
			},
			plotOptions : {
				series : {
					dataLabels : {
						color : '#B0B0B3'
					},
					marker : {
						lineColor : '#333'
					}
				},
				boxplot : {
					fillColor : '#505053'
				},
				candlestick : {
					lineColor : 'white'
				},
				errorbar : {
					color : 'white'
				},
			},
			// legend: {
			// itemStyle: {
			// color: '#E0E0E3'
			// },
			// itemHoverStyle: {
			// color: '#FFF'
			// },
			// itemHiddenStyle: {
			// color: '#606063'
			// }
			// },
			// credits: {
			// style: {
			// color: '#666'
			// }
			// },
			labels : {
				style : {
					color : '#707073'
				}
			},

			drilldown : {
				activeAxisLabelStyle : {
					color : '#F0F0F3'
				},
				activeDataLabelStyle : {
					color : '#F0F0F3'
				}
			},

			navigation : {
				buttonOptions : {
					symbolStroke : '#DDDDDD',
					theme : {
						fill : '#505053'
					}
				}
			},

			// scroll charts
			rangeSelector : {
				buttonTheme : {
					fill : '#505053',
					stroke : '#000000',
					style : {
						color : '#CCC'
					},
					states : {
						hover : {
							fill : '#707073',
							stroke : '#000000',
							style : {
								color : 'white'
							}
						},
						select : {
							fill : '#000003',
							stroke : '#000000',
							style : {
								color : 'white'
							}
						}
					}
				},
				inputBoxBorderColor : '#505053',
				inputStyle : {
					backgroundColor : '#333',
					color : 'silver'
				},
				labelStyle : {
					color : 'silver'
				}
			},

			navigator : {
				handles : {
					backgroundColor : '#666',
					borderColor : '#AAA'
				},
				outlineColor : '#CCC',
				maskFill : 'rgba(255,255,255,0.1)',
				series : {
					color : '#7798BF',
					lineColor : '#A6C7ED'
				},
				xAxis : {
					gridLineColor : '#505053'
				}
			},

			scrollbar : {
				barBackgroundColor : '#808083',
				barBorderColor : '#808083',
				buttonArrowColor : '#CCC',
				buttonBackgroundColor : '#606063',
				buttonBorderColor : '#606063',
				rifleColor : '#FFF',
				trackBackgroundColor : '#404043',
				trackBorderColor : '#404043'
			},

			// special colors for some of the
			legendBackgroundColor : 'rgba(0, 0, 0, 0.5)',
			background2 : '#505053',
			dataLabelsColor : '#B0B0B3',
			textColor : '#C0C0C0',
			contrastTextColor : '#F0F0F3',
			maskColor : 'rgba(255,255,255,0.3)'
		};

		// Apply the theme
		Highcharts.setOptions(Highcharts.theme);
	},

	createLineChart : function(selector, titleText, json, xA, yA, colExt, valExt) {
		
//		json=chartline = [{"name":"평균","data":[["1월",5.09],["2월",null],["3월",10],["4월",null],["5월",3.38],["6월",6],["7월",6.76],["8월",4],["9월",4.2],["10월",6.7],["11월",4.7],["12월",0]]},{"name":"최대","data":[["1월",10],["2월",9.2],["3월",9.3],["4월",4.6],["5월",4.9],["6월",9.6],["7월",9.9],["8월",5],["9월",9.8],["10월",9.3],["11월",7.1],["12월",0]]},{"name":"최소","data":[["1월",0],["2월",0],["3월",0],["4월",0],["5월",0],["6월",0],["7월",0],["8월",0],["9월",0],["10월",0],["11월",0],["12월",0]]}]
//		console.log("chartline1111 = "+JSON.stringify(json));
		this.setTheme();
		if ( colExt == undefined ) colExt = '';
		if ( valExt == undefined ) valExt = '';
		Highcharts.chart(selector, {
			title : {
				align : 'left',
				text : titleText
			},
			xAxis: {
		        labels: {
		            format: '{value}'+yA
		        },
		    },
			xAxis : xA,
			//yAxis : yA,
			navigation : {
				buttonOptions : {
					enabled : false
				}
			},
			 tooltip: {
			        pointFormat: '{point.y} '+valExt,
			        shared: false
			},

			series:json,

			/*series : json,
			pointStart: 2010*/
		});
	},

	createSplineChart : function(selector, titleText, json, xA, yA, colExt, valExt, dateParam) {
		this.setTheme();
		if ( colExt == undefined ) colExt = '';
		if ( valExt == undefined ) valExt = '';
		Highcharts.chart(selector, {

			chart: {
		        type: 'spline',
		        scrollablePlotArea: {
		            minWidth: 600,
		            scrollPositionX: 1
		        }
		    },

			title : {
				align : 'left',
				text : titleText
			},
			xAxis: {
				labels: {
					format: '{value}'+yA
				},
			},
			xAxis : xA,
			//yAxis : yA,
			navigation : {
				buttonOptions : {
					enabled : false
				}
			},

			tooltip: {
				pointFormat: '{point.y} '+valExt,
				shared: false
			},

			series:json,

			/*series : json,
			pointStart: 2010*/
		});
	},

	createSplineTimeChart : function(selector, titleText, json, xA, yA, colExt, valExt, dateParam) {
		if(titleText.indexOf('풍속/풍향')>=0){
			//풍속,풍향 차트
			this.createSpilneTimeWspdChart(selector, titleText, json, xA, yA, colExt, valExt, dateParam);
			return;
		}
		this.setTheme();
		if ( colExt == undefined ) colExt = '';
		if ( valExt == undefined ) valExt = '';
		Highcharts.chart(selector, {

			chart: {
				type: 'spline',
				scrollablePlotArea: {
					minWidth: 600,
					scrollPositionX: 1
				}
			},

			title : {
				align : 'left',
				text : titleText
			},
			xAxis: {
				labels: {
					format: '{value}'+yA
				},
			},
			xAxis : xA,
			//yAxis : yA,
			navigation : {
				buttonOptions : {
					enabled : false
				}
			},

			tooltip: {
		        headerFormat: '<b>{series.name}</b><br/>',
		        pointFormat: '{point.x:%Y-%m-%d %H:%M:%S}<br/>{point.y:.2f}'
		    },

			series:json,

			/*series : json,
			pointStart: 2010*/
		});
	},
	
	createSpilneTimeWspdChart : function(selector, titleText, json, xA, yA, colExt, valExt, dateParam) {
	
		this.setTheme();
		if ( colExt == undefined ) colExt = '';
		if ( valExt == undefined ) valExt = '';
		Highcharts.chart(selector, {

			chart: {
				type: 'spline',
				scrollablePlotArea: {
					minWidth: 600,
					scrollPositionX: 1
				}
			},

			title : {
				align : 'left',
				text : titleText
			},
			xAxis: {
				labels: {
					format: '{value}'+yA
				},
			},
			xAxis : xA,
			//yAxis : yA,
			navigation : {
				buttonOptions : {
					enabled : false
				}
			},

			tooltip: {
		        headerFormat: '<b>{series.name}</b><br/>',
		        pointFormat: '{point.x:%Y-%m-%d %H:%M:%S}<br/>{point.y:.2f} / {point.z}'
		    },

			series:json,

			/*series : json,
			pointStart: 2010*/
		});
	},

	createAreaChart : function(selector, titleText, json, xA, yA, colExt, valExt) {
		this.setTheme();
		if ( colExt == undefined ) colExt = '';
		if ( valExt == undefined ) valExt = '';
		Highcharts.chart(selector, {
			chart: {
				type: 'area'
			},
			title : {
				align : 'left',
				text : titleText
			},
			xAxis: {
				labels: {
					format: '{value}'+yA
				},
			},
			xAxis : xA,
			//yAxis : yA,
			navigation : {
				buttonOptions : {
					enabled : false
				}
			},

			tooltip: {
				pointFormat: '{point.y} '+valExt,
				shared: false
			},

			series:json,

			/*series : json,
			pointStart: 2010*/
		});
	},

	createBarChart : function(selector, titleText, json, xA, yA, valExt) {
		
//		json= [{"name":"평균","data":[["2018년",1.01],["2019년",""]]},{"name":"최대","data":[["2018년",14.8],["2019년",1]]},{"name":"최소","data":[["2018년",5],["2019년",-8.9]]}];
//		json=null;
//		console.log("hard coding");
//		console.log("json(chartaaaa) = "+JSON.stringify(json));
		
		this.setTheme();
		Highcharts.chart(selector, {
			chart : {
				type : 'column'
			},
			title : {
				align : 'left',
				text : titleText
			},
			xAxis : xA,
			yAxis : yA,
			tooltip: {
				pointFormat: '{point.y} '+valExt,
				shared: false
			},
			/*plotOptions : {
				column : {
					pointPadding : 0.2,
					borderWidth : 0
				}
			},*/

			navigation : {
				buttonOptions : {
					enabled : false
				}
			},
			series : json,
		});
	},

	randomRange : function(n1, n2) {
		return Math.floor((Math.random() * (n2 - n1 + 1)) + n1);
	},
}
