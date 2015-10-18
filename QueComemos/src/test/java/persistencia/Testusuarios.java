package persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;



public class Testusuarios  extends AbstractPersistenceTest implements WithGlobalEntityManager {
	

		  @Before
		  public void setUp() {
			  
		  
		  }
		  

			@Test
			public void contextUp() {
				assertNotNull(entityManager());
			}


			@Test
			public void contextUpWithTransaction() throws Exception {
				withTransaction(() -> {});
			}


		  
		  
		  
		  

}


