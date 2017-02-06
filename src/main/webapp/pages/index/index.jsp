<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Principal</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/styles/stylesheet.css"
	type="text/css">
</head>
<body>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/index/index.js"></script>
	<script type="text/javascript">
			$(document).ready(function() {				
				var p1 = new index();
				p1.init();
			});
		</script>

	<div class="Container">
		<div class="Header">
			<span>Elija el modo</span>
		</div>

		<form:form method="post" action="eleccion" commandName="user">
			<input type="hidden" id="eleccion" name="eleccion" />
			<div class="Menu">
				<input type="submit" id="tirador" class="standardButton"
					value="Tirador de Dados" />
				<button id="estadisticas" class="standardButton">Estadisticas</button>
				<input type="submit" id="combate" class="standardButton"
					value="Combate" /> <input type="submit" id="creacionPj"
					class="standardButton" value="Armar Personajes" />
			</div>

			<div class="MenuEstadisticas">
				<input type="submit" class="standardButton" name="eSimple"
					id="eSimple" type="button" value="Simple" /> <input type="submit"
					class="standardButton" name="eCombate" id="eCombate" type="button"
					value="Combate" /> <input type="submit" class="standardButton"
					name="eDestino" id="eDestino" type="button" value="Destino" /> <input
					type="submit" class="standardButton" name="eArmas" id="eArmas"
					type="button" value="Armas" />
			</div>
		</form:form>
	</div>

</body>
</html>