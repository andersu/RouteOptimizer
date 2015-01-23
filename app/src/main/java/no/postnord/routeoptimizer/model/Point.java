package no.postnord.routeoptimizer.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Point implements Serializable {

	private double lat;
	private double lng;

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public LatLng toLatLng() {
		return new LatLng(lat, lng);
	}
}
