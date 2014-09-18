<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<div class="content">
	<div class="contentBox">
		<s:form action="SearchConditionsAction" theme="simple">
			<table cellpadding="5">
				<tr>
					<td><input type="hidden" name="opmUserId"
						value="<sec:authentication property="name"/>" /> IP：<s:textfield
							name="serverName" /></td>
				</tr>
				<tr>
					<td>系统：<s:checkboxlist name="serviceName"
							list="serviceNameList" />
					</td>
				</tr>
				<tr>
					<td>报警点：<s:textfield name="componentName" />
					</td>
				</tr>
				<tr>
					<td>报警级别：<s:checkboxlist name="alarmLevel"
							list="{'active', 'warning', 'inactive'}" />
					</td>
				</tr>
				<tr>
					<td>报警响应时段：<s:checkboxlist name="alarmTimeRegion"
							list="{'7*24', '7*12', '5*9'}" />
					</td>
				</tr>
				<tr>
					<td>心跳周期：<s:textfield name="delayTimeLow" />秒到 <s:textfield
							name="delayTimeHigh" />秒（空代表时间不限）
					</td>
				</tr>
				<tr>
					<td>报警点状态：<s:checkboxlist name="status"
							list="{'ACTIVE_UP', 'ACTIVE_DOWN', 'WARNING_UP', 'WARNING_DOWN', 'INACTIVE', 'OK', 'DELETED'}" />
					</td>
				</tr>
				<tr>
					<td>第一报警人:
						<select name="firstAlarmPerson">
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
					<td>第二报警人:
						<select name="secondAlarmPerson">
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
					<td>
						<s:submit value="搜索"/>
					</td>
				</tr>
			</table>
		</s:form>
	</div>
</div>