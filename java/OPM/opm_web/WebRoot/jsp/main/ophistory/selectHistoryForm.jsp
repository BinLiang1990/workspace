<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<div class="floatFilter" style="padding-left: 8px">
	<form name="searchHistoryForm" action="GetOpHistoryAction.action"
		method="post">
		<ul>

			<li>开始时间：<input type="text" size="10" onfocus="WdatePicker()"
				name="startTime" id="startTime"
				value="<s:property value="startTime"/>" /></li>
			<li>结束时间：<input type="text" size="10" onfocus="WdatePicker()"
				name="endTime" id="endTime" value="<s:property value="endTime"/>" />
			</li>
			<li>用户名： <select name="userName" id="userName">
					<option></option>
					<s:iterator value="opmusers" status="s" id="opmuser">
						<option value="<s:property value='#opmuser.username' />">
							<s:property value='#opmuser.username' />
						</option>
					</s:iterator>
			</select> <!--
			<input type="text"
				list="users" name="userName" id="userName"
				value="<s:property value="userName"/>"
				/> <datalist id="users">
				<s:iterator value="opmusers" status="s" id="opmuser">
					<option value="<s:property value='#opmuser.username' />">
						<s:property value='#opmuser.username' />
					</option>
				</s:iterator>
				<option value="其他"></option>
				</datalist>
				-->
			</li>

			<li>操作类别：<select name="operation" id="operation">
					<option value=""></option>
					<option value="change alarm level">更改报警级别</option>
					<option value="change delay time">调整响应时间</option>
					<option value="delete node">删除报警点</option>
					<option value="set no alarm time">设定报警忽略</option>
			</select></li>
			<li>操作机器：<input type="text" id="livemonitorId"
				name="livemonitorId" value="<s:property value="livemonitorId"/>" />
			</li>
			<li><a class="button" onclick="searchHistoryForm.submit()"><span
					class="buttonRight">搜索历史</span> </a></li>

		</ul>
	</form>
</div>
