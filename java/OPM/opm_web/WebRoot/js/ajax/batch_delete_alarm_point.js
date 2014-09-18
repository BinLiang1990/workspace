function batchDeleteAlarmPoint() {
	var url = 'AjaxDeleteAListAction.action';
	var checkboxId = "";
	var checkbox = $("input[name='check']");
	for(var i = 0; i < checkbox.length; ++i) {
		if(checkbox[i].checked) {
			checkboxId += checkbox[i].value;
			checkboxId += ',';
		}
	}
    var params = {
		id : checkboxId,
		opmuser_userid : $('#opmuser_userid').val(),
    };
    $.post(url, params);
    for(i = 0; i < checkbox.length; ++i) {
    	if(checkbox[i].checked) {
    		var tr_id = "#tr" + checkbox[i].value;
    		$(tr_id).hide();
    	}
    }
	visclose('submitDeleteList');
}

function batchRecoveryAlarmPoint() {
	var url = 'AjaxRecoveryAListAction.action';
	var checkboxId = "";
	var checkbox = $("input[name='check']");
	for(var i = 0; i < checkbox.length; ++i) {
		if(checkbox[i].checked) {
			checkboxId += checkbox[i].value;
			checkboxId += ',';
		}
	}
    var params = {
		id : checkboxId,
		opmuser_userid : $('#opmuser_userid').val(),
    };
    $.post(url, params);
    for(i = 0; i < checkbox.length; ++i) {
    	if(checkbox[i].checked) {
    		var tr_id = "#tr" + checkbox[i].value;
    		$(tr_id).hide();
    	}
    }
	visclose('submitRecoveryList');
}