<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvModelVo"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.Set"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<ColumnVo> column = (ArrayList<ColumnVo>) request.getAttribute("column");

ArrayList<CctvModelVo> list = (ArrayList<CctvModelVo>)request.getAttribute("result");
HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String gbn = map.get("gbn");
String offset = map.get("offset");
String sortCol = map.get("sortCol");
String sortTyp = map.get("sortTyp");
String modelNm = map.get("modelNm");
if(modelNm == null) modelNm = "";
String makerNm = map.get("makerNm");
if(makerNm == null) makerNm = "";
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.log.css">
<%-- <script type="text/javascript" src="<%= context %>/res/geomex.xeus.system.mng.log.js"></script> --%>

<script type="text/javascript">
var gbn = '<%=gbn%>';
var schObj = new Object();
schObj.modelNm = '<%=modelNm%>';
schObj.makerNm = '<%=makerNm%>';


	$(document).ready(function(){

		/*페이징 처리*/
		$("#" + parentView).find(".paging_wrap").paging({
			current	  : 10,
			max  	  : Number($("#" + parentView).find("#max").val()),
			nowOffset : Number($("#" + parentView).find("#offset").val()),
			bindEvent : callView
		});

		_common.callAjax("/sysMng/getEquipTopMenuView.do", {'gbn': gbn}, function(view) {
			$("#" + parentView).find("#menuWrap").html('');
			$("#" + parentView).find("#menuWrap").html(view);
		});

	});

	function callView(offset,_param){
		if(offset == null) offset = 0;
		if(_param === undefined){
			_param = {};
			for(var key in schObj) {
				if (schObj[key] != ""){
					_param[key] = schObj[key];
				}
			}
		}
		_param['limit'] = 10;
		_param['offset'] = offset;
		_param['sortCol'] = 'mgr_no';
		_param['sortTyp'] = 'asc';
		_param['gbn'] = gbn;

		_common.callAjax('/nms/getCctvModelMngView.do', _param, function(view){
			$("#" + parentView).find("#overlay-west-contents").html(view);
		});
	}

	/* 검색 입력항목 엔터키 입력 이벤트 입니다.*/
	$("#" + parentView).find("#wrap").find(".sendData").keyup(function(e){
	    if(e.which == 13){
	    	$("#" + parentView).find("#wrap").find("#searchBtn").click();
	    }
	});

	/* 검색 버튼 이벤트 입니다.*/
	$("#" + parentView).find("#wrap").find("#searchBtn").click(function(){
		var _param = _common.utils.collectSendData("#search");
		callView(0, _param);
	});

	/* 신규추가 버튼 이벤트 입니다.*/
	$("#" + parentView).find("#wrap").find("#search").find('#addBtn').click(function(){
		createPopup();
		$("#" + parentView).find('#edit_pop_wrap').find('#delBtn').hide();
		$("#" + parentView).find('#edit_pop_wrap').find('#saveBtn').attr('mode', 'add');

		$("#" + parentView).find('#edit_pop_wrap').bPopup({
			appendTo: $('#'+parentView),
			onOpen: function() {
				$("#" + parentView).find("#edit_pop_wrap").find('#saveBtn').off('click');
				$("#" + parentView).find("#edit_pop_wrap").find('#delBtn').off('click');
				$("#" + parentView).find("#edit_pop_wrap").find('.bpopClose').off('click');

				/* 팝업 저장 버튼 클릭 이벤트입니다..*/
				$("#" + parentView).find("#edit_pop_wrap").find('#saveBtn').click(function(){
					var mode = $(this).attr('mode');
					console.log(mode);
					var _param = _common.utils.collectSendData("#edit_pop_wrap");
					console.log(_param);
					_common.callAjax('/nms/'+mode+'CctvModel.json', _param, function(json){
						if(json.result){
							alert('저장되었습니다.');
							$("#" + parentView).find('#edit_pop_wrap').find('.bpopClose').click();
							callView(0);
						}
					});
				});

				$("#" + parentView).find('#edit_pop_wrap').find('.bpopClose').click(function(){
					$("#" + parentView).find('#edit_pop_wrap').bPopup().close();
					$("#" + parentView).find('.bpopup').remove();
				});
			},
			onClose: function() {
				$("#" + parentView).find('.bpopup').remove();
			}
		});
	});

	/* 신규추가 버튼 이벤트 입니다.*/
	$("#" + parentView).find("#wrap").find('.mngBtn').click(function(){
		createPopup();
		var _param = {};
		_param['mgrNo'] = $(this).attr('k');
		_common.callAjax('/nms/getCctvModel.json', _param, function(json){
			if(json.result != null){
				$("#" + parentView).find('#edit_pop_wrap').find('#mgrNo').val(json.result.mgrNo.trim());
				$("#" + parentView).find('#edit_pop_wrap').find('#modelNm').val(_common.utils.validNull(json.result.modelNm));
				$("#" + parentView).find('#edit_pop_wrap').find('#makerNm').val(_common.utils.validNull(json.result.makerNm));
				$("#" + parentView).find('#edit_pop_wrap').find('#modelDesc').val(_common.utils.validNull(json.result.modelDesc));
			}
		});

		$("#" + parentView).find('#edit_pop_wrap').find('#saveBtn').attr('mode', 'edit');
		$("#" + parentView).find('#edit_pop_wrap').bPopup({
			appendTo: $('#'+parentView),
			onOpen: function() {
				$("#" + parentView).find("#edit_pop_wrap").find('#saveBtn').off('click');
				$("#" + parentView).find("#edit_pop_wrap").find('#delBtn').off('click');
				$("#" + parentView).find("#edit_pop_wrap").find('.bpopClose').off('click');

				/* 팝업 저장 버튼 클릭 이벤트입니다..*/
				$("#" + parentView).find("#edit_pop_wrap").find('#saveBtn').click(function(){
					var mode = $(this).attr('mode');
					var _param = _common.utils.collectSendData("#edit_pop_wrap");
					_common.callAjax('/nms/'+mode+'CctvModel.json', _param, function(json){
						if(json.result){
							alert('저장되었습니다.');
							$("#" + parentView).find('#edit_pop_wrap').find('.bpopClose').click();
							callView(0);
						}
					});
				});

				/* 팝업 삭제 버튼 클릭 이벤트입니다..*/
				$("#" + parentView).find("#edit_pop_wrap").find('#delBtn').click(function(){
					var mgrNo = $("#" + parentView).find("#edit_pop_wrap").find('#mgrNo').val();
					_common.callAjax('/nms/delCctvModel.json', {'mgrNo': mgrNo}, function(json){
						if(json.result){
							alert('삭제되었습니다.');
							$("#" + parentView).find('#edit_pop_wrap').find('.bpopClose').click();
							callView(0);
						}
					});
				});

				$("#" + parentView).find('#edit_pop_wrap').find('.bpopClose').click(function(){
					$("#" + parentView).find('#edit_pop_wrap').bPopup().close();
					$("#" + parentView).find('.bpopup').remove();
				});
			},
			onClose: function() {
				$("#" + parentView).find('.bpopup').remove();
			}
		});
	});

	function createPopup (){
		var str = '';
		str+= '<div class="bpopup" id="edit_pop_wrap">';
        str+= '    <div id="bpop_wrap">';
        str+= '        <h2 id="bpop_title">';
        str+= '                        CCTV 모델관리';
        str+= '        </h2>';
        str+= '        <table>';
        str+= '            <tr class="hidden">';
        str+= '                <th class="top">관리번호</th>';
        str+= '                <td>';
        str+= '                    <input type="text" class="sendData" id="mgrNo" name="mgrNo"/>';
        str+= '                </td>';
        str+= '            </tr>';
        str+= '            <tr class="top">';
        str+= '                <th class="top">모델명</th>';
        str+= '                <td>';
        str+= '                    <input type="text" class="sendData" id="modelNm" name="modelNm" placeholder="모델명"/>';
        str+= '                </td>';
        str+= '            </tr>';
        str+= '            <tr class="top">';
        str+= '                <th class="top">제조사명</th>';
        str+= '                <td>';
        str+= '                    <input type="text" class="sendData" id="makerNm" name="makerNm" placeholder="제조사명"/>';
        str+= '                </td>';
        str+= '            </tr>';
        str+= '            <tr class="top">';
        str+= '                <th class="top">상세설명</th>';
        str+= '                <td>';
        str+= '                    <input type="text" class="sendData" id="modelDesc" name="modelDesc" placeholder="상세설명"/>';
        str+= '                </td>';
        str+= '            </tr>';
        str+= '        </table>';
        str+= '        <table>';
        str+= '            <tr align="center">';
        str+= '                <td class="lastTd" colspan="2" style="border: 0 !important;">';
        str+= '                    <button id="saveBtn" tabindex="4">저장</button>';
        str+= '                    <button id="delBtn" tabindex="4">삭제</button>';
        str+= '                    <button id="closeEditPop" class="bpopClose" tabindex="5">취소</button>';
        str+= '                </td>';
        str+= '            </tr>';
        str+= '        </table>';
        str+= '    </div>';
        str+= '</div>';

        $("#" + parentView).find('#wrap').append(str);
	}

</script>
<div>

    <input type="hidden" id="offset" value="<%= offset %>" />
    <input type="hidden" id="max" value="<%= request.getAttribute("count") %>" />

    <div id="wrap">
    	<div id="menuWrap">
        </div>
        <!-- <p class="searchTitle">
            <button class="logTab" url="/log/getAssetLogView.do" excel="Asset">시설물 관리</button>
            <button class="logTab" url="/log/getMsgLogView.do" excel="Msg">SMS</button>
            <button class="logTab" url="/log/getIf112LogView.do" excel="112">112 긴급영상 지원</button>
            <button class="logTab" url="/log/getIf112JsonLogView.do" excel="112Json">112 긴급출동 메소드 호출 현황</button>
            <button class="logTab" url="/log/getIf119LogView.do" excel="119">119 긴급출동</button>
            <button class="logTab" url="/log/getIfDscLogView.do" excel="Dsc">사회적약자</button>
            <button class="logTab" url="/log/getIfEvtLogView.do" excel="Evt">이벤트로그</button>
            <button class="logTab" active="active" url="/log/getAccessView.do" excel="Access">접근이력관리</button>
        </p> -->
        <div id="search">
            <span>모델명 : </span><input id="modelNm" class="searchInput keyup sendData" type="text" value="" placeholder="모델명">
            <span>제조사명 : </span><input id="makerNm" class="searchInput keyup sendData" type="text" value="" placeholder="제조사명">
            <button id="searchBtn" class="stat_btn">검색</button>
            <button id="addBtn" class="stat_btn stat_gray_btn">신규추가</button>
            <!-- <button id="excelBtn"class="paleBtn">내보내기</button> -->
            <span id="count">총 <%= request.getAttribute("count") %>개의 건이 검색되었습니다.</span>
        </div>
        <div id="content">
           <table id="userList">
                <thead>
                    <tr>
<%
for(int i=0; i<column.size(); i++){
    if(column.get(i).getTblId().equals("xeus.asset_cctv_model")){
%>
                        <th><%= column.get(i).getColNm() %></th>
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
                    <tr>
                        <td colspan="5"><b>검색결과가 존재하지 않습니다.</b></td>
                    </tr>
<%
}else{
    for(int i=0; i<list.size(); i++){
%>
                    <tr>
                        <td><%= StrUtil.chkNull(list.get(i).getMgrNo()).trim() %></td>
                        <td><%= StrUtil.chkNull(list.get(i).getModelNm()) %></td>
                        <td><%= StrUtil.chkNull(list.get(i).getMakerNm()) %></td>
                        <td><%= StrUtil.chkNull(list.get(i).getModelDesc()) %></td>
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
</div>
<!-- </body>
</html> -->