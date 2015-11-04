package observadores;


import repositorios.Consulta;
import repositorios.Recetario;
import interfaces.ObservadorI;

public class ObservadorDeConsultas implements ObservadorI {

	@Override
	public void notificar(Consulta unaConsulta) {
				
		Recetario.getInstance().agregarConsulta(unaConsulta);		
		
		
	}
	
	

}
