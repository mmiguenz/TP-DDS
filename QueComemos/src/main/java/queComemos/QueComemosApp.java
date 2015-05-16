package queComemos;

import java.util.Set;

public class QueComemosApp {
	
	public static Set<Usuario> usuarios ; 
	public static Set<Receta> recetas;
	public static Set<CondicionPreexistenteI> inadecuados;


	public static Set<CondicionPreexistenteI> calcularInadecuadosParaReceta(Receta receta)
	{
		return (Set<CondicionPreexistenteI>) inadecuados.stream().filter(inadecuado -> inadecuado.esAptaReceta(receta));
		
		
	}

}

