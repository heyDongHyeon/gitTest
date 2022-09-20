<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.equipmgr.service.MobileVo"%>
<%@ include file="../common.jsp" %>
<%
CodeConvertor cde = (CodeConvertor) request.getAttribute("code");
/*
 * C02 인증상태
 * 계정상태코드와 같음
 */
HashMap<String, String> authStatCdMap = cde.convertCodeGrpToAllCde("C02");
Set<String> authStatCdKey = authStatCdMap.keySet();
Iterator<String> authStatCdItr = authStatCdKey.iterator();
/* C20 사용목적구분 */
HashMap<String, String> purpCdMap = cde.convertCodeGrpToAllCde("C20");
Set<String> purpCdKey = purpCdMap.keySet();
Iterator<String> purpCdItr = purpCdKey.iterator();

ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");

ArrayList<MobileVo> list = (ArrayList<MobileVo>)request.getAttribute("result");

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String gbn = map.get("gbn");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String userIdOrNm = StrUtil.chkNull(map.get("userIdOrNm"));
String modelNm = StrUtil.chkNull(map.get("modelNm"));
String purpCd = StrUtil.chkNull(map.get("purpCd"));
String authStatCd = StrUtil.chkNull(map.get("authStatCd"));
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.log.css">
<%-- <script type="text/javascript" src="<%= context %>/res/geomex.xeus.nms.mobile.js"></script> --%>
<script type="text/javascript">
var offset="<%= offset %>";
var gbn="<%= gbn %>";
var userIdOrNm = "<%=userIdOrNm%>";
var modelNm = "<%=modelNm%>";
var purpCd = "<%=purpCd%>";
var authStatCd = "<%=authStatCd%>";

$(document).ready(function(){
	_common.callAjax("/sysMng/getEquipTopMenuView.do", {'gbn': gbn}, function(view) {
		$("#" + parentView).find("#menuWrap").html('');
		$("#" + parentView).find("#menuWrap").html(view);
	});
	$("#" + parentView).find(".paging_wrap").paging({
		current	  : 10,
		max  	  : Number($("#" + parentView).find("#max").val()),
		nowOffset : Number($("#" + parentView).find("#offset").val()),
		bindEvent : callView
	});
});

/* 검색버튼 */
$("#" + parentView).find("#searchBtn").click(function(){
	var _param = _common.utils.collectSendData('#search');
	callView(0, _param);
});

/* 신규 팝업 */
$("#" + parentView).find("#addBtn").click(function(){
	$("#" + parentView).find("#mobile_edit_pop_wrap").bPopup({
		appendTo: $('#'+parentView),
		onOpen : function(){
			$("#" + parentView).find("#mobile_edit_pop_wrap").find('.sendData').each(function(){
				if($(this).is("input")) $(this).val('') ;
				else if($(this).is("select")) $(this).find('option:eq(0)').attr("selected", "selected");
			});
			$("#" + parentView).find("#mobile_edit_pop_wrap").find("#saveBtn").attr("mode", "add");
			$("#" + parentView).find("#mobile_edit_pop_wrap").find(".edit").addClass("hidden");


			_common.callAjax("/user/getList.json", {"discardChk": "Y"}, function(json){
				if(json.result){
					var str = '<option value="">선택하여 주십시오.</option>';
					for(var i=0; i<json.result.length; i++){
						str += '<option value="'+json.result[i].userId+'">'+json.result[i].userId+'('+json.result[i].userNm+')</option>';
					}
					$('#mobile_edit_pop_wrap').find("#userId").html('');
					$('#mobile_edit_pop_wrap').find("#userId").html(str);
				}
			}, false);
		}
	});
});

/* 정보 수정(수정 팝업창 생성)*/
$("#" + parentView).find(".mngBtn").click(function(){
	var mgrNo = $(this).attr("k");
	$("#" + parentView).find("#mobile_edit_pop_wrap").bPopup({
		appendTo: $('#'+parentView),
		onOpen : function(){
			_common.callAjax("/user/getList.json", {"discardChk": "Y"}, function(json){
				if(json.result){
					var str = '<option value="">선택하여 주십시오.</option>';
					for(var i=0; i<json.result.length; i++){
						str += '<option value="'+json.result[i].userId+'">'+json.result[i].userId+'('+json.result[i].userNm+')</option>';
					}
					$('#mobile_edit_pop_wrap').find("#userId").html('');
					$('#mobile_edit_pop_wrap').find("#userId").html(str);
				}
			}, false);
			_common.callAjax("/nms/getMobileItem.json", {"mgrNo" : mgrNo}, function(json){
				if(json.result != null){
					for(var key in json.result){
						$target = $("#" + parentView).find("#mobile_edit_pop_wrap").find("#" + key);
						if($target.is("input")) $target.val(json.result[key]) ;
						else if($target.is("select")) $target.val(json.result[key]).attr("selected", "selected");
					}
					$("#" + parentView).find("#mobile_edit_pop_wrap").find("#saveBtn").attr("mode", "edit");
					$("#" + parentView).find("#mobile_edit_pop_wrap").find(".edit").removeClass("hidden");
				}
			}, false);
		}
	});
});

/* bPopup Close */
$("#" + parentView).find(".bpopClose").click(function(){
	var chkNotice = $("#" + parentView).find('.notice').length;
	if(chkNotice == 0){
		$("#" + parentView).find(".bpopup").bPopup().close();
	}
});

/* 모바일 기기 등록 및 수정 */
$("#" + parentView).find('#mobile_edit_pop_wrap').find("#saveBtn").click(function(){
	var chkNotice = $("#" + parentView).find('.notice').length;
	if(chkNotice == 0){
		confirm("저장하시겠습니까?", function(){
			var mode = $("#" + parentView).find('#mobile_edit_pop_wrap').find("#saveBtn").attr("mode")
			var _param = _common.utils.collectSendData('#mobile_edit_pop_wrap');
			//console.log(_param);
			_common.callAjax("/nms/"+mode+"Mobile.json", _param, function(json){
				//console.log(json);
				if(json.result){
					alert('저장되었습니다.');
					$("#" + parentView).find("#mobile_edit_pop_wrap").bPopup().close();
					callView();
				}
			}, false);
		});
	}
});

function callView(offset, _param){
	if(offset == null) offset = 0;
	if(_param == null){
		var _param = {};
		if(userIdOrNm != "" && userIdOrNm != null)	_param["userIdOrNm"] = userIdOrNm;
		if(modelNm != "" && modelNm != null)		_param["modelNm"] = modelNm;
		if(purpCd != "" && purpCd != null)			_param["purpCd"] = purpCd;
		if(authStatCd != "" && authStatCd != null)	_param["authStatCd"] = authStatCd;
	}
	_param["limit"] = 10;
	_param["offset"] = offset;
	_param['gbn'] = gbn;
	/* if(_param["userIdOrNm"] == null){

	} */

	_common.callAjax("/nms/getMobileManageView.do", _param, function(view){
		$("#" + parentView).find(".bpopup").remove();
		$("#" + parentView).find("#overlay-west-contents").html(view);
	});
}

</script>
<div>

	<input type="hidden" id="offset" value="<%= offset %>" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

    <div id="wrap">
	    <div id="menuWrap">
		</div>
	    <div id="title">모바일 기기 관리</div>
	    <div id="search">
	    	<input id="userIdOrNm" class="keyup sendData" type="text" value="" placeholder="사용자 계정 또는 이름">
            <span>모델명 : </span><input id="modelNm" class="keyup sendData" type="text" value="" placeholder="모델명">
            <span>사용목적 : <select id="purpCd" class="sendData">
                <option value="">전체</option>
<%
while(purpCdItr.hasNext()){    String str = (String)purpCdItr.next();
%>
                <option value="<%= str %>"><%= purpCdMap.get(str) %></option>
<%
}
%>
            </select>
            <span>인증상태 : <select id="authStatCd" class="sendData">
                <option value="">전체</option>
<%
while(authStatCdItr.hasNext()){    String str = (String)authStatCdItr.next();
	if(!str.equals("15")){
%>
                <option value="<%= str %>"><%= authStatCdMap.get(str) %></option>
<%
	}
}
%>
            </select>
            <button id="searchBtn" class="paleBtn">검색</button>
            <button id="addBtn" class="paleBtn">신규추가</button>
            <!-- <button id="excelBtn"class="paleBtn">내보내기</button> -->
            <span id="count">총 <%= request.getAttribute("count") %>개의 건이 검색되었습니다.</span>
        </div>
        <div id="content">
			<table id="userList">
				<thead>
	            	<tr>
<%
for(int i=0; i<column.size(); i++){
    if(column.get(i).getTblId().equals("xeus.asset_mobile")){
%>
              			<th><%= column.get(i).getColNm().replace("코드", "") %></th>
<%
    }
}
%>
						<th>관리</th>
		            </tr>
		        </thead>
		        <tbody>
<%
if(list.size() == 0){
%>
		            <tr class="tCenter">
		                <td colspan="11"><p style="padding: 150px 0px;"><b>결과가 존재하지 않습니다.</b></p></td>
		            </tr>
<%
}else{
    for(int i=0; i<list.size(); i++){
%>
		            <tr class="tCenter">
		            	<td><%= list.get(i).getMgrNo() %></td>
		                <td><%= StrUtil.chkNull(list.get(i).getUserId()) %><br>(<%= StrUtil.chkNull(list.get(i).getUserNm()) %>)</td>
		                <td><%= cde.convertCodeToName("C20", list.get(i).getPurpCd()) %></td>
		                <td><%= StrUtil.chkNull(list.get(i).getModelNm()) %></td>
		                <td><%= StrUtil.chkNull(list.get(i).getDeviceId()) %></td>
		                <td><%= cde.convertCodeToName("C02", list.get(i).getAuthStatCd()) %></td>
		                <td><%= DateUtil.formatDate(list.get(i).getStatChgDat()) %></td>
						<td><%= StrUtil.chkNull(list.get(i).getAcptUsrId()) %><br><%= StrUtil.chkNull(list.get(i).getAcptUserNm()) %></td>
		                <td><%= DateUtil.formatDate(list.get(i).getLastLogDat()) %></td>
		                <td><%= StrUtil.chkNull(list.get(i).getLoginYn()) %></td>
		                <td><button class="mngBtn" k="<%= list.get(i).getMgrNo() %>"></button></td>
		            </tr>
<%
    }
}
%>
		        </tbody>
		    </table>
		</div>
		<div class="paging_wrap"></div>
	</div>


	<div class="bpopup" id="mobile_edit_pop_wrap">
	    <div id="bpop_wrap">
	        <h2 id="bpop_title">모바일 기기 관리</h2>
	        <table>
	            <tr class="top">
	                <th class="top">사용자ID</th>
	                <td>
	                    <select class="sendData" id="userId">
	                        <!-- <option value="">선택하여 주십시오.</option> -->
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <th class="top">사용목적</th>
	                <td>
	                	<select class="sendData" id="purpCd">
<%
purpCdItr = purpCdKey.iterator();
while(purpCdItr.hasNext()){    String str = (String)purpCdItr.next();
%>
                			<option value="<%= str %>"><%= purpCdMap.get(str) %></option>
<%
}
%>
						</select>
	                </td>
	            </tr>
	            <tr>
	                <th class="top">모델명</th>
	                <td>
	                    <input type="text" id="modelNm" class="sendData" placeholder="모델명"/>
	                </td>
	            </tr>
	            <tr class="edit">
	                <th class="top">인증상태</th>
	                <td>
	                	<select class="sendData" id="authStatCd">
<%
authStatCdItr = authStatCdKey.iterator();
while(authStatCdItr.hasNext()){    String str = (String)authStatCdItr.next();
%>
                			<option value="<%= str %>"><%= authStatCdMap.get(str) %></option>
<%
}
%>
						</select>
	                </td>
	            </tr>
	            <tr>
	                <th class="top">기기식별정보</th>
	                <td>
	                    <input type="text" id="deviceId" class="sendData" placeholder="디바이스 ID"/>
	                </td>
	            </tr>
	            <tr class="hidden">
	                <td colspan="2">
	                    <input type="hidden" id="mgrNo" class="sendData" value=""/>
	                    <input type="hidden" id="statChgDat" class="sendData" value=""/>
	                    <input type="hidden" id="acptUsrId" class="sendData" value=""/>
	                    <input type="hidden" id="lastLogDat" class="sendData" value=""/>
	                    <input type="hidden" id="loginYn" class="sendData" value=""/>
	                </td>
	            </tr>
	        </table>
	        <table>
	            <tr align="center">
	                <td class="lastTd" colspan="2" style="border: 0 !important;">
	                    <button id="saveBtn" tabindex="4">저장</button>
	                    <button id="closeEditPop" class="bpopClose" tabindex="5">취소</button>
	                </td>
	            </tr>
	        </table>
	    </div>
    </div>

</div>

