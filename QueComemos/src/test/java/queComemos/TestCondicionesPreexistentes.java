package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import usuario.GustosSobreAlimentos;
import usuario.Usuario;

import org.junit.Before;
import org.junit.Test;

import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestCondicionesPreexistentes {
	
	private Celiaco celiaco;
	private Vegano vegano;
	private Hipertenso hipertenso;
	private Diabetico diabetico;
	

	@Before
	public void setUp() throws Exception {
		
		List<String>comidasProhibidas= new ArrayList<String>();
		comidasProhibidas.add("caldo");
		
		
		
		
		 celiaco = new Celiaco("celiaco",comidasProhibidas);
		 vegano = new Vegano("vegano",comidasProhibidas);
		 hipertenso = new Hipertenso("hipertenso",comidasProhibidas);
		 diabetico = new Diabetico("diabetico",comidasProhibidas);
				

		
		
	}

	@Test
	public void testDiabeticoSubsanaCondicion() {
		
		
	 Usuario usr =new Usuario(3,"Juan", "Masculino",
				LocalDate.parse("1998-08-05"), 60.0, 1.75, "Activa", null, null,
				null);
	 
	 assertTrue(diabetico.subSanaCondicion(usr));
	
	 
	  
		
	}
	
	
	@Test
	public void testDiabeticoNoSubsanaCondicionFallaPorPeso() {
		
		
	 Usuario usr =new Usuario(3,"Juan", "Masculino",
				LocalDate.parse("1998-08-05"), 80.0, 1.75, "Activa", null, null,
				null);
	 
	 assertFalse(diabetico.subSanaCondicion(usr));
	
	 
	  
		
	}
	
	@Test
	public void testDiabeticoNoSubsanaCondicionFallaPorRutina() {
		
		
	 Usuario usr =new Usuario(3,"Juan", "Masculino",
				LocalDate.parse("1998-08-05"), 50.0, 1.75, "Leve", null, null,
				null);
	 
	 assertFalse(diabetico.subSanaCondicion(usr));
		 
	  
		
	}
	
	
	@Test
	public void testHipertensoSubsanaCondicion() {
		
		
	 Usuario usr =new Usuario(3,"Juan", "Masculino",
				LocalDate.parse("1998-08-05"), 50.0, 1.75, "Intensiva", null, null,
				null);
	 
	 assertTrue(hipertenso.subSanaCondicion(usr));
		 
	  
		
	}
	
	@Test
	public void testHipertensoNoSubsanaCondicionFallaPorRutina() {
		
		
	 Usuario usr =new Usuario(3,"Juan", "Masculino",
				LocalDate.parse("1998-08-05"), 50.0, 1.75, "Leve", null, null,
				null);
	 
	 assertFalse(hipertenso.subSanaCondicion(usr));
		 
	  
		
	}
	
	
	@Test
	public void testVeganoSubsanaCondicion() {
		
		List<String> comidasQueGusta  = new ArrayList<>();
		List<String> comidasQueDisgusta  = new ArrayList<>();
		
		comidasQueGusta.add("fruta");
		
		GustosSobreAlimentos preferenciaAlimenticia = new GustosSobreAlimentos(comidasQueGusta,comidasQueDisgusta);
		
		
		
		
	 Usuario usr =new Usuario(3,"Juan", "Masculino",
				LocalDate.parse("1998-08-05"), 50.0, 1.75, "Leve", preferenciaAlimenticia, null,
				null);
	 
	 assertFalse(vegano.subSanaCondicion(usr));
		 
	  
		
	}
	
	@Test
	public void testVeganoNoSubsanaCondicionFallaPorComidasQueGusta() {
		
		
		List<String> comidasQueGusta  = new ArrayList<>();
		List<String> comidasQueDisgusta  = new ArrayList<>();
		
		GustosSobreAlimentos preferenciaAlimenticia = new GustosSobreAlimentos(comidasQueGusta,comidasQueDisgusta);
		
		
		
		
	 Usuario usr =new Usuario(3,"Juan", "Masculino",
				LocalDate.parse("1998-08-05"), 50.0, 1.75, "Leve", preferenciaAlimenticia, null,
				null);
	 
	 assertFalse(vegano.subSanaCondicion(usr));
		 
	  
		
	}
	
	
	
	
	
	
	


}
