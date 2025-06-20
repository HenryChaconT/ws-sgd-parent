package pe.gob.serfor.osutd.sgd.repository.bean.ubigeo;

import java.util.ArrayList;
import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ubigeo;

public class UbigeoResponse {
	private Mensaje mensaje;
	private List<Ubigeo>  lstUbigeo;

	public UbigeoResponse() {		
		super();
		this.lstUbigeo = new ArrayList<Ubigeo>();
		this.mensaje = new Mensaje();
		// TODO Auto-generated constructor stub
	}

	public List<Ubigeo> getLstUbigeo() {
		return lstUbigeo;
	}

	public void setLstUbigeo(List<Ubigeo> lstUbigeo) {
		this.lstUbigeo = lstUbigeo;
	}

	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}

}
