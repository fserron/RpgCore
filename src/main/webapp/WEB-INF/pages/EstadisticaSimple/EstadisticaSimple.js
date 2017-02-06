function EstadisticaSimple() {
	
};

EstadisticaSimple.prototype.exitosTotales = 0;
EstadisticaSimple.prototype.fracasosTotales = 0;
EstadisticaSimple.prototype.dadosTirados = 0;
EstadisticaSimple.prototype.dadosTotales = 0;
EstadisticaSimple.prototype.dadosParaReTirar = 0;
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
		$('#bonoDados').val(parseInt(bono - 1));
	});

	$('#radioNormal').click(function(){
		self.limpiarSeleccionSuerte();
		$('.Suerte').hide();
		$('.Atributos').show();
		$('.Habilidades').show();
	});
	
	$('#radioSuerte').click(function(){
		$('.Suerte').show();
		$('.Atributos').show();
		$('.Habilidades').show();
	});
	
	$('#radioDestino').click(function(){
		$('.Suerte').show();
		$('.Atributos').hide();
		$('.Habilidades').hide();
		self.limpiarSeleccionAtributo();
		self.limpiarSeleccionHabilidad();
	});
	
	this.bindearBotonTirar();
	
	$('#resetear').click(function(){
		self.limpiarSeleccionAtributo();
		self.limpiarSeleccionHabilidad();
		self.limpiarSeleccionSuerte();
		$('#bonoDados').val(0);
		$("#radioNormal").prop("checked", true);
		$('.Atributos').show();
		$('.Habilidades').show();
		$('.Resultados').hide();
		$('.Suerte').hide();
		self.reiniciarAreaResultados();
		self.exitosTotales = 0;
		self.fracasosTotales = 0;
	});
	
	//Ocultamos todos los divs que no van.
	$('.Resultados').hide();
	$('.Suerte').hide();
};

EstadisticaSimple.prototype.bindearBotonTirar = function(){
	var self = this;
	$('#hacerTirada').bind('click', function(){
		var modo = $('input[name=tipoTirada]:radio:checked').val();
		$('.Errores').html('');
		$('#total').text('');
		self.reiniciarAreaResultados();
		
		//Valido que esten seleccionados los datos:
		if ((self.atributoPressed && self.habilidadPressed && 
			(modo == 'normal' ||  (modo == 'suerte' && self.suertePressed))) ||
			(modo == 'destino' && self.suertePressed)){
			$('.Resultados').show();
			self.exitosTotales = 0;
			self.fracasosTotales = 0;
			self.dadosTirados = 0;
			self.dadosTotales = 0;
			self.presionarBoton($(this));
			$(this).unbind();
			self.hacerTirada();
		} else {
			$('.Errores').append($.parseHTML('<span>¡Falta ingresar alguno de los datos necesarios!</span>'));
		}
	});
}

EstadisticaSimple.prototype.reiniciarAreaResultados = function(){
	$('#tablaResultados').html('<tbody><tr><th>El resultado de la tirada fue:</th></tr><tr><td><div class="Dados" style="text-align:center"></div></td></tr></tbody>');
}

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

EstadisticaSimple.prototype.hacerTirada = function(){
	var habilidad = $('.botonHabilidadPresionado').text();
	var suerte = $('.botonSuertePresionado').text();
	var modo = $('input[name=tipoTirada]:radio:checked').val();
	var bono = $('#bonoDados').val();
	
	if (modo == 'normal' || modo == 'suerte'){
		this.dadosTotales = parseInt(this.convertirANumeros(habilidad)) + parseInt(bono);
	} else {
		this.dadosTotales = this.convertirANumeros(suerte);
	}
	
	for (var i = 0; i < this.dadosTotales; i++){
		if (i != 0 && i % 10 == 0) {
			$('.Dados').append('<br>');
		}
		$('.Dados').append($.parseHTML('<a id="dado' + i + '" class="dadoNormal">F</a>'));
		var id = "#dado" + i;
		this.rolearDado(20, 10, id);
	}
	
}

EstadisticaSimple.prototype.rolearDado = function(times, velocidad, element){
	var self = this;
	$(element).text(this.convertirALetras(Math.round(Math.random() * 5) + 1));
    if (times > 1){
        setTimeout(function(){
			self.rolearDado(times-1, velocidad, element);
		}, parseInt(velocidad) + 100);
    } else {
		this.dadosTirados++;
		this.verificarFinal();
	}
}

EstadisticaSimple.prototype.calcularExitos = function(element){
	var puntuacion = this.convertirANumeros($(element).text());
	var atributo = this.convertirANumeros($('.botonAtributoPresionado').text());
	var suerte = this.convertirANumeros($('.botonSuertePresionado').text());
	var modo = $('input[name=tipoTirada]:radio:checked').val();
	
	var objetivo = 0;
	if (modo == 'normal' || modo == 'suerte') {
		objetivo = atributo;
	} else {
		objetivo = suerte;
	}
	if (puntuacion == 1){
		$(element).css('color', '#ff0000');
		this.fracasosTotales++;
	} else if (puntuacion <= objetivo){
		$(element).css('color', '#37db00');
		this.exitosTotales++;
	}
}

EstadisticaSimple.prototype.verificarFinal = function(){
	var modo = $('input[name=tipoTirada]:radio:checked').val();

	if (this.dadosTirados == this.dadosTotales) { //Significa que finalizo la tirada
		for (var i = 0; i < this.dadosTotales; i++){
			var element = "#dado" + i;
			this.calcularExitos(element);
			if (modo == 'suerte'){
				this.calcularReTirada(element);
			}
		}
		
		//Si hay dados a retirar, los retiramos
		if (modo == 'suerte' && this.dadosParaReTirar > 0){
			this.dadosTotales += this.dadosParaReTirar;
			//Remuevo la clase "Dados" de la tirada anterior, ya que no la voy a usar mas
			$('.Dados').removeClass();
			//Añado una nueva linea para la retirada, con la clase "Dados"
			$('#tablaResultados tr:last').after('<tr><td><div class="Dados" style="text-align:center"></div></td></tr>');
			
			for (var i = 0; i < this.dadosParaReTirar; i++){
				if (i != 0 && i % 10 == 0) {
					$('.Dados').append('<br>');
				}
				$('.Dados').append($.parseHTML('<a id="dado' + i + '" class="dadoNormal">F</a>'));
				var id = "#dado" + i;
				this.rolearDado(20, 10, id);
			}
		}
		
		var totalFinal = 0;
		if (modo == 'normal' || modo == 'suerte') {
			totalFinal = this.exitosTotales;
		} else if (modo == 'destino'){
			totalFinal = this.exitosTotales - this.fracasosTotales;
		}
		
		//Generamos la leyenda a mostrar
		var leyenda;
		if (totalFinal < 0) {
			leyenda = '¡La tirada fue un FRACASO con ' + totalFinal + ' exitos!';
		} else if (totalFinal == 0){
			leyenda = '¡No hubo exitos!';
		} else if (totalFinal == 1){
			leyenda = '¡Exito!';
		} else {
			leyenda = 'Hubo ' + totalFinal + ' exitos!';
		}

		//Creamos la fila de la tabla para mostrar la leyenda
		$('#tablaResultados tr:last').after('<tr><td align="center">' + leyenda + '</td></tr>');
		
		//Por ultimo, habilito el boton "Tirar"
		this.soltarBoton($('#hacerTirada'));
		this.bindearBotonTirar();
	}
	
}

EstadisticaSimple.prototype.calcularReTirada = function(element){
	var puntuacion = this.convertirANumeros($(element).text());
	var suerte = this.convertirANumeros($('.botonSuertePresionado').text());
	
	var objetivo = 0;
	if ((puntuacion != 1) && (puntuacion <= suerte)){	
		this.dadosParaReTirar++;
		$(element).removeClass().addClass('dadoSuerte');
	}
}

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

EstadisticaSimple.prototype.presionarBoton = function(id){
	var estiloAnterior = $(id).attr('class');
	$(id).removeClass().addClass(estiloAnterior + 'Presionado');
	$(id).css('cursor', 'default');
}

EstadisticaSimple.prototype.soltarBoton = function(id){
	var estiloPresionado = '.' + $(id).attr('class');
	var estiloSinPresionar = estiloPresionado.substring(1, estiloPresionado.length - 10);
	$(estiloPresionado).each(function(){
		$(this).removeClass().addClass(estiloSinPresionar);
		$(this).css('cursor', 'pointer');
	});
}