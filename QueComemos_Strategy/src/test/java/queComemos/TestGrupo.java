package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestGrupo {
	
	private Grupo grupo;
	private Set<Usuario> usuariosColeccion;
	
	@Before
	public void setUp() throws Exception {

		Usuario usuario1 = new Usuario("Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				null, null, null);
		Usuario usuario2 = new Usuario("Juan", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				null, null, null);

		usuariosColeccion = new HashSet<Usuario>();
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
		
		grupo = new Grupo("grupo 1", usuariosColeccion, null);
		assertTrue(grupo.getUsuarios().stream().anyMatch(usr->usr.getNombre().equals("Juan")));
	}
	

}
