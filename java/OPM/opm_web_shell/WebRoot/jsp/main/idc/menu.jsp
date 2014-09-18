<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="menu">
	<div class="menuLeft"></div>
	<div class="menuCenter">
		<div class="menuCenterLeft">
			<ul>
				<li class="hover"><a href="./GetAllIdcAction.action">数据中心(IDC)</a>
				</li>
				<li class="menuLine"></li>
				<li><a href="./GetAllOpmUsersAction.action">用户管理</a></li>
			</ul>
		</div>
		<jsp:include page="/jsp/common/menu_right.jsp"></jsp:include>

	</div>
	<div class="menuRight"></div>
</div>
