package ar.edu.utn.frba.dds.tpAnual.queComemos;

import java.util.List;
import java.util.Objects;

public class GustosSobreAlimentos {
	
	private List<String> comidasQueGusta;
	private List<String> comidasQueDisgusta;
	
	public GustosSobreAlimentos(List<String> comidasQueGusta,List<String> comidasQueDisgusta) {
	
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
}