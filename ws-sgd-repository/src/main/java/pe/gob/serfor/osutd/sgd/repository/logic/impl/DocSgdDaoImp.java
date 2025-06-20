package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoObjBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ArchivoPrincipal;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosMPBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosSgdBean;
//import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoInterno;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoMesaPartes;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoSgd;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DocumentoSgdMP;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.EnvioExterno;
import pe.gob.serfor.osutd.sgd.repository.logic.DocSgdDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;

//import pe.gob.serfor.wssisged.logic.bean.ArchivoPrincipal;
//import pe.gob.serfor.wssisged.logic.bean.ParametroDocumentoSgdBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosMPBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosSgdBean;
//import pe.gob.serfor.wssisged.logic.dao.DocSgdDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.DocumentoInterno;
//import pe.gob.serfor.wssisged.logic.model.DocumentoMesaPartes;
//import pe.gob.serfor.wssisged.logic.model.DocumentoSgd;
//import pe.gob.serfor.wssisged.logic.model.DocumentoSgdMP;
//import pe.gob.serfor.wssisged.logic.model.EnvioExterno;
//import pe.gob.serfor.wssisged.logic.model.Expediente;

@Repository("DocSgdDao")
public class DocSgdDaoImp extends JdbcDaoSupport implements DocSgdDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

//	private static final long serialVersionUID = -6550935832637477791L;
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public ArchivoPrincipal buscarArchPrincipal(ParametrosBusqBean bean) throws Exception {
		ArchivoPrincipal documento= new ArchivoPrincipal();

		StringBuilder sql1 = new StringBuilder();
		sql1.append("SELECT \n")
				.append("  CASE \n")
				.append("    WHEN EXISTS ( \n")
				.append("      SELECT 1 \n")
				.append("      FROM IDOSGD.tdtv_archivo_doc WITH (NOLOCK) \n")
				.append("      WHERE nu_ann = ? AND nu_emi = ? AND bl_doc IS NOT NULL \n")
				.append("    ) THEN 1 ELSE 0 \n")
				.append("  END AS tieneArchivo");



		try {

			Object[] params1 = new Object[] { bean.getAnio(), bean.getNumEmi(), bean.getNuAne() };
			int retorno = this.getJdbcTemplate().queryForObject(sql1.toString(),
					BeanPropertyRowMapper.newInstance(Integer.class), params1);

			if (retorno == 0) {
				StringBuilder cadena = new StringBuilder("");
				cadena.append(
						" select  w_de_ruta_origen  wdeRutaOrigen, DE_RUTA_ORIGEN  deRutaOrigen,co_gru  coGru from IDOSGD.TDTV_ARCHIVO_DOC t ");
				cadena.append(" inner join IDOSGD.TDTV_REMITOS r on (t.NU_EMI=r.NU_EMI  and  t.NU_ANN=r.NU_ANN) ");
				cadena.append(" where t.NU_EMI=?  and   t.NU_ANN=?  ");

				DocumentoObjBean documentoObjBean=getNombreArchivo(bean.getAnio(),bean.getNumEmi());


				String baseRuta = ConstantesUtil.RUTA_ARCHIVOS; // ejemplo: /glassfish/tmppcm
				String subRuta = bean.getAnio() + "/" + bean.getNumEmi(); // ejemplo: 2025/0000006429

				String  rutaDestino = baseRuta + "/" + subRuta + "/" + documentoObjBean.getNombreArchivo();

				File directorio = new File(baseRuta + "/" + subRuta);
				if (!directorio.exists()) {
					directorio.mkdirs(); // crea toda la estructura intermedia
				}

				byte[] docuemnto= leerArchivo(rutaDestino);

				Object[] params = new Object[] { bean.getNumEmi(), bean.getAnio() };
				documento = this.getJdbcTemplate().queryForObject(cadena.toString(),
						BeanPropertyRowMapper.newInstance(ArchivoPrincipal.class), params);

				documento.setBlDoc(docuemnto);



			}else {
				StringBuilder cadena = new StringBuilder("");
				cadena.append(
						" select  bl_doc,w_de_ruta_origen  wdeRutaOrigen, DE_RUTA_ORIGEN  deRutaOrigen,co_gru  coGru from IDOSGD.TDTV_ARCHIVO_DOC t ");
				cadena.append(" inner join IDOSGD.TDTV_REMITOS r on (t.NU_EMI=r.NU_EMI  and  t.NU_ANN=r.NU_ANN) ");
				cadena.append(" where t.NU_EMI=?  and   t.NU_ANN=?  ");
				Object[] params = new Object[] { bean.getNumEmi(), bean.getAnio() };
				documento = this.getJdbcTemplate().queryForObject(cadena.toString(),
						BeanPropertyRowMapper.newInstance(ArchivoPrincipal.class), params);
			}


			return documento;

		} catch (EmptyResultDataAccessException e) {
			log.error("Error", e);
			return null;

		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Error", e);
			return null;
		} catch (Exception e) {
			log.error("Error", e);
			throw new Exception(e);
			// return null;
		}
	}



	public DocumentoObjBean getNombreArchivo(String pnuAnn, String pnuEmi) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select \n nu_ann, \n nu_emi, \n de_ruta_origen nombre_Archivo,\n COALESCE(len(bl_doc),0) nu_Tamano,\nw_de_ruta_origen w_Nombre_Archivo,\nCOALESCE(len(w_bl_doc),0) w_Nu_Tamano \n from IDOSGD.tdtv_archivo_doc WITH (NOLOCK) \n where nu_ann = ?\nand nu_emi = ?");

		DocumentoObjBean docObjBean = new DocumentoObjBean();

		try {
			docObjBean = (DocumentoObjBean) this.getJdbcTemplate().queryForObject(sql.toString(), (RowMapper) BeanPropertyRowMapper.newInstance(DocumentoObjBean.class), new Object[]{pnuAnn, pnuEmi});
		} catch (EmptyResultDataAccessException e) {
			docObjBean = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docObjBean;
	}

	public byte[] leerArchivo(String rutaArchivo) throws IOException {
		File archivo = new File(rutaArchivo);
		FileInputStream fis = null;
		byte[] contenido = null;

		try {
			fis = new FileInputStream(archivo);
			contenido = new byte[(int) archivo.length()];
			fis.read(contenido);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return contenido;
	}







	@Override
	public List<DocumentoSgd> buscarDocumento(ParametrosSgdBean bean) throws Exception {
		System.out.println(bean.toString());
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		Map<String, Object> objectParam = new HashMap<String, Object>();

		StringBuilder cadena = new StringBuilder("");

		cadena.append(
				" SELECT a.NU_EMI, a.NU_ANN  AS ANIO,NU_COR_EMI, a.CO_GRU, a.FE_EMI fecEmision,[IDOSGD].[PK_SGD_DESCRIPCION_TI_DESTINO](A.TI_EMI) DE_TI_EMI,");
		cadena.append("  case when a.CO_GRU='1'  THEN 'OFICINA'  when a.CO_GRU='2'  THEN 'ESPECIALISTA' ");
		cadena.append(" when a.CO_GRU='3'  THEN 'MESA DE PARTES' ELSE ' ' END AS bandejaorigen, ");

		cadena.append(
				" a.CO_TIP_DOC_ADM as codTipDoc,IDOSGD.PK_SGD_DESCRIPCION_DE_DOCUMENTO (a.CO_TIP_DOC_ADM) desDocumento,");
		cadena.append(" CASE A.TI_EMI       WHEN '01' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG ");
		cadena.append(" WHEN '05' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG    ELSE A.DE_DOC_SIG ");
		cadena.append(
				"END  AS NUM_DOC, A.ES_DOC_EMI,     ISNULL(R.NU_EXPEDIENTE,'') numExpediente,A.NU_FOLIOS as num_folios,");
		cadena.append("[IDOSGD].[PK_SGD_DESCRIPCION_ESTADOS_MP](A.ES_DOC_EMI, 'TDTV_REMITOS') ESTADO_DOC_DES,");
		cadena.append("[IDOSGD].[PK_SGD_DESCRIPCION_DE_DEPENDENCIA](A.CO_DEP_EMI) DEPENDENCIA_ORIGEN,");
		cadena.append(
				" [IDOSGD].PK_SGD_DESCRIPCION_TI_EMI_EMP(A.NU_ANN, A.NU_EMI)  AS REMITENTE, A.DE_ASU  ASUNTO,A.CO_DEP_EMI,A.CO_EMP_EMI,A.CO_OTR_ORI_EMI,");
		cadena.append(" A.NU_DOC_EMI,A.DE_DOC_SIG, A.TI_EMI, A.NU_RUC_EMI, A.NU_DNI_EMI, ");
		cadena.append(" A.TI_ENV_MSJ,  CASE A.TI_ENV_MSJ WHEN '0' THEN 'MESA DE PARTES' WHEN '1' THEN 'ENVDIRECTO' ");
		cadena.append(
				" WHEN '2' THEN 'MESA VIRTUAL'  WHEN '3' THEN 'NOTIFICACIÓN ELECECTRÓNICA' END    tipoEnvioExterno");

		cadena.append(
				" FROM IDOSGD.TDTV_REMITOS A  INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN R ON R.NU_ANN=A.NU_ANN AND R.NU_EMI=A.NU_EMI ");
		cadena.append(" WHERE 1=1 AND A.ES_DOC_EMI NOT IN ('5', '9', '7') AND A.CO_GRU = '3'  AND A.ES_ELI='0'");

		cadena.append("  and  A.NU_ANN = :panio ");
		objectParam.put("panio", bean.getAnio());
		if (bean.getNumEmi() != null) {
			cadena.append(" and A.NU_EMI=:pnuEmi ");
			objectParam.put("pnuEmi", bean.getNumEmi());
		}
		if (bean.getNumDocumento() != null) {
			cadena.append(" and A.NU_DOC_EMI=:pnuDoc ");
			objectParam.put("pnuEmi", bean.getNumDocumento());
		}
		if (bean.getTipDoc() != null) {
			cadena.append(" and a.CO_TIP_DOC_ADM=:pcodTipDoc ");
			objectParam.put("pcodTipDoc", bean.getTipDoc());
		}
		if (bean.getNumExpediente() != null) {
			cadena.append(" and  R.NU_EXPEDIENTE=:pnuExpediente ");
			objectParam.put("pnuExpediente", bean.getNumExpediente());
		}
		if (bean.getEsDocOrigen() != null && bean.getEsDocOrigen().equals("1")) {
			cadena.append(" AND A.CO_GRU IN ('1','3')  and  TI_EMI_ref  is null ");
		}

		cadena.append(" UNION ALL ");
		cadena.append("select a.NU_EMI, a.NU_ANN AS ANIO,NU_COR_EMI, a.CO_GRU,FE_EMI fecEmision,");
		cadena.append(" [IDOSGD].[PK_SGD_DESCRIPCION_TI_DESTINO](A.TI_EMI) DE_TI_EMI,  ");
		cadena.append("  case when a.CO_GRU='1'  THEN 'OFICINA'  when a.CO_GRU='2'  THEN 'ESPECIALISTA' ");
		cadena.append(" when a.CO_GRU='3'  THEN 'MESA DE PARTES' ELSE ' ' END AS bandejaorigen, ");
		cadena.append(
				" a.CO_TIP_DOC_ADM as codTipDoc,IDOSGD.PK_SGD_DESCRIPCION_DE_DOCUMENTO (a.CO_TIP_DOC_ADM) desDocumento,");
		cadena.append(
				" R.NU_DOC  AS NUM_DOC, A.ES_DOC_EMI, ISNULL(R.NU_EXPEDIENTE,'') numExpediente,A.NU_FOLIOS as num_folios, [IDOSGD].[PK_SGD_DESCRIPCION_ESTADOS](A.ES_DOC_EMI, 'TDTV_REMITOS') ESTADO_DOC_DES, ");
		cadena.append(
				" [IDOSGD].[PK_SGD_DESCRIPCION_DE_DEPENDENCIA](A.CO_DEP_EMI) as DEPENDENCIA_ORIGEN, IDOSGD.PK_SGD_DESCRIPCION_TI_EMI_EMP (a.NU_ANN, a.NU_EMI) REMITENTE, ");
		cadena.append(
				" DE_ASU ASUNTO ,CO_DEP_EMI, A.CO_EMP_EMI, A.CO_OTR_ORI_EMI, A.NU_DOC_EMI,A.DE_DOC_SIG, A.TI_EMI, A.NU_RUC_EMI, A.NU_DNI_EMI, ");
		cadena.append(" A.TI_ENV_MSJ,  CASE A.TI_ENV_MSJ WHEN '0' THEN 'MESA DE PARTES' WHEN '1' THEN 'ENVDIRECTO' ");
		cadena.append(
				" WHEN '2' THEN 'MESA VIRTUAL'  WHEN '3' THEN 'NOTIFICACIÓN ELECECTRÓNICA' END    tipoEnvioExterno");
		cadena.append(
				" FROM IDOSGD.TDTV_REMITOS A INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN R  ON R.NU_ANN = A.NU_ANN AND R.NU_EMI = A.NU_EMI ");
		cadena.append(
				"	and  A.ES_DOC_EMI NOT IN ('5', '9', '7')  AND A.ES_ELI = '0' 	 AND A.ES_DOC_EMI NOT IN ('5', '9', '7') AND A.IN_OFICIO = '0' AND A.CO_GRU IN ('1','2') ");

		cadena.append("   where  A.NU_ANN = :panio ");
		objectParam.put("panio", bean.getAnio());
		if (bean.getNumEmi() != null) {
			cadena.append(" and A.NU_EMI=:pnuEmi ");
			objectParam.put("pnuEmi", bean.getNumEmi());
		}
		if (bean.getNumDocumento() != null) {
			cadena.append(" and R.NU_DOC=:pnuDoc ");
			objectParam.put("pnuDoc", bean.getNumDocumento());
		}
		if (bean.getTipDoc() != null) {
			cadena.append(" and a.CO_TIP_DOC_ADM=:pcodTipDoc ");
			objectParam.put("pcodTipDoc", bean.getTipDoc());
		}
		if (bean.getNumExpediente() != null) {
			cadena.append(" and  R.NU_EXPEDIENTE=:pnuExpediente ");
			objectParam.put("pnuExpediente", bean.getNumExpediente());
		}
		if (bean.getEsDocOrigen() != null && bean.getEsDocOrigen().equals("1")) {
			cadena.append(" AND A.CO_GRU IN ('1','3')  and  TI_EMI_ref  is null ");
		}
		cadena.append("ORDER BY FE_EMI");

		try {
//			List<DocumentoSgd> lstDoc = this.getJdbcTemplate().query(cadena.toString(), objectParam,
//					(RowMapper) BeanPropertyRowMapper.newInstance(DocumentoSgd.class));
			List<DocumentoSgd> lstDoc = namedParameterJdbcTemplate.query(cadena.toString(), objectParam, BeanPropertyRowMapper.newInstance(DocumentoSgd.class));

			//System.out.println(lstDoc.get(0).toString());

			return lstDoc;

		} catch (EmptyResultDataAccessException e) {
			log.error("Error", e);
			return null;

		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Error", e);
			return null;
		} catch (Exception e) {
			log.error("Error", e);
			throw new Exception(e);

		}
	}

	@Override
	public List<EnvioExterno> buscarTipoEnvioExterno(int anio, String nuExpediente) throws Exception {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		Map<String, Object> objectParam = new HashMap<String, Object>();

		StringBuilder cadena = new StringBuilder("");

		cadena.append(
				" select A.TI_ENV_MSJ, a.nu_emi,a.nu_ann, CASE A.TI_ENV_MSJ WHEN '0' THEN 'MESA DE PARTES' WHEN '1' THEN 'ENVDIRECTO' ");
		cadena.append(
				" WHEN '2' THEN 'MESA VIRTUAL'  WHEN '3' THEN 'NOTIFICACIÓN ELECECTRÓNICA' END    tipoEnvioExterno, DE_RUTA_ORIGEN    AS nomArchivoPrincipal ");
		cadena.append(" from IDOSGD.TDTV_REMITOS A INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN B  ");
		cadena.append("  ON B.NU_ANN=A.NU_ANN AND B.NU_EMI=A.NU_EMI  ");
		cadena.append(" inner join   IDOSGD.TDTV_ARCHIVO_DOC  TA  ON TA.NU_EMI=B.NU_EMI  and   TA.NU_ANN=B.NU_ANN ");
		cadena.append(
				"  WHERE 1=1 AND A.NU_ANN =:panio  AND A.ES_DOC_EMI  not in('05','07','09')  and A.TI_ENV_MSJ  in('0','3') ");
		objectParam.put("panio", anio);
		cadena.append(" and  B.NU_EXPEDIENTE=:pnuExpediente ");
		objectParam.put("pnuExpediente", nuExpediente);
		cadena.append("ORDER BY FE_EMI");
		try {
//			List<EnvioExterno> lstDoc = this.getJdbcTemplate().query(cadena.toString(), objectParam,
//					(RowMapper) BeanPropertyRowMapper.newInstance(EnvioExterno.class));
			List<EnvioExterno> lstDoc = namedParameterJdbcTemplate.query(cadena.toString(), objectParam,
					BeanPropertyRowMapper.newInstance(EnvioExterno.class));

			return lstDoc;

		} catch (EmptyResultDataAccessException e) {
			log.error("Error", e);
			return null;

		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Error", e);
			return null;
		} catch (Exception e) {
			log.error("Error", e);
			throw new Exception(e);

		}
	}

	@Override
	public List<DocumentoSgdMP> buscarDocumentosMesaPartesOtros(ParametrosMPBean bean) throws Exception {

		//System.out.println(bean.toString());

		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		Map<String, Object> objectParam = new HashMap<String, Object>();

		StringBuilder cadena = new StringBuilder("");

		cadena.append(
				" SELECT a.NU_EMI, a.NU_ANN  AS ANIO,o.NU_DOC_OTR_ORI,NU_COR_EMI, a.CO_GRU, a.FE_EMI fecEmision,[IDOSGD].[PK_SGD_DESCRIPCION_TI_DESTINO](A.TI_EMI) DE_TI_EMI,");
		cadena.append("  case when a.CO_GRU='1'  THEN 'OFICINA'  when a.CO_GRU='2'  THEN 'ESPECIALISTA' ");
		cadena.append(" when a.CO_GRU='3'  THEN 'MESA DE PARTES' ELSE ' ' END AS bandejaorigen, ");
		cadena.append(
				" a.CO_TIP_DOC_ADM as codTipDoc,IDOSGD.PK_SGD_DESCRIPCION_DE_DOCUMENTO (a.CO_TIP_DOC_ADM) desDocumento,");
		cadena.append(" CASE A.TI_EMI       WHEN '01' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG ");
		cadena.append(" WHEN '05' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG    ELSE A.DE_DOC_SIG ");
		cadena.append(
				"END  AS NUM_DOC, A.ES_DOC_EMI,     ISNULL(R.NU_EXPEDIENTE,'') numExpediente,A.NU_FOLIOS as num_folios,");
		cadena.append("[IDOSGD].[PK_SGD_DESCRIPCION_ESTADOS_MP](A.ES_DOC_EMI, 'TDTV_REMITOS') ESTADO_DOC_DES,");
		cadena.append("[IDOSGD].[PK_SGD_DESCRIPCION_DE_DEPENDENCIA](A.CO_DEP_EMI) DEPENDENCIA_ORIGEN,");
		cadena.append(
				" [IDOSGD].PK_SGD_DESCRIPCION_TI_EMI_EMP(A.NU_ANN, A.NU_EMI)  AS REMITENTE, A.DE_ASU  ASUNTO,A.CO_DEP_EMI,A.CO_EMP_EMI,A.CO_OTR_ORI_EMI,");
		cadena.append(" A.NU_DOC_EMI,A.DE_DOC_SIG, A.TI_EMI tiEmi, A.NU_RUC_EMI  nuRucEmi, ");
		cadena.append(
				"A.NU_DNI_EMI nuDniCiudadano,   IDOSGD.PK_SGD_DESCRIPCION_ANI_SIMIL(A.NU_DNI_EMI) nombreCiudadano, ");
		cadena.append(
				"A.REMI_NU_DNI_EMI  	nuDniRepLegal,   IDOSGD.PK_SGD_DESCRIPCION_ANI_SIMIL(A.REMI_NU_DNI_EMI) nombreRepLegal");
		cadena.append(
				" FROM IDOSGD.TDTV_REMITOS A  INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN R ON R.NU_ANN=A.NU_ANN AND R.NU_EMI=A.NU_EMI ");
		cadena.append(
				" inner join IDOSGD.tdtr_otro_origen o  on (a.CO_OTR_ORI_EMI= o.CO_OTR_ORI and CO_TIP_OTR_ORI=:tipDocex)");
		cadena.append(" WHERE 1=1 AND A.ES_DOC_EMI NOT IN ('5', '9', '7') AND A.CO_GRU = '3'  AND A.ES_ELI='0'");
		cadena.append("  and  A.NU_ANN = :panio ");
		objectParam.put("panio", bean.getAnio());

		if (bean.getNumDocumento() != null) {
			cadena.append(" and A.NU_DOC_EMI=:pnuDoc ");
			objectParam.put("pnuDoc", bean.getNumDocumento());
		}
		if (bean.getTipDoc() != null) {
			cadena.append(" and a.CO_TIP_DOC_ADM=:pcodTipDoc ");
			objectParam.put("pcodTipDoc", bean.getTipDoc());
		}

		else if (bean.getTipoBusqueda().equals("6")) { // carnet de extranjeria
			cadena.append(" and o.NU_DOC_OTR_ORI=:nuDocOtr ");
			objectParam.put("tipDocex", "02");
			objectParam.put("nuDocOtr", bean.getNumDocIden());

		}

		else if (bean.getTipoBusqueda().equals("7")) { // pasaporte
			cadena.append(" and o.NU_DOC_OTR_ORI=:nuDocOtr ");
			objectParam.put("tipDocex", "03");
			objectParam.put("nuDocOtr", bean.getNumDocIden());

		}
		// and o.NU_DOC_OTR_ORI='40404040'

		cadena.append("ORDER BY FE_EMI");

		try {
//			List<DocumentoSgdMP> lstDoc = this.getJdbcTemplate().query(cadena.toString(), objectParam,
//					(RowMapper) BeanPropertyRowMapper.newInstance(DocumentoSgdMP.class));
			List<DocumentoSgdMP> lstDoc = namedParameterJdbcTemplate.query(cadena.toString(), objectParam, BeanPropertyRowMapper.newInstance(DocumentoSgdMP.class));

			return lstDoc;

		} catch (EmptyResultDataAccessException e) {
			log.error("Error", e);
			return null;

		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Error", e);
			return null;
		} catch (Exception e) {
			log.error("Error", e);
			throw new Exception(e);

		}
	}

	@Override
	public List<DocumentoSgdMP> buscarDocumentosMesaPartes(ParametrosMPBean bean) throws Exception {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		Map<String, Object> objectParam = new HashMap<String, Object>();

		StringBuilder cadena = new StringBuilder("");

		cadena.append(
				" SELECT a.NU_EMI, a.NU_ANN  AS ANIO,NU_COR_EMI, a.CO_GRU, a.FE_EMI fecEmision,[IDOSGD].[PK_SGD_DESCRIPCION_TI_DESTINO](A.TI_EMI) DE_TI_EMI,");
		cadena.append("  case when a.CO_GRU='1'  THEN 'OFICINA'  when a.CO_GRU='2'  THEN 'ESPECIALISTA' ");
		cadena.append(" when a.CO_GRU='3'  THEN 'MESA DE PARTES' ELSE ' ' END AS bandejaorigen, ");
		cadena.append(
				" a.CO_TIP_DOC_ADM as codTipDoc,IDOSGD.PK_SGD_DESCRIPCION_DE_DOCUMENTO (a.CO_TIP_DOC_ADM) desDocumento,");
		cadena.append(" CASE A.TI_EMI       WHEN '01' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG ");
		cadena.append(" WHEN '05' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG    ELSE A.DE_DOC_SIG ");
		cadena.append(
				"END  AS NUM_DOC, A.ES_DOC_EMI,     ISNULL(R.NU_EXPEDIENTE,'') numExpediente,A.NU_FOLIOS as num_folios,");
		cadena.append("[IDOSGD].[PK_SGD_DESCRIPCION_ESTADOS_MP](A.ES_DOC_EMI, 'TDTV_REMITOS') ESTADO_DOC_DES,");
		cadena.append("[IDOSGD].[PK_SGD_DESCRIPCION_DE_DEPENDENCIA](A.CO_DEP_EMI) DEPENDENCIA_ORIGEN,");
		cadena.append(
				" [IDOSGD].PK_SGD_DESCRIPCION_TI_EMI_EMP(A.NU_ANN, A.NU_EMI)  AS REMITENTE, A.DE_ASU  ASUNTO,A.CO_DEP_EMI,A.CO_EMP_EMI,A.CO_OTR_ORI_EMI,");
		cadena.append(" A.NU_DOC_EMI,A.DE_DOC_SIG, A.TI_EMI tiEmi, A.NU_RUC_EMI  nuRucEmi, ");
		cadena.append(
				"A.NU_DNI_EMI nuDniCiudadano,   IDOSGD.PK_SGD_DESCRIPCION_ANI_SIMIL(A.NU_DNI_EMI) nombreCiudadano, ");
		cadena.append(
				"A.REMI_NU_DNI_EMI  	nuDniRepLegal,   IDOSGD.PK_SGD_DESCRIPCION_ANI_SIMIL(A.REMI_NU_DNI_EMI) nombreRepLegal");
		cadena.append(
				" FROM IDOSGD.TDTV_REMITOS A  INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN R ON R.NU_ANN=A.NU_ANN AND R.NU_EMI=A.NU_EMI ");
		cadena.append(" WHERE 1=1 AND A.ES_DOC_EMI NOT IN ('5', '9', '7') AND A.CO_GRU = '3'  AND A.ES_ELI='0'");
		cadena.append("  and  A.NU_ANN = :panio ");
		objectParam.put("panio", bean.getAnio());

		if (bean.getNumDocumento() != null) {
			cadena.append(" and A.NU_DOC_EMI=:pnuDoc ");
			objectParam.put("pnuDoc", bean.getNumDocumento());
		}
		if (bean.getTipDoc() != null) {
			cadena.append(" and a.CO_TIP_DOC_ADM=:pcodTipDoc ");
			objectParam.put("pcodTipDoc", bean.getTipDoc());
		}

		if (bean.getTipoBusqueda().equals("4")) { // consulta por dni
			if (bean.getNumDocIden() != null) {
				cadena.append(" and ( A.REMI_NU_DNI_EMI=:pnuDNI  or A.NU_DNI_EMI=:pnuDNI )");
				objectParam.put("pnuDNI", bean.getNumDocIden());
			}
		} else if (bean.getTipoBusqueda().equals("3")) { // consulta por numero de emision
			if (bean.getNumEmi() != null) {
				cadena.append(" and  R.NU_EXPEDIENTE=:pnumEmi ");
				objectParam.put("pnumEmi", bean.getNumEmi());
			}
		} else if (bean.getTipoBusqueda().equals("2")) { // consulta por numero de expediente
			if (bean.getNumExpediente() != null) {
				cadena.append(" and  R.NU_EXPEDIENTE=:pnuExpediente ");
				objectParam.put("pnuExpediente", bean.getNumExpediente());
			}
		} else if (bean.getTipoBusqueda().equals("5")) { // consulta por numero de ruc
			cadena.append(" and  A.NU_RUC_EMI=:pnuRuc ");
			objectParam.put("pnuRuc", bean.getNumDocIden());
		}

		cadena.append("ORDER BY FE_EMI");

		try {
//			List<DocumentoSgdMP> lstDoc = this.getJdbcTemplate().query(cadena.toString(), objectParam,
//					(RowMapper) BeanPropertyRowMapper.newInstance(DocumentoSgdMP.class));
			List<DocumentoSgdMP> lstDoc = namedParameterJdbcTemplate.query(cadena.toString(),
					objectParam, BeanPropertyRowMapper.newInstance(DocumentoSgdMP.class));

			return lstDoc;

		} catch (EmptyResultDataAccessException e) {
			log.error("Error", e);
			return null;

		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Error", e);
			return null;
		} catch (Exception e) {
			log.error("Error", e);
			throw new Exception(e);

		}
	}

	@Override
	public List<DocumentoSgd> listarDocumento() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocumentoInterno> buscarDatosDoumentoInterno(ParametrosSgdBean bean) throws Exception {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		Map<String, Object> objectParam = new HashMap<String, Object>();

		StringBuilder cadena = new StringBuilder(" ");
		cadena.append("  select a.NU_EMI, a.NU_ANN anio,NU_COR_EMI, a.CO_GRU, ");
		cadena.append(
				"  a.CO_TIP_DOC_ADM as codTipDoc,IDOSGD.PK_SGD_DESCRIPCION_DE_DOCUMENTO (a.CO_TIP_DOC_ADM) desDocumento,  R.NU_DOC ");
		cadena.append(
				" ,ISNULL(R.NU_EXPEDIENTE,'') nu_expediente, IDOSGD.PK_SGD_DESCRIPCION_TI_EMI_EMP (a.NU_ANN, a.NU_EMI) dependenciaDestino, ");
		cadena.append(
				" FE_EMI ,DE_ASU asunto ,CO_LOC_EMI,  CO_DEP_EMI,  A.CO_EMP_EMI, IDOSGD.PK_SGD_DESCRIPCION_TI_EMI_EMP (a.NU_ANN, a.NU_EMI) remitente ");
		cadena.append(
				" FROM IDOSGD.TDTV_REMITOS A INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN R  ON R.NU_ANN = A.NU_ANN AND R.NU_EMI = A.NU_EMI ");
		cadena.append(" and A.ES_DOC_EMI = '0'  AND A.ES_ELI = '0' 	 AND A.ES_DOC_EMI NOT IN ('5', '9', '7')	");
		cadena.append(" AND A.IN_OFICIO = '0' AND A.CO_GRU IN ('1','2') ");
		// and R.NU_EXPEDIENTE='2020-0004355'

		objectParam.put("panio", bean.getAnio());
		if (bean.getNumEmi() != null) {
			cadena.append(" and R.NU_EMI=:pnuEmi ");
			objectParam.put("pnuEmi", bean.getNumEmi());
		}
		if (bean.getNumDocumento() != null) {
			cadena.append(" and R.NU_DOC=:pnuDoc ");
			objectParam.put("pnuEmi", bean.getNumDocumento());
		}
		if (bean.getTipDoc() != null) {
			cadena.append(" and a.CO_TIP_DOC_ADM=:pcodTipDoc");
			objectParam.put("pcodTipDoc", bean.getTipDoc());
		}
		if (bean.getNumExpediente() != null) {
			cadena.append(" and  R.NU_EXPEDIENTE=:pnuExpediente");
			objectParam.put("pnuExpediente", bean.getNumExpediente());
		}

		try {

//			List<DocumentoInterno> lstDoc = this.getJdbcTemplate().query(cadena.toString(), objectParam,
//					(RowMapper) BeanPropertyRowMapper.newInstance(DocumentoInterno.class));
			List<DocumentoInterno> lstDoc = namedParameterJdbcTemplate.query(cadena.toString(),
					objectParam, BeanPropertyRowMapper.newInstance(DocumentoInterno.class));

			return lstDoc;

		} catch (EmptyResultDataAccessException e) {
			log.error("Error", e);
			return null;

		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Error", e);
			return null;
		} catch (Exception e) {
			log.error("Error", e);
			throw new Exception(e);

		}
	}

	@Override
	public List<DocumentoMesaPartes> buscarDocumentoMesaPartes(ParametrosSgdBean bean) throws Exception {
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
		Map<String, Object> objectParam = new HashMap<String, Object>();

		StringBuilder cadena = new StringBuilder("");

		cadena.append(" SELECT A.CO_GRU, [IDOSGD].[PK_SGD_DESCRIPCION_TI_DESTINO](A.TI_EMI) DE_TI_EMI,a.FE_EMI, ");
		cadena.append(" [IDOSGD].PK_SGD_DESCRIPCION_TI_EMI_EMP(A.NU_ANN, A.NU_EMI) ORIGEN,  ");
		cadena.append(" [IDOSGD].[PK_SGD_DESCRIPCION_DE_DOCUMENTO](A.CO_TIP_DOC_ADM) tipoDocumento, ");
		cadena.append(" CASE A.TI_EMI       WHEN '01' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG ");
		cadena.append("WHEN '05' THEN A.NU_DOC_EMI + '-' + A.NU_ANN + '/' + A.DE_DOC_SIG    ELSE A.DE_DOC_SIG ");
		cadena.append(" END nuDocumento,    ");
		cadena.append(
				"  A.ES_DOC_EMI codEstDocEmitido, [IDOSGD].[PK_SGD_DESCRIPCION_ESTADOS_MP](A.ES_DOC_EMI, 'TDTV_REMITOS') estado,");
		cadena.append(" [IDOSGD].[PK_SGD_DESCRIPCION_DE_LOCAL](A.CO_LOC_EMI) local,  ");
		cadena.append(" [IDOSGD].[PK_SGD_DESCRIPCION_DE_DEPENDENCIA](A.CO_DEP) dependenciaOrigen, ");
		cadena.append(
				" B.NU_EXPEDIENTE, A.DE_ASU  ASUNTO,A.CO_GRU,A.CO_TIP_DOC_ADM  as codTipDoc,A.CO_DEP_EMI,A.CO_EMP_EMI,A.CO_OTR_ORI_EMI, ");
		cadena.append(
				"  A.NU_DOC_EMI,A.DE_DOC_SIG, A.TI_EMI,A.NU_RUC_EMI, A.NU_DNI_EMI, A.ES_DOC_EMI, A.CO_DEP, A.NU_COR_EMI  ");
		cadena.append(
				" FROM IDOSGD.TDTV_REMITOS A  INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN B ON B.NU_ANN=A.NU_ANN AND B.NU_EMI=A.NU_EMI WHERE 1=1  ");
		cadena.append(
				"  AND A.ES_DOC_EMI NOT IN ('5', '9', '7') AND A.CO_GRU = '3' AND A.TI_EMI<>'01' AND A.ES_ELI='0'  ");
		// cadena.append(" AND A.CO_DEP_EMI = '10602' ");

		objectParam.put("panio", bean.getAnio());
		if (bean.getNumEmi() != null) {
			cadena.append(" and a.NU_EMI=:pnuEmi ");
			objectParam.put("pnuEmi", bean.getNumEmi());
		}
		if (bean.getNumDocumento() != null) {
			cadena.append(" and b.NU_DOC=:pnuDoc ");
			objectParam.put("pnuEmi", bean.getNumDocumento());
		}
		if (bean.getTipDoc() != null) {
			cadena.append(" and a.CO_TIP_DOC_ADM=:pcodTipDoc");
			objectParam.put("pcodTipDoc", bean.getTipDoc());
		}
		if (bean.getNumExpediente() != null) {
			cadena.append(" and  B.NU_EXPEDIENTE=:pnuExpediente");
			objectParam.put("pnuExpediente", bean.getNumExpediente());
		}

		try {

//			List<DocumentoMesaPartes> lstDoc = this.getJdbcTemplate().query(cadena.toString(), objectParam,
//					(RowMapper) BeanPropertyRowMapper.newInstance(DocumentoMesaPartes.class));
			List<DocumentoMesaPartes> lstDoc = namedParameterJdbcTemplate.query(cadena.toString(),
					objectParam, BeanPropertyRowMapper.newInstance(DocumentoMesaPartes.class));

			return lstDoc;

		} catch (EmptyResultDataAccessException e) {
			log.error("Error", e);
			return null;

		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Error", e);
			return null;
		} catch (Exception e) {
			log.error("Error", e);
			throw new Exception(e);

		}
	}
//	@Override
//	public String buscarNroExpediente(ParametrosSgdBean bean) throws Exception {
//		
//		Map<String, Object> objectParam = new HashMap<String, Object>();
//		 
//		StringBuilder   cadena = new StringBuilder("");
//
//	
//	 cadena.append( "select  ISNULL(R.NU_EXPEDIENTE,'') nu_expediente  ");
//	 cadena.append(" FROM IDOSGD.TDTV_REMITOS A INNER JOIN IDOSGD.TDTX_REMITOS_RESUMEN R  ON R.NU_ANN = A.NU_ANN AND R.NU_EMI = A.NU_EMI ");   
//	 cadena.append("	and  A.ES_DOC_EMI NOT IN ('5', '9', '7')  AND A.ES_ELI = '0' 	 AND A.ES_DOC_EMI NOT IN ('5', '9', '7') AND A.IN_OFICIO = '0' AND A.CO_GRU IN ('1','2') ");	
//			
//	 cadena.append("   where  A.NU_ANN = :panio ");
//		    objectParam.put("panio", bean.getAnio());
//		    if(bean.getNumEmi()!=null) {
//			    cadena.append(" and A.NU_EMI=:pnuEmi ");	
//			    objectParam.put("pnuEmi", bean.getNumEmi());
//			}
//		    
//			try {
//		           return this.namedParameterJdbcTemplateS.queryForObject(cadena.toString(),objectParam, (RowMapper)BeanPropertyRowMapper.newInstance( Expediente.class));
//		           
//		       	} catch (EmptyResultDataAccessException e){
//		           	log.error("Error",e);
//		               return null;
//		           
//		       	} catch (IncorrectResultSizeDataAccessException e) {
//		           	log.error("Error",e);
//		           	return null;
//		       	} catch (Exception e) {
//		       		log.error("Error",e);
//		       		throw new Exception(e);
//		       	}
//				
//		
//	}

}
