package queComemos;

import java.util.List;

public interface Observers {
	
	public void notificar (List<Receta> recetas);
	public void notificar (Receta recetas);

}
