package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.tpAnual.queComemos.GustosSobreAlimentos;
import procesamientos.Ordenar;
import procesamientos.OrdenarPorCalorias;
import procesamientos.OrdenarPorNombre;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestOrdenamiento {
	
	private List<Receta> recetas ;

	@Before
	public void setUp() throws Exception {
		
		recetas = new ArrayList<Receta>();
		
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		List<String> comidasQueGustaUsrNoSaludable = new ArrayList<String>();
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();

		comidasQueGustaUsrSaludable.add("Fruta");
		comidasQueGustaUsrSaludable.add("Carne");
		comidasQueGustaUsrSaludable.add("Pasta");

		comidasQueGustaUsrNoSaludable.add("Fritos");
		comidasQueGustaUsrNoSaludable.add("Snacks");

		
		
		comidasQueDisgustaUsr.add("Verduras");

		GustosSobreAlimentos preferenciaAlimenticiaSaludable = new GustosSobreAlimentos(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		GustosSobreAlimentos preferenciaAlimenticiaNoSaludable = new GustosSobreAlimentos(
				comidasQueGustaUsrNoSaludable, comidasQueDisgustaUsr);

		List<String>comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Pan");

		
		List<String>	instrucciones = new ArrayList<String>();
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		List<Receta> subRecetas=new ArrayList<Receta>();
		
		List<CondicionPreexistenteI> inadecuados=new ArrayList<CondicionPreexistenteI>();
		List<String>comidasProhibidasH= new ArrayList<String>();
		comidasProhibidasH.add("caldo");
		
		
		Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
		Vegano vegano = new Vegano("vegano",comidasProhibidas);
		Hipertenso hipertenso = new Hipertenso("hipertenso",comidasProhibidasH);
		Diabetico diabetico = new Diabetico("diabetico",comidasProhibidas);
		
		
		
			
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
		
		     Preparacion preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
			 String dificultad = "Baja";
			 String temporada= "Verano";
		
		QueComemosApp.inicializar();
		
		
		
		
	
	recetas.add( new Receta("PolloConPapasA", 60.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta("CarneAlHornoA",4524.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta("CarneAlHornoB",1534.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta("PolloConPapasB", 80.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta("CarneAlHornoC",124.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta("PolloConPapasC", 550.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta("AA",1574.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta("PolloConPapasA", 21600.0,preparacion,dificultad,temporada,subRecetas,inadecuados));		
	
	
		
		
	}

	@Test
	public void testOrdernarPorCalorias() {
		
		CriterioOrdenamientoI criterio =  new OrdenarPorCalorias();
		ProcesamientoI orden = new Ordenar(criterio);
		
		List<Receta> recetasOrdenadas =  orden.procesar(recetas);
		
		assertTrue(recetasOrdenadas.stream().findFirst().get().getNombre().equals("PolloConPapasA"));
		
		
		
		
	}	@Test
	public void testOrdernarPorNombre() {
		
		CriterioOrdenamientoI criterio =  new OrdenarPorNombre();
		ProcesamientoI orden = new Ordenar(criterio);
		
		List<Receta> recetasOrdenadas =  orden.procesar(recetas);
	
		assertTrue(recetasOrdenadas.stream().findFirst().get().getNombre().equals("AA"));
		
		
		
		
	}
	

}
