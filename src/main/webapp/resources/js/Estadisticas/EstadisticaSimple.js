function EstadisticaSimple() {};

EstadisticaSimple.prototype.atributoPressed = false;
EstadisticaSimple.prototype.habilidadPressed = false;
EstadisticaSimple.prototype.suertePressed = false;

EstadisticaSimple.prototype.init = function() {
	var self = this;

    $('#bonoDados').keyup(function() {
        if ($(this).val().match(/[0-9 -()+]+$/) === null) {
            $(this).val('0');
        }
    });
    
    $('#exitosObjetivo').keyup(function() {
        if ($(this).val().match(/^[0-9]+$/) === null) {
            $(this).val('0');
        }
    });
	
	$('.botonAtributo').click(function(){
		self.limpiarSeleccionAtributo();
		self.presionarBoton($(this));
		self.atributoPressed = true;
	});
	
	$('.botonHabilidad').click(function(){
		self.limpiarSeleccionHabilidad();
		self.presionarBoton($(this));
		self.habilidadPressed = true;
	});
	
	$('.botonSuerte').click(function(){
		self.limpiarSeleccionSuerte();
		self.presionarBoton($(this));
		self.suertePressed = true;
	});
	
	$('#sumarBono').click(function(){
		var bono = $('#bonoDados').val();
		$('#bonoDados').val(parseInt(bono) + 1);
	});
	
	$('#restarBono').click(function(){
		var bono = $('#bonoDados').val();
		if (parseInt(bono) > 0) {
			$('#bonoDados').val(parseInt(bono - 1));	
		}
	});
	
	$('#sumarObjetivo').click(function(){
		var objetivo = $('#exitosObjetivo').val();
		$('#exitosObjetivo').val(parseInt(objetivo) + 1);
	});
	
	$('#restarObjetivo').click(function(){
		var objetivo = $('#exitosObjetivo').val();
		$('#exitosObjetivo').val(parseInt(objetivo - 1));
	});

//	$('#radio_NORMAL').click(function(){
//		self.limpiarSeleccionSuerte();
//		$('.Suerte').hide();
//		$('.Atributos').show();
//		$('.Habilidades').show();
//	});
//	
//	$('#radio_CON_SUERTE').click(function(){
//		$('.Suerte').show();
//		$('.Atributos').show();
//		$('.Habilidades').show();
//	});
//	
//	$('#radio_FATIDICA').click(function(){
//		$('.Suerte').show();
//		$('.Atributos').hide();
//		$('.Habilidades').hide();
//		self.limpiarSeleccionAtributo();
//		self.limpiarSeleccionHabilidad();
//	});
	
	$('#menu').click(function(){
		$('#eleccion').val('menu');
	});
	
	this.bindearBotonTirar();
	
	$('#resetear').click(function(){
		self.limpiarSeleccionAtributo();
		self.limpiarSeleccionHabilidad();
		self.limpiarSeleccionSuerte();
		$('#bonoDados').val(0);
//		$("#radio_NORMAL").prop("checked", true);
		$('.Atributos').show();
		$('.Habilidades').show();
		$('.Resultados').hide();
		$('.Suerte').hide();
		$('.Resultados').hide();
	});
	
	//Ocultamos todos los divs que no van.
	$('.Resultados').hide();
//	$('.Suerte').hide();
};

EstadisticaSimple.prototype.bindearBotonTirar = function(){
	var self = this;
	$('#hacerTirada').bind('click', function(){
		$('.Errores').html('');
		$('.Resultados').hide();

		var boton = $(this);
		
		//Valido que esten seleccionados los datos:
		if ((self.atributoPressed && self.habilidadPressed && self.suertePressed)){
			
			self.presionarBoton(boton);
			
			$('.Container').block({ 
	        	message: '<h1>Procesando.</br>Por favor espere...</h1>', 
	            css: { border: '3px solid #a00' } 
	        });
	        
			var atributo = $('.botonAtributoPresionado').text();
			var habilidad = $('.botonHabilidadPresionado').text();
			var suerte = $('.botonSuertePresionado').text();
			$('#atributo').val(atributo);
			$('#habilidad').val(habilidad);
			$('#suerte').val(suerte);
		    
			$.ajax({
				type: "POST",
				url: "estadisticaSimple.do?decorator=none",
				data: $('#eSimpleForm').serialize(),
				dataType: "json",
				success: function(resultado) {
					$('.Resultados').show();
					self.soltarBoton(boton);
					$('.Container').unblock();
					
					$('#tablaEstadistica').highcharts({
				        chart: { type: 'area' },
				        title: { text: 'Cantidad de exitos por tirada' },
				        //subtitle: { text: 'Source: ESTADISTICA PURA' },
				        xAxis: {
				            allowDecimals: false,
				            title: { text: 'Cantidad de exitos' },
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
				        tooltip: { pointFormat: 'En una tirada {series.name} hubo<br/><b>{point.y:,.0f}</b> tiradas con {point.x} exitos' },
				        plotOptions: {
				            area: {
				                pointStart: resultado.minimoFatidico,
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
				            name: 'Normal',
				            data: resultado.exitosNormal
				        }, {
				            name: 'Con Suerte',
				            data: resultado.exitosSuerte
				        }, {
				            name: 'Fatidica',
				            data: resultado.exitosFatidico
				        }]
				    });
					
					//Resultados
					$('#promedioNormal').text(resultado.promedioExitosNormal);
					$('#probExitoNormal').text(resultado.probabilidadExitoNormal);
					$('#promedioSuerte').text(resultado.promedioExitosSuerte);
					$('#probExitoSuerte').text(resultado.probabilidadExitoSuerte);
					$('#promedioFatidico').text(resultado.promedioExitosFatidico);
					$('#probExitoFatidico').text(resultado.probabilidadExitoFatidico);
				},
				error: function(a, b) {
					alert("Se produjo un error al hacer el llamado de ajax.");
					self.soltarBoton(boton);
					$('.Container').unblock();
				}
			});
			
		} else {
			$('.Errores').append($.parseHTML('<span>¡Falta ingresar alguno de los datos necesarios!</span>'));
		}
	});
};

EstadisticaSimple.prototype.reiniciarAreaResultados = function(){
	var columnasResultado = $('.Resultados').size();
	for (var i = 0; i < columnasResultado - 1; i++) { //Itero entre todos menos el original
		$('.Resultados').last().parent().remove();
	}
	$('.Resultados').html('<table id="tablaResultados" border="1"><tbody><tr><td><div class="Dados" style="text-align:center"></div></td></tr></tbody></table>');
	$('#total').text('');
};

EstadisticaSimple.prototype.limpiarSeleccionAtributo = function(){
	$('.botonAtributoPresionado').each(function(){
		$(this).removeClass().addClass('botonAtributo');
	});
	this.atributoPressed = false;
};

EstadisticaSimple.prototype.limpiarSeleccionHabilidad = function(){
	$('.botonHabilidadPresionado').each(function(){
		$(this).removeClass().addClass('botonHabilidad');
	});
	this.habilidadPressed = false;
};

EstadisticaSimple.prototype.limpiarSeleccionSuerte = function(){
	$('.botonSuertePresionado').each(function(){
		$(this).removeClass().addClass('botonSuerte');
	});
	this.suertePressed = false;
};
/*
EstadisticaSimple.prototype.convertirALetras = function(numero){
	var letra = '';
	switch(numero){
		case 1:
			letra = 'F';
			break;
		case 2:
			letra = 'E';
			break;
		case 3:
			letra = 'D';
			break;
		case 4:
			letra = 'C';
			break;
		case 5:
			letra = 'B';
			break;
		case 6:
			letra = 'A';
			break;
	}
	
	return letra;
};

EstadisticaSimple.prototype.convertirANumeros = function(letra){
	var numero = 0;
	if (letra == 'A'){
		numero = 6;
	} else if (letra == 'B'){
		numero = 5;
	} else if (letra == 'C'){
		numero = 4;
	} else if (letra == 'D'){
		numero = 3;
	} else if (letra == 'E'){
		numero = 2;
	} else if (letra == 'F'){
		numero = 1;
	}
	
	return numero;
};
*/
EstadisticaSimple.prototype.presionarBoton = function(id){
	var estiloAnterior = $(id).attr('class');
	$(id).removeClass().addClass(estiloAnterior + 'Presionado');
	$(id).css('cursor', 'default');
	$(id).attr('disabled', 'disabled');
};

EstadisticaSimple.prototype.soltarBoton = function(id){
	var estiloPresionado = '.' + $(id).attr('class');
	var estiloSinPresionar = estiloPresionado.substring(1, estiloPresionado.length - 10);
	$(estiloPresionado).each(function(){
		$(this).removeClass().addClass(estiloSinPresionar);
		$(this).css('cursor', 'pointer');
		$(this).removeAttr('disabled');
	});
};