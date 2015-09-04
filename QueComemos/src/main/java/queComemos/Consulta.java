package queComemos;

import java.util.ArrayList;
import java.util.List;

public class Consulta {
	
	
	private List<FiltroI> filtros ;
	private ProcesamientoI procesamientoPosterior;
	private Usuario usr ;
	private List<Receta> resultadoConsulta;
	

	
	
	




	public Consulta(Usuario usr)
	{
				
		this.filtros = new ArrayList<FiltroI>();
		this.usr = usr;	
		this.resultadoConsulta= new ArrayList<Receta>();
						
	}
	
	
	public void agregarFiltro(FiltroI unFiltro ){
		
		this.filtros.add(unFiltro);
			
	}
	
	
	public void establecerProcesamientoPosterior(ProcesamientoI procesamientoPosterior)
	{
		this.procesamientoPosterior = procesamientoPosterior;		
		
	}
	
	
	public void  consultarRecetas()
	{
		
		 GestorDeConsultas.getInstance().consultarRecetas(this);
		
		
	}
	
	public List<FiltroI> getFiltros() {
		return filtros;
	}


	public ProcesamientoI getProcesamientoPosterior() {
		return procesamientoPosterior;
	}


	public Usuario getUsr() {
		return usr;
	}
	
	
	
	
	public List<Receta> obtenerResultadoConsulta() {
		return resultadoConsulta;
	}


	public void setResultadoConsulta(List<Receta> resultadoConsulta) {
		this.resultadoConsulta = resultadoConsulta;
	}


	public Integer cantidadRecetasResultado() {
		
	 return  this.resultadoConsulta.size();
	}


	
}
