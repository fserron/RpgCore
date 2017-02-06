package backend.bo.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import backend.bo.TiradaBO;
import backend.dao.DadoDAO;
import backend.dao.impl.DadoDAOImpl;
import backend.entities.Tirada;
import backend.entities.TiradaCombate;

import common.entities.DTO.TiradaCombateDTO;
import common.entities.DTO.TiradaDTO;

@Component
public class TiradaBOImpl implements TiradaBO {
	
	private DadoDAO DadoDAO;
	
	public TiradaBOImpl(){
		DadoDAO = new DadoDAOImpl();
	}
	
	public Tirada hacerTirada(TiradaDTO dto){
		Tirada tirada = null;
		boolean chance = dto.isChance();
		
		//Armamos el argumento "cantidad"
		Integer totalDados = 0;
		totalDados += dto.getHabilidad();
		totalDados += dto.getBono();
		totalDados -= dto.getPenalizacion();
		
		List<Integer> resultadoTirada = null;
		
		if (dto.isLucky() && !dto.isChance()){
			resultadoTirada = hacerTiradaRecursiva(dto.getSuerte(), totalDados, 0);
		} else if (dto.isLucky() && dto.isChance()) { //Si es una tirada de destino y gasto suerte, ambos se anulan.
			chance = false;
			resultadoTirada = DadoDAO.hacerTiradaSimple(dto.getSuerte());
		} else {
			resultadoTirada = DadoDAO.hacerTiradaSimple(totalDados);
		}
		
		//Evaluamos los resultados
		int exitos = 0;
		int fracasos = 0;
		for (Integer dado : resultadoTirada){
			if (dto.getAtributo() >= dado && dado != 6) exitos++;
				
			if (chance && dado == 6) fracasos++;
		}
		
		tirada = new Tirada();
		tirada.setExitos(exitos);
		tirada.setFracasos(fracasos);
		Collections.sort(resultadoTirada);
		tirada.setResultadoTirada(resultadoTirada);
		
		return tirada;
	}
	
	private List<Integer> hacerTiradaRecursiva(Integer numRepeticion, Integer cantidad, Integer contador){
		
		//Hacemos la tirada
		List<Integer> resultadoTirada = DadoDAO.hacerTiradaSimple(cantidad);
		
		//Evaluamos los resultados
		int reroll = 0;
		for (Integer dado : resultadoTirada){
			if (dado != 6 && dado <= numRepeticion) reroll++;
		}
		
		if (reroll > 0 && contador < numRepeticion) {
			contador++;
			resultadoTirada.addAll(hacerTiradaRecursiva(numRepeticion, reroll, contador));
		}
		
		return resultadoTirada;
	}
	
	public TiradaCombate hacerTiradaCombate(TiradaCombateDTO dto){
		TiradaCombate tirada = new TiradaCombate();
		boolean chance = dto.isChance();
		boolean lucky = dto.isLucky();
		
		//Hacemos la tirada de emboque
		List<Integer> resultadoTirada = this.emboque(dto, chance, lucky);
		
		//Calculamos los exitos
		Integer exitos = this.calcularExitos(dto, resultadoTirada, chance);
		
		//Calculamos el daño
		Integer dano = this.calcularDano(dto, resultadoTirada, exitos);
		
		tirada.setExitos(exitos);
		tirada.setResultadoTirada(resultadoTirada);
		tirada.setDano(dano);
		
		//this.imprimirResultadosConsola(dto, tirada);
		
		return tirada;
	}

	private Integer calcularDano(TiradaCombateDTO dto, List<Integer> resultadoTirada, Integer exitos) {
		List<Integer> tiradaDano = null;
		List<Integer> tiradaFracaso = null;
		int dano = 0;
		
		//Si el golpe pego...
		if (conecto(dto, exitos)){
			
			if (dto.isAplicarDano()){ //Si hay una segunda tirada
				Integer dados = this.calcularDadosDano(dto, exitos);
				
				tiradaDano = DadoDAO.hacerTiradaVariable(dto.getCarasDadoDano(), dados);
				
				//Si habia una dificultad y hubo al menos un exito, agregamos al daño el daño base del arma
				dano = dto.getBonoDanoBase();
				
				//Contamos el daño
				for (Integer dado : tiradaDano) dano += dado;
				
				//Por ultimo, si hay armadura...
				if (dto.getArmadura() != null) { 
					//Si esta es menor que el daño, reduce el daño
					if (dto.getArmadura() < dano) dano -= dto.getArmadura();
					//Si la armadura es mayor o igual al daño, lo nulifica
					else if (dto.getArmadura() >= dano) dano = 0;
				}
			} else { //Si no hay otra tirada, sumo el total de los dados
				dano = dto.getBonoDanoBase(); //Sumo el daño base, si hay.
				
				for (int valorDado : resultadoTirada) {
					if (valorDado <= dto.getDificultad().getPuntuacion()){ //Si el dado es menor o igual que el atributo del atacante 
						dano += valorDado; //Se lo sumamos al daño
					}
				}
			}

		} else if (exitos < 0){ //Si hubo fracasos
			tiradaFracaso = DadoDAO.hacerTiradaVariable(dto.getCaras(), exitos * -1);
			
			if (tiradaFracaso != null && !tiradaFracaso.isEmpty()){
				for (Integer dado : tiradaFracaso){
					dano += dado;
				}
			}
			
			//Como es auto-daño, lo negamos
			dano = dano * -1;
			
		}

		return dano;
		
	}

	private List<Integer> emboque(TiradaCombateDTO dto, boolean chance, boolean lucky) {
		List<Integer> resultadoTirada;
		int caraNula = dto.getCaras(); //La cara del dado que es nula es la maxima
		
		Integer totalDados = calcularDadosEmboque(dto);
		
		//Si uso suerte, le sumamos
		if (dto.isLucky() && !chance){
			resultadoTirada = this.hacerTiradaCombateRecursiva(dto.getSuerte(), dto.getCaras(), totalDados, 0);
		} else if (chance && !dto.isLucky()) {
			totalDados = dto.getSuerte();
			resultadoTirada = DadoDAO.hacerTiradaVariable(dto.getCaras(), totalDados);
		} else if (dto.isExplota()){
			resultadoTirada = this.hacerTiradaCombateRecursiva(dto.getValorExplosion(), dto.getCaras(), totalDados, -1);
		} else { //En cualquier otro caso. Si es una tirada de destino y gasto suerte, ambos se anulan.
			resultadoTirada = DadoDAO.hacerTiradaVariable(dto.getCaras(), totalDados);
		}
		
		//Se ordenan los resultados
		Collections.sort(resultadoTirada);
		
		if (dto.getSustraccion() != 0){ //Si la victima nos resta dados
			if (dto.getSustraccion() < resultadoTirada.size()){ //Si hay mas exitos que la sustraccion...
				if (dto.getDificultad().isMenorIgual()){//Si la dificultad la mide algo del atacante
					for (int i = 0; i < dto.getSustraccion(); i++){ //Voy restando los mas bajos
						resultadoTirada.remove(0);
					}
				} else {
					for (int i = resultadoTirada.size() -1; i >= dto.getSustraccion(); i--){ //Voy restando los mas altos
						if (resultadoTirada.get(i) != caraNula) resultadoTirada.remove(i);
					}
				}
			} else { //Si no, se sacan todos los dados menos las caras nulas...
				for (int i = 1; i < caraNula; i++){
					while (resultadoTirada.contains(new Integer(i))) resultadoTirada.remove(new Integer(i));
				}
			}
		}
		
		if (dto.getRetencion() != 0){ //Nos quedamos con solo alguno de los exitos
			Integer retencion = dto.getRetencion();
			
			if (retencion < resultadoTirada.size()){ //Si hay mas exitos que la retencion...
				int sobrantes = resultadoTirada.size() - retencion;
				if (dto.getDificultad().isMenorIgual()){ //Si la dificultad la mide algo del atacante
					for (int i = resultadoTirada.size() - 1; i >= retencion; i--){ //Voy restando los mas altos
						resultadoTirada.remove(i);
					}
				} else {//Si la dificultad la mide algo del defensor
					for (int i = 0; i < sobrantes; i++){  
						if (resultadoTirada.lastIndexOf(caraNula) != -1) { //Voy restando las caras nulas...
							resultadoTirada.remove(resultadoTirada.lastIndexOf(caraNula));
						} else { //y las mas bajas
							resultadoTirada.remove(0);	
						}
					}
				}
			} //Si no, nos quedamos con la tirada entera
		}
		
		return resultadoTirada;
	}
	
	private Integer calcularExitos(TiradaCombateDTO dto, List<Integer> resultadoTirada, boolean chance) {
		int exitos = 0;
		int caraNula = dto.getCaras(); //La cara del dado que es nula es la maxima
		
		//Contamos los exitos
		if (dto.getDificultad() != null){
			for (Integer dado : resultadoTirada){
				if (dto.getDificultad().isMenorIgual()){
					if (dado <= dto.getDificultad().getPuntuacion() && dado != caraNula) exitos++;
				} else {
					if (dado >= dto.getDificultad().getPuntuacion() && dado != caraNula) exitos++;
				}
				
				if (chance && dado == caraNula) exitos--;
			}
			
		} else { //Si la tirada no tiene dificultad, contamos todos los dados menos la cara nula
			for (Integer dado : resultadoTirada){
				if (dado != caraNula) exitos++;
				
				if (chance && dado == caraNula) exitos--;
			}
		}
		
		return exitos;
	}
	
	private List<Integer> hacerTiradaCombateRecursiva(Integer numRepeticion, Integer caras, Integer cantidad, Integer contador){
		
		//Hacemos la tirada
		List<Integer> resultadoTirada = DadoDAO.hacerTiradaVariable(caras, cantidad);
		
		//Evaluamos los resultados
		int reroll = 0;
		for (Integer dado : resultadoTirada){
			if (dado != caras && dado <= numRepeticion) reroll++;
		}
		
		
		if (reroll > 0 && contador < numRepeticion) {
			if (contador > 0){
				contador++;
			}
			resultadoTirada.addAll(hacerTiradaCombateRecursiva(numRepeticion, caras, reroll, contador));
		}
		
		return resultadoTirada;
	}
	
	private Integer calcularDadosEmboque(TiradaCombateDTO dto){
		//Cantidad de dados a tirar
		Integer totalDados = 0;
		totalDados += dto.getHabilidad();
		totalDados += dto.getBono();
		totalDados += dto.getBonoArma();
		totalDados -= dto.getPenalizacion();
		
		return totalDados;
	}
	
	private Integer calcularDadosDano(TiradaCombateDTO dto, Integer exitos) {
		Integer totalDados = 0;
		
		totalDados += exitos - dto.getDefensa();
		totalDados += dto.getAtributo();
		totalDados += dto.getBonoArma();
		totalDados -= (dto.isRestarDefensa())? dto.getDefensa() : 0; //Serian los dados que quedan por arriba de la Defensa.
		
		return totalDados;
	}
	
	private boolean conecto(TiradaCombateDTO dto, int exitos) {
		boolean hit = false;
		
		//Establezco el numero objetivo de exitos para pegar
		int objetivo = 1; //Por defecto es 1.
		objetivo = (dto.getObjetivo() != null && dto.getObjetivo().getPuntuacion() != 0)? dto.getObjetivo().getPuntuacion() : 1;
		
		//Si necesitaba mas de uno para pegar...
		if (exitos >= objetivo){
			hit = true;
		}
		
		return hit;
	}
	
	private void imprimirResultadosConsola(TiradaCombateDTO dto, TiradaCombate tirada) {
		StringBuffer sb = new StringBuffer();
		sb.append("La tirada de emboque fue: ");
		sb.append(tirada.getResultadoTirada());
		sb.append(". Hubo " + tirada.getExitos() + " exitos (A dificultad " + dto.getDefensa() + "). ");
		sb.append("¡Hizo " + tirada.getDano() + " daños!");
		
		System.err.println(sb.toString());
		
	}
}
