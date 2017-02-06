package backend.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import backend.dao.ArmaDAO;
import backend.dao.PersonajeDAO;
import backend.entities.Arma;
import backend.entities.Atributos;
import backend.entities.Cabecera;
import backend.entities.Habilidades;
import backend.entities.Personaje;
import backend.entities.enumerators.Clase;
import backend.entities.enumerators.Rank;
import backend.entities.enumerators.Rank.Ranking;

public class PersonajeDAOImpl implements PersonajeDAO {
	
	private ArmaDAO armaDAO;
	
	private List<String> camposCabecera = Arrays.asList("nombre", "jugador", "naturaleza", "concepto", "motivacion", "origen", "edad", "clase");
	
	private List<String> camposAtributos = Arrays.asList("fuerza", "agilidad", "resistencia", "magia", "personalidad", "inteligencia", "suerte");
	
	private List<String> camposHabilidadesFisicas = Arrays.asList("atletismo", "combate", "conduccion", "pericias", "punteria", "sigilo", "supervivencia", "latrocinio");
	
	private List<String> camposHabilidadesMentales = Arrays.asList("alerta", "academicismo", "ciencia", "concentracion", "investigacion", "medicina", "ocultismo", "sentidoMagico");
	
	private List<String> camposHabilidadesSociales = Arrays.asList("burocracia", "callejeo", "empatia", "empatiaAnimal", "engano", "expresionArtistica", "intimidacion", "sociabilizar");
	
	//Lista de atributos que son Strings
	private List<String> textos = Arrays.asList("nombre", "jugador", "naturaleza", "concepto", "motivacion", "origen");
	
	//Lista de atributos que son enteros
	private List<String> enteros = Arrays.asList("edad");
	
	//Lista de clases inhumanas
	private List<Clase> clasesInhumanas = Arrays.asList(Clase.DEAD_APOSTOL, Clase.ELEMENTAL, Clase.NEKO, Clase.HEROIC_SPIRIT, Clase.TRUE_ANCESTOR, Clase.DEMON, Clase.GOD, Clase.PHANTASMAL_BEAST, Clase.DIVINE_BEAST);
	

	
	/** FLETAR CON SPRING */
	public PersonajeDAOImpl(){
		this.armaDAO = new ArmaDAOImpl();
	}
	
	private Properties personajeBase(String nombre){
		StringBuilder filename = new StringBuilder();
		filename.append("backend/resources/");
		filename.append(nombre);
		filename.append(".properties");
		
		Properties pj = null;
		
		try {
			FileInputStream in = new FileInputStream(filename.toString());
			pj = new Properties();
			pj.load(in);
		} catch (FileNotFoundException e) {
			System.exit(1);
			e.printStackTrace();
		} catch (IOException e) {
			System.exit(1);
			e.printStackTrace();
		}
		
		return pj;
	}

	public Personaje popularPersonaje(String nombrePersonaje){
		Cabecera cabecera = new Cabecera();
		Atributos atributos = new Atributos();
		Habilidades habilidades = new Habilidades();
		Personaje personaje = new Personaje();
		
		Properties props = personajeBase(nombrePersonaje);
		
		//La lista de atributos en el properties.
		Set<Object> keys = props.keySet();
		
		for (Object key : keys){
			String clave = (String) key;
			
			try {
				if (camposCabecera.contains(clave)){
					popularCampoCabecera(clave, cabecera, props);
				} else if (camposAtributos.contains(clave)){
					popularCampoAtributo(clave, atributos, props);
				} else if (camposHabilidadesFisicas.contains(clave)){
					popularCampoHabilidadFisica(clave, habilidades, props);
				} else if (camposHabilidadesMentales.contains(clave)){
					popularCampoHabilidadMentales(clave, habilidades, props);
				} else if (camposHabilidadesSociales.contains(clave)){
					popularCampoHabilidadSociales(clave, habilidades, props);
				} else if (clave.equals("armas")){
					setAtributoArmas(clave, personaje, props);
				}
			} catch (IllegalArgumentException e) {
				System.err.println("El set del metodo " + clave + " no soporta los argumentos ingresados.");
				e.printStackTrace();
			} catch (SecurityException e) {
				System.err.println("El set del metodo " + clave + " no esta disponible.");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.err.println("El set del metodo " + clave + " no esta disponible.");
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				System.err.println("El set del metodo " + clave + " no existe.");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				System.err.println("El set del metodo " + clave + " no esta disponible.");
				e.printStackTrace();
			}

		}
		
		// Una vez que termino de popular la hoja, si es de clase inhumano, se llenan los atributos correspondientes.
		Clase clase = personaje.getCabecera().getClase();
		if (null != clase){
			if (clasesInhumanas.contains(clase)){
				atributos.getFuerza().setRankingInhumano(personaje.getAtributos().getFuerza().getRanking());
				atributos.getAgilidad().setRankingInhumano(personaje.getAtributos().getAgilidad().getRanking());
				atributos.getResistencia().setRankingInhumano(personaje.getAtributos().getResistencia().getRanking());
				atributos.getInteligencia().setRankingInhumano(personaje.getAtributos().getInteligencia().getRanking());
				atributos.getPersonalidad().setRankingInhumano(personaje.getAtributos().getPersonalidad().getRanking());
				atributos.getMagia().setRankingInhumano(personaje.getAtributos().getMagia().getRanking());
			}
		}
		
		personaje.setCabecera(cabecera);
		personaje.setAtributos(atributos);
		personaje.setHabilidades(habilidades);

		return personaje;
	}
	
	private void popularCampoCabecera(String clave, Cabecera cabecera, Properties props) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		
		if (textos.contains(clave)){ //Si lo que viene es un texto plano
			setAtributoTexto(clave, cabecera, props);
		} else if (enteros.contains(clave)){ //Si lo que viene es un numero
			setAtributoNumerico(clave, cabecera, props);
		} else if (clave.equals("clase")){ //Si lo que viene es la clase
			setAtributoClase(clave, cabecera, props);
		}
	}
	
	private void popularCampoAtributo(String clave, Atributos atributos, Properties props) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		setAtributoRanking(clave, atributos, props);
	}
	
	private void popularCampoHabilidadFisica(String clave, Habilidades habilidades, Properties props) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		setAtributoRanking(clave, habilidades.getFisicas(), props);
	}
	
	private void popularCampoHabilidadMentales(String clave, Habilidades habilidades, Properties props) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		setAtributoRanking(clave, habilidades.getMentales(), props);
	}
	
	private void popularCampoHabilidadSociales(String clave, Habilidades habilidades, Properties props) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		setAtributoRanking(clave, habilidades.getSociales(), props);
	}
	
	private void setAtributoTexto(String clave, Object clase, Properties props) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String setter = "set" + clave.substring(0, 1).toUpperCase() + clave.substring(1, clave.length());
		Method set = clase.getClass().getDeclaredMethod(setter, String.class);
		String argumento = (String) props.get(clave);
		set.invoke(clase, argumento);
	}
	
	private void setAtributoNumerico(String clave, Object clase, Properties props) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String setter = "set" + clave.substring(0, 1).toUpperCase() + clave.substring(1, clave.length());
		Method set = clase.getClass().getDeclaredMethod(setter, Integer.class);
		Integer argumento = Integer.parseInt((String) props.get(clave));
		set.invoke(clase, argumento);
	}
	
	private void setAtributoClase(String clave, Object clase, Properties props) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String setter = "set" + clave.substring(0, 1).toUpperCase() + clave.substring(1, clave.length());
		Method set = clase.getClass().getDeclaredMethod(setter, Clase.class);
		Clase argumento = Clase.parseTipo(Integer.parseInt((String) props.get(clave)));
		set.invoke(clase, argumento);
	}
	
	private void setAtributoArmas(String clave, Object clase, Properties props) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String setter = "set" + clave.substring(0, 1).toUpperCase() + clave.substring(1, clave.length());
		List<String> armasTexto = Arrays.asList(((String) props.get(clave)).split("\\s*,\\s*"));
		List<Arma> armas = new ArrayList<Arma>();
		
		//Iteramos por cada arma
		for (int i = 0; i < armasTexto.size(); i++){
			Arma arma = armaDAO.obtenerArma(Integer.parseInt(armasTexto.get(i)));
			armas.add(arma); //La agregamos a la lista de armas
		}
		
		Method set = clase.getClass().getDeclaredMethod(setter, List.class);
		set.invoke(clase, armas); //Las seteamos en el personaje
	}
	
	private void setAtributoRanking(String clave, Object clase, Properties props) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String setter = "set" + clave.substring(0, 1).toUpperCase() + clave.substring(1, clave.length());
		Method set = clase.getClass().getDeclaredMethod(setter, Rank.class);
		String ranking = (String) props.get(clave);
		Integer bonificador = calcularBonificadorRanking(ranking);
		
		if (ranking.contains("Inhumana")){
			//Primero obtengo el atributo normal
			String claveAtributo = ranking.substring(0, ranking.indexOf("Inhumana"));
			String getter = "get" + claveAtributo.substring(0, 1).toUpperCase() + clave.substring(1, clave.length());
			Method get = clase.getClass().getDeclaredMethod(getter, Rank.class);
			Rank atributo = (Rank) get.invoke(clase);
			
			//Creo el objeto del atributo inhumano
			Ranking argumento = Rank.parseRanking(ranking.substring(0, ranking.length() - bonificador)).getRanking();
			if (null != bonificador) argumento.setBonificador(bonificador);
			
			atributo.setRankingInhumano(argumento);
			
			set.invoke(clase, atributo);
			
		} else {
			Rank argumento = Rank.parseRanking(ranking.substring(0, ranking.length() - bonificador));
			if (null != bonificador)
				argumento.getRanking().setBonificador(bonificador);
			set.invoke(clase, argumento);
		}
	}
	
	private Integer calcularBonificadorRanking(String ranking) {
		Integer bonificador = 0;
		if (ranking.substring(ranking.length() - 1, ranking.length()).equals("+")){
			if (ranking.substring(ranking.length() - 2, ranking.length() - 1).equals("+")){
				if (ranking.substring(ranking.length() - 3, ranking.length() - 2).equals("+"))
					bonificador = 3;
				else
					bonificador = 2;
			} else {
				bonificador = 1;
			}
		}
		
		return bonificador;
	}

	public ArmaDAO getArmaDAO() {
		return armaDAO;
	}

	public void setArmaDAO(ArmaDAO armaDAO) {
		this.armaDAO = armaDAO;
	}
	
}
