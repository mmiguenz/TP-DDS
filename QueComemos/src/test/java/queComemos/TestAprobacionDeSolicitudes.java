package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.CriterioAprobacionI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import manejoDeSolicitudes.CriteroApruebaSiEsJuan;
import manejoDeSolicitudes.ManejoDeSolicitudes;

import org.junit.Before;
import org.junit.Test;

import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioMasGenerico;
import builderUsuario.UsuarioParaAprobacionDeSolicitudes;
import receta.Receta;
import usuario.GustosSobreAlimentos;
import usuario.Usuario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestAprobacionDeSolicitudes {
	
	private List<Usuario> usuarios ;

	@Before
	public void setUp() throws Exception {
		
				
		 usuarios = new ArrayList<Usuario>();

		UsuarioBuilder usr1 = new UsuarioParaAprobacionDeSolicitudes("martin");
		
		UsuarioBuilder usr2 = new UsuarioParaAprobacionDeSolicitudes("pablo");

	
		UsuarioBuilder usr3 = new UsuarioParaAprobacionDeSolicitudes("analia");
	
		
		UsuarioBuilder usr4 = new UsuarioParaAprobacionDeSolicitudes("juana");

		UsuarioBuilder usr5 = new UsuarioParaAprobacionDeSolicitudes("juan");
	
		
		UsuarioBuilder usr6 = new UsuarioParaAprobacionDeSolicitudes("sebastian");

		
		UsuarioBuilder usr7 = new UsuarioParaAprobacionDeSolicitudes("matias");

		
		UsuarioBuilder usr8 = new UsuarioParaAprobacionDeSolicitudes("juan");
		
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
	public void testAprobacionesAutomaticas() {
		
		CriterioAprobacionI criterio = new CriteroApruebaSiEsJuan(); 
		ManejoDeSolicitudes administrador  =  new ManejoDeSolicitudes(usuarios,criterio);
		
		assertEquals(administrador.getMotivosRechazos().size(),6);
		
		
		
		
	}

	
	@Test
	public void testAprobacionesManuales() {
		
		GustosSobreAlimentos preferencia = new GustosSobreAlimentos(new ArrayList<String>(),new ArrayList<String>());
		
		List<Usuario> usuariosM = new ArrayList<>();
		
		usuariosM.addAll(usuarios);
		
		ManejoDeSolicitudes administrador  =  new ManejoDeSolicitudes(usuarios);		
		
			
		
		
		administrador.aprueba(usuariosM.get(0));
		administrador.aprueba(usuariosM.get(1));
		administrador.aprueba(usuariosM.get(2));
		administrador.desAprueba(usuariosM.get(3),"NoMeCaeBien");
		administrador.desAprueba(usuariosM.get(4),"NoMeCaeBien");
		administrador.aprueba(usuariosM.get(5));
		administrador.aprueba(usuariosM.get(6));
		administrador.desAprueba(usuariosM.get(7),"NoMeCaeBien");
		administrador.aprueba(usuariosM.get(8));		
		

		
		
		assertEquals(administrador.getMotivosRechazos().size(),3);
		
		
		
		
	}
	
	

}
