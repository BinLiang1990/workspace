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
<td height="25" align="center"><input type="checkbox"
	id="selectedlid" name="selectedlid"
	value=<s:property value='#livemonitor.id' /> /></td>
<td align="center"><s:property value='#livemonitor.id' /></td>
<td align="center"><s:property value='#livemonitor.serviceName' />
</td>
<!--  <td>not support yet</td>-->
<td align="center"><s:property value='#livemonitor.componentName' />
</td>
<td align="center"><s:property value='#livemonitor.serverName' />NA/<s:property
		value='#livemonitor.serverIpaddress' /></td>
<td align="center"><s:property value='#livemonitor.convertedType' /></td>
<td align="center"><s:property value='#livemonitor.criticality' />
</td>
<td align="center"><s:property
		value='#livemonitor.convertedCSTTime' /></td>
<td align="center"><s:property value='#livemonitor.duration' /></td>
<td align="center"><a
	href="./GetAPointHistory.action?lid=<s:property value='#livemonitor.id' />">查看历史</a>
</td>
<td align="center"><a
	onclick="setAcp('${livemonitor.id}', '<sec:authentication property='name'/>')"
	<s:property
			value='#livemonitor.convertedIsAccpted'  escape="false" />
</td>
<td align="center"><a onclick="setAla('${livemonitor.id}')"
	<s:property
			value='#livemonitor.convertedIsAlarmed'  escape="false" />
</td>
<td align="center"><s:property value='#livemonitor.alarmPerson' />
</td>
<td align="center">
<a onclick="setRes('${livemonitor.history.id}')"
<s:property
			value='#livemonitor.convertedIsResponsed'  escape="false" />
</td>