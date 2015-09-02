package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestMostrarRecetasAccesiblesPorUsuario {
	
	private Usuario usr;
	private Receta receta;
	private Receta receta2;

	

	@Before
	public void setUp() throws Exception {
		QueComemosApp.inicializar();
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		List<String> comidasQueGustaUsrNoSaludable = new ArrayList<String>();
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();

		comidasQueGustaUsrSaludable.add("Fruta");
		comidasQueGustaUsrSaludable.add("Carne");
		comidasQueGustaUsrSaludable.add("Pasta");

		comidasQueGustaUsrNoSaludable.add("Fritos");
		comidasQueGustaUsrNoSaludable.add("Snacks");

		
		
		comidasQueDisgustaUsr.add("Verduras");

		PreferenciaAlimenticia preferenciaAlimenticiaSaludable = new PreferenciaAlimenticia(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		PreferenciaAlimenticia preferenciaAlimenticiaNoSaludable = new PreferenciaAlimenticia(
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
		
		
		 usr = new Usuario (3,"juan","masculino",LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
					preferenciaAlimenticiaNoSaludable,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());
		
		
		
		  receta = new Receta("PolloConPapas", 50.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		 receta2 = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		QueComemosApp.recetas.add(receta);
		usr.getMisRecetas().add(receta2);

		
	}

	@Test
	public void testMostrarRecetasVisibles() {
	
		
		List<Receta> recetasVisibles = QueComemosApp.mostrarRecetasAccesiblesPorUsuario(usr);
				
		
		assertTrue(recetasVisibles.contains(receta) && recetasVisibles.contains(receta2));
		
	}

}
