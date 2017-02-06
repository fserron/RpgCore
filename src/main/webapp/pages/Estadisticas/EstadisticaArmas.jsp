<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Estadistica de Armas</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/stylesheet.css"
	type="text/css">
</head>
<body>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.blockUI.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/js/Estadisticas/EstadisticaArmas.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Estadisticas/Graficos.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Highcharts-4.0.4/js/highcharts.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Highcharts-4.0.4/js/modules/data.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Highcharts-4.0.4/js/modules/drilldown.js"></script>
	<script type="text/javascript">
			$(document).ready(function() {
				var p1 = new EstadisticaArmas();
				p1.init();
			});
		</script>

	<div class="Container">
		<table border="1">
			<tr>
				<form:form id="eSimpleForm" method="post" modelAttribute="model">
					<td valign="top" width=20% height=100%>
						<div class="Opciones">
							<table border="1">
								<tr>
									<td colspan="2" align="center"><span class="Titulo">Caracteristicas</span></td>
								</tr>
								<tr>
									<td align="center" width=10%><span>Bono Arma: </span></td>
									<td align="center" width=10%>
										<a href="#" id="restarBonoArma" class="simbolos">-</a>
										<form:input path="bonoArma" class="bonos" type="text" value="0" size="1" style="text-align:center" id="bonoArma" />
										<a href="#" id="sumarBonoArma" class="simbolos">+</a>
									</td>
								</tr>
								<tr>
									<td align="center" width=10%><span>Daño Base: </span></td>
									<td align="center" width=10%>
										<a href="#" id="restarBonoBase" class="simbolos">-</a>
										<form:input path="bonoBase" class="bonos" type="text" value="0" size="1" style="text-align:center" id="bonoBase" />
										<a href="#" id="sumarBonoBase" class="simbolos">+</a>
									</td>
								</tr>
								<tr>
									<td align="center" width=10%><span>Bono / Pen.</span></td>
									<td align="center" width=10%>
										<a href="#" id="restarBono" class="simbolos">-</a>
										<form:input path="bono" class="bonos" type="text" value="0" size="1" style="text-align:center" id="bono" />
										<a href="#" id="sumarBono" class="simbolos">+</a>
									</td>
								</tr>
								<tr>
									<td align="center" width=10%><span>¿Es Arma de Fuego?</span></td>
									<td align="center">
										<form:checkbox path="armaFuego" id="armaFuego"/>
									</td>
								</tr>
								<tr>
									<td align="center" width=10%>
										<div class="BotonesPotencia">
											<table border="1">
												<tr>
													<td align="center" width=10%><span>Potencia: </span></td>
												</tr>
												<tr>
													<td align="center">
														<table border="1">
															<tr>
																<th>1</th>
																<th>2</th>
																<th>3</th>
																<th>4</th>
																<th>5</th>
															</tr>
															<tr>
																<td><form:radiobutton id="potencia1" class="radioPotencia" path="potencia" value="1" /></td>
																<td><form:radiobutton id="potencia2" class="radioPotencia" path="potencia" value="2" /></td>
																<td><form:radiobutton id="potencia3" class="radioPotencia" path="potencia" value="3" /></td>
																<td><form:radiobutton id="potencia4" class="radioPotencia" path="potencia" value="4" /></td>
																<td><form:radiobutton id="potencia5" class="radioPotencia" path="potencia" value="5" /></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
									</td>
								</tr>
								<!-- 
								<tr>
									<td align="center" width=10%><span>¿Explosión?</span></td>
									<td align="center">
										<form:select path="valorExplosion" id="valorExplosion" class="dano">
											<form:options cssStyle="align:left" items="${model.listaCaras}" />
										</form:select>
									</td>
								</tr>
								 -->
								<tr>
									<td align="center" width=10%><span>Tiradas: </span></td>
									<td align="center" width=10%>
										<form:select id="numTiradas" path="numTiradas">
											<form:options cssStyle="align:left" items="${model.cantidadTiradas}" />
										</form:select>
									</td>
								</tr>
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
					</td>
				</form:form>
				<td width=60% height=100% align="center" style="vertical-align: top">
					<div class="Resultados" style="text-align: center">
						<table id="tablaResultados" border="1">
							<tr>
								<td colspan="4"><span class="Titulo">Resultados</span></td>
							</tr>
							<tr>
								<td><span class="Informe">Promedio</span></td>
								<td><div id="promedio" /></td>
								<td><span class="Informe">Probabilidad Emboque</span></td>
								<td><div id="probEmboque" /></td>
							</tr>
							<tr>
								<td><span class="Informe">Minimo</span></td>
								<td><div id="danoMinimo" /></td>
								<td><span class="Informe">Maximo</span></td>
								<td><div id="danoMaximo" /></td>
							</tr>
							<tr>
								<td width=60% height=100% colspan="4">
									<div id="graficos" />
								</td>
							</tr>
							<tr>
								<td width=60% height=100% colspan="4">
									<div id="tablaEstadistica">
										<table border="1" align="center">
											<tr>
												<td width=60% colspan="2" align="left">
													<span class="Informe">Probabilidad Emboque</span>
													<table border="1">
														<tr>
															<th> </th>
															<th>H1</th>
															<th>H2</th>
															<th>H3</th>
															<th>H4</th>
															<th>H5</th>
														</tr>
														<tr id="p1"/>
														<tr id="p2"/>
														<tr id="p3"/>
														<tr id="p4"/>
														<tr id="p5"/>
													</table>
												</td>
												<td width=60% colspan="2" align="right">
													<span class="Informe">Promedio Daño</span>
													<table border="1">
														<tr>
															<th> </th>
															<th>H 1</th>
															<th>H 2</th>
															<th>H 3</th>
															<th>H 4</th>
															<th>H 5</th>
														</tr>
														<tr id="x1"/>
														<tr id="x2"/>
														<tr id="x3"/>
														<tr id="x4"/>
														<tr id="x5"/>
													</table>
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
