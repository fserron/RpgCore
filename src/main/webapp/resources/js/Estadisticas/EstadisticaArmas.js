function EstadisticaArmas() {};

EstadisticaArmas.prototype.carasPressed = false;
EstadisticaArmas.prototype.atributoPressed = false;
EstadisticaArmas.prototype.habilidadPressed = false;
EstadisticaArmas.prototype.suertePressed = false;
EstadisticaArmas.prototype.mapaDano = [];
EstadisticaArmas.prototype.detalleTiradas = [];
EstadisticaArmas.prototype.graficos = null;

EstadisticaArmas.prototype.init = function() {
	var self = this;
	graficos = new Graficos();
	
    $('.bonos').keyup(function() {
        if ($(this).val().match(/[0-9 -()+]+$/) === null) {
            $(this).val('0');
        }
    });

    $('.simbolos').click(function(){
    	var id = $(this).attr('id').substring(0, 5);
    	
    	if (id == 'sumar'){
    		self.sumar($(this).attr('id').substring(9, $(this).attr('id').length));
    	} else if (id == 'resta'){
    		self.restar($(this).attr('id').substring(10, $(this).attr('id').length));
    	}
    });
    
	$('#armaFuego').click(function(){
		if (this.checked){
			$('.BotonesPotencia').show();
		} else {
			$('.BotonesPotencia').hide();
		}
	});
    
	$('#menu').click(function(){
		$('#eleccion').val('menu');
	});	
	
	this.bindearBotonTirar();
	this.valoresPorDefecto();
	
	$('#resetear').click(function(){
		$('#bono').val(0);
		$('#bonoArma').val(0);
		$('#bonoBase').val(0);
		$('#numTiradas').val(100000);
		$('#armaFuego').attr('check', false);
		$('#valorExplosion').val(0);
		
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
	
	//Ocultamos todos los divs que no van.
	$('.Resultados').hide();
//	$('.Suerte').hide();
};

EstadisticaArmas.prototype.valoresPorDefecto = function(){
	$('#armaFuego').attr('check', false);
	$('#numTiradas').val(100000);
	$('.BotonesPotencia').hide();
};

EstadisticaArmas.prototype.bindearBotonTirar = function(){
	var self = this;
	$('#hacerTirada').bind('click', function(){
		$('.Errores').html('');
		$('.Resultados').hide();

		var boton = $(this);
			
		self.presionarBoton(boton);
		
		$('.Container').block({ 
        	message: '<h1>Procesando.</br>Por favor espere...</h1>', 
            css: { border: '3px solid #a00' } 
        });
	    
		$.ajax({
			type: "POST",
			url: "tablaEstadisticaArmas.do?decorator=none",
			data: $('#eSimpleForm').serialize(),
			dataType: "json",
			success: function(resultado) {
				$('.Resultados').show();
				self.soltarBoton(boton);
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
};

EstadisticaArmas.prototype.reiniciarAreaResultados = function(){
	var columnasResultado = $('.Resultados').size();
	for (var i = 0; i < columnasResultado - 1; i++) { //Itero entre todos menos el original
		$('.Resultados').last().parent().remove();
	}
	$('.Resultados').html('<table id="tablaResultados" border="1"><tbody><tr><td><div class="Dados" style="text-align:center"></div></td></tr></tbody></table>');
	$('#total').text('');
};

EstadisticaArmas.prototype.limpiarSeleccionCaras = function(buttonClass){
	$('.botonCaras2Presionado').each(function(){
		$(this).removeClass().addClass('botonCaras2');
	});
	this.atributoPressed = false;
};

EstadisticaArmas.prototype.presionarBoton = function(id){
	var estiloAnterior = $(id).attr('class');
	$(id).removeClass().addClass(estiloAnterior + 'Presionado');
	$(id).css('cursor', 'default');
	$(id).attr('disabled', 'disabled');
};

EstadisticaArmas.prototype.soltarBoton = function(id){
	var estiloPresionado = '.' + $(id).attr('class');
	var estiloSinPresionar = estiloPresionado.substring(1, estiloPresionado.length - 10);
	$(estiloPresionado).each(function(){
		$(this).removeClass().addClass(estiloSinPresionar);
		$(this).css('cursor', 'pointer');
		$(this).removeAttr('disabled');
	});
};


EstadisticaArmas.prototype.limpiarRadios = function(){
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

EstadisticaArmas.prototype.sumar  = function(id){
	var nombre = "#bono" + id;
	var bono = $(nombre).val();
	$(nombre).val(parseInt(bono) + 1);
};

EstadisticaArmas.prototype.restar  = function(id){
	var nombre = "#bono" + id;
	var bono = $(nombre).val();
	$(nombre).val(parseInt(bono - 1));
};

//
//EstadisticaArmas.prototype.limpiarSeleccionHabilidad = function(){
//	$('.botonHabilidadPresionado').each(function(){
//		$(this).removeClass().addClass('botonHabilidad');
//	});
//	this.habilidadPressed = false;
//};
//
//EstadisticaArmas.prototype.limpiarSeleccionSuerte = function(){
//	$('.botonSuertePresionado').each(function(){
//		$(this).removeClass().addClass('botonSuerte');
//	});
//	this.suertePressed = false;
//};