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

import pe.gob.serfor.osutd.sgd.repository.bean.logic.ParametrosBusqBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo;
import pe.gob.serfor.osutd.sgd.repository.logic.DocAnexoDao;
//import pe.gob.serfor.wssisged.logic.bean.ParametrosBusqBean;
//import pe.gob.serfor.wssisged.logic.dao.DocAnexoDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.Anexo;

@Repository
public class DocAnexoDaoImpl extends JdbcDaoSupport implements DocAnexoDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1233325006385361879L;
	protected final Log log = LogFactory.getLog(getClass());

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
	public Anexo buscarAnexo(ParametrosBusqBean bean) throws Exception {
		StringBuilder cadena = new StringBuilder("");
		cadena.append(" SELECT ANX.NU_ANN, ANX.NU_EMI,ANX.DE_RUT_ORI, ANX.DE_DET AS VNOMDOC, ANX.NU_ANE,ANX.BL_DOC ");
		cadena.append("  from  IDOSGD.TDTV_ANEXOS ANX ");
		cadena.append(" where 	 ANX.NU_EMI = ? AND ");
		cadena.append(" ANX.NU_ANN = ? AND  ANX.TI_PUBLIC = '1' and  ANX.NU_ANE=?  ");
		try {

			Object[] params = new Object[] { bean.getNumEmi(), bean.getAnio(), bean.getNuAne() };
			Anexo anexo = this.getJdbcTemplate().queryForObject(cadena.toString(),
					BeanPropertyRowMapper.newInstance(Anexo.class), params);
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

}
