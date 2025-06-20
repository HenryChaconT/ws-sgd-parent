package pe.gob.serfor.osutd.sgd.service.logic.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.CiudadanoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DestinatarioDocumentoEmiBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocExternoRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoSolicitud;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ExpedienteDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.OtroOrigenBean2;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.PersonaJuridicaBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ReferenciaDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.RemitenteDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.response.TipoDocumentoMP;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ciudadano;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.OtroOrigen;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.PersonaJuridica;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.logic.CiudadanoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.DocSgdInteDao;
import pe.gob.serfor.osutd.sgd.repository.logic.OtroOrigenDao;
import pe.gob.serfor.osutd.sgd.repository.logic.ProveedorDao;
import pe.gob.serfor.osutd.sgd.repository.logic.TipoDocumentoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Fecha;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Util;
import pe.gob.serfor.osutd.sgd.service.logic.MesaPartesService;

//import pe.gob.serfor.wssisged.logic.bean.integracion.CitizenBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.CiudadanoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DestinatarioDocumentoEmiBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocExternoRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoSolicitud;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ExpedienteDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.OtroOrigenBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.OtroOrigenBean2;
//import pe.gob.serfor.wssisged.logic.bean.integracion.PersonaJuridicaBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ReferenciaDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.RemitenteDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.response.TipoDocumentoMP;
//import pe.gob.serfor.wssisged.logic.dao.AnexoDocumentoDao;
//import pe.gob.serfor.wssisged.logic.dao.CiudadanoDao;
//import pe.gob.serfor.wssisged.logic.dao.DocSgdInteDao;
//import pe.gob.serfor.wssisged.logic.dao.EmiDocumentoAdmDao;
//import pe.gob.serfor.wssisged.logic.dao.OtroOrigenDao;
//import pe.gob.serfor.wssisged.logic.dao.ProveedorDao;
//import pe.gob.serfor.wssisged.logic.dao.TipoDocumentoDao;
//import pe.gob.serfor.wssisged.logic.model.Ciudadano;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.model.OtroOrigen;
//import pe.gob.serfor.wssisged.logic.model.PersonaJuridica;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.logic.service.MesaPartesService;
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;
//import pe.gob.serfor.wssisged.utils.Fecha;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;
//import pe.gob.serfor.wssisged.utils.Util;

@Component
@Transactional(readOnly = true)
public class MesaPartesServiceImpl implements MesaPartesService {
	@Autowired
	private DocSgdInteDao _DocSgdInteDao;

	@Autowired
	private TipoDocumentoDao tipoDocumentoDao;

	@Autowired
	private ProveedorDao _ProveedorDao;
	@Autowired
	private CiudadanoDao _CiudadanoDao;
	@Autowired
	private OtroOrigenDao _OtroOrigenDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public MethodResponseBean<String> Add_DocumentoExternoRecep(DocExternoRecepBean docExternoRecepBean,
			Usuario usuario, String idAplicacion) throws Exception {
		MethodResponseBean<String> response = new MethodResponseBean<String>();
		String vReturn = "NO_OK";

		String anioActual = Fecha.getAnioActual() + "";
		String fechaActual = Fecha.getFechaActual();
		docExternoRecepBean.setNuAnn(anioActual);
		docExternoRecepBean.setNuAnn(anioActual);

		// docExternoRecepBean.docdocumentoEmiBean

		String pnuAnn = docExternoRecepBean.getNuAnn();

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
		remitenteBean.setInd_origen(idAplicacion);
		remitenteBean.setCoLocEmi(usuario.getCO_LOC());

		remitenteBean.setCoDepEmi(_DocSgdInteDao.getDependenciaMesaPartes(ConstantesUtil.IND_MESA_PARTES));
		remitenteBean.setCoDep(usuario.getCO_DEPENDENCIA());

		remitenteBean.setCoEmpEmi(usuario.getCOD_JEFE_DEPENDENCIA());

		remitenteBean.setCoEmpRes(usuario.getCEMP_CODEMP());

		/*
		 * "coLocEmi": "001", "coDepEmi": "10602", "coEmpEmi": "01346", "coEmpRes":
		 * "01346",
		 */
		DocumentoSolicitud documento = _DocSgdInteDao.existeDocumento(pnuAnn, docExternoRecepBean.getIdSolicitud(),
				idAplicacion);
		String pnuEmi = documento.getNuEmi();
		ArrayList<ReferenciaDocExtRecepBean> lstReferencia = docExternoRecepBean.getLstReferencia();
		ArrayList<DestinatarioDocumentoEmiBean> lstDestinatario = docExternoRecepBean.getLstDestinatario();

		if (pnuAnn != null && pnuEmi != null && !pnuAnn.equals("") && !pnuEmi.equals("")) {
			if (documento.getEsDocEmi().equals("5") || documento.getEsDocEmi().equals("7")) {
				update_DocumentoExternoRecep(docExternoRecepBean, usuario);
			} else {
				response.setSuccess(false);
				response.setMessage("Expediente ya ha sido procesado ");
				return response;
			}

		} else {

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

			vReturn = Util.obtenerStringFechaFormat(expedienteBean.getFeExp(), "dd/MM/yyyy HH:mm:ss",
					"dd/MM/yyyy HH:mm");
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
				vReturn = this._DocSgdInteDao.getNumeroExpediente(expedienteBean);
			}
			documentoExtRecepBean.setNuCorrExp(expedienteBean.getNuCorrExp());
			documentoExtRecepBean.setNuExpediente(expedienteBean.getNuExpediente());

			vReturn = this._DocSgdInteDao.insExpedienteBean(expedienteBean);
			if (!"OK".equals(vReturn)) {
				response.setSuccess(false);
				response.setMessage("Error al Grabar Expediente.");
				return response;
			}

			documentoExtRecepBean.setCoUseMod(pcoUserMod);
			documentoExtRecepBean.setCoEsDocEmiMp("5");
			vReturn = this._DocSgdInteDao.getNroCorrelativoDocumento(documentoExtRecepBean.getNuAnn(),
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

			vReturn = this._DocSgdInteDao.insDocumentoExtBean(documentoExtRecepBean, expedienteBean, remitenteBean);
			if (!"OK".equals(vReturn)) {
				response.setSuccess(false);
				response.setMessage("Error al Grabar Documento.");
				return response;
			}
			pnuEmi = documentoExtRecepBean.getNuEmi();
			// separanción de lógica
			vReturn = this._DocSgdInteDao.audiEstadoDocumentoRemito(usuario, documentoExtRecepBean.getNuAnn(), pnuEmi,
					"NC");
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
					vReturn = this._DocSgdInteDao.insReferenciaDocumentoEmi(pnuAnn, pnuEmi, ref);
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

					vReturn = this._DocSgdInteDao.insDestinatarioDocumentoEmi(pnuAnn, pnuEmi, dest);
					if ("NO_OK".equals(vReturn)) {
						response.setSuccess(false);
						response.setMessage("Error Agregando Destinatario.");
					}
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
	public TipoDocumentoMP listarDocumentosMP() throws Exception {
		TipoDocumentoMP tipo = new TipoDocumentoMP();
		tipo.setLstDocumentos(tipoDocumentoDao.listarDocumentosMP());
		return tipo;

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public MethodResponseBean<String> update_DocumentoExternoRecep(DocExternoRecepBean docExternoRecepBean,
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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public CiudadanoBean registrarCiudadano(Ciudadano ciudadano, String codUsuario) throws Exception {

		CiudadanoBean ciudadanobean = new CiudadanoBean();

		// Si existe y FLG está para acutalizar
		if (_CiudadanoDao.CiudadanoExiste(ciudadano.getNuDni())) {
			if (ciudadano.isFlagActulaizar()) {

				_CiudadanoDao.actualizar(ciudadano, codUsuario);
				Mensaje mensaje = new Mensaje();
				mensaje.setCodigo(ConstantesUtil.COD_PER_NATURAL_ACTUALIZADO_SATIS);
				mensaje.setDesMensaje(ConstantesUtil.COD_PER_NATURAL_ACTUALIZADO_SATIS_DES);
				ciudadanobean.setMensaje(mensaje);

			} else {

				Mensaje mensaje = new Mensaje();
				mensaje.setCodigo(ConstantesUtil.ERR_PER_NATURAL_YA_EXISTE);
				mensaje.setDesMensaje(ConstantesUtil.ERR_PER_NATURAL_YA_EXISTE_DES);
				ciudadanobean.setMensaje(mensaje);
			}

		} else {
			StringBuffer sql = new StringBuffer();

			_CiudadanoDao.insertar(ciudadano, codUsuario);
			Mensaje mensaje = new Mensaje();
			mensaje.setCodigo(ConstantesUtil.COD_PER_NATURAL_REGISTRADO_SATISF);
			mensaje.setDesMensaje(ConstantesUtil.COD_PER_NATURAL_REGISTRADO_SATISF_DESC);
			ciudadanobean.setMensaje(mensaje);
		}

		return ciudadanobean;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public PersonaJuridicaBean registrarPersonaJuridica(PersonaJuridica personaJuridica, String codUsuario)
			throws Exception {

		PersonaJuridicaBean response = new PersonaJuridicaBean();

		// Si existe y FLG está para acutalizar
		if (_ProveedorDao.ProveedorExiste(personaJuridica.getNuRuc())) {
			if (personaJuridica.isFlagActualizar()) {

				_ProveedorDao.actualizar(personaJuridica);
				Mensaje mensaje = new Mensaje();
				response.setPersonajuridica(personaJuridica);
				mensaje.setCodigo(ConstantesUtil.COD_PER_JURID_ACTUALIZADO_SATIS);
				mensaje.setDesMensaje(ConstantesUtil.COD_PER_JURID_ACTUALIZADO_SATIS_DES);
				response.setMensaje(mensaje);

			} else {

				Mensaje mensaje = new Mensaje();

				mensaje.setCodigo(ConstantesUtil.ERR_PER_JURID_YA_EXISTE);
				mensaje.setDesMensaje(ConstantesUtil.ERR_PER_JURID_YA_EXISTE_DESC);
				response.setMensaje(mensaje);
			}

		} else {
			StringBuffer sql = new StringBuffer();

			_ProveedorDao.insertar(personaJuridica);
			Mensaje mensaje = new Mensaje();
			response.setPersonajuridica(personaJuridica);
			mensaje.setCodigo(ConstantesUtil.COD_PER_JURID_INSERTA_SATISF);
			mensaje.setDesMensaje(ConstantesUtil.COD_PER_JURID_INSERTA_SATISF_DESC);
			response.setMensaje(mensaje);
		}

		return response;
	}

	String exiteOtroOrigen(OtroOrigen otroOrigen) throws Exception {

		String codigoOTrOrigen = null;
		if (otroOrigen.getCoOtrOri() != null) {
			codigoOTrOrigen = _OtroOrigenDao.OtroOrigenExisteCodigo(otroOrigen.getCoOtrOri());
		} else {
			codigoOTrOrigen = _OtroOrigenDao.OtroOrigenExisteNumero(otroOrigen.getCoTipOtrOri(),
					otroOrigen.getNuDocOtrOri());
		}

		return codigoOTrOrigen;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public OtroOrigenBean2 registrarOtroOrigen(OtroOrigen otroOrigen) throws Exception {
		OtroOrigenBean2 response = new OtroOrigenBean2();
		otroOrigen.setApeMatOtr(otroOrigen.getApeMatOtr().toUpperCase());
		otroOrigen.setApePatOtr(otroOrigen.getApePatOtr().toUpperCase());
		otroOrigen.setNomOtr(otroOrigen.getNomOtr().toUpperCase());
		otroOrigen.setRazSocOtr(otroOrigen.getRazSocOtr().toUpperCase());

		String codigo = exiteOtroOrigen(otroOrigen);

		if (codigo != null) {
			if (otroOrigen.isFlagActualizar()) {

				_OtroOrigenDao.actualizar(otroOrigen);
				Mensaje mensaje = new Mensaje();
				response.setOtroOrigen(otroOrigen);
				mensaje.setCodigo(ConstantesUtil.COD_OTR_OR_ACTUALIZADO_SATIS);
				mensaje.setDesMensaje(ConstantesUtil.COD_OTR_OR_ACTUALIZADO_SATIS_DES);
				response.setMensaje(mensaje);

			} else {

				Mensaje mensaje = new Mensaje();

				mensaje.setCodigo(ConstantesUtil.COD_OTR_OR_YA_EXISTE);
				mensaje.setDesMensaje(ConstantesUtil.COD_OTR_OR_YA_EXISTE_DESC);
				response.setMensaje(mensaje);
			}

		} else {
			StringBuffer sql = new StringBuffer();

			_OtroOrigenDao.insertar(otroOrigen);
			Mensaje mensaje = new Mensaje();
			response.setOtroOrigen(otroOrigen);
			mensaje.setCodigo(ConstantesUtil.COD_OTR_OR_INSERTA_SATISF);
			mensaje.setDesMensaje(ConstantesUtil.COD_OTR_OR_INSERTA_SATISF_DESC);
			response.setMensaje(mensaje);
		}

		return response;

	}

}
