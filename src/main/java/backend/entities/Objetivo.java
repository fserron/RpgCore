package backend.entities;

public class Objetivo {

	private Integer puntuacion;
	private String descripcion;
	private boolean menorIgual;
	
	public Integer getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isMenorIgual() {
		return menorIgual;
	}
	public void setMenorIgual(boolean menorIgual) {
		this.menorIgual = menorIgual;
	}
}
