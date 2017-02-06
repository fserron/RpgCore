package entities.entities;

import entities.enumerators.Rank;
import entities.enumerators.Rank.Ranking;

public class Defensa {

	private Ranking objetivo;
	
	private int penalizacion;
	
	private Rank atributoEmboque;
	
	private Rank atributoDefensa;
	
	private Arma arma;

	public Ranking getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Ranking objetivo) {
		this.objetivo = objetivo;
	}

	public int getPenalizacion() {
		return penalizacion;
	}

	public void setPenalizacion(int penalizacion) {
		this.penalizacion = penalizacion;
	}

	public Rank getAtributoEmboque() {
		return atributoEmboque;
	}

	public void setAtributoEmboque(Rank atributoEmboque) {
		this.atributoEmboque = atributoEmboque;
	}

	public Rank getAtributoDefensa() {
		return atributoDefensa;
	}

	public void setAtributoDefensa(Rank atributoDefensa) {
		this.atributoDefensa = atributoDefensa;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

}
