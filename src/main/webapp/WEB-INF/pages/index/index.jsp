<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Menu Principal</title>
		<link rel="stylesheet" href="./jsp/stylesheet.css" type="text/css">
	</head>
	<body>		
		<script src="./jsp/jquery-1.10.2.min.js"></script>
		<script src="./jsp/index/index.js"></script>
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

			<div class="Menu">
				<a href="#" id="estadisticas" class="standardButton">Estadisticas</a>
				<a href="#" id="combate" class="standardButton">Combate</a>
				<a href="#" id="creacionPj" class="standardButton">Armar Personajes</a>
			</div>

			<div class="MenuEstadisticas">
				<button class="standardButton" id="eSimple" type="button">Simple</button><br>
				<button class="standardButton" id="eCombate" type="button">Combate</button><br>
				<button class="standardButton" id="eDestino" type="button">Destino</button><br>
				<button class="standardButton" id="eArmas" type="button">Armas</button><br>
			</div>
		</div>

	</body>
</html>
