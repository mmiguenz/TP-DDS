package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.FiltroI;
import interfaces.ObservadorI;
import interfaces.ProcesamientoI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderReceta.RecetaBuilder;
import builderReceta.RecetaGenerica;
import procesamientos.ConsiderarSiEsPar;
import procesamientos.TomarDiezPrimeros;
import receta.Ingrediente;
import receta.Preparacion;
import receta.Receta;
import repositorios.Consulta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;
import usuario.GustosSobreAlimentos;
import usuario.Usuario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import filtros.FiltroParaUsuariosConSobrepeso;
import filtros.FiltroPreparacionCara;

public class TestProcesamientoPosterior {
	

	private  List<FiltroI> filtros; 
	private Receta receta;
	private Usuario usr;
	
	

	@Before
	public void setUp() throws Exception {
		
		RepoUsuarios.inadecuados= new ArrayList<CondicionPreexistenteI>();
		Recetario.recetas = new ArrayList<Receta>();
		Recetario.observadores = new ArrayList<ObservadorI>();
		
		GustosSobreAlimentos preferenciaAlimenticia; 
		 List<String> comidasProhibidas;
		 List<CondicionPreexistenteI> condiciones; 
		 Celiaco celiaco;
		 Hipertenso hipertenso;
		 Vegano vegano;
		 Diabetico diabetico;
		
		
		// Preferencia Alimenticia
		
		List<String> comidasQueGusta = new ArrayList<String>();
		List<String> comidasQueDisgusta = new ArrayList<String>();
		comidasQueGusta.add("pollo");
		comidasQueGusta.add("carne");
		comidasQueDisgusta.add("tomate");
		comidasQueDisgusta.add("pescado");
				
		 preferenciaAlimenticia = new GustosSobreAlimentos(comidasQueGusta,comidasQueDisgusta);
		
		
		// Condiciones Preexistentes
		
		
		condiciones = new ArrayList<CondicionPreexistenteI>();
		
		comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("pan");
		comidasProhibidas.add("salame");
		
		 celiaco = new Celiaco("celiaco",comidasProhibidas);
		 hipertenso= new Hipertenso("hipertenso",comidasProhibidas);
		 vegano = new Vegano("vegano",new ArrayList<String>());
		 diabetico =  new Diabetico("Diabetico",new ArrayList<String>());
		 
		
		 
//		 Recetario.inicializar();
		 
		 
		 // Crear Receta 
		 
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
			
			
			Recetario.recetas = new ArrayList<Receta>();
			 receta = constructorReceta.crearReceta();
			Recetario.recetas.add(receta);
		 
		 
		 
		 
		 
		 //Filtros 
		 
		  filtros = new ArrayList<FiltroI>();
		  
		  
		  
		  
		  // Iniciar QueComemos
		  
			

		  RepoUsuarios.inadecuados.add(diabetico);
		  RepoUsuarios.inadecuados.add(celiaco);
		  RepoUsuarios.inadecuados.add(hipertenso);
		  RepoUsuarios.inadecuados.add(vegano);
			
			
			
			FiltroI filtroPorGusto = new FiltroParaUsuariosConSobrepeso();
			FiltroI filtroPreparacionCara = new FiltroPreparacionCara();
			FiltroI filtroSobrePeso= new FiltroParaUsuariosConSobrepeso();
			
			filtros.add(filtroSobrePeso);
			filtros.add(filtroPreparacionCara);
			filtros.add(filtroPorGusto);
			
			condiciones.add(hipertenso);
			condiciones.add(celiaco);
			
		 
		 
			 usr = new Usuario((long) 2,"Juan","Masculino",LocalDate.parse("1994-08-05"),90.0,175.0,"Leve",preferenciaAlimenticia,condiciones,new ArrayList<Receta>());
		
		
	}

	@Test
	public void testProcesamientoPosteriorTomar10Primeros() {
		
			
	
							
		
		ProcesamientoI procesamiento = new TomarDiezPrimeros();
		
		Consulta consultaRecetas = new Consulta(usr);
		consultaRecetas.establecerProcesamientoPosterior(procesamiento);
		consultaRecetas.consultarRecetas();
		
		
		assertTrue(consultaRecetas.cantidadRecetasResultado() <= 10);
		
		
	}
	
	@Test
	public void testProcesamientoPosteriorDevuelveSiEsPar() {
		
	
		ProcesamientoI procesamiento = new ConsiderarSiEsPar();
		
		
		
		Consulta consultaRecetas = new Consulta(usr);
		consultaRecetas.establecerProcesamientoPosterior(procesamiento);
		consultaRecetas.consultarRecetas();
		
		
						
								
		
	assertTrue(consultaRecetas.cantidadRecetasResultado()  == 7);		
		
		
	}


}
