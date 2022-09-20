<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.AuthGrpVo"%>
<%@ page import="geomex.xeus.sysmgr.service.AuthVo"%>
<%@ page import="geomex.xeus.map.service.MapVo"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="../common.jsp" %>
<%
ArrayList<MapVo> favList = (ArrayList<MapVo>) request.getAttribute("favList");
%>
<!-- 스케일 -->
<div id="scale-wrap" xeus-show="true">1 : <input type="text" id="scale-val" value="1000"></div>
<!-- 검색창 -->
<div id="search-parent" xeus-show="true"></div>
<!-- 지도 div -->
<div id="xeus-map-content-aws" class="map-target">

    <div id="popup"></div>
  	<div class="weather-parent" xeus-show="true">
		<div class="weatherInfoBox">
			<span class="weatherIcon"></span>
			<span class="weatherTemperature"></span>
			<span class="weatherInfo">
				<span class="weatherPopLabel"></span>
				<span class="weatherPop"></span>
				<span class="weatherVecWsd"></span>
				<span class="weatherReh"></span>
				| <span class="weatherO3Label">오존 : </span><span class="weatherO3"></span>
				| <span class="weatherPm10Label">미세먼지 : </span><span class="weatherPm10"></span>
				| <span class="weatherPm25Label">초미세먼지 : </span><span class="weatherPm25"></span>
			</span>
		</div>
				<!-- 			날씨 div, 오존, 미세먼지 추가 예정 -> 공공데이터포털 or skt? -->
	<!-- 	<div id="top_right">
			<div id="DateBox">
				<span id="date"></span> <span id="time"></span>
			</div>
		</div> -->
	</div>
</div>
<div id="xeus-overview-awsView"></div>
<div id="fav-wrap">
   <div id="fav-header">
       <span id="fav-title">관심영역</span>
       <img src="<%= context %>/res/img/popup_close_normal.png" id="popup-closer">
   </div>
   <div id="fav-body">
        <input type="text" id="fav-name" class="keyup" for="#fav-add" placeholder="관심영역 이름"><button id="fav-add">등록</button><br>
        <div id="fav-content">
<% if(favList.size() > 0){ %>
<% for(int i=0; i<favList.size(); i++){
      String k = favList.get(i).getMgrSeq();
      double x = favList.get(i).getX();
      double y = favList.get(i).getY();
      int zoom = favList.get(i).getZoom();
%>
			<div class="fav-list" k="<%= k %>" x="<%= x %>" y="<%= y %>" zoom="<%= zoom %>"><span><%= favList.get(i).getTitle() %></span><button class="fav-del" k="<%= k %>">삭제</button></div>
<% }}else{ %>
				<div align="center" style="margin-top: 60px;">관심영역이 존재하지 않습니다.</div>
<% } %>
        </div>
   </div>
</div>
<div id="virtual-map-boundary"></div>
<!-- 사이드 메뉴바 -->
<div id="overlay-west-side-bar" class="overlay-side-bar">
    <!-- 장비관리(NMS) -->
 <!--    <button id="btn-nmg-monitor-view" class="menu-button" for="btn-nms-mng" icon="menu8">상태<br>모니터링</button> -->
    <button id="btn-ndps-aws-view" class="menu-button" for="btn-aws-mng" icon="menu25">시설물 모니터링</button>
</div>
<!--  west pane, 지도와 overlay 됨, 검색 영역 -->
<!--  2017.11.20 by kim, xeus-event추가: 기능구분용 -->
<div id="center-overlay-west" xeus-show='false' xeus-full-size="false" xeus-event=''>
    <div id="overlay-west-bar" class="overlay-bar">
        <!-- 171212 -->
        <!-- <button id="west_btn_close" class="overlay-bar-button left-float">닫기</button> -->
        <button id="west_btn_close" class="overlay-bar-button left-float"><img src="/xeus/res/img/close_btn.png"/></button>
    </div>
    <div id="overlay-west-contents" >
    </div>
</div>

<div id="center-lgd-overlay-west" xeus-show='false' xeus-event=''>
	<div id="overlay-lgd-west-bar" class="overlay-bar">
        <!-- 171212 -->
        <!-- <button id="west_btn_close" class="overlay-bar-button left-float">닫기</button> -->
        <button id="west_lgd_btn_close" class="overlay-bar-button left-float"><img src="/xeus/res/img/close_btn.png"/></button>
    </div>
    <div id="overlay-lgd-west-contents" >
    </div>
</div>

<div id="center-overlay-west-tab" xeus-show="true">
    <button id="btn-map-home" class="menu-button">홈</button>
    <button id="btn-map-cler" class="menu-button">정리</button>
    <button id="btn-map-prev" class="menu-button">이전</button>
    <button id="btn-map-next" class="menu-button">다음</button>
    <button id="btn-map-move" class="menu-button">이동</button>
    <button id="btn-map-dist" class="menu-button">거리</button>
    <button id="btn-map-area" class="menu-button">면적</button>
    <button id="btn-map-indx" class="menu-button">인덱스맵</button>
    <button id="btn-map-favr" class="menu-button">관심영역</button>
    <button id="btn-map-image" class="menu-button">화면저장</button>
    <button id="btn-lgd-mng" class="menu-button">범례</button>
</div>

<!--  east pane, cctv가 보여질 영역, 지도와 overlay 됨 -->
<div id="center-overlay-east" xeus-show='false' xeus-event=''></div>

<div id="overlay-south-side-bar">
    <span id="coorX"></span>
    <span id="coorY"></span>
    <span id="addr"></span>
</div>
