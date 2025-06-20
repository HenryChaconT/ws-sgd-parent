package pe.gob.serfor.osutd.sgd.repository.bean.ubigeo;

import java.util.ArrayList;
import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.UsuarioAcceso;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ubigeo;

public class UbigeoResponseProvincia {
	private Mensaje mensaje;
	private List<Ubigeo>  lstUbigeo;
	private UsuarioAcceso usuarioAcceso;
	
	

	public UsuarioAcceso getUsuarioAcceso() {
		return usuarioAcceso;
	}

	public void setUsuarioAcceso(UsuarioAcceso usuarioAcceso) {
		this.usuarioAcceso = usuarioAcceso;
	}

	public UbigeoResponseProvincia() {
		super();
		// TODO Auto-generated constructor stub
		this.lstUbigeo = new ArrayList<Ubigeo>();
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
