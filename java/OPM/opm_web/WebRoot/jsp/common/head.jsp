<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<base href="<%=basePath%>">

<title>OPM 5 - IDC : <s:property value="#session.idc" /></title>

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
<link rel="stylesheet" href="js/tab/ui.tabs.css" type="text/css"
	media="print, projection, screen" />

<script type="text/javascript" src="js/tab/jquery-1.7.2.min.js"></script>
<script src="js/tab/ui.core.js" type="text/javascript"></script>
<script src="js/tab/ui.tabs.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$('#rotate > ul').tabs({
			fx : {
				opacity : 'toggle'
			}
		}).tabs('rotate', 2000);
	});
</script>
<!--date-->
<script src="js/DatePicker/WdatePicker.js" type="text/javascript"></script>

<script type="text/javascript" src="js/opm/nowtime.js"></script>
<script type="text/javascript" src="js/opm/alarm_page_js.js"></script>
<script type="text/javascript" src="js/opm/monitor_page_js.js"></script>
<script type="text/javascript" src="js/opm/user_page_js.js"></script>
<script type="text/javascript" src="js/opm/history_page_js.js"></script>
<script type="text/javascript" src="js/opm/music_on.js"></script>


<script type="text/javascript" src="js/hight_charts/js/highcharts.js"></script>
<script type="text/javascript"
	src="js/hight_charts/js/modules/exporting.js"></script>

<script type="text/javascript" src="js/opm/draw_chart.js"></script>
<script type="text/javascript" src="js/opm/create_table.js"></script>

<script type="text/javascript" language="javascript"
	src="js/datatable/js/jquery.dataTables.js"></script>

<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('#historytable').dataTable({

			"fnDrawCallback" : function(oSettings) {
				/* Need to redo the counters if filtered or sorted */
				if (oSettings.bSorted || oSettings.bFiltered) {
					this.$('td:first-child', {
						"filter" : "applied"
					}).each(function(i) {
						that.fnUpdate(i + 1, this.parentNode, 0, false, false);
					});
				}
			},
			"aoColumnDefs" : [ {
				"bSortable" : true,
				"aTargets" : [ 0 ]
			} ],

			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",

			"sScrollX" : "100%",
			"sScrollXInner" : "250%",
			"bScrollCollapse" : true,
			"aaSorting" : [ [ 8, "desc" ] ],
			"bSort" : true

		});

	});
</script>

<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('#ophistorytable').dataTable({
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers",
			"aaSorting" : [ [ 8, "desc" ] ]
		});
	});
</script>

<script type="text/javascript" language="javascript">
	$(document).ready(function() {
		$('#usertable').dataTable({
			"bJQueryUI" : true,
			"sPaginationType" : "full_numbers"
		});
	});
</script>

</head>
