package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.tpAnual.queComemos.recetas.Recetario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import observadores.ObserverVegetariano;
import observadores.ObserverVecesConsultada;
import observadores.ObserverPorSexo;
import observadores.ObserverPorHora;

public class TestReceta {
	
	private String nombre;
	private double calorias;
	private Preparacion preparacion ;
	private String dificultad;
	private String temporada;
	private List<Receta> subRecetas;
	private List<	CondicionPreexistenteI> inadecuados;

	@Before
	public void setUp() throws Exception {
	
	List<String>	instrucciones = new ArrayList<String>();
	List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
	List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
	subRecetas=new ArrayList<Receta>();
	
	inadecuados=new ArrayList<CondicionPreexistenteI>();
	List<String>comidasProhibidas= new ArrayList<String>();
	List<String>comidasProhibidasH= new ArrayList<String>();
	comidasProhibidasH.add("caldo");
	
	
	Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
	Vegano vegano = new Vegano("vegano",comidasProhibidas);
	Hipertenso hipertenso = new Hipertenso("hipertenso",comidasProhibidasH);
	Diabetico diabetico = new Diabetico("diabetico",comidasProhibidas);
	
//	QueComemosApp.inadecuados=new ArrayList<CondicionPreexistenteI>();

	
		
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
		List<String> comidas= new ArrayList<String>();
		comidas.add("Sal");
		Hipertenso hipertenso = new Hipertenso("Hipertenso",comidas);
		Recetario.inadecuados.add(hipertenso);
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertTrue(receta.getInadecuados().contains(hipertenso));
	}
	
	public void testAdecuadoParaHipertenso() {
		List<String> comidas= new ArrayList<String>();
		Hipertenso hipertenso = new Hipertenso("Hipertenso",comidas);
//		QueComemosApp.inicializar();
		Recetario.inadecuados.add(hipertenso);
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(hipertenso));

}
	
	
	@Test
	public void testInadecuadoParaVegano() {
		List<String> comidas= new ArrayList<String>();
		comidas.add("Carne");
		Vegano vegano = new Vegano("Vegano",comidas);
		Recetario.inadecuados.add(vegano);
		
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertTrue(receta.getInadecuados().contains(vegano));
	}
	
	@Test
	public void testAdecuadoParaVegano() {
		List<String> comidas= new ArrayList<String>();
		Vegano vegano = new Vegano("Vegano",comidas);
		Recetario.inadecuados.add(vegano);
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(vegano));
	}
	
	
	@Test
	public void testInadecuadoParaCeliaco() {
		List<String> comidas= new ArrayList<String>();
		Celiaco celiaco = new Celiaco("Celiaco",comidas);
		Recetario.inadecuados.add(celiaco);
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	@Test
	public void testAdecuadoParaCeliaco() {
		List<String> comidas= new ArrayList<String>();
		Celiaco celiaco = new Celiaco("Celiaco",comidas);
		Recetario.inadecuados.add(celiaco);
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	
	@Test
	public void testNoInadecuadoParaCeliaco() {
		List<String> comidas= new ArrayList<String>();
		Celiaco celiaco = new Celiaco("celiaco",comidas);
		Recetario.inadecuados.add(celiaco);
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	
	@Test
	public void testNoAdecuadoParaCeliaco() {
		List<String> comidas= new ArrayList<String>();
		Celiaco celiaco = new Celiaco("celiaco",comidas);
		Recetario.inadecuados.add(celiaco);
		
		
		 Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 assertFalse(receta.getInadecuados().contains(celiaco));
	}
	
	
	@Test
	public void testNoInadecuadoParaDiabetico() {
		List<String> comidas= new ArrayList<String>();
		Diabetico diabetico = new Diabetico("diabetico",comidas);
		Recetario.inadecuados.add(diabetico);
		
		
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
		List<String> conjuntoDeComidas = new ArrayList<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Carne");
		conjuntoDeComidas.add("Pollo");
		
		
		assertTrue(receta.contieneAlguna(conjuntoDeComidas));
		
			
	}
		
	
	@Test
	public void testRecetaNoContieneAlgunaComidaDeUnaLista()
	{
		Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		List<String> conjuntoDeComidas = new ArrayList<String>();
		
		conjuntoDeComidas.add("Miel");
		conjuntoDeComidas.add("Pescado");
		conjuntoDeComidas.add("Pollo");
		
		
		assertFalse(receta.contieneAlguna(conjuntoDeComidas));
		
		
		
		
		
	}
	
	

	
	

}
