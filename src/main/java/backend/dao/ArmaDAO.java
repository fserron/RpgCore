package backend.dao;

import backend.entities.Arma;

public interface ArmaDAO {

	/**
	 * Metodo que devuelve un arma correspondiente a un codigo.
	 * 
	 * @param codigo
	 * @return el arma
	 */
	public Arma obtenerArma(Integer codigo);
	
	/**
	 * Metodo que verifica si existe el arma correspondiente a un codigo.
	 * 
	 * @param codigo
	 * @return un booleano que indica si el arma existe o no.
	 */
	public boolean existeArma(Integer codigo);
	
}
