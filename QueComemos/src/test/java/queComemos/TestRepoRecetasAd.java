package queComemos;

import static org.junit.Assert.*;
import interfaces.ObservadorI;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import condicionesPreexistentes.CondicionPreexistente;
import queComemos.entrega3.creacionales.RecetaBuilder;
import receta.Receta;
import repositorios.ConsultaRepoExtAd;
import repositorios.Recetario;
import repositorios.RepoRecetasAd;
import repositorios.RepoUsuarios;

public class TestRepoRecetasAd {
	
	private ConsultaRepoExtAd consulta;
	private RepoRecetasAd repo;

	@Before
	public void setUp() throws Exception {
		
		RepoUsuarios.getInstance().inadecuados= new ArrayList<CondicionPreexistente>();
		
		
		
		  repo = new  RepoRecetasAd();	
	}

	@Test
	public void testTraerREcetasRepoExternoSon12() {
		
		
				
		assertEquals(repo.traerTodasRecetasExternas().size(),12 );
		
	}
	
	
	
	@Test 
	public void TestconsultarEnRepoExternoPorDificultadDificil()
	
    {
			
		 consulta = new ConsultaRepoExtAd(null,"dificil",new ArrayList<String>());
		  assertEquals(repo.consultaEnRepoExterno(consulta).size(),2);
			
			
		
		
		
		
	}
	
	@Test 
	public void TestconsultarEnRepoExternoPorDificultadFacil()
	
    {
			
		 consulta = new ConsultaRepoExtAd(null,"facil",new ArrayList<String>());
		  assertEquals(repo.consultaEnRepoExterno(consulta).size(),6);
			
			
			
		
		
	}
	
	
	@Test 
	public void TestconsultarEnRepoExternoPorDificultadMediana()
	
    {
			
		 consulta = new ConsultaRepoExtAd(null,"mediana",new ArrayList<String>());
		  assertEquals(repo.consultaEnRepoExterno(consulta).size(),4);
					
		
		
		
		
	}
	
	
	@Test
	public void TestconsultarEnRepoExternoPorNombre()
	
    {
			
	 consulta = new ConsultaRepoExtAd("ensalada" ,null,new ArrayList<String>());
	  assertEquals(repo.consultaEnRepoExterno(consulta).size(),3);
					
		
		
		
		
	}
	

	
	@Test
	public void TestconsultarEnRepoExternoPorPalabras()
	
    {
			
		
		List<String> palabrasClave = new ArrayList<>();
		palabrasClave.add("lechuga");
		palabrasClave.add("ricota");
		palabrasClave.add("cassatta");
		
		 consulta = new ConsultaRepoExtAd(null ,null,palabrasClave);
		
	  assertEquals(repo.consultaEnRepoExterno(consulta).size(),3);
					
		
		
		
		
	}
	
	
	@Test
	public void TestconsultarEnRepoExternoPorTodo()
	
    {
			
		
		List<String> palabrasClave = new ArrayList<>();
		palabrasClave.add("lechuga");
		palabrasClave.add("croutons");
		palabrasClave.add("parmesano");
		
		 consulta = new ConsultaRepoExtAd("ensalada caesar" ,"facil",palabrasClave);
		
	  assertEquals(repo.consultaEnRepoExterno(consulta).size(),1);
						
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
