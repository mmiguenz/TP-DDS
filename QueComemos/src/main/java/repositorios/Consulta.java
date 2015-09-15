package repositorios;

import interfaces.FiltroI;
import interfaces.ProcesamientoI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import condicionesPreexistentes.Vegano;
import receta.Receta;
import usuario.Usuario;

public class Consulta {
	
	
	private List<FiltroI> filtros ;
	private ProcesamientoI procesamientoPosterior;
	private Usuario usr ;
	private List<Receta> resultadoConsulta;
	private LocalDateTime horaConsulta;
	




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
		
		
		this.setHoraConsulta(LocalDateTime.now());
		Recetario.observadores.forEach(obervador -> obervador.notificar(this));
				
		 
		
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


	public Long cantidadRecetasResultado() {
		
	 return  this.resultadoConsulta.stream().count();
	}


	public LocalDateTime getHoraConsulta() {
		return horaConsulta;
	}


	public void setHoraConsulta(LocalDateTime horaConsulta) {
		this.horaConsulta = horaConsulta;
	}


	public boolean estaEnRangoHorario(LocalDateTime horaConsultaDesde,LocalDateTime horaConsultaHasta) {
	
		return (horaConsulta.isAfter(horaConsultaDesde) && horaConsulta.isBefore(horaConsultaHasta)) 
				|| (horaConsulta.getHour() == horaConsultaDesde.getHour());
		
	}


	public boolean esVeganoConsultandoDificil() {
		
		return (usr.getCondicionesPreexistentes().stream().anyMatch(condicion -> condicion.getClass().equals(Vegano.class)))
				&& this.resultadoConsulta.stream().anyMatch(receta -> receta.getDificultad().toLowerCase().equals("dificil"));
	}


	
}