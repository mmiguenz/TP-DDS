package queComemos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import queComemos.entrega3.creacionales.RecetaBuilder;

public class TestRepoRecetasAd {
	
	private ConsultaRepoExtAd consulta;

	@Before
	public void setUp() throws Exception {
		
		QueComemosApp.inicializar();
		
		
	}

	@Test
	public void testTraerREcetasRepoExternoSon12() {
		
		
		RepoRecetasAd  repo = new  RepoRecetasAd();	
		
		assertEquals(repo.traerTodasRecetasExternas().size(),12 );
		
	}
	
	
	
	@Test 
	public void TestconsultarEnRepoExternoPorDificultadDificil()
	
    {
			
		 consulta = new ConsultaRepoExtAd(null,"dificil",new ArrayList<String>());
		  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),2);
			
			
		
		
		
		
	}
	
	@Test 
	public void TestconsultarEnRepoExternoPorDificultadFacil()
	
    {
			
		 consulta = new ConsultaRepoExtAd(null,"facil",new ArrayList<String>());
		  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),6);
			
			
		
		
		
		
	}
	
	
	@Test 
	public void TestconsultarEnRepoExternoPorDificultadMediana()
	
    {
			
		 consulta = new ConsultaRepoExtAd(null,"mediana",new ArrayList<String>());
		  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),4);
					
		
		
		
		
	}
	
	
	@Test
	public void TestconsultarEnRepoExternoPorNombre()
	
    {
			
	 consulta = new ConsultaRepoExtAd("ensalada" ,null,new ArrayList<String>());
	  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),3);
					
		
		
		
		
	}
	

	
	@Test
	public void TestconsultarEnRepoExternoPorPalabras()
	
    {
			
		
		List<String> palabrasClave = new ArrayList<>();
		palabrasClave.add("lechuga");
		palabrasClave.add("ricota");
		palabrasClave.add("cassatta");
		
		 consulta = new ConsultaRepoExtAd(null ,null,palabrasClave);
		
	  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),3);
					
		
		
		
		
	}
	
	
	@Test
	public void TestconsultarEnRepoExternoPorTodo()
	
    {
			
		
		List<String> palabrasClave = new ArrayList<>();
		palabrasClave.add("lechuga");
		palabrasClave.add("croutons");
		palabrasClave.add("parmesano");
		
		 consulta = new ConsultaRepoExtAd("ensalada caesar" ,"facil",palabrasClave);
		
	  assertEquals(QueComemosApp.consultarEnRepoExterno(consulta).size(),1);
		
					
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
