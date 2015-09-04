package observadores;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import queComemos.Consulta;
import queComemos.GestorDeConsultas;
import queComemos.ObservadorI;
import queComemos.Receta;
import queComemos.Usuario;

public class ObserverPorHora implements ObservadorI{

	
	public ObserverPorHora(){
				
			
	}
	
	@Override
	public void notificar(Consulta consulta){
		Calendar calendario=Calendar.getInstance();
		int hora=calendario.get(Calendar.HOUR_OF_DAY);
		

		GestorDeConsultas.getInstance().getEstadistica().actualizarRecetasPorHora(hora, consulta.cantidadRecetasResultado());
	
	}
	
	
	

}
