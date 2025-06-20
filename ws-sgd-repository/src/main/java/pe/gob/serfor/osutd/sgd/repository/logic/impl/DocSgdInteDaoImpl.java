package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

//import java.io.InputStream;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.util.Iterator;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
//import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
//import org.springframework.jdbc.support.lob.DefaultLobHandler;
//import org.springframework.jdbc.support.lob.LobCreator;
//import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.AudiEstadosMovDocBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DestinatarioDocumentoEmiBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DestinoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoEmiBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoSolicitud;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ExpedienteDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ReferenciaDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ReferenciaRemitoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.RemitenteDocExtRecepBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.logic.DocSgdInteDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Util;

//import pe.gob.serfor.wssisged.logic.bean.DocumentoAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.AudiEstadosMovDocBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DestinatarioDocumentoEmiBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DestinoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocExternoRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoEmiBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoSolicitud;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ExpedienteDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ReferenciaBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ReferenciaDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ReferenciaRemitoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.RemitenteDocExtRecepBean;
//import pe.gob.serfor.wssisged.logic.dao.DocSgdInteDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleJdbcDaoBase;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.utils.Util;
//import pe.gob.serfor.wssisged.utils.Utilidades;

@Repository
public class DocSgdInteDaoImpl extends JdbcDaoSupport implements DocSgdInteDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public String getNumeroExpediente(ExpedienteDocExtRecepBean expedienteBean) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sqlQry1 = new StringBuffer();
		sqlQry1.append(
				"select\tRIGHT(REPLICATE('0', 7) + (RTRIM(LTRIM(CAST((ISNULL(MAX(NU_CORR_EXP), 0) + 1) AS VARCHAR)))), 7) nuCorrExp, ");
		sqlQry1.append(
				"\tSUBSTRING((SELECT RETORNO FROM [IDOSGD].[PK_SGD_DESCRIPCION_DE_SIGLA_CORTA](?)), 1, 6) deSiglaCorta ");
		sqlQry1.append("from IDOSGD.tdtc_expediente ");
		sqlQry1.append("where nu_ann_exp = ? ");
		sqlQry1.append("and co_dep_exp = ? ");
		sqlQry1.append("and co_gru = '3' ");

		// try {
		Map mapResult = this.getJdbcTemplate().queryForMap(sqlQry1.toString(), new Object[] {
				expedienteBean.getCoDepEmi(), expedienteBean.getNuAnnExp(), expedienteBean.getCoDepEmi() });
		String snuCorrExp = String.valueOf(mapResult.get("nuCorrExp"));
		String sdeSiglaCorta = String.valueOf(mapResult.get("deSiglaCorta"));
		expedienteBean.setNuCorrExp(snuCorrExp);

		expedienteBean.setNuExpediente(expedienteBean.getNuAnnExp() + "-" + snuCorrExp);
		vReturn = "OK";
		/*
		 * } catch (Exception e) { e.printStackTrace(); } return vReturn;
		 */
		return vReturn;
	}

	@Override
	public String insExpedienteBean(ExpedienteDocExtRecepBean expedienteBean) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		String clave = Util.generateRandomLetter(4);
		StringBuffer sqlQry1 = new StringBuffer();
		sqlQry1.append("select RIGHT(REPLICATE('0', 10) + CAST((ISNULL(MAX(nu_sec_exp), 0) + 1) AS VARCHAR), 10) ");
		sqlQry1.append("from IDOSGD.tdtc_expediente ");
		sqlQry1.append("where nu_ann_exp = ? ");

		StringBuffer sqlIns = new StringBuffer();
		sqlIns.append(
				"INSERT INTO IDOSGD.TDTC_EXPEDIENTE(\nnu_ann_exp,\nnu_sec_exp,\nfe_exp,\nfe_vence,\nco_proceso,\nde_detalle,\nco_dep_exp,\nco_gru,\nnu_corr_exp,\nnu_expediente,\nus_crea_audi,\nfe_crea_audi,\nus_modi_audi,\nfe_modi_audi,\nes_estado,ccod_tipo_exp,CCLAVE)\nVALUES(?,?,(SELECT CONVERT(DATETIME, ?, 103)),(SELECT CONVERT(DATETIME, ?, 103)),?,?,?,'3',?,?,?,CURRENT_TIMESTAMP,?,CURRENT_TIMESTAMP,'0',?, ?)");

		// try {
		String feVence = expedienteBean.getFeVence();
		if (feVence == null || feVence.trim().length() <= 0) {
			feVence = null;
		}
		String snuSecExp = (String) this.getJdbcTemplate().queryForObject(sqlQry1.toString(), String.class,
				new Object[] { expedienteBean.getNuAnnExp() });
		expedienteBean.setNuSecExp(snuSecExp);

		this.getJdbcTemplate().update(sqlIns.toString(),
				new Object[] { expedienteBean.getNuAnnExp(), snuSecExp, expedienteBean.getFeExp(), feVence,
						expedienteBean.getCoProceso(), expedienteBean.getDeDetalle(), expedienteBean.getCoDepEmi(),
						expedienteBean.getNuCorrExp(), expedienteBean.getNuExpediente(), expedienteBean.getUsCreaAudi(),
						expedienteBean.getUsCreaAudi(), expedienteBean.getCoTipoExp(), clave });
		vReturn = "OK";
		/*
		 * } catch (DuplicateKeyException con) { vReturn =
		 * "Numero de Expediente Duplicado."; } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		return vReturn;
	}

	@Override
	public String getNroCorrelativoDocumento(String pnuAnn, String pcoDepEmi, String ptiEmi) {
		String vReturn = "NO_OK";
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append("select ISNULL(MAX(nu_cor_doc), 0) + 1 ");
		sqlQry.append("from IDOSGD.tdtv_remitos ");
		sqlQry.append("where nu_ann=? ");
		sqlQry.append("and co_dep_emi=? ");
		sqlQry.append("AND TI_EMI=? ");
		try {
			vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
					new Object[] { pnuAnn, pcoDepEmi, ptiEmi });
		} catch (Exception e) {
			e.printStackTrace();
			vReturn = "NO_OK";
		}
		return vReturn;
	}

	@Override
	public String getDependenciaMesaPartes(String tipoMesa) {
		String vReturn = "NO_OK";
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append("select CO_DEPENDENCIA from IDOSGD.RHTM_DEPENDENCIA  where IN_MESA_PARTES=? ");

		try {
			vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
					new Object[] { tipoMesa });
		} catch (Exception e) {
			e.printStackTrace();
			vReturn = "NO_OK";
		}
		return vReturn;
	}

	@Override
	public String insDocumentoExtBean(DocumentoExtRecepBean documentoExtRecepBean,
			ExpedienteDocExtRecepBean expedienteBean, RemitenteDocExtRecepBean remitenteDocExtRecepBean) {
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();
		String codGrupo = "3";
		String estadoEnRegistro = "5";
		String estadoNoEliminacion = "0";
		String nDepExp = "1";
		String indOrigen = "1";
		if (remitenteDocExtRecepBean.getInd_origen() != null) {
			indOrigen = remitenteDocExtRecepBean.getInd_origen();
		}
		// String fechaCreacion="CURRENT_TIMESTAMP";
		String fechaModificacion = null;
		;
		// NU_DET_EXP

		// sqlUpd.append(
		// "INSERT INTO
		// IDOSGD.tdtv_remitos(\nNU_ANN,\nNU_EMI,\nNU_COR_EMI,\nCO_LOC_EMI,\nCO_DEP_EMI,\nCO_EMP_EMI,\nCO_EMP_RES,
		// \nTI_EMI,\nNU_DNI_EMI,\nNU_RUC_EMI,\nCO_OTR_ORI_EMI,\nFE_EMI,\nCO_GRU,\nDE_ASU,
		// \nES_DOC_EMI, \nNU_DIA_ATE, \nCO_TIP_DOC_ADM, \nNU_COR_DOC, \nDE_DOC_SIG,
		// \nNU_ANN_EXP, \nNU_SEC_EXP, \nNU_DET_EXP, \nNU_FOLIOS,
		// \nES_ELI,\nCO_USE_CRE,\nFE_USE_CRE,\nCO_USE_MOD,\nCO_DEP,\nFE_USE_MOD,\nCDIR_REMITE,\nAUT_CORREOE,\nCEXP_CORREOE,\nCTELEFONO,\nCCOD_DPTO,\nCCOD_PROV,\nCCOD_DIST,\nREMI_TI_EMI,\nREMI_NU_DNI_EMI,\nREMI_CO_OTR_ORI_EMI,\nREMI_CARGO,\nCCOD_ORIGING,\nCNUM_DNIMSG,\nCOBS_DOCUMENTO,\nCDOC_DESTRAM,\nCONG_CO_OTR_ORI,\nIND_TIPOCONG,\nIND_TIPOCONGINV,\nIND_REITCONGINV,\nNRO_SOBREAUT,\nAN_SOBREAUT,\nCIND_SENSIBLE,\nIND_ORIGEN)\nVALUES(?,?,?,?,?,?,?,?,?,?,?,(SELECT
		// CONVERT(DATETIME, ?,
		// 103)),'3',?,'5',?,?,?,?,?,?,'1',?,'0',?,CURRENT_TIMESTAMP,?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'1')");

		sqlUpd.append(
				" INSERT INTO IDOSGD.tdtv_remitos( NU_ANN, NU_EMI, NU_COR_EMI, CO_LOC_EMI, CO_DEP_EMI, CO_EMP_EMI, CO_EMP_RES,TI_EMI, NU_DNI_EMI, ");
		sqlUpd.append("  NU_RUC_EMI, CO_OTR_ORI_EMI, FE_EMI, CO_GRU, DE_ASU,  ES_DOC_EMI,  NU_DIA_ATE, ");
		sqlUpd.append(
				" CO_TIP_DOC_ADM,  NU_COR_DOC,  DE_DOC_SIG,  NU_ANN_EXP,  NU_SEC_EXP,  NU_DET_EXP,  NU_FOLIOS,  ES_ELI, ");
		sqlUpd.append(
				" CO_USE_CRE, FE_USE_CRE, CO_USE_MOD, CO_DEP, FE_USE_MOD, CDIR_REMITE, AUT_CORREOE, CEXP_CORREOE, CTELEFONO, ");
		sqlUpd.append(
				" CCOD_DPTO, CCOD_PROV, CCOD_DIST, REMI_TI_EMI, REMI_NU_DNI_EMI, REMI_CO_OTR_ORI_EMI, REMI_CARGO, CCOD_ORIGING, ");
		sqlUpd.append(
				" CNUM_DNIMSG, COBS_DOCUMENTO, CDOC_DESTRAM, CONG_CO_OTR_ORI, IND_TIPOCONG, IND_TIPOCONGINV, IND_REITCONGINV, ");
		sqlUpd.append(
				" NRO_SOBREAUT, AN_SOBREAUT, CIND_SENSIBLE, IND_ORIGEN,N_ID_SOLICITUD) VALUES(?,?,?,?,?,?,?,?,?,?,?, "); // 11
		sqlUpd.append(" (SELECT CONVERT(DATETIME, ?, 103)),?,?,?,?,?,?,?,?,?,?,?,?, "); // 24
		sqlUpd.append(" ?,CURRENT_TIMESTAMP,?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");// 52

		// try {
		String coOtros = remitenteDocExtRecepBean.getCoOtros();
		if (coOtros == null || coOtros.trim().length() <= 0) {
			coOtros = null;
		}

		SimpleJdbcCall simpleJdbcCall = (new SimpleJdbcCall(this.getJdbcTemplate()))
				.withProcedureName("[IDOSGD].[PK_SGD_UTILS_SEC_REMITOS_NU_EMI]").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter[] { (SqlParameter) new SqlOutParameter("COD_NU_EMI", 4) });

		Map out = simpleJdbcCall.execute(new Object[0]);
		int codigo = ((Integer) out.get("COD_NU_EMI")).intValue();
		String snuEmi = String.format("%010d", new Object[] { Integer.valueOf(codigo) });

		documentoExtRecepBean.setNuEmi(snuEmi);
		// CO_DEP 28
		this.getJdbcTemplate().update(sqlUpd.toString(),
				new Object[] { documentoExtRecepBean.getNuAnn(), snuEmi, expedienteBean.getNuCorrExp(),
						remitenteDocExtRecepBean.getCoLocEmi(), remitenteDocExtRecepBean.getCoDepEmi(),
						remitenteDocExtRecepBean.getCoEmpEmi(), remitenteDocExtRecepBean.getCoEmpRes(),
						remitenteDocExtRecepBean.getTiEmi(), remitenteDocExtRecepBean.getNuDni(),
						remitenteDocExtRecepBean.getNuRuc(), coOtros, expedienteBean.getFeExp(), // 12
						codGrupo,
						// GRUPO EN DURO
						documentoExtRecepBean.getDeAsu(),
						// ESTADO DEL DOCUMENTO EN REGISTRO
						estadoEnRegistro, documentoExtRecepBean.getNuDiaAte(),

						documentoExtRecepBean.getCoTipDocAdm(), documentoExtRecepBean.getNuCorDoc(),
						documentoExtRecepBean.getDeDocSig(), expedienteBean.getNuAnnExp(), expedienteBean.getNuSecExp(),
						// NU_DET_EXP EN DURO
						nDepExp, documentoExtRecepBean.getNuFolios(),
						// INDICADOR no ELIMINAdo EN DURO
						estadoNoEliminacion, documentoExtRecepBean.getCoUseMod(),
						// FECHA CREACION 26

						documentoExtRecepBean.getCoUseMod(), remitenteDocExtRecepBean.getCoDep(),
						// FECHA MODIFICACION 29

						remitenteDocExtRecepBean.getDeDireccion(), remitenteDocExtRecepBean.getNotificado(), // 31
						remitenteDocExtRecepBean.getDeCorreo(), remitenteDocExtRecepBean.getTelefono(),
						remitenteDocExtRecepBean.getIdDepartamento(), remitenteDocExtRecepBean.getIdProvincia(),
						remitenteDocExtRecepBean.getIdDistrito(), remitenteDocExtRecepBean.getEmiResp(),
						remitenteDocExtRecepBean.getNuDniRes(), remitenteDocExtRecepBean.getCoOtrosRes(),
						remitenteDocExtRecepBean.getDeCargo(), documentoExtRecepBean.getCoOriDoc(),
						documentoExtRecepBean.getNroDniTramitante(), documentoExtRecepBean.getDeObservacion(),
						documentoExtRecepBean.getCoTraDest(), remitenteDocExtRecepBean.getCoComision(),
						remitenteDocExtRecepBean.getCoTipoCongresista(), remitenteDocExtRecepBean.getCoTipoInv(),
						remitenteDocExtRecepBean.getReiterativo(), documentoExtRecepBean.getnSobre(),
						documentoExtRecepBean.getAnioSobre(), documentoExtRecepBean.getSensible(), indOrigen,
						remitenteDocExtRecepBean.getN_id_solicitud()
				// '1'//, remitenteDocExtRecepBean.getInd_origen()
				});

		vReturn = "OK";
		/*
		 * } catch (DuplicateKeyException con) { vReturn =
		 * "Numero de Documento Duplicado."; } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		return vReturn;
	}

	@Override
	public String audiEstadoDocumentoRemito(Usuario currentUser, String nuAnn, String nuEmi, String esDoc) {
		String vReturn = "NO_OK";
		AudiEstadosMovDocBean audi = new AudiEstadosMovDocBean();
		audi.setNuAnn(nuAnn);
		audi.setNuEmi(nuEmi);
		audi.setTiProceso("R");
		audi.setEsProceso(esDoc);
		audi.setDeUser(currentUser.getCoUsuario());
		audi.setDeIpPc(currentUser.getIpPC());
		audi.setDeNamePc(currentUser.getNombrePC());
		audi.setDeUserPc(currentUser.getUsuPc());
		if (isChangeEstadoDoc(audi)) {
			vReturn = this.audiEstadoDocumento(audi);
		} else {
			vReturn = "OK";
		}
		return vReturn;
	}

	private boolean isChangeEstadoDoc(AudiEstadosMovDocBean audi) {
		boolean vResult = false;
		String esDocBD = null;
		try {
			if (audi.getNuAnn() != null && audi.getNuAnn().trim().length() > 0 && audi.getNuEmi() != null
					&& audi.getNuEmi().trim().length() > 0) {
				if (audi.getNuDes() != null && audi.getNuDes().trim().length() > 0) {
					DocumentoBean doc = this.getEstadoDocumento(audi.getNuAnn(), audi.getNuEmi(), audi.getNuDes());
					esDocBD = doc.getEsDocRec();
				} else {
					DocumentoEmiBean doc = this.getEstadoDocumentoAudi(audi.getNuAnn(), audi.getNuEmi());
					esDocBD = doc.getEsDocEmi();
					if (esDocBD != null && esDocBD.equals("9") && audi.getEsProceso() != null
							&& audi.getEsProceso().equals("9")) {
						esDocBD = "X";
					}
				}
				if (audi.getEsProceso() != null && esDocBD != null && esDocBD.trim().length() > 0
						&& !audi.getEsProceso().equals(esDocBD)) {
					audi.setEsProceso(esDocBD);
					vResult = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vResult;
	}

	private DocumentoBean getEstadoDocumento(String nuAnn, String nuEmi, String nuDes) {
		StringBuffer sql = new StringBuffer();
		DocumentoBean documentoBean = null;
		sql.append(
				"SELECT CO_EMP_DES,ES_DOC_REC,NU_ANN,NU_EMI,NU_DES,TI_DES,CO_DEP_DES\nFROM IDOSGD.TDTV_DESTINOS\nWHERE NU_ANN = ?\nAND NU_EMI = ? AND NU_DES = ? AND ES_ELI='0'");

		try {
			documentoBean = (DocumentoBean) this.getJdbcTemplate().queryForObject(sql.toString(),
					(RowMapper) BeanPropertyRowMapper.newInstance(DocumentoBean.class),
					new Object[] { nuAnn, nuEmi, nuDes });
		} catch (EmptyResultDataAccessException e) {
			documentoBean = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentoBean;
	}

	public DocumentoEmiBean getEstadoDocumentoAudi(String pnuAnn, String pnuEmi) {
		StringBuffer sql = new StringBuffer();
		DocumentoEmiBean documentoEmiBean = null;
		sql.append("SELECT CO_EMP_EMI, ");
		sql.append("ES_DOC_EMI,NU_ANN, ");
		sql.append("NU_EMI, ");
		sql.append("TI_EMI, ");
		sql.append("CO_DEP_EMI, ");
		sql.append("CO_TIP_DOC_ADM, ");
		sql.append("NU_DOC_EMI ");
		sql.append("FROM IDOSGD.TDTV_REMITOS ");
		sql.append("WHERE nu_ann = ? ");
		sql.append("AND nu_emi = ? ");

		try {
			documentoEmiBean = (DocumentoEmiBean) this.getJdbcTemplate().queryForObject(sql.toString(),
					(RowMapper) BeanPropertyRowMapper.newInstance(DocumentoEmiBean.class),
					new Object[] { pnuAnn, pnuEmi });
		} catch (EmptyResultDataAccessException e) {
			documentoEmiBean = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentoEmiBean;
	}

	public DocumentoSolicitud existeDocumento(String pnuAnn, String nuSolicitud, String indOrigen) {
		StringBuffer sql = new StringBuffer();
		DocumentoSolicitud documentoEmiBean = null;
		sql.append(" select a.NU_EMI, a.NU_ANN , a.CO_GRU,  ");
		sql.append(" A.ES_DOC_EMI, ISNULL(R.NU_EXPEDIENTE,'') nu_expediente, A.ES_DOC_EMI  ");
		sql.append("  FROM IDOSGD.TDTV_REMITOS A INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN R  ");
		sql.append("   ON R.NU_ANN = A.NU_ANN AND R.NU_EMI = A.NU_EMI   ");
		sql.append("   AND A.ES_ELI = '0' AND A.IN_OFICIO = '0'  ");
		sql.append("where 1=1 and a.co_gru='3'  and	A.NU_ANN = ? and A.ES_DOC_EMI!='9'  ");
		sql.append(" and a.n_id_solicitud=?  and a.ind_origen=? ");

		try {
			documentoEmiBean = (DocumentoSolicitud) this.getJdbcTemplate().queryForObject(sql.toString(),
					(RowMapper) BeanPropertyRowMapper.newInstance(DocumentoSolicitud.class),
					new Object[] { pnuAnn, nuSolicitud, indOrigen });
		} catch (EmptyResultDataAccessException e) {
			documentoEmiBean = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return documentoEmiBean;
	}

	private String audiEstadoDocumento(AudiEstadosMovDocBean audi) {
		String vReturn = "NO_OK";
		StringBuffer sql = new StringBuffer();
		sql.append(
				"INSERT INTO IDOSGD.tdtv_estados_mov( NU_ANN, NU_EMI, NU_DES, FE_DML, TI_PROCESO, ES_PROCESO, DE_USER, DE_IPPC, DE_NAMEPC, DE_USERPC ) VALUES(?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?)");

		try {
			this.getJdbcTemplate().update(sql.toString(),
					new Object[] { audi.getNuAnn(), audi.getNuEmi(), audi.getNuDes(), audi.getTiProceso(),
							audi.getEsProceso(), audi.getDeUser(), audi.getDeIpPc(), audi.getDeNamePc(),
							audi.getDeUserPc() });
			vReturn = "OK";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vReturn;
	}

	@Override
	public String insReferenciaDocumentoEmi(String pnuAnn, String pnuEmi, ReferenciaDocExtRecepBean ref) {
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();
		sqlUpd.append(
				"INSERT INTO IDOSGD.tdtr_referencia(\nNU_ANN,\nNU_EMI,\nCO_REF,\nNU_ANN_REF,\nNU_EMI_REF,\nNU_DES_REF,\nCO_USE_CRE,\nFE_USE_CRE)\nVALUES(?,?,?,?,?,?,?,CURRENT_TIMESTAMP)");

		/* try { */
		SimpleJdbcCall simpleJdbcCall = (new SimpleJdbcCall(this.getJdbcTemplate()))
				.withProcedureName("[IDOSGD].[PK_SGD_UTILS_SEC_REFERENCIA_CO_REF]")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter[] { (SqlParameter) new SqlOutParameter("COD_NU_ANN_REF", 4) });
		Map out = simpleJdbcCall.execute(new Object[0]);
		int codigo = ((Integer) out.get("COD_NU_ANN_REF")).intValue();
		String cod_num_ann_ref = String.format("%010d", new Object[] { Integer.valueOf(codigo) });

		ref.setCoRef(cod_num_ann_ref);
		String nuDes = ref.getNuDes();
		if (nuDes == null || nuDes.trim().length() <= 0) {
			nuDes = null;
		}

		this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { pnuAnn, pnuEmi, cod_num_ann_ref, ref.getNuAnn(),
				ref.getNuEmi(), nuDes, ref.getCoUseCre() });
		vReturn = "OK";
		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		return vReturn;
	}

	@Override
	public String insDestinatarioDocumentoEmi(String nuAnn, String nuEmi,
			DestinatarioDocumentoEmiBean destinatarioDocumentoEmiBean) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();
		sqlUpd.append(
				"INSERT INTO IDOSGD.TDTV_DESTINOS(\nNU_ANN,\nNU_EMI,\nNU_DES,\nCO_LOC_DES,\nCO_DEP_DES,\nTI_DES,\nCO_EMP_DES,\nCO_PRI,\nDE_PRO,\nCO_MOT, \nES_ELI,\nFE_USE_CRE,\nFE_USE_MOD,\nCO_USE_MOD,\nCO_USE_CRE,\nES_DOC_REC,\nES_ENV_POR_TRA)\nVALUES(?,?,?,?,?,?,?,?,?,?,'0',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,?,?,'0','0')");

		// try {
		String vnuDes = (String) this.getJdbcTemplate().queryForObject(
				"select ISNULL(MAX(a.nu_des) + 1,1) FROM IDOSGD.tdtv_destinos a where \nA.NU_ANN=? and\nA.NU_EMI=?",
				String.class, new Object[] { nuAnn, nuEmi });

		destinatarioDocumentoEmiBean.setNuDes(vnuDes);
		this.getJdbcTemplate().update(sqlUpd.toString(),
				new Object[] { nuAnn, nuEmi, vnuDes, destinatarioDocumentoEmiBean.getCoLocal(),
						destinatarioDocumentoEmiBean.getCoDependencia(),
						destinatarioDocumentoEmiBean.getCoTipoDestino(), destinatarioDocumentoEmiBean.getCoEmpleado(),
						destinatarioDocumentoEmiBean.getCoPrioridad(), destinatarioDocumentoEmiBean.getDeIndicaciones(),
						destinatarioDocumentoEmiBean.getCoTramite(), destinatarioDocumentoEmiBean

								.getCoUseCre(),
						destinatarioDocumentoEmiBean.getCoUseCre() });
		vReturn = "OK";
		/*
		 * } catch (Exception e) { e.printStackTrace(); }
		 */

		return vReturn;
	}

	@Override
	public String updRemitosResumenDes(String pnuAnn, String pnuEmi) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		String vti_des = ti_co_dep_des_v(pnuAnn, pnuEmi);
		String vco_pri = ti_des_est_v(pnuAnn, pnuEmi);
		String vnu_cant_des = nu_cant_des(pnuAnn, pnuEmi);
		String vresOriDes = resumenOriDestinos(pnuAnn, pnuEmi);

		if (vti_des != null) {
			vti_des = vti_des.trim();
			if (vti_des.length() >= 100) {
				vti_des = vti_des.substring(1, 100);
			}
		}
		if (vco_pri != null) {
			vco_pri = vco_pri.trim();
		}
		if (vresOriDes != null) {
			vresOriDes = vresOriDes.trim();
			if (vresOriDes.length() > 399) {
				vresOriDes = vresOriDes.substring(0, 399);
			}
		}
		vReturn = updRemitoResumenDestinatario(pnuAnn, pnuEmi, vti_des, vco_pri, vnu_cant_des, vresOriDes);

		return vReturn;
	}

	private String ti_co_dep_des_v(String nu_ann, String nu_emi) {
		String vReturn = "";
		List<DestinoBean> destinatarios = getDestinosListCodDepTipoDes(nu_ann, nu_emi);
		for (DestinoBean destinatario : destinatarios) {
			if (vReturn != "") {
				vReturn = vReturn + ";\r\n";
			}
			if ("01".equals(destinatario.getTiDes())) {
				vReturn = vReturn + destinatario.getCoDepDes();
			}
		}
		return vReturn;
	}

	private List<DestinoBean> getDestinosListCodDepTipoDes(String nu_ann, String nu_emi) {
		List<DestinoBean> list = new ArrayList<DestinoBean>();
		try {
			// list = this.emiDocumentoAdmDao.getListaDestinosCodDepTipoDes(nu_ann, nu_emi);
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT DISTINCT co_dep_des, ");
			sql.append("ti_des ");
			sql.append("FROM IDOSGD.tdtv_destinos ");
			sql.append("WHERE es_eli = '0' ");
			sql.append("AND nu_ann = ? ");
			sql.append("AND nu_emi = ? ");
			sql.append("ORDER BY ti_des");
			try {
				list = this.getJdbcTemplate().query(sql.toString(),
						(RowMapper) BeanPropertyRowMapper.newInstance(DestinoBean.class),
						new Object[] { nu_ann, nu_emi });
			} catch (EmptyResultDataAccessException e) {
				list = null;
			} catch (Exception e) {
				list = null;
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	private String ti_des_est_v(String nu_ann, String nu_emi) {
		String vReturn = "1";
		List<DestinoBean> destinatarios = getDestinosListCodPri(nu_ann, nu_emi);
		Iterator<DestinoBean> iterator = destinatarios.iterator();
		if (iterator.hasNext()) {
			DestinoBean destinatario = iterator.next();
			vReturn = destinatario.getCoPri();
		}

		return vReturn;
	}

	private List<DestinoBean> getDestinosListCodPri(String nu_ann, String nu_emi) {
		List<DestinoBean> list = new ArrayList<DestinoBean>();
		try {
			// list = this.emiDocumentoAdmDao.getListaDestinosCodPri(nu_ann, nu_emi);
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT co_pri ");
			sql.append("FROM IDOSGD.tdtv_destinos ");
			sql.append("WHERE es_eli = '0' ");
			sql.append("AND nu_ann = ? ");
			sql.append("AND nu_emi = ? ");
			sql.append("ORDER BY co_pri desc");

			try {
				list = this.getJdbcTemplate().query(sql.toString(),
						(RowMapper) BeanPropertyRowMapper.newInstance(DestinoBean.class),
						new Object[] { nu_ann, nu_emi });
			} catch (EmptyResultDataAccessException e) {
				list = null;
			} catch (Exception e) {
				list = null;
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	private String nu_cant_des(String nu_ann, String nu_emi) {
		String vReturn = "0";
		// vReturn = this.emiDocumentoAdmDao.getNumDestinos(nu_ann, nu_emi);
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append("SELECT count(nu_des) ");
		sqlQry.append("FROM IDOSGD.tdtv_destinos ");
		sqlQry.append("WHERE nu_ann = ? ");
		sqlQry.append("AND nu_emi = ? ");
		try {
			vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
					new Object[] { nu_ann, nu_emi });
		} catch (Exception e) {
			vReturn = "0";
			e.printStackTrace();
		}
		return vReturn;
	}

	private String resumenOriDestinos(String nu_ann, String nu_emi) {
		String vReturn = "";
		List<DestinoBean> destinatarios = getOriDestinosList(nu_ann, nu_emi);
		for (DestinoBean destinatario : destinatarios) {
			if (vReturn != "") {
				vReturn = vReturn + "\r\n";
			}

			if (destinatario.getTiDes().equals("02") || destinatario.getTiDes().equals("03")
					|| destinatario.getTiDes().equals("04")) {
				vReturn = vReturn + destinatario.getCoDepDes();
			}
		}
		return vReturn;
	}

	private List<DestinoBean> getOriDestinosList(String nu_ann, String nu_emi) {
		// List<DestinoBean> list = null;
		List<DestinoBean> list = new ArrayList<DestinoBean>();
		try {
			// list = this.emiDocumentoAdmDao.getListaDestinosOriTipoDes(nu_ann, nu_emi);
			StringBuffer sql = new StringBuffer();
			// List<DestinoBean> list = new ArrayList<DestinoBean>();
			sql.append("SELECT ti_des, ");
			sql.append("CASE ti_des ");
			sql.append("\tWHEN '02' THEN [IDOSGD].[PK_SGD_DESCRIPCION_DE_PROVEEDOR](nu_ruc_des) ");
			sql.append("\tWHEN '03' THEN [IDOSGD].[PK_SGD_DESCRIPCION_ANI_SIMIL](nu_dni_des) ");
			sql.append("\tWHEN '04' THEN [IDOSGD].[PK_SGD_DESCRIPCION_OTRO_ORIGEN](co_otr_ori_des) ");
			sql.append("\tELSE ' ' ");
			sql.append("END co_dep_des ");
			sql.append("FROM IDOSGD.tdtv_destinos ");
			sql.append("where nu_ann = ? ");
			sql.append("and nu_emi = ? ");
			sql.append("and es_eli = '0' ");
			sql.append("ORDER BY ti_des ");

			try {
				list = this.getJdbcTemplate().query(sql.toString(),
						(RowMapper) BeanPropertyRowMapper.newInstance(DestinoBean.class),
						new Object[] { nu_ann, nu_emi });
			} catch (EmptyResultDataAccessException e) {
				list = null;
			} catch (Exception e) {
				list = null;
				e.printStackTrace();
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public String updRemitoResumenDestinatario(String pnuAnn, String pnuEmi, String vti_des, String vco_pri,
			String vnu_cant_des, String vresOriDes) {
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();
		sqlUpd.append("UPDATE IDOSGD.TDTX_REMITOS_RESUMEN SET ");
		sqlUpd.append("TI_EMI_DES = ?,CO_PRIORIDAD = ? ,nu_candes=? ");
		sqlUpd.append("WHERE NU_ANN = ? ");
		sqlUpd.append("AND NU_EMI = ? ");

		StringBuffer sqlUpd2 = new StringBuffer();
		sqlUpd2.append("UPDATE IDOSGD.TDTV_REMITOS SET DE_ORI_DES = ?\nWHERE NU_ANN = ? \nAND NU_EMI = ? ");

		try {
			this.getJdbcTemplate().update(sqlUpd.toString(),
					new Object[] { vti_des, vco_pri, vnu_cant_des, pnuAnn, pnuEmi });

			this.getJdbcTemplate().update(sqlUpd2.toString(), new Object[] { vresOriDes, pnuAnn, pnuEmi });

			vReturn = "OK";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vReturn;
	}

	@Override
	public String updRemitosResumenRef(String pnuAnn, String pnuEmi) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		String vti_ori = ti_emi_ref(pnuAnn, pnuEmi);
		String vde_ori_emi = resumenOriReferencia(pnuAnn, pnuEmi);
		if (vti_ori != null) {
			vti_ori = vti_ori.trim();
			if (vti_ori.length() >= 100) {
				vti_ori = vti_ori.substring(1, 100);
			}
		}
		// vReturn = this.emiDocumentoAdmDao.updRemitoResumenReferencia(pnuAnn, pnuEmi,
		// vti_ori, vde_ori_emi);
		vReturn = updRemitoResumenReferencia(pnuAnn, pnuEmi, vti_ori, vde_ori_emi);
		return vReturn;
	}

	private String ti_emi_ref(String nu_ann, String nu_emi) {
		String vReturn = "";
		List<ReferenciaRemitoBean> referenciasRemitos = getReferenciaRemitoList(nu_ann, nu_emi);
		for (ReferenciaRemitoBean referenciaRemito : referenciasRemitos) {
			if (vReturn != "") {
				vReturn = vReturn + ";\r\n";
			}
			if ("01".equals(referenciaRemito.getTi_emi())) {
				vReturn = vReturn + referenciaRemito.getCo_dep_emi();
				continue;
			}
			if ("02".equals(referenciaRemito.getTi_emi())) {
				vReturn = vReturn + "P";
				continue;
			}
			if ("03".equals(referenciaRemito.getTi_emi())) {
				vReturn = vReturn + "C";
				continue;
			}
			if ("04".equals(referenciaRemito.getTi_emi())) {
				vReturn = vReturn + "O";
				continue;
			}
			if ("05".equals(referenciaRemito.getTi_emi())) {
				vReturn = vReturn + "F";
			}
		}
		return vReturn;
	}

	private List<ReferenciaRemitoBean> getReferenciaRemitoList(String nu_ann, String nu_emi) {
		List<ReferenciaRemitoBean> list = new ArrayList<ReferenciaRemitoBean>();
		try {
			// list = this.emiDocumentoAdmDao.getListaReferenciaRemitos(nu_ann, nu_emi);
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT DISTINCT b.ti_emi, b.co_dep_emi ");
			sql.append(
					"FROM   IDOSGD.tdtr_referencia a INNER JOIN IDOSGD.tdtv_remitos b ON a.nu_ann_ref = b.nu_ann AND    a.nu_emi_ref = b.nu_emi  ");
			sql.append("WHERE  ");
			sql.append("     a.nu_ann = ? ");
			sql.append("AND    a.nu_emi = ? ");
			sql.append("ORDER  BY b.ti_emi");
			try {
				list = this.getJdbcTemplate().query(sql.toString(),
						(RowMapper) BeanPropertyRowMapper.newInstance(ReferenciaRemitoBean.class),
						new Object[] { nu_ann, nu_emi });
			} catch (EmptyResultDataAccessException e) {
				list = null;
			} catch (Exception e) {
				list = null;
				e.printStackTrace();
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	private String resumenOriReferencia(String nu_ann, String nu_emi) {
		String vReturn = "";
		List<ReferenciaRemitoBean> referenciasRemitos = getOriReferenciaList(nu_ann, nu_emi);
		for (ReferenciaRemitoBean referenciaRemito : referenciasRemitos) {
			if (vReturn != "") {
				vReturn = vReturn + "\r\n";
			}
			if (referenciaRemito.getTi_emi().equals("02") || referenciaRemito.getTi_emi().equals("03")
					|| referenciaRemito.getTi_emi().equals("04")) {
				vReturn = vReturn + referenciaRemito.getCo_dep_emi();
			}
		}
		return vReturn;
	}

	private List<ReferenciaRemitoBean> getOriReferenciaList(String nu_ann, String nu_emi) {
		List<ReferenciaRemitoBean> list = new ArrayList<ReferenciaRemitoBean>();
		try {
			// list = this.emiDocumentoAdmDao.getOriReferenciaLista(nu_ann, nu_emi);
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT b.ti_emi, ");
			sql.append("CASE b.ti_emi ");
			sql.append("\tWHEN '02' THEN [IDOSGD].[PK_SGD_DESCRIPCION_DE_PROVEEDOR](b.nu_ruc_emi) ");
			sql.append("\tWHEN '03' THEN [IDOSGD].[PK_SGD_DESCRIPCION_ANI_SIMIL](b.nu_dni_emi) ");
			sql.append("\tWHEN '04' THEN [IDOSGD].[PK_SGD_DESCRIPCION_OTRO_ORIGEN](b.co_otr_ori_emi) ");
			sql.append("\tELSE ' ' ");
			sql.append("END co_dep_emi ");
			sql.append("FROM   IDOSGD.tdtr_referencia a ");
			sql.append(
					"INNER JOIN\t   IDOSGD.tdtv_remitos b ON  a.nu_ann_ref = b.nu_ann AND    a.nu_emi_ref = b.nu_emi ");
			sql.append("WHERE ");
			sql.append("     a.nu_ann = ? ");
			sql.append("AND    a.nu_emi = ? ");
			sql.append("ORDER  BY b.ti_emi ");

			try {
				list = this.getJdbcTemplate().query(sql.toString(),
						(RowMapper) BeanPropertyRowMapper.newInstance(ReferenciaRemitoBean.class),
						new Object[] { nu_ann, nu_emi });
			} catch (EmptyResultDataAccessException e) {
				list = null;
			} catch (Exception e) {
				list = null;
				e.printStackTrace();
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	private String updRemitoResumenReferencia(String pnuAnn, String pnuEmi, String vti_ori, String vdeOriEmi) {
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();
		sqlUpd.append("UPDATE IDOSGD.TDTX_REMITOS_RESUMEN SET ");
		sqlUpd.append("TI_EMI_REF = ? ");
		sqlUpd.append("WHERE NU_ANN = ? ");
		sqlUpd.append("AND NU_EMI = ? ");

		StringBuffer sqlUpd2 = new StringBuffer();
		sqlUpd2.append("UPDATE IDOSGD.TDTV_REMITOS SET DE_ORI_EMI = ? ");
		sqlUpd2.append("WHERE NU_ANN = ? ");
		sqlUpd2.append("AND NU_EMI = ? ");

		try {
			this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { vti_ori, pnuAnn, pnuEmi });

			this.getJdbcTemplate().update(sqlUpd2.toString(), new Object[] { vdeOriEmi, pnuAnn, pnuEmi });

			vReturn = "OK";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vReturn;
	}

	@Override
	public String updExpedienteBean(ExpedienteDocExtRecepBean expedienteBean) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();

		sqlUpd.append(
				"update IDOSGD.TDTC_EXPEDIENTE \nset US_MODI_AUDI=?\n,FE_VENCE=(SELECT CONVERT(DATETIME, ?, 103))\n,CO_PROCESO=?\n,FE_MODI_AUDI=CURRENT_TIMESTAMP\nwhere\nNU_ANN_EXP=? and\nNU_SEC_EXP=?");

		try {
			String feVence = expedienteBean.getFeVence();
			if (feVence == null || feVence.trim().length() <= 0) {
				feVence = null;
			}
			this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { expedienteBean.getUsCreaAudi(), feVence,
					expedienteBean.getCoProceso(), expedienteBean.getNuAnnExp(), expedienteBean.getNuSecExp() });
			vReturn = "OK";
		} catch (Exception e) {
			e.printStackTrace();
			vReturn = e.getMessage();
		}

		return vReturn;
	}

	@Override
	public String updDocumentoExtBean(String nuAnn, String nuEmi, DocumentoExtRecepBean documentoExtRecepBean,
			ExpedienteDocExtRecepBean expedienteBean, RemitenteDocExtRecepBean remitenteDocExtRecepBean,
			String pcoUserMod) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();
		sqlUpd.append("update IDOSGD.tdtv_remitos \nset CO_USE_MOD=?,");

		if (documentoExtRecepBean != null) {
			documentoExtRecepBean.setNuAnn(nuAnn);
			sqlUpd.append(
					"DE_ASU=?\n,COBS_DOCUMENTO=?\n,DE_DOC_SIG=?\n,CO_TIP_DOC_ADM=?\n,NU_FOLIOS=?\n,NU_DIA_ATE=?,\n");

			sqlUpd.append("CCOD_ORIGING=?,\n");
			sqlUpd.append("CNUM_DNIMSG=?,\n");
			sqlUpd.append("NRO_SOBREAUT=?,\n");
			sqlUpd.append("AN_SOBREAUT=?,\n");
			sqlUpd.append("CDOC_DESTRAM=?,\n");

			if (documentoExtRecepBean.getNuCorDoc() == null || documentoExtRecepBean.getNuCorDoc().trim().equals("")) {
				String vnuCorDoc = getNroCorrelativoDocumento(nuAnn, documentoExtRecepBean.getCoDepEmi(),
						documentoExtRecepBean.getTiEmi());
				documentoExtRecepBean.setNuCorDoc(vnuCorDoc);
				sqlUpd.append("NU_COR_DOC=").append(vnuCorDoc).append(",\n");
			}

			sqlUpd.append("CIND_SENSIBLE='")
					.append((documentoExtRecepBean.getSensible() == null
							|| documentoExtRecepBean.getSensible().equals("null")) ? "0"
									: documentoExtRecepBean.getSensible())
					.append("',\n");
		}

		if (remitenteDocExtRecepBean != null) {
			if (remitenteDocExtRecepBean.getCoOtros() != null && remitenteDocExtRecepBean.getCoOtros().equals("")) {
				remitenteDocExtRecepBean.setCoOtros(null);
			}
			sqlUpd.append("TI_EMI=?,\n");
			sqlUpd.append("NU_RUC_EMI=?,\n");
			sqlUpd.append("NU_DNI_EMI=?,\n");
			sqlUpd.append("CO_OTR_ORI_EMI=?,\n");
			sqlUpd.append("CDIR_REMITE=?,\n");
			sqlUpd.append("CEXP_CORREOE=?,\n");
			sqlUpd.append("CTELEFONO=?,\n");
			sqlUpd.append("REMI_TI_EMI=?,\n");
			sqlUpd.append("REMI_NU_DNI_EMI=?,\n");
			sqlUpd.append("REMI_CARGO=?,\n");
			sqlUpd.append("CONG_CO_OTR_ORI=?,\n");
			sqlUpd.append("IND_TIPOCONG=?,\n");
			sqlUpd.append("IND_TIPOCONGINV=?,\n");
			sqlUpd.append("REMI_CO_OTR_ORI_EMI=?,\n");
			sqlUpd.append("CCOD_DPTO=?,\n");
			sqlUpd.append("CCOD_PROV=?,\n");
			sqlUpd.append("CCOD_DIST=?,\n");

			// sqlUpd.append("A.AUT_CORREOE='")
			// .append((remitenteDocExtRecepBean.getNotificado() == null
			// || remitenteDocExtRecepBean.getNotificado().equals("null")) ? "0"
			// : remitenteDocExtRecepBean.getNotificado())
			// .append("',\n");
			// sqlUpd.append("A.IND_REITCONGINV='")

			sqlUpd.append("IND_REITCONGINV='")
					.append((remitenteDocExtRecepBean.getReiterativo() == null
							|| remitenteDocExtRecepBean.getReiterativo().equals("null")) ? "0"
									: remitenteDocExtRecepBean.getReiterativo())
					.append("',\n");
		}
		sqlUpd.append("FE_USE_MOD=CURRENT_TIMESTAMP where\nNU_ANN=? and\nNU_EMI=?");

		try {
			if (documentoExtRecepBean != null && remitenteDocExtRecepBean == null) {
				this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { pcoUserMod, documentoExtRecepBean

						.getDeAsu(), documentoExtRecepBean.getDeObservacion(), documentoExtRecepBean.getDeDocSig(),
						documentoExtRecepBean.getCoTipDocAdm(), documentoExtRecepBean.getNuFolios(),
						documentoExtRecepBean.getNuDiaAte(), documentoExtRecepBean.getCoOriDoc(),
						documentoExtRecepBean.getNroDniTramitante(), documentoExtRecepBean.getnSobre(),
						documentoExtRecepBean.getAnioSobre(), documentoExtRecepBean.getCoTraDest(), nuAnn, nuEmi });

			} else if (documentoExtRecepBean == null && remitenteDocExtRecepBean != null) {
				this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { pcoUserMod, remitenteDocExtRecepBean

						.getTiEmi(), remitenteDocExtRecepBean.getNuRuc(), remitenteDocExtRecepBean.getNuDni(),
						remitenteDocExtRecepBean.getCoOtros(), remitenteDocExtRecepBean.getDeDireccion(),
						remitenteDocExtRecepBean.getDeCorreo(), remitenteDocExtRecepBean.getTelefono(),
						remitenteDocExtRecepBean.getEmiResp(), remitenteDocExtRecepBean.getNuDniRes(),
						remitenteDocExtRecepBean.getDeCargo(), remitenteDocExtRecepBean.getCoComision(),
						remitenteDocExtRecepBean.getCoTipoCongresista(), remitenteDocExtRecepBean.getCoTipoInv(),
						remitenteDocExtRecepBean.getCoOtrosRes(), remitenteDocExtRecepBean.getIdDepartamento(),
						remitenteDocExtRecepBean.getIdProvincia(), remitenteDocExtRecepBean.getIdDistrito(), nuAnn,
						nuEmi });

			} else if (documentoExtRecepBean != null && remitenteDocExtRecepBean != null) {

				this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { pcoUserMod, documentoExtRecepBean

						.getDeAsu(), documentoExtRecepBean.getDeObservacion(), documentoExtRecepBean.getDeDocSig(),
						documentoExtRecepBean.getCoTipDocAdm(), documentoExtRecepBean.getNuFolios(),
						documentoExtRecepBean.getNuDiaAte(), documentoExtRecepBean.getCoOriDoc(),
						documentoExtRecepBean.getNroDniTramitante(), documentoExtRecepBean.getnSobre(),
						documentoExtRecepBean.getAnioSobre(), documentoExtRecepBean.getCoTraDest(),
						remitenteDocExtRecepBean.getTiEmi(), remitenteDocExtRecepBean.getNuRuc(),
						remitenteDocExtRecepBean.getNuDni(), remitenteDocExtRecepBean.getCoOtros(),
						remitenteDocExtRecepBean.getDeDireccion(), remitenteDocExtRecepBean.getDeCorreo(),
						remitenteDocExtRecepBean.getTelefono(), remitenteDocExtRecepBean.getEmiResp(),
						remitenteDocExtRecepBean.getNuDniRes(), remitenteDocExtRecepBean.getDeCargo(),
						remitenteDocExtRecepBean.getCoComision(), remitenteDocExtRecepBean.getCoTipoCongresista(),
						remitenteDocExtRecepBean.getCoTipoInv(), remitenteDocExtRecepBean.getCoOtrosRes(),
						remitenteDocExtRecepBean.getIdDepartamento(), remitenteDocExtRecepBean.getIdProvincia(),
						remitenteDocExtRecepBean.getIdDistrito(), nuAnn, nuEmi });
			} else {

				this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { pcoUserMod, nuAnn, nuEmi });
			}
			vReturn = "OK";
		} /*
			 * catch (DuplicateKeyException con) { vReturn =
			 * "Numero de Documento Duplicado"; }
			 */ catch (Exception e) {
			e.printStackTrace();
		}
		return vReturn;
	}

	@Override
	public String updReferenciaDocumentoEmi(String pnuAnn, String pnuEmi, ReferenciaDocExtRecepBean ref) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();
		sqlUpd.append(
				"update IDOSGD.tdtr_referencia \nset NU_ANN_REF = ?, \nNU_EMI_REF = ?, \nNU_DES_REF = ?\nWHERE NU_ANN = ? AND NU_EMI = ? \nAND CO_REF = ?");

		this.getJdbcTemplate().update(sqlUpd.toString(),
				new Object[] { ref.getNuAnn(), ref.getNuEmi(), ref.getNuDes(), pnuAnn, pnuEmi, ref.getCoRef() });
		vReturn = "OK";

		return vReturn;
	}

	@Override
	public String delReferenciaDocumentoEmi(String pnuAnn, String pnuEmi, String pnuAnnRef, String pnuEmiRef) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sqlIns = new StringBuffer();
		sqlIns.append(
				"DELETE FROM IDOSGD.tdtr_referencia\nWHERE NU_ANN = ? AND NU_EMI = ? \nAND NU_ANN_REF = ? AND NU_EMI_REF = ?");

		this.getJdbcTemplate().update(sqlIns.toString(), new Object[] { pnuAnn, pnuEmi, pnuAnnRef, pnuEmiRef });
		vReturn = "OK";

		return vReturn;
	}

	@Override
	public String updDestinatarioDocumentoEmi(String nuAnn, String nuEmi,
			DestinatarioDocumentoEmiBean destinatarioDocumentoEmiBean) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();

		sqlUpd.append(
				"update IDOSGD.TDTV_DESTINOS \nset CO_LOC_DES=?\n,CO_DEP_DES=?\n,CO_EMP_DES=?\n,CO_PRI=?\n,DE_PRO=?\n,CO_MOT=?\n,CO_OTR_ORI_DES=? \n,NU_DNI_DES=? \n,NU_RUC_DES=? \n,FE_USE_MOD=CURRENT_TIMESTAMP\n,CO_USE_MOD=?\nwhere\nNU_ANN=? and\nNU_EMI=? and\nNU_DES=?");

		this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { destinatarioDocumentoEmiBean.getCoLocal(),
				destinatarioDocumentoEmiBean.getCoDependencia(), destinatarioDocumentoEmiBean.getCoEmpleado(),
				destinatarioDocumentoEmiBean.getCoPrioridad(), destinatarioDocumentoEmiBean.getDeIndicaciones(),
				destinatarioDocumentoEmiBean.getCoTramite(), destinatarioDocumentoEmiBean.getCoOtroOrigen(),
				destinatarioDocumentoEmiBean.getNuDni(), destinatarioDocumentoEmiBean.getNuRuc(),
				destinatarioDocumentoEmiBean.getCoUseCre(), nuAnn, nuEmi, destinatarioDocumentoEmiBean.getNuDes() });
		vReturn = "OK";

		return vReturn;
	}

	@Override
	public String delDestinatarioDocumentoEmi(String nuAnn, String nuEmi, String pnuDes) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sqlDel = new StringBuffer();
		sqlDel.append("delete from IDOSGD.TDTV_DESTINOS\nWHERE NU_ANN = ?\nAND NU_EMI = ?\nand NU_DES=?");

		this.getJdbcTemplate().update(sqlDel.toString(), new Object[] { nuAnn, nuEmi, pnuDes });
		vReturn = "OK";

		return vReturn;
	}

	@Override
	public boolean isValidUpdateExpedienteBean(String pnuAnn, String pnuEmi) {
		// TODO Auto-generated method stub
		boolean isValid = false;
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append(" select ISNULL(max(NU_EMI), 0) FROM IDOSGD.TDTV_REMITOS A  ");
		sqlQry.append(" where a.ES_DOC_EMI=5 and A.CO_GRU = '3'  and  ");
		sqlQry.append(" NU_ANN=? and  ");
		sqlQry.append(" NU_EMI=? ");

		String vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
				new Object[] { pnuAnn, pnuEmi });

		if (vReturn.equals("0")) {
			isValid = false;
		} else {
			isValid = true;
		}

		return isValid;
	}

}
