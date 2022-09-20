<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page import="java.util.HashMap"%> --%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="geomex.xeus.tvius.service.CrmsSysParamVo"%>
<%@ page import="com.google.common.base.CaseFormat"%>
<%@ include file="../common.jsp" %>
<%!
    private static String firstCharToLowerCase(String str) {
        if(str == null || str.length() == 0)
            return "";

        if(str.length() == 1)
            return str.toLowerCase();

        char[] chArr = str.toCharArray();
        chArr[0] = Character.toLowerCase(chArr[0]);

        return new String(chArr);
    }

    public String toCamelCase(String input){
        String str = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, input);
        str = firstCharToLowerCase(str);

        return str;
    }
%>
<%

    //HashMap<String, String> param = (HashMap<String, String>)request.getAttribute("param");


    ArrayList<CrmsSysParamVo> list = (ArrayList<CrmsSysParamVo>)request.getAttribute("list");

    //수정하지 않는 항목의 키값을 배열에 저장한다.
    String[] exceptList = {
                            "event.evt112", "event.evt119", "event.evtDsc"
                            , "nms.cpu.warn.value", "nms.mem.warn.value", "nms.rep.chk", "nms.rep.interval", "nms.status.chk", "nms.status.interval"
                            , "sys.upload_path"
                            , "tvius.admin_sms_list", "tvius.hash_files", "tvius.mask_route_af", "tvius.mask_route_bf", "tvius.masking_yn", "tvius.storage_path"
                        };
    String[] sliderList = {
							"event.fullrain_limit", "event.snow_limit"

                        };

    String[] checkboxList = {"event.bell_evt"};
    String[] checkboxArrList = {"event.ndps_evt","event.bell_led", "event.cctv_evt", "event.ivcp_evt"};
    String[] dayLimitList = {"tvius.last_sms_dat"};
    String[] dayList = {"tvius.avi_play_dat", "tvius.renew_play_dat"};
    String[] minuteList = {"tvius.avi_play_time", "event.dementia.interval"};
    String[] distList = {"cctv.net_dist_limit"};
    String[] cntList = {"sys.login_lock_cnt", "tvius.avi_play_cnt", "tvius.evi_play_cnt", "tvius.file_down_cnt", "tvius.renew_play_cnt", "tvius.rqst_lock_cnt"};
    String[] bigStrList = {"event.car.ftp", "event.relay.http"};
/*
    HashMap<String, String> info = new HashMap<String, String>();
    info.put("tvius.avi_play_cnt",   "<span>- 사용자가 영상반출 신청을 했을 때, 기본으로 적용되는 재생횟수를 설정할 수 있습니다.</span><br>"
                                    +"<span>- 재생횟수는 <span class='text_highlight'>2자리 이하</span>로 입력하셔야 합니다.</span>");
    info.put("tvius.avi_play_dat",   "<span>- 사용자가 영상반출 신청 시, 기본으로 적용되는 최대 재생기간을 설정할 수 있습니다.<br></span>"
                                    +"<span>- 재생기간은 <span class='text_highlight'>2자리 이하</span>로 입력하셔야 합니다.<br></span>");
    info.put("tvius.renew_play_cnt", "<span>- 연장신청을 [승인]할 때 추가 <span class='text_highlight'>재생횟수 기본 값</span>을 설정할 수 있습니다.<br></span>"
                                    +"<span>- 재생횟수는 <span class='text_highlight'>2자리 이하</span>로 입력하셔야 합니다.<br></span>");
    info.put("tvius.renew_play_dat", "<span>- 연장신청을 [승인]할 때 추가 <span class='text_highlight'>재생기간 기본 값</span>을 설정할 수 있습니다.<br></span>"
                                    +"<span>- 재생기간은 <span class='text_highlight'>2자리 이하</span>로 입력하셔야 합니다.<br></span>");
    info.put("tvius.evi_play_cnt",   "<span>- 증거신청을 [승인]할 때 추가 <span class='text_highlight'>재생횟수 기본 값</span>을 설정할 수 있습니다.<br></span>"
                                    +"<span>- 재생횟수는 <span class='text_highlight'>2자리 이하</span>로 입력하셔야 합니다.<br></span>");
    info.put("tvius.avi_play_time",  "<span>- 사용자의 영상 미리보기의 시간을 설정할 수 있습니다.<br></span>"
                                    +"<span>- 최대 시간은 <span class='text_highlight'>10분</span> 입니다.<br></span>");
    info.put("tvius.rqst_lock_cnt",  "<span>- 영상반출신청은 활용결과 미입력건수가 관리자가 지정한 건수보다 초과되면 사용자의 영상반출신청이 제한됩니다. <br></span>"
                                    +"<span>- 반출영상신청제한은 <span class='text_highlight'>2자리 이하</span>로 입력하셔야 합니다.<br></span>");
    info.put("sys.login_lock_cnt",   "<span>- 로그인 시도 횟수를 초과한 사용자는 시스템 로그인이 제한됩니다.<br></span>"
                                    +"<span>- 로그인 재시도 횟수는 <span class='text_highlight'>5회 이하</span>로 입력하셔야 합니다.</span>");
    info.put("tvius.last_sms_dat",   "<span>- 사용자가 반출신청한 건수에 <span class='text_highlight'>재생만료일 알림 문자</span>를 몇 일 전부터 전송할지를 설정할 수 있습니다.<br></span>"
                                    +"<span>- 해당 만료일은 <span class='text_highlight'>최대 10일</span>까지 지정할 수 있습니다.<br></span>");
    info.put("tvius.preview_avi",    "<span class='one_line_span'>- 영상 반출 신청 시 <span class='text_highlight'>동영상 미리보기</span> 가능여부를 선택합니다.</span>");
    info.put("tvius.preview_photo",  "<span class='one_line_span'>- 영상 반출 신청 시 <span class='text_highlight'>사진 미리보기</span> 가능여부를 선택합니다.</span>");
    info.put("tvius.file_down_cnt",  "<span>- 사용자가 영상을 다운로드할 수 있는  횟수를 지정합니다.<br></span>"
                                    +"<span>- 다운로드 횟수는 <span class='text_highlight'>최대 2자리 이하</span>로 입력하셔야합니다.</span>");
    info.put("cctv.net_dist_limit",  "<span>- 투망 모니터링 거리 제한을 설정합니다.<br></span>"
                                    +"<span>- '0'을 입력할 경우 <span class='text_highlight'>무제한</span>으로 설정됩니다.</span>"); */
%>
<link rel="stylesheet" type="text/css" href="<%= context %>/res/css/xeus.systemMng.envSet.css">
<script type="text/javascript" src="<%= context %>/res/geomex.xeus.system.mng.env.set.js"></script>
<div>
    <div id="wrap" class="mCustomScrollbar" data-mcs-theme="minimal-dark">

        <div id="content">
            <table id="userList">
                <colgroup>
                    <col width="250"/>
                    <col width=""/>
                </colgroup>
<%
    for(int i=0; i<list.size(); i++){
        if(Arrays.asList(exceptList).contains(list.get(i).getPropKey())){
%>
                <!-- 현재 수정하지 않는 항목에 별도의 작업을 해야된다면 이쪽에서 처리 -->
                <%-- <input id="<%=toCamelCase(list.get(i).getPropKey().split(".")[1])%>" type="hidden" class="sendData" value="<%= StrUtil.chkNull(list.get(i).getPropValue()) %>"> 회 --%>
<%
        } else{
%>
                <tr>
                    <th><%= list.get(i).getPropNm() %></th>
                    <td align="left" style="text-align: left;">

<%
            if(Arrays.asList(checkboxList).contains(list.get(i).getPropKey())){
%>
                        <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" class="sendData" type="checkbox" <%= (StrUtil.chkNull(list.get(i).getPropValue()).equals("Y") ? "checked" : "") %>>
<%
            } else if(Arrays.asList(checkboxArrList).contains(list.get(i).getPropKey())){
            	 HashMap<String, Object> map = StrUtil.strToMap(list.get(i).getPropValue());
            	 int index = 0;
            	 for(Map.Entry<String, Object> entry : map.entrySet()) {
            		 index++;
            		 String key = entry.getKey();
            		 String value = (String)entry.getValue();

            		//String key = pairs[j].split(":")[0];
%>
 					<input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])+index%>" key="<%= key %>" class="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" type="checkbox" <%= (value.equals("Y") ? "checked" : "") %>>
 					<label for="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])+index%>"><%= key %></label>
<%
            	}
            } else if(Arrays.asList(dayLimitList).contains(list.get(i).getPropKey())){
%>
                        <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" type="text" class="sendData" value="<%= StrUtil.chkNull(list.get(i).getPropValue()) %>"> 일 전
<%
            } else if(Arrays.asList(dayList).contains(list.get(i).getPropKey())){
%>
                        <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" type="text" class="sendData" value="<%= StrUtil.chkNull(list.get(i).getPropValue()) %>"> 일
<%
            } else if(Arrays.asList(minuteList).contains(list.get(i).getPropKey())){
%>
                        <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" type="text" class="sendData" value="<%= StrUtil.chkNull(list.get(i).getPropValue()) %>"> 분
<%
            } else if(Arrays.asList(distList).contains(list.get(i).getPropKey())){
%>
                        <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" type="text" class="sendData" value="<%= StrUtil.chkNull(list.get(i).getPropValue()) %>"> M
<%
            } else if(Arrays.asList(cntList).contains(list.get(i).getPropKey())){
%>
                        <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" type="text" class="sendData" value="<%= StrUtil.chkNull(list.get(i).getPropValue()) %>"> 회
<%
            } else if(Arrays.asList(sliderList).contains(list.get(i).getPropKey())){
            	String minStr= list.get(i).getPropValue().split(",")[0];
            	String maxStr= list.get(i).getPropValue().split(",")[1];
%>
						<div style="width:290px; margin-bottom:10px;">
							주의보 : <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>Min" type="text" value="<%= minStr%>" style="width:40px; margin-right:5px;">mm 이상
							/ 경보 : <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>Max" type="text" value="<%= maxStr%>" style="width:40px;  margin-right:5px;">mm 이상
						</div>

						<div style="width:300px;">
							<div id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" type="text" class="sys_slider_bar sendData" value="<%= StrUtil.chkNull(list.get(i).getPropValue()) %>" style=" "></div>
						</div>
<%
            } else {
                if(Arrays.asList(bigStrList).contains(list.get(i).getPropKey())){
%>
                        <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" type="text" class="sendData"
                        style="width:99%; padding-left: 10px; text-align: left;" value="<%= StrUtil.chkNull(list.get(i).getPropValue()) %>">
<%
                } else{
%>
                        <input id="<%=toCamelCase(list.get(i).getPropKey().split("\\.")[1])%>" type="text" class="sendData" value="<%= StrUtil.chkNull(list.get(i).getPropValue()) %>">
<%
                }
            }
%>
                    </td>
                    <%-- <td>
                        <!-- 설명 추가하는 작업 해야 함.
                                                        미리보기 권한은 preview가 앞으로 오게해서 뭉칠 수 있도록 조정(order by 되서 들어오니까 예쁘게 리스트화되게)
                         -->
                        <%= StrUtil.chkNull(info.get(list.get(i).getPropKey())) %>
                    </td> --%>
                </tr>
<%
        }
    }
%>
            </table>
        </div>

        <div class="btnDiv">
            <button id="btn_save" class="stat_btn">저 장</button>
            <button id="btn_reset" class="stat_btn stat_gray_btn">초 기 화</button>
        </div>

    </div>
</div>
