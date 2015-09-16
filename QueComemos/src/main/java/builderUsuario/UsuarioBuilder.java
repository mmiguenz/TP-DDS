package builderUsuario;

import interfaces.CondicionPreexistenteI;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import excepciones.FalloValidacionAlCrearUsuario;
import receta.Receta;
import usuario.GustosSobreAlimentos;
import usuario.Usuario;

public abstract class UsuarioBuilder {
	
	protected Usuario nuevoUsuario;
	
	
	
	public  void establecerNombre(String nombre)
	{
		nuevoUsuario.setNombre(nombre);
		
		
	}
	public  void establecerSexo(String sexo)
	{
		nuevoUsuario.setSexo(sexo);
		
		
	}
	public  void establecerFechaNacimiento(LocalDate fechaNacimiento){
		
		nuevoUsuario.setFechaNacimiento(fechaNacimiento);
		
	}
	public  void establecerPeso(Double peso)
	{
		
		nuevoUsuario.setPeso(peso);
	}
	public  void establecerEstatura(Double estatura)
	{
		nuevoUsuario.setEstatura(estatura);
		
	}
	public  void establecerRutina(String rutina)
	{
		nuevoUsuario.setRutina(rutina);
		
	}


	public UsuarioBuilder()
	{
		

		 
			
		List<String> comidasQueGusta = new ArrayList<String>();
		List<String> comidasQueDisgusta = new ArrayList<String>();
			
			
		GustosSobreAlimentos preferenciaAlimenticia = new GustosSobreAlimentos(comidasQueGusta,comidasQueDisgusta);
			
	 nuevoUsuario = new Usuario(null ,"","", LocalDate.now(), 0.0, 0.0, "", preferenciaAlimenticia, new ArrayList<CondicionPreexistenteI>(),  new ArrayList<Receta>());
			
				
		 	
		
	}
	
	public  Usuario crearUsuario()
	{
		if(nuevoUsuario.validar())
			return nuevoUsuario;
		else
			throw new FalloValidacionAlCrearUsuario();
		
				
	}
	
	
	public  void esCeliaco()
	{
		
	List<String> comidasProhibidas = new ArrayList<String>();
	comidasProhibidas.add("pan");
	comidasProhibidas.add("harina");
	
	nuevoUsuario.getCondicionesPreexistentes().add(new Celiaco("celiaco",comidasProhibidas));
	
			
		
	}
	public  void esHipertenso()
	{
		
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("sal");
		comidasProhibidas.add("vino");
		
		nuevoUsuario.getCondicionesPreexistentes().add(new Hipertenso("hipertenso",comidasProhibidas));
		
	}
	public  void esDiabetico()
	{		
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("azucar");
		comidasProhibidas.add("caramelo");
		comidasProhibidas.add("helado");
		
		nuevoUsuario.getCondicionesPreexistentes().add(new Diabetico("diabetico",comidasProhibidas));
		
		
	}
	public  void esVegano()
	{
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("carne");
		comidasProhibidas.add("pollo");
		comidasProhibidas.add("pescado");
		
		nuevoUsuario.getCondicionesPreexistentes().add(new Vegano("vegano",comidasProhibidas));
		
		
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
