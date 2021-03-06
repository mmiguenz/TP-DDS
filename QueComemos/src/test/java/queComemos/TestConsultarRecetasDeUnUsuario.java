package queComemos;

import static org.junit.Assert.*;
import interfaces.FiltroI;
import interfaces.ObservadorI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import observadores.ObservadorVeganoConsultaRecetaDificil;

import org.junit.Before;
import org.junit.Test;

import builderReceta.RecetaBuilder;
import builderReceta.RecetaGenerica;
import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioMasGenerico;
import builderUsuario.UsuarioSinValidacion;
import receta.Receta;
import repositorios.Consulta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;
import usuario.Usuario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.CondicionPreexistente;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import filtros.FiltroParaUsuariosConSobrepeso;
import filtros.FiltroPreparacionCara;
import filtros.FiltroRechazaTodo;

public class TestConsultarRecetasDeUnUsuario {
	
	
	private List<String> comidasProhibidas;
	private Celiaco celiaco;
	private Hipertenso hipertenso;
	private Vegano vegano;
	private Diabetico diabetico;
	private Receta receta;
	private FiltroParaUsuariosConSobrepeso filtroSobrePeso;
	private FiltroRechazaTodo filtroRechazaTodo;
	private Usuario usr;
	private Usuario usrObeso;
	
	

	@Before
	public void setUp() throws Exception {
		
		
		
		
		
				
		UsuarioBuilder constructorDeUsuarioObeso = new UsuarioSinValidacion();
		constructorDeUsuarioObeso.nombre("juan");
		constructorDeUsuarioObeso.sexo("Masculino");
		constructorDeUsuarioObeso.fechaNacimiento(LocalDate.parse("1994-08-05"));
		constructorDeUsuarioObeso.rutina("Leve");
		constructorDeUsuarioObeso.peso(10000.0);
		constructorDeUsuarioObeso.estatura(1.0);
		constructorDeUsuarioObeso.leGusta("pollo");
		constructorDeUsuarioObeso.leGusta("carne");
		constructorDeUsuarioObeso.leDisgusta("tomate");
		constructorDeUsuarioObeso.leDisgusta("pescado");
		constructorDeUsuarioObeso.esHipertenso();
		constructorDeUsuarioObeso.esCeliaco();
		
		
		UsuarioBuilder constructorDeUsuario = new UsuarioSinValidacion();
		constructorDeUsuario.nombre("juan");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.fechaNacimiento(LocalDate.parse("1994-08-05"));
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.peso(1.0);
		constructorDeUsuario.estatura(175.0);
		constructorDeUsuario.leGusta("pollo");
		constructorDeUsuario.leGusta("carne");
		constructorDeUsuario.leDisgusta("tomate");
		constructorDeUsuario.leDisgusta("pescado");
		constructorDeUsuario.esHipertenso();
		constructorDeUsuario.esCeliaco();
		
		
		usr = constructorDeUsuario.crearUsuario();
		usrObeso = constructorDeUsuarioObeso.crearUsuario();
		
		
		

		
		// Condiciones Preexistentes
		
		
			 
		 
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
		 
    		
    		FiltroPreparacionCara filtroPreparacionCara = new FiltroPreparacionCara();
    		 filtroSobrePeso= new FiltroParaUsuariosConSobrepeso();
    		 filtroRechazaTodo = new FiltroRechazaTodo();
    		 

	}

	@Test
	public void testConsultaSobreRecetasVisiblesDeUsuario() {
			
		

		Consulta consultaRecetas = new Consulta(usr);
		consultaRecetas.agregarFiltro(filtroSobrePeso);
		consultaRecetas.consultarRecetas();
		
	
		
		assertFalse(consultaRecetas.obtenerResultadoConsulta().contains(receta));
		
		
		

	}
	
	@Test
	public void tesConsultaRecetasDeUsuarioUsoFiltroRechazaTodo()
	{
		
			
		
		Consulta consultaRecetas = new Consulta(usr);
		consultaRecetas.agregarFiltro(filtroRechazaTodo);
		
		consultaRecetas.consultarRecetas();
		
			
		assertTrue(consultaRecetas.obtenerResultadoConsulta().isEmpty());
		
		
		
		
	}
	
	
	@Test
	public void testConsultaSobreRecetasVisiblesDeUsuarioConSobrePeso() {
			
		

		Consulta consultaRecetas = new Consulta(usrObeso);
		consultaRecetas.agregarFiltro(filtroSobrePeso);
		consultaRecetas.consultarRecetas();
		
	
		
		assertFalse(consultaRecetas.obtenerResultadoConsulta().contains(receta));
		
		
		

	}
	
	
	
	@Test
	public void testVaganoConsultadoDificil() {
		
				 
		Recetario.getInstance().observadores.add(new ObservadorVeganoConsultaRecetaDificil());
		
		UsuarioBuilder constructorU = new UsuarioSinValidacion();
		constructorU.esVegano();
		
		RecetaBuilder constructorR = new RecetaGenerica();
		constructorR.dificultad("dificil");
		
		Receta unaReceta =  constructorR.crearReceta();
		Recetario.getInstance().agregarReceta(unaReceta);

		Consulta consultaRecetas = new Consulta(constructorU.crearUsuario());
		
		
		consultaRecetas.consultarRecetas();
		
	
		
		assertTrue(Recetario.getInstance().veganosConsultandoRecetasDificiles.size() == 1 );
		
		
		

	}
	


	

}
