package queComemos;

import static org.junit.Assert.*;
import interfaces.ObservadorI;



import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderReceta.RecetaBuilder;
import builderReceta.RecetaGenerica;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.CondicionPreexistente;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import receta.Receta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;

public class TestReceta {
	
	
	private RecetaBuilder constructorReceta;
	@Before
	public void setUp() throws Exception {
		
		RepoUsuarios.getInstance().inadecuados= new ArrayList<CondicionPreexistente>();
		
	

	
	constructorReceta = new RecetaGenerica();
	constructorReceta.ingredienteAgregar("Azucar", "grs", 150.);
	constructorReceta.ingredienteAgregar("Carne", "kg",2.);
	constructorReceta.ingredienteAgregar("papa", "kg", 3.);
	constructorReceta.condimentoAgregar("Sal", "grs", 100.);
	constructorReceta.condimentoAgregar("Mayonesa", "grs", 100.);
	constructorReceta.dificultad("baja");
	constructorReceta.temporada("verano");
	constructorReceta.nombre("CarneAlHorno");
	constructorReceta.calorias(1524.0);
	
	
	 


		 
		
	}

	@Test
	public void testInadecuadoParaHipertenso() {
		List<String> comidas= new ArrayList<String>();
		comidas.add("Sal");
		Hipertenso hipertenso = new Hipertenso(null,"Hipertenso",comidas);
		RepoUsuarios.getInstance().inadecuados.add(hipertenso);
		
		
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.getInadecuados().contains(hipertenso));
	}
	
	public void testAdecuadoParaHipertenso() {
		List<String> comidas= new ArrayList<String>();
		Hipertenso hipertenso = new Hipertenso(null,"Hipertenso",comidas);
//		QueComemosApp.inicializar();
		RepoUsuarios.getInstance().inadecuados.add(hipertenso);
		
		
	
		
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.getInadecuados().contains(hipertenso));

}
	
	
	@Test
	public void testInadecuadoParaVegano() {
		List<String> comidas= new ArrayList<String>();
		comidas.add("Carne");
		Vegano vegano = new Vegano(null, "Vegano",comidas);
		RepoUsuarios.getInstance().inadecuados.add(vegano);
		
		
		
		
		
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.getInadecuados().contains(vegano));
	}
	
	@Test
	public void testAdecuadoParaVegano() {
		List<String> comidas= new ArrayList<String>();
		Vegano vegano = new Vegano(null, "Vegano",comidas);
		RepoUsuarios.getInstance().inadecuados.add(vegano);
		
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.getInadecuados().contains(vegano));
	}
	
	
	@Test
	public void testInadecuadoParaCeliaco() {
		List<String> comidas= new ArrayList<String>();
		Celiaco celiaco = new Celiaco(null, "Celiaco",comidas);
		RepoUsuarios.getInstance().inadecuados.add(celiaco);
		
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	@Test
	public void testAdecuadoParaCeliaco() {
		List<String> comidas= new ArrayList<String>();
		Celiaco celiaco = new Celiaco(null, "Celiaco",comidas);
		RepoUsuarios.getInstance().inadecuados.add(celiaco);
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	
	@Test
	public void testNoInadecuadoParaCeliaco() {
		List<String> comidas= new ArrayList<String>();
		Celiaco celiaco = new Celiaco(null, "celiaco",comidas);
		RepoUsuarios.getInstance().inadecuados.add(celiaco);
		
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	
	@Test
	public void testNoAdecuadoParaCeliaco() {
		List<String> comidas= new ArrayList<String>();
		Celiaco celiaco = new Celiaco(null, "celiaco",comidas);
		RepoUsuarios.getInstance().inadecuados.add(celiaco);
		
		
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	
	@Test
	public void testNoInadecuadoParaDiabetico() {
		List<String> comidas= new ArrayList<String>();
		Diabetico diabetico = new Diabetico(null, "diabetico",comidas);
		RepoUsuarios.getInstance().inadecuados.add(diabetico);
		
		
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.getInadecuados().contains("Diabetico"));
	}
	
	
	@Test
	public void testRecetaContieneUnaComida()
	{
		 Receta receta = constructorReceta.crearReceta();
		 assertTrue(receta.contiene("Carne"));
		
		
	}
	
	
	@Test
	public void testRecetaNoContieneUnaComida()
	{
		 Receta receta = constructorReceta.crearReceta();
		 assertFalse(receta.contiene("Chinchulin"));
		
		
	}
	
	@Test
	public void testRecetaContieneAlgunaComidaDeUnaLista()
	{
		 Receta receta = constructorReceta.crearReceta();
		List<String> conjuntoDeComidas = new ArrayList<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Carne");
		conjuntoDeComidas.add("Pollo");
		
		
		assertTrue(receta.contieneAlguna(conjuntoDeComidas));
		
			
	}
		
	
	@Test
	public void testRecetaNoContieneAlgunaComidaDeUnaLista()
	{
		 Receta receta = constructorReceta.crearReceta();
		List<String> conjuntoDeComidas = new ArrayList<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Pescado");
		conjuntoDeComidas.add("Pollo");
		
		
		assertFalse(receta.contieneAlguna(conjuntoDeComidas));
		
		
		
		
		
	}
	
	@Test
	public void testRecetaConSubRecetasContieneAlgunaComidaDeUnaLista()
	{
		
		 
		
			
		RecetaBuilder	otroConstructorReceta = new RecetaGenerica();
			otroConstructorReceta.ingredienteAgregar("Azucar", "grs", 150.);
			otroConstructorReceta.ingredienteAgregar("Carne", "kg",2.);
			otroConstructorReceta.ingredienteAgregar("papa", "kg", 3.);
			otroConstructorReceta.condimentoAgregar("Sal", "grs", 100.);
			otroConstructorReceta.condimentoAgregar("Mayonesa", "grs", 100.);
			otroConstructorReceta.dificultad("baja");
			otroConstructorReceta.temporada("verano");
			otroConstructorReceta.nombre("PolloConPapas");
			otroConstructorReceta.calorias(100.0);
		 
		
		Receta subreceta = otroConstructorReceta.crearReceta();
		
		constructorReceta.subRecetaAgregar(subreceta);
		
		Receta receta = constructorReceta.crearReceta();
		
		List<String> conjuntoDeComidas = new ArrayList<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Carne");
		conjuntoDeComidas.add("Pollo");
		
		
		assertTrue(receta.contieneAlguna(conjuntoDeComidas));
		
			
	}
		
	
	@Test
	public void testRecetaConSubRecetasNoContieneAlgunaComidaDeUnaLista()
	{
		
		
		RecetaBuilder	otroConstructorReceta = new RecetaGenerica();
			otroConstructorReceta.ingredienteAgregar("Azucar", "grs", 150.);
			otroConstructorReceta.ingredienteAgregar("Carne", "kg",2.);
			otroConstructorReceta.ingredienteAgregar("papa", "kg", 3.);
			otroConstructorReceta.condimentoAgregar("Sal", "grs", 100.);
			otroConstructorReceta.condimentoAgregar("Mayonesa", "grs", 100.);
			otroConstructorReceta.dificultad("baja");
			otroConstructorReceta.temporada("verano");
			otroConstructorReceta.nombre("PolloConPapas");
			otroConstructorReceta.calorias(100.0);
		 
		
		Receta subreceta = otroConstructorReceta.crearReceta();
		
		constructorReceta.subRecetaAgregar(subreceta);
		
		Receta receta = constructorReceta.crearReceta();
		
		List<String> conjuntoDeComidas = new ArrayList<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Pescado");
		conjuntoDeComidas.add("Pollo");
		
		
		assertFalse(receta.contieneAlguna(conjuntoDeComidas));
		
			
		
	}
	
	
	
	
	@Test 
	public void testValidaReceta()
	{

		Receta receta = constructorReceta.crearReceta();
				
		assertTrue(receta.validar());
		
		
	}
	
	
	@Test 
	public void testNoalidaReceta()
	{
		constructorReceta.calorias(.0);
		Receta receta = constructorReceta.crearReceta();
				
		assertFalse(receta.validar());
		
		
	}
	
	
	
	
	
	
	

	
	

}
