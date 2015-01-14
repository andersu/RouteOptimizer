package no.postnord.routeoptimizer.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Route {
    private List<Leg> legs;

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }


	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("legs", legs)
				.toString();
	}
}
