function Creador() {
	
};

Creador.prototype.atributosPrimarios = 0; //1 =  Fisicos; 2 = Mentales
Creador.prototype.maximoPrimarios = 4;
Creador.prototype.maximoSecundario = 3;
Creador.prototype.maximoExtra = 2;

Creador.prototype.init = function() {
	var self = this;
	
	$('#primariosFisicos').click(function(){
		self.primariosFisicos();
		self.habilitarBotonesAtributos();
		self.atributosPrimarios = 1;
	});
	
	$('#primariosMentales').click(function(){
		self.primariosMentales();
		self.habilitarBotonesAtributos();
		self.atributosPrimarios = 2;
	});
	
	$('#reiniciar').click(function(){
		self.reiniciar();
	});
	
	$('.botonPuntuacionMedium').each(function(){
		$(this).click(function(){
			var funcion = $(this).attr('id').substring(0, 5);
			if (funcion == 'sumar'){
				self.aumentarAtributo($(this).closest('.contenedorPuntuacion').find('.puntuacionAtributos'));
			} else if(funcion == 'resta'){
				self.disminuirAtributo($(this).closest('.contenedorPuntuacion').find('.puntuacionAtributos'));
			}
		});		
	});
	
	self.reiniciar();
};

Creador.prototype.reiniciar = function(){
	var self = this;
	
	$('.puntuacionAtributos').each(function(){
		$(this).val('1');
	});
	
	$('.puntuacionHabilidades').each(function(){
		$(this).val('0');
	});
	
	$('.puntosDisponibles').each(function(){
		$(this).val('0');
	});
	
	$('#primariosFisicos').val("Primarios");
	$('#primariosMentales').val("Primarios");
	$('#primariosFisicos').prop('disabled', false);
	$('#primariosMentales').prop('disabled', false);
	$('#primariosFisicos').removeClass().addClass('standardButtonMedium');
	$('#primariosMentales').removeClass().addClass('standardButtonMedium');	

	self.deshabilitarTodosBotones();
	
	alert('Debe presionar uno de los botones "Primario" para elegir que atributos sean los primarios.');
};

Creador.prototype.deshabilitarTodosBotones = function(){
	$('.botonPuntuacionMedium').each(function(){
		$(this).removeClass().addClass('botonPuntuacionMediumDisabled');
		$(this).bind('click', false);
	});
	$('.botonPuntuacionSmall').each(function(){
		$(this).removeClass().addClass('botonPuntuacionSmallDisabled');
		$(this).bind('click', false);
	});
};

Creador.prototype.primariosFisicos = function(){
	var self = this;
	
	$('#disponibleFisicos').val(self.maximoPrimarios);
	$('#disponibleMentales').val(self.maximoSecundario);
	$('#disponibleExtras').val(self.maximoExtra);	
	$('#primariosMentales').val("Secundarios");
	$('#primariosFisicos').prop('disabled', true);
	$('#primariosMentales').prop('disabled', true);
	$('#primariosFisicos').removeClass().addClass('standardButtonMediumDisabled');
	$('#primariosMentales').removeClass().addClass('standardButtonMediumDisabled');	
	
	alert('Se asignaron:\n\t- 4 puntos para distribuir entre los atributos Fisicos.\n\t- 3 puntos para distribuir entre los atributos Mentales.\n\t- 2 puntos extras para repartir donde sea, incluido Suerte.');
};

Creador.prototype.primariosMentales = function(){
	$('#disponibleMentales').val(self.maximoPrimarios);
	$('#disponibleFisicos').val(self.maximoSecundario);
	$('#disponibleExtras').val(self.maximoExtra);	
	$('#primariosFisicos').val("Secundarios");
	$('#primariosFisicos').prop('disabled', true);
	$('#primariosMentales').prop('disabled', true);
	$('#primariosFisicos').removeClass().addClass('standardButtonMediumDisabled');
	$('#primariosMentales').removeClass().addClass('standardButtonMediumDisabled');

	alert('Se asignaron:\n\t- 4 puntos para distribuir entre los atributos Mentales.\n\t- 3 puntos para distribuir entre los atributos Fisicos.\n\t- 2 puntos extras para repartir donde sea, incluido Suerte.');
};

Creador.prototype.habilitarBotonesAtributos = function(){
	var self = this;
	
	$('#contenedorFisicos tbody tr a[href]').each(function(){
		$(this).removeClass().addClass('botonPuntuacionMedium');
		$(this).bind('click', true);
	});
	
	$('#contenedorMentales tbody tr a[href]').each(function(){
		$(this).removeClass().addClass('botonPuntuacionMedium');
		$(this).bind('click', true);
	});
	
	$('#contenedorSobrenaturales tbody tr a[href]').each(function(){
		if($(this).attr('id') != 'reiniciar'){
			$(this).removeClass().addClass('botonPuntuacionMedium');
			$(this).bind('click', true);
		}		
	});
	
};

Creador.prototype.aumentarAtributo = function(valor){
	var disponibles = null;
	var puntuacion = valor.text();
	var tabla = valor.parents("table:first").attr('id');
	
	if (tabla == 'contenedorFisicos'){
		disponibles = $('#disponibleFisicos').val() + $('#disponibleExtras').val();
	} else if (tabla == 'contenedorMentales'){
		disponibles = $('#disponibleMentales').val() + $('#disponibleExtras').val();
	} else if (tabla == 'contenedorSobrenaturales'){
		disponibles = $('#disponibleExtras').val();
	}

	if (disponibles > 0){
		if (puntuacion < 5){
			puntuacion += 1;
			$(this).val(puntuacion);
			
			if (tabla == 'contenedorFisicos'){
				if ($('#disponibleFisicos').val() > 0){
					$('#disponibleFisicos').val($('#disponibleFisicos').val() - 1);
				} else if ($('#disponibleExtras').val() > 0){
					$('#disponibleExtras').val($('#disponibleExtras').val() - 1);
				}
			}
			if (tabla == 'contenedorMentales'){
				if ($('#disponibleMentales').val() > 0){
					$('#disponibleMentales').val($('#disponibleMentales').val() - 1);
				} else if ($('#disponibleExtras').val() > 0){
					$('#disponibleExtras').val($('#disponibleExtras').val() - 1);
				}
			}
			if (tabla == 'contenedorSobrenaturales'){
				$('#disponibleExtras').val($('#disponibleExtras').val() - 1);
			}
			
		} else {
			alert('¡No se puede incrementar a mas de 5!');
		}
	} else {
		alert('¡No hay mas puntos disponibles!');
	}
};

Creador.prototype.disminuirAtributo = function(valor){
	var self = this;
	
	var max = null;
	var disponibles = null;
	var puntuacion = valor.text()
	var tabla = valor.parents("table:first").attr('id');
	
	if (tabla == 'contenedorFisicos'){
		disponibles = $('#disponibleFisicos').val();
		
		if (self.atributosPrimarios == 1){
			max = self.maximoPrimarios;
		} else {
			max = self.maximoSecundario;
		}
	} else if (tabla == 'contenedorMentales'){
		disponibles = $('#disponibleMentales').val();
		
		if (self.atributosPrimarios == 2){
			max = self.maximoPrimarios;
		} else {
			max = self.maximoSecundario;
		}
	} else if (tabla == 'contenedorSobrenaturales'){
		disponibles = $('#disponibleExtras').val();
		
		max = self.maximoExtra;
	}

	if (puntuacion > 1){
		puntuacion -= 1;
		$(this).val(puntuacion);
		
		if (tabla == 'contenedorFisicos'){
			if (disponibles < max){
				$('#disponibleFisicos').val($('#disponibleFisicos').val() + 1);
			} else {
				$('#disponibleExtras').val($('#disponibleExtras').val() + 1);
			}
		}
		if (tabla == 'contenedorMentales'){
			if (disponibles < max){
				$('#disponibleMentales').val($('#disponibleMentales').val() + 1);
			} else {
				$('#disponibleExtras').val($('#disponibleExtras').val() + 1);
			}
		}
		if (tabla == 'contenedorSobrenaturales'){
			$('#disponibleExtras').val($('#disponibleExtras').val() + 1);
		}
	} else {
		alert('¡No se puede bajar por debajo de 1!');
	}
};

