/**
 *
 */
var xeusJsonParser = {

	/**
	 * Socket > GIS Json Body 입니다.
	 */
	json : null,

	getTemplate : function(type){
		var _template = {
			// 이벤트 타입 코드 (112, 119, DSC 등)
			"statEvetTypCd" : "",

			// 이벤트 메세지 타입 (전문구분코드 - 99로그인, 10사건정보, 20출동정보 등)
			"statMsgTypCd" : "",

			// 위치정보 명 – 예) 인천지하철 부평역
			"outbPosNm" : "",

			// 이벤트 명 – 예) 화재, 정전, 침수
			"statEvetNm" : "",

			// 이벤트 해제일시 입력 ex) "20171122131540889"
			"statEvetClrDtm" : "",

			// 이벤트 접수 내용 – 화재 발생, 정전 발생, ㅇㅇ사거리 교통사고
			"statEvetCntn" : "",

			// 이벤트 범주 - 예) 사회재난, 자연재난
			"statEvetType" : "",

			// 좌표값 (배열이지만 단건만 등록.)
			"outbPos" : [{ "x" : "", "y" : "" }],

			// 이벤트 발생일시
			"statEvetOutbDtm" : "",

			// 조치내용 또는 종료사유
			"statEvetActnCntn" : "",

			// 이벤트 프로세스 코드 (10 발생 | 40 정보변경 | 50 해제 | 90 취소 | 91 종료)
			"procSt" : "",

			// 모의 여부 (Y 모의 | N 실제)
			"isTest" : "",

			// 이벤트 고유 ID
			"uSvcOutbId" : "",

			// 조치자
			"statEvetActnMn" : "",

			// 이벤트 조치일자 (조치 또는 종료시만 년월일시분초)
			"statEvetActnDtm" : "",

			// 서비스명칭 - 예) 112긴급출동지원서비스
			"statEvetSvcTyp" : "",

			// 주제별 상세 내용(원문)
			"etcCntn" : {}
		}

		if(type == "119"){
			_template.etcCntn = {
				"EVENT_TYP_CD" : "119",
				"MSG_TYP_CD" : "10",
				"STA_TYP_CD" : "10",
				"MSG_STA_DTM" : "20180503150413",
				"SND_SYS_CD" : "119",
				"RCV_SYS_CD" : "UCP",
				"SND_DTM" : "20180503151013",
				"SEND_NUM" : "T20180504171843",
				"EVT_NM" : "구급",
				"EVT_DTL" : "교통사고",
				"LON" : "126.89833328898494",
				"LAT" : "37.483267840449614",
				"ADDR" : "서울 구로구 구로동 823",
				"BJD_CD" : "1154510103",
				"EVT_DTM" : "20180325202844",
				"SENDER_ID" : "geomex"
			}
		}else if(type == "112"){
			_template.etcCntn = {
				"EVENT_TYP_CD" : "112",
				"MSG_TYP_CD" : "10",
				"STA_TYP_CD" : "10",
				"MSG_STA_DTM" : "20180504171841",
				"SND_SYS_CD" : "112",
				"RCV_SYS_CD" : "UCP",
				"SND_DTM" : "20180503151013",
				"SEND_NUM" : "T20180504171841",
				"EVT_GRD" : "112",
				"EVT_TYP" : "교통사고",
				"LON" : "126.89527706188873",
				"LAT" : "37.48453900965434",
				"ADDR" : "서울 구로구 구로동 198-4",
				"BJD_CD" : "1154510103",
				"EVT_DTM" : "20180504171841",
				"SENDER_ID" : "geomex"
			}
		}else if(type == "DSC"){
			_template.etcCntn = {
				"EVENT_TYP_CD" : "DSC",
				"MSG_TYP_CD" : "10",
				"STA_TYP_CD" : "10",
				"MSG_STA_DTM" : "20180503150413",
				"SND_SYS_CD" : "WP1",
				"RCV_SYS_CD" : "UCP",
				"SND_DTM" : "20180417151013",
				"SEND_NUM" : "T20180504171845",
				"SVC_TYP" : "프로기",
				"EVT_LON" : "126.894728",
				"EVT_LAT" : "37.465077",
				"EVT_ADDR" : "서울특별시 금천구 독산동 1006-77",
				"EVT_BJD" : "1154510103",
				"REF_ID" : "red_id",
				"DSC_NM" : "둘리",
				"DSC_PHONE" : "010-1112-3131",
				"DSC_BIRTH" : "900204",
				"DSC_SEX" : "남",
				"DSC_ADDR" : "강원도 춘천시 서면 박사로 882",
				"GUARD_NM" : "홍길동",
				"GUARD_PHONE" : "010-7754-2151",
				"EVT_DTM" : "20180424130431",
				"IMAGE" : "20180424130431.png;aaaaaa.png",
				"INFO" : "info.....",
				"NOTE" : "한글 연습....",
				"SENDER_ID" : "geomex"
			}
		}

		return _template;
	},


	/**
	 * 112, 119, DCS JSON 내용을 통합 JSON 내용으로 변경합니다.
	 * 코드 변경을 최소화 하기위하여 제작되었으며, 추후 변경될 수 있습니다.
	 *
	 * @param json
	 * @returns {___anonymous165_997}
	 * @deprecated
	 */
	convertJson : function(json){
		var _template = {
			// 이벤트 타입 코드 (112, 119, DSC 등)
			"statEvetTypCd" : "",
			// 이벤트 메세지 타입 (전문구분코드 - 99로그인, 10사건정보, 20출동정보 등)
			"statMsgTypCd" : "",
			// 위치정보 명 – 예) 인천지하철 부평역
			"outbPosNm" : "",
			// 이벤트 명 – 예) 화재, 정전, 침수
			"statEvetNm" : "",
			// 이벤트 해제일시 입력 ex) "20171122131540889"
			"statEvetClrDtm" : "",
			// 이벤트 접수 내용 – 화재 발생, 정전 발생, ㅇㅇ사거리 교통사고
			"statEvetCntn" : "",
			// 이벤트 범주 - 예) 사회재난, 자연재난
			"statEvetType" : "",
			// 좌표값 (배열이지만 단건만 등록.)
			"outbPos" : [{ "x" : "", "y" : "" }],
			// 이벤트 발생일시
			"statEvetOutbDtm" : "",
			// 조치내용 또는 종료사유
			"statEvetActnCntn" : "",
			// 이벤트 프로세스 코드 (10 발생 | 40 정보변경 | 50 해제 | 90 취소 | 91 종료)
			"procSt" : "",
			// 모의 여부 (Y 모의 | N 실제)
			"isTest" : "",
			// 이벤트 고유 ID
			"uSvcOutbId" : "",
			// 조치자
			"statEvetActnMn" : "",
			// 이벤트 조치일자 (조치 또는 종료시만 년월일시분초)
			"statEvetActnDtm" : "",
			// 서비스명칭 - 예) 112긴급출동지원서비스
			"statEvetSvcTyp" : "",
			// 주제별 상세 내용(원문)
			"etcCntn" : {}
		}
		//112 {"EVENT_TYP_CD":"112","MSG_TYP_CD":"10","STA_TYP_CD":"10","MSG_STA_DTM":"20180417150413","SND_SYS_CD":"119","RCV_SYS_CD":"UCP","SND_DTM":"20180417151013","SEND_NUM":"T20180325202844","EVT_GRD":"112","EVT_TYP":"교통사고","LON":"126.894728","LAT":"37.465077","ADDR":"서울특별시 금천구 독산동 1006-77","BJD_CD":"1154510103","EVT_DTM":"20180325202844","SENDER_ID":"아이디119"}
		//119 {"EVENT_TYP_CD":"119","MSG_TYP_CD":"10","STA_TYP_CD":"10","MSG_STA_DTM":"20180417150413","SND_SYS_CD":"119","RCV_SYS_CD":"UCP","SND_DTM":"20180417151013","SEND_NUM":"T20180325202844","EVT_NM":"구급","EVT_DTL":"교통사고","LON":"126.894728","LAT":"37.465077","ADDR":"서울특별시 금천구 독산동 1006-77","BJD_CD":"1154510103","EVT_DTM":"20180325202844","SENDER_ID":"아이디119"}
		//DCS {"EVENT_TYP_CD":"DSC","MSG_TYP_CD":"10","STA_TYP_CD":"10","MSG_STA_DTM":"20180417150413","SND_SYS_CD":"WP1","RCV_SYS_CD":"UCP","SND_DTM":"20180417151013","SEND_NUM":"T20180325202844","SVC_TYP":"프로기","EVT_LON":"126.894728","EVT_LAT":"37.465077","EVT_ADDR":"서울특별시 금천구 독산동 1006-77","EVT_BJD":"1154510103","REF_ID":"red_id","DSC_NM":"dsc_nm","DSC_PHONE":"dsc_phone","DSC_BIRTH":"birth","DSC_SEX":"dsc-sex","DSC_ADDR":"-addr","GUARD_NM":"guard-nm","GUARD_PHONE":"phone...","EVT_DTM":"evt_dtm...","IMAGE":"","INFO":"info.....","NOTE":"한글 연습....","SENDER_ID":"userdsc"}

/*		if(json.statEvetTypCd == "NDPSWARN"){
			_template.statEvetNm = "기상 예경보";//json.SVC_TYP;
			_template.statEvetType = "자연재난";
		}*/


		return _template;
	},

	/**
	 * 사회적약자의 상세정보를 조합합니다.
	 *
	 * @param json
	 * @returns {String}
	 * @deprecated
	 */
	parseDSC : function(json){
		var result = "";

		result += "대상자 : " + json.DSC_NM + " (" + json.DSC_SEX + " / " + json.DSC_BIRTH + " / " + json.DSC_PHONE + " / " + json.DSC_ADDR + ")<br>";
		result += "보호자 : " + json.GUARD_NM + " (" + json.GUARD_PHONE + ")";

		return result;
	},

	/**
	 * Json Body 를 상단 Json 객체에 Binding 합니다.
	 *
	 * @param json
	 * @returns {___anonymous_xeusJsonParser}
	 */
	setJson : function(json){
		//this.json = this.convertJson(json);
		this.json = json;

		/* 위치 설정 */
		var outbPos = this.json.outbPos[0];
		try {
			if(Number(outbPos.x) == 0 && Number(outbPos.y) == 0){
				if(this.json.statEvetTypCd == "NDMS119"){
					//위치정보가 없을 때, 세종시 중심 좌표로
//					this.json.outbPos[0].x = 127.257383612993;
//					this.json.outbPos[0].y = 36.5685035626025;
				}else{
					var xy = Spatial.convertAddrToXY(this.json.outbPosNm);
					this.json.outbPos[0].x = xy[0];
					this.json.outbPos[0].y = xy[1];
				}
			}
		} catch (e) {
			var date = new Date();
			try {
				console.log(date.formatYMDHMS(date.getYMDHMS()) + " Json > outbPos(" + outbPos.toString() + ") Error.");
			} catch (e) {
				console.log(date.formatYMDHMS(date.getYMDHMS()) + " Json > outbPos Error.");
				console.log(this.json);
			}
		}

		/* 내부코드(심볼 상태 등) 설정 */
		var statEvetTypCd = this.json.statEvetTypCd;
		if(statEvetTypCd == "CCTVLOCK" || statEvetTypCd == "CCTVSHER" || statEvetTypCd == "NDPSWARN"){
			this.json["isNotExternal"] = true;
		}else{
			this.json["isNotExternal"] = false;
		}

		/* 재난유형 설정 */
		/*if(this.json.statEvetType == null){													//215 <STD033> E01
			_common.callAjax("/widget/getUnitList.json", { "unitSvcId" : this.json.statEvetId.substring(3, 9) }, function(json) {
				if(json.result.length > 0){
					xeusJsonParser.json.StatEvet["statEvetType"] = json.result[0].unitSvcDesc;
				}else{
					xeusJsonParser.json.StatEvet["statEvetType"] = "미분류";
				}
			}, false);
		}*/

		return this;
	},

	/**
	 * Json 을 리턴합니다.
	 *
	 * @returns {___anonymous111_1152}
	 */
	getJson : function(){ return this.json; },

	/**
	 * 이벤트를 시작합니다.
	 *
	 * @returns {___anonymous_xeusJsonParser}
	 */
	Start : function(_Map){
		if(this.isStart() && !evetPin){
			if(this.json.isNotExternal){
				var outbPos = this.json.outbPos[0];
				var center = Spatial.convertProjection([Number(outbPos.x), Number(outbPos.y)], "EPSG:4326", "EPSG:5186");
				if(this.json.statEvetTypCd == "CCTVLOCK"){
					var etcCntn = "";
					if(this.json.etcCntn != "" && this.json.etcCntn != null){
						if(typeof this.json.etcCntn == "string") etcCntn = JSON.parse(this.json.etcCntn);
						if(typeof this.json.etcCntn == "object") etcCntn = this.json.etcCntn;
					}
					xeusSymbol.addLock(center, etcCntn.gid);
				}
				if(this.json.statEvetTypCd == "CCTVSHER"){
					var etcCntn = "";
					if(this.json.etcCntn != "" && this.json.etcCntn != null){
						if(typeof this.json.etcCntn == "string") etcCntn = JSON.parse(this.json.etcCntn);
						if(typeof this.json.etcCntn == "object") etcCntn = this.json.etcCntn;
					}
					confirm("영상 공유 이벤트를 수신하였습니다.<br>CCTV를 재생하시겠습니까?", function(){
						var _point = etcCntn['point'];
						var _cnt = xeusCCTV.cctv.getVideoDialogCount();
						var _isPreview = true;

						xeusCCTV.cctv.createVideoDialog(etcCntn, _cnt, _isPreview);
						xeusCCTV.cctv.reload();
						xeusSymbol.addPlay(etcCntn["point"], etcCntn["gid"]);
					});
				}
				if(this.json.statEvetTypCd == "CCTVPREV"){
					var etcCntn = "";
					if(this.json.etcCntn != "" && this.json.etcCntn != null){
						if(typeof this.json.etcCntn == "string") etcCntn = JSON.parse(this.json.etcCntn);
						if(typeof this.json.etcCntn == "object") etcCntn = this.json.etcCntn;
					}
					var _cctv = etcCntn.cctv;
					var _Json = JSON.parse(JSON.stringify(this.json));
						_Json["targetId"] = etcCntn.userId;
						_Json.statEvetOutbDtm = new Date().getYMDHMS();
						_Json.statEvetClrDtm = new Date().getYMDHMS();
						_Json.statEvetActnMn = userId;
						_Json.procSt = "91";

					// TODO CCTV정보 함께 서버에 전송하여 재생처리.
					confirm(etcCntn.userId + " 사용자가 CCTV(명칭 : " + etcCntn.cctvNm + ") 선영상재생을 요청하였습니다.<br>승인하시겠습니까?", function(){
						_Json.statEvetActnCntn = "사용자(" + userId + ")가 CCTV(명칭 : " + etcCntn.cctvNm + ") 선영상재생을 승인하였습니다.";
						_Json.etcCntn = JSON.stringify({ "response" : "true", "cctv" : _cctv });

						_common.callAjax("/ws/addEvent.json", { "json" : JSON.stringify(_Json) }, function(_json){
							if(_json.result){

							}
						});
					}, function(){
						_Json.statEvetActnCntn = "사용자(" + userId + ")가 CCTV(명칭 : " + etcCntn.cctvNm + ") 선영상재생을 거부하였습니다.";
						_Json.etcCntn = JSON.stringify({ "response" : "false", "cctv" : _cctv });

						_common.callAjax("/ws/addEvent.json", { "json" : JSON.stringify(_Json) }, function(_json){
							if(_json.result){

							}
						});
					});
				}

				if(this.json.statEvetTypCd == "NDPSWARN"){
				}
			}else{
				this.move(null, _Map);
			}
		}



		WIDGET.getEventListWidget();

		_common.callAjax("/eventList/getEventChk.json", {key : xeusJsonParser.getUSvcOutbId()}, function(_json){
			if ( _json.result != null ) {

				$("#alram").trigger("play");
				_common.callAjax("/alarm/start.json", {}, function(_json){
				});
			}
		}, false);

		WIDGET.showEventPopup();
		return this;
	},

	/**
	 * 이벤트를 종료합니다.
	 * 현재 보고있는 이벤트 상세정보와 발생한 이벤트의 ID가 동일할경우,
	 * 벡터 클리어 및 CCTV 종료를 요청합니다.
	 *
	 * @returns {___anonymous_xeusJsonParser}
	 */
	Stop : function(){
		WIDGET.getEventListWidget();
		WIDGET.showEventPopup();

		var uSvcOutbId = $("#ctntTable").attr("uSvcOutbId");
		if(xeusJsonParser.getJson().uSvcOutbId == uSvcOutbId){
			//_common.callAjax("/Socket.json", { "typ" : "close" }, function(){});
			if(eventVectorSource) eventVectorSource.clear();
			Spatial.stopInterval();
			/*$("#btn-map-cler").click();
			$("#btn-map-home").click();*/
			$("#ctntTable").find(".ctntTd").html("");
		}

		var outbPos = this.json.outbPos[0];
		var center = Spatial.convertProjection([Number(outbPos.x), Number(outbPos.y)], "EPSG:4326", "EPSG:5186");

		if(this.json.statEvetTypCd == "CCTVLOCK"){
			var etcCntn = "";
			if(this.json.etcCntn != "" && this.json.etcCntn != null){
				if(typeof this.json.etcCntn == "string") etcCntn = JSON.parse(this.json.etcCntn);
				if(typeof this.json.etcCntn == "object") etcCntn = this.json.etcCntn;
			}
			xeusSymbol.removeFeature(etcCntn.gid, "isLock");
		}
		if(this.json.statEvetTypCd == "CCTVSHER"){
			var etcCntn = "";
			if(this.json.etcCntn != "" && this.json.etcCntn != null){
				if(typeof this.json.etcCntn == "string") etcCntn = JSON.parse(this.json.etcCntn);
				if(typeof this.json.etcCntn == "object") etcCntn = this.json.etcCntn;
			}
			$("button.close_" + etcCntn.mgrNo).click();
		}
		if(this.json.statEvetTypCd == "CCTVPREV"){
			var etcCntn = "";
			if(this.json.etcCntn != "" && this.json.etcCntn != null){
				if(typeof this.json.etcCntn == "string") etcCntn = JSON.parse(this.json.etcCntn);
				if(typeof this.json.etcCntn == "object") etcCntn = this.json.etcCntn;
			}
			if(Boolean(etcCntn.response)){
				alert("요청하신 CCTV 선영상재생이 수락되었습니다.<br>CCTV를 재생합니다.");

				var _cctv = etcCntn.cctv;
				var _point = _cctv['point'];
				var _cnt = xeusCCTV.cctv.getVideoDialogCount();
				var _isPreview = true;

				xeusCCTV.cctv.createVideoDialog(_cctv, _cnt, _isPreview);
				xeusCCTV.cctv.reload();
				xeusSymbol.addPlay(_cctv["point"], _cctv["gid"]);
			}else{
				alert("요청하신 CCTV 선영상재생이 거부되었습니다.");
			}
		}

		return this;
	},

	/**
	 * 모의 테스트 여부를 리턴합니다.
	 *
	 * @returns {Boolean}
	 */
	isTest : function(){
		if(Boolean(this.json.isTest)){
			return true;
		}else{
			return false;
		}
	},

	/**
	 * 이벤트 여부를 리턴합니다.
	 *
	 * @returns {Boolean}
	 */
	isStart : function(){
		if(this.json.procSt == "10"){
			return true;
		}else{
			return false;
		}
	},

	/**
	 * 이벤트 정보변경여부를 리턴합니다.
	 *
	 * @returns {Boolean}
	 */
	isChange : function(){
		if(this.json.procSt == "40"){
			return true;
		}else{
			return false;
		}
	},

	/**
	 * 이벤트 종료 여부를 리턴합니다.
	 *
	 * @returns {Boolean}
	 */
	isEnd : function(){
		if(this.json.procSt == "50" || this.json.procSt == "90" || this.json.procSt == "91"){
			return true;
		}else{
			return false;
		}
	},

	/**
	 * 타겟 그룹을 리턴합니다.
	 *
	 * @returns
	 */
	getTargetGrp : function(){
		var result = this.json.targetGrp;
		if(result == null) result = "";
		if(result == "null") result = "";

		return result;
	},

	/**
	 * 이벤트 타입을 리턴합니다. (112, 119, DSC, CCTVLOCK, CCTVSHRE)
	 *
	 * @returns
	 */
	getStatEvetTypCd : function(){ return this.json.statEvetTypCd; },

	/**
	 * 이벤트 단계를 한글로 리턴합니다.
	 *
	 * @returns {Boolean}
	 */
	getProcSt : function(){
		if(this.json.procSt == "10"){
			return "발생";
		}else if(this.json.procSt == "40"){
			return "변경";
		}else if(this.json.procSt == "50"){
			return "해제";
		}else if(this.json.procSt == "90"){
			return "취소";
		}else if(this.json.procSt == "91"){
			return "종료";
		}
	},

	/**
	 * 이벤트 단계를 색깔로 리턴합니다.
	 *
	 * @returns {Boolean}
	 */
	getProcStColor : function(){
		if(this.json.procSt == "10"){
			return "red";
		}else if(this.json.procSt == "40"){
			return "gray";
		}else if(this.json.procSt == "50"){
			return "green";
		}else if(this.json.procSt == "90"){
			return "green";
		}else if(this.json.procSt == "91"){
			return "green";
		}
	},

	/**
	 * 이벤트 상태를 리턴합니다.
	 *
	 * @returns {Boolean}
	 */
	getProcStCd : function(){ return this.json.procSt },

	/**
	 * 이벤트 발생일시를 리턴합니다.
	 *
	 * @returns {String}
	 */
	getYmd : function(){ return this.json.statEvetOutbDtm; },

	/**
	 * 이벤트 종료일시를 리턴합니다.
	 *
	 * @returns {String}
	 */
	getEndYmd : function(){ return this.json.statEvetActnDtm; },

	/**
	 * 이벤트 주소를 리턴합니다.
	 *
	 * @returns {String}
	 */
	getAddr : function(){ return this.json.outbPosNm; },

	/**
	 * 이벤트 경위도를 리턴합니다.
	 *
	 * @returns {String}
	 */
	getXY : function(){ return this.json.outbPos[0]; },

	/**
	 * 이벤트 타입을 리턴합니다.
	 *
	 * @returns {String}
	 */
	getEventType : function(){ return this.json.statEvetNm; },

	/**
	 * 이벤트 유형을 리턴합니다.
	 *
	 * @returns {String}
	 */
	getEventParentType : function(){ return this.json.statEvetType; },

	/**
	 * 이벤트 내용을 리턴합니다.
	 *
	 * @returns {String}
	 */
	getEventMsg : function(){ return this.json.statEvetCntn; },

	/**
	 * 이벤트 조치내용을 리턴합니다.
	 *
	 * @returns {String}
	 */
	getEventActnMsg : function(){ return this.json.statEvetActnCntn; },

	/**
	 * 트레이스ID를 리턴합니다.
	 *
	 * @returns {String}
	 */
	getUSvcOutbId : function(){ return this.json.uSvcOutbId; },

	/**
	 * 이벤트 발생위치를 리턴합니다.
	 * 데이터 구조는 배열이나, 1개만 등록됩니다.
	 * 또한 경위도를 TM으로 변경하여 리턴합니다.
	 *
	 * @returns {Array}
	 */
	getEventLocation : function(json){
		var locArray = this.json.outbPos;
		if(json) locArray = json.outbPos;
		var xy = new Array();
		for(var i=0; i<locArray.length; i++){
			var lonlat = [Number(locArray[i].x), Number(locArray[i].y)];
			var tm = Spatial.convertProjection(lonlat, "EPSG:4326", xeusLayout.mapService.getMap().getView().getProjection().getCode());
			xy.push(tm);
		}
		return xy;
	},

	/**
	 * 이벤트 컨텐츠를 변경합니다.
	 *
	 * @returns {Array}
	 */
	setEventContent : function(json, typ){
		var JSON = this.json;
		if(json) JSON = json;
		var $tbl = $('#ctntTable');
		var statEvetTypeNm = JSON.statEvetTypCd + " / ";
		if(JSON.statEvetTypCd == "DSC") statEvetTypeNm = "";

		var targetStatEvetNm = $("#targetStatEvetNm").is(":checked");
		var targetStatEvetOutbDtm = $("#targetStatEvetOutbDtm").is(":checked");
		var targetOutbPos = $("#targetOutbPos").is(":checked");

		if($("#targetStatEvetNm").length > 0){
			if(!targetStatEvetNm){
				$("#statEvetNm").hide();
				$("#statEvetNm").prev().hide();
			}else{
				$("#statEvetNm").show();
				$("#statEvetNm").prev().show();
			}
		}

		if($("#targetStatEvetOutbDtm").length > 0){
			if(!targetStatEvetOutbDtm){
				$("#statEvetOutbDtm").hide();
				$("#statEvetOutbDtm").prev().hide();
			}else{
				$("#statEvetOutbDtm").show();
				$("#statEvetOutbDtm").prev().show();
			}
		}

		if($("#targetStatEvetActnDtm").length > 0){
			if(!targetStatEvetOutbDtm){
				$("#statEvetActnDtm").hide();
				$("#statEvetActnDtm").prev().hide();
			}else{
				$("#statEvetActnDtm").show();
				$("#statEvetActnDtm").prev().show();
			}
		}

		if($("#targetOutbPos").length > 0){
			if(!targetOutbPos){
				$("#outbPosX, #outbPosY, #outbPosNm").hide();
				$("#outbPosX, #outbPosY, #outbPosNm").prev().hide();
			}else{
				$("#outbPosX, #outbPosY, #outbPosNm").show();
				$("#outbPosX, #outbPosY, #outbPosNm").prev().show();
			}
		}

		if ( typ === 'wait' ) {
			$tbl = $('#ctntWaitTable');
		} else if ( typ === 'hist' ) {
			$tbl = $('#ctntHistTable');

		}

		$tbl.data(JSON);
		$tbl.attr("uSvcOutbId", JSON.uSvcOutbId);
		$tbl.find("#statEvetNm").text(statEvetTypeNm + JSON.statEvetNm);
		$tbl.find("#statEvetOutbDtm").text(new Date().formatYMDHMS(JSON.statEvetOutbDtm.substring(0, 14)));
		$tbl.find("#statEvetActnDtm").text(new Date().formatYMDHMS(JSON.statEvetActnDtm.substring(0, 14)));
		$tbl.find("#outbPosX").text(JSON.outbPos[0].x);
		$tbl.find("#outbPosY").text(JSON.outbPos[0].y);
		$tbl.find("#outbPosNm").text(JSON.outbPosNm);

		$tbl.find("#statEvetCntn").html(JSON.statEvetCntn.replace(/;;/gi, '\n'));
		$tbl.find("#statActnCntn").html(JSON.statEvetActnCntn.replace(/;;/gi, '\n'));
	},

	/**
	 * 이벤트 발생위치로 이동합니다.
	 */
	move : function(json, _Map, dsc){

		var JSON = this.json;
		var center = this.getEventLocation()[0];
		if(json){
			JSON = json;
			center = this.getEventLocation(json)[0];
		}
		if(center[0] > 0 && center[1] > 0){
			this.setEventContent(JSON);

			var targetMap = xeusLayout.mapService.getMap();
			if(_Map) targetMap = _Map;

			var lnglat = Spatial.convertProjection(center, targetMap.getView().getProjection().getCode(), "EPSG:4326");
			var addr = Spatial.convertXYToAddr(lnglat[0], lnglat[1]);
			if(addr == "error") addr = "";
			if(dsc != undefined ) addr = dsc;
			var point = new ol.Feature(new ol.geom.Point(center));
			point.setStyle(new ol.style.Style({
					text : new ol.style.Text({
					text: addr,
					textAlign: "center",
					textBaseline: "hanging",
					offsetY: 30,
					font: "bold 15px arial",
					fill: new ol.style.Fill({
						color: "blue"
					}),
					stroke: new ol.style.Stroke({
						color: "#FFFFFF",
						width: 3
					})
				})
			}));
			eventVectorSource.clear();
			eventVectorSource.addFeature(point);
			Spatial.stopInterval();
			var color = '30';

			//이벤트 심볼 설정
			if(JSON.statEvetTypCd == "NDMSWARN") {
				console.log(JSON.statEvetNm);
				if(JSON.statEvetNm.contains("산불발생정보")) color = "11";
				if(JSON.statEvetNm.contains("우량시단위")) color = "12";
				if(JSON.statEvetNm.contains("고속도로특별상황관리")) color = "13";
				if(JSON.statEvetNm.contains("기상특보")) color = "14";
				if(JSON.statEvetNm.contains("동네예보")) color = "15";
				if(JSON.statEvetNm.contains("지진현황")) color = "16";
				if(JSON.statEvetNm.contains("응급복구장비")) color = "17";
			}

			if(JSON.statEvetTypCd == "NDMS119"){
				if(JSON.statEvetNm.contains("화재")) color = "21";
				if(JSON.statEvetNm.contains("구조")) color = "22";
				if(JSON.statEvetNm.contains("구급")) color = "23";
				if(JSON.statEvetNm.contains("기타")) color = "24";
			}

			Spatial.animateInterval = setInterval(function(){
				setTimeout(function(){
					/*if(color == 'sb1_1'){
						color = 'sb1_2';
					}else{
						color = 'sb1_1';
					}*/
					point.setStyle(new ol.style.Style({

						image :
							(dsc === undefined ? new ol.style.Icon(({
							//src : '../res/sym/evt/' + color + '.png'
							src: "../sym/getSymbol.do?mgrNo=" + smartCitySym[color]
						})) : ''),

						text : new ol.style.Text({
							text: addr,
							textAlign: "center",
							textBaseline: "hanging",
							offsetY: 30,
							font: "bold 15px arial",
							fill: new ol.style.Fill({
								color: "blue"
							}),
							stroke: new ol.style.Stroke({
								color: "#FFFFFF",
								width: 2
							})
						}),
						zindex: 10000
					}));
				}, 100);
			}, 100);

			var moveCenter = center;
			moveCenter = [center[0], center[1]];
			targetMap.getView().animate({
				center : moveCenter,
				zoom : 15,
				duration : 500
			});
		}else{
			var date = new Date();
			try {
			//	console.log(date.formatYMDHMS(date.getYMDHMS()) + " Json > outbPos(" + JSON.outbPos.toString() + ") Error.");
			} catch (e) {
			//	console.log(date.formatYMDHMS(date.getYMDHMS()) + " Json > outbPos Error.");
			//	console.log(this.json);
			}
		}
	},

}