package repositorios;


import java.util.ArrayList;
import java.util.List;

import org.json.*;

import condicionesPreexistentes.CondicionPreexistente;
import queComemos.entrega3.repositorio.BusquedaRecetas;
import queComemos.entrega3.repositorio.RepoRecetas;
import receta.Ingrediente;
import receta.Preparacion;
import receta.Receta;







public class RepoRecetasAd {
	
	public List<Receta>  traerTodasRecetasExternas()
	{
		
		BusquedaRecetas busqueda = new BusquedaRecetas();
		RepoRecetas repoRecetas = new RepoRecetas();
		String resultado = repoRecetas.getRecetas(busqueda);
		
		return  this.mapiarResultado(resultado);
		
	}
	
	
	
	public List<Receta> consultaEnRepoExterno(ConsultaRepoExtAd consulta)
	{
		
		BusquedaRecetas busqueda = new BusquedaRecetas();
		RepoRecetas repo = new RepoRecetas();
		busqueda.setNombre(consulta.getNombre());
		busqueda.setDificultad(consulta.dificultad());
		
		
		
		
		
		consulta.getPalabrasClaves().forEach(palabra -> busqueda.agregarPalabraClave(palabra));
		
		
		String resultado =  repo.getRecetas(busqueda);
		return this.mapiarResultado(resultado);
		
		
		

		
		
	}
	
	
	
	
	private   List<Receta> mapiarResultado(String resultado)

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
			Double calorias  =  jObj.getDouble("totalCalorias");
			String dificultad = jObj.getString("dificultadReceta");

			
			
			Preparacion preparacion = this.mapiarPreparacion(ingredientes);
			
			 /* 
			  * =================================================
			  * Mapeo Receta
			  * =================================================
			  */
			recetas.add(new Receta(null, nombre,calorias,preparacion,dificultad,"",new ArrayList<Receta>(),new ArrayList<CondicionPreexistente>()));
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
