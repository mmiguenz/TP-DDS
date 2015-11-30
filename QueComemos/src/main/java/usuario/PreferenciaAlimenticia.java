package usuario;

import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="PreferenciasAlimenticias")
public class PreferenciaAlimenticia {
	
	@Id
	@GeneratedValue
	@Column(name="PreferenciaAlimenticiaID")
	Long id;
	
	@ElementCollection
	@CollectionTable(name="ComidasQueGusta")
	private List<String> comidasQueGusta;
	@ElementCollection
	@CollectionTable(name="ComidasQueDisgusta")
	private List<String> comidasQueDisgusta;
	
	public PreferenciaAlimenticia(List<String> comidasQueGusta,List<String> comidasQueDisgusta) {
	
		this.comidasQueGusta = comidasQueGusta;
		this.comidasQueDisgusta = comidasQueDisgusta;
	}

	public List<String> getComidasQueGusta() {
		return comidasQueGusta;
	}

	public void setComidasQueGusta(List<String> comidasQueGusta) {
		this.comidasQueGusta = comidasQueGusta;
	}

	public List<String> getComidasQueDisgusta() {
		return comidasQueDisgusta;
	}

	public void setComidasQueDisgusta(List<String> comidasQueDisgusta) {
		this.comidasQueDisgusta = comidasQueDisgusta;
	}
	
	public boolean leGustanLasComidas(List<String> preferencias)
	{
		return preferencias.stream().allMatch((unaPreferencia -> leGusta(unaPreferencia)));
	}
	
	public boolean leGusta(String preferencia)
	{
		return comidasQueGusta.contains(preferencia);
	}

	public PreferenciaAlimenticia(Long id, List<String> comidasQueGusta,
			List<String> comidasQueDisgusta) {
		super();
		this.id = id;
		this.comidasQueGusta = comidasQueGusta;
		this.comidasQueDisgusta = comidasQueDisgusta;
	}
	
	
	public PreferenciaAlimenticia()
	{
		
		super();
	}
}