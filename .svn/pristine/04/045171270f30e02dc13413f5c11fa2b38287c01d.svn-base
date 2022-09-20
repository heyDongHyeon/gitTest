<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ include file="../common.jsp" %>
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
		Public.NMS.Cable.EditStart();
	});

	$("#" + parentView).find(".searchWrapper").find("#saveBtn").click(function(){
		confirm("내용을 저장하시겠습니까?", function(){
			_common.callAjax("/netwk/editCable.json", _common.utils.collectSendData("#" + parentView + " .searchWrapper #searchTable"), function(json){
                if(json.result){
                    alert("수정되었습니다.");
                    $("#" + parentView).find(".sendData").val("");
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
<div class="overflow searchWrapper mCustomScrollbar" data-mcs-theme="minimal-dark" onselectstart="return false">

    <p class="searchTitle">
        <button class="tab" url="/nms/getCableView.do" active="active">케이블 수정</button><button class="tab" url="/nms/getCableRegView.do">케이블 등록</button><button class="tab" url="/nms/getCableDelView.do">케이블 삭제</button>
    </p>
    <table id="searchTable" class="searchTable">
        <tr class="hidden">
            <th width="100px">GID</th>
            <td>
                <input type="text" id="gid" name="gid" class="sendData">
            </td>
        </tr>
        <tr class="hidden">
            <th width="100px">WKT</th>
            <td>
                <input type="text" id="wkt" name="wkt" class="sendData">
            </td>
        </tr>
        <tr>
            <th width="100px">케이블이름</th>
            <td>
                <input type="text" id="netNm" name="netNm" class="sendData">
            </td>
        </tr>
        <!-- <tr>
            <th>시작 시설물</th>
            <td>
                <input type="text" id="stMgrNo" name="stMgrNo" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>종료 시설물</th>
            <td>
                <input type="text" id="edMgrNo" name="edMgrNo" class="wide sendData">
            </td>
        </tr> -->
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
            <td colspan="2" class="tCenter noneBack">
                <button class="blackBtn" style="margin: 3px 0px 3px 0px;" id="selectBtn">케이블 선택</button>
                <button class="blackBtn" style="margin: 3px 0px 3px 1px;" id="saveBtn">내용저장</button>
            </td>
        </tr>
    </table>
    <p class="searchTitle">범례</p>
    <div id="legendWrap"></div>

</div>