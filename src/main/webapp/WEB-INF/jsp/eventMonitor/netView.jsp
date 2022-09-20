<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ include file="../common.jsp" %>
<%

String netDistLimit = (String)request.getAttribute("param");

//System.out.println(netDistLimit);

//if(param.containsKey("net_dist_limit")) netDistLimit = param.get("net_dist_limit");

/* ArrayList<CctvVo> list = null;

if (request.getAttribute("result") != null){
    list = (ArrayList<CctvVo>) request.getAttribute("result");
} */

%>
<script type="text/javascript">
$('#btn_pop_close').click(function(){
	xeusLayout.showYesNoDialog("영상 재생 종료",
			"CCTV영상 재생을 종료하시겠습니까?</br> 모든 CCTV영상 화면을 닫습니다.",
			function() {
		//xeusCCTV.closeAllGridPanePlayer();
		//xeusCCTV.closeAllGridPanePlayerAndKeepPane();
		var _gid = $('#HD_Player').data('gid');
		if(_gid){
			xeusCCTV.cctv.removePlayList(_gid);
			$('#HD_Player').removeData();
			$('#HD_Player').empty();
			var _html = '';
			_html += '<div id="HD_Canvas_div">';
		    _html += '</div>';
		    $('#HD_Player').append(_html);
		}

		xeusCCTV.stopNetMornitoring();
	});
});

$("#net-dist-limit").keydown(function(e){
	/* if(e.which == 13){
		$("#btn_sch").click();
	} */

	var keyID = (e.which) ? e.which : e.keyCode;
	if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
		return;
	else
		return false;
});

$("#net-dist-limit").keyup(function(e){
	/* if(e.which == 13){
		$("#btn_sch").click();
	} */
	var keyID = (e.which) ? e.which : e.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
		return;
	else
		event.target.value = event.target.value.replace(/[^0-9]/g, "");
});

$(".area").droppable({
	accept: ".cctv-overlay-content-img",
	hoverClass: "dropBoxHover",
    deactivate: function(){
    	//$(".searchWrapper").find(".aviWrapper").find(".dropBox").hide("clip", 200);
    },
	drop: function(event, ui){

		var netSchGbn = $(this).attr("id");
		var data = $(ui.draggable).data();
		ui.draggable.draggable("option", "revert", false);

		data.netSchGbn = netSchGbn;

		if(xeusCCTV.cctv.existNetList(data.gid)){
			alert('이미 재생중인 CCTV입니다.');
			return false;
		}

		if(data.stateCd == "장애"){
			alert('장애 CCTV는 투망모니터링 기능이 지원되지 않습니다.');
			return;
		}

		var type = data.mgrNo.substring(0, 3);
		if(type !== "CTV"){
			alert('투망모니터링은 CCTV만 등록할 수 있습니다.');
			return false;
		}

		/*
		이미 재생중인 패널일수도 있으므로 영상 재생 중지
		*/


		//data에 포함되어있는 gid를 얻음.
		var gid = $(this).data('gid');
		//타이틀 div의 하위 태그(타이틀, img태그) 삭제
		//투망모니터링 시작 시 어차피 생성됨.
		$(this).find('.xeus-dialog-title-div').empty();
		//재생이 되지 않는 패널엔 gid가 undefined이므로 체크 후 삭제하여야 함.
		//체크 안하면 오류 발생
		if (gid != undefined) xeusCCTV.cctv.removeNetList(gid);
		//재생중인 div의 하위 목록도 삭제
		$(this).find('.grid_parent').empty();
		//canvas 태그 다시 생성
		_html = "";
		_html += '<div id="grid_'+$(this).attr('id')+'_div">';
		_html += '</div>';
		$(this).find('.grid_parent').html(_html);
		//기존에 들어있던 데이터 삭제
		$(this).removeData();

		xeusCCTV.showNetMornitorInGridPane(data);
		var newFeature = xeusCCTV.makeArrowFeature(data);

		var features = xeusCCTV.vectorPointLayer.getSource().getFeatures();
		for(var i=0; i<features.length; i++){
			if(features[i].getProperties().name == netSchGbn)
				xeusCCTV.vectorPointLayer.getSource().removeFeature(features[i]);
		}
		xeusCCTV.vectorPointLayer.getSource().addFeature(newFeature);
		xeusCCTV.cctv.reload();
	}
});

$('#hd_camara_list').change(function(){

	var _gid = $('#HD_Player').data('gid');
	if(_gid){
		xeusCCTV.cctv.removePlayList(_gid);
	}

	$('#HD_Player').removeData();
	$('#HD_Player').empty();

	var _html = '';
	_html += '<div id="HD_Canvas_div">';
    _html += '</div>';
    $('#HD_Player').append(_html);


	var mgrNo = $(this).val();

	if(mgrNo){
		_common.callAjax("/cctv/getCctv.json", {"mgrNo" : mgrNo}, function(json){
			if(json.result){

				console.log(json.result);

				var gid = json.result['gid'];
				var mgrNo = json.result['mgrNo'];
				var cctvNm = json.result['cctvNm'];
				var deviceId = json.result['deviceId'];
				var channelNo = json.result['channelNo'];
				var gbnCd = json.result['gbnCd'];
				var gbnTxt = json.result['gbnTxt'];
				var angle = json.result['angle'];
				var point = json.result['point'];

				// 이미 재생되고 있을 cctv일 수도 있으므로 후에 확인해야 함.
				if (xeusCCTV.cctv.existPlayList(gid)) {
					//return;
				}

				var $player = $('#HD_Player');
			    var $ptzTop = $("<span class='ptzBtn' id='ptzTop'></span>").data({"type" : "Up", "mgrNo" : mgrNo});
				var $ptzBottom = $("<span class='ptzBtn' id='ptzBottom'></span>").data({"type" : "Down", "mgrNo" : mgrNo});
				var $ptzLeft = $("<span class='ptzBtn' id='ptzLeft'></span>").data({"type" : "Left", "mgrNo" : mgrNo});
				var $ptzRight = $("<span class='ptzBtn' id='ptzRight'></span>").data({"type" : "Right", "mgrNo" : mgrNo});
				var $zoomIn = $("<span class='ptzBtn' id='ptzZoomIn'></span>").data({"type" : "ZoomIn", "mgrNo" : mgrNo});
				var $zoomOut = $("<span class='ptzBtn' id='ptzZoomOut'></span>").data({"type" : "ZoomOut", "mgrNo" : mgrNo});

				$player.append($ptzTop).append($ptzBottom).append($ptzLeft).append($ptzRight).append($zoomIn).append($zoomOut);


				var _player_grid_id = 'HD_Player';
				var _canvasId = 'HD_Canvas';
				var _size =  "608x342";

				var _jsmpeg = xeusCCTV.cctv.getMpegPlayer(VIDEO_WEBSOCKET_URL, _canvasId, mgrNo, _size);
				xeusCCTV.cctv.addPlayList(_player_grid_id+"_div", json.result, _jsmpeg);
				$('#' + _player_grid_id).data('gid', gid);
				$('#' + _player_grid_id).data('mgrNo', mgrNo);
				$('#' + _player_grid_id).data('gbnCd', gbnCd);
				xeusCCTV.cctv.reload();

				var isDown = false;
				var $target = null;
				$player.find(".ptzBtn").mousedown(function(){
					isDown = true;
					$target = $(this);
					//console.log("PTZ : " + $(this).data().type + " Start");
					var mgrNo = $(this).data().mgrNo;
					var code = $(this).data().type;

					_common.callAjax("/onvif/setPTZ.json", {"mediaId" : mgrNo, "action" : "start", "code" : code}, function(){});
				}).mouseup(function(){
					isDown = false;
					$target = null;
					var mgrNo = $(this).data().mgrNo;
					var code = $(this).data().type;

					_common.callAjax("/onvif/setPTZ.json", {"mediaId" : mgrNo, "action" : "stop", "code" : code}, function(){});
				});

				$player.find(".ptzBtn").hover(function(){
					$(this).stop().fadeIn("fast");

					var id = $(this).attr("id");
					if(id == "ptzZoomIn" || id == "ptzZoomOut"){
						$("#ptzZoomIn").stop().fadeIn("fast");
						$("#ptzZoomOut").stop().fadeIn("fast");
					}
				}, function(){
					if(isDown) $target.mouseup();
					$(this).stop().fadeOut("fast");

				});

				$player.find("canvas").hover(function(){
					$(".ptzBtn").stop().fadeIn("fast");
				}, function(){
					$(".ptzBtn").stop().fadeOut("fast");
				});

				//화면 크기에 따른 ptz버튼들의 css 정리
				var _player_top_posi = $player.position().top;
				$player.find('#ptzTop').css('left', (($player.width()-10)/2)-20);
				$player.find('#ptzTop').css('top', (_player_top_posi + 5));
				$player.find('#ptzBottom').css('left', (($player.width()-10)/2)-20);
				$player.find('#ptzLeft').css('top', (_player_top_posi+($player.height()-30)/2)-15);
				$player.find('#ptzRight').css('top', (_player_top_posi+($player.height()-20)/2)-15);
				$player.find('#ptzZoomIn').css('right', ($player.width()/2)-25);
				$player.find('#ptzZoomIn').css('top', (_player_top_posi+$player.height()/2)-25);
				$player.find('#ptzZoomOut').css('right', ($player.width()/2)-25);
				$player.find('#ptzZoomOut').css('top', (_player_top_posi+$player.height()/2));
			}
		});
	}
});

function onlyNumber(event){
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( (keyID >= 48 && keyID <= 57) || (keyID >= 96 && keyID <= 105) || keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
		return;
	else
		return false;
}
function removeChar(event) {
	event = event || window.event;
	var keyID = (event.which) ? event.which : event.keyCode;
	if ( keyID == 8 || keyID == 46 || keyID == 37 || keyID == 39 )
		return;
	else
		event.target.value = event.target.value.replace(/[^0-9]/g, "");
}
</script>
<style>

    .esPanel {
        background: rgb(59, 59, 59);
        padding: 0px;
    }
    .area{
        width: 203px;
        height: 186px;
        border: 1px solid #666;
        background: transparent;
        float: left;
    }
    .area span {
        color: #fff;
        width: 100%;
        display: table-cell;
        vertical-align: middle;
        margin: 0;
        border: 0;
        margin-left: 10px;
    }
    .title_wrap {
        border: 1px solid rgba(255, 255, 255, 0.1);
        min-height: 30px;
    }
    .title_wrap .cctv_title{
    	line-height: 30px;
  		display:inline-block;
   		width:200px;
    	white-space:nowrap;
   		overflow:hidden;
    	text-overflow:ellipsis;
    }
    .xeus-dialog-title-icon {
        cursor: pointer;
    }

    /* 투망거리제한 관련 */
    #net-dist-limit-wrap{
        float: right;
        color: white;
        height: 30px;
        font-size: 13px;
        margin-right: 10px;
    }
    #net-dist-limit-wrap #net-dist-limit{
        height: 26px;
        text-align: right;
        padding-right: 10px;
        width: 100px;
        background: white;
        margin-top: 2px;
        border: none;
    }
</style>


<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">투망 모니터링</b>
    <button type="button" id="gridpane_btn_close">
        <img src="../res/img/close_btn.png">
    </button>
    <button class="blueBtn" id="btn_pop_close" style="width: 80px;">재생 종료</button>
    <div id="net-dist-limit-wrap">
        투망거리제한 : <input id="net-dist-limit" type="text" value="<%=netDistLimit%>" readonly> M
    </div>
</div>
<div id="overlay-east-contents" class="esPanel mCustomScrollbar" data-mcs-theme="minimal-dark">




    <div id="netGrid">
        <div>
            <div class="area" id="leftTop">
                <div><!--  class="ui-dialog-titlebar ui-corner-all  ui-widget-header ui-helper-clearfix " -->
                    <div class="title_wrap">
                        <div class="xeus-dialog-title-div">
                            <!--
                            <a href="#">
                                <img class="xeus-dialog-title-icon"> src="/xeus/res/sym/cctv/19.png"
                            </a>
                            <span class="cctv_title"></span>
                            -->
                        </div>
                    </div>
                </div>
                <div class="grid_parent" style="position: relative; width:200px; height:150px;">
                    <div id="grid_leftTop_div">
                    </div>
                </div>
            </div>
            <div class="area" id="top">
                <div>
                    <div class="title_wrap">
                        <div class="xeus-dialog-title-div">
                        </div>
                    </div>
                </div>
                <div class="grid_parent" style="position: relative; width:200px; height:150px;">
<!--                     <canvas id="grid_top" width="200" height="150"> -->
<!--                     </canvas> -->
                    <div id="grid_top_div">
                    </div>
                </div>
            </div>
            <div class="area" id="rightTop">
                <div>
                    <div class="title_wrap">
                        <div class="xeus-dialog-title-div">
                        </div>
                    </div>
                </div>
                <div class="grid_parent" style="position: relative; width:200px; height:150px;">
<!--                     <canvas id="grid_rightTop" width="200" height="150"> -->
<!--                     </canvas> -->
                    <div id="grid_rightTop_div">
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="area" id="left">
                <div>
                    <div class="title_wrap">
                        <div class="xeus-dialog-title-div">
                        </div>
                    </div>
                </div>
                <div class="grid_parent" style="position: relative; width:200px; height:150px;">
<!--                     <canvas id="grid_left" width="200" height="150"> -->
<!--                     </canvas> -->
                    <div id="grid_left_div">
                    </div>
                </div>
            </div>
            <div class="area" id="center">
                <div>
                    <div class="title_wrap">
                        <div class="xeus-dialog-title-div">
                        </div>
                    </div>
                </div>
                <div class="grid_parent" style="position: relative; width:200px; height:150px;">
<!--                     <canvas id="grid_center" width="200" height="150"> -->
<!--                     </canvas> -->
                    <div id="grid_center_div">
                    </div>
                </div>
            </div>
            <div class="area" id="right">
                <div>
                    <div class="title_wrap">
                        <div class="xeus-dialog-title-div">
                        </div>
                    </div>
                </div>
                <div class="grid_parent" style="position: relative; width:200px; height:150px;">
<!--                     <canvas id="grid_right" width="200" height="150"> -->
<!--                     </canvas> -->
                     <div id="grid_right_div">
                    </div>
                </div>
            </div>
        </div>
        <div>
            <div class="area" id="leftBottom">
                <div>
                    <div class="title_wrap">
                        <div class="xeus-dialog-title-div">
                        </div>
                    </div>
                </div>
                <div class="grid_parent" style="position: relative; width:200px; height:150px;">
<!--                     <canvas id="grid_leftBottom" width="200" height="150"> -->
<!--                     </canvas> -->
                      <div id="grid_leftBottom_div">
                    </div>
                </div>
            </div>
            <div class="area" id="bottom">
                <div>
                    <div class="title_wrap">
                        <div class="xeus-dialog-title-div">
                        </div>
                    </div>
                </div>
                <div class="grid_parent" style="position: relative; width:200px; height:150px;">
<!--                     <canvas id="grid_bottom" width="200" height="150"> -->
<!--                     </canvas> -->
                      <div id="grid_bottom_div">
                    </div>
                </div>
            </div>
            <div class="area" id="rightBottom">
                <div>
                    <div class="title_wrap">
                        <div class="xeus-dialog-title-div">
                        </div>
                    </div>
                </div>
                <div class="grid_parent" style="position: relative; width:200px; height:150px;">
<!--                     <canvas id="grid_rightBottom" width="200" height="150"> -->
<!--                     </canvas> -->
                    <div id="grid_rightBottom_div">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- <div id="HD_Panel" style="float: left; width: 613px;">border: 1px solid white; height: 309px;
        <div style="height: 32px;">border: 1px solid red;
            <select id="hd_camara_list" style="width: 300px; height: 30px;">
                <option value="">== 고해상도 카메라 선택 ==</option>
                <option value="CTV0000192">CCTV1</option>
                <option value="CTV0000193">CCTV2</option>
                <option value="CTV0000194">CCTV3</option>
                <option value="CTV0000195">CCTV4</option>
            </select>
        </div>
        <div id="HD_Player" style="height: 345px; border: 1px solid #666;">
            <canvas id="HD_Canvas" width="608" height="342">
            </canvas>
        </div>
    </div> -->
</div>
