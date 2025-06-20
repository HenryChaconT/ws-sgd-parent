package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class DocumentosAnexoBean {
  private List<DatosAnexoBean>  anexos;
  private Mensaje  mensaje;

public List<DatosAnexoBean> getAnexos() {
	return anexos;
}
public void setAnexos(List<DatosAnexoBean> anexos) {
	this.anexos = anexos;
}
public Mensaje getMensaje() {
	return mensaje;
}
public void setMensaje(Mensaje mensaje) {
	this.mensaje = mensaje;
}
}
