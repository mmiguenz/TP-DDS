package queComemos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestRepoRecetasAd {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTraerREcetasRepoExternoSon12() {
		
		QueComemosApp.inicializar();
		RepoRecetasAd  repo = new  RepoRecetasAd();
		
		
		
		assertEquals(repo.traerTodasRecetasExternas().size(),12 );
		
	}
	
	
	
	@Test 
	public void TestconsultarEnRepoExternoPorDificultadDificil()
	
    {
		QueComemosApp.inicializar();	
		ConsultaRepoExtAd consulta = new ConsultaRepoExtAd(null,"dificil",new ArrayList<String>());
		  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),2);
			
			
		
		
		
		
	}
	
	@Test 
	public void TestconsultarEnRepoExternoPorDificultadFacil()
	
    {
		QueComemosApp.inicializar();	
		ConsultaRepoExtAd consulta = new ConsultaRepoExtAd(null,"facil",new ArrayList<String>());
		  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),6);
			
			
		
		
		
		
	}
	
	
	@Test 
	public void TestconsultarEnRepoExternoPorDificultadMediana()
	
    {
		QueComemosApp.inicializar();	
		ConsultaRepoExtAd consulta = new ConsultaRepoExtAd(null,"mediana",new ArrayList<String>());
		  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),4);
					
		
		
		
		
	}
	
	
	@Test
	public void TestconsultarEnRepoExternoPorNombre()
	
    {
		QueComemosApp.inicializar();	
		ConsultaRepoExtAd consulta = new ConsultaRepoExtAd("ensalada" ,"facil",new ArrayList<String>());
	  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),2);
					
		
		
		
		
	}
	
	/*
	 * =============================================================
	 * Falta Implementar para que le pueda llegar nula la dificultad 
	 * =============================================================
	 */
	
	@Test
	public void TestconsultarEnRepoExternoPorPalabras()
	
    {
		QueComemosApp.inicializar();	
		
		List<String> palabrasClave = new ArrayList<>();
		palabrasClave.add("lechuga");
		palabrasClave.add("ricota");
		palabrasClave.add("cassatta");
		
		ConsultaRepoExtAd consulta = new ConsultaRepoExtAd("ensalada" ,"facil",palabrasClave);
		
	  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),1);
					
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
