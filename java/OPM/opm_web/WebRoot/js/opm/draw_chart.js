function draw_chart() {
	/**
	 * Visualize an HTML table using Highcharts. The top (horizontal) header is
	 * used for series names, and the left (vertical) header is used for
	 * category names. This function is based on jQuery.
	 *
	 * @param {Object}
	 *            table The reference to the HTML table to visualize
	 * @param {Object}
	 *            options Highcharts options
	 */
	Highcharts.visualize = function(table, options) {
		// the categories
		options.xAxis.categories = [];
		$('tbody th', table).each(function(i) {
			options.xAxis.categories.push(this.innerHTML);
		});

		// the data series
		options.series = [ {
			type : 'column'
		}, {
			type : 'spline'
		} ];
		$('tr', table)
				.each(
						function(i) {
							var tr = this;
							$('th, td', tr)
									.each(
											function(j) {
												if (j > 0) { // skip first
													// column
													if (i == 0) { // get the
														// name and
														// init the
														// series
														options.series[j - 1] = {
															name : this.innerHTML,
															data : []
														};
													} else { // add values
														options.series[j - 1].data
																.push(parseFloat(this.innerHTML));
														options.series[j - 1].type = 'column';
													}
												}
											});
						});

		$('tr', table)
				.each(
						function(i) {
							var tr = this;
							$('th, td', tr)
									.each(
											function(j) {
												if (j > 0) { // skip first
													// column
													if (i == 0) { // get the
														// name and
														// init the
														// series
														options.series[j] = {
															name : this.innerHTML,
															data : []
														};
													} else { // add values
														options.series[j].data
																.push(parseFloat(this.innerHTML));
														options.series[j].type = 'spline';
													}

												}
											});
						});

		var chart = new Highcharts.Chart(options);
	};

	var table = document.getElementById('charttable'), options = {
		chart : {
			renderTo : 'container',
		},
		title : {
			text : 'Alarm Number Chart'
		},
		xAxis : {},
		yAxis : {
			title : {
				text : 'Number'
			}
		},
		tooltip : {
			formatter : function() {
				return '<b>' + this.series.name + '</b><br/><b>' + this.y
						+ '</b>';
			}
		},
	};

	Highcharts.visualize(table, options);
}
