package entities.enumerators;

public enum Resultados {
	
	SIN_EXITOS(0),
	EXITOS_1(1),
	EXITOS_2(2),
	EXITOS_3(3),
	EXITOS_4(4),
	EXITOS_5(5),
	EXITOS_6(6),
	EXITOS_7(7),
	EXITOS_8(8),
	EXITOS_9(9),
	EXITOS_10(10),
	EXITOS_11_PLUS(19),
	FRACASOS(99),
	TOTAL_EXITOS(11),
	TOTAL_FRACASOS(66),
	EXITOS_MAX(999);
	
	private Integer id;

	private Resultados(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
