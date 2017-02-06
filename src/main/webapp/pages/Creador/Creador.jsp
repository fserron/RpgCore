<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Creador de Personajes</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/stylesheet.css"
	type="text/css">
</head>
<body>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Creador/Creador.js"></script>
	<script type="text/javascript">
			$(document).ready(function() {				
				var p1 = new Creador();
				p1.init();
			});
		</script>
		
	<div class="Container">
		<table border="1" width="100%"> <!-- Contenedor general -->
			<tr>
				<td>
					<div class="Opciones">
						<table border="1" width="100%"> <!-- Contenedor Atributos -->
							<tr>
								<td align="center">
									<table border="1" id="contenedorFisicos"> <!-- Contenedor Atributos Fisicos -->
										<tr>
											<td width="20%" align="center"><span class="textoAtributos" id="fuerzaLabel">Fuerza</span></td>
											<td width="10%" class="contenedorPuntuacion" align="center">
												<a href="#" id="restarFuerza" class="botonPuntuacionMedium">-</a>
												<span style="text-align: center" id="fuerzaLabel" class="puntuacionAtributos">1</span>
												<a href="#" id="sumarFuerza" class="botonPuntuacionMedium">+</a>
											</td>
											<td width="20%" align="center"><span class="textoAtributos" id="destrezaLabel">Destreza</span></td>
											<td width="10%" class="contenedorPuntuacion" align="center">
												<a href="#" id="restarDestreza" class="botonPuntuacionMedium">-</a>
												<span style="text-align: center" id="destrezaLabel" class="puntuacionAtributos">1</span>
												<a href="#" id="sumarDestreza" class="botonPuntuacionMedium">+</a>
											</td>
											<td width="20%" align="center"><span class="textoAtributos" id="resistenciaLabel">Resistencia</span></td>
											<td width="10%" class="contenedorPuntuacion" align="center">
												<a href="#" id="restarResistencia" class="botonPuntuacionMedium">-</a>
												<span style="text-align: center" id="resistenciaLabel" class="puntuacionAtributos">1</span>
												<a href="#" id="sumarResistencia" class="botonPuntuacionMedium">+</a>
											</td>
											<td width="10%" align="center">
												<input type="submit" id="primariosFisicos" class="standardButtonMedium" value="Primarios" />
											</td>
										</tr>
									</table>
									<table border="1" id="contenedorMentales"> <!-- Contenedor Atributos Mentales -->
										<tr>
											<td width="20%" align="center"><span class="textoAtributos" id="inteligenciaLabel">Inteligencia</span></td>
											<td width="10%" class="contenedorPuntuacion" align="center">
												<a href="#" id="restarInteligencia" class="botonPuntuacionMedium">-</a>
												<span style="text-align: center" id="inteligenciaLabel" class="puntuacionAtributos">1</span>
												<a href="#" id="sumarInteligencia" class="botonPuntuacionMedium">+</a>
											</td>
											<td width="20%" align="center"><span class="textoAtributos" id="astuciaLabel">Astucia</span></td>
											<td width="10%" class="contenedorPuntuacion" align="center">
												<a href="#" id="restarAstucia" class="botonPuntuacionMedium">-</a>
												<span style="text-align: center" id="astuciaLabel" class="puntuacionAtributos">1</span>
												<a href="#" id="sumarAstucia" class="botonPuntuacionMedium">+</a>
											</td>
											<td width="20%" align="center"><span class="textoAtributos" id="resolucionLabel">Resolucion</span></td>
											<td width="10%" class="contenedorPuntuacion" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionMedium">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionAtributos">1</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionMedium">+</a>
											</td>
											<td width="10%" align="center">
												<input type="submit" id="primariosMentales" class="standardButtonMedium" value="Primarios" />
											</td>
										</tr>
									</table>
									<table align="left" border="1" id="contenedorSobrenaturales"> <!-- Contenedor Atributos Sobrenaturales -->
										<tr>
											<td width="80%" align="center"><span class="textoAtributos" id="suerteLabel">Suerte</span></td>
											<td width="9.7%" class="contenedorPuntuacion" align="center">
												<a href="#" id="restarSuerte" class="botonPuntuacionMedium">-</a>
												<span style="text-align: center" id="suerteLabel" class="puntuacionAtributos">1</span>
												<a href="#" id="sumarSuerte" class="botonPuntuacionMedium">+</a>
											</td>
											<td width="10%" align="center">
												<a href="#" id="reiniciar" class="standardButtonMedium">Reiniciar</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<div class="Opciones">
						<table width="100%" border="1"> <!-- Contenedor Habilidades -->
							<tr>
								<td align="center">
									<table width="100%" border="1"> <!-- Contenedor Habilidades Fisicas -->
										<tr>
											<td colspan="2" align="center"><span class="textoAtributos">Fisicas</span></td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Atletismo</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Combate</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Conduccion</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Pericias</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Punteria</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Sigilo</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Supervivencia</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
									</table>
								</td>
								<td align="center">
									<table width="100%" border="1"> <!-- Contenedor Habilidades Mentales -->
										<tr>
											<td colspan="2" align="center"><span class="textoAtributos">Mentales</span></td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Alerta</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Ciencia</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Concentracion</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Investigacion</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Logistica</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Medicina</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Ocultismo</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
									</table>
								</td>
								<td align="center">
									<table width="100%" border="1"> <!-- Contenedor Habilidades Sociales -->
										<tr>
											<td colspan="2" align="center"><span class="textoAtributos">Sociales</span></td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Burocracia</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Callejeo</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Empatia</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Engaño</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Expresion Artistica</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Intimidacion</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
										<tr>
											<td width="75%"><span class="textoHabilidades">Sociabilizar</span></td>
											<td width="25%" align="center">
												<a href="#" id="restarResolucion" class="botonPuntuacionSmall">-</a>
												<span style="text-align: center" id="resolucionLabel" class="puntuacionHabilidades">0</span>
												<a href="#" id="sumarResolucion" class="botonPuntuacionSmall">+</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
				<td valign="top">
					<div class="PuntosDisponibles">
						<table border="1" width="100%"> <!-- Contenedor Titulo Puntos disponibles -->
							<tr>
								<td align="center">
									<table border="1"> <!-- Contenedor Puntos Atributos Fisicos -->
										<tr>
											<td><span class="puntosDisponibles" id="disponibleFisicos">0</span></td>
										</tr>
									</table>
									<table border="1"> <!-- Contenedor Puntos Atributos Mentales -->
										<tr>
											<td><span class="puntosDisponibles" id="disponibleMentales">0</span></td>
										</tr>
									</table>
									<table border="1"> <!-- Contenedor Puntos Atributos Extras -->
										<tr>
											<td><span class="puntosDisponibles" id="disponibleExtras">0</span></td>
										</tr>
									</table>
									<table border="1"> <!-- Contenedor Puntos Habilidades -->
										<tr>
											<td><span class="puntosDisponibles" id="disponibleHabilidades">0</span></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
				<td valign="top" width="3%">
					<div class="divFlotante">
						<span class="titulo90g">Disponibles</span>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>