package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioParaAprobacionDeSolicitudes;
import receta.Receta;
import repositorios.RepoUsuarios;
import usuario.PreferenciaAlimenticia;
import usuario.Usuario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.CondicionPreexistente;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestRepoUsuarios extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	private  List<Usuario> usuarios; 
	private CondicionPreexistente celiaco;
	private CondicionPreexistente vegano;
	private CondicionPreexistente hipertenso;
	private CondicionPreexistente diabetico;

	@Before
	public void setUp() throws Exception {	
		
		RepoUsuarios.repoUsuarios=null;
				
		celiaco = RepoUsuarios.getInstance().obtenerCondicion("celiaco");
		 hipertenso=RepoUsuarios.getInstance().obtenerCondicion("hipertenso");
		 vegano = RepoUsuarios.getInstance().obtenerCondicion("vegano");
		 diabetico = RepoUsuarios.getInstance().obtenerCondicion("diabetico");
		 
		 
		 
		
		
		 usuarios = new ArrayList<Usuario>();

		UsuarioBuilder usr1 = new UsuarioParaAprobacionDeSolicitudes("martin");
		
		UsuarioBuilder usr2 = new UsuarioParaAprobacionDeSolicitudes("pablo");

	
		UsuarioBuilder usr3 = new UsuarioParaAprobacionDeSolicitudes("analia");
	
		
		UsuarioBuilder usr4 = new UsuarioParaAprobacionDeSolicitudes("juana");

		UsuarioBuilder usr5 = new UsuarioParaAprobacionDeSolicitudes("juan");
	
		
		UsuarioBuilder usr6 = new UsuarioParaAprobacionDeSolicitudes("sebastian");

		
		UsuarioBuilder usr7 = new UsuarioParaAprobacionDeSolicitudes("matias");

		
		UsuarioBuilder usr8 = new UsuarioParaAprobacionDeSolicitudes("matias");
		
		UsuarioBuilder usr9 = new UsuarioParaAprobacionDeSolicitudes("matias");
		
	
		usuarios.add(usr1.crearUsuario());
		usuarios.add(usr2.crearUsuario());
		usuarios.add(usr3.crearUsuario());
		usuarios.add(usr4.crearUsuario());
		usuarios.add(usr5.crearUsuario());
		usuarios.add(usr6.crearUsuario());
		usuarios.add(usr7.crearUsuario());
		usuarios.add(usr8.crearUsuario());
		usuarios.add(usr9.crearUsuario());
		
		
		
	}
	
	

	@Test
	public void testRepoUsuarioAddUnoSolo() {
		
		RepoUsuarios.repoUsuarios=null;
			
		RepoUsuarios.getInstance().add(usuarios.get(0));
		assertEquals(RepoUsuarios.getInstance().listarTodos().size(),1);
		
		
		
	}
	
	@Test
	public void testRepoUsuarioAddDos() {
		
			
		RepoUsuarios.getInstance().add(usuarios.get(0));
		RepoUsuarios.getInstance().add(usuarios.get(1));
		assertEquals(RepoUsuarios.getInstance().listarTodos().size(),2);
		
		
		
	}
	
	
	@Test
	public void testRepoUsuarioRemoveUltimo() {
		
			
		RepoUsuarios.getInstance().add(usuarios.get(0));
		RepoUsuarios.getInstance().add(usuarios.get(1));
		RepoUsuarios.getInstance().add(usuarios.get(2));
		
		RepoUsuarios.getInstance().remove(usuarios.get(2));
		
		assertEquals(RepoUsuarios.getInstance().listarTodos().size(),3);
		
		
		
	}
	

	
	
	@Test
	public void testGetUsuario()
	{
		
		PreferenciaAlimenticia preferencia = new PreferenciaAlimenticia(new ArrayList<String>(),new ArrayList<String>());
		
		
		List<CondicionPreexistente> condiciones1 = new ArrayList<>();
		
		
		condiciones1.add(vegano);
		condiciones1.add(hipertenso);
		
		
		
		//RepoUsuarios.usuarios.addAll(usuarios);
			
		Usuario usr8 = new Usuario(null,"matiasM", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, false, condiciones1, new ArrayList<Receta>(), null);
		RepoUsuarios.getInstance().add(usr8);
		
		
		Usuario usrSearch = new Usuario(null,"matiasM", null, null, null, null, null, null, false, null, null, null);
		
		
		 assertTrue(RepoUsuarios.getInstance().get(usrSearch).equals(usr8));
		 
		 
		
		
	}
	
	
	@Test
	public void testListUsuariosPorNombre()
	{
		
		//RepoUsuarios.usuarios.addAll(usuarios);
		
		Usuario usrSearch = new Usuario(null,"matias", null, null, null, null, null, null, false, null, null, null);
		
		
		assertEquals(RepoUsuarios.getInstance().list(usrSearch).size(),0);
		
		
		
		
	}
	
	
	@Test
	public void testListUsuariosPorNombreYCondiciones()
	{
		RepoUsuarios.repoUsuarios=null;
		
		usuarios = new ArrayList<Usuario>();
		
		PreferenciaAlimenticia preferencia = new PreferenciaAlimenticia(new ArrayList<String>(),new ArrayList<String>());
		
		
		List<CondicionPreexistente> condiciones1 = new ArrayList<>();

		
		condiciones1.add(vegano);
		
		
		Usuario usr1 = new Usuario(null,"martin", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, false, condiciones1, new ArrayList<Receta>(), null);

		
		usuarios.add(usr1);
		
		//RepoUsuarios.usuarios.addAll(usuarios);
		
		
		
		Usuario usrSearch = new Usuario(null,"martin", null, null, null, null, null, null, false, condiciones1, null, null);
		
		
		assertEquals(RepoUsuarios.getInstance().list(usrSearch).size(),0);
		
		
		
		
	}

	
	

	
	
	
	
	
	
	
	
	
	
	

}
