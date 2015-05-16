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
	private Set<CondicionPreexistenteI> condicionesPreexistentes;
	private Set<Receta> misRecetas;

	public Usuario(String nombre, String sexo, LocalDate fechaNacimiento,
			Double peso, Double estatura, String rutina,
			PreferenciaAlimenticia preferenciaAlimenticia,
			Set<CondicionPreexistenteI> condicionesPreexistentes,
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

	public Set<CondicionPreexistenteI> getCondicionesPreexistentes() {
		return condicionesPreexistentes;
	}

	public void setCondicionesPreexistentes(
			Set<CondicionPreexistenteI> condicionesPreexistentes) {
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

	public boolean validaCondicionesPreexistentes(Usuario usr) {
		if (usr.condicionesPreexistentes.isEmpty())
			return true;
		else
			return usr.condicionesPreexistentes.stream().allMatch(condicion -> condicion.subSanaCondicion(usr));
			/*return usr.condicionesPreexistentes.size() == usr.condicionesPreexistentes
					.stream().filter(condicion -> condicion.validar(usr))
					.count();*/

	}
	

	/*
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
*/

	public void agregarReceta(Receta receta) {

		if (receta.validar()) {
			misRecetas.add(receta);

		}

	}

	public void modificarUnaReceta(Receta recetaModificada) {

		misRecetas.removeIf(receta -> receta.equals(recetaModificada));
		this.agregarReceta(recetaModificada);

	}

	public boolean puedeVer(Receta receta) {
		return esAdecuadaLaReceta(receta) && 
				(misRecetas.contains(receta) || QueComemosApp.recetas.contains(receta));

	}


/*de aca para abajo lo hice porque dijo ue el usuario era quien debia saber si una receta es valida para una condicion preexistente (cosa que para mi esta mal porque deberia saberlo la clase vegaano, diabetico, celiaco o hipertenso)*/
	/*falta arreglar lo que comente y testearlo*/
	public boolean esAdecuadaLaReceta(Receta receta){
		return this.condicionesPreexistentes.stream().allMatch(condicion -> condicion.esAptaReceta(receta));		
	}
	
	/*public boolean esAptaRecetaHipertenso(Receta receta){
		return !(receta.contiene("sal")||receta.contiene("caldo"));
		
		
		
		/*creo que es una combinacion de los dos metodos que hay que usar para diabeticos y para veganos, porque hay que preguntarle si tiene como ingredientes y como condimentos por la sal y por el caldo
	
	}*/
	/*public boolean esAptaRecetaDiabetico(Receta receta){
		
		
		return !(receta.buscaIngrediente("azucar").getCantidad() >100.0  );
		
		/*hay que hacer un metodo que busque en los condimentos y preguntarle si la receta tiene azucar y 100 o mas gramos
	}*/
	
	/*public boolean esAptaRecetaVegano(Receta receta){
		return !(receta.contiene("pollo") || receta.contiene("carne") || receta.contiene("chivito") || receta.contiene("chori"));
		/*si estas seguro que el receta.contiene va a receta.preparacion.ingrediente y de la lista de ingredientes busca si algun nonmbre es igual al que le pases por parametro dejalo asi, sino habria que hacer un metodo que sea algo asi recete.getPreparacion().getIngrediente().stream().anyMatch(y la condicion)
	}*/
	
	/*public boolean esAptaRecetaCeliaco(Receta receta){
		return true;
	}*/
}