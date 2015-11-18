package condicionesPreexistentes;
import static javax.persistence.InheritanceType.SINGLE_TABLE;
import javax.persistence.*;
import receta.Receta;
import usuario.Usuario;

@Entity
@Table(name="CondicionesPreexistentes")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name="TipoCondicion",discriminatorType = DiscriminatorType.STRING)
public abstract class CondicionPreexistente {

	@Id
	@GeneratedValue
	@Column(name="CondicionID")
	Long id;
	
	
	public String nombre;
	


	
	
	public abstract boolean subSanaCondicion(Usuario usuario);
	public abstract boolean esAptaReceta(Receta receta);

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public CondicionPreexistente(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		
	}
	
	

}