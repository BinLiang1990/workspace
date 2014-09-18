function setUsr(id, uid, ugroup, uname, uphone, unote) {
	document.getElementById("id").value = id;
	document.getElementById("uid").value = uid;
	document.getElementById("ugroup").value = ugroup;
	document.getElementById("uname").value = uname;
	document.getElementById("uphone").value = uphone;
	document.getElementById("unote").value = unote;

}

function setUid(uid) {
	document.getElementById("user_uid").value = uid;
}

function checkchangeSelfPwd() {
	if (document.getElementById("cgpwd").value.toLowerCase() == document
			.getElementById("cgpwdag").value.toLowerCase()) {
		document.forms["changePwdForm"].submit();
		//document.getElementById("changePwdForm").submit();
	} else {
		alert("请输入相同密码");
	}
}

function checkchangeUserPwd() {
	if (document.getElementById("cgupwd").value.toLowerCase() == document
			.getElementById("cgupwdag").value.toLowerCase()) {
		document.forms["changeUserPwdForm"].submit();
		//document.getElementById("changePwdForm").submit();
	} else {
		alert("请输入相同密码");
	}
}
