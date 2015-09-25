package logConsultas;

import repositorios.Consulta;
import repositorios.Recetario;
import interfaces.LoggerI;

public class LoggerMock implements LoggerI {



	@Override
	public void loguear(Consulta consulta) {
		
		Recetario.consultasLogueadas.add(consulta);
	}

}
