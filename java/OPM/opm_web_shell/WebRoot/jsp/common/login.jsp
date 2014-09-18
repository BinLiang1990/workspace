<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<jsp:include page="/jsp/common/head.jsp"></jsp:include>
<body>
	<div class="main">
		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<br>

		<div class="content">
			<div class="contentCenter">
				<table align="center">
					<tr>
						<td><img src="./images/bgimg.jpg"
							src="../../images/bgimg.jpg" border="0" width="800"></td>
					</tr>
				</table>
			</div>
			<div class="contentCenter">
				<form id="autologinForm" action="./j_spring_security_check"
					method="post">
					<ul>
						<li></li>
					</ul>
					<table border="0" width="400px" align="center">
						<tr>
							<td><label for="j_username">Username</label>
							</td>
							<td><input id="j_username" name="j_username" type="text" />
							</td>

							<td><label for="j_password">Password</label>
							</td>
							<td><input id="j_password" name="j_password" type="password" />
							</td>

							<td><input type="submit" value="Login"
								onclick="setUser_Pwd()" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>

</body>

</html>
