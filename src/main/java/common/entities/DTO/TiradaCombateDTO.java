package common.entities.DTO;

public class TiradaCombateDTO extends TiradaDTO{

	//Atributos Emboque
	private Integer armadura;
	private Integer defensa;
	private Integer retencion;
	private Integer sustraccion;
	private Integer bonoArma;
	private Integer bonoDanoBase;

	//Atributos Daño
	private boolean aplicarDano;
	private boolean restarDefensa;
	private boolean danoBase;
	private Integer carasDadoDano;
	private boolean explota;
	private Integer valorExplosion;
	
	public Integer getArmadura() {
		return armadura;
	}
	public void setArmadura(Integer armadura) {
		this.armadura = armadura;
	}
	public Integer getDefensa() {
		return defensa;
	}
	public void setDefensa(Integer defensa) {
		this.defensa = defensa;
	}
	public Integer getRetencion() {
		return retencion;
	}
	public void setRetencion(Integer retencion) {
		this.retencion = retencion;
	}
	public Integer getSustraccion() {
		return sustraccion;
	}
	public void setSustraccion(Integer sustraccion) {
		this.sustraccion = sustraccion;
	}
	public Integer getBonoArma() {
		return bonoArma;
	}
	public void setBonoArma(Integer bonoArma) {
		this.bonoArma = bonoArma;
	}
	public Integer getBonoDanoBase() {
		return bonoDanoBase;
	}
	public void setBonoDanoBase(Integer bonoDanoBase) {
		this.bonoDanoBase = bonoDanoBase;
	}
	public boolean isAplicarDano() {
		return aplicarDano;
	}
	public void setAplicarDano(boolean aplicarDano) {
		this.aplicarDano = aplicarDano;
	}
	public boolean isRestarDefensa() {
		return restarDefensa;
	}
	public void setRestarDefensa(boolean restarDefensa) {
		this.restarDefensa = restarDefensa;
	}
	public boolean isDanoBase() {
		return danoBase;
	}
	public void setDanoBase(boolean danoBase) {
		this.danoBase = danoBase;
	}
	public Integer getCarasDadoDano() {
		return carasDadoDano;
	}
	public void setCarasDadoDano(Integer carasDadoDano) {
		this.carasDadoDano = carasDadoDano;
	}
	public boolean isExplota() {
		return explota;
	}
	public void setExplota(boolean explota) {
		this.explota = explota;
	}
	public Integer getValorExplosion() {
		return valorExplosion;
	}
	public void setValorExplosion(Integer valorExplosion) {
		this.valorExplosion = valorExplosion;
	}
}
