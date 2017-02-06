<!DOCTYPE html>
<html>
	<head>
		<title>Estadistica Simple</title>
		<link rel="stylesheet" href="../stylesheet.css" type="text/css">
	</head>
	<body>		
		<script src="../jquery-1.10.2.min.js"></script>
		<script src="EstadisticaSimple.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {				
				var p1 = new EstadisticaSimple();
				p1.init();
			});
		</script>
		
		<div class="Container">
			<div class="Opciones">
				<table border="1">
					<tr>
						<td colspan=2>
							<div class="Atributos">
								<span>Seleccione la puntuacion en el Atributo:</span><br>
								<div class="BotonesAtributos">
									<a href="#" id="atributoA" class="botonAtributo">A</a>
									<a href="#" id="atributoB" class="botonAtributo">B</a>
									<a href="#" id="atributoC" class="botonAtributo">C</a>
									<a href="#" id="atributoD" class="botonAtributo">D</a>
									<a href="#" id="atributoE" class="botonAtributo">E</a>
									<a href="#" id="atributoF" class="botonAtributo">F</a>
								</div>
							</div>
						</td>
						<td rowspan=6 align="center" style="vertical-align:top">
							<div class="Resultados">
							<table id="tablaResultados" border="1">
								<th>El resultado de la tirada fue:</th>
								<tr>
									<td>
										<div class="Dados" style="text-align:center"></div>
									</td>
								</tr>
							</table>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan=2>
							<div class="Habilidades">
								<span>Seleccione la puntuacion en la Habilidad:</span><br>
								<div class="BotonesHabilidades">
									<a href="#" id="habilidadA" class="botonHabilidad">A</a>
									<a href="#" id="habilidadB" class="botonHabilidad">B</a>
									<a href="#" id="habilidadC" class="botonHabilidad">C</a>
									<a href="#" id="habilidadD" class="botonHabilidad">D</a>
									<a href="#" id="habilidadE" class="botonHabilidad">E</a>
									<a href="#" id="habilidadF" class="botonHabilidad">F</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan=2>
							<div class="Suerte">
								<span>Seleccione la puntuacion en Suerte:</span><br>
								<div class="BotonesSuerte">
									<a href="#" id="suerteA" class="botonSuerte">A</a>
									<a href="#" id="suerteB" class="botonSuerte">B</a>
									<a href="#" id="suerteC" class="botonSuerte">C</a>
									<a href="#" id="suerteD" class="botonSuerte">D</a>
									<a href="#" id="suerteE" class="botonSuerte">E</a>
									<a href="#" id="suerteF" class="botonSuerte">F</a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan=2 align="center">
							<div class="Bonos">
								<span>Bono / Penalizacion (Total)</span><br>
								<a href="#" id="restarBono" class="simbolos">-</a>
								<input type="text" value="0" size="1" style="text-align:center" id="bonoDados">
								<a href="#" id="sumarBono" class="simbolos">+</a>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan=2 align="center">
							<input type="radio" name="tipoTirada" id="radioNormal" value="normal" checked>Normal
							<input type="radio" name="tipoTirada" id="radioSuerte" value="suerte">Con Suerte
							<input type="radio" name="tipoTirada" id="radioDestino" value="destino">Fatídica
						</td>
					<tr>
						<td align="left">
							<a href="#" id="hacerTirada" class="standardButtonMedium">Tirar!</a>
						</td>
						<td align="right">
							<a href="#" id="resetear" class="standardButtonMedium">Resetear</a>
						</td>
					</tr>
					<tr>
						<td colspan=3 align="center">
							<div class="Errores">
						</td>
					</tr>
				</table>
				</div>
		</div>

	</body>
</html>
