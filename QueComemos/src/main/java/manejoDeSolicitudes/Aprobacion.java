package manejoDeSolicitudes;

import usuario.Usuario;

public class Aprobacion {
	
	private Usuario usuario;
	private String motivo;
	private boolean isOk;
	
	
	
	
	
	public Aprobacion(Usuario usuario) {
		
		this.usuario = usuario;

	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	
	
	
	
	
}
