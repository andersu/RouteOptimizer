package no.postnord.routeoptimizer.model;

import java.io.Serializable;

public class Polyline implements Serializable{

	private String points;

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
}
