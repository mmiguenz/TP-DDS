package queComemos;

import java.util.HashMap;
import java.util.Hashtable;

public class Estadistica {

	private Hashtable<Integer, Integer> cantidadRecetasConsultadasPorHora;



	
		public Estadistica()
		{
			cantidadRecetasConsultadasPorHora = new Hashtable<Integer,Integer>();
			
			
			
		}
public void actualizarRecetasPorHora(Integer hora, Integer cantidadRecetas)
{
	
	Integer cantidadAnterior = cantidadRecetasConsultadasPorHora.get(hora);
	cantidadAnterior = (cantidadAnterior ==null)? 0 : cantidadAnterior;
	
	cantidadRecetasConsultadasPorHora.put(hora,  cantidadRecetas + cantidadAnterior );
	
	
	
}



public Integer obtenerRecetasPorHora(Integer hora)

{
	
 Integer cantidadConsultadas = cantidadRecetasConsultadasPorHora.get(hora); 
 
	return cantidadConsultadas==null?0:cantidadConsultadas;
	
	
}




}