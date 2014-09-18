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
<body onload="setOperation('${operation}')">
	<div class="main">
		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<jsp:include page="/jsp/main/ophistory/menu.jsp"></jsp:include>


		<div class="content">
			<div class="contentBox">
				<!-- select history -->
				<jsp:include page="/jsp/main/ophistory/selectHistoryForm.jsp"></jsp:include>
				<div id="rotate">
					<ul>
						<li><a href="#fragment-1"><span>查看数据</span> </a></li>
					</ul>
					<div id="fragment-1">
						<div class="floatFilter">
							<ul>
								<li><a class="button"
									href="ExportOpHistoryExcelAction.action"><span
										class="buttonRight">导出报表</span> </a></li>
							</ul>
						</div>
						<div class="">
							<table cellpadding="0" cellspacing="0" border="0" class="display"
								id="ophistorytable" width="100%">
								<thead>
									<tr>
										<jsp:include page="/jsp/main/ophistory/table_th.jsp"></jsp:include>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="ophistories" id="ophistory">
										<tr>
											<jsp:include page="/jsp/main/ophistory/table_td.jsp"></jsp:include>
										</tr>
									</s:iterator>
								</tbody>
								<tfoot>
									<jsp:include page="/jsp/main/ophistory/table_th.jsp"></jsp:include>
								</tfoot>
							</table>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>















