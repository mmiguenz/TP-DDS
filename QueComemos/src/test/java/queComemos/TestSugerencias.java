package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestSugerencias {
	
	private Usuario usr ;
	private Receta receta ;

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
		
		QueComemosApp.inadecuados=new ArrayList<CondicionPreexistenteI>();

		
			
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

			PreferenciaAlimenticia	preferenciaAlimenticiaSaludable = new PreferenciaAlimenticia(
						comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
			

			
		 receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,new ArrayList<Receta>() ,inadecuados);
	
		
		usr = new Usuario("Matias", "Masculino",
				LocalDate.parse("1994-08-05"), 70.0, 1.75, "Leve", preferenciaAlimenticiaSaludable, new ArrayList<CondicionPreexistenteI>(),
				null);
		
		
	
	}

	@Test
	public void testNoPuedeSugerirCarneAlHornoAUsuario() {
		QueComemosApp.inicializar();
		QueComemosApp.usuarios.add(usr);
		QueComemosApp.recetas.add(receta);
		
		

		
		assertFalse(QueComemosApp.puedeSugerir(receta, usr));
	}
	
	/*@Test
	public void testPuedeSugerirCarneAlHornoAUnGrupoDeCarnivoros() {
		
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		
		List<String> comidasGusta1= new ArrayList<String>();
		comidasGusta1.add("Carne");
		comidasGusta1.add("pollo");
		List<String> comidasNoGusta1= new ArrayList<String>();
		comidasNoGusta1.add("pescado");
		PreferenciaAlimenticia preferencia1=new PreferenciaAlimenticia(comidasGusta1,comidasNoGusta1);
		Usuario usuario1 = new Usuario("Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia1, condiciones, null);
		
		List<String> comidasGusta2= new ArrayList<String>();
		comidasGusta2.add("Carne");
		comidasGusta2.add("pescado");
		List<String> comidasNoGusta2= new ArrayList<String>();
		comidasNoGusta2.add("cucaracha");
		PreferenciaAlimenticia preferencia2=new PreferenciaAlimenticia(comidasGusta2,comidasNoGusta2);
		Usuario usuario2 = new Usuario("Juan", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia2, condiciones, null);
		
		List<String> comidasGusta3= new ArrayList<String>();
		comidasGusta3.add("Carne");
		List<String> comidasNoGusta3= new ArrayList<String>();
		comidasNoGusta3.add("caracol");
		PreferenciaAlimenticia preferencia3=new PreferenciaAlimenticia(comidasGusta3,comidasNoGusta3);
		Usuario usuario3 = new Usuario("Pepo", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia3, condiciones, null);
		
		List<Usuario> usuariosColeccion = new ArrayList<Usuario>();
		usuariosColeccion.add(usuario1);
		usuariosColeccion.add(usuario2);
		usuariosColeccion.add(usuario3);
		List<String> comidasGusta= new ArrayList<String>();
		comidasGusta.add("Carne");
		List<String> comidasNoGusta= new ArrayList<String>();
		comidasNoGusta.add("lavandina");
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(comidasGusta,comidasNoGusta);
		Grupo grupo = new Grupo("UnNombre" , usuariosColeccion, preferencia);	

		
		assertTrue(grupo.puedeSugerir(receta));
	}*/

	
	@Test
	public void testPuedeSugerirCarneAlHornoAUnGrupoConUnoDiabetico() {
		
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Azucar");
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));
		
		List<String> comidasGusta1= new ArrayList<String>();
		comidasGusta1.add("Carne");
		comidasGusta1.add("pollo");
		List<String> comidasNoGusta1= new ArrayList<String>();
		comidasNoGusta1.add("pescado");
		PreferenciaAlimenticia preferencia1=new PreferenciaAlimenticia(comidasGusta1,comidasNoGusta1);
		Usuario usuario1 = new Usuario("Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia1, condiciones, new ArrayList<Receta>());
		
		List<String> comidasGusta2= new ArrayList<String>();
		comidasGusta2.add("Carne");
		comidasGusta2.add("pescado");
		List<String> comidasNoGusta2= new ArrayList<String>();
		comidasNoGusta2.add("cucaracha");
		PreferenciaAlimenticia preferencia2=new PreferenciaAlimenticia(comidasGusta2,comidasNoGusta2);
		Usuario usuario2 = new Usuario("Juan", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia2, new ArrayList<CondicionPreexistenteI>(), new ArrayList<Receta>());
		
		List<String> comidasGusta3= new ArrayList<String>();
		comidasGusta3.add("pescado");
		List<String> comidasNoGusta3= new ArrayList<String>();
		comidasNoGusta3.add("Carne");
		PreferenciaAlimenticia preferencia3=new PreferenciaAlimenticia(comidasGusta3,comidasNoGusta3);
		Usuario usuario3 = new Usuario("Pepo", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia3, new ArrayList<CondicionPreexistenteI>(), new ArrayList<Receta>());
		
		List<Usuario> usuariosColeccion = new ArrayList<Usuario>();
		usuariosColeccion.add(usuario1);
		usuariosColeccion.add(usuario2);
		usuariosColeccion.add(usuario3);
		List<String> comidasGusta= new ArrayList<String>();
		comidasGusta.add("Carne");
		List<String> comidasNoGusta= new ArrayList<String>();
		comidasNoGusta.add("lavandina");
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(comidasGusta,comidasNoGusta);
		Grupo grupo = new Grupo("UnNombre" , usuariosColeccion, preferencia);	

		
		assertFalse(grupo.puedeSugerir(receta));
	}
	
}
