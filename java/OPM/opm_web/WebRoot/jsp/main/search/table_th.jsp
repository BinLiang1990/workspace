<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<th width="2%" height="25"></th>
<th width="3%">编号</th>
<th width="15%">主机名/IP</th>
<th width="5%">系统</th>
<th width="15%">报警点</th>
<th width="8%">描述</th>
<th width="2%">种类</th>
<th width="2%">关键</th>
<th width="8%">更新时间</th>
<th width="2%">查看历史</th>
<th width="8%">向谁报警</th>
<th width="8%">报警级别</th>
<th width="5%">响应调整</th>
<th width="10%">报警忽略</th>
<th width="2%">状态</th>
