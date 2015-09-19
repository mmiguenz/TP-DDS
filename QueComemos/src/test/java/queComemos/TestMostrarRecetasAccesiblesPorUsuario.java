package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.ObservadorI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderReceta.RecetaBuilder;
import builderReceta.RecetaGenerica;
import receta.Receta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;
import usuario.GustosSobreAlimentos;
import usuario.Usuario;
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
		Recetario.inadecuados= new ArrayList<CondicionPreexistenteI>();
		Recetario.recetas = new ArrayList<Receta>();
		Recetario.observadores = new ArrayList<ObservadorI>();
		
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		List<String> comidasQueGustaUsrNoSaludable = new ArrayList<String>();
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();

		comidasQueGustaUsrSaludable.add("Fruta");
		comidasQueGustaUsrSaludable.add("Carne");
		comidasQueGustaUsrSaludable.add("Pasta");

		comidasQueGustaUsrNoSaludable.add("Fritos");
		comidasQueGustaUsrNoSaludable.add("Snacks");

		
		
		comidasQueDisgustaUsr.add("Verduras");

		
		GustosSobreAlimentos preferenciaAlimenticiaNoSaludable = new GustosSobreAlimentos(comidasQueGustaUsrNoSaludable, comidasQueDisgustaUsr);

		List<String>comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Pan");

		
			List<CondicionPreexistenteI> inadecuados=new ArrayList<CondicionPreexistenteI>();
		List<String>comidasProhibidasH= new ArrayList<String>();
		comidasProhibidasH.add("caldo");
		
		
		Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
		Vegano vegano = new Vegano("vegano",comidasProhibidas);
		Hipertenso hipertenso = new Hipertenso("hipertenso",comidasProhibidasH);
		Diabetico diabetico = new Diabetico("diabetico",comidasProhibidas);
		
		
		RecetaBuilder constructorReceta = new RecetaGenerica();
		constructorReceta.nombre("CarneAlHorno");
		constructorReceta.calorias(1524.0);
		constructorReceta.ingredienteAgregar("Azucar", "grs", 150.);
		constructorReceta.ingredienteAgregar("Carne", "kg",2.);
		constructorReceta.ingredienteAgregar("papa", "kg", 3.);
		constructorReceta.condimentoAgregar("Sal", "grs", 100.);
		constructorReceta.condimentoAgregar("Mayonesa", "grs", 100.);
		constructorReceta.dificultad("baja");
		constructorReceta.temporada("vernano");
		
		receta2 = constructorReceta.crearReceta();
		
		constructorReceta.calorias(50.);
		constructorReceta.nombre("PolloConPapas");
		
		
		receta =constructorReceta.crearReceta();
		
		
		 usr = new Usuario (3,"juan","masculino",LocalDate.parse("2016-01-01"), 60.0, 1.7, "Leve",
					preferenciaAlimenticiaNoSaludable,new ArrayList<CondicionPreexistenteI>(),new ArrayList<Receta>());
		
		
		
		 
		 
		
		Recetario.recetas.add(receta);
		usr.getMisRecetas().add(receta2);

		
	}

	@Test
	public void testMostrarRecetasVisibles() {
	
		
		List<Receta> recetasVisibles = RepoUsuarios.mostrarRecetasAccesiblesPorUsuario(usr);
				
		
		assertTrue(recetasVisibles.contains(receta) && recetasVisibles.contains(receta2));
		
	}

}
