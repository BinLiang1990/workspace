function setUser_Pwd() {
	window.open('index.jsp?uid=' + document.getElementById("j_username").value
			+ '&pwd=' + document.getElementById("j_password").value, "_self");
	// window.close();
}

function checkPic() {
	var location = document.getElementById("upload").value;
	var point = location.lastIndexOf(".");
	var type = location.substr(point);
	if (type == ".jpg" || type == ".gif" || type == ".JPG" || type == ".GIF"
			|| type == ".PNG" || type == ".png") {
		img = document.createElement("img");
		img.src = location;
		if (img.fileSize > 102400) {
			alert("图片尺寸请不要大于100KB");
			return false;
		} else
			return true;
	} else {
		alert("只能输入jpg或者gif格式或者png的图片");
		document.getElementById("upload").value = "";
		return false;
	}
	return true;
}

function validate() {
	var check = true;
	if (document.getElementById("idc_name").value == "") {
		alert("idc名字不能为空.");
		document.getElementById("idc_name").focus();
		check = false;
	}
	if (document.getElementById("idc_local").value == "") {
		alert("地址不能为空.");
		document.getElementById("idc_local").focus();
		check = false;
	}
	if (document.getElementById("upload").value == "") {
		alert("图片不能为空.");
		document.getElementById("upload").focus();
		check = false;
	}
	if (check == true) {
		document.forms["addidc"].submit();
	}
}
