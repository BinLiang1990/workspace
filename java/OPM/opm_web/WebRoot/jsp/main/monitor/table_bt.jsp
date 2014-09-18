<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<th colspan="4" class="TDL">批量操作</th>
<th colspan="2"><a onclick="selectalarmLevellist()"
	href="javascript:visNew('submitAlarmLevelChangeList')"><b>报警级别修改</b></a></th>
<th colspan="2"><a onclick="selectdelayTimelist()"
	href="javascript:visNew('submitDelayTimeListChange')"><b>调整响应时间</b></a></th>
<th colspan="2"><a onclick="selectnoAlarmTimelist()"
	href="javascript:visNew('submitnoAlarmEndTimeListChange')"><b>设定报警忽略</b></a></th>
<th colspan="2"><a onclick="selectdeletelist()"
	href="javascript:visNew('submitDeleteList')"><b>删除报警点</b></a></th>
