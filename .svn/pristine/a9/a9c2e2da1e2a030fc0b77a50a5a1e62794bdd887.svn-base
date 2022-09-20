<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ include file="../../common.jsp" %>
<%
CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

HashMap<String, String> net = cde.convertCodeGrpToAllCde("C15");
Set<String> netKey = net.keySet();
Iterator<String> netItr = netKey.iterator();

HashMap<String, String> link = cde.convertCodeGrpToAllCde("C10");
Set<String> linkKey = link.keySet();
Iterator<String> linkItr = linkKey.iterator();
%>
<script>
(function(){
	LayerConst.ThemeLoad("asset_fnms");
	XeusLayer.createLegend("#legendWrap");

	$("#" + parentView).find(".searchWrapper").find("#selectBtn").click(function(){
		Public.NMS.Cable.DrawStart();
		$("#" + parentView).find("#drawCncl").show("slow");
	});

	$("#" + parentView).find(".searchWrapper").find("#drawCncl").click(function(){
		Public.NMS.Cable.interaction.setActive(false);
		Public.NMS.Cable.snapInteraction.setActive(false);
		Public.NMS.Cable.modifyInteraction.setActive(true);
		Public.NMS.Cable.selectInteraction.setActive(true);
		$("#" + parentView).find("#drawCncl").hide("slow");
	});

	$("#" + parentView).find(".searchWrapper").find("#saveBtn").click(function(){
		if($("#" + parentView).find("#cableList").find("option").length == 0){
			alert("케이블을 추가해주세요.");
			return false;
		}

	    confirm("선택하신 케이블을 신규추가 하시겠습니까?", function(){

	    	var param = {};
	        $("#" + parentView).find("#cableList").find("option").each(function(i){
	            param["cableList[" + i + "].linkGbnCd"] = $(this).attr("link_gbn_cd");
	            param["cableList[" + i + "].lineColor"] = $(this).attr("line_color");
	            param["cableList[" + i + "].netGbnCd"] = $(this).attr("net_gbn_cd");
	            param["cableList[" + i + "].netNm"] = $(this).attr("net_nm");
	            param["cableList[" + i + "].cableTyp"] = $(this).attr("cable_typ");
	            param["cableList[" + i + "].cableDesc"] = $(this).attr("cable_desc");
	            param["cableList[" + i + "].stMgrNo"] = $(this).attr("st_mgr_no");
	            param["cableList[" + i + "].edMgrNo"] = $(this).attr("ed_mgr_no");
	            param["cableList[" + i + "].wkt"] = $(this).attr("wkt");
	        });

            _common.callAjax("/netwk/addMultipleCable.json", param, function(json){
                if(json.result){
                    alert("저장되었습니다.");
                    $("#" + parentView).find(".sendData").val("");
                    Public.StopEvent();
                    LayerConst.ThemeLoad("asset_fnms");
                    XeusLayer.createLegend("#legendWrap");
                    Layers["asset_fnms"].reload();
                }
            }, false);

	    });
	});

	$("#" + parentView).find("#stMgrNoTxt, #edMgrNoTxt").autocomplete({
		source : function( req, res ){
			_common.callAjax("/nms/getInfra.json", { "facilityNm" : req.term }, function(json){
				var length = json.result.length;
				for(var i=0; i<length; i++){
					json.result[i]["label"] = json.result[i].facilityNm;
					json.result[i]["value"] = json.result[i].facilityNm;
				}
				res(json.result);
			});
		},
		minLength: 3,
		select: function(evt, ui){
			var id = evt.target.id;
			var val = ui.item.mgrNo;

			$("#" + parentView).find("#" + id.replace("Txt", "")).val(val);
		}
	});



})();
</script>
<style>
#cableList {
	height: 300px;
	-webkit-appearance: none;
}
</style>
<div class="overflow searchWrapper mCustomScrollbar" data-mcs-theme="minimal-dark" onselectstart="return false">

    <p class="searchTitle">
        <button class="tab" url="/nms/getCableView.do">케이블 수정</button><button class="tab" url="/nms/getCableRegView.do" active="active">케이블 등록</button><button class="tab" url="/nms/getCableDelView.do">케이블 삭제</button>
    </p>
    <table id="searchTable" class="searchTable">
        <tr>
            <th width="100px">케이블이름</th>
            <td>
                <input type="text" id="netNm" name="netNm" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>시작 시설물</th>
            <td>
                <input type="text" id="stMgrNoTxt" name="stMgrNoTxt" class="wide">
                <input type="text" id="stMgrNo" name="stMgrNo" class="wide sendData hidden">
            </td>
        </tr>
        <tr>
            <th>종료 시설물</th>
            <td>
                <input type="text" id="edMgrNoTxt" name="edMgrNoTxt" class="wide">
                <input type="text" id="edMgrNo" name="edMgrNo" class="wide sendData hidden">
            </td>
        </tr>
        <tr>
            <th>망구분</th>
            <td>
                <select id="netGbnCd" name="netGbnCd" class="sendData">
                    <option value=""></option>
<% while(netItr.hasNext()){
    String str = (String) netItr.next(); %>
                    <option value="<%= str %>"><%= net.get(str) %></option>
<% } %>
                </select>
            </td>
        </tr>
        <tr>
            <th>배선방식</th>
            <td>
                <select id="linkGbnCd" name="linkGbnCd" class="sendData">
                    <option value=""></option>
<% while(linkItr.hasNext()){
    String str = (String) linkItr.next(); %>
                    <option value="<%= str %>"><%= link.get(str) %></option>
<% } %>
                </select>
            </td>
        </tr>
        <tr>
            <th>케이블종류</th>
            <td>
                <input type="text" id="cableTyp" name="cableTyp" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>케이블설명</th>
            <td>
                <input type="text" id="cableDesc" name="cableDesc" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>선색상</th>
            <td>
                <input type="color" id="lineColor" name="lineColor" class="sendData" style="height:16px">
            </td>
        </tr>
        <tr>
            <th colspan="2">신규 케이블 목록</th>
        </tr>
        <tr>
            <td colspan="2" id="cableListWrapper">
            	<!-- <div class="tCenter">참고) 등록된 케이블은 더블클릭하여 삭제할 수 있습니다.</div> -->
                <select id="cableList" name="cableList" multiple="multiple">
                    <optgroup class="tCenter" label="등록된 케이블은 더블클릭하여 삭제할 수 있습니다."></optgroup>
                </select>
            </td>
        </tr>
        <tr>
            <th colspan="2" id="drawCncl" class="hidden pointer">그리기를 종료하시려면 여기를 눌러주세요.</th>
        </tr>
        <tr>
            <td colspan="2" class="tCenter noneBack">
                <button class="blackBtn" style="margin: 3px 0px 3px 0px;" id="selectBtn">케이블 그리기</button>
                <button class="blackBtn" style="margin: 3px 0px 3px 1px;" id="saveBtn">내용저장</button>
            </td>
        </tr>
    </table>
    <p class="searchTitle">범례</p>
    <div id="legendWrap"></div>

</div>