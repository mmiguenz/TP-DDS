package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioMasGenerico;
import repositorios.Consulta;
import usuario.Grupo;
import usuario.Usuario;

public class TestGrupo {
	
	private Grupo grupo;
	private List<Usuario> usuariosColeccion;
	
	@Before
	public void setUp() throws Exception {
		
		UsuarioBuilder constructorDeUsuario = new UsuarioMasGenerico();
		constructorDeUsuario.establecerNombre("Pedro");
		constructorDeUsuario.establecerSexo("Masculino");
		constructorDeUsuario.establecerFechaNacimiento(	LocalDate.parse("1990-01-01"));
		constructorDeUsuario.establecerPeso(60.0);
		constructorDeUsuario.establecerEstatura(1.7);
		constructorDeUsuario.establecerRutina("Leve");
		
		UsuarioBuilder otroConstructorDeUsuario = new UsuarioMasGenerico();
		otroConstructorDeUsuario.establecerNombre("Juan");
		otroConstructorDeUsuario.establecerSexo("Masculino");
		otroConstructorDeUsuario.establecerFechaNacimiento(	LocalDate.parse("1990-01-01"));
		otroConstructorDeUsuario.establecerPeso(60.0);
		otroConstructorDeUsuario.establecerEstatura(1.7);
		otroConstructorDeUsuario.establecerRutina("Leve");
				
				
		

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
		constructorDeUsuario.establecerNombre("Pepo");
		constructorDeUsuario.establecerSexo("Masculino");
		constructorDeUsuario.establecerFechaNacimiento(	LocalDate.parse("1990-01-01"));
		constructorDeUsuario.establecerPeso(60.0);
		constructorDeUsuario.establecerEstatura(1.7);
		constructorDeUsuario.establecerRutina("Leve");
		
		
		
	
		grupo.agregarUsuario(constructorDeUsuario.crearUsuario());
		
		
		assertTrue(grupo.getUsuarios().stream().anyMatch(usr->usr.getNombre().equals("Pepo")));
	}
	
	@Test
	public void testNoEstaPepoEnElGrupo() {
	
		grupo = new Grupo("UnNombre" , usuariosColeccion, null);
		assertFalse(grupo.getUsuarios().stream().anyMatch(usr->usr.getNombre().equals("Pepo")));
	}
	
	
	
			

}
