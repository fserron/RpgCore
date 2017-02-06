package common.entities.DTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

import backend.entities.Tirada;

public class EstadisticaDTO {

	private List<Tirada> resultadoTiradas;
	
	private Map<Integer, Integer> exitosTirada;
	
	private Map<Integer, List<List<Integer>>> mapaDano;

	private List<Integer> tiradas;
	
	private String promedioExitos;
	
	private String promedioFracasos;
	
	private Integer maximo;
	
	private Integer minimo;
	
	private String probabilidadExito;

	public List<Tirada> getResultadoTiradas() {
		return resultadoTiradas;
	}

	public void setResultadoTiradas(List<Tirada> resultadoTiradas) {
		this.resultadoTiradas = resultadoTiradas;
	}

	public Map<Integer, Integer> getExitosTirada() {
		return exitosTirada;
	}

	public void setExitosTirada(Map<Integer, Integer> exitosTirada) {
		this.exitosTirada = exitosTirada;
	}

	public List<Integer> getTiradas() {
		return tiradas;
	}

	public void setTiradas(List<Integer> tiradas) {
		this.tiradas = tiradas;
	}

	public String getPromedioExitos() {
		return promedioExitos;
	}
	
	public void setPromedioExitos(String promedioExitos) {
		this.promedioExitos = promedioExitos;
	}

	public String getPromedioFracasos() {
		return promedioFracasos;
	}

	public void setPromedioFracasos(String promedioFracasos) {
		this.promedioFracasos = promedioFracasos;
	}

	public Integer getMaximo() {
		return maximo;
	}

	public void setMaximo(Integer maximo) {
		this.maximo = maximo;
	}

	public Integer getMinimo() {
		return minimo;
	}

	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	public Map<Integer, List<List<Integer>>> getMapaDano() {
		return mapaDano;
	}

	public void setMapaDano(Map<Integer, List<List<Integer>>> mapaDano) {
		this.mapaDano = mapaDano;
	}

	public String getProbabilidadExito() {
		return probabilidadExito;
	}

	public void setProbabilidadExito(String probabilidadExito) {
		this.probabilidadExito = probabilidadExito;
	}
	
}
