package observadores;


import repositorios.Consulta;
import repositorios.Recetario;
import interfaces.ObservadorI;

public class ObservadorVeganoConsultaRecetaDificil implements ObservadorI {

	@Override
	public void notificar(Consulta unaConsulta) {

		
		
	   if 
	   (unaConsulta.esVeganoConsultandoDificil())
		   Recetario.veganosConsultandoRecetasDificiles.add(unaConsulta.getUsr());
				
	}
	
	
	
	
	

}
