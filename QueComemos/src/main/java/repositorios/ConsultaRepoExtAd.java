package repositorios;

import java.util.List;

import queComemos.entrega3.dominio.Dificultad;

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


	public Dificultad dificultad() {
		
	if (dificultad != null)
	{
		
	switch (dificultad){
	case "facil": return Dificultad.FACIL;
	case "mediana": return Dificultad.MEDIANA;
	case "dificil": return Dificultad.DIFICIL;
	default: return null; 
	} 
	
	
	}
	
	return null;
		
	}
		
}
