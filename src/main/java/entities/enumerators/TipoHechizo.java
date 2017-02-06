package entities.enumerators;

public enum TipoHechizo {

	ATAQUE(1),
	ASISTENCIA(2),
	INVOCACION(3),
	MALDICION(4),
	PASIVO(5),
	AUTO(9);
	
	private Integer numericValue;

	private TipoHechizo(Integer numericValue) {
		this.numericValue = numericValue;
	}

	public Integer getNumericValue() {
		return numericValue;
	}
	
}
