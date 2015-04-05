/* Esta es la clase origen, que se planteo en el curso. Que se decia que este debia conocer la tasa de imp.
 * le aplica una logica media pija, pero sirve para el ejercicio.
 *   */ 

public class Origen
{	
	private double dblTasaImp;
	
	public double getTasaImp(){
		
		return this.dblTasaImp;
		
	}
	
	public void setTasaImp(double tasa){
		this.dblTasaImp=tasa;
		
		
	}
	
	// este es el constructor de la clase, aca recibe por parametro un pais y ya setea la tasa de imp . 
	public Origen(String pais){
		pais = pais.toLowerCase();
		
		if (pais.equals("argentina")){
			
		setTasaImp(1);
						
			
		} else {
			setTasaImp(30);
			
		}
		
	
			
		
	}
	

}
