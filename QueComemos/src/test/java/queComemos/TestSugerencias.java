package queComemos;

import static org.junit.Assert.*;
import interfaces.CondicionPreexistenteI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import receta.Ingrediente;
import receta.Preparacion;
import receta.Receta;
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

		List<String>	instrucciones = new ArrayList<String>();
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
	
		List<CondicionPreexistenteI> inadecuados=new ArrayList<CondicionPreexistenteI>();
		List<String>comidasProhibidas= new ArrayList<String>();
		List<String>comidasProhibidasH= new ArrayList<String>();
		comidasProhibidasH.add("caldo");
		
		
		Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
		Vegano vegano = new Vegano("vegano",comidasProhibidas);
		Hipertenso hipertenso = new Hipertenso("hipertenso",comidasProhibidasH);
		Diabetico diabetico = new Diabetico("diabetico",comidasProhibidas);
		
//		QueComemosApp.inadecuados=new ArrayList<CondicionPreexistenteI>();

		
			
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
			 
			 
				List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
				List<String> comidasQueGustaUsrNoSaludable = new ArrayList<String>();
				List<String> comidasQueDisgustaUsr = new ArrayList<String>();

				comidasQueGustaUsrSaludable.add("Fruta");
				comidasQueGustaUsrSaludable.add("Carne");
				comidasQueGustaUsrSaludable.add("Pasta");

				comidasQueGustaUsrNoSaludable.add("Fritos");
				comidasQueGustaUsrNoSaludable.add("Snacks");

				
				
				comidasQueDisgustaUsr.add("Verduras");

			GustosSobreAlimentos	preferenciaAlimenticiaSaludable = new GustosSobreAlimentos(
						comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
			

			
		 receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,new ArrayList<Receta>() ,inadecuados);
	
		
		 
		 
		 
		usr = new Usuario(4,"Matias", "Masculino",
				LocalDate.parse("1994-08-05"), 70.0, 1.75, "Leve", preferenciaAlimenticiaSaludable, new ArrayList<CondicionPreexistenteI>(),
				null);
		
		
		
		/////////////
		
		
		List<String> comidasProhibidasDiabetico = new ArrayList<String>();
		comidasProhibidasDiabetico.add("Azucar");
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Diabetico("Diabetico", comidasProhibidasDiabetico));
		
		List<String> comidasGusta1= new ArrayList<String>();
		comidasGusta1.add("Carne");
		comidasGusta1.add("pollo");
		List<String> comidasNoGusta1= new ArrayList<String>();
		comidasNoGusta1.add("pescado");
		GustosSobreAlimentos preferencia1=new GustosSobreAlimentos(comidasGusta1,comidasNoGusta1);
		Usuario usuario1 = new Usuario(1,"Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia1, condiciones, new ArrayList<Receta>());
		
		List<String> comidasGusta2= new ArrayList<String>();
		comidasGusta2.add("Carne");
		comidasGusta2.add("pescado");
		List<String> comidasNoGusta2= new ArrayList<String>();
		comidasNoGusta2.add("cucaracha");
		GustosSobreAlimentos preferencia2=new GustosSobreAlimentos(comidasGusta2,comidasNoGusta2);
		Usuario usuario2 = new Usuario(2,"Juan", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia2, new ArrayList<CondicionPreexistenteI>(), new ArrayList<Receta>());
		
		List<String> comidasGusta3= new ArrayList<String>();
		comidasGusta3.add("pescado");
		List<String> comidasNoGusta3= new ArrayList<String>();
		comidasNoGusta3.add("Carne");
		GustosSobreAlimentos preferencia3=new GustosSobreAlimentos(comidasGusta3,comidasNoGusta3);
		Usuario usuario3 = new Usuario(3,"Pepo", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia3, new ArrayList<CondicionPreexistenteI>(), new ArrayList<Receta>());
		
		usuariosGrupo = new ArrayList<Usuario>();
		usuariosGrupo.add(usuario1);
		usuariosGrupo.add(usuario2);
		usuariosGrupo.add(usuario3);
		
		
		
		
		
		
		
	
	}

	@Test
	public void testNoPuedeSugerirCarneAlHornoAUsuario() {
//		QueComemosApp.inicializar();
//		QueComemosApp.usuarios.add(usr);
		
		

		
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
