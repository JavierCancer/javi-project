package edu.upc.eetac.dsa.jcancer.javi.project.javi_api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model.JaviRootAPI;

@Path("/")
public class JaviRootAPIResource {
	@GET
	public JaviRootAPI getRootAPI() {
		JaviRootAPI api = new JaviRootAPI();
		return api;
	}

}
