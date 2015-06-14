package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestGrupo {
	
	private Grupo grupo;
	private List<Usuario> usuariosColeccion;
	
	@Before
	public void setUp() throws Exception {

		Usuario usuario1 = new Usuario(3,"Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				null, null, null);
		Usuario usuario2 = new Usuario(4,"Juan", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				null, null, null);

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
		Usuario usuario3 = new Usuario(5,"Pepo", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				null, null, null);
		grupo.agregarUsuario(usuario3);
		assertTrue(grupo.getUsuarios().stream().anyMatch(usr->usr.getNombre().equals("Pepo")));
	}
	
	@Test
	public void testNoEstaPepoEnElGrupo() {
	
		grupo = new Grupo("UnNombre" , usuariosColeccion, null);
		assertFalse(grupo.getUsuarios().stream().anyMatch(usr->usr.getNombre().equals("Pepo")));
	}
	

}
