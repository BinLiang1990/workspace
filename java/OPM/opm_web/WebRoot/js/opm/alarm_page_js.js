function setAcp(lid, acceptedPerson) {
	document.getElementById("acplids").value = lid+",";
	document.getElementById("acceptedPerson").value = acceptedPerson;
}

function setAla(lid) {
		document.getElementById("alalids").value = lid+"";
}

function setRes(hid) {
	document.getElementById("reshid").value = hid;
}

function setResHL(hid, lid) {
	document.getElementById("reshid").value = hid;
	document.getElementById("lid").value = lid;

}

function playPause() {
	var myVideo = document.getElementsByTagName('video')[0];
	if (myVideo.paused)
		myVideo.play();
	else
		myVideo.pause();
}

function selectAceptlist() {
	var alist = document.getElementsByTagName("input");
	var blist = new Array();
	var j=0;
	for(var i =0; i < alist.length; i++)
		if(alist[i].type == "checkbox")
			if(alist[i].checked){
				blist[j] = alist[i].value;
				j++;
			}
	document.getElementById("acplids").value = blist;
}

function selectAlarmlist() {
	var alist = document.getElementsByTagName("input");
	var blist = new Array();
	var j=0;
	for(var i =0; i < alist.length; i++)
		if(alist[i].type == "checkbox")
			if(alist[i].checked){
				blist[j] = alist[i].value;
				j++;
			}
	document.getElementById("alalids").value = blist;
}

function selectResponslist() {
	var alist = document.getElementsByTagName("input");
	var blist = new Array();
	var j=0;
	for(var i =0; i < alist.length; i++)
		if(alist[i].type == "checkbox")
			if(alist[i].checked){
				blist[j] = alist[i].value;
				j++;
			}
	document.getElementById("reshids").value = blist;
}
