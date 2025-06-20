package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.TipoDocumento;
import pe.gob.serfor.osutd.sgd.repository.logic.TipoDocumentoDao;

//import pe.gob.serfor.wssisged.logic.dao.TipoDocumentoDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleJdbcDaoBase;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//
//import pe.gob.serfor.wssisged.logic.model.TipoDocumento;

@Repository
public class TipoDocumentoDaoImpl extends JdbcDaoSupport implements TipoDocumentoDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public List<TipoDocumento> listarDocumentosMP() throws Exception {

		StringBuilder sql = new StringBuilder("");
		sql.append(" SELECT CDOC_DESDOC descripcion,CDOC_TIPDOC codigo ");
		sql.append(
				" FROM  (SELECT  A.CDOC_TIPDOC, CASE  WHEN A.IN_USOABREVIA IS NOT NULL THEN UPPER(A.CDOC_DESDONOMREAL)  ELSE UPPER(A.CDOC_DESDOC) END  CDOC_DESDOC,");
		sql.append(
				"  A.CDOC_GRUPO FROM IDOSGD.SI_MAE_TIPO_DOC A  WHERE A.CDOC_INDBAJ ='0' and a.IN_MESAPARTES='1'  ) AS Q1 ");
		sql.append(" ");

		try {

			Object[] params = new Object[] {};
			List<TipoDocumento> list = this.getJdbcTemplate().query(sql.toString(),
					BeanPropertyRowMapper.newInstance(TipoDocumento.class), params);
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

}
