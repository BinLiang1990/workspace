<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="floatdiv">
	<div class="floatdiv_bg"></div>
	<!--遮罩背景 -->
	<div id="submitAlarmLevelChangeList" class="floatdiv_content"
		style="width: 500px">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="13" height="33" class="td_topleft"></td>
				<td class="td_topcenter"><div class="floatdiv_top"
						onMouseDown="moveEvent(event,'submitAlarmLevelChangeList')">
						<span>报警级别调整</span>
						<!-- 弹出层标题 -->
						<a href="javascript:visclose('submitAlarmLevelChangeList')"> <img
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
						<div class="submitAlarmLevelChangeList">
							<form name="alarmLevelFormList"
								action="ChangeAlarmLevelListAction.action" method="post">
								<input type="hidden" size="4" id="alalids" name="alalids" /> <input
									type="hidden" id="opmuser_userid" name="opmuser_userid"
									value="<sec:authentication property='name'/>" />

								<table width="100%">
									<tr>
										<td>报警级别：</td>
										<td><select name="alarmLevel_al" id="alarmLevel_al">
												<option selected value="active">active</option>
												<option value="inactive">inactive</option>
												<option value="warning">warning</option>
										</select></td>
									</tr>
									<tr>
										<td>报警响应时段：</td>
										<td><select name="alarmTimeRegion_al"
											id="alarmTimeRegion_al">
												<option selected value="7*24">7*24</option>
												<option value="7*12">7*12</option>
												<option value="5*9">5*9</option>
										</select></td>
									</tr>
									<tr>
										<td align="center" colspan="2"><a class="button"
											onclick="alarmLevelFormList.submit()"><span
												class="buttonRight">提交</span> </a> <a class="button"
											href="javascript:visclose('submitAlarmLevelChangeList')"><span
												class="buttonRight">取消</span> </a></td>
									</tr>
								</table>
							</form>
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
