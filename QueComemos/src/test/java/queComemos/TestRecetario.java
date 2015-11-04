package queComemos;

import static org.junit.Assert.*;
import interfaces.ObservadorI;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

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

public class TestRecetario extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Before
	public void setUp() throws Exception {
		
		RepoUsuarios.getInstance().inadecuados= new ArrayList<CondicionPreexistente>();

		
		CondicionPreexistente	 celiaco = new Celiaco(null, "celiaco",new ArrayList<String>());
		CondicionPreexistente hipertenso= new Hipertenso(null, "hipertenso",new ArrayList<String>());
		CondicionPreexistente vegano = new Vegano(null, "vegano",new ArrayList<String>());
		CondicionPreexistente diabetico =  new Diabetico(null, "Diabetico",new ArrayList<String>());
		 
		 
		RepoUsuarios.getInstance().inadecuados.add(celiaco);
		RepoUsuarios.getInstance().inadecuados.add(hipertenso);
		RepoUsuarios.getInstance().inadecuados.add(vegano);
		 RepoUsuarios.getInstance().inadecuados.add(diabetico);
		 
		 
		 
		 // Lo que Hago es , llenar las recetas del sistema, con las recetas del repo externo, para que tengan algun dato.
	  
		  
		 
		
		
	}

	@Test
	public void testModificarUnaRetaPublicaNoExistente() {
		
	
		
		
		Receta  recetaModificada = Recetario.getInstance().modificarRecetaPublica("No La Encuentres", null, 400.0, null, null, null);
		
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
		
		
		Receta  recetaModificada = Recetario.getInstance().modificarRecetaPublica("ensalada caesar", null, null, preparacion, null, null);
		
		assertTrue(recetaModificada.getPreparacion().equals(preparacion));
		
		
				
	}
	
	
	@Test
	public void testModificarUnaRetaPublicaCalorias() {
		
		
		
		
		Receta  recetaModificada = Recetario.getInstance().modificarRecetaPublica("ensalada caesar", null, 20.0, null, null, null);
		
		assertTrue(recetaModificada.getCalorias() == 20.0);
		
		
				
	}


}
