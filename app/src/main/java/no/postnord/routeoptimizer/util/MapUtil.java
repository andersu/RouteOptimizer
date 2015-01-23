package no.postnord.routeoptimizer.util;

import android.graphics.Color;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import no.postnord.routeoptimizer.model.Bounds;
import no.postnord.routeoptimizer.model.Leg;
import no.postnord.routeoptimizer.model.Route;

public class MapUtil {
	public static void drawPolyLines(GoogleMap map, Route route) {
		List<LatLng> points = decodePoly(route.getOverviewPolyline().getPoints());

		for (int i = 0; i < points.size() - 1; i++) {
			LatLng src = points.get(i);
			LatLng dest = points.get(i + 1);
			map.addPolyline(new PolylineOptions()
					.add(new LatLng(src.latitude, src.longitude),
							new LatLng(dest.latitude, dest.longitude))
					.width(5).color(Color.BLUE).geodesic(true));
		}
	}

	public static void addMarkers(GoogleMap map, Route route) {
		Leg firstLeg = route.getLegs().get(0);
		map.addMarker(new MarkerOptions().position(firstLeg.getStartLocation().toLatLng()));

		for (Leg leg : route.getLegs()) {
			map.addMarker(new MarkerOptions().position(leg.getEndLocation().toLatLng()).title(leg.getEnd_address()));
		}
	}

	private static List<LatLng> decodePoly(String encoded) {

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

	public static void moveCamera(GoogleMap map, Bounds bounds) {
		int padding = 100; // offset from edges of the map in pixels
		CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(MapUtil.getLatLngBounds(bounds), padding);
		map.moveCamera(cu);
	}

	private static LatLngBounds getLatLngBounds(Bounds bounds) {
		LatLngBounds.Builder builder = new LatLngBounds.Builder();

		builder.include(bounds.getNortheast().toLatLng());
		builder.include(bounds.getSouthwest().toLatLng());

		return builder.build();
	}
}
