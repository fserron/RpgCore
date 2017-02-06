package ar.net.fate.backend.bo;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.beans.factory.annotation.Autowired;

import common.entities.DTO.EstadisticaArmaDTO;
import common.entities.DTO.EstadisticaDTO;
import common.entities.DTO.TiradaDTO;

import utils.Utils;
import backend.bo.EstadisticaBO;
import backend.entities.enumerators.Rank;


public class EstadisticaBOTest extends TestCase {
	
	@Autowired
	private EstadisticaBO estadisticaBO;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EstadisticaBOTest(String testName){
        super(testName);
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite(PersonajeBOTest.class);
    }
    
    /**
     * Test de estadisticas.
     */
    public void pruebaEstadisticaSimple(){
		TiradaDTO dto = new TiradaDTO();
		
		dto.setAtributo(6);
		dto.setHabilidad(6);
		dto.setChance(false);
		dto.setLucky(false);
		dto.setSuerte(6);

		EstadisticaDTO tiradas = estadisticaBO.estadisticaTirada(dto, 50000);
		
		if (tiradas != null){
			imprimirResultados(tiradas, dto);
			assertTrue(true);
		}
    }
    
    public void pruebaSimulacionArma(){
    	TiradaDTO dto = new TiradaDTO();
    	dto.setLucky(false);
    	dto.setSuerte(6);

		EstadisticaArmaDTO simDTO = new EstadisticaArmaDTO();
		simDTO.setTiradaDTO(dto);
		simDTO.setCodigoArma(2001);
		simDTO.setRafaga(0);
		
		
		HashMap<Rank[], Float> resultados = new HashMap<Rank[], Float>();
		
		for (int atributo = 2; atributo <= 6; atributo++){
			for (int habilidad = 1; habilidad <= 6; habilidad++){
				dto.setAtributo(atributo);
				dto.setHabilidad(habilidad);
				Rank[] rankings = {Rank.parseRanking(atributo), Rank.parseRanking(habilidad)};
				resultados.put(rankings, estadisticaBO.danoPromedioArma(simDTO, 50000));
			}
		}
		
		//Una vez con todos los resultados, los imprimo
		System.out.println("Promedio de daño");
		System.out.println("\tF\tE\tD\tC\tB\tA"); //Cabecera
		for (int i = 2; i <= 6; i++){ // i es el atributo
			System.out.print(Rank.parseRanking(i).getRanking() + "\t");
			for (int j = 1; j <= 6; j++){ // j es la habilidad
				for (Rank[] combinacion : resultados.keySet()){
					if (combinacion[0].getRanking().equals(Rank.parseRanking(i).getRanking()) && combinacion[1].getRanking().equals(Rank.parseRanking(j).getRanking())){
						System.out.print(Utils.format(resultados.get(combinacion), 2) + "\t");
					}
				}
			}
			System.out.print("\n");
		}
		
		assertTrue(true);
    }
    
    private static void imprimirResultados(EstadisticaDTO resultados, TiradaDTO dto){
		
//		int totalExitos = 0;
//		int totalFracasos = 0;
//		HashMap<Integer, Integer> exitosTiradas = new HashMap<Integer, Integer>();
//		
//		for (Tirada tirada : resultados){
//			
//			//Voy acumulando el total de exitos
//			totalExitos += tirada.getExitos();
//			
//			//Voy calculando el total de fracasos
//			if (tirada.getFracasos() > tirada.getExitos()){
//				totalFracasos++;
//			}
//			
//			//Voy creando un mapa con exitos / cantidad de veces
//			int cantExitos = tirada.getExitos() - tirada.getFracasos();
//			Integer tempExitos = exitosTiradas.get(cantExitos);
//			if (tempExitos != null)
//				tempExitos++;
//			else
//				tempExitos = 1;
//			exitosTiradas.put(cantExitos, tempExitos);
//		}
//
//		String promedioExitos = Utils.format((float) totalExitos / resultados.size(), 3);

		System.out.println("Habilidad: " + Rank.parseRanking(dto.getHabilidad()).getRanking() + " con Atributo: " + Rank.parseRanking(dto.getAtributo()).getRanking());
		System.out.println("Promedio de exitos por tirada: " + resultados.getPromedioExitos());
		
//		// Probabilidad de Fracasos.
//		if (dto.isChance()){
//			System.out.println("Probabilidad de Fracasos: ");
//			System.out.println(Utils.format(((float) resultados.getPromedioFracasos() * 100), 10));
//		}
//
//		// Creamos una lista con los resultados ordenados
//		Set<Integer> results = new TreeSet<Integer>(resultados.getExitosTirada().keySet());
//		
//		// La mostramos
//		int max = 0;
//		System.out.println("Exitos por tirada:");
//		for (Integer exito : results){
//			String prob = Utils.format(((float) resultados.getExitosTirada().get(exito) / resultados.getResultadoTiradas().size() * 100), 3);
//			if (!prob.isEmpty())
//				System.out.println(exito + "\t" + prob);
//			else if (exito >= max)
//				max = exito;
//		}
//		if (max > 0){
//			StringBuffer sb = new StringBuffer();
//			sb.append("Maxima cantidad de exitos: ");
//			sb.append(resultados.getMaximo());
//			sb.append(". Cuya posibilidad es de ");
//			sb.append(resultados.getExitosTirada().get(resultados.getMaximo()));
//			sb.append(" en ");
//			sb.append(resultados.getResultadoTiradas().size());
//		}
	}

}
