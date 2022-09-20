<%@page import="geomex.xeus.equipmgr.service.InfraVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvModelVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ImageVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvVo"%>
<%@ page import="geomex.xeus.equipmgr.service.SiteVo"%>
<%@ page import="geomex.xeus.map.service.EmdVo"%>
<%@ page import="geomex.xeus.map.service.LiVo"%>
<%@ include file="../../common.jsp" %>
<%
CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

HashMap<String, String> gbn = cde.convertCodeGrpToAllCde("C12");
Set<String> gbnKey = gbn.keySet();
Iterator<String> gbnItr = gbnKey.iterator();

ArrayList<SiteVo> site = (ArrayList<SiteVo>) request.getAttribute("site");
ArrayList<OrganizationVo> orgz = (ArrayList<OrganizationVo>) request.getAttribute("orgz");
ArrayList<String> symList = (ArrayList<String>) request.getAttribute("sym");
ArrayList<EmdVo> emd = (ArrayList<EmdVo>) request.getAttribute("emd");
ArrayList<LiVo> li = (ArrayList<LiVo>) request.getAttribute("li");
ArrayList<ImageVo> img = (ArrayList<ImageVo>) request.getAttribute("img");

InfraVo vo = (InfraVo) request.getAttribute("vo");
if(vo == null) vo = new InfraVo();
%>
<script>
/* 드롭박스 이벤트 입니다. */
$("#" + parentView).find("#overlay-east-contents").find(".dropBox").droppable({
	accept: ".cctv-overlay-content-img",
	hoverClass: "dropBoxHover",
    deactivate: function(){
    	$("#" + parentView).find(".searchWrapper").find(".dropBox").hide("clip", 200);
    	//180528 이은규
    	//셀렉터 추가
    	$("#" + parentView).find("#overlay-east-contents").find(".dropBox").hide("clip", 200);
    },
	drop: function(event, ui){
		var data = $(ui.draggable).data();
		var type = data.mgrNo.substring(0, 3);

		ui.draggable.draggable("option", "revert", false);

		var url = "";
		if(type == "CTV") url = "/cctv/getCctvMngView.do";
		if(type == "INF") url = "/nms/getInfraMngView.do";

		_common.callAjax(url, {mgrNo : data.mgrNo}, function(view) {
			$("#" + parentView).find("#center-overlay-east").height(
				$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
			).html(view);
			$("#" + parentView).find(".btnDiv").removeClass("hidden");
		});
	}
});

/* input focus & blur 이벤트 입니다. */
$("#regTable").find("input[type=text]").on("focus", function(){
	$(this).css("color", "black");
	$(this).parent().css("background", "white");
});
$("#regTable").find("input[type=text]").on("blur", function(){
	$(this).css("color", "white");
	$(this).parent().css("background", "#333");
});

/* 심볼선택 ul 이벤트입니다. */
$("#" + parentView).find("#selectable").selectable({
    selecting: function(event, ui){
        if($("#" + parentView).find(".ui-selected, .ui-selecting").length > 1){
            $(ui.selecting).removeClass("ui-selecting");
        }
        try{
	        var img = $(ui.selecting).attr("img");
	        $("#" + parentView).find("#symCd").val(img.split(".")[0]);
        }catch(e){

        }
    }
});
$("#" + parentView).find("#selectable").find("li").click(function(){
	$(this).addClass("selected").siblings().removeClass("selected");
});

/* 우측 패덜 닫기 이벤트입니다. */
$("#" + parentView).find("#closeBtn").click(function(){
	xeusLayout.hideOverlayEastPane(500);
});

$("#" + parentView).find("#selectable").find("li").click(function(){
    $(this).addClass("selected").siblings().removeClass("selected");
});

/* 심볼선택 오프너 이벤트 입니다. */
$("#" + parentView).find("#overlay-east-contents").find("#symToggle").click(function(){
    $("#" + parentView).find("#imgWrapper").toggle();
});

/* 파일선택 추가 버튼 이벤트 입니다. */
$("#" + parentView).find("#overlay-east-contents").find("#uploadAddBtn").click(function(){
    var idx = $("#" + parentView).find("#hiddenForm").find("input[type=file]").length;
    if(idx > 1){
        alert("사진은 최대 2장까지 업로드 할 수 있습니다.");
        return false;
    }else{
        var $file = $("#" + parentView).find("#hiddenForm").find(".uploadImg").clone().val("");

        $("#" + parentView).find("#hiddenForm").append($file);
    }
});

/* 등록 버튼 이벤트 입니다. */
$("#" + parentView).find("#overlay-east-contents").find("#addBtn").click(function(){
    var param = _common.utils.collectSendData("#" + parentView + " #overlay-east-contents #regTable");
    param["statChkYn"] = $("#" + parentView).find("#statChkYn").is(":checked") ? "Y" : "N";
    param["useYn"] = $("#" + parentView).find("#useYn").is(":checked") ? "Y" : "N";
    param["ipAddr"] = param["ipAddr"].trim();

    var lng = $("#" + parentView).find("#regTable #lng").val();
    var lat = $("#" + parentView).find("#regTable #lat").val();
    if(!_common.utils.isNullAndEmpty(lng) && !_common.utils.isNullAndEmpty(lat)){
        var epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
        var mainCenter = ol.proj.transform([lng, lat], 'EPSG:4326', epsg);
        param["lng"] = mainCenter[0];
        param["lat"] = mainCenter[1];
    }

    var mode = "edit";
    if(_common.utils.isNullAndEmpty(param["mgrNo"])){
    	mode = "add";
    	delete param["mgrNo"];
    }
	//N12017-118R-13-4P-WF
    confirm("입력하신 내용을 저장하시겠습니까?", function(){
    	_common.callAjax("/nms/" + mode + "Infra.json", param, function(json){
            if(json.result){
                var k = json.mgrNo;
                var nm = $("#" + parentView).find("#hiddenForm").find(".uploadImg").val();
                if(nm != "" && !_common.utils.isNullAndEmpty(k)){
                    $("#" + parentView).find("#hiddenForm").find("#k").val(k);
                    _common.formSubmit("/image/addMultiple.json", $("#" + parentView).find("#hiddenForm"), function(json){
                        if(json.result){
                        	_common.callAjax("/nms/getInfraMngView.do", {mgrNo : k}, function(view) {
                				$("#" + parentView).find("#center-overlay-east").height(
                					$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
                				).html(view);
                				$("#" + parentView).find(".btnDiv").removeClass("hidden");
                			});
                        }else{
                            alert("이미지 저장을 실패하였습니다.");
                        }
                    });
                }

               	Layers["asset_infra"].reload();
            }
        }, false);
    });
});

/* 이미지 새탭으로 보기 이벤트 입니다. */
$("#" + parentView).find("#imgWrapper").find(".imgs").click(function(){
    var param = {"mgrSeq" : $(this).attr("k")};
    _common.postForm.open("/image/getImage.do", param);
});

/* 이미지 삭제 이벤트 입니다. */
$("#" + parentView).find(".imgBox").find(".close").click(function(){
    var $span = $(this).parent();
    var param = {"mgrSeq" : $(this).attr("k")};
    confirm("이미지를 삭제하시겠습니까?", function(){
    	_common.callAjax("/image/del.json", param, function(json){
            if(json.result) $span.remove();
        });
    });
});

/* 지도에서 위치설정 이벤트 입니다. */
$("#" + parentView).find("#overlay-east-contents").find("#mapClickBtn").click(function(){
    $("body").css("cursor", "crosshair");
    $("#" + parentView).find(".selectCancel").show(500);
    xeusLayout.mapService.getMap().on('click', Public.NMS.Infra.Start);
    Public.StopEvent = function(){
        $("body").css("cursor", "default").off("click");
        $("#" + parentView).find(".selectCancel").hide(500);
        xeusLayout.mapService.getMap().un('click', Public.NMS.Infra.Start);
    }
});
$("#" + parentView).find("#overlay-east-contents").find(".selectCancel").click(function(){
    Public.StopEvent();
});

/* 사진 추가 버튼 이벤트 입니다. */
$("#" + parentView).find("#overlay-east-contents").find("#uploadBtn").click(function(){
    $("#" + parentView).find("#hiddenForm").find(".uploadImg").click();
});

/* 상위 "사진 추가" 버튼을 통해 실제 이미지 선택시 업로드 이벤트 입니다. */
$("#" + parentView).find("#hiddenForm").find("#uploadImg").on("change", function(){
    var nm = $(this).val();
    if(nm != ""){
    	confirm("선택하신 파일을 업로드 하시겠습니까?", function(){
    		$("#" + parentView).find("#hiddenForm").find("#i").val($("#" + parentView).find(".imgBox").length + 1);
            _common.formSubmit("/image/add.json", $("#" + parentView).find("#hiddenForm"), function(json){
                if(json.result){
                    var v = "<%= vo.getMgrNo() %>";

                    _common.callAjax("/nms/getInfraMngView.do", {mgrNo : k}, function(view) {
        				$("#" + parentView).find("#center-overlay-east").height(
        					$(window).height() - $("#" + parentView).find("#layout-north").outerHeight() - $("#" + parentView).find("#overlay-east-bar").outerHeight()
        				).html(view);
        				$("#" + parentView).find(".btnDiv").removeClass("hidden");
        			});
                }
            });
    	}, function(){
    		$("#" + parentView).find("#hiddenForm").find("#uploadImg").val("");
    	});
    }
});

/* 내용 저장 버튼 이벤트 입니다. */
$("#" + parentView).find("#overlay-east-contents").find("#editBtn").click(function(){
    var param = _common.utils.collectSendData("#regTable");
    param["mgrNo"] = "<%= vo.getMgrNo() %>";
    param["statChkYn"] = $("#" + parentView).find("#statChkYn").is(":checked") ? "Y" : "N";
    param["useYn"] = $("#" + parentView).find("#useYn").is(":checked") ? "Y" : "N";

    var lng = $("#" + parentView).find("#regTable #lng").val();
    var lat = $("#" + parentView).find("#regTable #lat").val();
    if(!_common.utils.isNullAndEmpty(lng) && !_common.utils.isNullAndEmpty(lat)){
        var epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
        var mainCenter = ol.proj.transform([lng, lat], 'EPSG:4326', epsg);
        param["lng"] = mainCenter[0];
        param["lat"] = mainCenter[1];
    }

    _common.callAjax("/nms/editInfra.json", param, function(json){
        if(json.result){
            alert("저장되었습니다.");

            xeusLayout.mapService.getLayerByName("기반시설").getSource().clear();
            $("#" + parentView).find("#searchBtn").click();
        }
    });
});

$("#" + parentView).find("#overlay-east-contents").find(".selectCancel").click(function(){
    Public.StopEvent();
});

/* 심볼선택 오프너 이벤트 입니다. */
$("#" + parentView).find("#overlay-east-contents").find("#symToggle").click(function(){
	$("#" + parentView).find("#imgWrapper").toggle();
});

/* DatePicker 생성 이벤트입니다. */
$("#" + parentView).find("#overlay-east-contents").find(".datePicker").datepicker("destroy").datepicker({
    changeMonth: true,
    changeYear: true,
    dateFormat: "yymmdd",
    showButtonPanel: true,
    beforeShowDay: $.datepicker.noBefore
});

$("#" + parentView).find("#newInfra").click(function(){
	if(xeusCCTV.VIDEO_GRID != null) xeusCCTV.closeAllGridPanePlayer(0);
	//if(xeusCCTV.getGridPanePlayerCount() > 0) xeusCCTV.closeAllGridPanePlayer(0);
	_common.callAjax("/nms/getInfraAddView.do", null, function(view) {
		$("#" + parentView).find("#center-overlay-east").html(view);
		xeusLayout.EAST = CCTV_BTN_CCTV_MNG_EAST_SIZE;
		xeusLayout.showOverlayEastPane(ANI_DELAY, function() {
		});
	});
});
</script>
<style>
  #feedback { font-size: 1.4em; }
  #selectable .ui-selecting { background: #FECA40; }
  #selectable .ui-selected { background: #F39814; color: white; }
  #selectable { list-style-type: none; margin: 0; padding: 0; }
  #selectable li { margin: 3px; padding: 1px; float: left; width: 50px; height: 50px; font-size: 4em; text-align: center; }
  .ui-selectable-helper { display: none; }
</style>
<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">시설 관리</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
    <!--
    180411 이은규
    미등록리스트 없이 신규등록인 것처럼 보이기 위해 변경
     -->
	<!-- <button class="blueBtn" id="unregCctv">미등록 조회</button> -->
    <button class="blueBtn" id="newInfra" style="width: 96px;">신규 등록</button>
</div>

<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark">

	<div class="dropBox" style="font-size: 14px;">원하시는 시설물을 이곳에 드롭 해주세요!</div>

	<table id="regTable" class="list" cellspacing="0">
		<tr class="hidden">
            <th>관리번호</th>
            <td colspan="3">
                <input type="text" id="mgrNo" name="mgrNo" class="wide sendData" value="<%= StrUtil.chkNull(vo.getMgrNo()) %>">
            </td>
        </tr>
        <tr>
            <th width="60px">관리기관</th>
            <td colspan="3">
                <select id="orgMgrNo" name="orgMgrNo" class="wide sendData">
<% for(int i=0; i<orgz.size(); i++){ %>
                    <option value="<%= orgz.get(i).getOrgMgrNo() %>" <%= vo.getOrgMgrNo().equals(orgz.get(i).getOrgMgrNo()) ? "selected" : "" %>><%= orgz.get(i).getOrgNm() %></option>
<% } %>
                </select>
            </td>
        </tr>
        <%-- <tr>
            <th>사이트</th>
            <td colspan="3">
                <select id="siteMgrNo" name="siteMgrNo" class="wide sendData">
<% for(int i=0; i<site.size(); i++){ %>
                    <option value="<%= site.get(i).getMgrNo() %>"><%= site.get(i).getSiteNm() %></option>
<% } %>
                </select>
            </td>
        </tr> --%>
        <tr>
            <th>시설구분</th>
            <td colspan="3">
                <select id="fclGbnCd" name="fclGbnCd" class="wide sendData">
                    <option value=""></option>
<% while(gbnItr.hasNext()){
    String str = (String) gbnItr.next(); %>
                    <option value="<%= str %>" <%= vo.getFclGbnCd().equals(str) ? "selected" : "" %>><%= gbn.get(str) %></option>
<% } %>
                </select>
            </td>
        </tr>
        <tr>
            <th>설치일자</th>
            <td colspan="3">
                <input type="text" id="instDat" name="instDat" class="wide sendData datePicker" value="<%= StrUtil.chkNull(vo.getInstDat()) %>" readonly="readonly">
            </td>
        </tr>
        <tr>
            <th>장비명</th>
            <td colspan="3">
                <input type="text" id="facilityNm" name="facilityNm" class="wide sendData" value="<%= StrUtil.chkNull(vo.getFacilityNm()) %>">
            </td>
        </tr>
        <tr>
            <th>장비식별번호</th>
            <td colspan="3">
                <input type="text" id="facilityId" name="facilityId" class="wide sendData" value="<%= StrUtil.chkNull(vo.getFacilityId()) %>">
            </td>
        </tr>
        <!-- <tr>
            <th>심볼</th>
            <td colspan="3">
                <input type="text" id="symCd" name="symCd" class="hidden sendData">
                <button class="blackBtn wide" id="symToggle">심볼 선택</button>
            </td>
        </tr> -->
        <%-- <tr class="hidden" id="imgWrapper">
            <th>심볼<br>선택</th>
            <td colspan="3">
                <ol id="selectable">
<% for(int i=0; i<symList.size(); i++){ %>
                    <li class="ui-state-default" img="<%= symList.get(i) %>">
                        <img src="../res/sym/nms/<%= symList.get(i) %>">
                    </li>
<% } %>
                </ol>
            </td>
        </tr> --%>
        <tr>
            <th>IP</th>
            <td>
                <input type="text" id="ipAddr" name="ipAddr" class="wide sendData" value="<%= StrUtil.chkNull(vo.getIpAddr()).trim() %>">
            </td>
            <th>PORT</th>
            <td>
                <input type="text" id="portNum" name="portNum" class="wide sendData" value="<%= StrUtil.chkNull(vo.getPortNum()) %>">
            </td>
        </tr>
        <tr>
            <th>옵션</th>
            <td colspan="3">
                <input type="checkbox" id="statChkYn" name="statChkYn" class="sendData" <%= "Y".equals(vo.getStatChkYn()) ? "checked" : "" %>>상태
                <input type="checkbox" id="useYn" name="useYn" class="sendData" <%= "Y".equals(vo.getUseYn()) ? "checked" : "" %>>사용
            </td>
        </tr>
        <tr>
            <th>접속ID</th>
            <td colspan="3">
                <input type="text" id="conId" name="conId" class="wide sendData" value="<%= StrUtil.chkNull(vo.getConId()) %>">
            </td>
        </tr>
        <tr>
            <th>접속암호</th>
            <td colspan="3">
                <input type="text" id="conPwd" name="conPwd" class="wide sendData" value="<%= StrUtil.chkNull(vo.getConPwd()) %>">
            </td>
        </tr>
        <tr>
            <th>SNMP인증문자</th>
            <td colspan="3">
                <input type="text" id="snmpStr" name="snmpStr" class="wide sendData" value="<%= StrUtil.chkNull(vo.getSnmpStr()) %>">
            </td>
        </tr>
        <tr>
            <th>비고</th>
            <td colspan="3">
                <input type="text" id="rmark" name="rmark" class="wide sendData" value="<%= StrUtil.chkNull(vo.getRmark()) %>">
            </td>
        </tr>
        <tr>
            <th>경도</th>
            <td>
                <input type="text" id="lng" name="lng" class="wide sendData" value="<%= StrUtil.chkNull(vo.getLng()) %>">
            </td>
            <th>위도</th>
            <td>
                <input type="text" id="lat" name="lat" class="wide sendData" value="<%= StrUtil.chkNull(vo.getLat()) %>">
            </td>
        </tr>
        <tr>
            <th colspan="4" class="pointer tCenter hidden selectCancel">선택을 취소하려면 여기를 눌러주세요.</th>
        </tr>
        <tr>
            <th>사진</th>
            <td colspan="3" class="tCenter" id="imgWrapper">
                <form class="hidden" id="hiddenForm" method="POST" enctype="multipart/form-data" style="width: 150px;">
                    <input type="text" name="k" id="k" class="hidden" value="<%= StrUtil.chkNull(vo.getMgrNo()) %>">
                    <input type="text" name="p" id="p" class="hidden" value="\nms\">
                    <input type="file" name="uploadImg" class="uploadImg" accept="image/gif, image/jpeg, image/png">
                </form>
<% if(img == null){ %>
                <p style="padding: 150px 0px;"><b>이미지가 존재하지 않습니다.</b></p>
<% }else{ %>
    <% for(int i=0; i<img.size(); i++){ %>
                <span class="imgBox">
                    <img class="imgs" alt="<%= img.get(i).getFileNm() %>" src="../image/getImage.do?mgrSeq=<%= img.get(i).getMgrSeq() %>" k="<%= img.get(i).getMgrSeq() %>" style="max-width: 400px; max-height: 400px;">
                    <img class="close" src="../res/img/close.png" k="<%= img.get(i).getMgrSeq() %>">
                </span>
    <% } %>
<% } %>
            </td>
            <!-- <td class="tCenter">
                <button class="whiteBtn" id="uploadAddBtn">파일선택 추가</button>
            </td> -->
        </tr>
        <tr>
			<td colspan="4" class="tCenter" k="<%= vo.getMgrNo() %>">
				<!-- <button class="whiteBtn" id="uploadBtn">사진 추가</button> -->
                <button class="whiteBtn imgBtn" id="uploadBtn"><img class="close" height="20px" width="20px" src="/xeus/res/img/add.png"></button>
			</td>
		</tr>
    </table>

    <div class="btnDiv" k="<%= vo.getMgrNo() %>">
    	<button class="whiteBtn" id="mapClickBtn">지도에서 위치 선택</button>
        <button class="whiteBtn" style="margin: 3px 0px 3px 1px;" id="addBtn">저장</button>
        <button class="whiteBtn" id="delBtn">삭제</button>
    </div>
</div>
