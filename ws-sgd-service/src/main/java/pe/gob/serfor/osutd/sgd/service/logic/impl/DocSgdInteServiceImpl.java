package pe.gob.serfor.osutd.sgd.service.logic.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.CitizenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DestinatarioDocumentoEmiBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocExternoRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoFileBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoObjBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ExpedienteDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.OtroOrigenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ProveedorBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ReferenciaDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.RemitenteDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.logic.AnexoDocumentoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.CiudadanoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.DocSgdInteDao;
import pe.gob.serfor.osutd.sgd.repository.logic.EmiDocumentoAdmDao;
import pe.gob.serfor.osutd.sgd.repository.logic.OtroOrigenDao;
import pe.gob.serfor.osutd.sgd.repository.logic.ProveedorDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Fecha;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Util;
import pe.gob.serfor.osutd.sgd.service.logic.DocSgdInteService;

//import pe.gob.serfor.wssisged.logic.bean.integracion.CitizenBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DestinatarioDocumentoEmiBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocExternoRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoFileBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoObjBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ExpedienteDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.OtroOrigenBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ProveedorBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ReferenciaDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.RemitenteDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.dao.AnexoDocumentoDao;
//import pe.gob.serfor.wssisged.logic.dao.AuditoriaMovimientoDocDao;
//import pe.gob.serfor.wssisged.logic.dao.CiudadanoDao;
//import pe.gob.serfor.wssisged.logic.dao.DocExternoRecepDao;
//import pe.gob.serfor.wssisged.logic.dao.DocSgdInteDao;
//import pe.gob.serfor.wssisged.logic.dao.EmiDocumentoAdmDao;
//import pe.gob.serfor.wssisged.logic.dao.OtroOrigenDao;
//import pe.gob.serfor.wssisged.logic.dao.ProveedorDao;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.logic.service.ActualizaResumenService;
//import pe.gob.serfor.wssisged.logic.service.AuditoriaMovimientoDocService;
//import pe.gob.serfor.wssisged.logic.service.DocSgdInteService;
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;
//import pe.gob.serfor.wssisged.utils.Fecha;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;
//import pe.gob.serfor.wssisged.utils.Util;

@Component
@Transactional(readOnly = true)
public class DocSgdInteServiceImpl implements DocSgdInteService {

	@Autowired
	private DocSgdInteDao _DocSgdInteDao;
	@Autowired
	private AnexoDocumentoDao _AnexoDocumentoDao;
	@Autowired
	private EmiDocumentoAdmDao _EmiDocumentoAdmDao;
	@Autowired
	private ProveedorDao _ProveedorDao;
	@Autowired
	private CiudadanoDao _CiudadanoDao;
	@Autowired
	private OtroOrigenDao _OtroOrigenDao;

	/*
	 * @Autowired private AuditoriaMovimientoDocService
	 * _auditoriaMovimientoDocService;
	 * 
	 * @Autowired private ActualizaResumenService _actualizaResumenService;
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public MethodResponseBean<String> Add_DocumentoExternoRecep(DocExternoRecepBean docExternoRecepBean,
			Usuario usuario) throws Exception {
		MethodResponseBean<String> response = new MethodResponseBean<String>();
		String vReturn = "NO_OK";

		String anioActual = Fecha.getAnioActual() + "";
		String fechaActual = Fecha.getFechaActual();
		docExternoRecepBean.setNuAnn(anioActual);
		docExternoRecepBean.setNuAnn(anioActual);

		// docExternoRecepBean.docdocumentoEmiBean

		String pnuAnn = docExternoRecepBean.getNuAnn();
		String pnuEmi = docExternoRecepBean.getNuEmi();
		String pnuAnnExp = docExternoRecepBean.getNuAnnExp();

		String pnuSecExp = docExternoRecepBean.getNuSecExp();
		String pcoUserMod = docExternoRecepBean.getCoUserMod();
		String pcempCodEmp = docExternoRecepBean.getCempCodEmp();
		DocumentoExtRecepBean documentoExtRecepBean = docExternoRecepBean.getDocumentoEmiBean();

		documentoExtRecepBean.setNuAnn(anioActual);
		ExpedienteDocExtRecepBean expedienteBean = docExternoRecepBean.getExpedienteEmiBean();
		expedienteBean.setNuAnnExp(anioActual);
		expedienteBean.setFeExp(fechaActual);
		expedienteBean.setCoDepEmi(usuario.getCO_DEPENDENCIA());
		RemitenteDocExtRecepBean remitenteBean = docExternoRecepBean.getRemitenteEmiBean();

		remitenteBean.setCoLocEmi(usuario.getCO_LOC());
		//

		remitenteBean.setN_id_solicitud(docExternoRecepBean.getIdSolicitud() == null ? 0
				: Integer.parseInt(docExternoRecepBean.getIdSolicitud()));
		remitenteBean.setCoDepEmi(_DocSgdInteDao.getDependenciaMesaPartes(ConstantesUtil.IND_MESA_PARTES));
		remitenteBean.setCoDep(usuario.getCO_DEPENDENCIA());
		// remitenteBean.setCoEmpEmi(usuario.getCEMP_CODEMP());
		remitenteBean.setCoEmpEmi(usuario.getCOD_JEFE_DEPENDENCIA());
		// remitenteBean.setCoEmpRes(usuario.getCOD_JEFE_DEPENDENCIA());
		remitenteBean.setCoEmpRes(usuario.getCEMP_CODEMP());

		/*
		 * "coLocEmi": "001", "coDepEmi": "10602", "coEmpEmi": "01346", "coEmpRes":
		 * "01346",
		 */
		ArrayList<ReferenciaDocExtRecepBean> lstReferencia = docExternoRecepBean.getLstReferencia();
		ArrayList<DestinatarioDocumentoEmiBean> lstDestinatario = docExternoRecepBean.getLstDestinatario();

		docExternoRecepBean.setAccionBD("INS");
		expedienteBean.setUsCreaAudi(pcempCodEmp);
		/*
		 * j 02 n 03 o 04
		 */
		if (remitenteBean.getTiEmi().equals("03")) {
			if (remitenteBean.getNuDni() != null && !remitenteBean.getNuDni().isEmpty()) {
				if (!_CiudadanoDao.CiudadanoExiste(remitenteBean.getNuDni().trim())) {
					response.setSuccess(false);
					response.setMessage(
							"Remitente Ciudadano no se encunetra registrado DNI.".concat(remitenteBean.getNuDni()));
					return response;
				}
			} else {
				response.setSuccess(false);
				response.setMessage("El Número de Documento de Identidad es Obligatoria");
				return response;
			}
		} else if (remitenteBean.getTiEmi().equals("02")) {
			if (remitenteBean.getNuRuc() != null && !remitenteBean.getNuRuc().isEmpty()) {
				if (!_ProveedorDao.ProveedorExiste(remitenteBean.getNuRuc().trim())) {
					response.setSuccess(false);
					response.setMessage("Remitente Persona Jurídica no se encunetra registrado RUC."
							.concat(remitenteBean.getNuRuc()));
					return response;
				}
			} else {
				response.setSuccess(false);
				response.setMessage("El número de RUC es Obligatoria");
				return response;
			}
			// Responsable n 03 o 04
			if (remitenteBean.getEmiResp().equals("03")) {
				if (remitenteBean.getNuDni() != null && !remitenteBean.getNuDni().isEmpty()) {
					if (!_CiudadanoDao.CiudadanoExiste(remitenteBean.getNuDni().trim())) {
						response.setSuccess(false);
						response.setMessage("Remitente Ciudadano Emisor Resp. no se encunetra registrado DNI."
								.concat(remitenteBean.getNuDni()));
						return response;
					}
				} else {
					response.setSuccess(false);
					response.setMessage("Emisor Resp. el Número de Documento de Identidad es Obligatoria");
					return response;
				}
			} else if (remitenteBean.getEmiResp().equals("04")) {
				if (remitenteBean.getCoOtros() != null && !remitenteBean.getCoOtros().isEmpty()) {
					if (!_OtroOrigenDao.OtroOrigenExiste(remitenteBean.getCoOtros().trim())) {
						response.setSuccess(false);
						response.setMessage("Remitente Otros Emisor Resp. no se encunetra registrado "
								.concat(remitenteBean.getCoOtros()));
						return response;
					}
				} else {
					response.setSuccess(false);
					response.setMessage("Emisor Resp. Número Otros es Obligatorio");
					return response;
				}
			}
		} else if (remitenteBean.getTiEmi().equals("04")) {
			if (remitenteBean.getCoOtros() != null && !remitenteBean.getCoOtros().isEmpty()) {
				if (!_OtroOrigenDao.OtroOrigenExiste(remitenteBean.getCoOtros().trim())) {
					response.setSuccess(false);
					response.setMessage(
							"Remitente Otros no se encunetra registrado ".concat(remitenteBean.getCoOtros()));
					return response;
				}
			} else {
				response.setSuccess(false);
				response.setMessage("Número Otros es Obligatorio");
				return response;
			}
		}

		vReturn = Util.obtenerStringFechaFormat(expedienteBean.getFeExp(), "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy HH:mm");
		if ("NO_OK".equals(vReturn)) {
			response.setSuccess(false);
			response.setMessage("Error fecha Expediente.");
			return response;
		}
		expedienteBean.setFeExp(vReturn);

		vReturn = Util.obtenerStringFechaFormat(expedienteBean.getFeVence(), "dd/MM/yyyy HH:mm:ss", "dd/MM/yyyy");
		if ("NO_OK".equals(vReturn)) {
			response.setSuccess(false);
			response.setMessage("Error fecha vencimiento Expediente.");
			return response;
		}
		expedienteBean.setFeVence(vReturn);

		if (expedienteBean.getNuCorrExp() == null || expedienteBean.getNuCorrExp().equals("")) {
			vReturn = _DocSgdInteDao.getNumeroExpediente(expedienteBean);
		}
		documentoExtRecepBean.setNuCorrExp(expedienteBean.getNuCorrExp());
		documentoExtRecepBean.setNuExpediente(expedienteBean.getNuExpediente());

		vReturn = _DocSgdInteDao.insExpedienteBean(expedienteBean);
		if (!"OK".equals(vReturn)) {
			response.setSuccess(false);
			response.setMessage("Error al Grabar Expediente.");
			return response;
		}

		documentoExtRecepBean.setCoUseMod(pcoUserMod);
		documentoExtRecepBean.setCoEsDocEmiMp("5");
		vReturn = _DocSgdInteDao.getNroCorrelativoDocumento(documentoExtRecepBean.getNuAnn(),
				remitenteBean.getCoDepEmi(), remitenteBean.getTiEmi());
		if ("NO_OK".equals(vReturn)) {
			response.setSuccess(false);
			response.setMessage("Error al obtener ncorrelativo.");
			return response;
		}
		documentoExtRecepBean.setNuCorDoc(vReturn);
		documentoExtRecepBean.setNuSecExp(expedienteBean.getNuSecExp());

		// REGLA DEBE SER DEFINIDO , YA QUE SERÁ PARA USUARIOS EXTERNOS
		// remitenteBean.setCoDep(usuario.getCoDep());

		vReturn = _DocSgdInteDao.insDocumentoExtBean(documentoExtRecepBean, expedienteBean, remitenteBean);
		if (!"OK".equals(vReturn)) {
			response.setSuccess(false);
			response.setMessage("Error al Grabar Documento.");
			return response;
		}
		pnuEmi = documentoExtRecepBean.getNuEmi();
		// separanción de lógica
		vReturn = _DocSgdInteDao.audiEstadoDocumentoRemito(usuario, documentoExtRecepBean.getNuAnn(), pnuEmi, "NC");
		// vReturn = _auditoriaMovimientoDocService.audiEstadoDocumentoRemito(usuario,
		// documentoExtRecepBean.getNuAnn(), pnuEmi,"NC");
		if (!vReturn.equals("OK")) {
			response.setSuccess(false);
			response.setMessage("Error insertando Auditoria.");
			return response;
		}
		docExternoRecepBean.setNuEmi(pnuEmi);
		pnuAnnExp = expedienteBean.getNuAnnExp();
		pnuSecExp = expedienteBean.getNuSecExp();
		docExternoRecepBean.setNuAnnExp(pnuAnnExp);
		docExternoRecepBean.setNuSecExp(pnuSecExp);
		if (lstReferencia != null) {
			for (ReferenciaDocExtRecepBean ref : lstReferencia) {
				ref.setCoUseCre(pcoUserMod);
				vReturn = _DocSgdInteDao.insReferenciaDocumentoEmi(pnuAnn, pnuEmi, ref);
				if ("NO_OK".equals(vReturn)) {
					response.setSuccess(false);
					response.setMessage("Error Agregando Referencia.");
					return response;
				}
			}
		}
		if (lstDestinatario != null) {
			for (DestinatarioDocumentoEmiBean dest : lstDestinatario) {
				dest.setCoUseCre(pcoUserMod);
				//
				dest.setCoDependencia(usuario.getCO_DEPENDENCIA());
				dest.setCoLocal(usuario.getCO_LOC());
				dest.setCoEmpleado(usuario.getCEMP_CODEMP());

				vReturn = _DocSgdInteDao.insDestinatarioDocumentoEmi(pnuAnn, pnuEmi, dest);
				if ("NO_OK".equals(vReturn)) {
					response.setSuccess(false);
					response.setMessage("Error Agregando Destinatario.");
				}
			}
		}
		if (lstDestinatario != null && lstDestinatario.size() >= 1) {

			vReturn = _DocSgdInteDao.updRemitosResumenDes(pnuAnn, pnuEmi);
			// vReturn = this._actualizaResumenService.updRemitosResumenDes(pnuAnn, pnuEmi);
			if ("NO_OK".equals(vReturn)) {
				response.setSuccess(false);
				response.setMessage("Error Actualizando Remito resumen destinatario.");
				return response;
			}
		}
		if (lstReferencia != null && lstReferencia.size() >= 1) {
			vReturn = _DocSgdInteDao.updRemitosResumenRef(pnuAnn, pnuEmi);
			// vReturn = this._actualizaResumenService.updRemitosResumenRef(pnuAnn, pnuEmi);
			if ("NO_OK".equals(vReturn)) {
				response.setSuccess(false);
				response.setMessage("Error Actualizando Remito resumen Referencia");
				return response;
			}
		}

		response.setSuccess(true);
		response.setMessage("Expediente registrado correctamente");

		return response;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public MethodResponseBean<String> Update_DocumentoExternoRecep(DocExternoRecepBean docExternoRecepBean,
			Usuario usuario) throws Exception {
		// TODO Auto-generated method stub
		MethodResponseBean<String> response = new MethodResponseBean<String>();

		String pnuSecExp = docExternoRecepBean.getNuSecExp();
		String pcoUserMod = docExternoRecepBean.getCoUserMod();
		String pcempCodEmp = docExternoRecepBean.getCempCodEmp();

		DocumentoExtRecepBean documentoExtRecepBean = docExternoRecepBean.getDocumentoEmiBean();
		ExpedienteDocExtRecepBean expedienteBean = docExternoRecepBean.getExpedienteEmiBean();
		RemitenteDocExtRecepBean remitenteBean = docExternoRecepBean.getRemitenteEmiBean();
		ArrayList<ReferenciaDocExtRecepBean> lstReferencia = docExternoRecepBean.getLstReferencia();
		ArrayList<DestinatarioDocumentoEmiBean> lstDestinatario = docExternoRecepBean.getLstDestinatario();
		if (docExternoRecepBean.getNuAnn() != null && docExternoRecepBean.getNuEmi() != null
				&& !docExternoRecepBean.getNuAnn().equals("") && !docExternoRecepBean.getNuEmi().equals("")) {

			// VALIDA SI EL EXPEDIENTE ESTÁ EN ESTADO EN REGISTRO PARA SER ACTUALIZADO
			if (_DocSgdInteDao.isValidUpdateExpedienteBean(docExternoRecepBean.getNuAnn(),
					docExternoRecepBean.getNuEmi())) {

				/*
				 * j 02 n 03 o 04
				 */
				if (remitenteBean != null) {

					remitenteBean.setCoLocEmi(usuario.getCO_LOC());
					// remitenteBean.setCoDepEmi(usuario.getCO_DEPENDENCIA());

					remitenteBean.setCoDepEmi(_DocSgdInteDao.getDependenciaMesaPartes(ConstantesUtil.IND_MESA_PARTES));
					remitenteBean.setCoDep(usuario.getCO_DEPENDENCIA());

					remitenteBean.setCoEmpEmi(usuario.getCEMP_CODEMP());
					remitenteBean.setCoEmpRes(usuario.getCOD_JEFE_DEPENDENCIA());

					if (remitenteBean.getTiEmi().equals("03")) {
						if (remitenteBean.getNuDni() != null && !remitenteBean.getNuDni().isEmpty()) {
							if (!_CiudadanoDao.CiudadanoExiste(remitenteBean.getNuDni().trim())) {
								response.setSuccess(false);
								response.setMessage("Remitente Ciudadano no se encunetra registrado DNI."
										.concat(remitenteBean.getNuDni()));
								return response;
							}
						} else {
							response.setSuccess(false);
							response.setMessage("El Número de Documento de Identidad es Obligatoria");
							return response;
						}
					} else if (remitenteBean.getTiEmi().equals("02")) {
						if (remitenteBean.getNuRuc() != null && !remitenteBean.getNuRuc().isEmpty()) {
							if (!_ProveedorDao.ProveedorExiste(remitenteBean.getNuRuc().trim())) {
								response.setSuccess(false);
								response.setMessage("Remitente Persona Jurídica no se encunetra registrado RUC."
										.concat(remitenteBean.getNuRuc()));
								return response;
							}
						} else {
							response.setSuccess(false);
							response.setMessage("El número de RUC es Obligatoria");
							return response;
						}
						// Responsable n 03 o 04
						if (remitenteBean.getEmiResp().equals("03")) {
							if (remitenteBean.getNuDni() != null && !remitenteBean.getNuDni().isEmpty()) {
								if (!_CiudadanoDao.CiudadanoExiste(remitenteBean.getNuDni().trim())) {
									response.setSuccess(false);
									response.setMessage(
											"Remitente Ciudadano Emisor Resp. no se encunetra registrado DNI."
													.concat(remitenteBean.getNuDni()));
									return response;
								}
							} else {
								response.setSuccess(false);
								response.setMessage("Emisor Resp. el Número de Documento de Identidad es Obligatoria");
								return response;
							}
						} else if (remitenteBean.getEmiResp().equals("04")) {
							if (remitenteBean.getCoOtros() != null && !remitenteBean.getCoOtros().isEmpty()) {
								if (!_OtroOrigenDao.OtroOrigenExiste(remitenteBean.getCoOtros().trim())) {
									response.setSuccess(false);
									response.setMessage("Remitente Otros Emisor Resp. no se encunetra registrado "
											.concat(remitenteBean.getCoOtros()));
									return response;
								}
							} else {
								response.setSuccess(false);
								response.setMessage("Emisor Resp. Número Otros es Obligatorio");
								return response;
							}
						}
					} else if (remitenteBean.getTiEmi().equals("04")) {
						if (remitenteBean.getCoOtros() != null && !remitenteBean.getCoOtros().isEmpty()) {
							if (!_OtroOrigenDao.OtroOrigenExiste(remitenteBean.getCoOtros().trim())) {
								response.setSuccess(false);
								response.setMessage("Remitente Otros no se encunetra registrado "
										.concat(remitenteBean.getCoOtros()));
								return response;
							}
						} else {
							response.setSuccess(false);
							response.setMessage("Número Otros es Obligatorio");
							return response;
						}
					}
				}
				docExternoRecepBean.setAccionBD("UPD");
				String vReturn;
				String pcoDepEmi = docExternoRecepBean.getCoDependencia();
				if (expedienteBean != null) {
					expedienteBean.setUsCreaAudi(pcempCodEmp);
					vReturn = Util.obtenerStringFechaFormat(expedienteBean.getFeVence(), "dd/MM/yyyy HH:mm:ss",
							"dd/MM/yyyy");
					if ("NO_OK".equals(vReturn)) {
						response.setSuccess(false);
						response.setMessage("Error fecha vencimiento Expediente.");
						return response;
					}
					expedienteBean.setFeVence(vReturn);
					vReturn = _DocSgdInteDao.updExpedienteBean(expedienteBean);
					if ("NO_OK".equals(vReturn)) {
						response.setSuccess(false);
						response.setMessage("Error actualizando Expediente.");
						return response;
					}
				}
				if (documentoExtRecepBean != null || remitenteBean != null) {
					if (documentoExtRecepBean != null) {
						documentoExtRecepBean.setCoDepEmi(pcoDepEmi);
					}
					vReturn = _DocSgdInteDao.updDocumentoExtBean(docExternoRecepBean.getNuAnn(),
							docExternoRecepBean.getNuEmi(), documentoExtRecepBean, expedienteBean, remitenteBean,
							pcoUserMod);
					if (!"OK".equals(vReturn)) {
						response.setSuccess(false);
						response.setMessage("Error actualizando Documento.");
						// return response;
					}
				}
				for (ReferenciaDocExtRecepBean ref : lstReferencia) {
					String sAccionBD = ref.getAccionBd();
					ref.setCoUseCre(pcoUserMod);
					if ("INS".equals(sAccionBD)) {
						vReturn = this._DocSgdInteDao.insReferenciaDocumentoEmi(docExternoRecepBean.getNuAnn(),
								docExternoRecepBean.getNuEmi(), ref);
						if ("NO_OK".equals(vReturn)) {
							response.setSuccess(false);
							response.setMessage("Error Agregando Referencia");
						}
						continue;
					}
					if ("UPD".equals(sAccionBD)) {
						vReturn = this._DocSgdInteDao.updReferenciaDocumentoEmi(docExternoRecepBean.getNuAnn(),
								docExternoRecepBean.getNuEmi(), ref);
						if ("NO_OK".equals(vReturn)) {
							response.setSuccess(false);
							response.setMessage("Error Actualizando Referencia");
						}
						continue;
					}
					if ("DEL".equals(sAccionBD)) {
						vReturn = this._DocSgdInteDao.delReferenciaDocumentoEmi(docExternoRecepBean.getNuAnn(),
								docExternoRecepBean.getNuEmi(), ref.getNuAnn(), ref.getNuEmi());
						if ("NO_OK".equals(vReturn)) {
							response.setSuccess(false);
							response.setMessage("Error Borrando Referencia");
						}
					}
				}
				for (DestinatarioDocumentoEmiBean dest : lstDestinatario) {
					String sAccionBD = dest.getAccionBD();
					dest.setCoUseCre(pcoUserMod);

					dest.setCoDependencia(usuario.getCO_DEPENDENCIA());
					dest.setCoLocal(usuario.getCO_LOC());
					dest.setCoEmpleado(usuario.getCEMP_CODEMP());

					if ("INS".equals(sAccionBD)) {
						vReturn = this._DocSgdInteDao.insDestinatarioDocumentoEmi(docExternoRecepBean.getNuAnn(),
								docExternoRecepBean.getNuEmi(), dest);
						if ("NO_OK".equals(vReturn)) {
							response.setSuccess(false);
							response.setMessage("Error Agregando Destinatario");
						}
						continue;
					}
					if ("UPD".equals(sAccionBD)) {
						vReturn = this._DocSgdInteDao.updDestinatarioDocumentoEmi(docExternoRecepBean.getNuAnn(),
								docExternoRecepBean.getNuEmi(), dest);
						if ("NO_OK".equals(vReturn)) {
							response.setSuccess(false);
							response.setMessage("Error Actualizando Destinatario");
						}
						continue;
					}
					if ("DEL".equals(sAccionBD)) {
						vReturn = this._DocSgdInteDao.delDestinatarioDocumentoEmi(docExternoRecepBean.getNuAnn(),
								docExternoRecepBean.getNuEmi(), dest.getNuDes());
						if ("NO_OK".equals(vReturn)) {
							response.setSuccess(false);
							response.setMessage("Error Borrando Destinatario");
						}
					}
				}
				if (lstDestinatario.size() >= 1) {
					vReturn = this._DocSgdInteDao.updRemitosResumenDes(docExternoRecepBean.getNuAnn(),
							// vReturn =
							// this._actualizaResumenService.updRemitosResumenDes(docExternoRecepBean.getNuAnn(),

							docExternoRecepBean.getNuEmi());
					if ("NO_OK".equals(vReturn)) {
						response.setSuccess(false);
						response.setMessage("Error Actualizando Remito resumen destinatario");
						// return response;
					}
				}
				if (lstReferencia.size() >= 1) {
					vReturn = this._DocSgdInteDao.updRemitosResumenRef(docExternoRecepBean.getNuAnn(),
							// vReturn =
							// this._actualizaResumenService.updRemitosResumenRef(docExternoRecepBean.getNuAnn(),
							docExternoRecepBean.getNuEmi());
					if ("NO_OK".equals(vReturn)) {
						response.setSuccess(false);
						response.setMessage("Error Actualizando Remito resumen Referencia");
						// return response;
					}
				}
				response.setSuccess(true);
				response.setMessage("Expediente actualizado correctamente");
				return response;
			} else {
				response.setSuccess(false);
				response.setMessage(
						"El expediente no puede ser actualizado debido a que no se encuenta en estado [EN REGISTRO]");
				return response;
			}
		} else {
			response.setSuccess(false);
			response.setMessage("Es debe consignar el campo [pnuAnn,pnuEmia]");
			return response;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public String insArchivoAnexo(String coUsu, String pnuAnn, String pnuEmi, DocumentoFileBean pfileAnexo) {
		String vReturn = "NO_OK";
		String vnuAne = _AnexoDocumentoDao.getUltimoAnexo(pnuAnn, pnuEmi);

		DocumentoAnexoBean docAnexo = new DocumentoAnexoBean();

		docAnexo.setNuAnn(pnuAnn);
		docAnexo.setNuEmi(pnuEmi);
		docAnexo.setNuAne(vnuAne);
		docAnexo.setDeDet(pfileAnexo.getNombreArchivo());
		docAnexo.setDeRutOri(pfileAnexo.getNombreArchivo());
		docAnexo.setCoUseCre(coUsu);
		docAnexo.setCoUseMod(coUsu);
		validarBeanDocAnexo(docAnexo);

		InputStream archivoAnexo = new ByteArrayInputStream(pfileAnexo.getArchivoBytes());
		int size = (pfileAnexo.getArchivoBytes()).length;
		int maxUploadSize = 26214400;

		try {
			if (size <= maxUploadSize) {
				vReturn = _AnexoDocumentoDao.insArchivoAnexo(docAnexo, archivoAnexo, size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (archivoAnexo != null) {
					archivoAnexo.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return vReturn;
	}

	private void validarBeanDocAnexo(DocumentoAnexoBean docAnexo) {
		String pdeDet = docAnexo.getDeDet();
		if (pdeDet != null) {
			int tamCampoDeDet = 200;
			pdeDet = pdeDet.trim();
			int pLenDeDet = pdeDet.length();
			if (pLenDeDet > tamCampoDeDet) {
				pdeDet = pdeDet.substring(pLenDeDet - tamCampoDeDet, pLenDeDet);
			}
		}
		docAnexo.setDeDet(pdeDet);
	}

	@Override
	public String updArchivoAnexo(String coUsu, String pnuAnn, String pnuEmi, String pnuAne,
			DocumentoFileBean pfileAnexo) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		DocumentoAnexoBean docAnexo = new DocumentoAnexoBean();

		docAnexo.setNuAnn(pnuAnn);
		docAnexo.setNuEmi(pnuEmi);
		docAnexo.setNuAne(pnuAne);
		docAnexo.setDeDet(pfileAnexo.getNombreArchivo());
		docAnexo.setDeRutOri(pfileAnexo.getNombreArchivo());
		docAnexo.setCoUseCre(coUsu);
		docAnexo.setCoUseMod(coUsu);
		validarBeanDocAnexo(docAnexo);

		InputStream archivoAnexo = new ByteArrayInputStream(pfileAnexo.getArchivoBytes());
		int size = (pfileAnexo.getArchivoBytes()).length;
		int maxUploadSize = 10000000;
		try {
			if (size <= maxUploadSize) {
				vReturn = _AnexoDocumentoDao.updArchivoAnexo(docAnexo, archivoAnexo, size);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (archivoAnexo != null) {
					archivoAnexo.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return vReturn;
	}

	@Override
	public String cargaDocumentoEmi(String coUsu, String pnuAnn, String pnuEmi, DocumentoFileBean pfileAnexo)
			throws Exception {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		DocumentoObjBean docObjBean = new DocumentoObjBean();

		docObjBean.setNuAnn(pnuAnn);
		docObjBean.setNuEmi(pnuEmi);
		docObjBean.setTiCap("01");
		docObjBean.setNombreArchivo(pfileAnexo.getNombreArchivo());
		docObjBean.setCoUseMod(coUsu);
		docObjBean.setTipoDoc(
				docObjBean.getNombreArchivo().substring(docObjBean.getNombreArchivo().lastIndexOf('.') + 1));

		docObjBean.setNombreArchivo(
				docObjBean.getNombreArchivo().substring(docObjBean.getNombreArchivo().lastIndexOf('\\') + 1));

		byte[] archivoByte = pfileAnexo.getArchivoBytes();
		docObjBean.setDocumento(archivoByte);
		docObjBean.setNuTamano((int) Math.round(archivoByte.length / 1024.0D));

		vReturn = _EmiDocumentoAdmDao.updDocumentoObj(docObjBean);

		return vReturn;
	}

	@Override
	public MethodResponseBean<String> insProveedor(ProveedorBean proveedor) {
		// TODO Auto-generated method stub
		/*
		 * proveedor.setDescripcion(proveedor.getDescripcion().toUpperCase());
		 * proveedor.setCproDomicil(proveedor.getCproDomicil());
		 * proveedor.setCproEmail(proveedor.getCproEmail());
		 */
		return _ProveedorDao.insProveedor(proveedor);

	}

	@Override
	public String updProveedor(ProveedorBean proveedor, String codUsuario) {
		String vReturn = "NO_OK";
		proveedor.setDescripcion(proveedor.getDescripcion().toUpperCase().trim());
		proveedor.setCproDomicil(proveedor.getCproDomicil().toUpperCase().trim());
		proveedor.setCproEmail(proveedor.getCproEmail().toUpperCase().trim());

		vReturn = _ProveedorDao.updProveedor(proveedor, codUsuario);

		return vReturn;
	}

	@Override
	public MethodResponseBean<String> insCiudadano(CitizenBean ciudadano, String codUsuario) {

		ciudadano.setDeApp(ciudadano.getDeApp().toUpperCase().trim());
		ciudadano.setDeApm(ciudadano.getDeApm().toUpperCase().trim());
		ciudadano.setDeNom(ciudadano.getDeNom().toUpperCase().trim());
		return _CiudadanoDao.insCiudadano(ciudadano, codUsuario);

	}

	@Override
	public String updCiudadano(CitizenBean ciudadano, String codUsuario) {
		String vReturn = "NO_OK";
		ciudadano.setDeApp(ciudadano.getDeApp().toUpperCase().trim());
		ciudadano.setDeApm(ciudadano.getDeApm().toUpperCase().trim());
		ciudadano.setDeNom(ciudadano.getDeNom().toUpperCase().trim());
		vReturn = _CiudadanoDao.updCiudadano(ciudadano, codUsuario);
		return vReturn;
	}

	@Override
	public MethodResponseBean<String> insOtroOrigen(OtroOrigenBean otroOrigen) {
		// TODO Auto-generated method stub
		otroOrigen.setDeApeMatOtr(otroOrigen.getDeApeMatOtr().toUpperCase());
		otroOrigen.setDeApePatOtr(otroOrigen.getDeApePatOtr().toUpperCase());
		otroOrigen.setDeNomOtr(otroOrigen.getDeNomOtr().toUpperCase());
		otroOrigen.setDeRazSocOtr(otroOrigen.getDeRazSocOtr().toUpperCase());

		return _OtroOrigenDao.insOtroOrigen(otroOrigen);

	}

	@Override
	public String updOtroOrigen(OtroOrigenBean otroOrigen) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";

		otroOrigen.setDeApeMatOtr(otroOrigen.getDeApeMatOtr().toUpperCase());
		otroOrigen.setDeApePatOtr(otroOrigen.getDeApePatOtr().toUpperCase());
		otroOrigen.setDeNomOtr(otroOrigen.getDeNomOtr().toUpperCase());
		otroOrigen.setDeRazSocOtr(otroOrigen.getDeRazSocOtr().toUpperCase());

		vReturn = _OtroOrigenDao.updOtroOrigen(otroOrigen);

		return vReturn;
	}
}
