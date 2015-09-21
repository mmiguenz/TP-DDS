package tareas;

import interfaces.TareaI;

import java.util.ArrayList;
import java.util.List;

public class GestorDeTareas {
	
	public static GestorDeTareas gestorDeTareas;
	private List<TareaI> tareas;
	
	



	public static GestorDeTareas  getInstance()
	{
		if (gestorDeTareas == null)
		{
			gestorDeTareas = new GestorDeTareas();
						
		}
		
	return gestorDeTareas;	
		
		
	}
	
	
	private GestorDeTareas()
	{
		tareas = new ArrayList<TareaI>();
		 
	}
	
	
	public void  agregarTarea(TareaI unaTarea)
	{
		
		tareas.add(unaTarea);
		
	}
	
	
	public void procesarTareasPendientes()
	{
		tareas.forEach(tarea -> tarea.cumplir());		
		tareas.clear();
		
	}
	
	public List<TareaI> getTareas() {
		return tareas;
	}
	
	

}
