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

<td><s:property value='#livemonitor.id' /></td>
<td><s:property value='#livemonitor.serviceName' /></td>
<!--  <td>not support yet</td>-->
<td><s:property value='#livemonitor.componentName' /></td>
<td><s:property value='#livemonitor.serverName' />/<s:property
		value='#livemonitor.serverIpaddress' /></td>
<td><s:property value='#livemonitor.convertedCSTTime' /></td>
<td><s:property value='#livemonitor.type' /></td>
<td><s:property value='#livemonitor.criticality' /></td>
<td><s:property value='#livemonitor.description' /></td>
<td><a
	href="./GetAPointHistory.action?lid=<s:property value='#livemonitor.id' />">查看历史</a>
</td>
