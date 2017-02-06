function index() {
};

index.prototype.estadisticasPressed = false;

index.prototype.init = function() {
	var self = this;
	
	$('.MenuEstadisticas').hide();
	$('#estadisticas').click(function(){
		if (!self.estadisticasPressed) {
			$(this).removeClass().addClass('standardButtonPresionado');
			self.mostrarBotonesEstadisticas();
			self.estadisticasPressed = true;
		} else {
			$(this).removeClass().addClass('standardButton');
			self.ocultarBotonesEstadisticas();
			self.estadisticasPressed = false;
		}
		event.preventDefault();
	});
	$('#tirador').click(function(){
		self.levantarBotonEstadisticas();
		$('#eleccion').val('tirador');
	});
	$('#combate').click(function(){
		self.levantarBotonEstadisticas();
		$('#eleccion').val('combate');
	});
	$('#creacionPj').click(function(){
		self.levantarBotonEstadisticas();
		$('#eleccion').val('creacionPj');
	});
	$('#eSimple').click(function(){
		self.levantarBotonEstadisticas();
		$('#eleccion').val('estadisticaSimple');
	});
	$('#eCombate').click(function(){
		self.levantarBotonEstadisticas();
		$('#eleccion').val('estadisticaCombate');
	});
	$('#eDestino').click(function(){
		self.levantarBotonEstadisticas();
		$('#eleccion').val('estadisticaDestino');
	});
	$('#eArmas').click(function(){
		self.levantarBotonEstadisticas();
		$('#eleccion').val('estadisticaArmas');
	});
};

index.prototype.levantarBotonEstadisticas = function() {
	$('#estadisticas').removeClass().addClass('standardButton');
	this.ocultarBotonesEstadisticas();
	this.estadisticasPressed = false;
};


index.prototype.mostrarBotonesEstadisticas = function() {
	$('.MenuEstadisticas').show();
};

index.prototype.ocultarBotonesEstadisticas = function() {
	$('.MenuEstadisticas').hide();
};