if (window.NdmsData == null) var NdmsData = {};

/**
 * <pre>
 * 차트
 * 유틸리티객체입니다.
 *
 * @author 안형준
 * </pre>
 */
NdmsData = {

    dataView : function(cde, objArr) {

    	switch(cde) {
	    	case '1' :
	    		//소방
	    		NdmsData.fireData(objArr);
	    		break;
	    	case '2' :
	    		//기상특보 통보문
	    		NdmsData.weatherInfoData(objArr);
	    		break;
	    	case '3' :
	    		//예비특보 통보문
	    		NdmsData.spareInfoData(objArr);
	    		break;
	    	case '4' :
	    		//지진 통보문
	    		NdmsData.EartInfoData(objArr);
	    		break;
	    	case '5' :
	    		//지역 별 강우량
	    		NdmsData.areaRainData(objArr);
	    		break;
	    	case '6' :
    			//댐 수위
	    		NdmsData.damData(objArr);
    			break;
    		case '7' :
    			//지역 별 aws
    			NdmsData.awsData(objArr);
    			break;
    		case '8' :
    			//강우 수위
    			NdmsData.lvlData(objArr);
    			break;
    		case '9' :
    			//동네예보 강수확률
    			NdmsData.townRainData(objArr);
    			break;
    	}

    },



    /**
     * 긴급구조 소방 전문
     *
     */
    fireData : function(obj){
    	var str = '';
//    	var addr = obj.dsretcaddr;
//    	if ( addr == null ) addr = '없음';
    	str += '<div>';
    	str += '	<p>○ 신고 일시 - '+new Date().formatYMDHMS(obj.rept_dt)+'</p>';
    	str += '	<p>○ 신고 지역 명 - '+obj.rept_area_nm+' </p>';
    	str += '	<p>○ 신고 내용 - '+obj.rept_desc+' </p>';
    	str += '	<p>○ 긴급구조 위치 - '+obj.dstr_area_nm+' </p>';
    	str += '	<p>○ 긴급 구조 유형1 - '+obj.pttn_nm1+' </p>';
    	str += '	<p>○ 긴급 구조 유형2 - '+obj.pttn_nm2+' </p>';
    	str += '	<p>○ 긴급 구조 유형3 - '+obj.pttn_nm3+' </p>';
    	str += '	<p>○ 재난 규모 - '+obj.dstr_scop_nm+' </p>';
    	str += '	<p>○ 관제산황구분 - '+obj.dstr_stat_nm+' </p>';
    	str += '</div>';
    	$('#ndms_inform').html(str);

    	$(".ndmsAddedColumnRegTable").find(".sendData").each(function(){
    	    for(var key in obj){
    	        if(snakeToCamel(key) == $(this).attr("id")){
    	        	$(this).val(obj[key]);
    	        }
    	    }
    	});

//    	$(".ndmsAddedColumnRegTable").find("#_gid").val(obj._gid);
    },

    /**
     * 기상예보 통보문
     *
     */
    weatherInfoData : function(obj){
    	var str = '';
    	str += '<div>';
    	str += '	<p>○ 제목 - '+obj.etcttl+' </p>';
    	str += '	<p>○ 지역명 - '+obj.userdefineareanm+'</p>';
    	str += '	<p>○ 발표시각 - '+new Date().formatYMDHMS(obj.dttmfc)+'</p>';
    	str += '</div>';
    	if ( obj.statctnt != '' ) {

    		str += '<div>';
    		str += '	<p>○ 발표내용</p>';
    		str += '	<textarea readonly>'+obj.statctnt+'</textarea>';
    		str += '</div>';
    	}
    	str += '<div>';
    	str += '	<p>○ 예비 특보 발효 현황</p>';
    	str += '	<textarea readonly>'+obj.statpwvl+'</textarea>';
    	str += '</div>';
    	str += '<div>';
    	str += '	<p>○ 특보 발효 현황 내용</p>';
    	str += '	<textarea readonly>'+obj.stattmef+'</textarea>';
    	str += '</div>';
    	str += '<div>';
    	str += '	<p>○ 참고사항</p>';
    	str += '	<textarea readonly>'+obj.etcref+'</textarea>';
    	str += '</div>';

    	$('#ndms_inform').html(str);
    },

    /**
     * 예비특보 통보문
     *
     */
    spareInfoData : function(obj){
    	var str = '';
    	console.log(obj);
    	str += '<div>';
    	str += '	<p>○ 예보관 - '+obj.nmmanfc+' </p>';
    	str += '	<p>○ 발표시각 - '+new Date().formatYMDHMS(obj.dttmfc)+'</p>';
    	str += '</div>';
    	str += '<div>';
    	str += '	<p>○ 특보 발효 현황 내용 1</p>';
    	str += '	<textarea readonly>'+obj.stattm1+'</textarea>';
    	str += '</div>';
    	str += '<div>';
    	str += '	<p>○ 특보 발효 현황 내용 2</p>';
    	str += '	<textarea readonly>'+obj.stattm2+'</textarea>';
    	str += '</div>';

    	$('#ndms_inform').html(str);
    },

    /*
     * 지진현황 통보문
     *
     */
    EartInfoData : function(obj){
    	var str = '';
    	str += '<div>';
    	str += '	<p>○ 발생시각 - '+new Date().formatYMDHMS(obj.event_time)+'</p>';
    	str += '	<p>○ 발표시각 - '+new Date().formatYMDHMS(obj.report_time)+'</p>';
    	str += '	<p>○ 위치 - '+obj.location+' </p>';
    	str += '	<p>○ 위도- '+obj.latitude+' 경도- '+obj.longitude+'</p>';
    	str += '	<p>○ 규모 - '+obj.magnitude+'</p>';
    	str += '	<p>○ 발생깊이 - '+obj.depth+'</p>';
    	str += '	<p>○ 진도구분 - '+obj.intensity_type+'</p>';
    	str += '	<p>○ 진도등급 - '+obj.intensity_desc+'</p>';
    	str += '</div>';
    	str += '<div>';
    	str += '	<p>○ 참고사항</p>';
    	str += '	<textarea readonly>'+obj.instruction+'</textarea>';
    	str += '</div>';

    	$('#ndms_inform').html(str);
    },



	/**
	 * 댐 별 수위
	 *
	 */
    damData : function(objArr){

		var arrData = [];
		var datData = [];

		var cdstnData = {};
		var owlData= [];
		var infData= [];
		var ecpcData= [];
		var tototfData= [];

		for ( var i = 0; i < objArr.length; i++ ) {
			var obj = objArr[i];
			var dat = String(obj.ymdhm).substring(8,10)+':'+String(obj.ymdhm).substring(10,12);
			var damnm = obj.damnm;
			var owl = (Number(obj.owl)/10)/10;// 방수로 수위
			var ecpc = Number(obj.ecpc);// 공용량
			var inf = Number(obj.inf);// 유입량
			var tototf = Number(obj.tototf);// 총 방류량


			if ( cdstnData[damnm] == null ) {
				cdstnData[damnm] = {
						owlData : []
						, infData : []
						, ecpcData : []
						, tototfData : []
				}


			}
			//if ( !datData.includes(dat) ) {
				datData.push(dat);
			//}
			cdstnData[damnm].owlData.push([dat, owl]);
			cdstnData[damnm].infData.push([dat, inf]);
			cdstnData[damnm].ecpcData.push([dat, ecpc]);
			cdstnData[damnm].tototfData.push([dat, tototf]);

			//if ( !datData.includes(dat) ) {

			//cdstnData[damnm].data.push([dat, obsrvalue]);
			//}

		}

		datData.sort();

		for ( cdstnObj in cdstnData ) {

			var obj = cdstnData[cdstnObj];

			obj.owlData.sort();
			obj.infData.sort();
			obj.ecpcData.sort();
			obj.tototfData.sort();

			owlData.push({
				name : cdstnObj,
				data : obj.owlData
			});

			infData.push({
				name : cdstnObj,
				data : obj.infData
			});

			ecpcData.push({
				name : cdstnObj,
				data : obj.ecpcData
			});

			tototfData.push({
				name : cdstnObj,
				data : obj.tototfData
			});
		}

		var xAxis = {
			categories: datData
	    };

		$('#ndms_chart').html('');
		$('#ndms_chart').append('<div id="ndms_chart1"></div>');
	//	$('#ndms_chart').append('<div id="ndms_chart2"></div>');
		$('#ndms_chart').append('<div id="ndms_chart3"></div>');
		$('#ndms_chart').append('<div id="ndms_chart4"></div>');

		ChartUtils.createLineChart('ndms_chart1', '방수로 수위(단위 : EL.m)', owlData, xAxis, 'EL.m', 'EL.m');
	//	ChartUtils.createLineChart('ndms_chart2', '공용량(단위 : m)', infData, xAxis, 'm', 'm');
		ChartUtils.createLineChart('ndms_chart3', '유입량(단위 : m3/sec)', infData, xAxis, 'm3/sec', 'm3/sec');
		ChartUtils.createLineChart('ndms_chart4', '총 방류량(단위 : m3/sec)', tototfData, xAxis, 'm3/sec', 'm3/sec');
	},

	/**
	 * 지역 별 수위
	 *
	 */
	lvlData : function(objArr){

		var arrData = [];
		var datData = [];

		var cdstnData = {};
		var odsrData= [];
		for ( var i = 0; i < objArr.length; i++ ) {
			var obj = objArr[i];
			var dat = String(obj.obsrdttm).substring(8,10)+':'+String(obj.obsrdttm).substring(10,12);
			var obstnm = obj.obstnm;
			var obsrvalue = Number(obj.obsrvalue);// 강우량

			if ( obsrvalue < -900 ) obsrvalue = 0;
			if ( cdstnData[obstnm] == null ) {
				cdstnData[obstnm] = {
						data : []
				}
			}

			//if ( !datData.includes(dat) ) {
				datData.push(dat);
			//}
			cdstnData[obstnm].data.push([dat, obsrvalue]);

		}

		datData.sort();

		for ( cdstnObj in cdstnData ) {

			var obj = cdstnData[cdstnObj];

			obj.data.sort();
			odsrData.push({
				name : cdstnObj,
				data : obj.data
			});

		}

		var xAxis = {
				categories: datData
		};

		$('#ndms_chart').html('');
		$('#ndms_chart').append('<div id="ndms_chart1"></div>');

		ChartUtils.createLineChart('ndms_chart1', '수위(단위 : m)', odsrData, xAxis, 'm', 'm');
	},

	/**
	 * 지역 별 강우량
	 *
	 */
	areaRainData : function(objArr){

		var arrData = [];
		var datData = [];

		var cdstnData = {};
		var odsrData= [];
		for ( var i = 0; i < objArr.length; i++ ) {
			var obj = objArr[i];
			var dat = String(obj.obsrdttm).substring(8,10)+':'+String(obj.obsrdttm).substring(10,12);
			var startTime = objArr.obsrdttm+"";

			var obstnm = obj.obstnm;
			var obsrvalue = Number(obj.obsrvalue);// 강우량

			if ( obsrvalue < -900 ) obsrvalue = 0;
			if ( cdstnData[obstnm] == null ) {
				cdstnData[obstnm] = {
						data : []
				}


			}
			//if ( !datData.includes(dat) ) {
				datData.push(dat);
			//}
			console.log(obj.obsrdttm);
			cdstnData[obstnm].data.push([dat, obsrvalue]);

		}

		datData.sort();

		for ( cdstnObj in cdstnData ) {

			var obj = cdstnData[cdstnObj];
			var newData = [];
			obj.data.sort();

			for ( var i = 0; i < obj.data.length; i++ ) {
				newData.push(obj.data[i][1]);
			}

			odsrData.push({
				name : cdstnObj,
				data : newData
			});

		}

		var xAxis = {
			categories: datData
		};
		$('#ndms_chart').html('');
		$('#ndms_chart').append('<div id="ndms_chart1"></div>');

		ChartUtils.createSplineChart('ndms_chart1', '강우량(단위 : mm)', odsrData, xAxis, 'mm', 'mm');
	},

	/**
	 * AWS
	 *
	 */
	awsData : function(objArr){

		var datData = [];

		/*var cdstnData = {
				name :,
				data : []
		};*/
		var wthrHmData= {
				name : '평균 습도',
				data : []
		};
		var wthrDayData = {
				name : "일 누적 강수량",
				data : []
		};

		var atavgtaData = {
				name : "평균 기온",
				data : []
		};
		var wvwsData = {
				name : "평균 풍속",
				data : []
		};

		for ( var i = 0; i < objArr.length; i++ ) {
			var obj = objArr[i];
			var dat = obj.dtobz.substring(8,10)+'시';
			var cdstn = obj.cdstn;
			var wthrhm = Number(obj.wthrhm)/10;// 평균 습도 %
			var wthrday = Number(obj.wthrday)/10;//누적 강수량 mm
			var atavgta = Number(obj.atavgta) / 10; // 평균 기온 C
			var wvws = Number(obj.wvws)/10;//풍속 m/s

			if ( atavgta < -90 ) atavgta=null;
			if ( wthrhm < -90 ) wthrhm=null;
			if ( wvws < 0) wvws=null;
			if ( wthrday < -90 ) wthrday = null;
/*
			if ( cdstnData[cdstn] == null ) {
				cdstnData[cdstn] = {
						wthrHm : []
						, wthrDay : []
						, atAvg : []
						, wvWs : []
				}

				//wthrHmData.push([dat, wthrhm]);
				//wthrDayData.push([dat, wthrday]);
				//atavgtaData.push([dat, atavgta]);
				//wvwsData.push([dat, wvws]);

			}

			cdstnData[cdstn].wthrHm.push([dat, wthrhm]);
			cdstnData[cdstn].wthrDay.push([dat, wthrday]);;
			cdstnData[cdstn].atAvg.push([dat, atavgta]);
			cdstnData[cdstn].wvWs.push([dat, wvws]);
*/

			if ( !datData.includes(dat) ) {

				/*datData.push(dat);
				testData = {
						name : cdstn,
						data : [],
				}*/
				datData.push(dat);
				wthrHmData.data.push([dat, wthrhm]);
				wthrDayData.data.push([dat, wthrday]);
				atavgtaData.data.push([dat, atavgta]);
				wvwsData.data.push([dat, wvws]);
			}

		}
		datData.sort();
		wthrHmData.data.sort();
		wthrDayData.data.sort();
		atavgtaData.data.sort();
		wvwsData.data.sort();
/*		for ( cdstnObj in cdstnData ) {

			var obj = cdstnData[cdstnObj];

			obj.wthrHm.sort();
			obj.wthrDay.sort();
			obj.atAvg.sort();
			obj.wvWs.sort();
			console.log(cdstnObj);

			wthrHmData.push({
				name : cdstnObj,
				data : obj.wthrHm
			});
			wthrDayData.push({
				name : cdstnObj,
				data : obj.wthrDay
			});
			atavgtaData.push({
				name : cdstnObj,
				data : obj.atAvg
			});
			wvwsData.push({
				name : cdstnObj,
				data : obj.wvWs
			});
		}*/



		var xAxis = {
			categories: datData
	    };

		$('#ndms_chart').html('');
		$('#ndms_chart').append('<div id="ndms_chart1"></div>');
		$('#ndms_chart').append('<div id="ndms_chart2"></div>');
		$('#ndms_chart').append('<div id="ndms_chart3"></div>');
		$('#ndms_chart').append('<div id="ndms_chart4"></div>');

		ChartUtils.createLineChart('ndms_chart1', '누적 강수량(단위 : mm)', [wthrDayData], xAxis, 'mm', 'mm');
		ChartUtils.createAreaChart('ndms_chart2', '평균 기온(단위 : °C)', [atavgtaData], xAxis, '°C', '시', '°C');
		ChartUtils.createAreaChart('ndms_chart3', '평균 습도(단위 : %)', [wthrHmData], xAxis, '%', '시', '%');
		ChartUtils.createAreaChart('ndms_chart4', '평균 풍속(단위 : m/s)', [wvwsData], xAxis, 'm/s', '시', 'm/s');

	},

    /**
     * 동네예보 강수확률
     *
     */
    townRainData : function(objArr){

    	var arrData = [];
    	objArr = objArr[0];
    	var startTime = objArr.dttmfc+"";
    	var dat = new Date();
		var dataArr = [];
		var datArr = [];
    	dat.setYear(startTime.substring(0,4));
    	dat.setMonth(startTime.substring(4,6));
    	dat.setDate(startTime.substring(6,8));
    	dat.setHours(startTime.substring(8,10));

    	for ( var i = 0; i < 22; i++ ) {
    	//dat.setTime(dat.getTime((i+1)*360000));

    		var hh = new Date(Date.parse(dat) + (i*1000) * 60 * 60);

    		datArr.push(hh.getHours()+'시');
    		dataArr.push([hh.getHours()+'시', Number(objArr['qtyrsrat'+(i+1)]) ]);

    	}

    	arrData.push({
    		name: '강수확률',
    		data: dataArr
    	});

    	var xAxis = {
    			categories:datArr

    	};

    	ChartUtils.createBarChart('ndms_chart', '동네예보 강수확률(단위 : %)', arrData, xAxis, '%', '%');
    }

}

function snakeToCamel(source) {
	var val = source;
	var result = source.match(/\w*(\w\_\w)+\w*/g);
	if (result != null) {
		for (var i = 0; i < result.length; i++) {
			var word = result[i].toLowerCase();
			var arrUnderbar = word.match(/\_[a-zA-Z]/g);
			for (var j = 0; j < arrUnderbar.length; j++) {
				word = word.replace(arrUnderbar[j], arrUnderbar[j]
						.toUpperCase().replace("_", ""));
			}
			val = val.replace(result[i], word);
		}
	}
	return val;
}




$("#" + parentView).find("#overlay-east-bar").find("#closeBtn").click(function(){
	xeusLayout.WEST=SEND_BTN_SMS_VIEW_WEST_SIZE;
	xeusLayout.EAST=600;
	xeusLayout.hideOverlayEastPane(500);
});


$("#ndmsMapClickBtn").click(function(){
    $("body").css("cursor", "crosshair");
    $("#" + parentView).find(".selectCancel").show(500);		//알았다.
    xeusLayout.mapService.getMap().on('click', Public.CCTV.Add.Start);
    Public.StopEvent = function(){
        $("body").css("cursor", "default"). off("click");
        $("#" + parentView).find(".selectCancel").hide(500);
        xeusLayout.mapService.getMap().un('click', Public.CCTV.Add.Start);
    }
});


$("#ndmsSaveBtn").click(function(){

	var param = _common.utils.collectSendData(".ndmsAddedColumnRegTable");

	for(var key in param){

//		if(key == "casualties" || key == "memo" || key == "propertyDamage"){
//			continue;
//		}

		if(param[key] == ""){
			var name = $(".ndmsAddedColumnRegTable").find("#"+ key).prop("name");
			alert(name + "을 입력하세요.");
			return;
		}
	}

	var lng = param["lng"];
    var lat = param["lat"];

    param["_annox"] = lng;
    param["_annoy"] = lat;
    //긴급주고 key
    param["key"] = 1;


	confirm("저장하시겠습니까?", function(){
		_common.callAjax("/ndms/editNdmsDataByNdmsApi.json", param, function(json) {
			if(json.result){

//				var emdCd = $("#dstrAreaNm").val();
//				var obj = NDMS_OBJ_LIST[emdCd]
//				$(".ndmsAddedColumnRegTable").find(".sendData").each(function(){
//		    	    for(var key in obj){
//		    	        if(snakeToCamel(key) == $(this).attr("id")){
//		    	        	obj[key] = $(this).val();
//		    	        }
//		    	    }
//		    	});
//
//				$(".searchWrapper").find("#resultList").find(".tCenter").each(function(){
//			        var kOfTr = $(this).attr("k");
//			        if(kOfTr == emdCd){
//			        	var _annox = $(".ndmsAddedColumnRegTable").find("#lng").val();
//			        	var _annoy = $(".ndmsAddedColumnRegTable").find("#lat").val();
//
//			        	$(this).data()._annox = _annox
//			        	$(this).data()._annoy = _annoy
//			        }
//				});
				$("#" + parentView).find("#startDate").val(NDMS_PARAM_LIST.startDate);
				$("#" + parentView).find("#endDate").val(NDMS_PARAM_LIST.endDate);
				$("#" + parentView).find("#searchTable").find("#emdCd").val(NDMS_PARAM_LIST.emdCd);

				$(".searchWrapper").find("#searchBtn").click();

				alert("저장되었습니다.");
			}else{
				alert("서버에 요청 중 문제가 발생했습니다.\n관리자에게 문의하여 주십시오.");
			}
		});
	});
});

