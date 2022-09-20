<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.eventmonitor.service.FavCctvVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<FavCctvVo> list = (ArrayList<FavCctvVo>) request.getAttribute("result");
%>
<script>
$("#overlay-east-bar").find("#listToggle").width(70).click(function(){
	$("#overlay-east-contents").find("#listWrapper").toggle();
	if($("#overlay-east-contents").find("#listWrapper").is(":visible")){
		//$(this).text("목록 닫기");
		$(this).find("img").removeClass("open").addClass("close");
	}else{
		//$(this).text("목록 열기");
		$(this).find("img").removeClass("close").addClass("open");
	}
});

/* 신규추가버튼 이벤트 입니다. */
$("#regTable").find("#addBtn").click(function(){

	/*
	180104 이은규
	CCTV가 없으면 신규추가되면 안됨
	하단 추가 후 목록 추가될 때 이전형식으로 버튼이름 추가되던 것 수정
	*/
	if(xeusCCTV.VIDEO_GRID.getData() == "" ){
		alert('재생되고있는 CCTV가 없습니다.');

		return false;
	}

	var param = {
		"titleNm" : $("#regTable").find(".titleNm").val(),
		"jsonTxt" : JSON.stringify(xeusCCTV.VIDEO_GRID.getData()),
		"colNum" : xeusCCTV.VIDEO_GRID.cols,
		"chgDat" : new Date().getYMDHMS()
	}
	_common.callAjax("/cctv/addFavCctv.json", param, function(json){
		if(json.result){
			alert("저장되었습니다.");
			$("#regTable").find(".titleNm").val("");

			var $tr = $('<tr></tr>');
			$tr.append('<td><input type="text" class="sendData wide titleNm" value="' + param.titleNm + '"></td>');

			var $td = $('<td class="tCenter" width="150px" k="' + json.mgrSeq + '"></td>');

			/* var $editBtn = $('<button class="whiteBtn editBtn">저장</button>');
			var $viewCctvBtn = $('<button class="whiteBtn viewCctvBtn">보기</button>');
			var $deleteBtn = $('<button class="whiteBtn deleteBtn">삭제</button>'); */

			var $editBtn = $('<button class="imgBtn editBtn">'+
        	                	'<img src="/xeus/res/img/cctv/ico1_normal.png" onmouseover="this.src=\'/xeus/res/img/cctv/ico1_overon.png\'" onmouseout="this.src=\'/xeus/res/img/cctv/ico1_normal.png\'">'+
        	                 '</button>');
			var $viewCctvBtn = $('<button class="imgBtn viewCctvBtn">'+
            	                	'<img src="/xeus/res/img/cctv/ico2_normal.png" onmouseover="this.src=\'/xeus/res/img/cctv/ico2_overon.png\'" onmouseout="this.src=\'/xeus/res/img/cctv/ico2_normal.png\'">'+
            	                 '</button>');
			var $deleteBtn = $( '<button class="imgBtn deleteBtn">'+
                                	'<img src="/xeus/res/img/cctv/ico3_normal.png" onmouseover="this.src=\'/xeus/res/img/cctv/ico3_overon.png\'" onmouseout="this.src=\'/xeus/res/img/cctv/ico3_normal.png\'">'+
                            	'</button>');

			$td.append($editBtn)
			   .append(" ")
			   .append($viewCctvBtn)
			   .append(" ")
			   .append($deleteBtn);

			$tr.append($td);
			$("#center-overlay-east").find("#listTable").append($tr);

			/* 삭제버튼 이벤트 입니다. */
			$deleteBtn.click(function(){
				var v = $(this).parent().attr("k");
				var $tr = $(this).parent().parent();
				confirm("삭제하시겠습니까?", function(){
					_common.callAjax("/cctv/delFavCctv.json", {k : v}, function(json){
						if(json.result){
							alert("삭제되었습니다.");
							$tr.remove();
						}
					});
				});
			});

			/* 수정버튼 이벤트 입니다. */
			$editBtn.click(function(){
				var param = {
					"mgrSeq" : $(this).parent().attr("k"),
					"titleNm" : $(this).parent().prev().find(".titleNm").val(),
					"chgDat" : new Date().getYMDHMS()
				}
				confirm("제목을 수정하시겠습니까?", function(){
					_common.callAjax("/cctv/editFavCctv.json", param, function(json){
						if(json.result) alert("수정되었습니다.");
					});
				});
			});

			/* 보기버튼 이벤트 입니다. */
			$viewCctvBtn.click(function(){
				var v = $(this).parent().attr("k");

				_common.callAjax("/cctv/getFavCctvList.json", {mgrSeq : v}, function(json){
					if(json.result != null){

						console.log(json);

						var colNum = json.colNum;
						$("#gridpane_btn_x" + colNum).click();

						var _playList = xeusCCTV.cctv.getPlayListByType('_player_grid_');
						var _keys = Object.keys(_playList);
						if(_keys.length > 0){
							for (x = 0; x < _keys.length; x++) {
								var _gid = _keys[x];
								var _player_id = _playList[_gid].id;
								$('#' + _player_id).droppable('destroy');
								xeusCCTV.cctv.removePlayList(_gid);
								xeusCCTV.VIDEO_GRID.remove_widget($('#' + _player_id));
							}
							xeusCCTV.VIDEO_GRID.remove_all_widgets();
							xeusCCTV.cctv.reload();
						}

						setTimeout(function(){
							for(var i=0; i<json.result.length; i++){
								(function(i){
									setTimeout(function(){
										var cctvParam = json.result[i].cctvJson;
										cctvParam["point"] = Spatial.convertProjection([Number(cctvParam.lng), Number(cctvParam.lat)], "EPSG:4326", "EPSG:5186");

										xeusCCTV.showVideoInGridPane(cctvParam, json.result[i].col, json.result[i].row);
									}, 800 * i);
								})(i);
							}
							xeusCCTV.cctv.reload();
						}, 500);
					}
				});
			});
		}
	});
});

/* 보기버튼 이벤트 입니다. */
$("#center-overlay-east").find("#listTable").find(".viewCctvBtn").click(function(){
	var v = $(this).parent().attr("k");
	_common.callAjax("/cctv/getFavCctvList.json", {mgrSeq : v}, function(json){
		if(json.result != null){
			var colNum = json.colNum;
			$("#gridpane_btn_x" + colNum).click();

			var _playList = xeusCCTV.cctv.getPlayListByType('_player_grid_');
			var _keys = Object.keys(_playList);
			if(_keys.length > 0){
				for (x = 0; x < _keys.length; x++) {
					var _gid = _keys[x];
					var _player_id = _playList[_gid].id;
					$('#' + _player_id).droppable('destroy');
					xeusCCTV.cctv.removePlayList(_gid);
					xeusCCTV.VIDEO_GRID.remove_widget($('#' + _player_id));
				}
				xeusCCTV.VIDEO_GRID.remove_all_widgets();
				xeusCCTV.cctv.reload();
			}

			setTimeout(function(){
				for(var i=0; i<json.result.length; i++){
					(function(i){
						setTimeout(function(){
							var cctvParam = json.result[i].cctvJson;
							cctvParam["point"] = Spatial.convertProjection([Number(cctvParam.lng), Number(cctvParam.lat)], "EPSG:4326", "EPSG:5186");

							xeusCCTV.showVideoInGridPane(cctvParam, json.result[i].col, json.result[i].row);
						}, 800 * i);
					})(i);
				}
				xeusCCTV.cctv.reload();
			}, 500);
		}
	});
});

/* 삭제버튼 이벤트 입니다. */
$("#center-overlay-east").find("#listTable").find(".deleteBtn").click(function(){
	var v = $(this).parent().attr("k");
	var $tr = $(this).parent().parent();
	confirm("삭제하시겠습니까?", function(){
		_common.callAjax("/cctv/delFavCctv.json", {k : v}, function(json){
			if(json.result){
				alert("삭제되었습니다.");
				$tr.remove();
			}
		});
	});
});

/* 저장버튼 이벤트 입니다. */
$("#center-overlay-east").find("#listTable").find(".editBtn").click(function(){
	var param = {
		"mgrSeq" : $(this).parent().attr("k"),
		"jsonTxt" : JSON.stringify(xeusCCTV.VIDEO_GRID.getData()),
		"titleNm" : $(this).parent().prev().find(".titleNm").val(),
		"chgDat" : new Date().getYMDHMS()
	}
	confirm("수정하시겠습니까?", function(){
		_common.callAjax("/cctv/editFavCctv.json", param, function(json){
			if(json.result) alert("수정되었습니다.");
		});
	});
});

/* input focus & blur 이벤트 입니다. */
$("#listWrapper").find("input[type=text]").on("focus", function(){
	$(this).css("color", "black");
	$(this).parent().css("background", "white");
});
$("#listWrapper").find("input[type=text]").on("blur", function(){
	$(this).css("color", "white");
	$(this).parent().css("background", "#333");
});
</script>
<div id="overlay-east-bar" class="overlay-bar">
	<b class="overlay-bar-title">CCTV 영상 보기</b>
	<button type="button" id="gridpane_btn_close">
		<img src="../res/img/close_btn.png">
	</button>
    <!-- <button class="blueBtn" id="listToggle" style="margin: 0px; height: 30px;">목록 닫기</button> -->
    <button id="listToggle" class="listBtn">
        <img class="close" height="15px" width="15px" src="/xeus/res/img/right_double_angle.png">
        <!-- <img src="../res/img/close_btn.png"> -->
    </button>
	<button class="overlay-bar-button" id="gridpane_btn_x2">x2</button>
	<button class="overlay-bar-button" id="gridpane_btn_x3">x3</button>
	<button class="overlay-bar-button" id="gridpane_btn_x4">x4</button>

</div>
<div id="overlay-east-contents">
	<div id="listWrapper">
<% if(list.size() > 0){ %>
		<table class="list" cellspacing="0">
			<thead>
				<tr>
					<th>제목</th>
					<th width="167px">관리</th>
				</tr>
			</thead>
		</table>
		<div class="overflow" style="max-height: 100px;">
			<table id="listTable" class="list" cellspacing="0">
<%	for(int i=0; i<list.size(); i++){ %>
				<tr>
					<td>
						<input type="text" class="sendData wide titleNm" value="<%= list.get(i).getTitleNm() %>">
					</td>
					<td class="tCenter" width="150px" k="<%= list.get(i).getMgrSeq() %>">
						<!-- <button class="whiteBtn editBtn">저장</button>
						<button class="whiteBtn viewCctvBtn">보기</button>
						<button class="whiteBtn deleteBtn">삭제</button> -->
                        <button class="imgBtn editBtn">
                            <img src="/xeus/res/img/cctv/ico1_normal.png" onmouseover="this.src='/xeus/res/img/cctv/ico1_overon.png'" onmouseout="this.src='/xeus/res/img/cctv/ico1_normal.png'">
                        </button>
                        <button class="imgBtn viewCctvBtn">
                            <img src="/xeus/res/img/cctv/ico2_normal.png" onmouseover="this.src='/xeus/res/img/cctv/ico2_overon.png'" onmouseout="this.src='/xeus/res/img/cctv/ico2_normal.png'">
                        </button>
                        <button class="imgBtn deleteBtn">
                            <img src="/xeus/res/img/cctv/ico3_normal.png" onmouseover="this.src='/xeus/res/img/cctv/ico3_overon.png'" onmouseout="this.src='/xeus/res/img/cctv/ico3_normal.png'">
                        </button>
					</td>
				</tr>
<% 	} %>
			</table>
		</div>
<% } %>
		<table id="regTable" class="list" cellspacing="0">
			<tr>
				<th>신규</th>
				<td>
					<input type="text" class="wide titleNm">
				</td>
				<td class="tCenter" width="167px">
					<button class="whiteBtn middle" id="addBtn">+ &nbsp;신규 추가</button>
				</td>
			</tr>
		</table>
	</div>
	<div class="gridster">
		<ul id="video_player_grid"></ul>
	</div>
</div>