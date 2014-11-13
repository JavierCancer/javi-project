package edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model;


public class User {
	private String username;
	private String password;

	private boolean loginSuccessful;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return username;
	}

	public void setName(String name) {
		this.username = name;
	}
	
	public boolean isLoginSuccessful() {
		return loginSuccessful;
	}

	public void setLoginSuccessful(boolean loginSuccessful) {
		this.loginSuccessful = loginSuccessful;
	}
}

