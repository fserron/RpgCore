package backend.entities;

public class TiradaCombate extends Tirada {

	private int dano;
	
	private int autoDano;

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getAutoDano() {
		return autoDano;
	}

	public void setAutoDano(int autoDano) {
		this.autoDano = autoDano;
	}
	
	
}
