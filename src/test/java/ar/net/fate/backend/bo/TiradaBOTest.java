package ar.net.fate.backend.bo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.beans.factory.annotation.Autowired;

import common.entities.DTO.TiradaDTO;

import backend.bo.TiradaBO;
import backend.entities.Tirada;

public class TiradaBOTest extends TestCase {
	
	@Autowired
	private TiradaBO tiradaBO;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TiradaBOTest(String testName){
        super(testName);
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite(TiradaBOTest.class);
    }
    
    /**
     * Test de tiradas.
     */
    public void pruebaTirada(){
		TiradaDTO dto = new TiradaDTO();
		
		dto.setAtributo(5);
		dto.setHabilidad(5);
		dto.setPenalizacion(1);
		dto.setChance(false);
		dto.setLucky(false);
		dto.setSuerte(4);

		Tirada tirada = tiradaBO.hacerTirada(dto);
		
		System.out.println("Tirada: " + tirada.getResultadoTirada().toString());
		System.out.println("Exitos: " + tirada.getExitos());
		System.out.println("Fracasos: " + tirada.getFracasos());
		
		assertTrue(true);
    }
    
}
