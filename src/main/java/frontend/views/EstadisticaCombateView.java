package frontend.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.entities.DTO.EstadisticaDTO;

public class EstadisticaCombateView {

	private Map<String, Float> mapaDano;
	
	private Map<String,List<List<Object>>> detalleTiradas;
	
	private Integer maximo;
	
	private Integer minimo;
	
	private String promedioDano;
	
	private String promedioAutoDano;
	
	private String probabilidadDano;
	
	private Integer cantidadTiradas; 

	public Map<String, Float> getMapaDano() {
		return mapaDano;
	}

	public void setMapaDano(Map<String, Float> mapaDano) {
		this.mapaDano = mapaDano;
	}

	public Map<String, List<List<Object>>> getDetalleTiradas() {
		return detalleTiradas;
	}

	public void setDetalleTiradas(Map<String, List<List<Object>>> detalleTiradas) {
		this.detalleTiradas = detalleTiradas;
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

	public String getPromedioDano() {
		return promedioDano;
	}

	public void setPromedioDano(String promedioDano) {
		this.promedioDano = promedioDano;
	}

	public String getPromedioAutoDano() {
		return promedioAutoDano;
	}

	public void setPromedioAutoDano(String promedioAutoDano) {
		this.promedioAutoDano = promedioAutoDano;
	}

	public String getProbabilidadDano() {
		return probabilidadDano;
	}

	public void setProbabilidadDano(String probabilidadDano) {
		this.probabilidadDano = probabilidadDano;
	}

	public Integer getCantidadTiradas() {
		return cantidadTiradas;
	}

	public void setCantidadTiradas(Integer cantidadTiradas) {
		this.cantidadTiradas = cantidadTiradas;
	}

	public void establecerValores(EstadisticaDTO dto){
		this.maximo = dto.getMaximo();
		this.minimo = dto.getMinimo();
		this.promedioDano = dto.getPromedioExitos();
		this.armarDistribucionTorta(dto);
		this.probabilidadDano = dto.getProbabilidadExito();
	}
	
	private List<Integer> armarDistribucion(EstadisticaDTO dto){
		List<Integer> listaExitos = new ArrayList<Integer>();
		for (int i = this.minimo; i < dto.getMaximo() + 1; i++){
			Integer cantExitos = dto.getExitosTirada().get(new Integer(i));
			if (cantExitos != null) listaExitos.add(cantExitos);
			else listaExitos.add(0);
		}
		
		return listaExitos;
	}
	
	private void armarDistribucionTorta(EstadisticaDTO dto){
		List<String> distribucion = new ArrayList<String>();
		this.mapaDano = new HashMap<String, Float>();
		this.detalleTiradas = new HashMap<String,List<List<Object>>>();
		
		for (int i = this.minimo; i < dto.getMaximo() + 1; i++){
			Map<List<Integer>, Integer> tiradasRepetidas = new HashMap<List<Integer>, Integer>();
			List<List<Integer>> tiradas = dto.getMapaDano().get(new Integer(i));
			
			if (tiradas != null){
				for (List<Integer> tirada : dto.getMapaDano().get(new Integer(i))) {
					if (tiradasRepetidas.containsKey(tirada)){
						Integer ocurrencias = tiradasRepetidas.get(tirada) + 1;
						tiradasRepetidas.put(tirada, ocurrencias);
					} else {
						tiradasRepetidas.put(tirada, 1);
					}
				}
				
				List<List<Object>> tercerNivel = new ArrayList<List<Object>>();
				Float porcentajeTotal = new Float(0);
				
				for (List<Integer> tirada : tiradasRepetidas.keySet()){
					Integer cantidad = tiradasRepetidas.get(tirada);
					Float porcentaje = Float.valueOf((float) cantidad / this.cantidadTiradas);
					
					List<Object> valores = new ArrayList<Object>();
					valores.add(tirada.toString());
					valores.add(porcentaje);
					
					tercerNivel.add(valores);
					porcentajeTotal += porcentaje;
				}
				
				Comparator<List<Object>> comparator = new Comparator<List<Object>>() {

					@Override
					public int compare(List<Object> o1, List<Object> o2) {
						String primero = (String) o2.get(0);
						String segundo = (String) o1.get(0);
						
						primero = primero.replace("[", "");
						primero = primero.replace("]", "");
						primero = primero.replace(",", "");
						primero = primero.replace(" ", "");
						segundo = segundo.replace("[", "");
						segundo = segundo.replace("]", "");
						segundo = segundo.replace(",", "");
						segundo = segundo.replace(" ", "");
						
						long i = 0;
						long j = 0;
						
						if (!primero.isEmpty()) {
							try {
								i = Long.parseLong(primero);
							} catch (NumberFormatException nfe){
								System.err.println("El numero que causo el error fue: " + primero);
							}
						}
						if (!segundo.isEmpty()) {
							try {
								j = Long.parseLong(segundo);
							} catch (NumberFormatException nfe){
								System.err.println("El numero que causo el error fue: " + primero);
							}
						}
						
						if (i < j) {
							return 1;
						} else if (i > j) {
							return -1;
						} else {
							return 0;
						}
					}
				};
			    
				Collections.sort(tercerNivel, comparator);
				
				this.mapaDano.put(String.valueOf(i), porcentajeTotal);
				this.detalleTiradas.put(String.valueOf(i), tercerNivel);
			}
		}
	}
}
