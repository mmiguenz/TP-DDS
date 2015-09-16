package builderUsuario;

import java.time.LocalDate;

public class UsuarioParaAprobacionDeSolicitudes extends UsuarioBuilder {

	
	
	public UsuarioParaAprobacionDeSolicitudes(String nombre)
	{
		super();
		
		
		establecerNombre(nombre);
		establecerFechaNacimiento(LocalDate.parse("1994-05-06"));
		establecerPeso(10.0);
		establecerEstatura(20.5);
		establecerRutina("Intensa");
				
		
		
		
	}
	
	
	
	
}
