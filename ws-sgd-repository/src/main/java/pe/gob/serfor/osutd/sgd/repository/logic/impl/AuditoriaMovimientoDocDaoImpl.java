package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.AudiEstadosMovDocBean;
import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoObjBean;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.logic.AuditoriaMovimientoDocDao;

//import pe.gob.serfor.wssisged.logic.bean.integracion.AudiEstadosMovDocBean;
//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoObjBean;
//import pe.gob.serfor.wssisged.logic.dao.AuditoriaMovimientoDocDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleJdbcDaoBase;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.Usuario;

@Repository
public class AuditoriaMovimientoDocDaoImpl extends JdbcDaoSupport implements AuditoriaMovimientoDocDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public String audiVisualizaDocumento(DocumentoObjBean docVisualiza, Usuario usu) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sql = new StringBuffer();
		sql.append(
				"INSERT INTO IDOSGD.TDTV_VISUALIZA_DOC( NU_ANN, NU_EMI, FE_DML, DE_USER, DE_IPPC, DE_NAMEPC, DE_USERPC ) VALUES(?,?,GETDATE(),?,?,?,?)");

		int retorno = this.getJdbcTemplate().update(sql.toString(), new Object[] { docVisualiza.getNuAnn(),
				docVisualiza.getNuEmi(), usu.getCoUsuario(), usu.getIpPC(), usu.getNombrePC(), usu.getUsuPc() });
		vReturn = "OK";

		return vReturn;
	}

	@Override
	public String audiEstadoDocumento(AudiEstadosMovDocBean audi) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";
		StringBuffer sql = new StringBuffer();
		sql.append(
				"INSERT INTO IDOSGD.tdtv_estados_mov( NU_ANN, NU_EMI, NU_DES, FE_DML, TI_PROCESO, ES_PROCESO, DE_USER, DE_IPPC, DE_NAMEPC, DE_USERPC ) VALUES(?,?,?,CURRENT_TIMESTAMP,?,?,?,?,?,?)");

		this.getJdbcTemplate().update(sql.toString(),
				new Object[] { audi.getNuAnn(), audi.getNuEmi(), audi.getNuDes(), audi.getTiProceso(),
						audi.getEsProceso(), audi.getDeUser(), audi.getDeIpPc(), audi.getDeNamePc(),
						audi.getDeUserPc() });
		vReturn = "OK";

		return vReturn;
	}

}
