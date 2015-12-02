package receta;

import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="preparaciones")
public class Preparacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="PreparacionID")
	Long id;
	
	@OneToMany(cascade={CascadeType.PERSIST})
	@JoinColumn(name= "PreparacionID")
	private List<Ingrediente> ingredientes;
	@JoinColumn(name= "PreparacionID")
	@OneToMany(cascade={CascadeType.PERSIST})
	private List<Condimento> condimentos;
	@ElementCollection
	private List<String> explicacion;
	private String nombre;


	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}






	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public Preparacion(Long id , List<Ingrediente> ingredientes, List<Condimento> condimentos,List<String> explicacion)
	{
		this.id = null;
		this.ingredientes=ingredientes;
		this.explicacion=explicacion;
		this.condimentos =condimentos;
			
		
		
	}
	
	
	



	public List<Ingrediente> getIngredientes() {
		return ingredientes;
	}



	public void setIngredientes(List<Ingrediente> ingredientes) {
		this.ingredientes = ingredientes;
	}



	public List<Condimento> getCondimentos() {
		return condimentos;
	}



	public void setCondimentos(List<Condimento> condimentos) {
		this.condimentos = condimentos;
	}



	public List<String> getExplicacion() {
		return explicacion;
	}



	public void setExplicacion(List<String> explicacion) {
		this.explicacion = explicacion;
	}
	

	public boolean contiene(String nombreIngrediente)
	{
		return  (ingredientes.stream().anyMatch((ingrediente -> ingrediente.contiene(nombreIngrediente))))
				//.filter(ingrediente -> ingrediente.contiene(nombreIngrediente))).count() >0
				|| (condimentos.stream().anyMatch((condimento -> condimento.contiene(nombreIngrediente))));
				  //  .filter(condimento -> condimento.contiene(nombreIngrediente))).count() >0;
		
		
	}
	
	
	
	public Ingrediente buscaIngrediente(String ingre)
	{
		if (ingredientes.stream().anyMatch(ingrediente -> ingrediente.getNombre().equals(ingre)))
		{
			return this.buscarPorNombre(ingre, ingredientes);
			
		}
		
		return null;
		
		
	}
	
	public boolean validar()
	{
		return ! ingredientes.isEmpty();
		
		
	}
	
	



private Ingrediente buscarPorNombre(String nombre,List<Ingrediente> ingredientes)
{
	for (Ingrediente ingrediente : ingredientes)
	{
		
		if (ingrediente.getNombre().equals(nombre))
		{
			
			return ingrediente;
			
		}
		
	}
	
	return new Ingrediente("","",.0);
	
}


public Preparacion()
{
	super();
	
}

}
