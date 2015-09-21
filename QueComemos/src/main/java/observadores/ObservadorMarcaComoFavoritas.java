package observadores;

import repositorios.Consulta;
import tareas.GestorDeTareas;
import tareas.MarcarComoFavoritas;
import interfaces.ObservadorI;
import interfaces.TareaI;

public class ObservadorMarcaComoFavoritas implements ObservadorI {

	@Override
	public void notificar(Consulta unaConsulta) {
		
		if (unaConsulta.getUsr().isMarcaFavoritasLasConsultas())
		{
			TareaI marcarFavoritas = new MarcarComoFavoritas(unaConsulta);
			
			GestorDeTareas.getInstance().agregarTarea(marcarFavoritas);
			
			
		}
		
	}
	
	
	
	

}
