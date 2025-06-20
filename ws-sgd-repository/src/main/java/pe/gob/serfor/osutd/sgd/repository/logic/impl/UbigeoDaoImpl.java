package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Ubigeo;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDepartamento;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroDistrito;
import pe.gob.serfor.osutd.sgd.repository.bean.ubigeo.ParametroProvincia;
import pe.gob.serfor.osutd.sgd.repository.logic.UbigeoDao;

//import pe.gob.serfor.wssisged.logic.bean.ArchivoPrincipal;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDepartamento;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroDistrito;
//import pe.gob.serfor.wssisged.logic.bean.ubigeo.ParametroProvincia;
//import pe.gob.serfor.wssisged.logic.dao.UbigeoDao;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleJdbcDaoBase;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//import pe.gob.serfor.wssisged.logic.model.DocumentoSgd;
//import pe.gob.serfor.wssisged.logic.model.Ubigeo;

@Repository
public class UbigeoDaoImpl extends JdbcDaoSupport implements UbigeoDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public List<Ubigeo> buscarDepartamento(ParametroDepartamento parametro) throws Exception {
		StringBuilder cadena = new StringBuilder("");
		List<Object> params = new ArrayList<>();

		cadena.append("	select ubdep, ubprv, ubdis, ubloc ,coreg ,trim(nodep) nodep,");
		cadena.append(" trim(noprv) noprv , trim(nodis) nodis ,cpdis, stubi ,stsob ,feres, inubi, ub_inei,");
		cadena.append(
				" CCOD_TIPO_UBI  ambito  from IDOSGD.idtubias where ubprv='00' and  ubdis='00'  and coreg!='23' ");

		if (!StringUtils.isEmpty(parametro.getCodDep())) {
			cadena.append(" AND ubdep = ?");
			params.add(parametro.getCodDep());
		}

		if (!StringUtils.isEmpty(parametro.getDescripcion())) {
			cadena.append(" AND nodep LIKE ?");
			params.add("%" + parametro.getDescripcion() + "%"); // Correcto para `LIKE`
		}

		try {

			List<Ubigeo> lstDoc = this.getJdbcTemplate().query(cadena.toString(),
					(RowMapper) BeanPropertyRowMapper.newInstance(Ubigeo.class), params.toArray());

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
			// return null;
		}
	}

	@Override
	public List<Ubigeo> buscarProvincia(ParametroProvincia parametro) throws Exception {
		StringBuilder cadena = new StringBuilder();
		List<Object> params = new ArrayList<>();

		// Evitar valores nulos
		String desDepartamento = parametro.getDesDepartamento() == null ? "" : parametro.getDesDepartamento();
		String desProvincia = parametro.getDesProvincia() == null ? "" : parametro.getDesProvincia();

		cadena.append("SELECT ubdep, ubprv, ubdis, ubloc, coreg, TRIM(nodep) nodep, ");
		cadena.append("TRIM(noprv) noprv, TRIM(nodis) nodis, cpdis, stubi, stsob, feres, inubi, ub_inei, ");
		cadena.append("CCOD_TIPO_UBI ambito FROM IDOSGD.idtubias ");
		cadena.append("WHERE ubprv!='00' AND ubdis='00' AND coreg!='23' ");
		cadena.append("AND nodep LIKE ? AND noprv LIKE ? AND ubdep = ? ");

		// Agregar parámetros
		params.add("%" + desDepartamento + "%");
		params.add("%" + desProvincia + "%");
		params.add(parametro.getCodDep());

		if (!StringUtils.isEmpty(parametro.getCodProv())) {
			cadena.append("AND ubprv = ? ");
			params.add(parametro.getCodProv());
		}

		try {
			return this.getJdbcTemplate().query(
					cadena.toString(),
					new BeanPropertyRowMapper<>(Ubigeo.class),
					params.toArray()
			);
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
	public List<Ubigeo> buscarDistrito(ParametroDistrito parametro) throws Exception {
		StringBuilder cadena = new StringBuilder();
		List<Object> params = new ArrayList<>();

		// Evitar valores nulos
		String desDepartamento = parametro.getDesDepartamento() == null ? "" : parametro.getDesDepartamento();
		String desProvincia = parametro.getDesProvincia() == null ? "" : parametro.getDesProvincia();
		String desDistrito = parametro.getDesDistrito() == null ? "" : parametro.getDesDistrito();

		cadena.append("SELECT ubdep, ubprv, ubdis, ubloc, coreg, TRIM(nodep) nodep, ");
		cadena.append("TRIM(noprv) noprv, TRIM(nodis) nodis, cpdis, stubi, stsob, feres, inubi, ub_inei, ");
		cadena.append("CCOD_TIPO_UBI ambito FROM IDOSGD.idtubias ");
		cadena.append("WHERE ubprv!='00' AND ubdis!='00' AND coreg!='23' ");
		cadena.append("AND nodep LIKE ? AND noprv LIKE ? AND nodis LIKE ? AND ubdep = ? AND ubprv = ? ");

		// Agregar parámetros
		params.add("%" + desDepartamento + "%");
		params.add("%" + desProvincia + "%");
		params.add("%" + desDistrito + "%");
		params.add(parametro.getCodDep());
		params.add(parametro.getCodProv());

		try {
			return this.getJdbcTemplate().query(
					cadena.toString(),
					new BeanPropertyRowMapper<>(Ubigeo.class),
					params.toArray()
			);
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
