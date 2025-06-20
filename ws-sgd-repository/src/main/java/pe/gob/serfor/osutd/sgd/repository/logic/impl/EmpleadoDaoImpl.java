package pe.gob.serfor.osutd.sgd.repository.logic.impl;

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

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Empleado;
import pe.gob.serfor.osutd.sgd.repository.logic.EmpleadoDao;

//import pe.gob.serfor.wssisged.logic.dao.EmpleadoDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.Empleado;

@Repository
public class EmpleadoDaoImpl extends JdbcDaoSupport implements EmpleadoDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public Empleado buscarEmpleado(String codEmpleado) throws Exception {

		StringBuilder cadena = new StringBuilder("");
		cadena.append(" SELECT E.CEMP_CODEMP co_Empleado, E.CEMP_APEPAT ap_Paterno, E.CEMP_APEMAT ap_Materno,  ");
		cadena.append(" E.CEMP_DENOM nombres, E.CEMP_NU_DNI dni,E.CEMP_TIPSEX sexo, E.FEMP_FECNAC fecha_Nacimiento,  ");
		cadena.append(
				" E.CEMP_EMAIL email,E.CEMP_EST_EMP estado, E.CEMP_CO_DEPEND co_Dependencia, E.CEMP_CO_CARGO coCargo, ");
		cadena.append(" IDOSGD.PK_SGD_DESCRIPCION_DE_DEPENDENCIA(E.CEMP_CO_DEPEND) de_Dependencia,");
		cadena.append(" IDOSGD.PK_SGD_DESCRIPCION_DE_CARGO(E.CEMP_CO_CARGO) de_Cargo ");
		cadena.append(" FROM IDOSGD.RHTM_PER_EMPLEADOS E where  E.CEMP_CODEMP=? ");

		try {
			Object[] params = { codEmpleado };

			Empleado parametro = this.getJdbcTemplate().queryForObject(cadena.toString(),
					new BeanPropertyRowMapper<>(Empleado.class), params);

			if (parametro != null) {
				return parametro;
			}
			return null;
		} catch (EmptyResultDataAccessException e) {

			log.error("error", e);
			e.printStackTrace();
			return null;
		} catch (IncorrectResultSizeDataAccessException e) {
			log.error("Error", e);
			return null;
		} catch (Exception e) {
			log.error("error", e);
		}

		return null;
	}

}
