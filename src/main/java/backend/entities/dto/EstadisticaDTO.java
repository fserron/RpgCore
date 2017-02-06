package backend.entities.dto;

import java.util.List;
import java.util.Set;

import backend.entities.Tirada;

public class EstadisticaDTO {

	private List<Tirada> resultadoTiradas;
	
	//private Map<Integer, Integer> exitosTirada;
	
	private List<Integer> tiradas;
	
	private Set<Integer> exitos;
	
	private Float promedioExitos;
	
	private Float promedioFracasos;
	
	private Integer maximo;
	
	private Integer minimo;

	public List<Tirada> getResultadoTiradas() {
		return resultadoTiradas;
	}

	public void setResultadoTiradas(List<Tirada> resultadoTiradas) {
		this.resultadoTiradas = resultadoTiradas;
	}

//	public Map<Integer, Integer> getExitosTirada() {
//		return exitosTirada;
//	}
//
//	public void setExitosTirada(Map<Integer, Integer> exitosTirada) {
//		this.exitosTirada = exitosTirada;
//	}

	public List<Integer> getTiradas() {
		return tiradas;
	}

	public void setTiradas(List<Integer> tiradas) {
		this.tiradas = tiradas;
	}

	public Set<Integer> getExitos() {
		return exitos;
	}

	public void setExitos(Set<Integer> exitos) {
		this.exitos = exitos;
	}

	public Float getPromedioExitos() {
		return promedioExitos;
	}
	
	public void setPromedioExitos(Float promedioExitos) {
		this.promedioExitos = promedioExitos;
	}

	public Float getPromedioFracasos() {
		return promedioFracasos;
	}

	public void setPromedioFracasos(Float promedioFracasos) {
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
	
}
