package tareas;

import java.util.List;

import receta.Receta;
import repositorios.Consulta;
import usuario.Usuario;
import interfaces.TareaI;

public class MarcarComoFavoritas implements TareaI {

	private Usuario usuario ;
	private List<Receta> recetas;


	public MarcarComoFavoritas(Consulta unaConsulta) {
		this.usuario = unaConsulta.getUsr();
		this.recetas = unaConsulta.obtenerResultadoConsulta();
	}

	@Override
	public void cumplir() {
		
		usuario.getFavoritas().addAll(recetas);
		
		

	}

}
