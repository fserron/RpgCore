package frontend.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.bo.EstadisticaBO;
import backend.entities.Objetivo;

import common.entities.DTO.EstadisticaDTO;
import common.entities.DTO.TiradaCombateDTO;
import common.entities.DTO.TiradaDTO;

import frontend.entities.EstadisticasModel;
import frontend.views.EstadisticaCombateView;
import frontend.views.EstadisticaSimpleView;
import frontend.views.TablaEstadisticaCombateView;

@Controller
public class EstadisticasController {
	
	@Autowired
	EstadisticaBO estadisticaBO;
	
    @RequestMapping(value = "/estadisticaSimple.do", method = RequestMethod.POST)
    public @ResponseBody EstadisticaSimpleView calcularEstadisticaSimple(@ModelAttribute("model") EstadisticasModel model, BindingResult result) {
    	EstadisticaSimpleView view = new EstadisticaSimpleView();
    	EstadisticaDTO resultadosParciales = new EstadisticaDTO();
    	TiradaDTO tirada = new TiradaDTO();
    	
    	view.setCantidadTiradas(model.getNumTiradas());
    	tirada.setCaras(6);
    	
    	tirada.setAtributo(model.getAtributo());
    	tirada.setHabilidad(model.getHabilidad());
    	tirada.setSuerte(model.getSuerte());
    	tirada.setBono(model.getBono());

    	//Tirada Fatidica
    	tirada.setChance(true);
    	resultadosParciales = estadisticaBO.estadisticaTirada(tirada, model.getNumTiradas());
    	view.establecerValoresFatidicos(resultadosParciales);
    	tirada.setChance(false);
    	
    	//Tirada Normal
    	resultadosParciales = estadisticaBO.estadisticaTirada(tirada, model.getNumTiradas());
    	view.establecerValoresNormales(resultadosParciales);
    	
    	//Tirada con Suerte
    	tirada.setLucky(true);
    	resultadosParciales = estadisticaBO.estadisticaTirada(tirada, model.getNumTiradas());
    	view.establecerValoresSuerte(resultadosParciales);
    	tirada.setLucky(false);
    	    	
        return view;
    }
    
    @RequestMapping(value = "/estadisticaCombate.do", method = RequestMethod.POST)
    public @ResponseBody EstadisticaCombateView calcularEstadisticaCombate(@ModelAttribute("model") EstadisticasModel model, BindingResult result) {
    	EstadisticaCombateView view = new EstadisticaCombateView();
    	EstadisticaDTO resultadosParciales = new EstadisticaDTO();
    	TiradaCombateDTO tirada = new TiradaCombateDTO();
    	boolean chance = false;
    	
    	view.setCantidadTiradas(model.getNumTiradas());
    	tirada.setCaras(model.getCarasDado());
    	
    	tirada.setAtributo(model.getAtributo());
    	tirada.setHabilidad(model.getHabilidad());
    	tirada.setSuerte(model.getSuerte());
    	tirada.setBono(model.getBono());    	
    	tirada.setDefensa(model.getDefensa());
    	tirada.setArmadura(model.getArmadura());
    	tirada.setBonoArma(model.getBonoArma());
    	tirada.setBonoDanoBase(model.getBonoBase());
    	
    	//Si la cantidad de dados es negativa, deberia setear la dificultad como la suerte
		if (calcularDadosEmboque(model) <= 0 || model.isFatidica()){//Es una tirada fatidica
			chance = true;
			tirada.setDificultad(obtenerPuntuacion(model, 6));
		} else {
			tirada.setDificultad(obtenerPuntuacion(model, model.getDificultad()));
		}
		
    	tirada.setChance(chance);
    	tirada.setLucky(model.isConSuerte());
    	
    	tirada.setObjetivo(obtenerPuntuacion(model, model.getObjetivo()));
    	tirada.setRetencion(obtenerPuntuacion(model, model.getRetencion()).getPuntuacion());
    	tirada.setSustraccion(obtenerPuntuacion(model, model.getSustraccion()).getPuntuacion());
    	tirada.setPenalizacion(obtenerPuntuacion(model, model.getPenalizacion()).getPuntuacion());
    	
    	//Atributos Daño
    	tirada.setAplicarDano(model.isAplicarDano());
    	tirada.setCarasDadoDano(model.getCarasDadoDano());
    	tirada.setRestarDefensa(model.isRestarDefensa());
    	tirada.setDanoBase(model.isDanoBase());
    	if (model.getValorExplosion() != null && model.getValorExplosion().intValue() != 0) {
    		tirada.setExplota(true);
    		tirada.setValorExplosion(model.getValorExplosion().intValue());
    	} else tirada.setExplota(false);
    	
    	//Tirada Normal
    	resultadosParciales = estadisticaBO.estadisticaCombateTirada(tirada, model.getNumTiradas());
    	view.establecerValores(resultadosParciales);
    	    	
        return view;
    }
    
    @RequestMapping(value = "/tablaEstadisticaCombate.do", method = RequestMethod.POST)
    public @ResponseBody TablaEstadisticaCombateView generarTablaEstadisticaCombate(@ModelAttribute("model") EstadisticasModel model, BindingResult result) {
    	TablaEstadisticaCombateView view = new TablaEstadisticaCombateView();
    	EstadisticaDTO resultadosParciales = new EstadisticaDTO();
    	TiradaCombateDTO tirada = new TiradaCombateDTO();
    	boolean chance = false;
    	
    	tirada.setCaras(model.getCarasDado());
    	
    	List<String> tablaProbabilidad = new ArrayList<String>();
    	List<String> tablaPromedio = new ArrayList<String>();
    	
    	for (int atributo = 1; atributo < 6; atributo++){
        	for (int habilidad = 1; habilidad < 6; habilidad++){
        		model.setAtributo(atributo);
        		model.setHabilidad(habilidad);
        		
            	tirada.setAtributo(model.getAtributo());
            	tirada.setHabilidad(model.getHabilidad());
            	tirada.setSuerte(model.getSuerte());
            	tirada.setBono(model.getBono());    	
            	tirada.setDefensa(model.getDefensa());
            	tirada.setArmadura(model.getArmadura());
            	tirada.setBonoArma(model.getBonoArma());
            	tirada.setBonoDanoBase(model.getBonoBase());
            	
            	
            	
            	//Si la cantidad de dados es negativa, deberia setear la dificultad como la suerte
        		if (calcularDadosEmboque(model) <= 0 || model.isFatidica()){//Es una tirada fatidica
        			chance = true;
        			tirada.setDificultad(obtenerPuntuacion(model, 6));
        		} else {
        			tirada.setDificultad(obtenerPuntuacion(model, model.getDificultad()));
        		}

        		tirada.setLucky(model.isConSuerte());
            	tirada.setChance(chance);
            	
            	tirada.setObjetivo(obtenerPuntuacion(model, model.getObjetivo()));
            	tirada.setRetencion(obtenerPuntuacion(model, model.getRetencion()).getPuntuacion());
            	tirada.setSustraccion(obtenerPuntuacion(model, model.getSustraccion()).getPuntuacion());
            	tirada.setPenalizacion(obtenerPuntuacion(model, model.getPenalizacion()).getPuntuacion());
            	
            	//Atributos Daño
            	tirada.setAplicarDano(model.isAplicarDano());
            	tirada.setCarasDadoDano(model.getCarasDadoDano());
            	tirada.setRestarDefensa(model.isRestarDefensa());
            	tirada.setDanoBase(model.isDanoBase());
            	if (model.getValorExplosion() != null && model.getValorExplosion().intValue() != 0) {
            		tirada.setExplota(true);
            		tirada.setValorExplosion(model.getValorExplosion().intValue());
            	} else tirada.setExplota(false);
        		
        		resultadosParciales = estadisticaBO.generarTabla(tirada, model.getNumTiradas());
        		tablaProbabilidad.add(resultadosParciales.getProbabilidadExito());
        		tablaPromedio.add(resultadosParciales.getPromedioExitos());
        	}        	
    	}
    	
    	view.setTablaProbabilidad(tablaProbabilidad);
    	view.setTablaPromedio(tablaPromedio);
    	
    	return view;
    }
    
    @RequestMapping(value = "/tablaEstadisticaArmas.do", method = RequestMethod.POST)
    public @ResponseBody TablaEstadisticaCombateView generarTablaEstadisticaArmas(@ModelAttribute("model") EstadisticasModel model, BindingResult result) {
    	TablaEstadisticaCombateView view = new TablaEstadisticaCombateView();
    	EstadisticaDTO resultadosParciales = new EstadisticaDTO();
    	TiradaCombateDTO tirada = new TiradaCombateDTO();
    	boolean chance = false;
    	
    	List<String> tablaProbabilidad = new ArrayList<String>();
    	List<String> tablaPromedio = new ArrayList<String>();
    	
    	for (int atributo = 1; atributo < 6; atributo++){
        	for (int habilidad = 1; habilidad < 6; habilidad++){
        		model.setAtributo(atributo);
        		model.setHabilidad(habilidad);
        		model.setDefensa(1);
        		model.setPenalizacion(0);
        		
            	tirada.setAtributo(model.getAtributo());
            	tirada.setHabilidad(model.getHabilidad());
            	tirada.setBono(model.getBono());
            	tirada.setBonoArma(model.getBonoArma());
            	tirada.setBonoDanoBase(model.getBonoBase());
            	
            	tirada.setDefensa(1);
            	tirada.setArmadura(0);

            	//Si la cantidad de dados es cero o negativa, no hacemos la tirada
        		if (calcularDadosEmboque(model) > 0){
        			if (model.isArmaFuego()){
        				tirada.setDificultad(obtenerPuntuacion(model, 7)); //Potencia Arma
        				tirada.setExplota(true);
        				if (model.getAtributo() < model.getPotencia()){
            				tirada.setValorExplosion(model.getAtributo());	
        				} else {
        					tirada.setValorExplosion(model.getAtributo());
        				}
        			} else {
        				tirada.setDificultad(obtenerPuntuacion(model, 1)); //Atributo
        			}
                	
                	tirada.setObjetivo(obtenerPuntuacion(model, 4)); //Defensa
                	tirada.setRetencion(0);
                	tirada.setSustraccion(0);
                	tirada.setPenalizacion(0);
                	
                	//Atributos Daño
                	tirada.setAplicarDano(false);

//                	if (model.getValorExplosion() != null && model.getValorExplosion().intValue() != 0) {
//                		tirada.setExplota(true);
//                		tirada.setValorExplosion(model.getValorExplosion().intValue());
//                	} else tirada.setExplota(false);
            		
            		resultadosParciales = estadisticaBO.generarTabla(tirada, model.getNumTiradas());
            		tablaProbabilidad.add(resultadosParciales.getProbabilidadExito());
            		tablaPromedio.add(resultadosParciales.getPromedioExitos());
        		} else { //Las probabilidades de exito y daño son nulas
        			tablaProbabilidad.add("ND");
            		tablaPromedio.add("ND");
        		}
        	}        	
    	}
    	
    	view.setTablaProbabilidad(tablaProbabilidad);
    	view.setTablaPromedio(tablaPromedio);
    	
    	return view;
    }
    
    private Objetivo obtenerPuntuacion(EstadisticasModel model, Integer valor){
    	Objetivo puntuacion = new Objetivo();
    	puntuacion.setPuntuacion(0);
    	switch (valor){
    		case 1:
    			puntuacion.setPuntuacion(model.getAtributo());
    			puntuacion.setDescripcion("Atributo Atacante");
    			puntuacion.setMenorIgual(true);
    			return puntuacion;
    		case 2:
    			puntuacion.setPuntuacion(model.getHabilidad());
    			puntuacion.setDescripcion("Habilidad Atacante");
    			return puntuacion;
    		case 3:
    			puntuacion.setPuntuacion(model.getBono());
    			puntuacion.setDescripcion("Bono Atacante");
    			return puntuacion;
    		case 4:
    			puntuacion.setPuntuacion(model.getDefensa());
    			puntuacion.setDescripcion("Defensa");
    			puntuacion.setMenorIgual(false);
    			return puntuacion;
    		case 5:
    			puntuacion.setPuntuacion(model.getArmadura());
    			puntuacion.setDescripcion("Armadura");
    			return puntuacion;
    		case 6:
    			puntuacion.setPuntuacion(model.getSuerte());
    			puntuacion.setDescripcion("Suerte");
    			puntuacion.setMenorIgual(true);
    			return puntuacion;
    		case 7:
    			puntuacion.setPuntuacion(model.getPotencia());
    			puntuacion.setDescripcion("Arma Atacante");
    			puntuacion.setMenorIgual(true);
    			return puntuacion;
    		default:
    			return puntuacion;
    	}
    }
    
	private int calcularDadosEmboque(EstadisticasModel model){
		//Cantidad de dados a tirar
		Integer totalDados = 0;
		totalDados += model.getHabilidad();
		totalDados += model.getBono();
		totalDados += model.getBonoArma();
		totalDados -= obtenerPuntuacion(model, model.getPenalizacion()).getPuntuacion();
		
		return totalDados;
	}
}
