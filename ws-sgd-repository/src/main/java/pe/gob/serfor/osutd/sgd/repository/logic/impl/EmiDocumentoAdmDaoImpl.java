package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DestinoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoEmiBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoObjBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ReferenciaRemitoBean;
import pe.gob.serfor.osutd.sgd.repository.logic.EmiDocumentoAdmDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import pe.gob.serfor.wssisged.logic.bean.integracion.DestinoBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoEmiBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoObjBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.ReferenciaRemitoBean;
//import pe.gob.serfor.wssisged.logic.dao.EmiDocumentoAdmDao;
//
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;

@Repository
public class EmiDocumentoAdmDaoImpl extends JdbcDaoSupport implements EmiDocumentoAdmDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public String updDocumentoObj(final DocumentoObjBean docObjBean) throws Exception {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		boolean inInsert = false;

		try {
			int cant = 0;
			/*
			 * REVISAR COMPATBILIDAD queryForInt int cant = this.jdbcTemplate.queryForInt(
			 * "select count(nu_ann) from IDOSGD.tdtv_archivo_doc where nu_ann = ? and nu_emi = ?"
			 * , new Object[] { docObjBean.getNuAnn(), docObjBean.getNuEmi() });
			 */
			String result = null;

			StringBuffer sql = new StringBuffer();
			sql.append(" select count(nu_ann) from IDOSGD.tdtv_archivo_doc where nu_ann = ? and nu_emi = ?");

			result = (String) this.getJdbcTemplate().queryForObject(sql.toString(), String.class,
					new Object[] { docObjBean.getNuAnn(), docObjBean.getNuEmi() });

			cant = Integer.parseInt(result);

			if (cant > 0) {
				inInsert = false;
			} else {
				inInsert = true;
			}

			DefaultLobHandler defaultLobHandler = new DefaultLobHandler();
			if (inInsert) {


				sql = new StringBuffer();
				sql.append(
						"INSERT INTO IDOSGD.tdtv_archivo_doc (NU_ANN,NU_EMI,DE_RUTA_ORIGEN,ES_FIRMA,FEULA)\nVALUES(?,?,?,'0', (SELECT CONVERT(VARCHAR(10), CURRENT_TIMESTAMP, 112)))");



				String nombreArchivo = docObjBean.getNombreArchivo();

				String baseRuta= ConstantesUtil.RUTA_ARCHIVOS;
				String subRuta = docObjBean.getNuAnn() + "/" + docObjBean.getNuEmi();

				String rutaDestino = baseRuta + "/" + subRuta + "/" + nombreArchivo;

				File directorio = new File(baseRuta + "/" + subRuta);
				if (!directorio.exists()) {
					directorio.mkdirs(); // crea toda la estructura intermedia
				}


				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(rutaDestino);
					fos.write(docObjBean.getDocumento());
					fos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (fos != null) {
						try {
							fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}


				this.getJdbcTemplate().execute(sql.toString(),
						(PreparedStatementCallback) new AbstractLobCreatingPreparedStatementCallback(
								(LobHandler) defaultLobHandler) {
							protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
								ps.setString(1, docObjBean.getNuAnn());
								ps.setString(2, docObjBean.getNuEmi());
								//lobCreator.setBlobAsBytes(ps, 3, docObjBean.getDocumento());
								ps.setString(3, docObjBean.getNombreArchivo());
								//lobCreator.setBlobAsBytes(ps, 5, docObjBean.getDocumento());
								//ps.setString(6, docObjBean.getNombreArchivo());
							}
						});
				vReturn = "OK";

			} else {

				StringBuffer sql1 = new StringBuffer();
				StringBuffer sql2 = new StringBuffer();
				System.out.println("ingresa a update");
				/*if (docObjBean.getNombreArchivo().toUpperCase().indexOf(".DOCX") >= 0) {
					System.out.println("ingresa a update 2");
					sql1.append(
							"UPDATE IDOSGD.TDTV_ARCHIVO_DOC SET W_BL_DOC = ?, W_DE_RUTA_ORIGEN = ? \nWHERE NU_ANN = ? \nAND NU_EMI = ? ");

					this.getJdbcTemplate().execute(sql1.toString(),
							(PreparedStatementCallback) new AbstractLobCreatingPreparedStatementCallback(
									(LobHandler) defaultLobHandler) {
								protected void setValues(PreparedStatement ps, LobCreator lobCreator)
										throws SQLException {
									lobCreator.setBlobAsBytes(ps, 1, docObjBean.getDocumento());
									ps.setString(2, docObjBean.getNombreArchivo());
									ps.setString(3, docObjBean.getNuAnn());
									ps.setString(4, docObjBean.getNuEmi());
								}
							});
				}*/

				sql2.append(
						"UPDATE IDOSGD.TDTV_ARCHIVO_DOC SET DE_RUTA_ORIGEN = ?, FEULA=(SELECT CONVERT(VARCHAR(10), CURRENT_TIMESTAMP, 112)) \nWHERE NU_ANN = ? \nAND NU_EMI = ? ");


				String nombreArchivo = docObjBean.getNombreArchivo();

				String baseRuta= ConstantesUtil.RUTA_ARCHIVOS;
				String subRuta = docObjBean.getNuAnn() + "/" + docObjBean.getNuEmi();

				String rutaDestino = baseRuta + "/" + subRuta + "/" + nombreArchivo;

				File directorio = new File(baseRuta + "/" + subRuta);
				if (!directorio.exists()) {
					directorio.mkdirs(); // crea toda la estructura intermedia
				}


				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(rutaDestino);
					fos.write(docObjBean.getDocumento());
					fos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (fos != null) {
						try {
							fos.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}





				this.getJdbcTemplate().execute(sql2.toString(),
						(PreparedStatementCallback) new AbstractLobCreatingPreparedStatementCallback(
								(LobHandler) defaultLobHandler) {
							protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
								//lobCreator.setBlobAsBytes(ps, 1, docObjBean.getDocumento());
								ps.setString(1, docObjBean.getNombreArchivo());
								ps.setString(2, docObjBean.getNuAnn());
								ps.setString(3, docObjBean.getNuEmi());
							}
						});
				vReturn = "OK";
			}

		} catch (Exception e) {

			log.error("Error", e);
			e.printStackTrace();
			throw new Exception(e);
			// vReturn = e.getMessage();
		}

		return vReturn;
	}

	@Override
	public DocumentoEmiBean getEstadoDocumentoAudi(String pnuAnn, String pnuEmi) {
		// TODO Auto-generated method stub
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
		documentoEmiBean = (DocumentoEmiBean) this.getJdbcTemplate().queryForObject(sql.toString(),
				(RowMapper) BeanPropertyRowMapper.newInstance(DocumentoEmiBean.class), new Object[] { pnuAnn, pnuEmi });

		return documentoEmiBean;
	}

	@Override
	public String updRemitoResumenDestinatario(String pnuAnn, String pnuEmi, String vti_des, String vco_pri,
			String vnu_cant_des, String vresOriDes) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sqlUpd = new StringBuffer();
		sqlUpd.append("UPDATE IDOSGD.TDTX_REMITOS_RESUMEN SET ");
		sqlUpd.append("TI_EMI_DES = ?,CO_PRIORIDAD = ? ,nu_candes=? ");
		sqlUpd.append("WHERE NU_ANN = ? ");
		sqlUpd.append("AND NU_EMI = ? ");

		StringBuffer sqlUpd2 = new StringBuffer();
		sqlUpd2.append("UPDATE IDOSGD.TDTV_REMITOS SET DE_ORI_DES = ?\nWHERE NU_ANN = ? \nAND NU_EMI = ? ");

		this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { vti_des, vco_pri, vnu_cant_des, pnuAnn, pnuEmi });
		this.getJdbcTemplate().update(sqlUpd2.toString(), new Object[] { vresOriDes, pnuAnn, pnuEmi });
		vReturn = "OK";

		return vReturn;
	}

	@Override
	public String updRemitoResumenReferencia(String pnuAnn, String pnuEmi, String vti_ori, String vdeOriEmi) {
		// TODO Auto-generated method stub
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

		this.getJdbcTemplate().update(sqlUpd.toString(), new Object[] { vti_ori, pnuAnn, pnuEmi });
		this.getJdbcTemplate().update(sqlUpd2.toString(), new Object[] { vdeOriEmi, pnuAnn, pnuEmi });
		vReturn = "OK";
		return vReturn;
	}

	@Override
	public List<DestinoBean> getListaDestinosCodDepTipoDes(String nu_ann, String nu_emi) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DestinoBean> getListaDestinosCodPri(String nu_ann, String nu_emi) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		List<DestinoBean> list = new ArrayList<DestinoBean>();
		sql.append("SELECT DISTINCT co_dep_des, ");
		sql.append("ti_des ");
		sql.append("FROM IDOSGD.tdtv_destinos ");
		sql.append("WHERE es_eli = '0' ");
		sql.append("AND nu_ann = ? ");
		sql.append("AND nu_emi = ? ");
		sql.append("ORDER BY ti_des");

		list = this.getJdbcTemplate().query(sql.toString(),
				(RowMapper) BeanPropertyRowMapper.newInstance(DestinoBean.class), new Object[] { nu_ann, nu_emi });

		return list;
	}

	@Override
	public String getNumDestinos(String nu_ann, String nu_emi) {
		// TODO Auto-generated method stub
		String vReturn = "0";
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

	@Override
	public List<ReferenciaRemitoBean> getListaReferenciaRemitos(String nu_ann, String nu_emi) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		List<ReferenciaRemitoBean> list = new ArrayList<ReferenciaRemitoBean>();
		sql.append("SELECT DISTINCT b.ti_emi, b.co_dep_emi ");
		sql.append(
				"FROM   IDOSGD.tdtr_referencia a INNER JOIN IDOSGD.tdtv_remitos b ON a.nu_ann_ref = b.nu_ann AND    a.nu_emi_ref = b.nu_emi  ");
		sql.append("WHERE  ");
		sql.append("     a.nu_ann = ? ");
		sql.append("AND    a.nu_emi = ? ");
		sql.append("ORDER  BY b.ti_emi");
		list = this.getJdbcTemplate().query(sql.toString(),
				(RowMapper) BeanPropertyRowMapper.newInstance(ReferenciaRemitoBean.class),
				new Object[] { nu_ann, nu_emi });

		return list;
	}

	@Override
	public List<DestinoBean> getListaDestinosOriTipoDes(String nu_ann, String nu_emi) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		List<DestinoBean> list = new ArrayList<DestinoBean>();
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

		list = this.getJdbcTemplate().query(sql.toString(),
				(RowMapper) BeanPropertyRowMapper.newInstance(DestinoBean.class), new Object[] { nu_ann, nu_emi });

		return list;
	}

	@Override
	public List<ReferenciaRemitoBean> getOriReferenciaLista(String nu_ann, String nu_emi) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		List<ReferenciaRemitoBean> list = new ArrayList<ReferenciaRemitoBean>();
		sql.append("SELECT b.ti_emi, ");
		sql.append("CASE b.ti_emi ");
		sql.append("\tWHEN '02' THEN [IDOSGD].[PK_SGD_DESCRIPCION_DE_PROVEEDOR](b.nu_ruc_emi) ");
		sql.append("\tWHEN '03' THEN [IDOSGD].[PK_SGD_DESCRIPCION_ANI_SIMIL](b.nu_dni_emi) ");
		sql.append("\tWHEN '04' THEN [IDOSGD].[PK_SGD_DESCRIPCION_OTRO_ORIGEN](b.co_otr_ori_emi) ");
		sql.append("\tELSE ' ' ");
		sql.append("END co_dep_emi ");
		sql.append("FROM   IDOSGD.tdtr_referencia a ");
		sql.append("INNER JOIN\t   IDOSGD.tdtv_remitos b ON  a.nu_ann_ref = b.nu_ann AND    a.nu_emi_ref = b.nu_emi ");
		sql.append("WHERE ");
		sql.append("     a.nu_ann = ? ");
		sql.append("AND    a.nu_emi = ? ");
		sql.append("ORDER  BY b.ti_emi ");

		list = this.getJdbcTemplate().query(sql.toString(),
				(RowMapper) BeanPropertyRowMapper.newInstance(ReferenciaRemitoBean.class),
				new Object[] { nu_ann, nu_emi });

		return list;
	}

}
