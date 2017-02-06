package entities.enumerators;

public enum Clase {

	// HOMUNCULOS
	HOMUNCULUS(101),
	
	// HUMANOS
	MAGUS(201),
	EXECUTIONER(202),
	DEMON_HUNTER(203),
	HUMAN(204),
	
	//DEMI-HUMANOS
	FAIRIES(301),
	SUCCUBUS(302),
	WEREWOLF(303),
	
	//NO-MUERTOS
	GHOUL(401),
	VAMPIRE(402),
	DEAD_APOSTOL(403),
	
	//ESPIRITUS
	ELEMENTAL(501),
	NEKO(502),
	GHOST(503),
	HEROIC_SPIRIT(504), 
	TRUE_ANCESTOR(505),
	DEMON(506),
	GOD(507),
	
	//BESTIAS
	MONSTROUS_BEAST(601),
	PHANTASMAL_BEAST(602),
	DIVINE_BEAST(603);
	
	private Integer numericValue;

	private Clase(Integer numericValue) {
		this.numericValue = numericValue;
	}
	
	public static Clase parseTipo(Integer value) throws NumberFormatException{
		Clase tipo;
		if (value.equals(HOMUNCULUS.getNumericValue()))
			tipo = HOMUNCULUS;
		else if (value.equals(MAGUS.getNumericValue()))
			tipo = MAGUS;
		else if (value.equals(EXECUTIONER.getNumericValue()))
			tipo = EXECUTIONER;
		else if (value.equals(DEMON_HUNTER.getNumericValue()))
			tipo = DEMON_HUNTER;
		else if (value.equals(HUMAN.getNumericValue()))
			tipo = HUMAN;
		else if (value.equals(FAIRIES.getNumericValue()))
			tipo = FAIRIES;
		else if (value.equals(SUCCUBUS.getNumericValue()))
			tipo = SUCCUBUS;
		else if (value.equals(WEREWOLF.getNumericValue()))
			tipo = WEREWOLF;
		else if (value.equals(GHOUL.getNumericValue()))
			tipo = GHOUL;
		else if (value.equals(VAMPIRE.getNumericValue()))
			tipo = VAMPIRE;
		else if (value.equals(DEAD_APOSTOL.getNumericValue()))
			tipo = DEAD_APOSTOL;
		else if (value.equals(ELEMENTAL.getNumericValue()))
			tipo = ELEMENTAL;
		else if (value.equals(NEKO.getNumericValue()))
			tipo = NEKO;
		else if (value.equals(GHOST.getNumericValue()))
			tipo = GHOST;
		else if (value.equals(HEROIC_SPIRIT.getNumericValue()))
			tipo = HEROIC_SPIRIT;
		else if (value.equals(TRUE_ANCESTOR.getNumericValue()))
			tipo = TRUE_ANCESTOR;
		else if (value.equals(DEMON.getNumericValue()))
			tipo = DEMON;
		else if (value.equals(GOD.getNumericValue()))
			tipo = GOD;
		else if (value.equals(MONSTROUS_BEAST.getNumericValue()))
			tipo = MONSTROUS_BEAST;
		else if (value.equals(PHANTASMAL_BEAST.getNumericValue()))
			tipo = PHANTASMAL_BEAST;
		else if (value.equals(DIVINE_BEAST.getNumericValue()))
			tipo = DIVINE_BEAST;
		else {
			throw new NumberFormatException();
		}
		return tipo;
	}

	public Integer getNumericValue() {
		return numericValue;
	}
	
	public Integer getRacialMultiplier(){
		Integer multi = 1;
		if (this.equals(HOMUNCULUS))
			multi = 5;
		else if (this.equals(MAGUS))
			multi = 10;
		else if (this.equals(EXECUTIONER))
			multi = 10;
		else if (this.equals(DEMON_HUNTER))
			multi = 10;
		else if (this.equals(HUMAN))
			multi = 10;
		else if (this.equals(FAIRIES))
			multi = 15;
		else if (this.equals(SUCCUBUS))
			multi = 15;
		else if (this.equals(WEREWOLF))
			multi = 15;
		else if (this.equals(GHOUL))
			multi = 10;
		else if (this.equals(VAMPIRE))
			multi = 15;
		else if (this.equals(DEAD_APOSTOL))
			multi = 30;
		else if (this.equals(ELEMENTAL))
			multi = 20;
		else if (this.equals(NEKO))
			multi = 20;
		else if (this.equals(GHOST))
			multi = 5;
		else if (this.equals(HEROIC_SPIRIT))
			multi = 50;
		else if (this.equals(TRUE_ANCESTOR))
			multi = 75;
		else if (this.equals(DEMON))
			multi = 30;
		else if (this.equals(GOD))
			multi = 100;
		else if (this.equals(MONSTROUS_BEAST))
			multi = 20;
		else if (this.equals(PHANTASMAL_BEAST))
			multi = 40;
		else if (this.equals(DIVINE_BEAST))
			multi = 60;
		
		return multi;
	}	
}
