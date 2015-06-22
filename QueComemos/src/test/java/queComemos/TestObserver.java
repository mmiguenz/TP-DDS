package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import observadores.ObserverVegetariano;
import observadores.ObserverVecesConsultada;
import observadores.ObserverPorSexo;
import observadores.ObserverPorHora;

import org.junit.Test;

public class TestObserver {

	@Test
	public void testObserverRecetaPAraVegaoyDificil(){
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();

		comidasQueGustaUsrSaludable.add("frutas");
		comidasQueGustaUsrSaludable.add("Pasta");
		
		comidasQueDisgustaUsr.add("Carne");
		
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Carne");
		
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Vegano("Vegano", comidasProhibidas));
				
		Usuario usuario =new Usuario(2,"pedro","Hombre",LocalDate.parse("1990-01-01"),60.0,1.70,
				"Leve",preferencia,condiciones,null);
		
		QueComemosApp.inadecuados=new ArrayList<CondicionPreexistenteI>();
		
		List<String> comidas= new ArrayList<String>();
		
		Celiaco celiacoQueComemosApp = new Celiaco("Celiaco",comidas);
		QueComemosApp.inadecuados.add(celiacoQueComemosApp);
		
		Diabetico diabeticoQueComemosApp = new Diabetico("Diabetico",comidas);
		QueComemosApp.inadecuados.add(diabeticoQueComemosApp);
		
		List<String> comidasHipertenso= new ArrayList<String>();
		comidasHipertenso.add("Sal");
		comidasHipertenso.add("Caldo");
		Hipertenso hipertensoQueComemosApp = new Hipertenso("Hipertenso",comidasHipertenso);
		QueComemosApp.inadecuados.add(hipertensoQueComemosApp);
		
		List<String> comidasVegano= new ArrayList<String>();
		comidasHipertenso.add("Pollo");
		comidasHipertenso.add("Carne");
		comidasHipertenso.add("Chivito");
		comidasHipertenso.add("Coredero");
		Vegano veganoQueComemosApp = new Vegano("Vegano",comidasVegano);
		QueComemosApp.inadecuados.add(veganoQueComemosApp);
		
		//assertTrue(usuario.validar());
		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		
		Ingrediente sal= new Ingrediente("Sal","grs",10);
		Ingrediente papas= new Ingrediente("Papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		Ingrediente azucar= new Ingrediente("Azucar","grs",150);
		
		ingredientes.add(azucar);
		ingredientes.add(papas);
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
		List<String>	instrucciones = new ArrayList<String>();
		
		Preparacion preparacion=new Preparacion(ingredientes, condimentos, instrucciones);
		
		String dificultad="Dificil";
		String temporada="Verano";
		
		List<Receta> subRecetas=new ArrayList<Receta>();
		
		List<CondicionPreexistenteI> inadecuados = new ArrayList<CondicionPreexistenteI>();
		
		Receta receta = new Receta("Ensalada",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		List<Receta> misRecetas=new ArrayList<Receta>();
		misRecetas.add(receta);
		usuario.setMisRecetas(misRecetas);
		
		ObserverVegetariano obVeg = new ObserverVegetariano();
		receta.agregarObservador(obVeg);
		ObserverVecesConsultada obVC = new ObserverVecesConsultada();
		receta.agregarObservador(obVC);
		ObserverPorSexo obPS = new ObserverPorSexo();
		receta.agregarObservador(obPS);
		ObserverPorHora obPH = new ObserverPorHora();
		receta.agregarObservador(obPH);
		
		
		//assertTrue(subRecetas.isEmpty());
		//assertFalse(receta.contiene("Carne"));
		//assertFalse(usuario.getPreferenciaAlimenticia().getComidasQueDisgusta().stream().anyMatch(comida -> receta.contiene("Carne")));
		//assertTrue(usuario.getCondicionesPreexistentes().stream().allMatch(condicion -> condicion.esAptaReceta(receta)));

		
		List<FiltroI> filtros = new ArrayList<FiltroI>();
		List<Receta> recetasConsultadas=new ArrayList<Receta>();
		List<Receta> recetasQueComemosApp =new ArrayList<Receta>();
		QueComemosApp.recetas=recetasQueComemosApp;
		recetasConsultadas=QueComemosApp.consultarRecetas(usuario, filtros);
		
		//assertTrue((usuario.getCondicionesPreexistentes()).stream().anyMatch(cond->cond.getNombre().equals("Vegano"))
			//	&& receta.getDificultad().equals("Dificil"));
		
		//System.out.print(obVeg.veganosConsultaronRecetasDificiles());
		assertEquals((obVeg.veganosConsultaronRecetasDificiles()),1.0,0.001);
//		//no se porque el test no da, est todo chequeado, incluso lo mostre para ver si debe dar eso pero no da
//		
//		System.out.print(obVC.recetaMasConsultada());
//		
//		System.out.print(obPS.cualConsultaronLosHombres());
//		
//		System.out.print(obPH.consultadasEnLaHora(17));
//		//lo conulte en la hora 18
				
	}
		
	@Test
	public void testObserverRecetaParaVegaoyFacil(){
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();

		comidasQueGustaUsrSaludable.add("frutas");
		comidasQueGustaUsrSaludable.add("Pasta");
		
		comidasQueDisgustaUsr.add("Carne");
		
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Carne");
		
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Vegano("Vegano", comidasProhibidas));
				
		Usuario usuario =new Usuario(2,"pedro","Hombre",LocalDate.parse("1990-01-01"),60.0,1.70,
				"Leve",preferencia,condiciones,null);
		
		QueComemosApp.inadecuados=new ArrayList<CondicionPreexistenteI>();
		
		List<String> comidas= new ArrayList<String>();
		
		Celiaco celiacoQueComemosApp = new Celiaco("Celiaco",comidas);
		QueComemosApp.inadecuados.add(celiacoQueComemosApp);
		
		Diabetico diabeticoQueComemosApp = new Diabetico("Diabetico",comidas);
		QueComemosApp.inadecuados.add(diabeticoQueComemosApp);
		
		List<String> comidasHipertenso= new ArrayList<String>();
		comidasHipertenso.add("Sal");
		comidasHipertenso.add("Caldo");
		Hipertenso hipertensoQueComemosApp = new Hipertenso("Hipertenso",comidasHipertenso);
		QueComemosApp.inadecuados.add(hipertensoQueComemosApp);
		
		List<String> comidasVegano= new ArrayList<String>();
		comidasHipertenso.add("Pollo");
		comidasHipertenso.add("Carne");
		comidasHipertenso.add("Chivito");
		comidasHipertenso.add("Coredero");
		Vegano veganoQueComemosApp = new Vegano("Vegano",comidasVegano);
		QueComemosApp.inadecuados.add(veganoQueComemosApp);
		
		//assertTrue(usuario.validar());
		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		
		Ingrediente sal= new Ingrediente("Sal","grs",10);
		Ingrediente papas= new Ingrediente("Papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		Ingrediente azucar= new Ingrediente("Azucar","grs",150);
		
		ingredientes.add(azucar);
		ingredientes.add(papas);
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
		List<String>	instrucciones = new ArrayList<String>();
		
		Preparacion preparacion=new Preparacion(ingredientes, condimentos, instrucciones);
		
		String dificultad="Facil";
		String temporada="Verano";
		
		List<Receta> subRecetas=new ArrayList<Receta>();
		
		List<CondicionPreexistenteI> inadecuados = new ArrayList<CondicionPreexistenteI>();
		
		Receta receta = new Receta("Ensalada",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		List<Receta> misRecetas=new ArrayList<Receta>();
		misRecetas.add(receta);
		usuario.setMisRecetas(misRecetas);
		
		ObserverVegetariano obVeg = new ObserverVegetariano();
		receta.agregarObservador(obVeg);
		ObserverVecesConsultada obVC = new ObserverVecesConsultada();
		receta.agregarObservador(obVC);
		ObserverPorSexo obPS = new ObserverPorSexo();
		receta.agregarObservador(obPS);
		ObserverPorHora obPH = new ObserverPorHora();
		receta.agregarObservador(obPH);
		
		
		//assertTrue(subRecetas.isEmpty());
		//assertFalse(receta.contiene("Carne"));
		//assertFalse(usuario.getPreferenciaAlimenticia().getComidasQueDisgusta().stream().anyMatch(comida -> receta.contiene("Carne")));
		//assertTrue(usuario.getCondicionesPreexistentes().stream().allMatch(condicion -> condicion.esAptaReceta(receta)));

		
		List<FiltroI> filtros = new ArrayList<FiltroI>();
		List<Receta> recetasConsultadas=new ArrayList<Receta>();
		List<Receta> recetasQueComemosApp =new ArrayList<Receta>();
		QueComemosApp.recetas=recetasQueComemosApp;
		recetasConsultadas=QueComemosApp.consultarRecetas(usuario, filtros);
		
		//assertTrue((usuario.getCondicionesPreexistentes()).stream().anyMatch(cond->cond.getNombre().equals("Vegano"))
			//	&& receta.getDificultad().equals("Dificil"));
		assertEquals((obVeg.veganosConsultaronRecetasDificiles()),0.0,0.001);
				
	}
	
	@Test
	public void testObserverRecetaMasConsultada(){
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();

		comidasQueGustaUsrSaludable.add("frutas");
		comidasQueGustaUsrSaludable.add("Pasta");
		
		comidasQueDisgustaUsr.add("Carne");
		
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Carne");
		
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Vegano("Vegano", comidasProhibidas));
				
		Usuario usuario =new Usuario(2,"pedro","Hombre",LocalDate.parse("1990-01-01"),60.0,1.70,
				"Leve",preferencia,condiciones,null);
		
		QueComemosApp.inadecuados=new ArrayList<CondicionPreexistenteI>();
		
		List<String> comidas= new ArrayList<String>();
		
		Celiaco celiacoQueComemosApp = new Celiaco("Celiaco",comidas);
		QueComemosApp.inadecuados.add(celiacoQueComemosApp);
		
		Diabetico diabeticoQueComemosApp = new Diabetico("Diabetico",comidas);
		QueComemosApp.inadecuados.add(diabeticoQueComemosApp);
		
		List<String> comidasHipertenso= new ArrayList<String>();
		comidasHipertenso.add("Sal");
		comidasHipertenso.add("Caldo");
		Hipertenso hipertensoQueComemosApp = new Hipertenso("Hipertenso",comidasHipertenso);
		QueComemosApp.inadecuados.add(hipertensoQueComemosApp);
		
		List<String> comidasVegano= new ArrayList<String>();
		comidasHipertenso.add("Pollo");
		comidasHipertenso.add("Carne");
		comidasHipertenso.add("Chivito");
		comidasHipertenso.add("Coredero");
		Vegano veganoQueComemosApp = new Vegano("Vegano",comidasVegano);
		QueComemosApp.inadecuados.add(veganoQueComemosApp);
		
		//assertTrue(usuario.validar());
		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		
		Ingrediente sal= new Ingrediente("Sal","grs",10);
		Ingrediente papas= new Ingrediente("Papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		Ingrediente azucar= new Ingrediente("Azucar","grs",150);
		
		ingredientes.add(azucar);
		ingredientes.add(papas);
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
		List<String>	instrucciones = new ArrayList<String>();
		
		Preparacion preparacion=new Preparacion(ingredientes, condimentos, instrucciones);
		
		String dificultad="Dificil";
		String temporada="Verano";
		
		List<Receta> subRecetas=new ArrayList<Receta>();
		
		List<CondicionPreexistenteI> inadecuados = new ArrayList<CondicionPreexistenteI>();
		
		Receta receta = new Receta("Ensalada",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		List<Receta> misRecetas=new ArrayList<Receta>();
		misRecetas.add(receta);
		usuario.setMisRecetas(misRecetas);
		
		ObserverVegetariano obVeg = new ObserverVegetariano();
		receta.agregarObservador(obVeg);
		ObserverVecesConsultada obVC = new ObserverVecesConsultada();
		receta.agregarObservador(obVC);
		ObserverPorSexo obPS = new ObserverPorSexo();
		receta.agregarObservador(obPS);
		ObserverPorHora obPH = new ObserverPorHora();
		receta.agregarObservador(obPH);
		
		
		//assertTrue(subRecetas.isEmpty());
		//assertFalse(receta.contiene("Carne"));
		//assertFalse(usuario.getPreferenciaAlimenticia().getComidasQueDisgusta().stream().anyMatch(comida -> receta.contiene("Carne")));
		//assertTrue(usuario.getCondicionesPreexistentes().stream().allMatch(condicion -> condicion.esAptaReceta(receta)));

		
		List<FiltroI> filtros = new ArrayList<FiltroI>();
		List<Receta> recetasConsultadas=new ArrayList<Receta>();
		List<Receta> recetasQueComemosApp =new ArrayList<Receta>();
		QueComemosApp.recetas=recetasQueComemosApp;
		recetasConsultadas=QueComemosApp.consultarRecetas(usuario, filtros);
		
		//assertTrue((usuario.getCondicionesPreexistentes()).stream().anyMatch(cond->cond.getNombre().equals("Vegano"))
			//	&& receta.getDificultad().equals("Dificil"));
		
		assertTrue(obVC.recetaMasConsultada().equals("Ensalada"));
				
	}
	
	@Test
	public void testObserverRecetaParaLosHombres(){
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();

		comidasQueGustaUsrSaludable.add("frutas");
		comidasQueGustaUsrSaludable.add("Pasta");
		
		comidasQueDisgustaUsr.add("Carne");
		
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Carne");
		
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Vegano("Vegano", comidasProhibidas));
				
		Usuario usuario =new Usuario(2,"pedro","Hombre",LocalDate.parse("1990-01-01"),60.0,1.70,
				"Leve",preferencia,condiciones,null);
		
		QueComemosApp.inadecuados=new ArrayList<CondicionPreexistenteI>();
		
		List<String> comidas= new ArrayList<String>();
		
		Celiaco celiacoQueComemosApp = new Celiaco("Celiaco",comidas);
		QueComemosApp.inadecuados.add(celiacoQueComemosApp);
		
		Diabetico diabeticoQueComemosApp = new Diabetico("Diabetico",comidas);
		QueComemosApp.inadecuados.add(diabeticoQueComemosApp);
		
		List<String> comidasHipertenso= new ArrayList<String>();
		comidasHipertenso.add("Sal");
		comidasHipertenso.add("Caldo");
		Hipertenso hipertensoQueComemosApp = new Hipertenso("Hipertenso",comidasHipertenso);
		QueComemosApp.inadecuados.add(hipertensoQueComemosApp);
		
		List<String> comidasVegano= new ArrayList<String>();
		comidasHipertenso.add("Pollo");
		comidasHipertenso.add("Carne");
		comidasHipertenso.add("Chivito");
		comidasHipertenso.add("Coredero");
		Vegano veganoQueComemosApp = new Vegano("Vegano",comidasVegano);
		QueComemosApp.inadecuados.add(veganoQueComemosApp);
		
		//assertTrue(usuario.validar());
		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		
		Ingrediente sal= new Ingrediente("Sal","grs",10);
		Ingrediente papas= new Ingrediente("Papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		Ingrediente azucar= new Ingrediente("Azucar","grs",150);
		
		ingredientes.add(azucar);
		ingredientes.add(papas);
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
		List<String>	instrucciones = new ArrayList<String>();
		
		Preparacion preparacion=new Preparacion(ingredientes, condimentos, instrucciones);
		
		String dificultad="Dificil";
		String temporada="Verano";
		
		List<Receta> subRecetas=new ArrayList<Receta>();
		
		List<CondicionPreexistenteI> inadecuados = new ArrayList<CondicionPreexistenteI>();
		
		Receta receta = new Receta("Ensalada",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		List<Receta> misRecetas=new ArrayList<Receta>();
		misRecetas.add(receta);
		usuario.setMisRecetas(misRecetas);
		
		ObserverVegetariano obVeg = new ObserverVegetariano();
		receta.agregarObservador(obVeg);
		ObserverVecesConsultada obVC = new ObserverVecesConsultada();
		receta.agregarObservador(obVC);
		ObserverPorSexo obPS = new ObserverPorSexo();
		receta.agregarObservador(obPS);
		ObserverPorHora obPH = new ObserverPorHora();
		receta.agregarObservador(obPH);
		
		
		//assertTrue(subRecetas.isEmpty());
		//assertFalse(receta.contiene("Carne"));
		//assertFalse(usuario.getPreferenciaAlimenticia().getComidasQueDisgusta().stream().anyMatch(comida -> receta.contiene("Carne")));
		//assertTrue(usuario.getCondicionesPreexistentes().stream().allMatch(condicion -> condicion.esAptaReceta(receta)));

		
		List<FiltroI> filtros = new ArrayList<FiltroI>();
		List<Receta> recetasConsultadas=new ArrayList<Receta>();
		List<Receta> recetasQueComemosApp =new ArrayList<Receta>();
		QueComemosApp.recetas=recetasQueComemosApp;
		recetasConsultadas=QueComemosApp.consultarRecetas(usuario, filtros);
		
		//assertTrue((usuario.getCondicionesPreexistentes()).stream().anyMatch(cond->cond.getNombre().equals("Vegano"))
			//	&& receta.getDificultad().equals("Dificil"));
		
		assertTrue(obPS.cualConsultaronLosHombres().equals("Ensalada"));

	}
	
	@Test
	public void testObserverRecetaConsultadaALas18(){
		
		List<String> comidasQueGustaUsrSaludable = new ArrayList<String>();
		List<String> comidasQueDisgustaUsr = new ArrayList<String>();

		comidasQueGustaUsrSaludable.add("frutas");
		comidasQueGustaUsrSaludable.add("Pasta");
		
		comidasQueDisgustaUsr.add("Carne");
		
		PreferenciaAlimenticia preferencia=new PreferenciaAlimenticia(
				comidasQueGustaUsrSaludable, comidasQueDisgustaUsr);
		
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("Carne");
		
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		condiciones.add(new Vegano("Vegano", comidasProhibidas));
				
		Usuario usuario =new Usuario(2,"pedro","Hombre",LocalDate.parse("1990-01-01"),60.0,1.70,
				"Leve",preferencia,condiciones,null);
		
		QueComemosApp.inadecuados=new ArrayList<CondicionPreexistenteI>();
		
		List<String> comidas= new ArrayList<String>();
		
		Celiaco celiacoQueComemosApp = new Celiaco("Celiaco",comidas);
		QueComemosApp.inadecuados.add(celiacoQueComemosApp);
		
		Diabetico diabeticoQueComemosApp = new Diabetico("Diabetico",comidas);
		QueComemosApp.inadecuados.add(diabeticoQueComemosApp);
		
		List<String> comidasHipertenso= new ArrayList<String>();
		comidasHipertenso.add("Sal");
		comidasHipertenso.add("Caldo");
		Hipertenso hipertensoQueComemosApp = new Hipertenso("Hipertenso",comidasHipertenso);
		QueComemosApp.inadecuados.add(hipertensoQueComemosApp);
		
		List<String> comidasVegano= new ArrayList<String>();
		comidasHipertenso.add("Pollo");
		comidasHipertenso.add("Carne");
		comidasHipertenso.add("Chivito");
		comidasHipertenso.add("Coredero");
		Vegano veganoQueComemosApp = new Vegano("Vegano",comidasVegano);
		QueComemosApp.inadecuados.add(veganoQueComemosApp);
		
		//assertTrue(usuario.validar());
		
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		
		Ingrediente sal= new Ingrediente("Sal","grs",10);
		Ingrediente papas= new Ingrediente("Papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		Ingrediente azucar= new Ingrediente("Azucar","grs",150);
		
		ingredientes.add(azucar);
		ingredientes.add(papas);
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
		List<String>	instrucciones = new ArrayList<String>();
		
		Preparacion preparacion=new Preparacion(ingredientes, condimentos, instrucciones);
		
		String dificultad="Facil";
		String temporada="Verano";
		
		List<Receta> subRecetas=new ArrayList<Receta>();
		
		List<CondicionPreexistenteI> inadecuados = new ArrayList<CondicionPreexistenteI>();
		
		Receta receta = new Receta("Ensalada",1524.0,preparacion,dificultad,temporada,subRecetas,inadecuados);
		
		List<Receta> misRecetas=new ArrayList<Receta>();
		misRecetas.add(receta);
		usuario.setMisRecetas(misRecetas);
		
		ObserverVegetariano obVeg = new ObserverVegetariano();
		receta.agregarObservador(obVeg);
		ObserverVecesConsultada obVC = new ObserverVecesConsultada();
		receta.agregarObservador(obVC);
		ObserverPorSexo obPS = new ObserverPorSexo();
		receta.agregarObservador(obPS);
		ObserverPorHora obPH = new ObserverPorHora();
		receta.agregarObservador(obPH);
		
		
		//assertTrue(subRecetas.isEmpty());
		//assertFalse(receta.contiene("Carne"));
		//assertFalse(usuario.getPreferenciaAlimenticia().getComidasQueDisgusta().stream().anyMatch(comida -> receta.contiene("Carne")));
		//assertTrue(usuario.getCondicionesPreexistentes().stream().allMatch(condicion -> condicion.esAptaReceta(receta)));

		
		List<FiltroI> filtros = new ArrayList<FiltroI>();
		List<Receta> recetasConsultadas=new ArrayList<Receta>();
		List<Receta> recetasQueComemosApp =new ArrayList<Receta>();
		QueComemosApp.recetas=recetasQueComemosApp;
		recetasConsultadas=QueComemosApp.consultarRecetas(usuario, filtros);
		
		//assertTrue((usuario.getCondicionesPreexistentes()).stream().anyMatch(cond->cond.getNombre().equals("Vegano"))
			//	&& receta.getDificultad().equals("Dificil"));
		
		assertEquals(obPH.consultadasEnLaHora(LocalDateTime.now().getHour()),1.0,0.001);
				
	}

	
}
