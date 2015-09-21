package observadores;

import java.util.List;

import repositorios.Consulta;
import tareas.EnvioDeMail;
import tareas.GestorDeTareas;
import interfaces.ObservadorI;
import interfaces.TareaI;

public class ObservadorParaElEnvioDeMails implements ObservadorI {
	
	private List<String> usuariosParaNotificarViaMail;

	
	public ObservadorParaElEnvioDeMails(List<String> usuariosParaNotificarViaMail)
	{
						
		this.usuariosParaNotificarViaMail= usuariosParaNotificarViaMail;
		
		
	}
	
	@Override
	public void notificar(Consulta unaConsulta) {
		
		if(usuariosParaNotificarViaMail.contains(unaConsulta.getUsr().getNombre()))
		{
		TareaI envioDeMail = new EnvioDeMail(unaConsulta);
		
		GestorDeTareas.getInstance().agregarTarea(envioDeMail);
		}
	}

}
