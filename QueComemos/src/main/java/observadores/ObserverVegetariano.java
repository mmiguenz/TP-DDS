package observadores;

import queComemos.Consulta;
import queComemos.ObservadorI;
import queComemos.Receta;
import queComemos.Usuario;

public class ObserverVegetariano implements ObservadorI{

	@Override
	public void notificar(Consulta consulta) {
		// TODO Auto-generated method stub
		
	}
	/*
	double cuantosVeganosConsultaron;
	
	public ObserverVegetariano(){
		this.cuantosVeganosConsultaron=0;
	}
	
	@Override
	public void notificar(Usuario usuario, Receta receta){
		if((usuario.getCondicionesPreexistentes()).stream().anyMatch(cond->cond.getNombre().equals("Vegano"))
				&& receta.getDificultad().equals("Dificil")){
			this.cuantosVeganosConsultaron=this.cuantosVeganosConsultaron+1.0;
		}
	}
	
	public double veganosConsultaronRecetasDificiles(){
		return this.cuantosVeganosConsultaron;		
	}
*/
}
