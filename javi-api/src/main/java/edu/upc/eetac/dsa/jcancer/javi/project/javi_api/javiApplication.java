package edu.upc.eetac.dsa.jcancer.javi.project.javi_api;


import org.glassfish.jersey.linking.DeclarativeLinkingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class javiApplication extends ResourceConfig {
	public javiApplication() {
		super();
		register (DeclarativeLinkingFeature.class);
	}
}
