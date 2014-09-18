<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<td align="center"><s:property value="%{#ophistory.id}" /></td>
<td align="center"><s:property
		value="%{#ophistory.opmuser.username}" /></td>
<td align="center"><s:property
		value="%{#ophistory.convertedOperation}" /></td>
<!--
<td><s:property value="%{#ophistory.livemonitorId}" /></td>
-->
<td align="center"><s:property
		value="%{#ophistory.livemonitor.serviceName}" /></td>
<td align="center"><s:property
		value="%{#ophistory.livemonitor.componentName}" /></td>
<td align="center"><s:property
		value="%{#ophistory.livemonitor.serverIpaddress}" /></td>
<td align="center"><s:property value="%{#ophistory.originalValue}" /></td>
<td align="center"><s:property value="%{#ophistory.changeValue}" /></td>
<td align="center"><s:property
		value="%{#ophistory.convertCreatedAt}" /></td>


