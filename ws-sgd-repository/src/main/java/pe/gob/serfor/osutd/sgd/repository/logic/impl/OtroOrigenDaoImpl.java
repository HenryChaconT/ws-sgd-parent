package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.OtroOrigenBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.OtroOrigen;
import pe.gob.serfor.osutd.sgd.repository.logic.OtroOrigenDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.MethodResponseBean;

//import pe.gob.serfor.wssisged.logic.bean.integracion.OtroOrigenBean;
//import pe.gob.serfor.wssisged.logic.dao.OtroOrigenDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.*;
//import pe.gob.serfor.wssisged.logic.model.OtroOrigen;
//import pe.gob.serfor.wssisged.utils.MethodResponseBean;

@Repository
public class OtroOrigenDaoImpl extends JdbcDaoSupport implements OtroOrigenDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public MethodResponseBean<String> insOtroOrigen(OtroOrigenBean otroOrigen) {
		// TODO Auto-generated method stub
		MethodResponseBean<String> request = new MethodResponseBean<String>();
		boolean exists = false;
		// TODO Auto-generated method stub
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append(" SELECT  ISNULL(max(CO_OTR_ORI), 0) from IDOSGD.tdtr_otro_origen  ");
		sqlQry.append("  WHERE NU_DOC_OTR_ORI= ? AND CO_TIP_OTR_ORI = ?  ");

		String vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
				new Object[] { otroOrigen.getNuDocOtrOri(), otroOrigen.getCoTipOtrOri() });

		if (vReturn.trim().equals("0")) {
			exists = false;
		} else {
			exists = true;
		}
		// Si existe y FLG está para acutalizar
		if (exists) {
			if (otroOrigen.isFlgupdate()) {
				StringBuffer sql = new StringBuffer();
				sql.append("update IDOSGD.tdtr_otro_origen set ");
				sql.append("nu_doc_otr_ori=?,co_tip_otr_ori=?,de_ape_pat_otr=?, ");
				sql.append("de_ape_mat_otr=?,de_nom_otr=?,de_raz_soc_otr=?,de_dir_otro_ori=?, ");
				sql.append("ub_dep=?,ub_pro=?,ub_dis=? ,de_email=?,de_telefo=?  where co_otr_ori=? ");

				this.getJdbcTemplate().update(sql.toString(),
						new Object[] { otroOrigen.getNuDocOtrOri(), otroOrigen.getCoTipOtrOri(),
								otroOrigen.getDeApePatOtr(), otroOrigen.getDeApeMatOtr(), otroOrigen.getDeNomOtr(),
								otroOrigen.getDeRazSocOtr(), otroOrigen.getDeDirOtroOri(), otroOrigen.getUbDep(),
								otroOrigen.getUbPro(), otroOrigen.getUbDis(), otroOrigen.getDeEmail(),
								otroOrigen.getDeTelefo(), vReturn });

				request.setMessage("Otros Orignes actualizado correctamente");
			} else {
				//request.setSuccess(false);
				request.setMessage("Otros Orignes ya se encuentra registrado");
			}
			request.setResult(vReturn);
		} else {
			StringBuffer sql = new StringBuffer();
			sql.append("insert into IDOSGD.tdtr_otro_origen( ");
			sql.append("co_otr_ori, nu_doc_otr_ori, co_tip_otr_ori, de_ape_pat_otr , ");
			sql.append("de_ape_mat_otr, de_nom_otr, de_raz_soc_otr, de_dir_otro_ori, ");
			sql.append("ti_origen ,ref_co_otr_ori, ub_dep, ub_pro, ub_dis, es_activo, de_email, de_telefo ");
			sql.append(")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

			String seq = "SELECT RIGHT(REPLICATE('0', 10) + CAST((ISNULL(MAX(CAST(co_otr_ori AS INT)), 0) + 1) AS VARCHAR), 10) FROM IDOSGD.tdtr_otro_origen";
			String id = (String) this.getJdbcTemplate().queryForObject(seq, String.class);
			this.getJdbcTemplate().update(sql.toString(),
					new Object[] { id, otroOrigen.getNuDocOtrOri(), otroOrigen.getCoTipOtrOri(),
							otroOrigen.getDeApePatOtr(), otroOrigen.getDeApeMatOtr(), otroOrigen.getDeNomOtr(),
							otroOrigen.getDeRazSocOtr(), otroOrigen.getDeDirOtroOri(), otroOrigen.getTiOrigen(),
							otroOrigen.getRefCoOtrOri(), otroOrigen.getUbDep(), otroOrigen.getUbPro(),
							otroOrigen.getUbDis(), otroOrigen.getEsActivo(), otroOrigen.getDeEmail(),
							otroOrigen.getDeTelefo() });
			request.setSuccess(true);
			request.setMessage("Otros Orignes registrado correctamente");
			request.setResult(id);
		}
		
		return request;
	}

	@Override
	public String updOtroOrigen(OtroOrigenBean otroOrigen) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sql = new StringBuffer();
		sql.append("update IDOSGD.tdtr_otro_origen set ");
		sql.append("nu_doc_otr_ori=?,co_tip_otr_ori=?,de_ape_pat_otr=?, ");
		sql.append("de_ape_mat_otr=?,de_nom_otr=?,de_raz_soc_otr=?,de_dir_otro_ori=?, ");
		sql.append("ub_dep=?,ub_pro=?,ub_dis=? ,de_email=?,de_telefo=?  where co_otr_ori=? ");

		this.getJdbcTemplate().update(sql.toString(),
				new Object[] { otroOrigen.getNuDocOtrOri(), otroOrigen.getCoTipOtrOri(), otroOrigen.getDeApePatOtr(),
						otroOrigen.getDeApeMatOtr(), otroOrigen.getDeNomOtr(), otroOrigen.getDeRazSocOtr(),
						otroOrigen.getDeDirOtroOri(), otroOrigen.getUbDep(), otroOrigen.getUbPro(),
						otroOrigen.getUbDis(), otroOrigen.getDeEmail(), otroOrigen.getDeTelefo(),
						otroOrigen.getCoOtrOri() });

		vReturn = "OK";

		return vReturn;
	}

	@Override
	public Boolean OtroOrigenExiste(String NuDocOtrOri) {
		// TODO Auto-generated method stub
		boolean exists = false;
		// TODO Auto-generated method stub
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append(" SELECT  ISNULL(max(CO_OTR_ORI), 0) from IDOSGD.tdtr_otro_origen  ");
		sqlQry.append("  WHERE co_otr_ori= ? ");

		String vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
				new Object[] { NuDocOtrOri });

		if (vReturn.trim().equals("0")) {
			exists = false;
		} else {
			exists = true;
		}
		return exists;
	}
	

	@Override
	public String OtroOrigenExisteCodigo(String coOtroOri) throws Exception {
		// TODO Auto-generated method stub
		String  exists = null;
		// TODO Auto-generated method stub
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append(" SELECT  ISNULL(max(CO_OTR_ORI), 0) from IDOSGD.tdtr_otro_origen  ");
		sqlQry.append("  WHERE co_otr_ori= ? ");

		String vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
				new Object[] { coOtroOri });

		
		return vReturn;
	}
	@Override
	public String OtroOrigenExisteNumero(String tipoDoc,String nuDocOtr) throws Exception {
		// TODO Auto-generated method stub
		String  exists = null;
		// TODO Auto-generated method stub
		StringBuffer sqlQry = new StringBuffer();
		sqlQry.append(" SELECT  ISNULL(max(CO_OTR_ORI), 0) from IDOSGD.tdtr_otro_origen  ");
		sqlQry.append("  WHERE  co_tip_otr_ori=? and  nu_doc_otr_ori= ? ");

		String vReturn = (String) this.getJdbcTemplate().queryForObject(sqlQry.toString(), String.class,
				new Object[] { tipoDoc,nuDocOtr });

		
		return vReturn;
	}
	
	public  void  actualizar(OtroOrigen otroOrigen)  throws Exception {
		
		StringBuffer sql = new StringBuffer();
			sql.append("update IDOSGD.tdtr_otro_origen set ");
			sql.append("nu_doc_otr_ori=?,co_tip_otr_ori=?,de_ape_pat_otr=?, ");
			sql.append("de_ape_mat_otr=?,de_nom_otr=?,de_raz_soc_otr=?,de_dir_otro_ori=?, ");
			sql.append("ub_dep=?,ub_pro=?,ub_dis=? ,de_email=?,de_telefo=?  where co_otr_ori=? ");

			this.getJdbcTemplate().update(sql.toString(),
					new Object[] { otroOrigen.getNuDocOtrOri(), otroOrigen.getCoTipOtrOri(),
							otroOrigen.getApePatOtr(), otroOrigen.getApeMatOtr(), otroOrigen.getNomOtr(),
							otroOrigen.getRazSocOtr(), otroOrigen.getDirOtroOri(), otroOrigen.getUbDep(),
							otroOrigen.getUbPro(), otroOrigen.getUbDis(), otroOrigen.getEmail(),
							otroOrigen.getTelefono(), otroOrigen.getCoOtrOri() });
	}
	
	
	public  void  insertar(OtroOrigen otroOrigen)  throws Exception {
	
	
	StringBuffer sql = new StringBuffer();
		sql.append("insert into IDOSGD.tdtr_otro_origen( ");
		sql.append("co_otr_ori, nu_doc_otr_ori, co_tip_otr_ori, de_ape_pat_otr , ");
		sql.append("de_ape_mat_otr, de_nom_otr, de_raz_soc_otr, de_dir_otro_ori, ");
		sql.append("ti_origen ,ref_co_otr_ori, ub_dep, ub_pro, ub_dis, es_activo, de_email, de_telefo ");
		sql.append(")values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

		String seq = "SELECT RIGHT(REPLICATE('0', 10) + CAST((ISNULL(MAX(CAST(co_otr_ori AS INT)), 0) + 1) AS VARCHAR), 10) FROM IDOSGD.tdtr_otro_origen";
		String id = (String) this.getJdbcTemplate().queryForObject(seq, String.class);
		this.getJdbcTemplate().update(sql.toString(),
				new Object[] { id, otroOrigen.getNuDocOtrOri(), otroOrigen.getCoTipOtrOri(),
						otroOrigen.getApePatOtr(), otroOrigen.getApeMatOtr(), otroOrigen.getNomOtr(),
						otroOrigen.getRazSocOtr(), otroOrigen.getDirOtroOri(), otroOrigen.getTiOrigen(),
						otroOrigen.getRefCoOtrOri(), otroOrigen.getUbDep(), otroOrigen.getUbPro(),
						otroOrigen.getUbDis(), otroOrigen.getEsActivo(), otroOrigen.getEmail(),
						otroOrigen.getTelefono() });
		
	}

}
