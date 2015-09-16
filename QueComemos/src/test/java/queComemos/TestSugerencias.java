package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;
import interfaces.ObservadorI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builderUsuario.UsuarioBuilder;
import receta.Ingrediente;
import receta.Preparacion;
import receta.Receta;
import repositorios.Recetario;
import usuario.Grupo;
import usuario.GustosSobreAlimentos;
import usuario.Usuario;
import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestSugerencias {
	
	private Usuario usr ;
	private Receta receta ;
	private List<Usuario> usuariosGrupo;

	@Before
	public void setUp() throws Exception {
		
		Recetario.inadecuados= new ArrayList<CondicionPreexistenteI>();
		Recetario.recetas = new ArrayList<Receta>();
		Recetario.observadores = new ArrayList<ObservadorI>();

		List<String>	instrucciones = new ArrayList<String>();
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();

		
			
			instrucciones.add("Preparar");
			instrucciones.add("Revolver");
			instrucciones.add("Hornear");
			
			Ingrediente sal= new Ingrediente("Sal","grs",100);
			Ingrediente carne= new Ingrediente("Carne","kg",2);
			Ingrediente papas= new Ingrediente("papa","kg",3);
			Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
			Ingrediente azucar= new Ingrediente("Azucar","grs",150);
			
			ingredientes.add(azucar);
			ingredientes.add(carne);
			ingredientes.add(papas);
			condimentos.add(mayonesa);
			condimentos.add(sal);
						

			
			
		   Preparacion  preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
			 String dificultad = "Baja";
			 String temporada= "Verano";
			 
			 


			
		 receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,new ArrayList<Receta>(),null);
	
		
		 
		 UsuarioBuilder constructorUsuario = new UsuarioSinValidacion();
		 constructorUsuario.nombre("Matias");
		 constructorUsuario.sexo("masculino");
		 constructorUsuario.rutina("Leve");
		 constructorUsuario.peso(70.0);
		 constructorUsuario.estatura(1.75);
		 constructorUsuario.leGusta("Fruta");
		 constructorUsuario.leGusta("Carne");
		 constructorUsuario.leGusta("Pasta");
		 constructorUsuario.leDisgusta("Verduras");
		 
		usr = 	constructorUsuario.crearUsuario();

		
		
		
		 UsuarioBuilder otroConstructorUsuario = new UsuarioSinValidacion();
		 otroConstructorUsuario.nombre("Juan");
		 otroConstructorUsuario.sexo("Masculino");
		 otroConstructorUsuario.rutina("Leve");
		 otroConstructorUsuario.peso(60.0);
		 otroConstructorUsuario.estatura(1.7);
		 otroConstructorUsuario.leGusta("Carne");
		 otroConstructorUsuario.leGusta("pescado");
		 otroConstructorUsuario.leDisgusta("cucaracha");
		
		Usuario usuario2 =  otroConstructorUsuario.crearUsuario(); 
		
		
		 UsuarioBuilder otroConstructorUsuario3 = new UsuarioSinValidacion();
		 otroConstructorUsuario3.nombre("Juan");
		 otroConstructorUsuario3.sexo("Masculino");
		 otroConstructorUsuario3.rutina("Leve");
		 otroConstructorUsuario3.peso(60.0);
		 otroConstructorUsuario3.estatura(1.7);
		 otroConstructorUsuario3.leGusta("Carne");
		 otroConstructorUsuario3.leDisgusta("pescado");
		 		
		Usuario usuario3 =  otroConstructorUsuario.crearUsuario(); 
		
		

		
		usuariosGrupo = new ArrayList<Usuario>();
		usuariosGrupo.add(usr);
		usuariosGrupo.add(usuario2);
		usuariosGrupo.add(usuario3);
		
		
		
		
		
		
		
	
	}

	@Test
	public void testNoPuedeSugerirCarneAlHornoAUsuario() {

		
		assertTrue(usr.esAdecuadaLaReceta(receta));
	}
	
		
	@Test
	public void testPuedeSugerirCarneAlHornoAUnGrupoConUnoDiabetico() {
		
	
		List<String> comidasGusta= new ArrayList<String>();
		comidasGusta.add("Carne");
		List<String> comidasNoGusta= new ArrayList<String>();
		comidasNoGusta.add("lavandina");
		GustosSobreAlimentos preferencia=new GustosSobreAlimentos(comidasGusta,comidasNoGusta);
		Grupo grupo = new Grupo("UnNombre" , usuariosGrupo, preferencia);	

		
		assertTrue(grupo.puedeSugerir(receta));
	}
	
}
