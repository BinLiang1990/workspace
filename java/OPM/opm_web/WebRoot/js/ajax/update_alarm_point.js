function updateAlarmPoint() {
	var url = 'AjaxUpdateAlarmPointAction.action';
	var params = {
		id_1 : $('#id_1').val(),
		opmuser_userid : $('#opmuser_userid').val(),
		delayTime : $('#delayTime').val(),
		alarmLevel : $('#alarmLevel').val(),
		type : $('#type').val(),
		criticality : $('#criticality').val(),
		description : $('#description').val(),
		alarmTimeRegion : $('#alarmTimeRegion').val(),
		firstAlarmPerson : $('#firstAlarmPerson').val(),
		secondAlarmPerson : $('#secondAlarmPerson').val(),
		alarmPerson : $('#firstAlarmPerson').val() + "/" + $('#secondAlarmPerson').val(),
		noAlarmStartTimeComponent : $('#noAlarmStartTimeComponent').val(),
		noAlarmEndTimeComponent : $('#noAlarmEndTimeComponent').val()
	};
	$.post(url, params);
	// modify result.jsp
	var description_id = "#description" + $('#id_1').val();
	$(description_id).text($('#description').val());
	var type_id = "#type" + $('#id_1').val();
	$(type_id).text($('#type').find("option:selected").text());
	var criticality_id = "#criticality" + $('#id_1').val();
	$(criticality_id).text($('#criticality').val());
	var alarmPerson_id = "#alarmPerson" + $('#id_1').val();
	$(alarmPerson_id).text($('#firstAlarmPerson').val() + '/' + 
			$('#secondAlarmPerson').val());
	var alarmLevel_id = "#alarmLevel" + $('#id_1').val();
	$(alarmLevel_id).text($('#alarmLevel').val());
	var alarmTimeRegion_id = "#alarmTimeRegion" + $('#id_1').val();
	$(alarmTimeRegion_id).text($('#alarmTimeRegion').val());
	var delayTime_id = "#delayTime" + $('#id_1').val();
	$(delayTime_id).text($('#delayTime').val());
	var convertedNoAlarmStartTime_id = "#convertedNoAlarmStartTime"
			+ $('#id_1').val();
	$(convertedNoAlarmStartTime_id).text($('#noAlarmStartTimeComponent').val());
	var convertedNoAlarmEndTime_id = "#convertedNoAlarmEndTime"
			+ $('#id_1').val();
	$(convertedNoAlarmEndTime_id).text($('#noAlarmEndTimeComponent').val());
	visclose('submitWarningSite');
}