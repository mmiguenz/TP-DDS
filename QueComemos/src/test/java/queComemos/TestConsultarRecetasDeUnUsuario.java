package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.FiltroI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
import filtros.FiltroRechazaTodo;

public class TestConsultarRecetasDeUnUsuario {
	
	private	GustosSobreAlimentos preferenciaAlimenticia; 
	private List<String> comidasProhibidas;
	private List<CondicionPreexistenteI> condiciones; 
	private Celiaco celiaco;
	private Hipertenso hipertenso;
	private Vegano vegano;
	private Diabetico diabetico;
	private  List<FiltroI> filtros; 
	private Receta receta;
	private FiltroPreparacionCara filtroPreparacionCara;
	private FiltroParaUsuariosConSobrepeso filtroSobrePeso;
	private FiltroRechazaTodo filtroRechazaTodo;
	private Usuario usr;
	
	

	@Before
	public void setUp() throws Exception {
		

	// Preferencia Alimenticia
		
		List<String> comidasQueGusta = new ArrayList<String>();
		List<String> comidasQueDisgusta = new ArrayList<String>();
		comidasQueGusta.add("pollo");
		comidasQueGusta.add("carne");
		comidasQueDisgusta.add("tomate");
		comidasQueDisgusta.add("pescado");
		
		GustosSobreAlimentos preferenciaAlimenticia = new GustosSobreAlimentos(comidasQueGusta,comidasQueDisgusta);
		
		
		// Condiciones Preexistentes
		
		
		condiciones = new ArrayList<CondicionPreexistenteI>();
		
		comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("pan");
		comidasProhibidas.add("salame");
		
		 celiaco = new Celiaco("celiaco",comidasProhibidas);
		 hipertenso= new Hipertenso("hipertenso",comidasProhibidas);
		 vegano = new Vegano("vegano",new ArrayList<String>());
		 diabetico =  new Diabetico("Diabetico",new ArrayList<String>());
		 
		
		  // Iniciar QueComemos
		  
		 
//			Recetario.inicializar();
			Recetario.inadecuados.add(diabetico);
			Recetario.inadecuados.add(celiaco);
			Recetario.inadecuados.add(hipertenso);
			Recetario.inadecuados.add(vegano);
		 
		 
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
    		
    		
    		
    		condiciones.add(hipertenso);
    		condiciones.add(celiaco);
    		
    		
    		 usr = new Usuario(3,"Juan","Masculino",LocalDate.parse("1994-08-05"),90.0,175.0,"Leve",preferenciaAlimenticia,condiciones,new ArrayList<Receta>());
			
	
		 
		 
		 //Filtros 
		 
    		
    		 filtroPreparacionCara = new FiltroPreparacionCara();
    		 filtroSobrePeso= new FiltroParaUsuariosConSobrepeso();
    		 filtroRechazaTodo = new FiltroRechazaTodo();
    		 

	}

	@Test
	public void testConsultaSobreRecetasVisiblesDeUsuario() {
			
		

		Consulta consultaRecetas = new Consulta(usr);
		consultaRecetas.agregarFiltro(filtroSobrePeso);
		consultaRecetas.consultarRecetas();
		
	
		
		assertTrue(consultaRecetas.obtenerResultadoConsulta().contains(receta));
		
		
		

	}
	
	@Test
	public void tesConsultaRecetasDeUsuarioUsoFiltroRechazaTodo()
	{
		
			
		
		Consulta consultaRecetas = new Consulta(usr);
		consultaRecetas.agregarFiltro(filtroRechazaTodo);
		
		consultaRecetas.consultarRecetas();
		
			
		assertTrue(consultaRecetas.obtenerResultadoConsulta().isEmpty());
		
		
		
		
	}
	

	

}
