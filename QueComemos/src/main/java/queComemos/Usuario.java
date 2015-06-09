package queComemos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nombre;
	private String sexo;
	public List<Receta> getFavoritas() {
		return favoritas;
	}

	public void setFavoritas(List<Receta> favoritas) {
		this.favoritas = favoritas;
	}


	private LocalDate fechaNacimiento;
	private Double peso;
	private Double estatura;
	private String rutina;
	private PreferenciaAlimenticia preferenciaAlimenticia;
	private List<CondicionPreexistenteI> condicionesPreexistentes;
	private List<Receta> misRecetas;
	private List<Receta> favoritas;

	public Usuario(String nombre, String sexo, LocalDate fechaNacimiento,
			Double peso, Double estatura, String rutina,
			PreferenciaAlimenticia preferenciaAlimenticia,
			List<CondicionPreexistenteI> condicionesPreexistentes,
			List<Receta> misRecetas) {
		this.setNombre(nombre);
		this.setSexo(sexo);
		this.setFechaNacimiento(fechaNacimiento);
		this.setPeso(peso);
		this.setEstatura(estatura);
		this.setRutina(rutina);
		this.setPreferenciaAlimenticia(preferenciaAlimenticia);
		this.setCondicionesPreexistentes(condicionesPreexistentes);
		this.setMisRecetas(misRecetas);
		this.favoritas= new ArrayList<Receta>();

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

	public List<CondicionPreexistenteI> getCondicionesPreexistentes() {
		return condicionesPreexistentes;
	}

	public void setCondicionesPreexistentes(
			List<CondicionPreexistenteI> condicionesPreexistentes) {
		this.condicionesPreexistentes = condicionesPreexistentes;
	}

	public List<Receta> getMisRecetas() {
		return misRecetas;
	}

	public void setMisRecetas(List<Receta> misRecetas) {
		this.misRecetas = misRecetas;
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
		return this.tieneCamposObligatorios(this) && this.nombre.length() > 4
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

	public void modificarUnaReceta(Receta recetaModificada) {

		misRecetas.removeIf(receta -> receta.equals(recetaModificada));
		this.agregarReceta(recetaModificada);

	}
	
	public void modificaUnaRecetaPublica(String nombre, String nuevoNombre,Double calorias, Preparacion preparacion,List<Receta>subRecetas,String dificultad){
		Receta recetaModificada= QueComemosApp.modificarRecetaPublica( nombre,  nuevoNombre, calorias, preparacion,subRecetas, dificultad);
		this.agregarReceta(recetaModificada);
		
	}

	public boolean puedeVer(Receta receta) {
		return esAdecuadaLaReceta(receta) && 
				(misRecetas.contains(receta) || QueComemosApp.recetas.contains(receta));

	}



	public boolean esAdecuadaLaReceta(Receta receta){
		return this.condicionesPreexistentes.stream().allMatch(condicion -> condicion.esAptaReceta(receta))
				&& this.preferenciaAlimenticia.getComidasQueDisgusta().stream().anyMatch(comida -> receta.contiene(comida));		
	}
	
	
	public void marcarComoFavorita(Receta receta)
	{
		this.favoritas.add(receta);
		
	}

	public boolean leGusta(Receta receta) {
		// Falta implementar ya que no especifia bajo que criterio
		return true;
	}
	
	
	
}