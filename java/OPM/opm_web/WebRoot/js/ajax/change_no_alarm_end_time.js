function changeNoAlarmEndTime() {
	var url = 'AjaxChangeNoAlarmEndTimeAction.action';
    var params = {
    		nae_id : $('#nae_id').val(),
    		opmuser_userid : $('#opmuser_userid').val(),
    		noAlarmStartTime : $('#noAlarmStartTime').val(),
    		noAlarmEndTime : $('#noAlarmEndTime').val()
    };
    $.post(url, params);
    var noAlarmStartTime_id = "#convertedNoAlarmStartTime" + $('#nae_id').val();
    $(noAlarmStartTime_id).text($('#noAlarmStartTime').val());
    var noAlarmEndTime_id = "#convertedNoAlarmEndTime" + $('#nae_id').val();
    $(noAlarmEndTime_id).text($('#noAlarmEndTime').val());
    visclose('submitnoAlarmEndTimeChange');
}