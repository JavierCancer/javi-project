package edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model;

import java.util.List;

import javax.ws.rs.core.Link;



public class Autor {
	private List<Link> links;
	private String autor;
	private int id;
	public List<Link> getLinks() {
		return links;
	}
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
