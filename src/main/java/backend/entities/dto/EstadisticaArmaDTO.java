package backend.entities.dto;

public class EstadisticaArmaDTO {

	private TiradaDTO tiradaDTO;
	
	private Integer codigoArma;
	
	private Integer rafaga;

	public TiradaDTO getTiradaDTO() {
		return tiradaDTO;
	}

	public void setTiradaDTO(TiradaDTO tiradaDTO) {
		this.tiradaDTO = tiradaDTO;
	}

	public Integer getCodigoArma() {
		return codigoArma;
	}

	public void setCodigoArma(Integer codigoArma) {
		this.codigoArma = codigoArma;
	}

	public Integer getRafaga() {
		return rafaga;
	}

	public void setRafaga(Integer rafaga) {
		this.rafaga = rafaga;
	}
	
}
