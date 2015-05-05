package queComemos;

import java.time.LocalDateTime;
import java.util.Set;

public class Usuario{
	

	private DatosBasicosUsuario datosBasicos ;
	private ComplexionFisica datosFisicos;
	private PreferenciaAlimenticia preferenciaAlimenticia;
	private Set<CondicionPreexistente> condicionesPreexistentes;
	private Set<Receta> misRecetas;
	
	
	

	
	

	public Usuario(DatosBasicosUsuario datosBasicos,ComplexionFisica datosFisicos,
			PreferenciaAlimenticia preferenciaAlimenticia,
			Set<CondicionPreexistente> condicionesPreexistentes) {
		
		
		this.datosBasicos = datosBasicos;
		this.datosFisicos = datosFisicos;
		this.preferenciaAlimenticia = preferenciaAlimenticia;
		this.condicionesPreexistentes = condicionesPreexistentes;
	}







	public DatosBasicosUsuario getDatosBasicos() {
		return datosBasicos;
	}







	public void setDatosBasicos(DatosBasicosUsuario datosBasicos) {
		this.datosBasicos = datosBasicos;
	}







	public ComplexionFisica getDatosFisicos() {
		return datosFisicos;
	}







	public void setDatosFisicos(ComplexionFisica datosFisicos) {
		this.datosFisicos = datosFisicos;
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







	public double indiceMasaCorporal() {
		return datosFisicos.indiceMasaCorporal();
	}

}
