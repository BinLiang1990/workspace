function setStartTime(startTime) {
	document.getElementById("startTime").value = startTime;
}

function setEndTime(endTime) {
	document.getElementById("endTime").value = endTime;
}

function setServiceName(serviceName) {
	document.getElementById("serviceName").value = serviceName;
}

function setType(type) {
	document.getElementById("type").value = "function";
}

function setAlarmLevel(alarmLevel) {
	document.getElementById("alarmLevel").value = alarmLevel;
}

function setShow_ok(show_ok) {
	if (show_ok != "") {
		document.getElementById("show_ok").checked = true;
	}
}

function setAllHistory(startTime, endTime, serviceName, type, alarmLevel, show_ok) {
	document.getElementById("startTime").value = startTime;
	document.getElementById("endTime").value = endTime;
	document.getElementById("serviceName").value = serviceName;
	document.getElementById("type").value = type;
	document.getElementById("alarmLevel").value = alarmLevel;
	if (show_ok != "") {
		document.getElementById("show_ok").checked = true;
	}

}

function setOperation(operation) {
	document.getElementById("operation").value = operation;
}
