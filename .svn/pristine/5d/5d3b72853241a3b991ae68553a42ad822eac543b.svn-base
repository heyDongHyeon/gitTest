if (window.BoardAPI == null) var BoardAPI = {};

/**
 * <pre>
 * 유틸리티
 * 객체입니다.
 *
 * @author 이주영
 * </pre>
 */
BoardAPI = {

	getDate : function() {
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		var hours = today.getHours();
		var minutes = today.getMinutes();
		var second = today.getSeconds();

		if (hours < 10) {
			hours = '0' + hours
		}
		if (minutes < 10) {
			minutes = '0' + minutes
		}
		if (second < 10) {
			second = '0' + second
		}
		if (mm < 10) {
			mm = '0' + mm
		}
		if (dd < 10) {
			dd = '0' + dd
		}

		$('#date').text(yyyy + "-" + mm + "-" + dd);
		$('#time').text(hours + ":" + minutes + ":" + second);
	},

	/**
	 * 기상청 API 에 맞는 시간을 계산합니다.
	 */
	getWeatherDate : function() {
		var today = new Date();
		var dd = today.getDate();
		var mm = today.getMonth() + 1;
		var yyyy = today.getFullYear();
		var hours = today.getHours();
		var minutes = today.getMinutes();
		var time = null;

		if (minutes < 30) {
			hours = hours - 1;
			if (hours < 0) {
				today.setDate(today.getDate() - 1);
				dd = today.getDate();
				mm = today.getMonth() + 1;
				yyyy = today.getFullYear();
				hours = 23;
			}
		}
		if (hours > 23 && hours <= 02) {
			if(dd-1 == 0){
				dd = 30;
			}else{
				dd = dd - 1;
			}
			time = 23;
		} else if (hours > 02 && hours <= 05) {
			time = 02;
		} else if (hours > 05 && hours <= 08) {
			time = 05;
		} else if (hours > 08 && hours <= 11) {
			time = 08;
		} else if (hours > 11 && hours <= 14) {
			time = 11;
		} else if (hours > 14 && hours <= 17) {
			time = 14;
		} else if (hours > 17 && hours <= 20) {
			time = 17;
		} else if (hours > 20 && hours <= 23) {
			time = 20;
		}

		if (time < 10) {
			time = '0' + time
		}
		if (mm < 10) {
			mm = '0' + mm
		}
		if (dd < 10) {
			dd = '0' + dd
		}

		return {
			"date" : yyyy + "" + mm + "" + dd,
			"time" : time + "00"
		}

	},

	/**
	 * 지도 중심점 위치를 기준으로 대기를 갱신합니다.
	 */
	getAir : function(map) {

		var _PARAMETER = {};
		var station = null;
		var airStationParam = {};


		try{
			var lonlat = Spatial.convertCenterToLonLat(map);
			var addr = Spatial.convertXYToJibun(lonlat[0], lonlat[1], true);
			var umdName = addr.bjd.split(" ")[0];
			_PARAMETER["umdName"] = umdName;
		}catch(e){
			_PARAMETER["umdName"] =  "보람동";
		}


		_common.callAjax("/api/getTmLonLatByUmdName.xml", _PARAMETER, function(result) {

			if ($(result).find("resultCode").text() == "00") {
				airStationParam["tmX"] = $(result).find("tmX").text();
				airStationParam["tmY"] = $(result).find("tmY").text();

				_common.callAjax("/api/getAirStation.xml", airStationParam, function(result) {

					station = $(result).find("stationName").eq(0).text();

					_common.callAjax("/api/getAir.xml", { "stationName" : station }, function(result) {

						if ($(result).find("resultCode").text() == "00") {
							rst = result;
							var o3 = Number($(result).find("o3Grade").eq(0).text());
							var pm10 = Number($(result).find("pm10Grade").eq(0).text());
							var mp25 = Number($(result).find("pm25Grade").eq(0).text());
							if ( o3 === undefined ) return false;
							if (o3 == 1) {
								$('.weatherO3').text('좋음').css("color", "#00D8FF");
							} else if (o3 == 2) {
								$('.weatherO3').text('보통').css("color", "#0f0");
							} else if (o3 == 3) {
								$('.weatherO3').text('나쁨').css("color", "#ff0");
							} else if (o3 == 4) {
								$('.weatherO3').text('매우나쁨').css("color", "#f00");
							} else{
								$('.weatherO3').text('없음').css("color", "#3ef9c3");
							}

							if (pm10 == 1) {
								$('.weatherPm10').text('좋음').css("color", "#00D8FF");
							} else if (pm10 == 2) {
								$('.weatherPm10').text('보통').css("color", "#0f0");
							} else if (pm10 == 3) {
								$('.weatherPm10').text('나쁨').css("color", "#ff0");
							} else if (pm10 == 4) {
								$('.weatherPm10').text('매우나쁨').css("color", "#f00");
							} else{
								$('.weatherPm10').text('없음').css("color", "#3ef9c3");
							}

							if (mp25 == 1) {
								$('.weatherPm25').text('좋음').css("color", "#00D8FF");
							} else if (mp25 == 2) {
								$('.weatherPm25').text('보통').css("color", "#0f0");
							} else if (mp25 == 3) {
								$('.weatherPm25').text('나쁨').css("color", "#ff0");
							} else if (mp25 == 4) {
								$('.weatherPm25').text('매우나쁨').css("color", "#f00");
							} else{
								$('.weatherPm25').text('없음').css("color", "#3ef9c3");
							}
						} else {
							$('.weatherO3').text('없음').css("color", "#3ef9c3");
							$('.weatherPm10').text('없음').css("color", "#3ef9c3");
							$('.weatherPm25').text('없음').css("color", "#3ef9c3");
						}
					}, true);
				}, true);
			}
		},true);


	},

	/**
	 * 지도 중심점 위치를 기준으로 대기정보를 리턴합니다.
	 */
	getAirData : function(map) {
		var lonlat = Spatial.convertCenterToLonLat(map);
		var tm = Spatial.convertCenterToTm(map);
		var station = null;

		var _PARAMETER = {
			"tmX" : tm[0],
			"tmY" : tm[1]
		};

		var result = null;
		_common.callAjax("/api/getAirStation.xml", _PARAMETER, function(result) {
			station = $(result).find("stationName").eq(0).text();
			_common.callAjax("/api/getAir.xml", { "stationName" : station }, function(result) {
				if ($(result).find("resultCode").text() == "00") {
					result = result;
				}
			}, true);
		}, true);

		return result;
	},

	/**
	 * 지도 중심점 위치를 기준으로 날씨를 갱신합니다.
	 */
	getWeather : function(map) {
		// var lonlat = [geomex.map.DaumMap.Map.getCenter().gb,
		// geomex.map.DaumMap.Map.getCenter().hb];
		var lonlat = Spatial.convertCenterToLonLat(map);
		var grid = Spatial.convertDfsToXY(lonlat[1], lonlat[0]);

		var _PARAMETER = {
			"base_date" : BoardAPI.getWeatherDate().date,
			"base_time" : BoardAPI.getWeatherDate().time,
			"nx" : grid.nx,
			"ny" : grid.ny
		};
		// console.log(_PARAMETER);
		_common.callAjax("/api/getWeather.xml", _PARAMETER, function(result) {
			var weather_pty='  ';
			var weather_sky='  ';
			var weather_t3h='  ';
			var weather_vec='  ';
			var weather_wsd='  ';
			var weather_reh='  ';
			var weather_pop='  ';
			var children = $(result).find("items").children();
			children.each(function(cnt) {
				if ($(children[cnt]).find("category").text() == "PTY") {
					weather_pty = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "SKY") {
					weather_sky = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "TMP") {
					weather_t3h = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "VEC") {
					weather_vec = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "WSD") {
					weather_wsd = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "REH") {
					weather_reh = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "POP") {
					weather_pop = $(children[cnt]).find("fcstValue").text();
				}

			});

			try {
				// // // 강수형태
				// var weather_pty =
				// result.response.body.items.item[1].fcstValue;
				// // // 날씨
				// var weather_sky =
				// result.response.body.items.item[3].fcstValue;



				if(weather_sky != undefined && weather_sky != null){
					if (weather_pty == 1) {
						weather_sky = '08';
					} else if (weather_pty == 2) {
						weather_sky = '12';
					} else if (weather_pty == 3) {
						weather_sky = '11';
					} else {
						weather_sky = '0' + weather_sky;
					}

					var weather_alt = "";
					switch (weather_sky) {
					case '01':
						weather_alt = "맑음";
						break;
					case '02':
						weather_alt = "구름조금";
						break;
					case '03':
						weather_alt = "구름많음";
						break;
					case '04':
						weather_alt = "흐림";
						break;
					case '08':
						weather_alt = "비";
						break;
					case '12':
						weather_alt = "비/눈";
						break;
					case '11':
						weather_alt = "눈";
						break;
					}

					var weather_img = '<img src="../res/img/board/weather/NB' + weather_sky + '.png" alt="' + weather_alt + '" width="30" height="30" onerror="this.onerror=null;this.src=\'../res/img/icon_info_normal.png\';"/>';
					$('.weatherIcon').html(weather_img);
				}


//				if ( weather_sky === undefined ) return false;
				//
				// // 온도
				// var weather_t3h =
				// result.response.body.items.item[4].fcstValue;
				// //
				// /*
				// * // * 풍향 0 - 45 sN-NE 180 - 225 S-SW 45 - 90 // * NE-E 225 -
				// * 270 SW-W 90 - 135 E-SE 270 - // * 315 W-NW 135 - 180 SE-S
				// 315 -
				// * 360 NW-N //
				// */
				// var weather_vec =
				// result.response.body.items.item[7].fcstValue;
				if (weather_vec == 0) {
					weather_vec = '북';
				} else if (weather_vec > 0 && weather_vec < 45) {
					weather_vec = '북북동';
				} else if (weather_vec == 45) {
					weather_vec = '북동';
				} else if (weather_vec > 45 && weather_vec < 90) {
					weather_vec = '동북동';
				} else if (weather_vec == 90) {
					weather_vec = '동';
				} else if (weather_vec > 90 && weather_vec < 135) {
					weather_vec = '동남동';
				} else if (weather_vec == 135) {
					weather_vec = '남동';
				} else if (weather_vec > 135 && weather_vec < 180) {
					weather_vec = '남남동';
				} else if (weather_vec == 180) {
					weather_vec = '남';
				} else if (weather_vec > 180 && weather_vec < 225) {
					weather_vec = '남남서';
				} else if (weather_vec == 225) {
					weather_vec = '남서';
				} else if (weather_vec > 225 && weather_vec < 270) {
					weather_vec = '서남서';
				} else if (weather_vec == 270) {
					weather_vec = '서';
				} else if (weather_vec > 270 && weather_vec < 315) {
					weather_vec = '서북서';
				} else if (weather_vec == 315) {
					weather_vec = '북서';
				} else if (weather_vec > 315 && weather_vec < 360) {
					weather_vec = '북북서';
				} else if (weather_vec == 360) {
					weather_vec = '북';
				}
				// // // 풍속
				// var weather_wsd =
				// result.response.body.items.item[9].fcstValue;
				// // // 습도
				// var weather_reh =
				// result.response.body.items.item[2].fcstValue;
				// // 강수확률
				// var weather_pop =
				// result.response.body.items.item[0].fcstValue;
				// //

				$('.weatherTemperature').text('기온 : ' + weather_t3h + '℃ | ');
				$(".weatherPop").text('강수확률 : ' + weather_pop + '% | ');

				$('.weatherVecWsd').text('바람 : ' + weather_vec + ' ' + weather_wsd + 'm/s | ');
				$('.weatherReh').text('습도 : ' + weather_reh + '%');
			} catch (e) {
			}
			//
			// } else {
			// // console.log("Aaaa");
			// $('#weatherIcon').html('');
			// $('#weatherTemperature').text('-' + '℃');
			// $('#weatherRn1').text('-' + 'mm');
			// $('#weatherVecWsd').text(
			// '-' + ' ' + '-' + 'm/s');
			// $('#weatherReh').text('-' + '%');
			// }
		}, true);
	},

	getAPI : function(){
		setTimeout(function(){
			if ( xeusLayout.mapService != undefined) {
				BoardAPI.getWeather(xeusLayout.mapService.getMap());
				BoardAPI.getAir(xeusLayout.mapService.getMap());
				BoardAPI.getAPI();
			}
			else{
				BoardAPI.getAPI();	//xeusLayout.mapService가 undefined면 저기서 걸려서 안나온다...
			}
		}, 10*60*(1*1000));
//		}, 50*(1*1000));
	},

	/**
	 * 지도 중심점 위치를 기준으로 날씨 정보를 리턴합니다.
	 */
	getWeatherData : function(map, x, y) {
		var lonlat = Spatial.convertCenterToLonLat(map);
		if(x == null) x = lonlat[1];
		if(y == null) y = lonlat[0];
		var grid = Spatial.convertDfsToXY(x, y);

		var _PARAMETER = {
			"base_date" : BoardAPI.getWeatherDate().date,
			"base_time" : BoardAPI.getWeatherDate().time,
			"nx" : grid.nx,
			"ny" : grid.ny
		};

		var result = null;
		_common.callAjax("/api/getWeather.xml", _PARAMETER, function(xml) {
			var weather_pty;
			var weather_sky;
			var weather_t3h;
			var weather_vec;
			var weather_wsd;
			var weather_reh;
			var weather_pop;
			var children = $(xml).find("items").children();
			children.each(function(cnt) {
				if ($(children[cnt]).find("category").text() == "PTY") {
					weather_pty = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "SKY") {
					weather_sky = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "T3H") {
					weather_t3h = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "VEC") {
					weather_vec = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "WSD") {
					weather_wsd = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "REH") {
					weather_reh = $(children[cnt]).find("fcstValue").text();
				} else if ($(children[cnt]).find("category").text() == "POP") {
					weather_pop = $(children[cnt]).find("fcstValue").text();
				}

			});

			try {
				if (weather_pty == 1) {
					weather_sky = '08';
				} else if (weather_pty == 2) {
					weather_sky = '12';
				} else if (weather_pty == 3) {
					weather_sky = '11';
				} else {
					weather_sky = '0' + weather_sky;
				}
				var weather_alt = "";
				switch (weather_sky) {
				case '01':
					weather_alt = "맑음";
					break;
				case '02':
					weather_alt = "구름조금";
					break;
				case '03':
					weather_alt = "구름많음";
					break;
				case '04':
					weather_alt = "흐림";
					break;
				case '08':
					weather_alt = "비";
					break;
				case '12':
					weather_alt = "비/눈";
					break;
				case '11':
					weather_alt = "눈";
					break;
				}
				//
				var weather_img = '<img src="../res/img/board/weather/NB' + weather_sky + '.png" alt="' + weather_alt + '" width="30" height="30"/>';

				if (weather_vec == 0) {
					weather_vec = '북';
				} else if (weather_vec > 0 && weather_vec < 45) {
					weather_vec = '북북동';
				} else if (weather_vec == 45) {
					weather_vec = '북동';
				} else if (weather_vec > 45 && weather_vec < 90) {
					weather_vec = '동북동';
				} else if (weather_vec == 90) {
					weather_vec = '동';
				} else if (weather_vec > 90 && weather_vec < 135) {
					weather_vec = '동남동';
				} else if (weather_vec == 135) {
					weather_vec = '남동';
				} else if (weather_vec > 135 && weather_vec < 180) {
					weather_vec = '남남동';
				} else if (weather_vec == 180) {
					weather_vec = '남';
				} else if (weather_vec > 180 && weather_vec < 225) {
					weather_vec = '남남서';
				} else if (weather_vec == 225) {
					weather_vec = '남서';
				} else if (weather_vec > 225 && weather_vec < 270) {
					weather_vec = '서남서';
				} else if (weather_vec == 270) {
					weather_vec = '서';
				} else if (weather_vec > 270 && weather_vec < 315) {
					weather_vec = '서북서';
				} else if (weather_vec == 315) {
					weather_vec = '북서';
				} else if (weather_vec > 315 && weather_vec < 360) {
					weather_vec = '북북서';
				} else if (weather_vec == 360) {
					weather_vec = '북';
				}

				result = {
					"temp" : weather_t3h + "℃",
					"rain" : weather_pop + "%",
					/*"wind" : weather_vec + " " + weather_wsd + "m/s",*/
					"wind" : weather_wsd + "m/s",
					"hum"  : weather_reh + "%"
				}
				$('.weatherIcon').html(weather_img);
				$('.weatherTemperature').text('기온 : ' + weather_t3h + '℃ | ');
				$(".weatherPop").text('강수확률 : ' + weather_pop + '% | ');

				$('.weatherVecWsd').text('바람 : ' + weather_vec + ' ' + weather_wsd + 'm/s | ');
				$('.weatherReh').text('습도 : ' + weather_reh + '%');
			} catch (e) {

			}
		}, false);

		return result;
	},

}