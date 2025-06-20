package pe.gob.serfor.osutd.sgd.jwt;

import java.io.Serializable;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String application;

	// need default constructor for JSON Parsing
	public JwtRequest() {

	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public JwtRequest(String username, String password, String application) {
		this.username = username;
		this.password = password;
		this.application = application;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}
	
}
