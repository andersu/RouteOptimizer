package no.postnord.routeoptimizer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Route implements Serializable {
	private Bounds bounds;
    private List<Leg> legs;

	@SerializedName("overview_polyline")
	private Polyline overviewPolyline;

	public Bounds getBounds() {
		return bounds;
	}

	public void setBounds(Bounds bounds) {
		this.bounds = bounds;
	}

	public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

	public Polyline getOverviewPolyline() {
		return overviewPolyline;
	}

	public void setOverviewPolyline(Polyline overviewPolyline) {
		this.overviewPolyline = overviewPolyline;
	}
}
