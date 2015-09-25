package logConsultas;


import repositorios.Consulta;
import usuario.Usuario;

public class ConsultaMock extends Consulta {

	public ConsultaMock(Usuario usr) {
		super(usr);

				
	}
	
	
	public Long cantidadRecetasResultado() {
		
		 return Long.parseLong("110");
		}



}
