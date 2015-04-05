import java.util.Date;
public class TestReporteDeVentas
{
	public static void main (String args[]){
		
		Negocio negocio = new Negocio();
		Origen origArg = new Origen("argentina");
		Origen origFranc= new Origen("francia");
		Saco saco = new Saco(300,origArg);
		Pantalon pantalon = new Pantalon(250,origArg);
		Camisa camisa = new Camisa(200,origFranc);
		
		
		negocio.vender(saco,3,new Date("2015/04/01"));
		negocio.vender(camisa,10, new Date("2015,04/01"));
		negocio.vender(pantalon,2);
		Date f = new Date("2015/04/02");
		Reporte reporte=  negocio.registroDeVentas(f);
		
		reporte.mostrar();
		
		
		
		
	}

}
