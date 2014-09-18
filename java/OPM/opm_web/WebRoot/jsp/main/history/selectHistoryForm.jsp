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
	<form name="searchHistoryForm" action="GetHistoryAction.action"
		method="post">
		<ul>

			<li>开始时间： <input type="text" size="10" onfocus="WdatePicker()"
				name="startTime" id="startTime" />
			</li>

			<li>结束时间： <input type="text" size="10" onfocus="WdatePicker()"
				name="endTime" id="endTime" /></li>
			<li>系统： <!--
			<input type="text" value="" list="serviceNames"
				name="serviceName" id="serviceName" />
				<datalist id="serviceNames">
				<s:iterator value="serviceNames" status="s" id="serviceName">
					<option value="<s:property value='#serviceName' />">
						<s:property value='#serviceName' />
					</option>
				</s:iterator>
				</datalist>
			--> <select name="serviceName" id="serviceName">
					<option></option>
					<s:iterator value="serviceNames" status="s" id="serviceName">
						<option value="<s:property value='#serviceName' />">
							<s:property value='#serviceName' />
						</option>
					</s:iterator>
			</select>
			</li>

			<li>种类： <select name="type" id="type">
					<option></option>
					<option value="module">模块</option>
					<option value="function">功能</option>
					<option value="data">数据</option>
			</select>
			</li>
			<li>报警级别： <select name="alarmLevel" id="alarmLevel">
					<option></option>
					<option value="active">Active</option>
					<option value="warning">Warning</option>
					<option value="inactive">Inactive</option>
			</select>
			</li>
			<li><input type="checkbox" id="show_ok" name="show_ok" value="">忽略非报警状态</li>

		</ul>
		<ul>
			<li><input type="button" onclick="searchHistoryForm.submit()"
				value="搜索历史"></li>
		</ul>
	</form>
</div>
