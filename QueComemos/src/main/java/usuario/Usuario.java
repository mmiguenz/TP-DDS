 package usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import condicionesPreexistentes.CondicionPreexistente;
import receta.Preparacion;
import receta.Receta;
import repositorios.Recetario;

@Entity
@Table(name="Usuarios")
public class Usuario {

	@Id
	@GeneratedValue
	@Column(name="usuarioID")
	private Long id;
	private String nombre;
	private String sexo;
	@Convert(converter=LocalDateConverter.class)
	private LocalDate fechaNacimiento;
	private Double peso;
	private Double estatura;
	private String rutina;
	@OneToOne(cascade={CascadeType.PERSIST})
	private PreferenciaAlimenticia preferenciaAlimenticia;
	private boolean  marcaFavoritasLasConsultas ; 
	@ManyToMany
	private List<CondicionPreexistente> condicionesPreexistentes;
	@ManyToMany
	@CollectionTable(name="RecetasDeUsuario")
	private List<Receta> misRecetas;
	@ManyToMany
	@CollectionTable(name="RecetasFavoritasXUsuario")
	private List<Receta> favoritas;
	
	




	
		

	public List<Receta> getFavoritas() {
		return favoritas;
	}






	public Usuario(Long id, String nombre, String sexo,
			LocalDate fechaNacimiento, Double peso, Double estatura,
			String rutina, PreferenciaAlimenticia preferenciaAlimenticia,
			boolean marcaFavoritasLasConsultas,
			List<CondicionPreexistente> condicionesPreexistentes,
			List<Receta> misRecetas, List<Receta> favoritas) {

		this.id = id;
		this.nombre = nombre;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.peso = peso;
		this.estatura = estatura;
		this.rutina = rutina;
		this.preferenciaAlimenticia = preferenciaAlimenticia;
		this.marcaFavoritasLasConsultas = marcaFavoritasLasConsultas;
		this.condicionesPreexistentes = condicionesPreexistentes;
		this.misRecetas = misRecetas;
		this.favoritas = favoritas;
	}






	public void setFavoritas(List<Receta> favoritas) {
		this.favoritas = favoritas;
	}






	public Double indiceMasaCorporal() {
		return this.peso / (this.estatura * this.estatura);
	}

	public boolean sigueRutinaSaludable() {
		if (this.condicionesPreexistentes.isEmpty())
		//if (this.condicionesPreexistentes.stream().count() == 0)
			return (this.indiceMasaCorporal() >= 18 && this
					.indiceMasaCorporal() <= 30);

		else {

			return this.condicionesPreexistentes.stream().allMatch(condicion -> condicion.subSanaCondicion(this));
		}
	}

	public boolean validar() {
		return this.tieneCamposObligatorios(this) && this.nombre.length() >= 4
				&& this.fechaNacimiento.isBefore(LocalDate.now())
				&& this.validaCondicionesPreexistentes(this);

	}

	private boolean tieneCamposObligatorios(Usuario usr) {

		return usr.nombre != null && usr.rutina != null && usr.peso != null
				&& usr.estatura != null && usr.fechaNacimiento != null;

	}

	public boolean validaCondicionesPreexistentes(Usuario usr) {
		if (usr.condicionesPreexistentes.isEmpty())
			return true;
		else
			return usr.condicionesPreexistentes.stream().allMatch(condicion -> condicion.subSanaCondicion(usr));
			
	}
	

	

	public void agregarReceta(Receta receta) {

			
		if (receta.validar()) {
			misRecetas.add(receta);

		}

	}



	
	public void modificaUnaRecetaPublica(String nombre, String nuevoNombre,Double calorias, Preparacion preparacion,List<Receta>subRecetas,String dificultad){
		Receta recetaModificada= Recetario.getInstance().modificarRecetaPublica( nombre,  nuevoNombre, calorias, preparacion,subRecetas, dificultad);
		if (recetaModificada !=null)
		this.agregarReceta(recetaModificada);
		
	}

	public boolean puedeVer(Receta receta) {
		return esAdecuadaLaReceta(receta) && 
				(misRecetas.contains(receta) || Recetario.getInstance().listarTodas().contains(receta));

	}



	public boolean esAdecuadaLaReceta(Receta receta){
		return this.condicionesPreexistentes.stream().allMatch(condicion -> condicion.esAptaReceta(receta))
				&& !this.preferenciaAlimenticia.getComidasQueDisgusta().stream().anyMatch(comida -> receta.contiene(comida));		
	
	}
	
	
	public void marcarComoFavorita(Receta receta)
	{
		this.favoritas.add(receta);
		
	}

	public boolean leGusta(Receta receta) {
		// Falta implementar ya que no especifia bajo que criterio
		return true;
	}

	public Long getUsuarioID() {
		return id;
	}

	public void setUsuarioID(Long usuarioID) {
		this.id = usuarioID;
	}

	
	public String getRutina() {
		return rutina;
	}

	public void setRutina(String rutina) {
		this.rutina = rutina;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getEstatura() {
		return estatura;
	}

	public void setEstatura(Double estatura) {
		this.estatura = estatura;
	}

	public PreferenciaAlimenticia getPreferenciaAlimenticia() {
		return preferenciaAlimenticia;
	}

	public void setPreferenciaAlimenticia(
			PreferenciaAlimenticia preferenciaAlimenticia) {
		this.preferenciaAlimenticia = preferenciaAlimenticia;
	}

	public List<CondicionPreexistente> getCondicionesPreexistentes() {
		return condicionesPreexistentes;
	}

	public void setCondicionesPreexistentes(
			List<CondicionPreexistente> condicionesPreexistentes) {
		this.condicionesPreexistentes = condicionesPreexistentes;
	}

	public List<Receta> getMisRecetas() {
		return misRecetas;
	}

	public void setMisRecetas(List<Receta> misRecetas) {
		this.misRecetas = misRecetas;
	}

	public boolean isMarcaFavoritasLasConsultas() {
		return marcaFavoritasLasConsultas;
	}

	public void setMarcaFavoritasLasConsultas(boolean marcaFavoritasLasConsultas) {
		this.marcaFavoritasLasConsultas = marcaFavoritasLasConsultas;
	}






	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}


	public Usuario()
	{
		super();
		
	}



	
}