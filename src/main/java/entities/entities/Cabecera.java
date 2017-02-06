package entities.entities;

import entities.enumerators.Clase;

public class Cabecera {
	private String nombre;
	private String jugador;
	private Integer edad;
	private String naturaleza;
	private String concepto;
	private String motivacion;
	private Clase clase;
	private String origen;
	
	public Cabecera(){
		this.nombre = new String();
		this.jugador = new String();
		this.naturaleza = new String();
		this.concepto = new String();
		this.motivacion = new String();
		this.origen = new String();			
	}
	

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getNaturaleza() {
		return naturaleza;
	}

	public void setNaturaleza(String naturaleza) {
		this.naturaleza = naturaleza;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getMotivacion() {
		return motivacion;
	}

	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}
}
