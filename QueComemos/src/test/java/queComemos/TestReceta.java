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
	private Receta subReceta;
	private Set<String> inadecuados;

	@Before
	public void setUp() throws Exception {
		
	Set<String>	instrucciones = new HashSet<String>();
	Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
	Set<Ingrediente>condimentos = new HashSet<Ingrediente>();
		
		instrucciones.add("Preparar");
		instrucciones.add("Revolver");
		instrucciones.add("Hornear");
		
		Ingrediente sal= new Ingrediente("Sal","grs",100);
		Ingrediente carne= new Ingrediente("Carne","kg",2);
		Ingrediente papas= new Ingrediente("papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		
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
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subReceta=null );
		 assertTrue(receta.getInadecuados().contains("Hipertenso"));
	}
	
	@Test
	public void testNoInadecuadoParaDiabetico() {
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subReceta=null );
		 assertFalse(receta.getInadecuados().contains("Diabetico"));
	}
	
	
	@Test
	public void testRecetaContieneUnaComida()
	{
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subReceta=null );
		 assertTrue(receta.contiene("Carne"));
		
		
	}
	
	
	@Test
	public void testRecetaNoContieneUnaComida()
	{
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subReceta=null );
		 assertFalse(receta.contiene("Chinchulin"));
		
		
	}
	
	@Test
	public void testRecetaContieneAlgunaComidaDeUnaLista()
	{
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subReceta=null );
		Set<String> conjuntoDeComidas = new HashSet<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Carne");
		conjuntoDeComidas.add("Pollo");
		
		
		assertTrue(receta.contieneAlguna(conjuntoDeComidas));
		
		
		
		
		
	}
	
	
	@Test
	public void testRecetaNoContieneAlgunaComidaDeUnaLista()
	{
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subReceta=null );
		Set<String> conjuntoDeComidas = new HashSet<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Pescado");
		conjuntoDeComidas.add("Pollo");
		
		
		assertFalse(receta.contieneAlguna(conjuntoDeComidas));
		
		
		
		
		
	}
	
	
	
	

}
