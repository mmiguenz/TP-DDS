package queComemos;

import java.util.List;

public class CriteroApruebaSiEsJuan implements CriterioAprobacionI {

	@Override
	public Aprobacion aprobar(Usuario usr) {
		
		Aprobacion aprobacion = new Aprobacion(usr);
		
		if (  usr.getNombre().contains("juan"))
		{
			
			aprobacion.setOk(true);
			
			
			
		}
		else 
		{
			aprobacion.setOk(false);
			aprobacion.setMotivo("No se llama juan");
			
			
			
		}
		
		
		return  aprobacion;
		
		
		
		}
	
	

}
