package queComemos;


public class Perfil {
	
	Usuario user;
	
	public Perfil(Usuario user){
		this.user = user;
	}
	
	public boolean validate(Perfil solicitudPendiente) throws FalloValidacionException{
		if(solicitudPendiente.user.validar()){
			return true;
		}
		else 
			throw new FalloValidacionException();
	}

	

}