
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<jsp:include page="/jsp/common/refresh.jsp"></jsp:include>
<html>
<jsp:include page="/jsp/common/head.jsp"></jsp:include>
<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('.dataTable').dataTable({
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : false,
			"bAutoWidth" : false
		});
	});
</script>
<body onload="music_on(${music_on})">
	<!-- 受理 -->
	<jsp:include page="/jsp/main/alarm/accept_list_form.jsp"></jsp:include>
	<!--报警-->
	<jsp:include page="/jsp/main/alarm/alarm_list_form.jsp"></jsp:include>
	<!--响应-->
	<jsp:include page="/jsp/main/alarm/response_form.jsp"></jsp:include>
	<jsp:include page="/jsp/main/alarm/response_list_form.jsp"></jsp:include>

	<div class="main">
		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<jsp:include page="/jsp/main/alarmBackup/menu.jsp"></jsp:include>
		<jsp:include page="/jsp/main/alarm/music.jsp"></jsp:include>

		<div class="content">
			<div class="contentBox">
				<div class="title">Active up:</div>
				<div class="demo">
					<table cellpadding="0" cellspacing="0" border="0"
						class="display dataTable" id="allan">
						<thead>
							<jsp:include page="/jsp/main/alarm/table_tr.jsp"></jsp:include>
						</thead>
						<tbody>
							<s:iterator value="active_upLivemonitors" id="livemonitor">
								<tr>
									<jsp:include page="/jsp/main/alarm/table_td.jsp"></jsp:include>
									<td align="center"><img src="images/active_up.png"
										title="active_up" /></td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<tr class="bold">
								<jsp:include page="/jsp/main/alarm/table_bt.jsp"></jsp:include>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<div class="contentBox" style="margin: 30px auto">
				<div class="title">Active down:</div>
				<div class="demo">
					<table cellpadding="0" cellspacing="0" border="0"
						class="display dataTable" id="allan">
						<thead>
							<jsp:include page="/jsp/main/alarm/table_tr.jsp"></jsp:include>
						</thead>
						<tbody>
							<s:iterator value="active_downLivemonitors" id="livemonitor">
								<tr>
									<jsp:include page="/jsp/main/alarm/table_td.jsp"></jsp:include>
									<td align="center"><img src="images/active_down.png"
										title="activedown" /></td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<tr class="bold">
								<jsp:include page="/jsp/main/alarm/table_bt.jsp"></jsp:include>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<div class="contentBox">
				<div class="title">Warning Up:</div>
				<div id="demo">
					<table cellpadding="0" cellspacing="0" border="0"
						class="display dataTable" id="allan">
						<thead>
							<jsp:include page="/jsp/main/alarm/table_tr.jsp"></jsp:include>
						</thead>
						<tbody>
							<s:iterator value="warningLivemonitors" id="livemonitor">
								<tr>
									<jsp:include page="/jsp/main/alarm/table_td.jsp"></jsp:include>
									<td align="center"><img src="images/warning.png"
										title="warning" /></td>
								</tr>
							</s:iterator>
						</tbody>
						<tfoot>
							<tr class="bold">
								<jsp:include page="/jsp/main/alarm/table_bt.jsp"></jsp:include>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
