import java.util.Collection;

/* Esta es la clase Reporte. 
 * La uso para delegar todo lo que esta relaciona con motrar los datos por pantalla . 
 * En el caso de que haga que cambiar algo de como se muestran los datos, se cambia aca . */



public class Reporte
{
	public Collection<Venta> getVentasR()
	{
		return ventasR;
	}


	public void setVentasR(Collection<Venta> ventasR)
	{
		this.ventasR=ventasR;
	}


	public double getAcumVentas()
	{
		return acumVentas;
	}


	public void setAcumVentas(double acumVentas)
	{
		this.acumVentas=acumVentas;
	}


	private Collection<Venta> ventasR;
	private double acumVentas ;
	

	public Reporte(double acumVentas,Collection<Venta> ventasR){
		
		setAcumVentas(acumVentas);
		setVentasR(ventasR);		
		
	}
	
	public void mostrar(){
		
			
		System.out.println("Se hicieron un total de: "+ventasR.size()+ " Ventas\n\n");
		System.out.println("Se recaudo un total de : "+ acumVentas+ " pesos. \n");
		
	}
	
}
