package queComemos;

import java.util.List;

public class applicationsManager {
	
	
	public static List<Usuario> pendientesDeAprobacion;
	public static List<Aprobacion> motivosRechazos;
	
	
	
	
	  public static void  procesarSolicitudes()
	  {
		  
		  pendientesDeAprobacion.forEach( solicitud -> aprobar(solicitud));
		  
		  
	  }



	private static void aprobar(Usuario solicitud) {
		
		if (cumpleCondicion(solicitud).isOk())
		{	
		RepoUsuarios.add(solicitud);
		}else{
			
			
			motivosRechazos.add(cumpleCondicion(solicitud));
		 
			
			
		}
		
		
		
	}



	@SuppressWarnings("unused")
	private static Aprobacion cumpleCondicion(Usuario solicitud) {
		
		Aprobacion aprobacion =  new Aprobacion(solicitud);
		
		if(true) 
		{
			
			aprobacion.setOk(true);
			
			
		}else 
		{
			
			aprobacion.setOk(false);
			aprobacion.setMotivo("AlgunMotivo");
			
			
			
		}
		
		return aprobacion;
		
	}
	  

	
	

}
