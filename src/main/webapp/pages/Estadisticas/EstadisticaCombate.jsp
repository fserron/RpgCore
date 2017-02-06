<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Estadistica de Combate</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles/stylesheet.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/js/jquery-ui-1.12.1.custom/jquery-ui.css" type="text/css">
	
</head>
<body>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.blockUI.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/js/Estadisticas/EstadisticaCombate.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Estadisticas/Graficos.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Highcharts-4.0.4/js/highcharts.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Highcharts-4.0.4/js/modules/data.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/Highcharts-4.0.4/js/modules/drilldown.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script type="text/javascript">
			$(document).ready(function() {
				var p1 = new EstadisticaCombate();
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
									<td colspan="2" align="center"><span class="Titulo">Agresor</span></td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<form:hidden path="carasDado" id="carasDado" />
										<div class="Caras">
											<span>Seleccione el tipo de Dado:</span><br>
											<div class="BotonesCaras">
												<a href="#" id="4caras" class="botonCaras2">4</a> 
												<a href="#" id="6caras" class="botonCaras2">6</a>
												<a href="#" id="8caras" class="botonCaras2">8</a>
												<a href="#" id="10caras" class="botonCaras2">10</a>
												<a href="#" id="12caras" class="botonCaras2">12</a>
												<a href="#" id="20caras" class="botonCaras2">20</a>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<div class="Atributos">
											<span>Atributo:</span>
											<input type="text" id="atributoText" readonly style="border:0; color:#f6931f; font-weight:bold;">
											<br>
											<div id="BotonesAtributos" align="center" />
											<form:hidden path="atributo" id="atributo" />
										</div>
									</td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<div class="Habilidades">
											<span>Habilidad:</span>
											<input type="text" id="habilidadText" readonly style="border:0; color:#f6931f; font-weight:bold;">
											<br>
											<div id="BotonesHabilidades" align="center" />
											<form:hidden path="habilidad" id="habilidad" />
										</div>
									</td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<div class="Suerte">
											<span>Suerte:</span>
											<input type="text" id="suerteText" readonly style="border:0; color:#f6931f; font-weight:bold;">
											<br>
											<div id="BotonesSuerte" align="center" />
											<form:hidden path="suerte" id="suerte" />
										</div>
									</td>
								</tr>
								<tr>
									<td align="center" width=10%><span>Bono Arma: </span></td>
									<td align="center" width=10%>
										<a href="#" id="restarBonoArma" class="simbolos">-</a>
										<form:input path="bonoArma" type="text" value="0" size="1" style="text-align:center" id="bonoDadosArma" />
										<a href="#" id="sumarBonoArma" class="simbolos">+</a>
									</td>
								</tr>
								<tr>
									<td align="center" width=10%><span>Daño Base: </span></td>
									<td align="center" width=10%>
										<a href="#" id="restarDanoBase" class="simbolos">-</a>
										<form:input path="bonoBase" type="text" value="0" size="1" style="text-align:center" id="bonoBase" />
										<a href="#" id="sumarDanoBase" class="simbolos">+</a>
									</td>
								</tr>
								<tr>
									<td align="center" width=10%><span>Bono / Pen.</span></td>
									<td align="center" width=10%>
										<a href="#" id="restarBono" class="simbolos">-</a>
										<form:input path="bono" type="text" value="0" size="1" style="text-align:center" id="bonoDados" />
										<a href="#" id="sumarBono" class="simbolos">+</a>
									</td>
								</tr>
								<tr>
									<td align="center" width=10%><span>Tiradas: </span></td>
									<td align="center" width=10%>
										<form:select id="numTiradas" path="numTiradas">
											<form:options cssStyle="align:left" items="${model.cantidadTiradas}" />
										</form:select>
									</td>
								</tr>
								<tr>
									<td align="center">
										<span>¿Usar Suerte? </span>
										<form:checkbox path="conSuerte" id="usaSuerte"/>
									</td>
									<td align="center">
										<span>¿Es Fatidica? </span>
										<form:checkbox path="fatidica" id="esFatidica"/>
									</td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<span>¿Versus Victima?</span>
										<input type="checkbox" id="vsVictima"/>
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
					<td width=20% height=100% align="center" style="vertical-align: top">
						<div class="panelVictima" style="text-align: center">
							<table id="tablaVictima" border="1">
								<tr>
									<td colspan="2" align="center"><span class="Titulo">Victima</span></td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<div class="Objetivo">
											<span>Seleccione la puntuacion en Defensa:</span><br>
											<div class="BotonesDefensa" align="center">
												<table border="1">
													<tr>
														<th>1</th>
														<th>2</th>
														<th>3</th>
														<th>4</th>
														<th>5</th>
														<th>6</th>
														<th>7</th>
														<th>8</th>
														<th>9</th>
														<th>10</th>
													</tr>
													<tr>
														<td><form:radiobutton id="defensa1" class="radioDefensa" path="defensa" value="1" /></td>
														<td><form:radiobutton id="defensa2" class="radioDefensa" path="defensa" value="2" /></td>
														<td><form:radiobutton id="defensa3" class="radioDefensa" path="defensa" value="3" /></td>
														<td><form:radiobutton id="defensa4" class="radioDefensa" path="defensa" value="4" /></td>
														<td><form:radiobutton id="defensa5" class="radioDefensa" path="defensa" value="5" /></td>
														<td><form:radiobutton id="defensa6" class="radioDefensa" path="defensa" value="6" /></td>
														<td><form:radiobutton id="defensa7" class="radioDefensa" path="defensa" value="7" /></td>
														<td><form:radiobutton id="defensa8" class="radioDefensa" path="defensa" value="8" /></td>
														<td><form:radiobutton id="defensa9" class="radioDefensa" path="defensa" value="9" /></td>
														<td><form:radiobutton id="defensa10" class="radioDefensa" path="defensa" value="10" /></td>
													</tr>
												</table>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan=2 align="center">
										<div class="Armadura">
											<span>Seleccione la puntuacion en Armadura:</span><br>
											<div class="BotonesArmadura" align="center">
												<table border="1">
													<tr>
														<th>1</th>
														<th>2</th>
														<th>3</th>
														<th>4</th>
														<th>5</th>
														<th>6</th>
														<th>7</th>
														<th>8</th>
														<th>9</th>
														<th>10</th>
													</tr>
													<tr>
														<td><form:radiobutton id="armadura1" class="radioArmadura" path="armadura" value="1" /></td>
														<td><form:radiobutton id="armadura2" class="radioArmadura" path="armadura" value="2" /></td>
														<td><form:radiobutton id="armadura3" class="radioArmadura" path="armadura" value="3" /></td>
														<td><form:radiobutton id="armadura4" class="radioArmadura" path="armadura" value="4" /></td>
														<td><form:radiobutton id="armadura5" class="radioArmadura" path="armadura" value="5" /></td>
														<td><form:radiobutton id="armadura6" class="radioArmadura" path="armadura" value="6" /></td>
														<td><form:radiobutton id="armadura7" class="radioArmadura" path="armadura" value="7" /></td>
														<td><form:radiobutton id="armadura8" class="radioArmadura" path="armadura" value="8" /></td>
														<td><form:radiobutton id="armadura9" class="radioArmadura" path="armadura" value="9" /></td>
														<td><form:radiobutton id="armadura10" class="radioArmadura" path="armadura" value="10" /></td>
													</tr>
												</table>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<table border="0">
											<tr>
												<td colspan="2" align="center"><span>Sistemas Emboque</span></td>
											</tr>
											<tr>
												<td align="center"><span>Exitos Objetivo: </span></td>
												<td align="right">
													<form:select path="objetivo" id="objetivo">
														<form:options cssStyle="align:left" items="${model.listaValoresBlank}" />
													</form:select>
												</td>
											</tr>
											<tr>
												<td align="center"><span>Dificultad Tirada: </span></td>
												<td align="right">
													<form:select path="dificultad" id="dificultad">
														<form:options cssStyle="align:left" items="${model.listaValoresBlank}" />
													</form:select>
												</td>
											</tr>
											<tr>
												<td align="center"><span>Dados a Retener: </span></td>
												<td align="right">
													<form:select path="retencion" id="retencion">
														<form:options cssStyle="align:left" items="${model.listaValoresBlank}" />
													</form:select>
												</td>
											</tr>
											<tr>
												<td align="center"><span>Dados a Sustraer: </span></td>
												<td align="right">
													<form:select path="sustraccion" id="sustraccion">
														<form:options cssStyle="align:left" items="${model.listaValoresBlank}" />
													</form:select>
												</td>
											</tr>
											<tr>
												<td align="center"><span>Pen. Atacante: </span></td>
												<td align="right">
													<form:select path="penalizacion" id="penalizacion">
														<form:options cssStyle="align:left" items="${model.listaValoresBlank}" />
													</form:select>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td align="center">
										<table border="0">
											<tr>
												<td align="left">
													<span>¿Aplicar Sistemas Daño?</span>
												</td>
												<td align="center">
													<form:checkbox path="aplicarDano" id="aplicarDano"/>
												</td>
											</tr>
											<tr>
												<td align="left">
													<span>¿Restar Defensa a Exitos?</span>
												</td>
												<td align="center">
													<form:checkbox path="restarDefensa" id="restarDefensa" class="dano"/>
												</td>
											</tr>
											<tr>
												<td align="left">
													<span>¿Sumar arma a tirada daño?</span>
												</td>
												<td align="center">
													<form:checkbox path="sumarArma" id="sumarArma" class="dano"/>
												</td>
											</tr>
											<tr>
												<td align="left">
													<span>¿Que tipo de dado se usa?</span>
												</td>
												<td align="center">
													<form:select path="carasDadoDano" id="carasDadoDano" class="dano">
														<form:options cssStyle="align:left" items="${model.listaValoresCarasDado}" />
													</form:select>
												</td>
											</tr>
											<tr>
												<td align="left">
													<span>¿Explosión?</span>
												</td>
												<td align="center">
													<form:select path="valorExplosion" id="valorExplosion" class="dano">
														<form:options cssStyle="align:left" items="${model.listaCaras}" />
													</form:select>
												</td>
											</tr>
										</table>
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
							<tr>
								<td colspan="4" align="center">
									<input type="button" id="areas" class="standardButtonMedium" value="Grafico de Area" />
									<input type="button" id="torta" class="standardButtonMedium" value="Grafico de Torta" />
									<input type="button" id="tabla" class="standardButtonMedium" value="T. Probabilidades" />
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
