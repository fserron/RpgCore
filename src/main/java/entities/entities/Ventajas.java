package entities.entities;

/**
 * Ventajas Derivadas
 */
class Ventajas {
	
	//Salud Fisica = Resistencia x Raza
	private Integer aguante;
	
	//Salud Mental = Inteligencia x Raza
	private Integer resolucion;
	
	//Salud Social = Personalidad x Raza
	private Integer compostura;
	
	public Ventajas(){
		this.aguante = 0;
		this.resolucion = 0;
		this.compostura = 0;
	}

	public Integer getAguante() {
		return aguante;
	}

	public void setAguante(Integer aguante) {
		this.aguante = aguante;
	}

	public Integer getResolucion() {
		return resolucion;
	}

	public void setResolucion(Integer resolucion) {
		this.resolucion = resolucion;
	}

	public Integer getCompostura() {
		return compostura;
	}

	public void setCompostura(Integer compostura) {
		this.compostura = compostura;
	}
	
}
