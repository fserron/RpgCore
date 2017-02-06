package entities.entities;

import entities.enumerators.TipoHechizo;

public class Hechizo {

	private TipoHechizo tipo;
	
	private String nombre;
	
	private int costo;

	public TipoHechizo getTipo() {
		return tipo;
	}

	public void setTipo(TipoHechizo tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

}
