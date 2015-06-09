package queComemos;

import java.util.List;

public class PreferenciaAlimenticia {
	
	private List<String> comidasQueGusta;
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
	
	
	public boolean leGustaAlguna(List<String> preferencias)
	{
		return preferencias.stream().anyMatch((unaPreferencia -> this.leGusta(unaPreferencia)));
		
		
		
	}
	
	
	private boolean leGusta(String preferencia)
	{
		
		return comidasQueGusta.stream().anyMatch((unaComida ->unaComida==preferencia));
		
		
		
	}
	
	

}
