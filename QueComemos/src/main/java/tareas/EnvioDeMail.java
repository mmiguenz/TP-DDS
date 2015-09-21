package tareas;

import java.util.List;

import repositorios.Consulta;
import interfaces.FiltroI;
import interfaces.ProcesamientoI;
import interfaces.TareaI;

public class EnvioDeMail implements TareaI{

	private Consulta consulta;
	
	
	
	public EnvioDeMail(Consulta consulta){
		
		this.consulta= consulta;
		
	}
	
	@Override
	public void cumplir() {
		
		Mail mail  = new Mail(consulta);
		mail.enviar();		
				
		
	}
	
	
	

	private class Mail{
		
		private List<FiltroI> filtros;
		private ProcesamientoI procesamiento;
		private Long cantidadDeResultados;
		
		public Mail(Consulta consulta)
		{
			
			this.filtros = consulta.getFiltros();
			this.procesamiento = consulta.getProcesamientoPosterior();
			this.cantidadDeResultados = consulta.cantidadRecetasResultado();
			
		}
		
		
		public void enviar()
		{
			
			
		}
		
		
		
		
	}
	 
	
	
}
