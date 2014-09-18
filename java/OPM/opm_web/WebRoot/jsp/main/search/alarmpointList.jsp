<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script type="text/javascript" src="js/tab/jquery-1.7.2.min.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="floatdiv">
	<div class="floatdiv_bg"></div>
	<!--遮罩背景 -->
	<div id="alarmpointList" class="floatdiv_content" style="width: 600px">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="13" height="33" class="td_topleft"></td>
				<td class="td_topcenter"><div class="floatdiv_top"
						onMouseDown="moveEvent(event,'alarmpointList')">
						<span>报警点设置（不填代表不修改）</span>
						<!-- 弹出层标题 -->
						<a href="javascript:visclose('alarmpointList')"> <img
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
						<div class="submitWarning">

							<table>
								<tr>
									<td>描述：</td>
									<td><textarea name="description" id="descriptionBatch"></textarea></td>
								</tr>
								<tr>
									<td>种类：</td>
									<td><select name="type" id="typeBatch">
											<option></option>
											<option value="module">模块</option>
											<option value="data">数据</option>
											<option value="function">功能</option>

									</select></td>
								</tr>
								<tr>
									<td>关键程度：</td>
									<td><select name="criticality" id="criticalityBatch">
											<option></option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
									</select></td>
								</tr>
								<tr>
									<td>第一报警人:</td>
									<td>
										<select name="firstAlarmPersonBatch" id="firstAlarmPersonBatch">
											<option></option>
											<s:iterator value="opsOpmusersList" status="s" id="opsOpmuser">
												<option value="<s:property value='#opsOpmuser.username' />,<s:property value='#opsOpmuser.phone' />">
													<s:property value='#opsOpmuser.username' />,
													<s:property value='#opsOpmuser.phone' />
												</option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td>第二报警人:</td>
									<td>
										<select name="secondAlarmPersonBatch" id="secondAlarmPersonBatch">
											<option></option>
											<s:iterator value="opsOpmusersList" status="s" id="opsOpmuser">
												<option value="<s:property value='#opsOpmuser.username' />,<s:property value='#opsOpmuser.phone' />">
													<s:property value='#opsOpmuser.username' />,
													<s:property value='#opsOpmuser.phone' />
												</option>
											</s:iterator>
										</select>
									</td>
								</tr>
								<tr>
									<td>报警级别：</td>
									<td><select name="alarmLevel" id="alarmLevelBatch">
											<option></option>
											<option value="active">active</option>
											<option value="warning">warning</option>
									</select></td>
								</tr>
								<tr>
									<td>报警响应时段：</td>
									<td><select name="alarmTimeRegion"
										id="alarmTimeRegionBatch">
											<option></option>
											<option value="7*24">7*24</option>
											<option value="7*12">7*12</option>
											<option value="5*9">5*9</option>
									</select></td>
								</tr>
								<tr>
									<td>响应调整：</td>
									<td><input type="text" name="delayTime"
										id="delayTimeBatch" />（Sec）</td>
								</tr>
								<tr>
									<td align="left">从：</td>
									<td><input type="text" id="noAlarmStartTimeBatch"
										name="noAlarmStartTime"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" /></td>
								</tr>
								<tr>
									<td align="left">到：</td>
									<td><input type="text" id="noAlarmEndTimeBatch"
										name="noAlarmEndTime"
										onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" /> 不报警</td>
								</tr>
								<tr>
									<td align="center" colspan="2"><a class="button"
										onclick="batchModifyAlarmPoint()"><span
											class="buttonRight">提交</span> </a> <a class="button"
										href="javascript:visclose('alarmpointList')"><span
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
				<td width="13" class="td_bottomright"></td>
			</tr>
		</table>
	</div>
</div>
