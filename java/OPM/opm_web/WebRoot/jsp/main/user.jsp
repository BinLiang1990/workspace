<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<jsp:include page="/jsp/common/head.jsp"></jsp:include>

<body>
	<div class="main">
		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/menu.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/userChange.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/userAdd.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/changePwd.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/changeInfo.jsp"></jsp:include>

		<div class="contentBox">
			<br>
			<table cellpadding="0" cellspacing="0" border="0" class="display"
				id="usertable" width="100%">
				<thead>
					<tr>
						<th width="10%">登录帐号</th>
						<th width="15%">用户姓名</th>
						<th width="15%">用户组</th>
						<th width="20%">联系方式</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="opmusers" id="opmuser">
						<tr>
							<td align="center"><a
								href="javascript:visNew('submitUserChange')"
								onclick="setUsr('${opmuser.id}', '${ opmuser.userid }', '${ opmuser.usergroup }', '${ opmuser.username }', '${ opmuser.phone}', '${ opmuser.note}')"><s:property
										value='#opmuser.userid' /> </a></td>
							<td align="center"><s:property value='#opmuser.username' /></td>
							<td align="center"><s:property value='#opmuser.usergroup' />
							</td>
							<td align="center"><s:property value='#opmuser.phone' /></td>
							<td align="center"><s:property value='#opmuser.note' /></td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
					<tr>
						<th width="10%">登录帐号</th>
						<th width="15%">用户姓名</th>
						<th width="15%">用户组</th>
						<th width="20%">联系方式</th>
						<th>备注</th>
					</tr>
				</tfoot>
			</table>
			<br> <input type="button"
				onclick="javascript:visNew('submitUserAdd')" value="添加用户"> <input
				type="button" onclick="javascript:visNew('submitInfoChange')"
				value="更新信息"> <input type="button"
				onclick="javascript:visNew('submitPwdChange')" value="修改密码">

		</div>
	</div>
	</div>
</body>
</html>
