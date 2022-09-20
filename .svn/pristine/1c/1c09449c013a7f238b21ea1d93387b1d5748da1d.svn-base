/**
 *
 * ------------------------- WebSocket.readyState -------------------------
 *
 * WebSocket Wrapper 객체 입니다.
 * 객체 생성 후 create 메소드로 소켓에 연결 할 수 있습니다.
 * 연결이 해제 될 경우, 1초마다 계속 재접속합니다.
 *
 * CONNECTING	0	연결이 수립되지 않은 상태입니다.
 * OPEN			1	연결이 수립되어 데이터가 오고갈 수 있는 상태입니다.
 * CLOSING		2	연결이 닫히는 중 입니다.
 * CLOSED		3	연결이 종료되었거나, 연결에 실패한 경우입니다.
 *
 * ------------------------------------------------------------------------
 *
 * @auther 이주영
 */
var XeusWS = function(_Map){

	var _ConnURL, _XWS, _JSON, _this = null;
	var _isAlive = false;

	this.getURL = function(){ return this._ConnURL; }
	this.getJson = function(){ return this._JSON; }
	this.getWebSocket = function(){ return this._XWS; }
	this.isAlive = function(){ return this._isAlive; }

	/**
	 * 소켓을 연결합니다.
	 */
	this.create = function(url){
		_this = this;
		this._ConnURL = url;
		this._XWS = new WebSocket(url);
		this._XWS.onmessage = function(e){ _this.request(e); };
		this._XWS.onopen = function(e){ _this.open(e); };
		this._XWS.onclose = function(e){ _this.close(e); };
	}

	/**
	 * 연결이 완료되었을때의 콜백입니다.
	 */
	this.open = function(e){
		this._isAlive = true;

		var date = new Date();
		console.log(date.formatYMDHMS(date.getYMDHMS()) + " WebSocket(" + this._ConnURL + ") Open.");
		$("#wsStat").attr("src", "../res/img/green.png").attr("title", "WebSocket Open");;

	}

	/**
	 * 서버에게 데이터를 받을 때 발생합니다.
	 */
	this.request = function(e){

		// TODO 상태이상 소스 변경해야함. (서버, 클라이언트는 하단부 70 라인으로)
		if(e.data == "CCTV-Reload"){
			xeusCCTV.cctv.reload();
		}else{
			var _thisJson = null;
			var earthJson= null;
			try {
				if(JSON.parse(e.data).Magnitude != undefined){		//지진 broadcase이면
					earthJson=JSON.parse(e.data);
					console.log('지진!!');
					alert(
						  '상황구분 	&nbsp&nbsp&nbsp:&nbsp&nbsp&nbsp	'+earthJson.status+
						  '<br>발표시간	&nbsp&nbsp &nbsp:&nbsp&nbsp&nbsp	 '+earthJson.ReportTime+
						  '<br>발생시각 	&nbsp&nbsp&nbsp:&nbsp&nbsp&nbsp	 '+earthJson.EventTime+
						  '<br>발생위치 	&nbsp&nbsp&nbsp:&nbsp&nbsp&nbsp	 '+earthJson.Location+
						  '<br>위도 	&nbsp&nbsp&nbsp:&nbsp&nbsp&nbsp	 '+earthJson.Latitude+
						  '<br>경도 	&nbsp&nbsp&nbsp:&nbsp&nbsp&nbsp	 '+earthJson.Longitude+
						  '<br>세종시와의 거리 	&nbsp&nbsp&nbsp:&nbsp&nbsp&nbsp	 '+earthJson.distance+'km'+
						  '<br>규모 	&nbsp&nbsp&nbsp:&nbsp&nbsp&nbsp	 '+earthJson.Magnitude+
						  '<br>발생깊이 	&nbsp&nbsp&nbsp:&nbsp&nbsp&nbsp	 '+earthJson.Depth
					);
					return;
				}
				this._JSON = JSON.parse(e.data);
				_thisJson = this._JSON;
			} catch (error) {
				var date = new Date();
				console.log(date.formatYMDHMS(date.getYMDHMS()) + error.name);
				console.log(date.formatYMDHMS(date.getYMDHMS()) + error.message);
//				console.log(e.data);
			}
			var statEvetTypCd = _thisJson.statEvetTypCd;
			if(statEvetTypCd == "CCTVSHER") statEvetTypCd = "CCTVPlay";

			_common.callAjax("/auth/hasEvtAuth.json", { "authData" : statEvetTypCd }, function(json){
				if(json.result){
					if(_thisJson.statMsgTypCd == "99"){
						var senderId = _thisJson.etcCntn.SENDER_ID;
						_common.callAjax("/eventHist/setSession.json", null, function(json){
							alert(senderId + " 님 로그인되었습니다.");
						});
					}else{

						var date = new Date();
						var uSvcOutbId = _thisJson.uSvcOutbId;
						if(xeusJsonParser.setJson(_thisJson).isStart()){

							xeusJsonParser.Start(_Map);
							console.log(date.formatYMDHMS(date.getYMDHMS()) + " WebSocket(" + _this._ConnURL + ") Event(" + uSvcOutbId + ") Start.");

						}else if(xeusJsonParser.isChange()){

							WIDGET.getEventListWidget();
							console.log(date.formatYMDHMS(date.getYMDHMS()) + " WebSocket(" + _this._ConnURL + ") Event(" + uSvcOutbId + ") Change.");

						}else{

							xeusJsonParser.Stop();
							console.log(date.formatYMDHMS(date.getYMDHMS()) + " WebSocket(" + _this._ConnURL + ") Event(" + uSvcOutbId + ") Stop.");
						}
					}
				}
			});

			/*if(this._JSON.isNotExternal){

				var _Header = this._JSON.header;
				var _Body = this._JSON.body;

				if(_Header.action == "lockOn") xeusSymbol.addLock([_Body.x, _Body.y], _Body.gid);

				if(_Header.action == "lockOff") xeusSymbol.removeFeature(_Body.gid, "isLock");

			}else{

			}*/
		}
	}

	/**
	 * 서버에게 데이터를 전송 할 때 호출합니다.
	 */
	this.send = function(param){
		this._XWS.send(param);
	}

	/**
	 * 접속이 끊겼을때 호출됩니다.
	 */
	this.close = function(){
		var _this = this;
		this._isAlive = false;
		var date = new Date();
		console.log(date.formatYMDHMS(date.getYMDHMS()) + " WebSocket(" + this._ConnURL + ") Close.");
		$("#wsStat").attr("src", "../res/img/red.png").attr("title", "WebSocket Close");

		this.reconnect();
	}

	/**
	 * 재시도 메소드 입니다.
	 */
	this.reconnect = function(){
		var _this = this;
		var _URL = this._ConnURL;
		setTimeout(function(){
			var date = new Date();
			console.log(date.formatYMDHMS(date.getYMDHMS()) + " WebSocket try Connect.");
			_this.create(_URL);
		}, 1000);
	}

}