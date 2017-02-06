package backend.entities;

import backend.entities.enumerators.Rank;

/**
 * Habilidades
 */
public class Habilidades {
	
	private Fisicas fisicas;
	
	private Mentales mentales;
	
	private Sociales sociales;

	public Habilidades(){
		this.fisicas = new Fisicas();
		this.mentales = new Mentales();
		this.sociales = new Sociales();
	}
	//Fisicas
	public class Fisicas {
		private Rank atletismo;
		private Rank combate;
		private Rank conduccion;
		private Rank pericias;
		private Rank punteria;
		private Rank sigilo;
		private Rank supervivencia;
		private Rank latrocinio;
		
		public Fisicas(){
			this.atletismo = new Rank();
			this.combate = new Rank();
			this.conduccion = new Rank();
			this.pericias = new Rank();
			this.punteria = new Rank();
			this.sigilo = new Rank();
			this.supervivencia = new Rank();
			this.latrocinio = new Rank();
		}
		
		public Rank getAtletismo() {
			return atletismo;
		}
		
		public void setAtletismo(Rank atletismo) {
			this.atletismo = atletismo;
		}
		
		public Rank getCombate() {
			return combate;
		}
		
		public void setCombate(Rank combate) {
			this.combate = combate;
		}
		
		public Rank getConduccion() {
			return conduccion;
		}
		
		public void setConduccion(Rank conduccion) {
			this.conduccion = conduccion;
		}
		
		public Rank getPericias() {
			return pericias;
		}
		
		public void setPericias(Rank pericias) {
			this.pericias = pericias;
		}
		
		public Rank getPunteria() {
			return punteria;
		}
		
		public void setPunteria(Rank punteria) {
			this.punteria = punteria;
		}
		
		public Rank getSigilo() {
			return sigilo;
		}
		
		public void setSigilo(Rank sigilo) {
			this.sigilo = sigilo;
		}
		
		public Rank getSupervivencia() {
			return supervivencia;
		}
		
		public void setSupervivencia(Rank supervivencia) {
			this.supervivencia = supervivencia;
		}
		
		public Rank getLatrocinio() {
			return latrocinio;
		}
		
		public void setLatrocinio(Rank latrocinio) {
			this.latrocinio = latrocinio;
		}
		
	}
	
	//Mentales
	public class Mentales {
		private Rank alerta;
		private Rank academicismo;
		private Rank ciencia;
		private Rank concentracion;
		private Rank investigacion;
		private Rank medicina;
		private Rank ocultismo;
		private Rank sentidoMagico;
		
		public Mentales(){
			this.academicismo = new Rank();
			this.alerta = new Rank();
			this.ciencia = new Rank();
			this.concentracion = new Rank();
			this.investigacion = new Rank();
			this.medicina = new Rank();
			this.ocultismo = new Rank();
			this.sentidoMagico = new Rank();
		}
		
		public Rank getAlerta() {
			return alerta;
		}
		
		public void setAlerta(Rank alerta) {
			this.alerta = alerta;
		}
		
		public Rank getAcademicismo() {
			return academicismo;
		}
		
		public void setAcademicismo(Rank academicismo) {
			this.academicismo = academicismo;
		}
		
		public Rank getCiencia() {
			return ciencia;
		}
		
		public void setCiencia(Rank ciencia) {
			this.ciencia = ciencia;
		}
		
		public Rank getConcentracion() {
			return concentracion;
		}
		
		public void setConcentracion(Rank concentracion) {
			this.concentracion = concentracion;
		}
		
		public Rank getInvestigacion() {
			return investigacion;
		}
		
		public void setInvestigacion(Rank investigacion) {
			this.investigacion = investigacion;
		}
		
		public Rank getMedicina() {
			return medicina;
		}
		
		public void setMedicina(Rank medicina) {
			this.medicina = medicina;
		}
		
		public Rank getOcultismo() {
			return ocultismo;
		}
		
		public void setOcultismo(Rank ocultismo) {
			this.ocultismo = ocultismo;
		}
		
		public Rank getSentidoMagico() {
			return sentidoMagico;
		}
		
		public void setSentidoMagico(Rank sentidoMagico) {
			this.sentidoMagico = sentidoMagico;
		}
		
	}
	
	//Sociales
	public class Sociales {
		private Rank burocracia;
		private Rank callejeo;
		private Rank empatia;
		private Rank empatiaAnimal;
		private Rank engano;
		private Rank expresionArtistica;
		private Rank intimidacion;
		private Rank sociabilizar;
		
		public Sociales() {
			this.burocracia = new Rank();
			this.callejeo = new Rank();
			this.empatia = new Rank();
			this.empatiaAnimal = new Rank();
			this.engano = new Rank();
			this.expresionArtistica = new Rank();
			this.intimidacion = new Rank();
			this.sociabilizar = new Rank();
		}
		
		public Rank getBurocracia() {
			return burocracia;
		}
		
		public void setBurocracia(Rank burocracia) {
			this.burocracia = burocracia;
		}
		
		public Rank getCallejeo() {
			return callejeo;
		}
		
		public void setCallejeo(Rank callejeo) {
			this.callejeo = callejeo;
		}
		
		public Rank getEmpatia() {
			return empatia;
		}
		
		public void setEmpatia(Rank empatia) {
			this.empatia = empatia;
		}
		
		public Rank getEmpatiaAnimal() {
			return empatiaAnimal;
		}
		
		public void setEmpatiaAnimal(Rank empatiaAnimal) {
			this.empatiaAnimal = empatiaAnimal;
		}
		
		public Rank getEngano() {
			return engano;
		}
		
		public void setEngano(Rank engano) {
			this.engano = engano;
		}
		
		public Rank getExpresionArtistica() {
			return expresionArtistica;
		}
		
		public void setExpresionArtistica(Rank expresionArtistica) {
			this.expresionArtistica = expresionArtistica;
		}
		
		public Rank getIntimidacion() {
			return intimidacion;
		}
		
		public void setIntimidacion(Rank intimidacion) {
			this.intimidacion = intimidacion;
		}
		
		public Rank getSociabilizar() {
			return sociabilizar;
		}
		
		public void setSociabilizar(Rank sociabilizar) {
			this.sociabilizar = sociabilizar;
		}
		
	}
	
	public Fisicas getFisicas() {
		return fisicas;
	}

	public void setFisicas(Fisicas fisicas) {
		this.fisicas = fisicas;
	}

	public Mentales getMentales() {
		return mentales;
	}

	public void setMentales(Mentales mentales) {
		this.mentales = mentales;
	}

	public Sociales getSociales() {
		return sociales;
	}

	public void setSociales(Sociales sociales) {
		this.sociales = sociales;
	}
}