package builderUsuario;

import java.time.LocalDate;

public class UsuarioParaAprobacionDeSolicitudes extends UsuarioBuilder {

	
	
	public UsuarioParaAprobacionDeSolicitudes(String nombre)
	{
		super();
		
		
		nombre(nombre);
		fechaNacimiento(LocalDate.parse("1994-05-06"));
		peso(10.0);
		estatura(20.5);
		rutina("Intensa");
				
		
		
		
	}
	
	
	
	
}
