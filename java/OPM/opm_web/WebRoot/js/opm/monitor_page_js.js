function setAP(id_1, scn, cpn, sip, description, type, criticality, alarmPerson,alarmLevel,
		alarmTimeRegion, delayTime) {
	description = $('#description' + id_1).text();
	type = $('#type' + id_1).text();
	criticality = $('#criticality' + id_1).text();
	alarmPerson = $('#alarmPerson' + id_1).text();
	alarmLevel = $('#alarmLevel' + id_1).text();
	alarmTimeRegion = $('#alarmTimeRegion' + id_1).text();
	delayTime = $('#delayTime' + id_1).text();
	noAlarmStartTime = $('#convertedNoAlarmStartTime' + id_1).text();
	noAlarmEndTime = $('#convertedNoAlarmEndTime' + id_1).text();
	
	document.getElementById("id_1").value = id_1;
	document.getElementById("scn").value = scn;
	document.getElementById("cpn").value = cpn;
	document.getElementById("sip").value = sip;
	document.getElementById("description").value = description;
	document.getElementById("type").value = type;
	document.getElementById("criticality").value = criticality;
	var alarmPersonSplit = alarmPerson.split('/');
	document.getElementById("firstAlarmPerson").value = alarmPersonSplit[0];
	document.getElementById("secondAlarmPerson").value = alarmPersonSplit[1] == undefined ? "" : alarmPersonSplit[1];
	document.getElementById("alarmLevel").value = alarmLevel;
	document.getElementById("alarmTimeRegion").value = alarmTimeRegion;
	document.getElementById("delayTime").value = delayTime;
	document.getElementById("noAlarmStartTimeComponent").value = noAlarmStartTime;
	document.getElementById("noAlarmEndTimeComponent").value = noAlarmEndTime;
}

function setDe(id_1, delayTime) {
	delayTime = $('#delayTime' + id_1).text();
	document.getElementById("id_1_new").value = id_1;
	document.getElementById("delayTimeOri").value = delayTime;
	document.getElementById("delayTimeNew").value = delayTime;

}

function setAl(id_1, alarmLevel, alarmTimeRegion) {
	alarmLevel = $('#alarmLevel' + id_1).text();
	alarmTimeRegion = $('#alarmTimeRegion' + id_1).text();
	document.getElementById("id_1_al").value = id_1;
	document.getElementById("alarmLevel_al").value = alarmLevel;
	document.getElementById("alarmTimeRegion_al").value = alarmTimeRegion;

}

function setnal(nae_id, noAlarmStartTime, noAlarmEndTime) {
	if (noAlarmStartTime == null)
		noAlarmStartTime = "";
	if (noAlarmEndTime == null)
		noAlarmEndTime = "";
	noAlarmStartTime = $('#convertedNoAlarmStartTime' + nae_id).text();
	noAlarmEndTime = $('#convertedNoAlarmEndTime' + nae_id).text();
	document.getElementById("nae_id").value = nae_id;
	document.getElementById("noAlarmStartTime").value = noAlarmStartTime;
	document.getElementById("noAlarmEndTime").value = noAlarmEndTime;
}

function selectdeletelist() {
	var alist = document.getElementsByTagName("input");
	var blist = new Array();
	var j = 0;
	for ( var i = 0; i < alist.length; i++)
		if (alist[i].type == "checkbox")
			if (alist[i].checked) {
				blist[j] = alist[i].value;
				j++;
			}
	document.getElementById("dellids").value = blist;
}

function selectdelayTimelist() {
	var alist = document.getElementsByTagName("input");
	var blist = new Array();
	var j = 0;
	for ( var i = 0; i < alist.length; i++)
		if (alist[i].type == "checkbox")
			if (alist[i].checked) {
				blist[j] = alist[i].value;
				j++;
			}
	document.getElementById("delaylids").value = blist;
}

function selectalarmLevellist() {
	var alist = document.getElementsByTagName("input");
	var blist = new Array();
	var j = 0;
	for ( var i = 0; i < alist.length; i++)
		if (alist[i].type == "checkbox")
			if (alist[i].checked) {
				blist[j] = alist[i].value;
				j++;
			}
	document.getElementById("alalids").value = blist;
}

function selectnoAlarmTimelist() {
	var alist = document.getElementsByTagName("input");
	var blist = new Array();
	var j = 0;
	for ( var i = 0; i < alist.length; i++)
		if (alist[i].type == "checkbox")
			if (alist[i].checked) {
				blist[j] = alist[i].value;
				j++;
			}
	document.getElementById("naelids").value = blist;
}

function ischeckNum() {
	var num = document.getElementById('delayTimeNew').value;
	if (num) {
		if (!isNaN(num)) {
			return false;
		} else {
			alert('你输入的数据不是数字');
			document.getElementById('delayTimeNew').value = 600;
			myfm.isnum.select();
			return false;
		}
	} else {
		alert('需输入内容');
		myfm.isnum.focus();
	}
}

function ischeckListNum() {
	var num = document.getElementById('delayTimeListNew').value;
	if (num) {
		if (!isNaN(num)) {
			return false;
		} else {
			alert('你输入的数据不是数字');
			document.getElementById('delayTimeListNew').value = 600;
			myfm.isnum.select();
			return false;
		}
	} else {
		alert('需输入内容');
		myfm.isnum.focus();
	}
}
