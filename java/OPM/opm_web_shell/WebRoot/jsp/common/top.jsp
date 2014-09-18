<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="header">
	<div class="headerLeft">
		<a href=""> <img src="images/logo.png" /> </a>
	</div>
	<div class="headerRight">
		<div class="info">
			<div class="infoLeft"></div>
			<div class="infoCenter">
				<ul>
					<li><sec:authentication property="name" />
					</li>
					<li class="line"></li>
					<li><a href="./j_spring_security_logout">Logout</a></li>
				</ul>
			</div>
			<div class="infoRight"></div>
		</div>
	</div>
</div>
