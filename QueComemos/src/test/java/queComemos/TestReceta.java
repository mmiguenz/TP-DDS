package queComemos;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestReceta {
	
	private String nombre;
	private double calorias;
	private Preparacion preparacion ;
	private String dificultad;
	private String temporada;
	private Set<Receta> subRecetas;
	private Set<CondicionPreexistente> inadecuados;

	@Before
	public void setUp() throws Exception {
		
	Set<String>	instrucciones = new HashSet<String>();
	Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
	Set<Ingrediente>condimentos = new HashSet<Ingrediente>();
	subRecetas=new HashSet<Receta>();
	inadecuados=new HashSet<CondicionPreexistente>();
		
		instrucciones.add("Preparar");
		instrucciones.add("Revolver");
		instrucciones.add("Hornear");
		
		Ingrediente sal= new Ingrediente("Sal","grs",100);
		Ingrediente carne= new Ingrediente("Carne","kg",2);
		Ingrediente papas= new Ingrediente("papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		Ingrediente azucar= new Ingrediente("Azucar","grs",150);
		
		ingredientes.add(azucar);
		ingredientes.add(carne);
		ingredientes.add(papas);
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
		

		
		
	     preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
		 dificultad = "Baja";
		 temporada= "Verano";
		 
		 
		
	}

	@Test
	public void testInadecuadoParaHipertenso() {
		Set<String> comidas= new HashSet<String>();
		Hipertenso hipertenso = new Hipertenso("Hipertenso",comidas);
		inadecuados.add(hipertenso);
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertTrue(receta.getInadecuados().contains(hipertenso));
	}
	
	public void testAdecuadoParaHipertenso() {
		Set<String> comidas= new HashSet<String>();
		Hipertenso hipertenso = new Hipertenso("Hipertenso",comidas);
			
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(hipertenso));

}
	
	
	@Test
	public void testInadecuadoParaVegano() {
		Set<String> comidas= new HashSet<String>();
		Vegano vegano = new Vegano("Vegano",comidas);
		inadecuados.add(vegano);
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertTrue(receta.getInadecuados().contains(vegano));
	}
	
	@Test
	public void testAdecuadoParaVegano() {
		Set<String> comidas= new HashSet<String>();
		Vegano vegano = new Vegano("Vegano",comidas);
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(vegano));
	}
	
	
	@Test
	public void testInadecuadoParaCeliaco() {
		Set<String> comidas= new HashSet<String>();
		Celiaco celiaco = new Celiaco("Celiaco",comidas);
		inadecuados.add(celiaco);
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertTrue(receta.getInadecuados().contains(celiaco));
	}
	
	@Test
	public void testAdecuadoParaCeliaco() {
		Set<String> comidas= new HashSet<String>();
		Celiaco celiaco = new Celiaco("Celiaco",comidas);
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	
	@Test
	public void testNoInadecuadoParaCeliaco() {
		Set<String> comidas= new HashSet<String>();
		Celiaco celiaco = new Celiaco("celiaco",comidas);
		
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	
	@Test
	public void testNoAdecuadoParaCeliaco() {
		Set<String> comidas= new HashSet<String>();
		Celiaco celiaco = new Celiaco("celiaco",comidas);
		
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	
	@Test
	public void testNoInadecuadoParaDiabetico() {
		Set<String> comidas= new HashSet<String>();
		Diabetico diabetico = new Diabetico("diabetico",comidas);
		inadecuados.add(diabetico);
		
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains("Diabetico"));
	}
	
	
	@Test
	public void testRecetaContieneUnaComida()
	{
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertTrue(receta.contiene("Carne"));
		
		
	}
	
	
	@Test
	public void testRecetaNoContieneUnaComida()
	{
		Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.contiene("Chinchulin"));
		
		
	}
	
	@Test
	public void testRecetaContieneAlgunaComidaDeUnaLista()
	{
		Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		Set<String> conjuntoDeComidas = new HashSet<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Carne");
		conjuntoDeComidas.add("Pollo");
		
		
		assertTrue(receta.contieneAlguna(conjuntoDeComidas));
		
			
	}
		
	
	@Test
	public void testRecetaNoContieneAlgunaComidaDeUnaLista()
	{
		Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		Set<String> conjuntoDeComidas = new HashSet<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Pescado");
		conjuntoDeComidas.add("Pollo");
		
		
		assertFalse(receta.contieneAlguna(conjuntoDeComidas));
		
		
		
		
		
	}
	
	
	
	

}
