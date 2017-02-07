function EstadisticaCombate() {};

EstadisticaCombate.prototype.carasPressed = false;
EstadisticaCombate.prototype.atributoPressed = false;
EstadisticaCombate.prototype.habilidadPressed = false;
EstadisticaCombate.prototype.suertePressed = false;
EstadisticaCombate.prototype.mapaDano = [];
EstadisticaCombate.prototype.detalleTiradas = [];
EstadisticaCombate.prototype.graficos = null;

EstadisticaCombate.prototype.init = function() {
	var self = this;
	graficos = new Graficos();
	
    $('#bonoDados').keyup(function() {
        if ($(this).val().match(/[0-9 -()+]+$/) === null) {
            $(this).val('0');
        }
    });
    
    $('#bonoDadosArma').keyup(function() {
        if ($(this).val().match(/[0-9 -()+]+$/) === null) {
            $(this).val('0');
        }
    });
    
    $('#bonoBase').keyup(function() {
        if ($(this).val().match(/[0-9 -()+]+$/) === null) {
            $(this).val('0');
        }
    });	
    
    $('#exitosObjetivo').keyup(function() {
        if ($(this).val().match(/^[0-9]+$/) === null) {
            $(this).val('0');
        }
    });
	
	$('.botonCaras2').click(function(){
		self.limpiarSeleccionCaras();
		self.presionarBoton($(this));
		self.carasPressed = true;
		var carasDado = $('.botonCaras2Presionado').text();
		self.limpiarSliders();
		self.asignarSliders(carasDado);
	});
	
	$('#BotonesAtributos').slider({
		value: 1,
		min: 1,
		max: 2,
		step: 1,
		slide: function( event, ui ) {
			$( "#atributo" ).val( ui.value );
			$( "#atributoText" ).val( ui.value );
		}
	}).slider("disable");
	
	$('#BotonesHabilidades').slider({
		value: 1,
		min: 1,
		max: 2,
		step: 1,
		slide: function( event, ui ) {
			$( "#habilidad" ).val( ui.value );
			$( "#habilidadText" ).val( ui.value );
		}
	}).slider("disable");
	
	$('#BotonesSuerte').slider({
		value: 1,
		min: 1,
		max: 2,
		step: 1,
		slide: function( event, ui ) {
			$( "#suerte" ).val( ui.value );
			$( "#suerteText" ).val( ui.value );
		}
	}).slider("disable");
	
	$('#sumarBono').click(function(){
		var bono = $('#bonoDados').val();
		$('#bonoDados').val(parseInt(bono) + 1);
	});
	
	$('#restarBono').click(function(){
		var bono = $('#bonoDados').val();
		$('#bonoDados').val(parseInt(bono - 1));
	});
	
	$('#sumarBonoArma').click(function(){
		var objetivo = $('#bonoDadosArma').val();
		$('#bonoDadosArma').val(parseInt(objetivo) + 1);
	});
	
	$('#restarBonoArma').click(function(){
		var objetivo = $('#bonoDadosArma').val();
		$('#bonoDadosArma').val(parseInt(objetivo - 1));
	});
	
	$('#sumarDanoBase').click(function(){
		var objetivo = $('#bonoBase').val();
		$('#bonoBase').val(parseInt(objetivo) + 1);
	});
	
	$('#restarDanoBase').click(function(){
		var objetivo = $('#bonoBase').val();
		$('#bonoBase').val(parseInt(objetivo - 1));
	});    
	
	$('#menu').click(function(){
		$('#eleccion').val('menu');
	});
	
	$('#vsVictima').click(function(){
		var victima = $('#vsVictima').prop("checked");
		
		if (victima){
			$('.panelVictima').show();
		} else {
			$('.panelVictima').hide();
		}
		
	});
	
	$('#aplicarDano').click(function(){
//		$('#restarDefensa').prop("disabled", !this.checked);
//		$('#sumarArma').prop("disabled", !this.checked);
//		$('#danoBase').prop("disabled", !this.checked);
//		$('#carasDadoDano').prop("disabled", !this.checked);
		$('.dano').prop("disabled", !this.checked);
		$('#restarDefensa').prop('checked', false);
		$('#sumarArma').prop('checked', false);
		$('#danoBase').prop('checked', false);
		$('#carasDadoDano').val(6);
	});
	
	this.bindearBotonTirar();
	this.valoresPorDefecto();
	
	$('#resetear').click(function(){
		self.limpiarSeleccionCaras();
		self.limpiarRadios();
		$('#bonoDados').val(0);
		$('#bonoDadosArma').val(0);
		$('#bonoBase').val(0);
		$('.Resultados').hide();
	});
	
	$('#torta').click(function(){
		$('#tablaEstadistica').hide();
		graficos.torta($('#graficos'), self.mapaDano, self.detalleTiradas);
	});
	
	$('#areas').click(function(){
		$('#tablaEstadistica').hide();
		var resultados = [];
		var cont = 0;
		var minimo = Object.keys(self.mapaDano)[0];

		for (var i = minimo; i < Object.keys(self.mapaDano).length; i++){
			if (self.mapaDano[i] != undefined){
				resultados[cont] = Math.ceil(self.mapaDano[i] * $('#numTiradas').val());
			} else {
				resultados[cont] = 0;
			}
			cont++;
		}
		
		graficos.areas($('#graficos'), resultados, minimo);
	});
	
	$('#tabla').click(function(){
		$('.Container').block({ 
        	message: '<h1>Procesando.</br>Por favor espere...</h1>', 
            css: { border: '3px solid #a00' } 
        });
		
		$.ajax({
			type: "POST",
			url: "tablaEstadisticaCombate.do?decorator=none",
			data: $('#eSimpleForm').serialize(),
			dataType: "json",
			success: function(resultado) {
				$('.Container').unblock();
				var cont = 0;
				
				$('#graficos').html('');
				
				$('#tablaEstadistica').show();
				
				//Probabilidades
				for (var i = 0; i < Object.keys(resultado.tablaProbabilidad).length; i++){
					var idFila;
					
					if ((i)%5 == 0){
						cont++;
						idFila = '#p' + cont;
						$(idFila).html('<td><b>A' + cont + '</b></td>');
					}
					
					var html = '<td>' + resultado.tablaProbabilidad[i] + '%</td>';
					
					
					$(idFila).append(html);
				}
				
				//Promedios
				cont = 0;
				for (var i = 0; i < Object.keys(resultado.tablaPromedio).length; i++){
					var idFila;
					
					if ((i)%5 == 0){
						cont++;
						idFila = '#x' + cont;
						$(idFila).html('<td><b>A' + cont + '</b></td>');
					}
					
					var html = '<td>' + resultado.tablaPromedio[i] + '</td>';
					
					
					$(idFila).append(html);
				}
			},
			error: function(a, b) {
				alert("Se produjo un error al hacer el llamado de ajax.");
				$('.Container').unblock();
			}
		});
	});
	
	//Ocultamos todos los divs que no van.
	$('.Resultados').hide();
	$('.panelVictima').hide();
};

EstadisticaCombate.prototype.valoresPorDefecto = function(){
	$('#objetivo').val(0);
	$('#dificultad').val(1);
//	$('#retencion').val(1);
//	$('#sustraccion').val(0);
	$('#penalizacion').val(0);
	
	$('#carasDadoDano').val(6);
	$('.dano').prop("disabled", true);
}

EstadisticaCombate.prototype.bindearBotonTirar = function(){
	var self = this;
	$('#hacerTirada').bind('click', function(){
		$('.Errores').html('');
		$('.Resultados').hide();

		var boton = $(this);
		
		//Valido que esten seleccionados los datos:
		if ((self.carasPressed)){ //Agregar validacion de atributo, etc...
			
			self.presionarBoton(boton);
			
			$('.Container').block({ 
	        	message: '<h1>Procesando.</br>Por favor espere...</h1>', 
	            css: { border: '3px solid #a00' } 
	        });
	        
			var carasDado = $('.botonCaras2Presionado').text();
			$('#carasDado').val(carasDado);
		    
			$.ajax({
				type: "POST",
				url: "estadisticaCombate.do?decorator=none",
				data: $('#eSimpleForm').serialize(),
				dataType: "json",
				success: function(resultado) {
					$('.Resultados').show();
					self.soltarBoton(boton);
					$('.Container').unblock();
					$('#tablaEstadistica').hide();
					
					self.mapaDano = resultado.mapaDano;
					self.detalleTiradas =  resultado.detalleTiradas;
					
					graficos.torta($('#graficos'), resultado.mapaDano, resultado.detalleTiradas);
					
					//Resultados
					$('#promedio').text(resultado.promedioDano);
					$('#probEmboque').text(resultado.probabilidadDano + "%");
					$('#danoMinimo').text(resultado.minimo);
					$('#danoMaximo').text(resultado.maximo);
				},
				error: function(a, b) {
					alert("Se produjo un error al hacer el llamado de ajax.");
					self.soltarBoton(boton);
					$('.Container').unblock();
				}
			});
			
		} else {
			$('.Errores').append($.parseHTML('<span>�Falta ingresar alguno de los datos necesarios!</span>'));
		}
	});
};

EstadisticaCombate.prototype.reiniciarAreaResultados = function(){
	var columnasResultado = $('.Resultados').size();
	for (var i = 0; i < columnasResultado - 1; i++) { //Itero entre todos menos el original
		$('.Resultados').last().parent().remove();
	}
	$('.Resultados').html('<table id="tablaResultados" border="1"><tbody><tr><td><div class="Dados" style="text-align:center"></div></td></tr></tbody></table>');
	$('#total').text('');
};

EstadisticaCombate.prototype.limpiarSeleccionCaras = function(buttonClass){
	$('.botonCaras2Presionado').each(function(){
		$(this).removeClass().addClass('botonCaras2');
	});
	this.atributoPressed = false;
};

EstadisticaCombate.prototype.presionarBoton = function(id){
	var estiloAnterior = $(id).attr('class');
	$(id).removeClass().addClass(estiloAnterior + 'Presionado');
	$(id).css('cursor', 'default');
	$(id).attr('disabled', 'disabled');
};

EstadisticaCombate.prototype.soltarBoton = function(id){
	var estiloPresionado = '.' + $(id).attr('class');
	var estiloSinPresionar = estiloPresionado.substring(1, estiloPresionado.length - 10);
	$(estiloPresionado).each(function(){
		$(this).removeClass().addClass(estiloSinPresionar);
		$(this).css('cursor', 'pointer');
		$(this).removeAttr('disabled');
	});
};


EstadisticaCombate.prototype.limpiarRadios = function(){
	$('.radioAtributo').each(function(){
		$(this).prop('checked', false);
	});
	$('.radioHabilidad').each(function(){
		$(this).prop('checked', false);
	});
	$('.radioSuerte').each(function(){
		$(this).prop('checked', false);
	});
	$('.radioDefensa').each(function(){
		$(this).prop('checked', false);
	});
	$('.radioArmadura').each(function(){
		$(this).prop('checked', false);
	});
	this.atributoPressed = false;
	this.habilidadPressed = false;
	this.suertePressed = false;
};

EstadisticaCombate.prototype.limpiarSliders = function(){
	$( "#atributo" ).val( "1" );
	$( "#atributoText" ).val( "1" );
	$('#BotonesAtributos').slider("destroy");
	$( "#habilidad" ).val( "1" );
	$( "#habilidadText" ).val( "1" );
	$('#BotonesHabilidades').slider("destroy");
	$( "#suerte" ).val( "1" );
	$( "#suerteText" ).val( "1" );
	$('#BotonesSuerte').slider("destroy");
};

EstadisticaCombate.prototype.asignarSliders = function(caras){
	$('#BotonesAtributos').slider({
		value: 1,
		min: 1,
		max: caras,
		step: 1,
		slide: function( event, ui ) {
			$( "#atributo" ).val( ui.value );
			$( "#atributoText" ).val( ui.value );
		}
	});
	
	$('#BotonesHabilidades').slider({
		value: 1,
		min: 1,
		max: caras,
		step: 1,
		slide: function( event, ui ) {
			$( "#habilidad" ).val( ui.value );
			$( "#habilidadText" ).val( ui.value );
		}
	});
	
	$('#BotonesSuerte').slider({
		value: 1,
		min: 1,
		max: caras,
		step: 1,
		slide: function( event, ui ) {
			$( "#suerte" ).val( ui.value );
			$( "#suerteText" ).val( ui.value );
		}
	});
};
//
//EstadisticaCombate.prototype.limpiarSeleccionHabilidad = function(){
//	$('.botonHabilidadPresionado').each(function(){
//		$(this).removeClass().addClass('botonHabilidad');
//	});
//	this.habilidadPressed = false;
//};
//
//EstadisticaCombate.prototype.limpiarSeleccionSuerte = function(){
//	$('.botonSuertePresionado').each(function(){
//		$(this).removeClass().addClass('botonSuerte');
//	});
//	this.suertePressed = false;
//};