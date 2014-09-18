<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<th colspan="7" class="TDL">批量操作</th>
<th colspan="2"><a onclick="selectAceptlist()"
	href="javascript:visNew('submitAcceptList')"><b>受理</b></a></th>
<th colspan="3"><a onclick="selectAlarmlist()"
	href="javascript:visNew('submitAlarmList')"><b>报警</b></a></th>
<th colspan="3"><a onclick="selectResponslist()"
	href="javascript:visNew('submitResponseList')"><b>响应</b></a></th>

