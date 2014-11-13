package edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.MediaType;
import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.JaviResource;

public class Sting {
	
	private List<Link> links;
	private int id;
	private String username;
	private String titulo;
	private String autor;
	private String lengua;
	private String edicion;
	private String editorial;
	private String fecha_edicion;
	private String fecha_impresion;
	
	
	public List<Link> getLinks() {
		return links;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getLengua() {
		return lengua;
	}


	public void setLengua(String lengua) {
		this.lengua = lengua;
	}


	public String getEdicion() {
		return edicion;
	}


	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public String getFecha_edicion() {
		return fecha_edicion;
	}


	public void setFecha_edicion(String fecha_edicion) {
		this.fecha_edicion = fecha_edicion;
	}


	public String getFecha_impresion() {
		return fecha_impresion;
	}


	public void setFecha_impresion(String fecha_impresion) {
		this.fecha_impresion = fecha_impresion;
	}


	public void setLinks(List<Link> links) {
		this.links = links;
	}


	

	
	
	
	}

