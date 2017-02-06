package backend.entities;

import java.util.HashMap;

import backend.entities.enumerators.Estados;


public class Combate {

	HashMap<Integer, Personaje> combatientes;
	
	HashMap<Personaje, Estados> estados;

	public HashMap<Integer, Personaje> getCombatientes() {
		return combatientes;
	}

	public void setCombatientes(HashMap<Integer, Personaje> combatientes) {
		this.combatientes = combatientes;
	}

	public HashMap<Personaje, Estados> getEstados() {
		return estados;
	}

	public void setEstados(HashMap<Personaje, Estados> estados) {
		this.estados = estados;
	}
	
}
