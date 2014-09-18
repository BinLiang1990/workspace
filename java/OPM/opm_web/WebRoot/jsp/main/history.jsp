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
<body
	onload="setAllHistory('${ startTime }', '${ endTime }', '${ serviceName }','${type }', '${alarmLevel }', '${show_ok}')">
	<div class="main">

		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<jsp:include page="/jsp/main/history/menu.jsp"></jsp:include>
		<!-- 响应 -->
		<jsp:include page="/jsp/main/history/response_form.jsp"></jsp:include>

		<div class="content">
			<div class="contentBox">
				<!-- select history -->
				<jsp:include page="/jsp/main/history/selectHistoryForm.jsp"></jsp:include>
				<div id="rotate">
					<ul>
						<li><a onclick="" href="#fragment-1"><span>查看数据</span> </a></li>
						<li><a onclick="draw_chart()" href="#fragment-2"><span>查看图形</span>
						</a></li>

					</ul>
					<div id="fragment-1">
						<div class="floatFilter">
							<ul>
								<li>
									<form id="exportform"
										action="./ExportHistoryExcelAction.action" method="post">
										<input type="hidden" name="expdata"
											value=<s:property value='alarmPointStatusChangingHistories' /> />
										<a class="button" onclick="exportform.submit()"><span
											class="buttonRight">导出报表</span> </a>

									</form>
								</li>
							</ul>
						</div>
						<div id="demo">
							<table cellpadding="0" cellspacing="0" border="0" class="display"
								id="historytable" width="100%">
								<thead>
									<tr>
										<jsp:include page="/jsp/main/history/table_th.jsp"></jsp:include>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="alarmPointStatusChangingHistories"
										id="history" status="L">
										<tr>
											<jsp:include page="/jsp/main/history/table_td.jsp"></jsp:include>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>

						<!--  -->
						<!--  -->
					</div>

					<div id="fragment-2">
						<br>
						<div id="container"
							style="min-width: 1255px; height: 400px; margin: 0 auto"></div>
						<table id="charttable" style="display: none">
							<thead>
								<tr>
									<th>Date</th>
									<th>Alarm Number</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="dateAlarmCountMap" id="column">
									<br>
									<tr>
										<th><s:property value="key" /></th>
										<td><s:property value="value" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>

					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>















