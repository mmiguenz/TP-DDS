import java.util.Date;
/* Hice esta clase para correr y probrar la comparacion con las fechas */

public class TestComparaFecha
{
	
	@SuppressWarnings("deprecation")
	public static void main(String args[]){
		Date fecha1 = new Date("2015/02/01") ; 
		Date fecha2 = new Date("2014/02/01") ;
		
		
		System.out.println( " "+comparaFecha(fecha1,fecha2)+ "\n "+fecha1 +  "\t" + fecha2+" "  );
		
		
		
		
	}





@SuppressWarnings("deprecation")
private static boolean comparaFecha(Date f1, Date f2 ){
	
	
	return (f1.getYear() ==f2.getYear())&& (f1.getMonth()==f2.getMonth()) && (f1.getDay() == f2.getDay());  
	
}

}
	
	
