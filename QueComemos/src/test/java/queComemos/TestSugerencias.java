package queComemos;

import static org.junit.Assert.*;
import interfaces.ObservadorI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderReceta.RecetaBuilder;
import builderReceta.RecetaGenerica;
import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioSinValidacion;
import receta.Ingrediente;
import receta.Preparacion;
import receta.Receta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;
import usuario.Grupo;
import usuario.PreferenciaAlimenticia;
import usuario.Usuario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.CondicionPreexistente;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestSugerencias {
	
	private Usuario usr ;
	private Receta receta ;
	private List<Usuario> usuariosGrupo;

	@Before
	public void setUp() throws Exception {
		
		RepoUsuarios.inadecuados= new ArrayList<CondicionPreexistente>();
		Recetario.recetas = new ArrayList<Receta>();
		Recetario.observadores = new ArrayList<ObservadorI>();

		RecetaBuilder constructorReceta = new RecetaGenerica();
		constructorReceta.nombre("CarneAlHorno");
		constructorReceta.calorias(1524.);
		constructorReceta.instruccionesAgregar("Preparar");
		constructorReceta.instruccionesAgregar("Revolver");
		constructorReceta.instruccionesAgregar("Hornear");
		constructorReceta.ingredienteAgregar("Azucar", "grs", 150.);
		constructorReceta.ingredienteAgregar("papa", "kg", 3.);
		constructorReceta.ingredienteAgregar("Carne", "grs", 2.);
		constructorReceta.condimentoAgregar("Sal", "grs", 100.);
		constructorReceta.condimentoAgregar("Mayonesa", "grs", 100.);
		constructorReceta.temporada("Verano");
		constructorReceta.dificultad("Baja");
		
			
		  receta = constructorReceta.crearReceta();
	
		
		 
		 UsuarioBuilder constructorUsuario = new UsuarioSinValidacion();
		 constructorUsuario.nombre("Matias");
		 constructorUsuario.sexo("masculino");
		 constructorUsuario.rutina("Leve");
		 constructorUsuario.peso(70.0);
		 constructorUsuario.estatura(1.75);
		 constructorUsuario.leGusta("Fruta");
		 constructorUsuario.leGusta("Carne");
		 constructorUsuario.leGusta("Pasta");
		 constructorUsuario.leDisgusta("Verduras");
		 
		usr = 	constructorUsuario.crearUsuario();

		
		
		
		 UsuarioBuilder otroConstructorUsuario = new UsuarioSinValidacion();
		 otroConstructorUsuario.nombre("Juan");
		 otroConstructorUsuario.sexo("Masculino");
		 otroConstructorUsuario.rutina("Leve");
		 otroConstructorUsuario.peso(60.0);
		 otroConstructorUsuario.estatura(1.7);
		 otroConstructorUsuario.leGusta("Carne");
		 otroConstructorUsuario.leGusta("pescado");
		 otroConstructorUsuario.leDisgusta("cucaracha");
		
		Usuario usuario2 =  otroConstructorUsuario.crearUsuario(); 
		
		
		 UsuarioBuilder otroConstructorUsuario3 = new UsuarioSinValidacion();
		 otroConstructorUsuario3.nombre("Juan");
		 otroConstructorUsuario3.sexo("Masculino");
		 otroConstructorUsuario3.rutina("Leve");
		 otroConstructorUsuario3.peso(60.0);
		 otroConstructorUsuario3.estatura(1.7);
		 otroConstructorUsuario3.leGusta("Carne");
		 otroConstructorUsuario3.leDisgusta("pescado");
		 		
		Usuario usuario3 =  otroConstructorUsuario.crearUsuario(); 
		
		

		
		usuariosGrupo = new ArrayList<Usuario>();
		usuariosGrupo.add(usr);
		usuariosGrupo.add(usuario2);
		usuariosGrupo.add(usuario3);
		
		
		
		
		
		
		
	
	}

	@Test
	public void testNoPuedeSugerirCarneAlHornoAUsuario() {

		
		assertTrue(usr.esAdecuadaLaReceta(receta));
	}
	
		
	@Test
	public void testPuedeSugerirCarneAlHornoAUnGrupoConUnoDiabetico() {
		
	
		List<String> comidasGusta= new ArrayList<String>();
		comidasGusta.add("Carne");
		List<String> comidasNoGusta= new ArrayList<String>();
		comidasNoGusta.add("lavandina");
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(comidasGusta,comidasNoGusta);
		Grupo grupo = new Grupo("UnNombre" , usuariosGrupo, preferencia);	

		
		assertTrue(grupo.puedeSugerir(receta));
	}
	
}
