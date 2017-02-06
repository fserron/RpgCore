function Graficos() {};

Graficos.prototype.areas = function(div, resultado, minimo) {
	div.highcharts({
        chart: { type: 'area' },
        title: { text: 'Cantidad de daño por tirada' },
        //subtitle: { text: 'Source: ESTADISTICA PURA' },
        xAxis: {
            allowDecimals: false,
            title: { text: 'Cantidad de daño' },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        yAxis: {
            title: { text: 'Cantidad de tiradas' },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        tooltip: { pointFormat: 'En la tirada hubo<br/><b>{point.y:,.0f}</b> tiradas con {point.x} daño' },
        plotOptions: {
            area: {
                pointStart: minimo,
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 2,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
        },
        series: [{
            name: 'Distribucion',
            data: resultado
        }]
    });
}

Graficos.prototype.torta = function(div, mapaDano, detalle) {
	var data = [];
	var drilldown = [];
	
	$.each(mapaDano, function (name, y) {
		data.push({
			name: name,
			y: y * 100,
			drilldown: name
		});
	});
	$.each(detalle, function (key, value) {
		drilldown.push({
			name: key,
			id: key,
			data: value
		});
	});

	div.highcharts({
        chart: { type: 'pie' },
        title: { text: 'Cantidad de daño por tirada' },
//	    subtitle: {
//	        text: 'Click the slices to view versions. Source: netmarketshare.com.'
//	    },
	    plotOptions: {
	        series: {
	            dataLabels: {
	                enabled: true,
	                format: '{point.name}: {point.y:.4f}%'
	            }
	        }
	    },
	
	    tooltip: {
	        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
	        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
	    },
	
	    series: [{
	        name: 'Daño',
	        colorByPoint: true,
	        data: data
	    }],
	    drilldown: { series: drilldown }
	});
}