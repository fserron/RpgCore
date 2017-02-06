package backend.dao.impl;

import java.util.Properties;

import org.springframework.stereotype.Component;

import backend.dao.ArmaDAO;
import backend.entities.Arma;

@Component
public class ArmaDAOImpl implements ArmaDAO {
	
	private Properties armas;

	public Arma obtenerArma(Integer codigo){
		
		Arma arma = null;
		
		if (existeArma(codigo)){
			
			arma = new Arma();
			
			try { //Atributos basicos de toda arma
				arma.setNombre(armas.getProperty(codigo + "." + "nombre"));
				arma.setEmboque(Integer.parseInt(armas.getProperty(codigo + "." + "emboque")));
				arma.setDanyo(Integer.parseInt(armas.getProperty(codigo + "." + "danyo")));
				arma.setDurabilidad(Integer.parseInt(armas.getProperty(codigo + "." + "durabilidad")));
			} catch (NumberFormatException nfe){
				System.err.println("No se ingresaron correctamente los atributos basicos de las armas. Verifique armas.properties e intente nuevamente.");
				nfe.printStackTrace();
			}
			
			try { //Atributos avanzados de las armas
				String cortaDistancia = armas.getProperty(codigo + "." + "cortaDistancia");
				String mediaDistancia = armas.getProperty(codigo + "." + "mediaDistancia");
				String largaDistancia = armas.getProperty(codigo + "." + "largaDistancia");
				String capacidad = armas.getProperty(codigo + "." + "capacidad");
				String rafaga = armas.getProperty(codigo + "." + "rafaga");
				String supersonica = armas.getProperty(codigo + "." + "supersonica");
				
				if (null != cortaDistancia && !cortaDistancia.isEmpty())
					arma.setCortaDistancia(Integer.parseInt(cortaDistancia));
				else
					arma.setCortaDistancia(0);
				
				if (null != mediaDistancia && !mediaDistancia.isEmpty())
					arma.setMediaDistancia(Integer.parseInt(mediaDistancia));
				else
					arma.setMediaDistancia(0);
				
				if (null != largaDistancia && !largaDistancia.isEmpty())
					arma.setLargaDistancia(Integer.parseInt(largaDistancia));
				else
					arma.setLargaDistancia(0);
				
				if (null != capacidad && !capacidad.isEmpty())
					arma.setCapacidad(Integer.parseInt(capacidad));
				else
					arma.setCapacidad(0);
				
				if (null != rafaga && !rafaga.isEmpty())
					arma.setRafaga(Integer.parseInt(rafaga));
				else
					arma.setRafaga(0);
				
				if (null != supersonica && !supersonica.isEmpty())
					arma.setSupersonico(Boolean.parseBoolean(supersonica));
				else
					arma.setSupersonico(false);
		
			} catch (NumberFormatException nfe){
				System.err.println("No se ingresaron correctamente los atributos avanzados de las armas. Verifique armas.properties e intente nuevamente.");
				nfe.printStackTrace();
			}
		}
		
		return arma;
	}
	
	public boolean existeArma(Integer codigo){
		boolean existe = false;
		String nombreArma = (String) armas.get(codigo + ".nombre");
		
		if (null != nombreArma)
			existe = true;
		
		return existe;
	}
}
