package backend.dao;

import java.util.List;

public interface DadoDAO {

	public List<Integer> hacerTiradaSimple(Integer cantidad);
	
	public List<Integer> hacerTiradaVariable(Integer caras, Integer cantidad);
	
}
