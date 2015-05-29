package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestMostrarRecetasAccesiblesPorUsuario {

	

	@Before
	public void setUp() throws Exception {

		
	}

	@Test
	public void test() {
		QueComemosApp.inicializar();
		Set<String> comidasQueGustaUsrSaludable = new HashSet<String>();
		Set<String> comidasQueGustaUsrNoSaludable = new HashSet<String>();
		Set<String> comidasQueDisgustaUsr = new HashSet<String>();

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

		Set<String>comidasProhibidas = new HashSet<String>();
		comidasProhibidas.add("Pan");

		
		Set<String>	instrucciones = new HashSet<String>();
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
		Set<Ingrediente>condimentos = new HashSet<Ingrediente>();
		Set<Receta> subRecetas=new HashSet<Receta>();
		
		Set<CondicionPreexistenteI> inadecuados=new HashSet<CondicionPreexistenteI>();
		Set<String>comidasProhibidasH= new HashSet<String>();
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
		
		
		Usuario usr = new Usuario ("juan","masculino",LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
					preferenciaAlimenticiaNoSaludable,new HashSet<CondicionPreexistenteI>(),new HashSet<Receta>());
		
		
		
		 Receta receta = new Receta("PolloConPapas", 50.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		Receta receta2 = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		QueComemosApp.recetas.add(receta);
		usr.getMisRecetas().add(receta2);
		
		Set<Receta> recetasVisibles = QueComemosApp.mostrarRecetasAccesiblesPorUsuario(usr);
		
		
		
		assertTrue(recetasVisibles.contains(receta) && recetasVisibles.contains(receta2));
		
	}

}
