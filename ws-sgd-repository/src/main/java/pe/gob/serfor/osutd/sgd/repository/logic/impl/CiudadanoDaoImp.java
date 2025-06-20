package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.CitizenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ciudadano;
import pe.gob.serfor.osutd.sgd.repository.logic.CiudadanoDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.CitizenBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.CiudadanoBean;
//import pe.gob.serfor.wssisged.logic.dao.CiudadanoDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleJdbcDaoBase;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.Ciudadano;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;

@Repository
public class CiudadanoDaoImp extends JdbcDaoSupport implements CiudadanoDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public MethodResponseBean<String> insCiudadano(CitizenBean ciudadano, String codUsuario) {

		MethodResponseBean<String> request = new MethodResponseBean<String>();
		boolean exists = false;
		// TODO Auto-generated method stub
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append(" SELECT ISNULL(max(NULEM), 0) from IDOSGD.TDTX_ANI_SIMIL  ");
		sqlQry.append("  WHERE NULEM =?  ");

		String vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
				new Object[] { ciudadano.getNuLem() });

		if (vReturn.trim().equals("0")) {
			exists = false;
		} else {
			exists = true;
		}
		// Si existe y FLG está para acutalizar
		if (exists) {
			if (ciudadano.isFlgupdate()) {
				StringBuffer sql = new StringBuffer();
				sql.append("update IDOSGD.TDTX_ANI_SIMIL set ");
				sql.append("UBDEP=?, UBPRV=?, UBDIS=?, DEAPP=?, DEAPM = ?, DENOM= ?, ");
				sql.append("DEDOMICIL=?, DEEMAIL=?, DETELEFO=? ");
				sql.append("where NULEM=? ");
				this.getJdbcTemplate().update(sql.toString(),
						new Object[] { ciudadano.getUbDep(), ciudadano.getUbPrv(), ciudadano.getUbDis(), ciudadano.getDeApp(),
								ciudadano.getDeApm(), ciudadano.getDeNom(), ciudadano.getDeDomicil(), ciudadano.getDeEmail(),
								ciudadano.getDeTelefo(), ciudadano.getNuLem() });
				
				request.setMessage("Persona Jurídica actualizado correctamente");
			} else {
				request.setSuccess(false);
				request.setMessage("Persona Natural ya se encuentra registrado");
			}

		} else {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into IDOSGD.TDTX_ANI_SIMIL ");
			sql.append("(NULEM, UBDEP, UBPRV, UBDIS, DEAPP, DEAPM, DENOM, DEDOMICIL, DEEMAIL, DETELEFO) ");
			sql.append("values (?,?,?,?,?, ?,? ,?,?,?) ");

			this.getJdbcTemplate().update(sql.toString(),
					new Object[] { ciudadano.getNuLem(), ciudadano.getUbDep(), ciudadano.getUbPrv(),
							ciudadano.getUbDis(), ciudadano.getDeApp(), ciudadano.getDeApm(), ciudadano.getDeNom(),
							ciudadano.getDeDomicil(), ciudadano.getDeEmail(), ciudadano.getDeTelefo() });
			request.setSuccess(true);
			request.setMessage("Persona Natural registrado correstamente");
		}

		return request;
	}

	@Override
	public String updCiudadano(CitizenBean ciudadano, String codUsuario) {
		String vReturn = "NO_OK";

		StringBuffer sql = new StringBuffer();
		sql.append(" update IDOSGD.TDTX_ANI_SIMIL set ");
		sql.append("UBDEP=?, UBPRV=?, UBDIS=?, DEAPP=?, DEAPM = ?, DENOM= ?, ");
		sql.append("DEDOMICIL=?, DEEMAIL=?, DETELEFO=? ");
		sql.append("where NULEM=? ");
		this.getJdbcTemplate().update(sql.toString(),
				new Object[] { ciudadano.getUbDep(), ciudadano.getUbPrv(), ciudadano.getUbDis(), ciudadano.getDeApp(),
						ciudadano.getDeApm(), ciudadano.getDeNom(), ciudadano.getDeDomicil(), ciudadano.getDeEmail(),
						ciudadano.getDeTelefo(), ciudadano.getNuLem() });

		vReturn = "OK";
		return vReturn;
	}

	@Override
	public Boolean CiudadanoExiste(String NuLem) {
		// TODO Auto-generated method stub
		boolean exists = false;
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append(" SELECT ISNULL(max(NULEM), 0) from IDOSGD.TDTX_ANI_SIMIL  ");
		sqlQry.append("  WHERE NULEM =?  ");

		String vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
				new Object[] { NuLem });

		if (vReturn.trim().equals("0")) {
			exists = false;
		} else {
			exists = true;
		}
		return exists;
	}

	@Override
	public void actualizar(Ciudadano ciudadano, String codUsuario) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update IDOSGD.TDTX_ANI_SIMIL set ");
		sql.append("UBDEP=?, UBPRV=?, UBDIS=?, DEAPP=?, DEAPM = ?, DENOM= ?, ");
		sql.append("DEDOMICIL=?, DEEMAIL=?, DETELEFO=? ");
		sql.append("where NULEM=? ");
		this.getJdbcTemplate().update(sql.toString(),
				new Object[] { ciudadano.getUbDep(), ciudadano.getUbProv(), ciudadano.getUbDis(), ciudadano.getApePaterno(),
						ciudadano.getApeMaterno(), ciudadano.getNombre(), ciudadano.getDomicilio(), ciudadano.getEmail(),
						ciudadano.getTelefono(), ciudadano.getNuDni() });
	}

	@Override
	public void insertar(Ciudadano ciudadano, String codUsuario) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into IDOSGD.TDTX_ANI_SIMIL ");
		sql.append("(NULEM, UBDEP, UBPRV, UBDIS, DEAPP, DEAPM, DENOM, DEDOMICIL, DEEMAIL, DETELEFO) ");
		sql.append("values (?,?,?,?,?, ?,? ,?,?,?) ");

		this.getJdbcTemplate().update(sql.toString(),
				new Object[] { ciudadano.getNuDni(), ciudadano.getUbDep(), ciudadano.getUbProv(),
						ciudadano.getUbDis(), ciudadano.getApePaterno(), ciudadano.getApeMaterno(), ciudadano.getNombre(),
						ciudadano.getDomicilio(), ciudadano.getEmail(), ciudadano.getTelefono() });
	}

}
