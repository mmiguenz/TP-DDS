package queComemos;

import java.util.List;

public class ConsultaRepoExtAd {
	
	private String nombre;
	private String dificultad; 
	private List<String> palabrasClaves;

	
	public ConsultaRepoExtAd(String  nombre, String  dificultad, List<String> palabrasClaves)
	{
		
		this.nombre = nombre;
		this.dificultad = dificultad;
		this.palabrasClaves= palabrasClaves;
		
		
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDificultad() {
		return dificultad;
	}


	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}


	public List<String> getPalabrasClaves() {
		return palabrasClaves;
	}


	public void setPalabrasClaves(List<String> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}

}
