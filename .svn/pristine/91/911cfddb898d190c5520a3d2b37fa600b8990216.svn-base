<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.map.service.EmdVo"%>
<%@ page import="geomex.xeus.map.service.LiVo"%>
<%@ page import="geomex.xeus.equipmgr.service.SiteVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="java.util.ArrayList"%>
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
%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.nms.infra.search.js"></script>
<script>
/* 심볼선택 ul 이벤트입니다. */
$("#selectable").selectable({
    selecting: function(event, ui){
        if($(".ui-selected, .ui-selecting").length > 1){
            $(ui.selecting).removeClass("ui-selecting");
        }
        try{
	        var img = $(ui.selecting).attr("img");
	        $("#symCd").val(img.split(".")[0]);
        }catch(e){

        }
    }
});

$("#selectable").find("li").click(function(){
    $(this).addClass("selected").siblings().removeClass("selected");
});

/* 심볼선택 오프너 이벤트 입니다. */
$(".searchWrapper").find("#symToggle").click(function(){
    $("#imgWrapper").toggle();
});

/* 파일선택 추가 버튼 이벤트 입니다. */
$(".searchWrapper").find("#uploadAddBtn").click(function(){
    var idx = $("#hiddenForm").find("input[type=file]").length;
    if(idx > 1){
        alert("사진은 최대 2장까지 업로드 할 수 있습니다.");
        return false;
    }else{
        var $file = $("#hiddenForm").find(".uploadImg").clone().val("");

        $("#hiddenForm").append($file);
    }
});

/* 등록 버튼 이벤트 입니다. */
$(".searchWrapper").find("#addBtn").click(function(){
    var param = _common.utils.collectSendData(".searchWrapper #regTable");
    param["statChkYn"] = $("#statChkYn").is(":checked") ? "Y" : "N";
    param["useYn"] = $("#useYn").is(":checked") ? "Y" : "N";

    var lng = $("#regTable #lng").val();
    var lat = $("#regTable #lat").val();
    if(!_common.utils.isNullAndEmpty(lng) && !_common.utils.isNullAndEmpty(lat)){
        var epsg = xeusLayout.mapService.getMap().getView().getProjection().getCode();
        var mainCenter = ol.proj.transform([lng, lat], 'EPSG:4326', epsg);
        param["lng"] = mainCenter[0];
        param["lat"] = mainCenter[1];
    }

    //console.log(param);

    confirm("기반시설을 추가하시겠습니까?", function(){
    	_common.callAjax("/nms/addInfra.json", param, function(json){
            if(json.result){
                var k = json.mgrNo;
                var nm = $("#hiddenForm").find(".uploadImg").val();
                if(nm != "" && !_common.utils.isNullAndEmpty(k)){
                    $("#hiddenForm").find("#k").val(k);
                    _common.formSubmit("/image/addMultiple.json", $("#hiddenForm"), function(json){
                        if(json.result){
                        	//TODO 인프라 추가 후 지도 다시그리기 되어야 함.
                            $(".searchWrapper").find("button.tab").eq(1).click();
                            xeusLayout.mapService.getLayerByName("기반시설").getSource().clear();
                        }else{
                            alert("이미지 저장을 실패하였습니다.");
                        }
                    });
                }else{
                    $(".searchWrapper").find("button.tab").eq(1).click();
                }
            }
        }, false);
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
<div class="searchWrapper" onselectstart="return false">

    <p class="searchTitle">
        <button class="tab" url="/nms/getInfraView.do">범례</button><button class="tab" url="/nms/getInfraSearchView.do">검색</button><button class="tab" active="active" url="/nms/getInfraAddView.do">신규등록</button>
    </p>

    <table id="regTable" class="searchTable">
        <tr>
            <th width="60px">관리기관</th>
            <td colspan="3">
                <select id="orgMgrNo" name="orgMgrNo" class="sendData">
<% for(int i=0; i<orgz.size(); i++){ %>
                    <option value="<%= orgz.get(i).getOrgMgrNo() %>"><%= orgz.get(i).getOrgNm() %></option>
<% } %>
                </select>
            </td>
        </tr>
        <tr>
            <th>사이트</th>
            <td colspan="3">
                <select id="siteMgrNo" name="siteMgrNo" class="sendData">
<% for(int i=0; i<site.size(); i++){ %>
                    <option value="<%= site.get(i).getMgrNo() %>"><%= site.get(i).getSiteNm() %></option>
<% } %>
                </select>
            </td>
        </tr>
        <tr>
            <th>시설구분</th>
            <td colspan="3">
                <select id="fclGbnCd" name="fclGbnCd" class="sendData">
                    <option value=""></option>
<% while(gbnItr.hasNext()){
    String str = (String) gbnItr.next(); %>
                    <option value="<%= str %>"><%= gbn.get(str) %></option>
<% } %>
                </select>
            </td>
        </tr>
        <tr>
            <th>설치일자</th>
            <td colspan="3">
                <input type="text" id="instDat" name="instDat" class="wide sendData datePicker" readonly="readonly">
            </td>
        </tr>
        <tr>
            <th>시설명</th>
            <td colspan="3">
                <input type="text" id="facilityNm" name="facilityNm" class="wide sendData">
            </td>
        </tr>
        <!-- <tr>
            <th>심볼</th>
            <td colspan="3">
                <input type="text" id="symCd" name="symCd" class="hidden sendData">
                <button class="blackBtn wide" id="symToggle">심볼 선택</button>
            </td>
        </tr> -->
        <tr class="hidden" id="imgWrapper">
            <th>심볼<br>선택</th>
            <td colspan="3">
                <ol id="selectable">
<% for(int i=0; i<symList.size(); i++){
%>
                    <li class="ui-state-default" img="<%= symList.get(i) %>">
                        <img src="../res/sym/nms/<%= symList.get(i) %>">
                    </li>
<% } %>
                </ol>
            </td>
        </tr>
        <tr>
            <th>IP</th>
            <td>
                <input type="text" id="ipAddr" name="ipAddr" class="wide sendData">
            </td>
            <th>PORT</th>
            <td>
                <input type="text" id="portNum" name="portNum" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>옵션</th>
            <td colspan="3">
                <input type="checkbox" id="statChkYn" name="statChkYn" class="sendData">상태
                <input type="checkbox" id="useYn" name="useYn" class="sendData">사용
            </td>
        </tr>
        <tr>
            <th>접속ID</th>
            <td>
                <input type="text" id="conId" name="conId" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>접속암호</th>
            <td>
                <input type="text" id="conPwd" name="conPwd" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>SNMP인증문자</th>
            <td>
                <input type="text" id="snmpStr" name="snmpStr" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>비고</th>
            <td colspan="3">
                <input type="text" id="rmark" name="rmark" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>경도</th>
            <td>
                <input type="text" id="lng" name="lng" class="wide sendData">
            </td>
            <th>위도</th>
            <td>
                <input type="text" id="lat" name="lat" class="wide sendData middle">
                <button class="blackBtn" id="mapClickBtn">지도에서 선택</button>
            </td>
        </tr>
        <tr>
            <th colspan="4" class="pointer tCenter hidden selectCancel">선택을 취소하려면 여기를 눌러주세요.</th>
        </tr>
        <tr>
            <th>위치설명</th>
            <td colspan="3">
                <input type="text" id="rmark" name="rmark" class="wide sendData">
            </td>
        </tr>
        <tr>
            <th>사진</th>
            <td colspan="2" class="tCenter">
                <form id="hiddenForm" method="POST" enctype="multipart/form-data" style="width: 150px;">
                    <input type="text" name="k" id="k" class="hidden" value="">
                    <input type="text" name="p" id="p" class="hidden" value="\nms\">
                    <input type="file" name="uploadImg" class="uploadImg" accept="image/gif, image/jpeg, image/png">
                </form>
            </td>
            <td class="tCenter">
                <button class="blackBtn" id="uploadAddBtn">파일선택 추가</button>
            </td>
        </tr>
        <!-- <tr>
            <td colspan="4" class="tCenter noneBack">
                <button class="blackBtn" style="margin: 3px 0px 3px 0px;" id="cnclBtn">취소</button>
                <button class="blackBtn" style="margin: 3px 0px 3px 1px;" id="addBtn">저장</button>
            </td>
        </tr> -->
    </table>

    <div class="btnDiv">
        <button class="blackBtn" style="margin: 3px 0px 3px 0px;" id="cnclBtn">취소</button>
        <button class="blackBtn" style="margin: 3px 0px 3px 1px;" id="addBtn">저장</button>
    </div>

</div>