<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<tr>
	<th align="center" width="2%">选</th>
	<th align="center" width="4%">编号</th>
	<th width="7%" align="center">系统</th>
	<th width="7%" align="center">报警点</th>
	<th width="10%" align="center">Server/IP</th>
	<th width="4%" align="center">种类</th>
	<th width="2%" align="center">关键程度</th>
	<th width="8%" align="center">开始时间</th>
	<th width="8%" align="center">持续时间(分)</th>
	<th width="8%" align="center">查看历史</th>
	<th width="5%" align="center">受理</th>
	<th width="5%" align="center">报警</th>
	<th width="8%" align="center">向我报警</th>
	<th width="5%" align="center">响应</th>
	<th width="80%" align="center">状态</th>
</tr>
