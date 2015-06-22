package observadores;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import queComemos.ObservadorI;
import queComemos.Receta;
import queComemos.Usuario;

public class ObserverPorHora implements ObservadorI{
	public Map<Integer,Integer> recetasConsultadasPorHora;
	
	public ObserverPorHora(){
		recetasConsultadasPorHora=new HashMap<Integer,Integer>();
	}
	
	@Override
	public void notificar(Usuario usuario, Receta receta){
		Calendar calendario=Calendar.getInstance();
		int hora=calendario.get(Calendar.HOUR_OF_DAY);
		
		Integer valorActual = this.recetasConsultadasPorHora.get(hora);
		if(valorActual==null){
			valorActual=0;
		}
		this.recetasConsultadasPorHora.put(hora, valorActual+1);
		
	}
	
	public Integer consultadasEnLaHora(Integer hora){
		if(this.recetasConsultadasPorHora.containsKey(hora)){
			return this.recetasConsultadasPorHora.get(hora);
		}
		return 0;
	}

}
