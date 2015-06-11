package queComemos;


import java.util.ArrayList;
import java.util.List;

import org.json.*;

import javax.json.*;

import queComemos.entrega3.repositorio.BusquedaRecetas;
import queComemos.entrega3.repositorio.RepoRecetas;







public class RepoRecetasAd {
	
	public List<Receta>  traerTodasRecetasExternas()
	{
		
		BusquedaRecetas busqueda = new BusquedaRecetas();
		RepoRecetas repoRecetas = new RepoRecetas();
		String resultado = repoRecetas.getRecetas(busqueda);
		
		return  this.mapiarResultado(resultado);
		

		
	
		

		
	}
	
	
	
	
	public   List<Receta> mapiarResultado(String resultado)

	{
		
		 List<Receta> recetas = new ArrayList<Receta>();
		  
		 /* 
		  * =================================================
		  * Parseo JsonString 
		  * =================================================
		  */
		JSONArray jArr =new JSONArray(resultado); 
		for(int i = 0 ; i<jArr.length(); i++)
		{
			
			JSONObject jObj = jArr.getJSONObject(i);
			 
			String nombre =  jObj.getString("nombre");
			JSONArray ingredientes = jObj.getJSONArray("ingredientes");
			Double tiempoPreparacion = jObj.getDouble("tiempoPreparacion");
			Double calorias  =  jObj.getDouble("totalCalorias");
			String dificultad = jObj.getString("dificultadReceta");
			String autor = jObj.getString("autor");
			Integer anio = jObj.getInt("anioReceta");
			
			
			
			Preparacion preparacion = this.mapiarPreparacion(ingredientes);
			
			 /* 
			  * =================================================
			  * Mapeo Receta
			  * =================================================
			  */
			recetas.add(new Receta(nombre,calorias,preparacion,dificultad,"",new ArrayList<Receta>(),new ArrayList<CondicionPreexistenteI>()));
		}
		

		return recetas;

	}
	
	
	private Preparacion mapiarPreparacion(JSONArray ingredientes) {
		
		List<Ingrediente> ingres = new ArrayList<Ingrediente>();
		
		for (int i = 0 ; i< ingredientes.length(); i++)
		{
			
			Ingrediente ingre = new Ingrediente( ingredientes.getString(i) , "", 1.0);
			ingres.add(ingre);
			
			
			
		}
		
	 return  new Preparacion(ingres,new ArrayList<Ingrediente>(), new ArrayList<String>());
		 
		
		
		
	}





}
