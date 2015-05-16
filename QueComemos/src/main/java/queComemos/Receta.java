package queComemos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;


public class Receta {

	private String nombre;
	private double calorias;
	private Preparacion preparacion;
	private String dificultad;
	private String temporada;
	private Set<Receta> subRecetas;
	private Set<CondicionPreexistenteI> inadecuados;

	public Receta(String nombre, double calorias, Preparacion preparacion,
			String dificultad, String temporada, Set<Receta> subRecetas, Set<CondicionPreexistenteI> inadecuados) {

		
		
		this.nombre = nombre;
		this.calorias = calorias;
		this.preparacion = preparacion;
		this.dificultad = dificultad;
		this.temporada = temporada;
		this.subRecetas = subRecetas;
		this.inadecuados = calcularInadecuados(this);
				

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCalorias() {
		return calorias;
	}

	public void setCalorias(double calorias) {
		this.calorias = calorias;
	}

	public Preparacion getPreparacion() {
		return preparacion;
	}

	public void setPreparacion(Preparacion preparacion) {
		this.preparacion = preparacion;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public Set<Receta> getSubRecetas() {
		return subRecetas;
	}

	public void setSubRecetas(Set<Receta> subRecetas) {
		this.subRecetas = subRecetas;
	}

	public Set<CondicionPreexistenteI> getInadecuados() {
		return inadecuados;
	}

	public void setInadecuados(Set<CondicionPreexistenteI> inadecuados) {
		this.inadecuados = inadecuados;
	}

	public boolean contiene(String nombreIngrediente) {
		if (subRecetas.isEmpty())
			return preparacion.contiene(nombreIngrediente);
		else
			return preparacion.contiene(nombreIngrediente)
					|| subRecetas.stream().anyMatch(receta ->receta.contiene(nombreIngrediente));

	}

	public boolean contieneAlguna(Set<String> comidas) {

		return comidas.stream().anyMatch((comida -> this.contiene(comida)));
		//return comidas.stream().filter(comida -> this.contiene(comida)).count() > 0;

		/*
		 * for (String comida : comidas) {
		 * 
		 * if (this.contiene(comida)) return true;
		 * 
		 * }
		 * 
		 * return false
		 */

	}

	/*
	 * public boolean esADecuadaPara(Usuario usuario) {
	 * 
	 * return usuario.getCondicionesPreexistentes().stream().map(condicion ->
	 * condicion.getComidasProhibidas()) .filter(comidasProhibidas ->
	 * this.contieneAlguna((List<String>) comidasProhibidas)).count() > 0;
	 * 
	 * 
	 * 
	 * }
	 */
	/*este metodo va de la mano con los que estan en usuario y en cada clase de condionPreexistente para calcular para quien es inadecuada la receta*/
	public boolean esAdecuadaPara(Usuario usr){
		return usr.esAdecuadaLaReceta(this);
	}
/*para mi con algunos cambios es como lo habias planteado vos, porque nunca te pide que calcules si una receta es adecuada para un usuario en particular*/
	private Set<CondicionPreexistenteI> calcularInadecuados(Receta receta) {

		
		return QueComemosApp.calcularInadecuadosParaReceta(receta);
				
	}

	public boolean validar() {
		return preparacion.validar() && (calorias >= 10 && calorias <= 5000);

	}
	
	
	public Ingrediente buscaIngrediente(String ingre)
	{
		return preparacion.buscaIngrediente(ingre);
		
		
	}
	
	

}
