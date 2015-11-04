package condicionesPreexistentes;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

import java.util.List;

import javax.persistence.*;

import receta.Receta;
import usuario.Usuario;
@Entity
@Table(name="CondicionesPreexistentes")
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name="TipoCondicion")
public abstract class CondicionPreexistente {

	@Id
	@GeneratedValue
	@Column(name="CondicionID")
	Long id;
	
	
	public String nombre;
	@ElementCollection
	@CollectionTable(name="ComidasProhibidasPorCondicion")
	public List<String> comidasProhibidas;


	
	
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
	public List<String> getComidasQueDisgusta() {
		return comidasProhibidas;
	}
	public void setComidasQueDisgusta(List<String> comidasQueDisgusta) {
		this.comidasProhibidas = comidasQueDisgusta;
	}
	public CondicionPreexistente(Long id, String nombre,
			List<String> comidasQueDisgusta) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.comidasProhibidas = comidasQueDisgusta;
	}
	
	

}