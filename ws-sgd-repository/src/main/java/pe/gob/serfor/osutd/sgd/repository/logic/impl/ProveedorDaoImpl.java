package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.ProveedorBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.PersonaJuridica;
import pe.gob.serfor.osutd.sgd.repository.logic.ProveedorDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.ProveedorBean;
//import pe.gob.serfor.wssisged.logic.dao.ProveedorDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleJdbcDaoBase;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.PersonaJuridica;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;

@Repository
public class ProveedorDaoImpl extends JdbcDaoSupport implements ProveedorDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public MethodResponseBean<String> insProveedor(ProveedorBean proveedor) {

		MethodResponseBean<String> request = new MethodResponseBean<String>();
		boolean exists = false;
		// TODO Auto-generated method stub
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append(" SELECT ISNULL(max(CPRO_RUC), 0) from IDOSGD.LG_PRO_PROVEEDOR  ");
		sqlQry.append(" WHERE CPRO_RUC =?  ");

		String vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
				new Object[] { proveedor.getNuRuc() });

		if (vReturn.trim().equals("0")) {
			exists = false;
		} else {
			exists = true;
		}
		// Si existe y FLG está para acutalizar
		if (exists) {
			if (proveedor.isFlgupdate()) {
				StringBuffer sql = new StringBuffer();
				sql.append("update IDOSGD.LG_PRO_PROVEEDOR set ");
				sql.append(
						"cpro_ruc=?, cpro_razsoc=?, cpro_domicil=?, cubi_coddep=?, cubi_codpro = ?, cubi_coddis= ? , cpro_telefo =?, cpro_email=? ");
				sql.append("where cpro_ruc=? ");

				this.getJdbcTemplate().update(sql.toString(),
						new Object[] { proveedor.getNuRuc(), proveedor.getDescripcion(), proveedor.getCproDomicil(),
								proveedor.getCubiCoddep(), proveedor.getCubiCodpro(), proveedor.getCubiCoddis(),
								proveedor.getCproTelefo(), proveedor.getCproEmail(), proveedor.getNuRuc() });
				request.setMessage("Persona Jurídica actualizado correctamente");
			} else {
				request.setSuccess(false);
				request.setMessage("Persona Jurídica ya se encuentra registrado");
			}

		} else {
			// en caso no existase registra
			StringBuffer sql = new StringBuffer();
			sql.append("insert into IDOSGD.LG_PRO_PROVEEDOR ");
			sql.append(
					"(cpro_ruc, dpro_fecins ,cpro_razsoc, cpro_domicil, cubi_coddep, cubi_codpro, cubi_coddis , cpro_telefo,cpro_email) ");
			sql.append("values (?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?) ");

			if (proveedor.getNuRuc().isEmpty()) {
				String seq = "SELECT RIGHT(REPLICATE('0', 11) + CAST((ISNULL(MAX(CAST(CPRO_RUC AS BIGINT)), 0) + 1) AS VARCHAR), 11) FROM IDOSGD.LG_PRO_PROVEEDOR";
				String keyCPRO_RUC = (String) this.getJdbcTemplate().queryForObject(seq, String.class);
				proveedor.setNuRuc(keyCPRO_RUC);
			}

			this.getJdbcTemplate().update(sql.toString(),
					new Object[] { proveedor.getNuRuc(), proveedor.getDescripcion(), proveedor.getCproDomicil(),
							proveedor.getCubiCoddep(), proveedor.getCubiCodpro(), proveedor.getCubiCoddis(),
							proveedor.getCproTelefo(), proveedor.getCproEmail() });
			request.setMessage("Persona Jurídica registrado correctamente");
		}
		return request;
	}

	@Override
	public String updProveedor(ProveedorBean proveedor, String codProveedor) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";

		StringBuffer sql = new StringBuffer();
		sql.append("update IDOSGD.LG_PRO_PROVEEDOR set ");
		sql.append(
				"cpro_ruc=?, cpro_razsoc=?, cpro_domicil=?, cubi_coddep=?, cubi_codpro = ?, cubi_coddis= ? , cpro_telefo =?, cpro_email=? ");
		sql.append("where cpro_ruc=? ");

		this.getJdbcTemplate().update(sql.toString(),
				new Object[] { proveedor.getNuRuc(), proveedor.getDescripcion(), proveedor.getCproDomicil(),
						proveedor.getCubiCoddep(), proveedor.getCubiCodpro(), proveedor.getCubiCoddis(),
						proveedor.getCproTelefo(), proveedor.getCproEmail(), proveedor.getNuRuc() });

		vReturn = "OK";
		return vReturn;
	}

	@Override
	public Boolean ProveedorExiste(String NuRuc) {
		// TODO Auto-generated method stub
		boolean exists = false;
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append(" SELECT ISNULL(max(CPRO_RUC), 0) from IDOSGD.LG_PRO_PROVEEDOR  ");
		sqlQry.append(" WHERE CPRO_RUC =?  ");

		String vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
				new Object[] { NuRuc });

		if (vReturn.trim().equals("0")) {
			exists = false;
		} else {
			exists = true;
		}
		return exists;
	}
	
	
	public void insertar(PersonaJuridica proveedor) throws Exception {

		// en caso no existase registra
		StringBuffer sql = new StringBuffer();
		sql.append("insert into IDOSGD.LG_PRO_PROVEEDOR ");
		sql.append(
				"(cpro_ruc, dpro_fecins ,cpro_razsoc, cpro_domicil, cubi_coddep, cubi_codpro, cubi_coddis , cpro_telefo,cpro_email) ");
		sql.append("values (?,CURRENT_TIMESTAMP,?,?,?,?,?,?,?) ");

		if (proveedor.getNuRuc().isEmpty()) {
			String seq = "SELECT RIGHT(REPLICATE('0', 11) + CAST((ISNULL(MAX(CAST(CPRO_RUC AS BIGINT)), 0) + 1) AS VARCHAR), 11) FROM IDOSGD.LG_PRO_PROVEEDOR";
			String keyCPRO_RUC = (String) this.getJdbcTemplate().queryForObject(seq, String.class);
			proveedor.setNuRuc(keyCPRO_RUC);
		}

		this.getJdbcTemplate().update(sql.toString(),
				new Object[] { proveedor.getNuRuc(), proveedor.getDescripcion(), proveedor.getCproDomicil(),
						proveedor.getCubiCoddep(), proveedor.getCubiCodpro(), proveedor.getCubiCoddis(),
						proveedor.getCproTelefo(), proveedor.getCproEmail() });
	
	
	
	}



public void actualizar(PersonaJuridica  proveedor) throws Exception {

	StringBuffer sql = new StringBuffer();
		sql.append("update IDOSGD.LG_PRO_PROVEEDOR set ");
		sql.append("cpro_ruc=?, cpro_razsoc=?, cpro_domicil=?, cubi_coddep=?, cubi_codpro = ?, cubi_coddis= ? , cpro_telefo =?, cpro_email=? ");
		sql.append("where cpro_ruc=? ");

			this.getJdbcTemplate().update(sql.toString(),
					new Object[] { proveedor.getNuRuc(), proveedor.getDescripcion(), proveedor.getCproDomicil(),
							proveedor.getCubiCoddep(), proveedor.getCubiCodpro(), proveedor.getCubiCoddis(),
							proveedor.getCproTelefo(), proveedor.getCproEmail(), proveedor.getNuRuc() });
	
}

}
