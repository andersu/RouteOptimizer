package no.postnord.routeoptimizer.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Leg implements Serializable {
	private TextValue distance;
	private TextValue duration;
	private String start_address;
	private String end_address;

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

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("distance", distance)
				.append("duration", duration)
				.append("start_address", start_address)
				.append("end_address", end_address)
				.toString();
	}
}
