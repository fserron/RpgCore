package backend.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import backend.dao.DadoDAO;

@Component
public class DadoDAOImpl implements DadoDAO {

	public List<Integer> hacerTiradaSimple(Integer cantidad){
		List<Integer> resultados = new ArrayList<Integer>();
		
		for (int i = 0; i < cantidad; i++){
			resultados.add(tirarDadoSimple());
		}
		
		return resultados;
	}
	
	private Integer tirarDadoSimple(){
		return (int) Math.ceil(6 * Math.random());
	}
	
	public List<Integer> hacerTiradaVariable(Integer caras, Integer cantidad){
		List<Integer> resultados = new ArrayList<Integer>();
		
		for (int i = 0; i < cantidad; i++){
			resultados.add(tirarDadoVariable(caras));
		}
		
		return resultados;
	}
	
	private Integer tirarDadoVariable(Integer caras){
		return (int) Math.ceil(caras * Math.random());
	}
	
}
