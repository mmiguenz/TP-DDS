package queComemos;

import java.util.Set;

public class Hipertenso extends CondicionPreexistente {

	public Hipertenso(String nombre, Set<String> comidasProhibidas) {
		super(nombre, comidasProhibidas);

	}

	public boolean subSanaCondicion(Usuario usuario) {

		return usuario.getRutina() == "Intensiva";
	}

	public static boolean esRecomendable(Receta subReceta,
			Preparacion preparacion) {

		if (subReceta == null)
			return !((preparacion.contiene("Caldo")) || (preparacion
					.contiene("Sal")));
		else
			return (!((preparacion.contiene("Caldo")) || (preparacion
					.contiene("Sal"))) || !((subReceta.contiene("Caldo")) || (subReceta
					.contiene("Sal"))));

	}

	public boolean validar(Usuario usr) {
		return usr.validarHipertenso();

	}
	
	public boolean esAptaReceta(Usuario usr, Receta receta){
		return usr.esAptaRecetaHipertenso(receta);
	}

}