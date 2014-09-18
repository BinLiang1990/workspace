<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<script type="text/javascript" src="js/ajax/batch_modify_alarm_point.js"></script>
<script type="text/javascript" src="js/ajax/batch_delete_alarm_point.js"></script>
<jsp:include page="/jsp/common/head.jsp"></jsp:include>
<script type="text/javascript">
function fillSearchConditions() {
	var commond = "${requestScope.commond}";
	if(commond != "fill") {
		return;
	}
	var serverName = "${requestScope.serverName}";
	$('#SearchConditionsAction_serverName').val(serverName);
	var serviceName = "${requestScope.serviceName}";
	var serviceNameList = serviceName.split(';');
	var i = 0, j = 0;
	while(i < $('[name="serviceName"]').length) {
		$('[name="serviceName"]')[i].checked = false;
		while(j < serviceNameList.length - 1) {
			if($('[name="serviceName"]')[i].value == serviceNameList[j]) {
				$('[name="serviceName"]')[i].checked = true;
				++j;
			}
			break;
		}
		++i;
	}
	var componentName = "${requestScope.componentName}";
	$('#SearchConditionsAction_componentName').val(componentName);
	var alarmLevel = "${requestScope.alarmLevel}";
	var alarmLevelList = alarmLevel.split(';');
	i = 2;
	j = 0;
	while(i < 6) {
		$('[name="alarmLevel"]')[i].checked = false;
		while(j < alarmLevelList.length - 1) {
			if($('[name="alarmLevel"]')[i].value == alarmLevelList[j]) {
				$('[name="alarmLevel"]')[i].checked = true;
				++j;
			}
			break;
		}
		++i;
	}
	var alarmTimeRegion = "${requestScope.alarmTimeRegion}";
	var alarmTimeRegionList = alarmTimeRegion.split(';');
	i = 2;
	j = 0;
	while(i < 5) {
		$('[name="alarmTimeRegion"]')[i].checked = false;
		while(j < alarmTimeRegionList.length - 1) {
			if($('[name="alarmTimeRegion"]')[i].value == alarmTimeRegionList[j]) {
				$('[name="alarmTimeRegion"]')[i].checked = true;
				++j;
			}
			break;
		}
		++i;
	}
	var delayTimeLow = "${requestScope.delayTimeLow}";
	var delayTimeHigh = "${requestScope.delayTimeHigh}";
	$('#SearchConditionsAction_delayTimeLow').val(delayTimeLow);
	$('#SearchConditionsAction_delayTimeHigh').val(delayTimeHigh);
	var status = "${requestScope.status}";
	var statusList = status.split(';');
	i = j = 0;
	while(i < 6) {
		$('[name="status"]')[i].checked = false;
		while(j < statusList.length - 1) {
			if($('[name="status"]')[i].value == statusList[j]) {
				$('[name="status"]')[i].checked = true;
				++j;
			}
			break;
		}
		++i;
	}
}
</script>
<body onload="fillSearchConditions()">
	<!--响应时间调整-->
	<jsp:include page="/jsp/main/monitor/delayTime.jsp"></jsp:include>

	<!-- 响应级别 -->
	<jsp:include page="/jsp/main/monitor/alarmLevel.jsp"></jsp:include>

	<!--报警点-->
	<jsp:include page="/jsp/main/monitor/alarmpoint.jsp"></jsp:include>
	<jsp:include page="/jsp/main/search/alarmpointList.jsp"></jsp:include>

	<!-- 忽略报警 -->
	<jsp:include page="/jsp/main/monitor/noAlarmEnd.jsp"></jsp:include>

	<!--  删除报警点 -->
	<jsp:include page="/jsp/main/monitor/delete_list_form.jsp"></jsp:include>
	
	<!-- 恢复报警点 -->
	<jsp:include page="/jsp/main/monitor/recovery_list_form.jsp"></jsp:include>
	<div class="main">
		<jsp:include page="/jsp/common/top.jsp"></jsp:include>
		<jsp:include page="/jsp/main/search/menu.jsp"></jsp:include>
		<jsp:include page="/jsp/main/search/main.jsp"></jsp:include>
		<div class="content">
			<div class="contentBox">
				<table id="allan" class="display dataTable" cellspacing="0"
					cellpadding="0" border="0" style="table-layout:fixed;">
					<thead>
						<tr>
							<jsp:include page="/jsp/main/search/table_th.jsp"></jsp:include>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="searchResult" id="livemonitor">
							<tr id="tr${livemonitor.id }">
								<td height="25" align="center"><input type="checkbox"
									name="check" id="id${livemonitor.id }"
									value="${livemonitor.id }"></td>
								<td align="center"><s:property value='id' /></td>
								<td align="center"><s:property
										value='serverIpaddress' /></td>
								<td align="center"><s:property value='serviceName' /></td>
								<td align="center"><a
									href="javascript:visNew('submitWarningSite')"
									onclick="setAP('${livemonitor.id}', '${serviceName }', '${ livemonitor.componentName }', '${ ip }',
										'${ livemonitor.description }', '${ livemonitor.type }', '${ livemonitor.criticality }', '${ livemonitor.alarmPerson }',
										'${ livemonitor.alarmLevel }', '${ livemonitor.alarmTimeRegion }', '${ livemonitor.delayTime }');">
										<s:property value="componentName" />
								</a></td>
								<td align="center"><span id="description${livemonitor.id }"><s:property
											value='description' /></span></td>
								<td align="center"><span id="type${livemonitor.id }"><s:property
											value='convertedType' /></span></td>
								<td align="center"><span id="criticality${livemonitor.id }"><s:property
											value='criticality' /></span></td>
								<td align="center"><s:property value='convertedCSTTime' />
								</td>
								<td align="center"><a
									href="./GetAPointHistory.action?lid=<s:property value='id' />">查看历史</a>
								</td>
								<td align="center"><span id="alarmPerson${livemonitor.id }"><s:property
											value="alarmPerson" /></span></td>
								<td align="center"><a
									href="javascript:visNew('submitAlarmLevelChange')"
									onclick="setAl('${livemonitor.id}', '${ livemonitor.alarmLevel }', '${ livemonitor.alarmTimeRegion }')">
										<span id="alarmLevel${livemonitor.id }"><s:property
												value="alarmLevel" /></span> / <span
										id="alarmTimeRegion${livemonitor.id }"><s:property
												value="alarmTimeRegion" /></span>
								</a></td>
								<td align="center"><a
									href="javascript:visNew('submitWarningChange')"
									onclick="setDe('${livemonitor.id}', '${ livemonitor.delayTime }')">
										<span id="delayTime${livemonitor.id}"><s:property
												value="delayTime" /></span> sec
								</a></td>
								<td align="center"><a
									href="javascript:visNew('submitnoAlarmEndTimeChange')"
									onclick="setnal('${livemonitor.id}', '${ livemonitor.convertedNoAlarmStartTime }','${ livemonitor.convertedNoAlarmEndTime }')">
										从<span id="convertedNoAlarmStartTime${livemonitor.id}"><s:property
												value="convertedNoAlarmStartTime" /></span>到<span
										id="convertedNoAlarmEndTime${livemonitor.id}"><s:property
												value="convertedNoAlarmEndTime" /></span>
								</a></td>
								<td align="center"><s:property value="statusPic"
										escape="false" /></td>
							</tr>
						</s:iterator>
						<tr>
							<td><input type="checkbox" id="id0" value="id0"
								onclick="checkAll()"></td>
							<td></td>
							<td><a href="javascript:visNew('alarmpointList')">批量修改</a></td>
							<td colspan="2"><a
								href="javascript:visNew('submitDeleteList')">删除报警点</a></td>
						</tr>

					</tbody>


					<!--  
				    -->
				</table>
			</div>

		</div>
	</div>
</body>
</html>