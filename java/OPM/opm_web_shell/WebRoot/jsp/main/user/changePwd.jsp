<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="floatdiv">
	<div class="floatdiv_bg"></div>
	<!--遮罩背景 -->
	<div id="submitPwdChange" class="floatdiv_content"
		style="width:500px">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="13" height="33" class="td_topleft"></td>
				<td class="td_topcenter"><div class="floatdiv_top"
						onMouseDown="moveEvent(event,'submitPwdChange')">
						<span>修改密码</span>
						<!-- 弹出层标题 -->
						<a href="javascript:visclose('submitPwdChange')"> <img
							src="js/float/images/dialog_closebtn.gif"
							onMouseOver="this.src='js/float/images/dialog_closebtn_over.gif'"
							onMouseOut="this.src='js/float/images/dialog_closebtn.gif'" /> </a>
					</div>
				</td>
				<td width="13" class="td_topright"></td>
			</tr>
			<tr>
				<td width="13" class="td_centerleft"></td>
				<td class="td_centercenter"><div class="floatdiv_body">
						<!-- 弹出层内容开始 -->
						<div class="submitPwdChange">
							<form name="changePwdForm" action="ChangePwdAction.action" method="post">
							<input type="hidden" size="4" id="uid" name="uid" value="<sec:authentication property='name'/>"  />
							<table width="100%">
								<tr>
									<td width="20%" align="right">新密码：</td>
									<td width="80%"><input type="password" id="cgpwd" name="pwd"
										/></td>
								</tr>
								<tr>
									<td align="right">确认新密码：</td>
									<td><input type="password"  id="cgpwdag" name="pwdag"/></td>
								</tr>
								<tr>
									<td align="center" colspan="2"><a class="button" onclick="checkchangeSelfPwd()"><span
											class="buttonRight">提交</span> </a> <a class="button"
										href="javascript:visclose('submitPwdChange')"><span
											class="buttonRight">取消</span> </a>
									</td>
								</tr>
							</table>
							</form>
						</div>
						<!-- 弹出层内容结束 -->
					</div>
				</td>
				<td width="13" class="td_centerright"></td>
			</tr>
			<tr>
				<td width="13" height="13" class="td_bottomleft"></td>
				<td class="td_bottomcenter"></td>
				<td width="13" class="td_bottomright"></td>
			</tr>
		</table>
	</div>
</div>
