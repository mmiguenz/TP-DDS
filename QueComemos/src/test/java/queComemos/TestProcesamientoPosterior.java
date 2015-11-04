package queComemos;

import static org.junit.Assert.*;
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
import usuario.PreferenciaAlimenticia;
import usuario.Usuario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.CondicionPreexistente;
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
		
		RepoUsuarios.getInstance().inadecuados= new ArrayList<CondicionPreexistente>();
		
		PreferenciaAlimenticia preferenciaAlimenticia; 
		 List<String> comidasProhibidas;
		 List<CondicionPreexistente> condiciones; 
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
				
		 preferenciaAlimenticia = new PreferenciaAlimenticia(comidasQueGusta,comidasQueDisgusta);
		
		
		// Condiciones Preexistentes
		
		
		condiciones = new ArrayList<CondicionPreexistente>();
		
		comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("pan");
		comidasProhibidas.add("salame");
		
		 celiaco = new Celiaco(null, "celiaco",comidasProhibidas);
		 hipertenso= new Hipertenso(null, "hipertenso",comidasProhibidas);
		 vegano = new Vegano(null, "vegano",new ArrayList<String>());
		 diabetico =  new Diabetico(null, "Diabetico",new ArrayList<String>());
		 
		
		 
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
			
			
			
			 receta = constructorReceta.crearReceta();
			Recetario.getInstance().agregarReceta(receta);
		 
		 
		 
		 
		 
		 //Filtros 
		 
		  filtros = new ArrayList<FiltroI>();
		  
		  
		  
		  
		  // Iniciar QueComemos
		  
			

		  RepoUsuarios.getInstance().inadecuados.add(diabetico);
		  RepoUsuarios.getInstance().inadecuados.add(celiaco);
		  RepoUsuarios.getInstance().inadecuados.add(hipertenso);
		  RepoUsuarios.getInstance().inadecuados.add(vegano);
			
			
			
			FiltroI filtroPorGusto = new FiltroParaUsuariosConSobrepeso();
			FiltroI filtroPreparacionCara = new FiltroPreparacionCara();
			FiltroI filtroSobrePeso= new FiltroParaUsuariosConSobrepeso();
			
			filtros.add(filtroSobrePeso);
			filtros.add(filtroPreparacionCara);
			filtros.add(filtroPorGusto);
			
			condiciones.add(hipertenso);
			condiciones.add(celiaco);
			
		 
		 
			 usr = new Usuario(null,"Juan","Masculino",LocalDate.parse("1994-08-05"),90.0,175.0,"Leve",preferenciaAlimenticia,false, condiciones,new ArrayList<Receta>(), null);
		
		
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
		
		
						
								
		
	assertTrue(consultaRecetas.cantidadRecetasResultado()  == 6);		
		
		
	}


}
