function batchModifyAlarmPoint() {
	var url = 'AjaxBatchModifyAlarmPointAction.action';
	var checkboxId = "";
	var checkbox = $("input[name='check']");
	for (var i = 0; i < checkbox.length; ++i) {
		if (checkbox[i].checked) {
			checkboxId += checkbox[i].value;
			checkboxId += ',';
		}
	}
	var params = {
		id : checkboxId,
		opmuser_userid : $('#opmuser_userid').val(),
		description : $('#descriptionBatch').val(),
		type : $('#typeBatch').val(),
		criticality : $('#criticalityBatch').val(),
		firstAlarmPerson : $('#firstAlarmPersonBatch').val(),
		secondAlarmPerson : $('#secondAlarmPersonBatch').val(),
		alarmPerson : $('#firstAlarmPersonBatch').val() + "/" +
			$('#secondAlarmPersonBatch').val(),
		alarmLevel : $('#alarmLevelBatch').val(),
		alarmTimeRegion : $('#alarmTimeRegionBatch').val(),
		delayTime : $('#delayTimeBatch').val(),
		noAlarmStartTime : $('#noAlarmStartTimeBatch').val(),
		noAlarmEndTime : $('#noAlarmEndTimeBatch').val()
	};
	$.post(url, params);
	for (i = 0; i < checkbox.length; ++i) {
		if (checkbox[i].checked) {
			var descriptionId = "#description";
			var typeId = "#type";
			var criticalityId = "#criticality";
			var alarmPersonId = "#alarmPerson";
			var alarmLevelId = "#alarmLevel";
			var alarmTimeRegionId = "#alarmTimeRegion";
			var delayTimeId = "#delayTime";
			var convertedNoAlarmStartTimeId = "#convertedNoAlarmStartTime";
			var convertedNoAlarmEndTimeId = "#convertedNoAlarmEndTime";
			descriptionId += checkbox[i].value;
			typeId += checkbox[i].value;
			criticalityId += checkbox[i].value;
			alarmPersonId += checkbox[i].value;
			alarmLevelId += checkbox[i].value;
			alarmTimeRegionId += checkbox[i].value;
			delayTimeId += checkbox[i].value;
			convertedNoAlarmStartTimeId += checkbox[i].value;
			convertedNoAlarmEndTimeId += checkbox[i].value;
			if ($('#descriptionBatch').val().length > 0) {
				$(descriptionId).text($('#descriptionBatch').val());
			}
			if ($('#typeBatch').val().length > 0) {
				$(typeId).text($('#typeBatch').find("option:selected").text());
			}
			if ($('#criticalityBatch').val().length > 0) {
				$(criticalityId).text($('#criticalityBatch').val());
			}
			if ($('#firstAlarmPersonBatch').val().length > 0 || 
					$('#secondAlarmPersonBatch').val().length > 0) {
				$(alarmPersonId).text(params.alarmPerson);
			}
			if ($('#alarmLevelBatch').val().length > 0) {
				$(alarmLevelId).text($('#alarmLevelBatch').val());
			}
			if ($('#alarmTimeRegionBatch').val().length > 0) {
				$(alarmTimeRegionId).text($('#alarmTimeRegionBatch').val());
			}
			if ($('#delayTimeBatch').val() > 0) {
				$(delayTimeId).text($('#delayTimeBatch').val());
			}
			if ($('#noAlarmStartTimeBatch').val().length > 0) {
				$(convertedNoAlarmStartTimeId).text($('#noAlarmStartTimeBatch').val());
			}
			if ($('#noAlarmEndTimeBatch').val().length > 0) {
				$(convertedNoAlarmEndTimeId).text($('#noAlarmEndTimeBatch').val());
			}
		}
	}
	visclose('alarmpointList');
}

function checkAll() {
	var checkbox = $("input[name='check']");
	if ($("#id0").attr("checked") == "checked") {
		for (var i = 0; i < checkbox.length; ++i) {
			checkbox[i].checked = true;
		}
	} else {
		for (var i = 0; i < checkbox.length; ++i) {
			checkbox[i].checked = false;
		}
	}
}