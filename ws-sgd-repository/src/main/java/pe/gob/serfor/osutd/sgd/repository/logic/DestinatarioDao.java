package pe.gob.serfor.osutd.sgd.repository.logic;

import java.util.List;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Destinatario;

//import pe.gob.serfor.wssisged.logic.model.Destinatario;

public interface DestinatarioDao {

	public List<Destinatario> buscarDestinatario(int anio, String nuEmi) throws Exception;

}
