package backend.bo;

import common.entities.DTO.EstadisticaArmaDTO;
import common.entities.DTO.EstadisticaDTO;
import common.entities.DTO.TiradaCombateDTO;
import common.entities.DTO.TiradaDTO;


public interface EstadisticaBO {

	public Float danoPromedioArma(EstadisticaArmaDTO dto, Integer muestra);
	
	public EstadisticaDTO estadisticaTirada(TiradaDTO dto, Integer muestra);

	public EstadisticaDTO estadisticaCombateTirada(TiradaCombateDTO dto, Integer muestra);

	public EstadisticaDTO generarTabla(TiradaCombateDTO tirada,Integer numTiradas);
	
}
