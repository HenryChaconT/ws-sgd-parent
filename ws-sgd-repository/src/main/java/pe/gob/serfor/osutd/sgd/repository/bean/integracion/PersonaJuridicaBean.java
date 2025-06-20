package pe.gob.serfor.osutd.sgd.repository.bean.integracion;


import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.PersonaJuridica;

public class PersonaJuridicaBean {

	private PersonaJuridica personajuridica;
	private Mensaje mensaje;
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public PersonaJuridica getPersonajuridica() {
		return personajuridica;
	}
	public void setPersonajuridica(PersonaJuridica personajuridica) {
		this.personajuridica = personajuridica;
	}
	
}
