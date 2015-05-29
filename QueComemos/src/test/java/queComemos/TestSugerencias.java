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

}
