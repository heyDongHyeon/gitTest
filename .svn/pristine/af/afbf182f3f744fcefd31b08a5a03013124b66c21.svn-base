<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="geomex.xeus.sysmgr.service.OrganizationVo"%>
<%@ page import="geomex.xeus.equipmgr.service.CctvModelVo"%>
<%@ page import="geomex.xeus.util.code.CodeConvertor"%>
<%@ page import="geomex.xeus.sysmgr.service.ColumnVo"%>
<%@ page import="geomex.xeus.sysmgr.service.ImageVo"%>
<%@ page import="geomex.xeus.equipmgr.service.VoiceVo"%>

<%@ include file="../common.jsp" %>
<%
	CodeConvertor cde = (CodeConvertor) request.getAttribute("code");

	/* CD94 // 긴급구조구분 */
	HashMap<String, String> divisionMap = cde.convertCodeGrpToAllCde("C94");
	Set<String> divisionSet = new TreeSet<String>(divisionMap.keySet());
	Iterator<String> divisionKey = divisionSet.iterator();

	/* CD95 // 긴급구조인명피해(명)*/
	HashMap<String, String> casualtiesMap = cde.convertCodeGrpToAllCde("C95");
	Set<String> casualtiesSet = new TreeSet<String>(casualtiesMap.keySet());
	Iterator<String> casualtiesKey = casualtiesSet.iterator();

%>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndms.chart.js"></script>
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.ndms.data.js"></script>

<div id="overlay-east-bar" class="overlay-bar">
    <b class="overlay-bar-title">NDMS 상세 정보</b>
	<button type="button" id="closeBtn">
		<img src="../res/img/close_btn.png">
	</button>
</div>
<div id="overlay-east-contents" style="background: rgb(59, 59, 59);" class="ui-droppable mCustomScrollbar" data-mcs-theme="minimal-dark">
	<div id="ndms_inform" class="common_layout"></div>
	<table id="regTable" class="list ndmsAddedColumnRegTable" cellspacing="0" style="margin-top: 60px;">
		<tbody>

			<tr style= "display:none">
				<th>지역</th>
				<td colspan="3">
					<input type="text" id="dstrAreaNm" name="지역" class="sendData wide" value="">
				</td>
			</tr>
			<tr style= "display:none">
				<th>고유값</th>
				<td colspan="3">
					<input type="text" id="_gid" name="고유값" class="sendData wide" value="">
				</td>
			</tr>
			<tr>
				<th>구분</th>
				<td colspan="3">
					<select id="division" name="구분" class="sendData wide">
						<option value="10" selected>-선택-</option>
<%
						 while(divisionKey.hasNext()){
                			 String str = (String) divisionKey.next();
                			 //미입력일 때는 optino생성하지 않는다.
	                		 if("10".equals(str)){
	                		 	continue;
	                		 }
%>

						<option value="<%= str %>"><%= divisionMap.get(str) %></option>


<%
						 }
%>
<!-- 						<option value="미입력" selected>-선택-</option> -->
<!-- 						<option value="도로교통">도로교통</option> -->
<!-- 						<option value="화재">화재</option> -->
<!-- 						<option value="산불">산불</option> -->
<!-- 						<option value="열차">열차</option> -->
<!-- 						<option value="지하철">지하철</option> -->
<!-- 						<option value="폭발">폭발</option> -->
<!-- 						<option value="해양">해양</option> -->
<!-- 						<option value="가스">가스</option> -->
<!-- 						<option value="유/도선">유/도선</option> -->
<!-- 						<option value="환경오염">환경오염</option> -->
<!-- 						<option value="공단 내 시설">공단 내 시설</option> -->
<!-- 						<option value="광산">광산</option> -->
<!-- 						<option value="전기(감전)">전기(감전)</option> -->
<!-- 						<option value="승강기">승강기</option> -->
<!-- 						<option value="보일러">보일러</option> -->
<!-- 						<option value="항공기">항공기</option> -->
<!-- 						<option value="붕괴">붕괴</option> -->
<!-- 						<option value="물놀이">물놀이</option> -->
<!-- 						<option value="익사">익사</option> -->
<!-- 						<option value="등산">등산</option> -->
<!-- 						<option value="추락">추락</option> -->
<!-- 						<option value="농기계">농기계</option> -->
<!-- 						<option value="자전거">자전거</option> -->
<!-- 						<option value="레저(생활체육)">레저(생활체육)</option> -->
<!-- 						<option value="놀이시설">놀이시설</option> -->
<!-- 						<option value="놀이시설">재난 외</option> -->
<!-- 						<option value="놀이시설">기타</option> -->
					</select>
				</td>
			</tr>
			<tr>
				<th>인명피해(명)</th>
	            <td colspan="3" style="background: rgb(51, 51, 51);">
	                <select id="casualties" name="인명피해(명)" class="sendData wide">
						<option value="" selected>-선택-</option>
<%
						while(casualtiesKey.hasNext()){
                			 String str = (String) casualtiesKey.next();
                			 System.out.println("str = " + str);
                			 System.out.println("casualtiesMap.get(str) = " + casualtiesMap.get(str));
%>
						<option value="<%= str %>"><%= casualtiesMap.get(str) %></option>
<%
						}
%>
<!-- 						<option value="부상">부상</option> -->
<!-- 						<option value="기타">기타</option> -->
					</select>
	            </td>
				</tr>
			<tr>
				<th>재산피해액(억원)</th>
				  <td colspan="3">
	                <input type="number" id="propertyDamage" name="재산피해액(억원)" class="sendData wide" value="" placeholder="재산피해액(억원)">
	            </td>
			</tr>
			<tr>
				<th>메모</th>
				  <td colspan="3">
	                <input type="text" id="memo" name="메모" class="sendData wide" value="" placeholder="메모">
	            </td>
			</tr>
<!-- 			<tr> -->
<!-- 				<th>경도</th> -->
<!-- 				  <td style="background: rgb(51, 51, 51);"> -->
<!-- 					<input type="text" id="lng" name="lng" class="sendData wide" value="" style="color: rgb(255, 255, 255);"> -->
<!-- 				  </td> -->
<!-- 		    </tr> -->
<!-- 		    <tr> -->
<!-- 				<th>위도</th> -->
<!-- 				  <td style="background: rgb(51, 51, 51);"> -->
<!-- 					<input type="text" id="lat" name="lat" class="sendData wide" value="" style="color: rgb(255, 255, 255);"> -->
<!-- 			      </td> -->
<!-- 			</tr> -->
			<tr>
				<th>경도</th>
				<td>
					<input type="number" id="lng" name="경위도" class="sendData wide" value="">
				</td>
				<th>위도</th>
				<td>
					<input type="number" id="lat" name="경위도" class="sendData wide" value="">
				</td>
			</tr>

  		 </tbody>
  	</table>
  	<div class="btnDiv">
        <button class="whiteBtn" id="ndmsMapClickBtn">지도에서 위치 선택</button>
<!--         <button class="whiteBtn" id="saveBtn">저장</button> -->
        <button class="btn_style" id="ndmsSaveBtn" style="margin-top: 80px;">저 장</button>
<!--         <button class="whiteBtn" id="saveBtn">저장</button> -->
    </div>

</div>
