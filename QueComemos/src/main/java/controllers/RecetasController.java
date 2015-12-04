package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;





import java.util.stream.Collectors;


import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import receta.Receta;
import repositorios.Recetario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RecetasController implements WithGlobalEntityManager, TransactionalOps {

	
	public ModelAndView mostrar(Request request , Response response)
	{
	    List<Receta> recetas;
	    
	    recetas = Recetario.getInstance().listaRecetasUsuario();
 

	    HashMap<String, Object> viewModel = new HashMap<>();
	    viewModel.put("listRecetas", recetas);

	    return new ModelAndView(viewModel, "recetas.hbs");
	  }
		

	
	
	public ModelAndView mostrarDetalle(Request request , Response response)
	{
		 String recetaid =  request.params(":id");
		Receta receta = Recetario.getInstance().obtenerReceta(Long.parseLong(recetaid));
		 	
			HashMap<String, Object> viewModel = new HashMap<>();
		    viewModel.put("receta", receta);
		    viewModel.put("subrecetas", receta.getSubRecetas());
		    viewModel.put("ingredientes", receta.getPreparacion().getIngredientes());
		    viewModel.put("condimentos", receta.getPreparacion().getCondimentos());
		    viewModel.put("explicacion", receta.getPreparacion().getExplicacion());
		    viewModel.put("inadecuados", receta.getInadecuados());
		    		    		
		return new ModelAndView(viewModel, "detalleReceta.hbs");
	}

	    
	public Void accederAlDetalle(Request request , Response response)
	{
	    
		String recetaid = request.queryParams("recetaid");
		
			
		response.redirect("/recetas/receta/"+recetaid);
		
		
		return null;
				
	}
	
	public Void modificarReceta(Request request , Response response)
	{
	    
		 String recetaid =  request.queryParams("recetaid");
		
		 Receta receta = Recetario.getInstance().obtenerReceta(Long.parseLong(recetaid));
		
		receta.setNombre(request.queryParams("nombreReceta"));
		receta.setTemporada(request.queryParams("temporada"));
		receta.setCalorias(Double.parseDouble(request.queryParams("caloriasReceta")));
		receta.setDificultad(request.queryParams("dificultad"));
		
		  withTransaction(() -> {
			 entityManager().persist(receta);
		    });
			
		
		
		
		response.redirect("/recetas");
		
		return null;
				
	}
	
	  
	

}