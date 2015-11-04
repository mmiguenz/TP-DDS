package tareas;

import java.util.stream.Collectors;

import repositorios.Recetario;
import interfaces.LoggerI;
import interfaces.TareaI;

public class LoguearConsultas implements TareaI {

	private LoggerI  logger ;
	
	
	public LoguearConsultas(LoggerI logger)
	{
		this.logger  = logger;
		
		
	}
	
	@Override
	public void cumplir() {
		
		
		
		
		Recetario
		.getInstance()
		.listarConsultas()
		.stream()
		.filter(consulta -> consulta.cantidadRecetasResultado() > Long.parseLong("100"))
		.forEach(consulta -> logger.loguear(consulta));
		
		
	}
	
	
	

}
