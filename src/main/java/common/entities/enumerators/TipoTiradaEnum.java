package common.entities.enumerators;

public enum TipoTiradaEnum {
	
	NORMAL("Normal"),
	CON_SUERTE("Con Suerte"),
	FATIDICA("Fatidica");
	
	private String description;

	private TipoTiradaEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
