package queComemos;

public class ObservadorDeVeganos{
	private int cuantosConsultaron;
	
	public void notificar(){
		cuantosConsultaron=cuantosConsultaron+1;
	}
	
	public int cuantosConsultaron(){
		
	return cuantosConsultaron;
	}

}
