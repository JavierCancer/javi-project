package edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model;

public class JaviError {
	
	private int status;
	private String message;

	public JaviError() {
		super();
	}

	public JaviError(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
