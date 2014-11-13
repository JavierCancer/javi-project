package edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model;

import java.util.List;

import javax.ws.rs.core.Link;

public class Resenya {
	private List<Link> links;
	private int idresenya;
	private int idsting;
	private String username;
	private String autor;
	private String resenya;
	
	
	
	public List<Link> getLinks() {
		return links;
	}



	public int getIdresenya() {
		return idresenya;
	}



	public void setIdresenya(int idresenya) {
		this.idresenya = idresenya;
	}



	public int getIdsting() {
		return idsting;
	}



	public void setIdsting(int idsting) {
		this.idsting = idsting;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public String getResenya() {
		return resenya;
	}



	public void setResenya(String resenya) {
		this.resenya = resenya;
	}



	public void setLinks(List<Link> links) {
		this.links = links;
	}



	

}
