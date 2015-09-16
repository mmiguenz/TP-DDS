package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioParaAprobacionDeSolicitudes;
import receta.Receta;
import repositorios.RepoUsuarios;
import usuario.GustosSobreAlimentos;
import usuario.Usuario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestRepoUsuarios {
	
	private  List<Usuario> usuarios; 
	

	@Before
	public void setUp() throws Exception {
		
		
		RepoUsuarios.usuarios= new ArrayList<Usuario>();
		
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
		
			
		RepoUsuarios.add(usuarios.get(0));
		assertEquals(RepoUsuarios.usuarios.size(),1);
		
		
		
	}
	
	@Test
	public void testRepoUsuarioAddDos() {
		
			
		RepoUsuarios.add(usuarios.get(0));
		RepoUsuarios.add(usuarios.get(1));
		assertEquals(RepoUsuarios.usuarios.size(),2);
		
		
		
	}
	
	
	@Test
	public void testRepoUsuarioRemoveUltimo() {
		
			
		RepoUsuarios.add(usuarios.get(0));
		RepoUsuarios.add(usuarios.get(1));
		RepoUsuarios.add(usuarios.get(2));
		
		RepoUsuarios.remove(usuarios.get(2));
		
		assertEquals(RepoUsuarios.usuarios.size(),2);
		
		
		
	}
	
	
	@Test
	public void testRepoUsuarioUpdateCambioNombre() {
		

		GustosSobreAlimentos preferencia = new GustosSobreAlimentos(new ArrayList<String>(),new ArrayList<String>());

		List<CondicionPreexistenteI> condiciones3 = new ArrayList<>();
		
		CondicionPreexistenteI celiaco = new Celiaco("celiaco",new ArrayList<String>());
		CondicionPreexistenteI diabetico = new Diabetico("diabetico",new ArrayList<String>());
		CondicionPreexistenteI vegano = new Vegano("vegano",new ArrayList<String>());
		CondicionPreexistenteI hipertenso = new Hipertenso("Hipertenso",new ArrayList<String>());
		
	
		condiciones3.add(celiaco);
		condiciones3.add(diabetico);
		condiciones3.add(vegano);
		condiciones3.add(hipertenso);
		
		
		Usuario usr =  new Usuario(1,"matias", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones3, new ArrayList<Receta>());
		
		Usuario usrUpdate =  new Usuario(1,"juan", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones3, new ArrayList<Receta>());
		
		
		
		RepoUsuarios.add(usr);
		
		RepoUsuarios.update(usrUpdate);
	
	
		
		assertTrue(RepoUsuarios.usuarios.get(0).getNombre().equals("juan") && RepoUsuarios.usuarios.get(0).getUsuarioID().equals(usr.getUsuarioID()) );
		
		
		
	}
	
	
	@Test
	public void testGetUsuario()
	{
		
		GustosSobreAlimentos preferencia = new GustosSobreAlimentos(new ArrayList<String>(),new ArrayList<String>());
		
	
		CondicionPreexistenteI vegano = new Vegano("vegano",new ArrayList<String>());
		CondicionPreexistenteI hipertenso = new Hipertenso("Hipertenso",new ArrayList<String>());
		
		List<CondicionPreexistenteI> condiciones1 = new ArrayList<>();
		
		
		condiciones1.add(vegano);
		condiciones1.add(hipertenso);
		
		
		
		RepoUsuarios.usuarios.addAll(usuarios);
			
		Usuario usr8 = new Usuario(7,"matiasM", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones1, new ArrayList<Receta>());
		RepoUsuarios.add(usr8);
		
		
		Usuario usrSearch = new Usuario(1,"matiasM", null, null, null, null, null, null, null, null);
		
		
		 assertTrue(RepoUsuarios.get(usrSearch).equals(usr8));
		 
		 
		
		
	}
	
	
	@Test
	public void testListUsuariosPorNombre()
	{
		
		RepoUsuarios.usuarios.addAll(usuarios);
		
		Usuario usrSearch = new Usuario(1,"matias", null, null, null, null, null, null, null, null);
		
		
		assertEquals(RepoUsuarios.list(usrSearch).size(),3);
		
		
		
		
	}
	
	
	@Test
	public void testListUsuariosPorNombreYCondiciones()
	{
		
		RepoUsuarios.usuarios= new ArrayList<Usuario>();
		usuarios = new ArrayList<Usuario>();
		
		GustosSobreAlimentos preferencia = new GustosSobreAlimentos(new ArrayList<String>(),new ArrayList<String>());
		
		
		

		CondicionPreexistenteI vegano = new Vegano("vegano",new ArrayList<String>());

		List<CondicionPreexistenteI> condiciones1 = new ArrayList<>();

		
		condiciones1.add(vegano);
		
		
		Usuario usr1 = new Usuario(1,"martin", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones1, new ArrayList<Receta>());

		
		usuarios.add(usr1);
		
		RepoUsuarios.usuarios.addAll(usuarios);
		
		
		
		Usuario usrSearch = new Usuario(1,"martin", null, null, null, null, null, null, condiciones1, null);
		
		
		assertEquals(RepoUsuarios.list(usrSearch).size(),1);
		
		
		
		
	}
	

	
	
	
	
	
	
	
	
	
	
	

}
