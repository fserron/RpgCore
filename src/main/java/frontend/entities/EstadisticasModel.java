package frontend.entities;

import java.util.LinkedHashMap;

import common.entities.enumerators.TipoTiradaEnum;

public class EstadisticasModel {

	private Integer atributo;
	private Integer habilidad;
	private Integer suerte;
	private Integer bono;
	private TipoTiradaEnum tipoTirada;
	private Integer numTiradas;
	
	private Integer defensa;
	private Integer carasDado;
	private Integer armadura;
	private Integer objetivo;
	private Integer retencion;
	private Integer sustraccion;
	private Integer penalizacion;
	private Integer dificultad;
	private boolean conSuerte;
	private boolean fatidica;
	private Integer bonoArma;
	private Integer bonoBase;
	
	//Atributos daño
	private boolean aplicarDano;
	private boolean restarDefensa;
	private boolean sumarArma;
	private boolean danoBase;
	private Integer carasDadoDano;
	private Integer valorExplosion;
	
	//Atributos armas
	private boolean armaFuego;
	private Integer potencia;
	
	/*
	 * Comienzo GETTERS y SETTERS
	 */
	public Integer getAtributo() {
		return atributo;
	}
	public void setAtributo(Integer atributo) {
		this.atributo = atributo;
	}
	public Integer getHabilidad() {
		return habilidad;
	}
	public void setHabilidad(Integer habilidad) {
		this.habilidad = habilidad;
	}
	public Integer getSuerte() {
		return suerte;
	}
	public void setSuerte(Integer suerte) {
		this.suerte = suerte;
	}
	public Integer getBono() {
		return bono;
	}
	public void setBono(Integer bono) {
		this.bono = bono;
	}
	public TipoTiradaEnum getTipoTirada() {
		return tipoTirada;
	}
	public void setTipoTirada(TipoTiradaEnum tipoTirada) {
		this.tipoTirada = tipoTirada;
	}
	public Integer getNumTiradas() {
		return numTiradas;
	}
	public void setNumTiradas(Integer numTiradas) {
		this.numTiradas = numTiradas;
	}
	public Integer getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(Integer objetivo) {
		this.objetivo = objetivo;
	}
	public Integer getDefensa() {
		return defensa;
	}
	public void setDefensa(Integer defensa) {
		this.defensa = defensa;
	}
	public Integer getCarasDado() {
		return carasDado;
	}
	public void setCarasDado(Integer carasDado) {
		this.carasDado = carasDado;
	}
	public Integer getArmadura() {
		return armadura;
	}
	public void setArmadura(Integer armadura) {
		this.armadura = armadura;
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
	public Integer getPenalizacion() {
		return penalizacion;
	}
	public void setPenalizacion(Integer penalizacion) {
		this.penalizacion = penalizacion;
	}
	public Integer getDificultad() {
		return dificultad;
	}
	public void setDificultad(Integer dificultad) {
		this.dificultad = dificultad;
	}
	public boolean isConSuerte() {
		return conSuerte;
	}
	public void setConSuerte(boolean conSuerte) {
		this.conSuerte = conSuerte;
	}
	public boolean isFatidica() {
		return fatidica;
	}
	public void setFatidica(boolean fatidica) {
		this.fatidica = fatidica;
	}
	public Integer getBonoArma() {
		return bonoArma;
	}
	public void setBonoArma(Integer bonoArma) {
		this.bonoArma = bonoArma;
	}
	public Integer getBonoBase() {
		return bonoBase;
	}
	public void setBonoBase(Integer bonoBase) {
		this.bonoBase = bonoBase;
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
	public boolean isSumarArma() {
		return sumarArma;
	}
	public void setSumarArma(boolean sumarArma) {
		this.sumarArma = sumarArma;
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
	public Integer getValorExplosion() {
		return valorExplosion;
	}
	public void setValorExplosion(Integer valorExplosion) {
		this.valorExplosion = valorExplosion;
	}
	public boolean isArmaFuego() {
		return armaFuego;
	}
	public void setArmaFuego(boolean armaFuego) {
		this.armaFuego = armaFuego;
	}
	public Integer getPotencia() {
		return potencia;
	}
	public void setPotencia(Integer bonoPotencia) {
		this.potencia = bonoPotencia;
	}
	/*
	 * Comienzo funciones de vista
	 */
	public TipoTiradaEnum[] getTipos(){
		return TipoTiradaEnum.values();
	}
	
	public LinkedHashMap<Integer, String> getCantidadTiradas(){
		LinkedHashMap<Integer, String> cantidadTiradas = new LinkedHashMap<Integer, String>();
		cantidadTiradas.put(100, "100");
		cantidadTiradas.put(1000, "1.000");
		cantidadTiradas.put(10000, "10.000");
		cantidadTiradas.put(100000, "100.000");
		//cantidadTiradas.put(1000000, "1.000.000"); //Se comenta debido a que sobrecarga la memoria de la maquina (8GB).
		return cantidadTiradas;
	}
	
	public LinkedHashMap<Integer, String> getListaValores(){
		LinkedHashMap<Integer, String> listaValores = new LinkedHashMap<Integer, String>();
		listaValores.put(1, "Atributo Atacante");
		listaValores.put(2, "Habilidad Atacante");
		listaValores.put(3, "Arma Atacante");
		listaValores.put(4, "Defensa");
		listaValores.put(5, "Armadura");
		return listaValores;
	}
	
	public LinkedHashMap<Integer, String> getListaValoresBlank(){
		LinkedHashMap<Integer, String> listaValores = new LinkedHashMap<Integer, String>();
		listaValores.put(0, "");
		listaValores.put(1, "Atributo Atacante");
		listaValores.put(2, "Habilidad Atacante");
		listaValores.put(3, "Arma Atacante");
		listaValores.put(4, "Defensa");
		listaValores.put(5, "Armadura");
		return listaValores;
	}
	
	public LinkedHashMap<Integer, String> getListaValoresCarasDado(){
		LinkedHashMap<Integer, String> listaValores = new LinkedHashMap<Integer, String>();
		listaValores.put(4, "D4");
		listaValores.put(6, "D6");
		listaValores.put(8, "D8");
		listaValores.put(10, "D10");
		listaValores.put(12, "D12");
		listaValores.put(20, "D20");
		return listaValores;
	}

	public LinkedHashMap<Integer, String> getListaCaras(){
		LinkedHashMap<Integer, String> listaValores = new LinkedHashMap<Integer, String>();
		listaValores.put(0, "No");
		listaValores.put(1, "Con 1");
		listaValores.put(2, "Con 2");
		listaValores.put(3, "Con 3");
		listaValores.put(4, "Con 4");
		listaValores.put(5, "Con 5");
		return listaValores;
	}
}
