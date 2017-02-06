package backend.entities;

import java.util.List;


public class Personaje {
	
	private Cabecera cabecera;
	
	private Atributos atributos;
	
	private Habilidades habilidades;
	
	private Ventajas ventajas;

	// Adicionales
	private List<Arma> armas;
	
	private List<Hechizo> hechizos;
	
	public Personaje() {
		this.cabecera = new Cabecera();
		this.atributos = new Atributos();
		this.habilidades = new Habilidades();
		this.ventajas = new Ventajas();
	}

	public List<Arma> getArmas() {
		return armas;
	}

	public void setArmas(List<Arma> armas) {
		this.armas = armas;
	}
	
	public List<Hechizo> getHechizos() {
		return hechizos;
	}

	public void setHechizos(List<Hechizo> hechizos) {
		this.hechizos = hechizos;
	}
	
	//Ventajas Derivadas
	public int getDefensa(){
		//Calculamos la defensa como el promedio (redondeado hacia abajo) de los 3 atributos fisicos
		Integer sumatoria = 
			this.getAtributos().getFuerza().getRanking().getNumericValue() +
			this.getAtributos().getAgilidad().getRanking().getNumericValue() +
			this.getAtributos().getResistencia().getRanking().getNumericValue();
		
		return (int) Math.floor(sumatoria / 3);
	}
	
	public int getDefensaInhumana(){
		//Calculamos la defensa como el promedio (redondeado hacia abajo) de los 3 atributos fisicos
		Integer sumatoria = 
			this.getAtributos().getFuerza().getRankingInhumano().getNumericValue() +
			this.getAtributos().getAgilidad().getRankingInhumano().getNumericValue() +
			this.getAtributos().getResistencia().getRankingInhumano().getNumericValue();
		
		return (int) Math.floor(sumatoria / 3);
	}
	
	//Acciones del personaje
	public int getHPTotal(){
		return (this.getAtributos().getResistencia().toInt() * this.getCabecera().getClase().getRacialMultiplier()); 
	}
	
	public void regenerar(){
		this.getVentajas().setAguante(getHPTotal());
	}
	
	public Cabecera getCabecera() {
		return cabecera;
	}

	public void setCabecera(Cabecera cabecera) {
		this.cabecera = cabecera;
	}

	public Atributos getAtributos() {
		return atributos;
	}

	public void setAtributos(Atributos atributos) {
		this.atributos = atributos;
	}

	public Habilidades getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(Habilidades habilidades) {
		this.habilidades = habilidades;
	}

	public Ventajas getVentajas() {
		return ventajas;
	}

	public void setVentajas(Ventajas ventajas) {
		this.ventajas = ventajas;
	}

}