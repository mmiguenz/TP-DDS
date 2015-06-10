package queComemos;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import condicionesPreexistentes.Celiaco;
import condicionesPreexistentes.Diabetico;
import condicionesPreexistentes.Hipertenso;
import condicionesPreexistentes.Vegano;
import filtros.FiltroParaUsuariosConSobrepeso;
import filtros.FiltroPreparacionCara;
import filtros.FiltroRechazaTodo;

public class TestConsultarRecetasDeUnUsuario {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testConsultaSobreRecetasVisiblesDeUsuario() {
		

		
		List<String> comidasQueGusta = new ArrayList<String>();
		List<String> comidasQueDisgusta = new ArrayList<String>();
		comidasQueGusta.add("pollo");
		comidasQueGusta.add("carne");
		comidasQueDisgusta.add("tomate");
		comidasQueDisgusta.add("pescado");
		
		PreferenciaAlimenticia preferenciaAlimenticia = new PreferenciaAlimenticia(comidasQueGusta,comidasQueDisgusta);
		
		
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("pan");
		comidasProhibidas.add("salame");
		Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
		Hipertenso hipertenso= new Hipertenso("hipertenso",comidasProhibidas);
		Vegano vegano = new Vegano("vegano",new ArrayList<String>());
		Diabetico diabetico =  new Diabetico("Diabetico",new ArrayList<String>());
		
		condiciones.add(hipertenso);
		condiciones.add(celiaco);
		
		
		Usuario usr = new Usuario("Juan","Masculino",LocalDate.parse("1994-08-05"),90.0,175.0,"Leve",preferenciaAlimenticia,condiciones,new ArrayList<Receta>());
		
		List<String>	instrucciones = new ArrayList<String>();
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		List<Receta> subRecetas=new ArrayList<Receta>();
		

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
		
	 Preparacion    preparacion = new Preparacion(ingredientes,condimentos,instrucciones);

		QueComemosApp.inicializar();

		QueComemosApp.inadecuados.add(diabetico);
		QueComemosApp.inadecuados.add(celiaco);
		QueComemosApp.inadecuados.add(hipertenso);
		QueComemosApp.inadecuados.add(vegano);
		
		
		Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,"baja","verano",subRecetas,condiciones);
		QueComemosApp.recetas.add(receta);

		
		
		List<FiltroI> filtros = new ArrayList<FiltroI>();
		FiltroI filtroPorGusto = new FiltroParaUsuariosConSobrepeso();
		FiltroI filtroPreparacionCara = new FiltroPreparacionCara();
		FiltroI filtroSobrePeso= new FiltroParaUsuariosConSobrepeso();
		
		filtros.add(filtroSobrePeso);
		filtros.add(filtroPreparacionCara);
		filtros.add(filtroPorGusto);
		
		List<Receta> resultadoConsulta =QueComemosApp.consultarRecetas(usr, filtros);
		
		assertTrue(resultadoConsulta.contains(receta));
		
		
		

	}
	
	@Test
	public void tesConsultaRecetasDeUsuarioUsoFiltroRechazaTodo()
	{
		List<String> comidasQueGusta = new ArrayList<String>();
		List<String> comidasQueDisgusta = new ArrayList<String>();
		comidasQueGusta.add("pollo");
		comidasQueGusta.add("carne");
		comidasQueDisgusta.add("tomate");
		comidasQueDisgusta.add("pescado");
		
		PreferenciaAlimenticia preferenciaAlimenticia = new PreferenciaAlimenticia(comidasQueGusta,comidasQueDisgusta);
		
		
		List<CondicionPreexistenteI> condiciones = new ArrayList<CondicionPreexistenteI>();
		List<String> comidasProhibidas = new ArrayList<String>();
		comidasProhibidas.add("pan");
		comidasProhibidas.add("salame");
		Celiaco celiaco = new Celiaco("celiaco",comidasProhibidas);
		Hipertenso hipertenso= new Hipertenso("hipertenso",comidasProhibidas);
		Vegano vegano = new Vegano("vegano",new ArrayList<String>());
		Diabetico diabetico =  new Diabetico("Diabetico",new ArrayList<String>());
		
		condiciones.add(hipertenso);
		condiciones.add(celiaco);
		
		
		Usuario usr = new Usuario("Juan","Masculino",LocalDate.parse("1994-08-05"),90.0,175.0,"Leve",preferenciaAlimenticia,condiciones,new ArrayList<Receta>());
		
		List<String>	instrucciones = new ArrayList<String>();
		List<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
		List<Ingrediente>condimentos = new ArrayList<Ingrediente>();
		List<Receta> subRecetas=new ArrayList<Receta>();
		

		instrucciones.add("Preparar");
		instrucciones.add("Revolver");
		instrucciones.add("Hornear");
		
		Ingrediente sal= new Ingrediente("Sal","grs",100);
		Ingrediente carne= new Ingrediente("Carne","kg",2);
		Ingrediente papas= new Ingrediente("papa","kg",3);
		Ingrediente mayonesa= new Ingrediente("Mayonesa","grs",100);
		Ingrediente azucar= new Ingrediente("Azucar","grs",150);
		Ingrediente lomo = new Ingrediente("lomo","kg",1);
		
		ingredientes.add(azucar);
		ingredientes.add(carne);
		ingredientes.add(papas);
		condimentos.add(mayonesa);
		condimentos.add(sal);
		
	 Preparacion    preparacion = new Preparacion(ingredientes,condimentos,instrucciones);

		QueComemosApp.inicializar();

		QueComemosApp.inadecuados.add(diabetico);
		QueComemosApp.inadecuados.add(celiaco);
		QueComemosApp.inadecuados.add(hipertenso);
		QueComemosApp.inadecuados.add(vegano);
		
		
		Receta receta = new Receta("CarneAlHorno",1524.0,preparacion,"baja","verano",subRecetas,condiciones);
		QueComemosApp.recetas.add(receta);

		
		
		List<FiltroI> filtros = new ArrayList<FiltroI>();
		FiltroParaUsuariosConSobrepeso filtroPorGusto = new FiltroParaUsuariosConSobrepeso();
		FiltroPreparacionCara filtroPreparacionCara = new FiltroPreparacionCara();
		FiltroParaUsuariosConSobrepeso filtroSobrePeso= new FiltroParaUsuariosConSobrepeso();
		FiltroI filtroRechazaTodo = new FiltroRechazaTodo();
		
		filtros.add(filtroSobrePeso);
		filtros.add(filtroPreparacionCara);
		filtros.add(filtroPorGusto);
		filtros.add(filtroRechazaTodo);
		
		List<Receta> resultadoConsulta =QueComemosApp.consultarRecetas(usr, filtros);
		
		assertTrue(resultadoConsulta.isEmpty());
		
		
		
	}
	
	
	
	
	
	

}
