<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<div class="menuCenterRight">
	<ul>
		<li>现在时间：<span class=".timefont" id=localtime></span>
		</li>
		<li><label>时区选择：<select><option>GMT+8</option>
			</select>
		</label></li>
	</ul>
	<script type="text/javascript">
	tick();
	</script>
</div>
