package entities.entities;

import entities.enumerators.Rank;


public class Atributos {

	private Rank fuerza;
	private Rank agilidad;
	private Rank resistencia;
	private Rank magia;
	private Rank personalidad;
	private Rank inteligencia;
	private Rank suerte;
	
	public Atributos(){
		this.fuerza = new Rank();
		this.agilidad = new Rank();
		this.resistencia = new Rank();
		this.magia = new Rank();
		this.personalidad = new Rank();
		this.inteligencia = new Rank();
		this.suerte = new Rank();
	}

	public Rank getFuerza() {
		return fuerza;
	}

	public void setFuerza(Rank fuerza) {
		this.fuerza = fuerza;
	}

	public Rank getAgilidad() {
		return agilidad;
	}

	public void setAgilidad(Rank agilidad) {
		this.agilidad = agilidad;
	}

	public Rank getResistencia() {
		return resistencia;
	}

	public void setResistencia(Rank resistencia) {
		this.resistencia = resistencia;
	}

	public Rank getMagia() {
		return magia;
	}

	public void setMagia(Rank magia) {
		this.magia = magia;
	}

	public Rank getPersonalidad() {
		return personalidad;
	}

	public void setPersonalidad(Rank personalidad) {
		this.personalidad = personalidad;
	}
	
	public Rank getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(Rank inteligencia) {
		this.inteligencia = inteligencia;
	}

	public Rank getSuerte() {
		return suerte;
	}

	public void setSuerte(Rank suerte) {
		this.suerte = suerte;
	}
}
