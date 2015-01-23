package no.postnord.routeoptimizer.view;

import android.support.v4.app.FragmentActivity;
import android.widget.ImageButton;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;

import java.io.Serializable;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.model.Route;
import no.postnord.routeoptimizer.util.MapUtil;

@EActivity(R.layout.activity_route_legs)
public class RouteLegsActivity extends FragmentActivity implements OnMapReadyCallback {

	@Extra("route")
	Route route;

	private RouteLegsFragment routeLegsFragment;

	@FragmentById
	MapFragment fragmentMap;

	@ViewById
	ImageButton imageButtonShowMap;

	@AfterViews
	void addRouteLegsFragment() {
		if (fragmentMap != null) {
			fragmentMap.getMapAsync(this);
		}

		if (routeLegsFragment == null) {
			routeLegsFragment = RouteLegsFragment_.builder()
					.legs((Serializable) route.getLegs())
					.build();
		}

		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.fragment_list, routeLegsFragment)
				.commit();
	}

	@Override
	public void onMapReady(GoogleMap map) {
		MapUtil.addMarkers(map, route);
		MapUtil.drawPolyLines(map, route);
	}

	@Click
	void imageButtonShowMapClicked() {
		MapActivity_.intent(this).route(route).start();
	}
}
