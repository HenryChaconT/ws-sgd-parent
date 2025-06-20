package pe.gob.serfor.osutd.sgd.service.logic.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.AnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ArchivoPrincipal;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ArchivoPrincipalBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.CabeceraDocumentoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DatosAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DatosDocumentoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DatosDocumentoSgdBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DatosDocumentoSgdMPBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DestinatarioBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentoPrincipalBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentosAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentosBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.DocumentosMPBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosMPBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosSgdBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Destinatario;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoInterno;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoMesaPartes;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoSgd;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoSgdMP;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.EnvioExterno;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.logic.AnexoDocumentoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.DestinatarioDao;
import pe.gob.serfor.osutd.sgd.repository.logic.DocSgdDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Fecha;
import pe.gob.serfor.osutd.sgd.service.logic.DocSgdService;

//import pe.gob.serfor.wssisged.logic.bean.AnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.ArchivoPrincipal;
//import pe.gob.serfor.wssisged.logic.bean.ArchivoPrincipalBean;
//import pe.gob.serfor.wssisged.logic.bean.CabeceraDocumentoBean;
//import pe.gob.serfor.wssisged.logic.bean.DatosAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.DatosDocumentoBean;
//import pe.gob.serfor.wssisged.logic.bean.DatosDocumentoSgdBean;
//import pe.gob.serfor.wssisged.logic.bean.DatosDocumentoSgdMPBean;
//import pe.gob.serfor.util.Fecha;
//import pe.gob.serfor.wssisged.logic.bean.DestinatarioBean;
//import pe.gob.serfor.wssisged.logic.bean.DocumentoPrincipalBean;
//import pe.gob.serfor.wssisged.logic.bean.DocumentosAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.DocumentosBean;
//import pe.gob.serfor.wssisged.logic.bean.DocumentosMPBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametroDocumentoSgdBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosMPBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosSgdBean;
//import pe.gob.serfor.wssisged.logic.dao.AnexoDocumentoDao;
//import pe.gob.serfor.wssisged.logic.dao.DestinatarioDao;
//import pe.gob.serfor.wssisged.logic.dao.DocAnexoDao;
//import pe.gob.serfor.wssisged.logic.dao.DocSgdDao;
//import pe.gob.serfor.wssisged.logic.model.Anexo;
//import pe.gob.serfor.wssisged.logic.model.Destinatario;
//import pe.gob.serfor.wssisged.logic.model.DocumentoDigital;
//import pe.gob.serfor.wssisged.logic.model.DocumentoInterno;
//import pe.gob.serfor.wssisged.logic.model.DocumentoMesaPartes;
//import pe.gob.serfor.wssisged.logic.model.DocumentoSgd;
//import pe.gob.serfor.wssisged.logic.model.DocumentoSgdMP;
//import pe.gob.serfor.wssisged.logic.model.EnvioExterno;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.service.DocSgdService;
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;
//import pe.gob.serfor.wssisged.utils.Fecha;
//import pe.gob.serfor.wssisged.utils.MjeValidacionProperties;
//import pe.gob.serfor.wssisged.utils.Util;

@Component
@Transactional(readOnly = true)
public class DocSgdServiceImpl implements DocSgdService {
	protected final Log log = LogFactory.getLog(getClass());
	@Autowired
	DocSgdDao docSgdDao;
	@Autowired
	DestinatarioDao destinatarioDao;
	@Autowired
	AnexoDocumentoDao docAnexoDao;

	@Override
	public DocumentoPrincipalBean buscarDocumento(ParametrosSgdBean parametro) throws Exception {
		DocumentoPrincipalBean documento = null;
		List<DocumentoSgd> lsDocumentos = docSgdDao.buscarDocumento(parametro);
		if (lsDocumentos.isEmpty() || lsDocumentos == null) {

			documento = new DocumentoPrincipalBean();
			documento.setMensaje(new Mensaje());
			documento.getMensaje().setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
			documento.getMensaje().setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
			return documento;
		}

		if (lsDocumentos.size() > 1) {

			documento = new DocumentoPrincipalBean();
			documento.setMensaje(new Mensaje());
			documento.getMensaje().setCodigo(ConstantesUtil.ERR_MAS_DE_UNA_COINCIDENCIA);
			documento.getMensaje().setDesMensaje(ConstantesUtil.ERR_MAS_DE_UNA_COINCIDENCIA_DES);
			return documento;
		}

		DocumentoSgd docTemp = lsDocumentos.get(0);
		documento = new DocumentoPrincipalBean();
		documento.setDatosDocumento(new DatosDocumentoBean());

		documento.getDatosDocumento().setRemitente(docTemp.getRemitente());
		documento.getDatosDocumento().setNuExpediente(docTemp.getNumExpediente());
		documento.getDatosDocumento().setNumDocumento(docTemp.getNumDoc());
		documento.getDatosDocumento().setTipoDocumento(docTemp.getDesDocumento());
		documento.getDatosDocumento().setAsunto(docTemp.getAsunto());
		documento.getDatosDocumento().setFeEmi(Fecha.getFecha(docTemp.getFecEmision()));
		documento.getDatosDocumento().setNumEmi(docTemp.getNuEmi());
		documento.getDatosDocumento().setAnio(docTemp.getAnio());

		List<Destinatario> lstDestinatario = destinatarioDao.buscarDestinatario(
				Integer.parseInt(documento.getDatosDocumento().getAnio()), documento.getDatosDocumento().getNumEmi());

		if (!lstDestinatario.isEmpty() && lstDestinatario != null) {
			List<DestinatarioBean> lstDestinatarioFin = new ArrayList<DestinatarioBean>();
			for (Destinatario destinatario : lstDestinatario) {
				DestinatarioBean destinatariotemp = new DestinatarioBean();
				destinatariotemp.setDependenciaDestino(destinatario.getDependenciaDestino());
				destinatariotemp.setDestinatario(destinatario.getDestinatario());
				lstDestinatarioFin.add(destinatariotemp);

			}
			documento.setLstDestinatario(lstDestinatarioFin);
		}

		return documento;
	}

	@Override
	public DocumentosMPBean listarDocumentosMesaPartes(ParametrosMPBean parametro) throws Exception {
		DocumentosMPBean documento = null;
		DatosDocumentoSgdMPBean documentoFinal = null;
		List<DocumentoSgdMP> lsDocumentos = null;
		if (parametro.getTipoBusqueda() != null
				&& (parametro.getTipoBusqueda().equals("6") || parametro.getTipoBusqueda().equals("7"))) { // carnet de
																											// extranjeria
			lsDocumentos = docSgdDao.buscarDocumentosMesaPartesOtros(parametro);

		} else {

			lsDocumentos = docSgdDao.buscarDocumentosMesaPartes(parametro);
		}

		if (lsDocumentos.isEmpty() || lsDocumentos == null) {

			documento = new DocumentosMPBean();
			documento.setMensaje(new Mensaje());
			documento.getMensaje().setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
			documento.getMensaje().setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
			return documento;
		}
		documento = new DocumentosMPBean();
		documento.setDocumentos(new ArrayList<DatosDocumentoSgdMPBean>());

		for (DocumentoSgdMP docTemp : lsDocumentos) {
			documentoFinal = new DatosDocumentoSgdMPBean();
			documentoFinal.setRemitente(docTemp.getRemitente());
			documentoFinal.setNuExpediente(docTemp.getNumExpediente());
			documentoFinal.setNumDocumento(docTemp.getNumDoc());
			documentoFinal.setTipoDocumento(docTemp.getDesDocumento());
			documentoFinal.setAsunto(docTemp.getAsunto());
			documentoFinal.setTipoBandeja(docTemp.getBandejaorigen());

			documentoFinal.setEstado(docTemp.getEstadoDocDes());
			documentoFinal.setNumEmi(docTemp.getNuEmi());
			documentoFinal.setAnio(docTemp.getAnio());
			documentoFinal.setCoGru(docTemp.getCoGru());
			documentoFinal.setDependenciaOrigen(docTemp.getDependenciaOrigen());
			documentoFinal.setFecEmision(Fecha.getFecha(docTemp.getFecEmision()));
			documentoFinal.setNuDniCiudadano(docTemp.getNuDniCiudadano());
			documentoFinal.setNombreCiudadano(docTemp.getNombreCiudadano());
			documentoFinal.setNuDniRepLegal(docTemp.getNuDniRepLegal());
			documentoFinal.setNombreRepLegal(docTemp.getNombreRepLegal());
			documentoFinal.setTiEmi(docTemp.getTiEmi());
			documentoFinal.setNumRuc(docTemp.getNuRucEmi());
			documentoFinal.setCodTipDoc(docTemp.getCodTipDoc());
			documentoFinal.setNuDocOtrOri(docTemp.getNuDocOtrOri());

			// nuRucEmi

			List<Destinatario> lstDestinatario = destinatarioDao
					.buscarDestinatario(Integer.parseInt(documentoFinal.getAnio()), documentoFinal.getNumEmi());

			if (!lstDestinatario.isEmpty() && lstDestinatario != null) {
				List<DestinatarioBean> lstDestinatarioFin = new ArrayList<DestinatarioBean>();
				for (Destinatario destinatario : lstDestinatario) {
					DestinatarioBean destinatariotemp = new DestinatarioBean();
					destinatariotemp.setDependenciaDestino(destinatario.getDependenciaDestino());
					destinatariotemp.setDestinatario(destinatario.getDestinatario());
					lstDestinatarioFin.add(destinatariotemp);

				}
				documentoFinal.setLstDestinatario(lstDestinatarioFin);
			}

			// documentoFinal.setListaDocFinal();
			List<EnvioExterno> listaExterno = docSgdDao.buscarTipoEnvioExterno(
					Integer.parseInt(documentoFinal.getAnio()), documentoFinal.getNuExpediente());
			for (EnvioExterno envio : listaExterno) {
				String[] cadema = envio.getNomArchivoPrincipal().split("\\$");
				envio.setNomArchivoPrincipal(cadema[2].trim() + cadema[7].substring(1, cadema[7].length()));

			}
			documentoFinal.setListaDocFinal(listaExterno);
			documento.getDocumentos().add(documentoFinal);

		}

		return documento;
	}

	@Override
	public DocumentosBean listarDocumento(ParametrosSgdBean parametro) throws Exception {
		DocumentosBean documento = null;
		DatosDocumentoSgdBean documentoFinal = null;

		List<DocumentoSgd> lsDocumentos = docSgdDao.buscarDocumento(parametro);
		if (lsDocumentos == null || lsDocumentos.isEmpty()) {

			documento = new DocumentosBean();
			documento.setMensaje(new Mensaje());
			documento.getMensaje().setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
			documento.getMensaje().setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
			return documento;
		}

		documento = new DocumentosBean();
		documento.setDocumentos(new ArrayList<DatosDocumentoSgdBean>());
		int contador = 0;
		for (DocumentoSgd docTemp : lsDocumentos) {
			documentoFinal = new DatosDocumentoSgdBean();
			if (contador == 0) {
				documento.setCabecera(new CabeceraDocumentoBean());
				documento.getCabecera().setNumFolio(docTemp.getNumFolios());
				documento.getCabecera().setFecEmision(Fecha.getFecha(docTemp.getFecEmision()));
				documento.getCabecera().setBandejaOrigen(docTemp.getBandejaorigen());
				documento.getCabecera().setAsunto(docTemp.getAsunto());
				documento.getCabecera().setNumEmi(docTemp.getNuEmi());
				documento.getCabecera().setRemitente(docTemp.getRemitente());
				documento.getCabecera().setNumExpediente(docTemp.getNumExpediente());
				documento.getCabecera().setEstado(docTemp.getEstadoDocDes());
			}
			contador++;

			documentoFinal.setRemitente(docTemp.getRemitente());
			documentoFinal.setNuExpediente(docTemp.getNumExpediente());
			documentoFinal.setNumDocumento(docTemp.getNumDoc());
			documentoFinal.setTipoDocumento(docTemp.getDesDocumento());
			documentoFinal.setAsunto(docTemp.getAsunto());
			documentoFinal.setTipoBandeja(docTemp.getBandejaorigen());

			documentoFinal.setEstado(docTemp.getEstadoDocDes());
			documentoFinal.setNumEmi(docTemp.getNuEmi());
			documentoFinal.setAnio(docTemp.getAnio());
			documentoFinal.setCoGru(docTemp.getCoGru());
			documentoFinal.setDependenciaOrigen(docTemp.getDependenciaOrigen());
			documentoFinal.setFecEmision(Fecha.getFecha(docTemp.getFecEmision()));
			documentoFinal.setTiEnvMsj(docTemp.getTiEnvMsj());
			documentoFinal.setTipoEnvioExterno(docTemp.getTipoEnvioExterno());
			documentoFinal.setCodTipDoc(docTemp.getCodTipDoc());

			List<Destinatario> lstDestinatario = destinatarioDao
					.buscarDestinatario(Integer.parseInt(documentoFinal.getAnio()), documentoFinal.getNumEmi());

			if (!lstDestinatario.isEmpty() && lstDestinatario != null) {
				List<DestinatarioBean> lstDestinatarioFin = new ArrayList<DestinatarioBean>();
				for (Destinatario destinatario : lstDestinatario) {
					DestinatarioBean destinatariotemp = new DestinatarioBean();
					destinatariotemp.setDependenciaDestino(destinatario.getDependenciaDestino());
					destinatariotemp.setDestinatario(destinatario.getDestinatario());
					lstDestinatarioFin.add(destinatariotemp);

				}
				documentoFinal.setLstDestinatario(lstDestinatarioFin);
			}
			documento.getDocumentos().add(documentoFinal);

		}
		//System.out.println(documento.getDocumentos().get(0).toString());
		return documento;
	}

	@Override
	public DocumentoPrincipalBean buscarDocumentoBandeja(ParametrosSgdBean parametro) throws Exception {
		DocumentoPrincipalBean documento = null;
		List<DocumentoMesaPartes> lsDocumentos = docSgdDao.buscarDocumentoMesaPartes(parametro);

		List<DocumentoInterno> lsDocumentosInterno = docSgdDao.buscarDatosDoumentoInterno(parametro);

		if ((lsDocumentos.isEmpty() || lsDocumentos == null)
				& (lsDocumentosInterno.isEmpty() || lsDocumentosInterno == null)) {

			documento = new DocumentoPrincipalBean();
			documento.setMensaje(new Mensaje());
			documento.getMensaje().setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
			documento.getMensaje().setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
			return documento;
		}
		if (lsDocumentos.size() > 1 || lsDocumentosInterno.size() > 1) {
			documento.setMensaje(new Mensaje());
			documento.getMensaje().setCodigo(ConstantesUtil.ERR_MAS_DE_UNA_COINCIDENCIA);
			documento.getMensaje().setDesMensaje(ConstantesUtil.ERR_MAS_DE_UNA_COINCIDENCIA_DES);
			return documento;

		}
		if (lsDocumentos.size() == 1 || lsDocumentosInterno.size() == 1) {
			documento.setMensaje(new Mensaje());
			documento.getMensaje().setCodigo(ConstantesUtil.ERR_MAS_DE_UNA_COINCIDENCIA);
			documento.getMensaje().setDesMensaje(ConstantesUtil.ERR_MAS_DE_UNA_COINCIDENCIA_DES);
			return documento;

		}
		if (lsDocumentos.size() == 1) {
			DocumentoMesaPartes docTemp = lsDocumentos.get(0);
			documento = new DocumentoPrincipalBean();
			documento.setDatosDocumento(new DatosDocumentoBean());
			documento.getDatosDocumento().setOrigen(docTemp.getOrigen());
			// documento.getDatosDocumento().setRemitente(docTemp.getRemitente());
			documento.getDatosDocumento().setNuExpediente(docTemp.getNuExpediente());
			documento.getDatosDocumento().setNumDocumento(docTemp.getNuDocumento());
			// documento.getDatosDocumento().setTipoDocumento(docTemp.get());
			documento.getDatosDocumento().setAsunto(docTemp.getAsunto());
			// documento.setMotivo(docTemp.getMotivo());
			documento.getDatosDocumento().setEstado(docTemp.getEstado());
			// documento.getDatosDocumento().setFeEmi(docTemp.getFecEmision());
			documento.getDatosDocumento().setNumEmi(docTemp.getNuEmi());
			documento.getDatosDocumento().setAnio(docTemp.getAnio());

		} else {
			DocumentoInterno docTemp = lsDocumentosInterno.get(0);
			documento = new DocumentoPrincipalBean();
			documento.setDatosDocumento(new DatosDocumentoBean());
			documento.getDatosDocumento().setRemitente(docTemp.getRemitente());
			// documento.getDatosDocumento().setRemitente(docTemp.getRemitente());
			documento.getDatosDocumento().setNuExpediente(docTemp.getNuExpediente());
			documento.getDatosDocumento().setNumDocumento(docTemp.getNuDocumento());
			// documento.getDatosDocumento().setTipoDocumento(docTemp.get());
			documento.getDatosDocumento().setAsunto(docTemp.getAsunto());
			// documento.setMotivo(docTemp.getMotivo());
			documento.getDatosDocumento().setEstado(docTemp.getEstado());
			documento.getDatosDocumento().setNumEmi(docTemp.getNuEmi());
			documento.getDatosDocumento().setAnio(docTemp.getAnio());

		}
		List<Destinatario> lstDestinatario = destinatarioDao.buscarDestinatario(
				Integer.parseInt(documento.getDatosDocumento().getAnio()), documento.getDatosDocumento().getNumEmi());

		if (!lstDestinatario.isEmpty() && lstDestinatario != null) {
			List<DestinatarioBean> lstDestinatarioFin = new ArrayList<DestinatarioBean>();
			for (Destinatario destinatario : lstDestinatario) {
				DestinatarioBean destinatariotemp = new DestinatarioBean();
				destinatariotemp.setDependenciaDestino(destinatario.getDependenciaDestino());
				destinatariotemp.setDestinatario(destinatario.getDestinatario());
				lstDestinatarioFin.add(destinatariotemp);

			}
			documento.setLstDestinatario(lstDestinatarioFin);
		}

		return documento;
	}

	@Override
	public DocumentosAnexoBean listarAnexos(ParametrosBusqBean parametro) throws Exception {
		List<Anexo> lstAnexos = docAnexoDao.listarAnexos(parametro.getAnio(), parametro.getNumEmi());
		DocumentosAnexoBean documentos = new DocumentosAnexoBean();

		if (!lstAnexos.isEmpty() && lstAnexos != null) {
			documentos.setAnexos(new ArrayList<DatosAnexoBean>());
			for (Anexo anexo : lstAnexos) {
				DatosAnexoBean anexoFin = new DatosAnexoBean();
				anexoFin.setNuAne(anexoFin.getNuAne());
				int pos = anexo.getDeRutOri().lastIndexOf(".");
				if (pos > 0) {
					anexoFin.setTipoDoc(anexo.getDeRutOri().substring(pos + 1).toLowerCase());

				}
				anexoFin.setVnomDoc(anexo.getDeRutOri());
				anexoFin.setNuAne(anexo.getNuAne());
				anexoFin.setNumEmi(anexo.getNuEmi());
				anexoFin.setNuAnn(anexo.getNuAnn());
				documentos.getAnexos().add(anexoFin);

			}

		} else {
			documentos.setMensaje(new Mensaje());
			documentos.getMensaje().setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
			documentos.getMensaje().setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
		}

		return documentos;
	}

	@Override
	public AnexoBean descargarAnexo(ParametrosBusqAnexoBean parametro) throws Exception {
		Anexo anexo = docAnexoDao.buscarAnexo(parametro);
		AnexoBean anexoFin = new AnexoBean();
		if (anexo == null) {
			anexoFin.setMensaje(new Mensaje());
			anexoFin.getMensaje().setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
			anexoFin.getMensaje().setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);

		}

		int pos = anexo.getDeRutOri().lastIndexOf(".");
		if (pos > 0) {
			anexoFin.setTipoDoc(anexo.getDeRutOri().substring(pos + 1).toLowerCase());

		}
		String encodedBase64 = new String(Base64.encodeBase64(anexo.getBlDoc()));
		anexoFin.setByteFile(encodedBase64);
		anexoFin.setNomDoc(anexo.getDeRutOri());
		anexoFin.setNuAne(anexo.getNuAne());
		anexoFin.setNuEmi(anexo.getNuEmi());

		return anexoFin;
	}

	@Override
	public ArchivoPrincipalBean descargarArchivoPrincipal(ParametrosBusqBean parametro) throws Exception {
		ArchivoPrincipal archivo = docSgdDao.buscarArchPrincipal(parametro);
		ArchivoPrincipalBean archFinal = new ArchivoPrincipalBean();
		if (archivo == null) {

			archFinal.setMensaje(new Mensaje());
			archFinal.getMensaje().setCodigo(ConstantesUtil.ERR_NO_RESULTADOS);
			archFinal.getMensaje().setDesMensaje(ConstantesUtil.ERR_NO_RESULTADOS_DES);
		}

//hoy

		if (archivo != null) {
			String encodedBase64 = new String(Base64.encodeBase64(archivo.getBlDoc()));
			archFinal.setByteFile(encodedBase64);
			archFinal.setNomDoc(archivo.getDeRutaOrigen());
			if (archivo.getCoGru().equals("3")) {
				int pos = archivo.getDeRutaOrigen().lastIndexOf(".");
				if (pos > 0) {
					archFinal.setTipoDoc(archivo.getDeRutaOrigen().substring(pos + 1).toLowerCase());
					archFinal.setNomDoc(archivo.getDeRutaOrigen());
				}

			} else {
				String[] cadema = archivo.getDeRutaOrigen().split("\\$");
				archFinal.setNomDoc(cadema[2].trim() + cadema[7].substring(1, cadema[7].length()));
				archFinal.setTipoDoc(cadema[7].substring(2, cadema[7].length()));
			}

		}
		return archFinal;
	}

	@Override
	public AnexoBean descargarAnexo(ParametrosBusqBean parametro) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
