package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.integracion.DocumentoFileBean;
import pe.gob.serfor.osutd.sgd.repository.logic.EmiDocumentoAltaServiceDao;

//import pe.gob.serfor.wssisged.logic.bean.integracion.DocumentoFileBean;
//import pe.gob.serfor.wssisged.logic.dao.EmiDocumentoAltaServiceDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleJdbcDaoBase;

@Repository
public class EmiDocumentoAltaServiceDaoImpl extends JdbcDaoSupport implements EmiDocumentoAltaServiceDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public String cargaDocumentoEmi(String coUsu, String pnuAnn, String pnuEmi, DocumentoFileBean pfileAnexo) {
		// TODO Auto-generated method stub
		String vReturn = "NO_OK";

		return vReturn;
	}

}
