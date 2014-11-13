package edu.upc.eetac.dsa.jcancer.javi.project.javi_api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model.Resenya;
import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model.Sting;
import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model.StingCollection;

	@Path("/stings")
	public class JaviResource {
		@Context
		
		private SecurityContext security;
		private DataSource ds = DataSourceSPA.getInstance().getDataSource();

	

		@GET
		@Produces(MediaType.JAVI_API_STING_COLLECTION)
		
		
		public StingCollection getStings() {
			
			
			StingCollection stings = new StingCollection();
			Connection conn = null;
			Statement stmt = null;
			String sql;
			
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}

		
			try {
				stmt = conn.createStatement();
				sql = " select * from stings";
				
				ResultSet rs = stmt.executeQuery(sql);
				
				
				while (rs.next()) {
					Sting sting = new Sting();
					
					sting.setTitulo(rs.getString("titulo"));
					sting.setAutor(rs.getString("autor"));
					sting.setLengua(rs.getString("lengua"));
					sting.setEdicion(rs.getString("edicion"));
					sting.setEditorial(rs.getString("editorial"));
					sting.setFecha_edicion(rs.getString("fecha_edicion"));
					sting.setFecha_impresion(rs.getString("fecha_impresion"));
					
					stings.addSting(sting);
				}
			}	
					
			 catch (SQLException e)
			 {
				throw new ServerErrorException(e.getMessage(),
						Response.Status.INTERNAL_SERVER_ERROR);
				
			} 			 
			
			 finally {
				
				 try {
					if (stmt != null)
						stmt.close();
						conn.close();
				} 
				 
				 catch (SQLException e){
					 e.printStackTrace();	 
				 
				 } 	 
				 
			}
				
			

			return stings;
		}

		private String GET_STING_BY_ID_QUERY = "select * from stings where id=?";
	@GET
	@Path("/{stingid}")
	@Produces(MediaType.JAVI_API_STING_COLLECTION)
		
		public Sting getSting(@PathParam("stingid") int stingid) {
			Sting StingId = new Sting();

			Connection conn = null;
			Statement stmt = null;
			String sql;
			
			System.out.println("El id a seleccionar es:  " + stingid);
			try {
				conn = ds.getConnection();
			} catch (SQLException e) {
				throw new ServerErrorException("Could not connect to the database",
						Response.Status.SERVICE_UNAVAILABLE);
			}

			
			try {
				stmt = conn.createStatement();
				
				
				
				sql = " select * from stings where id='" + stingid +"'";
				
				
				
				ResultSet rs = stmt.executeQuery(sql);
				
				if (rs.next()) {
				
					
					StingId.setTitulo(rs.getString("titulo"));
					StingId.setAutor(rs.getString("autor"));
					StingId.setLengua(rs.getString("lengua"));
					StingId.setEdicion(rs.getString("edicion"));
					StingId.setEditorial(rs.getString("editorial"));
					StingId.setFecha_edicion(rs.getString("fecha_edicion"));
					StingId.setFecha_impresion(rs.getString("fecha_impresion"));
				
					
				} else {
				throw new NotFoundException("There's no sting with stingid="
				+ StingId);
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

			return StingId;
	}
	
	

	
	private String INSERT_AUTHOR_QUERY = "insert into stings (titulo, autor, lengua, edicion, editorial, fecha_edicion, fecha_impresion) values (?,?,?,?,?,?,?)";
	
	@POST
	@Consumes(MediaType.JAVI_API_STING)
	@Produces(MediaType.JAVI_API_STING)
	public Sting createSting(Sting sting) { // CREATE
		System.out.println("validaAdmin 2");
		validaAdmin();
	
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(INSERT_AUTHOR_QUERY,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, sting.getTitulo());
			stmt.setString(2, sting.getAutor());
			stmt.setString(3, sting.getLengua());
			stmt.setString(4, sting.getEdicion());
			stmt.setString(5, sting.getEditorial());
			stmt.setString(6, sting.getFecha_edicion());
			stmt.setString(7, sting.getFecha_impresion());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idlibro = rs.getInt(1);
				sting = getStingFromDatabase(Integer.toString(idlibro));
				System.out.println("db cargada correctamente");
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
		return sting;
	}


	private Sting getStingFromDatabase(String stings) {
		
		Sting StingId = new Sting();
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
			
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		
		
		try {
			
		
			stmt = conn.prepareStatement(GET_STING_BY_ID_QUERY);
			
			stmt.setInt(1, Integer.valueOf(stings));
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				StingId.setTitulo(rs.getString("titulo"));
				StingId.setAutor(rs.getString("autor"));
				StingId.setLengua(rs.getString("lengua"));
				StingId.setEdicion(rs.getString("edicion"));
				StingId.setEditorial(rs.getString("editorial"));
				StingId.setFecha_edicion(rs.getString("fecha_edicion"));
				StingId.setFecha_impresion(rs.getString("fecha_impresion"));
				
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
		
		return StingId;
	}
	
	private void validaAdmin() {
		if (!security.isUserInRole("administrador"))
			throw new ForbiddenException("Solo admins.");
		
				}	
	
	
	
	private String DELETE_LIBRO_QUERY = "delete from stings where id=?";

	@DELETE
	@Path("/{id}")
	public void deleteSting(@PathParam("id") String stingid) {
		System.out.println("validaAdmin 3");
		validaAdmin();
		
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_LIBRO_QUERY);
			stmt.setInt(1, Integer.valueOf(stingid));
			int rows = stmt.executeUpdate();			
			if (rows == 0)
				;// Deleting inexistent sting
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				conn.close();
			} catch (SQLException e) {
				
				System.out.println("borrado correctamente");
			}
		}
	}
	
	private String UPDATE_STING_QUERY = "update stings set titulo=ifnull(?, titulo), autor=ifnull(?, autor), lengua=ifnull(?, lengua), edicion=ifnull(?, edicion), editorial=ifnull(?, editorial) where id=?";

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.JAVI_API_STING)
	@Produces(MediaType.JAVI_API_STING)
	public Sting updateSting(@PathParam("id") String id, Sting sting) {
		System.out.println("Valida 1");
		validaAdmin();
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}
	//	validateUpdateSting(sting);
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(UPDATE_STING_QUERY);
			stmt.setString(1, sting.getTitulo());
			stmt.setString(2, sting.getAutor());
			stmt.setString(3, sting.getLengua());
			stmt.setString(4, sting.getEdicion());
			stmt.setString(5, sting.getEditorial());
			stmt.setInt(6, Integer.valueOf(id));

			int rows = stmt.executeUpdate();
			if (rows == 1)
				sting = getStingFromDatabase(id);
			else {
				throw new NotFoundException("There's no sting with id="
						+ id);
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

		return sting;
	}
/*	
Cualquier usuario con rol registrado puede crear, actualizar y eliminar una reseña
del libro que haya hecho él. Una reseña consta de:

- Nombre en el sistema (username) del usuario registrado que realiza la reseña.
- Nombre real del usuario registrado que realiza la reseña.
- Fecha y hora de la última edición de la reseña
- Texto de la reseña limitado a 500 caracteres.
	
*/	

//Crear resenya
	
	

/*		

	
	private String INSERT_RESENYA_QUERY = "insert into resenya(idsting, username, autor, resenya) values (?,?,?,?)";
	
	@POST
	@Path("/resenya/{idresenya}")
	@Consumes(MediaType.JAVI_API_STING)
	@Produces(MediaType.JAVI_API_STING)
	public Resenya createResenya (@PathParam("resenya") Resenya resenya) { // CREATE	
		Connection conn = null;
		System.out.println("llego aqui 1");
		try {
			conn = ds.getConnection();
			
		} catch (SQLException e) {
			
			throw new ServerErrorException("Could not connect to the database",
					
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		
		System.out.println("llego aqui 2");
		try {
			stmt = conn.prepareStatement(INSERT_RESENYA_QUERY,
					Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, resenya.getIdsting());
			stmt.setString(2, resenya.getUsername());
			stmt.setString(3, resenya.getAutor());
			stmt.setString(4, resenya.getResenya());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int idlibro = rs.getInt(1);
				resenya = getResenyaFromDatabase(Integer.toString(idlibro));
				System.out.println("db cargada correctamente");
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
		return resenya;
	}


	
	
	private String GET_RESENYA_BY_ID_STING = "select * from resenya where idresenya=?";
	
	private Resenya getResenyaFromDatabase(String idresenya) { // GET AUTHOR DATABASE

		Resenya resenya= new Resenya();

		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServerErrorException("Could not connect to the database",
					Response.Status.SERVICE_UNAVAILABLE);
		}

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(GET_RESENYA_BY_ID_STING);
			stmt.setInt(1, Integer.valueOf(idresenya));
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				resenya.setIdresenya(rs.getInt("idresenya"));
				resenya.setIdsting(rs.getInt("idsting"));
				resenya.setUsername(rs.getString("username"));
				resenya.setAutor(rs.getString("autor"));
				resenya.setResenya(rs.getString("resenya"));				
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
		return resenya;

	}	*/
	}
	
			
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
			
		
				
	
	

