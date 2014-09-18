
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>

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
<html>
<jsp:include page="/jsp/common/head.jsp"></jsp:include>
<body>
	<div class="main">
		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<jsp:include page="/jsp/main/monitor/menu.jsp"></jsp:include>
		<div class="content">
			<div class="contentBox">
				<table cellpadding="5">
					<tr>
						<td>
							<button type="button" onclick="$('#display_form').toggle()">新建报警点</button>
						</td>
						<td>
							<span>${requestScope.req}</span>
						</td>
					</tr>
					<tr id="display_form" style="display:none">
						<td>
							<s:form action="CreateAlarmSpotAction" theme="simple">
								<table cellpadding="5">
									<tr>
										<td>名称：</td>
										<td><s:textfield name="name" />example:service.component，多个报警点用"/"相连</td>
									</tr>
									<tr>
										<td>第一报警人:</td>
										<td>
											<select name="firstAlarmPerson" id="firstAlarmPerson">
												<option value=""></option>
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
											<select name="secondAlarmPerson" id="secondAlarmPerson">
												<option value=""></option>
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
										<td>心跳周期：</td>
										<td><input type="text" name="intervalTime" value="600" id="intervalTime"/>秒</td>
									</tr>
									<tr>
										<td>报警级别：</td>
										<td><select name="alarmLevel" id="alarmLevel">
											<option value="active">active</option>
											<option value="inactive">inactive</option>
											<option value="warning">warning</option>
										</select></td>
									</tr>
									<tr>
										<td>种类：</td>
										<td><select name="type" id="type">
											<option value="module">模块</option>
											<option value="data">数据</option>
											<option value="function">功能</option>
										</select></td>
									</tr>
									<tr>
										<td>紧急程度：</td>
										<td><select name="criticality" id="criticality">
											<option value="3">3</option>
											<option value="2">2</option>
											<option value="1">1</option>
										</select></td>
									</tr>
									<tr>
										<td>报警响应时段：</td>
										<td><select name="alarmTimeRegion" id="alarmTimeRegion">
											<option value="5*9">5*9</option>
											<option value="7*12">7*12</option>
											<option value="7*24">7*24</option>
										</select></td>
									</tr>
									<tr>
										<td align="left">从：</td>
										<td>
											<input type="text" id="noAlarmStartTime"
											name="noAlarmStartTime"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" />
										</td>
									</tr>
									<tr>
										<td align="left">到：</td>
										<td>
											<input type="text" id="noAlarmEndTime"
											name="noAlarmEndTime"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd H:mm:ss'})" /> 不报警
										</td>
									</tr>
									<tr>
										<td>描述：</td>
										<td><textarea name="description" id="description"></textarea></td>
									</tr>
								</table>
								<s:submit value="提交"></s:submit>
							</s:form>
						</td>
					</tr>
				</table>
				
			</div>
		</div>
	</div>
</body>

</html>















