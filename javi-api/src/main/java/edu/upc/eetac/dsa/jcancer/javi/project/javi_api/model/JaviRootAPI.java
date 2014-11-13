package edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model;

import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.JaviRootAPIResource;
import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.MediaType;
import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.JaviResource;

public class JaviRootAPI {
	
	@InjectLinks({
		@InjectLink(resource = JaviRootAPIResource.class, style = Style.ABSOLUTE, rel = "self bookmark home", title = "javi_api Root API", method = "getRootAPI"),
		@InjectLink(resource = JaviResource.class, style = Style.ABSOLUTE, rel = "stings", title = "Latest stings", type = MediaType.JAVI_API_STING_COLLECTION),
		@InjectLink(resource = JaviResource.class, style = Style.ABSOLUTE, rel = "create-stings", title = "Latest stings", type = MediaType.JAVI_API_STING) })
	private List<Link> links;

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
}