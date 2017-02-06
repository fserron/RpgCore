package entities.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

public class Tirada {
	
	private List<Integer> resultadoTirada;
	
	private int exitos;
	
	private int fracasos;
	
	public Tirada(){
		this.resultadoTirada = new ArrayList<Integer>();
		this.exitos = 0;
		this.fracasos = 0;
	}
	
	public List<Integer> getResultadoTirada() {
		return resultadoTirada;
	}

	public void setResultadoTirada(List<Integer> resultadoTirada) {
		this.resultadoTirada = resultadoTirada;
	}

	public int getExitos() {
		return exitos;
	}

	public void setExitos(int exitos) {
		this.exitos = exitos;
	}

	public int getFracasos() {
		return fracasos;
	}

	public void setFracasos(int fracasos) {
		this.fracasos = fracasos;
	}
	
}
