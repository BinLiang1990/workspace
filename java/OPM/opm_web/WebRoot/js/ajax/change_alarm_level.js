function changeAlarmLevel() {
	var url = 'AjaxChangeAlarmLevelAction.action';
    var params = {
    		id_1_al : $('#id_1_al').val(),
    		opmuser_userid : $('#opmuser_userid').val(),
    		alarmLevel_al : $('#alarmLevel_al').val(),
    		alarmTimeRegion_al : $('#alarmTimeRegion_al').val()
    };
    $.post(url, params);
    var alarmLevel_id = "#alarmLevel" + $('#id_1_al').val();
	$(alarmLevel_id).text($('#alarmLevel_al').val());
	var alarmTimeRegion_id = "#alarmTimeRegion" + $('#id_1_al').val();
	$(alarmTimeRegion_id).text($('#alarmTimeRegion_al').val());
    visclose('submitAlarmLevelChange');
}