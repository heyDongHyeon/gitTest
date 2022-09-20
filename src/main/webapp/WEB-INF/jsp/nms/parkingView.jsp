<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>
<script>
(function(){

	Public.NMS.Parking.Start();

})();
</script>
<style>
#stateWrap {
	position: absolute;
	width: 500px;
	height: 200px;
	z-index: 1;
	background-color: rgba(255, 255, 255, 0.5);
	top: 0;
	right: 0;
	/* border-radius: 0px 0px 100px 100px; */
	text-align: center;
	text-shadow: 2px 2px grey;
	font-weight: bold;
	font-size: 27px;
}
</style>
<div class="overflow searchWrapper" onselectstart="return false">

    <!-- <img id="img1" src="../res/img/parking.jpg" style="width: 100%; height: 100%;">
    <img id="img2" src="../res/img/parking.jpg" style="width: 100%; height: 100%; filter: grayscale(1); display: none;"> -->
    <div class="tCenter">
	    <img id="img1" src="../proxy/getShapshot.json?path=getShapshot&cctvMgrNo=CTV0000194&fileType=viewer" style="width: auto; height: 100%;">
	    <img id="img2" src="../proxy/getShapshot.json?path=getShapshot&cctvMgrNo=CTV0000194&fileType=viewer" style="width: auto; height: 100%; filter: grayscale(1); display: none;">
    </div>

    <div id="stateWrap">
    	<p id="allCnt"></p>
    	<p id="nowCnt"></p>
    	<p id="marginCnt"></p>
    </div>

</div>