package frontend.views;

import java.util.ArrayList;
import java.util.List;

import common.entities.DTO.EstadisticaDTO;

public class EstadisticaSimpleView {

	private List<Integer> exitosNormal;
	
	private List<Integer> exitosSuerte;
	
	private List<Integer> exitosFatidico;
	
	private Integer maximoNormal;
	
	private Integer maximoSuerte;
	
	private Integer maximoFatidico;
	
	private Integer minimoNormal;
	
	private Integer minimoSuerte;
	
	private Integer minimoFatidico;
	
	private String promedioExitosNormal;
	
	private String promedioExitosSuerte;
	
	private String promedioExitosFatidico;
	
	private String promedioFracasos;
	
	private String probabilidadExitoNormal;
	
	private String probabilidadExitoSuerte;
	
	private String probabilidadExitoFatidico;
	
	private Integer cantidadTiradas;

	public List<Integer> getExitosNormal() {
		return exitosNormal;
	}

	public void setExitosNormal(List<Integer> exitosNormal) {
		this.exitosNormal = exitosNormal;
	}

	public List<Integer> getExitosSuerte() {
		return exitosSuerte;
	}

	public void setExitosSuerte(List<Integer> exitosSuerte) {
		this.exitosSuerte = exitosSuerte;
	}

	public List<Integer> getExitosFatidico() {
		return exitosFatidico;
	}

	public void setExitosFatidico(List<Integer> exitosFatidico) {
		this.exitosFatidico = exitosFatidico;
	}

	public Integer getMaximoNormal() {
		return maximoNormal;
	}

	public void setMaximoNormal(Integer maximoNormal) {
		this.maximoNormal = maximoNormal;
	}

	public Integer getMaximoSuerte() {
		return maximoSuerte;
	}

	public void setMaximoSuerte(Integer maximoSuerte) {
		this.maximoSuerte = maximoSuerte;
	}

	public Integer getMaximoFatidico() {
		return maximoFatidico;
	}

	public void setMaximoFatidico(Integer maximoFatidico) {
		this.maximoFatidico = maximoFatidico;
	}

	public Integer getMinimoNormal() {
		return minimoNormal;
	}

	public void setMinimoNormal(Integer minimoNormal) {
		this.minimoNormal = minimoNormal;
	}

	public Integer getMinimoSuerte() {
		return minimoSuerte;
	}

	public void setMinimoSuerte(Integer minimoSuerte) {
		this.minimoSuerte = minimoSuerte;
	}

	public Integer getMinimoFatidico() {
		return minimoFatidico;
	}

	public void setMinimoFatidico(Integer minimoFatidico) {
		this.minimoFatidico = minimoFatidico;
	}

	public String getPromedioExitosNormal() {
		return promedioExitosNormal;
	}

	public void setPromedioExitosNormal(String promedioExitosNormal) {
		this.promedioExitosNormal = promedioExitosNormal;
	}

	public String getPromedioExitosSuerte() {
		return promedioExitosSuerte;
	}

	public void setPromedioExitosSuerte(String promedioExitosSuerte) {
		this.promedioExitosSuerte = promedioExitosSuerte;
	}

	public String getPromedioExitosFatidico() {
		return promedioExitosFatidico;
	}

	public void setPromedioExitosFatidico(String promedioExitosFatidico) {
		this.promedioExitosFatidico = promedioExitosFatidico;
	}

	public String getPromedioFracasos() {
		return promedioFracasos;
	}

	public void setPromedioFracasos(String promedioFracasos) {
		this.promedioFracasos = promedioFracasos;
	}

	public String getProbabilidadExitoNormal() {
		return probabilidadExitoNormal;
	}

	public void setProbabilidadExitoNormal(String probabilidadExitoNormal) {
		this.probabilidadExitoNormal = probabilidadExitoNormal;
	}

	public String getProbabilidadExitoSuerte() {
		return probabilidadExitoSuerte;
	}

	public void setProbabilidadExitoSuerte(String probabilidadExitoSuerte) {
		this.probabilidadExitoSuerte = probabilidadExitoSuerte;
	}

	public String getProbabilidadExitoFatidico() {
		return probabilidadExitoFatidico;
	}

	public void setProbabilidadExitoFatidico(String probabilidadExitoFatidico) {
		this.probabilidadExitoFatidico = probabilidadExitoFatidico;
	}

	public Integer getCantidadTiradas() {
		return cantidadTiradas;
	}

	public void setCantidadTiradas(Integer cantidadTiradas) {
		this.cantidadTiradas = cantidadTiradas;
	}
	
	public void establecerValoresFatidicos(EstadisticaDTO fatidico){
		this.maximoFatidico = fatidico.getMaximo();
		this.minimoFatidico = fatidico.getMinimo();
		this.promedioExitosFatidico = fatidico.getPromedioExitos();
		this.promedioFracasos = fatidico.getPromedioFracasos();
		this.exitosFatidico = this.armarDistribucion(fatidico);
		this.probabilidadExitoFatidico = fatidico.getProbabilidadExito();
	}
	
	public void establecerValoresNormales(EstadisticaDTO normal){
		this.maximoNormal = normal.getMaximo();
		this.minimoNormal = normal.getMinimo();
		this.promedioExitosNormal = normal.getPromedioExitos();
		this.exitosNormal = this.armarDistribucion(normal);
		this.probabilidadExitoNormal = normal.getProbabilidadExito();
	}
	
	public void establecerValoresSuerte(EstadisticaDTO suerte){
		this.maximoSuerte = suerte.getMaximo();
		this.minimoSuerte = suerte.getMinimo();
		this.promedioExitosSuerte = suerte.getPromedioExitos();
		this.exitosSuerte = this.armarDistribucion(suerte);
		this.probabilidadExitoSuerte = suerte.getProbabilidadExito();
	}
	
	private List<Integer> armarDistribucion(EstadisticaDTO dto){
		List<Integer> listaExitos = new ArrayList<Integer>();
		for (int i = this.minimoFatidico; i < dto.getMaximo() + 1; i++){
			Integer cantExitos = dto.getExitosTirada().get(new Integer(i));
			if (cantExitos != null) listaExitos.add(cantExitos);
			else listaExitos.add(0);
		}
		
		return listaExitos;
	}
	
	public Float getProbabilidadExito(Integer minimo, List<Integer> exitos){
		Integer tiradasSinExito = 0; //Las tiradas con cero o menos exitos.
		Integer cantidadFallos = 1; //Por defecto es 1: La tiradas con 0 exitos
		
		//Para las tiradas fatidicas que dieron negativo
		if (minimo < 0)	{
			//Cantidad de tiradas que dieron negativo
			cantidadFallos = (minimo * -1) + 1;
			
			for (int i = 0; i < cantidadFallos; i++){ //Desde el minimo hasta cero
				tiradasSinExito += exitos.get(i);
			}
		} else { //Para las tiradas que no tuvieron exitos negativos
			tiradasSinExito = exitos.get((this.minimoFatidico * -1));
		}
		
		Float tiradasConExito = (float) this.cantidadTiradas - tiradasSinExito;
		
		return Float.valueOf(tiradasConExito / this.cantidadTiradas);
	}

}
