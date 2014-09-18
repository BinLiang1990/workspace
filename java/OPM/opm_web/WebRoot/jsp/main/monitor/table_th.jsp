<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<th width="3%" height="25"></th>
<th width="5%">编号</th>
<th width="8%">报警点</th>
<th width="10%">描述</th>
<th width="5%">种类</th>
<th width="5%">关键</th>
<th width="12%">更新时间</th>
<th width="8%">查看历史</th>
<th width="8%">向谁报警</th>
<th width="8%">报警级别</th>
<th width="5%">响应调整</th>
<th width="15%">报警忽略</th>
<th width="5%">状态</th>
