package observadores;

import java.util.HashMap;
import java.util.Map;

import queComemos.Receta;
import queComemos.ObservadorI;
import queComemos.Usuario;

public class ObserverVecesConsultada implements ObservadorI{
	private Map<Receta,Integer> recetasConsultadas;
	
	public ObserverVecesConsultada(){
		this.recetasConsultadas=new HashMap<Receta,Integer>();
	}
	
	@Override
	public void notificar(Usuario usuario, Receta receta){
		Integer valorActual = this.recetasConsultadas.get(receta);
		if(valorActual==null){
			valorActual=0;
		}
		this.recetasConsultadas.put(receta, valorActual+1);
	}
	
	public String recetaMasConsultada(){
		String nombreMasConsultada=null;
		Integer vecesMasConsultada=0;
		
		for(Receta receta: this.recetasConsultadas.keySet()){
			if(this.recetasConsultadas.get(receta)>vecesMasConsultada){
				vecesMasConsultada=this.recetasConsultadas.get(receta);
				nombreMasConsultada=receta.getNombre();
			}
		}
		
		return nombreMasConsultada;
		
	}

}
