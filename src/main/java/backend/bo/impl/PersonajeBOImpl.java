package backend.bo.impl;

import backend.bo.PersonajeBO;
import backend.dao.PersonajeDAO;
import backend.dao.impl.PersonajeDAOImpl;
import backend.entities.Personaje;

public class PersonajeBOImpl implements PersonajeBO {

	private PersonajeDAO personajeDAO;
	
	public PersonajeBOImpl(){
		this.personajeDAO = new PersonajeDAOImpl(); 
	}
	
	public Personaje popularPersonaje(String nombrePersonaje){
		return personajeDAO.popularPersonaje(nombrePersonaje);
	}
}
