package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderReceta.RecetaBuilder;
import builderReceta.RecetaGenerica;
import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioMasGenerico;
import receta.Receta;
import repositorios.Consulta;
import repositorios.Recetario;
import repositorios.RepoUsuarios;
import usuario.Grupo;
import usuario.GustosSobreAlimentos;
import usuario.Usuario;

public class TestGrupo {
	
	private Grupo grupo;
	private List<Usuario> usuariosColeccion;
	
	@Before
	public void setUp() throws Exception {
		RepoUsuarios.inadecuados = new ArrayList<CondicionPreexistenteI>();
		
		UsuarioBuilder constructorDeUsuario = new UsuarioMasGenerico();
		constructorDeUsuario.nombre("Pedro");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.fechaNacimiento(	LocalDate.parse("1990-01-01"));
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		
		UsuarioBuilder otroConstructorDeUsuario = new UsuarioMasGenerico();
		otroConstructorDeUsuario.nombre("Juan");
		otroConstructorDeUsuario.sexo("Masculino");
		otroConstructorDeUsuario.fechaNacimiento(	LocalDate.parse("1990-01-01"));
		otroConstructorDeUsuario.peso(60.0);
		otroConstructorDeUsuario.estatura(1.7);
		otroConstructorDeUsuario.rutina("Leve");
				
				
		

		Usuario usuario1 = constructorDeUsuario.crearUsuario();
		Usuario usuario2 = otroConstructorDeUsuario.crearUsuario();
		
		
	
		usuariosColeccion = new ArrayList<Usuario>();
		usuariosColeccion.add(usuario1);
		usuariosColeccion.add(usuario2);


	}

	@Test
	public void testEstaPedroEnUsuariosColeccion() {
		
		assertTrue(usuariosColeccion.stream().anyMatch(usr->usr.getNombre().equals("Pedro")));
	}
	
	@Test
	public void testEstaJuanEnUsuariosColeccion() {
		
		assertTrue(usuariosColeccion.stream().anyMatch(usr->usr.getNombre().equals("Juan")));
	}
	
	@Test
	public void testEstaMatiasEnUsuariosColeccion() {
		
		assertFalse(usuariosColeccion.stream().anyMatch(usr->usr.getNombre().equals("Matias")));
	}
	
	@Test
	public void testEstaJuanEnElGrupo() {
	
		grupo = new Grupo("UnNombre" , usuariosColeccion, null);
		assertTrue(grupo.getUsuarios().stream().anyMatch(usr->usr.getNombre().equals("Juan")));
	}
	
	@Test
	public void testEstaPepoEnElGrupo() {
	
		grupo = new Grupo("UnNombre" , usuariosColeccion, null);
		
		UsuarioBuilder constructorDeUsuario = new UsuarioMasGenerico();
		constructorDeUsuario.nombre("Pepo");
		constructorDeUsuario.sexo("Masculino");
		constructorDeUsuario.fechaNacimiento(	LocalDate.parse("1990-01-01"));
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.7);
		constructorDeUsuario.rutina("Leve");
		
		
		
	
		grupo.agregarUsuario(constructorDeUsuario.crearUsuario());
		
		
		assertTrue(grupo.getUsuarios().stream().anyMatch(usr->usr.getNombre().equals("Pepo")));
	}
	
	@Test
	public void testNoEstaPepoEnElGrupo() {
	
		grupo = new Grupo("UnNombre" , usuariosColeccion, null);
		assertFalse(grupo.getUsuarios().stream().anyMatch(usr->usr.getNombre().equals("Pepo")));
	}
	
	@Test
	public void testEsAdecuadaReceta() {
	
		
		grupo = new Grupo("UnNombre" , usuariosColeccion, new GustosSobreAlimentos(new ArrayList<String>(),new ArrayList<String>()));
		RecetaBuilder constructorDeReceta = new RecetaGenerica();
		constructorDeReceta.nombre("papasAlHorno");
		constructorDeReceta.dificultad("facil");
		
		Receta receta = constructorDeReceta.crearReceta();
		
		assertTrue(grupo.esAdecuadaParaGrupo(receta));
		
		
		
		
	}
	
	@Test
	public void testLeGustaAlGrupo() {
	
		
		grupo = new Grupo("UnNombre" , usuariosColeccion, new GustosSobreAlimentos(new ArrayList<String>(),new ArrayList<String>()));
		RecetaBuilder constructorDeReceta = new RecetaGenerica();
		constructorDeReceta.nombre("papasAlHorno");
		constructorDeReceta.dificultad("facil");
		
		Receta receta = constructorDeReceta.crearReceta();
		
		assertFalse(grupo.leGustaAlGrupo(receta));
		
		
		
		
	}
	
	

	
	
	
	
	
			

}
