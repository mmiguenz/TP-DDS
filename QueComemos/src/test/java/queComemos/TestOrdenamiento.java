package queComemos;

import static org.junit.Assert.*;
import interfaces.CriterioOrdenamientoI;
import interfaces.ObservadorI;
import interfaces.ProcesamientoI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import procesamientos.Ordenar;
import procesamientos.OrdenarPorCalorias;
import procesamientos.OrdenarPorNombre;
import receta.Condimento;
import receta.Ingrediente;
import receta.Preparacion;
import receta.Receta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;
import usuario.PreferenciaAlimenticia;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.CondicionPreexistente;
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


		List<String>comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Pan");

		
		List<String>	instrucciones = new ArrayList<String>();
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Condimento>condimentos = new ArrayList<Condimento>();
		List<Receta> subRecetas=new ArrayList<Receta>();
		
		List<CondicionPreexistente> inadecuados=new ArrayList<CondicionPreexistente>();
		List<String>comidasProhibidasH= new ArrayList<String>();
		comidasProhibidasH.add("caldo");
		
		
		
			
			instrucciones.add("Preparar");
			instrucciones.add("Revolver");
			instrucciones.add("Hornear");
			
			Condimento sal= new Condimento("Sal","grs",100);
			Ingrediente carne= new Ingrediente("Carne","kg",2);
			Ingrediente papas= new Ingrediente("papa","kg",3);
			Condimento mayonesa= new Condimento("Mayonesa","grs",100);
			Ingrediente azucar= new Ingrediente("Azucar","grs",150);
			
			ingredientes.add(azucar);
			ingredientes.add(carne);
			ingredientes.add(papas);
			condimentos.add(mayonesa);
			condimentos.add(sal);
		
		     Preparacion preparacion = new Preparacion(null,ingredientes,condimentos,instrucciones);
			 String dificultad = "Baja";
			 String temporada= "Verano";
		
		
		
		
		
	
	recetas.add( new Receta(null, "PolloConPapasA", 60.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta(null, "CarneAlHornoA",4524.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta(null, "CarneAlHornoB",1534.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta(null, "PolloConPapasB", 80.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta(null, "CarneAlHornoC",124.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta(null, "PolloConPapasC", 550.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta(null, "AA",1574.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
	recetas.add( new Receta(null, "PolloConPapasA", 21600.0,preparacion,dificultad,temporada,subRecetas,inadecuados));		
	
	
		
		
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
