<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.AuthGrpVo"%>
<%@ page import="geomex.xeus.sysmgr.service.AuthVo"%>
<%@ page import="geomex.xeus.map.service.MapVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="../common.jsp" %>
<%
//ArrayList<MapVo> favList = (ArrayList<MapVo>) request.getAttribute("favList");
HashMap<String, String> map = (HashMap<String, String>) request.getAttribute("map");
System.out.println(map);
String isTray = map.get("isTray");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="Mon, 06 Jan 1990 00:00:01 GMT">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<title>:: 세종시 스마트 재난정보 전파 시스템 ::</title>
<link rel="shortcut icon" href="<%= context %>/res/img/geomex.ico">

<link rel="stylesheet" type="text/css" href="<%= context %>/common/ui-1.12.1/themes/ui-darkness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/common/ol-v4.0.1/ol.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/common/gridstack/gridstack.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/common/gridstack/gridstack-extra.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/common/jquery.gridster.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/common/ui-1.12.1/jquery-ui.MonthPicker.min.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/common/bxslider/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/common/zTree/zTreeStyle.css">

<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.tvius.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.loading.css">
<% if(browser.equals("IE")){ %>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.layout.IE.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.favMap.IE.css">
<% }else{ %>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.layout.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.favMap.css">
<% } %>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.layer.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.ol.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.search.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.paging.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.mapSearch.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.cctv.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.send.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.ndms.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.stat.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/common/scroll/jquery.mCustomScrollbar.css">
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.roadview.css">

<script type="text/javascript" src="<%= context %>/common/FileSaver.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/bootstrap.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/lodash.js"></script>
<script type="text/javascript" src="<%= context %>/common/gridstack/gridstack.js"></script>
<script type="text/javascript" src="<%= context %>/common/gridstack/gridstack.jQueryUI.js"></script>
<script type="text/javascript" src="<%= context %>/common/gridstack/gridstack.custom.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.gridster.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.form.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.MonthPicker.ko.js"></script>

<script type="text/javascript" src="<%= context %>/common/jquery.inputmask.js"></script><!-- 추가됏음. -->
<script type="text/javascript" src="<%= context %>/common/jquery.inputmask.date.extensions.js"></script><!-- 추가됏음. -->
<script type="text/javascript" src="<%= context %>/common/jquery.inputmask.extensions.js"></script><!-- 추가됏음. -->
<script type="text/javascript" src="<%= context %>/common/jquery.inputmask.numeric.extensions.js"></script><!-- 추가됏음. -->
<script type="text/javascript" src="<%= context %>/common/jquery.inputmask.regex.extensions.js"></script><!-- 추가됏음. -->
<script type="text/javascript" src="<%= context %>/common/jquery.rowspanizer.js"></script><!-- 추가됏음. -->
<script type="text/javascript" src="<%= context %>/common/jquery.bxslider.js"></script><!-- 추가됏음. -->
<script type="text/javascript" src="<%= context %>/common/scroll/jquery.mCustomScrollbar.concat.min.js"></script>

<script type="text/javascript" src="<%= context %>/common/jquery.timepicker.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.download.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/tea-block.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/base64.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/utf8.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/jsbn.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/rsa.js"></script>
<script type="text/javascript" src="<%= context %>/common/cipher/helper.js"></script>
<script type="text/javascript" src="<%= context %>/common/HashMap.js"></script>
<script type="text/javascript" src="<%= context %>/common/Date.js"></script>

<script type="text/javascript" src="<%= context %>/common/zTree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/zTree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/zTree/jquery.ztree.exedit.min.js"></script>
<script type="text/javascript" src="<%= context %>/common/zTree/jquery.ztree.exhide.min.js"></script>

<!--
180411 이은규 bPopup추가
-->
<script type="text/javascript" src="<%= context %>/common/jquery.bpopup.js"></script>
<script>

	var parentView = ""; //모든 페이지에 전역으로 사용되며, 각 페이지 별로 최상위 선택자로 쓰인다.
	var srchExcEventParam="";
</script>
<!-- ol-debug는 ie에서 안나옴 -->
<script type="text/javascript" src="<%= context %>/common/ol-v4.0.1/ol-debug.js"></script>
<script type="text/javascript" src="<%= context %>/common/ol-v4.0.1/ext/settextpathstyle.js"></script>
<script type="text/javascript" src="<%= context %>/common/ol-v4.0.1/ext/cspline.js"></script>
<script type="text/javascript" src="<%= context %>/common/proj4js-2.4.3/proj4.js"></script>
<script type="text/javascript" src="<%= context %>/common/common.js"></script>
<script type="text/javascript" src="<%= context %>/common/color.js"></script>
<script type="text/javascript" src="<%= context %>/common/underscore.js"></script>
<script type="text/javascript" src="<%= context %>/common/string.js"></script>
<script type="text/javascript" src="<%= context %>/common/Date.js"></script>
<script type="text/javascript" src="<%= context %>/common/jquery.paging.js"></script>
<script type="text/javascript" src="<%= context %>/common/geomex.xeus.ol.custom.measure.js"></script>
<script type="text/javascript" src="<%= context %>/common/geomex.xeus.ol.custom.overview.js"></script>
<script type="text/javascript" src="<%= context %>/common/geomex.xeus.ol.custom.feature_drag.js"></script>
<%-- <script type="text/javascript" src="<%= context %>/common/jsmpeg.min20171213.js"></script> --%> <!-- by khkim -->
<script type="text/javascript" src="<%= context %>/common/xeus.player.2.1.0.js"></script>
<%-- <script type="text/javascript" src="<%= context %>/common/xeus.player.2.0.2.js"></script> --%>
<script type="text/javascript" src="<%= context %>/common/spin.min.js"></script><!-- 영상반출용 로딩 관련 -->
<%-- <script type="text/javascript" src="<%= context %>/common/tooltipsy.min.js"></script> --%><!-- 영상반출 툴팁용, 현재 미사용 > CCTV 타이틀 메뉴에 사요할 예정. by 이주영 -->
<script type="text/javascript" src="<%= context %>/common/tooltipsy.custom.js"></script><!-- 영상반출 툴팁용, 현재 미사용 > CCTV 타이틀 메뉴에 사요할 예정. by 이주영 -->

<script type="text/javascript" src="<%= context %>/common/highcharts/highcharts.js"></script>
<script type="text/javascript" src="<%= context %>/common/highcharts/highcharts-more.js"></script>
<script type="text/javascript" src="<%= context %>/common/highcharts/solid-gauge.js"></script>
<script type="text/javascript" src="<%= context %>/common/highcharts/series-label.js"></script>
<script type="text/javascript" src="<%= context %>/common/highcharts/exporting.js"></script>
<script type="text/javascript" src="<%= context %>/common/highcharts/themes/dark-unica.js"></script>

<script type="text/javascript" src="<%= context %>/common/nprogress/nprogress.js"></script>

<script type="text/javascript" src="<%= context %>/res/xeusConfig.js"></script>

<script type="text/javascript" src="<%= context %>/res/geomex.xeus.proj4.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.map.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.tms.daum.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.tms.naver.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.tms.emap.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.button.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.map.fav.js"></script>

<%-- <script type="text/javascript" src="<%= context %>/res/geomex.xeus.main.js"></script> --%>

<script type="text/javascript" src="<%= context %>/res/xeusGlobal.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusGlobal-CCTV.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusGlobal-NMS.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusGlobal-EVT.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusGlobal-TVIUS.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusGlobal-NDPS.js"></script>

<script type="text/javascript" src="<%= context %>/res/xeusLayerList.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusLayerTheme.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusLayer.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusLayout.js"></script>

<script type="text/javascript" src="<%= context %>/res/xeusWebSocket.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusJsonParser.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusJsonFacilityParser.js"></script>

<script type="text/javascript" src="<%= context %>/res/xeusCCTV.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusSymbol.js"></script>
<%-- <script type="text/javascript" src="<%= context %>/res/xeusCCTVNet.js"></script> --%>

<script type="text/javascript" src="<%= context %>/res/geomex.xeus.Search.js"></script>
<script type="text/javascript" src="<%= context %>/res/xeusSearch.js"></script>
<script type="text/javascript" src="<%= context %>/common/spatial.js"></script>

<script type="text/javascript" src="<%= context %>/res/geomex.xeus.mapEvent.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.map.widget.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.map.DaumRoadView.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.board.api.js"></script>




<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=eMOg6p1ED2RwjkxMuhsJ"></script>
<!-- <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=632a514d03318cde3f6d304dd343f5e9"></script> -->

<!-- 개발자용 다음 카카오 javascript key -->
<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=f82d0170a2da4ad990e69d935de462ab"></script>
<!-- 실서버 다음 카카오 javascript key(20220823 업데이트) -->
<!-- <script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=16871f914fced527390f6f295cbb16d5"></script> -->


<script type="text/javascript">

</script>
<!-- <script type="text/javascript" src="//apis.daum.net/maps/maps3.js?apikey=08eda4238467118689dd57e62853f297&libraries=services"></script>
<script type="text/javascript" src="http://apis.daum.net/maps/maps3.js?apikey=74b2a77931b2c5716877d34a8a78add9" charset="utf-8"></script> -->

<style>
#contextMenu{
    width: 150px;
    height: 153px;
    position: absolute;
    top: 50px;
    right: 0px;
    background: #3e3f48;
    z-index: 999;
    display: none;
    border-top: 1px solid #30303A;
    border-left: 1px solid #30303A;
    border-bottom: 2px solid #30303A;
    border-right: 2px solid #30303A;
}

#contextMenu .item:first-child {
    border-top: none !important;
}
#contextMenu .item {
    width: 150px;
    height: 50px;
    cursor: pointer;
    color: #a7a7aa;
    outline: none;
    font-size: 12px;
    font-weight: bold;
    background: transparent;
    margin: 0;
    border-right: none !important;
    border-left: none !important;
    border-bottom: 1px #303035 solid;
    border-top: 1px #65656d solid;
    text-align: center;
    line-height: 50px;
    /* display: none; */
}

#contextMenu .item:hover {
    color: white;
}
</style>

<script type="text/javascript">
// 2017.11.16 by khkim , 테스트 코드 삭제
    // ready시 자동수행 설정..
var ctxPath = '<%= context %>';
var socket = null;
var evetPin = false;
var isTray = <%= isTray %>;
$(document).ready(function() {

	_common.callAjax("/user/sessionCheck.json", {}, function(json){
	    if(json.result){
	    	$("#welcomView").show();
	    	$("#welcomView").find("div").eq(0).center().slideDown(1200);
	    }
	});

	/* _common.callAjax("/map/eventView.do", {}, function(view){
		parentView = "eventView";
	    $("#layout-center").find("#eventView").html(view);
	    $(".tab").not(":eq(0)").hide();
	}, false); */

	<%-- _common.callAjax("/user/sessionCheck.json", {}, function(json){
	    if(!json.result){
	        alert("세션이 존재하지 않습니다.\n로그인 페이지로 이동합니다.");
	        location.href = "<%= context %>";
	    }
	}); --%>

	_common.setCode();
	xeusLayout.setMenuEvent();
	/* _common.setCode(function(){
	    xeusLayout.initLayout('xeus');
	    xeusLayout.initMap('xeus-map-content', '.tab[target=eventView]');

	    $("#overlay-west-side-bar").find(".menu-button").each(function(){
	        var icon = $(this).attr("icon");
	        if(icon != null && icon != ""){
	            var $img = $("<img src='../res/img/menu/" + icon + ".png'>").css("margin-top", "-10px");
	            $(this).prepend("<br>");
	            $(this).prepend($img);
	        }
	    });

	}); */

	<%-- $(document).on("click", "#btn-logout", function(){.find("div").eq(0).center().slideDown(1200);
		if(confirm.toString().search("native code") > 0){
	    	if(confirm("로그아웃 하시겠습니까?")){
	            location.href = "<%= context %>"+"/user/signOut.do";
	    	}
		}else{
			confirm("로그아웃 하시겠습니까?", function(){
				location.href = "<%= context %>"+"/user/signOut.do";
				});
		}
	}); --%>
	BoardAPI.getAPI();

	var _html = '';
	_html += '<div id="contextMenu">';
	_html += '	<div class="item" id="editPw">비밀번호수정</div>';
	_html += '	<div class="item" id="editInfo">정보수정</div>';
	_html += '	<div class="item" id="logout">로그아웃</div>';
	_html += '</div>';

	$('#main-menu-group').append(_html);
	$("#btn-logout").mouseover(function(){
		$('#contextMenu').show();
	}).mouseout(function(){
		$('#contextMenu').hide();
	});

	/* 수정 팝업 */
	$("#noticeWrap").find(".notcTitle").click(function(){
		var mgrSeq = $(this).attr("k");

		_common.callAjax("/notice/getItem.json", {"mgrSeq" : mgrSeq}, function(json){
			if(json.result != null){
			/* 	$("#" + parentView).find("#formTr, #downTr").removeClass("hidden");
				$("#" + parentView).find("#formTr").addClass("hidden"); */
				for(var key in json.result){
					$("#" + parentView).find("#edit_pop_wrap").find("#" + key).val(json.result[key]);
					if(key == "atchFileNm") $("#" + parentView).find("#fileDown").val(json.result[key]);
				}
				$("#" + parentView).find("#edit_pop_wrap").bPopup({appendTo: $('#'+parentView)});
				$("#" + parentView).find("#edit_pop_wrap").find("#saveBtn").attr("mode", "edit");
				$("#" + parentView).find("#edit_pop_wrap").find("#delBtn").show();

				/*if($("#fileDown").val() == ""){
					$("#formTr").removeClass("hidden");
				}*/
			}
		}, false);
	});


	 $("#editPw").click(function(){
	 	$('#editPwPop').bPopup({
				appendTo: $('#main-menu-group'),
				onOpen: function() {
					_common.callAjax("/user/alterPw.do", null, function(view) {
						$('#editPwPop').html('');
						$('#editPwPop').html(view);
						$('#editPwPop').find('#edit_pw').show();
						$('#editPwPop').bPopup().reposition();

						$('#editPwPop').find('#closePwPop').click(function(){
							$('#editPwPop').bPopup().close();
						});
				    });
				},
				onClose: function() {
					$('#main-menu-group').find('#edit_pw').remove();
				}
			});
	 });

	$("#editInfo").click(function(){
		$('#editInfoPop').bPopup({
			appendTo: $('#main-menu-group'),
			onOpen: function() {
				_common.callAjax("/user/alter.do", null, function(view) {
					$('#editInfoPop').html('');
					$('#editInfoPop').html(view);
					$('#editInfoPop').find('#user_edit_pop_wrap').show();
					$('#editInfoPop').bPopup().reposition();

					$('#editInfoPop').find('#closeEditPop').click(function(){
						$('#editInfoPop').bPopup().close();
					});
			    });
			},
			onClose: function() {
				$('#main-menu-group').find('#user_edit_pop_wrap').remove();
			}
		});
	});
	$("#logout").click(function(){
		if(confirm.toString().search("native code") > 0){
	    	if(confirm("로그아웃 하시겠습니까?")){
	    		location.href = "<%= context %>"+"/user/signOut.do";
	    	}
		}else{
			confirm("로그아웃 하시겠습니까?", function(){
				location.href = "<%= context %>"+"/user/signOut.do";
				});
		}
	});

	$("#contextMenu").mouseover(function(){
		$(this).show();
	}).mouseout(function(){
		$(this).hide();
	});

	$(document).on('click', '#west-slide-btn', function() {
		if ($("#" + parentView).find("#center-overlay-west").attr('xeus-show') == 'true') {
			xeusLayout.hideOverlayWestPane(ANI_DELAY, function() {
				//console.log("show....'left pane.....");
			});
		} else {
			xeusLayout.showOverlayWestPane(ANI_DELAY, function() {
				//console.log("show....'left pane.....");
			});
		}
	});

	//$('#east-slide-btn').on('click', function() {
	$(document).on('click', '#east-slide-btn', function() {

		if ($("#" + parentView).find("#center-overlay-east").attr('xeus-show') == 'true') {
			xeusLayout.hideOverlayEastPane(ANI_DELAY, function() {
				//console.log("show....'right pane.....");
			});
		} else {
			xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
				//console.log("show....'right pane.....");
			});
		}
	});

	function subBtnEvent($this) {
		$("#tabs .tab").each(function(){
			$(this).css("background", "#30303a").removeAttr("active");

	    	var src = $(this).find(".tabRight").attr("src");
	    	$(this).find(".tabRight").attr("src", src.replace("over", "normal")).css("z-index", 0);
		});

		var src = $this.find(".tabRight").attr("src");
		$this.css("background", "#4582ac").attr("active", "active");
		$this.find(".tabRight").attr("src", src.replace("normal", "over")).css("z-index", 1);

		var targetView = $this.attr("target");
		xeusLayout.mapService = $this.data('map');
		parentView = targetView;
		$("#layout-center").find(".viewWrap").hide();
		$("#layout-center").find("#" + targetView).show();
		if(xeusLayout.mapService) xeusLayout.mapService.getMap().updateSize();

		//맵 별 cctv 객체 사용
		xeusCCTV.cctv = $this.data('cctv');
	}

	$(document).on("click", "#tabs .tab", function(){
		var $this = $(this);

		if ( geomex.xeus.DaumRoadView.isAlive() ) {
			xeusLayout.showYesNoDialog("다음 로드뷰 종료",
					"화면 전환 시 다음 로드뷰 기능은 종료됩니다.<br>다음 로드뷰 기능을 종료하시겠습니까?",
					function() {
						geomex.xeus.DaumRoadView.destroyRoadView();
						subBtnEvent($this);
			});
		} else {
			subBtnEvent($this);
		}

	});

	$(document).on("click", "#tabs .close", function(){
		var targetView = $(this).parent().attr("target");
		$("#" + targetView).html("");
		$(this).parent().removeData();
		$(this).parent().hide();

		var $targetObj = $('.menu-button[target='+targetView+']');
		$targetObj.removeClass('active');
		var src = $targetObj.attr("src");

		$targetObj.attr("src", src.replace("over", "normal")).css("z-index", 1);

		if(targetView == "tviusMngView") Public.TVIUS.Init.Clear();

		setTimeout(function(){
			var visibleLength = 0;
			$("#tabs").find(".tab").each(function(){
				if($(this).is(":visible")){
					$(this).click();
					visibleLength++;
					return false;
				}
			});

			if(visibleLength == 0) {
				$("#welcomView").show();
				$("#welcomView").find("div").eq(0).center().slideDown(1200);
			}
		}, 100);
	});

	$('#main-menu-group').find(".menu-button").each(function(){
		var key = $(this).attr('id');
		var $obj = $(this);
		_common.callAjax("/auth/hasAuth.json", { "authData" : key }, function(json){
				if( !json.result ) {
					$obj.hide();
				}
		}, false);
		//$('.viewWrap').hide();
		//$('#'+$(this).attr("target")).show();
	});

	$('#main-menu-group').find(".menu-button, #btn-logout").hover(function(){
		var src = $(this).attr("src");
		$(this).attr("src", src.replace("normal", "over"));
	}, function(){
		var src = $(this).attr("src");
		if ( !$(this).hasClass('active') ) {
			$(this).attr("src", src.replace("over", "normal"));
		}
	});

	});

	window.onload = function(){
	setTimeout(function(){
		if(isTray) $("#btn-cctv-view").click();
	    /* xeusLayout.reLayout();
	    xeusLayout.mapService.getMap().addControl(new ol.control.ZoomSlider()); */
	}, 1000);

	/* 좌측 패널 포지션 변경을 감지하고 관심영역도 이동합니다. */
	/* $('#'+parentView).find("#center-overlay-west-tab").onPositionChanged(function(){
	    var position = $('#'+parentView).find("#center-overlay-west-tab").position();
	    $('#'+parentView).find("#fav-wrap").css("left", position.left + 50);
	}); */

	/* 180511 이은규
	영상반출 관리 화면 접근 시 시스템 로고에 이벤트를 걸기 위한 속성 부여
	초기 화면 로드 시 기본적으로 CCTV모니터링 버튼을 활성화한다.*/
	//$('#btn-cctv-view').attr("active", "active");

	//2017.11.17 by khkim 주석삭제  /* 검색 뷰 요청 이벤트입니다. */
	/* 2017-12-22 이주영 - 문서전체 로드 후 뷰 요청하도록 변경 */
	/* xeusSearch.searchMenuEvent();
	_common.callAjax("/search/getSearchView.do", null, function(view) {
	     $('#'+parentView).find("#search-parent").html(view);

	     $('#'+parentView).find("#scale-wrap").fadeIn(100);
	     $("#search-parent").fadeIn(100);
	     $("#jibunUI").show(0);
	     xeusLayout.reLayout();
	}); */

}
</script>
</head>
<body>
	<div id="loadingWrap"></div>
	<div id="tabs">
		<button class="tab blueBtn hidden" target="eventView"><span class="title">이벤트모니터링GIT</span><span class="close"></span><img class="tabRight" src="../res/img/tab/tab_over_right.png"></button>
		<!-- <button class="tab blueBtn hidden" target="bigdataView"><span class="title">빅데이터 분석</span><span class="close"></span><img class="tabRight" src="../res/img/tab/tab_over_right.png"></button> -->
		<button class="tab blueBtn hidden" target="ndmsView"><span class="title">NDMS</span><span class="close"></span><img class="tabRight" src="../res/img/tab/tab_over_right.png"></button>
		<button class="tab blueBtn hidden" target="ndpsView"><span class="title">재난시설물</span><span class="close"></span><img class="tabRight" src="../res/img/tab/tab_over_right.png"></button>
		<button class="tab blueBtn hidden" target="sendView"><span class="title">상황전파</span><span class="close"></span><img class="tabRight" src="../res/img/tab/tab_over_right.png"></button>
		<button class="tab blueBtn hidden" target="statView"><span class="title">통계</span><span class="close"></span><img class="tabRight" src="../res/img/tab/tab_over_right.png"></button>
		<!-- <button class="tab blueBtn hidden" target="boardView"><span class="title">대시보드</span><span class="close"></span><img class="tabRight" src="../res/img/tab/tab_over_right.png"></button>
		 --><button class="tab blueBtn hidden" target="nmsView"><span class="title">장비관리</span><span class="close"></span><img class="tabRight" src="../res/img/tab/tab_over_right.png"></button>
		<button class="tab blueBtn hidden" target="systemView"><span class="title">시스템관리</span><span class="close"></span><img class="tabRight" src="../res/img/tab/tab_over_right.png"></button>
	</div>
    <!-- 문서 전체를 감싸는 최상위 div -->
    <div id="layout-body">
        <!-- 상단(North) 패널을 감싸는 div -->
        <div id="layout-north">
            <div id="north-menu">

                <!-- 왼쪽슬라이드 접기/보이기 -->
                <div id="west-slide-group" class="west-menu-group">
                    <button id="west-slide-btn">
                        <img height="40px" width="20px" src="<%= context %>/res/img/right_double_angle.png">
                    </button>
                </div>

                <!-- 시스템 로고 -->
                <div id="logo-group" class="left-menu-group">
                    <button id="system-logo">
                        <img src="<%= context %>/res/img/map_top_ci.png">
                    </button>
                </div>

                <!--  메뉴 목록... -->
                <div id="main-menu-group" class="left-menu-group">
<%
	String hidden = "";
	if(userId == null || "".equals(userId)) hidden = "hidden";
	%>
					<img src="../res/img/menu_top/icon1_normal.png" id="btn-cctv-view" target="eventView" class="menu-button <%= hidden %>" title="이벤트 모니터링"/>
					<img src="../res/img/menu_top/icon11_normal.png" id="btn-ndms-view" target="ndmsView" class="menu-button <%= hidden %>" title="NDMS"/>
					<img src="../res/img/menu_top/icon12_normal.png" id="btn-ndps-view" target="ndpsView" class="menu-button <%= hidden %>" title="재난 시설물"/>
					<img src="../res/img/menu_top/icon10_normal.png" id="btn-send-view" target="sendView" class="menu-button <%= hidden %>" title="상황전파"/>
					<img src="../res/img/menu_top/icon5_normal.png" id="btn-stat-mng" target="statView" class="menu-button <%= hidden %>" title="통계"/>
					<img src="../res/img/menu_top/icon4_normal.png" id="btn-nms-mng" target="nmsView" class="menu-button <%= hidden %>" title="장비관리"/>
					<img src="../res/img/menu_top/icon6_normal.png" id="btn-dashboard-view" target="dashboardView" class="menu-button <%= hidden %>" title="대시보드"/>
					<%-- <img src="../res/img/menu_top/icon6_normal.png" id="btn-boad-view" target="boardView" class="menu-button <%= hidden %>" title="현황판"/>
					 --%><img src="../res/img/menu_top/icon7_normal.png" id="btn-sys-mng" target="systemView" class="menu-button <%= hidden %>" title="시스템관리"/>

					<img src="../res/img/menu_top/icon8_normal.png" id="btn-logout" class="<%= hidden %>"/>
                </div>

                <%-- <button id="btn-logout" class="<%= hidden %>">로그아웃</button> --%>
                <!-- 오른쪽 슬라이드 접기/보이기 -->
                <div id="east-slide-group" class="east-menu-group">
                    <button id="east-slide-btn">
                        <img height="40px" width="20px" src="<%= context %>/res/img/left_double_angle.png">
                    </button>
                </div>
            </div>
        </div>
        <!-- 지도와 상단 메뉴를 감싸는 Center div -->
        <div id="layout-center" style="background: url(../res/img/bg.png) no-repeat; background-size: 100% 100%;">
<% if(userId == null || "".equals(userId)){ %>
        	<div class="viewWrap" id="loginView"><iframe src="../user/login.do"></iframe></div>
<% } %>
        	<div class="viewWrap <%= hidden %>" id="welcomView" style="background: url(../res/img/bg.png) no-repeat; background-size: 100% 100%;/* background-repeat: round; */ width: 100%; height: 100%;">
        		<div align="center" style="position: absolute; width: 100%; display: none;">
        			<div style="color: white;font-weight: bold;font-size: 40px;/* text-shadow: 5px 5px 10px dimgrey; */">안녕하세요.</div>
        			<div style="color: white;font-weight: bold;font-size: 16px;/* text-shadow: 3px 3px 10px dimgrey; */margin-top: 25px;">우측 상단의 대 메뉴를 눌러 업무를 시작하실 수 있습니다.</div>
        		</div>
        	</div>
        	<div class="viewWrap" id="eventView"></div>
        	<div class="viewWrap" id="tviusMngView"></div>
        	<div class="viewWrap" id="statView"></div>
        	<div class="viewWrap" id="boardView"></div>
        	<div class="viewWrap" id="nmsView"></div>
        	<div class="viewWrap" id="systemView"></div>
        	<div class="viewWrap" id="ndmsView"></div>
        	<div class="viewWrap" id="ndpsView"></div>
        	<div class="viewWrap" id="sendView"></div>
        </div>

    </div>

    <div id="editInfoPop">
    </diV>

    <div id="editPwPop">
    </diV>

</body>
</html>