package frontend.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import common.entities.enumerators.TipoTiradaEnum;

import frontend.entities.EstadisticasModel;

@Controller
public class MainController {
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listUsers() {
        String url = "index/index";
        return url;
    }
    
    @RequestMapping("/eleccion")
    public ModelAndView eleccion(@RequestParam String eleccion) {
    	ModelAndView view;
    	
    	if( eleccion.equals("menu") ){
    		view = new ModelAndView();
    		view.setViewName("index/index");
	    } else if( eleccion.equals("tirador") ){
	    	view = new ModelAndView();
	    	view.setViewName("Tirador/Tirador");
	    } else if ( eleccion.equals("estadisticaSimple")){
	    	EstadisticasModel model = new EstadisticasModel();
	    	view = new ModelAndView(
	    			"Estadisticas/EstadisticaSimple",
	    			"model",
	    			model);	    
	    } else if ( eleccion.equals("estadisticaCombate")){
	    	EstadisticasModel model = new EstadisticasModel();
	    	view = new ModelAndView(
	    			"Estadisticas/EstadisticaCombate",
	    			"model",
	    			model);
	    } else if ( eleccion.equals("estadisticaArmas")){
	    	EstadisticasModel model = new EstadisticasModel();
	    	view = new ModelAndView(
	    			"Estadisticas/EstadisticaArmas",
	    			"model",
	    			model);
	    } else { //TODO: Borrar, esta aca por cualquier error
	    	view = new ModelAndView();
	    	view.setViewName("index/index");
	    }
    	
        return view;
    }
}