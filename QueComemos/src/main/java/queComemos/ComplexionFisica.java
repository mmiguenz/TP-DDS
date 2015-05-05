package queComemos;

public class ComplexionFisica {
	
	private double peso;
	private double estatura;
	
	
	public ComplexionFisica(double peso,double estatura)
	{
		this.peso=peso;
		this.estatura=estatura;
		
		
		
	}
	
	
	public double indiceMasaCorporal() {
		return this.peso/(this.estatura*this.estatura);
	}
	

}
