package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.FiltroI;
import interfaces.ProcesamientoI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import procesamientos.TomarDiezPrimeros;
import receta.Ingrediente;
import receta.Preparacion;
import receta.Receta;
import repositorios.Consulta;
import repositorios.Recetario;
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
		 
		    List<String>	instrucciones = new ArrayList<String>();
			List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
			List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
			List<Receta> subRecetas=new ArrayList<Receta>();
			

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
			
		 Preparacion    preparacion = new Preparacion(ingredientes,condimentos,instrucciones);

			
			
			 receta = new Receta("CarneAlHorno",1524.0,preparacion,"baja","verano",subRecetas,condiciones);
			Recetario.recetas.add(receta);
		 
		 
		 
		 
		 
		 //Filtros 
		 
		  filtros = new ArrayList<FiltroI>();
		  
		  
		  
		  
		  // Iniciar QueComemos
		  
			

			Recetario.inadecuados.add(diabetico);
			Recetario.inadecuados.add(celiaco);
			Recetario.inadecuados.add(hipertenso);
			Recetario.inadecuados.add(vegano);
			
			
			
			FiltroI filtroPorGusto = new FiltroParaUsuariosConSobrepeso();
			FiltroI filtroPreparacionCara = new FiltroPreparacionCara();
			FiltroI filtroSobrePeso= new FiltroParaUsuariosConSobrepeso();
			
			filtros.add(filtroSobrePeso);
			filtros.add(filtroPreparacionCara);
			filtros.add(filtroPorGusto);
			
			condiciones.add(hipertenso);
			condiciones.add(celiaco);
			
		 
		 
			 usr = new Usuario(2,"Juan","Masculino",LocalDate.parse("1994-08-05"),90.0,175.0,"Leve",preferenciaAlimenticia,condiciones,new ArrayList<Receta>());
		
		
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
		
	
		ProcesamientoI procesamiento = new TomarDiezPrimeros();
		
		Consulta consultaRecetas = new Consulta(usr);
		consultaRecetas.establecerProcesamientoPosterior(procesamiento);
		consultaRecetas.consultarRecetas();
		
		
		assertTrue(consultaRecetas.cantidadRecetasResultado() % 2  == 0);
		
		
	}


}
