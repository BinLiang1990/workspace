function changeDelayTime() {
	var url = 'AjaxChangeDelayTimeAction.action';
    var params = {
    		id_1_new : $('#id_1_new').val(),
    		opmuser_userid : $('#opmuser_userid').val(),
    		delayTimeNew : $('#delayTimeNew').val()
    };
    $.post(url, params);
    var id = "#delayTime" + $('#id_1_new').val();
	$(id).text($('#delayTimeNew').val());
    visclose('submitWarningChange');
}