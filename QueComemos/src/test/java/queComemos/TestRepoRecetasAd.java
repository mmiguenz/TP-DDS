package queComemos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import queComemos.entrega3.creacionales.RecetaBuilder;

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
		ConsultaRepoExtAd consulta = new ConsultaRepoExtAd("ensalada" ,null,new ArrayList<String>());
	  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),3);
					
		
		
		
		
	}
	

	
	@Test
	public void TestconsultarEnRepoExternoPorPalabras()
	
    {
		QueComemosApp.inicializar();	
		
		List<String> palabrasClave = new ArrayList<>();
		palabrasClave.add("lechuga");
		palabrasClave.add("ricota");
		palabrasClave.add("cassatta");
		
		ConsultaRepoExtAd consulta = new ConsultaRepoExtAd(null ,null,palabrasClave);
		
	  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),3);
					
		
		
		
		
	}
	
	
	@Test
	public void TestconsultarEnRepoExternoPorTodo()
	
    {
		QueComemosApp.inicializar();	
		
		List<String> palabrasClave = new ArrayList<>();
		palabrasClave.add("lechuga");
		palabrasClave.add("croutons");
		palabrasClave.add("parmesano");
		
		ConsultaRepoExtAd consulta = new ConsultaRepoExtAd("ensalada caesar" ,"facil",palabrasClave);
		
	  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),1);
	  
		
					
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
