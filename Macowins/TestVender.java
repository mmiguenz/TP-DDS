
public class TestVender
{
	public static void main (String args[]){
		
		Negocio negocio = new Negocio();
		Origen origArg = new Origen("argentina");
		Origen origFranc= new Origen("francia");
		Saco saco = new Saco(300,origArg);
		Pantalon pantalon = new Pantalon(250,origArg);
		Camisa camisa = new Camisa(200,origFranc);
		
		
		negocio.vender(saco,3);
		negocio.vender(camisa,10);
		negocio.vender(pantalon,2);
		
		negocio.mostrarVentas(); // muestro porPantalla las fechas de las ventas y informo cantidad.
		
	}

}
