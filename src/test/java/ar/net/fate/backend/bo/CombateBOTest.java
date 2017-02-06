package ar.net.fate.backend.bo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.beans.factory.annotation.Autowired;

import backend.bo.CombateBO;

import com.sdc.controller.AppTest;

public class CombateBOTest extends TestCase {
	
	@Autowired
	private CombateBO combateBO;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CombateBOTest(String testName){
        super(testName);
    }
    
    /**
     * @return the suite of tests being tested
     */
    public static Test suite(){
        return new TestSuite(PersonajeBOTest.class);
    }
    
    /**
     * Rigourous Test :-)
     */
    public void testApp(){
        assertTrue( true );
    }
    
}
