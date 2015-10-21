package queComemos;

import static org.junit.Assert.*;
import interfaces.ObservadorI;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import receta.Receta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.CondicionPreexistente;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import receta.Ingrediente;
import receta.Preparacion;

public class TestRecetario {

	@Before
	public void setUp() throws Exception {
		
		RepoUsuarios.inadecuados= new ArrayList<CondicionPreexistente>();
		Recetario.recetas = new ArrayList<Receta>();
		Recetario.observadores = new ArrayList<ObservadorI>();
		
		
		CondicionPreexistente	 celiaco = new Celiaco("celiaco",new ArrayList<String>());
		CondicionPreexistente hipertenso= new Hipertenso("hipertenso",new ArrayList<String>());
		CondicionPreexistente vegano = new Vegano("vegano",new ArrayList<String>());
		CondicionPreexistente diabetico =  new Diabetico("Diabetico",new ArrayList<String>());
		 
		 
		RepoUsuarios.inadecuados.add(celiaco);
		RepoUsuarios.inadecuados.add(hipertenso);
		RepoUsuarios.inadecuados.add(vegano);
		 RepoUsuarios.inadecuados.add(diabetico);
		 
		 
		 
		 // Lo que Hago es , llenar las recetas del sistema, con las recetas del repo externo, para que tengan algun dato.
		 Recetario.recetas.addAll(Recetario.listarTodas());   
		  
		 
		
		
	}

	@Test
	public void testModificarUnaRetaPublicaNoExistente() {
		
	
		
		
		Receta  recetaModificada = Recetario.modificarRecetaPublica("No La Encuentres", null, 400.0, null, null, null);
		
		assertTrue(recetaModificada == null);
		
		
	}
	
	@Test
	public void testModificarUnaRetaPublicaPreparacion() {
		
		List<Ingrediente> ingredientes = new ArrayList<>();
		List<Ingrediente> condimentos = new ArrayList<>();
		List<String> instrucciones = new ArrayList<>();
		
		Ingrediente pollo =new Ingrediente("pollo","kg",1.0);
		Ingrediente papas =new Ingrediente("papas","kg",0.5);		
		Ingrediente sal =new Ingrediente("sal","grs",10);
				
				
		ingredientes.add(pollo);
		ingredientes.add(papas);
		
		condimentos.add(sal);
		
		
		instrucciones.add("hornear");
		instrucciones.add("dorar");
		instrucciones.add("servir");
		
		
		
		Preparacion preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
		
		
		Receta  recetaModificada = Recetario.modificarRecetaPublica("ensalada caesar", null, null, preparacion, null, null);
		
		assertTrue(recetaModificada.getPreparacion().equals(preparacion));
		
		
				
	}
	
	
	@Test
	public void testModificarUnaRetaPublicaCalorias() {
		
		
		
		
		Receta  recetaModificada = Recetario.modificarRecetaPublica("ensalada caesar", null, 20.0, null, null, null);
		
		assertTrue(recetaModificada.getCalorias() == 20.0);
		
		
				
	}


}
