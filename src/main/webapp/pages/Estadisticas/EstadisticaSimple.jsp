<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Estadistica Simple</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/stylesheet.css"
	type="text/css">
</head>
<body>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.blockUI.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/js/Estadisticas/EstadisticaSimple.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Highcharts-4.0.4/js/highcharts.js"></script>
	<script type="text/javascript">
			$(document).ready(function() {
				var p1 = new EstadisticaSimple();
				p1.init();
			});
		</script>

	<div class="Container">
		<table border="1">
			<tr>
				<td><form:form id="eSimpleForm" method="post" modelAttribute="model">
						<div class="Opciones">
							<table border="1">
								<tr>
									<td colspan="2" align="center">
										<form:hidden path="atributo" id="atributo" />
										<div class="Atributos">
											<span>Seleccione la puntuacion en el Atributo:</span><br>
											<div class="BotonesAtributos">
												<a href="#" id="atributo5" class="botonAtributo">5</a> 
												<a href="#" id="atributo4" class="botonAtributo">4</a>
												<a href="#" id="atributo3" class="botonAtributo">3</a>
												<a href="#" id="atributo2" class="botonAtributo">2</a>
												<a href="#" id="atributo1" class="botonAtributo">1</a>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<form:hidden path="habilidad" id="habilidad" />
										<div class="Habilidades">
											<span>Seleccione la puntuacion en la Habilidad:</span><br>
											<div class="BotonesHabilidades" align="center">
												<a href="#" id="habilidad5" class="botonHabilidad">5</a>
												<a href="#" id="habilidad4" class="botonHabilidad">4</a>
												<a href="#" id="habilidad3" class="botonHabilidad">3</a>
												<a href="#" id="habilidad2" class="botonHabilidad">2</a>
												<a href="#" id="habilidad1" class="botonHabilidad">1</a>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<form:hidden path="suerte" id="suerte" />
										<div class="Suerte">
											<span>Seleccione la puntuacion en Suerte:</span><br>
											<div class="BotonesSuerte" align="center">
												<a href="#" id="suerte5" class="botonSuerte">5</a>
												<a href="#" id="suerte4" class="botonSuerte">4</a>
												<a href="#" id="suerte3" class="botonSuerte">3</a>
												<a href="#" id="suerte2" class="botonSuerte">2</a>
												<a href="#" id="suerte1" class="botonSuerte">1</a>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="2">
										<div class="Bonos">
											<span>Bono / Penalizacion (Total)</span><br> <a href="#" id="restarBono" class="simbolos">-</a>
											<form:input path="bono" type="text" value="0" size="1" style="text-align:center" id="bonoDados" />
											<a href="#" id="sumarBono" class="simbolos">+</a>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<table border="1">
											<tr>
												<td align="center">
													<span>Tiradas: </span> 
													<form:select path="numTiradas">
														<form:options cssStyle="align:left" items="${model.cantidadTiradas}" />
													</form:select>
												</td>
												<td align="center"><span>Exitos Objetivo: </span> 
													<a href="#" id="restarObjetivo" class="simbolos">-</a> 
													<form:input path="objetivo" type="text" value="0" size="1" style="text-align:center" id="exitosObjetivo" /> 
													<a href="#" id="sumarObjetivo" class="simbolos">+</a>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<!-- 
								<tr>
									<td colspan=2 align="center">
										<c:forEach var="tipo" items="${model.tipos}">
											<form:radiobutton id="radio_${tipo}" path="tipoTirada" value="${tipo}" />${tipo.description}
										</c:forEach>
									</td>
								</tr>
								 -->
								<tr>
									<td align="left"><input type="button" id="hacerTirada" class="standardButtonMedium" value="Tirar!" /></td>
									<td align="right"><a href="#" id="resetear" class="standardButtonMedium">Resetear</a></td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<div class="Errores" />
									</td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<input type="hidden" id="eleccion" name="eleccion" />
										<input type="submit" id="volver" class="standardButtonMedium" value="Volver al Menu" />
									</td>
								</tr>
							</table>
						</div>
					</form:form></td>
				<td rowspan=6 align="center" style="vertical-align: top">
					<div class="Resultados" style="text-align: center">
						<table id="tablaResultados" border="1">
							<tr>
								<td colspan="4"><span class="Titulo">Resultados</span></td>
							</tr>
							<tr>
								<td><span class="Informe">Promedio Normal</span></td>
								<td><div id="promedioNormal" /></td>
								<td><span class="Informe">Probabilidad Exito Normal</span></td>
								<td><div id="probExitoNormal" /></td>
							</tr>
							<tr>
								<td><span class="Informe">Promedio con Suerte</span></td>
								<td><div id="promedioSuerte" /></td>
								<td><span class="Informe">Probabilidad Exito con Suerte</span></td>
								<td><div id="probExitoSuerte" /></td>
							</tr>
							<tr>
								<td><span class="Informe">Promedio Fatidico</span></td>
								<td><div id="promedioFatidico" /></td>
								<td><span class="Informe">Probabilidad Exito Fatidico</span></td>
								<td><div id="probExitoFatidico" /></td>
							</tr>
							<tr>
								<td colspan="4"><div id="tablaEstadistica" /></td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
