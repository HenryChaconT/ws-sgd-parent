package pe.gob.serfor.osutd.sgd.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Mensaje;
import pe.gob.serfor.osutd.sgd.repository.bean.logic.model.Usuario;
import pe.gob.serfor.osutd.sgd.service.logic.UsuarioService;

@Service("jwtUserDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	@Qualifier("passwordEncoder")
	PasswordEncoder passwordEncoder;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
	@Override
	public UserDetails loadUserByUsername(String credenciales) throws UsernameNotFoundException {
		Mensaje resultado = new Mensaje() ;
		try {
			
			String[] parts = credenciales.split("#");
			String username = "";
			String password = "";
			Integer application = null;
			if (parts.length > 1) {
				username = parts[0];
				password = parts[1];
				application = Integer.parseInt(parts[2]);
			} else {
				username = parts[0];
			}
			
			if (parts.length > 1) {
				resultado = usuarioService.validarCredencialesSGD(username, password, application);
			}else {
				
				Usuario usuario = usuarioService.findUserByCo(username);
				
				if(usuario != null) {
					
					return new User(usuario.getCoUsuario(), passwordEncoder.encode(usuario.getPASSWORD()), new ArrayList<>());
				}else {
					
					throw new UsernameNotFoundException("Usuario o contraseña inválidos");
				}
			}
			
			if (resultado != null) {
				
				throw new UsernameNotFoundException("Usuario o contraseña inválidos");
				
			}else {
				
				return new User(username, passwordEncoder.encode(password.trim()), new ArrayList<>());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UsernameNotFoundException("Usuario o contraseña inválidos");
		}
	}

//	https://www.javainuse.com/onlineBcrypt
//	serforotidev
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("usSgd".equals(username)) {
//			return new User(username, "$2a$10$Z1mJ463d7xHDQ3//8BXvg.HaAzzQE1KMSGsLibn7I0vtzMqssfi/q", new ArrayList<>());	
//		} else {
//			throw new UsernameNotFoundException("Usuario no encontrado con nombre de usuario: " + username);
//		}
//	}
	
}
