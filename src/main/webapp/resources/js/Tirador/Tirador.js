function Tirador() {
	
};

Tirador.prototype.exitosTotales = 0;
Tirador.prototype.fracasosTotales = 0;
Tirador.prototype.dadosTirados = 0;
Tirador.prototype.dadosTotales = 0;
Tirador.prototype.dadosParaReTirar = 0;
Tirador.prototype.reTiradas = 0;
Tirador.prototype.tablas = 0;
Tirador.prototype.atributoPressed = false;
Tirador.prototype.habilidadPressed = false;
Tirador.prototype.suertePressed = false;

Tirador.prototype.init = function() {
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
	
	$('#menu').click(function(){
		$('#eleccion').val('menu');
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

Tirador.prototype.bindearBotonTirar = function(){
	var self = this;
	$('#hacerTirada').bind('click', function(){
		var modo = $('input[name=tipoTirada]:radio:checked').val();
		$('.Errores').html('');
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

Tirador.prototype.reiniciarAreaResultados = function(){
	var columnasResultado = $('.Resultados').size();
	for (var i = 0; i < columnasResultado - 1; i++) { //Itero entre todos menos el original
		$('.Resultados').last().parent().remove();
	}
	$('.Resultados').html('<table id="tablaResultados0" border="1"><tbody><tr><td><div class="Dados" style="text-align:center"></div></td></tr></tbody></table>');
	$('#total').text('');
	this.tablas = 0;
	this.reTiradas = 0;
}

Tirador.prototype.limpiarSeleccionAtributo = function(){
	$('.botonAtributoPresionado').each(function(){
		$(this).removeClass().addClass('botonAtributo');
	});
	this.atributoPressed = false;
};

Tirador.prototype.limpiarSeleccionHabilidad = function(){
	$('.botonHabilidadPresionado').each(function(){
		$(this).removeClass().addClass('botonHabilidad');
	});
	this.habilidadPressed = false;
};

Tirador.prototype.limpiarSeleccionSuerte = function(){
	$('.botonSuertePresionado').each(function(){
		$(this).removeClass().addClass('botonSuerte');
	});
	this.suertePressed = false;
};

Tirador.prototype.hacerTirada = function(){
	var habilidad = $('.botonHabilidadPresionado').text();
	var suerte = $('.botonSuertePresionado').text();
	var modo = $('input[name=tipoTirada]:radio:checked').val();
	var bono = $('#bonoDados').val();
	
	if (modo == 'normal' || modo == 'suerte'){
		this.dadosTotales = parseInt(this.convertirANumeros(habilidad)) + parseInt(bono);
	} else {
		this.dadosTotales = this.convertirANumeros(suerte);
	}
	
	$('.Dados').closest('td').after('<td>' + parseInt(this.reTiradas + 1) + '</td>');
	
	for (var i = 0; i < this.dadosTotales; i++){
		if (i != 0 && i % 10 == 0) {
			$('.Dados').append('<br>');
		}
		$('.Dados').append($.parseHTML('<a id="dado-r' + this.reTiradas + '-' + i + '" class="dadoNormal">F</a>'));
		var id = "#dado-r" + this.reTiradas + "-" + i ;
		this.rolearDado(20, 10, id);
	}
	
}

Tirador.prototype.rolearDado = function(times, velocidad, element){
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

Tirador.prototype.calcularExitos = function(element){
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

Tirador.prototype.verificarFinal = function(){
	var modo = $('input[name=tipoTirada]:radio:checked').val();
	var resultado = $('resultadoFinal').html();
	
	if (this.dadosTirados == this.dadosTotales) { //Significa que finalizo la tirada
		this.dadosParaReTirar = 0; //Reiniciamos los dados a re-tirar
		for (var i = 0; i < this.dadosTotales; i++){
			var element = "#dado-r" + this.reTiradas + "-" + i;
			this.calcularExitos(element);
			if (modo == 'suerte'){
				this.calcularReTirada(element);
			}
		}
		
		//Si hay dados a retirar, los retiramos
		if (this.dadosParaReTirar > 0){
			this.reTiradas++;
			this.dadosTotales = this.dadosParaReTirar;
			this.dadosTirados = 0;
			//Remuevo la clase "Dados" de la tirada anterior, ya que no la voy a usar mas
			$('.Dados').removeClass();
			
			//Si hay mas de 10 retiradas, creo una nueva columna
			if (this.reTiradas % 20 == 0 && this.reTiradas != 0) {
				this.tablas++;
				$('.Resultados').parent().last().after('<td rowspan=6 align="center" style="vertical-align:top"><div class="Resultados" style="text-align:center"><table id="tablaResultados' + this.tablas + '" border="1"><tr><td><div class="Dados" style="text-align:center"></div></td></tr></table></div></td>');
			} else {
				//Añado una nueva linea para la retirada, con la clase "Dados"
				$('#tablaResultados' + this.tablas + ' tr:last').after('<tr><td><div class="Dados" style="text-align:center"></div></td></tr>');
			}
			
			$('.Dados').closest('td').after('<td>' + parseInt(this.reTiradas + 1) + '</td>');

			for (var i = 0; i < this.dadosParaReTirar; i++){
				if (i != 0 && i % 10 == 0) {
					$('.Dados').append('<br>');
				}
				$('.Dados').append($.parseHTML('<a id="dado-r' + this.reTiradas + '-' + i + '" class="dadoNormal">F</a>'));
				var id = "#dado-r" + this.reTiradas + "-" + i;
				this.rolearDado(20, 10, id);
			}
			
		} else if (resultado == undefined) {
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
			$('#tablaResultados' + this.tablas + ' tr:last').after('<tr><td id="resultadoFinal" align="center">' + leyenda + '</td></tr>');
			
			//Por ultimo, habilito el boton "Tirar"
			this.soltarBoton($('#hacerTirada'));
			this.bindearBotonTirar();
		}
		
	}
	
}

Tirador.prototype.calcularReTirada = function(element){
	var puntuacion = this.convertirANumeros($(element).text());
	var suerte = this.convertirANumeros($('.botonSuertePresionado').text());
	
	var objetivo = 0;
	if ((puntuacion != 1) && (puntuacion <= suerte)){	
		this.dadosParaReTirar++;
		$(element).removeClass().addClass('dadoSuerte');
	}
}

Tirador.prototype.convertirALetras = function(numero){
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

Tirador.prototype.convertirANumeros = function(letra){
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

Tirador.prototype.presionarBoton = function(id){
	var estiloAnterior = $(id).attr('class');
	$(id).removeClass().addClass(estiloAnterior + 'Presionado');
	$(id).css('cursor', 'default');
}

Tirador.prototype.soltarBoton = function(id){
	var estiloPresionado = '.' + $(id).attr('class');
	var estiloSinPresionar = estiloPresionado.substring(1, estiloPresionado.length - 10);
	$(estiloPresionado).each(function(){
		$(this).removeClass().addClass(estiloSinPresionar);
		$(this).css('cursor', 'pointer');
	});
}