if (window.NdpsData == null) var NdpsData = {};

/**
 * <pre>
 * 차트
 * 유틸리티객체입니다.
 *
 * @author 안형준
 * </pre>
 */
NdpsData = {

		/**
		 * NDPS 차트 표출
		 *  - 라인 차트
		 *
		 * @param objArr 데이터 리스트 [MAP1,MAP2...]
		 * @param nm 관측소명
		 */
		getChartData : function(objArr, nm, ext){
			var dataObj= {
					name : nm,
					data : []
			};
			var date = new Date();

			for ( var i = 0; i < objArr.length; i++ ) {
				var obj = objArr[i];
				var num;
				//cm 이면 데이터%10, mm이면 데이터 그대로
				if(ext==='cm'){
					num=Number(obj.data/10);
				}
				else{
					num=Number(obj.data);
				}
				dataObj.data.push(
						{
							x: new Date().formatStrDatTODat( obj.dt).getTime()+(3600000*9),
							y : num
						}
				);
			}
			var xAxis = {
			        type: 'datetime',
			        tickPixelInterval: 150
			    };

			// $("#center-overlay-east").html('');
			// $('#center-overlay-east').append('<div id="ndps_chart1"></div>');
			$('#ndps_chart').html('');
			$('#ndps_chart').append('<div id="ndps_chart1"></div>');

			ChartUtils.createSplineTimeChart('ndps_chart1', nm+'(단위:'+ext+')', [dataObj], xAxis, ext, ext);
		},

		getChartAwsData : function(objArr, nm, ext){
			var awsList=['tempData','humiData','wspdData'];
			var awsNmList=['온도','습도','풍속/풍향'];
			var awsExtList=['℃','%','m/s']
			$('#ndps_chart').html('');

			for(var i=0; i<awsList.length; i++){
				var dataObj= {
						name : nm,
						data : []
				};
				var date = new Date();

				for ( var j = 0; j < objArr.length; j++ ) {

					var obj = objArr[j];
					var num;
					//cm 이면 데이터%10, mm이면 데이터 그대로
					num=Number(eval('obj.'+awsList[i]+'/10'));
					if(awsList[i]=='wspdData'){
						dataObj.data.push(
								{
									x: new Date().formatStrDatTODat( obj.dt).getTime()+(3600000*9),
									y : num,
									z : this.getWdirNmFromWdirData(obj.wdirData/10)
								}
						);
					}else{
						dataObj.data.push(
								{
									x: new Date().formatStrDatTODat( obj.dt).getTime()+(3600000*9),
									y : num
								}
						);
					}


				}
				var xAxis = {
			        type: 'datetime',
			        tickPixelInterval: 150
				};

				// $("#center-overlay-east").html('');
				// $('#center-overlay-east').append('<div id="ndps_chart1"></div>');
				$('#ndps_chart').append('<div id="ndps_chart'+i+'"></div>');

				ChartUtils.createSplineTimeChart('ndps_chart'+i, awsNmList[i]+'(단위:'+awsExtList[i]+')', [dataObj], xAxis, awsExtList[i], awsExtList[i]);
			}


		},

		getWdirNmFromWdirData : function(wdirData){
			if(0<wdirData && wdirData<=11.25){
				return '북';
			}
			else if(11.25<wdirData && wdirData<=33.75 ){
				return '북북동';
			}
			else if(33.75<wdirData && wdirData<=56.25 ){
				return '북동';
			}
			else if(56.25<wdirData && wdirData<=78.75 ){
				return '동북동';
			}
			else if(78.75<wdirData && wdirData<=101.25 ){
				return '동';
			}
			else if(101.25<wdirData && wdirData<=123.75){
				return '동남동';
			}
			else if(123.75<wdirData && wdirData<=146.25 ){
				return '남동';
			}
			else if(146.25<wdirData && wdirData<=168.75 ){
				return '남남동';
			}
			else if(168.75<wdirData && wdirData<=191.25 ){
				return '남';
			}
			else if(191.25<wdirData && wdirData<= 213.75){
				return '남남서';
			}
			else if(213.75<wdirData && wdirData<=236.25 ){
				return '남서';
			}
			else if(236.25<wdirData && wdirData<=258.75 ){
				return '서남서';
			}
			else if(258.75<wdirData && wdirData<=281.25  ){
				return '서';
			}
			else if(281.25 <wdirData && wdirData<=303.75  ){
				return '서북서';
			}
			else if(303.75 <wdirData && wdirData<=326.25  ){
				return '북서';
			}
			else if(326.25 <wdirData && wdirData<=348.75  ){
				return '북북서';
			}
			else if(348.75 <wdirData){
				return '북';
			}
			else{
				return '';
			}

		},



};

$("#" + parentView).find("#overlay-east-bar").find("#closeBtn").click(function(){
	xeusLayout.WEST=NDPS_BTN_VIEW_WEST_SIZE;
	xeusLayout.EAST=600;
	xeusLayout.hideOverlayEastPane(500);
});
