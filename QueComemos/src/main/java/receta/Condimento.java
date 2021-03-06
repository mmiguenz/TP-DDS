package receta;

import javax.persistence.*;

@Entity
@Table(name="condimentos")
public   class Condimento  {
		
		@Id
		@GeneratedValue
		@Column(name="ID")
		Long id;
		
		
		private String nombre ;
		private String medida;
		private double cantidad;
	
	public Condimento(String nombre,String medida,double cantidad)
	{
		this.nombre=nombre;
		this.medida=medida;
		this.cantidad=cantidad;
		
		
		
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	
	
	public String getMedida() {
		return medida;
	}


	public void setMedida(String medida) {
		this.medida = medida;
	}


	public double getCantidad() {
		return cantidad;
	}
	
	


	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}







	public boolean contiene(String nombreIngrediente)
	{
	
				return nombre.equals( nombreIngrediente);
	
	
		
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public Condimento()
	{
		super();
		
	}

}

