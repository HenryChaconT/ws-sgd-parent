package pe.gob.serfor.osutd.sgd.repository.bean.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;

public class DocumentoAnexoBean {
  private AnexoBean  anexo;
  private Mensaje  mensaje;
public AnexoBean getAnexo() {
	return anexo;
}
public void setAnexo(AnexoBean anexo) {
	this.anexo = anexo;
}
public Mensaje getMensaje() {
	return mensaje;
}
public void setMensaje(Mensaje mensaje) {
	this.mensaje = mensaje;
}
}
