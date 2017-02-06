package backend.bo.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import backend.bo.EstadisticaBO;
import backend.bo.TiradaBO;
import backend.dao.ArmaDAO;
import backend.dao.DadoDAO;
import backend.entities.Arma;
import backend.entities.Tirada;
import backend.entities.TiradaCombate;

import common.entities.DTO.EstadisticaArmaDTO;
import common.entities.DTO.EstadisticaDTO;
import common.entities.DTO.TiradaCombateDTO;
import common.entities.DTO.TiradaDTO;

@Component
public class EstadisticaBOImpl implements EstadisticaBO {
	@Autowired
	private ArmaDAO armaDAO;
	
	@Autowired
	private DadoDAO dadoDAO;
	
	@Autowired
	private TiradaBO tiradaBO;
	
	public Float danoPromedioArma(EstadisticaArmaDTO dto, Integer muestra){
		
		Arma arma = armaDAO.obtenerArma(dto.getCodigoArma());
		
		List<Integer> total = new ArrayList<Integer>();

		for (int i = 0; i < muestra; i++){
			dto.getTiradaDTO().setBono(arma.getEmboque() + dto.getRafaga());
			
			Tirada emboque = tiradaBO.hacerTirada(dto.getTiradaDTO());
			
			if (emboque.getExitos() > 0){
				int totalDanyo = 0;
				List<Integer> dano = null;
				
				if (arma.isSupersonico()){ // Para armas de fuego
					int reserva = emboque.getExitos() + arma.getDanyo();
					dano = dadoDAO.hacerTiradaSimple(reserva);
				} else { // Para armas cuerpo a cuerpo
					int reserva = emboque.getExitos();
					dano = dadoDAO.hacerTiradaSimple(reserva);
					totalDanyo += dto.getTiradaDTO().getAtributo() + arma.getDanyo();
				}
				
				for (int a = 0; a < dano.size(); a++) totalDanyo += dano.get(a);
				
				total.add(totalDanyo);
				
			}
		}
		
		//Sumo el total de los daños
		int totalDanyo = 0;
		for (int a = 0; a < total.size(); a++) totalDanyo += total.get(a);

		return (float) totalDanyo / muestra;
	}
	
	public EstadisticaDTO estadisticaTirada(TiradaDTO dto, Integer muestra){
		EstadisticaDTO estadisticaDto = new EstadisticaDTO();
		
		List<Tirada> resultados = new ArrayList<Tirada>();
		Map<Integer, Integer> exitos = new HashMap<Integer, Integer>();
		Integer maximo = Integer.MIN_VALUE;
		Integer minimo = Integer.MAX_VALUE;
		Float totalExitos = (float) 0.0;
		Float totalFracasos = (float) 0.0;
		Integer tiradasExitosas = 0;
		
		for (int prueba = 0; prueba < muestra; prueba++){			
			Tirada emboque = tiradaBO.hacerTirada(dto);
			
			int cantExitos = emboque.getExitos() - emboque.getFracasos();
			
			//Maxima cantidad de exitos
			if (cantExitos > maximo) maximo = cantExitos;
			
			//Minima cantidad de exitos
			if (cantExitos < minimo) minimo = cantExitos;
			
			//Total de exitos, para calcular el promedio
			totalExitos += emboque.getExitos();
			
			//Total de fracasos, para calcular el promedio
			totalFracasos += emboque.getFracasos();
			
			//Si hubieron exitos
			if (cantExitos > 0) tiradasExitosas++;
			
			//Voy creando un mapa con exitos / cantidad de veces
			Integer tempExitos = exitos.get(cantExitos);
			
			if (tempExitos != null)
				tempExitos++;
			else
				tempExitos = 1;
			
			exitos.put(cantExitos, tempExitos);
			
			resultados.add(emboque);
		}
		
		estadisticaDto.setResultadoTiradas(resultados);
		estadisticaDto.setMaximo(maximo);
		estadisticaDto.setMinimo(minimo);
		estadisticaDto.setPromedioExitos(this.calcularPromedio(totalExitos, muestra));
		estadisticaDto.setPromedioFracasos(this.calcularPromedio(totalFracasos, muestra));
		estadisticaDto.setProbabilidadExito(this.calcularProbabilidad(tiradasExitosas.floatValue(), muestra));
		estadisticaDto.setExitosTirada(exitos);		
		
		return estadisticaDto;
	}

	public EstadisticaDTO estadisticaCombateTirada(TiradaCombateDTO dto, Integer muestra){
		EstadisticaDTO estadisticaDto = new EstadisticaDTO();
		
		List<Tirada> resultados = new ArrayList<Tirada>();
		Map<Integer, List<List<Integer>>> mapaDano = new HashMap<Integer, List<List<Integer>>>();
		//Num. Exitos -> 
		Integer maximo = Integer.MIN_VALUE;
		Integer minimo = Integer.MAX_VALUE;
		Float totalDano = (float) 0.0;
		Float totalAutoDano = (float) 0.0;
		Integer contadorGolpes = 0;
		
		for (int prueba = 0; prueba < muestra; prueba++){			
			TiradaCombate tiradaDano = tiradaBO.hacerTiradaCombate(dto);
			
			int dano = tiradaDano.getDano();
			
			//Maxima cantidad de exitos
			if (tiradaDano.getDano() > maximo) maximo = dano;
			
			//Minima cantidad de exitos
			if (tiradaDano.getDano() < minimo) minimo = dano;
			
			//Total de daño, para calcular el promedio
			totalDano += tiradaDano.getDano();
			
			//Total de fracasos, para calcular el promedio
			totalAutoDano += tiradaDano.getAutoDano();
			
			//Si hubo daño, significa que el ataque tuvo exito
			if (tiradaDano.getDano() > 0) contadorGolpes++;
			
			//Voy creando un mapa con exitos / cantidad de veces
			List<List<Integer>> tempDano = mapaDano.get(dano);
			if (tempDano == null) tempDano = new ArrayList<List<Integer>>();
				
			tempDano.add(tiradaDano.getResultadoTirada());
			
			mapaDano.put(dano, tempDano);
			
			resultados.add(tiradaDano);
		}
		
		estadisticaDto.setResultadoTiradas(resultados);
		estadisticaDto.setMaximo(maximo);
		estadisticaDto.setMinimo(minimo);
		estadisticaDto.setPromedioExitos(this.calcularPromedio(totalDano, muestra));
		estadisticaDto.setPromedioFracasos(this.calcularPromedio(totalAutoDano, muestra));
		estadisticaDto.setProbabilidadExito(this.calcularProbabilidad(contadorGolpes.floatValue(), muestra));
		estadisticaDto.setMapaDano(mapaDano);
		
		return estadisticaDto;
	}
	
	public EstadisticaDTO generarTabla(TiradaCombateDTO dto, Integer muestra){
		EstadisticaDTO estadisticaDto = new EstadisticaDTO();

		Integer contadorGolpes = 0;
		Float totalDano = (float) 0.0;
		
		for (int prueba = 0; prueba < muestra; prueba++){			
			TiradaCombate tiradaDano = tiradaBO.hacerTiradaCombate(dto);
			
			//Si hubo daño, significa que el ataque tuvo exito
			if (tiradaDano.getDano() > 0) contadorGolpes++;
			
			//Total de daño, para calcular el promedio
			totalDano += tiradaDano.getDano();
		}
		
		estadisticaDto.setProbabilidadExito(this.calcularProbabilidad(contadorGolpes.floatValue(), muestra));
		estadisticaDto.setPromedioExitos(this.calcularPromedio(totalDano, muestra));
		
		return estadisticaDto;
	}
	
	public String calcularProbabilidad(Float objetivo, Integer total){
		String resultado;
		
		Double probabilidad = Double.valueOf(objetivo / total) * 100;
		
		NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		resultado = format.format(probabilidad);
		
		return resultado;
	}

	public String calcularPromedio(Float total, Integer muestra){
		String resultado = null;
		
		Double promedio = Double.valueOf(total / muestra);
		
		NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		resultado = format.format(promedio);

		return resultado;
	}
}
