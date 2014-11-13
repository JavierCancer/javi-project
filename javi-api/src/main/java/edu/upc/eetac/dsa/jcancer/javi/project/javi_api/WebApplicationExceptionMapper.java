package edu.upc.eetac.dsa.jcancer.javi.project.javi_api;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model.JaviError;

@Provider
public class WebApplicationExceptionMapper implements
		ExceptionMapper<WebApplicationException> {
	@Override
	public Response toResponse(WebApplicationException exception) {
		JaviError error = new JaviError(
				exception.getResponse().getStatus(), exception.getMessage());
		return Response.status(error.getStatus()).entity(error)
				.type(MediaType.JAVI_API_ERROR).build();
	}

}