package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TestSugerencias {
	
	private Usuario usr ;
	private Receta receta ;

	@Before
	public void setUp() throws Exception {

		Set<String>	instrucciones = new HashSet<String>();
		Set<Ingrediente> ingredientes = new HashSet<Ingrediente>();
		Set<Ingrediente>condimentos = new HashSet<Ingrediente>();
	
		Set<CondicionPreexistenteI> inadecuados=new HashSet<CondicionPreexistenteI>();
		Set<String>comidasProhibidas= new HashSet<String>();
		Set<String>comidasProhibidasH= new HashSet<String>();
		comidasProhibidasH.add("caldo");
		
		
		Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
		Vegano vegano = new Vegano("vegano",comidasProhibidas);
		Hipertenso hipertenso = new Hipertenso("hipertenso",comidasProhibidasH);
		Diabetico diabetico = new Diabetico("diabetico",comidasProhibidas);
		
		QueComemosApp.inadecuados=new HashSet<CondicionPreexistenteI>();

		
			
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
			 
			 
				Set<String> comidasQueGustaUsrSaludable = new HashSet<String>();
				Set<String> comidasQueGustaUsrNoSaludable = new HashSet<String>();
				Set<String> comidasQueDisgustaUsr = new HashSet<String>();

				comidasQueGustaUsrSaludable.add("Fruta");
				comidasQueGustaUsrSaludable.add("Carne");
				comidasQueGustaUsrSaludable.add("Pasta");

				comidasQueGustaUsrNoSaludable.add("Fritos");
				comidasQueGustaUsrNoSaludable.add("Snacks");

				
				
				comidasQueDisgustaUsr.add("Verduras");

			PreferenciaAlimenticia	preferenciaAlimenticiaSaludable = new PreferenciaAlimenticia(
						comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
			

			
		 receta = new Receta("CarneAlHorno",1524.0,preparacion,dificultad,temporada,new HashSet<Receta>() ,inadecuados);
	
		
		usr = new Usuario("Matias", "Masculino",
				LocalDate.parse("1994-08-05"), 70.0, 1.75, "Leve", preferenciaAlimenticiaSaludable, new HashSet<CondicionPreexistenteI>(),
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
		
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		
		Set<String> comidasGusta1= new HashSet<String>();
		comidasGusta1.add("Carne");
		comidasGusta1.add("pollo");
		Set<String> comidasNoGusta1= new HashSet<String>();
		comidasNoGusta1.add("pescado");
		PreferenciaAlimenticia preferencia1=new PreferenciaAlimenticia(comidasGusta1,comidasNoGusta1);
		Usuario usuario1 = new Usuario("Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia1, condiciones, null);
		
		Set<String> comidasGusta2= new HashSet<String>();
		comidasGusta2.add("Carne");
		comidasGusta2.add("pescado");
		Set<String> comidasNoGusta2= new HashSet<String>();
		comidasNoGusta2.add("cucaracha");
		PreferenciaAlimenticia preferencia2=new PreferenciaAlimenticia(comidasGusta2,comidasNoGusta2);
		Usuario usuario2 = new Usuario("Juan", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia2, condiciones, null);
		
		Set<String> comidasGusta3= new HashSet<String>();
		comidasGusta3.add("Carne");
		Set<String> comidasNoGusta3= new HashSet<String>();
		comidasNoGusta3.add("caracol");
		PreferenciaAlimenticia preferencia3=new PreferenciaAlimenticia(comidasGusta3,comidasNoGusta3);
		Usuario usuario3 = new Usuario("Pepo", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia3, condiciones, null);
		
		Set<Usuario> usuariosColeccion = new HashSet<Usuario>();
		usuariosColeccion.add(usuario1);
		usuariosColeccion.add(usuario2);
		usuariosColeccion.add(usuario3);
		Set<String> comidasGusta= new HashSet<String>();
		comidasGusta.add("Carne");
		Set<String> comidasNoGusta= new HashSet<String>();
		comidasNoGusta.add("lavandina");
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(comidasGusta,comidasNoGusta);
		Grupo grupo = new Grupo("UnNombre" , usuariosColeccion, preferencia);	

		
		assertTrue(grupo.puedeSugerir(receta));
	}*/

	
	@Test
	public void testPuedeSugerirCarneAlHornoAUnGrupoConUnoDiabetico() {
		
		Set<String> comidasProhibidas = new HashSet<String>();
		comidasProhibidas.add("Azucar");
		Set<CondicionPreexistenteI> condiciones = new HashSet<CondicionPreexistenteI>();
		condiciones.add(new Diabetico("Diabetico", comidasProhibidas));
		
		Set<String> comidasGusta1= new HashSet<String>();
		comidasGusta1.add("Carne");
		comidasGusta1.add("pollo");
		Set<String> comidasNoGusta1= new HashSet<String>();
		comidasNoGusta1.add("pescado");
		PreferenciaAlimenticia preferencia1=new PreferenciaAlimenticia(comidasGusta1,comidasNoGusta1);
		Usuario usuario1 = new Usuario("Pedro", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia1, condiciones, new HashSet<Receta>());
		
		Set<String> comidasGusta2= new HashSet<String>();
		comidasGusta2.add("Carne");
		comidasGusta2.add("pescado");
		Set<String> comidasNoGusta2= new HashSet<String>();
		comidasNoGusta2.add("cucaracha");
		PreferenciaAlimenticia preferencia2=new PreferenciaAlimenticia(comidasGusta2,comidasNoGusta2);
		Usuario usuario2 = new Usuario("Juan", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia2, new HashSet<CondicionPreexistenteI>(), new HashSet<Receta>());
		
		Set<String> comidasGusta3= new HashSet<String>();
		comidasGusta3.add("pescado");
		Set<String> comidasNoGusta3= new HashSet<String>();
		comidasNoGusta3.add("Carne");
		PreferenciaAlimenticia preferencia3=new PreferenciaAlimenticia(comidasGusta3,comidasNoGusta3);
		Usuario usuario3 = new Usuario("Pepo", "Masculino",
				LocalDate.parse("1990-01-01"), 60.0, 1.7, "Leve",
				preferencia3, new HashSet<CondicionPreexistenteI>(), new HashSet<Receta>());
		
		Set<Usuario> usuariosColeccion = new HashSet<Usuario>();
		usuariosColeccion.add(usuario1);
		usuariosColeccion.add(usuario2);
		usuariosColeccion.add(usuario3);
		Set<String> comidasGusta= new HashSet<String>();
		comidasGusta.add("Carne");
		Set<String> comidasNoGusta= new HashSet<String>();
		comidasNoGusta.add("lavandina");
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(comidasGusta,comidasNoGusta);
		Grupo grupo = new Grupo("UnNombre" , usuariosColeccion, preferencia);	

		
		assertFalse(grupo.puedeSugerir(receta));
	}
	
}
