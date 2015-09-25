package usuario;

import java.util.List;

import receta.Receta;

public class Grupo {

	private String nombre;
	private List<Usuario> usuarios;
	private GustosSobreAlimentos preferenciaAlimenticia;
	

	public Grupo(String nombre, List<Usuario> usuario,
			GustosSobreAlimentos preferenciaAlimenticia) {
		this.setNombre(nombre);
		this.setUsuarios(usuario);
		this.setPreferenciaAlimenticia(preferenciaAlimenticia);
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> user) {
		this.usuarios=user;
	}
	
	public void agregarUsuario(Usuario user){
		this.usuarios.add(user);
	}
	
	
	public GustosSobreAlimentos getPreferenciaAlimenticia() {
		return preferenciaAlimenticia;
	}

	public void setPreferenciaAlimenticia(
			GustosSobreAlimentos preferenciaAlimenticia) {
		this.preferenciaAlimenticia = preferenciaAlimenticia;
	}
	
	public boolean puedeSugerir(Receta receta){
		
		return this.leGustaAlGrupo(receta);
		
	}
	
	public boolean esAdecuadaParaGrupo(Receta receta){
		return (usuarios.stream().allMatch(usr->usr.esAdecuadaLaReceta(receta)));
	}
	
	public boolean leGustaAlGrupo( Receta receta ){
		
		return (usuarios.stream().allMatch(usuario -> usuario.leGusta(receta) )  ) && receta.contieneAlguna( this.preferenciaAlimenticia.getComidasQueGusta())  ;
	}
	
	
	
	
	
}