package pe.gob.serfor.osutd.sgd.repository.acceso.impl;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
//import pe.gob.serfor.wssisged.logic.jdbc.dao.SimpleJdbcDaoBase;
//import pe.gob.serfor.wssisged.logic.dao.UsuarioDao;

import pe.gob.serfor.osutd.sgd.repository.acceso.UsuarioDao;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

//import pe.gob.serfor.wssisged.logic.model.Usuario;
//import pe.gob.serfor.wssisged.utils.ConstantesUtil;

@Repository
public class UsuarioDaoImpl extends JdbcDaoSupport implements UsuarioDao {
	
	@Autowired
	@Qualifier("accesoDataSource")
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	protected final Log log = LogFactory.getLog(getClass());

	public Usuario findUsuario(String username, String clave, Integer codAplicacion) throws Exception {
		//System.out.println(codAplicacion+" codigo de aplicacion: ");
		String query = " exec [DBO].[PA_VALIDAR_USUARIO] ?,?,? ";

		try {

			String claveUsuario = cifrar(clave,"SgDPasswordSecretPasswor");
//			MapSqlParameterSource parameters = new MapSqlParameterSource();
			//Object[] params = new Object[] { username, claveUsuario, codAplicacion, "", 0, "" };
			//System.out.println(claveUsuario);
			Object[] params = new Object[] { username, claveUsuario, codAplicacion};

			Usuario prueba=this.getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<>(Usuario.class), params);
			//System.out.println(prueba.getUsername());

			return this.getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<>(Usuario.class), params);

		} catch (EmptyResultDataAccessException e) {
			log.error("Error Consulta BD.", e);
			return null;

		} catch (IncorrectResultSizeDataAccessException e) {

			log.error("Error en consulta ususario", e);
			return null;
		}

	}
	
	public Usuario findUserByCo(String username) throws Exception {

		String query = " exec [DBO].[PA_SELECT_USUARIO_BY_CODIGO] ?,?,? ";

		try {
//			MapSqlParameterSource parameters = new MapSqlParameterSource();
			Object[] params = new Object[] { username, 0, "" };

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
	public Usuario ObtenerDatosUsuario(String username) throws Exception {
		String query = " exec [DBO].[PA_OBTENERDATOSUSUARIO_X_USUARIO] ? ";

//		MapSqlParameterSource parameters = new MapSqlParameterSource();
		Object[] params = new Object[] { username };

		return this.getJdbcTemplate().queryForObject(query, new BeanPropertyRowMapper<>(Usuario.class), params);

	}


	public String cifrar(String sinCifrar, String key) throws Exception {
		byte[] bytes = sinCifrar.getBytes("UTF-8");
		Cipher aes = obtieneCipher(true, key);
		byte[] cifrado = aes.doFinal(bytes);
		return DatatypeConverter.printHexBinary(cifrado);
	}
	private Cipher obtieneCipher(boolean paraCifrar, String keyString) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.update(keyString.getBytes(StandardCharsets.UTF_8));
		byte[] keyBytes = Arrays.copyOf(digest.digest(), 16);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");

		Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
		if (paraCifrar) {
			aes.init(1, key);
		} else {
			aes.init(2, key);
		}

		return aes;
	}

}