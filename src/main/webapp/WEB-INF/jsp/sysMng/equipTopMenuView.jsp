<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.util.code.DateUtil"%>
<%@ page import="geomex.xeus.util.code.StrUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ include file="../common.jsp" %>
<%

HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map");
String gbn = map.get("gbn");
%>
<style>
   #wrap button.topMenuTab[active=active] {
	    background: #4582ac;
	}

	#wrap button.topMenuTab {
	    background: #555;
	    border: none;
	    border-bottom: none;
	    padding: 10px 18px;
	    font-size: 13px;
	    cursor: pointer;
	    outline: none;
	    color:#f6f6f6;
	}
</style>
<script type="text/javascript">
var gbn = '<%=gbn%>';
$("#top_menu").find("button.topMenuTab").each(function(){
	$(this).removeAttr("active");
	if($(this).attr("gbn") == gbn) $(this).attr("active", "active");
});


/* 탭 클릭 이벤트 입니다. */
$("#top_menu").find("button.topMenuTab").click(function(){
	var _url = $(this).attr("url");
	var gbn = $(this).attr("gbn");
	if(_url != null){// && _mode != null && _supPath != null
		var _param = {};
		_param['limit'] = 10;
		_param['offset'] = 0;
		_param['gbn'] = gbn;
		_common.callAjax(_url, _param, function(view) {
			$("#"+parentView).find(".bpopup").remove();
			$("#"+parentView+" #overlay-west-contents").html(view);
		});
	}
    //$(".searchWrapper").find("#searchBtn").click();
});
</script>

<div id="top_menu">
    <p class="searchTitle">
		<button class="topMenuTab" url="/vms/getVmsView.do" gbn="vms">VMS 관리</button><!-- active="active"  -->
		<button class="topMenuTab" url="/nms/getCctvModelMngView.do" gbn="model">CCTV 모델 관리</button>
	<!-- 	<button class="topMenuTab" url="/nms/getMobileManageView.do" gbn="mobile">모바일 관리</button> -->
	</p>
</div>
