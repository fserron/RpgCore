package common.entities.DTO;

import backend.entities.Objetivo;

public class TiradaDTO {

	//Cantidad de caras de dado
	private int caras;
	
	//Atributo del personaje que hace la tirada
	private int atributo;
	
	//Habilidad del personaje que hace la tirada
	private int habilidad;
	
	//Suerte del personaje que hace la tirada
	private int suerte;
	
	//Bonos totales
	private int bono;
	
	//Penalizaciones totales
	private int penalizacion;
	
	//Indicador de si es una tirada de destino
	private boolean chance;
	
	//Indicador de si se gasto un punto de suerte
	private boolean lucky;
	
	//Numero que tiene que salir en la cara del dado para que sea considerado un exito
	private Objetivo dificultad;
	
	//Cantidad de exitos a los que hay que llegar
	private Objetivo objetivo;

	public TiradaDTO(){
		this.caras = 6;
		this.atributo = 1;
		this.suerte = 1;
		this.habilidad = 0;
		this.bono = 0;
		this.penalizacion = 0;
		this.chance = false;
		this.lucky = false;
		this.objetivo = new Objetivo();
		this.dificultad = new Objetivo();
	}
	
	public int getCaras() {
		return caras;
	}

	public void setCaras(int caras) {
		this.caras = caras;
	}

	public int getAtributo() {
		return atributo;
	}

	public void setAtributo(int atributo) {
		this.atributo = atributo;
	}

	public int getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(int habilidad) {
		this.habilidad = habilidad;
	}

	public int getSuerte() {
		return suerte;
	}

	public void setSuerte(int suerte) {
		this.suerte = suerte;
	}

	public int getBono() {
		return bono;
	}

	public void setBono(int bono) {
		this.bono = bono;
	}

	public int getPenalizacion() {
		return penalizacion;
	}

	public void setPenalizacion(int penalizacion) {
		this.penalizacion = penalizacion;
	}

	public boolean isChance() {
		return chance;
	}

	public void setChance(boolean chance) {
		this.chance = chance;
	}

	public boolean isLucky() {
		return lucky;
	}

	public void setLucky(boolean lucky) {
		this.lucky = lucky;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	public Objetivo getDificultad() {
		return dificultad;
	}

	public void setDificultad(Objetivo dificultad) {
		this.dificultad = dificultad;
	}
}
