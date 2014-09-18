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
<td height="25" align="center"><input type="checkbox"
	id="selectedlid" name="selectedlid"
	value=<s:property value='#livemonitor.id' /> /></td>
<td align="center"><s:property value="%{#livemonitor.id}" /></td>
<td align="center"><a href="javascript:visNew('submitWarningSite')"
	onclick="setAP('${livemonitor.id}', '${serviceName }', '${ livemonitor.componentName }', '${ ip }',
					'${ livemonitor.description }', '${ livemonitor.type }', '${ livemonitor.criticality }', '${ livemonitor.alarmPerson }',
					'${ livemonitor.alarmLevel }', '${ livemonitor.alarmTimeRegion }', '${ livemonitor.delayTime }');"><s:property
			value="%{#livemonitor.componentName}" /> </a></td>
<td align="center"><s:property value="%{#livemonitor.description}" />
</td>
<td align="center"><s:property
		value="%{#livemonitor.convertedType}" /></td>
<td align="center"><s:property value="%{#livemonitor.criticality}" />
</td>
<td align="center"><s:property
		value="%{#livemonitor.convertedCSTTime}" /></td>
<td align="center"><a
	href="./GetAPointHistory.action?lid=<s:property value='#livemonitor.id' />">查看历史</a>
</td>

<!--  -->
<td align="center"><s:property value="%{#livemonitor.alarmPerson}" /></td>

<td align="center"><a
	href="javascript:visNew('submitAlarmLevelChange')"
	onclick="setAl('${livemonitor.id}', '${ livemonitor.alarmLevel }', '${ livemonitor.alarmTimeRegion }')"><s:property
			value="%{#livemonitor.alarmLevel}" /> / <s:property
			value="%{#livemonitor.alarmTimeRegion}" /> </a></td>
<td align="center"><a
	href="javascript:visNew('submitWarningChange')"
	onclick="setDe('${livemonitor.id}', '${ livemonitor.delayTime }')"><s:property
			value="%{#livemonitor.delayTime}" /> sec</a></td>

<td align="center"><a
	href="javascript:visNew('submitnoAlarmEndTimeChange')"
	onclick="setnal('${livemonitor.id}', '${ livemonitor.convertedNoAlarmStartTime }','${ livemonitor.convertedNoAlarmEndTime }')">从<s:property
			value="%{#livemonitor.convertedNoAlarmStartTime}" /><br>到<s:property
			value="%{#livemonitor.convertedNoAlarmEndTime}" />
</a></td>
<!--
<td align="center"><a href="javascript:visNew('submitnoAlarmEndTimeChange')"
	onclick="setnal('${livemonitor.id}', '${ livemonitor.noAlarmStartTime }','${ livemonitor.noAlarmEndTime }')">从<s:property
			value="%{#livemonitor.noAlarmStartTime}" /><br>到<s:property
			value="%{#livemonitor.noAlarmEndTime}" /> </a>
</td>
-->
<td align="center"><s:property value="%{#livemonitor.statusPic}"
		escape="false" /></td>
