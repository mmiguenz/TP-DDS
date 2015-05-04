package queComemos;

import java.util.List;

public class InfNutricional {
	
	private double calorias;
	private List<String> inadecuados;
	
	

	
	public boolean isOk()
	{
		return calorias >10 && calorias <5000;
		
		
	}
}
