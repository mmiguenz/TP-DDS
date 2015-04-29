package queComemos;

public class Usuario{
	
	private double peso;
	private double estatura;

	public Usuario(double peso,double estatura){
		
		this.peso = peso;
		this.estatura = estatura;
	}

	public double indiceMasaCorporal() {
		return this.peso/(this.estatura*this.estatura);
	}

}
