package builderUsuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.CondicionPreexistente;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import excepciones.FalloValidacionAlCrearUsuario;
import receta.Receta;
import repositorios.RepoUsuarios;
import usuario.PreferenciaAlimenticia;
import usuario.Usuario;

public abstract class UsuarioBuilder {
	
	protected Usuario nuevoUsuario;
	
	
	public void marcaFavoritas(boolean valor)
	{
		nuevoUsuario.setMarcaFavoritasLasConsultas(valor);
		
	}
	
	public  void nombre(String nombre)
	{
		nuevoUsuario.setNombre(nombre);
		
		
	}
	public  void sexo(String sexo)
	{
		nuevoUsuario.setSexo(sexo);
		
		
	}
	public  void fechaNacimiento(LocalDate fechaNacimiento){
		
		nuevoUsuario.setFechaNacimiento(fechaNacimiento);
		
	}
	public  void peso(Double peso)
	{
		
		nuevoUsuario.setPeso(peso);
	}
	public  void estatura(Double estatura)
	{
		nuevoUsuario.setEstatura(estatura);
		
	}
	public  void rutina(String rutina)
	{
		nuevoUsuario.setRutina(rutina);
		
	}


	public UsuarioBuilder()
	{
		

		 
			
		List<String> comidasQueGusta = new ArrayList<String>();
		List<String> comidasQueDisgusta = new ArrayList<String>();
			
			
		PreferenciaAlimenticia preferenciaAlimenticia = new PreferenciaAlimenticia(comidasQueGusta,comidasQueDisgusta);
			
	 nuevoUsuario = new Usuario(null ,"","", LocalDate.now(), 0.0, 0.0, "", preferenciaAlimenticia, false, new ArrayList<CondicionPreexistente>(),  new ArrayList<Receta>(), null);
			
				
		 	
		
	}
	
	public  Usuario crearUsuario()
	{
		if(nuevoUsuario.validar())
		{
			Usuario usuarior =  new Usuario(nuevoUsuario.getUsuarioID(),nuevoUsuario.getNombre(),nuevoUsuario.getSexo(),nuevoUsuario.getFechaNacimiento(),nuevoUsuario.getPeso(),nuevoUsuario.getEstatura(),nuevoUsuario.getRutina(),nuevoUsuario.getPreferenciaAlimenticia(),false, nuevoUsuario.getCondicionesPreexistentes(),nuevoUsuario.getMisRecetas(), new ArrayList<Receta>()) ;
			usuarior.setMarcaFavoritasLasConsultas(nuevoUsuario.isMarcaFavoritasLasConsultas());		
			return usuarior;
		}
		else{
			throw new FalloValidacionAlCrearUsuario();
		}
				
	}
	
	
	public  void esCeliaco()
	{
		
	
	nuevoUsuario.getCondicionesPreexistentes().add(RepoUsuarios.getInstance().obtenerCondicion("celiaco"));
	
			
		
	}
	public  void esHipertenso()
	{
		
		
		nuevoUsuario.getCondicionesPreexistentes().add(RepoUsuarios.getInstance().obtenerCondicion("hipertenso"));
		
	}
	public  void esDiabetico()
	{		
	
		
		nuevoUsuario.getCondicionesPreexistentes().add(RepoUsuarios.getInstance().obtenerCondicion("diabetico"));
		
		
	}
	public  void esVegano()
	{
	
		
		nuevoUsuario.getCondicionesPreexistentes().add(RepoUsuarios.getInstance().obtenerCondicion("vegano"));
		
		
	}
	
	public void leGusta(String comida)
	{
		
		nuevoUsuario.getPreferenciaAlimenticia().getComidasQueGusta().add(comida);
		
	}
	
	public void leDisgusta(String comida)
	{
		
		nuevoUsuario.getPreferenciaAlimenticia().getComidasQueDisgusta().add(comida);
		
	}
	
	
		
	
	
	
	

		
		
	
}
