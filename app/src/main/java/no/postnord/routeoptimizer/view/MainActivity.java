package no.postnord.routeoptimizer.view;

import android.app.Activity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

import no.postnord.routeoptimizer.R;
import no.postnord.routeoptimizer.model.DirectionResponse;
import no.postnord.routeoptimizer.model.Route;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@FragmentById
	CreateRouteFragment fragmentCreateRoute;

	public void showResult(DirectionResponse result) {
		Route route = result.getRoutes().get(0);
		if (route != null) {
			RouteLegsActivity_.intent(this).route(route).start();
			fragmentCreateRoute.moveCarButton();
		}
	}
}
