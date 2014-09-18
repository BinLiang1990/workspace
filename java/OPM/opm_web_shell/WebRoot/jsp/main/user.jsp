<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/jsp/common/refresh.jsp"></jsp:include>
<html>
<jsp:include page="/jsp/common/head.jsp"></jsp:include>
<script type="text/javascript">
	function getCheckedId() {
		var checkboxId = "";
		var checkbox = $("input[name='check']");
		for (var i = 0; i < checkbox.length; ++i) {
			if (checkbox[i].checked) {
				checkboxId += checkbox[i].value;
				checkboxId += ',';
			}
		}
		$('#checkedId').val(checkboxId);
		$('#checkedId').hide();
	}
</script>
<body>
	<div class="main">
		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/menu.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/userChange.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/userAdd.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/userDelete.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/changePwd.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/changeUserPwd.jsp"></jsp:include>
		<jsp:include page="/jsp/main/user/changeInfo.jsp"></jsp:include>

		<div class="contentBox">
			<br>
			<table cellpadding="0" cellspacing="0" border="0" class="display"
				id="usertable" width="100%">
				<thead>
					<tr>
						<th width="3%"></th>
						<th width="10%">登录帐号</th>
						<th width="15%">用户姓名</th>
						<th width="15%">用户组</th>
						<th width="20%">联系方式</th>
						<th>备注</th>
						<th>重置密码</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="opmusers" id="opmuser">
						<tr>
							<td>
							<input type="checkbox" name="check" id="id${opmuser.id }"
								value="${opmuser.id }">
							</td>
							<td align="center"><a
								href="javascript:visNew('submitUserChange')"
								onclick="setUsr('${opmuser.id}', '${ opmuser.userid }', '${ opmuser.usergroup }', '${ opmuser.username }', '${ opmuser.phone}', '${ opmuser.note}')"><s:property
										value='#opmuser.userid' /> </a>
							</td>
							<td align="center"><s:property value='#opmuser.username' />
							</td>
							<td align="center"><s:property value='#opmuser.usergroup' />
							</td>
							<td align="center"><s:property value='#opmuser.phone' />
							</td>
							<td align="center"><s:property value='#opmuser.note' />
							</td>
							<td align="center"><a onclick="setUid('${ opmuser.userid }')" href="javascript:visNew('submitUserPwdChange')">修改密码</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
			<br> 
			<input type="button"
				onclick="javascript:visNew('submitUserAdd')" value="添加用户"> 
			<input type="button"
				onclick="getCheckedId();javascript:visNew('submitUserDelete')" value="禁用用户"> 
		</div>
	</div>
</body>
</html>
