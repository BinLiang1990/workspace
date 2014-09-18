<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<th>编号</th>
<th>系统</th>
<!-- <th width="5%">数据中心</th> -->
<th>报警点</th>
<th>状态改变</th>
<th>记录时间</th>
<th>结束时间</th>
<th>持续时间(分)</th>
<th>报警级别</th>
<th>Server/IP</th>
<th>GS响应时间(分)</th>
<th>种类</th>
<th>描述</th>

<!--  -->
<th>关键程度</th>


<th>受理人</th>
<th>受理时间</th>

<th>接警人</th>
<th>报警时间</th>

<th>响应</th>
<th>响应人</th>
<th>响应时间</th>
<th>反馈</th>
<th>备注</th>

