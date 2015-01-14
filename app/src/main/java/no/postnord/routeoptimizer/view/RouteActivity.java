package no.postnord.routeoptimizer.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.adapter.LegListAdapter;
import no.postnord.routeoptimizer.model.Leg;

@EActivity(R.layout.activity_route)
public class RouteActivity extends Activity {

	@Extra("legs")
	List<Leg> legs;


	@ViewById
	ListView listViewLegs;

	@Bean
	LegListAdapter legListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

	@AfterViews
	void initViews() {
		listViewLegs.setAdapter(legListAdapter);
		legListAdapter.setLegs(legs);
	}
}
