package pe.gob.serfor.osutd.sgd.repository.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Destinatario;

//import pe.gob.serfor.wssisged.logic.model.Destinatario;
//import pe.gob.serfor.wssisged.logic.model.Documento;
//import pe.gob.serfor.wssisged.logic.model.DocumentoDigital;

public interface DocTrabajadorDao {

	public List<Destinatario> busquecarDestinatario(Integer idEntidad);
}
