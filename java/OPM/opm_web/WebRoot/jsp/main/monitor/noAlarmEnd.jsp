<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script type="text/javascript" src="js/ajax/change_no_alarm_end_time.js"></script>
<div class="floatdiv">
	<div class="floatdiv_bg"></div>
	<!--遮罩背景 -->
	<div id="submitnoAlarmEndTimeChange" class="floatdiv_content"
		style="width: 500px">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="13" height="33" class="td_topleft"></td>
				<td class="td_topcenter"><div class="floatdiv_top"
						onMouseDown="moveEvent(event,'submitnoAlarmEndTimeChange')">
						<span>报警忽略调整</span>
						<!-- 弹出层标题 -->
						<a href="javascript:visclose('submitnoAlarmEndTimeChange')"> <img
							src="js/float/images/dialog_closebtn.gif"
							onMouseOver="this.src='js/float/images/dialog_closebtn_over.gif'"
							onMouseOut="this.src='js/float/images/dialog_closebtn.gif'" />
						</a>
					</div></td>
				<td width="13" class="td_topright"></td>
			</tr>
			<tr>
				<td width="13" class="td_centerleft"></td>
				<td class="td_centercenter"><div class="floatdiv_body">
						<!-- 弹出层内容开始 -->
						<div class="submitWarningChange">
							<input type="hidden" size="4" id="nae_id" name="nae_id" /> <input
								type="hidden" id="opmuser_userid" name="opmuser_userid"
								value="<sec:authentication property='name'/>" />

							<table width="100%">

								<tr>
									<td align="right">从：</td>
									<td><input type="text" id="noAlarmStartTime"
										name="noAlarmStartTime"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" /></td>
								</tr>
								<tr>
									<td align="right">到：</td>
									<td><input type="text" id="noAlarmEndTime"
										name="noAlarmEndTime"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" /> 不报警</td>
								</tr>

								<tr>
									<td align="center" colspan="2"><a class="button"
										onclick="changeNoAlarmEndTime()"><span
											class="buttonRight">提交</span> </a> <a class="button"
										href="javascript:visclose('submitnoAlarmEndTimeChange')"><span
											class="buttonRight">取消</span> </a></td>
								</tr>
							</table>
						</div>
						<!-- 弹出层内容结束 -->
					</div></td>
				<td width="13" class="td_centerright"></td>
			</tr>
			<tr>
				<td width="13" height="13" class="td_bottomleft"></td>
				<td class="td_bottomcenter"></td>
				<td width="13" class="td_bottomright">fr</td>
			</tr>
		</table>
	</div>
</div>
