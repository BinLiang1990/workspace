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
	<div id="music_form" class="floatdiv_content" style="width: 500px;">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td width="13" height="33" class="td_topleft"></td>
				<td class="td_topcenter"><div class="floatdiv_top"
						onMouseDown="moveEvent(event,'music_form')">
						<span>报警来了！！！</span>
						<!-- 弹出层标题 -->
						<a onclick="javascript:playPause()"
							href="javascript:visclose('music_form')"> <img
							src="js/float/images/dialog_closebtn.gif"
							onMouseOver="this.src='js/float/images/dialog_closebtn_over.gif'"
							onMouseOut="this.src='js/float/images/dialog_closebtn.gif'" />
						</a>
					</div></td>
				<td width="13" class="td_topright"></td>
			</tr>
			<tr>
				<td width="13" class="td_centerleft"></td>
				<td height=300 class="td_centercenter"><div
						class="floatdiv_body">
						<!-- 弹出层内容开始 -->
						<video id="music" width="500" controls="controls"> <source
							src="music/music.webm" type="video/webm" /> Your browser does
						not support the video tag<br>
						Please choose chrome, forefox or opera </video>

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

