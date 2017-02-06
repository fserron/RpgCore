package backend.bo;

import common.entities.DTO.TiradaCombateDTO;
import common.entities.DTO.TiradaDTO;

import backend.entities.Tirada;
import backend.entities.TiradaCombate;

public interface TiradaBO {

	public Tirada hacerTirada(TiradaDTO dto);

	TiradaCombate hacerTiradaCombate(TiradaCombateDTO dto);
	
}
