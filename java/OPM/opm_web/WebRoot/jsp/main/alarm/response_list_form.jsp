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
<div class="floatdiv">
	<div class="floatdiv_bg"></div>
	<!--遮罩背景 -->
	<div id="submitResponseList" class="floatdiv_content"
		style="width: 500px">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="13" height="33" class="td_topleft"></td>
				<td class="td_topcenter"><div class="floatdiv_top"
						onMouseDown="moveEvent(event,'submitResponse')">
						<span>响应</span>
						<!-- 弹出层标题 -->
						<a href="javascript:visclose('submitResponseList')"> <img
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
						<div class="submitResponseList">
							<form name="responselistaction"
								action="ResponsedListAction.action" method="post">
								<input type="hidden" id="reshids" name="reshids" />

								<table>
									<tr>
										<td width="20%">响应人：</td>
										<td width="80%"><input type="text"
											name="responsedPersonId" id="responsedPersonId"
											value="<sec:authentication property='name'/>" readonly /></td>
									</tr>
									<tr>
										<td>响应处理：</td>
										<td><textarea name="responsedHandling"></textarea></td>
									</tr>
									<tr>
										<td>备注：</td>
										<td><textarea name="responsedNote"></textarea></td>
									</tr>
									<tr>
										<td align="center" colspan="2"><a class="button"
											onclick="responselistaction.submit()"><span
												class="buttonRight">提交</span> </a> <a class="button"
											href="javascript:visclose('submitResponseList')"><span
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
				<td width="13" class="td_bottomright"></td>
			</tr>
		</table>
	</div>
</div>
