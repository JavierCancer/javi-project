package edu.upc.eetac.dsa.jcancer.javi.project.javi_api.model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import org.glassfish.jersey.linking.Binding;
import org.glassfish.jersey.linking.InjectLink;
import org.glassfish.jersey.linking.InjectLink.Style;
import org.glassfish.jersey.linking.InjectLinks;

import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.MediaType;
import edu.upc.eetac.dsa.jcancer.javi.project.javi_api.JaviResource;

public class StingCollection {
	@InjectLinks({
			@InjectLink(resource = JaviResource.class, style = Style.ABSOLUTE, rel = "create-sting", title = "Create sting", type = MediaType.JAVI_API_STING),
			@InjectLink(value = "/stings?before={before}", style = Style.ABSOLUTE, rel = "previous", title = "Previous stings", type = MediaType.JAVI_API_STING_COLLECTION, bindings = { @Binding(name = "before", value = "${instance.oldestTimestamp}") }),
			@InjectLink(value = "/stings?after={after}", style = Style.ABSOLUTE, rel = "current", title = "Newest stings", type = MediaType.JAVI_API_STING_COLLECTION, bindings = { @Binding(name = "after", value = "${instance.newestTimestamp}") }) })
	private List<Link> links;
	private List<Sting> stings;
	private long newestTimestamp;
	private long oldestTimestamp;

	public StingCollection() {
		super();
		stings = new ArrayList<>();
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Sting> getStings() {
		return stings;
	}

	public void setStings(List<Sting> stings) {
		this.stings = stings;
	}

	public long getNewestTimestamp() {
		return newestTimestamp;
	}

	public void setNewestTimestamp(long newestTimestamp) {
		this.newestTimestamp = newestTimestamp;
	}

	public long getOldestTimestamp() {
		return oldestTimestamp;
	}

	public void setOldestTimestamp(long oldestTimestamp) {
		this.oldestTimestamp = oldestTimestamp;
	}
	public void addSting(Sting sting) {
		stings.add(sting);
	}

}