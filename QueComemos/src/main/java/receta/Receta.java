package receta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import condicionesPreexistentes.CondicionPreexistente;
import repositorios.RepoUsuarios;


@Entity
@Table(name="Recetas")
public class Receta {

	@Id
	@GeneratedValue
	@Column(name="RecetaID")
	Long id;
	
	
	private String nombre;
	private double calorias;
	@ManyToOne
	private Preparacion preparacion;
	private String dificultad;
	private String temporada;
	@ManyToMany
	@CollectionTable(name="SubRecetas")
	private List<Receta> subRecetas;
	@ManyToMany
	private List<CondicionPreexistente> inadecuados;


	public Receta(String nombre, double calorias, Preparacion preparacion,
			String dificultad, String temporada, List<Receta> subRecetas, List<CondicionPreexistente> inadecuados) {

		
		
		this.nombre = nombre;
		this.calorias = calorias;
		this.preparacion = preparacion;
		this.dificultad = dificultad;
		this.temporada = temporada;
		this.subRecetas = subRecetas;
		this.inadecuados = calcularInadecuados();


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

	public List<Receta> getSubRecetas() {
		return subRecetas;
	}

	public void setSubRecetas(List<Receta> subRecetas) {
		this.subRecetas = subRecetas;
	}

	public List<CondicionPreexistente> getInadecuados() {
		return inadecuados;
	}

	public void setInadecuados(List<CondicionPreexistente> inadecuados) {
		this.inadecuados = inadecuados;
	}

	public boolean contiene(String nombreIngrediente) {
		if (subRecetas.isEmpty())
			return preparacion.contiene(nombreIngrediente);
		else
			return preparacion.contiene(nombreIngrediente)
					|| subRecetas.stream().anyMatch(receta ->receta.contiene(nombreIngrediente));

	}

	public boolean contieneAlguna(List<String> comidas) {

		return comidas.stream().anyMatch((comida -> this.contiene(comida)));
	
	}


		


	public List<CondicionPreexistente> calcularInadecuados() {

		
		return RepoUsuarios.calcularInadecuadosParaReceta(this);
				
	}

	public boolean validar() {
		return preparacion.validar() && (calorias >= 10 && calorias <= 5000);

	}
	
	
	public Ingrediente buscaIngrediente(String ingre)
	{
		return preparacion.buscaIngrediente(ingre);
		
		
	}

	public static Receta crearReceta() {
		
		return new Receta("", 0.0
						,new Preparacion(new ArrayList<Ingrediente>(), new ArrayList<Ingrediente>(), new ArrayList<String>())
						, "", "", new ArrayList<Receta>(), new ArrayList<CondicionPreexistente>());
		
		
	}
	


	

}
