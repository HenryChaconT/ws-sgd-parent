package pe.gob.serfor.osutd.sgd.repository.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ProveedorBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.PersonaJuridica;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.ProveedorBean;
//import pe.gob.serfor.wssisged.logic.model.PersonaJuridica;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;

public interface ProveedorDao {

	MethodResponseBean<String> insProveedor(ProveedorBean proveedor);

	Boolean ProveedorExiste(String NuRuc);

	String updProveedor(ProveedorBean proveedor, String codProveedor);

	void insertar(PersonaJuridica proveedor) throws Exception;

	void actualizar(PersonaJuridica proveedor) throws Exception;
}
