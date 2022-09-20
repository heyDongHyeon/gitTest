/**
 * <pre>
 * 공간정보 함수 객체입니다.
 *
 * @author 이주영
 * </pre>
 */
if(window.Spatial == null) var Spatial = {

	/**
	 * api 키를 가져온다.
	 *
	 */
	getAPIKey : function(){
	//alert('2');
		//return '59442ebbb9d724914da2ed287734238f';


//		return '632a514d03318cde3f6d304dd343f5e9';
		return 'fdd8cebb497a0f7bcc4245ec2bf0a8a2';
	},
	/**
	 * <pre>
	 * 지도 객체의 Extent 값을 리턴합니다.
	 *
	 * 필수) Map - 지도객체
	 * </pre>
	 */
	getExtent : function(Map){
		return Map.getView().calculateExtent(Map.getSize());
	},

	/**
	 * <pre>
	 * 지도 객체의 중심좌표를 위경도 좌표로 변환하게 리턴합니다.
	 *
	 * 필수) Map - 지도객체
	 * </pre>
	 */
	convertCenterToLonLat : function(Map){
		return ol.proj.transform(Map.getView().getCenter(), Map.getView().getProjection().getCode(), "EPSG:4326");
	},

	/**
	 * <pre>
	 * 지도 객체의 중심좌표를 TM 좌표로 변환하게 리턴합니다.
	 *
	 * 필수) Map - 지도객체
	 * </pre>
	 */
	convertCenterToTm : function(Map){
		return ol.proj.transform(Map.getView().getCenter(), Map.getView().getProjection().getCode(), "EPSG:5186");
	},

	/**
	 * <pre>
	 * 주어진 좌표계를 통해 변경합니다.
	 *
	 * 필수) xy - XY(Array)
	 * 필수) b_epsg - 소스 좌표계(String)
	 * 필수) t_epsg - 타켓 좌표계(String)
	 * </pre>
	 */
	convertProjection : function(xy, b_epsg, t_epsg){
		return ol.proj.transform(xy, b_epsg, t_epsg);
	},


	animateInterval : null,
	stopInterval : function(){
		if(this.animateInterval != null){
			clearInterval(this.animateInterval);
			this.animateInterval = null;
		}
	},
	addCicleAnimate : function(map, feature){
		var duration = 1500;
		var start = new Date().getTime();
		var listenerKey;

		function animate(event) {
			var vectorContext = event.vectorContext;
			var frameState = event.frameState;
			var flashGeom = feature.getGeometry().clone();
			var elapsed = frameState.time - start;
			var elapsedRatio = elapsed / duration;
			var radius = ol.easing.easeOut(elapsedRatio) * 25 + 5;
			var opacity = ol.easing.easeOut(1 - elapsedRatio);

			var style = new ol.style.Style({
				image: new ol.style.Circle({
					radius: radius,
					snapToPixel: false,
					stroke: new ol.style.Stroke({
						color: 'rgba(255, 0, 0, ' + opacity + ')',
						width: 0.25 + opacity
					})
				})
			});

			vectorContext.setStyle(style);
			vectorContext.drawGeometry(flashGeom);
			if (elapsed > duration) {
				ol.Observable.unByKey(listenerKey);
				return;
			}
			map.render();
		}

		listenerKey = map.on('postcompose', animate);

	},

	/**
	 * <pre>
	 * 경위도를 도분초 형식으로 변환합니다.
	 *
	 * 필수) deg - 경도 또는 위도 (Number)
	 *
	 * @return 도분초 문자열
	 * </pre>
	 */
	convertDegToDMS : function(deg){
		var d = Math.floor (deg);
		var minfloat = (deg-d)*60;
		var m = Math.floor(minfloat);
		var secfloat = (minfloat-m)*60;
		//var s = Math.round(secfloat);
		var s = secfloat.toFixed(4);
		/*if(s == 60){
			m++;
			s = 0;
		}
		if(m == 60){
			d++;
			m = 0;
		}*/
		return ("" + d + "도 " + m + "분 " + s + "초");
	},

	/**
	 * <pre>
	 * 도분초를 경위도 형식으로 변환합니다.
	 *
	 * 필수) d - 도 (Number)
	 * 필수) m - 분 (Number)
	 * 필수) s - 초 (Number)
	 *
	 * @return 경위도 Number
	 * </pre>
	 */
	convertDMSToDeg : function(d, m, s){
		var deg = Number(d) + (Number(m)/60.0) + (Number(s)/3600.0);
		return deg;
	},

	/**
	 * <pre>
	 * 위경도를 기상청 그리드로 변환합니다.
	 *
	 * 필수) lat - 위도 (Number)
	 * 필수) lon - 경도 (Number)
	 *
	 * @return rs - 파라미터 및 그리드 정보 (Object)
	 * </pre>
	 */
	convertDfsToXY : function(lat, lon){
		var RE = 6371.00877; // 지구 반경(km)
		var GRID = 5.0; // 격자 간격(km)
		var SLAT1 = 30.0; // 투영 위도1(degree)
		var SLAT2 = 60.0; // 투영 위도2(degree)
		var OLON = 126.0; // 기준점 경도(degree)
		var OLAT = 38.0; // 기준점 위도(degree)
		var XO = 43; // 기준점 X좌표(GRID)
		var YO = 136; // 기1준점 Y좌표(GRID)

		var DEGRAD = Math.PI / 180.0;
		var RADDEG = 180.0 / Math.PI;

		var re = RE / GRID;
		var slat1 = SLAT1 * DEGRAD;
		var slat2 = SLAT2 * DEGRAD;
		var olon = OLON * DEGRAD;
		var olat = OLAT * DEGRAD;

		var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5);
			sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn);
		var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5);
			sf = Math.pow(sf, sn) * Math.cos(slat1) / sn;
		var ro = Math.tan(Math.PI * 0.25 + olat * 0.5);
			ro = re * sf / Math.pow(ro, sn);

		var rs = {};
			rs['lat'] = lat;
			rs['lng'] = lon;
		var ra = Math.tan(Math.PI * 0.25 + (lat) * DEGRAD * 0.5);
			ra = re * sf / Math.pow(ra, sn);
		var theta = lon * DEGRAD - olon;
		if (theta > Math.PI) theta -= 2.0 * Math.PI;
		if (theta < -Math.PI) theta += 2.0 * Math.PI;
		theta *= sn;

		rs['nx'] = Math.floor(ra * Math.sin(theta) + XO + 0.5);
		rs['ny'] = Math.floor(ro - ra * Math.cos(theta) + YO + 0.5);

		return rs;
	},

	/**
	 * <pre>
	 * 주소를 이용하여 경위도를 받아옵니다.
	 *
	 * 필수) addr (String)
	 *
	 * </pre>
	 */
	convertAddrToXY : function(addr){
		var result = "";
		$.ajax({
			type : "GET",
//			url : "../tms/dmz?url=http://dapi.kakao.com/v2/local/search/address.json&query=" + addr,
			url : "http://dapi.kakao.com/v2/local/search/address.json&query=" + addr,
			dataType : "json",
			async : false,
			beforeSend : function(xhr){
				xhr.setRequestHeader("Authorization", "KakaoAK "+Spatial.getAPIKey());
			},
			success : function(json){
				if(json.documents.length == 0){
					result = "error";
				}else{
					result = [Number(json.documents[0].x), Number(json.documents[0].y)];
				}
			}
		});

		if(result == "error"){
			$.ajax({
				type : "GET",
				url : "http://dapi.kakao.com/v2/local/search/keyword.json?query=" + addr,
				dataType : "json",
				async : false,
				beforeSend : function(xhr){
					xhr.setRequestHeader("Authorization", "KakaoAK "+Spatial.getAPIKey());
				},
				success : function(json){
					if(json.documents.length == 0){
						result = "error";
					}else{
						result = [Number(json.documents[0].x), Number(json.documents[0].y)];
					}
				}
			});
		}

		return result;
	},

	/**
	 * <pre>
	 * xy를 이용하여 주소를 받아옵니다.
	 *
	 * 필수) addr (String)
	 * 선택
	 *
	 * </pre>
	 */
	convertXYToJibun : function(x, y, returnObject){

		var result = "";

		var sendURL = "https://dapi.kakao.com/v2/local/geo/coord2address.json";
		var param = {
			url : null,
			input_coord : "WGS84",
			x : x,
			y : y
		}

		param.url = "https://dapi.kakao.com/v2/local/geo/coord2address.json";
//		if(_IS_PROXY_) sendURL = _PROXY_API_DATA_URL_;

		$.ajax({
			type : "GET",
			url : sendURL,
			dataType : "json",
			data : param,
			async : false,
			beforeSend : function(xhr){
				xhr.setRequestHeader("Authorization", "KakaoAK 892c2cad01a8701f993c3a5dd4b2bdda");
			},
			success : function(json){
				if(json.documents.length == 0){
					result = "error";
				}else{
					result = json.documents[0].address.main_address_no;
					if(!_common.utils.isNullAndEmpty(json.documents[0].address.sub_address_no)){
						result += "-" + json.documents[0].address.sub_address_no;
					}

					if(returnObject){
						result = {};
						var jibun = json.documents[0].address.main_address_no;
						if(!_common.utils.isNullAndEmpty(json.documents[0].address.sub_address_no)){
							jibun += "-" + json.documents[0].address.sub_address_no;
						}
						result["jibun"] = jibun;
						result["bjd"] = json.documents[0].address.region_3depth_name;
						result["san"] = "";
						if(json.documents[0].address.mountain_yn == "Y") result["san"] = "산";
					}
				}
			}
		});
		return result;
	},

	/**
	 * <pre>
	 * 주소를 이용하여 경위도를 받아옵니다.
	 *
	 * 필수) addr (String)
	 *
	 * </pre>
	 */
	convertXYToAddr : function(x, y){
		var result = "";
		$.ajax({
			type : "GET",
			url : "http://dapi.kakao.com/v2/local/geo/coord2address.json?input_coord=WGS84&x=" + x + "&y=" + y,
			dataType : "json",
			async : false,
			beforeSend : function(xhr){
				xhr.setRequestHeader("Authorization", "KakaoAK "+Spatial.getAPIKey());
			},
			success : function(json){
				if(json.documents.length == 0){
					result = "error";
				}else{
					result = json.documents[0].address.address_name;
				}
			}
		});
		return result;
	},

	/**
	 * <pre>
	 * 키워드를 이용하여 경위도를 받아옵니다.
	 *
	 * 필수) addr (String)
	 *
	 * </pre>
	 */
	convertKeywordToAddr : function(query){
		var result = "";
		$.ajax({
			type : "GET",
			url : "http://dapi.kakao.com/v2/local/search/keyword.json?query=" + query,
			dataType : "json",
			async : false,
			beforeSend : function(xhr){
				xhr.setRequestHeader("Authorization", "KakaoAK "+Spatial.getAPIKey());
			},
			success : function(json){
				if(json.documents.length == 0){
					result = "error";
				}else{
					result = [Number(json.documents[0].x), Number(json.documents[0].y)];
				}
			}
		});
		return result;
	},

	/**
	 * <pre>
	 * TM > LonLat 변환하여 각도를 구합니다.
	 * </pre>
	 *
	 * @param startTm - TM Array
	 * @param endTm - TM Array
	 * @returns
	 */
	getAngle : function(startTm, endTm){

		var startLonLat = Spatial.convertProjection(startTm, "EPSG:5186", "EPSG:4326");
		var endLonLat = Spatial.convertProjection(endTm, "EPSG:5186", "EPSG:4326");

		var startLat = startLonLat[1];
		var startLon = startLonLat[0];
		var endLat = endLonLat[1];
		var endLon = endLonLat[0];

		// 현재 위치 : 위도나 경도는 지구 중심을 기반으로 하는 각도이기 때문에 라디안 각도로 변환한다.
		var curLatRadian = startLat * (3.141592 / 180);
		var curLonRadian = startLon * (3.141592 / 180);

	    // 목표 위치 : 위도나 경도는 지구 중심을 기반으로 하는 각도이기 때문에 라디안 각도로 변환한다.
		var destLatRadian = endLat * (3.141592 / 180);
		var destLonRadian = endLon * (3.141592 / 180);

	    // radian distance
		var radianDistance = 0;
		radianDistance = Math.acos(Math.sin(curLatRadian) * Math.sin(destLatRadian) + Math.cos(curLatRadian) * Math.cos(destLatRadian) * Math.cos(curLonRadian - destLonRadian));

	    // 목적지 이동 방향을 구한다.(현재 좌표에서 다음 좌표로 이동하기 위해서는 방향을 설정해야 한다. 라디안값이다.
		var radianBearing = Math.acos((Math.sin(destLatRadian) - Math.sin(curLatRadian) * Math.cos(radianDistance)) / (Math.cos(curLatRadian) * Math.sin(radianDistance)));        // acos의 인수로 주어지는 x는 360분법의 각도가 아닌 radian(호도)값이다.

		var trueBearing = 0;
		if(Math.sin(destLonRadian - curLonRadian) < 0){
			trueBearing = radianBearing * (180 / 3.141592);
			trueBearing = 360 - trueBearing;
		}else{
			trueBearing = radianBearing * (180 / 3.141592);
		}

		return parseInt(trueBearing);
	},

	/**
	 * <pre>
	 * Degrees 값을 Radians 로 변경합니다.
	 * </pre>
	 *
	 * @param degrees - Number
	 * @returns {Number}
	 */
	convertRadians : function(degrees){
		return degrees * Math.PI / 180;
	},

	/**
	 * <pre>
	 * Radians 값을 Degrees 로 변경합니다.
	 * </pre>
	 *
	 * @param radians - Number
	 * @returns {Number}
	 */
	convertDegrees : function(radians){
		return radians * 180 / Math.PI;
	},

	/**
	 * <pre>
	 * 경위도를 이용하여 주소데이터를 받아옵니다.
	 *
	 * 필수) x - lat 127 (Number)
	 * 필수) y - lon 38  (Number)
	 *
	 * </pre>
	 */
	/*convertXYToAddr : function(x, y){
		$.ajax({
			url      : "http://apis.daum.net/local/geo/coord2detailaddr?apikey=043cf003ea7e5ecff70b2e5cf9a9cd5e&x=" + x + "&y=" + y + "&inputCoordSystem=WGS84&output=json",
			dataType : "jsonp",
			success  : function(json){
				var array = json.region.split(" ");
				var text = " ";
				for(var i=0; i<array.length; i++){
					$("#addrText").attr("addr" + i, array[i]);
					if(i != 0){
						text += " > " + array[i];
					}else{
						text += array[i];
					}
				}
				$("#addrText").text(text);
			},
			error    : function(){
				//alert("서버에 요청중 문제가 발생했습니다.\n관리자에게 문의하여 주십시오.");
			}
		});
	}*/


	/**

	 * 경위도를 이용하여 거리 구하기

	 */
	getDistanceFromLatLon : function(loc){
		var lat1 = 36.56850356260252;
	    var lng1 = 127.25738361299256;
	    var lat2 = loc.lat;
	    var lng2 = loc.lon;

	    function deg2rad(deg) {
	        return deg * (Math.PI/180)
	    }
	    var r = 6371; //지구의 반지름(km)
	    var dLat = deg2rad(lat2-lat1);
	    var dLon = deg2rad(lng2-lng1);
	    var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
	    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    var d = r * c; // Distance in km
	    return Math.round(d*1000);
	}
}