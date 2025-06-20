package pe.gob.serfor.osutd.sgd.repository.logic.impl;

//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

//import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Anexo;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Destinatario;
import pe.gob.serfor.osutd.sgd.repository.logic.DestinatarioDao;

//import pe.gob.serfor.wssisged.logic.dao.DestinatarioDao;
//
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.Destinatario;

@Repository
public class DestinatarioDaoImpl extends JdbcDaoSupport implements DestinatarioDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public List<Destinatario> buscarDestinatario(int anio, String nuEmi) throws Exception {

//		Map<String, Object> objectParam = new HashMap<String, Object>();

		StringBuilder cadena = new StringBuilder(" ");
		cadena.append(" SELECT co_dep_des, co_emp_des, ti_des, nu_dni_des, nu_ruc_des, co_otr_ori_des, ");
		cadena.append("	(CASE WHEN TI_DES='01' THEN IDOSGD.PK_SGD_DESCRIPCION_DE_NOM_EMP (CO_EMP_DES) ");
		cadena.append("  WHEN TI_DES='02' THEN IDOSGD.PK_SGD_DESCRIPCION_DE_NOM_EMP (NU_RUC_DES)  ");
		cadena.append("	 WHEN TI_DES='03' THEN IDOSGD.PK_SGD_DESCRIPCION_ANI_SIMIL (NU_DNI_DES) ");
		cadena.append(
				"	 WHEN TI_DES='04' THEN IDOSGD.PK_SGD_DESCRIPCION_OTRO_ORIGEN (CO_OTR_ORI_DES)    END)AS DESTINATARIO, ");
		cadena.append("	case   when ti_des='01' then IDOSGD.PK_SGD_DESCRIPCION_DE_DEPENDENCIA(co_dep_des) ");
		cadena.append("	 when ti_des='02' then 'RUC -'+ IDOSGD.PK_SGD_DESCRIPCION_DE_DEPENDENCIA(nu_ruc_des) ");
		cadena.append(" when ti_des='03' then 'CIUDADANO -'+ IDOSGD.PK_SGD_DESCRIPCION_DE_DEPENDENCIA(nu_dni_des) ");
		cadena.append("	when ti_des='04' then 'OTROS -'+ IDOSGD.PK_SGD_DESCRIPCION_DE_DEPENDENCIA(co_otr_ori_des) ");
		cadena.append("  when ti_des='05' then  IDOSGD.PK_SGD_DESCRIPCION_DE_NOM_EMP(CO_EMP_DES) END ");
		cadena.append("	 AS dependenciaDestino  FROM   IDOSGD.tdtv_destinos WHERE  es_eli = '0'");
//		cadena.append(" AND    nu_ann = :panio AND nu_emi = :pnuEmi ");
		cadena.append(" AND    nu_ann = ? AND nu_emi = ? ");

//		objectParam.put("panio", anio);
//		objectParam.put("pnuEmi", nuEmi);

		try {
//			List<Destinatario> lstDoc = this.namedParameterJdbcTemplateS.query(cadena.toString(), objectParam,
//					(RowMapper) BeanPropertyRowMapper.newInstance(Destinatario.class));
//			return list;
			Object[] params = new Object[] { anio, nuEmi };
			
			List<Destinatario> lstDoc = this.getJdbcTemplate().query(cadena.toString(), BeanPropertyRowMapper.newInstance(Destinatario.class), params);

			return lstDoc;

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
