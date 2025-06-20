package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Trabajador;

public class TrabajadorBean {
	
	private List<Trabajador> trabajador;  
	private Mensaje  mensaje;
	
	
	public List<Trabajador> getTrabajador() {
		return trabajador;
	}
	public void setTrabajador(List<Trabajador> trabajador) {
		this.trabajador = trabajador;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

}
