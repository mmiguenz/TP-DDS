package condicionesPreexistentes;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

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
	
	
	
	public abstract boolean subSanaCondicion(Usuario usuario);
	public abstract boolean esAptaReceta(Receta receta);
	
	

}