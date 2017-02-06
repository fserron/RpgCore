function index() {
};

index.prototype.init = function() {
	var self = this;
	var estadisticasPressed = false;
	
	$('.MenuEstadisticas').hide();
	$('#estadisticas').click(function(){
		if (!estadisticasPressed) {
			$(this).removeClass().addClass('pressedButton');
			self.mostrarBotonesEstadisticas();
			estadisticasPressed = true;
		} else {
			$(this).removeClass().addClass('standardButton');
			self.ocultarBotonesEstadisticas();
			estadisticasPressed = false;
		}

	});
	$('#combate').click(function(){
		if (estadisticasPressed) {
			$('#estadisticas').removeClass().addClass('standardButton');
			self.ocultarBotonesEstadisticas();
			estadisticasPressed = false;
		}
	});
};


index.prototype.mostrarBotonesEstadisticas = function() {
	$('.MenuEstadisticas').show();
};

index.prototype.ocultarBotonesEstadisticas = function() {
	$('.MenuEstadisticas').hide();
};