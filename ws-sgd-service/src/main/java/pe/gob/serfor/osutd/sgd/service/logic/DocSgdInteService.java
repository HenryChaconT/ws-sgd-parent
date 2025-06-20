package pe.gob.serfor.osutd.sgd.service.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.CitizenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocExternoRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoFileBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.OtroOrigenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ProveedorBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.CitizenBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocExternoRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoFileBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.OtroOrigenBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ProveedorBean;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;

public interface DocSgdInteService {

	// EXPEDIENTE
	MethodResponseBean<String> Add_DocumentoExternoRecep(DocExternoRecepBean trxDocExternoRecepBean, Usuario usuario)
			throws Exception;

	MethodResponseBean<String> Update_DocumentoExternoRecep(DocExternoRecepBean trxDocExternoRecepBean, Usuario usuario)
			throws Exception;

	// ANEXO
	String insArchivoAnexo(String coUsu, String pnuAnn, String pnuEmi, DocumentoFileBean pfileAnexo);

	String updArchivoAnexo(String coUsu, String pnuAnn, String pnuEmi, String pnuAne, DocumentoFileBean pfileAnexo);

	// DOC PRINCIPAL

	String cargaDocumentoEmi(String coUsu, String pnuAnn, String pnuEmi, DocumentoFileBean pfileAnexo) throws Exception;

	/// PERSONA JURIDICA
	MethodResponseBean<String> insProveedor(ProveedorBean proveedor);

	String updProveedor(ProveedorBean proveedor, String codUsuario);

	// PERSONA NATURAL
	MethodResponseBean<String> insCiudadano(CitizenBean ciudadano, String codUsuario);

	String updCiudadano(CitizenBean ciudadano, String codUsuario);

	// OTROS ORIGENES - PERSONAS
	MethodResponseBean<String> insOtroOrigen(OtroOrigenBean otroOrigen);

	String updOtroOrigen(OtroOrigenBean otroOrigen);

}
