package repositorios;

import interfaces.FiltroI;
import interfaces.ProcesamientoI;

import java.util.ArrayList;
import java.util.List;

import receta.Receta;
import usuario.Usuario;

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
		
		resultadoConsulta =   Recetario.listarTodas();
		
		filtros.forEach(filtro -> resultadoConsulta = filtro.filtrar(resultadoConsulta, this.usr));
		
		if (this.procesamientoPosterior != null )
			resultadoConsulta = procesamientoPosterior.procesar(resultadoConsulta) ;
		
		
		 
		
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