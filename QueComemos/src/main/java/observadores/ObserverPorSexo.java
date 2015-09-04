package observadores;

import java.util.HashMap;
import java.util.Map;

import queComemos.Consulta;
import queComemos.ObservadorI;
import queComemos.Receta;
import queComemos.Usuario;

public class ObserverPorSexo implements ObservadorI{

	@Override
	public void notificar(Consulta consulta) {
		// TODO Auto-generated method stub
		
	}
	/*
	private Map<Receta,Integer> recetasConsultadasPorHombre;
	private Map<Receta,Integer> recetasConsultadasPorMujer;
	
	public ObserverPorSexo(){
		this.recetasConsultadasPorHombre=new HashMap<Receta,Integer>();
		this.recetasConsultadasPorMujer=new HashMap<Receta,Integer>();
	}
	
	@Override
	public void notificar(Usuario usuario, Receta receta){
		if (usuario.getSexo().equals("Hombre")){
			Integer valorActual = this.recetasConsultadasPorHombre.get(receta);
			if(valorActual==null){
				valorActual=0;
			}
			this.recetasConsultadasPorHombre.put(receta, valorActual+1);
		}
		if (usuario.getSexo().equals("Mujer")){
			Integer valorActual = this.recetasConsultadasPorMujer.get(receta);
			if(valorActual==null){
				valorActual=0;
			}
			this.recetasConsultadasPorMujer.put(receta, valorActual+1);
		}
	}
	
	public String cualConsultaronLosHombres(){
		
		String nombreMasConsultada=null;
		Integer vecesMasConsultada=0;
		
		for(Receta receta: this.recetasConsultadasPorHombre.keySet()){
			if(this.recetasConsultadasPorHombre.get(receta)>vecesMasConsultada){
				vecesMasConsultada=this.recetasConsultadasPorHombre.get(receta);
				nombreMasConsultada=receta.getNombre();
			}
		}
		
		return nombreMasConsultada;
	}
	
	public String cualConsultaronLasMujeres(){
		
		String nombreMasConsultada=null;
		Integer vecesMasConsultada=0;
		
		for(Receta receta: this.recetasConsultadasPorMujer.keySet()){
			if(this.recetasConsultadasPorMujer.get(receta)>vecesMasConsultada){
				vecesMasConsultada=this.recetasConsultadasPorMujer.get(receta);
				nombreMasConsultada=receta.getNombre();
			}
		}
		
		return nombreMasConsultada;
	}
*/
}
