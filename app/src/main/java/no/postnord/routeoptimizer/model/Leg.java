package no.postnord.routeoptimizer.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Leg implements Serializable {
	private TextValue distance;
	private TextValue duration;
	private String start_address;
	private String end_address;

	@SerializedName("start_location")
	private Point startLocation;

	@SerializedName("end_location")
	private Point endLocation;

	private List<Step> steps;

	public TextValue getDistance() {
		return distance;
	}

	public void setDistance(TextValue distance) {
		this.distance = distance;
	}

	public TextValue getDuration() {
		return duration;
	}

	public void setDuration(TextValue duration) {
		this.duration = duration;
	}

	public String getStart_address() {
		return start_address;
	}

	public void setStart_address(String start_address) {
		this.start_address = start_address;
	}

	public String getEnd_address() {
		return end_address;
	}

	public void setEnd_address(String end_address) {
		this.end_address = end_address;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	public Point getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Point startLocation) {
		this.startLocation = startLocation;
	}

	public Point getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(Point endLocation) {
		this.endLocation = endLocation;
	}
}
