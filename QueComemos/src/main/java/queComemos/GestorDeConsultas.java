package queComemos;

import java.util.ArrayList;
import java.util.List;


public class GestorDeConsultas {
	
	private static GestorDeConsultas gestor;
	private List<ObservadorI> observadores;
	private Estadistica estadistica;
	
	
	
	
	
	public static GestorDeConsultas getInstance()
	{
		gestor = gestor!=null?gestor: new GestorDeConsultas();		
		 
		 return gestor;
		
	}
	
	
	public GestorDeConsultas()
	{
		
		observadores = new ArrayList<ObservadorI>();
		estadistica = new Estadistica() ;
		
		
	}
	
	
	public void agregarObservador(ObservadorI obsNuevo){
		this.observadores.add(obsNuevo);
	}
	
	
	
	
	public void notificarObservadores(Consulta unaConsulta){
		
		
		this.observadores.forEach(obs->obs.notificar(unaConsulta));
	}
	




public void consultarRecetas (Consulta consulta )
{	
	
	 List<Receta> recetasEncontradas = new ArrayList<>(); 
	
	if(consulta.getProcesamientoPosterior() == null)	
		recetasEncontradas = QueComemosApp.consultarRecetas(consulta.getUsr(), consulta.getFiltros());
	else 
		recetasEncontradas = QueComemosApp.consultarRecetas(consulta.getUsr(), consulta.getFiltros(),consulta.getProcesamientoPosterior());
	
			
	 consulta.setResultadoConsulta(recetasEncontradas);
	 	 
	 notificarObservadores(consulta);
	 
		
	
}


public Estadistica getEstadistica()
{
	return estadistica ;
	
	
}


}
