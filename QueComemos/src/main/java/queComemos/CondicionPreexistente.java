package queComemos;

import java.util.Set;

public abstract class CondicionPreexistente {
	
	protected String nombre; 
	protected Set<String> comidasProhibidas;

	
	
	public CondicionPreexistente(String nombre, Set<String> comidasProhibidas) {

		this.nombre = nombre;
		this.comidasProhibidas = comidasProhibidas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Set<String> getComidasProhibidas() {
		return comidasProhibidas;
	}
	public void setComidasProhibidas(Set<String> comidasProhibidas) {
		this.comidasProhibidas = comidasProhibidas;
	}
	
	
	public boolean subSanaCondicion(Usuario usuario)
	{
		return false;
		
		
	}
	
	public static boolean esRecomendable(double calorias,Receta subReceta, Preparacion preparacion)
	{
		
		return true ; 
		
		
	}
	
	
	public  boolean validar(Usuario usr)
	{
		
		return true ;
	}
	public boolean validarReceta(Usuario usr, Receta receta){
		return true;
	}
	

}
