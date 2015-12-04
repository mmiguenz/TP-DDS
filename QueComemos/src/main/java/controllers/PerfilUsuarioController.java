package controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import repositorios.RepoUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuario.Usuario;

public class PerfilUsuarioController implements WithGlobalEntityManager, TransactionalOps {
	
	
	public ModelAndView mostrar(Request request, Response response)
	{
		Usuario usuario = RepoUsuarios.getInstance().usuarioSession;
		
		Long imc =Math.round(usuario.indiceMasaCorporal());
				
					
		HashMap<String, Object> viewModel = new HashMap<>();
		viewModel.put("usuario", usuario);
		viewModel.put("imc",imc);
		viewModel.put("preferencia",usuario.getPreferenciaAlimenticia());
		viewModel.put("misRecetas",usuario.getMisRecetas());
		viewModel.put("favoritas",usuario.getFavoritas());
		
				
		
		return new ModelAndView(viewModel, "perfil.hbs");
	}
	
	
	public Void modificarPerfil(Request request, Response response)
	{
		String usuarioID = request.queryParams("usuarioID");
		
		Usuario usuario	= entityManager().find(Usuario.class, Long.parseLong(usuarioID)); 
		
		usuario.setEstatura(Double.parseDouble(request.queryParams("estatura")));
		usuario.setFechaNacimiento(LocalDate.parse(request.queryParams("fechaNacimiento")));
		usuario.setNombre(request.queryParams("nombre"));
		usuario.setPeso(Double.parseDouble(request.queryParams("peso")));
		usuario.setSexo(request.queryParams("sexo"));
		usuario.setRutina(request.queryParams("rutina"));
		

		  withTransaction(() -> {
			 entityManager().persist(usuario);
		    });
			
					
		response.redirect("/");
		
		return null;
		
	}
	

}
