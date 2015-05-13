package queComemos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Usuario {

	private String nombre;
	private String sexo;
	private LocalDate fechaNacimiento;
	private Double peso;
	private Double estatura;
	private String rutina;
	private PreferenciaAlimenticia preferenciaAlimenticia;
	private Set<CondicionPreexistente> condicionesPreexistentes;
	private Set<Receta> misRecetas;

	public Usuario(String nombre, String sexo, LocalDate fechaNacimiento,
			Double peso, Double estatura, String rutina,
			PreferenciaAlimenticia preferenciaAlimenticia,
			Set<CondicionPreexistente> condicionesPreexistentes,
			Set<Receta> misRecetas) {
		this.setNombre(nombre);
		this.setSexo(sexo);
		this.setFechaNacimiento(fechaNacimiento);
		this.setPeso(peso);
		this.setEstatura(estatura);
		this.setRutina(rutina);
		this.setPreferenciaAlimenticia(preferenciaAlimenticia);
		this.setCondicionesPreexistentes(condicionesPreexistentes);

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

	public Set<CondicionPreexistente> getCondicionesPreexistentes() {
		return condicionesPreexistentes;
	}

	public void setCondicionesPreexistentes(
			Set<CondicionPreexistente> condicionesPreexistentes) {
		this.condicionesPreexistentes = condicionesPreexistentes;
	}

	public Set<Receta> getMisRecetas() {
		return misRecetas;
	}

	public void setMisRecetas(Set<Receta> misRecetas) {
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
			/*boolean aux = true;
			for (CondicionPreexistente condicion : condicionesPreexistentes) {
				aux = aux && condicion.subSanaCondicion(this);

			}*/
			
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

	/*debe ser private, lo cambie ara hacer los test*/public boolean validaCondicionesPreexistentes(Usuario usr) {
		if (usr.condicionesPreexistentes.isEmpty())
			return true;
		else
			return usr.condicionesPreexistentes.stream().allMatch(condicion -> condicion.validar(usr));
			/*return usr.condicionesPreexistentes.size() == usr.condicionesPreexistentes
					.stream().filter(condicion -> condicion.validar(usr))
					.count();*/

	}
	
	public boolean validarVegano(Set<String> preferenciasProhibidas){
		if(this.preferenciaAlimenticia==null || this.preferenciaAlimenticia.getComidasQueGusta().isEmpty()) {return true;}
		else{
		return this.getPreferenciaAlimenticia().leGustaAlguna(
				preferenciasProhibidas);
		}
	}
	public boolean validarDiabetico(){
		
		return this.sexo != null && this.preferenciaAlimenticia != null;
	}
	
	public boolean validarHipertenso(){
		
		return this.preferenciaAlimenticia != null;
	}


	public void agregarReceta(Receta receta) {

		if (receta.validar()) {
			misRecetas.add(receta);

		}

	}

	public void modificarUnaReceta(Receta recetaModificada) {

		misRecetas.removeIf(receta -> receta.getNombre() == recetaModificada
				.getNombre());
		this.agregarReceta(recetaModificada);

	}

	public boolean puedeVer(Receta receta) {
		return misRecetas.contains(receta)
				|| QueComemosApp.recetas.contains(receta);

	}

}
