package pe.gob.serfor.osutd.sgd.service.logic;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.CiudadanoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocExternoRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.OtroOrigenBean2;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.PersonaJuridicaBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.response.TipoDocumentoMP;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ciudadano;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.OtroOrigen;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.PersonaJuridica;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.CiudadanoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocExternoRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.OtroOrigenBean2;
//import pe.gob.serfor.wssisged.logic.bean.integracion.PersonaJuridicaBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.response.TipoDocumentoMP;
//import pe.gob.serfor.wssisged.logic.model.Ciudadano;
//import pe.gob.serfor.wssisged.logic.model.OtroOrigen;
//import pe.gob.serfor.wssisged.logic.model.PersonaJuridica;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;

public interface MesaPartesService {

	public MethodResponseBean<String> Add_DocumentoExternoRecep(DocExternoRecepBean docExternoRecepBean,
			Usuario usuario, String idAplicacion) throws Exception;

	public TipoDocumentoMP listarDocumentosMP() throws Exception;

	public CiudadanoBean registrarCiudadano(Ciudadano ciudadano, String codUsuario) throws Exception;

	public PersonaJuridicaBean registrarPersonaJuridica(PersonaJuridica personaJuridica, String codUsuario)
			throws Exception;

	public OtroOrigenBean2 registrarOtroOrigen(OtroOrigen otroOrigen) throws Exception;
}
