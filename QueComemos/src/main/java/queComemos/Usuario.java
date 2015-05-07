package queComemos;

import java.time.LocalDate;
import java.util.Set;

public class Usuario{
	

	private String nombre;
	private String sexo ; 
	private LocalDate fechaNacimiento;
	private double peso;
	private double estatura;
	private String rutina;
	private PreferenciaAlimenticia preferenciaAlimenticia;
	private Set<CondicionPreexistente> condicionesPreexistentes;
	private Set<Receta> misRecetas;


	
	
	
	public Usuario(String nombre,String sexo,LocalDate fechaNacimiento,double peso,double estatura
					,String rutina,PreferenciaAlimenticia preferenciaAlimenticia,Set<CondicionPreexistente> condicionesPreexistentes
					,Set<Receta> misRecetas)
		{
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


	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getEstatura() {
		return estatura;
	}

	public void setEstatura(double estatura) {
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


	
	
	public double indiceMasaCorporal() {
		 return this.peso/(this.estatura*this.estatura);
	}
	
	
	public boolean sigueRutinaSaludable()
	{
		if (this.condicionesPreexistentes.stream().count()==0)
			return (this.indiceMasaCorporal() >=18 && this.indiceMasaCorporal()<=30);
		
		else{
			boolean aux=true;
			for (CondicionPreexistente condicion : condicionesPreexistentes)
			{
			  aux= aux && condicion.subSanaCondicion(this);
				
				
			}
			
			return aux;
		}
	}

}
