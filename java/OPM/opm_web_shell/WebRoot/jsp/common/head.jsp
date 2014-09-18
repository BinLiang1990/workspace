<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<base href="<%=basePath%>">

<title>OPM 3.0</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="OPM page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="js/datatable/css/demo_page.css"
	type="text/css"></link>
<link rel="stylesheet" href="js/datatable/css/demo_table.css"
	type="text/css"></link>
<link rel="stylesheet" href="js/datatable/css/demo_table_jui.css"
	type="text/css"></link>
<link rel="stylesheet"
	href="js/datatable/examples_support/themes/smoothness/jquery-ui-1.8.4.custom.css"
	type="text/css"></link>

<link type="image/x-icon" href="favicon.ico" rel="Shortcut Icon">
<link href="style/style.css" rel="stylesheet" media="screen" />
<script type="text/JavaScript" src="js/comm.js"></script>
<!--tree--->
<script type="text/JavaScript" src="js/tree/js/js4cnltreemenu.js"></script>
<link href="js/tree/css/css4cnltreemenu.css" rel="stylesheet"
	media="screen" />
<!--浮动层-->
<link href="js/float/style/main.css" rel="stylesheet" media="screen" />
<script type="text/JavaScript" src="js/float/main.js"></script>
<!--tab-->
<script type="text/javascript" src="js/tab/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="js/opm/opm_shell_js.js"></script>
<script type="text/javascript" src="js/opm/md5.js"></script>
<script type="text/javascript" src="js/opm/user_page_js.js"></script>

<script type="text/javascript" language="javascript"
	src="js/datatable/js/jquery.dataTables.js"></script>

<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('#usertable').dataTable({
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"bPaginate" : false,
			"bLengthChange" : false,
			"bFilter" : true,
			"bSort" : true,
			"bInfo" : false,
			"bAutoWidth" : false
		});
	});
</script>

</head>
