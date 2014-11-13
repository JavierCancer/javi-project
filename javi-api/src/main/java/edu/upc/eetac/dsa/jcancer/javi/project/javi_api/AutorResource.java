package edu.upc.eetac.dsa.jcancer.javi.project.javi_api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model.Autor;


@Path("/autor")
public class AutorResource {
	
	
	@Context
	private SecurityContext security;

	private DataSource ds = DataSourceSPA.getInstance().getDataSource();

	private String INSERT_AUTOR_QUERY = "insert into autores (autor) value (?)";

	@POST
	@Consumes(MediaType.JAVI_API_AUTOR)
	@Produces(MediaType.JAVI_API_AUTOR)
	public Autor createAutor(Autor author) { // CREATE
	//	validateAdmin();
		System.out.println("Creando Autor....");
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_AUTOR_QUERY,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, author.getAutor());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int autint = rs.getInt(1);
				author = getAutFromDatabase(Integer.toString(autint));
				System.out.println("id del nuevo autor: "+ author.getId());
				System.out.println("Name del nuevo autor: "+ author.getAutor());
			}
		} catch (SQLException e) {
			throw new ServerErrorException(e.getMessage(),
					Response.Status.INTERNAL_SERVER_ERROR);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
			}
		}
		return author;
	}
	private String GET_AUTOR_BY_ID_QUERY = "select * from autores where id=?";
	private Autor getAutFromDatabase(String auint) {
		

			Autor author = new Autor();

			Connection conn = null;
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}

			PreparedStatement stmt = null;
			try {
				stmt = conn.prepareStatement(GET_AUTOR_BY_ID_QUERY);
				stmt.setInt(1, Integer.valueOf(auint));
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					author.setId(rs.getInt("id"));
					author.setAutor(rs.getString("autor"));
				}
			} catch (SQLException e) {
				throw new ServerErrorException(e.getMessage(),
						Response.Status.INTERNAL_SERVER_ERROR);
			} finally {
				try {
					if (stmt != null)
						stmt.close();
					conn.close();
				} catch (SQLException e) {
				}
			}
			return author;

		}
		
	}
	

