package main;

import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.EntityManager;


import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;


import receta.Receta;
import repositorios.RepoUsuarios;
import usuario.Usuario;
import builderReceta.RecetaBuilder;
import builderReceta.RecetaGenerica;
import builderUsuario.UsuarioBuilder;
import builderUsuario.UsuarioMasGenerico;

public class Entorno implements WithGlobalEntityManager,TransactionalOps {
	
	
	public static void main(String[] args) 
	{
				
		Entorno entorno = new Entorno();
		
		entorno.run();
		
				
		
	}

	private void run() {
		
		
		
RecetaBuilder rb = new RecetaGenerica();
		
		rb.calorias(550.);
		rb.condimentoAgregar("sal", "grs", 100.);
		rb.dificultad("Media");
		rb.ingredienteAgregar("papas", "kg", 1.);
		rb.instruccionesAgregar("condimentar papas");
		rb.instruccionesAgregar("pone en fuente");
		rb.instruccionesAgregar("hornear 1 hs");
		rb.instruccionesAgregar("sacar del horno");
		rb.instruccionesAgregar("servir");		
		rb.nombre("papas al horno");
		rb.temporada("Todas");
		
		Receta papasAlHorno = rb.crearReceta();
		
		
				
		rb.calorias(100.);
		rb.condimentoAgregar("sal", "grs", 100.);
		rb.dificultad("Media");
		rb.ingredienteAgregar("pollo", "kg", 1.);
		rb.ingredienteAgregar("papas", "kgs", 1.);
		rb.instruccionesAgregar("condimentar pollo");
		rb.instruccionesAgregar("pone en fuente");
		rb.instruccionesAgregar("hornear 1 hs");
		rb.instruccionesAgregar("sacar del horno");
		rb.instruccionesAgregar("servir");		
		rb.nombre("pollo al horno con papas");
		rb.temporada("Todas");
		rb.subRecetaAgregar(papasAlHorno);
		
		
		Receta polloAlHornoConPapas = rb.crearReceta();
		
		
		rb.calorias(550.);
		rb.condimentoAgregar("sal", "grs", 25.);
		rb.condimentoAgregar("ajo", "grs", 25.);
		rb.condimentoAgregar("perejil", "grs", 25.);
		rb.dificultad("Media");
		rb.ingredienteAgregar("pan rayado", "kg", 1.);
		rb.ingredienteAgregar("huevo", "u", 3.);
		rb.ingredienteAgregar("carne", "kg", 1.);
		rb.instruccionesAgregar("batir huevo");
		rb.instruccionesAgregar("mojar carne y huevo");
		rb.instruccionesAgregar("pasar carne por pan rayado");
		rb.instruccionesAgregar("hornear 35 min aprox");		
		rb.instruccionesAgregar("servir");
		rb.nombre("Milanesas");
		rb.temporada("Todas");
		
		Receta milanesas = rb.crearReceta();
		
		rb.calorias(1500.);
		rb.condimentoAgregar("azucar", "grs", 500.);
		rb.dificultad("dificil");
		rb.ingredienteAgregar("huevo", "u", 3.);
		rb.ingredienteAgregar("harina", "kg", 1.);
		rb.ingredienteAgregar("manteca", "kg",0.5);
		rb.instruccionesAgregar("batir huevo , harina y manteca");
		rb.instruccionesAgregar("poner en fuente ");
		rb.instruccionesAgregar("hornear 35 min aprox");		
		rb.instruccionesAgregar("servir");
		rb.nombre("Torta");
		rb.temporada("Todas");
		
		Receta torta = rb.crearReceta();
		
		
		
		
		
		
		UsuarioBuilder ub = new UsuarioMasGenerico();
		ub.estatura(172.0);
		ub.fechaNacimiento(LocalDate.parse("1994-08-05"));
		ub.leDisgusta("verdura");
		ub.leGusta("carne");
		ub.marcaFavoritas(true);
		ub.nombre("Pepito");
		ub.sexo("Masculino");
		ub.rutina("Liviana");
		ub.peso(66.0);
		
		
		Usuario usr = ub.crearUsuario();
		
		
		
		usr.marcarComoFavorita(torta);
		usr.marcarComoFavorita(milanesas);
		usr.marcarComoFavorita(polloAlHornoConPapas);
		
		 withTransaction(() -> {
		    RepoUsuarios.getInstance().add(usr);
		    });
		
		

		
	}

}
