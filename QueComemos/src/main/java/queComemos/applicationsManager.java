package queComemos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationsManager {
	
	
	private List<Usuario> pendientesDeAprobacion;
	private  List<Aprobacion> motivosRechazos;
	private CriterioAprobacionI criterio;
	
	
	
	
	
	
	
	public ApplicationsManager(List<Usuario> pendientesDeAprobacion, CriterioAprobacionI criterio) {

		this.pendientesDeAprobacion = pendientesDeAprobacion;
		this.criterio= criterio;
		this.motivosRechazos  = new ArrayList<Aprobacion>();
		this.procesarSolicitudes();
	}


	
	
	
	  public  List<Usuario> getPendientesDeAprobacion() {
		return pendientesDeAprobacion;
	}



	public  void setPendientesDeAprobacion(
			List<Usuario> pendientesDeAprobacion) {
		this.pendientesDeAprobacion = pendientesDeAprobacion;
	}



	public  List<Aprobacion> getMotivosRechazos() {
		return this.motivosRechazos;
	}



	public  void setMotivosRechazos(List<Aprobacion> motivosRechazos) {
		this.motivosRechazos = motivosRechazos;
	}





	public  void  procesarSolicitudes()
	  {
		  
		List<Aprobacion> aprobaciones=  new ArrayList<Aprobacion>() ;
		  pendientesDeAprobacion.forEach(solicitud -> aprobaciones.add(criterio.aprobar(solicitud)));
		  
		  
		  motivosRechazos.addAll(aprobaciones.stream().filter(aprobacion -> ! aprobacion.isOk() ).collect(Collectors.toList()));
		  
		  
	  }







	
	
	

}
