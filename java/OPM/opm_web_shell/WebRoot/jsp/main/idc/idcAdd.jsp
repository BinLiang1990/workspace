<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<div class="floatdiv">
	<div class="floatdiv_bg"></div>
	<!--遮罩背景 -->
	<div id="submitIdcAdd" class="floatdiv_content" style="width:500px">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="13" height="33" class="td_topleft"></td>
				<td class="td_topcenter"><div class="floatdiv_top"
						onMouseDown="moveEvent(event,'submitIdcAdd')">
						<span>添加用户</span>
						<!-- 弹出层标题 -->
						<a href="javascript:visclose('submitIdcAdd')"> <img
							src="js/float/images/dialog_closebtn.gif"
							onMouseOver="this.src='js/float/images/dialog_closebtn_over.gif'"
							onMouseOut="this.src='js/float/images/dialog_closebtn.gif'" /> </a>
					</div></td>
				<td width="13" class="td_topright"></td>
			</tr>
			<tr>
				<td width="13" class="td_centerleft"></td>
				<td class="td_centercenter"><div class="floatdiv_body">
						<!-- 弹出层内容开始 -->
						<div class="submitIdcAdd">
							<form id="addidc" name="addidc" action="AddIdcAction.action" method="POST"
								enctype="multipart/form-data" onsubmit="return validate()">
								<table width="100%">
									<tr>
										<td><label>IDC：</label></td>
										<td>
										<input id="idc_name" name="idc_name" type="text" />
										</td>
									</tr>
									<tr>
										<td><label>IDC OPM 网站：</label></td>
										<td>
										http://<input id="idc_local" name="idc_local"
											type="text" />/opm_web

										</td>
									</tr>
									<tr>
										<td><label>IDC 图片：</label></td>
										<td>
										<s:file id="upload" name="upload" onchange="checkPic()"></s:file>
										</td>
									</tr>
									<tr>
										<td><label>备注：</label></td>
										<td>
										<textarea name="idc_description"></textarea></td>
									</tr>
									<tr>
										<td align="center" colspan="2"><a class="button"
											onclick="validate()"><span class="buttonRight">提交</span>
										</a> <a class="button" href="javascript:visclose('submitIdcAdd')"><span
												class="buttonRight">取消</span> </a></td>
									</tr>
								</table>
							</form>
							<!--
							<form name="addidc" action="AddIdcAction.action" method="post">
								<table width="100%">
									<tr>
										<td><label>IDC名：</label></td>
										<td><input id="idc_name" name="idc_name" type="text" />
										</td>
									</tr>


									<tr>
										<td><label>IDC OPM 网站：</label></td>
										<td>http://<input id="idc_local" name="idc_local"
											type="text" />/opm_web</td>
									</tr>
									<tr>
										<td><label>IDC 图片：</label></td>
										<td><input type="file" name="idc_image"></td>
									</tr>
									<tr>
										<td><label>备注：</label></td>
										<td><textarea name="idc_description"></textarea></td>
									</tr>
									<tr>
										<td align="center" colspan="2"><a class="button"
											onclick="addidc.submit()"><span class="buttonRight">提交</span>
										</a> <a class="button" href="javascript:visclose('submitIdcAdd')"><span
												class="buttonRight">取消</span> </a></td>
									</tr>
								</table>
							</form>
							-->
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
