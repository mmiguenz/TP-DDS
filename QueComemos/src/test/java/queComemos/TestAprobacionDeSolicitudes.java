package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;

public class TestAprobacionDeSolicitudes {
	
	private List<Usuario> usuarios ;

	@Before
	public void setUp() throws Exception {
		
				
		 usuarios = new ArrayList<Usuario>();
		
		PreferenciaAlimenticia preferencia = new PreferenciaAlimenticia(new ArrayList<String>(),new ArrayList<String>());
		
		
		
		CondicionPreexistenteI celiaco = new Celiaco("celiaco",new ArrayList<String>());
		CondicionPreexistenteI diabetico = new Diabetico("diabetico",new ArrayList<String>());
		CondicionPreexistenteI vegano = new Vegano("vegano",new ArrayList<String>());
		CondicionPreexistenteI hipertenso = new Hipertenso("Hipertenso",new ArrayList<String>());
		
		List<CondicionPreexistenteI> condiciones1 = new ArrayList<>();
		List<CondicionPreexistenteI> condiciones2 = new ArrayList<>();
		List<CondicionPreexistenteI> condiciones3 = new ArrayList<>();
		
		
		condiciones1.add(vegano);
		condiciones1.add(hipertenso);
		
		condiciones2.add(celiaco);
		condiciones2.add(diabetico);
		
		condiciones3.add(celiaco);
		condiciones3.add(diabetico);
		condiciones3.add(vegano);
		condiciones3.add(hipertenso);
		
		
		
		
		
		
		
		
		Usuario usr1 = new Usuario(1,"martin", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones3, new ArrayList<Receta>());
		Usuario usr2 = new Usuario(2,"pablo", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones3, new ArrayList<Receta>());
		Usuario usr3 = new Usuario(3,"analia", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones2, new ArrayList<Receta>());
		Usuario usr4 = new Usuario(4,"juana", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones2, new ArrayList<Receta>());
		Usuario usr5 = new Usuario(5,"juan", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones1, new ArrayList<Receta>());
		Usuario usr6 = new Usuario(6,"sebastian", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones1, new ArrayList<Receta>());
		Usuario usr7 = new Usuario(7,"matias", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones3, new ArrayList<Receta>());
		Usuario usr8 = new Usuario(8,"juan", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones3, new ArrayList<Receta>());
		Usuario usr9 = new Usuario(9,"matias", "", LocalDate.parse("1994-05-06"), 10.0, 20.5, "Intensa", preferencia, condiciones1, new ArrayList<Receta>());
		
		usuarios.add(usr1);
		usuarios.add(usr2);
		usuarios.add(usr3);
		usuarios.add(usr4);
		usuarios.add(usr5);
		usuarios.add(usr6);
		usuarios.add(usr7);
		usuarios.add(usr8);
		usuarios.add(usr9);
		
		
		
	
		
		
		
		
	}

	@Test
	public void testAprobaciones() {
		
		CriterioAprobacionI criterio = new CriteroApruebaSiEsJuan(); 
		ApplicationsManager administrador  =  new ApplicationsManager(usuarios,criterio);
		
		assertEquals(administrador.getMotivosRechazos().size(),6);
		
		
		
		
	}

}
