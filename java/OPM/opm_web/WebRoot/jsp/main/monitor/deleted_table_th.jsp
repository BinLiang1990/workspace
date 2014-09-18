<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<tr>
	<th width="5%">编号</th>
	<th width="10%">系统</th>
	<!--  <th width="8%">数据中心</th>-->
	<th width="7%">报警点</th>
	<th width="25%">Server/IP</th>
	<th>最后更新</th>
	<th width="5%">种类</th>
	<th width="8%">关键程度</th>
	<th>描述</th>
	<th width="8%">查看历史</th>
</tr>
