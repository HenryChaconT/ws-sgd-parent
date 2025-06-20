package pe.gob.serfor.osutd.sgd.repository.logic.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.DatosUsuario;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.UsuarioSgd;
import pe.gob.serfor.osutd.sgd.repository.logic.UsuarioSgdDao;
import pe.gob.serfor.osutd.sgd.repository.logic.utils.Utilidad;

//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleSgdJdbcDao;
//
//import pe.gob.serfor.wssisged.logic.dao.UsuarioSgdDao;
//import pe.gob.serfor.wssisged.logic.model.DatosUsuario;
//import pe.gob.serfor.wssisged.logic.model.Mensaje;
//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.logic.model.UsuarioSgd;
//import pe.gob.serfor.wssisged.utils.Utilidad;

@Repository
public class UsuarioSgdDaoImpl extends JdbcDaoSupport implements UsuarioSgdDao {
	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	protected final Log log = LogFactory.getLog(getClass());

	@Override
	public Usuario ObtenerDatosUsuario(String username) throws Exception {
		//System.out.println(username+" obtener datos");
		String query = " exec [DBO].[PA_OBTENERDATOSUSUARIO_X_USUARIO] ? ";

		try {
			MapSqlParameterSource parameters = new MapSqlParameterSource();
			Object[] params = new Object[] { username };

			return this.getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<>(Usuario.class), params);

		} catch (EmptyResultDataAccessException e) {
			log.error("Error Consulta BD.", e);
			return null;

		} catch (IncorrectResultSizeDataAccessException e) {

			log.error("Error en consulta ususario", e);
			return null;
		}
	}

	@Override
	public void getRptaIdentificacion(Mensaje msg, UsuarioSgd usuario) throws Exception {

		if (msg == null) {
			msg = new Mensaje();
		}

		StringBuffer sql = new StringBuffer();
		sql.append(
				"SELECT cemp_codemp,cclave de_Password, CAST(CONVERT(CHAR(10),DFE_MOD_CLAVE, 126) AS DATE) FE_MOD_CLAVE,  ");
		sql.append(" coalesce(ES_USUARIO,'N') ES_ACTIVO, CAST(CONVERT(CHAR(10), GETDATE(), 126) AS DATE) FE_ACTUAL,");
		sql.append(" CURRENT_TIMESTAMP FULL_FECHA_ACTUAL, coalesce(NU_INTENTO,0) + 1 NRO_INTENTO,IN_AD as inAD   ");
		sql.append(" from IDOSGD.seg_usuarios1 where cod_user = ? ");

		DatosUsuario datosUsuario = null;

		try {
			datosUsuario = (DatosUsuario) this.getJdbcTemplate().queryForObject(sql.toString(),
					(RowMapper) BeanPropertyRowMapper.newInstance(DatosUsuario.class),
					new Object[] { usuario.getCoUsuario() });
			usuario.setCempCodemp(datosUsuario.getCempCodemp());
			usuario.setInAD(datosUsuario.getInAD());
			String esUsuario = datosUsuario.getEsActivo();
			boolean userBloqMaxIntentos = false;

			if (esUsuario != null && (esUsuario.equals("A") || esUsuario.equals("N") || esUsuario.equals("M"))) {
				boolean passOk = false;

				if (esUsuario.equals("M")) {
					usuario.setDePassword(usuario.getDePassword().toUpperCase());
					datosUsuario.setEsActivo("A");
				}

				if (usuario.getInAD().equals("1")) {
					msg.setCodigo("2829");
					msg.setDesMensaje("Validacipor Active Directory");

				} else {

					String dePass = Utilidad.getInstancia().cifrar(usuario.getDePassword(), "SgDPasswordSecretPasswor");
					if (dePass.equals(datosUsuario.getDePassword())) {
						passOk = true;
						usuario.setFeModClave(datosUsuario.getFeModClave());
						usuario.setEsUsuario(datosUsuario.getEsActivo());
						usuario.setFeActual(datosUsuario.getFeActual());
						usuario.setCempCodemp(datosUsuario.getCempCodemp());
						msg.setCodigo("00");
						msg.setDesMensaje("Usuario Valido");

					} else {

						msg.setCodigo("005");
						msg.setDesMensaje("El Usuario o la Contraseña incorrecta");
					}
					if (esUsuario.equals("A")) {
						if (passOk) {
							// desbloquearUsuario(usuario.getCoUsuario());
						} else {
							if (datosUsuario.getNroIntento() == 10) {
								userBloqMaxIntentos = true;
							}
							// incrementarNumeroIntentos(usuario.getCoUsuario());
						}
					}
				}
			} else if (esUsuario != null && esUsuario.equals("I")) {
				userBloqMaxIntentos = true;
			} else {
				msg.setCodigo("005");
				msg.setDesMensaje("Usuario Bloqueado.");
			}
			if (userBloqMaxIntentos) {
				usuario.setFullFechaActual(datosUsuario.getFullFechaActual());
				usuario.setdFecMod(datosUsuario.getdFecMod());
				msg.setCodigo("009");
				msg.setDesMensaje("Usuario bloqueado por mde intentos.");
			}
		} catch (EmptyResultDataAccessException e) {
			msg.setCodigo("005");
			msg.setDesMensaje("Usuario no existe");
		} catch (Exception e) {
			msg.setCodigo("5001");
			msg.setDesMensaje("Error interno de base de datos, no es posible iniciar session");
			throw e;
		}
	}

	@Override
	public void desencripta(Mensaje msg, UsuarioSgd usuario) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT fn_desencripta('");
		sql.append(usuario.getNuDni());
		sql.append("','Onpe01') as NU_DNI_USUARIO, ");
		sql.append(" fn_desencripta('");
		sql.append(usuario.getDePassword());
		sql.append("','Onpe01') as DE_PASSWORD FROM DUAL ");
		UsuarioSgd usr = null;
		try {
			usr = (UsuarioSgd) this.getJdbcTemplate().queryForObject(sql.toString(),
					(RowMapper) BeanPropertyRowMapper.newInstance(Usuario.class));
			usuario.setNuDni(usr.getNuDni());
			usuario.setDePassword(usr.getDePassword());
		} catch (Exception e) {
			msg.setCodigo("5004");
			msg.setDesMensaje("Error Interno, la instruccion SQL contiene errores");
		}
	}

}