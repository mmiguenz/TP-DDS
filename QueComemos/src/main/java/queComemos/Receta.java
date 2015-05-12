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
	private Receta subReceta;
	private Set<String> inadecuados;

	public Receta(String nombre, double calorias, Preparacion preparacion,
			String dificultad, String temporada, Receta subReceta) {

		this.nombre = nombre;
		this.calorias = calorias;
		this.preparacion = preparacion;
		this.dificultad = dificultad;
		this.temporada = temporada;
		this.subReceta = subReceta;
		this.inadecuados = calcularInadecuados(subReceta, preparacion);

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

	public Receta getSubReceta() {
		return subReceta;
	}

	public void setSubReceta(Receta subReceta) {
		this.subReceta = subReceta;
	}

	public Set<String> getInadecuados() {
		return inadecuados;
	}

	public void setInadecuados(Set<String> inadecuados) {
		this.inadecuados = inadecuados;
	}

	public boolean contiene(String nombreIngrediente) {
		if (subReceta == null)
			return preparacion.contiene(nombreIngrediente);
		else
			return preparacion.contiene(nombreIngrediente)
					|| subReceta.contiene(nombreIngrediente);

	}

	public boolean contieneAlguna(Set<String> comidas) {

		return comidas.stream().filter(comida -> this.contiene(comida)).count() > 0;

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

	private Set<String> calcularInadecuados(Receta subReceta,
			Preparacion preparacion) {

		Set<String> inadecuados = new HashSet<String>();

		if (!(Hipertenso.esRecomendable(subReceta, preparacion)))
			inadecuados.add("Hipertenso");

		if (!(Diabetico.esRecomendable(calorias, subReceta, preparacion)))
			inadecuados.add("Diabetico");

		if (!(Celiaco.esRecomendable(calorias, subReceta, preparacion)))
			inadecuados.add("Celiaco");

		if (!(Vegano.esRecomendable(subReceta, preparacion)))
			inadecuados.add("Vegano");

		return inadecuados;
	}

	public boolean validar() {
		return preparacion.validar() && (calorias >= 10 && calorias <= 5000);

	}

}
