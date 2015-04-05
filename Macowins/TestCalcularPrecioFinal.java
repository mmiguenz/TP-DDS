
public class TestCalcularPrecioFinal
{
public static void main(String[] arg ){
		
		Prenda.setValorX(140);
		Origen origen=new Origen("francia");
		Saco saco = new Saco(100,origen);
		Negocio negocio= new Negocio();
		
		
	double precioFinal=	negocio.calcularPrecioFinal(saco);
	
	System.out.println(precioFinal);
				
							
		
	}

}
