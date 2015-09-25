package procesamientos;

import interfaces.CriterioOrdenamientoI;


import java.util.Comparator;

import java.util.List;

import java.util.stream.Collectors;

import receta.Receta;

public class OrdenarPorCalorias implements CriterioOrdenamientoI {

	@Override
	public List<Receta> ordernar(List<Receta> recetas) {
		
		Comparator<Receta> porCalorias = (r1,r2) -> Double.compare(r1.getCalorias(), r2.getCalorias());
		
	return	recetas.stream().sorted(porCalorias).collect(Collectors.toList());
		
	}
	
}
	
	
		
		




