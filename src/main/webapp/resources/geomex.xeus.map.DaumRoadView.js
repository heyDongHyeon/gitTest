/**
 * <pre>
 * 다음 로드뷰 객체입니다.
 *
 * @author 이주영
 * </pre>
 */
geomex.xeus.DaumRoadView = {

	TMS : null,
	marker : null,
	markerTarget : null,

	removeMarker : function(){
		if(this.TMS != null){
			xeusLayout.mapService.getMap().removeLayer(this.TMS);
			this.TMS = null;
		}
		if(this.marker != null){
			xeusLayout.mapService.getMap().removeLayer(this.marker);
			this.marker = null;
		}
		if(this.markerTarget != null){
			$("#RoadViewMarker").remove();
			this.markerTarget = null;
		}

		return this;
	},
	createTMS : function(){
		var daumMap = new geomex.xeus.tms.DaumMap();
		this.TMS = daumMap.createRoadViewLayer({
						visible : true,
						proxy : false
					});
		xeusLayout.mapService.getMap().addLayer(this.TMS);
	},
	createMarker : function(){
		this.removeMarker();
		this.createTMS();

		$("#RoadViewMarker").remove();
		$('#'+parentView).find(".map-target").append("<div id='RoadViewMarker'></div>");

		this.markerTarget = document.getElementById('RoadViewMarker');
		$(this.markerTarget).append("<div class='MapWalker'><div class='figure'></div><div class='angleBack'></div></div>");

		this.marker = new ol.Overlay({
			position: xeusLayout.mapService.getMap().getView().getCenter(),
			positioning: 'center-center',
			element: this.markerTarget,
			stopEvent: true
		});

		this.markerTarget.addEventListener('mousedown', function(evt) {
			function move(evt) {
				try{
					geomex.xeus.DaumRoadView.marker.setPosition(xeusLayout.mapService.getMap().getEventCoordinate(evt));
				}catch(e){
					console.error(e);
					if(e.message.contains("daum is not defined")){
						window.removeEventListener('mousemove', move);
						window.removeEventListener('mouseup', end);
						geomex.xeus.DaumRoadView.destroyRoadView();
						alert("로드뷰를 생성할 수 없습니다.\n\n네트워크 환경을 확인해 주세요.");
					}
				}
			};
			function end(evt) {
				try{
					var xy = geomex.xeus.DaumRoadView.marker.getPosition();
					var coordinate = Spatial.convertProjection(xy, "EPSG:5186", "EPSG:4326");

//					var transXY = ol.proj.transform(xy, 'EPSG:5186', 'EPSG:4326');
					var position = new daum.maps.LatLng(coordinate[1], coordinate[0]);	//클릭한 지점 좌표

					var roadview_pop=$("#" + parentView).find('#roadview_pop');
					if(roadview_pop.css("display")=="none"){	//none일 시 none을 없애고 표출시킨다.
						roadview_pop.css("display","");
					}

					geomex.xeus.DaumRoadView.createRoadView(position);

					window.removeEventListener('mousemove', move);
					window.removeEventListener('mouseup', end);
				}catch(e){
					console.error(e);
					if(e.message.contains("daum is not defined")){
						window.removeEventListener('mousemove', move);
						window.removeEventListener('mouseup', end);
						geomex.xeus.DaumRoadView.destroyRoadView();
						alert("로드뷰를 생성할 수 없습니다.\n\n네트워크 환경을 확인해 주세요.");
					}
				}

			};
			window.addEventListener('mousemove', move);
			window.addEventListener('mouseup', end);
		});

		xeusLayout.mapService.getMap().addOverlay(this.marker);
//		xeusLayout.mapService.getMap().addLayer(this.TMS);
	},

	rc : null,
	rv : null,

	removeRoadView : function(){
		if(this.rc != null){
			this.rc = null;
		}
		if(this.rv != null){
			if(this.rv.isLoaded()){
				this.rv.remove();
			}
			this.rv = null;
		}
		return this;
	},

	setAngle : function(angle){
		var threshold = 22.5;
		for(var i=0; i<16; i++){
			if(angle > (threshold * i) && angle < (threshold * (i + 1))){
				$(this.markerTarget).find(".MapWalker").attr("class", "MapWalker " + 'm' + i);
				break;
			}
		}
	},

	buffer : null,
	position : null,
	getNearsetPanoId : function(position, buffer){
		this.buffer = buffer;
		this.position = position;
		this.rc.getNearestPanoId(position, buffer, function(panoId) {
	        if (panoId !== null) {
	        	geomex.xeus.DaumRoadView.rv.setPanoId(panoId, position);
	        	geomex.xeus.DaumRoadView.rv.relayout();

	        }else{
	        	if(geomex.xeus.DaumRoadView.buffer > 500){
	        		alert("근접한 로드뷰 가능 도로가 없습니다.");
	        		return false;
	        	}
	        	geomex.xeus.DaumRoadView.getNearsetPanoId(
        			geomex.xeus.DaumRoadView.position,
        			geomex.xeus.DaumRoadView.buffer + 50
	        	);
	        }
	    });
	},

	createRoadView : function(position){
		$("#roadview").find("div").remove();
		this.removeRoadView();

		this.rc = new daum.maps.RoadviewClient();
		this.rv = new daum.maps.Roadview(document.getElementById("roadview"));	//daumAPI는 jquery가 안됨.

		this.getNearsetPanoId(position, 50);	//로드뷰 생성

		$('#roadview_close').off('click').on('click', function(){	//로드뷰 x버튼 클릭 시
			geomex.xeus.DaumRoadView.destroyRoadView();
		});

//		this.createMarker();	//마커 생성
//		this.createTMS();	//로드뷰도로 생성

		daum.maps.event.addListener(this.rv, "position_changed", function(){	//다른 좌표를 클릭 시

//			var xy = [geomex.xeus.DaumRoadView.rv.getPosition().ib, geomex.xeus.DaumRoadView.rv.getPosition().jb];
			var xy = [geomex.map.DaumRoadView.rv.getPosition().gb, geomex.map.DaumRoadView.rv.getPosition().hb]

			var transXY = ol.proj.transform(xy, 'EPSG:4326', 'EPSG:5186');
			var pan = geomex.xeus.DaumRoadView.rv.getViewpoint().pan; //로드뷰의 가로각도

			xeusLayout.mapService.getMap().getView().setCenter(transXY);	//지도를 클릭한 좌표가 중심좌표로 되게 이동.
			geomex.xeus.DaumRoadView.marker.setPosition(transXY);
			geomex.xeus.DaumRoadView.setAngle(pan);
		});

		daum.maps.event.addListener(this.rv, "viewpoint_changed", function(){	//로드뷰 회전 시
			var pan = geomex.xeus.DaumRoadView.rv.getViewpoint().pan;
			geomex.xeus.DaumRoadView.setAngle(pan);
		});
	},


	isAlive : function(){
		var bool = false;
		if(this.TMS != null || this.marker != null || this.markerTarget != null || this.rc != null || this.rv != null){
			bool = true;
		}

		return bool;
	},

	destroyRoadView : function(){
		$("#RoadViewMarker").remove();
		$('#roadview_pop').remove();
		this.removeRoadView().removeMarker();
	}
}