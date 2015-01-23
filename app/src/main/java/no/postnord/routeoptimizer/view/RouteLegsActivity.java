package no.postnord.routeoptimizer.view;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.model.Bounds;
import no.postnord.routeoptimizer.model.Leg;
import no.postnord.routeoptimizer.model.Route;

@EActivity(R.layout.activity_route_legs)
public class RouteLegsActivity extends FragmentActivity implements OnMapReadyCallback {

	@Extra("route")
	Route route;

	private RouteLegsFragment routeLegsFragment;

	@FragmentById
	MapFragment fragmentMap;

	@AfterViews
	void addRouteLegsFragment() {
		fragmentMap.getMapAsync(this);

		if (routeLegsFragment == null) {
			routeLegsFragment = RouteLegsFragment_.builder()
					.legs((Serializable) route.getLegs())
					.build();
		}

		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.list_fragment, routeLegsFragment)
				.commit();
	}

	@Override
	public void onMapReady(GoogleMap map) {
		Leg firstLeg = route.getLegs().get(0);
		map.addMarker(new MarkerOptions().position(firstLeg.getStartLocation().toLatLng()));

		for (Leg leg : route.getLegs()) {
			map.addMarker(new MarkerOptions().position(leg.getEndLocation().toLatLng()).title(leg.getEnd_address()));
		}

		List<LatLng> points = decodePoly(route.getOverviewPolyline().getPoints());

		int padding = 50; // offset from edges of the map in pixels
		CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(getLatLngBounds(route.getBounds()), padding);
		map.moveCamera(cu);

		for (int i = 0; i < points.size() - 1; i++) {
			LatLng src = points.get(i);
			LatLng dest = points.get(i + 1);
			map.addPolyline(new PolylineOptions()
					.add(new LatLng(src.latitude, src.longitude),
							new LatLng(dest.latitude, dest.longitude))
					.width(5).color(Color.BLUE).geodesic(true));
		}
	}

	private List<LatLng> decodePoly(String encoded) {

		List<LatLng> poly = new ArrayList<LatLng>();
		int index = 0, len = encoded.length();
		int lat = 0, lng = 0;

		while (index < len) {
			int b, shift = 0, result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lat += dlat;

			shift = 0;
			result = 0;
			do {
				b = encoded.charAt(index++) - 63;
				result |= (b & 0x1f) << shift;
				shift += 5;
			} while (b >= 0x20);
			int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
			lng += dlng;

			LatLng p = new LatLng((((double) lat / 1E5)),
					(((double) lng / 1E5)));
			poly.add(p);
		}

		return poly;
	}

	LatLngBounds getLatLngBounds(Bounds bounds) {
		LatLngBounds.Builder builder = new LatLngBounds.Builder();

		builder.include(bounds.getNortheast().toLatLng());
		builder.include(bounds.getSouthwest().toLatLng());

		return builder.build();
	}
}
