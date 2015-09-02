package builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import queComemos.CondicionPreexistenteI;
import queComemos.GustosSobreAlimentos;
import queComemos.Receta;
import queComemos.Usuario;

public class UsuarioBuilder {
	private Integer usuarioID;
	private String nombre;
	private String sexo;
	private LocalDate fechaNacimiento;
	private Double peso;
	private Double estatura;
	private String rutina;
	private List<String> comidasQueGusta;
	private List<String> comidasQueDisgusta;
	private GustosSobreAlimentos preferenciaAlimenticia;
	private List<CondicionPreexistenteI> condicionesPreexistentes;
	private List<Receta> misRecetas;
	private List<Receta> favoritas;
	
	public UsuarioBuilder(){
		this.usuarioID=0;
		this.nombre="";
		this.sexo="";
		this.fechaNacimiento=LocalDate.parse("2100-01-01");
		this.peso=0.0;
		this.estatura=0.0;
		this.rutina="";
		this.comidasQueGusta=new ArrayList<String>();
		this.comidasQueDisgusta=new ArrayList<String>();
		//this.preferenciaAlimenticia=new PreferenciaAlimenticia(this.comidasQueGusta,this.comidasQueDisgusta);
		this.condicionesPreexistentes= new ArrayList<CondicionPreexistenteI>();
		this.misRecetas=new ArrayList<Receta>();
		this.favoritas=new ArrayList<Receta>();
		
	}
	
	public void setUsuarioID(Integer id){
		this.usuarioID=id;
	}
	
	public void setNombre(String n){
		this.nombre=n;
	}
	
	public void setSexo(String s){
		this.sexo=s;
	}
	
	public void setFechaDeNacimiento(String f){
		this.fechaNacimiento=LocalDate.parse(f);
	}
	
	public void setPeso(Double p){
		this.peso=p;
	}
	
	public void setEstatura(Double e){
		this.estatura=e;
	}
	
	public void setRutina(String r){
		this.rutina=r;
	}
	
	public void setComidasQueGusta(String c){
		if(!(c==null)){
			this.comidasQueGusta.add(c);
		}
	}
	
	public void setComidasQueGusta(List<String> c){
		if(!(c==null)){
			this.comidasQueGusta.addAll(c);
		}
	}
	
	public void setComidasQueDisgusta(String c){
		if(!(c==null)){
			this.comidasQueDisgusta.add(c);
		}
	}
	
	public void setComidasQueDisgusta(List<String> c){
		if(!(c==null)){
			this.comidasQueDisgusta.addAll(c);
		}
	}
	
	private void setPreferenciaAlimenticia(List<String>cg,List<String>cdg){
		if(!(cg==null)){
			this.preferenciaAlimenticia.setComidasQueGusta(cg);
		}
		if(!(cdg==null)){
			this.preferenciaAlimenticia.setComidasQueDisgusta(cdg);
		}
	}
	
	public void setCondicionesPreexistentes(CondicionPreexistenteI cp){
		if(!(cp==null)){
			this.condicionesPreexistentes.add(cp);
		}
	}
	
	public void setCondicionesPreexistentes(List<CondicionPreexistenteI> cp){
		if(!(cp==null)){
			this.condicionesPreexistentes.addAll(cp);
		}
	}
	
	public void setMisRecetas(Receta r){
		if(!(r==null)){
			this.misRecetas.add(r);
		}
	}
	
	public void setMisRecetas(List<Receta> r){
		if(!(r==null)){
			this.misRecetas.addAll(r);
		}
	}
	
	public void setFavoritas(Receta r){
		if(!(r==null)){
			this.favoritas.add(r);
		}
	}
	
	public void setFavoritas(List<Receta> r){
		if(!(r==null)){
			this.favoritas.addAll(r);
		}
	}
	
	public boolean validar() {
		return this.tieneCamposObligatorios() && this.nombre.length() > 4
				&& this.fechaNacimiento.isBefore(LocalDate.now())
				&& this.validaCondicionesPreexistentes();
	}
	
	private boolean tieneCamposObligatorios() {
		return this.nombre != null && this.rutina != null && this.peso != null
				&& this.estatura != null && this.fechaNacimiento != null;
	}
	
	public boolean validaCondicionesPreexistentes() {
		if (this.condicionesPreexistentes.isEmpty()){
			return true;
		}else{
			return this.condicionesPreexistentes.stream().allMatch(condicion -> 
				condicion.subSanaCondicionBuilder(this.rutina,this.peso,this.comidasQueGusta));
		}			
	}
	
	public Usuario crearUsuario() throws Exception{
		if(validar()){
			this.preferenciaAlimenticia=new GustosSobreAlimentos(this.comidasQueGusta,this.comidasQueDisgusta);
			Usuario usuario= new Usuario(this.usuarioID,
					this.nombre,
					this.sexo,
					this.fechaNacimiento,
					this.peso,
					this.estatura,
					this.rutina,
					this.preferenciaAlimenticia,
					this.condicionesPreexistentes,
					this.misRecetas);
			usuario.setFavoritas(this.favoritas);
			return usuario;
		}else{
			//falta lanzar la excepcion si no valida
			throw new Exception("No se pudo crear el usuario");
		}
	}
	

}