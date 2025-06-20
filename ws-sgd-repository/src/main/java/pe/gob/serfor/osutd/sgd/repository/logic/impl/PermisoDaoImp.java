package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

//import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Permiso;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.PermisoUuario;
import pe.gob.serfor.osutd.sgd.repository.logic.PermisoDao;

//import pe.gob.serfor.wssisged.logic.dao.PermisoDao;
//
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.Permiso;
//import pe.gob.serfor.wssisged.logic.model.PermisoUuario;

@Repository
public class PermisoDaoImp extends JdbcDaoSupport implements PermisoDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public boolean obtenerPermiso(Permiso permiso) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("Select P.COD_USER,P.COD_APLICA,P.COD_OPC from  IDOSGD.SEG_USER_APLICA_OPC  P ");
		sql.append(" inner join IDOSGD.SEG_USUARIOS1 U ON (P.COD_USER=U.COD_USER AND U.ES_USUARIO='A') ");
		sql.append(" WHERE  U.CEMP_CODEMP=?  AND  P.ES_HABILITADO='1' AND COD_APLICA=?  AND P.COD_OPC=?  ");

		Permiso permisoBean = null;

		try {
			permisoBean = (Permiso) this.getJdbcTemplate().queryForObject(sql.toString(),
					(RowMapper) BeanPropertyRowMapper.newInstance(Permiso.class),
					new Object[] { permiso.getCempCodEmp(), permiso.getCodAplica(), permiso.getCodOpc() });
		} catch (EmptyResultDataAccessException e) {
			if (permisoBean == null) {
				return false;
			}

		} catch (Exception e) {

			throw e;
		}
		return true;

	}

	@Override
	public PermisoUuario tieneAcceso(String codAplicacion, String codUsuario) throws Exception {

		// activo=1
		// admin=1
		PermisoUuario permisoBean;
		StringBuffer sql = new StringBuffer();
		sql.append(
				" SELECT ES_ACTIVO,ES_ADMIN FROM IDOSGD.SEG_USER_APLICA WHERE COD_APLICA=? AND COD_USER=?  and ES_ACTIVO='1' ");

		try {
			permisoBean = (PermisoUuario) this.getJdbcTemplate().queryForObject(sql.toString(),
					(RowMapper) BeanPropertyRowMapper.newInstance(PermisoUuario.class),
					new Object[] { codAplicacion, codUsuario });

		} catch (EmptyResultDataAccessException e) {
			return null;

		}

		catch (Exception e) {

			throw e;
		}
		return permisoBean;

	}

}
