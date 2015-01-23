package no.postnord.routeoptimizer.model;

import java.io.Serializable;

public class Step implements Serializable {

	Polyline polyline;

	public Polyline getPolyline() {
		return polyline;
	}

	public void setPolyline(Polyline polyline) {
		this.polyline = polyline;
	}
}
