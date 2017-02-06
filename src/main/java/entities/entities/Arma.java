package entities.entities;

public class Arma {

	private String nombre;

	private int emboque;
	
	private int danyo;
	
	private int durabilidad;
	
	//Atributos de armas a distancia	
	private int cortaDistancia;
	
	private int mediaDistancia;
	
	private int largaDistancia;
	
	private int capacidad;
	
	private int rafaga;
	
	private int reservaBalas;
	
	private boolean isSupersonico;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEmboque() {
		return emboque;
	}

	public void setEmboque(int emboque) {
		this.emboque = emboque;
	}

	public int getDanyo() {
		return danyo;
	}

	public void setDanyo(int danyo) {
		this.danyo = danyo;
	}

	public int getDurabilidad() {
		return durabilidad;
	}

	public void setDurabilidad(int durabilidad) {
		this.durabilidad = durabilidad;
	}

	public int getCortaDistancia() {
		return cortaDistancia;
	}

	public void setCortaDistancia(int cortaDistancia) {
		this.cortaDistancia = cortaDistancia;
	}

	public int getMediaDistancia() {
		return mediaDistancia;
	}

	public void setMediaDistancia(int mediaDistancia) {
		this.mediaDistancia = mediaDistancia;
	}

	public int getLargaDistancia() {
		return largaDistancia;
	}

	public void setLargaDistancia(int largaDistancia) {
		this.largaDistancia = largaDistancia;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getRafaga() {
		return rafaga;
	}

	public void setRafaga(int rafaga) {
		this.rafaga = rafaga;
	}

	public int getReservaBalas() {
		return reservaBalas;
	}

	public void setReservaBalas(int reservaBalas) {
		this.reservaBalas = reservaBalas;
	}
	
	public void recargar(){
		reservaBalas = capacidad;
	}

	public boolean isSupersonico() {
		return isSupersonico;
	}

	public void setSupersonico(boolean supersonico) {
		this.isSupersonico = supersonico;
	}
	
	public boolean isRanged(){
		if (this.cortaDistancia > 3)
			return true;
		else
			return false;
	}
}
