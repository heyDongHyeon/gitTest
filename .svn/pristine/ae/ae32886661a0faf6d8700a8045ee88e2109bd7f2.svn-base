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
        Public.NMS.Cable.DelStart();
    });

    $("#" + parentView).find(".searchWrapper").find("#delBtn").click(function(){
        if($("#" + parentView).find("#cableList").find("option").length == 0){
            alert("케이블을 추가해주세요.");
            return false;
        }

        confirm("선택하신 케이블을 삭제 하시겠습니까?", function(){
        	var param = {};
            $("#" + parentView).find("#cableList").find("option").each(function(i){
		        var gid = $(this).attr("gid");
                param["cableList[" + i + "].gid"] = gid;
            });

            _common.callAjax("/netwk/delMultipleCable.json", param, function(json){
                if(json.result){
                    alert("삭제되었습니다.");
                    Public.StopEvent();
                    LayerConst.ThemeLoad("asset_fnms");
                    XeusLayer.createLegend("#legendWrap");
                    Layers["asset_fnms"].reload();
                }
            }, false);
        });
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
        <button class="tab" url="/nms/getCableView.do">케이블 수정</button><button class="tab" url="/nms/getCableRegView.do">케이블 등록</button><button class="tab" url="/nms/getCableDelView.do" active="active">케이블 삭제</button>
    </p>
    <table id="searchTable">
        <tr>
            <th>선택 케이블 목록</th>
        </tr>
        <tr>
            <td id="cableListWrapper">
                <select id="cableList" name="cableList" multiple="multiple">
                    <optgroup class="tCenter" label="등록된 케이블은 더블클릭하여 삭제할 수 있습니다."></optgroup>
                </select>
            </td>
        </tr>
        <tr>
            <th id="drawCncl" class="hidden pointer">선택을 종료하시려면 여기를 눌러주세요.</th>
        </tr>
        <tr>
            <td class="tCenter noneBack">
                <button class="blackBtn" style="margin: 3px 0px 3px 0px;" id="selectBtn">케이블 선택</button>
                <button class="blackBtn" style="margin: 3px 0px 3px 1px;" id="delBtn">선택 케이블 삭제</button>
            </td>
        </tr>
    </table>
    <p class="searchTitle">범례</p>
    <div id="legendWrap"></div>

</div>