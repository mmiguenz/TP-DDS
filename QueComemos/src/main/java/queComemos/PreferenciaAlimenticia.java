package queComemos;

import java.util.Set;

public class PreferenciaAlimenticia {
	
	private Set<String> comidasQueGusta;
	private Set<String> comidasQueDisgusta;
	
	
	public PreferenciaAlimenticia(Set<String> comidasQueGusta,Set<String> comidasQueDisgusta) {
	
		this.comidasQueGusta = comidasQueGusta;
		this.comidasQueDisgusta = comidasQueDisgusta;
	}


	public Set<String> getComidasQueGusta() {
		return comidasQueGusta;
	}


	public void setComidasQueGusta(Set<String> comidasQueGusta) {
		this.comidasQueGusta = comidasQueGusta;
	}


	public Set<String> getComidasQueDisgusta() {
		return comidasQueDisgusta;
	}


	public void setComidasQueDisgusta(Set<String> comidasQueDisgusta) {
		this.comidasQueDisgusta = comidasQueDisgusta;
	}
	
	
	
	
	

}
