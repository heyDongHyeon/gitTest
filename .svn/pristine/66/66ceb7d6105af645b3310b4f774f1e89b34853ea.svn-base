// ////////////////////////////////////////////////////
// 검색패널안의 메뉴이벤트...
// ////////////////////////////////////////////////////
var xeusSearch = {

	resize_search : function() {
		$("#" + parentView).find("#srchResult").show();
	},

	init_input : function(){ // //검색 타입 변경시 입력값들 초기화
		 $("#" + parentView).find("#srchResult").html(null);	//검색결과 제거
		 $("#" + parentView).find("#emdCd").prop("selectedIndex", 0).change(); //읍면동 콤보박스 초기화
		 if($("#" + parentView).find("#san").is(":checked")){ //산 체크 해제
			 $("#" + parentView).find("#san").prop('checked', false); //jquery 1.6이상버전
			 //$("#" + parentView).find("#san").attr('checked', false); = jquery 1.6이하버전
		 }
		 $("#" + parentView).find("#rnCd").prop("selectedIndex", 0); //도로명 콤보박스 초기화
		 $("#" + parentView).find("#epsg").prop("selectedIndex", 0); //투영원점 콤보박스 초기화
		 $("#" + parentView).find(".sendData.keyup").val(""); //input box 초기화
		 $("#" + parentView).find(".sendData.keynext").val(""); //input box 초기화
	},

	//주소검색 탭 토글 버튼 등록 함수
	addrToggle : function(){
		xeusLayout.searchAddrToggleButtons = new geomex.xeus.ToggleButtons();
		xeusLayout.searchAddrToggleButtons.add("#jibunTab", function(){
			xeusSearch.init_input();
			$("#" + parentView).find("#jibunUI").show();
			$("#" + parentView).find("#doroUI").hide();
		});
		xeusLayout.searchAddrToggleButtons.add("#doroTab", function(){
			xeusSearch.init_input();
			$("#" + parentView).find("#jibunUI").hide();
			$("#" + parentView).find("#doroUI").show();
		});
		xeusLayout.searchAddrToggleButtons.toggle('#jibunTab');
	},

	//좌표검색 탭 토글 버튼 등록 함수
	locToggle : function(){
		xeusLayout.searchLocToggleButtons = new geomex.xeus.ToggleButtons();
		xeusLayout.searchLocToggleButtons.add("#tmTab", function(){
			xeusSearch.init_input();
			$("#" + parentView).find("#tmUI").show();
			$("#" + parentView).find("#lnglatUI").hide();
			$("#" + parentView).find("#tmX").focus();
		});
		xeusLayout.searchLocToggleButtons.add("#lnglatTab", function(){
			xeusSearch.init_input();
			$("#" + parentView).find("#tmUI").hide();
			$("#" + parentView).find("#lnglatUI").show();
			$("#" + parentView).find("#lng").focus();
		});
		xeusLayout.searchLocToggleButtons.toggle('#tmTab');
	},

	searchMenuEvent : function() {
		// resize 이벤트 등록
		$(document).ready(function() {
			xeusSearch.resize_search();
		});
		$(window).resize(function() {
			xeusSearch.resize_search();
		});

		$(document).on("change", ".searchMode", function(){
			var mode = $(this).val();
			if(mode == 1){
				$("#" + parentView).find("#jibunUI").show();
				$("#" + parentView).find("#doroUI").hide();
				$("#" + parentView).find("#lnglatUI").hide();
				$("#" + parentView).find("#addrUI").hide();
			}else if(mode == 2){
				$("#" + parentView).find("#jibunUI").hide();
				$("#" + parentView).find("#doroUI").show();
				$("#" + parentView).find("#lnglatUI").hide();
				$("#" + parentView).find("#addrUI").hide();
			}else if(mode == 3){
				$("#" + parentView).find("#jibunUI").hide();
				$("#" + parentView).find("#doroUI").hide();
				$("#" + parentView).find("#lnglatUI").show();
				$("#" + parentView).find("#addrUI").hide();
			}else if(mode == 4){
				$("#" + parentView).find("#jibunUI").hide();
				$("#" + parentView).find("#doroUI").hide();
				$("#" + parentView).find("#lnglatUI").hide();
				$("#" + parentView).find("#addrUI").show();
			}
		});

		//버튼이벤트(주소, 통합, 좌표 검색버튼)
		$(document).on("click",	"#west_btn_addr",function() {
			_common.callAjax("/search/getAddrView.do", null, function(html) {
				$("#" + parentView).find("#search-wrap").html(html);
				$("#" + parentView).find("#jibunUI").show();
				$("#" + parentView).find("#doroUI").hide();
				$("#" + parentView).find("#bon").focus();
				xeusSearch.addrToggle();//주소검색 탭 toggle버튼으로 변경
			});
		});

		$(document).on("click", "#west_btn_api", function() {
			_common.callAjax("/search/getApiView.do", null, function(html) {
				$("#" + parentView).find("#search-wrap").html(html);
				$("#" + parentView).find("#searchStr").focus();
			});
		});

		$(document).on("click",	"#west_btn_loc", function() {
			_common.callAjax("/search/getLocationView.do", null, function(html) {
				$("#" + parentView).find("#search-wrap").html(html);
				$("#" + parentView).find("#tmUI").show();
				$("#" + parentView).find("#lnglatUI").hide();
				$("#" + parentView).find("#tmX").focus();
				xeusSearch.locToggle();//좌표검색 탭 toggle버튼으로 변경
			});
		});

		/* 검색 타입 변경(통합검색 라디오버튼)*/
		$(document).on("click", "#srchType1",function(){
			$("#" + parentView).find("#srchResult").html(null); //검색 타입 변경시 검색결과 제거
			$("#" + parentView).find("#searchStr").val("");//input box 초기화
		});

		$(document).on("click", "#srchType2",function(){
			$("#" + parentView).find("#srchResult").html(null); //검색 타입 변경시 검색결과 제거
			$("#" + parentView).find("#searchStr").val("");//input box 초기화
		});

		/* 지번주소검색에서 읍면동 선택하면 리 선택가능하게 변경 */
		$(document).on("change", "#search-parent .emdSearchCd",function() {
			if ($("#" + parentView).find("#search-parent #emdCd option:selected").attr("value") == "-99999") { //기본값 선택(선택하세요.)
				$("#" + parentView).find("#search-parent #liCd").prop("selectedIndex", 0); //기본값으로 변경(선택하세요.)
				$("#" + parentView).find("#search-parent #liCd").attr("disabled", "disabled"); //리 콤보박스 선택 안되도록
			} else {

				var liChk = $("#" + parentView).find('#liCd').length;
				if(liChk > 0){
					$("#" + parentView).find("#search-parent #liCd").removeAttr("disabled"); //리 콤보박스 선택 되도록
					$("#" + parentView).find("#search-parent #liCd").prop("selectedIndex", 0); //기본값으로 변경(선택하세요.)
					var emdCd = $("#" + parentView).find("#search-parent #emdCd option:selected").attr("value"); //읍면동 콤보박스에서 선택한 읍면동 코드

					var li_length = document.getElementById("liCd").length;
					for (var i = 0; i < li_length; i++) {
						if ($("#" + parentView).find("#search-parent #liCd option:eq(" + i + ")").attr("value") == "-99999") {
							$("#" + parentView).find("#search-parent #liCd option:eq(" + i + ")").css("display", "initial"); //리 콤보박스의 -99999(선택하세요)는 보이도록
						} else if (emdCd.substring(5,8) != $("#" + parentView).find("#liCd option:eq(" + i + ")").attr("emd")) {	//리 콤보박스에서 선택된 읍면동과 관련없는 것은 안보이도록
																											//180801 이은규 : emdCd는 8자리, option의 emd는 3자리라서 수정함.
							$("#" + parentView).find("#search-parent #liCd option:eq(" + i + ")").css("display", "none");
						} else {
							$("#" + parentView).find("#search-parent #liCd option:eq(" + i + ")").css("display", "initial"); //읍면동과 관련있는것은 보이도록
						}
					}
				}
			}
		});

		/* 주소 검색 */
		$(document).on("click", ".addrSearch", function() {
			var str = $("#" + parentView).find("#addrUI").find("#addr").val();
			if (_common.utils.isNullAndEmpty(str)) {
				alert("주소를 입력해 주세요.");
			} else {
				var xy = Spatial.convertAddrToXY(str);
				if(xy === undefined || xy == "error" || xy === ''){
					alert("입력하신 주소가 존재하지 않습니다.");
				}else{
					var juso = Spatial.convertXYToAddr(xy[0], xy[1]);
					var t_epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
					var center = Spatial.convertProjection(xy, "EPSG:4326", t_epsg);
					_search.moveLocation(center, juso);
				}
			}
		});

		/* 지번검색 */
		$(document).on("click", ".jibunSearch", function() {

			if ( $(this).get(0) != $('#'+parentView + ' .jibunSearch').get(0)) {
				return false;
			}
			var _param = _common.utils.collectSendData('#'+parentView+" #jibunUI");

			// 1. 읍면동 선택 확인
			//	* 체크 안함. 어차피 bjdCd에서 걸림
			// 2. 리 선택 확인(리 미포함 지역이면 패스)
			//	* 체크 안함. 면만 선택하고 리 선택없이 검색할 때도 있음.
			// 3. 산 체크 확인
			// 4. 본, 부 가공(4자리로 변경)
			// 5. 본 입력 확인
			// 6. 본, 부 유효성 검사
			// 7. 검색

			////////////////////////////////////////////////////////////////

			// 1.
			/*if(_param.emdCd == '-99999'){
				alert("읍면동을 선택해 주세요.");
				return false;
			}*/

			// 2.
			/*var liChk = $('#liCd').length;

			if(liChk > 0){

				var cnt = 0;
				$('#liCd').find('option').each(function(){
					if($(this).css('display') != 'none') cnt++;
				});
				if(cnt > 1){
					alert("리를 선택해 주세요.");
					return false;
				}
			}
			*/

			// 3.
			_param.san = "1"
			if ($("#" + parentView).find("#jibunUI").find("#san").is(":checked")){ //산이 체크되어있으면 _param.san을 2로 변경
				_param.san = "2";
			}

			// 4.
			//본번을 4자리로 만들어줌
			if (_param.bon.length == 1) {
				_param.bon = "000" + _param.bon;
			} else if (_param.bon.length == 2) {
				_param.bon = "00" + _param.bon;
			} else if (_param.bon.length == 3) {
				_param.bon = "0" + _param.bon;
			}
			//부번을 4자리로 만들어줌
			if (_param.bu != null && _param.bu != "") {
				if (_param.bu.length == 1) {
					_param.bu = "000" + _param.bu;
				} else if (_param.bu.length == 2) {
					_param.bu = "00" + _param.bu;
				} else if (_param.bu.length == 3) {
					_param.bu = "0" + _param.bu;
				}
			} else {
				_param.bu = "0000";
			}

			_param.bjdCd = $("#" + parentView).find("#jibunUI").find("#liCd option:selected").attr("bjdCd");	//선택된 리에서 코드 가져옴
																						//리가 없거나 선택되지 않으면 undefined가 됨.
			if(_param.bjdCd === undefined && _param.emdCd != '-99999') {
				_param.bjdCd = _param.emdCd + '00';
				_param.liChk = 'N';
			} else {
				_param.liChk = 'Y';
			}


			_param.pnu = _param.bjdCd + _param.san + _param.bon + _param.bu;

			if (_common.utils.isNullAndEmpty(_param.bjdCd)) {
				alert("읍면동,리를 선택해 주세요.");
				return false;
			}

			// 5.
			if (_common.utils.isNullAndEmpty(_param.bon)) {
				alert("본번을 입력해 주세요.");
				$("#" + parentView).find("#jibunUI").find("#bon").focus();
				return false;
			}

			// 6.
			if (isNaN(Number(_param.bon)) || isNaN(Number(_param.bu))) {
				alert("본번과 부번은 숫자만 가능합니다.");
				return false;
			}

			if (_param.bon.length > 4 || _param.bu.length > 4) {
				alert("본번과 부번은 4자리까지 입력할 수 있습니다.");
				return false;
			}

			// 7.
			_search.getAddrSearchList(_param);
		});

		/* 새주소검색 */
		$(document).on("click", ".doroSearch", function() {

			if ( $(this).get(0) != $('#'+parentView + ' .doroSearch').get(0)) {
				return false;
			}

			var _param = _common.utils.collectSendData('#'+parentView+" #doroUI");

			if (_common.utils.isNullAndEmpty(_param.bon)) {
				alert("본번을 입력해 주세요.");
				$("#" + parentView).find("#doroUI").find("#bon").focus();
				return false;
			} else if (isNaN(Number(_param.bon)) || isNaN(Number(_param.bu))) {
				alert("본번과 부번은 숫자만 가능합니다.");
				return false;
			} else {
				_search.getNewAddrSearchList(_param);
			}
		});

		/* 통합 검색 */
		$(document).on("click", ".apiSearch", function() {

			if ( $(this).get(0) != $('#'+parentView + ' .apiSearch').get(0)) {
				return false;
			}

			var type = "local";
			if ($("#" + parentView).find("#srchType2").is(":checked")){ //통합검색중 주소검색이면 type을 addr로 변경
				type = "addr";
			}
			var str = $("#" + parentView).find("#searchStr").val();

			if (_common.utils.isNullAndEmpty(str)) {
				alert("검색어를 입력해 주세요.");
				$("#" + parentView).find("#searchStr").focus();
			} else {
				_search.getApiSearchList(type, str);
			}
		});

		/* TM <-> LngLat검색 */
		$(document).on("click", ".tmSearch", function() {

			if ( $(this).get(0) != $('#'+parentView + ' .tmSearch').get(0)) {
				return false;
			}

			var _param = _common.utils.collectSendData('#'+parentView+" #tmUI");
			if (!_common.utils.validNaN(_param.tmX)) {
				alert("X 값을 정확히 입력해 주세요.");
				$("#" + parentView).find("#tmUI").find("#tmX").focus();
				return false;
			}
			if (!_common.utils.validNaN(_param.tmY)) {
				alert("Y 값을 정확히 입력해 주세요.");
				$("#" + parentView).find("#tmUI").find("#tmY").focus();
				return false;
			}
			_search.getTmToLngLat(_param);
		});

		/* LngLat <-> TM 검색 */
		$(document).on("click", ".lnglatSearch", function() {

			if ( $(this).get(0) != $('#'+parentView + ' .lnglatSearch').get(0)) {
				return false;
			}

			var _param = _common.utils.collectSendData('#'+parentView+" #lnglatUI");
			if (!_common.utils.validNaN(_param.lng)) {
				alert("경도 값을 정확히 입력해 주세요.");
				$("#" + parentView).find("#lnglatUI").find("#lng").focus();
				return false;
			}
			if (!_common.utils.validNaN(_param.lat)) {
				alert("위도 값을 정확히 입력해 주세요.");
				$("#" + parentView).find("#lnglatUI").find("#lat").focus();
				return false;
			}
			_search.getLngLatToTm(_param);
		});

		/* 위치버튼 */
		$(document).on("click", ".moveLocation", function() { //위치버튼 클릭
			var _param = {
				key : $(this).parent().attr("key"),
				val : $(this).parent().attr("val"),
				text : $(this).parent().attr("text")
			};

			_search.getLocation(_param);
		});

		/* 엔터키 이벤트 */
		//검색시 엔터 치면 검색버튼 눌림
		$(document).on("keyup", ".keyup", function(e) {
			if (e.which == 13) {
				var selector = $(this).attr("for");
				$(selector).click();
			}
		});
		/* 엔터키 이벤트 */
		//좌표검색일때 x값 또는 경도 입력후 엔터 치면 y값 또는 위도로 포커스
		$(document).on("keyup", ".keynext", function(e) {
			if (e.which == 13) {
				$("#" + parentView).find(".keyup").focus();
			}
		});
	}
};