<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<td><s:property value="#L.index+1" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.livemonitor.serviceName}" /></td>
<!--<td>A</td>-->
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.livemonitor.componentName}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.status}" /> to <s:property
		value="%{#history.changedAlarmHistory.status}" /></td>
<td align="center"><s:property value="%{#history.startTime}" /></td>
<td align="center"><s:property value="%{#history.stopTime}" /></td>
<td align="center"><s:property value="%{#history.duration}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.livemonitor.alarmLevel}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.livemonitor.serverName}" />/<s:property
		value="%{#history.originalAlarmHistory.livemonitor.serverIpaddress}" />
</td>
<td align="center"><s:property value="%{#history.responseTime}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.livemonitor.convertedType}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.livemonitor.description}" /></td>

<td align="center"><s:property
		value="%{#history.originalAlarmHistory.livemonitor.criticality}" /></td>


<td align="center"><s:property
		value="%{#history.originalAlarmHistory.acceptedPerson}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.convertedAcceptedTime}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.alarmedPerson}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.convertedAlarmedTime}" /></td>
<!-- response -->
<!--
<td align="center"><a href="javascript:visNew('submitResponse')"
	onclick="setResHL('${history.originalAlarmHistory.id}', '${ history.originalAlarmHistory.livemonitor.id }')"><s:property
			value='#history.originalAlarmHistory.isResponsed' /> </a>
</td>
-->
<td align="center"><a
	onclick="setResHL('${history.originalAlarmHistory.id}', '${ history.originalAlarmHistory.livemonitor.id }')"
	<s:property
			value='#history.originalAlarmHistory.convertedIsResponsed' escape="false" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.responsedPerson}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.convertedResponsedTime}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.responsedHandling}" /></td>
<td align="center"><s:property
		value="%{#history.originalAlarmHistory.responsedNote}" /></td>


