<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>
<script>
$("#" + parentView).find(".searchRing").click(function(){
	var v = $(this).attr("k");
	Public.NMS.Ring.getList(v);
});

Public.NMS.Ring.getList("CCTV");
</script>
<div class="searchWrapper mCustomScrollbar" data-mcs-theme="minimal-dark">

	<p class="searchTitle">논리망 조회</p>
    <div align="center">
	    <button class="whiteBtn searchRing" k="CCTV" style="padding: 5px 30px;">CCTV</button>
	    <button class="whiteBtn searchRing" k="WIFI" style="padding: 5px 30px;">WiFi</button>
	    <button class="whiteBtn searchRing" k="LORA" style="padding: 5px 30px;">LoRa</button>
    </div>

    <div id="resultList" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark" style="max-height: 95%;">
	    <table>
	    </table>
    </div>
</div>