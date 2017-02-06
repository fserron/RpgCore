package ar.net.fate.backend.bo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.beans.factory.annotation.Autowired;

import backend.bo.PersonajeBO;
import backend.entities.Personaje;

public class PersonajeBOTest extends TestCase {
	
	@Autowired
	private PersonajeBO personajeBO;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public PersonajeBOTest(String testName){
        super(testName);
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite(PersonajeBOTest.class);
    }
    
    /**
     * Test de carga de personajes.
     */
    public void pruebaCargaPersonaje(){
		
		Personaje pj = personajeBO.popularPersonaje("999_DummyHuman_GUN");
		
		if (pj.getCabecera().getNombre() != null){
			assertTrue(true);
		}
	}
    

}
