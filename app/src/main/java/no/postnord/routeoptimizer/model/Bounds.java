package no.postnord.routeoptimizer.model;

import java.io.Serializable;

public class Bounds implements Serializable {

	private Point northeast;
	private Point southwest;

	public Point getNortheast() {
		return northeast;
	}

	public void setNortheast(Point northeast) {
		this.northeast = northeast;
	}

	public Point getSouthwest() {
		return southwest;
	}

	public void setSouthwest(Point southwest) {
		this.southwest = southwest;
	}
}
