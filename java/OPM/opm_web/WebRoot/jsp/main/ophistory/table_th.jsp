<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<th width="5%">编号</th>
<th width="6%">操作人员</th>
<th width="10%">操作类型</th>
<!--
<th width="4%">报警点编号</th>
-->
<th width="7%">服务</th>
<th width="8%">报警点</th>
<th width="15%">报警点IP</th>
<th width="15%">修改前值</th>
<th width="15%">修改后值</th>
<th width="15%">修改日期</th>
