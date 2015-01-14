package no.postnord.routeoptimizer.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class DirectionResponse {

    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }


	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("routes", routes)
				.toString();
	}
}
