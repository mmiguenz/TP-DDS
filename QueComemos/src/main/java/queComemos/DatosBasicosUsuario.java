package queComemos;

import java.time.LocalDate;

public class DatosBasicosUsuario {

	private String nombre;
	private String sexo ; 
	private LocalDate fechaNacimiento;
	
	
	
	public DatosBasicosUsuario(String nombre,String sexo,LocalDate fechaNacimiento)
	{
		this.nombre=nombre;
		this.sexo=sexo;
		this.fechaNacimiento=fechaNacimiento;
		
		
		
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
	
	
}
