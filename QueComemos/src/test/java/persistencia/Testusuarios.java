package persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioMasGenerico;
import builderUsuario.UsuarioSinValidacion;
import repositorios.RepoUsuariosPersistible;
import usuario.Usuario;



public class Testusuarios  extends AbstractPersistenceTest implements WithGlobalEntityManager {
	

		  @Before
		  public void setUp() {
			  
		  
		  }
		  

			@Test
			public void contextUp() {
				assertNotNull(entityManager());
			}


			@Test
			public void contextUpWithTransaction() throws Exception {
				withTransaction(() -> {});
			}
			
			
			@Test 
			public void testBuscarUsuario()
			{
				Usuario usuario = RepoUsuariosPersistible.getInstance().getUsuario(Long.parseLong("1"));
				
				
				
			}
			
			
			
			@Test 
			public void testAgregarUsuario()
			{
				UsuarioBuilder u = new UsuarioSinValidacion();
				u.esHipertenso();
				u.estatura(10.2);
				u.leDisgusta("pollo");
				u.leGusta("tomate");
				u.marcaFavoritas(true);
				u.nombre("juancito");
				u.rutina("Moderada");
				u.sexo("Masculino");
				u.peso(60.5);
				u.fechaNacimiento(LocalDate.parse("1994-01-01"));
				
				
				Usuario usuario = u.crearUsuario();
				
				RepoUsuariosPersistible.getInstance().agregarUsuario(usuario);
				
				
			}
			


		  
		  
		  
		  

}


