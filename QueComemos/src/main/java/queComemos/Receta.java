package queComemos;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.Observable;
import java.util.Observer;


public class Receta extends Observable{

	private String nombre;
	private double calorias;
	private Preparacion preparacion;
	private String dificultad;
	private String temporada;
	private List<Receta> subRecetas;
	private List<CondicionPreexistenteI> inadecuados;
	private Set<ObservadorI> observadores;

	public Receta(String nombre, double calorias, Preparacion preparacion,
			String dificultad, String temporada, List<Receta> subRecetas, List<CondicionPreexistenteI> inadecuados) {

		
		
		this.nombre = nombre;
		this.calorias = calorias;
		this.preparacion = preparacion;
		this.dificultad = dificultad;
		this.temporada = temporada;
		this.subRecetas = subRecetas;
		this.inadecuados = calcularInadecuados();
		//this.inadecuados = inadecuados;
		this.observadores= new HashSet<ObservadorI>();

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

	public List<CondicionPreexistenteI> getInadecuados() {
		return inadecuados;
	}

	public void setInadecuados(List<CondicionPreexistenteI> inadecuados) {
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


	/*public boolean esAdecuadaPara(Usuario usr){
		////////////////////////////////////////////////////////////////////////
		if (usr.esAdecuadaLaReceta(this)){
			this.observadores.forEach(obs->obs.notificar(usr, this));
			return true;
		}else{
			return false;
		}
		
	}*/
	
	public boolean esAdecuadaParaGrupo(List<Usuario> usuarios){
		return (usuarios.stream().allMatch(usr->usr.esAdecuadaLaReceta(this)));
	}
	

	public List<CondicionPreexistenteI> calcularInadecuados() {

		
		return QueComemosApp.calcularInadecuadosParaReceta(this);
				
	}

	public boolean validar() {
		return preparacion.validar() && (calorias >= 10 && calorias <= 5000);

	}
	
	
	public Ingrediente buscaIngrediente(String ingre)
	{
		return preparacion.buscaIngrediente(ingre);
		
		
	}
	
	public boolean leGustaAlGrupo(PreferenciaAlimenticia preferenciaAlimenticia){
		
		return ((this.getPreparacion().leGusta(preferenciaAlimenticia.getComidasQueGusta())) && !(this.getPreparacion().leGusta(preferenciaAlimenticia.getComidasQueDisgusta())));
	}
	
	public void agregarObservador(ObservadorI obsNuevo){
		this.observadores.add(obsNuevo);
	}
	
	

}
