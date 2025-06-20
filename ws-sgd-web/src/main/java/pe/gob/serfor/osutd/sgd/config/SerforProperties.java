package pe.gob.serfor.osutd.sgd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;

@ConfigurationProperties(prefix = "serfor", ignoreUnknownFields = false)
public final class SerforProperties {
	
	private String sistema;
	
	private final CorsConfiguration cors = new CorsConfiguration();
	
	private Seguridad seguridad;
	
	private boolean https;
		
	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public boolean isHttps() {
		return https;
	}

	public void setHttps(boolean https) {
		this.https = https;
	}

	public CorsConfiguration getCors() {
		return cors;
	}
	
	public Seguridad getSeguridad() {
		return seguridad;
	}

	public void setSeguridad(Seguridad seguridad) {
		this.seguridad = seguridad;
	}

	public static final class Seguridad {
    	
    	private String ldap;
    	
    	public String getLdap() {
			return ldap;
		}

		public void setLdap(String ldap) {
			this.ldap = ldap;
		}
		
    }
}
