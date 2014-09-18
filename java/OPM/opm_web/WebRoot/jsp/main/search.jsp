<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<script type="text/javascript">
	function defaultCheck() {
		for(var i = 0; i < 3; ++i) {
			$('[name="alarmLevel"]')[i].checked = true;
			$('[name="alarmTimeRegion"]')[i].checked = true;
		}
		for(var i = 0; i < 5; ++i) {
			$('[name="status"]')[i].checked = true;
		}
	}
</script>
<jsp:include page="/jsp/common/head.jsp"></jsp:include>
<body onload="defaultCheck()">
	<div class="main">
		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<jsp:include page="/jsp/main/search/menu.jsp"></jsp:include>
		<jsp:include page="/jsp/main/search/main.jsp"></jsp:include>
	</div>
</body>
</html>