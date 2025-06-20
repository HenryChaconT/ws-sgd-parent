package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.persistence.internal.libraries.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoObjBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqAnexoBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo;
import pe.gob.serfor.osutd.sgd.repository.logic.AnexoDocumentoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.ConstantesUtil;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Utilidades;

//import pe.gob.serfor.wssisged.logic.bean.DocumentoAnexoBean;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqAnexoBean;
//import pe.gob.serfor.wssisged.logic.dao.AnexoDocumentoDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleJdbcDaoBase;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.Anexo;
//import pe.gob.serfor.wssisged.utils.Utilidades;

@Repository
public class AnexoDocumentoDaoImpl extends JdbcDaoSupport implements AnexoDocumentoDao {
	protected final Log log = LogFactory.getLog(getClass());

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public String insArchivoAnexo(
			final /* pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoAnexoBean */ DocumentoAnexoBean docAnexo,
			final InputStream archivoAnexo, final int size) {
		String vReturn = "NO_OK";

		StringBuffer sql = new StringBuffer();
		sql.append(
				"insert into IDOSGD.tdtv_anexos( nu_ann, nu_emi, nu_ane, de_det, de_rut_ori, co_use_cre, fe_use_cre, co_use_mod, fe_use_mod, feula,ti_public  ) values(?,?,?,?,?,?,GETDATE(),?,GETDATE(),IDOSGD.PK_SGD_DESCRIPCION_FORMAT(GETDATE(),'yyyymmdd'),'1')");

		DefaultLobHandler defaultLobHandler = new DefaultLobHandler();


		String nombreArchivo = Utilidades.fn_getCleanFileName(docAnexo.getDeDet());


		String baseRuta = ConstantesUtil.RUTA_ARCHIVOS;
		String subRuta = docAnexo.getNuAnn() + "/" + docAnexo.getNuEmi();
		//nombreArchivo = docObjBean.getNombreArchivo();

		String rutaDestino = baseRuta + "/" + subRuta + "/" + nombreArchivo;

		File directorio = new File(baseRuta + "/" + subRuta);
		if (!directorio.exists()) {
			directorio.mkdirs(); // crea toda la estructura intermedia
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(rutaDestino);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = archivoAnexo.read(buffer)) != -1) {
				fos.write(buffer, bytesRead == -1 ? 0 : 0, bytesRead);
			}

			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) fos.close();
				if (archivoAnexo != null) archivoAnexo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		this.getJdbcTemplate().execute(sql.toString(),
				(PreparedStatementCallback) new AbstractLobCreatingPreparedStatementCallback(
						(LobHandler) defaultLobHandler) {
					protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
						ps.setString(1, docAnexo.getNuAnn());
						ps.setString(2, docAnexo.getNuEmi());
						ps.setString(3, docAnexo.getNuAne());
						ps.setString(4, Utilidades.fn_getCleanFileName(docAnexo.getDeDet()));
						ps.setString(5, Utilidades.fn_getCleanFileName(docAnexo.getDeRutOri()));
						ps.setString(6, docAnexo.getCoUseCre());
						ps.setString(7, docAnexo.getCoUseMod());
						//lobCreator.setBlobAsBinaryStream(ps, 8, archivoAnexo, size);
					}
				});
		vReturn = "OK";
		return vReturn;
	}

	@Override
	public String updArchivoAnexo(
			final /* pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoAnexoBean */ DocumentoAnexoBean docAnexo,
			final InputStream archivoAnexo, final int size) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		System.out.println("ingreo a anexo");
		StringBuffer sql = new StringBuffer();
		sql.append(
				"update IDOSGD.tdtv_anexos set ti_public=?,nu_ann=?,nu_emi=?, nu_ane=?, de_det=?, de_rut_ori=?, co_use_cre=?, ");
		sql.append(" fe_use_cre=CURRENT_TIMESTAMP, co_use_mod=?, fe_use_mod=CURRENT_TIMESTAMP, ");
		sql.append(" feula=convert(varchar, GETDATE(), 103) where  nu_ann=? and nu_emi=? and nu_ane=? ");

		DefaultLobHandler defaultLobHandler = new DefaultLobHandler();

		String nombreArchivo = Utilidades.fn_getCleanFileName(docAnexo.getDeDet());

		String baseRuta = ConstantesUtil.RUTA_ARCHIVOS;
		String subRuta = docAnexo.getNuAnn() + "/" + docAnexo.getNuEmi();

		String rutaDestino = baseRuta + "/" + subRuta + "/" + nombreArchivo;

		File directorio = new File(baseRuta + "/" + subRuta);
		if (!directorio.exists()) {
			directorio.mkdirs(); // crea toda la estructura intermedia
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(rutaDestino);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = archivoAnexo.read(buffer)) != -1) {
				fos.write(buffer, bytesRead == -1 ? 0 : 0, bytesRead);
			}

			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) fos.close();
				if (archivoAnexo != null) archivoAnexo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		try {
			this.getJdbcTemplate().execute(sql.toString(),
					(PreparedStatementCallback) new AbstractLobCreatingPreparedStatementCallback(
							(LobHandler) defaultLobHandler) {
						protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
							ps.setString(1, docAnexo.getTiPublic());
							ps.setString(2, docAnexo.getNuAnn());
							ps.setString(3, docAnexo.getNuEmi());
							ps.setString(4, docAnexo.getNuAne());
							ps.setString(5, docAnexo.getDeDet());
							ps.setString(6, docAnexo.getDeRutOri());
							ps.setString(7, docAnexo.getCoUseCre());
							ps.setString(8, docAnexo.getCoUseMod());
							//lobCreator.setBlobAsBinaryStream(ps, 9, archivoAnexo, size);
							ps.setString(9, docAnexo.getNuAnn());
							ps.setString(10, docAnexo.getNuEmi());
							ps.setString(11, docAnexo.getNuAne());
						}
					});
			vReturn = "OK";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vReturn;
	}

	@Override
	public String getUltimoAnexo(String pnuAnn, String pnuEmi) {
		String result = null;

		StringBuffer sql = new StringBuffer();
		sql.append(
				" select CAST(ISNULL(max(nu_ane),0) + 1 AS VARCHAR(10)) nu_ane  from IDOSGD.tdtv_anexos  where nu_ann = ?  and nu_emi = ? ");

		try {
			result = (String) this.getJdbcTemplate().queryForObject(sql.toString(), String.class,
					new Object[] { pnuAnn, pnuEmi });
		} catch (Exception e) {
			result = "1";
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Anexo> listarAnexos(String anio, String nuEmi) throws Exception {

		StringBuilder cadena = new StringBuilder("");
		cadena.append(" SELECT ANX.NU_ANN, ANX.NU_EMI,ANX.DE_RUT_ORI, ANX.DE_DET AS VNOMDOC, ANX.NU_ANE,ANX.NU_ANE ");
		cadena.append("  from  IDOSGD.TDTV_ANEXOS ANX ");
		cadena.append(" where 	 ANX.NU_EMI = ? AND ");
		cadena.append(" ANX.NU_ANN = ? AND  ANX.TI_PUBLIC = '1'  order  by ANX.NU_ANE ");

		try {

			Object[] params = new Object[] { nuEmi, anio };
			List<Anexo> list = this.getJdbcTemplate().query(cadena.toString(),
					BeanPropertyRowMapper.newInstance(Anexo.class), params);
			return list;

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
	public Anexo buscarAnexo(ParametrosBusqAnexoBean bean) throws Exception {

		Anexo anexo = new Anexo();

		StringBuilder sql1 = new StringBuilder();
		sql1.append("SELECT CASE \n")
				.append("  WHEN EXISTS ( \n")
				.append("    SELECT 1 \n")
				.append("    FROM IDOSGD.TDTV_ANEXOS WITH (NOLOCK) \n")
				.append("    WHERE NU_ANN = ? AND NU_EMI = ? AND BL_DOC IS NOT NULL AND NU_ANE = ? \n")
				.append("  ) THEN 1 ELSE 0 \n")
				.append("END AS tieneArchivo");


		try {


			//int retorno;
			Object[] params1 = new Object[] { bean.getAnio(), bean.getNumEmi(), bean.getNuAne() };
			int retorno = this.getJdbcTemplate().queryForObject(sql1.toString(),
					BeanPropertyRowMapper.newInstance(Integer.class), params1);

			if (retorno == 0) {


				StringBuilder cadena = new StringBuilder("");
				cadena.append(" SELECT ANX.NU_ANN, ANX.NU_EMI,ANX.DE_RUT_ORI, ANX.DE_DET AS VNOMDOC, ANX.NU_ANE ");
				cadena.append("  from  IDOSGD.TDTV_ANEXOS ANX ");
				cadena.append(" where 	 ANX.NU_EMI = ? AND ");
				cadena.append(" ANX.NU_ANN = ? AND  ANX.TI_PUBLIC = '1' and  ANX.NU_ANE=?  ");

				DocumentoObjBean documentoObjBean=getNombreArchivoAnexo(bean.getAnio(),bean.getNumEmi(),bean.getNuAne());
				//System.out.println(documentoObjBean.getNombreArchivo()+" es nombre_Archivo");


				String baseRuta = ConstantesUtil.RUTA_ARCHIVOS; // ejemplo: /glassfish/tmppcm
				String subRuta = bean.getAnio() + "/" + bean.getNumEmi(); // ejemplo: 2025/0000006429
				//nombreArchivo = docObjBean.getNombreArchivo();

				String rutaDestino = baseRuta + "/" + subRuta + "/" + documentoObjBean.getNombreArchivo();

				File directorio = new File(baseRuta + "/" + subRuta);
				if (!directorio.exists()) {
					directorio.mkdirs(); // crea toda la estructura intermedia
				}



				byte[] docuemnto= leerArchivo(rutaDestino);

				try {


					Object[] params = new Object[] { bean.getNumEmi(), bean.getAnio(), bean.getNuAne() };
					anexo = this.getJdbcTemplate().queryForObject(cadena.toString(),
							BeanPropertyRowMapper.newInstance(Anexo.class), params);

					anexo.setBlDoc(docuemnto);

				} catch (EmptyResultDataAccessException e) {
					anexo = null;
				} catch (Exception e) {
					StringBuilder mensaje = new StringBuilder();
					mensaje.append("Anexo:" + bean.getAnio() + "." + bean.getNumEmi() + "." + bean.getNuAne());
					logger.error(mensaje, e);
				}



			}else {

				StringBuilder cadena = new StringBuilder("");
				cadena.append(" SELECT ANX.NU_ANN, ANX.NU_EMI,ANX.DE_RUT_ORI, ANX.DE_DET AS VNOMDOC, ANX.NU_ANE,ANX.BL_DOC ");
				cadena.append("  from  IDOSGD.TDTV_ANEXOS ANX ");
				cadena.append(" where 	 ANX.NU_EMI = ? AND ");
				cadena.append(" ANX.NU_ANN = ? AND  ANX.TI_PUBLIC = '1' and  ANX.NU_ANE=?  ");

				Object[] params = new Object[] { bean.getNumEmi(), bean.getAnio(), bean.getNuAne() };
				anexo = this.getJdbcTemplate().queryForObject(cadena.toString(),
						BeanPropertyRowMapper.newInstance(Anexo.class), params);
				//return anexo;
			}

			return anexo;

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


	public DocumentoObjBean getNombreArchivoAnexo(String pnuAnn, String pnuEmi, String pnuAnexo) {
		StringBuilder sql = new StringBuilder();
		sql.append("\tselect \n nu_ann,\n nu_emi,\n nu_ane,\n de_rut_ori nombre_Archivo,\n COALESCE(LEN(bl_doc),0) nu_Tamano\n from IDOSGD.tdtv_anexos WITH (NOLOCK) \nwhere nu_ann = ?\nand nu_emi = ?\nand nu_ane = ?");
		System.out.println("ingreso para ver si es null");
		System.out.println(pnuAnn + " " + pnuEmi + " " + pnuAnexo);
		DocumentoObjBean docObjBean = new DocumentoObjBean();

		try {
			docObjBean = (DocumentoObjBean) this.getJdbcTemplate().queryForObject(sql.toString(), (RowMapper) BeanPropertyRowMapper.newInstance(DocumentoObjBean.class), new Object[]{pnuAnn, pnuEmi, pnuAnexo});
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






}
