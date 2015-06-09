package queComemos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenarPorCalorias implements CriterioOrdenamientoI {

	@Override
	public List<Receta> ordernar(List<Receta> recetas) {
		
		Comparator<Receta> porCalorias = (r1,r2) -> Double.compare(r1.getCalorias(), r2.getCalorias());
		
	return	recetas.stream().sorted(porCalorias).collect(Collectors.toList());
		
	}
	
	
	public static void main (String ars [])
	{
		
		 {
			
			List<Receta>	recetas = new ArrayList<Receta>();
				
				
				List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
				List<String> comidasQueGustaUsrNoSaludable = new ArrayList<String>();
				List<String> comidasQueDisgustaUsr = new ArrayList<String>();

				comidasQueGustaUsrSaludable.add("Fruta");
				comidasQueGustaUsrSaludable.add("Carne");
				comidasQueGustaUsrSaludable.add("Pasta");

				comidasQueGustaUsrNoSaludable.add("Fritos");
				comidasQueGustaUsrNoSaludable.add("Snacks");

				
				
				comidasQueDisgustaUsr.add("Verduras");

				PreferenciaAlimenticia preferenciaAlimenticiaSaludable = new PreferenciaAlimenticia(
						comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
				PreferenciaAlimenticia preferenciaAlimenticiaNoSaludable = new PreferenciaAlimenticia(
						comidasQueGustaUsrNoSaludable, comidasQueDisgustaUsr);

				List<String>comidasProhibidas = new ArrayList<String>();
				comidasProhibidas.add("Pan");

				
				List<String>	instrucciones = new ArrayList<String>();
				List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
				List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
				List<Receta> subRecetas=new ArrayList<Receta>();
				
				List<CondicionPreexistenteI> inadecuados=new ArrayList<CondicionPreexistenteI>();
				List<String>comidasProhibidasH= new ArrayList<String>();
				comidasProhibidasH.add("caldo");
				
				
				Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
				Vegano vegano = new Vegano("vegano",comidasProhibidas);
				Hipertenso hipertenso = new Hipertenso("hipertenso",comidasProhibidasH);
				Diabetico diabetico = new Diabetico("diabetico",comidasProhibidas);
				
				
				
					
					instrucciones.add("Preparar");
					instrucciones.add("Revolver");
					instrucciones.add("Hornear");
					
					Ingrediente sal= new Ingrediente("Sal","grs",100);
					Ingrediente carne= new Ingrediente("Carne","kg",2);
					Ingrediente papas= new Ingrediente("papa","kg",3);
					Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
					Ingrediente azucar= new Ingrediente("Azucar","grs",150);
					
					ingredientes.add(azucar);
					ingredientes.add(carne);
					ingredientes.add(papas);
					condimentos.add(mayonesa);
					condimentos.add(sal);
				
				     Preparacion preparacion = new Preparacion(ingredientes,condimentos,instrucciones);
					 String dificultad = "Baja";
					 String temporada= "Verano";
				
				QueComemosApp.inicializar();
				
				
				
				
			
			recetas.add( new Receta("PolloConPapasB", 1.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
			recetas.add( new Receta("CarneAlHornoA",2.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
			recetas.add( new Receta("CarneAlHornoB",3.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
			recetas.add( new Receta("PolloConPapasB", 5.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
			recetas.add( new Receta("CarneAlHornoC",8.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
			recetas.add( new Receta("PolloConPapasC", 4.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
			recetas.add( new Receta("CarneAlHornoD",10.0,preparacion,dificultad,temporada,subRecetas,inadecuados));
			recetas.add( new Receta("PolloConPapasA", 9.0,preparacion,dificultad,temporada,subRecetas,inadecuados));		
			
			Comparator<Receta> porCalorias = (r1,r2) -> Double.compare(r1.getCalorias(), r2.getCalorias());
			
			
		
			 
			 String n = recetas.stream().sorted(porCalorias).findFirst().get().getNombre();
			 
			 
			 for(Receta receta :  recetas.stream().sorted(porCalorias).collect(Collectors.toList()))
			 {
				 
				 System.out.println(receta.getNombre() + "  " + receta.getCalorias());
				 
				 
			 }
			 
			
			
	}
	
	
	}
	
		}	




