<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="floatdiv">
	<div class="floatdiv_bg"></div>
	<!--遮罩背景 -->
	<div id="submitUserChange" class="floatdiv_content"
		style="width: 500px">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="13" height="33" class="td_topleft"></td>
				<td class="td_topcenter"><div class="floatdiv_top"
						onMouseDown="moveEvent(event,'submitUserChange')">
						<span>修改用户</span>
						<!-- 弹出层标题 -->
						<a href="javascript:visclose('submitUserChange')"> <img
							src="js/float/images/dialog_closebtn.gif"
							onMouseOver="this.src='js/float/images/dialog_closebtn_over.gif'"
							onMouseOut="this.src='js/float/images/dialog_closebtn.gif'" />
						</a>
					</div></td>
				<td width="13" class="td_topright"></td>
			</tr>
			<tr>
				<td width="13" class="td_centerleft"></td>
				<td class="td_centercenter"><div class="floatdiv_body">
						<!-- 弹出层内容开始 -->
						<div class="submitUserChange">
							<form name="changeuser" action="ModifyUserAction.action"
								method="post">
								<input type="hidden" name="id" id="id" readonly />
								<table width="100%">
									<tr>
										<td><label>登录名</label></td>
										<td><input type="text" name="uid" id="uid" readonly /></td>
									</tr>

									<tr>
										<td><label>用户组</label></td>
										<td><select name="ugroup" id="ugroup">
												<%
													Map<String, String> map = new HashMap<String, String>();
													map.put("ROLE_VIEWER", "viewer");
													map.put("ROLE_GS", "gs");
													map.put("ROLE_OPS", "ops");
													map.put("ROLE_ADMIN", "admin");
													request.setAttribute("map", map);
												%>
												<option value="ROLE_VIEWER">${map["ROLE_VIEWER"]}</option>
												<option value="ROLE_GS">${map["ROLE_GS"]}</option>
												<option value="ROLE_OPS">${map["ROLE_OPS"]}</option>
												<option value="ROLE_ADMIN">${map["ROLE_ADMIN"]}</option>
										</select></td>
									</tr>
									<tr>
										<td><label>用户名</label></td>
										<td><input id="uname" name="uname" type="text" /></td>
									</tr>
									<tr>
										<td><label>电话</label></td>
										<td><input id="uphone" name="uphone" type="text" /></td>
									</tr>
									<tr>
										<td><label>备注</label></td>
										<td><textarea name="unote" id="unote">
											</textarea></td>
									</tr>
									<tr>
										<td align="center" colspan="2"><a class="button"
											onclick="changeuser.submit()"><span class="buttonRight">提交</span>
										</a> <a class="button"
											href="javascript:visclose('submitUserChange')"><span
												class="buttonRight">取消</span> </a></td>
									</tr>
								</table>
							</form>
						</div>
						<!-- 弹出层内容结束 -->
					</div></td>
				<td width="13" class="td_centerright"></td>
			</tr>
			<tr>
				<td width="13" height="13" class="td_bottomleft"></td>
				<td class="td_bottomcenter"></td>
				<td width="13" class="td_bottomright">fr</td>
			</tr>
		</table>
	</div>
</div>
