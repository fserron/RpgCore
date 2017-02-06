package backend.entities.enumerators;

public enum Estados {

	OK(1),
	FULL_DODGE(2),
	KO(9);
	
	private Integer numericValue;

	private Estados(Integer numericValue) {
		this.numericValue = numericValue;
	}

	public Integer getNumericValue() {
		return numericValue;
	}
	
}
