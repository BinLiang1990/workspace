<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/jsp/common/head.jsp"></jsp:include>
<html>
<jsp:include page="/jsp/common/refresh.jsp"></jsp:include>

<body>
	<div class="main">
		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<jsp:include page="/jsp/main/idc/menu.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/userChange.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/userAdd.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/changePwd.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/changeInfo.jsp"></jsp:include>

		<div class="contentBox">
			<br>
			<div class="table">
				<table width="100%" style="margin:12px auto" class="contentTable">
					<thead>
						<tr>
							<th width=30%>点击进入</th>
							<th>数据中心</th>
							<th>OPM地址</th>
							<th>数据中心描述</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="opmidcs" id="opmidc">
							<tr>
								<td align="center"><a
									href="http://<s:property value='#opmidc.location' />/opm_web/GetAllAlarmPointAction.action?idc=<s:property value='#opmidc.name' />"
									target="_blank">
										<img src="./idc_image/<s:property value='#opmidc.image' />"
										border="0" width="250" height="250"> </a></td>
								<td align="center"><s:property value='#opmidc.name' />
								</td>
								<td align="center"><s:property value='#opmidc.location' />
								</td>
								<td align="center"><s:property value='#opmidc.description' />
								</td>
							</tr>
						</s:iterator>
					</tbody>
					<tfoot>
						<tr>
							<td><input type="button"
								onclick="javascript:visNew('submitIdcAdd')" value="添加IDC">
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>

	<div class="main">
		<jsp:include page="/jsp/main/idc/idcAdd.jsp"></jsp:include>
	</div>

</body>
</html>
