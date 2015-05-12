package queComemos;

import java.util.Set;

public class Diabetico extends CondicionPreexistente {

	public Diabetico(String nombre, Set<String> comidasProhibidas) {
		super(nombre, comidasProhibidas);

	}

	public boolean subSanaCondicion(Usuario usuario) {
		return usuario.getRutina() == "Activa" && usuario.getPeso() <= 70.0;

	}

	public static boolean esRecomendable(double calorias, Receta subReceta,
			Preparacion preparacion) {
		if (subReceta == null)
			return !(preparacion.contiene("Azucar"));
		else
			return !(preparacion.contiene("Azucar"))
					|| !(subReceta.contiene("Azucar"));

	}

	public boolean validar(Usuario usr) {
		return usr.validarDiabetico();

	}

}