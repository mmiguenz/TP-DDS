package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import usuario.Usuario;

import org.junit.Before;
import org.junit.Test;

import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioMasGenerico;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestCondicionesPreexistentes {
	
	private Celiaco celiaco;
	private Vegano vegano;
	private Hipertenso hipertenso;
	private Diabetico diabetico;
	private UsuarioBuilder constructorDeUsuario;
	

	@Before
	public void setUp() throws Exception {
		
		List<String>comidasProhibidas= new ArrayList<String>();
		comidasProhibidas.add("caldo");
		
		
		
		
		 celiaco = new Celiaco("celiaco",comidasProhibidas);
		 vegano = new Vegano("vegano",comidasProhibidas);
		 hipertenso = new Hipertenso("hipertenso",comidasProhibidas);
		 diabetico = new Diabetico("diabetico",comidasProhibidas);
				

		
		 constructorDeUsuario= new UsuarioMasGenerico();
		 constructorDeUsuario.nombre("juan");
		 constructorDeUsuario.fechaNacimiento(LocalDate.parse("1998-08-05"));
		 constructorDeUsuario.sexo("Masculino");
		 
		 
		 
		
	}

	@Test
	public void testDiabeticoSubsanaCondicion() {
		
		
		constructorDeUsuario.rutina("Activa");
		constructorDeUsuario.peso(60.0);
		constructorDeUsuario.estatura(1.75);

		Usuario usr = constructorDeUsuario.crearUsuario();
		
	 assertTrue(diabetico.subSanaCondicion(usr));
	
	 
	  
		
	}
	
	
	@Test
	public void testDiabeticoNoSubsanaCondicionFallaPorPeso() {
		
		
		constructorDeUsuario.rutina("Activa");
		constructorDeUsuario.peso(80.0);
		constructorDeUsuario.estatura(1.75);

		Usuario usr = constructorDeUsuario.crearUsuario();
			
		 assertFalse(diabetico.subSanaCondicion(usr));
	  
		
	}
	
	@Test
	public void testDiabeticoNoSubsanaCondicionFallaPorRutina() {
		
		
		
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.peso(80.0);
		constructorDeUsuario.estatura(1.75);

		Usuario usr = constructorDeUsuario.crearUsuario();
	 
		assertFalse(diabetico.subSanaCondicion(usr));
		 
	  
		
	}
	
	
	@Test
	public void testHipertensoSubsanaCondicion() {
		
		constructorDeUsuario.rutina("Intensiva");
		constructorDeUsuario.peso(50.0);
		constructorDeUsuario.estatura(1.75);

		Usuario usr = constructorDeUsuario.crearUsuario();
		
		assertTrue(hipertenso.subSanaCondicion(usr));
		 
	  
		
	}
	
	@Test
	public void testHipertensoNoSubsanaCondicionFallaPorRutina() {
		
		
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.peso(50.0);
		constructorDeUsuario.estatura(1.75);

		Usuario usr = constructorDeUsuario.crearUsuario();
	    assertFalse(hipertenso.subSanaCondicion(usr));
		 
	  
		
	}
	
	
	@Test
	public void testVeganoSubsanaCondicion() {
		
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.peso(50.0);
		constructorDeUsuario.estatura(1.75);
		constructorDeUsuario.leGusta("frutas");

		Usuario usr = constructorDeUsuario.crearUsuario();
		

	 assertTrue(vegano.subSanaCondicion(usr));
		 
	  
		
	}
	
	@Test
	public void testVeganoNoSubsanaCondicionFallaPorComidasQueGusta() {
		
		
		constructorDeUsuario.rutina("Leve");
		constructorDeUsuario.peso(50.0);
		constructorDeUsuario.estatura(1.75);

		Usuario usr = constructorDeUsuario.crearUsuario();
	 
		assertFalse(vegano.subSanaCondicion(usr));
		 
	  
		
	}
	
	
	
	
	
	
	


}
