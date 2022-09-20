/**
 * 통계 화면 입니다.<br>
 */
function chartResize() {

		var contentHeight = $('#layout-body').innerHeight()-300;
		//$('#stat_chart_type').css('height', contentHeight + 'px');
		$('#stat_chart_type').css('height', '69%');
	};

$(window).resize(function(){
	chartResize();
	console.log('2');
});

$(document).ready(function (){

	var schObj  = {};
	//통계종류 셀렉트
	$("#" + parentView).find('.stat_type').on('change', function(){
		var v = $(this).val();
		var target = $(this).attr('target');

		$('.stat_select_obj').val('');
		$('.stat_select_obj .active').removeClass('active');
		
		if ( v != '' ) {
			$('#'+target+' option[k='+v+']').addClass('active');
		}
		if(v=='dmmst'){
			$('#stat_emd').addClass('non');
			$('#stat_emd_sp').addClass('non');
			
			$('#stat_damnm').removeClass('non');
			$('#stat_damnm_sp').removeClass('non');
		}
		else{
			$('#stat_emd').removeClass('non');
			$('#stat_emd_sp').removeClass('non');
			
			$('#stat_damnm').addClass('non');
			$('#stat_damnm_sp').addClass('non');
		}
	});

	//NDPS 셀렉트
	$("#" + parentView).find('#stat_sub_type').on('change', function(){
		var v = $(this).val();
		var target = $(this).attr('target');
		if ( target === 'stat_eqb' ) {

			$('#'+target).val('');
			$('#'+target+' option').remove('');

			if ( v != '' ) {
				getEqgList(v);
			}
		}

	});

	//통계종류 셀렉트
	$("#" + parentView).find('#stat_bet_select').on('change', function(){
		var v = $(this).val();
		var target = $(this).attr('target');

		$('#stat_bec_select').val('');
		$('#stat_bec_select .active').removeClass('active');


		$('#stat_sec_select').val('');
		$('#stat_sec_select .active').removeClass('active');

		if ( v != '' ) {
			$('#'+target+' option[k="'+v+'"]').addClass('active');
		}
	});

	//통계종류 셀렉트
	$("#" + parentView).find('#stat_bec_select').on('change', function(){
		var v = $(this).val();
		var target = $(this).attr('target');

		$('#stat_sec_select').val('');
		$('#stat_sec_select .active').removeClass('active');

		if ( v != '' ) {
			$('#'+target+' option[k="'+v+'"]').addClass('active');
		}
	});

	//연도 셀렉트
	$("#" + parentView).find('#stat_year').on('change', function(){
		var v = $(this).val();

		if ( v === '' ) {
			$('#stat_month').val('');
			$('#stat_month .active').removeClass('active');

		} else {
			$('#stat_month option').addClass('active');
		}
	});

	//표출 방식 전환.
	$('#stat_change_btn').on('click', function(){

		if ( $(this).text() === '테이블 조회' ) {
			$(this).text('차트 조회');

			$('#stat_chart_type').hide();
			$('#stat_table_type').show();

		} else {
			$(this).text('테이블 조회');

			$('#stat_table_type').hide();
			$('#stat_chart_type').show();
		}
	});
	
	//외부페이지 이동
	$('#external_page_btn').on('click', function(){
		var popUrl = "http://bangjae.sejong.go.kr/report4409.asp?pflag=1";	//팝업창에 출력될 페이지 URL
	//	var popUrl = "http://www.naver.com";
		var popOption = "width=1000, height=700";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	});

	$('#stat_event_search_btn').on('click', function(){

		$("#" + parentView).find("#wrap").find('#userList colgroup').html('');
		$("#" + parentView).find("#wrap").find('#userList tbody').html('');
		$("#" + parentView).find("#wrap").find('#userList thead').html('');
		$("#" + parentView).find("#wrap").find('stat_table_type').html('');

		var statBet = $('#stat_bet_select').val();//대분류 ( 재난 / 이벤트 )
		var statBec = $('#stat_bec_select').val();//중분류 ( 사회재난 자연재난, 지능형 NDMS NDPS )
		var statSec = $('#stat_sec_select').val();//소분류 ( 배회 등등 )
		var statYear = $('#stat_year').val();//월
		var statMonth = $('#stat_month').val();//월

		var statCate = $('#stat_bet_select option[value="'+statBet+'"]').text();//통계 대분류.
		var statExt = '건';//
		var statTitle = $('#stat_bec_select option[value="'+statBec+'"]').text();//통계 제목.

		if ( statBet === '' ) {
			alert('이벤트 종류는 필수 선택 입니다.');

			return false;
		}

		//통계 전체 타입 설정
		var pag = 'getEventStatYear';//연도별
		var ext = '년';//연도별
		var dat = 'year';//연도별

		if ( statYear != '' && statMonth === '' ) {
			pag = 'getEventStatMonth';//월별
			ext = '월';
			dat = 'month';
		} else if ( statYear != '' &&  statMonth != '' ) {
			pag = 'getEventStatDay';//일별
			ext = '일';
			dat = 'day';
		}

		if ( statBec === '' ) {
			statTitle = statCate;
		}
		var param = {
				bet : statBet
				, bec : statBec
				, sec : statSec
				, time: statYear+statMonth
				, excel : pag
				, exceltitle : statCate+' '+statBec+' '+statSec+'_'+statYear+statMonth+'_'
		};
//		console.log("param(오늘) = "+JSON.stringify(param));
		schObj = param;
		_common.callAjax("/stat/"+pag+".json", param, function(json){
		
			var result = json.result;
//			console.log("result9zz = "+JSON.stringify(result));
			var datList = json.datList;
//			console.log("result9gg(datlist) = "+JSON.stringify(datList));
			try{
				$("#" + parentView).find("#wrap").find('#userList colgroup').html(createCol(datList.length+2));
				$("#" + parentView).find("#wrap").find('#userList thead').html(createThead(datList, dat, ext));

				$("#" + parentView).find("#wrap").find('#userList tbody').html(createBody(result, dat, statExt, statTitle));
				$('#stat_table_title').text(statTitle+'( 단위 : '+statExt+' )');

				createEventChart(result, datList, dat, ext, statExt, statTitle);
			}catch(e){
				ChartUtils.createBarChart('stat_chart_type', ' ', null, null, null, null);
			}
			
		}, false);
	});

	$('#stat_ndms_search_btn').on('click', function(){
		
		$("#" + parentView).find("#wrap").find('#userList colgroup').html('');
		$("#" + parentView).find("#wrap").find('#userList tbody').html('');
		$("#" + parentView).find("#wrap").find('#userList thead').html('');
		$("#" + parentView).find("#wrap").find('stat_table_type').html('');

		var statType = $('#stat_type').val();//통계 종류
		var statSubType = $('#stat_sub_type').val();//통계 기준
		var statYear = $('#stat_year').val();//연도
		var statMonth = $('#stat_month').val();//월
		var statType = $('.stat_type').val();
		var statEmd;
		if( statType=='dmmst'){
			statEmd = $('#stat_damnm').val();//지역
		}
		else{
			statEmd = $('#stat_emd').val();//지역
		}

		var statExt = $('#stat_sub_type option[value="'+statSubType+'"]').attr('typ');//단위.
		var statTitle = $('#stat_sub_type option[value="'+statSubType+'"]').text();//통계 제목.
		var statCate = $('#stat_type option[value="'+statType+'"]').text();//통계 대분류.

		if ( statType === '' || statSubType === '' ) {
			alert('통계 종류와 통계 기준은 필수 선택 입니다.');

			return false;
		}



		//통계 전체 타입 설정
		var pag = 'getNdmsStatYear';//연도별
		var ext = '년';//연도별
		var dat = 'year';//연도별

		if ( statYear != '' && statMonth === '' ) {
			pag = 'getNdmsStatMonth';//월별
			ext = '월';
			dat = 'month';
		} else if ( statYear != '' &&  statMonth != '' ) {
			pag = 'getNdmsStatDay';//일별
			ext = '일';
			dat = 'day';
		}

		var param = {
				type : statType
				, ctgory : statSubType
				, emd : statEmd
				, time: statYear+statMonth
				, excel : pag
				, exceltitle : statCate+' '+statTitle+'_'+statYear+statMonth+'_'
		};

		schObj = param;
		
		_common.callAjax("/stat/"+pag+".json", param, function(json){
			
			
			var result = json.result;
//			console.log("resutlss(result) = "+JSON.stringify(result));
			var datList = json.datList;
//			console.log("resutl(datalist) = "+JSON.stringify(datList));
			try{
				$("#" + parentView).find("#wrap").find('#userList colgroup').html(createCol(datList.length+2));
				$("#" + parentView).find("#wrap").find('#userList thead').html(createThead(datList, dat, ext));
				$("#" + parentView).find("#wrap").find('#userList tbody').html(createBody(result, dat, statExt, statTitle));
				$('#stat_table_title').text(statTitle+'( 단위 : '+statExt+' )');
				createNdmsChart(result, datList, dat, ext, statExt, statTitle);
			}catch(e){
				ChartUtils.createBarChart('stat_chart_type', ' ', null, null, null, null);
			}
		
		}, false);
	});

	$('#stat_ndps_search_btn').on('click', function(){

		$("#" + parentView).find("#wrap").find('#userList colgroup').html('');
		$("#" + parentView).find("#wrap").find('#userList tbody').html('');
		$("#" + parentView).find("#wrap").find('#userList thead').html('');
		$("#" + parentView).find("#wrap").find('stat_table_type').html('');

		var statType = $('#stat_type').val();//통계 종류
		var statSubType = $('#stat_sub_type').val();//통계 기준
		var statYear = $('#stat_year').val();//연도
		var statMonth = $('#stat_month').val();//월
		var statEqb = $('#stat_eqb').val();//장비

		var statExt = $('#stat_sub_type option:selected').attr('typ');//단위.
		var statTitle = $('#stat_sub_type option:selected').text();
		var statCate = $('#stat_type option[value="'+statType+'"]').text();//통계 대분류.

		if ( statType === '' || statSubType === '' ) {
			alert('통계 종류와 통계 기준은 필수 선택 입니다.');

			return false;
		}

		//통계 전체 타입 설정
		var pag = 'getNdpsStatYear';//연도별
		var ext = '년';//연도별
		var dat = 'year';//연도별

		if ( statYear != '' && statMonth === '' ) {
			pag = 'getNdpsStatMonth';//월별
			ext = '월';
			dat = 'month';
		} else if ( statYear != '' &&  statMonth != '' ) {
			pag = 'getNdpsStatDay';//일별
			ext = '일';
			dat = 'day';
		}
		var tableName='';
		var valueType='';
		if(statType==='B03105'){
			tableName='awsdata_view';
			if(statTitle==='온도'){
				valueType='TEMP_DATA';
			}else if(statTitle==='습도'){
				valueType='HUMI_DATA';
			}else{
				valueType='WSPD_DATA';
			}
			
		}else{
			tableName='weather_view';
			valueType='MESURE_DATA';
		}
		
		
		var param = {
				tableName:tableName
				,valueType :valueType
				, type : statType
				, ctgory : statSubType
				, nm : statEqb
				, time: statYear+statMonth
				, excel : pag
				, exceltitle : statCate+' '+statTitle+'_'+statYear+statMonth+'_'
		};

		schObj = param;

		_common.callAjax("/stat/"+pag+".json", param, function(json){
			console.log(json);
			if(json.result[0].item==undefined){
				alert('데이터가 없습니다.');
				return;
			}
			for ( var i=0; i < json.result.length; i++ ) {
				json.result[i].cate = Base64.decode(json.result[i].cate);
			}
			var result = json.result;
		    console.log(result);
			var datList = json.datList;
			$("#" + parentView).find("#wrap").find('#userList colgroup').html(createCol(datList.length+2));
			$("#" + parentView).find("#wrap").find('#userList thead').html(createThead(datList, dat, ext));
//			$("#" + parentView).find("#wrap").find('#userList tbody').html(createBody(result, dat));
			$("#" + parentView).find("#wrap").find('#userList tbody').html(createBody(result, dat, statExt, statTitle));
			
			$('#stat_table_title').text(statTitle+'( 단위 : '+statExt+' )');
			createNdmsChart(result, datList, dat, ext, statExt, statTitle);

		}, false);
	});


	function getEqgList(se){
		_common.callAjax("/ndps/getEqbList.json", {se : se}, function(json){
			var list = json.result;
			var str = '';

			str += '<option value="">전체</option>';
			if ( list.length != 0 ) {
				for ( var i = 0; i < list.length; i++ ) {
					var obj = list[i];

					if ( obj.nm != '' ) {

						str += '<option value="'+obj.nm+'" class="">'+obj.nm+'</option>';
					}
					// <option value="01" k="mm" class="non">1월</option>
				}
			}
			$('#stat_eqb').html(str);

		}, false);
	}

	/**
	 * 차트를 생성한다. EVENT
	 * - 읍면동 및 댐과 같은 기준은 통합하고 통계 기준 별로 차트를 생성한다.( 건수, 최대, 최소, 평군 )
	 *
	 * @param size
	 * @returns
	 */
	function createEventChart(list, datList, dat, ext, statExt, statTitle){
		var str = '';
		var datArr = [];
		var dataArr = [];
		//날짜 배열 생성
		for (datIndex in datList ){
			datArr.push(datList[datIndex][dat]+''+ext);
		}

//		console.log('chart dat : ' + datArr);

		var xAxis = {
			categories: datArr
		};

		var chartData = [];
		for ( var k = 0; k < list.length; k++ ){

			var obj = list[k];
			var item = obj.item;
			var cateArr = obj.cate.split(';');

			var dataArr = [];

			for ( var j = 0; j < cateArr.length; j++ ) {
				if ( datArr[j] != undefined ) {
					var dat = datArr[j];
					var data =  Number(cateArr[j]);
					dataArr.push([dat, data]);
				}
			}
			chartData.push({
				name : item,
				data : dataArr
			});
		}
		chartResize();
		ChartUtils.createBarChart('stat_chart_type', ' ', chartData, xAxis, statExt, statExt);

	}


	/**
	 * 차트를 생성한다. NDMS
	 * - 읍면동 및 댐과 같은 기준은 통합하고 통계 기준 별로 차트를 생성한다.( 건수, 최대, 최소, 평군 )
	 *
	 * @param size
	 * @returns
	 */
	function createNdmsChart(list, datList, dat, ext, statExt, statTitle){
		var str = '';
		var datArr = [];
		var dataArr = [];
		//날짜 배열 생성
		for (datIndex in datList ){
			datArr.push(datList[datIndex][dat]+''+ext);
		}

		var xAxis = {
				categories: datArr
		};

		var obj = list[list.length-1];
		var cateArr = obj.cate.split(';');
		
		var colArr = obj.col.split(',');
		
		var colLen = 0;

		if ( obj.item === '차트' ) {

			//col 기준으로 차트 생성 - 여러개
			var chartData = [];
			for ( var i = 0; i < colArr.length; i++ ) {
				var dataArr = [];
				if(statTitle!='적설량' && statTitle!='강우량'){
					if(colArr[i]==='합계'){
						continue;
					}
				}
				for ( var j = 0; j < cateArr.length; j++ ) {
					if ( datArr[j] != undefined ) {
						var dat = datArr[j];
						var data =  Number(cateArr[j].split(',')[i]);
						dataArr.push([dat, statDataConvert(data, statExt, statTitle)]);
					}
				}
			
				chartData.push({
					name : colArr[i],
					data : dataArr
				});

			}
			chartResize();

			//어떤 차트인지..... 그에 따른 typ 값 변경
			if ( colArr.length > 2 ) {
				if ( ext === '년' ) {
					ChartUtils.createBarChart('stat_chart_type', ' ', chartData, xAxis, statExt, statExt);
				} else {
					ChartUtils.createLineChart('stat_chart_type', ' ', chartData, xAxis, statExt, statExt);
				}

			} else {
				ChartUtils.createBarChart('stat_chart_type', ' ', chartData, xAxis, statExt, statExt);
			}
		}
	}


	/**
	 * table의 col을 만든다. ( 일정 길이 만큼 생성하기 위해서...)
	 *
	 * @param size
	 * @returns
	 */
	function createCol(size){
		var str = '';

		for ( var i = 0; i < size; i++ ) {
			str += '<col width="'+100/size+'%">';
		}

		return str;
	}


	/**
	 * table의 thead를 만든다. ( 동적 생성을 위해... )
	 *
	 * @param datList
	 * @returns
	 */
	function createThead(list, dat, ext){
		var str = '<tr><th></th><th>구 분</th>';

		for ( var i = 0; i < list.length; i++ ) {
			str += '<th>'+list[i][dat]+ext+'</th>';
		}
		str += '</tr>';

		return str;
	}

	/**
	 * table의 tbody를 만든다. ( col 갯수에 따라 유동적으로.,,.)
	 *
	 * @param list
	 * @param dat
	 * @returns
	 */
	function createBody(list, dat, statExt, statTitle){
		var str = '';
		for ( var i = 0; i < list.length; i++ ) {
			var obj = list[i];
			var colArr = obj.col.split(',');
			var cateArr = obj.cate.split(';');
			var colLen = 0;


			for ( var j = 0; j < colArr.length; j++ ) {
				var col = colArr[j];
				if ( obj.item != '차트' ) {

					str += '<tr>';

					if ( j === 0 ) {
						str += '	<td rowspan="'+colArr.length+'">'+obj.item+'</td>';
					}
					if ( col != undefined && col != '' ) {
						str += '	<td rowspan="1">'+col+'</td>'

					}

					for ( var k = 0; k < cateArr.length; k++ ) {

						var cate = cateArr[k].split(',')[j];
						if ( cate != undefined && cate != '' ) {
							str += '<td rowspan="1">'+statDataConvert(cate, statExt, statTitle)+'</td>'
						}
					}

					str += '</tr>'
				}
			}
		}

		return str;

	}

	function statDataConvert(data, ext, tit){
		if(data == 9999 || data == -9999){
			return null;
		}
		else{
			if ( ext === '%') {
				data = data /10;
			} else if ( ext === 'm/s' ) {
				data = data /10;
			} else if ( ext === '°C' ) {
				data = data /10;
			} else if ( ext === 'cm'){
				data = data /10;
			}else if(ext ==='m'){
				data = data /10;
			}
			data = Number(data).toFixed(2);
			return Number(data);
		}
	}

	$("#" + parentView).find("#wrap").find("#stat_excel_btn").click(function(){
		confirm("* 검색결과를 엑셀로 다운로드 하시겠습니까?", function(){
			var max = $("#" + parentView).find("#wrap").find('#userList tbody').length;
			if ( max == "0"){
				alert("* 검색결과가 존재하지 않습니다.");
			} else{
				var _param = {};
				for(var key in schObj) {
					if (schObj[key] != ""){
						_param[key] = schObj[key];
					}
				}
				_param['body'] =  encodeURIComponent($('#stat_table_type').html(),"UTF-8");
				
				_common.postForm.submit("/stat/getExcel.do", _param);
			}
		});
	});
});